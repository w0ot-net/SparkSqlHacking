package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.InformixTypeInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class InformixAdapter extends BaseDatastoreAdapter {
   public InformixAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("ProjectionInTableReferenceJoins");
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("CreateIndexesBeforeForeignKeys");
      this.supportedOptions.remove("AutoIncrementNullSpecification");
      this.supportedOptions.remove("AutoIncrementColumnTypeSpecification");
      this.supportedOptions.remove("ColumnOptions_NullsKeyword");
      this.supportedOptions.remove("DeferredConstraints");
   }

   public void initialiseDatastore(Object conn) {
      try {
         Statement st = ((Connection)conn).createStatement();

         try {
            st.execute(this.getSTRPOSDropFunction());
         } catch (SQLException e) {
            NucleusLogger.DATASTORE.warn(Localiser.msg("051027", new Object[]{e}));
         }

         try {
            st.execute(this.getSTRPOSFunction());
         } catch (SQLException e) {
            NucleusLogger.DATASTORE.warn(Localiser.msg("051027", new Object[]{e}));
         }

         st.close();
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_SCHEMA.warn("Exception when trying to initialise datastore", e);
         throw new NucleusDataStoreException(e.getMessage(), e);
      }
   }

   public String getVendorID() {
      return "informix";
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new InformixTypeInfo(rs);
   }

   public String getIdentifierQuoteString() {
      return "";
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      String useSerial = (String)this.getValueForProperty("datanucleus.rdbms.adapter.informixUseSerialForIdentity");
      return useSerial != null && useSerial.equalsIgnoreCase("true") ? "SELECT first 1 dbinfo('sqlca.sqlerrd1') from systables" : "SELECT first 1 dbinfo('serial8') from systables";
   }

   public String getAutoIncrementKeyword() {
      String useSerial = (String)this.getValueForProperty("datanucleus.rdbms.adapter.informixUseSerialForIdentity");
      return useSerial != null && useSerial.equalsIgnoreCase("true") ? "SERIAL" : "SERIAL8";
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getAddForeignKeyStatement(ForeignKey fk, IdentifierFactory factory) {
      if (fk.getName() != null) {
         String identifier = factory.getIdentifierInAdapterCase(fk.getName());
         return "ALTER TABLE " + fk.getTable().toString() + " ADD CONSTRAINT" + ' ' + fk + ' ' + "CONSTRAINT" + ' ' + identifier;
      } else {
         return "ALTER TABLE " + fk.getTable().toString() + " ADD " + fk;
      }
   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      if (ck.getName() != null) {
         String identifier = factory.getIdentifierInAdapterCase(ck.getName());
         return "ALTER TABLE " + ck.getTable().toString() + " ADD CONSTRAINT" + ' ' + ck + ' ' + "CONSTRAINT" + ' ' + identifier;
      } else {
         return "ALTER TABLE " + ck.getTable().toString() + " ADD " + ck;
      }
   }

   public String getDatastoreDateStatement() {
      return "SELECT FIRST 1 (CURRENT) FROM SYSTABLES";
   }

   private String getSTRPOSFunction() {
      return "create function NUCLEUS_STRPOS(str char(40),search char(40),from smallint) returning smallint\ndefine i,pos,lenstr,lensearch smallint;\nlet lensearch = length(search);\nlet lenstr = length(str);\nif lenstr=0 or lensearch=0 then return 0; end if;\nlet pos=-1;\nfor i=1+from to lenstr\nif substr(str,i,lensearch)=search then\nlet pos=i;\nexit for;\nend if;\nend for;\nreturn pos;\nend function;";
   }

   private String getSTRPOSDropFunction() {
      return "drop function NUCLEUS_STRPOS;";
   }

   public boolean isStatementTimeout(SQLException sqle) {
      return sqle.getErrorCode() == -213 ? true : super.isStatementTimeout(sqle);
   }
}

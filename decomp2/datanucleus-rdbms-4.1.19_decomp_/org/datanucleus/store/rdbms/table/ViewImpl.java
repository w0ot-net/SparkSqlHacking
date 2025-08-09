package org.datanucleus.store.rdbms.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.MissingColumnException;
import org.datanucleus.store.rdbms.exceptions.MissingTableException;
import org.datanucleus.store.rdbms.exceptions.NotAViewException;
import org.datanucleus.store.rdbms.exceptions.PrimaryKeyColumnNotAllowedException;
import org.datanucleus.store.rdbms.exceptions.UnexpectedColumnException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class ViewImpl extends AbstractTable {
   public ViewImpl(DatastoreIdentifier name, RDBMSStoreManager storeMgr) {
      super(name, storeMgr);
   }

   public void preInitialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
   }

   public void postInitialize(ClassLoaderResolver clr) {
      this.assertIsInitialized();
   }

   public boolean validate(Connection conn, boolean validateColumnStructure, boolean autoCreate, Collection autoCreateErrors) throws SQLException {
      this.assertIsInitialized();
      RDBMSSchemaHandler handler = (RDBMSSchemaHandler)this.storeMgr.getSchemaHandler();
      String tableType = handler.getTableType(conn, this);
      if (tableType == null) {
         throw new MissingTableException(this.getCatalogName(), this.getSchemaName(), this.toString());
      } else if (!tableType.equals("VIEW")) {
         throw new NotAViewException(this.toString(), tableType);
      } else {
         long startTime = System.currentTimeMillis();
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("031004", new Object[]{this}));
         }

         Map<DatastoreIdentifier, Column> unvalidated = new HashMap(this.columnsByIdentifier);

         for(RDBMSColumnInfo ci : this.storeMgr.getColumnInfoForTable(this, conn)) {
            DatastoreIdentifier colIdentifier = this.storeMgr.getIdentifierFactory().newIdentifier(IdentifierType.COLUMN, ci.getColumnName());
            Column col = (Column)unvalidated.get(colIdentifier);
            if (col == null) {
               if (!this.hasColumnName(colIdentifier)) {
                  throw new UnexpectedColumnException(this.toString(), ci.getColumnName(), this.getSchemaName(), this.getCatalogName());
               }
            } else if (validateColumnStructure) {
               col.validate(ci);
               unvalidated.remove(colIdentifier);
            } else {
               unvalidated.remove(colIdentifier);
            }
         }

         if (unvalidated.size() > 0) {
            throw new MissingColumnException(this, unvalidated.values());
         } else {
            this.state = 4;
            if (NucleusLogger.DATASTORE.isDebugEnabled()) {
               NucleusLogger.DATASTORE.debug(Localiser.msg("045000", System.currentTimeMillis() - startTime));
            }

            return false;
         }
      }
   }

   protected List getSQLDropStatements() {
      this.assertIsInitialized();
      ArrayList stmts = new ArrayList();
      stmts.add(this.dba.getDropViewStatement(this));
      return stmts;
   }

   protected synchronized void addColumnInternal(Column col) {
      if (col.isPrimaryKey()) {
         throw new PrimaryKeyColumnNotAllowedException(this.toString(), col.getIdentifier().toString());
      } else {
         super.addColumnInternal(col);
      }
   }
}

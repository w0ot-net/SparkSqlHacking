package org.datanucleus.store.rdbms.valuegenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.datanucleus.ExecutionContext;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.valuegenerator.AbstractDatastoreGenerator;
import org.datanucleus.store.valuegenerator.ValueGenerationBlock;
import org.datanucleus.store.valuegenerator.ValueGenerationException;
import org.datanucleus.store.valuegenerator.AbstractDatastoreGenerator.ConnectionPreference;
import org.datanucleus.util.NucleusLogger;

public class MaxGenerator extends AbstractRDBMSGenerator {
   public MaxGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 1;
   }

   public ValueGenerationBlock reserveBlock(long size) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      RDBMSStoreManager rdbmsMgr = (RDBMSStoreManager)this.storeMgr;
      SQLController sqlControl = rdbmsMgr.getSQLController();

      ValueGenerationBlock var8;
      try {
         String stmt = this.getStatement();
         ps = sqlControl.getStatementForUpdate(this.connection, stmt, false);
         rs = sqlControl.executeStatementQuery((ExecutionContext)null, this.connection, stmt, ps);
         if (rs.next()) {
            var8 = new ValueGenerationBlock(new Object[]{rs.getLong(1) + 1L});
            return var8;
         }

         var8 = new ValueGenerationBlock(new Object[]{1L});
      } catch (SQLException e) {
         NucleusLogger.VALUEGENERATION.warn("Exception thrown getting next value for MaxGenerator", e);
         throw new ValueGenerationException("Exception thrown getting next value for MaxGenerator", e);
      } finally {
         try {
            if (rs != null) {
               rs.close();
            }

            if (ps != null) {
               sqlControl.closeStatement(this.connection, ps);
            }
         } catch (SQLException var17) {
         }

      }

      return var8;
   }

   private String getStatement() {
      RDBMSStoreManager srm = (RDBMSStoreManager)this.storeMgr;
      StringBuilder stmt = new StringBuilder();
      stmt.append("SELECT max(");
      stmt.append(srm.getIdentifierFactory().getIdentifierInAdapterCase((String)this.properties.get("column-name")));
      stmt.append(") FROM ");
      stmt.append(srm.getIdentifierFactory().getIdentifierInAdapterCase((String)this.properties.get("table-name")));
      return stmt.toString();
   }

   public AbstractDatastoreGenerator.ConnectionPreference getConnectionPreference() {
      return ConnectionPreference.EXISTING;
   }
}

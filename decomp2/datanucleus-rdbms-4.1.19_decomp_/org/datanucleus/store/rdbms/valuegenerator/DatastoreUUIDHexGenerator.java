package org.datanucleus.store.rdbms.valuegenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.datanucleus.ExecutionContext;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.valuegenerator.ValueGenerationBlock;
import org.datanucleus.store.valuegenerator.ValueGenerationException;
import org.datanucleus.util.Localiser;

public final class DatastoreUUIDHexGenerator extends AbstractRDBMSGenerator {
   public DatastoreUUIDHexGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 10;
      if (this.properties != null && this.properties.get("key-cache-size") != null) {
         try {
            this.allocationSize = Integer.parseInt((String)this.properties.get("key-cache-size"));
         } catch (Exception var4) {
            throw new ValueGenerationException(Localiser.msg("040006", new Object[]{this.properties.get("key-cache-size")}));
         }
      }

   }

   public static Class getStorageClass() {
      return String.class;
   }

   protected synchronized ValueGenerationBlock reserveBlock(long size) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<String> oid = new ArrayList();
      RDBMSStoreManager srm = (RDBMSStoreManager)this.storeMgr;
      SQLController sqlControl = srm.getSQLController();

      ValueGenerationBlock var21;
      try {
         DatastoreAdapter dba = srm.getDatastoreAdapter();
         String stmt = dba.getSelectNewUUIDStmt();
         ps = sqlControl.getStatementForQuery(this.connection, stmt);

         for(int i = 1; (long)i < size; ++i) {
            rs = sqlControl.executeStatementQuery((ExecutionContext)null, this.connection, stmt, ps);
            if (rs.next()) {
               oid.add(rs.getString(1));
            }
         }

         var21 = new ValueGenerationBlock(oid);
      } catch (SQLException e) {
         throw new ValueGenerationException(Localiser.msg("040008", new Object[]{e.getMessage()}));
      } finally {
         try {
            if (rs != null) {
               rs.close();
            }

            if (ps != null) {
               sqlControl.closeStatement(this.connection, ps);
            }
         } catch (SQLException var18) {
         }

      }

      return var21;
   }
}

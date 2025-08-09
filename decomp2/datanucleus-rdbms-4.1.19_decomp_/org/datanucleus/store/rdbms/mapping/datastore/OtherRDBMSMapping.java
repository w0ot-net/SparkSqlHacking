package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class OtherRDBMSMapping extends AbstractDatastoreMapping {
   public OtherRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   protected void initialize() {
      this.initTypeInfo();
   }

   public int getJDBCType() {
      return 1111;
   }

   public void setObject(PreparedStatement ps, int exprIndex, Object value) {
      try {
         ps.setObject(exprIndex, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet resultSet, int exprIndex) {
      try {
         return resultSet.getObject(exprIndex);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Boolean", "" + exprIndex, this.column, e.getMessage()}), e);
      }
   }
}

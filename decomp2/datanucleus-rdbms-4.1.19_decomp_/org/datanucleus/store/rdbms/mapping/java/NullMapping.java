package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ExecutionContext;
import org.datanucleus.store.rdbms.RDBMSStoreManager;

public class NullMapping extends SingleFieldMapping {
   public NullMapping(RDBMSStoreManager storeMgr) {
      this.initialize(storeMgr, (String)null);
   }

   public Class getJavaType() {
      return null;
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return null;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
   }
}

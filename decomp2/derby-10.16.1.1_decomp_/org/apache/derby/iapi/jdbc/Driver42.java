package org.apache.derby.iapi.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.impl.jdbc.EmbedCallableStatement42;
import org.apache.derby.impl.jdbc.EmbedConnection;
import org.apache.derby.impl.jdbc.EmbedPreparedStatement42;
import org.apache.derby.impl.jdbc.EmbedResultSet;
import org.apache.derby.impl.jdbc.EmbedResultSet42;
import org.apache.derby.impl.jdbc.EmbedStatement;

public class Driver42 extends InternalDriver {
   public PreparedStatement newEmbedPreparedStatement(EmbedConnection var1, String var2, boolean var3, int var4, int var5, int var6, int var7, int[] var8, String[] var9) throws SQLException {
      return new EmbedPreparedStatement42(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public BrokeredConnection newBrokeredConnection(BrokeredConnectionControl var1) throws SQLException {
      return new BrokeredConnection42(var1);
   }

   public EmbedResultSet newEmbedResultSet(EmbedConnection var1, ResultSet var2, boolean var3, EmbedStatement var4, boolean var5) throws SQLException {
      return new EmbedResultSet42(var1, var2, var3, var4, var5);
   }

   public CallableStatement newEmbedCallableStatement(EmbedConnection var1, String var2, int var3, int var4, int var5) throws SQLException {
      return new EmbedCallableStatement42(var1, var2, var3, var4, var5);
   }
}

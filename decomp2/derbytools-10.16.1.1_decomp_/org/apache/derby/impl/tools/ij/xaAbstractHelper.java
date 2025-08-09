package org.apache.derby.impl.tools.ij;

import java.sql.Connection;
import java.sql.SQLException;

interface xaAbstractHelper {
   void XADataSourceStatement(ij var1, Token var2, Token var3, String var4) throws SQLException;

   void XAConnectStatement(ij var1, Token var2, Token var3, String var4) throws SQLException;

   void XADisconnectStatement(ij var1, String var2) throws SQLException;

   Connection XAGetConnectionStatement(ij var1, String var2) throws SQLException;

   void CommitStatement(ij var1, Token var2, Token var3, int var4) throws SQLException;

   void EndStatement(ij var1, int var2, int var3) throws SQLException;

   void ForgetStatement(ij var1, int var2) throws SQLException;

   void PrepareStatement(ij var1, int var2) throws SQLException;

   ijResult RecoverStatement(ij var1, int var2) throws SQLException;

   void RollbackStatement(ij var1, int var2) throws SQLException;

   void StartStatement(ij var1, int var2, int var3) throws SQLException;

   Connection DataSourceStatement(ij var1, Token var2, Token var3, Token var4, Token var5, String var6) throws SQLException;

   void CPDataSourceStatement(ij var1, Token var2, Token var3) throws SQLException;

   void CPConnectStatement(ij var1, Token var2, Token var3, String var4) throws SQLException;

   Connection CPGetConnectionStatement(ij var1, String var2) throws SQLException;

   void CPDisconnectStatement(ij var1, String var2) throws SQLException;

   void setFramework(String var1);
}

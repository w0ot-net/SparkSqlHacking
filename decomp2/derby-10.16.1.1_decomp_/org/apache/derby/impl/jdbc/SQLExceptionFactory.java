package org.apache.derby.impl.jdbc;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.sql.SQLTransactionRollbackException;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
import org.apache.derby.shared.common.error.ExceptionFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class SQLExceptionFactory extends ExceptionFactory {
   public SQLException getSQLException(String var1, String var2, SQLException var3, int var4, Throwable var5, Object... var6) {
      String var7 = StandardException.getSQLStateFromIdentifier(var2);
      StandardException var8 = this.wrapArgsForTransportAcrossDRDA(var2, var5, var6);
      Object var9;
      if (var7.startsWith("08")) {
         var9 = new SQLNonTransientConnectionException(var1, var7, var4, var8);
      } else if (var7.startsWith("22")) {
         var9 = new SQLDataException(var1, var7, var4, var8);
      } else if (var7.startsWith("23")) {
         if (var7.equals("23502")) {
            var9 = new SQLIntegrityConstraintViolationException(var1, var7, var4, var8);
         } else if (var7.equals("23513")) {
            var9 = new DerbySQLIntegrityConstraintViolationException(var1, var7, var4, var8, var6[1], var6[0]);
         } else {
            var9 = new DerbySQLIntegrityConstraintViolationException(var1, var7, var4, var8, var6[0], var6[1]);
         }
      } else if (var7.startsWith("28")) {
         var9 = new SQLInvalidAuthorizationSpecException(var1, var7, var4, var8);
      } else if (var7.startsWith("40")) {
         var9 = new SQLTransactionRollbackException(var1, var7, var4, var8);
      } else if (var7.startsWith("42")) {
         var9 = new SQLSyntaxErrorException(var1, var7, var4, var8);
      } else if (var7.startsWith("0A")) {
         var9 = new SQLFeatureNotSupportedException(var1, var7, var4, var8);
      } else if (!var7.equals("XCL52.S".substring(0, 5)) && !var7.equals("XBDA0.C.1".substring(0, 5))) {
         var9 = new SQLException(var1, var7, var4, var8);
      } else {
         var9 = new SQLTimeoutException(var1, var7, var4, var8);
      }

      SQLException var10 = var8.getNextException();
      if (var10 != null) {
         ((SQLException)var9).setNextException(var10);
      }

      if (var3 != null) {
         ((SQLException)var9).setNextException(var3);
      }

      return (SQLException)var9;
   }

   public final SQLException getSQLException(String var1, SQLException var2, Throwable var3, Object... var4) {
      String var5 = MessageService.getTextMessage(var1, var4);
      int var6 = StandardException.getSeverityFromIdentifier(var1);
      return this.getSQLException(var5, var1, var2, var6, var3, var4);
   }

   private StandardException wrapArgsForTransportAcrossDRDA(String var1, Throwable var2, Object[] var3) {
      if (var2 instanceof StandardException var4) {
         if (var1.equals(var4.getMessageId())) {
            return var4;
         }
      }

      return StandardException.newException(var1, var2, var3);
   }
}

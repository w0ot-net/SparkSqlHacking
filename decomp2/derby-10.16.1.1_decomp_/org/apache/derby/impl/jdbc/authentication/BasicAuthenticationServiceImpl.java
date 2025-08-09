package org.apache.derby.impl.jdbc.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.derby.authentication.UserAuthenticator;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.dictionary.PasswordHasher;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.shared.common.error.StandardException;

public final class BasicAuthenticationServiceImpl extends AuthenticationServiceBase implements UserAuthenticator {
   public boolean canSupport(Properties var1) {
      if (!this.requireAuthentication(var1)) {
         return false;
      } else {
         String var2 = PropertyUtil.getPropertyFromSet(var1, "derby.authentication.provider");
         return var2 == null || var2.length() == 0 || StringUtil.SQLEqualsIgnoreCase(var2, "BUILTIN");
      }
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      super.boot(var1, var2);

      try {
         MessageDigest var3 = MessageDigest.getInstance("SHA-1");
         var3.reset();
      } catch (NoSuchAlgorithmException var4) {
         throw Monitor.exceptionStartingModule(var4);
      }

      this.setAuthenticationService(this);
   }

   public boolean authenticateUser(String var1, String var2, String var3, Properties var4) throws SQLException {
      Object var5 = null;
      int var6 = 0;
      if (var1 == null) {
         return false;
      } else {
         Object var7 = null;
         Object var8 = null;
         String var13;
         if ((var13 = var4.getProperty("drdaSecMec")) != null) {
            var6 = Integer.parseInt(var13);
         }

         String var9 = "derby.user.".concat(var1);
         String var14 = this.getDatabaseProperty(var9);
         String var15;
         if (var14 != null) {
            if (var6 != 8) {
               try {
                  var15 = this.hashPasswordUsingStoredAlgorithm(var1, var2, var14);
               } catch (StandardException var11) {
                  throw Util.generateCsSQLException(var11);
               }
            } else {
               var14 = this.substitutePassword(var1, var14, var4, true);
               var15 = var2;
            }
         } else {
            try {
               Properties var10 = this.getDatabaseProperties();
               if (var10 != null) {
                  this.hashUsingDefaultAlgorithm(var1, var2, var10);
               }
            } catch (StandardException var12) {
               throw Util.generateCsSQLException(var12);
            }

            var14 = this.getSystemProperty(var9);
            var15 = var2;
            if (var14 != null && var6 == 8) {
               var14 = this.substitutePassword(var1, var14, var4, false);
            }
         }

         boolean var16 = var14 != null && var14.equals(var15);
         if (!var16 && var6 == 8) {
            throw Util.generateCsSQLException("08004.C.12");
         } else {
            return var16;
         }
      }
   }

   private String hashPasswordUsingStoredAlgorithm(String var1, String var2, String var3) throws StandardException {
      if (var3.startsWith("3b60")) {
         return this.hashPasswordSHA1Scheme(var2);
      } else {
         PasswordHasher var4 = new PasswordHasher(var3);
         return var4.hashAndEncode(var1, var2);
      }
   }
}

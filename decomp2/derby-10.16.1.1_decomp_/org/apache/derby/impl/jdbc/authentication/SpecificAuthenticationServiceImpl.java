package org.apache.derby.impl.jdbc.authentication;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import org.apache.derby.authentication.UserAuthenticator;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public class SpecificAuthenticationServiceImpl extends AuthenticationServiceBase {
   private String specificAuthenticationScheme;

   public boolean canSupport(Properties var1) {
      if (!this.requireAuthentication(var1)) {
         return false;
      } else if (PropertyUtil.nativeAuthenticationEnabled(var1)) {
         return false;
      } else {
         this.specificAuthenticationScheme = PropertyUtil.getPropertyFromSet(var1, "derby.authentication.provider");
         return this.specificAuthenticationScheme != null && this.specificAuthenticationScheme.length() != 0 && !StringUtil.SQLEqualsIgnoreCase(this.specificAuthenticationScheme, "BUILTIN") && !this.specificAuthenticationScheme.equalsIgnoreCase("LDAP");
      }
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      super.boot(var1, var2);

      Object var3;
      try {
         Class var11 = Class.forName(this.specificAuthenticationScheme);
         if (!UserAuthenticator.class.isAssignableFrom(var11)) {
            throw StandardException.newException("XBM0L.D", new Object[]{this.specificAuthenticationScheme, "org.apache.derby.authentication.UserAuthenticator"});
         }

         UserAuthenticator var5 = (UserAuthenticator)var11.getConstructor().newInstance();
         this.setAuthenticationService(var5);
         return;
      } catch (ClassNotFoundException var6) {
         var3 = var6;
      } catch (InstantiationException var7) {
         var3 = var7;
      } catch (IllegalAccessException var8) {
         var3 = var8;
      } catch (NoSuchMethodException var9) {
         var3 = var9;
      } catch (InvocationTargetException var10) {
         var3 = var10;
      }

      String var10000 = var3.getClass().getName();
      String var4 = var10000 + ": " + ((Throwable)var3).getMessage();
      throw StandardException.newException("XBM0M.D", new Object[]{this.specificAuthenticationScheme, var4});
   }
}

package org.apache.derby.impl.jdbc.authentication;

import java.util.Properties;
import org.apache.derby.authentication.UserAuthenticator;
import org.apache.derby.shared.common.error.StandardException;

public final class NoneAuthenticationServiceImpl extends AuthenticationServiceBase implements UserAuthenticator {
   public boolean canSupport(Properties var1) {
      return !this.requireAuthentication(var1);
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      super.boot(var1, var2);
      this.setAuthenticationService(this);
   }

   public boolean authenticateUser(String var1, String var2, String var3, Properties var4) {
      return true;
   }
}

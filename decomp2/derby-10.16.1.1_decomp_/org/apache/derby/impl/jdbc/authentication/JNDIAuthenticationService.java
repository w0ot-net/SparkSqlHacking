package org.apache.derby.impl.jdbc.authentication;

import java.util.Properties;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public class JNDIAuthenticationService extends AuthenticationServiceBase {
   private String authenticationProvider;

   public boolean canSupport(Properties var1) {
      if (!this.requireAuthentication(var1)) {
         return false;
      } else {
         this.authenticationProvider = PropertyUtil.getPropertyFromSet(var1, "derby.authentication.provider");
         return this.authenticationProvider != null && StringUtil.SQLEqualsIgnoreCase(this.authenticationProvider, "LDAP");
      }
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      super.boot(var1, var2);
      LDAPAuthenticationSchemeImpl var3 = new LDAPAuthenticationSchemeImpl(this, var2);
      this.setAuthenticationService(var3);
   }
}

package org.apache.hive.service.auth;

import javax.security.sasl.AuthenticationException;
import net.sf.jpam.Pam;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;

public class PamAuthenticationProviderImpl implements PasswdAuthenticationProvider {
   private final String pamServiceNames;

   PamAuthenticationProviderImpl() {
      HiveConf conf = new HiveConf();
      this.pamServiceNames = conf.getVar(ConfVars.HIVE_SERVER2_PAM_SERVICES);
   }

   public void Authenticate(String user, String password) throws AuthenticationException {
      if (this.pamServiceNames != null && !this.pamServiceNames.trim().isEmpty()) {
         String[] pamServices = this.pamServiceNames.split(",");

         for(String pamService : pamServices) {
            Pam pam = new Pam(pamService);
            boolean isAuthenticated = pam.authenticateSuccessful(user, password);
            if (!isAuthenticated) {
               throw new AuthenticationException("Error authenticating with the PAM service: " + pamService);
            }
         }

      } else {
         throw new AuthenticationException("No PAM services are set.");
      }
   }
}

package org.apache.hive.service.auth;

import javax.security.sasl.AuthenticationException;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.util.ReflectionUtils;

public class CustomAuthenticationProviderImpl implements PasswdAuthenticationProvider {
   private final PasswdAuthenticationProvider customProvider;

   CustomAuthenticationProviderImpl() {
      HiveConf conf = new HiveConf();
      Class<? extends PasswdAuthenticationProvider> customHandlerClass = conf.getClass(ConfVars.HIVE_SERVER2_CUSTOM_AUTHENTICATION_CLASS.varname, PasswdAuthenticationProvider.class);
      this.customProvider = (PasswdAuthenticationProvider)ReflectionUtils.newInstance(customHandlerClass, conf);
   }

   public void Authenticate(String user, String password) throws AuthenticationException {
      this.customProvider.Authenticate(user, password);
   }
}

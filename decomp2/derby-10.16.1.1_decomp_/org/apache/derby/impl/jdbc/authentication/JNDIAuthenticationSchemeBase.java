package org.apache.derby.impl.jdbc.authentication;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.authentication.UserAuthenticator;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.shared.common.i18n.MessageService;

public abstract class JNDIAuthenticationSchemeBase implements UserAuthenticator {
   protected final JNDIAuthenticationService authenticationService;
   protected String providerURL;
   private AccessFactory store;
   protected Properties initDirContextEnv;

   public JNDIAuthenticationSchemeBase(JNDIAuthenticationService var1, Properties var2) {
      this.authenticationService = var1;
      this.setInitDirContextEnv(var2);
      this.setJNDIProviderProperties();
   }

   protected abstract void setJNDIProviderProperties();

   private void setInitDirContextEnv(Properties var1) {
      this.initDirContextEnv = new Properties();
      if (var1 != null) {
         Enumeration var2 = var1.propertyNames();

         while(var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            if (var3.startsWith("java.naming.")) {
               this.initDirContextEnv.put(var3, var1.getProperty(var3));
            }
         }
      }

   }

   protected static final SQLException getLoginSQLException(Exception var0) {
      String var1 = MessageService.getTextMessage("08004", new Object[]{var0});
      SQLException var2 = new SQLException(var1, "08004", 40000);
      return var2;
   }
}

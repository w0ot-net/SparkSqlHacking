package org.apache.ivy.util.url;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.util.Credentials;
import org.apache.ivy.util.Message;

public final class CredentialsStore {
   private static final Map KEYRING = new HashMap();
   private static final Set SECURED_HOSTS = new HashSet();
   public static final CredentialsStore INSTANCE = new CredentialsStore();

   private CredentialsStore() {
   }

   public void addCredentials(String realm, String host, String userName, String passwd) {
      if (userName != null) {
         Credentials c = new Credentials(realm, host, userName, passwd);
         Message.debug("credentials added: " + c);
         KEYRING.put(c.getKey(), c);
         SECURED_HOSTS.add(host);
      }
   }

   public Credentials getCredentials(String realm, String host) {
      String key = Credentials.buildKey(realm, host);
      Message.debug("try to get credentials for: " + key);
      return (Credentials)KEYRING.get(key);
   }

   public boolean hasCredentials(String host) {
      return SECURED_HOSTS.contains(host);
   }
}

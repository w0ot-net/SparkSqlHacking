package org.apache.zookeeper.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.zookeeper.data.ClientInfo;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.apache.zookeeper.server.auth.ProviderRegistry;

public final class AuthUtil {
   private AuthUtil() {
   }

   public static String getUser(Id id) {
      AuthenticationProvider provider = ProviderRegistry.getProvider(id.getScheme());
      return provider == null ? null : provider.getUserName(id.getId());
   }

   public static String getUsers(List authInfo) {
      if (authInfo == null) {
         return null;
      } else {
         String formatted = (String)authInfo.stream().map(AuthUtil::getUser).filter((name) -> name != null && !name.trim().isEmpty()).collect(Collectors.joining(","));
         return formatted.isEmpty() ? null : formatted;
      }
   }

   public static List getClientInfos(List authInfo) {
      List<ClientInfo> clientAuthInfo = new ArrayList(authInfo.size());
      authInfo.forEach((id) -> {
         String user = getUser(id);
         clientAuthInfo.add(new ClientInfo(id.getScheme(), user == null ? "" : user));
      });
      return clientAuthInfo;
   }
}

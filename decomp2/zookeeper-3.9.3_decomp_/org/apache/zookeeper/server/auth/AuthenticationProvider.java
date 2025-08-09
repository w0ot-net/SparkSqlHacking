package org.apache.zookeeper.server.auth;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.ServerCnxn;

public interface AuthenticationProvider {
   String getScheme();

   KeeperException.Code handleAuthentication(ServerCnxn var1, byte[] var2);

   default List handleAuthentication(HttpServletRequest request, byte[] authData) {
      return new ArrayList();
   }

   boolean matches(String var1, String var2);

   boolean isAuthenticated();

   boolean isValid(String var1);

   default String getUserName(String id) {
      return id;
   }
}

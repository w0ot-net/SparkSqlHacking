package org.sparkproject.jetty.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.security.Credential;

public class UserStore extends AbstractLifeCycle {
   protected final Map _users = new ConcurrentHashMap();

   public void addUser(String username, Credential credential, String[] roles) {
      this._users.put(username, new User(username, credential, roles));
   }

   public void removeUser(String username) {
      this._users.remove(username);
   }

   public UserPrincipal getUserPrincipal(String username) {
      User user = (User)this._users.get(username);
      return user == null ? null : user.getUserPrincipal();
   }

   public List getRolePrincipals(String username) {
      User user = (User)this._users.get(username);
      return user == null ? null : user.getRolePrincipals();
   }

   public String toString() {
      return String.format("%s@%x[users.count=%d]", this.getClass().getSimpleName(), this.hashCode(), this._users.size());
   }

   protected class User {
      protected UserPrincipal _userPrincipal;
      protected List _rolePrincipals = Collections.emptyList();

      protected User(String username, Credential credential, String[] roles) {
         this._userPrincipal = new UserPrincipal(username, credential);
         this._rolePrincipals = Collections.emptyList();
         if (roles != null) {
            this._rolePrincipals = (List)Arrays.stream(roles).map(RolePrincipal::new).collect(Collectors.toList());
         }

      }

      protected UserPrincipal getUserPrincipal() {
         return this._userPrincipal;
      }

      protected List getRolePrincipals() {
         return this._rolePrincipals;
      }
   }
}

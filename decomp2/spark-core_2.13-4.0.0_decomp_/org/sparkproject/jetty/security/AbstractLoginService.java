package org.sparkproject.jetty.security;

import jakarta.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

public abstract class AbstractLoginService extends ContainerLifeCycle implements LoginService {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractLoginService.class);
   protected IdentityService _identityService = new DefaultIdentityService();
   protected String _name;
   protected boolean _fullValidate = false;

   protected abstract List loadRoleInfo(UserPrincipal var1);

   protected abstract UserPrincipal loadUserInfo(String var1);

   protected AbstractLoginService() {
      this.addBean(this._identityService);
   }

   public String getName() {
      return this._name;
   }

   public void setIdentityService(IdentityService identityService) {
      if (this.isRunning()) {
         throw new IllegalStateException("Running");
      } else {
         this.updateBean(this._identityService, identityService);
         this._identityService = identityService;
      }
   }

   public void setName(String name) {
      if (this.isRunning()) {
         throw new IllegalStateException("Running");
      } else {
         this._name = name;
      }
   }

   public String toString() {
      return String.format("%s@%x[%s]", this.getClass().getSimpleName(), this.hashCode(), this._name);
   }

   public UserIdentity login(String username, Object credentials, ServletRequest request) {
      if (username == null) {
         return null;
      } else {
         UserPrincipal userPrincipal = this.loadUserInfo(username);
         if (userPrincipal != null && userPrincipal.authenticate(credentials)) {
            List<RolePrincipal> roles = this.loadRoleInfo(userPrincipal);
            List<String> roleNames = new ArrayList();
            Subject subject = new Subject();
            userPrincipal.configureSubject(subject);
            if (roles != null) {
               roles.forEach((p) -> {
                  p.configureForSubject(subject);
                  roleNames.add(p.getName());
               });
            }

            subject.setReadOnly();
            return this._identityService.newUserIdentity(subject, userPrincipal, (String[])roleNames.toArray(new String[0]));
         } else {
            return null;
         }
      }
   }

   public boolean validate(UserIdentity user) {
      if (!this.isFullValidate()) {
         return true;
      } else {
         UserPrincipal fresh = this.loadUserInfo(user.getUserPrincipal().getName());
         if (fresh == null) {
            return false;
         } else if (user.getUserPrincipal() instanceof UserPrincipal) {
            return fresh.authenticate((UserPrincipal)user.getUserPrincipal());
         } else {
            throw new IllegalStateException("UserPrincipal not known");
         }
      }
   }

   public IdentityService getIdentityService() {
      return this._identityService;
   }

   public void logout(UserIdentity user) {
   }

   public boolean isFullValidate() {
      return this._fullValidate;
   }

   public void setFullValidate(boolean fullValidate) {
      this._fullValidate = fullValidate;
   }
}

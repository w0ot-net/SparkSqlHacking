package org.sparkproject.jetty.security;

import java.security.Principal;
import javax.security.auth.Subject;
import org.sparkproject.jetty.server.UserIdentity;

public interface IdentityService {
   String[] NO_ROLES = new String[0];

   Object associate(UserIdentity var1);

   void disassociate(Object var1);

   Object setRunAs(UserIdentity var1, RunAsToken var2);

   void unsetRunAs(Object var1);

   UserIdentity newUserIdentity(Subject var1, Principal var2, String[] var3);

   RunAsToken newRunAsToken(String var1);

   UserIdentity getSystemUserIdentity();
}

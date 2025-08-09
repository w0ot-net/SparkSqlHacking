package org.codehaus.commons.compiler;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import org.codehaus.commons.nullanalysis.NotNullByDefault;

public final class Sandbox {
   private final AccessControlContext accessControlContext;

   public Sandbox(PermissionCollection permissions) {
      this.accessControlContext = new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain((CodeSource)null, permissions)});
   }

   public Object confine(PrivilegedAction action) {
      return AccessController.doPrivileged(action, this.accessControlContext);
   }

   public Object confine(PrivilegedExceptionAction action) throws Exception {
      try {
         return AccessController.doPrivileged(action, this.accessControlContext);
      } catch (PrivilegedActionException pae) {
         throw pae.getException();
      }
   }

   static {
      if (System.getSecurityManager() == null) {
         Policy.setPolicy(new Policy() {
            @NotNullByDefault(false)
            public PermissionCollection getPermissions(CodeSource codesource) {
               for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
                  if ("sun.rmi.server.LoaderHandler".equals(element.getClassName()) && "loadClass".equals(element.getMethodName())) {
                     return new Permissions();
                  }
               }

               return super.getPermissions(codesource);
            }

            @NotNullByDefault(false)
            public boolean implies(ProtectionDomain domain, Permission permission) {
               return true;
            }
         });
         System.setSecurityManager(new SecurityManager());
      }

   }
}

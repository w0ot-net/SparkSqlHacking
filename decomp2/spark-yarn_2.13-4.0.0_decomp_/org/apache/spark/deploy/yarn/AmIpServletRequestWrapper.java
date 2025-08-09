package org.apache.spark.deploy.yarn;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class AmIpServletRequestWrapper extends HttpServletRequestWrapper {
   private final AmIpPrincipal principal;

   public AmIpServletRequestWrapper(HttpServletRequest request, AmIpPrincipal principal) {
      super(request);
      this.principal = principal;
   }

   public Principal getUserPrincipal() {
      return this.principal;
   }

   public String getRemoteUser() {
      return this.principal.getName();
   }

   public boolean isUserInRole(String role) {
      return false;
   }
}

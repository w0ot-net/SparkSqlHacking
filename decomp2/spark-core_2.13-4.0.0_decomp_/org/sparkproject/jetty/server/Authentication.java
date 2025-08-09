package org.sparkproject.jetty.server;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Authentication {
   Authentication UNAUTHENTICATED = new Authentication() {
      public String toString() {
         return "UNAUTHENTICATED";
      }
   };
   Authentication NOT_CHECKED = new Authentication() {
      public String toString() {
         return "NOT CHECKED";
      }
   };
   Authentication SEND_CONTINUE = new Challenge() {
      public String toString() {
         return "CHALLENGE";
      }
   };
   Authentication SEND_FAILURE = new Failure() {
      public String toString() {
         return "FAILURE";
      }
   };
   Authentication SEND_SUCCESS = new SendSuccess() {
      public String toString() {
         return "SEND_SUCCESS";
      }
   };

   public static class Failed extends QuietServletException {
      public Failed(String message) {
         super(message);
      }
   }

   public interface Challenge extends ResponseSent {
   }

   public interface Deferred extends LoginAuthentication, LogoutAuthentication {
      Authentication authenticate(ServletRequest var1);

      Authentication authenticate(ServletRequest var1, ServletResponse var2);
   }

   public interface Failure extends ResponseSent {
   }

   public interface LoginAuthentication extends Authentication {
      Authentication login(String var1, Object var2, ServletRequest var3);
   }

   public interface LogoutAuthentication extends Authentication {
      Authentication logout(ServletRequest var1);
   }

   public interface NonAuthenticated extends LoginAuthentication {
   }

   public interface ResponseSent extends Authentication {
   }

   public interface SendSuccess extends ResponseSent {
   }

   public interface User extends LogoutAuthentication {
      String getAuthMethod();

      UserIdentity getUserIdentity();

      boolean isUserInRole(UserIdentity.Scope var1, String var2);
   }

   public interface Wrapped extends Authentication {
      HttpServletRequest getHttpServletRequest();

      HttpServletResponse getHttpServletResponse();
   }
}

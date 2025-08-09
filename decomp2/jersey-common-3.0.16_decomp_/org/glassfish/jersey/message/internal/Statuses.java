package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.Status.Family;

public final class Statuses {
   public static Response.StatusType from(int code) {
      Response.StatusType result = Status.fromStatusCode(code);
      return (Response.StatusType)(result != null ? result : new StatusImpl(code, ""));
   }

   public static Response.StatusType from(int code, String reason) {
      return new StatusImpl(code, reason);
   }

   public static Response.StatusType from(Response.StatusType status, String reason) {
      return new StatusImpl(status.getStatusCode(), reason);
   }

   private Statuses() {
      throw new AssertionError("Instantiation not allowed.");
   }

   private static final class StatusImpl implements Response.StatusType {
      private final int code;
      private final String reason;
      private final Response.Status.Family family;

      private StatusImpl(int code, String reason) {
         this.code = code;
         this.reason = reason;
         this.family = Family.familyOf(code);
      }

      public int getStatusCode() {
         return this.code;
      }

      public String getReasonPhrase() {
         return this.reason;
      }

      public String toString() {
         return this.reason;
      }

      public Response.Status.Family getFamily() {
         return this.family;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof Response.StatusType)) {
            return false;
         } else {
            Response.StatusType status = (Response.StatusType)o;
            if (this.code != status.getStatusCode()) {
               return false;
            } else if (this.family != status.getFamily()) {
               return false;
            } else {
               if (this.reason != null) {
                  if (!this.reason.equals(status.getReasonPhrase())) {
                     return false;
                  }
               } else if (status.getReasonPhrase() != null) {
                  return false;
               }

               return true;
            }
         }
      }

      public int hashCode() {
         int result = this.code;
         result = 31 * result + (this.reason != null ? this.reason.hashCode() : 0);
         result = 31 * result + this.family.hashCode();
         return result;
      }
   }
}

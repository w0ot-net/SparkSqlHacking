package net.sf.jpam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PamReturnValue {
   public static final PamReturnValue PAM_SUCCESS = new PamReturnValue(0, "Successful function return.");
   public static final PamReturnValue PAM_OPEN_ERR = new PamReturnValue(1, "dlopen() failure when dynamically loading a service module.");
   public static final PamReturnValue PAM_SYMBOL_ERR = new PamReturnValue(2, "Symbol not found.");
   public static final PamReturnValue PAM_SERVICE_ERR = new PamReturnValue(3, "Error in service module.");
   public static final PamReturnValue PAM_SYSTEM_ERR = new PamReturnValue(4, "System error.");
   public static final PamReturnValue PAM_BUF_ERR = new PamReturnValue(5, "Memory buffer error.");
   public static final PamReturnValue PAM_PERM_DENIED = new PamReturnValue(6, "Permission denied.");
   public static final PamReturnValue PAM_AUTH_ERR = new PamReturnValue(7, "Authentication failure.");
   public static final PamReturnValue PAM_CRED_INSUFFICIENT = new PamReturnValue(8, "Can not access authentication data due to insufficient credentials.");
   public static final PamReturnValue PAM_AUTHINFO_UNAVAIL = new PamReturnValue(9, "Underlying authentication service can not retrieve authentication information.");
   public static final PamReturnValue PAM_USER_UNKNOWN = new PamReturnValue(10, "User not known to the underlying authentication module.");
   public static final PamReturnValue PAM_MAXTRIES = new PamReturnValue(11, "An authentication service has maintained a retry count which has been reached.  No further retries should be attempted.");
   public static final PamReturnValue PAM_NEW_AUTHTOK_REQD = new PamReturnValue(12, "New authentication token required. This is normally returned if the machine security policies require that the password should be changed because the password is NULL or it has aged.");
   public static final PamReturnValue PAM_ACCT_EXPIRED = new PamReturnValue(13, "User account has expired.");
   public static final PamReturnValue PAM_SESSION_ERR = new PamReturnValue(14, "Can not make/remove an entry for the specified session.");
   public static final PamReturnValue PAM_CRED_UNAVAIL = new PamReturnValue(15, "Underlying authentication service can not retrieve user credentials unavailable.");
   public static final PamReturnValue PAM_CRED_EXPIRED = new PamReturnValue(16, "User credentials expired.");
   public static final PamReturnValue PAM_CRED_ERR = new PamReturnValue(17, "Failure setting user credentials.");
   public static final PamReturnValue PAM_NO_MODULE_DATA = new PamReturnValue(18, "No module specific data is present.");
   public static final PamReturnValue PAM_CONV_ERR = new PamReturnValue(19, "Conversation error.");
   public static final PamReturnValue PAM_AUTHTOK_ERR = new PamReturnValue(20, "Authentication token manipulation error.");
   public static final PamReturnValue PAM_AUTHTOK_RECOVER_ERR = new PamReturnValue(21, "Authentication information cannot be recovered.");
   public static final PamReturnValue PAM_AUTHTOK_LOCK_BUSY = new PamReturnValue(22, "Authentication token lock busy.");
   public static final PamReturnValue PAM_AUTHTOK_DISABLE_AGING = new PamReturnValue(23, "Authentication token aging disabled.");
   public static final PamReturnValue PAM_TRY_AGAIN = new PamReturnValue(24, "Preliminary check by password service.");
   public static final PamReturnValue PAM_IGNORE = new PamReturnValue(25, "Ignore underlying account module regardless of whether the control flagis required, optional, or sufficient.");
   public static final PamReturnValue PAM_ABORT = new PamReturnValue(26, "Critical error (?module fail now request).");
   public static final PamReturnValue PAM_AUTHTOK_EXPIRED = new PamReturnValue(27, "User's authentication token has expired.");
   public static final PamReturnValue PAM_MODULE_UNKNOWN = new PamReturnValue(28, "Module is not known.");
   public static final PamReturnValue PAM_BAD_ITEM = new PamReturnValue(29, "Bad item passed to pam_*_item().");
   public static final PamReturnValue PAM_CONV_AGAIN = new PamReturnValue(30, "Conversation function is event driven and data is not available yet.");
   public static final PamReturnValue PAM_INCOMPLETE = new PamReturnValue(31, "Please call this function again to complete authentication stack. Before calling again, verify that conversation is completed.");
   private static final PamReturnValue[] PRIVATE_VALUES;
   private final String description;
   private final int id;
   public static final List VALUES;

   private PamReturnValue(int id, String description) {
      this.id = id;
      this.description = description;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof PamReturnValue)) {
         return false;
      } else {
         PamReturnValue pamReturnValue = (PamReturnValue)o;
         return this.id == pamReturnValue.id;
      }
   }

   public static PamReturnValue fromId(int id) throws IllegalArgumentException {
      int maxId = VALUES.size() - 1;
      if (id <= maxId && id >= 0) {
         return (PamReturnValue)VALUES.get(id);
      } else {
         throw new IllegalArgumentException("id " + id + " is not between 0 and " + maxId);
      }
   }

   public int hashCode() {
      return this.id;
   }

   public String toString() {
      return this.description;
   }

   static {
      PRIVATE_VALUES = new PamReturnValue[]{PAM_SUCCESS, PAM_OPEN_ERR, PAM_SYMBOL_ERR, PAM_SERVICE_ERR, PAM_SYSTEM_ERR, PAM_BUF_ERR, PAM_PERM_DENIED, PAM_AUTH_ERR, PAM_CRED_INSUFFICIENT, PAM_AUTHINFO_UNAVAIL, PAM_USER_UNKNOWN, PAM_MAXTRIES, PAM_NEW_AUTHTOK_REQD, PAM_ACCT_EXPIRED, PAM_SESSION_ERR, PAM_CRED_UNAVAIL, PAM_CRED_EXPIRED, PAM_CRED_ERR, PAM_NO_MODULE_DATA, PAM_CONV_ERR, PAM_AUTHTOK_ERR, PAM_AUTHTOK_RECOVER_ERR, PAM_AUTHTOK_LOCK_BUSY, PAM_AUTHTOK_DISABLE_AGING, PAM_TRY_AGAIN, PAM_IGNORE, PAM_ABORT, PAM_AUTHTOK_EXPIRED, PAM_MODULE_UNKNOWN, PAM_BAD_ITEM, PAM_CONV_AGAIN, PAM_INCOMPLETE};
      VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
   }
}

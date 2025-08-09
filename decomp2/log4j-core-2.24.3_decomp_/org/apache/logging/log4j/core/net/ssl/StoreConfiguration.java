package org.apache.logging.log4j.core.net.ssl;

import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.status.StatusLogger;

public class StoreConfiguration {
   static final String PKCS12 = "PKCS12";
   static final String JKS = "JKS";
   protected static final StatusLogger LOGGER = StatusLogger.getLogger();
   private String location;
   private PasswordProvider passwordProvider;

   public StoreConfiguration(final String location, final PasswordProvider passwordProvider) {
      this.location = location;
      this.passwordProvider = (PasswordProvider)Objects.requireNonNull(passwordProvider, "passwordProvider");
   }

   /** @deprecated */
   @Deprecated
   public StoreConfiguration(final String location, final char[] password) {
      this(location, (PasswordProvider)(new MemoryPasswordProvider(password)));
   }

   /** @deprecated */
   @Deprecated
   public StoreConfiguration(final String location, final String password) {
      this(location, (PasswordProvider)(new MemoryPasswordProvider(password == null ? null : password.toCharArray())));
   }

   public void clearSecrets() {
      this.location = null;
      this.passwordProvider = null;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(final String location) {
      this.location = location;
   }

   /** @deprecated */
   @Deprecated
   public String getPassword() {
      return String.valueOf(this.passwordProvider.getPassword());
   }

   public char[] getPasswordAsCharArray() {
      return this.passwordProvider.getPassword();
   }

   public void setPassword(final char[] password) {
      this.passwordProvider = new MemoryPasswordProvider(password);
   }

   /** @deprecated */
   @Deprecated
   public void setPassword(final String password) {
      this.passwordProvider = new MemoryPasswordProvider(password == null ? null : password.toCharArray());
   }

   protected Object load() throws StoreConfigurationException {
      return null;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.location == null ? 0 : this.location.hashCode());
      result = 31 * result + Arrays.hashCode(this.passwordProvider.getPassword());
      return result;
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         StoreConfiguration<?> other = (StoreConfiguration)obj;
         if (!Objects.equals(this.location, other.location)) {
            return false;
         } else {
            return Arrays.equals(this.passwordProvider.getPassword(), other.passwordProvider.getPassword());
         }
      }
   }
}

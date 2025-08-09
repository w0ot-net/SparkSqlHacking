package org.apache.logging.log4j.core.net.ssl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.util.NetUtils;

public class AbstractKeyStoreConfiguration extends StoreConfiguration {
   private final KeyStore keyStore;
   private final String keyStoreType;

   public AbstractKeyStoreConfiguration(final String location, final PasswordProvider passwordProvider, final String keyStoreType) throws StoreConfigurationException {
      super(location, passwordProvider);
      this.keyStoreType = keyStoreType == null ? SslConfigurationDefaults.KEYSTORE_TYPE : keyStoreType;
      this.keyStore = this.load();
   }

   /** @deprecated */
   @Deprecated
   public AbstractKeyStoreConfiguration(final String location, final char[] password, final String keyStoreType) throws StoreConfigurationException {
      this(location, (PasswordProvider)(new MemoryPasswordProvider(password)), keyStoreType);
   }

   /** @deprecated */
   @Deprecated
   public AbstractKeyStoreConfiguration(final String location, final String password, final String keyStoreType) throws StoreConfigurationException {
      this(location, (PasswordProvider)(new MemoryPasswordProvider(password == null ? null : password.toCharArray())), keyStoreType);
   }

   protected KeyStore load() throws StoreConfigurationException {
      String loadLocation = this.getLocation();
      char[] password = this.getPasswordAsCharArray();
      LOGGER.debug("Loading keystore from location {}", loadLocation);

      KeyStore var4;
      try {
         KeyStore ks = KeyStore.getInstance(this.keyStoreType);
         if (loadLocation != null) {
            InputStream fin = this.openInputStream(loadLocation);

            KeyStore var5;
            try {
               ks.load(fin, password);
               LOGGER.debug("KeyStore successfully loaded from location {}", loadLocation);
               var5 = ks;
            } catch (Throwable var17) {
               if (fin != null) {
                  try {
                     fin.close();
                  } catch (Throwable var16) {
                     var17.addSuppressed(var16);
                  }
               }

               throw var17;
            }

            if (fin != null) {
               fin.close();
            }

            return var5;
         }

         if (this.keyStoreType.equalsIgnoreCase("JKS") || this.keyStoreType.equalsIgnoreCase("PKCS12")) {
            throw new IOException("The location is null");
         }

         ks.load((InputStream)null, password);
         LOGGER.debug("KeyStore successfully loaded");
         var4 = ks;
      } catch (CertificateException e) {
         LOGGER.error("No Provider supports a KeyStoreSpi implementation for the specified type {} for location {}", this.keyStoreType, loadLocation, e);
         throw new StoreConfigurationException(loadLocation, e);
      } catch (NoSuchAlgorithmException e) {
         LOGGER.error("The algorithm used to check the integrity of the keystore cannot be found for location {}", loadLocation, e);
         throw new StoreConfigurationException(loadLocation, e);
      } catch (KeyStoreException e) {
         LOGGER.error("KeyStoreException for location {}", loadLocation, e);
         throw new StoreConfigurationException(loadLocation, e);
      } catch (FileNotFoundException e) {
         LOGGER.error("The keystore file {} is not found", loadLocation, e);
         throw new StoreConfigurationException(loadLocation, e);
      } catch (IOException e) {
         LOGGER.error("Something is wrong with the format of the keystore or the given password for location {}", loadLocation, e);
         throw new StoreConfigurationException(loadLocation, e);
      } finally {
         if (password != null) {
            Arrays.fill(password, '\u0000');
         }

      }

      return var4;
   }

   private InputStream openInputStream(final String filePathOrUri) {
      return ConfigurationSource.fromUri(NetUtils.toURI(filePathOrUri)).getInputStream();
   }

   public KeyStore getKeyStore() {
      return this.keyStore;
   }

   public int hashCode() {
      int prime = 31;
      int result = super.hashCode();
      result = 31 * result + (this.keyStore == null ? 0 : this.keyStore.hashCode());
      result = 31 * result + (this.keyStoreType == null ? 0 : this.keyStoreType.hashCode());
      return result;
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         AbstractKeyStoreConfiguration other = (AbstractKeyStoreConfiguration)obj;
         if (!Objects.equals(this.keyStore, other.keyStore)) {
            return false;
         } else {
            return Objects.equals(this.keyStoreType, other.keyStoreType);
         }
      }
   }

   public String getKeyStoreType() {
      return this.keyStoreType;
   }
}

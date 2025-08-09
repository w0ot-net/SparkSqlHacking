package org.apache.zookeeper.common;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Optional;
import org.apache.zookeeper.util.PemReader;

class PEMFileLoader extends FileKeyStoreLoader {
   private PEMFileLoader(String keyStorePath, String trustStorePath, String keyStorePassword, String trustStorePassword) {
      super(keyStorePath, trustStorePath, keyStorePassword, trustStorePassword);
   }

   public KeyStore loadKeyStore() throws IOException, GeneralSecurityException {
      Optional<String> passwordOption;
      if (this.keyStorePassword != null && this.keyStorePassword.length() != 0) {
         passwordOption = Optional.of(this.keyStorePassword);
      } else {
         passwordOption = Optional.empty();
      }

      File file = new File(this.keyStorePath);
      return PemReader.loadKeyStore(file, file, passwordOption);
   }

   public KeyStore loadTrustStore() throws IOException, GeneralSecurityException {
      return PemReader.loadTrustStore(new File(this.trustStorePath));
   }

   static class Builder extends FileKeyStoreLoader.Builder {
      PEMFileLoader build() {
         return new PEMFileLoader(this.keyStorePath, this.trustStorePath, this.keyStorePassword, this.trustStorePassword);
      }
   }
}

package org.apache.zookeeper.common;

import java.util.Objects;

abstract class FileKeyStoreLoader implements KeyStoreLoader {
   final String keyStorePath;
   final String trustStorePath;
   final String keyStorePassword;
   final String trustStorePassword;

   FileKeyStoreLoader(String keyStorePath, String trustStorePath, String keyStorePassword, String trustStorePassword) {
      this.keyStorePath = keyStorePath;
      this.trustStorePath = trustStorePath;
      this.keyStorePassword = keyStorePassword;
      this.trustStorePassword = trustStorePassword;
   }

   abstract static class Builder {
      String keyStorePath;
      String trustStorePath;
      String keyStorePassword;
      String trustStorePassword;

      Builder setKeyStorePath(String keyStorePath) {
         this.keyStorePath = (String)Objects.requireNonNull(keyStorePath);
         return this;
      }

      Builder setTrustStorePath(String trustStorePath) {
         this.trustStorePath = (String)Objects.requireNonNull(trustStorePath);
         return this;
      }

      Builder setKeyStorePassword(String keyStorePassword) {
         this.keyStorePassword = (String)Objects.requireNonNull(keyStorePassword);
         return this;
      }

      Builder setTrustStorePassword(String trustStorePassword) {
         this.trustStorePassword = (String)Objects.requireNonNull(trustStorePassword);
         return this;
      }

      abstract FileKeyStoreLoader build();
   }
}

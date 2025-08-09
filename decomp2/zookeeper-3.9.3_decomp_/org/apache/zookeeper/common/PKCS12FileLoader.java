package org.apache.zookeeper.common;

class PKCS12FileLoader extends StandardTypeFileKeyStoreLoader {
   private PKCS12FileLoader(String keyStorePath, String trustStorePath, String keyStorePassword, String trustStorePassword) {
      super(keyStorePath, trustStorePath, keyStorePassword, trustStorePassword, StandardTypeFileKeyStoreLoader.SupportedStandardKeyFormat.PKCS12);
   }

   static class Builder extends FileKeyStoreLoader.Builder {
      PKCS12FileLoader build() {
         return new PKCS12FileLoader(this.keyStorePath, this.trustStorePath, this.keyStorePassword, this.trustStorePassword);
      }
   }
}

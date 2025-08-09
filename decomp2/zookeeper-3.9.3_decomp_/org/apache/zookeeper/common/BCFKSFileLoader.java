package org.apache.zookeeper.common;

class BCFKSFileLoader extends StandardTypeFileKeyStoreLoader {
   private BCFKSFileLoader(String keyStorePath, String trustStorePath, String keyStorePassword, String trustStorePassword) {
      super(keyStorePath, trustStorePath, keyStorePassword, trustStorePassword, StandardTypeFileKeyStoreLoader.SupportedStandardKeyFormat.BCFKS);
   }

   static class Builder extends FileKeyStoreLoader.Builder {
      BCFKSFileLoader build() {
         return new BCFKSFileLoader(this.keyStorePath, this.trustStorePath, this.keyStorePassword, this.trustStorePassword);
      }
   }
}

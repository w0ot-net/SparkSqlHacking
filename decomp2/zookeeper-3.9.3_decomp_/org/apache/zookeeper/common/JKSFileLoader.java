package org.apache.zookeeper.common;

class JKSFileLoader extends StandardTypeFileKeyStoreLoader {
   private JKSFileLoader(String keyStorePath, String trustStorePath, String keyStorePassword, String trustStorePassword) {
      super(keyStorePath, trustStorePath, keyStorePassword, trustStorePassword, StandardTypeFileKeyStoreLoader.SupportedStandardKeyFormat.JKS);
   }

   static class Builder extends FileKeyStoreLoader.Builder {
      JKSFileLoader build() {
         return new JKSFileLoader(this.keyStorePath, this.trustStorePath, this.keyStorePassword, this.trustStorePassword);
      }
   }
}

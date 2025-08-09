package org.apache.zookeeper.common;

import java.util.Objects;

public class FileKeyStoreLoaderBuilderProvider {
   static FileKeyStoreLoader.Builder getBuilderForKeyStoreFileType(KeyStoreFileType type) {
      switch ((KeyStoreFileType)Objects.requireNonNull(type)) {
         case JKS:
            return new JKSFileLoader.Builder();
         case PEM:
            return new PEMFileLoader.Builder();
         case PKCS12:
            return new PKCS12FileLoader.Builder();
         case BCFKS:
            return new BCFKSFileLoader.Builder();
         default:
            throw new AssertionError("Unexpected StoreFileType: " + type.name());
      }
   }
}

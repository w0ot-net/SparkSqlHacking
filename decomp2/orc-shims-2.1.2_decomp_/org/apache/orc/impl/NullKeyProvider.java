package org.apache.orc.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

class NullKeyProvider implements KeyProvider {
   public List getKeyNames() {
      return new ArrayList();
   }

   public HadoopShims.KeyMetadata getCurrentKeyVersion(String keyName) {
      throw new IllegalArgumentException("Unknown key " + keyName);
   }

   public LocalKey createLocalKey(HadoopShims.KeyMetadata key) {
      throw new IllegalArgumentException("Unknown key " + String.valueOf(key));
   }

   public Key decryptLocalKey(HadoopShims.KeyMetadata key, byte[] encryptedKey) {
      return null;
   }

   public HadoopShims.KeyProviderKind getKind() {
      return null;
   }
}

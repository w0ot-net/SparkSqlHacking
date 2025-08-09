package org.apache.orc;

public interface EncryptionKey extends Comparable {
   String getKeyName();

   int getKeyVersion();

   EncryptionAlgorithm getAlgorithm();

   EncryptionVariant[] getEncryptionRoots();

   boolean isAvailable();
}

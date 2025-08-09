package org.apache.orc;

import java.io.IOException;
import java.security.Key;

public interface EncryptionVariant extends Comparable {
   EncryptionKey getKeyDescription();

   TypeDescription getRoot();

   int getVariantId();

   Key getFileFooterKey() throws IOException;

   Key getStripeKey(long var1) throws IOException;
}

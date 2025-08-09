package org.apache.orc;

public interface StripeInformation {
   long getOffset();

   long getLength();

   long getIndexLength();

   long getDataLength();

   long getFooterLength();

   long getNumberOfRows();

   long getStripeId();

   boolean hasEncryptionStripeId();

   long getEncryptionStripeId();

   byte[][] getEncryptedLocalKeys();
}

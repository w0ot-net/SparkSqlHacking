package org.apache.commons.compress.archivers.zip;

public class X0019_EncryptionRecipientCertificateList extends PKWareExtraHeader {
   static final ZipShort HEADER_ID = new ZipShort(25);

   public X0019_EncryptionRecipientCertificateList() {
      super(HEADER_ID);
   }
}

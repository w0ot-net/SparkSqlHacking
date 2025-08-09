package org.bouncycastle.asn1;

import java.io.IOException;

public class ASN1Absent extends ASN1Primitive {
   public static final ASN1Absent INSTANCE = new ASN1Absent();

   private ASN1Absent() {
   }

   public int hashCode() {
      return 0;
   }

   boolean encodeConstructed() {
      return false;
   }

   int encodedLength(boolean var1) throws IOException {
      return 0;
   }

   void encode(ASN1OutputStream var1, boolean var2) throws IOException {
   }

   boolean asn1Equals(ASN1Primitive var1) {
      return var1 == this;
   }
}

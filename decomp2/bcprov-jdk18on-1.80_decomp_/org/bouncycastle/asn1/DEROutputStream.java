package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

class DEROutputStream extends DLOutputStream {
   DEROutputStream(OutputStream var1) {
      super(var1);
   }

   DEROutputStream getDERSubStream() {
      return this;
   }

   void writeElements(ASN1Encodable[] var1) throws IOException {
      int var2 = 0;

      for(int var3 = var1.length; var2 < var3; ++var2) {
         var1[var2].toASN1Primitive().toDERObject().encode(this, true);
      }

   }

   void writePrimitive(ASN1Primitive var1, boolean var2) throws IOException {
      var1.toDERObject().encode(this, var2);
   }

   void writePrimitives(ASN1Primitive[] var1) throws IOException {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         var1[var3].toDERObject().encode(this, true);
      }

   }
}

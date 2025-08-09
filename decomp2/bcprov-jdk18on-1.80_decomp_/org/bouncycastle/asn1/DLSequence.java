package org.bouncycastle.asn1;

import java.io.IOException;

public class DLSequence extends ASN1Sequence {
   private int contentsLength = -1;

   public DLSequence() {
   }

   public DLSequence(ASN1Encodable var1) {
      super(var1);
   }

   public DLSequence(ASN1Encodable var1, ASN1Encodable var2) {
      super(var1, var2);
   }

   public DLSequence(ASN1EncodableVector var1) {
      super(var1);
   }

   public DLSequence(ASN1Encodable[] var1) {
      super(var1);
   }

   DLSequence(ASN1Encodable[] var1, boolean var2) {
      super(var1, var2);
   }

   private int getContentsLength() throws IOException {
      if (this.contentsLength < 0) {
         int var1 = this.elements.length;
         int var2 = 0;

         for(int var3 = 0; var3 < var1; ++var3) {
            ASN1Primitive var4 = this.elements[var3].toASN1Primitive().toDLObject();
            var2 += var4.encodedLength(true);
         }

         this.contentsLength = var2;
      }

      return this.contentsLength;
   }

   int encodedLength(boolean var1) throws IOException {
      return ASN1OutputStream.getLengthOfEncodingDL(var1, this.getContentsLength());
   }

   void encode(ASN1OutputStream var1, boolean var2) throws IOException {
      var1.writeIdentifier(var2, 48);
      DLOutputStream var3 = var1.getDLSubStream();
      int var4 = this.elements.length;
      if (this.contentsLength < 0 && var4 <= 16) {
         int var9 = 0;
         ASN1Primitive[] var6 = new ASN1Primitive[var4];

         for(int var7 = 0; var7 < var4; ++var7) {
            ASN1Primitive var8 = this.elements[var7].toASN1Primitive().toDLObject();
            var6[var7] = var8;
            var9 += var8.encodedLength(true);
         }

         this.contentsLength = var9;
         var1.writeDL(var9);

         for(int var10 = 0; var10 < var4; ++var10) {
            ((ASN1OutputStream)var3).writePrimitive(var6[var10], true);
         }
      } else {
         var1.writeDL(this.getContentsLength());

         for(int var5 = 0; var5 < var4; ++var5) {
            ((ASN1OutputStream)var3).writePrimitive(this.elements[var5].toASN1Primitive(), true);
         }
      }

   }

   ASN1BitString toASN1BitString() {
      return new DLBitString(BERBitString.flattenBitStrings(this.getConstructedBitStrings()), false);
   }

   ASN1External toASN1External() {
      return new DLExternal(this);
   }

   ASN1OctetString toASN1OctetString() {
      return new DEROctetString(BEROctetString.flattenOctetStrings(this.getConstructedOctetStrings()));
   }

   ASN1Set toASN1Set() {
      return new DLSet(false, this.toArrayInternal());
   }

   ASN1Primitive toDLObject() {
      return this;
   }
}

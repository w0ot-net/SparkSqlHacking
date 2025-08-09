package org.bouncycastle.asn1;

import java.io.IOException;

public class DERTaggedObject extends ASN1TaggedObject {
   public DERTaggedObject(int var1, ASN1Encodable var2) {
      super(true, var1, var2);
   }

   public DERTaggedObject(int var1, int var2, ASN1Encodable var3) {
      super(true, var1, var2, var3);
   }

   public DERTaggedObject(boolean var1, int var2, ASN1Encodable var3) {
      super(var1, var2, var3);
   }

   public DERTaggedObject(boolean var1, int var2, int var3, ASN1Encodable var4) {
      super(var1, var2, var3, var4);
   }

   DERTaggedObject(int var1, int var2, int var3, ASN1Encodable var4) {
      super(var1, var2, var3, var4);
   }

   boolean encodeConstructed() {
      return this.isExplicit() || this.obj.toASN1Primitive().toDERObject().encodeConstructed();
   }

   int encodedLength(boolean var1) throws IOException {
      ASN1Primitive var2 = this.obj.toASN1Primitive().toDERObject();
      boolean var3 = this.isExplicit();
      int var4 = var2.encodedLength(var3);
      if (var3) {
         var4 += ASN1OutputStream.getLengthOfDL(var4);
      }

      var4 += var1 ? ASN1OutputStream.getLengthOfIdentifier(this.tagNo) : 0;
      return var4;
   }

   void encode(ASN1OutputStream var1, boolean var2) throws IOException {
      ASN1Primitive var3 = this.obj.toASN1Primitive().toDERObject();
      boolean var4 = this.isExplicit();
      if (var2) {
         int var5 = this.tagClass;
         if (var4 || var3.encodeConstructed()) {
            var5 |= 32;
         }

         var1.writeIdentifier(true, var5, this.tagNo);
      }

      if (var4) {
         var1.writeDL(var3.encodedLength(true));
      }

      var3.encode(var1.getDERSubStream(), var4);
   }

   ASN1Sequence rebuildConstructed(ASN1Primitive var1) {
      return new DERSequence(var1);
   }

   ASN1TaggedObject replaceTag(int var1, int var2) {
      return new DERTaggedObject(this.explicitness, var1, var2, this.obj);
   }

   ASN1Primitive toDERObject() {
      return this;
   }

   ASN1Primitive toDLObject() {
      return this;
   }
}

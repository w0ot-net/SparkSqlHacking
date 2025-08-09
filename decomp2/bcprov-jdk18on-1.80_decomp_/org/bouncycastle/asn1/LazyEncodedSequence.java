package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

class LazyEncodedSequence extends ASN1Sequence {
   private byte[] encoded;

   LazyEncodedSequence(byte[] var1) throws IOException {
      if (null == var1) {
         throw new NullPointerException("'encoded' cannot be null");
      } else {
         this.encoded = var1;
      }
   }

   public ASN1Encodable getObjectAt(int var1) {
      this.force();
      return super.getObjectAt(var1);
   }

   public Enumeration getObjects() {
      byte[] var1 = this.getContents();
      return (Enumeration)(null != var1 ? new LazyConstructionEnumeration(var1) : super.getObjects());
   }

   public int hashCode() {
      this.force();
      return super.hashCode();
   }

   public Iterator iterator() {
      this.force();
      return super.iterator();
   }

   public int size() {
      this.force();
      return super.size();
   }

   public ASN1Encodable[] toArray() {
      this.force();
      return super.toArray();
   }

   ASN1Encodable[] toArrayInternal() {
      this.force();
      return super.toArrayInternal();
   }

   int encodedLength(boolean var1) throws IOException {
      byte[] var2 = this.getContents();
      return null != var2 ? ASN1OutputStream.getLengthOfEncodingDL(var1, var2.length) : super.toDLObject().encodedLength(var1);
   }

   void encode(ASN1OutputStream var1, boolean var2) throws IOException {
      byte[] var3 = this.getContents();
      if (null != var3) {
         var1.writeEncodingDL(var2, 48, var3);
      } else {
         super.toDLObject().encode(var1, var2);
      }
   }

   ASN1BitString toASN1BitString() {
      return ((ASN1Sequence)this.toDLObject()).toASN1BitString();
   }

   ASN1External toASN1External() {
      return ((ASN1Sequence)this.toDLObject()).toASN1External();
   }

   ASN1OctetString toASN1OctetString() {
      return ((ASN1Sequence)this.toDLObject()).toASN1OctetString();
   }

   ASN1Set toASN1Set() {
      return ((ASN1Sequence)this.toDLObject()).toASN1Set();
   }

   ASN1Primitive toDERObject() {
      this.force();
      return super.toDERObject();
   }

   ASN1Primitive toDLObject() {
      this.force();
      return super.toDLObject();
   }

   private synchronized void force() {
      if (null != this.encoded) {
         ASN1InputStream var1 = new ASN1InputStream(this.encoded, true);

         try {
            ASN1EncodableVector var2 = var1.readVector();
            var1.close();
            this.elements = var2.takeElements();
            this.encoded = null;
         } catch (IOException var3) {
            throw new ASN1ParsingException("malformed ASN.1: " + var3, var3);
         }
      }

   }

   private synchronized byte[] getContents() {
      return this.encoded;
   }
}

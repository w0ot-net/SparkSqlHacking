package org.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1Null extends ASN1Primitive {
   static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1Null.class, 5) {
      ASN1Primitive fromImplicitPrimitive(DEROctetString var1) {
         return ASN1Null.createPrimitive(var1.getOctets());
      }
   };

   public static ASN1Null getInstance(Object var0) {
      if (var0 instanceof ASN1Null) {
         return (ASN1Null)var0;
      } else if (var0 != null) {
         try {
            return (ASN1Null)TYPE.fromByteArray((byte[])var0);
         } catch (IOException var2) {
            throw new IllegalArgumentException("failed to construct NULL from byte[]: " + var2.getMessage());
         }
      } else {
         return null;
      }
   }

   public static ASN1Null getInstance(ASN1TaggedObject var0, boolean var1) {
      return (ASN1Null)TYPE.getContextInstance(var0, var1);
   }

   ASN1Null() {
   }

   public int hashCode() {
      return -1;
   }

   boolean asn1Equals(ASN1Primitive var1) {
      return var1 instanceof ASN1Null;
   }

   public String toString() {
      return "NULL";
   }

   static ASN1Null createPrimitive(byte[] var0) {
      if (0 != var0.length) {
         throw new IllegalStateException("malformed NULL encoding encountered");
      } else {
         return DERNull.INSTANCE;
      }
   }
}

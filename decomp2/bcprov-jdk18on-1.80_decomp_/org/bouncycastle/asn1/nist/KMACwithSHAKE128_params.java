package org.bouncycastle.asn1.nist;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class KMACwithSHAKE128_params extends ASN1Object {
   private static final byte[] EMPTY_STRING = new byte[0];
   private static final int DEF_LENGTH = 256;
   private final int outputLength;
   private final byte[] customizationString;

   public KMACwithSHAKE128_params(int var1) {
      this.outputLength = var1;
      this.customizationString = EMPTY_STRING;
   }

   public KMACwithSHAKE128_params(int var1, byte[] var2) {
      this.outputLength = var1;
      this.customizationString = Arrays.clone(var2);
   }

   public static KMACwithSHAKE128_params getInstance(Object var0) {
      if (var0 instanceof KMACwithSHAKE128_params) {
         return (KMACwithSHAKE128_params)var0;
      } else {
         return var0 != null ? new KMACwithSHAKE128_params(ASN1Sequence.getInstance(var0)) : null;
      }
   }

   private KMACwithSHAKE128_params(ASN1Sequence var1) {
      if (var1.size() > 2) {
         throw new IllegalArgumentException("sequence size greater than 2");
      } else {
         if (var1.size() == 2) {
            this.outputLength = ASN1Integer.getInstance(var1.getObjectAt(0)).intValueExact();
            this.customizationString = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(1)).getOctets());
         } else if (var1.size() == 1) {
            if (var1.getObjectAt(0) instanceof ASN1Integer) {
               this.outputLength = ASN1Integer.getInstance(var1.getObjectAt(0)).intValueExact();
               this.customizationString = EMPTY_STRING;
            } else {
               this.outputLength = 256;
               this.customizationString = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(0)).getOctets());
            }
         } else {
            this.outputLength = 256;
            this.customizationString = EMPTY_STRING;
         }

      }
   }

   public int getOutputLength() {
      return this.outputLength;
   }

   public byte[] getCustomizationString() {
      return Arrays.clone(this.customizationString);
   }

   public ASN1Primitive toASN1Primitive() {
      ASN1EncodableVector var1 = new ASN1EncodableVector();
      if (this.outputLength != 256) {
         var1.add(new ASN1Integer((long)this.outputLength));
      }

      if (this.customizationString.length != 0) {
         var1.add(new DEROctetString(this.getCustomizationString()));
      }

      return new DERSequence(var1);
   }
}

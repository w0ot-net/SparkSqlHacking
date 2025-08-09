package org.bouncycastle.internal.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class GCMParameters extends ASN1Object {
   private byte[] nonce;
   private int icvLen;

   public static GCMParameters getInstance(Object var0) {
      if (var0 instanceof GCMParameters) {
         return (GCMParameters)var0;
      } else {
         return var0 != null ? new GCMParameters(ASN1Sequence.getInstance(var0)) : null;
      }
   }

   private GCMParameters(ASN1Sequence var1) {
      this.nonce = ASN1OctetString.getInstance(var1.getObjectAt(0)).getOctets();
      if (var1.size() == 2) {
         this.icvLen = ASN1Integer.getInstance(var1.getObjectAt(1)).intValueExact();
      } else {
         this.icvLen = 12;
      }

   }

   public GCMParameters(byte[] var1, int var2) {
      this.nonce = Arrays.clone(var1);
      this.icvLen = var2;
   }

   public byte[] getNonce() {
      return Arrays.clone(this.nonce);
   }

   public int getIcvLen() {
      return this.icvLen;
   }

   public ASN1Primitive toASN1Primitive() {
      ASN1EncodableVector var1 = new ASN1EncodableVector(2);
      var1.add(new DEROctetString(this.nonce));
      if (this.icvLen != 12) {
         var1.add(new ASN1Integer((long)this.icvLen));
      }

      return new DERSequence(var1);
   }
}

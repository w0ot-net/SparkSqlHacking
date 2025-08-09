package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class KyberPublicKey extends ASN1Object {
   private byte[] t;
   private byte[] rho;

   public KyberPublicKey(byte[] var1, byte[] var2) {
      this.t = var1;
      this.rho = var2;
   }

   /** @deprecated */
   public KyberPublicKey(ASN1Sequence var1) {
      this.t = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(0)).getOctets());
      this.rho = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(1)).getOctets());
   }

   public byte[] getT() {
      return Arrays.clone(this.t);
   }

   public byte[] getRho() {
      return Arrays.clone(this.rho);
   }

   public ASN1Primitive toASN1Primitive() {
      ASN1EncodableVector var1 = new ASN1EncodableVector();
      var1.add(new DEROctetString(this.t));
      var1.add(new DEROctetString(this.rho));
      return new DERSequence(var1);
   }

   public static KyberPublicKey getInstance(Object var0) {
      if (var0 instanceof KyberPublicKey) {
         return (KyberPublicKey)var0;
      } else {
         return var0 != null ? new KyberPublicKey(ASN1Sequence.getInstance(var0)) : null;
      }
   }
}

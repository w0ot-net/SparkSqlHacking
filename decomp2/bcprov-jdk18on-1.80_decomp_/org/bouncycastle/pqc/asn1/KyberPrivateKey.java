package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class KyberPrivateKey extends ASN1Object {
   private int version;
   private byte[] s;
   private KyberPublicKey publicKey;
   private byte[] hpk;
   private byte[] nonce;

   public KyberPrivateKey(int var1, byte[] var2, byte[] var3, byte[] var4, KyberPublicKey var5) {
      this.version = var1;
      this.s = var2;
      this.publicKey = var5;
      this.hpk = var3;
      this.nonce = var4;
   }

   public KyberPrivateKey(int var1, byte[] var2, byte[] var3, byte[] var4) {
      this(var1, var2, var3, var4, (KyberPublicKey)null);
   }

   public int getVersion() {
      return this.version;
   }

   public byte[] getS() {
      return Arrays.clone(this.s);
   }

   public KyberPublicKey getPublicKey() {
      return this.publicKey;
   }

   public byte[] getHpk() {
      return Arrays.clone(this.hpk);
   }

   public byte[] getNonce() {
      return Arrays.clone(this.nonce);
   }

   private KyberPrivateKey(ASN1Sequence var1) {
      this.version = ASN1Integer.getInstance(var1.getObjectAt(0)).intValueExact();
      if (this.version != 0) {
         throw new IllegalArgumentException("unrecognized version");
      } else {
         this.s = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(1)).getOctets());
         byte var2 = 1;
         if (var1.size() == 5) {
            var2 = 0;
            this.publicKey = KyberPublicKey.getInstance(var1.getObjectAt(2));
         }

         this.hpk = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(3 - var2)).getOctets());
         this.nonce = Arrays.clone(ASN1OctetString.getInstance(var1.getObjectAt(4 - var2)).getOctets());
      }
   }

   public ASN1Primitive toASN1Primitive() {
      ASN1EncodableVector var1 = new ASN1EncodableVector();
      var1.add(new ASN1Integer((long)this.version));
      var1.add(new DEROctetString(this.s));
      if (this.publicKey != null) {
         var1.add(new KyberPublicKey(this.publicKey.getT(), this.publicKey.getRho()));
      }

      var1.add(new DEROctetString(this.hpk));
      var1.add(new DEROctetString(this.nonce));
      return new DERSequence(var1);
   }

   public static KyberPrivateKey getInstance(Object var0) {
      if (var0 instanceof KyberPrivateKey) {
         return (KyberPrivateKey)var0;
      } else {
         return var0 != null ? new KyberPrivateKey(ASN1Sequence.getInstance(var0)) : null;
      }
   }
}

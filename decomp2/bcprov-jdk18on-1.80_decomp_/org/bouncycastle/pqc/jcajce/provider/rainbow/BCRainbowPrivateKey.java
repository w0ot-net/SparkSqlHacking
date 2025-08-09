package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.crypto.util.PrivateKeyFactory;
import org.bouncycastle.pqc.jcajce.interfaces.RainbowPrivateKey;
import org.bouncycastle.pqc.jcajce.interfaces.RainbowPublicKey;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.bouncycastle.pqc.jcajce.spec.RainbowParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class BCRainbowPrivateKey implements RainbowPrivateKey {
   private static final long serialVersionUID = 1L;
   private transient RainbowPrivateKeyParameters params;
   private transient String algorithm;
   private transient byte[] encoding;
   private transient ASN1Set attributes;

   public BCRainbowPrivateKey(RainbowPrivateKeyParameters var1) {
      this.init(var1, (ASN1Set)null);
   }

   public BCRainbowPrivateKey(PrivateKeyInfo var1) throws IOException {
      this.init(var1);
   }

   private void init(PrivateKeyInfo var1) throws IOException {
      this.init((RainbowPrivateKeyParameters)PrivateKeyFactory.createKey(var1), var1.getAttributes());
   }

   private void init(RainbowPrivateKeyParameters var1, ASN1Set var2) {
      this.attributes = var2;
      this.params = var1;
      this.algorithm = Strings.toUpperCase(var1.getParameters().getName());
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof BCRainbowPrivateKey) {
         BCRainbowPrivateKey var2 = (BCRainbowPrivateKey)var1;
         return Arrays.areEqual(this.getEncoded(), var2.getEncoded());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.getEncoded());
   }

   public final String getAlgorithm() {
      return this.algorithm;
   }

   public byte[] getEncoded() {
      if (this.encoding == null) {
         this.encoding = KeyUtil.getEncodedPrivateKeyInfo((AsymmetricKeyParameter)this.params, (ASN1Set)this.attributes);
      }

      return Arrays.clone(this.encoding);
   }

   public RainbowParameterSpec getParameterSpec() {
      return RainbowParameterSpec.fromName(this.params.getParameters().getName());
   }

   public String getFormat() {
      return "PKCS#8";
   }

   public RainbowPublicKey getPublicKey() {
      return new BCRainbowPublicKey(new RainbowPublicKeyParameters(this.params.getParameters(), this.params.getPublicKey()));
   }

   RainbowPrivateKeyParameters getKeyParams() {
      return this.params;
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      byte[] var2 = (byte[])var1.readObject();
      this.init(PrivateKeyInfo.getInstance(var2));
   }

   private void writeObject(ObjectOutputStream var1) throws IOException {
      var1.defaultWriteObject();
      var1.writeObject(this.getEncoded());
   }
}

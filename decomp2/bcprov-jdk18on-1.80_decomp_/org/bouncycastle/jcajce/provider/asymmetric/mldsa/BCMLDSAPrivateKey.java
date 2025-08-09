package org.bouncycastle.jcajce.provider.asymmetric.mldsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jcajce.interfaces.MLDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.MLDSAPublicKey;
import org.bouncycastle.jcajce.spec.MLDSAParameterSpec;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPublicKeyParameters;
import org.bouncycastle.pqc.crypto.util.PrivateKeyFactory;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Fingerprint;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class BCMLDSAPrivateKey implements MLDSAPrivateKey {
   private static final long serialVersionUID = 1L;
   private transient MLDSAPrivateKeyParameters params;
   private transient String algorithm;
   private transient byte[] encoding;
   private transient ASN1Set attributes;

   public BCMLDSAPrivateKey(MLDSAPrivateKeyParameters var1) {
      this.params = var1;
      this.algorithm = Strings.toUpperCase(MLDSAParameterSpec.fromName(var1.getParameters().getName()).getName());
   }

   public BCMLDSAPrivateKey(PrivateKeyInfo var1) throws IOException {
      this.init(var1);
   }

   private void init(PrivateKeyInfo var1) throws IOException {
      this.init((MLDSAPrivateKeyParameters)PrivateKeyFactory.createKey(var1), var1.getAttributes());
   }

   private void init(MLDSAPrivateKeyParameters var1, ASN1Set var2) {
      this.attributes = var2;
      this.params = var1;
      this.algorithm = Strings.toUpperCase(MLDSAParameterSpec.fromName(var1.getParameters().getName()).getName());
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof BCMLDSAPrivateKey) {
         BCMLDSAPrivateKey var2 = (BCMLDSAPrivateKey)var1;
         return Arrays.areEqual(this.params.getEncoded(), var2.params.getEncoded());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.params.getEncoded());
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

   public MLDSAPublicKey getPublicKey() {
      MLDSAPublicKeyParameters var1 = this.params.getPublicKeyParameters();
      return var1 == null ? null : new BCMLDSAPublicKey(var1);
   }

   public byte[] getPrivateData() {
      return this.params.getEncoded();
   }

   public byte[] getSeed() {
      return this.params.getSeed();
   }

   public MLDSAParameterSpec getParameterSpec() {
      return MLDSAParameterSpec.fromName(this.params.getParameters().getName());
   }

   public String getFormat() {
      return "PKCS#8";
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      String var2 = Strings.lineSeparator();
      byte[] var3 = this.params.getPublicKey();
      var1.append(this.getAlgorithm()).append(" ").append("Private Key").append(" [").append((new Fingerprint(var3)).toString()).append("]").append(var2).append("    public data: ").append(Hex.toHexString(var3)).append(var2);
      return var1.toString();
   }

   MLDSAPrivateKeyParameters getKeyParams() {
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

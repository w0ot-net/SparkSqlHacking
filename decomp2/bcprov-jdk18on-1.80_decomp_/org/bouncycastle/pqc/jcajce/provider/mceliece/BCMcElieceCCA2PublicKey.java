package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.pqc.asn1.McElieceCCA2PublicKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.crypto.util.PublicKeyFactory;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;

public class BCMcElieceCCA2PublicKey implements CipherParameters, PublicKey {
   private static final long serialVersionUID = 1L;
   private transient McElieceCCA2PublicKeyParameters params;

   public BCMcElieceCCA2PublicKey(McElieceCCA2PublicKeyParameters var1) {
      this.params = var1;
   }

   private void init(SubjectPublicKeyInfo var1) throws IOException {
      this.params = (McElieceCCA2PublicKeyParameters)PublicKeyFactory.createKey(var1);
   }

   public String getAlgorithm() {
      return "McEliece-CCA2";
   }

   public int getN() {
      return this.params.getN();
   }

   public int getK() {
      return this.params.getK();
   }

   public int getT() {
      return this.params.getT();
   }

   public GF2Matrix getG() {
      return this.params.getG();
   }

   public String toString() {
      String var1 = "McEliecePublicKey:\n";
      var1 = var1 + " length of the code         : " + this.params.getN() + "\n";
      var1 = var1 + " error correction capability: " + this.params.getT() + "\n";
      var1 = var1 + " generator matrix           : " + this.params.getG().toString();
      return var1;
   }

   public boolean equals(Object var1) {
      if (var1 != null && var1 instanceof BCMcElieceCCA2PublicKey) {
         BCMcElieceCCA2PublicKey var2 = (BCMcElieceCCA2PublicKey)var1;
         return this.params.getN() == var2.getN() && this.params.getT() == var2.getT() && this.params.getG().equals(var2.getG());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 37 * (this.params.getN() + 37 * this.params.getT()) + this.params.getG().hashCode();
   }

   public byte[] getEncoded() {
      McElieceCCA2PublicKey var1 = new McElieceCCA2PublicKey(this.params.getN(), this.params.getT(), this.params.getG(), MessageDigestUtils.getDigestAlgID(this.params.getDigest()));
      AlgorithmIdentifier var2 = new AlgorithmIdentifier(PQCObjectIdentifiers.mcElieceCca2);

      try {
         SubjectPublicKeyInfo var3 = new SubjectPublicKeyInfo(var2, var1);
         return var3.getEncoded();
      } catch (IOException var4) {
         return null;
      }
   }

   public String getFormat() {
      return "X.509";
   }

   AsymmetricKeyParameter getKeyParams() {
      return this.params;
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      byte[] var2 = (byte[])var1.readObject();
      this.init(SubjectPublicKeyInfo.getInstance(var2));
   }

   private void writeObject(ObjectOutputStream var1) throws IOException {
      var1.defaultWriteObject();
      var1.writeObject(this.getEncoded());
   }
}

package org.bouncycastle.pqc.jcajce.provider.ntru;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.util.PrivateKeyFactory;
import org.bouncycastle.pqc.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.pqc.jcajce.interfaces.NTRUKey;
import org.bouncycastle.pqc.jcajce.spec.NTRUParameterSpec;
import org.bouncycastle.util.Arrays;

public class BCNTRUPrivateKey implements PrivateKey, NTRUKey {
   private static final long serialVersionUID = 1L;
   private transient NTRUPrivateKeyParameters params;
   private transient ASN1Set attributes;

   public BCNTRUPrivateKey(NTRUPrivateKeyParameters var1) {
      this.params = var1;
   }

   public BCNTRUPrivateKey(PrivateKeyInfo var1) throws IOException {
      this.init(var1);
   }

   private void init(PrivateKeyInfo var1) throws IOException {
      this.attributes = var1.getAttributes();
      this.params = (NTRUPrivateKeyParameters)PrivateKeyFactory.createKey(var1);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof BCNTRUPrivateKey) {
         BCNTRUPrivateKey var2 = (BCNTRUPrivateKey)var1;
         return Arrays.areEqual(this.params.getEncoded(), var2.params.getEncoded());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.params.getEncoded());
   }

   public final String getAlgorithm() {
      return "NTRU";
   }

   public byte[] getEncoded() {
      try {
         PrivateKeyInfo var1 = PrivateKeyInfoFactory.createPrivateKeyInfo(this.params, this.attributes);
         return var1.getEncoded();
      } catch (IOException var2) {
         return null;
      }
   }

   public NTRUParameterSpec getParameterSpec() {
      return NTRUParameterSpec.fromName(this.params.getParameters().getName());
   }

   public String getFormat() {
      return "PKCS#8";
   }

   NTRUPrivateKeyParameters getKeyParams() {
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

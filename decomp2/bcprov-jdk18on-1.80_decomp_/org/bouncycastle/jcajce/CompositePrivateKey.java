package org.bouncycastle.jcajce;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.internal.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.jcajce.provider.asymmetric.compositesignatures.CompositeIndex;
import org.bouncycastle.jcajce.provider.asymmetric.compositesignatures.KeyFactorySpi;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;

public class CompositePrivateKey implements PrivateKey {
   private final List keys;
   private ASN1ObjectIdentifier algorithmIdentifier;

   public CompositePrivateKey(PrivateKey... var1) {
      this(MiscObjectIdentifiers.id_composite_key, var1);
   }

   public CompositePrivateKey(ASN1ObjectIdentifier var1, PrivateKey... var2) {
      this.algorithmIdentifier = var1;
      if (var2 != null && var2.length != 0) {
         ArrayList var3 = new ArrayList(var2.length);

         for(int var4 = 0; var4 < var2.length; ++var4) {
            var3.add(var2[var4]);
         }

         this.keys = Collections.unmodifiableList(var3);
      } else {
         throw new IllegalArgumentException("at least one private key must be provided for the composite private key");
      }
   }

   public CompositePrivateKey(PrivateKeyInfo var1) {
      CompositePrivateKey var2 = null;
      ASN1ObjectIdentifier var3 = var1.getPrivateKeyAlgorithm().getAlgorithm();

      try {
         if (!CompositeIndex.isAlgorithmSupported(var3)) {
            throw new IllegalStateException("Unable to create CompositePrivateKey from PrivateKeyInfo");
         }

         KeyFactorySpi var4 = new KeyFactorySpi();
         var2 = (CompositePrivateKey)var4.generatePrivate(var1);
         if (var2 == null) {
            throw new IllegalStateException("Unable to create CompositePrivateKey from PrivateKeyInfo");
         }
      } catch (IOException var5) {
         throw Exceptions.illegalStateException(var5.getMessage(), var5);
      }

      this.keys = var2.getPrivateKeys();
      this.algorithmIdentifier = var2.getAlgorithmIdentifier();
   }

   public List getPrivateKeys() {
      return this.keys;
   }

   public String getAlgorithm() {
      return CompositeIndex.getAlgorithmName(this.algorithmIdentifier);
   }

   public ASN1ObjectIdentifier getAlgorithmIdentifier() {
      return this.algorithmIdentifier;
   }

   public String getFormat() {
      return "PKCS#8";
   }

   public byte[] getEncoded() {
      ASN1EncodableVector var1 = new ASN1EncodableVector();
      if (this.algorithmIdentifier.equals(MiscObjectIdentifiers.id_composite_key)) {
         for(int var7 = 0; var7 < this.keys.size(); ++var7) {
            PrivateKeyInfo var8 = PrivateKeyInfo.getInstance(((PrivateKey)this.keys.get(var7)).getEncoded());
            var1.add(var8);
         }

         try {
            return (new PrivateKeyInfo(new AlgorithmIdentifier(this.algorithmIdentifier), new DERSequence(var1))).getEncoded("DER");
         } catch (IOException var5) {
            throw new IllegalStateException("unable to encode composite private key: " + var5.getMessage());
         }
      } else {
         byte[] var2 = null;

         for(int var3 = 0; var3 < this.keys.size(); ++var3) {
            PrivateKeyInfo var4 = PrivateKeyInfo.getInstance(((PrivateKey)this.keys.get(var3)).getEncoded());
            var2 = Arrays.concatenate(var2, var4.getPrivateKey().getOctets());
         }

         try {
            return (new PrivateKeyInfo(new AlgorithmIdentifier(this.algorithmIdentifier), var2)).getEncoded("DER");
         } catch (IOException var6) {
            throw new IllegalStateException("unable to encode composite private key: " + var6.getMessage());
         }
      }
   }

   public int hashCode() {
      return this.keys.hashCode();
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof CompositePrivateKey)) {
         return false;
      } else {
         boolean var2 = true;
         CompositePrivateKey var3 = (CompositePrivateKey)var1;
         if (!var3.getAlgorithmIdentifier().equals(this.algorithmIdentifier) || !this.keys.equals(var3.keys)) {
            var2 = false;
         }

         return var2;
      }
   }
}

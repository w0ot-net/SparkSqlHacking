package org.bouncycastle.jcajce;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.internal.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.jcajce.provider.asymmetric.compositesignatures.CompositeIndex;
import org.bouncycastle.jcajce.provider.asymmetric.compositesignatures.KeyFactorySpi;

public class CompositePublicKey implements PublicKey {
   private final List keys;
   private final ASN1ObjectIdentifier algorithmIdentifier;

   public CompositePublicKey(PublicKey... var1) {
      this(MiscObjectIdentifiers.id_composite_key, var1);
   }

   public CompositePublicKey(ASN1ObjectIdentifier var1, PublicKey... var2) {
      this.algorithmIdentifier = var1;
      if (var2 != null && var2.length != 0) {
         ArrayList var3 = new ArrayList(var2.length);

         for(int var4 = 0; var4 < var2.length; ++var4) {
            var3.add(var2[var4]);
         }

         this.keys = Collections.unmodifiableList(var3);
      } else {
         throw new IllegalArgumentException("at least one public key must be provided for the composite public key");
      }
   }

   public CompositePublicKey(SubjectPublicKeyInfo var1) {
      ASN1ObjectIdentifier var2 = var1.getAlgorithm().getAlgorithm();
      CompositePublicKey var3 = null;

      try {
         if (!CompositeIndex.isAlgorithmSupported(var2)) {
            throw new IllegalStateException("unable to create CompositePublicKey from SubjectPublicKeyInfo");
         }

         KeyFactorySpi var4 = new KeyFactorySpi();
         var3 = (CompositePublicKey)var4.generatePublic(var1);
         if (var3 == null) {
            throw new IllegalStateException("unable to create CompositePublicKey from SubjectPublicKeyInfo");
         }
      } catch (IOException var5) {
         throw new IllegalStateException(var5.getMessage(), var5);
      }

      this.keys = var3.getPublicKeys();
      this.algorithmIdentifier = var3.getAlgorithmIdentifier();
   }

   public List getPublicKeys() {
      return this.keys;
   }

   public String getAlgorithm() {
      return CompositeIndex.getAlgorithmName(this.algorithmIdentifier);
   }

   public ASN1ObjectIdentifier getAlgorithmIdentifier() {
      return this.algorithmIdentifier;
   }

   public String getFormat() {
      return "X.509";
   }

   public byte[] getEncoded() {
      ASN1EncodableVector var1 = new ASN1EncodableVector();

      for(int var2 = 0; var2 < this.keys.size(); ++var2) {
         if (this.algorithmIdentifier.equals(MiscObjectIdentifiers.id_composite_key)) {
            var1.add(SubjectPublicKeyInfo.getInstance(((PublicKey)this.keys.get(var2)).getEncoded()));
         } else {
            SubjectPublicKeyInfo var3 = SubjectPublicKeyInfo.getInstance(((PublicKey)this.keys.get(var2)).getEncoded());
            var1.add(var3.getPublicKeyData());
         }
      }

      try {
         return (new SubjectPublicKeyInfo(new AlgorithmIdentifier(this.algorithmIdentifier), new DERSequence(var1))).getEncoded("DER");
      } catch (IOException var4) {
         throw new IllegalStateException("unable to encode composite public key: " + var4.getMessage());
      }
   }

   public int hashCode() {
      return this.keys.hashCode();
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof CompositePublicKey)) {
         return false;
      } else {
         boolean var2 = true;
         CompositePublicKey var3 = (CompositePublicKey)var1;
         if (!var3.getAlgorithmIdentifier().equals(this.algorithmIdentifier) || !this.keys.equals(var3.keys)) {
            var2 = false;
         }

         return var2;
      }
   }
}

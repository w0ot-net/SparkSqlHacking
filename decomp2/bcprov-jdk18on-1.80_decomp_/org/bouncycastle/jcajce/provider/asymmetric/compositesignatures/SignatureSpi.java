package org.bouncycastle.jcajce.provider.asymmetric.compositesignatures;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.internal.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.jcajce.CompositePrivateKey;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jcajce.spec.ContextParameterSpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.Exceptions;

public class SignatureSpi extends java.security.SignatureSpi {
   private static final Map canonicalNames = new HashMap();
   private static final String ML_DSA_44 = "ML-DSA-44";
   private static final String ML_DSA_65 = "ML-DSA-65";
   private static final String ML_DSA_87 = "ML-DSA-87";
   private Key compositeKey;
   private final ASN1ObjectIdentifier algorithm;
   private final Signature[] componentSignatures;
   private final byte[] domain;
   private final Digest preHashDigest;
   private final byte[] hashOID;
   private final JcaJceHelper helper;
   private ContextParameterSpec contextSpec;
   private AlgorithmParameters engineParams;
   private boolean unprimed;

   SignatureSpi(ASN1ObjectIdentifier var1) {
      this(var1, (Digest)null, (ASN1ObjectIdentifier)null);
   }

   SignatureSpi(ASN1ObjectIdentifier var1, Digest var2, ASN1ObjectIdentifier var3) {
      this.helper = new BCJcaJceHelper();
      this.engineParams = null;
      this.unprimed = true;
      this.algorithm = var1;
      this.preHashDigest = var2;
      String[] var4 = CompositeIndex.getPairing(var1);
      if (var2 != null) {
         try {
            this.hashOID = var3.getEncoded();
         } catch (IOException var7) {
            throw new IllegalStateException("unable to encode domain value");
         }
      } else {
         this.hashOID = null;
      }

      try {
         this.domain = var1.getEncoded();
      } catch (IOException var6) {
         throw new IllegalStateException("unable to encode domain value");
      }

      this.componentSignatures = new Signature[var4.length];

      try {
         for(int var5 = 0; var5 != this.componentSignatures.length; ++var5) {
            this.componentSignatures[var5] = Signature.getInstance(var4[var5], "BC");
         }

      } catch (GeneralSecurityException var8) {
         throw Exceptions.illegalStateException(var8.getMessage(), var8);
      }
   }

   protected void engineInitVerify(PublicKey var1) throws InvalidKeyException {
      if (!(var1 instanceof CompositePublicKey)) {
         throw new InvalidKeyException("Public key is not composite.");
      } else {
         this.compositeKey = var1;
         CompositePublicKey var2 = (CompositePublicKey)this.compositeKey;
         if (!var2.getAlgorithmIdentifier().equals(this.algorithm)) {
            throw new InvalidKeyException("Provided composite public key cannot be used with the composite signature algorithm.");
         } else {
            this.sigInitVerify();
         }
      }
   }

   private void sigInitVerify() throws InvalidKeyException {
      CompositePublicKey var1 = (CompositePublicKey)this.compositeKey;

      for(int var2 = 0; var2 < this.componentSignatures.length; ++var2) {
         this.componentSignatures[var2].initVerify((PublicKey)var1.getPublicKeys().get(var2));
      }

      this.unprimed = true;
   }

   protected void engineInitSign(PrivateKey var1) throws InvalidKeyException {
      if (!(var1 instanceof CompositePrivateKey)) {
         throw new InvalidKeyException("Private key is not composite.");
      } else {
         this.compositeKey = var1;
         CompositePrivateKey var2 = (CompositePrivateKey)var1;
         if (!var2.getAlgorithmIdentifier().equals(this.algorithm)) {
            throw new InvalidKeyException("Provided composite private key cannot be used with the composite signature algorithm.");
         } else {
            this.sigInitSign();
         }
      }
   }

   private void sigInitSign() throws InvalidKeyException {
      CompositePrivateKey var1 = (CompositePrivateKey)this.compositeKey;

      for(int var2 = 0; var2 < this.componentSignatures.length; ++var2) {
         this.componentSignatures[var2].initSign((PrivateKey)var1.getPrivateKeys().get(var2));
      }

      this.unprimed = true;
   }

   private void baseSigInit() throws SignatureException {
      try {
         this.componentSignatures[0].setParameter(new ContextParameterSpec(this.domain));
      } catch (InvalidAlgorithmParameterException var4) {
         throw new IllegalStateException("unable to set context on ML-DSA");
      }

      if (this.preHashDigest == null) {
         for(int var1 = 0; var1 < this.componentSignatures.length; ++var1) {
            Signature var2 = this.componentSignatures[var1];
            var2.update(this.domain);
            if (this.contextSpec == null) {
               var2.update((byte)0);
            } else {
               byte[] var3 = this.contextSpec.getContext();
               var2.update((byte)var3.length);
               var2.update(var3);
            }
         }
      }

      this.unprimed = false;
   }

   protected void engineUpdate(byte var1) throws SignatureException {
      if (this.unprimed) {
         this.baseSigInit();
      }

      if (this.preHashDigest != null) {
         this.preHashDigest.update(var1);
      } else {
         for(int var2 = 0; var2 < this.componentSignatures.length; ++var2) {
            Signature var3 = this.componentSignatures[var2];
            var3.update(var1);
         }
      }

   }

   protected void engineUpdate(byte[] var1, int var2, int var3) throws SignatureException {
      if (this.unprimed) {
         this.baseSigInit();
      }

      if (this.preHashDigest != null) {
         this.preHashDigest.update(var1, var2, var3);
      } else {
         for(int var4 = 0; var4 < this.componentSignatures.length; ++var4) {
            Signature var5 = this.componentSignatures[var4];
            var5.update(var1, var2, var3);
         }
      }

   }

   protected byte[] engineSign() throws SignatureException {
      if (this.preHashDigest != null) {
         this.processPreHashedMessage();
      }

      ASN1EncodableVector var1 = new ASN1EncodableVector();

      try {
         for(int var2 = 0; var2 < this.componentSignatures.length; ++var2) {
            byte[] var3 = this.componentSignatures[var2].sign();
            var1.add(new DERBitString(var3));
         }

         return (new DERSequence(var1)).getEncoded("DER");
      } catch (IOException var4) {
         throw new SignatureException(var4.getMessage());
      }
   }

   private void processPreHashedMessage() throws SignatureException {
      byte[] var1 = new byte[this.preHashDigest.getDigestSize()];
      this.preHashDigest.doFinal(var1, 0);

      for(int var2 = 0; var2 < this.componentSignatures.length; ++var2) {
         Signature var3 = this.componentSignatures[var2];
         var3.update(this.domain, 0, this.domain.length);
         if (this.contextSpec == null) {
            var3.update((byte)0);
         } else {
            byte[] var4 = this.contextSpec.getContext();
            var3.update((byte)var4.length);
            var3.update(var4);
         }

         var3.update(this.hashOID, 0, this.hashOID.length);
         var3.update(var1, 0, var1.length);
      }

   }

   protected boolean engineVerify(byte[] var1) throws SignatureException {
      ASN1Sequence var2 = DERSequence.getInstance(var1);
      if (var2.size() != this.componentSignatures.length) {
         return false;
      } else {
         if (this.preHashDigest != null && this.preHashDigest != null) {
            this.processPreHashedMessage();
         }

         boolean var3 = false;

         for(int var4 = 0; var4 < this.componentSignatures.length; ++var4) {
            if (!this.componentSignatures[var4].verify(ASN1BitString.getInstance(var2.getObjectAt(var4)).getOctets())) {
               var3 = true;
            }
         }

         return !var3;
      }
   }

   protected void engineSetParameter(AlgorithmParameterSpec var1) throws InvalidAlgorithmParameterException {
      if (!this.unprimed) {
         throw new InvalidAlgorithmParameterException("attempt to set parameter after update");
      } else if (var1 instanceof ContextParameterSpec) {
         this.contextSpec = (ContextParameterSpec)var1;

         try {
            if (this.compositeKey instanceof PublicKey) {
               this.sigInitVerify();
            } else {
               this.sigInitSign();
            }

         } catch (InvalidKeyException var3) {
            throw new InvalidAlgorithmParameterException("keys invalid on reset: " + var3.getMessage(), var3);
         }
      } else {
         throw new InvalidAlgorithmParameterException("unknown parameterSpec passed to composite signature");
      }
   }

   private void setSigParameter(Signature var1, String var2, List var3, List var4) throws InvalidAlgorithmParameterException {
      for(int var5 = 0; var5 != var3.size(); ++var5) {
         this.getCanonicalName((String)var3.get(var5));
         if (((String)var3.get(var5)).equals(var2)) {
            var1.setParameter((AlgorithmParameterSpec)var4.get(var5));
         }
      }

   }

   private String getCanonicalName(String var1) {
      String var2 = (String)canonicalNames.get(var1);
      return var2 != null ? var2 : var1;
   }

   protected void engineSetParameter(String var1, Object var2) throws InvalidParameterException {
      throw new UnsupportedOperationException("engineSetParameter unsupported");
   }

   protected Object engineGetParameter(String var1) throws InvalidParameterException {
      throw new UnsupportedOperationException("engineGetParameter unsupported");
   }

   protected final AlgorithmParameters engineGetParameters() {
      if (this.engineParams == null && this.contextSpec != null) {
         try {
            this.engineParams = this.helper.createAlgorithmParameters("CONTEXT");
            this.engineParams.init(this.contextSpec);
         } catch (Exception var2) {
            throw Exceptions.illegalStateException(var2.toString(), var2);
         }
      }

      return this.engineParams;
   }

   static {
      canonicalNames.put("MLDSA44", "ML-DSA-44");
      canonicalNames.put("MLDSA65", "ML-DSA-65");
      canonicalNames.put("MLDSA87", "ML-DSA-87");
      canonicalNames.put(NISTObjectIdentifiers.id_ml_dsa_44.getId(), "ML-DSA-44");
      canonicalNames.put(NISTObjectIdentifiers.id_ml_dsa_65.getId(), "ML-DSA-65");
      canonicalNames.put(NISTObjectIdentifiers.id_ml_dsa_87.getId(), "ML-DSA-87");
   }

   public static final class HashMLDSA44_ECDSA_P256_SHA256 extends SignatureSpi {
      public HashMLDSA44_ECDSA_P256_SHA256() {
         super(MiscObjectIdentifiers.id_HashMLDSA44_ECDSA_P256_SHA256, new SHA256Digest(), NISTObjectIdentifiers.id_sha256);
      }
   }

   public static final class HashMLDSA44_Ed25519_SHA512 extends SignatureSpi {
      public HashMLDSA44_Ed25519_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA44_Ed25519_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA44_RSA2048_PKCS15_SHA256 extends SignatureSpi {
      public HashMLDSA44_RSA2048_PKCS15_SHA256() {
         super(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PKCS15_SHA256, new SHA256Digest(), NISTObjectIdentifiers.id_sha256);
      }
   }

   public static final class HashMLDSA44_RSA2048_PSS_SHA256 extends SignatureSpi {
      public HashMLDSA44_RSA2048_PSS_SHA256() {
         super(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PSS_SHA256, new SHA256Digest(), NISTObjectIdentifiers.id_sha256);
      }
   }

   public static final class HashMLDSA65_ECDSA_P384_SHA512 extends SignatureSpi {
      public HashMLDSA65_ECDSA_P384_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_P384_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA65_ECDSA_brainpoolP256r1_SHA512 extends SignatureSpi {
      public HashMLDSA65_ECDSA_brainpoolP256r1_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_brainpoolP256r1_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA65_Ed25519_SHA512 extends SignatureSpi {
      public HashMLDSA65_Ed25519_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_Ed25519_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA65_RSA3072_PKCS15_SHA512 extends SignatureSpi {
      public HashMLDSA65_RSA3072_PKCS15_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PKCS15_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA65_RSA3072_PSS_SHA512 extends SignatureSpi {
      public HashMLDSA65_RSA3072_PSS_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PSS_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA65_RSA4096_PKCS15_SHA512 extends SignatureSpi {
      public HashMLDSA65_RSA4096_PKCS15_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PKCS15_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA65_RSA4096_PSS_SHA512 extends SignatureSpi {
      public HashMLDSA65_RSA4096_PSS_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PSS_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA87_ECDSA_P384_SHA512 extends SignatureSpi {
      public HashMLDSA87_ECDSA_P384_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_P384_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA87_ECDSA_brainpoolP384r1_SHA512 extends SignatureSpi {
      public HashMLDSA87_ECDSA_brainpoolP384r1_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_brainpoolP384r1_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class HashMLDSA87_Ed448_SHA512 extends SignatureSpi {
      public HashMLDSA87_Ed448_SHA512() {
         super(MiscObjectIdentifiers.id_HashMLDSA87_Ed448_SHA512, new SHA512Digest(), NISTObjectIdentifiers.id_sha512);
      }
   }

   public static final class MLDSA44_ECDSA_P256_SHA256 extends SignatureSpi {
      public MLDSA44_ECDSA_P256_SHA256() {
         super(MiscObjectIdentifiers.id_MLDSA44_ECDSA_P256_SHA256);
      }
   }

   public static final class MLDSA44_Ed25519_SHA512 extends SignatureSpi {
      public MLDSA44_Ed25519_SHA512() {
         super(MiscObjectIdentifiers.id_MLDSA44_Ed25519_SHA512);
      }
   }

   public static final class MLDSA44_RSA2048_PKCS15_SHA256 extends SignatureSpi {
      public MLDSA44_RSA2048_PKCS15_SHA256() {
         super(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PKCS15_SHA256);
      }
   }

   public static final class MLDSA44_RSA2048_PSS_SHA256 extends SignatureSpi {
      public MLDSA44_RSA2048_PSS_SHA256() {
         super(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PSS_SHA256);
      }
   }

   public static final class MLDSA65_ECDSA_P384_SHA384 extends SignatureSpi {
      public MLDSA65_ECDSA_P384_SHA384() {
         super(MiscObjectIdentifiers.id_MLDSA65_ECDSA_P384_SHA384);
      }
   }

   public static final class MLDSA65_ECDSA_brainpoolP256r1_SHA256 extends SignatureSpi {
      public MLDSA65_ECDSA_brainpoolP256r1_SHA256() {
         super(MiscObjectIdentifiers.id_MLDSA65_ECDSA_brainpoolP256r1_SHA256);
      }
   }

   public static final class MLDSA65_Ed25519_SHA512 extends SignatureSpi {
      public MLDSA65_Ed25519_SHA512() {
         super(MiscObjectIdentifiers.id_MLDSA65_Ed25519_SHA512);
      }
   }

   public static final class MLDSA65_RSA3072_PKCS15_SHA256 extends SignatureSpi {
      public MLDSA65_RSA3072_PKCS15_SHA256() {
         super(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PKCS15_SHA256);
      }
   }

   public static final class MLDSA65_RSA3072_PSS_SHA256 extends SignatureSpi {
      public MLDSA65_RSA3072_PSS_SHA256() {
         super(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PSS_SHA256);
      }
   }

   public static final class MLDSA65_RSA4096_PKCS15_SHA384 extends SignatureSpi {
      public MLDSA65_RSA4096_PKCS15_SHA384() {
         super(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PKCS15_SHA384);
      }
   }

   public static final class MLDSA65_RSA4096_PSS_SHA384 extends SignatureSpi {
      public MLDSA65_RSA4096_PSS_SHA384() {
         super(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PSS_SHA384);
      }
   }

   public static final class MLDSA87_ECDSA_P384_SHA384 extends SignatureSpi {
      public MLDSA87_ECDSA_P384_SHA384() {
         super(MiscObjectIdentifiers.id_MLDSA87_ECDSA_P384_SHA384);
      }
   }

   public static final class MLDSA87_ECDSA_brainpoolP384r1_SHA384 extends SignatureSpi {
      public MLDSA87_ECDSA_brainpoolP384r1_SHA384() {
         super(MiscObjectIdentifiers.id_MLDSA87_ECDSA_brainpoolP384r1_SHA384);
      }
   }

   public static final class MLDSA87_Ed448_SHA512 extends SignatureSpi {
      public MLDSA87_Ed448_SHA512() {
         super(MiscObjectIdentifiers.id_MLDSA87_Ed448_SHA512);
      }
   }
}

package org.bouncycastle.jcajce.provider.asymmetric.compositesignatures;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.internal.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.internal.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.jcajce.CompositePrivateKey;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;

public class KeyFactorySpi extends BaseKeyFactorySpi implements AsymmetricKeyInfoConverter {
   private static final AlgorithmIdentifier mlDsa44;
   private static final AlgorithmIdentifier mlDsa65;
   private static final AlgorithmIdentifier mlDsa87;
   private static final AlgorithmIdentifier falcon512Identifier;
   private static final AlgorithmIdentifier ed25519;
   private static final AlgorithmIdentifier ecDsaP256;
   private static final AlgorithmIdentifier ecDsaBrainpoolP256r1;
   private static final AlgorithmIdentifier rsa;
   private static final AlgorithmIdentifier ed448;
   private static final AlgorithmIdentifier ecDsaP384;
   private static final AlgorithmIdentifier ecDsaBrainpoolP384r1;
   private static Map pairings;
   private static Map componentKeySizes;
   private JcaJceHelper helper;

   public KeyFactorySpi() {
      this((JcaJceHelper)null);
   }

   public KeyFactorySpi(JcaJceHelper var1) {
      this.helper = var1;
   }

   protected Key engineTranslateKey(Key var1) throws InvalidKeyException {
      if (this.helper == null) {
         this.helper = new BCJcaJceHelper();
      }

      try {
         if (var1 instanceof PrivateKey) {
            return this.generatePrivate(PrivateKeyInfo.getInstance(var1.getEncoded()));
         }

         if (var1 instanceof PublicKey) {
            return this.generatePublic(SubjectPublicKeyInfo.getInstance(var1.getEncoded()));
         }
      } catch (IOException var3) {
         throw new InvalidKeyException("Key could not be parsed: " + var3.getMessage());
      }

      throw new InvalidKeyException("Key not recognized");
   }

   public PrivateKey generatePrivate(PrivateKeyInfo var1) throws IOException {
      if (this.helper == null) {
         this.helper = new BCJcaJceHelper();
      }

      ASN1ObjectIdentifier var2 = var1.getPrivateKeyAlgorithm().getAlgorithm();
      if (!MiscObjectIdentifiers.id_alg_composite.equals(var2) && !MiscObjectIdentifiers.id_composite_key.equals(var2)) {
         try {
            Object var13;
            try {
               ASN1Encodable var14 = var1.parsePrivateKey();
               if (var14 instanceof ASN1OctetString) {
                  var13 = DERSequence.getInstance(ASN1OctetString.getInstance(var14).getOctets());
               } else {
                  var13 = DERSequence.getInstance(var14);
               }
            } catch (Exception var10) {
               ASN1EncodableVector var16 = new ASN1EncodableVector();
               byte[] var18 = var1.getPrivateKey().getOctets();
               var16.add(new DEROctetString(Arrays.copyOfRange((byte[])var18, 0, 32)));
               var16.add(new DEROctetString(Arrays.copyOfRange((byte[])var18, 32, var18.length)));
               var13 = new DERSequence(var16);
            }

            List var15 = this.getKeyFactoriesFromIdentifier(var2);
            PrivateKey[] var17 = new PrivateKey[((ASN1Sequence)var13).size()];
            AlgorithmIdentifier[] var19 = (AlgorithmIdentifier[])pairings.get(var2);

            for(int var20 = 0; var20 < ((ASN1Sequence)var13).size(); ++var20) {
               if (((ASN1Sequence)var13).getObjectAt(var20) instanceof ASN1OctetString) {
                  ASN1EncodableVector var8 = new ASN1EncodableVector(3);
                  var8.add(var1.getVersion());
                  var8.add(var19[var20]);
                  var8.add(((ASN1Sequence)var13).getObjectAt(var20));
                  PKCS8EncodedKeySpec var9 = new PKCS8EncodedKeySpec(PrivateKeyInfo.getInstance(new DERSequence(var8)).getEncoded());
                  var17[var20] = ((KeyFactory)var15.get(var20)).generatePrivate(var9);
               } else {
                  ASN1Sequence var21 = ASN1Sequence.getInstance(((ASN1Sequence)var13).getObjectAt(var20));
                  PKCS8EncodedKeySpec var22 = new PKCS8EncodedKeySpec(PrivateKeyInfo.getInstance(var21).getEncoded());
                  var17[var20] = ((KeyFactory)var15.get(var20)).generatePrivate(var22);
               }
            }

            return new CompositePrivateKey(var2, var17);
         } catch (GeneralSecurityException var11) {
            throw Exceptions.ioException(var11.getMessage(), var11);
         }
      } else {
         ASN1Sequence var3 = DERSequence.getInstance(var1.parsePrivateKey());
         PrivateKey[] var4 = new PrivateKey[var3.size()];

         for(int var5 = 0; var5 != var3.size(); ++var5) {
            ASN1Sequence var6 = ASN1Sequence.getInstance(var3.getObjectAt(var5));
            PrivateKeyInfo var7 = PrivateKeyInfo.getInstance(var6);

            try {
               var4[var5] = this.helper.createKeyFactory(var7.getPrivateKeyAlgorithm().getAlgorithm().getId()).generatePrivate(new PKCS8EncodedKeySpec(var7.getEncoded()));
            } catch (Exception var12) {
               throw new IOException("cannot decode generic composite: " + var12.getMessage(), var12);
            }
         }

         return new CompositePrivateKey(var4);
      }
   }

   public PublicKey generatePublic(SubjectPublicKeyInfo var1) throws IOException {
      if (this.helper == null) {
         this.helper = new BCJcaJceHelper();
      }

      ASN1ObjectIdentifier var2 = var1.getAlgorithm().getAlgorithm();
      ASN1Sequence var3 = null;
      byte[][] var4 = new byte[2][];

      try {
         var3 = DERSequence.getInstance(var1.getPublicKeyData().getBytes());
      } catch (Exception var12) {
         var4 = this.split(var2, var1.getPublicKeyData());
      }

      if (!MiscObjectIdentifiers.id_alg_composite.equals(var2) && !MiscObjectIdentifiers.id_composite_key.equals(var2)) {
         try {
            int var14 = var3 == null ? var4.length : var3.size();
            List var15 = this.getKeyFactoriesFromIdentifier(var2);
            ASN1BitString[] var16 = new ASN1BitString[var14];

            for(int var17 = 0; var17 < var14; ++var17) {
               if (var3 != null) {
                  if (var3.getObjectAt(var17) instanceof DEROctetString) {
                     var16[var17] = new DERBitString(((DEROctetString)var3.getObjectAt(var17)).getOctets());
                  } else {
                     var16[var17] = (DERBitString)var3.getObjectAt(var17);
                  }
               } else {
                  var16[var17] = new DERBitString(var4[var17]);
               }
            }

            X509EncodedKeySpec[] var18 = this.getKeysSpecs(var2, var16);
            PublicKey[] var9 = new PublicKey[var14];

            for(int var10 = 0; var10 < var14; ++var10) {
               var9[var10] = ((KeyFactory)var15.get(var10)).generatePublic(var18[var10]);
            }

            return new CompositePublicKey(var2, var9);
         } catch (GeneralSecurityException var13) {
            throw Exceptions.ioException(var13.getMessage(), var13);
         }
      } else {
         ASN1Sequence var5 = ASN1Sequence.getInstance(var1.getPublicKeyData().getBytes());
         PublicKey[] var6 = new PublicKey[var5.size()];

         for(int var7 = 0; var7 != var5.size(); ++var7) {
            SubjectPublicKeyInfo var8 = SubjectPublicKeyInfo.getInstance(var5.getObjectAt(var7));

            try {
               var6[var7] = this.helper.createKeyFactory(var8.getAlgorithm().getAlgorithm().getId()).generatePublic(new X509EncodedKeySpec(var8.getEncoded()));
            } catch (Exception var11) {
               throw new IOException("cannot decode generic composite: " + var11.getMessage(), var11);
            }
         }

         return new CompositePublicKey(var6);
      }
   }

   byte[][] split(ASN1ObjectIdentifier var1, ASN1BitString var2) {
      int[] var3 = (int[])componentKeySizes.get(var1);
      byte[] var4 = var2.getOctets();
      byte[][] var5 = new byte[][]{new byte[var3[0]], new byte[var3[1]]};
      return var5;
   }

   private List getKeyFactoriesFromIdentifier(ASN1ObjectIdentifier var1) throws NoSuchAlgorithmException, NoSuchProviderException {
      ArrayList var2 = new ArrayList();
      new ArrayList();
      String[] var4 = CompositeIndex.getPairing(var1);
      if (var4 == null) {
         throw new NoSuchAlgorithmException("Cannot create KeyFactories. Unsupported algorithm identifier.");
      } else {
         var2.add(this.helper.createKeyFactory(CompositeIndex.getBaseName(var4[0])));
         var2.add(this.helper.createKeyFactory(CompositeIndex.getBaseName(var4[1])));
         return Collections.unmodifiableList(var2);
      }
   }

   private X509EncodedKeySpec[] getKeysSpecs(ASN1ObjectIdentifier var1, ASN1BitString[] var2) throws IOException {
      X509EncodedKeySpec[] var3 = new X509EncodedKeySpec[var2.length];
      SubjectPublicKeyInfo[] var4 = new SubjectPublicKeyInfo[var2.length];
      AlgorithmIdentifier[] var5 = (AlgorithmIdentifier[])pairings.get(var1);
      if (var5 == null) {
         throw new IOException("Cannot create key specs. Unsupported algorithm identifier.");
      } else {
         var4[0] = new SubjectPublicKeyInfo(var5[0], var2[0]);
         var4[1] = new SubjectPublicKeyInfo(var5[1], var2[1]);
         var3[0] = new X509EncodedKeySpec(var4[0].getEncoded());
         var3[1] = new X509EncodedKeySpec(var4[1].getEncoded());
         return var3;
      }
   }

   static {
      mlDsa44 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_ml_dsa_44);
      mlDsa65 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_ml_dsa_65);
      mlDsa87 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_ml_dsa_87);
      falcon512Identifier = new AlgorithmIdentifier(BCObjectIdentifiers.falcon_512);
      ed25519 = new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519);
      ecDsaP256 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(SECObjectIdentifiers.secp256r1));
      ecDsaBrainpoolP256r1 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(TeleTrusTObjectIdentifiers.brainpoolP256r1));
      rsa = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption);
      ed448 = new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed448);
      ecDsaP384 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(SECObjectIdentifiers.secp384r1));
      ecDsaBrainpoolP384r1 = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, new X962Parameters(TeleTrusTObjectIdentifiers.brainpoolP384r1));
      pairings = new HashMap();
      componentKeySizes = new HashMap();
      pairings.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PSS_SHA256, new AlgorithmIdentifier[]{mlDsa44, rsa});
      pairings.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PKCS15_SHA256, new AlgorithmIdentifier[]{mlDsa44, rsa});
      pairings.put(MiscObjectIdentifiers.id_MLDSA44_Ed25519_SHA512, new AlgorithmIdentifier[]{mlDsa44, ed25519});
      pairings.put(MiscObjectIdentifiers.id_MLDSA44_ECDSA_P256_SHA256, new AlgorithmIdentifier[]{mlDsa44, ecDsaP256});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PSS_SHA256, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PKCS15_SHA256, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PSS_SHA384, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PKCS15_SHA384, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_P384_SHA384, new AlgorithmIdentifier[]{mlDsa65, ecDsaP384});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_brainpoolP256r1_SHA256, new AlgorithmIdentifier[]{mlDsa65, ecDsaBrainpoolP256r1});
      pairings.put(MiscObjectIdentifiers.id_MLDSA65_Ed25519_SHA512, new AlgorithmIdentifier[]{mlDsa65, ed25519});
      pairings.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_P384_SHA384, new AlgorithmIdentifier[]{mlDsa87, ecDsaP384});
      pairings.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_brainpoolP384r1_SHA384, new AlgorithmIdentifier[]{mlDsa87, ecDsaBrainpoolP384r1});
      pairings.put(MiscObjectIdentifiers.id_MLDSA87_Ed448_SHA512, new AlgorithmIdentifier[]{mlDsa87, ed448});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PSS_SHA256, new AlgorithmIdentifier[]{mlDsa44, rsa});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PKCS15_SHA256, new AlgorithmIdentifier[]{mlDsa44, rsa});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_Ed25519_SHA512, new AlgorithmIdentifier[]{mlDsa44, ed25519});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA44_ECDSA_P256_SHA256, new AlgorithmIdentifier[]{mlDsa44, ecDsaP256});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PSS_SHA512, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PKCS15_SHA512, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PSS_SHA512, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PKCS15_SHA512, new AlgorithmIdentifier[]{mlDsa65, rsa});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_P384_SHA512, new AlgorithmIdentifier[]{mlDsa65, ecDsaP384});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_brainpoolP256r1_SHA512, new AlgorithmIdentifier[]{mlDsa65, ecDsaBrainpoolP256r1});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA65_Ed25519_SHA512, new AlgorithmIdentifier[]{mlDsa65, ed25519});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_P384_SHA512, new AlgorithmIdentifier[]{mlDsa87, ecDsaP384});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_brainpoolP384r1_SHA512, new AlgorithmIdentifier[]{mlDsa87, ecDsaBrainpoolP384r1});
      pairings.put(MiscObjectIdentifiers.id_HashMLDSA87_Ed448_SHA512, new AlgorithmIdentifier[]{mlDsa87, ed448});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PSS_SHA256, new int[]{1328, 268});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_RSA2048_PKCS15_SHA256, new int[]{1312, 284});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_Ed25519_SHA512, new int[]{1312, 32});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA44_ECDSA_P256_SHA256, new int[]{1312, 76});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PSS_SHA256, new int[]{1952, 256});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA3072_PKCS15_SHA256, new int[]{1952, 256});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PSS_SHA384, new int[]{1952, 542});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_RSA4096_PKCS15_SHA384, new int[]{1952, 542});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_P384_SHA384, new int[]{1952, 87});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_ECDSA_brainpoolP256r1_SHA256, new int[]{1952, 76});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA65_Ed25519_SHA512, new int[]{1952, 32});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_P384_SHA384, new int[]{2592, 87});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA87_ECDSA_brainpoolP384r1_SHA384, new int[]{2592, 87});
      componentKeySizes.put(MiscObjectIdentifiers.id_MLDSA87_Ed448_SHA512, new int[]{2592, 57});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PSS_SHA256, new int[]{1328, 268});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_RSA2048_PKCS15_SHA256, new int[]{1312, 284});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_Ed25519_SHA512, new int[]{1312, 32});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA44_ECDSA_P256_SHA256, new int[]{1312, 76});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PSS_SHA512, new int[]{1952, 256});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA3072_PKCS15_SHA512, new int[]{1952, 256});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PSS_SHA512, new int[]{1952, 542});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_RSA4096_PKCS15_SHA512, new int[]{1952, 542});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_P384_SHA512, new int[]{1952, 87});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_ECDSA_brainpoolP256r1_SHA512, new int[]{1952, 76});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA65_Ed25519_SHA512, new int[]{1952, 32});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_P384_SHA512, new int[]{2592, 87});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA87_ECDSA_brainpoolP384r1_SHA512, new int[]{2592, 87});
      componentKeySizes.put(MiscObjectIdentifiers.id_HashMLDSA87_Ed448_SHA512, new int[]{2592, 57});
   }
}

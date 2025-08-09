package org.bouncycastle.pqc.crypto.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.internal.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.io.Streams;

class Utils {
   static final AlgorithmIdentifier AlgID_qTESLA_p_I;
   static final AlgorithmIdentifier AlgID_qTESLA_p_III;
   static final AlgorithmIdentifier SPHINCS_SHA3_256;
   static final AlgorithmIdentifier SPHINCS_SHA512_256;
   static final AlgorithmIdentifier XMSS_SHA256;
   static final AlgorithmIdentifier XMSS_SHA512;
   static final AlgorithmIdentifier XMSS_SHAKE128;
   static final AlgorithmIdentifier XMSS_SHAKE256;
   static final Map categories;
   static final Map picnicOids;
   static final Map picnicParams;
   static final Map frodoOids;
   static final Map frodoParams;
   static final Map saberOids;
   static final Map saberParams;
   static final Map mcElieceOids;
   static final Map mcElieceParams;
   static final Map sphincsPlusOids;
   static final Map sphincsPlusParams;
   static final Map sikeOids;
   static final Map sikeParams;
   static final Map ntruOids;
   static final Map ntruParams;
   static final Map falconOids;
   static final Map falconParams;
   static final Map ntruprimeOids;
   static final Map ntruprimeParams;
   static final Map sntruprimeOids;
   static final Map sntruprimeParams;
   static final Map dilithiumOids;
   static final Map dilithiumParams;
   static final Map bikeOids;
   static final Map bikeParams;
   static final Map hqcOids;
   static final Map hqcParams;
   static final Map rainbowOids;
   static final Map rainbowParams;
   static final Map mlkemOids;
   static final Map mlkemParams;
   static final Map mldsaOids;
   static final Map mldsaParams;
   static final Map shldsaOids;
   static final Map shldsaParams;

   static ASN1ObjectIdentifier slhdsaOidLookup(SLHDSAParameters var0) {
      return (ASN1ObjectIdentifier)shldsaOids.get(var0);
   }

   static SLHDSAParameters slhdsaParamsLookup(ASN1ObjectIdentifier var0) {
      return (SLHDSAParameters)shldsaParams.get(var0);
   }

   static int qTeslaLookupSecurityCategory(AlgorithmIdentifier var0) {
      return (Integer)categories.get(var0.getAlgorithm());
   }

   static AlgorithmIdentifier qTeslaLookupAlgID(int var0) {
      switch (var0) {
         case 5:
            return AlgID_qTESLA_p_I;
         case 6:
            return AlgID_qTESLA_p_III;
         default:
            throw new IllegalArgumentException("unknown security category: " + var0);
      }
   }

   static AlgorithmIdentifier sphincs256LookupTreeAlgID(String var0) {
      if (var0.equals("SHA3-256")) {
         return SPHINCS_SHA3_256;
      } else if (var0.equals("SHA-512/256")) {
         return SPHINCS_SHA512_256;
      } else {
         throw new IllegalArgumentException("unknown tree digest: " + var0);
      }
   }

   static AlgorithmIdentifier xmssLookupTreeAlgID(String var0) {
      if (var0.equals("SHA-256")) {
         return XMSS_SHA256;
      } else if (var0.equals("SHA-512")) {
         return XMSS_SHA512;
      } else if (var0.equals("SHAKE128")) {
         return XMSS_SHAKE128;
      } else if (var0.equals("SHAKE256")) {
         return XMSS_SHAKE256;
      } else {
         throw new IllegalArgumentException("unknown tree digest: " + var0);
      }
   }

   static String sphincs256LookupTreeAlgName(SPHINCS256KeyParams var0) {
      AlgorithmIdentifier var1 = var0.getTreeDigest();
      if (var1.getAlgorithm().equals(SPHINCS_SHA3_256.getAlgorithm())) {
         return "SHA3-256";
      } else if (var1.getAlgorithm().equals(SPHINCS_SHA512_256.getAlgorithm())) {
         return "SHA-512/256";
      } else {
         throw new IllegalArgumentException("unknown tree digest: " + var1.getAlgorithm());
      }
   }

   static Digest getDigest(ASN1ObjectIdentifier var0) {
      if (var0.equals(NISTObjectIdentifiers.id_sha256)) {
         return new SHA256Digest();
      } else if (var0.equals(NISTObjectIdentifiers.id_sha512)) {
         return new SHA512Digest();
      } else if (var0.equals(NISTObjectIdentifiers.id_shake128)) {
         return new SHAKEDigest(128);
      } else if (var0.equals(NISTObjectIdentifiers.id_shake256)) {
         return new SHAKEDigest(256);
      } else {
         throw new IllegalArgumentException("unrecognized digest OID: " + var0);
      }
   }

   public static AlgorithmIdentifier getAlgorithmIdentifier(String var0) {
      if (var0.equals("SHA-1")) {
         return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
      } else if (var0.equals("SHA-224")) {
         return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
      } else if (var0.equals("SHA-256")) {
         return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
      } else if (var0.equals("SHA-384")) {
         return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
      } else if (var0.equals("SHA-512")) {
         return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
      } else {
         throw new IllegalArgumentException("unrecognised digest algorithm: " + var0);
      }
   }

   public static String getDigestName(ASN1ObjectIdentifier var0) {
      if (var0.equals(OIWObjectIdentifiers.idSHA1)) {
         return "SHA-1";
      } else if (var0.equals(NISTObjectIdentifiers.id_sha224)) {
         return "SHA-224";
      } else if (var0.equals(NISTObjectIdentifiers.id_sha256)) {
         return "SHA-256";
      } else if (var0.equals(NISTObjectIdentifiers.id_sha384)) {
         return "SHA-384";
      } else if (var0.equals(NISTObjectIdentifiers.id_sha512)) {
         return "SHA-512";
      } else {
         throw new IllegalArgumentException("unrecognised digest algorithm: " + var0);
      }
   }

   static ASN1ObjectIdentifier sphincsPlusOidLookup(SPHINCSPlusParameters var0) {
      return (ASN1ObjectIdentifier)sphincsPlusOids.get(var0);
   }

   static SPHINCSPlusParameters sphincsPlusParamsLookup(ASN1ObjectIdentifier var0) {
      return (SPHINCSPlusParameters)sphincsPlusParams.get(var0);
   }

   static ASN1ObjectIdentifier mcElieceOidLookup(CMCEParameters var0) {
      return (ASN1ObjectIdentifier)mcElieceOids.get(var0);
   }

   static CMCEParameters mcElieceParamsLookup(ASN1ObjectIdentifier var0) {
      return (CMCEParameters)mcElieceParams.get(var0);
   }

   static ASN1ObjectIdentifier frodoOidLookup(FrodoParameters var0) {
      return (ASN1ObjectIdentifier)frodoOids.get(var0);
   }

   static FrodoParameters frodoParamsLookup(ASN1ObjectIdentifier var0) {
      return (FrodoParameters)frodoParams.get(var0);
   }

   static ASN1ObjectIdentifier saberOidLookup(SABERParameters var0) {
      return (ASN1ObjectIdentifier)saberOids.get(var0);
   }

   static SABERParameters saberParamsLookup(ASN1ObjectIdentifier var0) {
      return (SABERParameters)saberParams.get(var0);
   }

   static ASN1ObjectIdentifier picnicOidLookup(PicnicParameters var0) {
      return (ASN1ObjectIdentifier)picnicOids.get(var0);
   }

   static PicnicParameters picnicParamsLookup(ASN1ObjectIdentifier var0) {
      return (PicnicParameters)picnicParams.get(var0);
   }

   static ASN1ObjectIdentifier falconOidLookup(FalconParameters var0) {
      return (ASN1ObjectIdentifier)falconOids.get(var0);
   }

   static FalconParameters falconParamsLookup(ASN1ObjectIdentifier var0) {
      return (FalconParameters)falconParams.get(var0);
   }

   static ASN1ObjectIdentifier ntruOidLookup(NTRUParameters var0) {
      return (ASN1ObjectIdentifier)ntruOids.get(var0);
   }

   static NTRUParameters ntruParamsLookup(ASN1ObjectIdentifier var0) {
      return (NTRUParameters)ntruParams.get(var0);
   }

   static ASN1ObjectIdentifier mlkemOidLookup(MLKEMParameters var0) {
      return (ASN1ObjectIdentifier)mlkemOids.get(var0);
   }

   static MLKEMParameters mlkemParamsLookup(ASN1ObjectIdentifier var0) {
      return (MLKEMParameters)mlkemParams.get(var0);
   }

   static ASN1ObjectIdentifier ntrulprimeOidLookup(NTRULPRimeParameters var0) {
      return (ASN1ObjectIdentifier)ntruprimeOids.get(var0);
   }

   static NTRULPRimeParameters ntrulprimeParamsLookup(ASN1ObjectIdentifier var0) {
      return (NTRULPRimeParameters)ntruprimeParams.get(var0);
   }

   static ASN1ObjectIdentifier sntruprimeOidLookup(SNTRUPrimeParameters var0) {
      return (ASN1ObjectIdentifier)sntruprimeOids.get(var0);
   }

   static SNTRUPrimeParameters sntruprimeParamsLookup(ASN1ObjectIdentifier var0) {
      return (SNTRUPrimeParameters)sntruprimeParams.get(var0);
   }

   static ASN1ObjectIdentifier mldsaOidLookup(MLDSAParameters var0) {
      return (ASN1ObjectIdentifier)mldsaOids.get(var0);
   }

   static MLDSAParameters mldsaParamsLookup(ASN1ObjectIdentifier var0) {
      return (MLDSAParameters)mldsaParams.get(var0);
   }

   static ASN1ObjectIdentifier dilithiumOidLookup(DilithiumParameters var0) {
      return (ASN1ObjectIdentifier)dilithiumOids.get(var0);
   }

   static DilithiumParameters dilithiumParamsLookup(ASN1ObjectIdentifier var0) {
      return (DilithiumParameters)dilithiumParams.get(var0);
   }

   static ASN1ObjectIdentifier bikeOidLookup(BIKEParameters var0) {
      return (ASN1ObjectIdentifier)bikeOids.get(var0);
   }

   static BIKEParameters bikeParamsLookup(ASN1ObjectIdentifier var0) {
      return (BIKEParameters)bikeParams.get(var0);
   }

   static ASN1ObjectIdentifier hqcOidLookup(HQCParameters var0) {
      return (ASN1ObjectIdentifier)hqcOids.get(var0);
   }

   static HQCParameters hqcParamsLookup(ASN1ObjectIdentifier var0) {
      return (HQCParameters)hqcParams.get(var0);
   }

   static ASN1ObjectIdentifier rainbowOidLookup(RainbowParameters var0) {
      return (ASN1ObjectIdentifier)rainbowOids.get(var0);
   }

   static RainbowParameters rainbowParamsLookup(ASN1ObjectIdentifier var0) {
      return (RainbowParameters)rainbowParams.get(var0);
   }

   static byte[] readOctetString(byte[] var0) throws IOException {
      if (var0[0] == 4) {
         ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
         int var2 = var1.read();
         int var3 = readLen(var1);
         if (var3 == var1.available()) {
            return Streams.readAll(var1);
         }
      }

      return null;
   }

   static int readLen(ByteArrayInputStream var0) {
      int var1 = var0.read();
      if (var1 < 0) {
         return -1;
      } else {
         if (var1 != (var1 & 127)) {
            int var2 = var1 & 127;

            for(var1 = 0; var2-- != 0; var1 = (var1 << 8) + var0.read()) {
            }
         }

         return var1;
      }
   }

   static {
      AlgID_qTESLA_p_I = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_I);
      AlgID_qTESLA_p_III = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_III);
      SPHINCS_SHA3_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
      SPHINCS_SHA512_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);
      XMSS_SHA256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
      XMSS_SHA512 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
      XMSS_SHAKE128 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake128);
      XMSS_SHAKE256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake256);
      categories = new HashMap();
      picnicOids = new HashMap();
      picnicParams = new HashMap();
      frodoOids = new HashMap();
      frodoParams = new HashMap();
      saberOids = new HashMap();
      saberParams = new HashMap();
      mcElieceOids = new HashMap();
      mcElieceParams = new HashMap();
      sphincsPlusOids = new HashMap();
      sphincsPlusParams = new HashMap();
      sikeOids = new HashMap();
      sikeParams = new HashMap();
      ntruOids = new HashMap();
      ntruParams = new HashMap();
      falconOids = new HashMap();
      falconParams = new HashMap();
      ntruprimeOids = new HashMap();
      ntruprimeParams = new HashMap();
      sntruprimeOids = new HashMap();
      sntruprimeParams = new HashMap();
      dilithiumOids = new HashMap();
      dilithiumParams = new HashMap();
      bikeOids = new HashMap();
      bikeParams = new HashMap();
      hqcOids = new HashMap();
      hqcParams = new HashMap();
      rainbowOids = new HashMap();
      rainbowParams = new HashMap();
      mlkemOids = new HashMap();
      mlkemParams = new HashMap();
      mldsaOids = new HashMap();
      mldsaParams = new HashMap();
      shldsaOids = new HashMap();
      shldsaParams = new HashMap();
      categories.put(PQCObjectIdentifiers.qTESLA_p_I, Integers.valueOf(5));
      categories.put(PQCObjectIdentifiers.qTESLA_p_III, Integers.valueOf(6));
      mcElieceOids.put(CMCEParameters.mceliece348864r3, BCObjectIdentifiers.mceliece348864_r3);
      mcElieceOids.put(CMCEParameters.mceliece348864fr3, BCObjectIdentifiers.mceliece348864f_r3);
      mcElieceOids.put(CMCEParameters.mceliece460896r3, BCObjectIdentifiers.mceliece460896_r3);
      mcElieceOids.put(CMCEParameters.mceliece460896fr3, BCObjectIdentifiers.mceliece460896f_r3);
      mcElieceOids.put(CMCEParameters.mceliece6688128r3, BCObjectIdentifiers.mceliece6688128_r3);
      mcElieceOids.put(CMCEParameters.mceliece6688128fr3, BCObjectIdentifiers.mceliece6688128f_r3);
      mcElieceOids.put(CMCEParameters.mceliece6960119r3, BCObjectIdentifiers.mceliece6960119_r3);
      mcElieceOids.put(CMCEParameters.mceliece6960119fr3, BCObjectIdentifiers.mceliece6960119f_r3);
      mcElieceOids.put(CMCEParameters.mceliece8192128r3, BCObjectIdentifiers.mceliece8192128_r3);
      mcElieceOids.put(CMCEParameters.mceliece8192128fr3, BCObjectIdentifiers.mceliece8192128f_r3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece348864_r3, CMCEParameters.mceliece348864r3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece348864f_r3, CMCEParameters.mceliece348864fr3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece460896_r3, CMCEParameters.mceliece460896r3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece460896f_r3, CMCEParameters.mceliece460896fr3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece6688128_r3, CMCEParameters.mceliece6688128r3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece6688128f_r3, CMCEParameters.mceliece6688128fr3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece6960119_r3, CMCEParameters.mceliece6960119r3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece6960119f_r3, CMCEParameters.mceliece6960119fr3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece8192128_r3, CMCEParameters.mceliece8192128r3);
      mcElieceParams.put(BCObjectIdentifiers.mceliece8192128f_r3, CMCEParameters.mceliece8192128fr3);
      frodoOids.put(FrodoParameters.frodokem640aes, BCObjectIdentifiers.frodokem640aes);
      frodoOids.put(FrodoParameters.frodokem640shake, BCObjectIdentifiers.frodokem640shake);
      frodoOids.put(FrodoParameters.frodokem976aes, BCObjectIdentifiers.frodokem976aes);
      frodoOids.put(FrodoParameters.frodokem976shake, BCObjectIdentifiers.frodokem976shake);
      frodoOids.put(FrodoParameters.frodokem1344aes, BCObjectIdentifiers.frodokem1344aes);
      frodoOids.put(FrodoParameters.frodokem1344shake, BCObjectIdentifiers.frodokem1344shake);
      frodoParams.put(BCObjectIdentifiers.frodokem640aes, FrodoParameters.frodokem640aes);
      frodoParams.put(BCObjectIdentifiers.frodokem640shake, FrodoParameters.frodokem640shake);
      frodoParams.put(BCObjectIdentifiers.frodokem976aes, FrodoParameters.frodokem976aes);
      frodoParams.put(BCObjectIdentifiers.frodokem976shake, FrodoParameters.frodokem976shake);
      frodoParams.put(BCObjectIdentifiers.frodokem1344aes, FrodoParameters.frodokem1344aes);
      frodoParams.put(BCObjectIdentifiers.frodokem1344shake, FrodoParameters.frodokem1344shake);
      saberOids.put(SABERParameters.lightsaberkem128r3, BCObjectIdentifiers.lightsaberkem128r3);
      saberOids.put(SABERParameters.saberkem128r3, BCObjectIdentifiers.saberkem128r3);
      saberOids.put(SABERParameters.firesaberkem128r3, BCObjectIdentifiers.firesaberkem128r3);
      saberOids.put(SABERParameters.lightsaberkem192r3, BCObjectIdentifiers.lightsaberkem192r3);
      saberOids.put(SABERParameters.saberkem192r3, BCObjectIdentifiers.saberkem192r3);
      saberOids.put(SABERParameters.firesaberkem192r3, BCObjectIdentifiers.firesaberkem192r3);
      saberOids.put(SABERParameters.lightsaberkem256r3, BCObjectIdentifiers.lightsaberkem256r3);
      saberOids.put(SABERParameters.saberkem256r3, BCObjectIdentifiers.saberkem256r3);
      saberOids.put(SABERParameters.firesaberkem256r3, BCObjectIdentifiers.firesaberkem256r3);
      saberOids.put(SABERParameters.ulightsaberkemr3, BCObjectIdentifiers.ulightsaberkemr3);
      saberOids.put(SABERParameters.usaberkemr3, BCObjectIdentifiers.usaberkemr3);
      saberOids.put(SABERParameters.ufiresaberkemr3, BCObjectIdentifiers.ufiresaberkemr3);
      saberOids.put(SABERParameters.lightsaberkem90sr3, BCObjectIdentifiers.lightsaberkem90sr3);
      saberOids.put(SABERParameters.saberkem90sr3, BCObjectIdentifiers.saberkem90sr3);
      saberOids.put(SABERParameters.firesaberkem90sr3, BCObjectIdentifiers.firesaberkem90sr3);
      saberOids.put(SABERParameters.ulightsaberkem90sr3, BCObjectIdentifiers.ulightsaberkem90sr3);
      saberOids.put(SABERParameters.usaberkem90sr3, BCObjectIdentifiers.usaberkem90sr3);
      saberOids.put(SABERParameters.ufiresaberkem90sr3, BCObjectIdentifiers.ufiresaberkem90sr3);
      saberParams.put(BCObjectIdentifiers.lightsaberkem128r3, SABERParameters.lightsaberkem128r3);
      saberParams.put(BCObjectIdentifiers.saberkem128r3, SABERParameters.saberkem128r3);
      saberParams.put(BCObjectIdentifiers.firesaberkem128r3, SABERParameters.firesaberkem128r3);
      saberParams.put(BCObjectIdentifiers.lightsaberkem192r3, SABERParameters.lightsaberkem192r3);
      saberParams.put(BCObjectIdentifiers.saberkem192r3, SABERParameters.saberkem192r3);
      saberParams.put(BCObjectIdentifiers.firesaberkem192r3, SABERParameters.firesaberkem192r3);
      saberParams.put(BCObjectIdentifiers.lightsaberkem256r3, SABERParameters.lightsaberkem256r3);
      saberParams.put(BCObjectIdentifiers.saberkem256r3, SABERParameters.saberkem256r3);
      saberParams.put(BCObjectIdentifiers.firesaberkem256r3, SABERParameters.firesaberkem256r3);
      saberParams.put(BCObjectIdentifiers.ulightsaberkemr3, SABERParameters.ulightsaberkemr3);
      saberParams.put(BCObjectIdentifiers.usaberkemr3, SABERParameters.usaberkemr3);
      saberParams.put(BCObjectIdentifiers.ufiresaberkemr3, SABERParameters.ufiresaberkemr3);
      saberParams.put(BCObjectIdentifiers.lightsaberkem90sr3, SABERParameters.lightsaberkem90sr3);
      saberParams.put(BCObjectIdentifiers.saberkem90sr3, SABERParameters.saberkem90sr3);
      saberParams.put(BCObjectIdentifiers.firesaberkem90sr3, SABERParameters.firesaberkem90sr3);
      saberParams.put(BCObjectIdentifiers.ulightsaberkem90sr3, SABERParameters.ulightsaberkem90sr3);
      saberParams.put(BCObjectIdentifiers.usaberkem90sr3, SABERParameters.usaberkem90sr3);
      saberParams.put(BCObjectIdentifiers.ufiresaberkem90sr3, SABERParameters.ufiresaberkem90sr3);
      picnicOids.put(PicnicParameters.picnicl1fs, BCObjectIdentifiers.picnicl1fs);
      picnicOids.put(PicnicParameters.picnicl1ur, BCObjectIdentifiers.picnicl1ur);
      picnicOids.put(PicnicParameters.picnicl3fs, BCObjectIdentifiers.picnicl3fs);
      picnicOids.put(PicnicParameters.picnicl3ur, BCObjectIdentifiers.picnicl3ur);
      picnicOids.put(PicnicParameters.picnicl5fs, BCObjectIdentifiers.picnicl5fs);
      picnicOids.put(PicnicParameters.picnicl5ur, BCObjectIdentifiers.picnicl5ur);
      picnicOids.put(PicnicParameters.picnic3l1, BCObjectIdentifiers.picnic3l1);
      picnicOids.put(PicnicParameters.picnic3l3, BCObjectIdentifiers.picnic3l3);
      picnicOids.put(PicnicParameters.picnic3l5, BCObjectIdentifiers.picnic3l5);
      picnicOids.put(PicnicParameters.picnicl1full, BCObjectIdentifiers.picnicl1full);
      picnicOids.put(PicnicParameters.picnicl3full, BCObjectIdentifiers.picnicl3full);
      picnicOids.put(PicnicParameters.picnicl5full, BCObjectIdentifiers.picnicl5full);
      picnicParams.put(BCObjectIdentifiers.picnicl1fs, PicnicParameters.picnicl1fs);
      picnicParams.put(BCObjectIdentifiers.picnicl1ur, PicnicParameters.picnicl1ur);
      picnicParams.put(BCObjectIdentifiers.picnicl3fs, PicnicParameters.picnicl3fs);
      picnicParams.put(BCObjectIdentifiers.picnicl3ur, PicnicParameters.picnicl3ur);
      picnicParams.put(BCObjectIdentifiers.picnicl5fs, PicnicParameters.picnicl5fs);
      picnicParams.put(BCObjectIdentifiers.picnicl5ur, PicnicParameters.picnicl5ur);
      picnicParams.put(BCObjectIdentifiers.picnic3l1, PicnicParameters.picnic3l1);
      picnicParams.put(BCObjectIdentifiers.picnic3l3, PicnicParameters.picnic3l3);
      picnicParams.put(BCObjectIdentifiers.picnic3l5, PicnicParameters.picnic3l5);
      picnicParams.put(BCObjectIdentifiers.picnicl1full, PicnicParameters.picnicl1full);
      picnicParams.put(BCObjectIdentifiers.picnicl3full, PicnicParameters.picnicl3full);
      picnicParams.put(BCObjectIdentifiers.picnicl5full, PicnicParameters.picnicl5full);
      ntruOids.put(NTRUParameters.ntruhps2048509, BCObjectIdentifiers.ntruhps2048509);
      ntruOids.put(NTRUParameters.ntruhps2048677, BCObjectIdentifiers.ntruhps2048677);
      ntruOids.put(NTRUParameters.ntruhps4096821, BCObjectIdentifiers.ntruhps4096821);
      ntruOids.put(NTRUParameters.ntruhps40961229, BCObjectIdentifiers.ntruhps40961229);
      ntruOids.put(NTRUParameters.ntruhrss701, BCObjectIdentifiers.ntruhrss701);
      ntruOids.put(NTRUParameters.ntruhrss1373, BCObjectIdentifiers.ntruhrss1373);
      ntruParams.put(BCObjectIdentifiers.ntruhps2048509, NTRUParameters.ntruhps2048509);
      ntruParams.put(BCObjectIdentifiers.ntruhps2048677, NTRUParameters.ntruhps2048677);
      ntruParams.put(BCObjectIdentifiers.ntruhps4096821, NTRUParameters.ntruhps4096821);
      ntruParams.put(BCObjectIdentifiers.ntruhps40961229, NTRUParameters.ntruhps40961229);
      ntruParams.put(BCObjectIdentifiers.ntruhrss701, NTRUParameters.ntruhrss701);
      ntruParams.put(BCObjectIdentifiers.ntruhrss1373, NTRUParameters.ntruhrss1373);
      falconOids.put(FalconParameters.falcon_512, BCObjectIdentifiers.falcon_512);
      falconOids.put(FalconParameters.falcon_1024, BCObjectIdentifiers.falcon_1024);
      falconParams.put(BCObjectIdentifiers.falcon_512, FalconParameters.falcon_512);
      falconParams.put(BCObjectIdentifiers.falcon_1024, FalconParameters.falcon_1024);
      mlkemOids.put(MLKEMParameters.ml_kem_512, NISTObjectIdentifiers.id_alg_ml_kem_512);
      mlkemOids.put(MLKEMParameters.ml_kem_768, NISTObjectIdentifiers.id_alg_ml_kem_768);
      mlkemOids.put(MLKEMParameters.ml_kem_1024, NISTObjectIdentifiers.id_alg_ml_kem_1024);
      mlkemParams.put(NISTObjectIdentifiers.id_alg_ml_kem_512, MLKEMParameters.ml_kem_512);
      mlkemParams.put(NISTObjectIdentifiers.id_alg_ml_kem_768, MLKEMParameters.ml_kem_768);
      mlkemParams.put(NISTObjectIdentifiers.id_alg_ml_kem_1024, MLKEMParameters.ml_kem_1024);
      ntruprimeOids.put(NTRULPRimeParameters.ntrulpr653, BCObjectIdentifiers.ntrulpr653);
      ntruprimeOids.put(NTRULPRimeParameters.ntrulpr761, BCObjectIdentifiers.ntrulpr761);
      ntruprimeOids.put(NTRULPRimeParameters.ntrulpr857, BCObjectIdentifiers.ntrulpr857);
      ntruprimeOids.put(NTRULPRimeParameters.ntrulpr953, BCObjectIdentifiers.ntrulpr953);
      ntruprimeOids.put(NTRULPRimeParameters.ntrulpr1013, BCObjectIdentifiers.ntrulpr1013);
      ntruprimeOids.put(NTRULPRimeParameters.ntrulpr1277, BCObjectIdentifiers.ntrulpr1277);
      ntruprimeParams.put(BCObjectIdentifiers.ntrulpr653, NTRULPRimeParameters.ntrulpr653);
      ntruprimeParams.put(BCObjectIdentifiers.ntrulpr761, NTRULPRimeParameters.ntrulpr761);
      ntruprimeParams.put(BCObjectIdentifiers.ntrulpr857, NTRULPRimeParameters.ntrulpr857);
      ntruprimeParams.put(BCObjectIdentifiers.ntrulpr953, NTRULPRimeParameters.ntrulpr953);
      ntruprimeParams.put(BCObjectIdentifiers.ntrulpr1013, NTRULPRimeParameters.ntrulpr1013);
      ntruprimeParams.put(BCObjectIdentifiers.ntrulpr1277, NTRULPRimeParameters.ntrulpr1277);
      sntruprimeOids.put(SNTRUPrimeParameters.sntrup653, BCObjectIdentifiers.sntrup653);
      sntruprimeOids.put(SNTRUPrimeParameters.sntrup761, BCObjectIdentifiers.sntrup761);
      sntruprimeOids.put(SNTRUPrimeParameters.sntrup857, BCObjectIdentifiers.sntrup857);
      sntruprimeOids.put(SNTRUPrimeParameters.sntrup953, BCObjectIdentifiers.sntrup953);
      sntruprimeOids.put(SNTRUPrimeParameters.sntrup1013, BCObjectIdentifiers.sntrup1013);
      sntruprimeOids.put(SNTRUPrimeParameters.sntrup1277, BCObjectIdentifiers.sntrup1277);
      sntruprimeParams.put(BCObjectIdentifiers.sntrup653, SNTRUPrimeParameters.sntrup653);
      sntruprimeParams.put(BCObjectIdentifiers.sntrup761, SNTRUPrimeParameters.sntrup761);
      sntruprimeParams.put(BCObjectIdentifiers.sntrup857, SNTRUPrimeParameters.sntrup857);
      sntruprimeParams.put(BCObjectIdentifiers.sntrup953, SNTRUPrimeParameters.sntrup953);
      sntruprimeParams.put(BCObjectIdentifiers.sntrup1013, SNTRUPrimeParameters.sntrup1013);
      sntruprimeParams.put(BCObjectIdentifiers.sntrup1277, SNTRUPrimeParameters.sntrup1277);
      mldsaOids.put(MLDSAParameters.ml_dsa_44, NISTObjectIdentifiers.id_ml_dsa_44);
      mldsaOids.put(MLDSAParameters.ml_dsa_65, NISTObjectIdentifiers.id_ml_dsa_65);
      mldsaOids.put(MLDSAParameters.ml_dsa_87, NISTObjectIdentifiers.id_ml_dsa_87);
      mldsaOids.put(MLDSAParameters.ml_dsa_44_with_sha512, NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512);
      mldsaOids.put(MLDSAParameters.ml_dsa_65_with_sha512, NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512);
      mldsaOids.put(MLDSAParameters.ml_dsa_87_with_sha512, NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512);
      mldsaParams.put(NISTObjectIdentifiers.id_ml_dsa_44, MLDSAParameters.ml_dsa_44);
      mldsaParams.put(NISTObjectIdentifiers.id_ml_dsa_65, MLDSAParameters.ml_dsa_65);
      mldsaParams.put(NISTObjectIdentifiers.id_ml_dsa_87, MLDSAParameters.ml_dsa_87);
      mldsaParams.put(NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512, MLDSAParameters.ml_dsa_44_with_sha512);
      mldsaParams.put(NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512, MLDSAParameters.ml_dsa_65_with_sha512);
      mldsaParams.put(NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512, MLDSAParameters.ml_dsa_87_with_sha512);
      dilithiumOids.put(DilithiumParameters.dilithium2, BCObjectIdentifiers.dilithium2);
      dilithiumOids.put(DilithiumParameters.dilithium3, BCObjectIdentifiers.dilithium3);
      dilithiumOids.put(DilithiumParameters.dilithium5, BCObjectIdentifiers.dilithium5);
      dilithiumParams.put(BCObjectIdentifiers.dilithium2, DilithiumParameters.dilithium2);
      dilithiumParams.put(BCObjectIdentifiers.dilithium3, DilithiumParameters.dilithium3);
      dilithiumParams.put(BCObjectIdentifiers.dilithium5, DilithiumParameters.dilithium5);
      bikeParams.put(BCObjectIdentifiers.bike128, BIKEParameters.bike128);
      bikeParams.put(BCObjectIdentifiers.bike192, BIKEParameters.bike192);
      bikeParams.put(BCObjectIdentifiers.bike256, BIKEParameters.bike256);
      bikeOids.put(BIKEParameters.bike128, BCObjectIdentifiers.bike128);
      bikeOids.put(BIKEParameters.bike192, BCObjectIdentifiers.bike192);
      bikeOids.put(BIKEParameters.bike256, BCObjectIdentifiers.bike256);
      hqcParams.put(BCObjectIdentifiers.hqc128, HQCParameters.hqc128);
      hqcParams.put(BCObjectIdentifiers.hqc192, HQCParameters.hqc192);
      hqcParams.put(BCObjectIdentifiers.hqc256, HQCParameters.hqc256);
      hqcOids.put(HQCParameters.hqc128, BCObjectIdentifiers.hqc128);
      hqcOids.put(HQCParameters.hqc192, BCObjectIdentifiers.hqc192);
      hqcOids.put(HQCParameters.hqc256, BCObjectIdentifiers.hqc256);
      rainbowParams.put(BCObjectIdentifiers.rainbow_III_classic, RainbowParameters.rainbowIIIclassic);
      rainbowParams.put(BCObjectIdentifiers.rainbow_III_circumzenithal, RainbowParameters.rainbowIIIcircumzenithal);
      rainbowParams.put(BCObjectIdentifiers.rainbow_III_compressed, RainbowParameters.rainbowIIIcompressed);
      rainbowParams.put(BCObjectIdentifiers.rainbow_V_classic, RainbowParameters.rainbowVclassic);
      rainbowParams.put(BCObjectIdentifiers.rainbow_V_circumzenithal, RainbowParameters.rainbowVcircumzenithal);
      rainbowParams.put(BCObjectIdentifiers.rainbow_V_compressed, RainbowParameters.rainbowVcompressed);
      rainbowOids.put(RainbowParameters.rainbowIIIclassic, BCObjectIdentifiers.rainbow_III_classic);
      rainbowOids.put(RainbowParameters.rainbowIIIcircumzenithal, BCObjectIdentifiers.rainbow_III_circumzenithal);
      rainbowOids.put(RainbowParameters.rainbowIIIcompressed, BCObjectIdentifiers.rainbow_III_compressed);
      rainbowOids.put(RainbowParameters.rainbowVclassic, BCObjectIdentifiers.rainbow_V_classic);
      rainbowOids.put(RainbowParameters.rainbowVcircumzenithal, BCObjectIdentifiers.rainbow_V_circumzenithal);
      rainbowOids.put(RainbowParameters.rainbowVcompressed, BCObjectIdentifiers.rainbow_V_compressed);
      shldsaOids.put(SLHDSAParameters.sha2_128s, NISTObjectIdentifiers.id_slh_dsa_sha2_128s);
      shldsaOids.put(SLHDSAParameters.sha2_128f, NISTObjectIdentifiers.id_slh_dsa_sha2_128f);
      shldsaOids.put(SLHDSAParameters.sha2_192s, NISTObjectIdentifiers.id_slh_dsa_sha2_192s);
      shldsaOids.put(SLHDSAParameters.sha2_192f, NISTObjectIdentifiers.id_slh_dsa_sha2_192f);
      shldsaOids.put(SLHDSAParameters.sha2_256s, NISTObjectIdentifiers.id_slh_dsa_sha2_256s);
      shldsaOids.put(SLHDSAParameters.sha2_256f, NISTObjectIdentifiers.id_slh_dsa_sha2_256f);
      shldsaOids.put(SLHDSAParameters.shake_128s, NISTObjectIdentifiers.id_slh_dsa_shake_128s);
      shldsaOids.put(SLHDSAParameters.shake_128f, NISTObjectIdentifiers.id_slh_dsa_shake_128f);
      shldsaOids.put(SLHDSAParameters.shake_192s, NISTObjectIdentifiers.id_slh_dsa_shake_192s);
      shldsaOids.put(SLHDSAParameters.shake_192f, NISTObjectIdentifiers.id_slh_dsa_shake_192f);
      shldsaOids.put(SLHDSAParameters.shake_256s, NISTObjectIdentifiers.id_slh_dsa_shake_256s);
      shldsaOids.put(SLHDSAParameters.shake_256f, NISTObjectIdentifiers.id_slh_dsa_shake_256f);
      shldsaOids.put(SLHDSAParameters.sha2_128s_with_sha256, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128s_with_sha256);
      shldsaOids.put(SLHDSAParameters.sha2_128f_with_sha256, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128f_with_sha256);
      shldsaOids.put(SLHDSAParameters.sha2_192s_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192s_with_sha512);
      shldsaOids.put(SLHDSAParameters.sha2_192f_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192f_with_sha512);
      shldsaOids.put(SLHDSAParameters.sha2_256s_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256s_with_sha512);
      shldsaOids.put(SLHDSAParameters.sha2_256f_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256f_with_sha512);
      shldsaOids.put(SLHDSAParameters.shake_128s_with_shake128, NISTObjectIdentifiers.id_hash_slh_dsa_shake_128s_with_shake128);
      shldsaOids.put(SLHDSAParameters.shake_128f_with_shake128, NISTObjectIdentifiers.id_hash_slh_dsa_shake_128f_with_shake128);
      shldsaOids.put(SLHDSAParameters.shake_192s_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_192s_with_shake256);
      shldsaOids.put(SLHDSAParameters.shake_192f_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_192f_with_shake256);
      shldsaOids.put(SLHDSAParameters.shake_256s_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_256s_with_shake256);
      shldsaOids.put(SLHDSAParameters.shake_256f_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_256f_with_shake256);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_128s, SLHDSAParameters.sha2_128s);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_128f, SLHDSAParameters.sha2_128f);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_192s, SLHDSAParameters.sha2_192s);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_192f, SLHDSAParameters.sha2_192f);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_256s, SLHDSAParameters.sha2_256s);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_256f, SLHDSAParameters.sha2_256f);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_128s, SLHDSAParameters.shake_128s);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_128f, SLHDSAParameters.shake_128f);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_192s, SLHDSAParameters.shake_192s);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_192f, SLHDSAParameters.shake_192f);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_256s, SLHDSAParameters.shake_256s);
      shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_256f, SLHDSAParameters.shake_256f);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128s_with_sha256, SLHDSAParameters.sha2_128s_with_sha256);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128f_with_sha256, SLHDSAParameters.sha2_128f_with_sha256);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192s_with_sha512, SLHDSAParameters.sha2_192s_with_sha512);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192f_with_sha512, SLHDSAParameters.sha2_192f_with_sha512);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256s_with_sha512, SLHDSAParameters.sha2_256s_with_sha512);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256f_with_sha512, SLHDSAParameters.sha2_256f_with_sha512);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_128s_with_shake128, SLHDSAParameters.shake_128s_with_shake128);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_128f_with_shake128, SLHDSAParameters.shake_128f_with_shake128);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_192s_with_shake256, SLHDSAParameters.shake_192s_with_shake256);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_192f_with_shake256, SLHDSAParameters.shake_192f_with_shake256);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_256s_with_shake256, SLHDSAParameters.shake_256s_with_shake256);
      shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_256f_with_shake256, SLHDSAParameters.shake_256f_with_shake256);
      sphincsPlusOids.put(SLHDSAParameters.sha2_128s, BCObjectIdentifiers.sphincsPlus_sha2_128s);
      sphincsPlusOids.put(SLHDSAParameters.sha2_128f, BCObjectIdentifiers.sphincsPlus_sha2_128f);
      sphincsPlusOids.put(SLHDSAParameters.sha2_192s, BCObjectIdentifiers.sphincsPlus_sha2_192s);
      sphincsPlusOids.put(SLHDSAParameters.sha2_192f, BCObjectIdentifiers.sphincsPlus_sha2_192f);
      sphincsPlusOids.put(SLHDSAParameters.sha2_256s, BCObjectIdentifiers.sphincsPlus_sha2_256s);
      sphincsPlusOids.put(SLHDSAParameters.sha2_256f, BCObjectIdentifiers.sphincsPlus_sha2_256f);
      sphincsPlusOids.put(SLHDSAParameters.shake_128s, BCObjectIdentifiers.sphincsPlus_shake_128s);
      sphincsPlusOids.put(SLHDSAParameters.shake_128f, BCObjectIdentifiers.sphincsPlus_shake_128f);
      sphincsPlusOids.put(SLHDSAParameters.shake_192s, BCObjectIdentifiers.sphincsPlus_shake_192s);
      sphincsPlusOids.put(SLHDSAParameters.shake_192f, BCObjectIdentifiers.sphincsPlus_shake_192f);
      sphincsPlusOids.put(SLHDSAParameters.shake_256s, BCObjectIdentifiers.sphincsPlus_shake_256s);
      sphincsPlusOids.put(SLHDSAParameters.shake_256f, BCObjectIdentifiers.sphincsPlus_shake_256f);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_128s_robust, BCObjectIdentifiers.sphincsPlus_sha2_128s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_128f_robust, BCObjectIdentifiers.sphincsPlus_sha2_128f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_128s_robust, BCObjectIdentifiers.sphincsPlus_shake_128s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_128f_robust, BCObjectIdentifiers.sphincsPlus_shake_128f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_128s, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_128f, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_192s_robust, BCObjectIdentifiers.sphincsPlus_sha2_192s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_192f_robust, BCObjectIdentifiers.sphincsPlus_sha2_192f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_192s_robust, BCObjectIdentifiers.sphincsPlus_shake_192s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_192f_robust, BCObjectIdentifiers.sphincsPlus_shake_192f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_192s, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_192f, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_256s_robust, BCObjectIdentifiers.sphincsPlus_sha2_256s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_256f_robust, BCObjectIdentifiers.sphincsPlus_sha2_256f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_256s_robust, BCObjectIdentifiers.sphincsPlus_shake_256s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_256f_robust, BCObjectIdentifiers.sphincsPlus_shake_256f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_256s, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_256f, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_128s_simple, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_128f_simple, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_192s_simple, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_192f_simple, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_256s_simple, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple);
      sphincsPlusOids.put(SPHINCSPlusParameters.haraka_256f_simple, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_128s, BCObjectIdentifiers.sphincsPlus_sha2_128s);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_128f, BCObjectIdentifiers.sphincsPlus_sha2_128f);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_192s, BCObjectIdentifiers.sphincsPlus_sha2_192s);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_192f, BCObjectIdentifiers.sphincsPlus_sha2_192f);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_256s, BCObjectIdentifiers.sphincsPlus_sha2_256s);
      sphincsPlusOids.put(SPHINCSPlusParameters.sha2_256f, BCObjectIdentifiers.sphincsPlus_sha2_256f);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_128s, BCObjectIdentifiers.sphincsPlus_shake_128s);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_128f, BCObjectIdentifiers.sphincsPlus_shake_128f);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_192s, BCObjectIdentifiers.sphincsPlus_shake_192s);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_192f, BCObjectIdentifiers.sphincsPlus_shake_192f);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_256s, BCObjectIdentifiers.sphincsPlus_shake_256s);
      sphincsPlusOids.put(SPHINCSPlusParameters.shake_256f, BCObjectIdentifiers.sphincsPlus_shake_256f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_128s, SPHINCSPlusParameters.sha2_128s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_128f, SPHINCSPlusParameters.sha2_128f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_128s, SPHINCSPlusParameters.shake_128s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_128f, SPHINCSPlusParameters.shake_128f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_192s, SPHINCSPlusParameters.sha2_192s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_192f, SPHINCSPlusParameters.sha2_192f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_192s, SPHINCSPlusParameters.shake_192s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_192f, SPHINCSPlusParameters.shake_192f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_256s, SPHINCSPlusParameters.sha2_256s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_256f, SPHINCSPlusParameters.sha2_256f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_256s, SPHINCSPlusParameters.shake_256s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_256f, SPHINCSPlusParameters.shake_256f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3, SPHINCSPlusParameters.sha2_128s_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3, SPHINCSPlusParameters.sha2_128f_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_128s_r3, SPHINCSPlusParameters.shake_128s_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_128f_r3, SPHINCSPlusParameters.shake_128f_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3, SPHINCSPlusParameters.haraka_128s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3, SPHINCSPlusParameters.haraka_128f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3, SPHINCSPlusParameters.sha2_192s_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3, SPHINCSPlusParameters.sha2_192f_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_192s_r3, SPHINCSPlusParameters.shake_192s_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_192f_r3, SPHINCSPlusParameters.shake_192f_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3, SPHINCSPlusParameters.haraka_192s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3, SPHINCSPlusParameters.haraka_192f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3, SPHINCSPlusParameters.sha2_256s_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3, SPHINCSPlusParameters.sha2_256f_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_256s_r3, SPHINCSPlusParameters.shake_256s_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_256f_r3, SPHINCSPlusParameters.shake_256f_robust);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3, SPHINCSPlusParameters.haraka_256s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3, SPHINCSPlusParameters.haraka_256f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3_simple, SPHINCSPlusParameters.sha2_128s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3_simple, SPHINCSPlusParameters.sha2_128f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_128s_r3_simple, SPHINCSPlusParameters.shake_128s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_128f_r3_simple, SPHINCSPlusParameters.shake_128f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple, SPHINCSPlusParameters.haraka_128s_simple);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple, SPHINCSPlusParameters.haraka_128f_simple);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3_simple, SPHINCSPlusParameters.sha2_192s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3_simple, SPHINCSPlusParameters.sha2_192f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_192s_r3_simple, SPHINCSPlusParameters.shake_192s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_192f_r3_simple, SPHINCSPlusParameters.shake_192f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple, SPHINCSPlusParameters.haraka_192s_simple);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple, SPHINCSPlusParameters.haraka_192f_simple);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3_simple, SPHINCSPlusParameters.sha2_256s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3_simple, SPHINCSPlusParameters.sha2_256f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_256s_r3_simple, SPHINCSPlusParameters.shake_256s);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_shake_256f_r3_simple, SPHINCSPlusParameters.shake_256f);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple, SPHINCSPlusParameters.haraka_256s_simple);
      sphincsPlusParams.put(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple, SPHINCSPlusParameters.haraka_256f_simple);
   }
}

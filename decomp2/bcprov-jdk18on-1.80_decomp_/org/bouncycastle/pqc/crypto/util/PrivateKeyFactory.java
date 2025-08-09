package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.CMCEPrivateKey;
import org.bouncycastle.pqc.asn1.FalconPrivateKey;
import org.bouncycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.SPHINCSPLUSPrivateKey;
import org.bouncycastle.pqc.asn1.SPHINCSPLUSPublicKey;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPrivateKey;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.bike.BIKEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.pqc.crypto.saber.SABERPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLAPrivateKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class PrivateKeyFactory {
   public static AsymmetricKeyParameter createKey(byte[] var0) throws IOException {
      if (var0 == null) {
         throw new IllegalArgumentException("privateKeyInfoData array null");
      } else if (var0.length == 0) {
         throw new IllegalArgumentException("privateKeyInfoData array empty");
      } else {
         return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(var0)));
      }
   }

   public static AsymmetricKeyParameter createKey(InputStream var0) throws IOException {
      return createKey(PrivateKeyInfo.getInstance((new ASN1InputStream(var0)).readObject()));
   }

   public static AsymmetricKeyParameter createKey(PrivateKeyInfo var0) throws IOException {
      if (var0 == null) {
         throw new IllegalArgumentException("keyInfo array null");
      } else {
         AlgorithmIdentifier var1 = var0.getPrivateKeyAlgorithm();
         ASN1ObjectIdentifier var2 = var1.getAlgorithm();
         if (var2.on(PQCObjectIdentifiers.qTESLA)) {
            ASN1OctetString var29 = ASN1OctetString.getInstance(var0.parsePrivateKey());
            return new QTESLAPrivateKeyParameters(Utils.qTeslaLookupSecurityCategory(var1), var29.getOctets());
         } else if (var2.equals(PQCObjectIdentifiers.sphincs256)) {
            return new SPHINCSPrivateKeyParameters(ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets(), Utils.sphincs256LookupTreeAlgName(SPHINCS256KeyParams.getInstance(var1.getParameters())));
         } else if (var2.equals(PQCObjectIdentifiers.newHope)) {
            return new NHPrivateKeyParameters(convert(ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets()));
         } else if (var2.equals(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig)) {
            ASN1OctetString var28 = parseOctetString(var0.getPrivateKey(), 64);
            byte[] var47 = var28.getOctets();
            ASN1BitString var54 = var0.getPublicKeyData();
            if (var54 != null) {
               byte[] var61 = var54.getOctets();
               return HSSPrivateKeyParameters.getInstance(Arrays.copyOfRange((byte[])var47, 4, var47.length), var61);
            } else {
               return HSSPrivateKeyParameters.getInstance(Arrays.copyOfRange((byte[])var47, 4, var47.length));
            }
         } else if (!var2.on(BCObjectIdentifiers.sphincsPlus) && !var2.on(BCObjectIdentifiers.sphincsPlus_interop)) {
            if (Utils.shldsaParams.containsKey(var2)) {
               SLHDSAParameters var27 = Utils.slhdsaParamsLookup(var2);
               ASN1OctetString var46 = parseOctetString(var0.getPrivateKey(), var27.getN() * 4);
               return new SLHDSAPrivateKeyParameters(var27, var46.getOctets());
            } else if (var2.on(BCObjectIdentifiers.picnic)) {
               byte[] var26 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
               PicnicParameters var45 = Utils.picnicParamsLookup(var2);
               return new PicnicPrivateKeyParameters(var45, var26);
            } else if (var2.on(BCObjectIdentifiers.pqc_kem_mceliece)) {
               CMCEPrivateKey var25 = CMCEPrivateKey.getInstance(var0.parsePrivateKey());
               CMCEParameters var44 = Utils.mcElieceParamsLookup(var2);
               return new CMCEPrivateKeyParameters(var44, var25.getDelta(), var25.getC(), var25.getG(), var25.getAlpha(), var25.getS());
            } else if (var2.on(BCObjectIdentifiers.pqc_kem_frodo)) {
               byte[] var24 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
               FrodoParameters var43 = Utils.frodoParamsLookup(var2);
               return new FrodoPrivateKeyParameters(var43, var24);
            } else if (var2.on(BCObjectIdentifiers.pqc_kem_saber)) {
               byte[] var23 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
               SABERParameters var42 = Utils.saberParamsLookup(var2);
               return new SABERPrivateKeyParameters(var42, var23);
            } else if (var2.on(BCObjectIdentifiers.pqc_kem_ntru)) {
               byte[] var22 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
               NTRUParameters var41 = Utils.ntruParamsLookup(var2);
               return new NTRUPrivateKeyParameters(var41, var22);
            } else if (!var2.equals(NISTObjectIdentifiers.id_alg_ml_kem_512) && !var2.equals(NISTObjectIdentifiers.id_alg_ml_kem_768) && !var2.equals(NISTObjectIdentifiers.id_alg_ml_kem_1024)) {
               if (var2.on(BCObjectIdentifiers.pqc_kem_ntrulprime)) {
                  ASN1Sequence var21 = ASN1Sequence.getInstance(var0.parsePrivateKey());
                  NTRULPRimeParameters var40 = Utils.ntrulprimeParamsLookup(var2);
                  return new NTRULPRimePrivateKeyParameters(var40, ASN1OctetString.getInstance(var21.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(var21.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(var21.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(var21.getObjectAt(3)).getOctets());
               } else if (var2.on(BCObjectIdentifiers.pqc_kem_sntruprime)) {
                  ASN1Sequence var20 = ASN1Sequence.getInstance(var0.parsePrivateKey());
                  SNTRUPrimeParameters var39 = Utils.sntruprimeParamsLookup(var2);
                  return new SNTRUPrimePrivateKeyParameters(var39, ASN1OctetString.getInstance(var20.getObjectAt(0)).getOctets(), ASN1OctetString.getInstance(var20.getObjectAt(1)).getOctets(), ASN1OctetString.getInstance(var20.getObjectAt(2)).getOctets(), ASN1OctetString.getInstance(var20.getObjectAt(3)).getOctets(), ASN1OctetString.getInstance(var20.getObjectAt(4)).getOctets());
               } else if (Utils.mldsaParams.containsKey(var2)) {
                  ASN1OctetString var19 = parseOctetString(var0.getPrivateKey(), 32);
                  MLDSAParameters var38 = Utils.mldsaParamsLookup(var2);
                  if (var19 instanceof DEROctetString) {
                     byte[] var53 = ASN1OctetString.getInstance(var19).getOctets();
                     if (var0.getPublicKeyData() != null) {
                        MLDSAPublicKeyParameters var60 = PublicKeyFactory.MLDSAConverter.getPublicKeyParams(var38, var0.getPublicKeyData());
                        return new MLDSAPrivateKeyParameters(var38, var53, var60);
                     } else {
                        return new MLDSAPrivateKeyParameters(var38, var53);
                     }
                  } else {
                     throw new IOException("not supported");
                  }
               } else if (!var2.equals(BCObjectIdentifiers.dilithium2) && !var2.equals(BCObjectIdentifiers.dilithium3) && !var2.equals(BCObjectIdentifiers.dilithium5)) {
                  if (!var2.equals(BCObjectIdentifiers.falcon_512) && !var2.equals(BCObjectIdentifiers.falcon_1024)) {
                     if (var2.on(BCObjectIdentifiers.pqc_kem_bike)) {
                        byte[] var18 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
                        BIKEParameters var37 = Utils.bikeParamsLookup(var2);
                        byte[] var52 = Arrays.copyOfRange((byte[])var18, 0, var37.getRByte());
                        byte[] var59 = Arrays.copyOfRange(var18, var37.getRByte(), 2 * var37.getRByte());
                        byte[] var64 = Arrays.copyOfRange(var18, 2 * var37.getRByte(), var18.length);
                        return new BIKEPrivateKeyParameters(var37, var52, var59, var64);
                     } else if (var2.on(BCObjectIdentifiers.pqc_kem_hqc)) {
                        byte[] var17 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
                        HQCParameters var36 = Utils.hqcParamsLookup(var2);
                        return new HQCPrivateKeyParameters(var36, var17);
                     } else if (var2.on(BCObjectIdentifiers.rainbow)) {
                        byte[] var16 = ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
                        RainbowParameters var35 = Utils.rainbowParamsLookup(var2);
                        return new RainbowPrivateKeyParameters(var35, var16);
                     } else if (var2.equals(PQCObjectIdentifiers.xmss)) {
                        XMSSKeyParams var15 = XMSSKeyParams.getInstance(var1.getParameters());
                        ASN1ObjectIdentifier var34 = var15.getTreeDigest().getAlgorithm();
                        XMSSPrivateKey var51 = XMSSPrivateKey.getInstance(var0.parsePrivateKey());

                        try {
                           XMSSPrivateKeyParameters.Builder var58 = (new XMSSPrivateKeyParameters.Builder(new XMSSParameters(var15.getHeight(), Utils.getDigest(var34)))).withIndex(var51.getIndex()).withSecretKeySeed(var51.getSecretKeySeed()).withSecretKeyPRF(var51.getSecretKeyPRF()).withPublicSeed(var51.getPublicSeed()).withRoot(var51.getRoot());
                           if (var51.getVersion() != 0) {
                              var58.withMaxIndex(var51.getMaxIndex());
                           }

                           if (var51.getBdsState() != null) {
                              BDS var63 = (BDS)XMSSUtil.deserialize(var51.getBdsState(), BDS.class);
                              var58.withBDSState(var63.withWOTSDigest(var34));
                           }

                           return var58.build();
                        } catch (ClassNotFoundException var8) {
                           throw new IOException("ClassNotFoundException processing BDS state: " + var8.getMessage());
                        }
                     } else if (var2.equals(PQCObjectIdentifiers.xmss_mt)) {
                        XMSSMTKeyParams var14 = XMSSMTKeyParams.getInstance(var1.getParameters());
                        ASN1ObjectIdentifier var33 = var14.getTreeDigest().getAlgorithm();

                        try {
                           XMSSMTPrivateKey var50 = XMSSMTPrivateKey.getInstance(var0.parsePrivateKey());
                           XMSSMTPrivateKeyParameters.Builder var57 = (new XMSSMTPrivateKeyParameters.Builder(new XMSSMTParameters(var14.getHeight(), var14.getLayers(), Utils.getDigest(var33)))).withIndex(var50.getIndex()).withSecretKeySeed(var50.getSecretKeySeed()).withSecretKeyPRF(var50.getSecretKeyPRF()).withPublicSeed(var50.getPublicSeed()).withRoot(var50.getRoot());
                           if (var50.getVersion() != 0) {
                              var57.withMaxIndex(var50.getMaxIndex());
                           }

                           if (var50.getBdsState() != null) {
                              BDSStateMap var62 = (BDSStateMap)XMSSUtil.deserialize(var50.getBdsState(), BDSStateMap.class);
                              var57.withBDSState(var62.withWOTSDigest(var33));
                           }

                           return var57.build();
                        } catch (ClassNotFoundException var9) {
                           throw new IOException("ClassNotFoundException processing BDS state: " + var9.getMessage());
                        }
                     } else if (var2.equals(PQCObjectIdentifiers.mcElieceCca2)) {
                        McElieceCCA2PrivateKey var13 = McElieceCCA2PrivateKey.getInstance(var0.parsePrivateKey());
                        return new McElieceCCA2PrivateKeyParameters(var13.getN(), var13.getK(), var13.getField(), var13.getGoppaPoly(), var13.getP(), Utils.getDigestName(var13.getDigest().getAlgorithm()));
                     } else {
                        throw new RuntimeException("algorithm identifier in private key not recognised");
                     }
                  } else {
                     FalconPrivateKey var12 = FalconPrivateKey.getInstance(var0.parsePrivateKey());
                     FalconParameters var32 = Utils.falconParamsLookup(var2);
                     return new FalconPrivateKeyParameters(var32, var12.getf(), var12.getG(), var12.getF(), var12.getPublicKey().getH());
                  }
               } else {
                  ASN1Encodable var11 = var0.parsePrivateKey();
                  DilithiumParameters var31 = Utils.dilithiumParamsLookup(var2);
                  if (var11 instanceof ASN1Sequence) {
                     ASN1Sequence var49 = ASN1Sequence.getInstance(var11);
                     int var56 = ASN1Integer.getInstance(var49.getObjectAt(0)).intValueExact();
                     if (var56 != 0) {
                        throw new IOException("unknown private key version: " + var56);
                     } else if (var0.getPublicKeyData() != null) {
                        DilithiumPublicKeyParameters var7 = PublicKeyFactory.DilithiumConverter.getPublicKeyParams(var31, var0.getPublicKeyData());
                        return new DilithiumPrivateKeyParameters(var31, ASN1BitString.getInstance(var49.getObjectAt(1)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(2)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(3)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(4)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(5)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(6)).getOctets(), var7.getT1());
                     } else {
                        return new DilithiumPrivateKeyParameters(var31, ASN1BitString.getInstance(var49.getObjectAt(1)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(2)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(3)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(4)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(5)).getOctets(), ASN1BitString.getInstance(var49.getObjectAt(6)).getOctets(), (byte[])null);
                     }
                  } else if (var11 instanceof DEROctetString) {
                     byte[] var48 = ASN1OctetString.getInstance(var11).getOctets();
                     if (var0.getPublicKeyData() != null) {
                        DilithiumPublicKeyParameters var55 = PublicKeyFactory.DilithiumConverter.getPublicKeyParams(var31, var0.getPublicKeyData());
                        return new DilithiumPrivateKeyParameters(var31, var48, var55);
                     } else {
                        return new DilithiumPrivateKeyParameters(var31, var48, (DilithiumPublicKeyParameters)null);
                     }
                  } else {
                     throw new IOException("not supported");
                  }
               }
            } else {
               ASN1OctetString var10 = parseOctetString(var0.getPrivateKey(), 64);
               MLKEMParameters var30 = Utils.mlkemParamsLookup(var2);
               return new MLKEMPrivateKeyParameters(var30, var10.getOctets());
            }
         } else {
            SPHINCSPlusParameters var3 = Utils.sphincsPlusParamsLookup(var2);
            ASN1Encodable var4 = var0.parsePrivateKey();
            if (var4 instanceof ASN1Sequence) {
               SPHINCSPLUSPrivateKey var5 = SPHINCSPLUSPrivateKey.getInstance(var4);
               SPHINCSPLUSPublicKey var6 = var5.getPublicKey();
               return new SPHINCSPlusPrivateKeyParameters(var3, var5.getSkseed(), var5.getSkprf(), var6.getPkseed(), var6.getPkroot());
            } else {
               return new SPHINCSPlusPrivateKeyParameters(var3, ASN1OctetString.getInstance(var4).getOctets());
            }
         }
      }
   }

   private static ASN1OctetString parseOctetString(ASN1OctetString var0, int var1) throws IOException {
      byte[] var2 = var0.getOctets();
      if (var2.length == var1) {
         return var0;
      } else {
         var2 = Utils.readOctetString(var2);
         return (ASN1OctetString)(var2 != null ? new DEROctetString(var2) : var0);
      }
   }

   private static short[] convert(byte[] var0) {
      short[] var1 = new short[var0.length / 2];

      for(int var2 = 0; var2 != var1.length; ++var2) {
         var1[var2] = Pack.littleEndianToShort(var0, var2 * 2);
      }

      return var1;
   }
}

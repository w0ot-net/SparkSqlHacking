package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.CMCEPrivateKey;
import org.bouncycastle.pqc.asn1.CMCEPublicKey;
import org.bouncycastle.pqc.asn1.FalconPrivateKey;
import org.bouncycastle.pqc.asn1.FalconPublicKey;
import org.bouncycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPrivateKey;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.bike.BIKEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.Composer;
import org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.saber.SABERPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLAPrivateKeyParameters;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Pack;

public class PrivateKeyInfoFactory {
   private PrivateKeyInfoFactory() {
   }

   public static PrivateKeyInfo createPrivateKeyInfo(AsymmetricKeyParameter var0) throws IOException {
      return createPrivateKeyInfo(var0, (ASN1Set)null);
   }

   public static PrivateKeyInfo createPrivateKeyInfo(AsymmetricKeyParameter var0, ASN1Set var1) throws IOException {
      if (var0 instanceof QTESLAPrivateKeyParameters) {
         QTESLAPrivateKeyParameters var29 = (QTESLAPrivateKeyParameters)var0;
         AlgorithmIdentifier var52 = Utils.qTeslaLookupAlgID(var29.getSecurityCategory());
         return new PrivateKeyInfo(var52, new DEROctetString(var29.getSecret()), var1);
      } else if (var0 instanceof SPHINCSPrivateKeyParameters) {
         SPHINCSPrivateKeyParameters var28 = (SPHINCSPrivateKeyParameters)var0;
         AlgorithmIdentifier var51 = new AlgorithmIdentifier(PQCObjectIdentifiers.sphincs256, new SPHINCS256KeyParams(Utils.sphincs256LookupTreeAlgID(var28.getTreeDigest())));
         return new PrivateKeyInfo(var51, new DEROctetString(var28.getKeyData()));
      } else if (!(var0 instanceof NHPrivateKeyParameters)) {
         if (var0 instanceof LMSPrivateKeyParameters) {
            LMSPrivateKeyParameters var27 = (LMSPrivateKeyParameters)var0;
            byte[] var50 = Composer.compose().u32str(1).bytes((Encodable)var27).build();
            byte[] var69 = Composer.compose().u32str(1).bytes((Encodable)var27.getPublicKey()).build();
            AlgorithmIdentifier var75 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig);
            return new PrivateKeyInfo(var75, new DEROctetString(var50), var1, var69);
         } else if (var0 instanceof HSSPrivateKeyParameters) {
            HSSPrivateKeyParameters var26 = (HSSPrivateKeyParameters)var0;
            byte[] var49 = Composer.compose().u32str(var26.getL()).bytes((Encodable)var26).build();
            byte[] var68 = Composer.compose().u32str(var26.getL()).bytes((Encodable)var26.getPublicKey().getLMSPublicKey()).build();
            AlgorithmIdentifier var74 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig);
            return new PrivateKeyInfo(var74, new DEROctetString(var49), var1, var68);
         } else if (var0 instanceof SPHINCSPlusPrivateKeyParameters) {
            SPHINCSPlusPrivateKeyParameters var25 = (SPHINCSPlusPrivateKeyParameters)var0;
            AlgorithmIdentifier var48 = new AlgorithmIdentifier(Utils.sphincsPlusOidLookup(var25.getParameters()));
            return new PrivateKeyInfo(var48, new DEROctetString(var25.getEncoded()), var1, var25.getPublicKey());
         } else if (var0 instanceof SLHDSAPrivateKeyParameters) {
            SLHDSAPrivateKeyParameters var24 = (SLHDSAPrivateKeyParameters)var0;
            AlgorithmIdentifier var47 = new AlgorithmIdentifier(Utils.slhdsaOidLookup(var24.getParameters()));
            return new PrivateKeyInfo(var47, var24.getEncoded(), var1, var24.getPublicKey());
         } else if (var0 instanceof PicnicPrivateKeyParameters) {
            PicnicPrivateKeyParameters var23 = (PicnicPrivateKeyParameters)var0;
            byte[] var46 = var23.getEncoded();
            AlgorithmIdentifier var67 = new AlgorithmIdentifier(Utils.picnicOidLookup(var23.getParameters()));
            return new PrivateKeyInfo(var67, new DEROctetString(var46), var1);
         } else if (var0 instanceof CMCEPrivateKeyParameters) {
            CMCEPrivateKeyParameters var22 = (CMCEPrivateKeyParameters)var0;
            AlgorithmIdentifier var45 = new AlgorithmIdentifier(Utils.mcElieceOidLookup(var22.getParameters()));
            CMCEPublicKey var66 = new CMCEPublicKey(var22.reconstructPublicKey());
            CMCEPrivateKey var73 = new CMCEPrivateKey(0, var22.getDelta(), var22.getC(), var22.getG(), var22.getAlpha(), var22.getS(), var66);
            return new PrivateKeyInfo(var45, var73, var1);
         } else if (var0 instanceof XMSSPrivateKeyParameters) {
            XMSSPrivateKeyParameters var21 = (XMSSPrivateKeyParameters)var0;
            AlgorithmIdentifier var44 = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss, new XMSSKeyParams(var21.getParameters().getHeight(), Utils.xmssLookupTreeAlgID(var21.getTreeDigest())));
            return new PrivateKeyInfo(var44, xmssCreateKeyStructure(var21), var1);
         } else if (var0 instanceof XMSSMTPrivateKeyParameters) {
            XMSSMTPrivateKeyParameters var20 = (XMSSMTPrivateKeyParameters)var0;
            AlgorithmIdentifier var43 = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyParams(var20.getParameters().getHeight(), var20.getParameters().getLayers(), Utils.xmssLookupTreeAlgID(var20.getTreeDigest())));
            return new PrivateKeyInfo(var43, xmssmtCreateKeyStructure(var20), var1);
         } else if (var0 instanceof McElieceCCA2PrivateKeyParameters) {
            McElieceCCA2PrivateKeyParameters var19 = (McElieceCCA2PrivateKeyParameters)var0;
            McElieceCCA2PrivateKey var42 = new McElieceCCA2PrivateKey(var19.getN(), var19.getK(), var19.getField(), var19.getGoppaPoly(), var19.getP(), Utils.getAlgorithmIdentifier(var19.getDigest()));
            AlgorithmIdentifier var65 = new AlgorithmIdentifier(PQCObjectIdentifiers.mcElieceCca2);
            return new PrivateKeyInfo(var65, var42);
         } else if (var0 instanceof FrodoPrivateKeyParameters) {
            FrodoPrivateKeyParameters var18 = (FrodoPrivateKeyParameters)var0;
            byte[] var41 = var18.getEncoded();
            AlgorithmIdentifier var64 = new AlgorithmIdentifier(Utils.frodoOidLookup(var18.getParameters()));
            return new PrivateKeyInfo(var64, new DEROctetString(var41), var1);
         } else if (var0 instanceof SABERPrivateKeyParameters) {
            SABERPrivateKeyParameters var17 = (SABERPrivateKeyParameters)var0;
            byte[] var40 = var17.getEncoded();
            AlgorithmIdentifier var63 = new AlgorithmIdentifier(Utils.saberOidLookup(var17.getParameters()));
            return new PrivateKeyInfo(var63, new DEROctetString(var40), var1);
         } else if (var0 instanceof NTRUPrivateKeyParameters) {
            NTRUPrivateKeyParameters var16 = (NTRUPrivateKeyParameters)var0;
            byte[] var39 = var16.getEncoded();
            AlgorithmIdentifier var62 = new AlgorithmIdentifier(Utils.ntruOidLookup(var16.getParameters()));
            return new PrivateKeyInfo(var62, new DEROctetString(var39), var1);
         } else if (var0 instanceof FalconPrivateKeyParameters) {
            FalconPrivateKeyParameters var15 = (FalconPrivateKeyParameters)var0;
            AlgorithmIdentifier var38 = new AlgorithmIdentifier(Utils.falconOidLookup(var15.getParameters()));
            FalconPublicKey var61 = new FalconPublicKey(var15.getPublicKey());
            FalconPrivateKey var72 = new FalconPrivateKey(0, var15.getSpolyf(), var15.getG(), var15.getSpolyF(), var61);
            return new PrivateKeyInfo(var38, var72, var1);
         } else if (var0 instanceof MLKEMPrivateKeyParameters) {
            MLKEMPrivateKeyParameters var14 = (MLKEMPrivateKeyParameters)var0;
            AlgorithmIdentifier var37 = new AlgorithmIdentifier(Utils.mlkemOidLookup(var14.getParameters()));
            byte[] var60 = var14.getSeed();
            return var60 == null ? new PrivateKeyInfo(var37, var14.getEncoded(), var1) : new PrivateKeyInfo(var37, var60, var1);
         } else if (var0 instanceof NTRULPRimePrivateKeyParameters) {
            NTRULPRimePrivateKeyParameters var13 = (NTRULPRimePrivateKeyParameters)var0;
            ASN1EncodableVector var36 = new ASN1EncodableVector();
            var36.add(new DEROctetString(var13.getEnca()));
            var36.add(new DEROctetString(var13.getPk()));
            var36.add(new DEROctetString(var13.getRho()));
            var36.add(new DEROctetString(var13.getHash()));
            AlgorithmIdentifier var59 = new AlgorithmIdentifier(Utils.ntrulprimeOidLookup(var13.getParameters()));
            return new PrivateKeyInfo(var59, new DERSequence(var36), var1);
         } else if (var0 instanceof SNTRUPrimePrivateKeyParameters) {
            SNTRUPrimePrivateKeyParameters var12 = (SNTRUPrimePrivateKeyParameters)var0;
            ASN1EncodableVector var35 = new ASN1EncodableVector();
            var35.add(new DEROctetString(var12.getF()));
            var35.add(new DEROctetString(var12.getGinv()));
            var35.add(new DEROctetString(var12.getPk()));
            var35.add(new DEROctetString(var12.getRho()));
            var35.add(new DEROctetString(var12.getHash()));
            AlgorithmIdentifier var58 = new AlgorithmIdentifier(Utils.sntruprimeOidLookup(var12.getParameters()));
            return new PrivateKeyInfo(var58, new DERSequence(var35), var1);
         } else if (var0 instanceof MLDSAPrivateKeyParameters) {
            MLDSAPrivateKeyParameters var11 = (MLDSAPrivateKeyParameters)var0;
            AlgorithmIdentifier var34 = new AlgorithmIdentifier(Utils.mldsaOidLookup(var11.getParameters()));
            byte[] var57 = var11.getSeed();
            if (var57 == null) {
               MLDSAPublicKeyParameters var71 = var11.getPublicKeyParameters();
               return new PrivateKeyInfo(var34, var11.getEncoded(), var1, var71.getEncoded());
            } else {
               MLDSAPublicKeyParameters var70 = var11.getPublicKeyParameters();
               return new PrivateKeyInfo(var34, var11.getSeed(), var1);
            }
         } else if (var0 instanceof DilithiumPrivateKeyParameters) {
            DilithiumPrivateKeyParameters var10 = (DilithiumPrivateKeyParameters)var0;
            AlgorithmIdentifier var33 = new AlgorithmIdentifier(Utils.dilithiumOidLookup(var10.getParameters()));
            DilithiumPublicKeyParameters var56 = var10.getPublicKeyParameters();
            return new PrivateKeyInfo(var33, new DEROctetString(var10.getEncoded()), var1, var56.getEncoded());
         } else if (var0 instanceof BIKEPrivateKeyParameters) {
            BIKEPrivateKeyParameters var9 = (BIKEPrivateKeyParameters)var0;
            AlgorithmIdentifier var32 = new AlgorithmIdentifier(Utils.bikeOidLookup(var9.getParameters()));
            byte[] var55 = var9.getEncoded();
            return new PrivateKeyInfo(var32, new DEROctetString(var55), var1);
         } else if (var0 instanceof HQCPrivateKeyParameters) {
            HQCPrivateKeyParameters var8 = (HQCPrivateKeyParameters)var0;
            AlgorithmIdentifier var31 = new AlgorithmIdentifier(Utils.hqcOidLookup(var8.getParameters()));
            byte[] var54 = var8.getEncoded();
            return new PrivateKeyInfo(var31, new DEROctetString(var54), var1);
         } else if (var0 instanceof RainbowPrivateKeyParameters) {
            RainbowPrivateKeyParameters var7 = (RainbowPrivateKeyParameters)var0;
            AlgorithmIdentifier var30 = new AlgorithmIdentifier(Utils.rainbowOidLookup(var7.getParameters()));
            byte[] var53 = var7.getEncoded();
            return new PrivateKeyInfo(var30, new DEROctetString(var53), var1);
         } else {
            throw new IOException("key parameters not recognized");
         }
      } else {
         NHPrivateKeyParameters var2 = (NHPrivateKeyParameters)var0;
         AlgorithmIdentifier var3 = new AlgorithmIdentifier(PQCObjectIdentifiers.newHope);
         short[] var4 = var2.getSecData();
         byte[] var5 = new byte[var4.length * 2];

         for(int var6 = 0; var6 != var4.length; ++var6) {
            Pack.shortToLittleEndian(var4[var6], var5, var6 * 2);
         }

         return new PrivateKeyInfo(var3, new DEROctetString(var5));
      }
   }

   private static XMSSPrivateKey xmssCreateKeyStructure(XMSSPrivateKeyParameters var0) throws IOException {
      byte[] var1 = var0.getEncoded();
      int var2 = var0.getParameters().getTreeDigestSize();
      int var3 = var0.getParameters().getHeight();
      byte var4 = 4;
      int var9 = 0;
      int var10 = (int)XMSSUtil.bytesToXBigEndian(var1, var9, var4);
      if (!XMSSUtil.isIndexValid(var3, (long)var10)) {
         throw new IllegalArgumentException("index out of bounds");
      } else {
         var9 += var4;
         byte[] var11 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var12 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var13 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var14 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var15 = XMSSUtil.extractBytesAtOffset(var1, var9, var1.length - var9);
         Object var16 = null;

         try {
            var24 = (BDS)XMSSUtil.deserialize(var15, BDS.class);
         } catch (ClassNotFoundException var18) {
            throw new IOException("cannot parse BDS: " + var18.getMessage());
         }

         return var24.getMaxIndex() != (1 << var3) - 1 ? new XMSSPrivateKey(var10, var11, var12, var13, var14, var15, var24.getMaxIndex()) : new XMSSPrivateKey(var10, var11, var12, var13, var14, var15);
      }
   }

   private static XMSSMTPrivateKey xmssmtCreateKeyStructure(XMSSMTPrivateKeyParameters var0) throws IOException {
      byte[] var1 = var0.getEncoded();
      int var2 = var0.getParameters().getTreeDigestSize();
      int var3 = var0.getParameters().getHeight();
      int var4 = (var3 + 7) / 8;
      int var9 = 0;
      int var10 = (int)XMSSUtil.bytesToXBigEndian(var1, var9, var4);
      if (!XMSSUtil.isIndexValid(var3, (long)var10)) {
         throw new IllegalArgumentException("index out of bounds");
      } else {
         var9 += var4;
         byte[] var11 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var12 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var13 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var14 = XMSSUtil.extractBytesAtOffset(var1, var9, var2);
         var9 += var2;
         byte[] var15 = XMSSUtil.extractBytesAtOffset(var1, var9, var1.length - var9);
         Object var16 = null;

         try {
            var24 = (BDSStateMap)XMSSUtil.deserialize(var15, BDSStateMap.class);
         } catch (ClassNotFoundException var18) {
            throw new IOException("cannot parse BDSStateMap: " + var18.getMessage());
         }

         return var24.getMaxIndex() != (1L << var3) - 1L ? new XMSSMTPrivateKey((long)var10, var11, var12, var13, var14, var15, var24.getMaxIndex()) : new XMSSMTPrivateKey((long)var10, var11, var12, var13, var14, var15);
      }
   }
}

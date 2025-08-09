package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.internal.asn1.isara.IsaraObjectIdentifiers;
import org.bouncycastle.pqc.asn1.McElieceCCA2PublicKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPublicKey;
import org.bouncycastle.pqc.asn1.XMSSPublicKey;
import org.bouncycastle.pqc.crypto.bike.BIKEPublicKeyParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEPublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPublicKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPublicKeyParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCPublicKeyParameters;
import org.bouncycastle.pqc.crypto.lms.Composer;
import org.bouncycastle.pqc.crypto.lms.HSSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.lms.LMSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAPublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPublicKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPublicKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePublicKeyParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimePublicKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPublicKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.crypto.saber.SABERPublicKeyParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLAPublicKeyParameters;
import org.bouncycastle.util.Encodable;

public class SubjectPublicKeyInfoFactory {
   private SubjectPublicKeyInfoFactory() {
   }

   public static SubjectPublicKeyInfo createSubjectPublicKeyInfo(AsymmetricKeyParameter var0) throws IOException {
      if (var0 instanceof QTESLAPublicKeyParameters) {
         QTESLAPublicKeyParameters var28 = (QTESLAPublicKeyParameters)var0;
         AlgorithmIdentifier var51 = Utils.qTeslaLookupAlgID(var28.getSecurityCategory());
         return new SubjectPublicKeyInfo(var51, var28.getPublicData());
      } else if (var0 instanceof SPHINCSPublicKeyParameters) {
         SPHINCSPublicKeyParameters var27 = (SPHINCSPublicKeyParameters)var0;
         AlgorithmIdentifier var50 = new AlgorithmIdentifier(PQCObjectIdentifiers.sphincs256, new SPHINCS256KeyParams(Utils.sphincs256LookupTreeAlgID(var27.getTreeDigest())));
         return new SubjectPublicKeyInfo(var50, var27.getKeyData());
      } else if (var0 instanceof NHPublicKeyParameters) {
         NHPublicKeyParameters var26 = (NHPublicKeyParameters)var0;
         AlgorithmIdentifier var49 = new AlgorithmIdentifier(PQCObjectIdentifiers.newHope);
         return new SubjectPublicKeyInfo(var49, var26.getPubData());
      } else if (var0 instanceof LMSPublicKeyParameters) {
         LMSPublicKeyParameters var25 = (LMSPublicKeyParameters)var0;
         byte[] var48 = Composer.compose().u32str(1).bytes((Encodable)var25).build();
         AlgorithmIdentifier var68 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig);
         return new SubjectPublicKeyInfo(var68, var48);
      } else if (var0 instanceof HSSPublicKeyParameters) {
         HSSPublicKeyParameters var24 = (HSSPublicKeyParameters)var0;
         byte[] var47 = Composer.compose().u32str(var24.getL()).bytes((Encodable)var24.getLMSPublicKey()).build();
         AlgorithmIdentifier var67 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig);
         return new SubjectPublicKeyInfo(var67, var47);
      } else if (var0 instanceof SLHDSAPublicKeyParameters) {
         SLHDSAPublicKeyParameters var23 = (SLHDSAPublicKeyParameters)var0;
         byte[] var46 = var23.getEncoded();
         AlgorithmIdentifier var66 = new AlgorithmIdentifier(Utils.slhdsaOidLookup(var23.getParameters()));
         return new SubjectPublicKeyInfo(var66, var46);
      } else if (var0 instanceof SPHINCSPlusPublicKeyParameters) {
         SPHINCSPlusPublicKeyParameters var22 = (SPHINCSPlusPublicKeyParameters)var0;
         byte[] var45 = var22.getEncoded();
         AlgorithmIdentifier var65 = new AlgorithmIdentifier(Utils.sphincsPlusOidLookup(var22.getParameters()));
         return new SubjectPublicKeyInfo(var65, var45);
      } else if (var0 instanceof CMCEPublicKeyParameters) {
         CMCEPublicKeyParameters var21 = (CMCEPublicKeyParameters)var0;
         byte[] var44 = var21.getEncoded();
         AlgorithmIdentifier var64 = new AlgorithmIdentifier(Utils.mcElieceOidLookup(var21.getParameters()));
         return new SubjectPublicKeyInfo(var64, var44);
      } else if (var0 instanceof XMSSPublicKeyParameters) {
         XMSSPublicKeyParameters var20 = (XMSSPublicKeyParameters)var0;
         byte[] var43 = var20.getPublicSeed();
         byte[] var63 = var20.getRoot();
         byte[] var70 = var20.getEncoded();
         if (var70.length > var43.length + var63.length) {
            AlgorithmIdentifier var73 = new AlgorithmIdentifier(IsaraObjectIdentifiers.id_alg_xmss);
            return new SubjectPublicKeyInfo(var73, new DEROctetString(var70));
         } else {
            AlgorithmIdentifier var72 = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss, new XMSSKeyParams(var20.getParameters().getHeight(), Utils.xmssLookupTreeAlgID(var20.getTreeDigest())));
            return new SubjectPublicKeyInfo(var72, new XMSSPublicKey(var43, var63));
         }
      } else if (var0 instanceof XMSSMTPublicKeyParameters) {
         XMSSMTPublicKeyParameters var19 = (XMSSMTPublicKeyParameters)var0;
         byte[] var42 = var19.getPublicSeed();
         byte[] var62 = var19.getRoot();
         byte[] var69 = var19.getEncoded();
         if (var69.length > var42.length + var62.length) {
            AlgorithmIdentifier var71 = new AlgorithmIdentifier(IsaraObjectIdentifiers.id_alg_xmssmt);
            return new SubjectPublicKeyInfo(var71, new DEROctetString(var69));
         } else {
            AlgorithmIdentifier var5 = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyParams(var19.getParameters().getHeight(), var19.getParameters().getLayers(), Utils.xmssLookupTreeAlgID(var19.getTreeDigest())));
            return new SubjectPublicKeyInfo(var5, new XMSSMTPublicKey(var19.getPublicSeed(), var19.getRoot()));
         }
      } else if (var0 instanceof McElieceCCA2PublicKeyParameters) {
         McElieceCCA2PublicKeyParameters var18 = (McElieceCCA2PublicKeyParameters)var0;
         McElieceCCA2PublicKey var41 = new McElieceCCA2PublicKey(var18.getN(), var18.getT(), var18.getG(), Utils.getAlgorithmIdentifier(var18.getDigest()));
         AlgorithmIdentifier var61 = new AlgorithmIdentifier(PQCObjectIdentifiers.mcElieceCca2);
         return new SubjectPublicKeyInfo(var61, var41);
      } else if (var0 instanceof FrodoPublicKeyParameters) {
         FrodoPublicKeyParameters var17 = (FrodoPublicKeyParameters)var0;
         byte[] var40 = var17.getEncoded();
         AlgorithmIdentifier var60 = new AlgorithmIdentifier(Utils.frodoOidLookup(var17.getParameters()));
         return new SubjectPublicKeyInfo(var60, new DEROctetString(var40));
      } else if (var0 instanceof SABERPublicKeyParameters) {
         SABERPublicKeyParameters var16 = (SABERPublicKeyParameters)var0;
         byte[] var39 = var16.getEncoded();
         AlgorithmIdentifier var59 = new AlgorithmIdentifier(Utils.saberOidLookup(var16.getParameters()));
         return new SubjectPublicKeyInfo(var59, new DERSequence(new DEROctetString(var39)));
      } else if (var0 instanceof PicnicPublicKeyParameters) {
         PicnicPublicKeyParameters var15 = (PicnicPublicKeyParameters)var0;
         byte[] var38 = var15.getEncoded();
         AlgorithmIdentifier var58 = new AlgorithmIdentifier(Utils.picnicOidLookup(var15.getParameters()));
         return new SubjectPublicKeyInfo(var58, new DEROctetString(var38));
      } else if (var0 instanceof NTRUPublicKeyParameters) {
         NTRUPublicKeyParameters var14 = (NTRUPublicKeyParameters)var0;
         byte[] var37 = var14.getEncoded();
         AlgorithmIdentifier var57 = new AlgorithmIdentifier(Utils.ntruOidLookup(var14.getParameters()));
         return new SubjectPublicKeyInfo(var57, var37);
      } else if (var0 instanceof FalconPublicKeyParameters) {
         FalconPublicKeyParameters var13 = (FalconPublicKeyParameters)var0;
         byte[] var36 = var13.getH();
         AlgorithmIdentifier var56 = new AlgorithmIdentifier(Utils.falconOidLookup(var13.getParameters()));
         byte[] var4 = new byte[var36.length + 1];
         var4[0] = (byte)(0 + var13.getParameters().getLogN());
         System.arraycopy(var36, 0, var4, 1, var36.length);
         return new SubjectPublicKeyInfo(var56, var4);
      } else if (var0 instanceof MLKEMPublicKeyParameters) {
         MLKEMPublicKeyParameters var12 = (MLKEMPublicKeyParameters)var0;
         AlgorithmIdentifier var35 = new AlgorithmIdentifier(Utils.mlkemOidLookup(var12.getParameters()));
         return new SubjectPublicKeyInfo(var35, var12.getEncoded());
      } else if (var0 instanceof NTRULPRimePublicKeyParameters) {
         NTRULPRimePublicKeyParameters var11 = (NTRULPRimePublicKeyParameters)var0;
         byte[] var34 = var11.getEncoded();
         AlgorithmIdentifier var55 = new AlgorithmIdentifier(Utils.ntrulprimeOidLookup(var11.getParameters()));
         return new SubjectPublicKeyInfo(var55, new DEROctetString(var34));
      } else if (var0 instanceof SNTRUPrimePublicKeyParameters) {
         SNTRUPrimePublicKeyParameters var10 = (SNTRUPrimePublicKeyParameters)var0;
         byte[] var33 = var10.getEncoded();
         AlgorithmIdentifier var54 = new AlgorithmIdentifier(Utils.sntruprimeOidLookup(var10.getParameters()));
         return new SubjectPublicKeyInfo(var54, new DEROctetString(var33));
      } else if (var0 instanceof DilithiumPublicKeyParameters) {
         DilithiumPublicKeyParameters var9 = (DilithiumPublicKeyParameters)var0;
         AlgorithmIdentifier var32 = new AlgorithmIdentifier(Utils.dilithiumOidLookup(var9.getParameters()));
         return new SubjectPublicKeyInfo(var32, var9.getEncoded());
      } else if (var0 instanceof MLDSAPublicKeyParameters) {
         MLDSAPublicKeyParameters var8 = (MLDSAPublicKeyParameters)var0;
         AlgorithmIdentifier var31 = new AlgorithmIdentifier(Utils.mldsaOidLookup(var8.getParameters()));
         return new SubjectPublicKeyInfo(var31, var8.getEncoded());
      } else if (var0 instanceof BIKEPublicKeyParameters) {
         BIKEPublicKeyParameters var7 = (BIKEPublicKeyParameters)var0;
         byte[] var30 = var7.getEncoded();
         AlgorithmIdentifier var53 = new AlgorithmIdentifier(Utils.bikeOidLookup(var7.getParameters()));
         return new SubjectPublicKeyInfo(var53, var30);
      } else if (var0 instanceof HQCPublicKeyParameters) {
         HQCPublicKeyParameters var6 = (HQCPublicKeyParameters)var0;
         byte[] var29 = var6.getEncoded();
         AlgorithmIdentifier var52 = new AlgorithmIdentifier(Utils.hqcOidLookup(var6.getParameters()));
         return new SubjectPublicKeyInfo(var52, var29);
      } else if (var0 instanceof RainbowPublicKeyParameters) {
         RainbowPublicKeyParameters var1 = (RainbowPublicKeyParameters)var0;
         byte[] var2 = var1.getEncoded();
         AlgorithmIdentifier var3 = new AlgorithmIdentifier(Utils.rainbowOidLookup(var1.getParameters()));
         return new SubjectPublicKeyInfo(var3, new DEROctetString(var2));
      } else {
         throw new IOException("key parameters not recognized");
      }
   }
}

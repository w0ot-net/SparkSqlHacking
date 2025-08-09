package org.bouncycastle.crypto.util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECGOST3410Parameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X448PublicKeyParameters;
import org.bouncycastle.internal.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.internal.asn1.rosstandart.RosstandartObjectIdentifiers;

public class SubjectPublicKeyInfoFactory {
   private static Set cryptoProOids = new HashSet(5);

   private SubjectPublicKeyInfoFactory() {
   }

   public static SubjectPublicKeyInfo createSubjectPublicKeyInfo(AsymmetricKeyParameter var0) throws IOException {
      if (var0 instanceof RSAKeyParameters) {
         RSAKeyParameters var18 = (RSAKeyParameters)var0;
         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPublicKey(var18.getModulus(), var18.getExponent()));
      } else if (var0 instanceof DSAPublicKeyParameters) {
         DSAPublicKeyParameters var17 = (DSAPublicKeyParameters)var0;
         DSAParameter var19 = null;
         DSAParameters var21 = var17.getParameters();
         if (var21 != null) {
            var19 = new DSAParameter(var21.getP(), var21.getQ(), var21.getG());
         }

         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, var19), new ASN1Integer(var17.getY()));
      } else if (var0 instanceof ECPublicKeyParameters) {
         ECPublicKeyParameters var16 = (ECPublicKeyParameters)var0;
         ECDomainParameters var2 = var16.getParameters();
         X962Parameters var3;
         if (var2 == null) {
            var3 = new X962Parameters(DERNull.INSTANCE);
         } else {
            if (var2 instanceof ECGOST3410Parameters) {
               ECGOST3410Parameters var23 = (ECGOST3410Parameters)var2;
               BigInteger var5 = var16.getQ().getAffineXCoord().toBigInteger();
               BigInteger var6 = var16.getQ().getAffineYCoord().toBigInteger();
               GOST3410PublicKeyAlgParameters var20 = new GOST3410PublicKeyAlgParameters(var23.getPublicKeyParamSet(), var23.getDigestParamSet());
               short var7;
               byte var8;
               ASN1ObjectIdentifier var9;
               if (cryptoProOids.contains(var23.getPublicKeyParamSet())) {
                  var7 = 64;
                  var8 = 32;
                  var9 = CryptoProObjectIdentifiers.gostR3410_2001;
               } else {
                  boolean var10 = var5.bitLength() > 256;
                  if (var10) {
                     var7 = 128;
                     var8 = 64;
                     var9 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512;
                  } else {
                     var7 = 64;
                     var8 = 32;
                     var9 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256;
                  }
               }

               byte[] var24 = new byte[var7];
               extractBytes(var24, var7 / 2, 0, var5);
               extractBytes(var24, var7 / 2, var8, var6);

               try {
                  return new SubjectPublicKeyInfo(new AlgorithmIdentifier(var9, var20), new DEROctetString(var24));
               } catch (IOException var12) {
                  return null;
               }
            }

            if (var2 instanceof ECNamedDomainParameters) {
               var3 = new X962Parameters(((ECNamedDomainParameters)var2).getName());
            } else {
               X9ECParameters var4 = new X9ECParameters(var2.getCurve(), new X9ECPoint(var2.getG(), false), var2.getN(), var2.getH(), var2.getSeed());
               var3 = new X962Parameters(var4);
            }
         }

         byte[] var22 = var16.getQ().getEncoded(false);
         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, var3), var22);
      } else if (var0 instanceof X448PublicKeyParameters) {
         X448PublicKeyParameters var15 = (X448PublicKeyParameters)var0;
         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X448), var15.getEncoded());
      } else if (var0 instanceof X25519PublicKeyParameters) {
         X25519PublicKeyParameters var14 = (X25519PublicKeyParameters)var0;
         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519), var14.getEncoded());
      } else if (var0 instanceof Ed448PublicKeyParameters) {
         Ed448PublicKeyParameters var13 = (Ed448PublicKeyParameters)var0;
         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed448), var13.getEncoded());
      } else if (var0 instanceof Ed25519PublicKeyParameters) {
         Ed25519PublicKeyParameters var1 = (Ed25519PublicKeyParameters)var0;
         return new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519), var1.getEncoded());
      } else {
         throw new IOException("key parameters not recognized");
      }
   }

   private static void extractBytes(byte[] var0, int var1, int var2, BigInteger var3) {
      byte[] var4 = var3.toByteArray();
      if (var4.length < var1) {
         byte[] var5 = new byte[var1];
         System.arraycopy(var4, 0, var5, var5.length - var4.length, var4.length);
         var4 = var5;
      }

      for(int var6 = 0; var6 != var1; ++var6) {
         var0[var2 + var6] = var4[var4.length - 1 - var6];
      }

   }

   static {
      cryptoProOids.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A);
      cryptoProOids.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B);
      cryptoProOids.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C);
      cryptoProOids.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA);
      cryptoProOids.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB);
   }
}

package org.bouncycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.pkcs.DHParameter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECGOST3410Parameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.internal.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.internal.asn1.oiw.ElGamalParameter;
import org.bouncycastle.internal.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.internal.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.util.Arrays;

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
         throw new IllegalArgumentException("keyInfo argument null");
      } else {
         AlgorithmIdentifier var1 = var0.getPrivateKeyAlgorithm();
         ASN1ObjectIdentifier var2 = var1.getAlgorithm();
         if (!var2.equals(PKCSObjectIdentifiers.rsaEncryption) && !var2.equals(PKCSObjectIdentifiers.id_RSASSA_PSS) && !var2.equals(X509ObjectIdentifiers.id_ea_rsa)) {
            if (var2.equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
               DHParameter var16 = DHParameter.getInstance(var1.getParameters());
               ASN1Integer var20 = (ASN1Integer)var0.parsePrivateKey();
               BigInteger var24 = var16.getL();
               int var30 = var24 == null ? 0 : var24.intValue();
               DHParameters var31 = new DHParameters(var16.getP(), var16.getG(), (BigInteger)null, var30);
               return new DHPrivateKeyParameters(var20.getValue(), var31);
            } else if (var2.equals(OIWObjectIdentifiers.elGamalAlgorithm)) {
               ElGamalParameter var15 = ElGamalParameter.getInstance(var1.getParameters());
               ASN1Integer var19 = (ASN1Integer)var0.parsePrivateKey();
               return new ElGamalPrivateKeyParameters(var19.getValue(), new ElGamalParameters(var15.getP(), var15.getG()));
            } else if (var2.equals(X9ObjectIdentifiers.id_dsa)) {
               ASN1Integer var14 = (ASN1Integer)var0.parsePrivateKey();
               ASN1Encodable var18 = var1.getParameters();
               DSAParameters var23 = null;
               if (var18 != null) {
                  DSAParameter var29 = DSAParameter.getInstance(var18.toASN1Primitive());
                  var23 = new DSAParameters(var29.getP(), var29.getQ(), var29.getG());
               }

               return new DSAPrivateKeyParameters(var14.getValue(), var23);
            } else if (var2.equals(X9ObjectIdentifiers.id_ecPublicKey)) {
               ECPrivateKey var13 = ECPrivateKey.getInstance(var0.parsePrivateKey());
               X962Parameters var17 = X962Parameters.getInstance(var1.getParameters().toASN1Primitive());
               Object var22;
               if (var17.isNamedCurve()) {
                  ASN1ObjectIdentifier var26 = ASN1ObjectIdentifier.getInstance(var17.getParameters());
                  var22 = ECNamedDomainParameters.lookup(var26);
               } else {
                  X9ECParameters var27 = X9ECParameters.getInstance(var17.getParameters());
                  var22 = new ECDomainParameters(var27);
               }

               BigInteger var28 = var13.getKey();
               return new ECPrivateKeyParameters(var28, (ECDomainParameters)var22);
            } else if (var2.equals(EdECObjectIdentifiers.id_X25519)) {
               return 32 == var0.getPrivateKeyLength() ? new X25519PrivateKeyParameters(var0.getPrivateKey().getOctets()) : new X25519PrivateKeyParameters(getRawKey(var0));
            } else if (var2.equals(EdECObjectIdentifiers.id_X448)) {
               return 56 == var0.getPrivateKeyLength() ? new X448PrivateKeyParameters(var0.getPrivateKey().getOctets()) : new X448PrivateKeyParameters(getRawKey(var0));
            } else if (var2.equals(EdECObjectIdentifiers.id_Ed25519)) {
               return new Ed25519PrivateKeyParameters(getRawKey(var0));
            } else if (var2.equals(EdECObjectIdentifiers.id_Ed448)) {
               return new Ed448PrivateKeyParameters(getRawKey(var0));
            } else if (!var2.equals(CryptoProObjectIdentifiers.gostR3410_2001) && !var2.equals(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512) && !var2.equals(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256)) {
               throw new RuntimeException("algorithm identifier in private key not recognised");
            } else {
               ASN1Encodable var12 = var1.getParameters();
               GOST3410PublicKeyAlgParameters var4 = GOST3410PublicKeyAlgParameters.getInstance(var12);
               Object var5 = null;
               Object var6 = null;
               ASN1Primitive var7 = var12.toASN1Primitive();
               ECGOST3410Parameters var21;
               BigInteger var25;
               if (var7 instanceof ASN1Sequence && (ASN1Sequence.getInstance(var7).size() == 2 || ASN1Sequence.getInstance(var7).size() == 3)) {
                  X9ECParameters var32 = ECGOST3410NamedCurves.getByOIDX9(var4.getPublicKeyParamSet());
                  var21 = new ECGOST3410Parameters(new ECNamedDomainParameters(var4.getPublicKeyParamSet(), var32), var4.getPublicKeyParamSet(), var4.getDigestParamSet(), var4.getEncryptionParamSet());
                  int var35 = var0.getPrivateKeyLength();
                  if (var35 != 32 && var35 != 64) {
                     ASN1Encodable var38 = var0.parsePrivateKey();
                     if (var38 instanceof ASN1Integer) {
                        var25 = ASN1Integer.getInstance(var38).getPositiveValue();
                     } else {
                        byte[] var11 = Arrays.reverse(ASN1OctetString.getInstance(var38).getOctets());
                        var25 = new BigInteger(1, var11);
                     }
                  } else {
                     var25 = new BigInteger(1, Arrays.reverse(var0.getPrivateKey().getOctets()));
                  }
               } else {
                  X962Parameters var8 = X962Parameters.getInstance(var1.getParameters());
                  if (var8.isNamedCurve()) {
                     ASN1ObjectIdentifier var9 = ASN1ObjectIdentifier.getInstance(var8.getParameters());
                     X9ECParameters var10 = ECNamedCurveTable.getByOID(var9);
                     var21 = new ECGOST3410Parameters(new ECNamedDomainParameters(var9, var10), var4.getPublicKeyParamSet(), var4.getDigestParamSet(), var4.getEncryptionParamSet());
                  } else if (var8.isImplicitlyCA()) {
                     var21 = null;
                  } else {
                     X9ECParameters var33 = X9ECParameters.getInstance(var8.getParameters());
                     var21 = new ECGOST3410Parameters(new ECNamedDomainParameters(var2, var33), var4.getPublicKeyParamSet(), var4.getDigestParamSet(), var4.getEncryptionParamSet());
                  }

                  ASN1Encodable var34 = var0.parsePrivateKey();
                  if (var34 instanceof ASN1Integer) {
                     ASN1Integer var36 = ASN1Integer.getInstance(var34);
                     var25 = var36.getValue();
                  } else {
                     ECPrivateKey var37 = ECPrivateKey.getInstance(var34);
                     var25 = var37.getKey();
                  }
               }

               return new ECPrivateKeyParameters(var25, new ECGOST3410Parameters(var21, var4.getPublicKeyParamSet(), var4.getDigestParamSet(), var4.getEncryptionParamSet()));
            }
         } else {
            RSAPrivateKey var3 = RSAPrivateKey.getInstance(var0.parsePrivateKey());
            return new RSAPrivateCrtKeyParameters(var3.getModulus(), var3.getPublicExponent(), var3.getPrivateExponent(), var3.getPrime1(), var3.getPrime2(), var3.getExponent1(), var3.getExponent2(), var3.getCoefficient());
         }
      }
   }

   private static byte[] getRawKey(PrivateKeyInfo var0) throws IOException {
      return ASN1OctetString.getInstance(var0.parsePrivateKey()).getOctets();
   }
}

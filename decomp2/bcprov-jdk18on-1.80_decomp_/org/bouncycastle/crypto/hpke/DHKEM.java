package org.bouncycastle.crypto.hpke;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.agreement.ECDHCBasicAgreement;
import org.bouncycastle.crypto.agreement.XDHBasicAgreement;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.generators.X448KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X448KeyGenerationParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PublicKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.math.ec.custom.sec.SecP256R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP384R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP521R1Curve;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

class DHKEM extends KEM {
   private AsymmetricCipherKeyPairGenerator kpGen;
   private BasicAgreement agreement;
   private final short kemId;
   private HKDF hkdf;
   private byte bitmask;
   private int Nsk;
   private int Nsecret;
   private int Nenc;
   ECDomainParameters domainParams;

   protected DHKEM(short var1) {
      this.kemId = var1;
      switch (var1) {
         case 16:
            this.hkdf = new HKDF((short)1);
            SecP256R1Curve var4 = new SecP256R1Curve();
            this.domainParams = new ECDomainParameters(var4, ((ECCurve)var4).createPoint(new BigInteger(1, Hex.decode("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296")), new BigInteger(1, Hex.decode("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5"))), ((ECCurve)var4).getOrder(), ((ECCurve)var4).getCofactor(), Hex.decode("c49d360886e704936a6678e1139d26b7819f7e90"));
            this.agreement = new ECDHCBasicAgreement();
            this.bitmask = -1;
            this.Nsk = 32;
            this.Nsecret = 32;
            this.Nenc = 65;
            this.kpGen = new ECKeyPairGenerator();
            this.kpGen.init(new ECKeyGenerationParameters(this.domainParams, new SecureRandom()));
            break;
         case 17:
            this.hkdf = new HKDF((short)2);
            SecP384R1Curve var3 = new SecP384R1Curve();
            this.domainParams = new ECDomainParameters(var3, ((ECCurve)var3).createPoint(new BigInteger(1, Hex.decode("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7")), new BigInteger(1, Hex.decode("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f"))), ((ECCurve)var3).getOrder(), ((ECCurve)var3).getCofactor(), Hex.decode("a335926aa319a27a1d00896a6773a4827acdac73"));
            this.agreement = new ECDHCBasicAgreement();
            this.bitmask = -1;
            this.Nsk = 48;
            this.Nsecret = 48;
            this.Nenc = 97;
            this.kpGen = new ECKeyPairGenerator();
            this.kpGen.init(new ECKeyGenerationParameters(this.domainParams, new SecureRandom()));
            break;
         case 18:
            this.hkdf = new HKDF((short)3);
            SecP521R1Curve var2 = new SecP521R1Curve();
            this.domainParams = new ECDomainParameters(var2, ((ECCurve)var2).createPoint(new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16), new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16)), ((ECCurve)var2).getOrder(), ((ECCurve)var2).getCofactor(), Hex.decode("d09e8800291cb85396cc6717393284aaa0da64ba"));
            this.agreement = new ECDHCBasicAgreement();
            this.bitmask = 1;
            this.Nsk = 66;
            this.Nsecret = 64;
            this.Nenc = 133;
            this.kpGen = new ECKeyPairGenerator();
            this.kpGen.init(new ECKeyGenerationParameters(this.domainParams, new SecureRandom()));
            break;
         case 32:
            this.hkdf = new HKDF((short)1);
            this.agreement = new XDHBasicAgreement();
            this.Nsecret = 32;
            this.Nsk = 32;
            this.Nenc = 32;
            this.kpGen = new X25519KeyPairGenerator();
            this.kpGen.init(new X25519KeyGenerationParameters(new SecureRandom()));
            break;
         case 33:
            this.hkdf = new HKDF((short)3);
            this.agreement = new XDHBasicAgreement();
            this.Nsecret = 64;
            this.Nsk = 56;
            this.Nenc = 56;
            this.kpGen = new X448KeyPairGenerator();
            this.kpGen.init(new X448KeyGenerationParameters(new SecureRandom()));
            break;
         default:
            throw new IllegalArgumentException("invalid kem id");
      }

   }

   public byte[] SerializePublicKey(AsymmetricKeyParameter var1) {
      switch (this.kemId) {
         case 16:
         case 17:
         case 18:
            return ((ECPublicKeyParameters)var1).getQ().getEncoded(false);
         case 32:
            return ((X25519PublicKeyParameters)var1).getEncoded();
         case 33:
            return ((X448PublicKeyParameters)var1).getEncoded();
         default:
            throw new IllegalStateException("invalid kem id");
      }
   }

   public byte[] SerializePrivateKey(AsymmetricKeyParameter var1) {
      switch (this.kemId) {
         case 16:
         case 17:
         case 18:
            return this.formatBigIntegerBytes(((ECPrivateKeyParameters)var1).getD().toByteArray(), this.Nsk);
         case 32:
            return ((X25519PrivateKeyParameters)var1).getEncoded();
         case 33:
            return ((X448PrivateKeyParameters)var1).getEncoded();
         default:
            throw new IllegalStateException("invalid kem id");
      }
   }

   public AsymmetricKeyParameter DeserializePublicKey(byte[] var1) {
      switch (this.kemId) {
         case 16:
         case 17:
         case 18:
            ECPoint var2 = this.domainParams.getCurve().decodePoint(var1);
            return new ECPublicKeyParameters(var2, this.domainParams);
         case 32:
            return new X25519PublicKeyParameters(var1);
         case 33:
            return new X448PublicKeyParameters(var1);
         default:
            throw new IllegalStateException("invalid kem id");
      }
   }

   public AsymmetricCipherKeyPair DeserializePrivateKey(byte[] var1, byte[] var2) {
      Object var3 = null;
      if (var2 != null) {
         var3 = this.DeserializePublicKey(var2);
      }

      switch (this.kemId) {
         case 16:
         case 17:
         case 18:
            BigInteger var4 = new BigInteger(1, var1);
            ECPrivateKeyParameters var5 = new ECPrivateKeyParameters(var4, this.domainParams);
            if (var3 == null) {
               ECPoint var8 = (new FixedPointCombMultiplier()).multiply(this.domainParams.getG(), var5.getD());
               var3 = new ECPublicKeyParameters(var8, this.domainParams);
            }

            return new AsymmetricCipherKeyPair((AsymmetricKeyParameter)var3, var5);
         case 32:
            X25519PrivateKeyParameters var7 = new X25519PrivateKeyParameters(var1);
            if (var3 == null) {
               var3 = var7.generatePublicKey();
            }

            return new AsymmetricCipherKeyPair((AsymmetricKeyParameter)var3, var7);
         case 33:
            X448PrivateKeyParameters var6 = new X448PrivateKeyParameters(var1);
            if (var3 == null) {
               var3 = var6.generatePublicKey();
            }

            return new AsymmetricCipherKeyPair((AsymmetricKeyParameter)var3, var6);
         default:
            throw new IllegalStateException("invalid kem id");
      }
   }

   int getEncryptionSize() {
      return this.Nenc;
   }

   private boolean ValidateSk(BigInteger var1) {
      BigInteger var2 = this.domainParams.getN();
      int var3 = var2.bitLength();
      int var4 = var3 >>> 2;
      if (var1.compareTo(BigInteger.valueOf(1L)) >= 0 && var1.compareTo(var2) < 0) {
         return WNafUtil.getNafWeight(var1) >= var4;
      } else {
         return false;
      }
   }

   public AsymmetricCipherKeyPair GeneratePrivateKey() {
      return this.kpGen.generateKeyPair();
   }

   public AsymmetricCipherKeyPair DeriveKeyPair(byte[] var1) {
      byte[] var2 = Arrays.concatenate(Strings.toByteArray("KEM"), Pack.shortToBigEndian(this.kemId));
      switch (this.kemId) {
         case 16:
         case 17:
         case 18:
            byte[] var12 = this.hkdf.LabeledExtract((byte[])null, var2, "dkp_prk", var1);
            int var4 = 0;

            for(byte[] var5 = new byte[1]; var4 <= 255; ++var4) {
               var5[0] = (byte)var4;
               byte[] var13 = this.hkdf.LabeledExpand(var12, var2, "candidate", var5, this.Nsk);
               var13[0] &= this.bitmask;
               BigInteger var14 = new BigInteger(1, var13);
               if (this.ValidateSk(var14)) {
                  ECPoint var15 = (new FixedPointCombMultiplier()).multiply(this.domainParams.getG(), var14);
                  ECPrivateKeyParameters var16 = new ECPrivateKeyParameters(var14, this.domainParams);
                  ECPublicKeyParameters var10 = new ECPublicKeyParameters(var15, this.domainParams);
                  return new AsymmetricCipherKeyPair(var10, var16);
               }
            }

            throw new IllegalStateException("DeriveKeyPairError");
         case 32:
            byte[] var11 = this.hkdf.LabeledExtract((byte[])null, var2, "dkp_prk", var1);
            byte[] var8 = this.hkdf.LabeledExpand(var11, var2, "sk", (byte[])null, this.Nsk);
            X25519PrivateKeyParameters var9 = new X25519PrivateKeyParameters(var8);
            return new AsymmetricCipherKeyPair(var9.generatePublicKey(), var9);
         case 33:
            byte[] var3 = this.hkdf.LabeledExtract((byte[])null, var2, "dkp_prk", var1);
            byte[] var6 = this.hkdf.LabeledExpand(var3, var2, "sk", (byte[])null, this.Nsk);
            X448PrivateKeyParameters var7 = new X448PrivateKeyParameters(var6);
            return new AsymmetricCipherKeyPair(var7.generatePublicKey(), var7);
         default:
            throw new IllegalStateException("invalid kem id");
      }
   }

   protected byte[][] Encap(AsymmetricKeyParameter var1) {
      return this.Encap(var1, this.kpGen.generateKeyPair());
   }

   protected byte[][] Encap(AsymmetricKeyParameter var1, AsymmetricCipherKeyPair var2) {
      byte[][] var3 = new byte[2][];
      this.agreement.init(var2.getPrivate());
      byte[] var4 = this.agreement.calculateAgreement(var1).toByteArray();
      byte[] var5 = this.formatBigIntegerBytes(var4, this.agreement.getFieldSize());
      byte[] var6 = this.SerializePublicKey(var2.getPublic());
      byte[] var7 = this.SerializePublicKey(var1);
      byte[] var8 = Arrays.concatenate(var6, var7);
      byte[] var9 = this.ExtractAndExpand(var5, var8);
      var3[0] = var9;
      var3[1] = var6;
      return var3;
   }

   protected byte[] Decap(byte[] var1, AsymmetricCipherKeyPair var2) {
      AsymmetricKeyParameter var3 = this.DeserializePublicKey(var1);
      this.agreement.init(var2.getPrivate());
      byte[] var4 = this.agreement.calculateAgreement(var3).toByteArray();
      byte[] var5 = this.formatBigIntegerBytes(var4, this.agreement.getFieldSize());
      byte[] var6 = this.SerializePublicKey(var2.getPublic());
      byte[] var7 = Arrays.concatenate(var1, var6);
      byte[] var8 = this.ExtractAndExpand(var5, var7);
      return var8;
   }

   protected byte[][] AuthEncap(AsymmetricKeyParameter var1, AsymmetricCipherKeyPair var2) {
      byte[][] var3 = new byte[2][];
      AsymmetricCipherKeyPair var4 = this.kpGen.generateKeyPair();
      this.agreement.init(var4.getPrivate());
      byte[] var5 = this.agreement.calculateAgreement(var1).toByteArray();
      byte[] var6 = this.formatBigIntegerBytes(var5, this.agreement.getFieldSize());
      this.agreement.init(var2.getPrivate());
      var5 = this.agreement.calculateAgreement(var1).toByteArray();
      byte[] var7 = this.formatBigIntegerBytes(var5, this.agreement.getFieldSize());
      byte[] var8 = Arrays.concatenate(var6, var7);
      byte[] var9 = this.SerializePublicKey(var4.getPublic());
      byte[] var10 = this.SerializePublicKey(var1);
      byte[] var11 = this.SerializePublicKey(var2.getPublic());
      byte[] var12 = Arrays.concatenate(var9, var10, var11);
      byte[] var13 = this.ExtractAndExpand(var8, var12);
      var3[0] = var13;
      var3[1] = var9;
      return var3;
   }

   protected byte[] AuthDecap(byte[] var1, AsymmetricCipherKeyPair var2, AsymmetricKeyParameter var3) {
      AsymmetricKeyParameter var4 = this.DeserializePublicKey(var1);
      this.agreement.init(var2.getPrivate());
      byte[] var5 = this.agreement.calculateAgreement(var4).toByteArray();
      byte[] var6 = this.formatBigIntegerBytes(var5, this.agreement.getFieldSize());
      this.agreement.init(var2.getPrivate());
      var5 = this.agreement.calculateAgreement(var3).toByteArray();
      byte[] var7 = this.formatBigIntegerBytes(var5, this.agreement.getFieldSize());
      byte[] var8 = Arrays.concatenate(var6, var7);
      byte[] var9 = this.SerializePublicKey(var2.getPublic());
      byte[] var10 = this.SerializePublicKey(var3);
      byte[] var11 = Arrays.concatenate(var1, var9, var10);
      byte[] var12 = this.ExtractAndExpand(var8, var11);
      return var12;
   }

   private byte[] ExtractAndExpand(byte[] var1, byte[] var2) {
      byte[] var3 = Arrays.concatenate(Strings.toByteArray("KEM"), Pack.shortToBigEndian(this.kemId));
      byte[] var4 = this.hkdf.LabeledExtract((byte[])null, var3, "eae_prk", var1);
      byte[] var5 = this.hkdf.LabeledExpand(var4, var3, "shared_secret", var2, this.Nsecret);
      return var5;
   }

   private byte[] formatBigIntegerBytes(byte[] var1, int var2) {
      byte[] var3 = new byte[var2];
      if (var1.length <= var2) {
         System.arraycopy(var1, 0, var3, var2 - var1.length, var1.length);
      } else {
         System.arraycopy(var1, var1.length - var2, var3, 0, var2);
      }

      return var3;
   }
}

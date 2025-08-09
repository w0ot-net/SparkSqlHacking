package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.KeyEncoder;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.agreement.ECDHCBasicAgreement;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi;
import org.bouncycastle.jcajce.spec.IESKEMParameterSpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;

public class IESKEMCipher extends BaseCipherSpi {
   private static final X9IntegerConverter converter = new X9IntegerConverter();
   private final JcaJceHelper helper = new BCJcaJceHelper();
   private final ECDHCBasicAgreement agreement;
   private final KDF2BytesGenerator kdf;
   private final Mac hMac;
   private final int macKeyLength;
   private final int macLength;
   private int ivLength;
   private IESEngine engine;
   private int state = -1;
   private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
   private AlgorithmParameters engineParam = null;
   private IESKEMParameterSpec engineSpec = null;
   private AsymmetricKeyParameter key;
   private SecureRandom random;
   private boolean dhaesMode = false;
   private AsymmetricKeyParameter otherKeyParameter = null;

   public IESKEMCipher(ECDHCBasicAgreement var1, KDF2BytesGenerator var2, Mac var3, int var4, int var5) {
      this.agreement = var1;
      this.kdf = var2;
      this.hMac = var3;
      this.macKeyLength = var4;
      this.macLength = var5;
   }

   public int engineGetBlockSize() {
      return 0;
   }

   public int engineGetKeySize(Key var1) {
      if (var1 instanceof ECKey) {
         return ((ECKey)var1).getParameters().getCurve().getFieldSize();
      } else {
         throw new IllegalArgumentException("not an EC key");
      }
   }

   public byte[] engineGetIV() {
      return null;
   }

   public AlgorithmParameters engineGetParameters() {
      if (this.engineParam == null && this.engineSpec != null) {
         try {
            this.engineParam = this.helper.createAlgorithmParameters("IES");
            this.engineParam.init(this.engineSpec);
         } catch (Exception var2) {
            throw new RuntimeException(var2.toString());
         }
      }

      return this.engineParam;
   }

   public void engineSetMode(String var1) throws NoSuchAlgorithmException {
      throw new NoSuchAlgorithmException("can't support mode " + var1);
   }

   public int engineGetOutputSize(int var1) {
      if (this.key == null) {
         throw new IllegalStateException("cipher not initialised");
      } else {
         int var2 = this.engine.getMac().getMacSize();
         int var3;
         if (this.otherKeyParameter == null) {
            ECCurve var5 = ((ECKeyParameters)this.key).getParameters().getCurve();
            int var6 = (var5.getFieldSize() + 7) / 8;
            var3 = 2 * var6;
         } else {
            var3 = 0;
         }

         int var7 = this.buffer.size() + var1;
         int var4;
         if (this.engine.getCipher() == null) {
            var4 = var7;
         } else if (this.state != 1 && this.state != 3) {
            if (this.state != 2 && this.state != 4) {
               throw new IllegalStateException("cipher not initialised");
            }

            var4 = this.engine.getCipher().getOutputSize(var7 - var2 - var3);
         } else {
            var4 = this.engine.getCipher().getOutputSize(var7);
         }

         if (this.state != 1 && this.state != 3) {
            if (this.state != 2 && this.state != 4) {
               throw new IllegalStateException("cipher not initialised");
            } else {
               return var4;
            }
         } else {
            return var2 + var3 + var4;
         }
      }
   }

   public void engineSetPadding(String var1) throws NoSuchPaddingException {
      throw new NoSuchPaddingException("padding not available with IESCipher");
   }

   public void engineInit(int var1, Key var2, AlgorithmParameters var3, SecureRandom var4) throws InvalidKeyException, InvalidAlgorithmParameterException {
      AlgorithmParameterSpec var5 = null;
      if (var3 != null) {
         try {
            var5 = var3.getParameterSpec(IESParameterSpec.class);
         } catch (Exception var7) {
            throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + var7.toString());
         }
      }

      this.engineParam = var3;
      this.engineInit(var1, var2, var5, var4);
   }

   public void engineInit(int var1, Key var2, AlgorithmParameterSpec var3, SecureRandom var4) throws InvalidAlgorithmParameterException, InvalidKeyException {
      this.otherKeyParameter = null;
      this.engineSpec = (IESKEMParameterSpec)var3;
      if (var1 != 1 && var1 != 3) {
         if (var1 != 2 && var1 != 4) {
            throw new InvalidKeyException("must be passed EC key");
         }

         if (!(var2 instanceof PrivateKey)) {
            throw new InvalidKeyException("must be passed recipient's private EC key for decryption");
         }

         this.key = ECUtils.generatePrivateKeyParameter((PrivateKey)var2);
      } else {
         if (!(var2 instanceof PublicKey)) {
            throw new InvalidKeyException("must be passed recipient's public EC key for encryption");
         }

         this.key = ECUtils.generatePublicKeyParameter((PublicKey)var2);
      }

      this.random = var4;
      this.state = var1;
      this.buffer.reset();
   }

   public void engineInit(int var1, Key var2, SecureRandom var3) throws InvalidKeyException {
      try {
         this.engineInit(var1, var2, (AlgorithmParameterSpec)null, var3);
      } catch (InvalidAlgorithmParameterException var5) {
         throw new IllegalArgumentException("cannot handle supplied parameter spec: " + var5.getMessage());
      }
   }

   public byte[] engineUpdate(byte[] var1, int var2, int var3) {
      this.buffer.write(var1, var2, var3);
      return null;
   }

   public int engineUpdate(byte[] var1, int var2, int var3, byte[] var4, int var5) {
      this.buffer.write(var1, var2, var3);
      return 0;
   }

   public byte[] engineDoFinal(byte[] var1, int var2, int var3) throws IllegalBlockSizeException, BadPaddingException {
      if (var3 != 0) {
         this.buffer.write(var1, var2, var3);
      }

      byte[] var4 = this.buffer.toByteArray();
      this.buffer.reset();
      ECDomainParameters var5 = ((ECKeyParameters)this.key).getParameters();
      if (this.state != 1 && this.state != 3) {
         if (this.state != 2 && this.state != 4) {
            throw new IllegalStateException("cipher not initialised");
         } else {
            ECPrivateKeyParameters var16 = (ECPrivateKeyParameters)this.key;
            ECCurve var17 = var16.getParameters().getCurve();
            int var18 = (var17.getFieldSize() + 7) / 8;
            if (var1[var2] == 4) {
               var18 = 1 + 2 * var18;
            } else {
               ++var18;
            }

            int var20 = var3 - (var18 + this.macLength);
            ECPoint var21 = var17.decodePoint(Arrays.copyOfRange(var1, var2, var2 + var18));
            this.agreement.init(this.key);
            byte[] var22 = converter.integerToBytes(this.agreement.calculateAgreement(new ECPublicKeyParameters(var21, var16.getParameters())), converter.getByteLength(var5.getCurve()));
            byte[] var23 = new byte[var20 + this.macKeyLength];
            this.kdf.init(new KDFParameters(var22, this.engineSpec.getRecipientInfo()));
            this.kdf.generateBytes(var23, 0, var23.length);
            byte[] var25 = new byte[var20];

            for(int var26 = 0; var26 != var25.length; ++var26) {
               var25[var26] = (byte)(var1[var2 + var18 + var26] ^ var23[var26]);
            }

            KeyParameter var27 = new KeyParameter(var23, var20, var23.length - var20);
            this.hMac.init(var27);
            this.hMac.update(var1, var2 + var18, var25.length);
            byte[] var15 = new byte[this.hMac.getMacSize()];
            this.hMac.doFinal(var15, 0);
            Arrays.clear(var27.getKey());
            Arrays.clear(var23);
            if (!Arrays.constantTimeAreEqual(this.macLength, var15, 0, var1, var2 + (var3 - this.macLength))) {
               throw new BadPaddingException("mac field");
            } else {
               return var25;
            }
         }
      } else {
         ECKeyPairGenerator var6 = new ECKeyPairGenerator();
         var6.init(new ECKeyGenerationParameters(var5, this.random));
         final boolean var7 = this.engineSpec.hasUsePointCompression();
         EphemeralKeyPairGenerator var8 = new EphemeralKeyPairGenerator(var6, new KeyEncoder() {
            public byte[] getEncoded(AsymmetricKeyParameter var1) {
               return ((ECPublicKeyParameters)var1).getQ().getEncoded(var7);
            }
         });
         EphemeralKeyPair var9 = var8.generate();
         this.agreement.init(var9.getKeyPair().getPrivate());
         byte[] var10 = converter.integerToBytes(this.agreement.calculateAgreement(this.key), converter.getByteLength(var5.getCurve()));
         byte[] var11 = new byte[var3 + this.macKeyLength];
         this.kdf.init(new KDFParameters(var10, this.engineSpec.getRecipientInfo()));
         this.kdf.generateBytes(var11, 0, var11.length);
         byte[] var12 = new byte[var3 + this.macLength];

         for(int var13 = 0; var13 != var3; ++var13) {
            var12[var13] = (byte)(var1[var2 + var13] ^ var11[var13]);
         }

         KeyParameter var24 = new KeyParameter(var11, var3, var11.length - var3);
         this.hMac.init(var24);
         this.hMac.update(var12, 0, var3);
         byte[] var14 = new byte[this.hMac.getMacSize()];
         this.hMac.doFinal(var14, 0);
         Arrays.clear(var24.getKey());
         Arrays.clear(var11);
         System.arraycopy(var14, 0, var12, var3, this.macLength);
         return Arrays.concatenate(var9.getEncodedPublicKey(), var12);
      }
   }

   public int engineDoFinal(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
      byte[] var6 = this.engineDoFinal(var1, var2, var3);
      System.arraycopy(var6, 0, var4, var5, var6.length);
      return var6.length;
   }

   public static class KEM extends IESKEMCipher {
      public KEM(Digest var1, Digest var2, int var3, int var4) {
         super(new ECDHCBasicAgreement(), new KDF2BytesGenerator(var1), new HMac(var2), var3, var4);
      }
   }

   public static class KEMwithSHA256 extends KEM {
      public KEMwithSHA256() {
         super(DigestFactory.createSHA256(), DigestFactory.createSHA256(), 32, 16);
      }
   }
}

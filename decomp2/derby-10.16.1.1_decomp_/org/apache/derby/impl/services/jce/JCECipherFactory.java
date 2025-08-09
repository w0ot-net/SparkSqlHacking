package org.apache.derby.impl.services.jce;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Enumeration;
import java.util.Properties;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.crypto.CipherFactory;
import org.apache.derby.iapi.services.crypto.CipherProvider;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

final class JCECipherFactory implements CipherFactory {
   private static final String MESSAGE_DIGEST = "MD5";
   private static final String DEFAULT_ALGORITHM = "DES/CBC/NoPadding";
   private static final String DES = "DES";
   private static final String DESede = "DESede";
   private static final String TripleDES = "TripleDES";
   private static final String AES = "AES";
   private static final int BLOCK_LENGTH = 8;
   private static final int AES_IV_LENGTH = 16;
   private int keyLengthBits;
   private int encodedKeyLength;
   private String cryptoAlgorithm;
   private String cryptoAlgorithmShort;
   private String cryptoProvider;
   private String cryptoProviderShort;
   private MessageDigest messageDigest;
   private SecretKey mainSecretKey;
   private byte[] mainIV;
   private Properties persistentProperties;
   private static final int VERIFYKEY_DATALEN = 4096;

   JCECipherFactory(boolean var1, Properties var2, boolean var3) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      this.init(var1, var2, var3);
   }

   static String providerErrorName(String var0) {
      return var0 == null ? "default" : var0;
   }

   private byte[] generateUniqueBytes() throws StandardException {
      try {
         String var1 = this.cryptoProviderShort;
         KeyGenerator var2;
         if (var1 == null) {
            var2 = KeyGenerator.getInstance(this.cryptoAlgorithmShort);
         } else {
            if (var1.equals("BouncyCastleProvider")) {
               var1 = "BC";
            }

            var2 = KeyGenerator.getInstance(this.cryptoAlgorithmShort, var1);
         }

         var2.init(this.keyLengthBits);
         SecretKey var3 = var2.generateKey();
         return var3.getEncoded();
      } catch (NoSuchAlgorithmException var4) {
         throw StandardException.newException("XBCXC.S", new Object[]{this.cryptoAlgorithm, providerErrorName(this.cryptoProviderShort)});
      } catch (NoSuchProviderException var5) {
         throw StandardException.newException("XBCXG.S", new Object[]{providerErrorName(this.cryptoProviderShort)});
      }
   }

   private EncryptedKeyResult encryptKey(byte[] var1, byte[] var2) throws StandardException {
      int var3 = var1.length;
      if (this.cryptoAlgorithmShort.equals("AES")) {
         var3 = 16;
      }

      byte[] var4 = this.getMuckFromBootPassword(var2, var3);
      SecretKey var5 = this.generateKey(var4);
      byte[] var6 = this.generateIV(var4);
      CipherProvider var7 = this.createNewCipher(1, var5, var6);
      this.encodedKeyLength = var1.length;
      var1 = this.padKey(var1, var7.getEncryptionBlockSize());
      byte[] var8 = new byte[var1.length];
      var7.encrypt(var1, 0, var1.length, var8, 0);
      String var9 = StringUtil.toHexString(var8, 0, var8.length);
      return new EncryptedKeyResult(var9, var1);
   }

   private byte[] padKey(byte[] var1, int var2) {
      byte[] var3 = var1;
      if (var1.length % var2 != 0) {
         int var4 = var1.length + var2 - var1.length % var2;
         var3 = new byte[var4];
         System.arraycopy(var1, 0, var3, 0, var1.length);
      }

      return var3;
   }

   private byte[] decryptKey(String var1, int var2, byte[] var3) throws StandardException {
      byte[] var4 = StringUtil.fromHexString(var1, 0, var2);
      int var5;
      if (this.cryptoAlgorithmShort.equals("AES")) {
         var5 = 16;
      } else {
         var5 = var4.length;
      }

      byte[] var6 = this.getMuckFromBootPassword(var3, var5);
      SecretKey var7 = this.generateKey(var6);
      byte[] var8 = this.generateIV(var6);
      this.createNewCipher(2, var7, var8).decrypt(var4, 0, var4.length, var4, 0);
      return var4;
   }

   private byte[] getMuckFromBootPassword(byte[] var1, int var2) {
      int var3 = var1.length;
      byte[] var4 = new byte[var2];
      int var5 = 0;

      for(int var6 = 0; var6 < var1.length; ++var6) {
         var5 += var1[var6];
      }

      for(int var7 = 0; var7 < var2; ++var7) {
         var4[var7] = (byte)(var1[(var7 + var5) % var3] ^ var1[var7 % var3] << 4);
      }

      return var4;
   }

   private SecretKey generateKey(byte[] var1) throws StandardException {
      int var2 = var1.length;
      if (var2 < 8) {
         throw StandardException.newException("XBCX2.S", new Object[]{8});
      } else {
         try {
            if (this.cryptoAlgorithmShort.equals("DES") && DESKeySpec.isWeak(var1, 0)) {
               byte[] var3 = StringUtil.getAsciiBytes("louDScap");

               for(int var4 = 0; var4 < 7; ++var4) {
                  var1[var4] = (byte)(var3[var4] << 3 ^ var1[var4]);
               }
            }

            return new SecretKeySpec(var1, this.cryptoAlgorithmShort);
         } catch (InvalidKeyException var5) {
            throw StandardException.newException("XBCX0.S", var5, new Object[0]);
         }
      }
   }

   private byte[] generateIV(byte[] var1) {
      byte var2 = 8;
      Object var3 = null;
      byte[] var6;
      if (this.cryptoAlgorithmShort.equals("AES")) {
         var2 = 16;
         var6 = new byte[var2];
         var6[0] = (byte)((var1[var1.length - 1] << 2 | 15) ^ var1[0]);

         for(int var4 = 1; var4 < 8; ++var4) {
            var6[var4] = (byte)((var1[var4 - 1] << var4 % 5 | 15) ^ var1[var4]);
         }

         for(int var7 = 8; var7 < 16; ++var7) {
            var6[var7] = var6[var7 - 8];
         }
      } else {
         var6 = new byte[8];
         var6[0] = (byte)((var1[var1.length - 1] << 2 | 15) ^ var1[0]);

         for(int var8 = 1; var8 < 8; ++var8) {
            var6[var8] = (byte)((var1[var8 - 1] << var8 % 5 | 15) ^ var1[var8]);
         }
      }

      return var6;
   }

   private int digest(byte[] var1) {
      this.messageDigest.reset();
      byte[] var2 = this.messageDigest.digest(var1);
      byte[] var3 = new byte[2];

      for(int var4 = 0; var4 < var2.length; ++var4) {
         var3[var4 % 2] ^= var2[var4];
      }

      int var5 = var3[0] & 255 | var3[1] << 8 & '\uff00';
      return var5;
   }

   public SecureRandom getSecureRandom() {
      return new SecureRandom(this.mainIV);
   }

   public CipherProvider createNewCipher(int var1) throws StandardException {
      return this.createNewCipher(var1, this.mainSecretKey, this.mainIV);
   }

   private CipherProvider createNewCipher(int var1, SecretKey var2, byte[] var3) throws StandardException {
      return new JCECipherProvider(var1, var2, var3, this.cryptoAlgorithm, this.cryptoProviderShort);
   }

   private void init(boolean var1, Properties var2, boolean var3) throws StandardException {
      boolean var4 = false;
      boolean var5 = var1;
      this.persistentProperties = new Properties();
      String var6 = var2.getProperty(var3 ? "newEncryptionKey" : "encryptionKey");
      if (var6 != null) {
         var5 = false;
      }

      this.cryptoProvider = var2.getProperty("encryptionProvider");
      if (this.cryptoProvider != null) {
         var4 = true;
         int var7 = this.cryptoProvider.lastIndexOf(46);
         if (var7 == -1) {
            this.cryptoProviderShort = this.cryptoProvider;
         } else {
            this.cryptoProviderShort = this.cryptoProvider.substring(var7 + 1);
         }
      }

      this.cryptoAlgorithm = var2.getProperty("encryptionAlgorithm");
      if (this.cryptoAlgorithm == null) {
         this.cryptoAlgorithm = "DES/CBC/NoPadding";
      } else {
         var4 = true;
      }

      if (var5) {
         this.persistentProperties.put("encryptionAlgorithm", this.cryptoAlgorithm);
      }

      int var24 = this.cryptoAlgorithm.indexOf(47);
      int var8 = this.cryptoAlgorithm.lastIndexOf(47);
      if (var24 >= 0 && var8 >= 0 && var24 != var8) {
         this.cryptoAlgorithmShort = this.cryptoAlgorithm.substring(0, var24);
         if (var4) {
            try {
               Class var9 = Class.forName("javax.crypto.ExemptionMechanism");
            } catch (Throwable var15) {
               throw StandardException.newException("XBCXJ.S", new Object[0]);
            }
         }

         if (!var1 && var2.getProperty("encryptionKeyLength") != null) {
            String var25 = var2.getProperty("encryptionKeyLength");
            int var10 = var25.lastIndexOf(45);
            this.encodedKeyLength = Integer.parseInt(var25.substring(var10 + 1));
            if (var10 != -1) {
               this.keyLengthBits = Integer.parseInt(var25.substring(0, var10));
            }
         }

         if (var6 == null && var1) {
            if (var2.getProperty("encryptionKeyLength") != null) {
               this.keyLengthBits = Integer.parseInt(var2.getProperty("encryptionKeyLength"));
            } else if (this.cryptoAlgorithmShort.equals("DES")) {
               this.keyLengthBits = 56;
            } else if (!this.cryptoAlgorithmShort.equals("DESede") && !this.cryptoAlgorithmShort.equals("TripleDES")) {
               this.keyLengthBits = 128;
            } else {
               this.keyLengthBits = 168;
            }
         }

         String var26 = this.cryptoAlgorithm.substring(var24 + 1, var8);
         if (!var26.equals("CBC") && !var26.equals("CFB") && !var26.equals("ECB") && !var26.equals("OFB")) {
            throw StandardException.newException("XBCXI.S", new Object[]{var26});
         } else {
            String var27 = this.cryptoAlgorithm.substring(var8 + 1, this.cryptoAlgorithm.length());
            if (!var27.equals("NoPadding")) {
               throw StandardException.newException("XBCXB.S", new Object[]{var27});
            } else {
               Object var11;
               try {
                  if (this.cryptoProvider != null && Security.getProvider(this.cryptoProviderShort) == null) {
                     Class var12 = Class.forName(this.cryptoProvider);
                     if (!Provider.class.isAssignableFrom(var12)) {
                        throw StandardException.newException("XBCXF.S.1", new Object[]{this.cryptoProvider});
                     }

                     Constructor var13 = var12.getConstructor();
                     Provider var14 = (Provider)var13.newInstance();
                     Security.addProvider(var14);
                  }

                  this.messageDigest = MessageDigest.getInstance("MD5");
                  byte[] var28;
                  if (var6 != null) {
                     if (var2.getProperty(var3 ? "newBootPassword" : "bootPassword") != null) {
                        throw StandardException.newException("XBM06.D", new Object[0]);
                     }

                     var28 = StringUtil.fromHexString(var6, 0, var6.length());
                     if (var28 == null) {
                        throw StandardException.newException(var6.length() % 2 == 0 ? "XBCXN.S" : "XBCXM.S", new Object[0]);
                     }
                  } else {
                     var28 = this.handleBootPassword(var1, var2, var3);
                     if (var1 || var3) {
                        this.persistentProperties.put("encryptionKeyLength", this.keyLengthBits + "-" + var28.length);
                     }
                  }

                  this.mainSecretKey = this.generateKey(var28);
                  this.mainIV = this.generateIV(var28);
                  if (var1) {
                     this.persistentProperties.put("dataEncryption", "true");
                     this.persistentProperties.put("data_encrypt_algorithm_version", String.valueOf(1));
                     this.persistentProperties.put("log_encrypt_algorithm_version", String.valueOf(1));
                  }

                  return;
               } catch (ClassNotFoundException var16) {
                  var11 = StandardException.newException("XBCXF.S", var16, new Object[]{this.cryptoProvider});
               } catch (InstantiationException var17) {
                  var11 = var17;
               } catch (IllegalAccessException var18) {
                  var11 = var18;
               } catch (NoSuchMethodException var19) {
                  var11 = var19;
               } catch (InvocationTargetException var20) {
                  var11 = var20;
               } catch (NoSuchAlgorithmException var21) {
                  var11 = var21;
               } catch (LinkageError var22) {
                  var11 = var22;
               } catch (ClassCastException var23) {
                  var11 = var23;
               }

               throw StandardException.newException("XBM0G.D", (Throwable)var11, new Object[0]);
            }
         }
      } else {
         throw StandardException.newException("XBCXH.S", new Object[]{this.cryptoAlgorithm});
      }
   }

   private byte[] handleBootPassword(boolean var1, Properties var2, boolean var3) throws StandardException {
      String var4 = var2.getProperty(var3 ? "newBootPassword" : "bootPassword");
      if (var4 == null) {
         throw StandardException.newException("XBM06.D", new Object[0]);
      } else {
         byte[] var5 = StringUtil.getAsciiBytes(var4);
         if (var5.length < 8) {
            String var7 = var1 ? "XBM07.D" : "XBM06.D";
            throw StandardException.newException(var7, new Object[0]);
         } else {
            byte[] var6;
            if (!var1 && !var3) {
               var6 = this.getDatabaseSecretKey(var2, var5, "XBM06.D");
            } else {
               var6 = this.generateUniqueBytes();
               this.persistentProperties.put("encryptedBootPassword", this.saveSecretKey(var6, var5));
            }

            return var6;
         }
      }
   }

   public void saveProperties(Properties var1) {
      Enumeration var2 = this.persistentProperties.keys();

      while(var2.hasMoreElements()) {
         String var3 = (String)var2.nextElement();
         var1.put(var3, this.persistentProperties.get(var3));
      }

      this.persistentProperties = null;
   }

   private byte[] getDatabaseSecretKey(Properties var1, byte[] var2, String var3) throws StandardException {
      String var4 = var1.getProperty("encryptedBootPassword");
      if (var4 == null) {
         throw StandardException.newException(var3, new Object[0]);
      } else {
         int var5 = var4.indexOf(45);
         if (var5 == -1) {
            throw StandardException.newException(var3, new Object[0]);
         } else {
            int var6 = Integer.parseInt(var4.substring(var5 + 1));
            byte[] var7 = this.decryptKey(var4, var5, var2);
            int var8 = this.digest(var7);
            if (var8 != var6) {
               throw StandardException.newException(var3, new Object[0]);
            } else if (this.encodedKeyLength != 0) {
               byte[] var9 = new byte[this.encodedKeyLength];
               System.arraycopy(var7, 0, var9, 0, this.encodedKeyLength);
               return var9;
            } else {
               return var7;
            }
         }
      }
   }

   private String saveSecretKey(byte[] var1, byte[] var2) throws StandardException {
      EncryptedKeyResult var3 = this.encryptKey(var1, var2);
      String var4 = var3.hexOutput;
      int var5 = this.digest(var3.paddedInputKey);
      return var4.concat("-" + var5);
   }

   public String changeBootPassword(String var1, Properties var2, CipherProvider var3) throws StandardException {
      int var4 = var1.indexOf(44);
      if (var4 == -1) {
         throw StandardException.newException("XBCX7.S", new Object[0]);
      } else {
         String var5 = var1.substring(0, var4).trim();
         byte[] var6 = StringUtil.getAsciiBytes(var5);
         if (var6 != null && var6.length >= 8) {
            String var7 = var1.substring(var4 + 1).trim();
            byte[] var8 = StringUtil.getAsciiBytes(var7);
            if (var8 != null && var8.length >= 8) {
               byte[] var9 = this.getDatabaseSecretKey(var2, var6, "XBCXA.S");
               byte[] var10 = this.generateIV(var9);
               if (!((JCECipherProvider)var3).verifyIV(var10)) {
                  throw StandardException.newException("XBCXA.S", new Object[0]);
               } else {
                  CipherProvider var11 = this.createNewCipher(2, this.generateKey(var9), var10);
                  this.vetCipherProviders(var11, var3, "XBCXA.S");
                  this.saveSecretKey(var9, var8);
                  var2.put("encryptionKeyLength", this.keyLengthBits + "-" + this.encodedKeyLength);
                  return this.saveSecretKey(var9, var8);
               }
            } else {
               throw StandardException.newException("XBCX2.S", new Object[]{8});
            }
         } else {
            throw StandardException.newException("XBCXA.S", new Object[0]);
         }
      }
   }

   private void vetCipherProviders(CipherProvider var1, CipherProvider var2, String var3) throws StandardException {
      short var4 = 1024;
      short var5 = 256;
      byte[] var6 = new byte[var4];
      byte[] var7 = new byte[var4];
      byte[] var8 = new byte[var4];

      for(int var9 = 0; var9 < var4; ++var9) {
         var6[var9] = (byte)(var9 % var5);
      }

      int var12 = var2.encrypt(var6, 0, var4, var7, 0);
      int var10 = var1.decrypt(var7, 0, var12, var8, 0);
      if (var12 == var4 && var10 == var4) {
         for(int var11 = 0; var11 < var4; ++var11) {
            if (var6[var11] != var8[var11]) {
               throw StandardException.newException(var3, new Object[0]);
            }
         }

      } else {
         throw StandardException.newException(var3, new Object[0]);
      }
   }

   public void verifyKey(boolean var1, StorageFactory var2, Properties var3) throws StandardException {
      if (var3.getProperty("encryptionKey") != null) {
         InputStream var4 = null;
         StorageRandomAccessFile var5 = null;
         byte[] var6 = new byte[4096];

         try {
            if (var1) {
               this.getSecureRandom().nextBytes(var6);
               byte[] var7 = this.getMD5Checksum(var6);
               CipherProvider var8 = this.createNewCipher(1, this.mainSecretKey, this.mainIV);
               var8.encrypt(var6, 0, var6.length, var6, 0);
               var5 = this.privAccessFile(var2, "verifyKey.dat", "rw");
               var5.writeInt(var7.length);
               var5.write(var7);
               var5.write(var6);
               var5.sync();
            } else {
               var4 = this.privAccessGetInputStream(var2, "verifyKey.dat");
               DataInputStream var21 = new DataInputStream(var4);
               int var22 = var21.readInt();
               byte[] var9 = new byte[var22];
               var21.readFully(var9);
               var21.readFully(var6);
               CipherProvider var10 = this.createNewCipher(2, this.mainSecretKey, this.mainIV);
               var10.decrypt(var6, 0, var6.length, var6, 0);
               byte[] var11 = this.getMD5Checksum(var6);
               if (!MessageDigest.isEqual(var9, var11)) {
                  throw StandardException.newException("XBCXK.S", new Object[0]);
               }
            }
         } catch (IOException var18) {
            throw StandardException.newException("XBCXL.S", var18, new Object[0]);
         } finally {
            try {
               if (var5 != null) {
                  var5.close();
               }

               if (var4 != null) {
                  var4.close();
               }
            } catch (IOException var19) {
               throw StandardException.newException("XBCXL.S", var19, new Object[0]);
            }

         }

      }
   }

   private byte[] getMD5Checksum(byte[] var1) throws StandardException {
      try {
         MessageDigest var2 = MessageDigest.getInstance("MD5");
         return var2.digest(var1);
      } catch (NoSuchAlgorithmException var3) {
         throw StandardException.newException("XBCXH.S", new Object[]{"MD5"});
      }
   }

   private StorageRandomAccessFile privAccessFile(StorageFactory var1, String var2, String var3) throws IOException {
      StorageFile var4 = var1.newStorageFile("", var2);
      return var4.getRandomAccessFile(var3);
   }

   private InputStream privAccessGetInputStream(StorageFactory var1, String var2) throws StandardException {
      StorageFile var3 = var1.newStorageFile("", var2);

      try {
         return var3.getInputStream();
      } catch (Exception var5) {
         throw StandardException.newException("XBCXL.S", var5, new Object[]{this.cryptoProvider});
      }
   }

   private static final class EncryptedKeyResult {
      public String hexOutput;
      public byte[] paddedInputKey;

      public EncryptedKeyResult(String var1, byte[] var2) {
         this.hexOutput = var1;
         this.paddedInputKey = var2;
      }
   }
}

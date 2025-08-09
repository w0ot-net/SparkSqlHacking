package org.apache.derby.impl.services.jce;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.derby.iapi.services.crypto.CipherProvider;
import org.apache.derby.shared.common.error.StandardException;

class JCECipherProvider implements CipherProvider {
   private Cipher cipher;
   private int mode;
   private boolean ivUsed = true;
   private final IvParameterSpec ivspec;
   private final int encryptionBlockSize;
   private boolean sunjce;
   private SecretKey cryptixKey;

   JCECipherProvider(int var1, SecretKey var2, byte[] var3, String var4, String var5) throws StandardException {
      this.ivspec = new IvParameterSpec(var3);

      Object var6;
      try {
         if (var5 == null) {
            this.cipher = Cipher.getInstance(var4);
            if ("SunJCE".equals(this.cipher.getProvider().getName())) {
               this.sunjce = true;
            }
         } else {
            if (var5.equals("SunJCE")) {
               this.sunjce = true;
            } else if (var5.equals("BouncyCastleProvider")) {
               var5 = "BC";
            }

            this.cipher = Cipher.getInstance(var4, var5);
         }

         this.encryptionBlockSize = this.cipher.getBlockSize();
         this.mode = var1;

         try {
            if (var1 == 1) {
               if (var4.indexOf("/ECB") > -1) {
                  this.cipher.init(1, var2);
               } else {
                  this.cipher.init(1, var2, this.ivspec);
               }
            } else {
               if (var1 != 2) {
                  throw StandardException.newException("XBCX1.S", new Object[0]);
               }

               if (var4.indexOf("/ECB") > -1) {
                  this.cipher.init(2, var2);
               } else {
                  this.cipher.init(2, var2, this.ivspec);
               }
            }
         } catch (InvalidKeyException var9) {
            if (!var4.startsWith("DES")) {
               throw StandardException.newException("XBCX0.S", var9, new Object[0]);
            }

            SecretKeyFactory var8;
            if (var5 == null) {
               var8 = SecretKeyFactory.getInstance(var2.getAlgorithm());
            } else {
               var8 = SecretKeyFactory.getInstance(var2.getAlgorithm(), var5);
            }

            var2 = var8.translateKey(new SecretKeySpec(var2.getEncoded(), var2.getAlgorithm()));
            if (var1 == 1) {
               if (var4.indexOf("/ECB") > -1) {
                  this.cipher.init(1, var2);
               } else {
                  this.cipher.init(1, var2, this.ivspec);
               }
            } else if (var1 == 2) {
               if (var4.indexOf("/ECB") > -1) {
                  this.cipher.init(2, var2);
               } else {
                  this.cipher.init(2, var2, this.ivspec);
               }
            }
         }

         this.cryptixKey = var2;
         if (this.cipher.getIV() == null) {
            this.ivUsed = false;
         }

         return;
      } catch (InvalidKeyException var10) {
         var6 = var10;
      } catch (NoSuchAlgorithmException var11) {
         throw StandardException.newException("XBCXC.S", new Object[]{var4, JCECipherFactory.providerErrorName(var5)});
      } catch (NoSuchProviderException var12) {
         throw StandardException.newException("XBCXG.S", new Object[]{JCECipherFactory.providerErrorName(var5)});
      } catch (GeneralSecurityException var13) {
         var6 = var13;
      }

      throw StandardException.newException("XBCX0.S", (Throwable)var6, new Object[0]);
   }

   public int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException {
      int var6 = 0;

      try {
         synchronized(this) {
            if (!this.sunjce) {
               try {
                  if (this.mode == 1) {
                     if (this.ivUsed) {
                        this.cipher.init(1, this.cryptixKey, this.ivspec);
                     } else {
                        this.cipher.init(1, this.cryptixKey);
                     }
                  } else if (this.mode == 2) {
                     if (this.ivUsed) {
                        this.cipher.init(2, this.cryptixKey, this.ivspec);
                     } else {
                        this.cipher.init(2, this.cryptixKey);
                     }
                  }
               } catch (InvalidKeyException var10) {
                  System.out.println("A " + var10);
                  throw StandardException.newException("XBCX0.S", var10, new Object[0]);
               }
            }

            var6 = this.cipher.doFinal(var1, var2, var3, var4, var5);
         }
      } catch (IllegalStateException var12) {
      } catch (GeneralSecurityException var13) {
         System.out.println("B " + var13);
         throw StandardException.newException("XBCX0.S", var13, new Object[0]);
      }

      return var6;
   }

   public int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException {
      int var6 = 0;

      try {
         synchronized(this) {
            if (!this.sunjce) {
               try {
                  if (this.mode == 1) {
                     if (this.ivUsed) {
                        this.cipher.init(1, this.cryptixKey, this.ivspec);
                     } else {
                        this.cipher.init(1, this.cryptixKey);
                     }
                  } else if (this.mode == 2) {
                     if (this.ivUsed) {
                        this.cipher.init(2, this.cryptixKey, this.ivspec);
                     } else {
                        this.cipher.init(2, this.cryptixKey);
                     }
                  }
               } catch (InvalidKeyException var10) {
                  System.out.println("C " + var10);
                  throw StandardException.newException("XBCX0.S", var10, new Object[0]);
               }
            }

            var6 = this.cipher.doFinal(var1, var2, var3, var4, var5);
         }
      } catch (IllegalStateException var12) {
      } catch (GeneralSecurityException var13) {
         System.out.println("D " + var13);
         throw StandardException.newException("XBCX0.S", var13, new Object[0]);
      }

      return var6;
   }

   boolean verifyIV(byte[] var1) {
      byte[] var2 = this.cipher.getIV();
      if (var2 == null) {
         return !this.ivUsed;
      } else if (var2.length != var1.length) {
         return false;
      } else {
         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var2[var3] != var1[var3]) {
               return false;
            }
         }

         return true;
      }
   }

   public int getEncryptionBlockSize() {
      return this.encryptionBlockSize;
   }
}

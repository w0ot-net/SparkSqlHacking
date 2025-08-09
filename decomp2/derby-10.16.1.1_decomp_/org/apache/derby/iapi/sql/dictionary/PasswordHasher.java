package org.apache.derby.iapi.sql.dictionary;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class PasswordHasher {
   private static final String ENCODING = "UTF-8";
   public static final String ID_PATTERN_SHA1_SCHEME = "3b60";
   public static final String ID_PATTERN_CONFIGURABLE_HASH_SCHEME = "3b61";
   public static final String ID_PATTERN_CONFIGURABLE_STRETCHED_SCHEME = "3b62";
   private static final char SEPARATOR_CHAR = ':';
   private String _messageDigestAlgorithm;
   private byte[] _salt;
   private int _iterations;

   public PasswordHasher(String var1, byte[] var2, int var3) {
      this._messageDigestAlgorithm = var1;
      this._salt = ArrayUtil.copy(var2);
      this._iterations = var3;
   }

   public PasswordHasher(String var1) {
      if (var1.startsWith("3b61")) {
         this._messageDigestAlgorithm = var1.substring(var1.indexOf(58) + 1);
         this._salt = null;
         this._iterations = 1;
      } else if (var1.startsWith("3b62")) {
         int var2 = var1.indexOf(58) + 1;
         int var3 = var1.indexOf(58, var2) + 1;
         int var4 = var1.indexOf(58, var3) + 1;
         this._salt = StringUtil.fromHexString(var1, var2, var3 - var2 - 1);
         this._iterations = Integer.parseInt(var1.substring(var3, var4 - 1));
         this._messageDigestAlgorithm = var1.substring(var4);
      }

   }

   public String hashPasswordIntoString(String var1, String var2) throws StandardException {
      if (var2 == null) {
         return null;
      } else {
         byte[] var3;
         byte[] var4;
         try {
            var3 = var1.getBytes("UTF-8");
            var4 = var2.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var8) {
            throw StandardException.plainWrapException(var8);
         }

         MessageDigest var5 = this.getEmptyMessageDigest();
         byte[] var6 = null;

         for(int var7 = 0; var7 < this._iterations; ++var7) {
            var5.reset();
            if (var6 != null) {
               var5.update(var6);
            }

            var5.update(var3);
            var5.update(var4);
            if (this._salt != null) {
               var5.update(this._salt);
            }

            var6 = var5.digest();
         }

         return StringUtil.toHexString(var6, 0, var6.length);
      }
   }

   private MessageDigest getEmptyMessageDigest() throws StandardException {
      if (this._messageDigestAlgorithm == null) {
         throw this.badMessageDigest((Throwable)null);
      } else {
         try {
            return MessageDigest.getInstance(this._messageDigestAlgorithm);
         } catch (NoSuchAlgorithmException var2) {
            throw this.badMessageDigest(var2);
         }
      }
   }

   private StandardException badMessageDigest(Throwable var1) {
      String var2 = this._messageDigestAlgorithm == null ? "NULL" : this._messageDigestAlgorithm;
      return StandardException.newException("XBCXW.S", var1, new Object[]{var2});
   }

   public String encodeHashingScheme() {
      return this.hashAndEncode("");
   }

   public String hashAndEncode(String var1, String var2) throws StandardException {
      String var3 = this.hashPasswordIntoString(var1, var2);
      return this.hashAndEncode(var3);
   }

   private String hashAndEncode(String var1) {
      return (this._salt == null || this._salt.length == 0) && this._iterations == 1 ? "3b61" + var1 + ":" + this._messageDigestAlgorithm : "3b62" + var1 + ":" + StringUtil.toHexString(this._salt, 0, this._salt.length) + ":" + this._iterations + ":" + this._messageDigestAlgorithm;
   }
}

package org.apache.commons.crypto.cipher;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import org.apache.commons.crypto.utils.Utils;

abstract class OpenSslFeedbackCipher {
   protected long context = 0L;
   protected final int algorithmMode;
   protected final int padding;
   protected int cipherMode = 0;

   OpenSslFeedbackCipher(long context, int algorithmMode, int padding) {
      this.context = context;
      this.algorithmMode = algorithmMode;
      this.padding = padding;
   }

   abstract void init(int var1, byte[] var2, AlgorithmParameterSpec var3) throws InvalidAlgorithmParameterException;

   abstract int update(ByteBuffer var1, ByteBuffer var2) throws ShortBufferException;

   abstract int update(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException;

   abstract int doFinal(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

   abstract int doFinal(ByteBuffer var1, ByteBuffer var2) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

   abstract void updateAAD(byte[] var1);

   public void clean() {
      if (this.context != 0L) {
         OpenSslNative.clean(this.context);
         this.context = 0L;
      }

   }

   public void checkState() {
      Utils.checkState(this.context != 0L, "Cipher context is invalid.");
   }
}

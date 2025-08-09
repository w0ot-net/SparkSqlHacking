package org.apache.commons.crypto.cipher;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public interface CryptoCipher extends Closeable {
   int getBlockSize();

   String getAlgorithm();

   void init(int var1, Key var2, AlgorithmParameterSpec var3) throws InvalidKeyException, InvalidAlgorithmParameterException;

   int update(ByteBuffer var1, ByteBuffer var2) throws ShortBufferException;

   int update(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException;

   int doFinal(ByteBuffer var1, ByteBuffer var2) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

   int doFinal(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

   default void updateAAD(byte[] aad) throws IllegalArgumentException, IllegalStateException, UnsupportedOperationException {
      throw new UnsupportedOperationException();
   }

   default void updateAAD(ByteBuffer aad) throws IllegalArgumentException, IllegalStateException, UnsupportedOperationException {
      throw new UnsupportedOperationException();
   }
}

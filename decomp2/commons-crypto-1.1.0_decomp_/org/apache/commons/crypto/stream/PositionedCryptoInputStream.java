package org.apache.commons.crypto.stream;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;
import org.apache.commons.crypto.stream.input.Input;
import org.apache.commons.crypto.utils.IoUtils;
import org.apache.commons.crypto.utils.Utils;

public class PositionedCryptoInputStream extends CtrCryptoInputStream {
   private final Queue bufferPool;
   private final Queue cipherPool;
   private final Properties properties;

   public PositionedCryptoInputStream(Properties properties, Input in, byte[] key, byte[] iv, long streamOffset) throws IOException {
      this(properties, in, Utils.getCipherInstance("AES/CTR/NoPadding", properties), CryptoInputStream.getBufferSize(properties), key, iv, streamOffset);
   }

   protected PositionedCryptoInputStream(Properties properties, Input input, CryptoCipher cipher, int bufferSize, byte[] key, byte[] iv, long streamOffset) throws IOException {
      super(input, cipher, bufferSize, key, iv, streamOffset);
      this.bufferPool = new ConcurrentLinkedQueue();
      this.cipherPool = new ConcurrentLinkedQueue();
      this.properties = properties;
   }

   public int read(long position, byte[] buffer, int offset, int length) throws IOException {
      this.checkStream();
      int n = this.input.read(position, buffer, offset, length);
      if (n > 0) {
         this.decrypt(position, buffer, offset, n);
      }

      return n;
   }

   public void readFully(long position, byte[] buffer, int offset, int length) throws IOException {
      this.checkStream();
      IoUtils.readFully(this.input, position, buffer, offset, length);
      if (length > 0) {
         this.decrypt(position, buffer, offset, length);
      }

   }

   public void readFully(long position, byte[] buffer) throws IOException {
      this.readFully(position, buffer, 0, buffer.length);
   }

   protected void decrypt(long position, byte[] buffer, int offset, int length) throws IOException {
      ByteBuffer inByteBuffer = this.getBuffer();
      ByteBuffer outByteBuffer = this.getBuffer();
      CipherState state = null;

      try {
         state = this.getCipherState();
         byte[] iv = (byte[])this.getInitIV().clone();
         this.resetCipher(state, position, iv);
         byte padding = this.getPadding(position);
         inByteBuffer.position(padding);

         for(int n = 0; n < length; padding = this.postDecryption(state, inByteBuffer, position + (long)n, iv)) {
            int toDecrypt = Math.min(length - n, inByteBuffer.remaining());
            inByteBuffer.put(buffer, offset + n, toDecrypt);
            this.decrypt(state, inByteBuffer, outByteBuffer, padding);
            outByteBuffer.get(buffer, offset + n, toDecrypt);
            n += toDecrypt;
         }
      } finally {
         this.returnBuffer(inByteBuffer);
         this.returnBuffer(outByteBuffer);
         this.returnCipherState(state);
      }

   }

   private void decrypt(CipherState state, ByteBuffer inByteBuffer, ByteBuffer outByteBuffer, byte padding) throws IOException {
      Utils.checkState(inByteBuffer.position() >= padding);
      if (inByteBuffer.position() != padding) {
         inByteBuffer.flip();
         outByteBuffer.clear();
         this.decryptBuffer(state, inByteBuffer, outByteBuffer);
         inByteBuffer.clear();
         outByteBuffer.flip();
         if (padding > 0) {
            outByteBuffer.position(padding);
         }

      }
   }

   private void decryptBuffer(CipherState state, ByteBuffer inByteBuffer, ByteBuffer outByteBuffer) throws IOException {
      int inputSize = inByteBuffer.remaining();

      try {
         int n = state.getCryptoCipher().update(inByteBuffer, outByteBuffer);
         if (n < inputSize) {
            state.getCryptoCipher().doFinal(inByteBuffer, outByteBuffer);
            state.reset(true);
         }

      } catch (GeneralSecurityException e) {
         throw new IOException(e);
      }
   }

   private byte postDecryption(CipherState state, ByteBuffer inByteBuffer, long position, byte[] iv) throws IOException {
      byte padding = 0;
      if (state.isReset()) {
         this.resetCipher(state, position, iv);
         padding = this.getPadding(position);
         inByteBuffer.position(padding);
      }

      return padding;
   }

   private void resetCipher(CipherState state, long position, byte[] iv) throws IOException {
      long counter = this.getCounter(position);
      CtrCryptoInputStream.calculateIV(this.getInitIV(), counter, iv);

      try {
         state.getCryptoCipher().init(2, this.key, new IvParameterSpec(iv));
      } catch (GeneralSecurityException var8) {
      }

      state.reset(false);
   }

   private CipherState getCipherState() throws IOException {
      CipherState state = (CipherState)this.cipherPool.poll();
      if (state == null) {
         CryptoCipher cryptoCipher;
         try {
            cryptoCipher = CryptoCipherFactory.getCryptoCipher("AES/CTR/NoPadding", this.properties);
         } catch (GeneralSecurityException e) {
            throw new IOException(e);
         }

         state = new CipherState(cryptoCipher);
      }

      return state;
   }

   private void returnCipherState(CipherState state) {
      if (state != null) {
         this.cipherPool.add(state);
      }

   }

   private ByteBuffer getBuffer() {
      ByteBuffer buffer = (ByteBuffer)this.bufferPool.poll();
      if (buffer == null) {
         buffer = ByteBuffer.allocateDirect(this.getBufferSize());
      }

      return buffer;
   }

   private void returnBuffer(ByteBuffer buf) {
      if (buf != null) {
         buf.clear();
         this.bufferPool.add(buf);
      }

   }

   public void close() throws IOException {
      if (this.isOpen()) {
         this.cleanBufferPool();
         super.close();
      }
   }

   private void cleanBufferPool() {
      ByteBuffer buf;
      while((buf = (ByteBuffer)this.bufferPool.poll()) != null) {
         CryptoInputStream.freeDirectBuffer(buf);
      }

   }

   private static class CipherState {
      private final CryptoCipher cryptoCipher;
      private boolean reset;

      public CipherState(CryptoCipher cipher) {
         this.cryptoCipher = cipher;
         this.reset = false;
      }

      public CryptoCipher getCryptoCipher() {
         return this.cryptoCipher;
      }

      public boolean isReset() {
         return this.reset;
      }

      public void reset(boolean reset) {
         this.reset = reset;
      }
   }
}

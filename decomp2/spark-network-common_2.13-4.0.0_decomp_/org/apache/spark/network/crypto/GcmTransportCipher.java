package org.apache.spark.network.crypto;

import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.StreamSegmentDecrypter;
import com.google.crypto.tink.subtle.StreamSegmentEncrypter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.util.ReferenceCounted;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.spark.network.util.AbstractFileRegion;
import org.apache.spark.network.util.ByteBufferWriteableChannel;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Longs;

public class GcmTransportCipher implements TransportCipher {
   private static final String HKDF_ALG = "HmacSha256";
   private static final int LENGTH_HEADER_BYTES = 8;
   @VisibleForTesting
   static final int CIPHERTEXT_BUFFER_SIZE = 32768;
   private final SecretKeySpec aesKey;

   public GcmTransportCipher(SecretKeySpec aesKey) {
      this.aesKey = aesKey;
   }

   AesGcmHkdfStreaming getAesGcmHkdfStreaming() throws InvalidAlgorithmParameterException {
      return new AesGcmHkdfStreaming(this.aesKey.getEncoded(), "HmacSha256", this.aesKey.getEncoded().length, 32768, 0);
   }

   @VisibleForTesting
   public String getKeyId() throws GeneralSecurityException {
      return TransportCipherUtil.getKeyId(this.aesKey);
   }

   @VisibleForTesting
   EncryptionHandler getEncryptionHandler() throws GeneralSecurityException {
      return new EncryptionHandler();
   }

   @VisibleForTesting
   DecryptionHandler getDecryptionHandler() throws GeneralSecurityException {
      return new DecryptionHandler();
   }

   public void addToChannel(Channel ch) throws GeneralSecurityException {
      ch.pipeline().addFirst("GcmTransportEncryption", this.getEncryptionHandler()).addFirst("GcmTransportDecryption", this.getDecryptionHandler());
   }

   @VisibleForTesting
   class EncryptionHandler extends ChannelOutboundHandlerAdapter {
      private final ByteBuffer plaintextBuffer;
      private final ByteBuffer ciphertextBuffer;
      private final AesGcmHkdfStreaming aesGcmHkdfStreaming = GcmTransportCipher.this.getAesGcmHkdfStreaming();

      EncryptionHandler() throws InvalidAlgorithmParameterException {
         this.plaintextBuffer = ByteBuffer.allocate(this.aesGcmHkdfStreaming.getPlaintextSegmentSize());
         this.ciphertextBuffer = ByteBuffer.allocate(this.aesGcmHkdfStreaming.getCiphertextSegmentSize());
      }

      public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
         GcmEncryptedMessage encryptedMessage = new GcmEncryptedMessage(this.aesGcmHkdfStreaming, msg, this.plaintextBuffer, this.ciphertextBuffer);
         ctx.write(encryptedMessage, promise);
      }
   }

   static class GcmEncryptedMessage extends AbstractFileRegion {
      private final Object plaintextMessage;
      private final ByteBuffer plaintextBuffer;
      private final ByteBuffer ciphertextBuffer;
      private final ByteBuffer headerByteBuffer;
      private final long bytesToRead;
      private long bytesRead = 0L;
      private final StreamSegmentEncrypter encrypter;
      private long transferred = 0L;
      private final long encryptedCount;

      GcmEncryptedMessage(AesGcmHkdfStreaming aesGcmHkdfStreaming, Object plaintextMessage, ByteBuffer plaintextBuffer, ByteBuffer ciphertextBuffer) throws GeneralSecurityException {
         Preconditions.checkArgument(plaintextMessage instanceof ByteBuf || plaintextMessage instanceof FileRegion, "Unrecognized message type: %s", (Object)plaintextMessage.getClass().getName());
         this.plaintextMessage = plaintextMessage;
         this.plaintextBuffer = plaintextBuffer;
         this.ciphertextBuffer = ciphertextBuffer;
         this.ciphertextBuffer.limit(0);
         this.bytesToRead = this.getReadableBytes();
         this.encryptedCount = 8L + aesGcmHkdfStreaming.expectedCiphertextSize(this.bytesToRead);
         byte[] lengthAad = Longs.toByteArray(this.encryptedCount);
         this.encrypter = aesGcmHkdfStreaming.newStreamSegmentEncrypter(lengthAad);
         this.headerByteBuffer = this.createHeaderByteBuffer();
      }

      private ByteBuffer createHeaderByteBuffer() {
         ByteBuffer encrypterHeader = this.encrypter.getHeader();
         return ByteBuffer.allocate(encrypterHeader.remaining() + 8).putLong(this.encryptedCount).put(encrypterHeader).flip();
      }

      public long position() {
         return 0L;
      }

      public long transferred() {
         return this.transferred;
      }

      public long count() {
         return this.encryptedCount;
      }

      public GcmEncryptedMessage touch(Object o) {
         super.touch(o);
         Object var4 = this.plaintextMessage;
         if (var4 instanceof ByteBuf byteBuf) {
            byteBuf.touch(o);
         } else {
            var4 = this.plaintextMessage;
            if (var4 instanceof FileRegion fileRegion) {
               fileRegion.touch(o);
            }
         }

         return this;
      }

      public GcmEncryptedMessage retain(int increment) {
         super.retain(increment);
         Object var4 = this.plaintextMessage;
         if (var4 instanceof ByteBuf byteBuf) {
            byteBuf.retain(increment);
         } else {
            var4 = this.plaintextMessage;
            if (var4 instanceof FileRegion fileRegion) {
               fileRegion.retain(increment);
            }
         }

         return this;
      }

      public boolean release(int decrement) {
         Object var4 = this.plaintextMessage;
         if (var4 instanceof ByteBuf byteBuf) {
            byteBuf.release(decrement);
         } else {
            var4 = this.plaintextMessage;
            if (var4 instanceof FileRegion fileRegion) {
               fileRegion.release(decrement);
            }
         }

         return super.release(decrement);
      }

      public long transferTo(WritableByteChannel target, long position) throws IOException {
         int transferredThisCall = 0;
         if (this.headerByteBuffer.hasRemaining()) {
            int written = target.write(this.headerByteBuffer);
            transferredThisCall += written;
            this.transferred += (long)written;
            if (this.headerByteBuffer.hasRemaining()) {
               return (long)written;
            }
         }

         if (this.ciphertextBuffer.hasRemaining()) {
            int written = target.write(this.ciphertextBuffer);
            transferredThisCall += written;
            this.transferred += (long)written;
            if (this.ciphertextBuffer.hasRemaining()) {
               return (long)transferredThisCall;
            }
         }

         while(this.bytesRead < this.bytesToRead) {
            long readableBytes = this.getReadableBytes();
            int readLimit = (int)Math.min(readableBytes, (long)this.plaintextBuffer.remaining());
            Object var10 = this.plaintextMessage;
            if (var10 instanceof ByteBuf byteBuf) {
               Preconditions.checkState(0 == this.plaintextBuffer.position());
               this.plaintextBuffer.limit(readLimit);
               byteBuf.readBytes(this.plaintextBuffer);
               Preconditions.checkState(readLimit == this.plaintextBuffer.position());
            } else {
               var10 = this.plaintextMessage;
               if (var10 instanceof FileRegion fileRegion) {
                  ByteBufferWriteableChannel plaintextChannel = new ByteBufferWriteableChannel(this.plaintextBuffer);
                  long plaintextRead = fileRegion.transferTo(plaintextChannel, fileRegion.transferred());
                  if (plaintextRead < (long)readLimit) {
                     return (long)transferredThisCall;
                  }
               }
            }

            boolean lastSegment = this.getReadableBytes() == 0L;
            this.plaintextBuffer.flip();
            this.bytesRead += (long)this.plaintextBuffer.remaining();
            this.ciphertextBuffer.clear();

            try {
               this.encrypter.encryptSegment(this.plaintextBuffer, lastSegment, this.ciphertextBuffer);
            } catch (GeneralSecurityException e) {
               throw new IllegalStateException("GeneralSecurityException from encrypter", e);
            }

            this.plaintextBuffer.clear();
            this.ciphertextBuffer.flip();
            int written = target.write(this.ciphertextBuffer);
            transferredThisCall += written;
            this.transferred += (long)written;
            if (this.ciphertextBuffer.hasRemaining()) {
               return (long)transferredThisCall;
            }
         }

         return (long)transferredThisCall;
      }

      private long getReadableBytes() {
         Object var3 = this.plaintextMessage;
         if (var3 instanceof ByteBuf byteBuf) {
            return (long)byteBuf.readableBytes();
         } else {
            var3 = this.plaintextMessage;
            if (var3 instanceof FileRegion fileRegion) {
               return fileRegion.count() - fileRegion.transferred();
            } else {
               throw new IllegalArgumentException("Unsupported message type: " + this.plaintextMessage.getClass().getName());
            }
         }
      }

      protected void deallocate() {
         Object var2 = this.plaintextMessage;
         if (var2 instanceof ReferenceCounted referenceCounted) {
            referenceCounted.release();
         }

         this.plaintextBuffer.clear();
         this.ciphertextBuffer.clear();
      }
   }

   @VisibleForTesting
   class DecryptionHandler extends ChannelInboundHandlerAdapter {
      private final ByteBuffer expectedLengthBuffer = ByteBuffer.allocate(8);
      private final ByteBuffer headerBuffer;
      private final ByteBuffer ciphertextBuffer;
      private final AesGcmHkdfStreaming aesGcmHkdfStreaming = GcmTransportCipher.this.getAesGcmHkdfStreaming();
      private final StreamSegmentDecrypter decrypter;
      private final int plaintextSegmentSize;
      private boolean decrypterInit = false;
      private boolean completed = false;
      private int segmentNumber = 0;
      private long expectedLength = -1L;
      private long ciphertextRead = 0L;

      DecryptionHandler() throws GeneralSecurityException {
         this.headerBuffer = ByteBuffer.allocate(this.aesGcmHkdfStreaming.getHeaderLength());
         this.ciphertextBuffer = ByteBuffer.allocate(this.aesGcmHkdfStreaming.getCiphertextSegmentSize());
         this.decrypter = this.aesGcmHkdfStreaming.newStreamSegmentDecrypter();
         this.plaintextSegmentSize = this.aesGcmHkdfStreaming.getPlaintextSegmentSize();
      }

      private boolean initalizeExpectedLength(ByteBuf ciphertextNettyBuf) {
         if (this.expectedLength < 0L) {
            ciphertextNettyBuf.readBytes(this.expectedLengthBuffer);
            if (this.expectedLengthBuffer.hasRemaining()) {
               return false;
            }

            this.expectedLengthBuffer.flip();
            this.expectedLength = this.expectedLengthBuffer.getLong();
            if (this.expectedLength < 0L) {
               throw new IllegalStateException("Invalid expected ciphertext length.");
            }

            this.ciphertextRead += 8L;
         }

         return true;
      }

      private boolean initalizeDecrypter(ByteBuf ciphertextNettyBuf) throws GeneralSecurityException {
         if (!this.decrypterInit) {
            ciphertextNettyBuf.readBytes(this.headerBuffer);
            if (this.headerBuffer.hasRemaining()) {
               return false;
            }

            this.headerBuffer.flip();
            byte[] lengthAad = Longs.toByteArray(this.expectedLength);
            this.decrypter.init(this.headerBuffer, lengthAad);
            this.decrypterInit = true;
            this.ciphertextRead += (long)this.aesGcmHkdfStreaming.getHeaderLength();
            if (this.expectedLength == this.ciphertextRead) {
               this.completed = true;
            }
         }

         return true;
      }

      public void channelRead(ChannelHandlerContext ctx, Object ciphertextMessage) throws GeneralSecurityException {
         Preconditions.checkArgument(ciphertextMessage instanceof ByteBuf, "Unrecognized message type: %s", (Object)ciphertextMessage.getClass().getName());
         ByteBuf ciphertextNettyBuf = (ByteBuf)ciphertextMessage;

         try {
            if (this.initalizeExpectedLength(ciphertextNettyBuf)) {
               if (!this.initalizeDecrypter(ciphertextNettyBuf)) {
                  return;
               }

               for(int nettyBufReadableBytes = ciphertextNettyBuf.readableBytes(); nettyBufReadableBytes > 0 && !this.completed; nettyBufReadableBytes = ciphertextNettyBuf.readableBytes()) {
                  int readableBytes = Integer.min(nettyBufReadableBytes, this.ciphertextBuffer.remaining());
                  int expectedRemaining = (int)(this.expectedLength - this.ciphertextRead);
                  int bytesToRead = Integer.min(readableBytes, expectedRemaining);
                  this.ciphertextBuffer.limit(this.ciphertextBuffer.position() + bytesToRead);
                  ciphertextNettyBuf.readBytes(this.ciphertextBuffer);
                  this.ciphertextRead += (long)bytesToRead;
                  if (this.ciphertextRead == this.expectedLength) {
                     this.completed = true;
                  } else if (this.ciphertextRead > this.expectedLength) {
                     throw new IllegalStateException("Read more ciphertext than expected.");
                  }

                  if (this.ciphertextBuffer.limit() != this.ciphertextBuffer.capacity() && !this.completed) {
                     this.ciphertextBuffer.limit(this.ciphertextBuffer.capacity());
                  } else {
                     ByteBuffer plaintextBuffer = ByteBuffer.allocate(this.plaintextSegmentSize);
                     this.ciphertextBuffer.flip();
                     this.decrypter.decryptSegment(this.ciphertextBuffer, this.segmentNumber, this.completed, plaintextBuffer);
                     ++this.segmentNumber;
                     this.ciphertextBuffer.clear();
                     plaintextBuffer.flip();
                     ctx.fireChannelRead(Unpooled.wrappedBuffer(plaintextBuffer));
                  }
               }

               return;
            }
         } finally {
            ciphertextNettyBuf.release();
         }

      }
   }
}

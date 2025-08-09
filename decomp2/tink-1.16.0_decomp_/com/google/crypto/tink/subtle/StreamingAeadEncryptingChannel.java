package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

class StreamingAeadEncryptingChannel implements WritableByteChannel {
   private WritableByteChannel ciphertextChannel;
   private StreamSegmentEncrypter encrypter;
   ByteBuffer ptBuffer;
   ByteBuffer ctBuffer;
   private int plaintextSegmentSize;
   boolean open = true;

   public StreamingAeadEncryptingChannel(NonceBasedStreamingAead streamAead, WritableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
      this.ciphertextChannel = ciphertextChannel;
      this.encrypter = streamAead.newStreamSegmentEncrypter(associatedData);
      this.plaintextSegmentSize = streamAead.getPlaintextSegmentSize();
      this.ptBuffer = ByteBuffer.allocate(this.plaintextSegmentSize);
      this.ptBuffer.limit(this.plaintextSegmentSize - streamAead.getCiphertextOffset());
      this.ctBuffer = ByteBuffer.allocate(streamAead.getCiphertextSegmentSize());
      this.ctBuffer.put(this.encrypter.getHeader());
      this.ctBuffer.flip();
      ciphertextChannel.write(this.ctBuffer);
   }

   public synchronized int write(ByteBuffer pt) throws IOException {
      if (!this.open) {
         throw new ClosedChannelException();
      } else {
         if (this.ctBuffer.remaining() > 0) {
            this.ciphertextChannel.write(this.ctBuffer);
         }

         int startPosition = pt.position();

         while(pt.remaining() > this.ptBuffer.remaining()) {
            if (this.ctBuffer.remaining() > 0) {
               return pt.position() - startPosition;
            }

            int sliceSize = this.ptBuffer.remaining();
            ByteBuffer slice = pt.slice();
            slice.limit(sliceSize);
            pt.position(pt.position() + sliceSize);

            try {
               this.ptBuffer.flip();
               this.ctBuffer.clear();
               if (slice.remaining() != 0) {
                  this.encrypter.encryptSegment(this.ptBuffer, slice, false, this.ctBuffer);
               } else {
                  this.encrypter.encryptSegment(this.ptBuffer, false, this.ctBuffer);
               }
            } catch (GeneralSecurityException ex) {
               throw new IOException(ex);
            }

            this.ctBuffer.flip();
            this.ciphertextChannel.write(this.ctBuffer);
            this.ptBuffer.clear();
            this.ptBuffer.limit(this.plaintextSegmentSize);
         }

         this.ptBuffer.put(pt);
         return pt.position() - startPosition;
      }
   }

   public synchronized void close() throws IOException {
      if (this.open) {
         while(this.ctBuffer.remaining() > 0) {
            int n = this.ciphertextChannel.write(this.ctBuffer);
            if (n <= 0) {
               throw new IOException("Failed to write ciphertext before closing");
            }
         }

         try {
            this.ctBuffer.clear();
            this.ptBuffer.flip();
            this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
         } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
         }

         this.ctBuffer.flip();

         while(this.ctBuffer.remaining() > 0) {
            int n = this.ciphertextChannel.write(this.ctBuffer);
            if (n <= 0) {
               throw new IOException("Failed to write ciphertext before closing");
            }
         }

         this.ciphertextChannel.close();
         this.open = false;
      }
   }

   public boolean isOpen() {
      return this.open;
   }
}

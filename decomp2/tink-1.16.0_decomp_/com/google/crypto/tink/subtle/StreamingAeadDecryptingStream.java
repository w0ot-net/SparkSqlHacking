package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingStream extends FilterInputStream {
   private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
   private final ByteBuffer ciphertextSegment;
   private final ByteBuffer plaintextSegment;
   private final int headerLength;
   private boolean headerRead;
   private boolean endOfCiphertext;
   private boolean endOfPlaintext;
   private boolean decryptionErrorOccured;
   private final byte[] aad;
   private int segmentNr;
   private final StreamSegmentDecrypter decrypter;
   private final int ciphertextSegmentSize;
   private final int firstCiphertextSegmentSize;

   public StreamingAeadDecryptingStream(NonceBasedStreamingAead streamAead, InputStream ciphertextStream, byte[] associatedData) throws GeneralSecurityException, IOException {
      super(ciphertextStream);
      this.decrypter = streamAead.newStreamSegmentDecrypter();
      this.headerLength = streamAead.getHeaderLength();
      this.aad = Arrays.copyOf(associatedData, associatedData.length);
      this.ciphertextSegmentSize = streamAead.getCiphertextSegmentSize();
      this.ciphertextSegment = ByteBuffer.allocate(this.ciphertextSegmentSize + 1);
      this.ciphertextSegment.limit(0);
      this.firstCiphertextSegmentSize = this.ciphertextSegmentSize - streamAead.getCiphertextOffset();
      this.plaintextSegment = ByteBuffer.allocate(streamAead.getPlaintextSegmentSize() + 16);
      this.plaintextSegment.limit(0);
      this.headerRead = false;
      this.endOfCiphertext = false;
      this.endOfPlaintext = false;
      this.segmentNr = 0;
      this.decryptionErrorOccured = false;
   }

   private void readHeader() throws IOException {
      if (this.headerRead) {
         this.setDecryptionErrorOccured();
         throw new IOException("Decryption failed.");
      } else {
         ByteBuffer header = ByteBuffer.allocate(this.headerLength);

         while(header.remaining() > 0) {
            int read = this.in.read(header.array(), header.position(), header.remaining());
            if (read == -1) {
               this.setDecryptionErrorOccured();
               throw new IOException("Ciphertext is too short");
            }

            if (read == 0) {
               throw new IOException("Could not read bytes from the ciphertext stream");
            }

            header.position(header.position() + read);
         }

         header.flip();

         try {
            this.decrypter.init(header, this.aad);
         } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
         }

         this.headerRead = true;
      }
   }

   private void setDecryptionErrorOccured() {
      this.decryptionErrorOccured = true;
      this.plaintextSegment.limit(0);
   }

   private void loadSegment() throws IOException {
      while(true) {
         if (!this.endOfCiphertext && this.ciphertextSegment.remaining() > 0) {
            int read = this.in.read(this.ciphertextSegment.array(), this.ciphertextSegment.position(), this.ciphertextSegment.remaining());
            if (read > 0) {
               this.ciphertextSegment.position(this.ciphertextSegment.position() + read);
               continue;
            }

            if (read == -1) {
               this.endOfCiphertext = true;
               continue;
            }

            if (read != 0) {
               continue;
            }

            throw new IOException("Could not read bytes from the ciphertext stream");
         }

         byte lastByte = 0;
         if (!this.endOfCiphertext) {
            lastByte = this.ciphertextSegment.get(this.ciphertextSegment.position() - 1);
            this.ciphertextSegment.position(this.ciphertextSegment.position() - 1);
         }

         this.ciphertextSegment.flip();
         this.plaintextSegment.clear();

         try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
         } catch (GeneralSecurityException ex) {
            this.setDecryptionErrorOccured();
            throw new IOException(ex.getMessage() + "\n" + this.toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, ex);
         }

         ++this.segmentNr;
         this.plaintextSegment.flip();
         this.ciphertextSegment.clear();
         if (!this.endOfCiphertext) {
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
            this.ciphertextSegment.put(lastByte);
         }

         return;
      }
   }

   public int read() throws IOException {
      byte[] oneByte = new byte[1];
      int ret = this.read(oneByte, 0, 1);
      if (ret == 1) {
         return oneByte[0] & 255;
      } else if (ret == -1) {
         return ret;
      } else {
         throw new IOException("Reading failed");
      }
   }

   public int read(byte[] dst) throws IOException {
      return this.read(dst, 0, dst.length);
   }

   public synchronized int read(byte[] dst, int offset, int length) throws IOException {
      if (this.decryptionErrorOccured) {
         throw new IOException("Decryption failed.");
      } else {
         if (!this.headerRead) {
            this.readHeader();
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.firstCiphertextSegmentSize + 1);
         }

         if (this.endOfPlaintext) {
            return -1;
         } else {
            int bytesRead;
            int sliceSize;
            for(bytesRead = 0; bytesRead < length; bytesRead += sliceSize) {
               if (this.plaintextSegment.remaining() == 0) {
                  if (this.endOfCiphertext) {
                     this.endOfPlaintext = true;
                     break;
                  }

                  this.loadSegment();
               }

               sliceSize = Math.min(this.plaintextSegment.remaining(), length - bytesRead);
               this.plaintextSegment.get(dst, bytesRead + offset, sliceSize);
            }

            return bytesRead == 0 && this.endOfPlaintext ? -1 : bytesRead;
         }
      }
   }

   public synchronized void close() throws IOException {
      super.close();
   }

   public synchronized int available() {
      return this.plaintextSegment.remaining();
   }

   public synchronized void mark(int readlimit) {
   }

   public boolean markSupported() {
      return false;
   }

   public long skip(long n) throws IOException {
      long maxSkipBufferSize = (long)this.ciphertextSegmentSize;
      long remaining = n;
      if (n <= 0L) {
         return 0L;
      } else {
         int size = (int)Math.min(maxSkipBufferSize, n);

         int bytesRead;
         for(byte[] skipBuffer = new byte[size]; remaining > 0L; remaining -= (long)bytesRead) {
            bytesRead = this.read(skipBuffer, 0, (int)Math.min((long)size, remaining));
            if (bytesRead <= 0) {
               break;
            }
         }

         return n - remaining;
      }
   }

   public synchronized String toString() {
      StringBuilder res = new StringBuilder();
      res.append("StreamingAeadDecryptingStream").append("\nsegmentNr:").append(this.segmentNr).append("\nciphertextSegmentSize:").append(this.ciphertextSegmentSize).append("\nheaderRead:").append(this.headerRead).append("\nendOfCiphertext:").append(this.endOfCiphertext).append("\nendOfPlaintext:").append(this.endOfPlaintext).append("\ndecryptionErrorOccured:").append(this.decryptionErrorOccured).append("\nciphertextSgement").append(" position:").append(this.ciphertextSegment.position()).append(" limit:").append(this.ciphertextSegment.limit()).append("\nplaintextSegment").append(" position:").append(this.plaintextSegment.position()).append(" limit:").append(this.plaintextSegment.limit());
      return res.toString();
   }
}

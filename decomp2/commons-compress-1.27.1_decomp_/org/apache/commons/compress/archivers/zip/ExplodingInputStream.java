package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.ExactMath;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.CloseShieldInputStream;

final class ExplodingInputStream extends InputStream implements InputStreamStatistics {
   private final InputStream in;
   private BitStream bits;
   private final int dictionarySize;
   private final int numberOfTrees;
   private final int minimumMatchLength;
   private BinaryTree literalTree;
   private BinaryTree lengthTree;
   private BinaryTree distanceTree;
   private final CircularBuffer buffer = new CircularBuffer(32768);
   private long uncompressedCount;
   private long treeSizes;

   ExplodingInputStream(int dictionarySize, int numberOfTrees, InputStream in) {
      if (dictionarySize != 4096 && dictionarySize != 8192) {
         throw new IllegalArgumentException("The dictionary size must be 4096 or 8192");
      } else if (numberOfTrees != 2 && numberOfTrees != 3) {
         throw new IllegalArgumentException("The number of trees must be 2 or 3");
      } else {
         this.dictionarySize = dictionarySize;
         this.numberOfTrees = numberOfTrees;
         this.minimumMatchLength = numberOfTrees;
         this.in = in;
      }
   }

   public void close() throws IOException {
      this.in.close();
   }

   private void fillBuffer() throws IOException {
      this.init();
      int bit = this.bits.nextBit();
      if (bit != -1) {
         if (bit == 1) {
            int literal;
            if (this.literalTree != null) {
               literal = this.literalTree.read(this.bits);
            } else {
               literal = this.bits.nextByte();
            }

            if (literal == -1) {
               return;
            }

            this.buffer.put(literal);
         } else {
            int distanceLowSize = this.dictionarySize == 4096 ? 6 : 7;
            int distanceLow = (int)this.bits.nextBits(distanceLowSize);
            int distanceHigh = this.distanceTree.read(this.bits);
            if (distanceHigh == -1 && distanceLow <= 0) {
               return;
            }

            int distance = distanceHigh << distanceLowSize | distanceLow;
            int length = this.lengthTree.read(this.bits);
            if (length == 63) {
               long nextByte = this.bits.nextBits(8);
               if (nextByte == -1L) {
                  return;
               }

               length = ExactMath.add(length, nextByte);
            }

            length += this.minimumMatchLength;
            this.buffer.copy(distance + 1, length);
         }

      }
   }

   public long getCompressedCount() {
      return this.bits.getBytesRead() + this.treeSizes;
   }

   public long getUncompressedCount() {
      return this.uncompressedCount;
   }

   private void init() throws IOException {
      if (this.bits == null) {
         BoundedInputStream cis = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(CloseShieldInputStream.wrap(this.in))).get();

         try {
            if (this.numberOfTrees == 3) {
               this.literalTree = BinaryTree.decode(cis, 256);
            }

            this.lengthTree = BinaryTree.decode(cis, 64);
            this.distanceTree = BinaryTree.decode(cis, 64);
            this.treeSizes += cis.getCount();
         } catch (Throwable var5) {
            if (cis != null) {
               try {
                  cis.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (cis != null) {
            cis.close();
         }

         this.bits = new BitStream(this.in);
      }

   }

   public int read() throws IOException {
      if (!this.buffer.available()) {
         try {
            this.fillBuffer();
         } catch (IllegalArgumentException ex) {
            throw new IOException("bad IMPLODE stream", ex);
         }
      }

      int ret = this.buffer.get();
      if (ret > -1) {
         ++this.uncompressedCount;
      }

      return ret;
   }
}

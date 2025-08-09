package org.apache.commons.compress.archivers.zip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.ArrayFill;

final class BinaryTree {
   private static final int UNDEFINED = -1;
   private static final int NODE = -2;
   private final int[] tree;

   static BinaryTree decode(InputStream inputStream, int totalNumberOfValues) throws IOException {
      if (totalNumberOfValues < 0) {
         throw new IllegalArgumentException("totalNumberOfValues must be bigger than 0, is " + totalNumberOfValues);
      } else {
         int size = inputStream.read() + 1;
         if (size == 0) {
            throw new IOException("Cannot read the size of the encoded tree, unexpected end of stream");
         } else {
            byte[] encodedTree = IOUtils.readRange(inputStream, size);
            if (encodedTree.length != size) {
               throw new EOFException();
            } else {
               int maxLength = 0;
               int[] originalBitLengths = new int[totalNumberOfValues];
               int pos = 0;

               for(byte b : encodedTree) {
                  int numberOfValues = ((b & 240) >> 4) + 1;
                  if (pos + numberOfValues > totalNumberOfValues) {
                     throw new IOException("Number of values exceeds given total number of values");
                  }

                  int bitLength = (b & 15) + 1;

                  for(int j = 0; j < numberOfValues; ++j) {
                     originalBitLengths[pos++] = bitLength;
                  }

                  maxLength = Math.max(maxLength, bitLength);
               }

               int oBitLengths = originalBitLengths.length;
               int[] permutation = new int[oBitLengths];

               for(int k = 0; k < permutation.length; permutation[k] = k++) {
               }

               int c = 0;
               int[] sortedBitLengths = new int[oBitLengths];

               for(int k = 0; k < oBitLengths; ++k) {
                  for(int l = 0; l < oBitLengths; ++l) {
                     if (originalBitLengths[l] == k) {
                        sortedBitLengths[c] = k;
                        permutation[c] = l;
                        ++c;
                     }
                  }
               }

               int code = 0;
               int codeIncrement = 0;
               int lastBitLength = 0;
               int[] codes = new int[totalNumberOfValues];

               for(int i = totalNumberOfValues - 1; i >= 0; --i) {
                  code += codeIncrement;
                  if (sortedBitLengths[i] != lastBitLength) {
                     lastBitLength = sortedBitLengths[i];
                     codeIncrement = 1 << 16 - lastBitLength;
                  }

                  codes[permutation[i]] = code;
               }

               BinaryTree tree = new BinaryTree(maxLength);

               for(int k = 0; k < codes.length; ++k) {
                  int bitLength = originalBitLengths[k];
                  if (bitLength > 0) {
                     tree.addLeaf(0, Integer.reverse(codes[k] << 16), bitLength, k);
                  }
               }

               return tree;
            }
         }
      }
   }

   BinaryTree(int depth) {
      if (depth >= 0 && depth <= 30) {
         this.tree = ArrayFill.fill(new int[(int)((1L << depth + 1) - 1L)], -1);
      } else {
         throw new IllegalArgumentException("depth must be bigger than 0 and not bigger than 30 but is " + depth);
      }
   }

   public void addLeaf(int node, int path, int depth, int value) {
      if (depth == 0) {
         if (this.tree[node] != -1) {
            throw new IllegalArgumentException("Tree value at index " + node + " has already been assigned (" + this.tree[node] + ")");
         }

         this.tree[node] = value;
      } else {
         this.tree[node] = -2;
         int nextChild = 2 * node + 1 + (path & 1);
         this.addLeaf(nextChild, path >>> 1, depth - 1, value);
      }

   }

   public int read(BitStream stream) throws IOException {
      int currentIndex = 0;

      while(true) {
         int bit = stream.nextBit();
         if (bit == -1) {
            return -1;
         }

         int childIndex = 2 * currentIndex + 1 + bit;
         int value = this.tree[childIndex];
         if (value != -2) {
            if (value != -1) {
               return value;
            }

            throw new IOException("The child " + bit + " of node at index " + currentIndex + " is not defined");
         }

         currentIndex = childIndex;
      }
   }
}

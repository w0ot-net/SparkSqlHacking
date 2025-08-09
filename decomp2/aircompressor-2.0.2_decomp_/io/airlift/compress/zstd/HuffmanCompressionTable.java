package io.airlift.compress.zstd;

import java.util.Arrays;

final class HuffmanCompressionTable {
   private final short[] values;
   private final byte[] numberOfBits;
   private int maxSymbol;
   private int maxNumberOfBits;

   public HuffmanCompressionTable(int capacity) {
      this.values = new short[capacity];
      this.numberOfBits = new byte[capacity];
   }

   public static int optimalNumberOfBits(int maxNumberOfBits, int inputSize, int maxSymbol) {
      if (inputSize <= 1) {
         throw new IllegalArgumentException();
      } else {
         int result = Math.min(maxNumberOfBits, Util.highestBit(inputSize - 1) - 1);
         result = Math.max(result, Util.minTableLog(inputSize, maxSymbol));
         result = Math.max(result, 5);
         result = Math.min(result, 12);
         return result;
      }
   }

   public void initialize(int[] counts, int maxSymbol, int maxNumberOfBits, HuffmanCompressionTableWorkspace workspace) {
      Util.checkArgument(maxSymbol <= 255, "Max symbol value too large");
      workspace.reset();
      NodeTable nodeTable = workspace.nodeTable;
      nodeTable.reset();
      int lastNonZero = this.buildTree(counts, maxSymbol, nodeTable);
      maxNumberOfBits = setMaxHeight(nodeTable, lastNonZero, maxNumberOfBits, workspace);
      Util.checkArgument(maxNumberOfBits <= 12, "Max number of bits larger than max table size");
      int symbolCount = maxSymbol + 1;

      for(int node = 0; node < symbolCount; ++node) {
         int symbol = nodeTable.symbols[node];
         this.numberOfBits[symbol] = nodeTable.numberOfBits[node];
      }

      short[] entriesPerRank = workspace.entriesPerRank;
      short[] valuesPerRank = workspace.valuesPerRank;

      for(int n = 0; n <= lastNonZero; ++n) {
         ++entriesPerRank[nodeTable.numberOfBits[n]];
      }

      short startingValue = 0;

      for(int rank = maxNumberOfBits; rank > 0; --rank) {
         valuesPerRank[rank] = startingValue;
         startingValue = (short)(startingValue + entriesPerRank[rank]);
         startingValue = (short)(startingValue >>> 1);
      }

      for(int n = 0; n <= maxSymbol; ++n) {
         this.values[n] = valuesPerRank[this.numberOfBits[n]]++;
      }

      this.maxSymbol = maxSymbol;
      this.maxNumberOfBits = maxNumberOfBits;
   }

   private int buildTree(int[] counts, int maxSymbol, NodeTable nodeTable) {
      short current = 0;

      for(int symbol = 0; symbol <= maxSymbol; ++symbol) {
         int count = counts[symbol];

         int position;
         for(position = current; position > 1 && count > nodeTable.count[position - 1]; --position) {
            nodeTable.copyNode(position - 1, position);
         }

         nodeTable.count[position] = count;
         nodeTable.symbols[position] = symbol;
         ++current;
      }

      int lastNonZero;
      for(lastNonZero = maxSymbol; nodeTable.count[lastNonZero] == 0; --lastNonZero) {
      }

      short nonLeafStart = 256;
      int currentNonLeaf = nonLeafStart;
      nodeTable.count[nonLeafStart] = nodeTable.count[lastNonZero] + nodeTable.count[lastNonZero - 1];
      nodeTable.parents[lastNonZero] = nonLeafStart;
      nodeTable.parents[lastNonZero - 1] = nonLeafStart;
      current = (short)(nonLeafStart + 1);
      int var15 = lastNonZero - 2;
      int root = 256 + lastNonZero - 1;

      for(int n = current; n <= root; ++n) {
         nodeTable.count[n] = 1073741824;
      }

      while(current <= root) {
         int child1;
         if (var15 >= 0 && nodeTable.count[var15] < nodeTable.count[currentNonLeaf]) {
            child1 = var15--;
         } else {
            child1 = currentNonLeaf++;
         }

         int child2;
         if (var15 >= 0 && nodeTable.count[var15] < nodeTable.count[currentNonLeaf]) {
            child2 = var15--;
         } else {
            child2 = currentNonLeaf++;
         }

         nodeTable.count[current] = nodeTable.count[child1] + nodeTable.count[child2];
         nodeTable.parents[child1] = current;
         nodeTable.parents[child2] = current++;
      }

      nodeTable.numberOfBits[root] = 0;

      for(int n = root - 1; n >= nonLeafStart; --n) {
         short parent = nodeTable.parents[n];
         nodeTable.numberOfBits[n] = (byte)(nodeTable.numberOfBits[parent] + 1);
      }

      for(int n = 0; n <= lastNonZero; ++n) {
         short parent = nodeTable.parents[n];
         nodeTable.numberOfBits[n] = (byte)(nodeTable.numberOfBits[parent] + 1);
      }

      return lastNonZero;
   }

   public void encodeSymbol(BitOutputStream output, int symbol) {
      output.addBitsFast(this.values[symbol], this.numberOfBits[symbol]);
   }

   public int write(Object outputBase, long outputAddress, int outputSize, HuffmanTableWriterWorkspace workspace) {
      byte[] weights = workspace.weights;
      int maxNumberOfBits = this.maxNumberOfBits;
      int maxSymbol = this.maxSymbol;

      for(int symbol = 0; symbol < maxSymbol; ++symbol) {
         int bits = this.numberOfBits[symbol];
         if (bits == 0) {
            weights[symbol] = 0;
         } else {
            weights[symbol] = (byte)(maxNumberOfBits + 1 - bits);
         }
      }

      int size = compressWeights(outputBase, outputAddress + 1L, outputSize - 1, weights, maxSymbol, workspace);
      if (maxSymbol > 127 && size > 127) {
         throw new AssertionError();
      } else if (size != 0 && size != 1 && size < maxSymbol / 2) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)size);
         return size + 1;
      } else {
         int entryCount = maxSymbol;
         size = (maxSymbol + 1) / 2;
         Util.checkArgument(size + 1 <= outputSize, "Output size too small");
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)(127 + maxSymbol));
         long output = outputAddress + 1L;
         weights[maxSymbol] = 0;

         for(int i = 0; i < entryCount; i += 2) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output, (byte)((weights[i] << 4) + weights[i + 1]));
            ++output;
         }

         return (int)(output - outputAddress);
      }
   }

   public boolean isValid(int[] counts, int maxSymbol) {
      if (maxSymbol > this.maxSymbol) {
         return false;
      } else {
         for(int symbol = 0; symbol <= maxSymbol; ++symbol) {
            if (counts[symbol] != 0 && this.numberOfBits[symbol] == 0) {
               return false;
            }
         }

         return true;
      }
   }

   public int estimateCompressedSize(int[] counts, int maxSymbol) {
      int numberOfBits = 0;

      for(int symbol = 0; symbol <= Math.min(maxSymbol, this.maxSymbol); ++symbol) {
         numberOfBits += this.numberOfBits[symbol] * counts[symbol];
      }

      return numberOfBits >>> 3;
   }

   private static int setMaxHeight(NodeTable nodeTable, int lastNonZero, int maxNumberOfBits, HuffmanCompressionTableWorkspace workspace) {
      int largestBits = nodeTable.numberOfBits[lastNonZero];
      if (largestBits <= maxNumberOfBits) {
         return largestBits;
      } else {
         int totalCost = 0;
         int baseCost = 1 << largestBits - maxNumberOfBits;

         int n;
         for(n = lastNonZero; nodeTable.numberOfBits[n] > maxNumberOfBits; --n) {
            totalCost += baseCost - (1 << largestBits - nodeTable.numberOfBits[n]);
            nodeTable.numberOfBits[n] = (byte)maxNumberOfBits;
         }

         while(nodeTable.numberOfBits[n] == maxNumberOfBits) {
            --n;
         }

         totalCost >>>= largestBits - maxNumberOfBits;
         int noSymbol = -252645136;
         int[] rankLast = workspace.rankLast;
         Arrays.fill(rankLast, noSymbol);
         int currentNbBits = maxNumberOfBits;

         for(int pos = n; pos >= 0; --pos) {
            if (nodeTable.numberOfBits[pos] < currentNbBits) {
               currentNbBits = nodeTable.numberOfBits[pos];
               rankLast[maxNumberOfBits - currentNbBits] = pos;
            }
         }

         while(totalCost > 0) {
            int numberOfBitsToDecrease;
            for(numberOfBitsToDecrease = Util.highestBit(totalCost) + 1; numberOfBitsToDecrease > 1; --numberOfBitsToDecrease) {
               int highPosition = rankLast[numberOfBitsToDecrease];
               int lowPosition = rankLast[numberOfBitsToDecrease - 1];
               if (highPosition != noSymbol) {
                  if (lowPosition == noSymbol) {
                     break;
                  }

                  int highTotal = nodeTable.count[highPosition];
                  int lowTotal = 2 * nodeTable.count[lowPosition];
                  if (highTotal <= lowTotal) {
                     break;
                  }
               }
            }

            while(numberOfBitsToDecrease <= 12 && rankLast[numberOfBitsToDecrease] == noSymbol) {
               ++numberOfBitsToDecrease;
            }

            totalCost -= 1 << numberOfBitsToDecrease - 1;
            if (rankLast[numberOfBitsToDecrease - 1] == noSymbol) {
               rankLast[numberOfBitsToDecrease - 1] = rankLast[numberOfBitsToDecrease];
            }

            ++nodeTable.numberOfBits[rankLast[numberOfBitsToDecrease]];
            if (rankLast[numberOfBitsToDecrease] == 0) {
               rankLast[numberOfBitsToDecrease] = noSymbol;
            } else {
               int var10002 = rankLast[numberOfBitsToDecrease]--;
               if (nodeTable.numberOfBits[rankLast[numberOfBitsToDecrease]] != maxNumberOfBits - numberOfBitsToDecrease) {
                  rankLast[numberOfBitsToDecrease] = noSymbol;
               }
            }
         }

         while(totalCost < 0) {
            if (rankLast[1] == noSymbol) {
               while(nodeTable.numberOfBits[n] == maxNumberOfBits) {
                  --n;
               }

               --nodeTable.numberOfBits[n + 1];
               rankLast[1] = n + 1;
               ++totalCost;
            } else {
               --nodeTable.numberOfBits[rankLast[1] + 1];
               int var20 = rankLast[1]++;
               ++totalCost;
            }
         }

         return maxNumberOfBits;
      }
   }

   private static int compressWeights(Object outputBase, long outputAddress, int outputSize, byte[] weights, int weightsLength, HuffmanTableWriterWorkspace workspace) {
      if (weightsLength <= 1) {
         return 0;
      } else {
         int[] counts = workspace.counts;
         Histogram.count(weights, weightsLength, counts);
         int maxSymbol = Histogram.findMaxSymbol(counts, 12);
         int maxCount = Histogram.findLargestCount(counts, maxSymbol);
         if (maxCount == weightsLength) {
            return 1;
         } else if (maxCount == 1) {
            return 0;
         } else {
            short[] normalizedCounts = workspace.normalizedCounts;
            int tableLog = FiniteStateEntropy.optimalTableLog(6, weightsLength, maxSymbol);
            FiniteStateEntropy.normalizeCounts(normalizedCounts, tableLog, counts, weightsLength, maxSymbol);
            long outputLimit = outputAddress + (long)outputSize;
            int headerSize = FiniteStateEntropy.writeNormalizedCounts(outputBase, outputAddress, outputSize, normalizedCounts, maxSymbol, tableLog);
            long output = outputAddress + (long)headerSize;
            FseCompressionTable compressionTable = workspace.fseTable;
            compressionTable.initialize(normalizedCounts, maxSymbol, tableLog);
            int compressedSize = FiniteStateEntropy.compress(outputBase, output, (int)(outputLimit - output), weights, weightsLength, compressionTable);
            if (compressedSize == 0) {
               return 0;
            } else {
               output += (long)compressedSize;
               return (int)(output - outputAddress);
            }
         }
      }
   }
}

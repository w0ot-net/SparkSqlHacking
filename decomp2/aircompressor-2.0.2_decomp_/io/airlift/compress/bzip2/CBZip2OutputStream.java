package io.airlift.compress.bzip2;

import java.io.IOException;
import java.io.OutputStream;

class CBZip2OutputStream extends OutputStream {
   private static final int MAX_BLOCK_SIZE = 9;
   private static final int[] R_NUMS = new int[]{619, 720, 127, 481, 931, 816, 813, 233, 566, 247, 985, 724, 205, 454, 863, 491, 741, 242, 949, 214, 733, 859, 335, 708, 621, 574, 73, 654, 730, 472, 419, 436, 278, 496, 867, 210, 399, 680, 480, 51, 878, 465, 811, 169, 869, 675, 611, 697, 867, 561, 862, 687, 507, 283, 482, 129, 807, 591, 733, 623, 150, 238, 59, 379, 684, 877, 625, 169, 643, 105, 170, 607, 520, 932, 727, 476, 693, 425, 174, 647, 73, 122, 335, 530, 442, 853, 695, 249, 445, 515, 909, 545, 703, 919, 874, 474, 882, 500, 594, 612, 641, 801, 220, 162, 819, 984, 589, 513, 495, 799, 161, 604, 958, 533, 221, 400, 386, 867, 600, 782, 382, 596, 414, 171, 516, 375, 682, 485, 911, 276, 98, 553, 163, 354, 666, 933, 424, 341, 533, 870, 227, 730, 475, 186, 263, 647, 537, 686, 600, 224, 469, 68, 770, 919, 190, 373, 294, 822, 808, 206, 184, 943, 795, 384, 383, 461, 404, 758, 839, 887, 715, 67, 618, 276, 204, 918, 873, 777, 604, 560, 951, 160, 578, 722, 79, 804, 96, 409, 713, 940, 652, 934, 970, 447, 318, 353, 859, 672, 112, 785, 645, 863, 803, 350, 139, 93, 354, 99, 820, 908, 609, 772, 154, 274, 580, 184, 79, 626, 630, 742, 653, 282, 762, 623, 680, 81, 927, 626, 789, 125, 411, 521, 938, 300, 821, 78, 343, 175, 128, 250, 170, 774, 972, 275, 999, 639, 495, 78, 352, 126, 857, 956, 358, 619, 580, 124, 737, 594, 701, 612, 669, 112, 134, 694, 363, 992, 809, 743, 168, 974, 944, 375, 748, 52, 600, 747, 642, 182, 862, 81, 344, 805, 988, 739, 511, 655, 814, 334, 249, 515, 897, 955, 664, 981, 649, 113, 974, 459, 893, 228, 433, 837, 553, 268, 926, 240, 102, 654, 459, 51, 686, 754, 806, 760, 493, 403, 415, 394, 687, 700, 946, 670, 656, 610, 738, 392, 760, 799, 887, 653, 978, 321, 576, 617, 626, 502, 894, 679, 243, 440, 680, 879, 194, 572, 640, 724, 926, 56, 204, 700, 707, 151, 457, 449, 797, 195, 791, 558, 945, 679, 297, 59, 87, 824, 713, 663, 412, 693, 342, 606, 134, 108, 571, 364, 631, 212, 174, 643, 304, 329, 343, 97, 430, 751, 497, 314, 983, 374, 822, 928, 140, 206, 73, 263, 980, 736, 876, 478, 430, 305, 170, 514, 364, 692, 829, 82, 855, 953, 676, 246, 369, 970, 294, 750, 807, 827, 150, 790, 288, 923, 804, 378, 215, 828, 592, 281, 565, 555, 710, 82, 896, 831, 547, 261, 524, 462, 293, 465, 502, 56, 661, 821, 976, 991, 658, 869, 905, 758, 745, 193, 768, 550, 608, 933, 378, 286, 215, 979, 792, 961, 61, 688, 793, 644, 986, 403, 106, 366, 905, 644, 372, 567, 466, 434, 645, 210, 389, 550, 919, 135, 780, 773, 635, 389, 707, 100, 626, 958, 165, 504, 920, 176, 193, 713, 857, 265, 203, 50, 668, 108, 645, 990, 626, 197, 510, 357, 358, 850, 858, 364, 936, 638};
   private static final int N_ITERS = 4;
   private static final int NUM_OVERSHOOT_BYTES = 20;
   private static final int SET_MASK = 2097152;
   private static final int CLEAR_MASK = -2097153;
   private static final int GREATER_ICOST = 15;
   private static final int LESSER_ICOST = 0;
   private static final int SMALL_THRESH = 20;
   private static final int DEPTH_THRESH = 10;
   private static final int WORK_FACTOR = 30;
   private static final int QSORT_STACK_SIZE = 1000;
   private static final int[] INCS = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
   private int last;
   private int origPtr;
   private final int blockSize100k;
   private boolean blockRandomised;
   private int bsBuff;
   private int bsLive;
   private final Crc32 crc32;
   private int nInUse;
   private int nMTF;
   private int workDone;
   private int workLimit;
   private boolean firstAttempt;
   private int currentChar;
   private int runLength;
   private int combinedCRC;
   private int allowableBlockSize;
   private Data data;
   private OutputStream out;

   private static void hbMakeCodeLengths(final byte[] len, final int[] freq, final Data dat, final int alphaSize, final int maxLen) {
      int[] heap = dat.heap;
      int[] weight = dat.weight;
      int[] parent = dat.parent;
      int i = alphaSize;

      while(true) {
         --i;
         if (i < 0) {
            i = 1;

            while(i) {
               i = 0;
               int nNodes = alphaSize;
               int nHeap = 0;
               heap[0] = 0;
               weight[0] = 0;
               parent[0] = -2;

               for(int i = 1; i <= alphaSize; ++i) {
                  parent[i] = -1;
                  ++nHeap;
                  heap[nHeap] = i;
                  int zz = nHeap;

                  int tmp;
                  for(tmp = heap[nHeap]; weight[tmp] < weight[heap[zz >> 1]]; zz >>= 1) {
                     heap[zz] = heap[zz >> 1];
                  }

                  heap[zz] = tmp;
               }

               while(nHeap > 1) {
                  int n1 = heap[1];
                  heap[1] = heap[nHeap];
                  --nHeap;
                  int zz = 1;
                  int tmp = heap[1];

                  while(true) {
                     int yy = zz << 1;
                     if (yy > nHeap) {
                        break;
                     }

                     if (yy < nHeap && weight[heap[yy + 1]] < weight[heap[yy]]) {
                        ++yy;
                     }

                     if (weight[tmp] < weight[heap[yy]]) {
                        break;
                     }

                     heap[zz] = heap[yy];
                     zz = yy;
                  }

                  heap[zz] = tmp;
                  int n2 = heap[1];
                  heap[1] = heap[nHeap];
                  --nHeap;
                  zz = 1;
                  tmp = heap[1];

                  while(true) {
                     int var26 = zz << 1;
                     if (var26 > nHeap) {
                        break;
                     }

                     if (var26 < nHeap && weight[heap[var26 + 1]] < weight[heap[var26]]) {
                        ++var26;
                     }

                     if (weight[tmp] < weight[heap[var26]]) {
                        break;
                     }

                     heap[zz] = heap[var26];
                     zz = var26;
                  }

                  heap[zz] = tmp;
                  ++nNodes;
                  parent[n1] = nNodes;
                  parent[n2] = nNodes;
                  int weightN1 = weight[n1];
                  int weightN2 = weight[n2];
                  weight[nNodes] = (weightN1 & -256) + (weightN2 & -256) | 1 + Math.max(weightN1 & 255, weightN2 & 255);
                  parent[nNodes] = -1;
                  ++nHeap;
                  heap[nHeap] = nNodes;
                  zz = nHeap;
                  tmp = heap[nHeap];

                  for(int weightTmp = weight[tmp]; weightTmp < weight[heap[zz >> 1]]; zz >>= 1) {
                     heap[zz] = heap[zz >> 1];
                  }

                  heap[zz] = tmp;
               }

               for(int i = 1; i <= alphaSize; ++i) {
                  int j = 0;

                  int parentK;
                  for(int k = i; (parentK = parent[k]) >= 0; ++j) {
                     k = parentK;
                  }

                  len[i - 1] = (byte)j;
                  if (j > maxLen) {
                     i = 1;
                  }
               }

               if (i) {
                  for(int i = 1; i < alphaSize; ++i) {
                     int j = weight[i] >> 8;
                     j = 1 + (j >> 1);
                     weight[i] = j << 8;
                  }
               }
            }

            return;
         }

         weight[i + 1] = (freq[i] == 0 ? 1 : freq[i]) << 8;
      }
   }

   public CBZip2OutputStream(final OutputStream out) throws IOException {
      this(out, 9);
   }

   private CBZip2OutputStream(final OutputStream out, final int blockSize) throws IOException {
      this.crc32 = new Crc32();
      this.currentChar = -1;
      if (blockSize < 1) {
         throw new IllegalArgumentException("blockSize(" + blockSize + ") < 1");
      } else if (blockSize > 9) {
         throw new IllegalArgumentException("blockSize(" + blockSize + ") > 9");
      } else {
         this.blockSize100k = blockSize;
         this.out = out;
         this.init();
      }
   }

   public void write(final int b) throws IOException {
      if (this.out != null) {
         this.write0(b);
      } else {
         throw new IOException("closed");
      }
   }

   private void writeRun() throws IOException {
      int lastShadow = this.last;
      if (lastShadow < this.allowableBlockSize) {
         int currentCharShadow = this.currentChar;
         Data dataShadow = this.data;
         dataShadow.inUse[currentCharShadow] = true;
         byte ch = (byte)currentCharShadow;
         int runLengthShadow = this.runLength;
         this.crc32.updateCRC(currentCharShadow, runLengthShadow);
         switch (runLengthShadow) {
            case 1:
               dataShadow.block[lastShadow + 2] = ch;
               this.last = lastShadow + 1;
               break;
            case 2:
               dataShadow.block[lastShadow + 2] = ch;
               dataShadow.block[lastShadow + 3] = ch;
               this.last = lastShadow + 2;
               break;
            case 3:
               byte[] block = dataShadow.block;
               block[lastShadow + 2] = ch;
               block[lastShadow + 3] = ch;
               block[lastShadow + 4] = ch;
               this.last = lastShadow + 3;
               break;
            default:
               runLengthShadow -= 4;
               dataShadow.inUse[runLengthShadow] = true;
               byte[] block = dataShadow.block;
               block[lastShadow + 2] = ch;
               block[lastShadow + 3] = ch;
               block[lastShadow + 4] = ch;
               block[lastShadow + 5] = ch;
               block[lastShadow + 6] = (byte)runLengthShadow;
               this.last = lastShadow + 5;
         }
      } else {
         this.endBlock();
         this.initBlock();
         this.writeRun();
      }

   }

   protected void finalize() throws Throwable {
      this.finish();
      super.finalize();
   }

   public void finish() throws IOException {
      if (this.out != null) {
         try {
            if (this.runLength > 0) {
               this.writeRun();
            }

            this.currentChar = -1;
            this.endBlock();
            this.endCompression();
         } finally {
            this.out = null;
            this.data = null;
         }
      }

   }

   public void close() throws IOException {
      if (this.out != null) {
         OutputStream outShadow = this.out;

         try {
            this.finish();
            outShadow.close();
            outShadow = null;
         } finally {
            outShadow.close();
         }
      }

   }

   public void flush() throws IOException {
      OutputStream outShadow = this.out;
      if (outShadow != null) {
         outShadow.flush();
      }

   }

   private void init() throws IOException {
      this.data = new Data(this.blockSize100k);
      this.bsPutUByte(104);
      this.bsPutUByte(48 + this.blockSize100k);
      this.combinedCRC = 0;
      this.initBlock();
   }

   private void initBlock() {
      this.crc32.initialiseCRC();
      this.last = -1;
      boolean[] inUse = this.data.inUse;
      int i = 256;

      while(true) {
         --i;
         if (i < 0) {
            this.allowableBlockSize = this.blockSize100k * 100000 - 20;
            return;
         }

         inUse[i] = false;
      }
   }

   private void endBlock() throws IOException {
      int blockCRC = this.crc32.getFinalCRC();
      this.combinedCRC = this.combinedCRC << 1 | this.combinedCRC >>> 31;
      this.combinedCRC ^= blockCRC;
      if (this.last != -1) {
         this.blockSort();
         this.bsPutUByte(49);
         this.bsPutUByte(65);
         this.bsPutUByte(89);
         this.bsPutUByte(38);
         this.bsPutUByte(83);
         this.bsPutUByte(89);
         this.bsPutInt(blockCRC);
         if (this.blockRandomised) {
            this.bsW(1, 1);
         } else {
            this.bsW(1, 0);
         }

         this.moveToFrontCodeAndSend();
      }
   }

   private void endCompression() throws IOException {
      this.bsPutUByte(23);
      this.bsPutUByte(114);
      this.bsPutUByte(69);
      this.bsPutUByte(56);
      this.bsPutUByte(80);
      this.bsPutUByte(144);
      this.bsPutInt(this.combinedCRC);
      this.bsFinishedWithStream();
   }

   public void write(final byte[] buf, int offs, final int len) throws IOException {
      if (offs < 0) {
         throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
      } else if (len < 0) {
         throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
      } else if (offs + len > buf.length) {
         throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > buf.length(" + buf.length + ").");
      } else if (this.out == null) {
         throw new IOException("stream closed");
      } else {
         int hi = offs + len;

         while(offs < hi) {
            this.write0(buf[offs++]);
         }

      }
   }

   private void write0(int b) throws IOException {
      if (this.currentChar != -1) {
         b &= 255;
         if (this.currentChar == b) {
            if (++this.runLength > 254) {
               this.writeRun();
               this.currentChar = -1;
               this.runLength = 0;
            }
         } else {
            this.writeRun();
            this.runLength = 1;
            this.currentChar = b;
         }
      } else {
         this.currentChar = b & 255;
         ++this.runLength;
      }

   }

   private static void hbAssignCodes(final int[] code, final byte[] length, final int minLen, final int maxLen, final int alphaSize) {
      int vec = 0;

      for(int n = minLen; n <= maxLen; ++n) {
         for(int i = 0; i < alphaSize; ++i) {
            if ((length[i] & 255) == n) {
               code[i] = vec++;
            }
         }

         vec <<= 1;
      }

   }

   private void bsFinishedWithStream() throws IOException {
      while(this.bsLive > 0) {
         int ch = this.bsBuff >> 24;
         this.out.write(ch);
         this.bsBuff <<= 8;
         this.bsLive -= 8;
      }

   }

   private void bsW(final int n, final int v) throws IOException {
      OutputStream outShadow = this.out;
      int bsLiveShadow = this.bsLive;

      int bsBuffShadow;
      for(bsBuffShadow = this.bsBuff; bsLiveShadow >= 8; bsLiveShadow -= 8) {
         outShadow.write(bsBuffShadow >> 24);
         bsBuffShadow <<= 8;
      }

      this.bsBuff = bsBuffShadow | v << 32 - bsLiveShadow - n;
      this.bsLive = bsLiveShadow + n;
   }

   private void bsPutUByte(final int c) throws IOException {
      this.bsW(8, c);
   }

   private void bsPutInt(final int u) throws IOException {
      this.bsW(8, u >> 24 & 255);
      this.bsW(8, u >> 16 & 255);
      this.bsW(8, u >> 8 & 255);
      this.bsW(8, u & 255);
   }

   private void sendMTFValues() throws IOException {
      byte[][] len = this.data.sendMTFValuesLen;
      int alphaSize = this.nInUse + 2;
      int t = 6;

      while(true) {
         --t;
         if (t < 0) {
            t = this.nMTF < 200 ? 2 : (this.nMTF < 600 ? 3 : (this.nMTF < 1200 ? 4 : (this.nMTF < 2400 ? 5 : 6)));
            this.sendMTFValues0(t, alphaSize);
            int nSelectors = this.sendMTFValues1(t, alphaSize);
            this.sendMTFValues2(t, nSelectors);
            this.sendMTFValues3(t, alphaSize);
            this.sendMTFValues4();
            this.sendMTFValues5(t, nSelectors);
            this.sendMTFValues6(t, alphaSize);
            this.sendMTFValues7();
            return;
         }

         byte[] lenT = len[t];
         int v = alphaSize;

         while(true) {
            --v;
            if (v < 0) {
               break;
            }

            lenT[v] = 15;
         }
      }
   }

   private void sendMTFValues0(final int nGroups, final int alphaSize) {
      byte[][] len = this.data.sendMTFValuesLen;
      int[] mtfFreq = this.data.mtfFreq;
      int remF = this.nMTF;
      int gs = 0;

      for(int nPart = nGroups; nPart > 0; --nPart) {
         int tFreq = remF / nPart;
         int ge = gs - 1;
         int aFreq = 0;

         for(int a = alphaSize - 1; aFreq < tFreq && ge < a; aFreq += mtfFreq[ge]) {
            ++ge;
         }

         if (ge > gs && nPart != nGroups && nPart != 1 && (nGroups - nPart & 1) != 0) {
            aFreq -= mtfFreq[ge--];
         }

         byte[] lenNp = len[nPart - 1];
         int v = alphaSize;

         while(true) {
            --v;
            if (v < 0) {
               gs = ge + 1;
               remF -= aFreq;
               break;
            }

            if (v >= gs && v <= ge) {
               lenNp[v] = 0;
            } else {
               lenNp[v] = 15;
            }
         }
      }

   }

   private int sendMTFValues1(final int nGroups, final int alphaSize) {
      Data dataShadow = this.data;
      int[][] rfreq = dataShadow.sendMTFValuesRfreq;
      int[] fave = dataShadow.sendMTFValuesFave;
      short[] cost = dataShadow.sendMTFValuesCost;
      char[] sfmap = dataShadow.sfmap;
      byte[] selector = dataShadow.selector;
      byte[][] len = dataShadow.sendMTFValuesLen;
      byte[] len0 = len[0];
      byte[] len1 = len[1];
      byte[] len2 = len[2];
      byte[] len3 = len[3];
      byte[] len4 = len[4];
      byte[] len5 = len[5];
      int nMTFShadow = this.nMTF;
      int nSelectors = 0;

      for(int iter = 0; iter < 4; ++iter) {
         int t = nGroups;

         while(true) {
            --t;
            if (t < 0) {
               nSelectors = 0;

               int ge;
               for(int gs = 0; gs < this.nMTF; gs = ge + 1) {
                  ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
                  if (nGroups == 6) {
                     short cost0 = 0;
                     short cost1 = 0;
                     short cost2 = 0;
                     short cost3 = 0;
                     short cost4 = 0;
                     short cost5 = 0;

                     for(int i = gs; i <= ge; ++i) {
                        int icv = sfmap[i];
                        cost0 = (short)(cost0 + (len0[icv] & 255));
                        cost1 = (short)(cost1 + (len1[icv] & 255));
                        cost2 = (short)(cost2 + (len2[icv] & 255));
                        cost3 = (short)(cost3 + (len3[icv] & 255));
                        cost4 = (short)(cost4 + (len4[icv] & 255));
                        cost5 = (short)(cost5 + (len5[icv] & 255));
                     }

                     cost[0] = cost0;
                     cost[1] = cost1;
                     cost[2] = cost2;
                     cost[3] = cost3;
                     cost[4] = cost4;
                     cost[5] = cost5;
                  } else {
                     int t = nGroups;

                     while(true) {
                        --t;
                        if (t < 0) {
                           for(int i = gs; i <= ge; ++i) {
                              int icv = sfmap[i];
                              int t = nGroups;

                              while(true) {
                                 --t;
                                 if (t < 0) {
                                    break;
                                 }

                                 cost[t] = (short)(cost[t] + (len[t][icv] & 255));
                              }
                           }
                           break;
                        }

                        cost[t] = 0;
                     }
                  }

                  int bt = -1;
                  int t = nGroups;
                  int bc = 999999999;

                  while(true) {
                     --t;
                     if (t < 0) {
                        int var10002 = fave[bt]++;
                        selector[nSelectors] = (byte)bt;
                        ++nSelectors;
                        int[] rfreqBt = rfreq[bt];

                        for(int i = gs; i <= ge; ++i) {
                           ++rfreqBt[sfmap[i]];
                        }
                        break;
                     }

                     int costT = cost[t];
                     if (costT < bc) {
                        bc = costT;
                        bt = t;
                     }
                  }
               }

               for(int t = 0; t < nGroups; ++t) {
                  hbMakeCodeLengths(len[t], rfreq[t], this.data, alphaSize, 20);
               }
               break;
            }

            fave[t] = 0;
            int[] rfreqt = rfreq[t];
            int i = alphaSize;

            while(true) {
               --i;
               if (i < 0) {
                  break;
               }

               rfreqt[i] = 0;
            }
         }
      }

      return nSelectors;
   }

   private void sendMTFValues2(final int nGroups, final int nSelectors) {
      Data dataShadow = this.data;
      byte[] pos = dataShadow.sendMTFValues2Pos;
      int i = nGroups;

      while(true) {
         --i;
         if (i < 0) {
            for(int i = 0; i < nSelectors; ++i) {
               byte llI = dataShadow.selector[i];
               byte tmp = pos[0];

               int j;
               byte tmp2;
               for(j = 0; llI != tmp; pos[j] = tmp2) {
                  ++j;
                  tmp2 = tmp;
                  tmp = pos[j];
               }

               pos[0] = tmp;
               dataShadow.selectorMtf[i] = (byte)j;
            }

            return;
         }

         pos[i] = (byte)i;
      }
   }

   private void sendMTFValues3(final int nGroups, final int alphaSize) {
      int[][] code = this.data.sendMTFValuesCode;
      byte[][] len = this.data.sendMTFValuesLen;

      for(int t = 0; t < nGroups; ++t) {
         int minLen = 32;
         int maxLen = 0;
         byte[] lenT = len[t];
         int i = alphaSize;

         while(true) {
            --i;
            if (i < 0) {
               hbAssignCodes(code[t], len[t], minLen, maxLen, alphaSize);
               break;
            }

            int l = lenT[i] & 255;
            if (l > maxLen) {
               maxLen = l;
            }

            if (l < minLen) {
               minLen = l;
            }
         }
      }

   }

   private void sendMTFValues4() throws IOException {
      boolean[] inUse = this.data.inUse;
      boolean[] inUse16 = this.data.sentMTFValues4InUse16;
      int i = 16;

      label66:
      while(true) {
         --i;
         if (i < 0) {
            for(int i = 0; i < 16; ++i) {
               this.bsW(1, inUse16[i] ? 1 : 0);
            }

            OutputStream outShadow = this.out;
            int bsLiveShadow = this.bsLive;
            int bsBuffShadow = this.bsBuff;

            for(int i = 0; i < 16; ++i) {
               if (inUse16[i]) {
                  int i16 = i * 16;

                  for(int j = 0; j < 16; ++j) {
                     while(bsLiveShadow >= 8) {
                        outShadow.write(bsBuffShadow >> 24);
                        bsBuffShadow <<= 8;
                        bsLiveShadow -= 8;
                     }

                     if (inUse[i16 + j]) {
                        bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
                     }

                     ++bsLiveShadow;
                  }
               }
            }

            this.bsBuff = bsBuffShadow;
            this.bsLive = bsLiveShadow;
            return;
         }

         inUse16[i] = false;
         int i16 = i * 16;
         int j = 16;

         do {
            --j;
            if (j < 0) {
               continue label66;
            }
         } while(!inUse[i16 + j]);

         inUse16[i] = true;
      }
   }

   private void sendMTFValues5(final int nGroups, final int nSelectors) throws IOException {
      this.bsW(3, nGroups);
      this.bsW(15, nSelectors);
      OutputStream outShadow = this.out;
      byte[] selectorMtf = this.data.selectorMtf;
      int bsLiveShadow = this.bsLive;
      int bsBuffShadow = this.bsBuff;

      for(int i = 0; i < nSelectors; ++i) {
         int j = 0;

         for(int hj = selectorMtf[i] & 255; j < hj; ++j) {
            while(bsLiveShadow >= 8) {
               outShadow.write(bsBuffShadow >> 24);
               bsBuffShadow <<= 8;
               bsLiveShadow -= 8;
            }

            bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
            ++bsLiveShadow;
         }

         while(bsLiveShadow >= 8) {
            outShadow.write(bsBuffShadow >> 24);
            bsBuffShadow <<= 8;
            bsLiveShadow -= 8;
         }

         ++bsLiveShadow;
      }

      this.bsBuff = bsBuffShadow;
      this.bsLive = bsLiveShadow;
   }

   private void sendMTFValues6(final int nGroups, final int alphaSize) throws IOException {
      byte[][] len = this.data.sendMTFValuesLen;
      OutputStream outShadow = this.out;
      int bsLiveShadow = this.bsLive;
      int bsBuffShadow = this.bsBuff;

      for(int t = 0; t < nGroups; ++t) {
         byte[] lenT = len[t];

         int curr;
         for(curr = lenT[0] & 255; bsLiveShadow >= 8; bsLiveShadow -= 8) {
            outShadow.write(bsBuffShadow >> 24);
            bsBuffShadow <<= 8;
         }

         bsBuffShadow |= curr << 32 - bsLiveShadow - 5;
         bsLiveShadow += 5;

         for(int i = 0; i < alphaSize; ++i) {
            int lti;
            for(lti = lenT[i] & 255; curr < lti; ++curr) {
               while(bsLiveShadow >= 8) {
                  outShadow.write(bsBuffShadow >> 24);
                  bsBuffShadow <<= 8;
                  bsLiveShadow -= 8;
               }

               bsBuffShadow |= 2 << 32 - bsLiveShadow - 2;
               bsLiveShadow += 2;
            }

            while(curr > lti) {
               while(bsLiveShadow >= 8) {
                  outShadow.write(bsBuffShadow >> 24);
                  bsBuffShadow <<= 8;
                  bsLiveShadow -= 8;
               }

               bsBuffShadow |= 3 << 32 - bsLiveShadow - 2;
               bsLiveShadow += 2;
               --curr;
            }

            while(bsLiveShadow >= 8) {
               outShadow.write(bsBuffShadow >> 24);
               bsBuffShadow <<= 8;
               bsLiveShadow -= 8;
            }

            ++bsLiveShadow;
         }
      }

      this.bsBuff = bsBuffShadow;
      this.bsLive = bsLiveShadow;
   }

   private void sendMTFValues7() throws IOException {
      Data dataShadow = this.data;
      byte[][] len = dataShadow.sendMTFValuesLen;
      int[][] code = dataShadow.sendMTFValuesCode;
      OutputStream outShadow = this.out;
      byte[] selector = dataShadow.selector;
      char[] sfmap = dataShadow.sfmap;
      int nMTFShadow = this.nMTF;
      int selCtr = 0;
      int bsLiveShadow = this.bsLive;
      int bsBuffShadow = this.bsBuff;

      for(int gs = 0; gs < nMTFShadow; ++selCtr) {
         int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
         int selectorSelCtr = selector[selCtr] & 255;
         int[] codeSelCtr = code[selectorSelCtr];

         for(byte[] lenSelCtr = len[selectorSelCtr]; gs <= ge; ++gs) {
            int sfmapI;
            for(sfmapI = sfmap[gs]; bsLiveShadow >= 8; bsLiveShadow -= 8) {
               outShadow.write(bsBuffShadow >> 24);
               bsBuffShadow <<= 8;
            }

            int n = lenSelCtr[sfmapI] & 255;
            bsBuffShadow |= codeSelCtr[sfmapI] << 32 - bsLiveShadow - n;
            bsLiveShadow += n;
         }

         gs = ge + 1;
      }

      this.bsBuff = bsBuffShadow;
      this.bsLive = bsLiveShadow;
   }

   private void moveToFrontCodeAndSend() throws IOException {
      this.bsW(24, this.origPtr);
      this.generateMTFValues();
      this.sendMTFValues();
   }

   private boolean mainSimpleSort(final Data dataShadow, final int lo, final int hi, final int d) {
      int bigN = hi - lo + 1;
      if (bigN < 2) {
         return this.firstAttempt && this.workDone > this.workLimit;
      } else {
         int hp;
         for(hp = 0; INCS[hp] < bigN; ++hp) {
         }

         int[] fmap = dataShadow.fmap;
         char[] quadrant = dataShadow.quadrant;
         byte[] block = dataShadow.block;
         int lastShadow = this.last;
         int lastPlus1 = lastShadow + 1;
         boolean firstAttemptShadow = this.firstAttempt;
         int workLimitShadow = this.workLimit;
         int workDoneShadow = this.workDone;

         label193:
         while(true) {
            --hp;
            if (hp < 0) {
               break;
            }

            int h = INCS[hp];
            int mj = lo + h - 1;
            int i = lo + h;

            while(i <= hi) {
               for(int k = 3; i <= hi; ++i) {
                  --k;
                  if (k < 0) {
                     break;
                  }

                  int v = fmap[i];
                  int vd = v + d;
                  int j = i;
                  boolean onceRunned = false;
                  int a = 0;

                  label185:
                  while(true) {
                     if (onceRunned) {
                        fmap[j] = a;
                        if ((j -= h) <= mj) {
                           break;
                        }
                     } else {
                        onceRunned = true;
                     }

                     a = fmap[j - h];
                     int i1 = a + d;
                     if (block[i1 + 1] == block[vd + 1]) {
                        if (block[i1 + 2] == block[vd + 2]) {
                           if (block[i1 + 3] == block[vd + 3]) {
                              if (block[i1 + 4] == block[vd + 4]) {
                                 if (block[i1 + 5] == block[vd + 5]) {
                                    i1 += 6;
                                    byte var10000 = block[i1];
                                    int i2 = vd + 6;
                                    if (var10000 == block[i2]) {
                                       int x = lastShadow;

                                       while(true) {
                                          if (x <= 0) {
                                             break label185;
                                          }

                                          x -= 4;
                                          if (block[i1 + 1] != block[i2 + 1]) {
                                             if ((block[i1 + 1] & 255) <= (block[i2 + 1] & 255)) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (quadrant[i1] != quadrant[i2]) {
                                             if (quadrant[i1] <= quadrant[i2]) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (block[i1 + 2] != block[i2 + 2]) {
                                             if ((block[i1 + 2] & 255) <= (block[i2 + 2] & 255)) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (quadrant[i1 + 1] != quadrant[i2 + 1]) {
                                             if (quadrant[i1 + 1] <= quadrant[i2 + 1]) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (block[i1 + 3] != block[i2 + 3]) {
                                             if ((block[i1 + 3] & 255) <= (block[i2 + 3] & 255)) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (quadrant[i1 + 2] != quadrant[i2 + 2]) {
                                             if (quadrant[i1 + 2] <= quadrant[i2 + 2]) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (block[i1 + 4] != block[i2 + 4]) {
                                             if ((block[i1 + 4] & 255) <= (block[i2 + 4] & 255)) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          if (quadrant[i1 + 3] != quadrant[i2 + 3]) {
                                             if (quadrant[i1 + 3] <= quadrant[i2 + 3]) {
                                                break label185;
                                             }
                                             break;
                                          }

                                          i1 += 4;
                                          if (i1 >= lastPlus1) {
                                             i1 -= lastPlus1;
                                          }

                                          i2 += 4;
                                          if (i2 >= lastPlus1) {
                                             i2 -= lastPlus1;
                                          }

                                          ++workDoneShadow;
                                       }
                                    } else if ((block[i1] & 255) <= (block[i2] & 255)) {
                                       break;
                                    }
                                 } else if ((block[i1 + 5] & 255) <= (block[vd + 5] & 255)) {
                                    break;
                                 }
                              } else if ((block[i1 + 4] & 255) <= (block[vd + 4] & 255)) {
                                 break;
                              }
                           } else if ((block[i1 + 3] & 255) <= (block[vd + 3] & 255)) {
                              break;
                           }
                        } else if ((block[i1 + 2] & 255) <= (block[vd + 2] & 255)) {
                           break;
                        }
                     } else if ((block[i1 + 1] & 255) <= (block[vd + 1] & 255)) {
                        break;
                     }
                  }

                  fmap[j] = v;
               }

               if (firstAttemptShadow && i <= hi && workDoneShadow > workLimitShadow) {
                  break label193;
               }
            }
         }

         this.workDone = workDoneShadow;
         return firstAttemptShadow && workDoneShadow > workLimitShadow;
      }
   }

   private static void vswap(int[] fmap, int p1, int p2, int n) {
      int t;
      for(int var5 = n + p1; p1 < var5; fmap[p2++] = t) {
         t = fmap[p1];
         fmap[p1++] = fmap[p2];
      }

   }

   private static byte med3(byte a, byte b, byte c) {
      return a < b ? (b < c ? b : (a < c ? c : a)) : (b > c ? b : (a > c ? c : a));
   }

   private void blockSort() {
      this.workLimit = 30 * this.last;
      this.workDone = 0;
      this.blockRandomised = false;
      this.firstAttempt = true;
      this.mainSort();
      if (this.firstAttempt && this.workDone > this.workLimit) {
         this.randomiseBlock();
         this.workLimit = 0;
         this.workDone = 0;
         this.firstAttempt = false;
         this.mainSort();
      }

      int[] fmap = this.data.fmap;
      this.origPtr = -1;
      int i = 0;

      for(int lastShadow = this.last; i <= lastShadow; ++i) {
         if (fmap[i] == 0) {
            this.origPtr = i;
            break;
         }
      }

   }

   private void mainQSort3(final Data dataShadow, final int loSt, final int hiSt, final int dSt) {
      int[] stackLl = dataShadow.stackLl;
      int[] stackHh = dataShadow.stackHh;
      int[] stackDd = dataShadow.stackDd;
      int[] fmap = dataShadow.fmap;
      byte[] block = dataShadow.block;
      stackLl[0] = loSt;
      stackHh[0] = hiSt;
      stackDd[0] = dSt;
      int sp = 1;

      while(true) {
         --sp;
         if (sp < 0) {
            return;
         }

         int lo = stackLl[sp];
         int hi = stackHh[sp];
         int d = stackDd[sp];
         if (hi - lo >= 20 && d <= 10) {
            int d1 = d + 1;
            int med = med3(block[fmap[lo] + d1], block[fmap[hi] + d1], block[fmap[lo + hi >>> 1] + d1]) & 255;
            int unLo = lo;
            int unHi = hi;
            int ltLo = lo;
            int gtHi = hi;

            while(true) {
               if (unLo <= unHi) {
                  int n = (block[fmap[unLo] + d1] & 255) - med;
                  if (n == 0) {
                     int temp = fmap[unLo];
                     fmap[unLo++] = fmap[ltLo];
                     fmap[ltLo++] = temp;
                     continue;
                  }

                  if (n < 0) {
                     ++unLo;
                     continue;
                  }
               }

               while(unLo <= unHi) {
                  int n = (block[fmap[unHi] + d1] & 255) - med;
                  if (n == 0) {
                     int temp = fmap[unHi];
                     fmap[unHi--] = fmap[gtHi];
                     fmap[gtHi--] = temp;
                  } else {
                     if (n <= 0) {
                        break;
                     }

                     --unHi;
                  }
               }

               if (unLo > unHi) {
                  if (gtHi < ltLo) {
                     stackLl[sp] = lo;
                     stackHh[sp] = hi;
                     stackDd[sp] = d1;
                     ++sp;
                  } else {
                     int n = Math.min(ltLo - lo, unLo - ltLo);
                     vswap(fmap, lo, unLo - n, n);
                     int m = Math.min(hi - gtHi, gtHi - unHi);
                     vswap(fmap, unLo, hi - m + 1, m);
                     n = lo + unLo - ltLo - 1;
                     m = hi - (gtHi - unHi) + 1;
                     stackLl[sp] = lo;
                     stackHh[sp] = n;
                     stackDd[sp] = d;
                     ++sp;
                     stackLl[sp] = n + 1;
                     stackHh[sp] = m - 1;
                     stackDd[sp] = d1;
                     ++sp;
                     stackLl[sp] = m;
                     stackHh[sp] = hi;
                     stackDd[sp] = d;
                     ++sp;
                  }
                  break;
               }

               int temp = fmap[unLo];
               fmap[unLo++] = fmap[unHi];
               fmap[unHi--] = temp;
            }
         } else if (this.mainSimpleSort(dataShadow, lo, hi, d)) {
            return;
         }
      }
   }

   private void mainSort() {
      Data dataShadow = this.data;
      int[] runningOrder = dataShadow.mainSortRunningOrder;
      int[] copy = dataShadow.mainSortCopy;
      boolean[] bigDone = dataShadow.mainSortBigDone;
      int[] ftab = dataShadow.ftab;
      byte[] block = dataShadow.block;
      int[] fmap = dataShadow.fmap;
      char[] quadrant = dataShadow.quadrant;
      int lastShadow = this.last;
      int workLimitShadow = this.workLimit;
      boolean firstAttemptShadow = this.firstAttempt;
      int i = 65537;

      while(true) {
         --i;
         if (i < 0) {
            for(int i = 0; i < 20; ++i) {
               block[lastShadow + i + 2] = block[i % (lastShadow + 1) + 1];
            }

            i = lastShadow + 20 + 1;

            while(true) {
               --i;
               if (i < 0) {
                  block[0] = block[lastShadow + 1];
                  i = block[0] & 255;

                  for(int i = 0; i <= lastShadow; ++i) {
                     int c2 = block[i + 1] & 255;
                     ++ftab[(i << 8) + c2];
                     i = c2;
                  }

                  for(int i = 1; i <= 65536; ++i) {
                     ftab[i] += ftab[i - 1];
                  }

                  i = block[1] & 255;

                  for(int i = 0; i < lastShadow; ++i) {
                     int c2 = block[i + 2] & 255;
                     fmap[--ftab[(i << 8) + c2]] = i;
                     i = c2;
                  }

                  fmap[--ftab[((block[lastShadow + 1] & 255) << 8) + (block[1] & 255)]] = lastShadow;
                  int i = 256;

                  while(true) {
                     --i;
                     if (i < 0) {
                        i = 364;

                        while(i != 1) {
                           i /= 3;

                           for(int i = i; i <= 255; ++i) {
                              int vv = runningOrder[i];
                              int a = ftab[vv + 1 << 8] - ftab[vv << 8];
                              int b = i - 1;
                              int j = i;

                              for(int ro = runningOrder[i - i]; ftab[ro + 1 << 8] - ftab[ro << 8] > a; ro = runningOrder[j - i]) {
                                 runningOrder[j] = ro;
                                 j -= i;
                                 if (j <= b) {
                                    break;
                                 }
                              }

                              runningOrder[j] = vv;
                           }
                        }

                        for(int i = 0; i <= 255; ++i) {
                           int ss = runningOrder[i];

                           for(int j = 0; j <= 255; ++j) {
                              int sb = (ss << 8) + j;
                              int ftabSb = ftab[sb];
                              if ((ftabSb & 2097152) != 2097152) {
                                 int lo = ftabSb & -2097153;
                                 int hi = (ftab[sb + 1] & -2097153) - 1;
                                 if (hi > lo) {
                                    this.mainQSort3(dataShadow, lo, hi, 2);
                                    if (firstAttemptShadow && this.workDone > workLimitShadow) {
                                       return;
                                    }
                                 }

                                 ftab[sb] = ftabSb | 2097152;
                              }
                           }

                           for(int j = 0; j <= 255; ++j) {
                              copy[j] = ftab[(j << 8) + ss] & -2097153;
                           }

                           int j = ftab[ss << 8] & -2097153;

                           for(int hj = ftab[ss + 1 << 8] & -2097153; j < hj; ++j) {
                              int fmapJ = fmap[j];
                              i = block[fmapJ] & 255;
                              if (!bigDone[i]) {
                                 fmap[copy[i]] = fmapJ == 0 ? lastShadow : fmapJ - 1;
                                 int var10002 = copy[i]++;
                              }
                           }

                           j = 256;

                           while(true) {
                              --j;
                              if (j < 0) {
                                 bigDone[ss] = true;
                                 if (i < 255) {
                                    j = ftab[ss << 8] & -2097153;
                                    int bbSize = (ftab[ss + 1 << 8] & -2097153) - j;

                                    int shifts;
                                    for(shifts = 0; bbSize >> shifts > 65534; ++shifts) {
                                    }

                                    for(int j = 0; j < bbSize; ++j) {
                                       int a2update = fmap[j + j];
                                       char qVal = (char)(j >> shifts);
                                       quadrant[a2update] = qVal;
                                       if (a2update < 20) {
                                          quadrant[a2update + lastShadow + 1] = qVal;
                                       }
                                    }
                                 }
                                 break;
                              }

                              ftab[(j << 8) + ss] |= 2097152;
                           }
                        }

                        return;
                     }

                     bigDone[i] = false;
                     runningOrder[i] = i;
                  }
               }

               quadrant[i] = 0;
            }
         }

         ftab[i] = 0;
      }
   }

   private void randomiseBlock() {
      boolean[] inUse = this.data.inUse;
      byte[] block = this.data.block;
      int lastShadow = this.last;
      int i = 256;

      while(true) {
         --i;
         if (i < 0) {
            i = 0;
            int rTPos = 0;
            int i = 0;

            for(int j = 1; i <= lastShadow; i = j++) {
               if (i == 0) {
                  i = (char)R_NUMS[rTPos];
                  ++rTPos;
                  if (rTPos == 512) {
                     rTPos = 0;
                  }
               }

               --i;
               block[j] = (byte)(block[j] ^ (i == 1 ? 1 : 0));
               inUse[block[j] & 255] = true;
            }

            this.blockRandomised = true;
            return;
         }

         inUse[i] = false;
      }
   }

   private void generateMTFValues() {
      int lastShadow = this.last;
      Data dataShadow = this.data;
      boolean[] inUse = dataShadow.inUse;
      byte[] block = dataShadow.block;
      int[] fmap = dataShadow.fmap;
      char[] sfmap = dataShadow.sfmap;
      int[] mtfFreq = dataShadow.mtfFreq;
      byte[] unseqToSeq = dataShadow.unseqToSeq;
      byte[] yy = dataShadow.generateMTFValuesYy;
      int nInUseShadow = 0;

      for(int i = 0; i < 256; ++i) {
         if (inUse[i]) {
            unseqToSeq[i] = (byte)nInUseShadow;
            ++nInUseShadow;
         }
      }

      this.nInUse = nInUseShadow;
      int eob = nInUseShadow + 1;

      for(int i = eob; i >= 0; --i) {
         mtfFreq[i] = 0;
      }

      int i = nInUseShadow;

      while(true) {
         --i;
         if (i < 0) {
            i = 0;
            int zPend = 0;

            for(int i = 0; i <= lastShadow; ++i) {
               byte llI = unseqToSeq[block[fmap[i]] & 255];
               byte tmp = yy[0];

               int j;
               byte tmp2;
               for(j = 0; llI != tmp; yy[j] = tmp2) {
                  ++j;
                  tmp2 = tmp;
                  tmp = yy[j];
               }

               yy[0] = tmp;
               if (j == 0) {
                  ++zPend;
               } else {
                  if (zPend > 0) {
                     --zPend;

                     while(true) {
                        if ((zPend & 1) == 0) {
                           sfmap[i] = 0;
                           ++i;
                           int var10002 = mtfFreq[0]++;
                        } else {
                           sfmap[i] = 1;
                           ++i;
                           int var24 = mtfFreq[1]++;
                        }

                        if (zPend < 2) {
                           zPend = 0;
                           break;
                        }

                        zPend = zPend - 2 >> 1;
                     }
                  }

                  sfmap[i] = (char)(j + 1);
                  ++i;
                  ++mtfFreq[j + 1];
               }
            }

            if (zPend > 0) {
               --zPend;

               while(true) {
                  if ((zPend & 1) == 0) {
                     sfmap[i] = 0;
                     ++i;
                     int var25 = mtfFreq[0]++;
                  } else {
                     sfmap[i] = 1;
                     ++i;
                     int var26 = mtfFreq[1]++;
                  }

                  if (zPend < 2) {
                     break;
                  }

                  zPend = zPend - 2 >> 1;
               }
            }

            sfmap[i] = (char)eob;
            int var27 = mtfFreq[eob]++;
            this.nMTF = i + 1;
            return;
         }

         yy[i] = (byte)i;
      }
   }

   private static final class Data {
      final boolean[] inUse = new boolean[256];
      final byte[] unseqToSeq = new byte[256];
      final int[] mtfFreq = new int[258];
      final byte[] selector = new byte[18002];
      final byte[] selectorMtf = new byte[18002];
      final byte[] generateMTFValuesYy = new byte[256];
      final byte[][] sendMTFValuesLen = new byte[6][258];
      final int[][] sendMTFValuesRfreq = new int[6][258];
      final int[] sendMTFValuesFave = new int[6];
      final short[] sendMTFValuesCost = new short[6];
      final int[][] sendMTFValuesCode = new int[6][258];
      final byte[] sendMTFValues2Pos = new byte[6];
      final boolean[] sentMTFValues4InUse16 = new boolean[16];
      final int[] stackLl = new int[1000];
      final int[] stackHh = new int[1000];
      final int[] stackDd = new int[1000];
      final int[] mainSortRunningOrder = new int[256];
      final int[] mainSortCopy = new int[256];
      final boolean[] mainSortBigDone = new boolean[256];
      final int[] heap = new int[260];
      final int[] weight = new int[516];
      final int[] parent = new int[516];
      final int[] ftab = new int[65537];
      final byte[] block;
      final int[] fmap;
      final char[] sfmap;
      final char[] quadrant;

      Data(int blockSize100k) {
         int n = blockSize100k * 100000;
         this.block = new byte[n + 1 + 20];
         this.fmap = new int[n];
         this.sfmap = new char[2 * n];
         this.quadrant = this.sfmap;
      }
   }
}

package io.airlift.compress.bzip2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

class CBZip2InputStream extends InputStream {
   private static final long BLOCK_DELIMITER = 54156738319193L;
   private static final int MAX_CODE_LEN = 23;
   public static final int END_OF_BLOCK = -2;
   private static final int END_OF_STREAM = -1;
   private static final int DELIMITER_BIT_LENGTH = 48;
   private long reportedBytesReadFromCompressedStream;
   private long bytesReadFromCompressedStream;
   private boolean initialized;
   private final byte[] array = new byte[1];
   private int last;
   private int origPtr;
   private int blockSize100k;
   private boolean blockRandomised;
   private long bsBuff;
   private long bsLive;
   private final Crc32 crc32 = new Crc32();
   private int nInUse;
   private BufferedInputStream in;
   private int currentChar = -1;
   private STATE currentState;
   private int storedBlockCRC;
   private int storedCombinedCRC;
   private int computedCombinedCRC;
   private boolean skipResult;
   private int suCount;
   private int suCh2;
   private int suChPrev;
   private int suI2;
   private int suJ2;
   private int suRNToGo;
   private int suRTPos;
   private int suTPos;
   private char suZ;
   private Data data;
   private static final int[] R_NUMS = new int[]{619, 720, 127, 481, 931, 816, 813, 233, 566, 247, 985, 724, 205, 454, 863, 491, 741, 242, 949, 214, 733, 859, 335, 708, 621, 574, 73, 654, 730, 472, 419, 436, 278, 496, 867, 210, 399, 680, 480, 51, 878, 465, 811, 169, 869, 675, 611, 697, 867, 561, 862, 687, 507, 283, 482, 129, 807, 591, 733, 623, 150, 238, 59, 379, 684, 877, 625, 169, 643, 105, 170, 607, 520, 932, 727, 476, 693, 425, 174, 647, 73, 122, 335, 530, 442, 853, 695, 249, 445, 515, 909, 545, 703, 919, 874, 474, 882, 500, 594, 612, 641, 801, 220, 162, 819, 984, 589, 513, 495, 799, 161, 604, 958, 533, 221, 400, 386, 867, 600, 782, 382, 596, 414, 171, 516, 375, 682, 485, 911, 276, 98, 553, 163, 354, 666, 933, 424, 341, 533, 870, 227, 730, 475, 186, 263, 647, 537, 686, 600, 224, 469, 68, 770, 919, 190, 373, 294, 822, 808, 206, 184, 943, 795, 384, 383, 461, 404, 758, 839, 887, 715, 67, 618, 276, 204, 918, 873, 777, 604, 560, 951, 160, 578, 722, 79, 804, 96, 409, 713, 940, 652, 934, 970, 447, 318, 353, 859, 672, 112, 785, 645, 863, 803, 350, 139, 93, 354, 99, 820, 908, 609, 772, 154, 274, 580, 184, 79, 626, 630, 742, 653, 282, 762, 623, 680, 81, 927, 626, 789, 125, 411, 521, 938, 300, 821, 78, 343, 175, 128, 250, 170, 774, 972, 275, 999, 639, 495, 78, 352, 126, 857, 956, 358, 619, 580, 124, 737, 594, 701, 612, 669, 112, 134, 694, 363, 992, 809, 743, 168, 974, 944, 375, 748, 52, 600, 747, 642, 182, 862, 81, 344, 805, 988, 739, 511, 655, 814, 334, 249, 515, 897, 955, 664, 981, 649, 113, 974, 459, 893, 228, 433, 837, 553, 268, 926, 240, 102, 654, 459, 51, 686, 754, 806, 760, 493, 403, 415, 394, 687, 700, 946, 670, 656, 610, 738, 392, 760, 799, 887, 653, 978, 321, 576, 617, 626, 502, 894, 679, 243, 440, 680, 879, 194, 572, 640, 724, 926, 56, 204, 700, 707, 151, 457, 449, 797, 195, 791, 558, 945, 679, 297, 59, 87, 824, 713, 663, 412, 693, 342, 606, 134, 108, 571, 364, 631, 212, 174, 643, 304, 329, 343, 97, 430, 751, 497, 314, 983, 374, 822, 928, 140, 206, 73, 263, 980, 736, 876, 478, 430, 305, 170, 514, 364, 692, 829, 82, 855, 953, 676, 246, 369, 970, 294, 750, 807, 827, 150, 790, 288, 923, 804, 378, 215, 828, 592, 281, 565, 555, 710, 82, 896, 831, 547, 261, 524, 462, 293, 465, 502, 56, 661, 821, 976, 991, 658, 869, 905, 758, 745, 193, 768, 550, 608, 933, 378, 286, 215, 979, 792, 961, 61, 688, 793, 644, 986, 403, 106, 366, 905, 644, 372, 567, 466, 434, 645, 210, 389, 550, 919, 135, 780, 773, 635, 389, 707, 100, 626, 958, 165, 504, 920, 176, 193, 713, 857, 265, 203, 50, 668, 108, 645, 990, 626, 197, 510, 357, 358, 850, 858, 364, 936, 638};

   public CBZip2InputStream(final InputStream in) {
      this.currentState = CBZip2InputStream.STATE.START_BLOCK_STATE;
      int blockSize = 57;
      this.blockSize100k = blockSize - 48;
      this.in = new BufferedInputStream(in, 9216);
   }

   public long getProcessedByteCount() {
      return this.reportedBytesReadFromCompressedStream;
   }

   private void updateProcessedByteCount(int count) {
      this.bytesReadFromCompressedStream += (long)count;
   }

   private int readAByte(InputStream inStream) throws IOException {
      int read = inStream.read();
      if (read >= 0) {
         this.updateProcessedByteCount(1);
      }

      return read;
   }

   private boolean skipToNextMarker(long marker, int markerBitLength) throws IllegalArgumentException {
      try {
         if (markerBitLength > 63) {
            throw new IllegalArgumentException("skipToNextMarker can not find patterns greater than 63 bits");
         } else {
            long bytes = this.bsR((long)markerBitLength);
            if (bytes == -1L) {
               this.reportedBytesReadFromCompressedStream = this.bytesReadFromCompressedStream;
               return false;
            } else {
               while(bytes != marker) {
                  bytes <<= 1;
                  bytes &= (1L << markerBitLength) - 1L;
                  int oneBit = (int)this.bsR(1L);
                  if (oneBit == -1) {
                     this.reportedBytesReadFromCompressedStream = this.bytesReadFromCompressedStream;
                     return false;
                  }

                  bytes |= (long)oneBit;
               }

               long markerBytesRead = ((long)markerBitLength + this.bsLive + 7L) / 8L;
               this.reportedBytesReadFromCompressedStream = this.bytesReadFromCompressedStream - markerBytesRead;
               return true;
            }
         }
      } catch (IOException var8) {
         this.reportedBytesReadFromCompressedStream = this.bytesReadFromCompressedStream;
         return false;
      }
   }

   private void makeMaps() {
      boolean[] inUse = this.data.inUse;
      byte[] seqToUnseq = this.data.seqToUnseq;
      int nInUseShadow = 0;

      for(int i = 0; i < 256; ++i) {
         if (inUse[i]) {
            seqToUnseq[nInUseShadow++] = (byte)i;
         }
      }

      this.nInUse = nInUseShadow;
   }

   private void changeStateToProcessABlock() throws IOException {
      if (this.skipResult) {
         this.initBlock();
         this.setupBlock();
      } else {
         this.currentState = CBZip2InputStream.STATE.EOF;
      }

   }

   public int read() throws IOException {
      if (this.in != null) {
         int result = this.read(this.array, 0, 1);
         int value = 255 & this.array[0];
         return result > 0 ? value : result;
      } else {
         throw new IOException("stream closed");
      }
   }

   public int read(final byte[] dest, final int offs, final int len) throws IOException {
      if (offs < 0) {
         throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
      } else if (len < 0) {
         throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
      } else if (offs + len > dest.length) {
         throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > dest.length(" + dest.length + ").");
      } else if (this.in == null) {
         throw new IOException("stream closed");
      } else {
         if (!this.initialized) {
            this.init();
            this.initialized = true;
         }

         int hi = offs + len;
         int destOffs = offs;

         int b;
         for(b = 0; destOffs < hi && (b = this.read0()) >= 0; dest[destOffs++] = (byte)b) {
         }

         int result = destOffs - offs;
         if (result == 0) {
            result = b;
            this.skipResult = this.skipToNextMarker(54156738319193L, 48);
            this.changeStateToProcessABlock();
         }

         return result;
      }
   }

   private int read0() throws IOException {
      int retChar = this.currentChar;
      switch (this.currentState) {
         case EOF:
            return -1;
         case NO_PROCESS_STATE:
            return -2;
         case START_BLOCK_STATE:
            throw new IllegalStateException();
         case RAND_PART_A_STATE:
            throw new IllegalStateException();
         case RAND_PART_B_STATE:
            this.setupRandPartB();
            break;
         case RAND_PART_C_STATE:
            this.setupRandPartC();
            break;
         case NO_RAND_PART_A_STATE:
            throw new IllegalStateException();
         case NO_RAND_PART_B_STATE:
            this.setupNoRandPartB();
            break;
         case NO_RAND_PART_C_STATE:
            this.setupNoRandPartC();
            break;
         default:
            throw new IllegalStateException();
      }

      return retChar;
   }

   private void init() throws IOException {
      int magic2 = this.readAByte(this.in);
      if (magic2 != 104) {
         throw new IOException("Stream is not BZip2 formatted: expected 'h' as first byte but got '" + (char)magic2 + "'");
      } else {
         int blockSize = this.readAByte(this.in);
         if (blockSize >= 49 && blockSize <= 57) {
            this.blockSize100k = blockSize - 48;
            this.initBlock();
            this.setupBlock();
         } else {
            throw new IOException("Stream is not BZip2 formatted: illegal blocksize " + (char)blockSize);
         }
      }
   }

   private void initBlock() throws IOException {
      char magic0 = this.bsGetUByte();
      char magic1 = this.bsGetUByte();
      char magic2 = this.bsGetUByte();
      char magic3 = this.bsGetUByte();
      char magic4 = this.bsGetUByte();
      char magic5 = this.bsGetUByte();
      if (magic0 == 23 && magic1 == 'r' && magic2 == 'E' && magic3 == '8' && magic4 == 'P' && magic5 == 144) {
         this.complete();
      } else {
         if (magic0 != '1' || magic1 != 'A' || magic2 != 'Y' || magic3 != '&' || magic4 != 'S' || magic5 != 'Y') {
            this.currentState = CBZip2InputStream.STATE.EOF;
            throw new IOException("bad block header");
         }

         this.storedBlockCRC = this.bsGetInt();
         this.blockRandomised = this.bsR(1L) == 1L;
         if (this.data == null) {
            this.data = new Data(this.blockSize100k);
         }

         this.getAndMoveToFrontDecode();
         this.crc32.initialiseCRC();
         this.currentState = CBZip2InputStream.STATE.START_BLOCK_STATE;
      }

   }

   private void endBlock() throws IOException {
      int computedBlockCRC = this.crc32.getFinalCRC();
      if (this.storedBlockCRC != computedBlockCRC) {
         this.computedCombinedCRC = this.storedCombinedCRC << 1 | this.storedCombinedCRC >>> 31;
         this.computedCombinedCRC ^= this.storedBlockCRC;
         throw new IOException("crc error");
      } else {
         this.computedCombinedCRC = this.computedCombinedCRC << 1 | this.computedCombinedCRC >>> 31;
         this.computedCombinedCRC ^= computedBlockCRC;
      }
   }

   private void complete() throws IOException {
      this.storedCombinedCRC = this.bsGetInt();
      this.currentState = CBZip2InputStream.STATE.EOF;
      this.data = null;
      if (this.storedCombinedCRC != this.computedCombinedCRC) {
         throw new IOException("crc error");
      }
   }

   public void close() throws IOException {
      InputStream inShadow = this.in;
      if (inShadow != null) {
         try {
            if (inShadow != System.in) {
               inShadow.close();
            }
         } finally {
            this.data = null;
            this.in = null;
         }
      }

   }

   private long bsR(final long n) throws IOException {
      long bsLiveShadow = this.bsLive;
      long bsBuffShadow = this.bsBuff;
      if (bsLiveShadow < n) {
         InputStream inShadow = this.in;

         do {
            int thech = this.readAByte(inShadow);
            if (thech < 0) {
               throw new IOException("unexpected end of stream");
            }

            bsBuffShadow = bsBuffShadow << 8 | (long)thech;
            bsLiveShadow += 8L;
         } while(bsLiveShadow < n);

         this.bsBuff = bsBuffShadow;
      }

      this.bsLive = bsLiveShadow - n;
      return bsBuffShadow >> (int)(bsLiveShadow - n) & (1L << (int)n) - 1L;
   }

   private boolean bsGetBit() throws IOException {
      long bsLiveShadow = this.bsLive;
      long bsBuffShadow = this.bsBuff;
      if (bsLiveShadow < 1L) {
         int thech = this.readAByte(this.in);
         if (thech < 0) {
            throw new IOException("unexpected end of stream");
         }

         bsBuffShadow = bsBuffShadow << 8 | (long)thech;
         bsLiveShadow += 8L;
         this.bsBuff = bsBuffShadow;
      }

      this.bsLive = bsLiveShadow - 1L;
      return (bsBuffShadow >> (int)(bsLiveShadow - 1L) & 1L) != 0L;
   }

   private char bsGetUByte() throws IOException {
      return (char)((int)this.bsR(8L));
   }

   private int bsGetInt() throws IOException {
      return (int)(((this.bsR(8L) << 8 | this.bsR(8L)) << 8 | this.bsR(8L)) << 8 | this.bsR(8L));
   }

   private static void hbCreateDecodeTables(final int[] limit, final int[] base, final int[] perm, final char[] length, final int minLen, final int maxLen, final int alphaSize) {
      int i = minLen;

      for(int pp = 0; i <= maxLen; ++i) {
         for(int j = 0; j < alphaSize; ++j) {
            if (length[j] == i) {
               perm[pp++] = j;
            }
         }
      }

      i = 23;

      while(true) {
         --i;
         if (i <= 0) {
            for(int i = 0; i < alphaSize; ++i) {
               ++base[length[i] + 1];
            }

            i = 1;

            for(int b = base[0]; i < 23; ++i) {
               b += base[i];
               base[i] = b;
            }

            i = minLen;
            int vec = 0;

            for(int b = base[minLen]; i <= maxLen; ++i) {
               int nb = base[i + 1];
               vec += nb - b;
               b = nb;
               limit[i] = vec - 1;
               vec <<= 1;
            }

            for(int i = minLen + 1; i <= maxLen; ++i) {
               base[i] = (limit[i - 1] + 1 << 1) - base[i];
            }

            return;
         }

         base[i] = 0;
         limit[i] = 0;
      }
   }

   private void recvDecodingTables() throws IOException {
      Data dataShadow = this.data;
      boolean[] inUse = dataShadow.inUse;
      byte[] pos = dataShadow.recvDecodingTablesPos;
      byte[] selector = dataShadow.selector;
      byte[] selectorMtf = dataShadow.selectorMtf;
      int inUse16 = 0;

      for(int i = 0; i < 16; ++i) {
         if (this.bsGetBit()) {
            inUse16 |= 1 << i;
         }
      }

      int i = 256;

      while(true) {
         --i;
         if (i < 0) {
            for(int i = 0; i < 16; ++i) {
               if ((inUse16 & 1 << i) != 0) {
                  int i16 = i << 4;

                  for(int j = 0; j < 16; ++j) {
                     if (this.bsGetBit()) {
                        inUse[i16 + j] = true;
                     }
                  }
               }
            }

            this.makeMaps();
            i = this.nInUse + 2;
            int nGroups = (int)this.bsR(3L);
            int nSelectors = (int)this.bsR(15L);

            for(int i = 0; i < nSelectors; ++i) {
               int j;
               for(j = 0; this.bsGetBit(); ++j) {
               }

               selectorMtf[i] = (byte)j;
            }

            int v = nGroups;

            while(true) {
               --v;
               if (v < 0) {
                  for(int i = 0; i < nSelectors; ++i) {
                     int v = selectorMtf[i] & 255;

                     byte tmp;
                     for(tmp = pos[v]; v > 0; --v) {
                        pos[v] = pos[v - 1];
                     }

                     pos[0] = tmp;
                     selector[i] = tmp;
                  }

                  char[][] len = dataShadow.tempCharArray2D;

                  for(int t = 0; t < nGroups; ++t) {
                     int curr = (int)this.bsR(5L);
                     char[] lenT = len[t];

                     for(int i = 0; i < i; ++i) {
                        while(this.bsGetBit()) {
                           curr += this.bsGetBit() ? -1 : 1;
                        }

                        lenT[i] = (char)curr;
                     }
                  }

                  this.createHuffmanDecodingTables(i, nGroups);
                  return;
               }

               pos[v] = (byte)v;
            }
         }

         inUse[i] = false;
      }
   }

   private void createHuffmanDecodingTables(final int alphaSize, final int nGroups) {
      Data dataShadow = this.data;
      char[][] len = dataShadow.tempCharArray2D;
      int[] minLens = dataShadow.minLens;
      int[][] limit = dataShadow.limit;
      int[][] base = dataShadow.base;
      int[][] perm = dataShadow.perm;

      for(int t = 0; t < nGroups; ++t) {
         int minLen = 32;
         int maxLen = 0;
         char[] lenT = len[t];
         int i = alphaSize;

         while(true) {
            --i;
            if (i < 0) {
               hbCreateDecodeTables(limit[t], base[t], perm[t], len[t], minLen, maxLen, alphaSize);
               minLens[t] = minLen;
               break;
            }

            char lent = lenT[i];
            if (lent > maxLen) {
               maxLen = lent;
            }

            if (lent < minLen) {
               minLen = lent;
            }
         }
      }

   }

   private void getAndMoveToFrontDecode() throws IOException {
      this.origPtr = (int)this.bsR(24L);
      this.recvDecodingTables();
      InputStream inShadow = this.in;
      Data dataShadow = this.data;
      byte[] ll8 = dataShadow.ll8;
      int[] unzftab = dataShadow.unzftab;
      byte[] selector = dataShadow.selector;
      byte[] seqToUnseq = dataShadow.seqToUnseq;
      char[] yy = dataShadow.getAndMoveToFrontDecodeYy;
      int[] minLens = dataShadow.minLens;
      int[][] limit = dataShadow.limit;
      int[][] base = dataShadow.base;
      int[][] perm = dataShadow.perm;
      int limitLast = this.blockSize100k * 100000;
      int i = 256;

      while(true) {
         --i;
         if (i < 0) {
            i = 0;
            int groupPos = 49;
            int eob = this.nInUse + 1;
            int nextSym = this.getAndMoveToFrontDecode0(0);
            int bsBuffShadow = (int)this.bsBuff;
            int bsLiveShadow = (int)this.bsLive;
            int lastShadow = -1;
            int zt = selector[i] & 255;
            int[] baseZt = base[zt];
            int[] limitZt = limit[zt];
            int[] permZt = perm[zt];
            int minLensZt = minLens[zt];

            while(nextSym != eob) {
               if (nextSym != 0 && nextSym != 1) {
                  ++lastShadow;
                  if (lastShadow >= limitLast) {
                     throw new IOException("block overrun");
                  }

                  char tmp = yy[nextSym - 1];
                  ++unzftab[seqToUnseq[tmp] & 255];
                  ll8[lastShadow] = seqToUnseq[tmp];
                  if (nextSym <= 16) {
                     for(int j = nextSym - 1; j > 0; yy[j--] = yy[j]) {
                     }
                  } else {
                     System.arraycopy(yy, 0, yy, 1, nextSym - 1);
                  }

                  yy[0] = tmp;
                  if (groupPos == 0) {
                     groupPos = 49;
                     ++i;
                     zt = selector[i] & 255;
                     baseZt = base[zt];
                     limitZt = limit[zt];
                     permZt = perm[zt];
                     minLensZt = minLens[zt];
                  } else {
                     --groupPos;
                  }

                  int zn;
                  for(zn = minLensZt; bsLiveShadow < zn; bsLiveShadow += 8) {
                     int thech = this.readAByte(inShadow);
                     if (thech < 0) {
                        throw new IOException("unexpected end of stream");
                     }

                     bsBuffShadow = bsBuffShadow << 8 | thech;
                  }

                  int zvec = bsBuffShadow >> bsLiveShadow - zn & (1 << zn) - 1;

                  for(bsLiveShadow -= zn; zvec > limitZt[zn]; zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 1) {
                     ++zn;

                     while(bsLiveShadow < 1) {
                        int thech = this.readAByte(inShadow);
                        if (thech < 0) {
                           throw new IOException("unexpected end of stream");
                        }

                        bsBuffShadow = bsBuffShadow << 8 | thech;
                        bsLiveShadow += 8;
                     }

                     --bsLiveShadow;
                  }

                  nextSym = permZt[zvec - baseZt[zn]];
               } else {
                  int s = -1;
                  int n = 1;

                  while(true) {
                     if (nextSym == 0) {
                        s += n;
                     } else {
                        if (nextSym != 1) {
                           n = seqToUnseq[yy[0]];

                           for(unzftab[n & 255] += s + 1; s-- >= 0; ll8[lastShadow] = (byte)n) {
                              ++lastShadow;
                           }

                           if (lastShadow >= limitLast) {
                              throw new IOException("block overrun");
                           }
                           break;
                        }

                        s += n << 1;
                     }

                     if (groupPos == 0) {
                        groupPos = 49;
                        ++i;
                        zt = selector[i] & 255;
                        baseZt = base[zt];
                        limitZt = limit[zt];
                        permZt = perm[zt];
                        minLensZt = minLens[zt];
                     } else {
                        --groupPos;
                     }

                     int zn;
                     for(zn = minLensZt; bsLiveShadow < zn; bsLiveShadow += 8) {
                        int thech = this.readAByte(inShadow);
                        if (thech < 0) {
                           throw new IOException("unexpected end of stream");
                        }

                        bsBuffShadow = bsBuffShadow << 8 | thech;
                     }

                     long zvec = (long)(bsBuffShadow >> bsLiveShadow - zn) & (1L << zn) - 1L;

                     for(bsLiveShadow -= zn; zvec > (long)limitZt[zn]; zvec = zvec << 1 | (long)(bsBuffShadow >> bsLiveShadow & 1)) {
                        ++zn;

                        while(bsLiveShadow < 1) {
                           int thech = this.readAByte(inShadow);
                           if (thech < 0) {
                              throw new IOException("unexpected end of stream");
                           }

                           bsBuffShadow = bsBuffShadow << 8 | thech;
                           bsLiveShadow += 8;
                        }

                        --bsLiveShadow;
                     }

                     nextSym = permZt[(int)(zvec - (long)baseZt[zn])];
                     n <<= 1;
                  }
               }
            }

            this.last = lastShadow;
            this.bsLive = (long)bsLiveShadow;
            this.bsBuff = (long)bsBuffShadow;
            return;
         }

         yy[i] = (char)i;
         unzftab[i] = 0;
      }
   }

   private int getAndMoveToFrontDecode0(final int groupNo) throws IOException {
      InputStream inShadow = this.in;
      Data dataShadow = this.data;
      int zt = dataShadow.selector[groupNo] & 255;
      int[] limitZt = dataShadow.limit[zt];
      int zn = dataShadow.minLens[zt];
      int zvec = (int)this.bsR((long)zn);
      int bsLiveShadow = (int)this.bsLive;

      int bsBuffShadow;
      for(bsBuffShadow = (int)this.bsBuff; zvec > limitZt[zn]; zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 1) {
         ++zn;

         while(bsLiveShadow < 1) {
            int thech = this.readAByte(inShadow);
            if (thech < 0) {
               throw new IOException("unexpected end of stream");
            }

            bsBuffShadow = bsBuffShadow << 8 | thech;
            bsLiveShadow += 8;
         }

         --bsLiveShadow;
      }

      this.bsLive = (long)bsLiveShadow;
      this.bsBuff = (long)bsBuffShadow;
      return dataShadow.perm[zt][zvec - dataShadow.base[zt][zn]];
   }

   private void setupBlock() throws IOException {
      if (this.data != null) {
         int[] cftab = this.data.cftab;
         int[] tt = this.data.initTT(this.last + 1);
         byte[] ll8 = this.data.ll8;
         cftab[0] = 0;
         System.arraycopy(this.data.unzftab, 0, cftab, 1, 256);
         int i = 1;

         for(int c = cftab[0]; i <= 256; ++i) {
            c += cftab[i];
            cftab[i] = c;
         }

         i = 0;

         int var10004;
         for(int lastShadow = this.last; i <= lastShadow; tt[var10004] = i++) {
            int var10002 = ll8[i] & 255;
            var10004 = cftab[ll8[i] & 255];
            cftab[var10002] = cftab[ll8[i] & 255] + 1;
         }

         if (this.origPtr >= 0 && this.origPtr < tt.length) {
            this.suTPos = tt[this.origPtr];
            this.suCount = 0;
            this.suI2 = 0;
            this.suCh2 = 256;
            if (this.blockRandomised) {
               this.suRNToGo = 0;
               this.suRTPos = 0;
               this.setupRandPartA();
            } else {
               this.setupNoRandPartA();
            }

         } else {
            throw new IOException("stream corrupted");
         }
      }
   }

   private void setupRandPartA() throws IOException {
      if (this.suI2 <= this.last) {
         this.suChPrev = this.suCh2;
         int suCh2Shadow = this.data.ll8[this.suTPos] & 255;
         this.suTPos = this.data.tt[this.suTPos];
         if (this.suRNToGo == 0) {
            this.suRNToGo = R_NUMS[this.suRTPos] - 1;
            if (++this.suRTPos == 512) {
               this.suRTPos = 0;
            }
         } else {
            --this.suRNToGo;
         }

         int var2;
         this.suCh2 = var2 = suCh2Shadow ^ (this.suRNToGo == 1 ? 1 : 0);
         ++this.suI2;
         this.currentChar = var2;
         this.currentState = CBZip2InputStream.STATE.RAND_PART_B_STATE;
         this.crc32.updateCRC(var2);
      } else {
         this.endBlock();
         this.initBlock();
         this.setupBlock();
      }

   }

   private void setupNoRandPartA() throws IOException {
      if (this.suI2 <= this.last) {
         this.suChPrev = this.suCh2;
         int suCh2Shadow = this.data.ll8[this.suTPos] & 255;
         this.suCh2 = suCh2Shadow;
         this.suTPos = this.data.tt[this.suTPos];
         ++this.suI2;
         this.currentChar = suCh2Shadow;
         this.currentState = CBZip2InputStream.STATE.NO_RAND_PART_B_STATE;
         this.crc32.updateCRC(suCh2Shadow);
      } else {
         this.currentState = CBZip2InputStream.STATE.NO_RAND_PART_A_STATE;
         this.endBlock();
         this.initBlock();
         this.setupBlock();
      }

   }

   private void setupRandPartB() throws IOException {
      if (this.suCh2 != this.suChPrev) {
         this.currentState = CBZip2InputStream.STATE.RAND_PART_A_STATE;
         this.suCount = 1;
         this.setupRandPartA();
      } else if (++this.suCount >= 4) {
         this.suZ = (char)(this.data.ll8[this.suTPos] & 255);
         this.suTPos = this.data.tt[this.suTPos];
         if (this.suRNToGo == 0) {
            this.suRNToGo = R_NUMS[this.suRTPos] - 1;
            if (++this.suRTPos == 512) {
               this.suRTPos = 0;
            }
         } else {
            --this.suRNToGo;
         }

         this.suJ2 = 0;
         this.currentState = CBZip2InputStream.STATE.RAND_PART_C_STATE;
         if (this.suRNToGo == 1) {
            this.suZ = (char)(this.suZ ^ 1);
         }

         this.setupRandPartC();
      } else {
         this.currentState = CBZip2InputStream.STATE.RAND_PART_A_STATE;
         this.setupRandPartA();
      }

   }

   private void setupRandPartC() throws IOException {
      if (this.suJ2 < this.suZ) {
         this.currentChar = this.suCh2;
         this.crc32.updateCRC(this.suCh2);
         ++this.suJ2;
      } else {
         this.currentState = CBZip2InputStream.STATE.RAND_PART_A_STATE;
         ++this.suI2;
         this.suCount = 0;
         this.setupRandPartA();
      }

   }

   private void setupNoRandPartB() throws IOException {
      if (this.suCh2 != this.suChPrev) {
         this.suCount = 1;
         this.setupNoRandPartA();
      } else if (++this.suCount >= 4) {
         this.suZ = (char)(this.data.ll8[this.suTPos] & 255);
         this.suTPos = this.data.tt[this.suTPos];
         this.suJ2 = 0;
         this.setupNoRandPartC();
      } else {
         this.setupNoRandPartA();
      }

   }

   private void setupNoRandPartC() throws IOException {
      if (this.suJ2 < this.suZ) {
         int suCh2Shadow = this.suCh2;
         this.currentChar = suCh2Shadow;
         this.crc32.updateCRC(suCh2Shadow);
         ++this.suJ2;
         this.currentState = CBZip2InputStream.STATE.NO_RAND_PART_C_STATE;
      } else {
         ++this.suI2;
         this.suCount = 0;
         this.setupNoRandPartA();
      }

   }

   public static enum STATE {
      EOF,
      START_BLOCK_STATE,
      RAND_PART_A_STATE,
      RAND_PART_B_STATE,
      RAND_PART_C_STATE,
      NO_RAND_PART_A_STATE,
      NO_RAND_PART_B_STATE,
      NO_RAND_PART_C_STATE,
      NO_PROCESS_STATE;
   }

   private static final class Data {
      final boolean[] inUse = new boolean[256];
      final byte[] seqToUnseq = new byte[256];
      final byte[] selector = new byte[18002];
      final byte[] selectorMtf = new byte[18002];
      final int[] unzftab = new int[256];
      final int[][] limit = new int[6][258];
      final int[][] base = new int[6][258];
      final int[][] perm = new int[6][258];
      final int[] minLens = new int[6];
      final int[] cftab = new int[257];
      final char[] getAndMoveToFrontDecodeYy = new char[256];
      final char[][] tempCharArray2D = new char[6][258];
      final byte[] recvDecodingTablesPos = new byte[6];
      int[] tt;
      byte[] ll8;

      Data(int blockSize100k) {
         this.ll8 = new byte[blockSize100k * 100000];
      }

      int[] initTT(int length) {
         int[] ttShadow = this.tt;
         if (ttShadow == null || ttShadow.length < length) {
            ttShadow = new int[length];
            this.tt = ttShadow;
         }

         return ttShadow;
      }
   }
}

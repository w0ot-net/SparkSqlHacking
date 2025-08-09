package com.ibm.icu.impl;

import com.ibm.icu.text.UTF16;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.CodePointMap;
import com.ibm.icu.util.CodePointTrie;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.MutableCodePointTrie;
import com.ibm.icu.util.VersionInfo;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public final class Normalizer2Impl {
   private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();
   private static final int DATA_FORMAT = 1316121906;
   private static final CodePointMap.ValueFilter segmentStarterMapper = new CodePointMap.ValueFilter() {
      public int apply(int value) {
         return value & Integer.MIN_VALUE;
      }
   };
   public static final int MIN_YES_YES_WITH_CC = 65026;
   public static final int JAMO_VT = 65024;
   public static final int MIN_NORMAL_MAYBE_YES = 64512;
   public static final int JAMO_L = 2;
   public static final int INERT = 1;
   public static final int HAS_COMP_BOUNDARY_AFTER = 1;
   public static final int OFFSET_SHIFT = 1;
   public static final int DELTA_TCCC_0 = 0;
   public static final int DELTA_TCCC_1 = 2;
   public static final int DELTA_TCCC_GT_1 = 4;
   public static final int DELTA_TCCC_MASK = 6;
   public static final int DELTA_SHIFT = 3;
   public static final int MAX_DELTA = 64;
   public static final int IX_NORM_TRIE_OFFSET = 0;
   public static final int IX_EXTRA_DATA_OFFSET = 1;
   public static final int IX_SMALL_FCD_OFFSET = 2;
   public static final int IX_RESERVED3_OFFSET = 3;
   public static final int IX_TOTAL_SIZE = 7;
   public static final int IX_MIN_DECOMP_NO_CP = 8;
   public static final int IX_MIN_COMP_NO_MAYBE_CP = 9;
   public static final int IX_MIN_YES_NO = 10;
   public static final int IX_MIN_NO_NO = 11;
   public static final int IX_LIMIT_NO_NO = 12;
   public static final int IX_MIN_MAYBE_YES = 13;
   public static final int IX_MIN_YES_NO_MAPPINGS_ONLY = 14;
   public static final int IX_MIN_NO_NO_COMP_BOUNDARY_BEFORE = 15;
   public static final int IX_MIN_NO_NO_COMP_NO_MAYBE_CC = 16;
   public static final int IX_MIN_NO_NO_EMPTY = 17;
   public static final int IX_MIN_LCCC_CP = 18;
   public static final int IX_MIN_MAYBE_NO = 20;
   public static final int IX_MIN_MAYBE_NO_COMBINES_FWD = 21;
   public static final int MAPPING_HAS_CCC_LCCC_WORD = 128;
   public static final int MAPPING_HAS_RAW_MAPPING = 64;
   public static final int MAPPING_LENGTH_MASK = 31;
   public static final int COMP_1_LAST_TUPLE = 32768;
   public static final int COMP_1_TRIPLE = 1;
   public static final int COMP_1_TRAIL_LIMIT = 13312;
   public static final int COMP_1_TRAIL_MASK = 32766;
   public static final int COMP_1_TRAIL_SHIFT = 9;
   public static final int COMP_2_TRAIL_SHIFT = 6;
   public static final int COMP_2_TRAIL_MASK = 65472;
   private VersionInfo dataVersion;
   private int minDecompNoCP;
   private int minCompNoMaybeCP;
   private int minLcccCP;
   private int minYesNo;
   private int minYesNoMappingsOnly;
   private int minNoNo;
   private int minNoNoCompBoundaryBefore;
   private int minNoNoCompNoMaybeCC;
   private int minNoNoEmpty;
   private int limitNoNo;
   private int centerNoNoDelta;
   private int minMaybeNo;
   private int minMaybeNoCombinesFwd;
   private int minMaybeYes;
   private CodePointTrie.Fast16 normTrie;
   private String extraData;
   private byte[] smallFCD;
   private CodePointTrie canonIterData;
   private ArrayList canonStartSets;
   private static final int CANON_NOT_SEGMENT_STARTER = Integer.MIN_VALUE;
   private static final int CANON_HAS_COMPOSITIONS = 1073741824;
   private static final int CANON_HAS_SET = 2097152;
   private static final int CANON_VALUE_MASK = 2097151;

   public Normalizer2Impl load(ByteBuffer bytes) {
      try {
         this.dataVersion = ICUBinary.readHeaderAndDataVersion(bytes, 1316121906, IS_ACCEPTABLE);
         int indexesLength = bytes.getInt() / 4;
         if (indexesLength <= 18) {
            throw new ICUUncheckedIOException("Normalizer2 data: not enough indexes");
         } else {
            int[] inIndexes = new int[indexesLength];
            inIndexes[0] = indexesLength * 4;

            for(int i = 1; i < indexesLength; ++i) {
               inIndexes[i] = bytes.getInt();
            }

            this.minDecompNoCP = inIndexes[8];
            this.minCompNoMaybeCP = inIndexes[9];
            this.minLcccCP = inIndexes[18];
            this.minYesNo = inIndexes[10];
            this.minYesNoMappingsOnly = inIndexes[14];
            this.minNoNo = inIndexes[11];
            this.minNoNoCompBoundaryBefore = inIndexes[15];
            this.minNoNoCompNoMaybeCC = inIndexes[16];
            this.minNoNoEmpty = inIndexes[17];
            this.limitNoNo = inIndexes[12];
            this.minMaybeNo = inIndexes[20];
            this.minMaybeNoCombinesFwd = inIndexes[21];
            this.minMaybeYes = inIndexes[13];

            assert (this.minMaybeNo & 7) == 0;

            this.centerNoNoDelta = (this.minMaybeNo >> 3) - 64 - 1;
            int offset = inIndexes[0];
            int nextOffset = inIndexes[1];
            int triePosition = bytes.position();
            this.normTrie = CodePointTrie.Fast16.fromBinary(bytes);
            int trieLength = bytes.position() - triePosition;
            if (trieLength > nextOffset - offset) {
               throw new ICUUncheckedIOException("Normalizer2 data: not enough bytes for normTrie");
            } else {
               ICUBinary.skipBytes(bytes, nextOffset - offset - trieLength);
               int var12 = inIndexes[2];
               int numChars = (var12 - nextOffset) / 2;
               if (numChars != 0) {
                  this.extraData = ICUBinary.getString(bytes, numChars, 0);
               }

               this.smallFCD = new byte[256];
               bytes.get(this.smallFCD);
               return this;
            }
         }
      } catch (IOException e) {
         throw new ICUUncheckedIOException(e);
      }
   }

   public Normalizer2Impl load(String name) {
      return this.load(ICUBinary.getRequiredData(name));
   }

   public void addLcccChars(UnicodeSet set) {
      int start = 0;

      int end;
      for(CodePointMap.Range range = new CodePointMap.Range(); this.normTrie.getRange(start, CodePointMap.RangeOption.FIXED_LEAD_SURROGATES, 1, (CodePointMap.ValueFilter)null, range); start = end + 1) {
         end = range.getEnd();
         int norm16 = range.getValue();
         if (norm16 > 64512 && norm16 != 65024) {
            set.add(start, end);
         } else if (this.minNoNoCompNoMaybeCC <= norm16 && norm16 < this.limitNoNo) {
            int fcd16 = this.getFCD16(start);
            if (fcd16 > 255) {
               set.add(start, end);
            }
         }
      }

   }

   public void addPropertyStarts(UnicodeSet set) {
      int start = 0;

      int end;
      for(CodePointMap.Range range = new CodePointMap.Range(); this.normTrie.getRange(start, CodePointMap.RangeOption.FIXED_LEAD_SURROGATES, 1, (CodePointMap.ValueFilter)null, range); start = end + 1) {
         end = range.getEnd();
         int value = range.getValue();
         set.add(start);
         if (start != end && this.isAlgorithmicNoNo(value) && (value & 6) > 2) {
            int prevFCD16 = this.getFCD16(start);

            while(true) {
               ++start;
               if (start > end) {
                  break;
               }

               int fcd16 = this.getFCD16(start);
               if (fcd16 != prevFCD16) {
                  set.add(start);
                  prevFCD16 = fcd16;
               }
            }
         }
      }

      for(int c = 44032; c < 55204; c += 28) {
         set.add(c);
         set.add(c + 1);
      }

      set.add(55204);
   }

   public void addCanonIterPropertyStarts(UnicodeSet set) {
      this.ensureCanonIterData();
      int start = 0;

      for(CodePointMap.Range range = new CodePointMap.Range(); this.canonIterData.getRange(start, segmentStarterMapper, range); start = range.getEnd() + 1) {
         set.add(start);
      }

   }

   public synchronized Normalizer2Impl ensureCanonIterData() {
      if (this.canonIterData == null) {
         MutableCodePointTrie mutableTrie = new MutableCodePointTrie(0, 0);
         this.canonStartSets = new ArrayList();
         int start = 0;
         CodePointMap.Range range = new CodePointMap.Range();

         while(this.normTrie.getRange(start, CodePointMap.RangeOption.FIXED_LEAD_SURROGATES, 1, (CodePointMap.ValueFilter)null, range)) {
            int end = range.getEnd();
            int norm16 = range.getValue();
            if (!isInert(norm16) && (this.minYesNo > norm16 || norm16 >= this.minNoNo) && (this.minMaybeNo > norm16 || norm16 >= this.minMaybeYes)) {
               for(int c = start; c <= end; ++c) {
                  int oldValue = mutableTrie.get(c);
                  int newValue = oldValue;
                  if (this.isMaybeYesOrNonZeroCC(norm16)) {
                     newValue = oldValue | Integer.MIN_VALUE;
                     if (norm16 < 64512) {
                        newValue |= 1073741824;
                     }
                  } else if (norm16 < this.minYesNo) {
                     newValue = oldValue | 1073741824;
                  } else {
                     int c2 = c;
                     int norm16_2 = norm16;
                     if (this.isDecompNoAlgorithmic(norm16)) {
                        c2 = this.mapAlgorithmic(c, norm16);
                        norm16_2 = this.getRawNorm16(c2);

                        assert !this.isHangulLV(norm16_2) && !this.isHangulLVT(norm16_2);
                     }

                     if (norm16_2 > this.minYesNo) {
                        int mapping = this.getDataForYesOrNo(norm16_2);
                        int firstUnit = this.extraData.charAt(mapping);
                        int length = firstUnit & 31;
                        if ((firstUnit & 128) != 0 && c == c2 && (this.extraData.charAt(mapping - 1) & 255) != 0) {
                           newValue = oldValue | Integer.MIN_VALUE;
                        }

                        if (length != 0) {
                           ++mapping;
                           int limit = mapping + length;
                           c2 = this.extraData.codePointAt(mapping);
                           this.addToStartSet(mutableTrie, c, c2);
                           if (norm16_2 >= this.minNoNo) {
                              while((mapping += Character.charCount(c2)) < limit) {
                                 c2 = this.extraData.codePointAt(mapping);
                                 int c2Value = mutableTrie.get(c2);
                                 if ((c2Value & Integer.MIN_VALUE) == 0) {
                                    mutableTrie.set(c2, c2Value | Integer.MIN_VALUE);
                                 }
                              }
                           }
                        }
                     } else {
                        this.addToStartSet(mutableTrie, c, c2);
                     }
                  }

                  if (newValue != oldValue) {
                     mutableTrie.set(c, newValue);
                  }
               }

               start = end + 1;
            } else {
               start = end + 1;
            }
         }

         this.canonIterData = mutableTrie.buildImmutable(CodePointTrie.Type.SMALL, CodePointTrie.ValueWidth.BITS_32);
      }

      return this;
   }

   public int getNorm16(int c) {
      return Normalizer2Impl.UTF16Plus.isLeadSurrogate(c) ? 1 : this.normTrie.get(c);
   }

   public int getRawNorm16(int c) {
      return this.normTrie.get(c);
   }

   public int getCompQuickCheck(int norm16) {
      if (norm16 >= this.minNoNo && 65026 > norm16) {
         return this.minMaybeNo <= norm16 ? 2 : 0;
      } else {
         return 1;
      }
   }

   public boolean isAlgorithmicNoNo(int norm16) {
      return this.limitNoNo <= norm16 && norm16 < this.minMaybeNo;
   }

   public boolean isCompNo(int norm16) {
      return this.minNoNo <= norm16 && norm16 < this.minMaybeNo;
   }

   public boolean isDecompYes(int norm16) {
      return norm16 < this.minYesNo || this.minMaybeYes <= norm16;
   }

   public int getCC(int norm16) {
      if (norm16 >= 64512) {
         return getCCFromNormalYesOrMaybe(norm16);
      } else {
         return norm16 >= this.minNoNo && this.limitNoNo > norm16 ? this.getCCFromNoNo(norm16) : 0;
      }
   }

   public static int getCCFromNormalYesOrMaybe(int norm16) {
      return norm16 >> 1 & 255;
   }

   public static int getCCFromYesOrMaybeYes(int norm16) {
      return norm16 >= 64512 ? getCCFromNormalYesOrMaybe(norm16) : 0;
   }

   public int getCCFromYesOrMaybeYesCP(int c) {
      return c < this.minCompNoMaybeCP ? 0 : getCCFromYesOrMaybeYes(this.getNorm16(c));
   }

   public int getFCD16(int c) {
      if (c < this.minDecompNoCP) {
         return 0;
      } else {
         return c <= 65535 && !this.singleLeadMightHaveNonZeroFCD16(c) ? 0 : this.getFCD16FromNormData(c);
      }
   }

   public boolean singleLeadMightHaveNonZeroFCD16(int lead) {
      byte bits = this.smallFCD[lead >> 8];
      if (bits == 0) {
         return false;
      } else {
         return (bits >> (lead >> 5 & 7) & 1) != 0;
      }
   }

   public int getFCD16FromNormData(int c) {
      int norm16 = this.getNorm16(c);
      if (norm16 >= this.limitNoNo) {
         if (norm16 >= 64512) {
            norm16 = getCCFromNormalYesOrMaybe(norm16);
            return norm16 | norm16 << 8;
         }

         if (norm16 >= this.minMaybeYes) {
            return 0;
         }

         if (norm16 < this.minMaybeNo) {
            int deltaTrailCC = norm16 & 6;
            if (deltaTrailCC <= 2) {
               return deltaTrailCC >> 1;
            }

            c = this.mapAlgorithmic(c, norm16);
            norm16 = this.getRawNorm16(c);
         }
      }

      if (norm16 > this.minYesNo && !this.isHangulLVT(norm16)) {
         int mapping = this.getData(norm16);
         int firstUnit = this.extraData.charAt(mapping);
         int fcd16 = firstUnit >> 8;
         if ((firstUnit & 128) != 0) {
            fcd16 |= this.extraData.charAt(mapping - 1) & '\uff00';
         }

         return fcd16;
      } else {
         return 0;
      }
   }

   private int getFCD16FromMaybeOrNonZeroCC(int norm16) {
      assert norm16 >= this.minMaybeNo;

      if (norm16 >= 64512) {
         norm16 = getCCFromNormalYesOrMaybe(norm16);
         return norm16 | norm16 << 8;
      } else if (norm16 >= this.minMaybeYes) {
         return 0;
      } else {
         int mapping = this.getDataForMaybe(norm16);
         int firstUnit = this.extraData.charAt(mapping);

         assert (firstUnit & 128) == 0 || (this.extraData.charAt(mapping - 1) & '\uff00') == 0;

         return firstUnit >> 8;
      }
   }

   public String getDecomposition(int c) {
      int norm16;
      if (c >= this.minDecompNoCP && !this.isMaybeYesOrNonZeroCC(norm16 = this.getNorm16(c))) {
         int decomp = -1;
         if (this.isDecompNoAlgorithmic(norm16)) {
            decomp = c = this.mapAlgorithmic(c, norm16);
            norm16 = this.getRawNorm16(c);
         }

         if (norm16 < this.minYesNo) {
            return decomp < 0 ? null : UTF16.valueOf(decomp);
         } else if (!this.isHangulLV(norm16) && !this.isHangulLVT(norm16)) {
            int mapping = this.getData(norm16);
            int length = this.extraData.charAt(mapping++) & 31;
            return this.extraData.substring(mapping, mapping + length);
         } else {
            StringBuilder buffer = new StringBuilder();
            Normalizer2Impl.Hangul.decompose(c, buffer);
            return buffer.toString();
         }
      } else {
         return null;
      }
   }

   public String getRawDecomposition(int c) {
      int norm16;
      if (c >= this.minDecompNoCP && !this.isDecompYes(norm16 = this.getNorm16(c))) {
         if (!this.isHangulLV(norm16) && !this.isHangulLVT(norm16)) {
            if (this.isDecompNoAlgorithmic(norm16)) {
               return UTF16.valueOf(this.mapAlgorithmic(c, norm16));
            } else {
               int mapping = this.getData(norm16);
               int firstUnit = this.extraData.charAt(mapping);
               int mLength = firstUnit & 31;
               if ((firstUnit & 64) != 0) {
                  int rawMapping = mapping - (firstUnit >> 7 & 1) - 1;
                  char rm0 = this.extraData.charAt(rawMapping);
                  if (rm0 <= 31) {
                     return this.extraData.substring(rawMapping - rm0, rawMapping);
                  } else {
                     StringBuilder buffer = (new StringBuilder(mLength - 1)).append(rm0);
                     mapping += 3;
                     return buffer.append(this.extraData, mapping, mapping + mLength - 2).toString();
                  }
               } else {
                  ++mapping;
                  return this.extraData.substring(mapping, mapping + mLength);
               }
            }
         } else {
            StringBuilder buffer = new StringBuilder();
            Normalizer2Impl.Hangul.getRawDecomposition(c, buffer);
            return buffer.toString();
         }
      } else {
         return null;
      }
   }

   public boolean isCanonSegmentStarter(int c) {
      return this.canonIterData.get(c) >= 0;
   }

   public boolean getCanonStartSet(int c, UnicodeSet set) {
      int canonValue = this.canonIterData.get(c) & Integer.MAX_VALUE;
      if (canonValue == 0) {
         return false;
      } else {
         set.clear();
         int value = canonValue & 2097151;
         if ((canonValue & 2097152) != 0) {
            set.addAll((UnicodeSet)this.canonStartSets.get(value));
         } else if (value != 0) {
            set.add(value);
         }

         if ((canonValue & 1073741824) != 0) {
            int norm16 = this.getRawNorm16(c);
            if (norm16 == 2) {
               int syllable = '가' + (c - 4352) * 588;
               set.add(syllable, syllable + 588 - 1);
            } else {
               this.addComposites(this.getCompositionsList(norm16), set);
            }
         }

         return true;
      }
   }

   public Appendable decompose(CharSequence s, StringBuilder dest) {
      this.decompose(s, 0, s.length(), dest, s.length());
      return dest;
   }

   public void decompose(CharSequence s, int src, int limit, StringBuilder dest, int destLengthEstimate) {
      if (destLengthEstimate < 0) {
         destLengthEstimate = limit - src;
      }

      dest.setLength(0);
      ReorderingBuffer buffer = new ReorderingBuffer(this, dest, destLengthEstimate);
      this.decompose(s, src, limit, buffer);
   }

   public int decompose(CharSequence s, int src, int limit, ReorderingBuffer buffer) {
      int minNoCP = this.minDecompNoCP;
      int c = 0;
      int norm16 = 0;
      int prevBoundary = src;
      int prevCC = 0;

      while(true) {
         int prevSrc = src;

         while(src != limit) {
            if ((c = s.charAt(src)) >= minNoCP && !this.isMostDecompYesAndZeroCC(norm16 = this.normTrie.bmpGet(c))) {
               if (!Normalizer2Impl.UTF16Plus.isLeadSurrogate(c)) {
                  break;
               }

               char c2;
               if (src + 1 != limit && Character.isLowSurrogate(c2 = s.charAt(src + 1))) {
                  c = Character.toCodePoint((char)c, c2);
                  norm16 = this.normTrie.suppGet(c);
                  if (!this.isMostDecompYesAndZeroCC(norm16)) {
                     break;
                  }

                  src += 2;
               } else {
                  ++src;
               }
            } else {
               ++src;
            }
         }

         if (src != prevSrc) {
            if (buffer != null) {
               buffer.flushAndAppendZeroCC(s, prevSrc, src);
            } else {
               prevCC = 0;
               prevBoundary = src;
            }
         }

         if (src == limit) {
            return src;
         }

         src += Character.charCount(c);
         if (buffer == null) {
            if (!this.isDecompYes(norm16)) {
               break;
            }

            int cc = getCCFromYesOrMaybeYes(norm16);
            if (prevCC > cc && cc != 0) {
               break;
            }

            prevCC = cc;
            if (cc <= 1) {
               prevBoundary = src;
            }
         } else {
            this.decompose(c, norm16, buffer);
         }
      }

      return prevBoundary;
   }

   public void decomposeAndAppend(CharSequence s, boolean doDecompose, ReorderingBuffer buffer) {
      int limit = s.length();
      if (limit != 0) {
         if (doDecompose) {
            this.decompose(s, 0, limit, buffer);
         } else {
            int c = Character.codePointAt(s, 0);
            int src = 0;

            int firstCC;
            int prevCC;
            int cc;
            for(firstCC = prevCC = cc = this.getCC(this.getNorm16(c)); cc != 0; cc = this.getCC(this.getNorm16(c))) {
               prevCC = cc;
               src += Character.charCount(c);
               if (src >= limit) {
                  break;
               }

               c = Character.codePointAt(s, src);
            }

            buffer.append(s, 0, src, false, firstCC, prevCC);
            buffer.append(s, src, limit);
         }
      }
   }

   public boolean compose(CharSequence s, int src, int limit, boolean onlyContiguous, boolean doCompose, ReorderingBuffer buffer) {
      int prevBoundary = src;
      int minNoMaybeCP = this.minCompNoMaybeCP;

      while(true) {
         int c = 0;
         int norm16 = 0;

         int prevSrc;
         while(true) {
            if (src == limit) {
               if (prevBoundary != limit && doCompose) {
                  buffer.append(s, prevBoundary, limit);
               }

               return true;
            }

            if ((c = s.charAt(src)) >= minNoMaybeCP && !this.isCompYesAndZeroCC(norm16 = this.normTrie.bmpGet(c))) {
               prevSrc = src++;
               if (!Normalizer2Impl.UTF16Plus.isLeadSurrogate(c)) {
                  break;
               }

               char c2;
               if (src != limit && Character.isLowSurrogate(c2 = s.charAt(src))) {
                  ++src;
                  c = Character.toCodePoint((char)c, c2);
                  norm16 = this.normTrie.suppGet(c);
                  if (!this.isCompYesAndZeroCC(norm16)) {
                     break;
                  }
               }
            } else {
               ++src;
            }
         }

         if (norm16 < this.minMaybeNo) {
            if (!doCompose) {
               return false;
            }

            if (this.isDecompNoAlgorithmic(norm16)) {
               if (this.norm16HasCompBoundaryAfter(norm16, onlyContiguous) || this.hasCompBoundaryBefore(s, src, limit)) {
                  if (prevBoundary != prevSrc) {
                     buffer.append(s, prevBoundary, prevSrc);
                  }

                  buffer.append(this.mapAlgorithmic(c, norm16), 0);
                  prevBoundary = src;
                  continue;
               }
            } else if (norm16 < this.minNoNoCompBoundaryBefore) {
               if (this.norm16HasCompBoundaryAfter(norm16, onlyContiguous) || this.hasCompBoundaryBefore(s, src, limit)) {
                  if (prevBoundary != prevSrc) {
                     buffer.append(s, prevBoundary, prevSrc);
                  }

                  int mapping = this.getDataForYesOrNo(norm16);
                  int length = this.extraData.charAt(mapping++) & 31;
                  buffer.append(this.extraData, mapping, mapping + length);
                  prevBoundary = src;
                  continue;
               }
            } else if (norm16 >= this.minNoNoEmpty && (this.hasCompBoundaryBefore(s, src, limit) || this.hasCompBoundaryAfter(s, prevBoundary, prevSrc, onlyContiguous))) {
               if (prevBoundary != prevSrc) {
                  buffer.append(s, prevBoundary, prevSrc);
               }

               prevBoundary = src;
               continue;
            }
         } else if (isJamoVT(norm16) && prevBoundary != prevSrc) {
            char prev = s.charAt(prevSrc - 1);
            if (c < 4519) {
               char l = (char)(prev - 4352);
               if (l < 19) {
                  if (!doCompose) {
                     return false;
                  }

                  int t;
                  if (src != limit && 0 < (t = s.charAt(src) - 4519) && t < 28) {
                     ++src;
                  } else if (this.hasCompBoundaryBefore(s, src, limit)) {
                     t = 0;
                  } else {
                     t = -1;
                  }

                  if (t >= 0) {
                     int syllable = '가' + (l * 21 + (c - 4449)) * 28 + t;
                     --prevSrc;
                     if (prevBoundary != prevSrc) {
                        buffer.append(s, prevBoundary, prevSrc);
                     }

                     buffer.append((char)syllable);
                     prevBoundary = src;
                     continue;
                  }
               }
            } else if (Normalizer2Impl.Hangul.isHangulLV(prev)) {
               if (!doCompose) {
                  return false;
               }

               int syllable = prev + c - 4519;
               --prevSrc;
               if (prevBoundary != prevSrc) {
                  buffer.append(s, prevBoundary, prevSrc);
               }

               buffer.append((char)syllable);
               prevBoundary = src;
               continue;
            }
         } else if (norm16 > 65024) {
            int cc = getCCFromNormalYesOrMaybe(norm16);
            if (onlyContiguous && this.getPreviousTrailCC(s, prevBoundary, prevSrc) > cc) {
               if (!doCompose) {
                  return false;
               }
            } else {
               int n16;
               while(true) {
                  if (src == limit) {
                     if (doCompose) {
                        buffer.append(s, prevBoundary, limit);
                     }

                     return true;
                  }

                  int prevCC = cc;
                  c = Character.codePointAt(s, src);
                  n16 = this.normTrie.get(c);
                  if (n16 < 65026) {
                     break;
                  }

                  cc = getCCFromNormalYesOrMaybe(n16);
                  if (prevCC > cc) {
                     if (!doCompose) {
                        return false;
                     }
                     break;
                  }

                  src += Character.charCount(c);
               }

               if (this.norm16HasCompBoundaryBefore(n16)) {
                  if (this.isCompYesAndZeroCC(n16)) {
                     src += Character.charCount(c);
                  }
                  continue;
               }
            }
         }

         if (prevBoundary != prevSrc && !this.norm16HasCompBoundaryBefore(norm16)) {
            c = Character.codePointBefore(s, prevSrc);
            norm16 = this.normTrie.get(c);
            if (!this.norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
               prevSrc -= Character.charCount(c);
            }
         }

         if (doCompose && prevBoundary != prevSrc) {
            buffer.append(s, prevBoundary, prevSrc);
         }

         int recomposeStartIndex = buffer.length();
         this.decomposeShort(s, prevSrc, src, false, onlyContiguous, buffer);
         src = this.decomposeShort(s, src, limit, true, onlyContiguous, buffer);
         this.recompose(buffer, recomposeStartIndex, onlyContiguous);
         if (!doCompose) {
            if (!buffer.equals(s, prevSrc, src)) {
               return false;
            }

            buffer.remove();
         }

         prevBoundary = src;
      }
   }

   public int composeQuickCheck(CharSequence s, int src, int limit, boolean onlyContiguous, boolean doSpan) {
      int qcResult = 0;
      int prevBoundary = src;
      int minNoMaybeCP = this.minCompNoMaybeCP;

      while(true) {
         int c = 0;
         int norm16 = 0;

         int prevSrc;
         while(true) {
            if (src == limit) {
               return src << 1 | qcResult;
            }

            if ((c = s.charAt(src)) >= minNoMaybeCP && !this.isCompYesAndZeroCC(norm16 = this.normTrie.bmpGet(c))) {
               prevSrc = src++;
               if (!Normalizer2Impl.UTF16Plus.isLeadSurrogate(c)) {
                  break;
               }

               char c2;
               if (src != limit && Character.isLowSurrogate(c2 = s.charAt(src))) {
                  ++src;
                  c = Character.toCodePoint((char)c, c2);
                  norm16 = this.normTrie.suppGet(c);
                  if (!this.isCompYesAndZeroCC(norm16)) {
                     break;
                  }
               }
            } else {
               ++src;
            }
         }

         int prevNorm16 = 1;
         if (prevBoundary != prevSrc) {
            prevBoundary = prevSrc;
            if (!this.norm16HasCompBoundaryBefore(norm16)) {
               c = Character.codePointBefore(s, prevSrc);
               int n16 = this.getNorm16(c);
               if (!this.norm16HasCompBoundaryAfter(n16, onlyContiguous)) {
                  prevBoundary = prevSrc - Character.charCount(c);
                  prevNorm16 = n16;
               }
            }
         }

         if (norm16 < this.minMaybeNo) {
            break;
         }

         int fcd16 = this.getFCD16FromMaybeOrNonZeroCC(norm16);
         int cc = fcd16 >> 8 & 255;
         if (onlyContiguous && cc != 0 && this.getTrailCCFromCompYesAndZeroCC(prevNorm16) > cc) {
            break;
         }

         while(true) {
            if (norm16 < 65026) {
               if (doSpan) {
                  return prevBoundary << 1;
               }

               qcResult = 1;
            }

            if (src == limit) {
               return src << 1 | qcResult;
            }

            int prevCC = fcd16 & 255;
            c = Character.codePointAt(s, src);
            norm16 = this.getNorm16(c);
            if (norm16 < this.minMaybeNo) {
               break;
            }

            fcd16 = this.getFCD16FromMaybeOrNonZeroCC(norm16);
            cc = fcd16 >> 8 & 255;
            if (prevCC > cc && cc != 0) {
               break;
            }

            src += Character.charCount(c);
         }

         if (!this.isCompYesAndZeroCC(norm16)) {
            break;
         }

         prevBoundary = src;
         src += Character.charCount(c);
      }

      return prevBoundary << 1;
   }

   public void composeAndAppend(CharSequence s, boolean doCompose, boolean onlyContiguous, ReorderingBuffer buffer) {
      int src = 0;
      int limit = s.length();
      if (!buffer.isEmpty()) {
         int firstStarterInSrc = this.findNextCompBoundary(s, 0, limit, onlyContiguous);
         if (0 != firstStarterInSrc) {
            int lastStarterInDest = this.findPreviousCompBoundary(buffer.getStringBuilder(), buffer.length(), onlyContiguous);
            StringBuilder middle = new StringBuilder(buffer.length() - lastStarterInDest + firstStarterInSrc + 16);
            middle.append(buffer.getStringBuilder(), lastStarterInDest, buffer.length());
            buffer.removeSuffix(buffer.length() - lastStarterInDest);
            middle.append(s, 0, firstStarterInSrc);
            this.compose(middle, 0, middle.length(), onlyContiguous, true, buffer);
            src = firstStarterInSrc;
         }
      }

      if (doCompose) {
         this.compose(s, src, limit, onlyContiguous, true, buffer);
      } else {
         buffer.append(s, src, limit);
      }

   }

   public int makeFCD(CharSequence s, int src, int limit, ReorderingBuffer buffer) {
      int prevBoundary = src;
      int c = 0;
      int prevFCD16 = 0;
      int fcd16 = 0;

      while(true) {
         int prevSrc = src;

         while(src != limit) {
            if ((c = s.charAt(src)) < this.minLcccCP) {
               prevFCD16 = ~c;
               ++src;
            } else if (!this.singleLeadMightHaveNonZeroFCD16(c)) {
               prevFCD16 = 0;
               ++src;
            } else {
               char c2;
               if (Normalizer2Impl.UTF16Plus.isLeadSurrogate(c) && src + 1 != limit && Character.isLowSurrogate(c2 = s.charAt(src + 1))) {
                  c = Character.toCodePoint((char)c, c2);
               }

               if ((fcd16 = this.getFCD16FromNormData(c)) > 255) {
                  break;
               }

               prevFCD16 = fcd16;
               src += Character.charCount(c);
            }
         }

         if (src != prevSrc) {
            if (src == limit) {
               if (buffer != null) {
                  buffer.flushAndAppendZeroCC(s, prevSrc, src);
               }
               break;
            }

            prevBoundary = src;
            if (prevFCD16 < 0) {
               int prev = ~prevFCD16;
               if (prev < this.minDecompNoCP) {
                  prevFCD16 = 0;
               } else {
                  prevFCD16 = this.getFCD16FromNormData(prev);
                  if (prevFCD16 > 1) {
                     prevBoundary = src - 1;
                  }
               }
            } else {
               int p = src - 1;
               if (Character.isLowSurrogate(s.charAt(p)) && prevSrc < p && Character.isHighSurrogate(s.charAt(p - 1))) {
                  --p;
                  prevFCD16 = this.getFCD16FromNormData(Character.toCodePoint(s.charAt(p), s.charAt(p + 1)));
               }

               if (prevFCD16 > 1) {
                  prevBoundary = p;
               }
            }

            if (buffer != null) {
               buffer.flushAndAppendZeroCC(s, prevSrc, prevBoundary);
               buffer.append(s, prevBoundary, src);
            }

            prevSrc = src;
         } else if (src == limit) {
            break;
         }

         src += Character.charCount(c);
         if ((prevFCD16 & 255) <= fcd16 >> 8) {
            if ((fcd16 & 255) <= 1) {
               prevBoundary = src;
            }

            if (buffer != null) {
               buffer.appendZeroCC(c);
            }

            prevFCD16 = fcd16;
         } else {
            if (buffer == null) {
               return prevBoundary;
            }

            buffer.removeSuffix(prevSrc - prevBoundary);
            src = this.findNextFCDBoundary(s, src, limit);
            this.decomposeShort(s, prevBoundary, src, false, false, buffer);
            prevBoundary = src;
            prevFCD16 = 0;
         }
      }

      return src;
   }

   public void makeFCDAndAppend(CharSequence s, boolean doMakeFCD, ReorderingBuffer buffer) {
      int src = 0;
      int limit = s.length();
      if (!buffer.isEmpty()) {
         int firstBoundaryInSrc = this.findNextFCDBoundary(s, 0, limit);
         if (0 != firstBoundaryInSrc) {
            int lastBoundaryInDest = this.findPreviousFCDBoundary(buffer.getStringBuilder(), buffer.length());
            StringBuilder middle = new StringBuilder(buffer.length() - lastBoundaryInDest + firstBoundaryInSrc + 16);
            middle.append(buffer.getStringBuilder(), lastBoundaryInDest, buffer.length());
            buffer.removeSuffix(buffer.length() - lastBoundaryInDest);
            middle.append(s, 0, firstBoundaryInSrc);
            this.makeFCD(middle, 0, middle.length(), buffer);
            src = firstBoundaryInSrc;
         }
      }

      if (doMakeFCD) {
         this.makeFCD(s, src, limit, buffer);
      } else {
         buffer.append(s, src, limit);
      }

   }

   public boolean hasDecompBoundaryBefore(int c) {
      return c < this.minLcccCP || c <= 65535 && !this.singleLeadMightHaveNonZeroFCD16(c) || this.norm16HasDecompBoundaryBefore(this.getNorm16(c));
   }

   public boolean norm16HasDecompBoundaryBefore(int norm16) {
      if (norm16 < this.minNoNoCompNoMaybeCC) {
         return true;
      } else if (norm16 >= this.limitNoNo) {
         return norm16 <= 64512 || norm16 == 65024;
      } else {
         int mapping = this.getDataForYesOrNo(norm16);
         int firstUnit = this.extraData.charAt(mapping);
         return (firstUnit & 128) == 0 || (this.extraData.charAt(mapping - 1) & '\uff00') == 0;
      }
   }

   public boolean hasDecompBoundaryAfter(int c) {
      if (c < this.minDecompNoCP) {
         return true;
      } else {
         return c <= 65535 && !this.singleLeadMightHaveNonZeroFCD16(c) ? true : this.norm16HasDecompBoundaryAfter(this.getNorm16(c));
      }
   }

   public boolean norm16HasDecompBoundaryAfter(int norm16) {
      if (norm16 > this.minYesNo && !this.isHangulLVT(norm16)) {
         if (norm16 >= this.limitNoNo) {
            if (this.isMaybeYesOrNonZeroCC(norm16)) {
               return norm16 <= 64512 || norm16 == 65024;
            }

            if (norm16 < this.minMaybeNo) {
               return (norm16 & 6) <= 2;
            }
         }

         int mapping = this.getData(norm16);
         int firstUnit = this.extraData.charAt(mapping);
         if (firstUnit > 511) {
            return false;
         } else if (firstUnit <= 255) {
            return true;
         } else {
            return (firstUnit & 128) == 0 || (this.extraData.charAt(mapping - 1) & '\uff00') == 0;
         }
      } else {
         return true;
      }
   }

   public boolean isDecompInert(int c) {
      return this.isDecompYesAndZeroCC(this.getNorm16(c));
   }

   public boolean hasCompBoundaryBefore(int c) {
      return c < this.minCompNoMaybeCP || this.norm16HasCompBoundaryBefore(this.getNorm16(c));
   }

   public boolean hasCompBoundaryAfter(int c, boolean onlyContiguous) {
      return this.norm16HasCompBoundaryAfter(this.getNorm16(c), onlyContiguous);
   }

   public boolean isCompInert(int c, boolean onlyContiguous) {
      int norm16 = this.getNorm16(c);
      return this.isCompYesAndZeroCC(norm16) && (norm16 & 1) != 0 && (!onlyContiguous || isInert(norm16) || this.extraData.charAt(this.getDataForYesOrNo(norm16)) <= 511);
   }

   public boolean hasFCDBoundaryBefore(int c) {
      return this.hasDecompBoundaryBefore(c);
   }

   public boolean hasFCDBoundaryAfter(int c) {
      return this.hasDecompBoundaryAfter(c);
   }

   public boolean isFCDInert(int c) {
      return this.getFCD16(c) <= 1;
   }

   private boolean isMaybe(int norm16) {
      return this.minMaybeNo <= norm16 && norm16 <= 65024;
   }

   private boolean isMaybeYesOrNonZeroCC(int norm16) {
      return norm16 >= this.minMaybeYes;
   }

   private static boolean isInert(int norm16) {
      return norm16 == 1;
   }

   private static boolean isJamoL(int norm16) {
      return norm16 == 2;
   }

   private static boolean isJamoVT(int norm16) {
      return norm16 == 65024;
   }

   private int hangulLVT() {
      return this.minYesNoMappingsOnly | 1;
   }

   private boolean isHangulLV(int norm16) {
      return norm16 == this.minYesNo;
   }

   private boolean isHangulLVT(int norm16) {
      return norm16 == this.hangulLVT();
   }

   private boolean isCompYesAndZeroCC(int norm16) {
      return norm16 < this.minNoNo;
   }

   private boolean isDecompYesAndZeroCC(int norm16) {
      return norm16 < this.minYesNo || norm16 == 65024 || this.minMaybeYes <= norm16 && norm16 <= 64512;
   }

   private boolean isMostDecompYesAndZeroCC(int norm16) {
      return norm16 < this.minYesNo || norm16 == 64512 || norm16 == 65024;
   }

   private boolean isDecompNoAlgorithmic(int norm16) {
      return this.limitNoNo <= norm16 && norm16 < this.minMaybeNo;
   }

   private int getCCFromNoNo(int norm16) {
      int mapping = this.getDataForYesOrNo(norm16);
      return (this.extraData.charAt(mapping) & 128) != 0 ? this.extraData.charAt(mapping - 1) & 255 : 0;
   }

   int getTrailCCFromCompYesAndZeroCC(int norm16) {
      return norm16 <= this.minYesNo ? 0 : this.extraData.charAt(this.getDataForYesOrNo(norm16)) >> 8;
   }

   private int mapAlgorithmic(int c, int norm16) {
      return c + (norm16 >> 3) - this.centerNoNoDelta;
   }

   private int getDataForYesOrNo(int norm16) {
      return norm16 >> 1;
   }

   private int getDataForMaybe(int norm16) {
      return norm16 - this.minMaybeNo + this.limitNoNo >> 1;
   }

   private int getData(int norm16) {
      if (norm16 >= this.minMaybeNo) {
         norm16 = norm16 - this.minMaybeNo + this.limitNoNo;
      }

      return norm16 >> 1;
   }

   private int getCompositionsListForDecompYes(int norm16) {
      return norm16 >= 2 && 64512 > norm16 ? this.getData(norm16) : -1;
   }

   private int getCompositionsListForComposite(int norm16) {
      int list = this.getData(norm16);
      int firstUnit = this.extraData.charAt(list);
      return list + 1 + (firstUnit & 31);
   }

   private int getCompositionsList(int norm16) {
      return this.isDecompYes(norm16) ? this.getCompositionsListForDecompYes(norm16) : this.getCompositionsListForComposite(norm16);
   }

   private int decomposeShort(CharSequence s, int src, int limit, boolean stopAtCompBoundary, boolean onlyContiguous, ReorderingBuffer buffer) {
      while(true) {
         if (src < limit) {
            int c = Character.codePointAt(s, src);
            if (stopAtCompBoundary && c < this.minCompNoMaybeCP) {
               return src;
            }

            int norm16 = this.getNorm16(c);
            if (stopAtCompBoundary && this.norm16HasCompBoundaryBefore(norm16)) {
               return src;
            }

            src += Character.charCount(c);
            this.decompose(c, norm16, buffer);
            if (!stopAtCompBoundary || !this.norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
               continue;
            }

            return src;
         }

         return src;
      }
   }

   private void decompose(int c, int norm16, ReorderingBuffer buffer) {
      if (norm16 >= this.limitNoNo) {
         if (this.isMaybeYesOrNonZeroCC(norm16)) {
            buffer.append(c, getCCFromYesOrMaybeYes(norm16));
            return;
         }

         if (norm16 < this.minMaybeNo) {
            c = this.mapAlgorithmic(c, norm16);
            norm16 = this.getRawNorm16(c);
         }
      }

      if (norm16 < this.minYesNo) {
         buffer.append(c, 0);
      } else if (!this.isHangulLV(norm16) && !this.isHangulLVT(norm16)) {
         int mapping = this.getData(norm16);
         int firstUnit = this.extraData.charAt(mapping);
         int length = firstUnit & 31;
         int trailCC = firstUnit >> 8;
         int leadCC;
         if ((firstUnit & 128) != 0) {
            leadCC = this.extraData.charAt(mapping - 1) >> 8;
         } else {
            leadCC = 0;
         }

         ++mapping;
         buffer.append(this.extraData, mapping, mapping + length, true, leadCC, trailCC);
      } else {
         Normalizer2Impl.Hangul.decompose(c, buffer);
      }

   }

   private int combine(int list, int trail) {
      if (trail < 13312) {
         int key1;
         int firstUnit;
         for(key1 = trail << 1; key1 > (firstUnit = this.extraData.charAt(list)); list += 2 + (firstUnit & 1)) {
         }

         if (key1 == (firstUnit & 32766)) {
            if ((firstUnit & 1) != 0) {
               return this.extraData.charAt(list + 1) << 16 | this.extraData.charAt(list + 2);
            } else {
               return this.extraData.charAt(list + 1);
            }
         } else {
            return -1;
         }
      } else {
         int key1 = 13312 + (trail >> 9 & -2);
         int key2 = trail << 6 & '\uffff';

         while(true) {
            int firstUnit;
            while(key1 <= (firstUnit = this.extraData.charAt(list))) {
               if (key1 != (firstUnit & 32766)) {
                  return -1;
               }

               int secondUnit;
               if (key2 <= (secondUnit = this.extraData.charAt(list + 1))) {
                  if (key2 == (secondUnit & '\uffc0')) {
                     return (secondUnit & -65473) << 16 | this.extraData.charAt(list + 2);
                  }

                  return -1;
               }

               if ((firstUnit & '耀') != 0) {
                  return -1;
               }

               list += 3;
            }

            list += 2 + (firstUnit & 1);
         }
      }
   }

   private void addComposites(int list, UnicodeSet set) {
      int firstUnit;
      do {
         firstUnit = this.extraData.charAt(list);
         int compositeAndFwd;
         if ((firstUnit & 1) == 0) {
            compositeAndFwd = this.extraData.charAt(list + 1);
            list += 2;
         } else {
            compositeAndFwd = (this.extraData.charAt(list + 1) & -65473) << 16 | this.extraData.charAt(list + 2);
            list += 3;
         }

         int composite = compositeAndFwd >> 1;
         if ((compositeAndFwd & 1) != 0) {
            this.addComposites(this.getCompositionsListForComposite(this.getRawNorm16(composite)), set);
         }

         set.add(composite);
      } while((firstUnit & '耀') == 0);

   }

   private void recompose(ReorderingBuffer buffer, int recomposeStartIndex, boolean onlyContiguous) {
      StringBuilder sb = buffer.getStringBuilder();
      int p = recomposeStartIndex;
      if (recomposeStartIndex != sb.length()) {
         int compositionsList = -1;
         int starter = -1;
         boolean starterIsSupplementary = false;
         int prevCC = 0;

         while(true) {
            int c = sb.codePointAt(p);
            p += Character.charCount(c);
            int norm16 = this.getNorm16(c);
            int cc = getCCFromYesOrMaybeYes(norm16);
            if (this.isMaybe(norm16) && compositionsList >= 0 && (prevCC < cc || prevCC == 0)) {
               if (isJamoVT(norm16)) {
                  if (c < 4519) {
                     char prev = (char)(sb.charAt(starter) - 4352);
                     if (prev < 19) {
                        int pRemove = p - 1;
                        char syllable = (char)('가' + (prev * 21 + (c - 4449)) * 28);
                        char t;
                        if (p != sb.length() && (t = (char)(sb.charAt(p) - 4519)) < 28) {
                           ++p;
                           syllable += t;
                        }

                        sb.setCharAt(starter, syllable);
                        sb.delete(pRemove, p);
                        p = pRemove;
                     }
                  }

                  if (p == sb.length()) {
                     break;
                  }

                  compositionsList = -1;
                  continue;
               }

               int compositeAndFwd;
               if ((compositeAndFwd = this.combine(compositionsList, c)) >= 0) {
                  int composite = compositeAndFwd >> 1;
                  int pRemove = p - Character.charCount(c);
                  sb.delete(pRemove, p);
                  p = pRemove;
                  if (starterIsSupplementary) {
                     if (composite > 65535) {
                        sb.setCharAt(starter, UTF16.getLeadSurrogate(composite));
                        sb.setCharAt(starter + 1, UTF16.getTrailSurrogate(composite));
                     } else {
                        sb.setCharAt(starter, (char)c);
                        sb.deleteCharAt(starter + 1);
                        starterIsSupplementary = false;
                        p = pRemove - 1;
                     }
                  } else if (composite > 65535) {
                     starterIsSupplementary = true;
                     sb.setCharAt(starter, UTF16.getLeadSurrogate(composite));
                     sb.insert(starter + 1, UTF16.getTrailSurrogate(composite));
                     p = pRemove + 1;
                  } else {
                     sb.setCharAt(starter, (char)composite);
                  }

                  if (p == sb.length()) {
                     break;
                  }

                  if ((compositeAndFwd & 1) != 0) {
                     compositionsList = this.getCompositionsListForComposite(this.getRawNorm16(composite));
                  } else {
                     compositionsList = -1;
                  }
                  continue;
               }
            }

            prevCC = cc;
            if (p == sb.length()) {
               break;
            }

            if (cc == 0) {
               if ((compositionsList = this.getCompositionsListForDecompYes(norm16)) >= 0) {
                  if (c <= 65535) {
                     starterIsSupplementary = false;
                     starter = p - 1;
                  } else {
                     starterIsSupplementary = true;
                     starter = p - 2;
                  }
               }
            } else if (onlyContiguous) {
               compositionsList = -1;
            }
         }

         buffer.flush();
      }
   }

   public int composePair(int a, int b) {
      int norm16 = this.getNorm16(a);
      if (isInert(norm16)) {
         return -1;
      } else {
         int list;
         if (norm16 < this.minYesNoMappingsOnly) {
            if (isJamoL(norm16)) {
               b -= 4449;
               if (0 <= b && b < 21) {
                  return '가' + ((a - 4352) * 21 + b) * 28;
               }

               return -1;
            }

            if (this.isHangulLV(norm16)) {
               b -= 4519;
               if (0 < b && b < 28) {
                  return a + b;
               }

               return -1;
            }

            list = this.getDataForYesOrNo(norm16);
            if (norm16 > this.minYesNo) {
               list += 1 + (this.extraData.charAt(list) & 31);
            }
         } else {
            if (norm16 < this.minMaybeNoCombinesFwd || 64512 <= norm16) {
               return -1;
            }

            list = this.getDataForMaybe(norm16);
            if (norm16 < this.minMaybeYes) {
               list += 1 + (this.extraData.charAt(list) & 31);
            }
         }

         if (b >= 0 && 1114111 >= b) {
            return this.combine(list, b) >> 1;
         } else {
            return -1;
         }
      }
   }

   private boolean hasCompBoundaryBefore(int c, int norm16) {
      return c < this.minCompNoMaybeCP || this.norm16HasCompBoundaryBefore(norm16);
   }

   private boolean norm16HasCompBoundaryBefore(int norm16) {
      return norm16 < this.minNoNoCompNoMaybeCC || this.isAlgorithmicNoNo(norm16);
   }

   private boolean hasCompBoundaryBefore(CharSequence s, int src, int limit) {
      return src == limit || this.hasCompBoundaryBefore(Character.codePointAt(s, src));
   }

   private boolean norm16HasCompBoundaryAfter(int norm16, boolean onlyContiguous) {
      return (norm16 & 1) != 0 && (!onlyContiguous || this.isTrailCC01ForCompBoundaryAfter(norm16));
   }

   private boolean hasCompBoundaryAfter(CharSequence s, int start, int p, boolean onlyContiguous) {
      return start == p || this.hasCompBoundaryAfter(Character.codePointBefore(s, p), onlyContiguous);
   }

   private boolean isTrailCC01ForCompBoundaryAfter(int norm16) {
      boolean var10000;
      if (!isInert(norm16)) {
         label29: {
            if (this.isDecompNoAlgorithmic(norm16)) {
               if ((norm16 & 6) <= 2) {
                  break label29;
               }
            } else if (this.extraData.charAt(this.getDataForYesOrNo(norm16)) <= 511) {
               break label29;
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   private int findPreviousCompBoundary(CharSequence s, int p, boolean onlyContiguous) {
      while(true) {
         if (p > 0) {
            int c = Character.codePointBefore(s, p);
            int norm16 = this.getNorm16(c);
            if (!this.norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
               p -= Character.charCount(c);
               if (!this.hasCompBoundaryBefore(c, norm16)) {
                  continue;
               }
            }
         }

         return p;
      }
   }

   private int findNextCompBoundary(CharSequence s, int p, int limit, boolean onlyContiguous) {
      while(true) {
         if (p < limit) {
            int c = Character.codePointAt(s, p);
            int norm16 = this.normTrie.get(c);
            if (!this.hasCompBoundaryBefore(c, norm16)) {
               p += Character.charCount(c);
               if (!this.norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
                  continue;
               }
            }
         }

         return p;
      }
   }

   private int findPreviousFCDBoundary(CharSequence s, int p) {
      while(true) {
         if (p > 0) {
            int c = Character.codePointBefore(s, p);
            int norm16;
            if (c >= this.minDecompNoCP && !this.norm16HasDecompBoundaryAfter(norm16 = this.getNorm16(c))) {
               p -= Character.charCount(c);
               if (!this.norm16HasDecompBoundaryBefore(norm16)) {
                  continue;
               }
            }
         }

         return p;
      }
   }

   private int findNextFCDBoundary(CharSequence s, int p, int limit) {
      while(true) {
         if (p < limit) {
            int c = Character.codePointAt(s, p);
            int norm16;
            if (c >= this.minLcccCP && !this.norm16HasDecompBoundaryBefore(norm16 = this.getNorm16(c))) {
               p += Character.charCount(c);
               if (!this.norm16HasDecompBoundaryAfter(norm16)) {
                  continue;
               }
            }
         }

         return p;
      }
   }

   private int getPreviousTrailCC(CharSequence s, int start, int p) {
      return start == p ? 0 : this.getFCD16(Character.codePointBefore(s, p));
   }

   private void addToStartSet(MutableCodePointTrie mutableTrie, int origin, int decompLead) {
      int canonValue = mutableTrie.get(decompLead);
      if ((canonValue & 4194303) == 0 && origin != 0) {
         mutableTrie.set(decompLead, canonValue | origin);
      } else {
         UnicodeSet set;
         if ((canonValue & 2097152) == 0) {
            int firstOrigin = canonValue & 2097151;
            canonValue = canonValue & -2097152 | 2097152 | this.canonStartSets.size();
            mutableTrie.set(decompLead, canonValue);
            this.canonStartSets.add(set = new UnicodeSet());
            if (firstOrigin != 0) {
               set.add(firstOrigin);
            }
         } else {
            set = (UnicodeSet)this.canonStartSets.get(canonValue & 2097151);
         }

         set.add(origin);
      }

   }

   public static final class Hangul {
      public static final int JAMO_L_BASE = 4352;
      public static final int JAMO_L_END = 4370;
      public static final int JAMO_V_BASE = 4449;
      public static final int JAMO_V_END = 4469;
      public static final int JAMO_T_BASE = 4519;
      public static final int JAMO_T_END = 4546;
      public static final int HANGUL_BASE = 44032;
      public static final int HANGUL_END = 55203;
      public static final int JAMO_L_COUNT = 19;
      public static final int JAMO_V_COUNT = 21;
      public static final int JAMO_T_COUNT = 28;
      public static final int JAMO_L_LIMIT = 4371;
      public static final int JAMO_V_LIMIT = 4470;
      public static final int JAMO_VT_COUNT = 588;
      public static final int HANGUL_COUNT = 11172;
      public static final int HANGUL_LIMIT = 55204;

      public static boolean isHangul(int c) {
         return 44032 <= c && c < 55204;
      }

      public static boolean isHangulLV(int c) {
         c -= 44032;
         return 0 <= c && c < 11172 && c % 28 == 0;
      }

      public static boolean isJamoL(int c) {
         return 4352 <= c && c < 4371;
      }

      public static boolean isJamoV(int c) {
         return 4449 <= c && c < 4470;
      }

      public static boolean isJamoT(int c) {
         int t = c - 4519;
         return 0 < t && t < 28;
      }

      public static boolean isJamo(int c) {
         return 4352 <= c && c <= 4546 && (c <= 4370 || 4449 <= c && c <= 4469 || 4519 < c);
      }

      public static int decompose(int c, Appendable buffer) {
         try {
            c -= 44032;
            int c2 = c % 28;
            c /= 28;
            buffer.append((char)(4352 + c / 21));
            buffer.append((char)(4449 + c % 21));
            if (c2 == 0) {
               return 2;
            } else {
               buffer.append((char)(4519 + c2));
               return 3;
            }
         } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
         }
      }

      public static void getRawDecomposition(int c, Appendable buffer) {
         try {
            int orig = c;
            c -= 44032;
            int c2 = c % 28;
            if (c2 == 0) {
               c /= 28;
               buffer.append((char)(4352 + c / 21));
               buffer.append((char)(4449 + c % 21));
            } else {
               buffer.append((char)(orig - c2));
               buffer.append((char)(4519 + c2));
            }

         } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
         }
      }
   }

   public static final class ReorderingBuffer implements Appendable {
      private final Normalizer2Impl impl;
      private final Appendable app;
      private final StringBuilder str;
      private final boolean appIsStringBuilder;
      private int reorderStart;
      private int lastCC;
      private int codePointStart;
      private int codePointLimit;

      public ReorderingBuffer(Normalizer2Impl ni, Appendable dest, int destCapacity) {
         this.impl = ni;
         this.app = dest;
         if (this.app instanceof StringBuilder) {
            this.appIsStringBuilder = true;
            this.str = (StringBuilder)dest;
            this.str.ensureCapacity(destCapacity);
            this.reorderStart = 0;
            if (this.str.length() == 0) {
               this.lastCC = 0;
            } else {
               this.setIterator();
               this.lastCC = this.previousCC();
               if (this.lastCC > 1) {
                  while(this.previousCC() > 1) {
                  }
               }

               this.reorderStart = this.codePointLimit;
            }
         } else {
            this.appIsStringBuilder = false;
            this.str = new StringBuilder();
            this.reorderStart = 0;
            this.lastCC = 0;
         }

      }

      public boolean isEmpty() {
         return this.str.length() == 0;
      }

      public int length() {
         return this.str.length();
      }

      public int getLastCC() {
         return this.lastCC;
      }

      public StringBuilder getStringBuilder() {
         return this.str;
      }

      public boolean equals(CharSequence s, int start, int limit) {
         return Normalizer2Impl.UTF16Plus.equal(this.str, 0, this.str.length(), s, start, limit);
      }

      public void append(int c, int cc) {
         if (this.lastCC > cc && cc != 0) {
            this.insert(c, cc);
         } else {
            this.str.appendCodePoint(c);
            this.lastCC = cc;
            if (cc <= 1) {
               this.reorderStart = this.str.length();
            }
         }

      }

      public void append(CharSequence s, int start, int limit, boolean isNFD, int leadCC, int trailCC) {
         if (start != limit) {
            if (this.lastCC > leadCC && leadCC != 0) {
               int c = Character.codePointAt(s, start);
               start += Character.charCount(c);
               this.insert(c, leadCC);

               for(; start < limit; this.append(c, leadCC)) {
                  c = Character.codePointAt(s, start);
                  start += Character.charCount(c);
                  if (start < limit) {
                     if (isNFD) {
                        leadCC = Normalizer2Impl.getCCFromYesOrMaybeYes(this.impl.getNorm16(c));
                     } else {
                        leadCC = this.impl.getCC(this.impl.getNorm16(c));
                     }
                  } else {
                     leadCC = trailCC;
                  }
               }
            } else {
               if (trailCC <= 1) {
                  this.reorderStart = this.str.length() + (limit - start);
               } else if (leadCC <= 1) {
                  this.reorderStart = this.str.length() + 1;
               }

               this.str.append(s, start, limit);
               this.lastCC = trailCC;
            }

         }
      }

      public ReorderingBuffer append(char c) {
         this.str.append(c);
         this.lastCC = 0;
         this.reorderStart = this.str.length();
         return this;
      }

      public void appendZeroCC(int c) {
         this.str.appendCodePoint(c);
         this.lastCC = 0;
         this.reorderStart = this.str.length();
      }

      public ReorderingBuffer append(CharSequence s) {
         if (s.length() != 0) {
            this.str.append(s);
            this.lastCC = 0;
            this.reorderStart = this.str.length();
         }

         return this;
      }

      public ReorderingBuffer append(CharSequence s, int start, int limit) {
         if (start != limit) {
            this.str.append(s, start, limit);
            this.lastCC = 0;
            this.reorderStart = this.str.length();
         }

         return this;
      }

      public void flush() {
         if (this.appIsStringBuilder) {
            this.reorderStart = this.str.length();
         } else {
            try {
               this.app.append(this.str);
               this.str.setLength(0);
               this.reorderStart = 0;
            } catch (IOException e) {
               throw new ICUUncheckedIOException(e);
            }
         }

         this.lastCC = 0;
      }

      public ReorderingBuffer flushAndAppendZeroCC(CharSequence s, int start, int limit) {
         if (this.appIsStringBuilder) {
            this.str.append(s, start, limit);
            this.reorderStart = this.str.length();
         } else {
            try {
               this.app.append(this.str).append(s, start, limit);
               this.str.setLength(0);
               this.reorderStart = 0;
            } catch (IOException e) {
               throw new ICUUncheckedIOException(e);
            }
         }

         this.lastCC = 0;
         return this;
      }

      public void remove() {
         this.str.setLength(0);
         this.lastCC = 0;
         this.reorderStart = 0;
      }

      public void removeSuffix(int suffixLength) {
         int oldLength = this.str.length();
         this.str.delete(oldLength - suffixLength, oldLength);
         this.lastCC = 0;
         this.reorderStart = this.str.length();
      }

      private void insert(int c, int cc) {
         this.setIterator();
         this.skipPrevious();

         while(this.previousCC() > cc) {
         }

         if (c <= 65535) {
            this.str.insert(this.codePointLimit, (char)c);
            if (cc <= 1) {
               this.reorderStart = this.codePointLimit + 1;
            }
         } else {
            this.str.insert(this.codePointLimit, Character.toChars(c));
            if (cc <= 1) {
               this.reorderStart = this.codePointLimit + 2;
            }
         }

      }

      private void setIterator() {
         this.codePointStart = this.str.length();
      }

      private void skipPrevious() {
         this.codePointLimit = this.codePointStart;
         this.codePointStart = this.str.offsetByCodePoints(this.codePointStart, -1);
      }

      private int previousCC() {
         this.codePointLimit = this.codePointStart;
         if (this.reorderStart >= this.codePointStart) {
            return 0;
         } else {
            int c = this.str.codePointBefore(this.codePointStart);
            this.codePointStart -= Character.charCount(c);
            return this.impl.getCCFromYesOrMaybeYesCP(c);
         }
      }
   }

   public static final class UTF16Plus {
      public static boolean isLeadSurrogate(int c) {
         return (c & -1024) == 55296;
      }

      public static boolean isTrailSurrogate(int c) {
         return (c & -1024) == 56320;
      }

      public static boolean isSurrogate(int c) {
         return (c & -2048) == 55296;
      }

      public static boolean isSurrogateLead(int c) {
         return (c & 1024) == 0;
      }

      public static boolean equal(CharSequence s1, CharSequence s2) {
         if (s1 == s2) {
            return true;
         } else {
            int length = s1.length();
            if (length != s2.length()) {
               return false;
            } else {
               for(int i = 0; i < length; ++i) {
                  if (s1.charAt(i) != s2.charAt(i)) {
                     return false;
                  }
               }

               return true;
            }
         }
      }

      public static boolean equal(CharSequence s1, int start1, int limit1, CharSequence s2, int start2, int limit2) {
         if (limit1 - start1 != limit2 - start2) {
            return false;
         } else if (s1 == s2 && start1 == start2) {
            return true;
         } else {
            while(start1 < limit1) {
               if (s1.charAt(start1++) != s2.charAt(start2++)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private static final class IsAcceptable implements ICUBinary.Authenticate {
      private IsAcceptable() {
      }

      public boolean isDataVersionAcceptable(byte[] version) {
         return version[0] == 5;
      }
   }
}

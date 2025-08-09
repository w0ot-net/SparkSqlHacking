package com.ibm.icu.impl.coll;

import com.ibm.icu.impl.Normalizer2Impl;
import com.ibm.icu.impl.Trie2_32;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.ICUException;

public final class CollationData {
   static final int REORDER_RESERVED_BEFORE_LATIN = 4110;
   static final int REORDER_RESERVED_AFTER_LATIN = 4111;
   static final int MAX_NUM_SPECIAL_REORDER_CODES = 8;
   private static final int[] EMPTY_INT_ARRAY = new int[0];
   static final int JAMO_CE32S_LENGTH = 67;
   Trie2_32 trie;
   int[] ce32s;
   long[] ces;
   String contexts;
   public CollationData base;
   int[] jamoCE32s = new int[67];
   public Normalizer2Impl nfcImpl;
   long numericPrimary = 301989888L;
   public boolean[] compressibleBytes;
   UnicodeSet unsafeBackwardSet;
   public char[] fastLatinTable;
   char[] fastLatinTableHeader;
   int numScripts;
   char[] scriptsIndex;
   char[] scriptStarts;
   public long[] rootElements;

   CollationData(Normalizer2Impl nfc) {
      this.nfcImpl = nfc;
   }

   public int getCE32(int c) {
      return this.trie.get(c);
   }

   int getCE32FromSupplementary(int c) {
      return this.trie.get(c);
   }

   boolean isDigit(int c) {
      return c < 1632 ? c <= 57 && 48 <= c : Collation.hasCE32Tag(this.getCE32(c), 10);
   }

   public boolean isUnsafeBackward(int c, boolean numeric) {
      return this.unsafeBackwardSet.contains(c) || numeric && this.isDigit(c);
   }

   public boolean isCompressibleLeadByte(int b) {
      return this.compressibleBytes[b];
   }

   public boolean isCompressiblePrimary(long p) {
      return this.isCompressibleLeadByte((int)p >>> 24);
   }

   int getCE32FromContexts(int index) {
      return this.contexts.charAt(index) << 16 | this.contexts.charAt(index + 1);
   }

   int getIndirectCE32(int ce32) {
      assert Collation.isSpecialCE32(ce32);

      int tag = Collation.tagFromCE32(ce32);
      if (tag == 10) {
         ce32 = this.ce32s[Collation.indexFromCE32(ce32)];
      } else if (tag == 13) {
         ce32 = -1;
      } else if (tag == 11) {
         ce32 = this.ce32s[0];
      }

      return ce32;
   }

   int getFinalCE32(int ce32) {
      if (Collation.isSpecialCE32(ce32)) {
         ce32 = this.getIndirectCE32(ce32);
      }

      return ce32;
   }

   long getCEFromOffsetCE32(int c, int ce32) {
      long dataCE = this.ces[Collation.indexFromCE32(ce32)];
      return Collation.makeCE(Collation.getThreeBytePrimaryForOffsetData(c, dataCE));
   }

   long getSingleCE(int c) {
      int ce32 = this.getCE32(c);
      CollationData d;
      if (ce32 == 192) {
         d = this.base;
         ce32 = this.base.getCE32(c);
      } else {
         d = this;
      }

      while(Collation.isSpecialCE32(ce32)) {
         switch (Collation.tagFromCE32(ce32)) {
            case 0:
            case 3:
               throw new AssertionError(String.format("unexpected CE32 tag for U+%04X (CE32 0x%08x)", c, ce32));
            case 1:
               return Collation.ceFromLongPrimaryCE32(ce32);
            case 2:
               return Collation.ceFromLongSecondaryCE32(ce32);
            case 4:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
               throw new UnsupportedOperationException(String.format("there is not exactly one collation element for U+%04X (CE32 0x%08x)", c, ce32));
            case 5:
               if (Collation.lengthFromCE32(ce32) != 1) {
                  throw new UnsupportedOperationException(String.format("there is not exactly one collation element for U+%04X (CE32 0x%08x)", c, ce32));
               }

               ce32 = d.ce32s[Collation.indexFromCE32(ce32)];
               break;
            case 6:
               if (Collation.lengthFromCE32(ce32) == 1) {
                  return d.ces[Collation.indexFromCE32(ce32)];
               }

               throw new UnsupportedOperationException(String.format("there is not exactly one collation element for U+%04X (CE32 0x%08x)", c, ce32));
            case 10:
               ce32 = d.ce32s[Collation.indexFromCE32(ce32)];
               break;
            case 11:
               assert c == 0;

               ce32 = d.ce32s[0];
               break;
            case 14:
               return d.getCEFromOffsetCE32(c, ce32);
            case 15:
               return Collation.unassignedCEFromCodePoint(c);
         }
      }

      return Collation.ceFromSimpleCE32(ce32);
   }

   int getFCD16(int c) {
      return this.nfcImpl.getFCD16(c);
   }

   long getFirstPrimaryForGroup(int script) {
      int index = this.getScriptIndex(script);
      return index == 0 ? 0L : (long)this.scriptStarts[index] << 16;
   }

   public long getLastPrimaryForGroup(int script) {
      int index = this.getScriptIndex(script);
      if (index == 0) {
         return 0L;
      } else {
         long limit = (long)this.scriptStarts[index + 1];
         return (limit << 16) - 1L;
      }
   }

   public int getGroupForPrimary(long p) {
      p >>= 16;
      if (p >= (long)this.scriptStarts[1] && (long)this.scriptStarts[this.scriptStarts.length - 1] > p) {
         int index;
         for(index = 1; p >= (long)this.scriptStarts[index + 1]; ++index) {
         }

         for(int i = 0; i < this.numScripts; ++i) {
            if (this.scriptsIndex[i] == index) {
               return i;
            }
         }

         for(int i = 0; i < 8; ++i) {
            if (this.scriptsIndex[this.numScripts + i] == index) {
               return 4096 + i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   private int getScriptIndex(int script) {
      if (script < 0) {
         return 0;
      } else if (script < this.numScripts) {
         return this.scriptsIndex[script];
      } else if (script < 4096) {
         return 0;
      } else {
         script -= 4096;
         return script < 8 ? this.scriptsIndex[this.numScripts + script] : 0;
      }
   }

   public int[] getEquivalentScripts(int script) {
      int index = this.getScriptIndex(script);
      if (index == 0) {
         return EMPTY_INT_ARRAY;
      } else if (script >= 4096) {
         return new int[]{script};
      } else {
         int length = 0;

         for(int i = 0; i < this.numScripts; ++i) {
            if (this.scriptsIndex[i] == index) {
               ++length;
            }
         }

         int[] dest = new int[length];
         if (length == 1) {
            dest[0] = script;
            return dest;
         } else {
            length = 0;

            for(int i = 0; i < this.numScripts; ++i) {
               if (this.scriptsIndex[i] == index) {
                  dest[length++] = i;
               }
            }

            return dest;
         }
      }
   }

   void makeReorderRanges(int[] reorder, UVector32 ranges) {
      this.makeReorderRanges(reorder, false, ranges);
   }

   private void makeReorderRanges(int[] reorder, boolean latinMustMove, UVector32 ranges) {
      ranges.removeAllElements();
      int length = reorder.length;
      if (length != 0 && (length != 1 || reorder[0] != 103)) {
         short[] table = new short[this.scriptStarts.length - 1];
         int index = this.scriptsIndex[this.numScripts + 4110 - 4096];
         if (index != 0) {
            table[index] = 255;
         }

         index = this.scriptsIndex[this.numScripts + 4111 - 4096];
         if (index != 0) {
            table[index] = 255;
         }

         assert this.scriptStarts.length >= 2;

         assert this.scriptStarts[0] == 0;

         index = this.scriptStarts[1];

         assert index == 768;

         int highLimit = this.scriptStarts[this.scriptStarts.length - 1];

         assert highLimit == 65280;

         int specials = 0;

         for(int i = 0; i < length; ++i) {
            int reorderCode = reorder[i] - 4096;
            if (0 <= reorderCode && reorderCode < 8) {
               specials |= 1 << reorderCode;
            }
         }

         for(int i = 0; i < 8; ++i) {
            int index = this.scriptsIndex[this.numScripts + i];
            if (index != 0 && (specials & 1 << i) == 0) {
               index = this.addLowScriptRange(table, index, index);
            }
         }

         int skippedReserved = 0;
         if (specials == 0 && reorder[0] == 25 && !latinMustMove) {
            int index = this.scriptsIndex[25];

            assert index != 0;

            int start = this.scriptStarts[index];

            assert index <= start;

            skippedReserved = start - index;
            index = start;
         }

         boolean hasReorderToEnd = false;
         int i = 0;

         while(i < length) {
            int script = reorder[i++];
            if (script == 103) {
               hasReorderToEnd = true;

               while(i < length) {
                  --length;
                  script = reorder[length];
                  if (script == 103) {
                     throw new IllegalArgumentException("setReorderCodes(): duplicate UScript.UNKNOWN");
                  }

                  if (script == -1) {
                     throw new IllegalArgumentException("setReorderCodes(): UScript.DEFAULT together with other scripts");
                  }

                  int index = this.getScriptIndex(script);
                  if (index != 0) {
                     if (table[index] != 0) {
                        throw new IllegalArgumentException("setReorderCodes(): duplicate or equivalent script " + scriptCodeString(script));
                     }

                     highLimit = this.addHighScriptRange(table, index, highLimit);
                  }
               }
               break;
            }

            if (script == -1) {
               throw new IllegalArgumentException("setReorderCodes(): UScript.DEFAULT together with other scripts");
            }

            int index = this.getScriptIndex(script);
            if (index != 0) {
               if (table[index] != 0) {
                  throw new IllegalArgumentException("setReorderCodes(): duplicate or equivalent script " + scriptCodeString(script));
               }

               index = this.addLowScriptRange(table, index, index);
            }
         }

         for(int i = 1; i < this.scriptStarts.length - 1; ++i) {
            int leadByte = table[i];
            if (leadByte == 0) {
               int start = this.scriptStarts[i];
               if (!hasReorderToEnd && start > index) {
                  index = start;
               }

               index = this.addLowScriptRange(table, i, index);
            }
         }

         if (index > highLimit) {
            if (index - (skippedReserved & '\uff00') <= highLimit) {
               this.makeReorderRanges(reorder, true, ranges);
            } else {
               throw new ICUException("setReorderCodes(): reordering too many partial-primary-lead-byte scripts");
            }
         } else {
            i = 0;
            int i = 1;

            while(true) {
               int nextOffset;
               for(nextOffset = i; i < this.scriptStarts.length - 1; ++i) {
                  int newLeadByte = table[i];
                  if (newLeadByte != 255) {
                     nextOffset = newLeadByte - (this.scriptStarts[i] >> 8);
                     if (nextOffset != i) {
                        break;
                     }
                  }
               }

               if (i != 0 || i < this.scriptStarts.length - 1) {
                  ranges.addElement(this.scriptStarts[i] << 16 | i & '\uffff');
               }

               if (i == this.scriptStarts.length - 1) {
                  return;
               }

               i = nextOffset;
               ++i;
            }
         }
      }
   }

   private int addLowScriptRange(short[] table, int index, int lowStart) {
      int start = this.scriptStarts[index];
      if ((start & 255) < (lowStart & 255)) {
         lowStart += 256;
      }

      table[index] = (short)(lowStart >> 8);
      int limit = this.scriptStarts[index + 1];
      lowStart = (lowStart & '\uff00') + ((limit & '\uff00') - (start & '\uff00')) | limit & 255;
      return lowStart;
   }

   private int addHighScriptRange(short[] table, int index, int highLimit) {
      int limit = this.scriptStarts[index + 1];
      if ((limit & 255) > (highLimit & 255)) {
         highLimit -= 256;
      }

      int start = this.scriptStarts[index];
      highLimit = (highLimit & '\uff00') - ((limit & '\uff00') - (start & '\uff00')) | start & 255;
      table[index] = (short)(highLimit >> 8);
      return highLimit;
   }

   private static String scriptCodeString(int script) {
      return script < 4096 ? Integer.toString(script) : "0x" + Integer.toHexString(script);
   }
}

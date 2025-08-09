package com.ibm.icu.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class Trie2_16 extends Trie2 {
   Trie2_16() {
   }

   public static Trie2_16 createFromSerialized(ByteBuffer bytes) throws IOException {
      return (Trie2_16)Trie2.createFromSerialized(bytes);
   }

   public final int get(int codePoint) {
      if (codePoint >= 0) {
         if (codePoint < 55296 || codePoint > 56319 && codePoint <= 65535) {
            int ix = this.index[codePoint >> 5];
            ix = (ix << 2) + (codePoint & 31);
            int value = this.index[ix];
            return value;
         }

         if (codePoint <= 65535) {
            int ix = this.index[2048 + (codePoint - '\ud800' >> 5)];
            ix = (ix << 2) + (codePoint & 31);
            int value = this.index[ix];
            return value;
         }

         if (codePoint < this.highStart) {
            int ix = 2080 + (codePoint >> 11);
            ix = this.index[ix];
            ix += codePoint >> 5 & 63;
            ix = this.index[ix];
            ix = (ix << 2) + (codePoint & 31);
            int value = this.index[ix];
            return value;
         }

         if (codePoint <= 1114111) {
            int value = this.index[this.highValueIndex];
            return value;
         }
      }

      return this.errorValue;
   }

   public int getFromU16SingleLead(char codeUnit) {
      int ix = this.index[codeUnit >> 5];
      ix = (ix << 2) + (codeUnit & 31);
      int value = this.index[ix];
      return value;
   }

   public int serialize(OutputStream os) throws IOException {
      DataOutputStream dos = new DataOutputStream(os);
      int bytesWritten = 0;
      bytesWritten += this.serializeHeader(dos);

      for(int i = 0; i < this.dataLength; ++i) {
         dos.writeChar(this.index[this.data16 + i]);
      }

      bytesWritten += this.dataLength * 2;
      return bytesWritten;
   }

   public int getSerializedLength() {
      return 16 + (this.header.indexLength + this.dataLength) * 2;
   }

   int rangeEnd(int startingCP, int limit, int value) {
      int cp = startingCP;
      int block = 0;
      int index2Block = 0;

      label66:
      while(cp < limit) {
         if (cp >= 55296 && (cp <= 56319 || cp > 65535)) {
            if (cp < 65535) {
               index2Block = 2048;
               block = this.index[index2Block + (cp - '\ud800' >> 5)] << 2;
            } else {
               if (cp >= this.highStart) {
                  if (value == this.index[this.highValueIndex]) {
                     cp = limit;
                  }
                  break;
               }

               int ix = 2080 + (cp >> 11);
               index2Block = this.index[ix];
               block = this.index[index2Block + (cp >> 5 & 63)] << 2;
            }
         } else {
            index2Block = 0;
            block = this.index[cp >> 5] << 2;
         }

         if (index2Block == this.index2NullOffset) {
            if (value != this.initialValue) {
               break;
            }

            cp += 2048;
         } else if (block == this.dataNullOffset) {
            if (value != this.initialValue) {
               break;
            }

            cp += 32;
         } else {
            int startIx = block + (cp & 31);
            int limitIx = block + 32;

            for(int ix = startIx; ix < limitIx; ++ix) {
               if (this.index[ix] != value) {
                  cp += ix - startIx;
                  break label66;
               }
            }

            cp += limitIx - startIx;
         }
      }

      if (cp > limit) {
         cp = limit;
      }

      return cp - 1;
   }
}

package org.apache.commons.text.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.TextStringBuilder;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

public class StringSubstitutorReader extends FilterReader {
   private static final int EOS = -1;
   private final TextStringBuilder buffer = new TextStringBuilder();
   private boolean eos;
   private final StringMatcher prefixEscapeMatcher;
   private final char[] read1CharBuffer = new char[]{'\u0000'};
   private final StringSubstitutor stringSubstitutor;
   private int toDrain;

   public StringSubstitutorReader(Reader reader, StringSubstitutor stringSubstitutor) {
      super(reader);
      this.stringSubstitutor = new StringSubstitutor(stringSubstitutor);
      this.prefixEscapeMatcher = StringMatcherFactory.INSTANCE.charMatcher(stringSubstitutor.getEscapeChar()).andThen(stringSubstitutor.getVariablePrefixMatcher());
   }

   private int buffer(int requestReadCount) throws IOException {
      int actualReadCount = this.buffer.readFrom(super.in, requestReadCount);
      this.eos = actualReadCount == -1;
      return actualReadCount;
   }

   private int bufferOrDrainOnEos(int requestReadCount, char[] target, int targetIndex, int targetLength) throws IOException {
      int actualReadCount = this.buffer(requestReadCount);
      return this.drainOnEos(actualReadCount, target, targetIndex, targetLength);
   }

   private int drain(char[] target, int targetIndex, int targetLength) {
      int actualLen = Math.min(this.buffer.length(), targetLength);
      int drainCount = this.buffer.drainChars(0, actualLen, target, targetIndex);
      this.toDrain -= drainCount;
      if (this.buffer.isEmpty() || this.toDrain == 0) {
         this.toDrain = 0;
      }

      return drainCount;
   }

   private int drainOnEos(int readCountOrEos, char[] target, int targetIndex, int targetLength) {
      if (readCountOrEos == -1) {
         if (this.buffer.isNotEmpty()) {
            this.toDrain = this.buffer.size();
            return this.drain(target, targetIndex, targetLength);
         } else {
            return -1;
         }
      } else {
         return readCountOrEos;
      }
   }

   private boolean isBufferMatchAt(StringMatcher stringMatcher, int pos) {
      return stringMatcher.isMatch((CharSequence)this.buffer, pos) == stringMatcher.size();
   }

   private boolean isDraining() {
      return this.toDrain > 0;
   }

   public int read() throws IOException {
      int count = 0;

      do {
         count = this.read(this.read1CharBuffer, 0, 1);
         if (count == -1) {
            return -1;
         }
      } while(count < 1);

      return this.read1CharBuffer[0];
   }

   public int read(char[] target, int targetIndexIn, int targetLengthIn) throws IOException {
      if (this.eos && this.buffer.isEmpty()) {
         return -1;
      } else if (targetLengthIn <= 0) {
         return 0;
      } else {
         int targetIndex = targetIndexIn;
         int targetLength = targetLengthIn;
         if (this.isDraining()) {
            int drainCount = this.drain(target, targetIndexIn, Math.min(this.toDrain, targetLengthIn));
            if (drainCount == targetLengthIn) {
               return targetLengthIn;
            }

            targetIndex = targetIndexIn + drainCount;
            targetLength = targetLengthIn - drainCount;
         }

         int minReadLenPrefix = this.prefixEscapeMatcher.size();
         this.buffer(this.readCount(minReadLenPrefix, 0));
         if (this.buffer.length() < minReadLenPrefix && targetLength < minReadLenPrefix) {
            int drainCount = this.drain(target, targetIndex, targetLength);
            targetIndex += drainCount;
            int targetSize = targetIndex - targetIndexIn;
            return this.eos && targetSize <= 0 ? -1 : targetSize;
         } else if (this.eos) {
            this.stringSubstitutor.replaceIn(this.buffer);
            this.toDrain = this.buffer.size();
            int drainCount = this.drain(target, targetIndex, targetLength);
            targetIndex += drainCount;
            int targetSize = targetIndex - targetIndexIn;
            return this.eos && targetSize <= 0 ? -1 : targetSize;
         } else {
            int balance = 0;
            StringMatcher prefixMatcher = this.stringSubstitutor.getVariablePrefixMatcher();
            int pos = 0;

            while(true) {
               if (targetLength > 0) {
                  if (this.isBufferMatchAt(prefixMatcher, 0)) {
                     balance = 1;
                     pos = prefixMatcher.size();
                  } else {
                     if (!this.isBufferMatchAt(this.prefixEscapeMatcher, 0)) {
                        int drainCount = this.drain(target, targetIndex, 1);
                        targetIndex += drainCount;
                        targetLength -= drainCount;
                        if (this.buffer.size() >= minReadLenPrefix) {
                           continue;
                        }

                        int var19 = this.bufferOrDrainOnEos(minReadLenPrefix, target, targetIndex, targetLength);
                        if (!this.eos && !this.isDraining()) {
                           continue;
                        }

                        if (var19 != -1) {
                           targetIndex += var19;
                           int var10000 = targetLength - var19;
                        }

                        int actual = targetIndex - targetIndexIn;
                        return actual > 0 ? actual : -1;
                     }

                     balance = 1;
                     pos = this.prefixEscapeMatcher.size();
                  }
               }

               if (targetLength <= 0) {
                  return targetLengthIn;
               }

               StringMatcher suffixMatcher = this.stringSubstitutor.getVariableSuffixMatcher();
               int minReadLenSuffix = Math.max(minReadLenPrefix, suffixMatcher.size());
               this.buffer(this.readCount(minReadLenSuffix, pos));
               if (this.eos) {
                  this.stringSubstitutor.replaceIn(this.buffer);
                  this.toDrain = this.buffer.size();
                  int drainCount = this.drain(target, targetIndex, targetLength);
                  return targetIndex + drainCount - targetIndexIn;
               }

               int readCount;
               do {
                  if (this.isBufferMatchAt(suffixMatcher, pos)) {
                     --balance;
                     ++pos;
                     if (balance == 0) {
                        break;
                     }
                  } else if (this.isBufferMatchAt(prefixMatcher, pos)) {
                     ++balance;
                     pos += prefixMatcher.size();
                  } else if (this.isBufferMatchAt(this.prefixEscapeMatcher, pos)) {
                     ++balance;
                     pos += this.prefixEscapeMatcher.size();
                  } else {
                     ++pos;
                  }

                  readCount = this.buffer(this.readCount(minReadLenSuffix, pos));
               } while(readCount != -1 || pos < this.buffer.size());

               int endPos = pos + 1;
               int leftover = Math.max(0, this.buffer.size() - pos);
               this.stringSubstitutor.replaceIn((TextStringBuilder)this.buffer, 0, Math.min(this.buffer.size(), endPos));
               pos = this.buffer.size() - leftover;
               int drainLen = Math.min(targetLength, pos);
               this.toDrain = pos;
               this.drain(target, targetIndex, drainLen);
               return targetIndex - targetIndexIn + drainLen;
            }
         }
      }
   }

   private int readCount(int count, int pos) {
      int avail = this.buffer.size() - pos;
      return avail >= count ? 0 : count - avail;
   }
}

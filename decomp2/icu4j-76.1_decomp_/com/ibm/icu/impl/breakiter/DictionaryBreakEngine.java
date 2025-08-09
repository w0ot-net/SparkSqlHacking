package com.ibm.icu.impl.breakiter;

import com.ibm.icu.impl.CharacterIteration;
import com.ibm.icu.text.UnicodeSet;
import java.text.CharacterIterator;

public abstract class DictionaryBreakEngine implements LanguageBreakEngine {
   UnicodeSet fSet = new UnicodeSet();

   public boolean handles(int c) {
      return this.fSet.contains(c);
   }

   public int findBreaks(CharacterIterator text, int startPos, int endPos, DequeI foundBreaks, boolean isPhraseBreaking) {
      int result = 0;
      int start = text.getIndex();

      int current;
      for(int c = CharacterIteration.current32(text); (current = text.getIndex()) < endPos && this.fSet.contains(c); c = CharacterIteration.current32(text)) {
         CharacterIteration.next32(text);
      }

      result = this.divideUpDictionaryRange(text, start, current, foundBreaks, isPhraseBreaking);
      text.setIndex(current);
      return result;
   }

   void setCharacters(UnicodeSet set) {
      this.fSet = new UnicodeSet(set);
      this.fSet.compact();
   }

   abstract int divideUpDictionaryRange(CharacterIterator var1, int var2, int var3, DequeI var4, boolean var5);

   static class PossibleWord {
      private static final int POSSIBLE_WORD_LIST_MAX = 20;
      private int[] lengths = new int[20];
      private int[] count = new int[1];
      private int prefix;
      private int offset = -1;
      private int mark;
      private int current;

      public PossibleWord() {
      }

      public int candidates(CharacterIterator fIter, DictionaryMatcher dict, int rangeEnd) {
         int start = fIter.getIndex();
         if (start != this.offset) {
            this.offset = start;
            this.prefix = dict.matches(fIter, rangeEnd - start, this.lengths, this.count, this.lengths.length);
            if (this.count[0] <= 0) {
               fIter.setIndex(start);
            }
         }

         if (this.count[0] > 0) {
            fIter.setIndex(start + this.lengths[this.count[0] - 1]);
         }

         this.current = this.count[0] - 1;
         this.mark = this.current;
         return this.count[0];
      }

      public int acceptMarked(CharacterIterator fIter) {
         fIter.setIndex(this.offset + this.lengths[this.mark]);
         return this.lengths[this.mark];
      }

      public boolean backUp(CharacterIterator fIter) {
         if (this.current > 0) {
            fIter.setIndex(this.offset + this.lengths[--this.current]);
            return true;
         } else {
            return false;
         }
      }

      public int longestPrefix() {
         return this.prefix;
      }

      public void markCurrent() {
         this.mark = this.current;
      }
   }

   public static class DequeI implements Cloneable {
      private int[] data = new int[50];
      private int lastIdx = 4;
      private int firstIdx = 4;

      public Object clone() throws CloneNotSupportedException {
         DequeI result = (DequeI)super.clone();
         result.data = (int[])this.data.clone();
         return result;
      }

      public int size() {
         return this.firstIdx - this.lastIdx;
      }

      public boolean isEmpty() {
         return this.size() == 0;
      }

      private void grow() {
         int[] newData = new int[this.data.length * 2];
         System.arraycopy(this.data, 0, newData, 0, this.data.length);
         this.data = newData;
      }

      public void offer(int v) {
         assert this.lastIdx > 0;

         this.data[--this.lastIdx] = v;
      }

      public void push(int v) {
         if (this.firstIdx >= this.data.length) {
            this.grow();
         }

         this.data[this.firstIdx++] = v;
      }

      public int pop() {
         assert this.size() > 0;

         return this.data[--this.firstIdx];
      }

      public int peek() {
         assert this.size() > 0;

         return this.data[this.firstIdx - 1];
      }

      int peekLast() {
         assert this.size() > 0;

         return this.data[this.lastIdx];
      }

      int pollLast() {
         assert this.size() > 0;

         return this.data[this.lastIdx++];
      }

      boolean contains(int v) {
         for(int i = this.lastIdx; i < this.firstIdx; ++i) {
            if (this.data[i] == v) {
               return true;
            }
         }

         return false;
      }

      public int elementAt(int i) {
         assert i < this.size();

         return this.data[this.lastIdx + i];
      }

      public void removeAllElements() {
         this.lastIdx = this.firstIdx = 4;
      }
   }
}

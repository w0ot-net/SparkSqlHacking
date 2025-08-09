package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import java.text.CharacterIterator;

final class BreakTransliterator extends Transliterator {
   private BreakIterator bi;
   private String insertion;
   private int[] boundaries;
   private int boundaryCount;
   static final int LETTER_OR_MARK_MASK = 510;

   public BreakTransliterator(String ID, UnicodeFilter filter, BreakIterator bi, String insertion) {
      super(ID, filter);
      this.boundaries = new int[50];
      this.boundaryCount = 0;
      this.bi = bi;
      this.insertion = insertion;
   }

   public BreakTransliterator(String ID, UnicodeFilter filter) {
      this(ID, filter, (BreakIterator)null, " ");
   }

   public String getInsertion() {
      return this.insertion;
   }

   public void setInsertion(String insertion) {
      this.insertion = insertion;
   }

   public BreakIterator getBreakIterator() {
      if (this.bi == null) {
         this.bi = BreakIterator.getWordInstance(new ULocale("th_TH"));
      }

      return this.bi;
   }

   public void setBreakIterator(BreakIterator bi) {
      this.bi = bi;
   }

   protected synchronized void handleTransliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
      this.boundaryCount = 0;
      int boundary = 0;
      this.getBreakIterator();
      this.bi.setText((CharacterIterator)(new ReplaceableCharacterIterator(text, pos.start, pos.limit, pos.start)));

      for(int var8 = this.bi.first(); var8 != -1 && var8 < pos.limit; var8 = this.bi.next()) {
         if (var8 != 0) {
            int cp = UTF16.charAt(text, var8 - 1);
            int type = UCharacter.getType(cp);
            if ((1 << type & 510) != 0) {
               cp = UTF16.charAt(text, var8);
               type = UCharacter.getType(cp);
               if ((1 << type & 510) != 0) {
                  if (this.boundaryCount >= this.boundaries.length) {
                     int[] temp = new int[this.boundaries.length * 2];
                     System.arraycopy(this.boundaries, 0, temp, 0, this.boundaries.length);
                     this.boundaries = temp;
                  }

                  this.boundaries[this.boundaryCount++] = var8;
               }
            }
         }
      }

      int delta = 0;
      int lastBoundary = 0;
      if (this.boundaryCount != 0) {
         delta = this.boundaryCount * this.insertion.length();
         lastBoundary = this.boundaries[this.boundaryCount - 1];

         while(this.boundaryCount > 0) {
            boundary = this.boundaries[--this.boundaryCount];
            text.replace(boundary, boundary, this.insertion);
         }
      }

      pos.contextLimit += delta;
      pos.limit += delta;
      pos.start = incremental ? lastBoundary + delta : pos.limit;
   }

   static void register() {
      Transliterator trans = new BreakTransliterator("Any-BreakInternal", (UnicodeFilter)null);
      Transliterator.registerInstance(trans, false);
   }

   public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
      UnicodeSet myFilter = this.getFilterAsUnicodeSet(inputFilter);
      if (myFilter.size() != 0) {
         targetSet.addAll((CharSequence)this.insertion);
      }

   }

   static final class ReplaceableCharacterIterator implements CharacterIterator {
      private Replaceable text;
      private int begin;
      private int end;
      private int pos;

      public ReplaceableCharacterIterator(Replaceable text, int begin, int end, int pos) {
         if (text == null) {
            throw new NullPointerException();
         } else {
            this.text = text;
            if (begin >= 0 && begin <= end && end <= text.length()) {
               if (pos >= begin && pos <= end) {
                  this.begin = begin;
                  this.end = end;
                  this.pos = pos;
               } else {
                  throw new IllegalArgumentException("Invalid position");
               }
            } else {
               throw new IllegalArgumentException("Invalid substring range");
            }
         }
      }

      public void setText(Replaceable text) {
         if (text == null) {
            throw new NullPointerException();
         } else {
            this.text = text;
            this.begin = 0;
            this.end = text.length();
            this.pos = 0;
         }
      }

      public char first() {
         this.pos = this.begin;
         return this.current();
      }

      public char last() {
         if (this.end != this.begin) {
            this.pos = this.end - 1;
         } else {
            this.pos = this.end;
         }

         return this.current();
      }

      public char setIndex(int p) {
         if (p >= this.begin && p <= this.end) {
            this.pos = p;
            return this.current();
         } else {
            throw new IllegalArgumentException("Invalid index");
         }
      }

      public char current() {
         return this.pos >= this.begin && this.pos < this.end ? this.text.charAt(this.pos) : '\uffff';
      }

      public char next() {
         if (this.pos < this.end - 1) {
            ++this.pos;
            return this.text.charAt(this.pos);
         } else {
            this.pos = this.end;
            return '\uffff';
         }
      }

      public char previous() {
         if (this.pos > this.begin) {
            --this.pos;
            return this.text.charAt(this.pos);
         } else {
            return '\uffff';
         }
      }

      public int getBeginIndex() {
         return this.begin;
      }

      public int getEndIndex() {
         return this.end;
      }

      public int getIndex() {
         return this.pos;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof ReplaceableCharacterIterator)) {
            return false;
         } else {
            ReplaceableCharacterIterator that = (ReplaceableCharacterIterator)obj;
            if (this.hashCode() != that.hashCode()) {
               return false;
            } else if (!this.text.equals(that.text)) {
               return false;
            } else {
               return this.pos == that.pos && this.begin == that.begin && this.end == that.end;
            }
         }
      }

      public int hashCode() {
         return this.text.hashCode() ^ this.pos ^ this.begin ^ this.end;
      }

      public Object clone() {
         try {
            ReplaceableCharacterIterator other = (ReplaceableCharacterIterator)super.clone();
            return other;
         } catch (CloneNotSupportedException var2) {
            throw new ICUCloneNotSupportedException();
         }
      }
   }
}

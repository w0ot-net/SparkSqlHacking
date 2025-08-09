package com.ibm.icu.impl;

import java.text.CharacterIterator;

public class CSCharacterIterator implements CharacterIterator {
   private int index;
   private CharSequence seq;

   public CSCharacterIterator(CharSequence text) {
      if (text == null) {
         throw new NullPointerException();
      } else {
         this.seq = text;
         this.index = 0;
      }
   }

   public char first() {
      this.index = 0;
      return this.current();
   }

   public char last() {
      this.index = this.seq.length();
      return this.previous();
   }

   public char current() {
      return this.index == this.seq.length() ? '\uffff' : this.seq.charAt(this.index);
   }

   public char next() {
      if (this.index < this.seq.length()) {
         ++this.index;
      }

      return this.current();
   }

   public char previous() {
      if (this.index == 0) {
         return '\uffff';
      } else {
         --this.index;
         return this.current();
      }
   }

   public char setIndex(int position) {
      if (position >= 0 && position <= this.seq.length()) {
         this.index = position;
         return this.current();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public int getBeginIndex() {
      return 0;
   }

   public int getEndIndex() {
      return this.seq.length();
   }

   public int getIndex() {
      return this.index;
   }

   public Object clone() {
      CSCharacterIterator copy = new CSCharacterIterator(this.seq);
      copy.setIndex(this.index);
      return copy;
   }
}

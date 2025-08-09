package com.ibm.icu.impl;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.UnicodeSet;

public class StringSegment implements CharSequence {
   private final String str;
   private int start;
   private int end;
   private boolean foldCase;

   public StringSegment(String str, boolean foldCase) {
      this.str = str;
      this.start = 0;
      this.end = str.length();
      this.foldCase = foldCase;
   }

   public int getOffset() {
      return this.start;
   }

   public void setOffset(int start) {
      assert start <= this.end;

      this.start = start;
   }

   public void adjustOffset(int delta) {
      assert this.start + delta >= 0;

      assert this.start + delta <= this.end;

      this.start += delta;
   }

   public void adjustOffsetByCodePoint() {
      this.start += Character.charCount(this.getCodePoint());
   }

   public void setLength(int length) {
      assert length >= 0;

      assert this.start + length <= this.str.length();

      this.end = this.start + length;
   }

   public void resetLength() {
      this.end = this.str.length();
   }

   public int length() {
      return this.end - this.start;
   }

   public char charAt(int index) {
      return this.str.charAt(index + this.start);
   }

   public CharSequence subSequence(int start, int end) {
      return this.str.subSequence(start + this.start, end + this.start);
   }

   public int getCodePoint() {
      assert this.start < this.end;

      char lead = this.str.charAt(this.start);
      char trail;
      return Character.isHighSurrogate(lead) && this.start + 1 < this.end && Character.isLowSurrogate(trail = this.str.charAt(this.start + 1)) ? Character.toCodePoint(lead, trail) : lead;
   }

   public int codePointAt(int index) {
      return this.str.codePointAt(this.start + index);
   }

   public boolean startsWith(int otherCp) {
      return codePointsEqual(this.getCodePoint(), otherCp, this.foldCase);
   }

   public boolean startsWith(UnicodeSet uniset) {
      int cp = this.getCodePoint();
      return cp == -1 ? false : uniset.contains(cp);
   }

   public boolean startsWith(CharSequence other) {
      if (other != null && other.length() != 0 && this.length() != 0) {
         int cp1 = Character.codePointAt(this, 0);
         int cp2 = Character.codePointAt(other, 0);
         return codePointsEqual(cp1, cp2, this.foldCase);
      } else {
         return false;
      }
   }

   public int getCommonPrefixLength(CharSequence other) {
      return this.getPrefixLengthInternal(other, this.foldCase);
   }

   public int getCaseSensitivePrefixLength(CharSequence other) {
      return this.getPrefixLengthInternal(other, false);
   }

   private int getPrefixLengthInternal(CharSequence other, boolean foldCase) {
      assert other.length() != 0;

      int offset;
      int cp1;
      for(offset = 0; offset < Math.min(this.length(), other.length()); offset += Character.charCount(cp1)) {
         cp1 = Character.codePointAt(this, offset);
         int cp2 = Character.codePointAt(other, offset);
         if (!codePointsEqual(cp1, cp2, foldCase)) {
            break;
         }
      }

      return offset;
   }

   private static final boolean codePointsEqual(int cp1, int cp2, boolean foldCase) {
      if (cp1 == cp2) {
         return true;
      } else if (!foldCase) {
         return false;
      } else {
         cp1 = UCharacter.foldCase(cp1, true);
         cp2 = UCharacter.foldCase(cp2, true);
         return cp1 == cp2;
      }
   }

   public boolean contentEquals(CharSequence other) {
      return Utility.charSequenceEquals(this, other);
   }

   public String toString() {
      return this.str.substring(0, this.start) + "[" + this.str.substring(this.start, this.end) + "]" + this.str.substring(this.end);
   }

   public String asString() {
      return this.str.substring(this.start, this.end);
   }
}

package org.apache.arrow.vector.compare;

public class Range {
   private int leftStart = -1;
   private int rightStart = -1;
   private int length = -1;

   public Range() {
   }

   public Range(int leftStart, int rightStart, int length) {
      this.leftStart = leftStart;
      this.rightStart = rightStart;
      this.length = length;
   }

   public int getLeftStart() {
      return this.leftStart;
   }

   public int getRightStart() {
      return this.rightStart;
   }

   public int getLength() {
      return this.length;
   }

   public Range setLeftStart(int leftStart) {
      this.leftStart = leftStart;
      return this;
   }

   public Range setRightStart(int rightStart) {
      this.rightStart = rightStart;
      return this;
   }

   public Range setLength(int length) {
      this.length = length;
      return this;
   }
}

package org.tukaani.xz;

abstract class BCJOptions extends FilterOptions {
   private final int alignment;
   int startOffset = 0;

   BCJOptions(int alignment) {
      this.alignment = alignment;
   }

   public void setStartOffset(int startOffset) throws UnsupportedOptionsException {
      if ((startOffset & this.alignment - 1) != 0) {
         throw new UnsupportedOptionsException("Start offset must be a multiple of " + this.alignment);
      } else {
         this.startOffset = startOffset;
      }
   }

   public int getStartOffset() {
      return this.startOffset;
   }

   public int getEncoderMemoryUsage() {
      return SimpleOutputStream.getMemoryUsage();
   }

   public int getDecoderMemoryUsage() {
      return SimpleInputStream.getMemoryUsage();
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         assert false;

         throw new RuntimeException();
      }
   }
}

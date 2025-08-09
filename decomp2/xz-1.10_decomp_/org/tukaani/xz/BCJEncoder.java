package org.tukaani.xz;

class BCJEncoder extends BCJCoder implements FilterEncoder {
   private final BCJOptions options;
   private final long filterID;
   private final byte[] props;

   BCJEncoder(BCJOptions options, long filterID) {
      assert isBCJFilterID(filterID);

      int startOffset = options.getStartOffset();
      if (startOffset == 0) {
         this.props = new byte[0];
      } else {
         this.props = new byte[4];

         for(int i = 0; i < 4; ++i) {
            this.props[i] = (byte)(startOffset >>> i * 8);
         }
      }

      this.filterID = filterID;
      this.options = (BCJOptions)options.clone();
   }

   public long getFilterID() {
      return this.filterID;
   }

   public byte[] getFilterProps() {
      return this.props;
   }

   public boolean supportsFlushing() {
      return false;
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return this.options.getOutputStream(out, arrayCache);
   }
}

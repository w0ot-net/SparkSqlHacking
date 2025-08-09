package org.tukaani.xz;

import org.tukaani.xz.lzma.LZMAEncoder;

class LZMA2Encoder extends LZMA2Coder implements FilterEncoder {
   private final LZMA2Options options;
   private final byte[] props = new byte[1];

   LZMA2Encoder(LZMA2Options options) {
      if (options.getPresetDict() != null) {
         throw new IllegalArgumentException("XZ doesn't support a preset dictionary for now");
      } else {
         if (options.getMode() == 0) {
            this.props[0] = 0;
         } else {
            int d = Math.max(options.getDictSize(), 4096);
            this.props[0] = (byte)(LZMAEncoder.getDistSlot(d - 1) - 23);
         }

         this.options = (LZMA2Options)options.clone();
      }
   }

   public long getFilterID() {
      return 33L;
   }

   public byte[] getFilterProps() {
      return this.props;
   }

   public boolean supportsFlushing() {
      return true;
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return this.options.getOutputStream(out, arrayCache);
   }
}

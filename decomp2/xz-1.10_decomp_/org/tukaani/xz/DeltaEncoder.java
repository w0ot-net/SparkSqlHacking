package org.tukaani.xz;

class DeltaEncoder extends DeltaCoder implements FilterEncoder {
   private final DeltaOptions options;
   private final byte[] props = new byte[1];

   DeltaEncoder(DeltaOptions options) {
      this.props[0] = (byte)(options.getDistance() - 1);
      this.options = (DeltaOptions)options.clone();
   }

   public long getFilterID() {
      return 3L;
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

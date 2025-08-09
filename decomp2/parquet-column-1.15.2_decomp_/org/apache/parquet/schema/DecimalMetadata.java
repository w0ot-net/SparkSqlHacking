package org.apache.parquet.schema;

/** @deprecated */
@Deprecated
public class DecimalMetadata {
   private final int precision;
   private final int scale;

   public DecimalMetadata(int precision, int scale) {
      this.precision = precision;
      this.scale = scale;
   }

   public int getPrecision() {
      return this.precision;
   }

   public int getScale() {
      return this.scale;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         DecimalMetadata that = (DecimalMetadata)o;
         if (this.precision != that.precision) {
            return false;
         } else {
            return this.scale == that.scale;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.precision;
      result = 31 * result + this.scale;
      return result;
   }
}

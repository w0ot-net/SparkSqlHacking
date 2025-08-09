package org.apache.spark.network.util;

public enum ByteUnit {
   BYTE(1L),
   KiB(1024L),
   MiB(1048576L),
   GiB(1073741824L),
   TiB(1099511627776L),
   PiB(1125899906842624L);

   private final long multiplier;

   private ByteUnit(long multiplier) {
      this.multiplier = multiplier;
   }

   public long convertFrom(long d, ByteUnit u) {
      return u.convertTo(d, this);
   }

   public long convertTo(long d, ByteUnit u) {
      if (this.multiplier > u.multiplier) {
         long ratio = this.multiplier / u.multiplier;
         if (Long.MAX_VALUE / ratio < d) {
            throw new IllegalArgumentException("Conversion of " + d + " exceeds Long.MAX_VALUE in " + this.name() + ". Try a larger unit (e.g. MiB instead of KiB)");
         } else {
            return d * ratio;
         }
      } else {
         return d / (u.multiplier / this.multiplier);
      }
   }

   public long toBytes(long d) {
      if (d < 0L) {
         throw new IllegalArgumentException("Negative size value. Size must be positive: " + d);
      } else {
         return d * this.multiplier;
      }
   }

   public long toKiB(long d) {
      return this.convertTo(d, KiB);
   }

   public long toMiB(long d) {
      return this.convertTo(d, MiB);
   }

   public long toGiB(long d) {
      return this.convertTo(d, GiB);
   }

   public long toTiB(long d) {
      return this.convertTo(d, TiB);
   }

   public long toPiB(long d) {
      return this.convertTo(d, PiB);
   }

   // $FF: synthetic method
   private static ByteUnit[] $values() {
      return new ByteUnit[]{BYTE, KiB, MiB, GiB, TiB, PiB};
   }
}

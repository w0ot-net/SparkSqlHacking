package org.joda.time.chrono;

import org.joda.time.DateTimeZone;
import org.joda.time.Instant;

class GJCacheKey {
   private final DateTimeZone zone;
   private final Instant cutoverInstant;
   private final int minDaysInFirstWeek;

   GJCacheKey(DateTimeZone var1, Instant var2, int var3) {
      this.zone = var1;
      this.cutoverInstant = var2;
      this.minDaysInFirstWeek = var3;
   }

   public int hashCode() {
      int var2 = 1;
      var2 = 31 * var2 + (this.cutoverInstant == null ? 0 : this.cutoverInstant.hashCode());
      var2 = 31 * var2 + this.minDaysInFirstWeek;
      var2 = 31 * var2 + (this.zone == null ? 0 : this.zone.hashCode());
      return var2;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!(var1 instanceof GJCacheKey)) {
         return false;
      } else {
         GJCacheKey var2 = (GJCacheKey)var1;
         if (this.cutoverInstant == null) {
            if (var2.cutoverInstant != null) {
               return false;
            }
         } else if (!this.cutoverInstant.equals(var2.cutoverInstant)) {
            return false;
         }

         if (this.minDaysInFirstWeek != var2.minDaysInFirstWeek) {
            return false;
         } else {
            if (this.zone == null) {
               if (var2.zone != null) {
                  return false;
               }
            } else if (!this.zone.equals(var2.zone)) {
               return false;
            }

            return true;
         }
      }
   }
}

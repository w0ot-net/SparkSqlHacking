package org.apache.spark.sql.connector.catalog;

import java.util.Objects;
import org.apache.spark.annotation.Evolving;

@Evolving
public class IdentityColumnSpec {
   private final long start;
   private final long step;
   private final boolean allowExplicitInsert;

   public IdentityColumnSpec(long start, long step, boolean allowExplicitInsert) {
      this.start = start;
      this.step = step;
      this.allowExplicitInsert = allowExplicitInsert;
   }

   public long getStart() {
      return this.start;
   }

   public long getStep() {
      return this.step;
   }

   public boolean isAllowExplicitInsert() {
      return this.allowExplicitInsert;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         IdentityColumnSpec that = (IdentityColumnSpec)o;
         return this.start == that.start && this.step == that.step && this.allowExplicitInsert == that.allowExplicitInsert;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.start, this.step, this.allowExplicitInsert});
   }

   public String toString() {
      return "IdentityColumnSpec{start=" + this.start + ", step=" + this.step + ", allowExplicitInsert=" + this.allowExplicitInsert + "}";
   }
}

package org.apache.datasketches.fdt;

public class Group implements Comparable {
   private int count = 0;
   private double est = (double)0.0F;
   private double ub = (double)0.0F;
   private double lb = (double)0.0F;
   private double fraction = (double)0.0F;
   private double rse = (double)0.0F;
   private String priKey = null;
   private static final String fmt = "%,12d%,15.2f%,15.2f%,15.2f%12.6f%12.6f %s";
   private static final String hfmt = "%12s%15s%15s%15s%12s%12s %s";

   public Group init(String priKey, int count, double estimate, double ub, double lb, double fraction, double rse) {
      this.count = count;
      this.est = estimate;
      this.ub = ub;
      this.lb = lb;
      this.fraction = fraction;
      this.rse = rse;
      this.priKey = priKey;
      return this;
   }

   public String getPrimaryKey() {
      return this.priKey;
   }

   public int getCount() {
      return this.count;
   }

   public double getEstimate() {
      return this.est;
   }

   public double getUpperBound() {
      return this.ub;
   }

   public double getLowerBound() {
      return this.lb;
   }

   public double getFraction() {
      return this.fraction;
   }

   public double getRse() {
      return this.rse;
   }

   public String getHeader() {
      return String.format("%12s%15s%15s%15s%12s%12s %s", "Count", "Est", "UB", "LB", "Fraction", "RSE", "PriKey");
   }

   public String toString() {
      return String.format("%,12d%,15.2f%,15.2f%,15.2f%12.6f%12.6f %s", this.count, this.est, this.ub, this.lb, this.fraction, this.rse, this.priKey);
   }

   public int compareTo(Group that) {
      return that.count - this.count;
   }

   public boolean equals(Object that) {
      if (this == that) {
         return true;
      } else if (!(that instanceof Group)) {
         return false;
      } else {
         return ((Group)that).count == this.count;
      }
   }

   public int hashCode() {
      return Integer.MAX_VALUE - this.count;
   }
}

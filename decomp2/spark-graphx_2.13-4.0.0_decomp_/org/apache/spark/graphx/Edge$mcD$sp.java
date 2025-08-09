package org.apache.spark.graphx;

public class Edge$mcD$sp extends Edge {
   public double attr$mcD$sp;

   public double attr$mcD$sp() {
      return this.attr$mcD$sp;
   }

   public double attr() {
      return this.attr$mcD$sp();
   }

   public void attr$mcD$sp_$eq(final double x$1) {
      this.attr$mcD$sp = x$1;
   }

   public void attr_$eq(final double x$1) {
      this.attr$mcD$sp_$eq(x$1);
   }

   public double copy$default$3() {
      return this.copy$default$3$mcD$sp();
   }

   public double copy$default$3$mcD$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcD$sp(final long srcId, final long dstId, final double attr$mcD$sp) {
      this.attr$mcD$sp = attr$mcD$sp;
      super(srcId, dstId, (Object)null);
   }
}

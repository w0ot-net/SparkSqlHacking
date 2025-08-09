package org.apache.spark.graphx;

public class Edge$mcZ$sp extends Edge {
   public boolean attr$mcZ$sp;

   public boolean attr$mcZ$sp() {
      return this.attr$mcZ$sp;
   }

   public boolean attr() {
      return this.attr$mcZ$sp();
   }

   public void attr$mcZ$sp_$eq(final boolean x$1) {
      this.attr$mcZ$sp = x$1;
   }

   public void attr_$eq(final boolean x$1) {
      this.attr$mcZ$sp_$eq(x$1);
   }

   public boolean copy$default$3() {
      return this.copy$default$3$mcZ$sp();
   }

   public boolean copy$default$3$mcZ$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcZ$sp(final long srcId, final long dstId, final boolean attr$mcZ$sp) {
      this.attr$mcZ$sp = attr$mcZ$sp;
      super(srcId, dstId, (Object)null);
   }
}

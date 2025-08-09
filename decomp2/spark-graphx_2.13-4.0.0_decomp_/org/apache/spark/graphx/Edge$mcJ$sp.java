package org.apache.spark.graphx;

public class Edge$mcJ$sp extends Edge {
   public long attr$mcJ$sp;

   public long attr$mcJ$sp() {
      return this.attr$mcJ$sp;
   }

   public long attr() {
      return this.attr$mcJ$sp();
   }

   public void attr$mcJ$sp_$eq(final long x$1) {
      this.attr$mcJ$sp = x$1;
   }

   public void attr_$eq(final long x$1) {
      this.attr$mcJ$sp_$eq(x$1);
   }

   public long copy$default$3() {
      return this.copy$default$3$mcJ$sp();
   }

   public long copy$default$3$mcJ$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcJ$sp(final long srcId, final long dstId, final long attr$mcJ$sp) {
      this.attr$mcJ$sp = attr$mcJ$sp;
      super(srcId, dstId, (Object)null);
   }
}

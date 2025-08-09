package org.apache.spark.graphx;

public class Edge$mcI$sp extends Edge {
   public int attr$mcI$sp;

   public int attr$mcI$sp() {
      return this.attr$mcI$sp;
   }

   public int attr() {
      return this.attr$mcI$sp();
   }

   public void attr$mcI$sp_$eq(final int x$1) {
      this.attr$mcI$sp = x$1;
   }

   public void attr_$eq(final int x$1) {
      this.attr$mcI$sp_$eq(x$1);
   }

   public int copy$default$3() {
      return this.copy$default$3$mcI$sp();
   }

   public int copy$default$3$mcI$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcI$sp(final long srcId, final long dstId, final int attr$mcI$sp) {
      this.attr$mcI$sp = attr$mcI$sp;
      super(srcId, dstId, (Object)null);
   }
}

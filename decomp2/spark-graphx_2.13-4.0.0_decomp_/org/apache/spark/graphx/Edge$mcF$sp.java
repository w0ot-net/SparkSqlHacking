package org.apache.spark.graphx;

public class Edge$mcF$sp extends Edge {
   public float attr$mcF$sp;

   public float attr$mcF$sp() {
      return this.attr$mcF$sp;
   }

   public float attr() {
      return this.attr$mcF$sp();
   }

   public void attr$mcF$sp_$eq(final float x$1) {
      this.attr$mcF$sp = x$1;
   }

   public void attr_$eq(final float x$1) {
      this.attr$mcF$sp_$eq(x$1);
   }

   public float copy$default$3() {
      return this.copy$default$3$mcF$sp();
   }

   public float copy$default$3$mcF$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcF$sp(final long srcId, final long dstId, final float attr$mcF$sp) {
      this.attr$mcF$sp = attr$mcF$sp;
      super(srcId, dstId, (Object)null);
   }
}

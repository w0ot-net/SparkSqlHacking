package org.apache.spark.graphx;

public class Edge$mcB$sp extends Edge {
   public byte attr$mcB$sp;

   public byte attr$mcB$sp() {
      return this.attr$mcB$sp;
   }

   public byte attr() {
      return this.attr$mcB$sp();
   }

   public void attr$mcB$sp_$eq(final byte x$1) {
      this.attr$mcB$sp = x$1;
   }

   public void attr_$eq(final byte x$1) {
      this.attr$mcB$sp_$eq(x$1);
   }

   public byte copy$default$3() {
      return this.copy$default$3$mcB$sp();
   }

   public byte copy$default$3$mcB$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcB$sp(final long srcId, final long dstId, final byte attr$mcB$sp) {
      this.attr$mcB$sp = attr$mcB$sp;
      super(srcId, dstId, (Object)null);
   }
}

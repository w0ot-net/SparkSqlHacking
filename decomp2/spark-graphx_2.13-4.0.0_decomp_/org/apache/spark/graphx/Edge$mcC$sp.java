package org.apache.spark.graphx;

public class Edge$mcC$sp extends Edge {
   public char attr$mcC$sp;

   public char attr$mcC$sp() {
      return this.attr$mcC$sp;
   }

   public char attr() {
      return this.attr$mcC$sp();
   }

   public void attr$mcC$sp_$eq(final char x$1) {
      this.attr$mcC$sp = x$1;
   }

   public void attr_$eq(final char x$1) {
      this.attr$mcC$sp_$eq(x$1);
   }

   public char copy$default$3() {
      return this.copy$default$3$mcC$sp();
   }

   public char copy$default$3$mcC$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public Edge$mcC$sp(final long srcId, final long dstId, final char attr$mcC$sp) {
      this.attr$mcC$sp = attr$mcC$sp;
      super(srcId, dstId, (Object)null);
   }
}

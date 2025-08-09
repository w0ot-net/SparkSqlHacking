package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcC$sp extends EdgeWithLocalIds {
   public final char attr$mcC$sp;

   public char attr$mcC$sp() {
      return this.attr$mcC$sp;
   }

   public char attr() {
      return this.attr$mcC$sp();
   }

   public char copy$default$5() {
      return this.copy$default$5$mcC$sp();
   }

   public char copy$default$5$mcC$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcC$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final char attr$mcC$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcC$sp = attr$mcC$sp;
   }
}

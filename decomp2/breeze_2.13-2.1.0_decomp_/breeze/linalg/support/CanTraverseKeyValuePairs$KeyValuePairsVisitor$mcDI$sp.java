package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcDI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcDI$sp $this, final Function1 indices, final double[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final double[] arr) {
      this.visitArray$mcDI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcDI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcDI$sp $this, final Function1 indices, final double[] arr) {
      $this.visitArray$mcDI$sp(indices, arr);
   }

   default void visitArray$mcDI$sp(final Function1 indices, final double[] arr) {
      this.visitArray$mcDI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcDI$sp $this, final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcDI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcDI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcDI$sp $this, final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcDI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcDI$sp(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcDI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcJI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcJI$sp $this, final Function1 indices, final long[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final long[] arr) {
      this.visitArray$mcJI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcJI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcJI$sp $this, final Function1 indices, final long[] arr) {
      $this.visitArray$mcJI$sp(indices, arr);
   }

   default void visitArray$mcJI$sp(final Function1 indices, final long[] arr) {
      this.visitArray$mcJI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcJI$sp $this, final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcJI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcJI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcJI$sp $this, final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcJI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcJI$sp(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcJI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

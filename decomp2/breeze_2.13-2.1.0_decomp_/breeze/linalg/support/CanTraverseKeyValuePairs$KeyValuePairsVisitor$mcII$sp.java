package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcII$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcII$sp $this, final Function1 indices, final int[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final int[] arr) {
      this.visitArray$mcII$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcII$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcII$sp $this, final Function1 indices, final int[] arr) {
      $this.visitArray$mcII$sp(indices, arr);
   }

   default void visitArray$mcII$sp(final Function1 indices, final int[] arr) {
      this.visitArray$mcII$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcII$sp $this, final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcII$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcII$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcII$sp $this, final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcII$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcII$sp(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcII$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

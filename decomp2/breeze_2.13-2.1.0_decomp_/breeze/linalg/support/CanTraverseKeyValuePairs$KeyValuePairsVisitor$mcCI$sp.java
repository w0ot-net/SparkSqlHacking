package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcCI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcCI$sp $this, final Function1 indices, final char[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final char[] arr) {
      this.visitArray$mcCI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcCI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcCI$sp $this, final Function1 indices, final char[] arr) {
      $this.visitArray$mcCI$sp(indices, arr);
   }

   default void visitArray$mcCI$sp(final Function1 indices, final char[] arr) {
      this.visitArray$mcCI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcCI$sp $this, final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcCI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcCI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcCI$sp $this, final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcCI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcCI$sp(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcCI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

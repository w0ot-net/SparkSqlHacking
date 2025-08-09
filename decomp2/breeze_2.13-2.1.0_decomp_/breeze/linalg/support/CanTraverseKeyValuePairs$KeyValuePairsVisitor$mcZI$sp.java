package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcZI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcZI$sp $this, final Function1 indices, final boolean[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final boolean[] arr) {
      this.visitArray$mcZI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcZI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcZI$sp $this, final Function1 indices, final boolean[] arr) {
      $this.visitArray$mcZI$sp(indices, arr);
   }

   default void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr) {
      this.visitArray$mcZI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcZI$sp $this, final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcZI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcZI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcZI$sp $this, final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcZI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcZI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

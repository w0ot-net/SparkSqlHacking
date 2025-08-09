package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcFI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcFI$sp $this, final Function1 indices, final float[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final float[] arr) {
      this.visitArray$mcFI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcFI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcFI$sp $this, final Function1 indices, final float[] arr) {
      $this.visitArray$mcFI$sp(indices, arr);
   }

   default void visitArray$mcFI$sp(final Function1 indices, final float[] arr) {
      this.visitArray$mcFI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcFI$sp $this, final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcFI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcFI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcFI$sp $this, final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcFI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcFI$sp(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcFI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

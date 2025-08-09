package breeze.linalg.support;

import scala.Function1;
import scala.runtime.BoxedUnit;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcVI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcVI$sp $this, final Function1 indices, final BoxedUnit[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final BoxedUnit[] arr) {
      this.visitArray$mcVI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcVI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcVI$sp $this, final Function1 indices, final BoxedUnit[] arr) {
      $this.visitArray$mcVI$sp(indices, arr);
   }

   default void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr) {
      this.visitArray$mcVI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcVI$sp $this, final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcVI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcVI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcVI$sp $this, final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcVI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcVI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

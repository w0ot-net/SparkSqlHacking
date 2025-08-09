package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcSI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcSI$sp $this, final Function1 indices, final short[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final short[] arr) {
      this.visitArray$mcSI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcSI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcSI$sp $this, final Function1 indices, final short[] arr) {
      $this.visitArray$mcSI$sp(indices, arr);
   }

   default void visitArray$mcSI$sp(final Function1 indices, final short[] arr) {
      this.visitArray$mcSI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcSI$sp $this, final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcSI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcSI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcSI$sp $this, final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcSI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcSI$sp(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcSI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

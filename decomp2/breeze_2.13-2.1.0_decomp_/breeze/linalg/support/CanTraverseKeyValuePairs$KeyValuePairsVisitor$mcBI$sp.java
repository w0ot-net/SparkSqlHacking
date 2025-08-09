package breeze.linalg.support;

import scala.Function1;

public interface CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcBI$sp extends CanTraverseKeyValuePairs.KeyValuePairsVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcBI$sp $this, final Function1 indices, final byte[] arr) {
      $this.visitArray(indices, arr);
   }

   default void visitArray(final Function1 indices, final byte[] arr) {
      this.visitArray$mcBI$sp(indices, arr);
   }

   // $FF: synthetic method
   static void visitArray$mcBI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcBI$sp $this, final Function1 indices, final byte[] arr) {
      $this.visitArray$mcBI$sp(indices, arr);
   }

   default void visitArray$mcBI$sp(final Function1 indices, final byte[] arr) {
      this.visitArray$mcBI$sp(indices, arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcBI$sp $this, final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(indices, arr, offset, length, stride);
   }

   default void visitArray(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcBI$sp(indices, arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcBI$sp$(final CanTraverseKeyValuePairs$KeyValuePairsVisitor$mcBI$sp $this, final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcBI$sp(indices, arr, offset, length, stride);
   }

   default void visitArray$mcBI$sp(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
      for(int i = 0; i < length; ++i) {
         this.visit$mcBI$sp(indices.apply$mcII$sp(i * stride + offset), arr[i * stride + offset]);
      }

   }
}

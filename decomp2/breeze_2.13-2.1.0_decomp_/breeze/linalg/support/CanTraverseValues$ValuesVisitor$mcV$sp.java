package breeze.linalg.support;

import scala.runtime.BoxedUnit;

public interface CanTraverseValues$ValuesVisitor$mcV$sp extends CanTraverseValues.ValuesVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcV$sp $this, final BoxedUnit[] arr) {
      $this.visitArray(arr);
   }

   default void visitArray(final BoxedUnit[] arr) {
      this.visitArray$mcV$sp(arr);
   }

   // $FF: synthetic method
   static void visitArray$mcV$sp$(final CanTraverseValues$ValuesVisitor$mcV$sp $this, final BoxedUnit[] arr) {
      $this.visitArray$mcV$sp(arr);
   }

   default void visitArray$mcV$sp(final BoxedUnit[] arr) {
      this.visitArray$mcV$sp(arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcV$sp $this, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(arr, offset, length, stride);
   }

   default void visitArray(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcV$sp(arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcV$sp$(final CanTraverseValues$ValuesVisitor$mcV$sp $this, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcV$sp(arr, offset, length, stride);
   }

   default void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
      if (stride == 1) {
         int index$macro$2 = offset;

         for(int limit$macro$4 = length + offset; index$macro$2 < limit$macro$4; ++index$macro$2) {
            this.visit$mcV$sp(arr[index$macro$2]);
         }
      } else {
         int index$macro$7 = 0;

         for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
            this.visit$mcV$sp(arr[index$macro$7 * stride + offset]);
         }
      }

   }
}

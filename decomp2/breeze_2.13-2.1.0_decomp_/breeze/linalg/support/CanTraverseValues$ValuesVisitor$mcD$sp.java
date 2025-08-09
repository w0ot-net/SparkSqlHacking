package breeze.linalg.support;

public interface CanTraverseValues$ValuesVisitor$mcD$sp extends CanTraverseValues.ValuesVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcD$sp $this, final double[] arr) {
      $this.visitArray(arr);
   }

   default void visitArray(final double[] arr) {
      this.visitArray$mcD$sp(arr);
   }

   // $FF: synthetic method
   static void visitArray$mcD$sp$(final CanTraverseValues$ValuesVisitor$mcD$sp $this, final double[] arr) {
      $this.visitArray$mcD$sp(arr);
   }

   default void visitArray$mcD$sp(final double[] arr) {
      this.visitArray$mcD$sp(arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcD$sp $this, final double[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(arr, offset, length, stride);
   }

   default void visitArray(final double[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcD$sp(arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcD$sp$(final CanTraverseValues$ValuesVisitor$mcD$sp $this, final double[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcD$sp(arr, offset, length, stride);
   }

   default void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
      if (stride == 1) {
         int index$macro$2 = offset;

         for(int limit$macro$4 = length + offset; index$macro$2 < limit$macro$4; ++index$macro$2) {
            this.visit$mcD$sp(arr[index$macro$2]);
         }
      } else {
         int index$macro$7 = 0;

         for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
            this.visit$mcD$sp(arr[index$macro$7 * stride + offset]);
         }
      }

   }
}

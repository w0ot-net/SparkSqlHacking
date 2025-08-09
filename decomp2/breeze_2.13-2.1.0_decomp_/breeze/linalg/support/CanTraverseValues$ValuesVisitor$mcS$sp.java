package breeze.linalg.support;

public interface CanTraverseValues$ValuesVisitor$mcS$sp extends CanTraverseValues.ValuesVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcS$sp $this, final short[] arr) {
      $this.visitArray(arr);
   }

   default void visitArray(final short[] arr) {
      this.visitArray$mcS$sp(arr);
   }

   // $FF: synthetic method
   static void visitArray$mcS$sp$(final CanTraverseValues$ValuesVisitor$mcS$sp $this, final short[] arr) {
      $this.visitArray$mcS$sp(arr);
   }

   default void visitArray$mcS$sp(final short[] arr) {
      this.visitArray$mcS$sp(arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcS$sp $this, final short[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(arr, offset, length, stride);
   }

   default void visitArray(final short[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcS$sp(arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcS$sp$(final CanTraverseValues$ValuesVisitor$mcS$sp $this, final short[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcS$sp(arr, offset, length, stride);
   }

   default void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
      if (stride == 1) {
         int index$macro$2 = offset;

         for(int limit$macro$4 = length + offset; index$macro$2 < limit$macro$4; ++index$macro$2) {
            this.visit$mcS$sp(arr[index$macro$2]);
         }
      } else {
         int index$macro$7 = 0;

         for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
            this.visit$mcS$sp(arr[index$macro$7 * stride + offset]);
         }
      }

   }
}

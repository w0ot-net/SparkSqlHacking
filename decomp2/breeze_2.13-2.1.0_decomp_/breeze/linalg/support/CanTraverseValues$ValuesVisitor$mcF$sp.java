package breeze.linalg.support;

public interface CanTraverseValues$ValuesVisitor$mcF$sp extends CanTraverseValues.ValuesVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcF$sp $this, final float[] arr) {
      $this.visitArray(arr);
   }

   default void visitArray(final float[] arr) {
      this.visitArray$mcF$sp(arr);
   }

   // $FF: synthetic method
   static void visitArray$mcF$sp$(final CanTraverseValues$ValuesVisitor$mcF$sp $this, final float[] arr) {
      $this.visitArray$mcF$sp(arr);
   }

   default void visitArray$mcF$sp(final float[] arr) {
      this.visitArray$mcF$sp(arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcF$sp $this, final float[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(arr, offset, length, stride);
   }

   default void visitArray(final float[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcF$sp(arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcF$sp$(final CanTraverseValues$ValuesVisitor$mcF$sp $this, final float[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcF$sp(arr, offset, length, stride);
   }

   default void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
      if (stride == 1) {
         int index$macro$2 = offset;

         for(int limit$macro$4 = length + offset; index$macro$2 < limit$macro$4; ++index$macro$2) {
            this.visit$mcF$sp(arr[index$macro$2]);
         }
      } else {
         int index$macro$7 = 0;

         for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
            this.visit$mcF$sp(arr[index$macro$7 * stride + offset]);
         }
      }

   }
}

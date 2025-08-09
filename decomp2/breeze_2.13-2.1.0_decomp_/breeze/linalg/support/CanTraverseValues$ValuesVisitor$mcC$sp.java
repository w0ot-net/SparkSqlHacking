package breeze.linalg.support;

public interface CanTraverseValues$ValuesVisitor$mcC$sp extends CanTraverseValues.ValuesVisitor {
   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcC$sp $this, final char[] arr) {
      $this.visitArray(arr);
   }

   default void visitArray(final char[] arr) {
      this.visitArray$mcC$sp(arr);
   }

   // $FF: synthetic method
   static void visitArray$mcC$sp$(final CanTraverseValues$ValuesVisitor$mcC$sp $this, final char[] arr) {
      $this.visitArray$mcC$sp(arr);
   }

   default void visitArray$mcC$sp(final char[] arr) {
      this.visitArray$mcC$sp(arr, 0, arr.length, 1);
   }

   // $FF: synthetic method
   static void visitArray$(final CanTraverseValues$ValuesVisitor$mcC$sp $this, final char[] arr, final int offset, final int length, final int stride) {
      $this.visitArray(arr, offset, length, stride);
   }

   default void visitArray(final char[] arr, final int offset, final int length, final int stride) {
      this.visitArray$mcC$sp(arr, offset, length, stride);
   }

   // $FF: synthetic method
   static void visitArray$mcC$sp$(final CanTraverseValues$ValuesVisitor$mcC$sp $this, final char[] arr, final int offset, final int length, final int stride) {
      $this.visitArray$mcC$sp(arr, offset, length, stride);
   }

   default void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
      if (stride == 1) {
         int index$macro$2 = offset;

         for(int limit$macro$4 = length + offset; index$macro$2 < limit$macro$4; ++index$macro$2) {
            this.visit$mcC$sp(arr[index$macro$2]);
         }
      } else {
         int index$macro$7 = 0;

         for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
            this.visit$mcC$sp(arr[index$macro$7 * stride + offset]);
         }
      }

   }
}

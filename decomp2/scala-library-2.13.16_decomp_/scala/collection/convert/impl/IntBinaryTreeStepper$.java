package scala.collection.convert.impl;

import scala.Function1;

public final class IntBinaryTreeStepper$ {
   public static final IntBinaryTreeStepper$ MODULE$ = new IntBinaryTreeStepper$();

   public IntBinaryTreeStepper from(final int maxLength, final Object root, final Function1 left, final Function1 right, final Function1 extract) {
      IntBinaryTreeStepper ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, left, right, extract);
      ans.initialize(root, maxLength);
      return ans;
   }

   private IntBinaryTreeStepper$() {
   }
}

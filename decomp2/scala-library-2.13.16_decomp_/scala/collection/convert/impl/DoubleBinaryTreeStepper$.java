package scala.collection.convert.impl;

import scala.Function1;

public final class DoubleBinaryTreeStepper$ {
   public static final DoubleBinaryTreeStepper$ MODULE$ = new DoubleBinaryTreeStepper$();

   public DoubleBinaryTreeStepper from(final int maxLength, final Object root, final Function1 left, final Function1 right, final Function1 extract) {
      DoubleBinaryTreeStepper ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, left, right, extract);
      ans.initialize(root, maxLength);
      return ans;
   }

   private DoubleBinaryTreeStepper$() {
   }
}

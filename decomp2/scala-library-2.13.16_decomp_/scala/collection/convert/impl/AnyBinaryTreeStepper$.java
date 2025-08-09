package scala.collection.convert.impl;

import scala.Function1;

public final class AnyBinaryTreeStepper$ {
   public static final AnyBinaryTreeStepper$ MODULE$ = new AnyBinaryTreeStepper$();

   public AnyBinaryTreeStepper from(final int maxLength, final Object root, final Function1 left, final Function1 right, final Function1 extract) {
      AnyBinaryTreeStepper ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, left, right, extract);
      ans.initialize(root, maxLength);
      return ans;
   }

   private AnyBinaryTreeStepper$() {
   }
}

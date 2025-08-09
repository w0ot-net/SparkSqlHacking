package scala.collection.convert.impl;

import scala.Function1;

public final class LongBinaryTreeStepper$ {
   public static final LongBinaryTreeStepper$ MODULE$ = new LongBinaryTreeStepper$();

   public LongBinaryTreeStepper from(final int maxLength, final Object root, final Function1 left, final Function1 right, final Function1 extract) {
      LongBinaryTreeStepper ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, left, right, extract);
      ans.initialize(root, maxLength);
      return ans;
   }

   private LongBinaryTreeStepper$() {
   }
}

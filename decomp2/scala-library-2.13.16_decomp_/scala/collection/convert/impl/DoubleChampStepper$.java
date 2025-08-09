package scala.collection.convert.impl;

import scala.Function2;
import scala.collection.immutable.Node;

public final class DoubleChampStepper$ {
   public static final DoubleChampStepper$ MODULE$ = new DoubleChampStepper$();

   public DoubleChampStepper from(final int maxSize, final Node root, final Function2 extract) {
      DoubleChampStepper ans = new DoubleChampStepper(maxSize, extract);
      ans.initRoot(root);
      return ans;
   }

   private DoubleChampStepper$() {
   }
}

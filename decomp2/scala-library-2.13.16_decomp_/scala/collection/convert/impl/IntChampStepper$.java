package scala.collection.convert.impl;

import scala.Function2;
import scala.collection.immutable.Node;

public final class IntChampStepper$ {
   public static final IntChampStepper$ MODULE$ = new IntChampStepper$();

   public IntChampStepper from(final int maxSize, final Node root, final Function2 extract) {
      IntChampStepper ans = new IntChampStepper(maxSize, extract);
      ans.initRoot(root);
      return ans;
   }

   private IntChampStepper$() {
   }
}

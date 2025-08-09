package scala.collection.convert.impl;

import scala.Function2;
import scala.collection.immutable.Node;

public final class AnyChampStepper$ {
   public static final AnyChampStepper$ MODULE$ = new AnyChampStepper$();

   public AnyChampStepper from(final int maxSize, final Node root, final Function2 extract) {
      AnyChampStepper ans = new AnyChampStepper(maxSize, extract);
      ans.initRoot(root);
      return ans;
   }

   private AnyChampStepper$() {
   }
}

package scala.collection.convert.impl;

import scala.Function2;
import scala.collection.immutable.Node;

public final class LongChampStepper$ {
   public static final LongChampStepper$ MODULE$ = new LongChampStepper$();

   public LongChampStepper from(final int maxSize, final Node root, final Function2 extract) {
      LongChampStepper ans = new LongChampStepper(maxSize, extract);
      ans.initRoot(root);
      return ans;
   }

   private LongChampStepper$() {
   }
}

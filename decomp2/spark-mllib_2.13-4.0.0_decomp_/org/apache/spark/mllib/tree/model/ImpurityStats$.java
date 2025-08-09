package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import scala.runtime.ModuleSerializationProxy;

public final class ImpurityStats$ implements Serializable {
   public static final ImpurityStats$ MODULE$ = new ImpurityStats$();

   public boolean $lessinit$greater$default$6() {
      return true;
   }

   public ImpurityStats getInvalidImpurityStats(final ImpurityCalculator impurityCalculator) {
      return new ImpurityStats(-Double.MAX_VALUE, impurityCalculator.calculate(), impurityCalculator, (ImpurityCalculator)null, (ImpurityCalculator)null, false);
   }

   public ImpurityStats getEmptyImpurityStats(final ImpurityCalculator impurityCalculator) {
      return new ImpurityStats(Double.NaN, impurityCalculator.calculate(), impurityCalculator, (ImpurityCalculator)null, (ImpurityCalculator)null, this.$lessinit$greater$default$6());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ImpurityStats$.class);
   }

   private ImpurityStats$() {
   }
}

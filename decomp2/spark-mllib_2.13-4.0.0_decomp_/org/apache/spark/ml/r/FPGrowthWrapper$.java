package org.apache.spark.ml.r;

import org.apache.spark.ml.fpm.FPGrowth;
import org.apache.spark.ml.fpm.FPGrowthModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import scala.Predef.;
import scala.runtime.BoxedUnit;

public final class FPGrowthWrapper$ implements MLReadable {
   public static final FPGrowthWrapper$ MODULE$ = new FPGrowthWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public Object load(final String path) {
      return MLReadable.load$(this, path);
   }

   public FPGrowthWrapper fit(final Dataset data, final double minSupport, final double minConfidence, final String itemsCol, final Integer numPartitions) {
      FPGrowth fpGrowth = (new FPGrowth()).setMinSupport(minSupport).setMinConfidence(minConfidence).setItemsCol(itemsCol);
      if (numPartitions != null && .MODULE$.Integer2int(numPartitions) > 0) {
         fpGrowth.setNumPartitions(.MODULE$.Integer2int(numPartitions));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      FPGrowthModel fpGrowthModel = fpGrowth.fit(data);
      return new FPGrowthWrapper(fpGrowthModel);
   }

   public MLReader read() {
      return new FPGrowthWrapper.FPGrowthWrapperReader();
   }

   private FPGrowthWrapper$() {
   }
}

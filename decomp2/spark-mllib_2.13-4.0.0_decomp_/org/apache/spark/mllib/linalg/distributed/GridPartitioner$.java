package org.apache.spark.mllib.linalg.distributed;

import java.io.Serializable;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;

public final class GridPartitioner$ implements Serializable {
   public static final GridPartitioner$ MODULE$ = new GridPartitioner$();

   public GridPartitioner apply(final int rows, final int cols, final int rowsPerPart, final int colsPerPart) {
      return new GridPartitioner(rows, cols, rowsPerPart, colsPerPart);
   }

   public GridPartitioner apply(final int rows, final int cols, final int suggestedNumPartitions) {
      .MODULE$.require(suggestedNumPartitions > 0);
      double scale = (double)1.0F / scala.math.package..MODULE$.sqrt((double)suggestedNumPartitions);
      int rowsPerPart = (int)scala.math.package..MODULE$.round(scala.math.package..MODULE$.max(scale * (double)rows, (double)1.0F));
      int colsPerPart = (int)scala.math.package..MODULE$.round(scala.math.package..MODULE$.max(scale * (double)cols, (double)1.0F));
      return new GridPartitioner(rows, cols, rowsPerPart, colsPerPart);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GridPartitioner$.class);
   }

   private GridPartitioner$() {
   }
}

package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003!!\u000b7OU3mCRLg/Z#se>\u0014(B\u0001\u0004\b\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0001\"C\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003\u0015-\t!!\u001c7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001c\u001b\u00059\u0011B\u0001\u000f\b\u0005\u0019\u0001\u0016M]1ng\u00061A%\u001b8ji\u0012\"\u0012a\b\t\u0003)\u0001J!!I\u000b\u0003\tUs\u0017\u000e^\u0001\u000ee\u0016d\u0017\r^5wK\u0016\u0013(o\u001c:\u0016\u0003\u0011\u0002\"AG\u0013\n\u0005\u0019:!a\u0003#pk\ndW\rU1sC6\f\u0001cZ3u%\u0016d\u0017\r^5wK\u0016\u0013(o\u001c:\u0016\u0003%\u0002\"\u0001\u0006\u0016\n\u0005-*\"A\u0002#pk\ndW\r"
)
public interface HasRelativeError extends Params {
   void org$apache$spark$ml$param$shared$HasRelativeError$_setter_$relativeError_$eq(final DoubleParam x$1);

   DoubleParam relativeError();

   // $FF: synthetic method
   static double getRelativeError$(final HasRelativeError $this) {
      return $this.getRelativeError();
   }

   default double getRelativeError() {
      return BoxesRunTime.unboxToDouble(this.$(this.relativeError()));
   }

   static void $init$(final HasRelativeError $this) {
      $this.org$apache$spark$ml$param$shared$HasRelativeError$_setter_$relativeError_$eq(new DoubleParam($this, "relativeError", "the relative target precision for the approximate quantile algorithm. Must be in the range [0, 1]", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.setDefault($this.relativeError(), BoxesRunTime.boxToDouble(0.001));
   }
}

package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007I\u0011A\u0012\t\u000bI\u0002AQA\u001a\u0003\u0013!\u000b7oU8mm\u0016\u0014(B\u0001\u0004\b\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0001\"C\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003\u0015-\t!!\u001c7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001c\u001b\u00059\u0011B\u0001\u000f\b\u0005\u0019\u0001\u0016M]1ng\u00061A%\u001b8ji\u0012\"\u0012a\b\t\u0003)\u0001J!!I\u000b\u0003\tUs\u0017\u000e^\u0001\u0007g>dg/\u001a:\u0016\u0003\u0011\u00022AG\u0013(\u0013\t1sAA\u0003QCJ\fW\u000e\u0005\u0002)_9\u0011\u0011&\f\t\u0003UUi\u0011a\u000b\u0006\u0003YE\ta\u0001\u0010:p_Rt\u0014B\u0001\u0018\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001'\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059*\u0012!C4fiN{GN^3s+\u00059\u0003"
)
public interface HasSolver extends Params {
   void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1);

   Param solver();

   // $FF: synthetic method
   static String getSolver$(final HasSolver $this) {
      return $this.getSolver();
   }

   default String getSolver() {
      return (String)this.$(this.solver());
   }

   static void $init$(final HasSolver $this) {
      $this.org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(new Param($this, "solver", "the solver algorithm for optimization", .MODULE$.apply(String.class)));
   }
}

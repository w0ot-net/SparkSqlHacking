package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\r!\u000b7\u000fV8m\u0015\t1q!\u0001\u0004tQ\u0006\u0014X\r\u001a\u0006\u0003\u0011%\tQ\u0001]1sC6T!AC\u0006\u0002\u00055d'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0004\u0001M\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQ2$D\u0001\b\u0013\tarA\u0001\u0004QCJ\fWn]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0006\u0011\n\u0005\u0005*\"\u0001B+oSR\f1\u0001^8m+\u0005!\u0003C\u0001\u000e&\u0013\t1sAA\u0006E_V\u0014G.\u001a)be\u0006l\u0017AB4fiR{G.F\u0001*!\t!\"&\u0003\u0002,+\t1Ai\\;cY\u0016\u0004"
)
public interface HasTol extends Params {
   void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1);

   DoubleParam tol();

   // $FF: synthetic method
   static double getTol$(final HasTol $this) {
      return $this.getTol();
   }

   default double getTol() {
      return BoxesRunTime.unboxToDouble(this.$(this.tol()));
   }

   static void $init$(final HasTol $this) {
      $this.org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(new DoubleParam($this, "tol", "the convergence tolerance for iterative algorithms (>= 0)", ParamValidators$.MODULE$.gtEq((double)0.0F)));
   }
}

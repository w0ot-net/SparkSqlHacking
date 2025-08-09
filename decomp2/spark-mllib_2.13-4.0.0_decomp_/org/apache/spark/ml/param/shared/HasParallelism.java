package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import org.apache.spark.util.ThreadUtils.;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005!B\u0005\u0005\u0006;\u0001!\ta\b\u0005\bG\u0001\u0011\r\u0011\"\u0001%\u0011\u0015A\u0003\u0001\"\u0001*\u0011\u0019i\u0003\u0001\"\u0001\u000b]\tq\u0001*Y:QCJ\fG\u000e\\3mSNl'BA\u0004\t\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0011BC\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003\u00171\t!!\u001c7\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c2\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u0011%\u0011A\u0004\u0003\u0002\u0007!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\t\t\u0003)\u0005J!AI\u000b\u0003\tUs\u0017\u000e^\u0001\fa\u0006\u0014\u0018\r\u001c7fY&\u001cX.F\u0001&!\tQb%\u0003\u0002(\u0011\tA\u0011J\u001c;QCJ\fW.\u0001\bhKR\u0004\u0016M]1mY\u0016d\u0017n]7\u0016\u0003)\u0002\"\u0001F\u0016\n\u00051*\"aA%oi\u0006\u0019r-\u001a;Fq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yiV\tq\u0006\u0005\u00021g5\t\u0011G\u0003\u00023+\u0005Q1m\u001c8dkJ\u0014XM\u001c;\n\u0005Q\n$\u0001E#yK\u000e,H/[8o\u0007>tG/\u001a=u\u0001"
)
public interface HasParallelism extends Params {
   void org$apache$spark$ml$param$shared$HasParallelism$_setter_$parallelism_$eq(final IntParam x$1);

   IntParam parallelism();

   // $FF: synthetic method
   static int getParallelism$(final HasParallelism $this) {
      return $this.getParallelism();
   }

   default int getParallelism() {
      return BoxesRunTime.unboxToInt(this.$(this.parallelism()));
   }

   // $FF: synthetic method
   static ExecutionContext getExecutionContext$(final HasParallelism $this) {
      return $this.getExecutionContext();
   }

   default ExecutionContext getExecutionContext() {
      int var1 = this.getParallelism();
      switch (var1) {
         case 1 -> {
            return .MODULE$.sameThread();
         }
         default -> {
            return scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(.MODULE$.newDaemonCachedThreadPool(this.getClass().getSimpleName() + "-thread-pool", var1, .MODULE$.newDaemonCachedThreadPool$default$3()));
         }
      }
   }

   static void $init$(final HasParallelism $this) {
      $this.org$apache$spark$ml$param$shared$HasParallelism$_setter_$parallelism_$eq(new IntParam($this, "parallelism", "the number of threads to use when running parallel algorithms", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.parallelism().$minus$greater(BoxesRunTime.boxToInteger(1))}));
   }
}

package org.apache.spark.ml.classification;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.IntArrayParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasBlockSize;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005I4\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001B\u0005\u0005\u0006i\u0001!\tA\u000e\u0005\bu\u0001\u0011\r\u0011\"\u0002<\u0011\u0015I\u0005\u0001\"\u0002K\u0011\u001d\u0011\u0006A1A\u0005FMCq!\u001a\u0001C\u0002\u0013\u0015a\rC\u0003p\u0001\u0011\u0015\u0001O\u0001\u000eNk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\u001c)be\u0006l7O\u0003\u0002\n\u0015\u0005q1\r\\1tg&4\u0017nY1uS>t'BA\u0006\r\u0003\tiGN\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h'%\u00011#G\u000f&Q-r\u0013\u0007\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035mi\u0011\u0001C\u0005\u00039!\u0011Q\u0004\u0015:pE\u0006\u0014\u0017\u000e\\5ti&\u001c7\t\\1tg&4\u0017.\u001a:QCJ\fWn\u001d\t\u0003=\rj\u0011a\b\u0006\u0003A\u0005\naa\u001d5be\u0016$'B\u0001\u0012\u000b\u0003\u0015\u0001\u0018M]1n\u0013\t!sDA\u0004ICN\u001cV-\u001a3\u0011\u0005y1\u0013BA\u0014 \u0005)A\u0015m]'bq&#XM\u001d\t\u0003=%J!AK\u0010\u0003\r!\u000b7\u000fV8m!\tqB&\u0003\u0002.?\tY\u0001*Y:Ti\u0016\u00048+\u001b>f!\tqr&\u0003\u00021?\tI\u0001*Y:T_24XM\u001d\t\u0003=IJ!aM\u0010\u0003\u0019!\u000b7O\u00117pG.\u001c\u0016N_3\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\u000e\t\u0003)aJ!!O\u000b\u0003\tUs\u0017\u000e^\u0001\u0007Y\u0006LXM]:\u0016\u0003q\u0002\"!\u0010 \u000e\u0003\u0005J!aP\u0011\u0003\u001b%sG/\u0011:sCf\u0004\u0016M]1nQ\r\u0011\u0011i\u0012\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\t2\t!\"\u00198o_R\fG/[8o\u0013\t15IA\u0003TS:\u001cW-I\u0001I\u0003\u0015\td&\u000e\u00181\u0003%9W\r\u001e'bs\u0016\u00148/F\u0001L!\r!BJT\u0005\u0003\u001bV\u0011Q!\u0011:sCf\u0004\"\u0001F(\n\u0005A+\"aA%oi\"\u001a1!Q$\u0002\rM|GN^3s+\u0005!\u0006cA\u001fV/&\u0011a+\t\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u00031~s!!W/\u0011\u0005i+R\"A.\u000b\u0005q+\u0014A\u0002\u001fs_>$h(\u0003\u0002_+\u00051\u0001K]3eK\u001aL!\u0001Y1\u0003\rM#(/\u001b8h\u0015\tqV\u0003K\u0002\u0005\u0003\u000e\f\u0013\u0001Z\u0001\u0006e9\u0002d\u0006M\u0001\u000fS:LG/[1m/\u0016Lw\r\u001b;t+\u00059\u0007cA\u001fVQB\u0011\u0011\u000e\\\u0007\u0002U*\u00111NC\u0001\u0007Y&t\u0017\r\\4\n\u00055T'A\u0002,fGR|'\u000fK\u0002\u0006\u0003\u000e\f\u0011cZ3u\u0013:LG/[1m/\u0016Lw\r\u001b;t+\u0005A\u0007f\u0001\u0004BG\u0002"
)
public interface MultilayerPerceptronParams extends ProbabilisticClassifierParams, HasSeed, HasMaxIter, HasTol, HasStepSize, HasSolver, HasBlockSize {
   void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$layers_$eq(final IntArrayParam x$1);

   void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$solver_$eq(final Param x$1);

   void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$initialWeights_$eq(final Param x$1);

   IntArrayParam layers();

   // $FF: synthetic method
   static int[] getLayers$(final MultilayerPerceptronParams $this) {
      return $this.getLayers();
   }

   default int[] getLayers() {
      return (int[])this.$(this.layers());
   }

   Param solver();

   Param initialWeights();

   // $FF: synthetic method
   static Vector getInitialWeights$(final MultilayerPerceptronParams $this) {
      return $this.getInitialWeights();
   }

   default Vector getInitialWeights() {
      return (Vector)this.$(this.initialWeights());
   }

   // $FF: synthetic method
   static boolean $anonfun$layers$1(final int[] t) {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.intArrayOps(t), ParamValidators$.MODULE$.gt((double)0.0F)) && t.length > 1;
   }

   static void $init$(final MultilayerPerceptronParams $this) {
      $this.org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$layers_$eq(new IntArrayParam($this, "layers", "Sizes of layers from input layer to output layer. E.g., Array(780, 100, 10) means 780 inputs, one hidden layer with 100 neurons and output layer of 10 neurons.", (t) -> BoxesRunTime.boxToBoolean($anonfun$layers$1(t))));
      $this.org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$solver_$eq(new Param($this, "solver", "The solver algorithm for optimization. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])MultilayerPerceptronClassifier$.MODULE$.supportedSolvers()).mkString(", ") + ". (Default l-bfgs)", ParamValidators$.MODULE$.inArray((Object)MultilayerPerceptronClassifier$.MODULE$.supportedSolvers()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$initialWeights_$eq(new Param($this, "initialWeights", "The initial weights of the model", scala.reflect.ClassTag..MODULE$.apply(Vector.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.blockSize().$minus$greater(BoxesRunTime.boxToInteger(128)), $this.solver().$minus$greater(MultilayerPerceptronClassifier$.MODULE$.LBFGS()), $this.stepSize().$minus$greater(BoxesRunTime.boxToDouble(0.03))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

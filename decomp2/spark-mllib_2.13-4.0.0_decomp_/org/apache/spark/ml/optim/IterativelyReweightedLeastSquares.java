package org.apache.spark.ml.optim;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.util.OptionalInstrumentation;
import org.apache.spark.ml.util.OptionalInstrumentation$;
import org.apache.spark.rdd.RDD;
import scala.Function2;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma!B\t\u0013\u0001Qa\u0002\u0002\u0003\u0019\u0001\u0005\u000b\u0007I\u0011A\u0019\t\u0011Y\u0002!\u0011!Q\u0001\nIB\u0001b\u000e\u0001\u0003\u0006\u0004%\t\u0001\u000f\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005s!A\u0011\n\u0001BC\u0002\u0013\u0005!\n\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003L\u0011!y\u0005A!b\u0001\n\u0003\u0001\u0006\u0002C)\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011I\u0003!Q1A\u0005\u0002MC\u0001b\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u0016\u0005\t1\u0002\u0011)\u0019!C\u0001!\"A\u0011\f\u0001B\u0001B\u0003%Q\tC\u0003[\u0001\u0011\u00051\fC\u0003d\u0001\u0011\u0005A\rC\u0004{\u0001E\u0005I\u0011A>\t\u0013\u00055\u0001!%A\u0005\u0002\u0005=!!I%uKJ\fG/\u001b<fYf\u0014Vm^3jO\"$X\r\u001a'fCN$8+];be\u0016\u001c(BA\n\u0015\u0003\u0015y\u0007\u000f^5n\u0015\t)b#\u0001\u0002nY*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xmE\u0002\u0001;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007C\u0001\u0013.\u001d\t)3F\u0004\u0002'U5\tqE\u0003\u0002)S\u00051AH]8piz\u001a\u0001!C\u0001!\u0013\tas$A\u0004qC\u000e\\\u0017mZ3\n\u00059z#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0017 \u00031Ig.\u001b;jC2lu\u000eZ3m+\u0005\u0011\u0004CA\u001a5\u001b\u0005\u0011\u0012BA\u001b\u0013\u0005e9V-[4ii\u0016$G*Z1tiN\u000bX/\u0019:fg6{G-\u001a7\u0002\u001b%t\u0017\u000e^5bY6{G-\u001a7!\u00031\u0011Xm^3jO\"$h)\u001e8d+\u0005I\u0004#\u0002\u0010;yI\u0012\u0015BA\u001e \u0005%1UO\\2uS>t'\u0007\u0005\u0002>\u00016\taH\u0003\u0002@)\u00059a-Z1ukJ,\u0017BA!?\u00059yeMZ:fi&s7\u000f^1oG\u0016\u0004BAH\"F\u000b&\u0011Ai\b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005y1\u0015BA$ \u0005\u0019!u.\u001e2mK\u0006i!/Z<fS\u001eDGOR;oG\u0002\nABZ5u\u0013:$XM]2faR,\u0012a\u0013\t\u0003=1K!!T\u0010\u0003\u000f\t{w\u000e\\3b]\u0006ia-\u001b;J]R,'oY3qi\u0002\n\u0001B]3h!\u0006\u0014\u0018-\\\u000b\u0002\u000b\u0006I!/Z4QCJ\fW\u000eI\u0001\b[\u0006D\u0018\n^3s+\u0005!\u0006C\u0001\u0010V\u0013\t1vDA\u0002J]R\f\u0001\"\\1y\u0013R,'\u000fI\u0001\u0004i>d\u0017\u0001\u0002;pY\u0002\na\u0001P5oSRtDc\u0002/^=~\u0003\u0017M\u0019\t\u0003g\u0001AQ\u0001M\u0007A\u0002IBQaN\u0007A\u0002eBQ!S\u0007A\u0002-CQaT\u0007A\u0002\u0015CQAU\u0007A\u0002QCQ\u0001W\u0007A\u0002\u0015\u000b1AZ5u)\u0011)\u0007\u000e\u001d=\u0011\u0005M2\u0017BA4\u0013\u0005\u0019JE/\u001a:bi&4X\r\\=SK^,\u0017n\u001a5uK\u0012dU-Y:u'F,\u0018M]3t\u001b>$W\r\u001c\u0005\u0006S:\u0001\rA[\u0001\nS:\u001cH/\u00198dKN\u00042a\u001b8=\u001b\u0005a'BA7\u0017\u0003\r\u0011H\rZ\u0005\u0003_2\u00141A\u0015#E\u0011\u001d\th\u0002%AA\u0002I\fQ!\u001b8tiJ\u0004\"a\u001d<\u000e\u0003QT!!\u001e\u000b\u0002\tU$\u0018\u000e\\\u0005\u0003oR\u0014qc\u00149uS>t\u0017\r\\%ogR\u0014X/\\3oi\u0006$\u0018n\u001c8\t\u000fet\u0001\u0013!a\u0001)\u0006)A-\u001a9uQ\u0006ia-\u001b;%I\u00164\u0017-\u001e7uII*\u0012\u0001 \u0016\u0003ev\\\u0013A \t\u0004\u007f\u0006%QBAA\u0001\u0015\u0011\t\u0019!!\u0002\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0004?\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005-\u0011\u0011\u0001\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00044ji\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u0012)\u0012A+ "
)
public class IterativelyReweightedLeastSquares implements Serializable {
   private final WeightedLeastSquaresModel initialModel;
   private final Function2 reweightFunc;
   private final boolean fitIntercept;
   private final double regParam;
   private final int maxIter;
   private final double tol;

   public WeightedLeastSquaresModel initialModel() {
      return this.initialModel;
   }

   public Function2 reweightFunc() {
      return this.reweightFunc;
   }

   public boolean fitIntercept() {
      return this.fitIntercept;
   }

   public double regParam() {
      return this.regParam;
   }

   public int maxIter() {
      return this.maxIter;
   }

   public double tol() {
      return this.tol;
   }

   public IterativelyReweightedLeastSquaresModel fit(final RDD instances, final OptionalInstrumentation instr, final int depth) {
      boolean converged = false;
      IntRef iter = IntRef.create(0);
      WeightedLeastSquaresModel model = this.initialModel();
      ObjectRef oldModel = ObjectRef.create((Object)null);

      while(iter.elem < this.maxIter() && !converged) {
         oldModel.elem = model;
         RDD newInstances = instances.map((instance) -> {
            Tuple2 var5 = (Tuple2)this.reweightFunc().apply(instance, (WeightedLeastSquaresModel)oldModel.elem);
            if (var5 != null) {
               double newLabel = var5._1$mcD$sp();
               double newWeight = var5._2$mcD$sp();
               Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(newLabel, newWeight);
               double newLabelx = ((Tuple2)var4)._1$mcD$sp();
               double newWeight = ((Tuple2)var4)._2$mcD$sp();
               return new Instance(newLabelx, newWeight, instance.features());
            } else {
               throw new MatchError(var5);
            }
         }, .MODULE$.apply(Instance.class));
         model = (new WeightedLeastSquares(this.fitIntercept(), this.regParam(), (double)0.0F, false, false, WeightedLeastSquares$.MODULE$.$lessinit$greater$default$6(), WeightedLeastSquares$.MODULE$.$lessinit$greater$default$7(), WeightedLeastSquares$.MODULE$.$lessinit$greater$default$8())).fit(newInstances, instr, depth);
         DenseVector oldCoefficients = ((WeightedLeastSquaresModel)oldModel.elem).coefficients();
         DenseVector coefficients = model.coefficients();
         org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)-1.0F, coefficients, oldCoefficients);
         double maxTolOfCoefficients = BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.doubleArrayOps(oldCoefficients.toArray()), BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x, y) -> scala.math.package..MODULE$.max(scala.math.package..MODULE$.abs(x), scala.math.package..MODULE$.abs(y))));
         double maxTol = scala.math.package..MODULE$.max(maxTolOfCoefficients, scala.math.package..MODULE$.abs(((WeightedLeastSquaresModel)oldModel.elem).intercept() - model.intercept()));
         if (maxTol < this.tol()) {
            converged = true;
            instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> org.apache.spark.util.MavenUtils..MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"IRLS converged in ", " iterations."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iter.elem))})))));
         }

         instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> org.apache.spark.util.MavenUtils..MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Iteration ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iter.elem))}))).$plus(org.apache.spark.util.MavenUtils..MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"relative tolerance = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RELATIVE_TOLERANCE..MODULE$, BoxesRunTime.boxToDouble(maxTol))}))))));
         ++iter.elem;
         if (iter.elem == this.maxIter()) {
            instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> org.apache.spark.util.MavenUtils..MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"IRLS reached the max number of iterations: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iter.elem))})))));
         }
      }

      return new IterativelyReweightedLeastSquaresModel(model.coefficients(), model.intercept(), model.diagInvAtWA(), iter.elem);
   }

   public OptionalInstrumentation fit$default$2() {
      return OptionalInstrumentation$.MODULE$.create(IterativelyReweightedLeastSquares.class);
   }

   public int fit$default$3() {
      return 2;
   }

   public IterativelyReweightedLeastSquares(final WeightedLeastSquaresModel initialModel, final Function2 reweightFunc, final boolean fitIntercept, final double regParam, final int maxIter, final double tol) {
      this.initialModel = initialModel;
      this.reweightFunc = reweightFunc;
      this.fitIntercept = fitIntercept;
      this.regParam = regParam;
      this.maxIter = maxIter;
      this.tol = tol;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasElasticNetParam;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasLoss;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b\u0001\u0003\u0005\n!\u0003\r\t!C\n\t\u000b\u0011\u0003A\u0011\u0001$\t\u000f)\u0003!\u0019!C#\u0017\"9A\r\u0001b\u0001\n\u000bZ\u0005b\u00025\u0001\u0005\u0004%)!\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\u0006i\u0002!\t&\u001e\u0005\u000f\u0003+\u0001\u0001\u0013aA\u0001\u0002\u0013%\u0011qCA\u0010\u0005Ya\u0015N\\3beJ+wM]3tg&|g\u000eU1sC6\u001c(B\u0001\u0006\f\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003\u00195\t!!\u001c7\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001cb\u0002\u0001\u000b\u001b=\u0019JCf\f\u001a6qmr\u0014\t\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00037qi\u0011aC\u0005\u0003;-\u0011q\u0002\u0015:fI&\u001cGo\u001c:QCJ\fWn\u001d\t\u0003?\u0011j\u0011\u0001\t\u0006\u0003C\t\naa\u001d5be\u0016$'BA\u0012\f\u0003\u0015\u0001\u0018M]1n\u0013\t)\u0003EA\u0006ICN\u0014Vm\u001a)be\u0006l\u0007CA\u0010(\u0013\tA\u0003E\u0001\nICN,E.Y:uS\u000etU\r\u001e)be\u0006l\u0007CA\u0010+\u0013\tY\u0003E\u0001\u0006ICNl\u0015\r_%uKJ\u0004\"aH\u0017\n\u00059\u0002#A\u0002%bgR{G\u000e\u0005\u0002 a%\u0011\u0011\u0007\t\u0002\u0010\u0011\u0006\u001ch)\u001b;J]R,'oY3qiB\u0011qdM\u0005\u0003i\u0001\u0012!\u0003S1t'R\fg\u000eZ1sI&T\u0018\r^5p]B\u0011qDN\u0005\u0003o\u0001\u0012A\u0002S1t/\u0016Lw\r\u001b;D_2\u0004\"aH\u001d\n\u0005i\u0002#!\u0003%bgN{GN^3s!\tyB(\u0003\u0002>A\t\u0019\u0002*Y:BO\u001e\u0014XmZ1uS>tG)\u001a9uQB\u0011qdP\u0005\u0003\u0001\u0002\u0012q\u0001S1t\u0019>\u001c8\u000f\u0005\u0002 \u0005&\u00111\t\t\u0002\u0014\u0011\u0006\u001cX*\u0019=CY>\u001c7nU5{K&sWJQ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tq\t\u0005\u0002\u0016\u0011&\u0011\u0011J\u0006\u0002\u0005+:LG/\u0001\u0004t_24XM]\u000b\u0002\u0019B\u0019QJ\u0014)\u000e\u0003\tJ!a\u0014\u0012\u0003\u000bA\u000b'/Y7\u0011\u0005ECfB\u0001*W!\t\u0019f#D\u0001U\u0015\t)V)\u0001\u0004=e>|GOP\u0005\u0003/Z\ta\u0001\u0015:fI\u00164\u0017BA-[\u0005\u0019\u0019FO]5oO*\u0011qK\u0006\u0015\u0004\u0005q\u0013\u0007CA/a\u001b\u0005q&BA0\u000e\u0003)\tgN\\8uCRLwN\\\u0005\u0003Cz\u0013QaU5oG\u0016\f\u0013aY\u0001\u0006c92d\u0006M\u0001\u0005Y>\u001c8\u000fK\u0002\u00049\u001a\f\u0013aZ\u0001\u0006e9\u001ad\u0006M\u0001\bKB\u001c\u0018\u000e\\8o+\u0005Q\u0007CA'l\u0013\ta'EA\u0006E_V\u0014G.\u001a)be\u0006l\u0007f\u0001\u0003]M\u0006Qq-\u001a;FaNLGn\u001c8\u0016\u0003A\u0004\"!F9\n\u0005I4\"A\u0002#pk\ndW\rK\u0002\u00069\u001a\f!D^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$bA\u001e@\u0002\u0002\u0005-\u0001CA<}\u001b\u0005A(BA={\u0003\u0015!\u0018\u0010]3t\u0015\tYX\"A\u0002tc2L!! =\u0003\u0015M#(/^2u)f\u0004X\rC\u0003\u0000\r\u0001\u0007a/\u0001\u0004tG\",W.\u0019\u0005\b\u0003\u00071\u0001\u0019AA\u0003\u0003\u001d1\u0017\u000e\u001e;j]\u001e\u00042!FA\u0004\u0013\r\tIA\u0006\u0002\b\u0005>|G.Z1o\u0011\u001d\tiA\u0002a\u0001\u0003\u001f\t\u0001CZ3biV\u0014Xm\u001d#bi\u0006$\u0016\u0010]3\u0011\u0007]\f\t\"C\u0002\u0002\u0014a\u0014\u0001\u0002R1uCRK\b/Z\u0001!gV\u0004XM\u001d\u0013wC2LG-\u0019;f\u0003:$GK]1og\u001a|'/\\*dQ\u0016l\u0017\rF\u0004w\u00033\tY\"!\b\t\u000b}<\u0001\u0019\u0001<\t\u000f\u0005\rq\u00011\u0001\u0002\u0006!9\u0011QB\u0004A\u0002\u0005=\u0011B\u0001;\u001d\u0001"
)
public interface LinearRegressionParams extends PredictorParams, HasRegParam, HasElasticNetParam, HasMaxIter, HasTol, HasFitIntercept, HasStandardization, HasWeightCol, HasSolver, HasAggregationDepth, HasLoss, HasMaxBlockSizeInMB {
   void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$solver_$eq(final Param x$1);

   void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$loss_$eq(final Param x$1);

   void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$epsilon_$eq(final DoubleParam x$1);

   // $FF: synthetic method
   StructType org$apache$spark$ml$regression$LinearRegressionParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   Param solver();

   Param loss();

   DoubleParam epsilon();

   // $FF: synthetic method
   static double getEpsilon$(final LinearRegressionParams $this) {
      return $this.getEpsilon();
   }

   default double getEpsilon() {
      return BoxesRunTime.unboxToDouble(this.$(this.epsilon()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final LinearRegressionParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      if (fitting) {
         Object var10000 = this.$(this.loss());
         String var4 = LinearRegression$.MODULE$.Huber();
         if (var10000 == null) {
            if (var4 != null) {
               return this.org$apache$spark$ml$regression$LinearRegressionParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
            }
         } else if (!var10000.equals(var4)) {
            return this.org$apache$spark$ml$regression$LinearRegressionParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
         }

         boolean var7;
         label29: {
            label28: {
               var6 = .MODULE$;
               Object var10001 = this.$(this.solver());
               String var5 = LinearRegression$.MODULE$.Normal();
               if (var10001 == null) {
                  if (var5 != null) {
                     break label28;
                  }
               } else if (!var10001.equals(var5)) {
                  break label28;
               }

               var7 = false;
               break label29;
            }

            var7 = true;
         }

         var6.require(var7, () -> "LinearRegression with huber loss doesn't support normal solver, please change solver to auto or l-bfgs.");
         .MODULE$.require(BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())) == (double)0.0F, () -> "LinearRegression with huber loss only supports L2 regularization, but got elasticNetParam = " + this.getElasticNetParam() + ".");
      }

      return this.org$apache$spark$ml$regression$LinearRegressionParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   static void $init$(final LinearRegressionParams $this) {
      $this.org$apache$spark$ml$regression$LinearRegressionParams$_setter_$solver_$eq(new Param($this, "solver", "The solver algorithm for optimization. Supported options: " + .MODULE$.wrapRefArray((Object[])LinearRegression$.MODULE$.supportedSolvers()).mkString(", ") + ". (Default auto)", ParamValidators$.MODULE$.inArray((Object)LinearRegression$.MODULE$.supportedSolvers()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$LinearRegressionParams$_setter_$loss_$eq(new Param($this, "loss", "The loss function to be optimized. Supported options: " + .MODULE$.wrapRefArray((Object[])LinearRegression$.MODULE$.supportedLosses()).mkString(", ") + ". (Default squaredError)", ParamValidators$.MODULE$.inArray((Object)LinearRegression$.MODULE$.supportedLosses()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$LinearRegressionParams$_setter_$epsilon_$eq(new DoubleParam($this, "epsilon", "The shape parameter to control the amount of robustness. Must be > 1.0.", ParamValidators$.MODULE$.gt((double)1.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.regParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.fitIntercept().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.standardization().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.elasticNetParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.solver().$minus$greater(LinearRegression$.MODULE$.Auto()), $this.aggregationDepth().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.loss().$minus$greater(LinearRegression$.MODULE$.SquaredError()), $this.epsilon().$minus$greater(BoxesRunTime.boxToDouble(1.35)), $this.maxBlockSizeInMB().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.ml.regression;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.storage.StorageLevel;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001B\f\u0019\u0001\rB\u0001\u0002\u0012\u0001\u0003\u0006\u0004%\t%\u0012\u0005\t9\u0002\u0011\t\u0011)A\u0005\r\")a\f\u0001C\u0001?\")a\f\u0001C\u0001G\")Q\r\u0001C\u0001M\")q\u000e\u0001C\u0001a\")a\u000f\u0001C\u0001o\")!\u0010\u0001C\u0001w\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\tY\u0002\u0001C\u0001\u0003;Aq!a\t\u0001\t\u0003\t)\u0003C\u0004\u0002,\u0001!\t!!\f\t\u000f\u0005M\u0002\u0001\"\u0001\u00026!9\u0011\u0011\t\u0001\u0005R\u0005\r\u0003bBA7\u0001\u0011\u0005\u0013qN\u0004\b\u0003\u000bC\u0002\u0012AAD\r\u00199\u0002\u0004#\u0001\u0002\n\"1al\u0005C\u0001\u0003OCq!!+\u0014\t\u0003\nY\u000bC\u0005\u00024N\t\t\u0011\"\u0003\u00026\nYa)\u0014*fOJ,7o]8s\u0015\tI\"$\u0001\u0006sK\u001e\u0014Xm]:j_:T!a\u0007\u000f\u0002\u00055d'BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0004\u0001M1\u0001\u0001\n\u001a6qy\u0002R!\n\u0014)]=j\u0011\u0001G\u0005\u0003Oa\u0011\u0011BU3he\u0016\u001c8o\u001c:\u0011\u0005%bS\"\u0001\u0016\u000b\u0005-R\u0012A\u00027j]\u0006dw-\u0003\u0002.U\t1a+Z2u_J\u0004\"!\n\u0001\u0011\u0005\u0015\u0002\u0014BA\u0019\u0019\u0005E1UJU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003KMJ!\u0001\u000e\r\u0003+\u0019\u000b7\r^8sSj\fG/[8o\u001b\u0006\u001c\u0007.\u001b8fgB\u0011QEN\u0005\u0003oa\u0011\u0011CR'SK\u001e\u0014Xm]:peB\u000b'/Y7t!\tID(D\u0001;\u0015\tY$$\u0001\u0003vi&d\u0017BA\u001f;\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\u0004\"a\u0010\"\u000e\u0003\u0001S!!\u0011\u000f\u0002\u0011%tG/\u001a:oC2L!a\u0011!\u0003\u000f1{wmZ5oO\u0006\u0019Q/\u001b3\u0016\u0003\u0019\u0003\"a\u0012)\u000f\u0005!s\u0005CA%M\u001b\u0005Q%BA&#\u0003\u0019a$o\\8u})\tQ*A\u0003tG\u0006d\u0017-\u0003\u0002P\u0019\u00061\u0001K]3eK\u001aL!!\u0015*\u0003\rM#(/\u001b8h\u0015\tyE\nK\u0002\u0002)j\u0003\"!\u0016-\u000e\u0003YS!a\u0016\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Z-\n)1+\u001b8dK\u0006\n1,A\u00034]Ar\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002U5\u00061A(\u001b8jiz\"\"A\f1\t\u000b\u0011\u001b\u0001\u0019\u0001$)\u0007\u0001$&\fK\u0002\u0004)j#\u0012A\f\u0015\u0004\tQS\u0016!D:fi\u001a\u000b7\r^8s'&TX\r\u0006\u0002hQ6\t\u0001\u0001C\u0003j\u000b\u0001\u0007!.A\u0003wC2,X\r\u0005\u0002lY6\tA*\u0003\u0002n\u0019\n\u0019\u0011J\u001c;)\u0007\u0015!&,A\btKR4\u0015\u000e^%oi\u0016\u00148-\u001a9u)\t9\u0017\u000fC\u0003j\r\u0001\u0007!\u000f\u0005\u0002lg&\u0011A\u000f\u0014\u0002\b\u0005>|G.Z1oQ\r1AKW\u0001\rg\u0016$h)\u001b;MS:,\u0017M\u001d\u000b\u0003ObDQ![\u0004A\u0002ID3a\u0002+[\u0003-\u0019X\r\u001e*fOB\u000b'/Y7\u0015\u0005\u001dd\b\"B5\t\u0001\u0004i\bCA6\u007f\u0013\tyHJ\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0011QS\u0016\u0001F:fi6Kg.\u001b\"bi\u000eDgI]1di&|g\u000eF\u0002h\u0003\u000fAQ![\u0005A\u0002uD3!\u0003+[\u0003)\u0019X\r^%oSR\u001cF\u000f\u001a\u000b\u0004O\u0006=\u0001\"B5\u000b\u0001\u0004i\bf\u0001\u0006U5\u0006Q1/\u001a;NCbLE/\u001a:\u0015\u0007\u001d\f9\u0002C\u0003j\u0017\u0001\u0007!\u000eK\u0002\f)j\u000b1b]3u'R,\u0007oU5{KR\u0019q-a\b\t\u000b%d\u0001\u0019A?)\u00071!&,\u0001\u0004tKR$v\u000e\u001c\u000b\u0004O\u0006\u001d\u0002\"B5\u000e\u0001\u0004i\bfA\u0007U5\u0006I1/\u001a;T_24XM\u001d\u000b\u0004O\u0006=\u0002\"B5\u000f\u0001\u00041\u0005f\u0001\bU5\u000691/\u001a;TK\u0016$GcA4\u00028!1\u0011n\u0004a\u0001\u0003s\u00012a[A\u001e\u0013\r\ti\u0004\u0014\u0002\u0005\u0019>tw\rK\u0002\u0010)j\u000bQ\u0001\u001e:bS:$2aLA#\u0011\u001d\t9\u0005\u0005a\u0001\u0003\u0013\nq\u0001Z1uCN,G\u000f\r\u0003\u0002L\u0005m\u0003CBA'\u0003'\n9&\u0004\u0002\u0002P)\u0019\u0011\u0011\u000b\u000f\u0002\u0007M\fH.\u0003\u0003\u0002V\u0005=#a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u00033\nY\u0006\u0004\u0001\u0005\u0019\u0005u\u0013QIA\u0001\u0002\u0003\u0015\t!a\u0018\u0003\u0007}#\u0013'\u0005\u0003\u0002b\u0005\u001d\u0004cA6\u0002d%\u0019\u0011Q\r'\u0003\u000f9{G\u000f[5oOB\u00191.!\u001b\n\u0007\u0005-DJA\u0002B]f\fAaY8qsR\u0019a&!\u001d\t\u000f\u0005M\u0014\u00031\u0001\u0002v\u0005)Q\r\u001f;sCB!\u0011qOA?\u001b\t\tIHC\u0002\u0002|i\tQ\u0001]1sC6LA!a \u0002z\tA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u0012)jC3\u0001\u0001+[\u0003-1UJU3he\u0016\u001c8o\u001c:\u0011\u0005\u0015\u001a2cB\n\u0002\f\u0006E\u0015q\u0013\t\u0004W\u00065\u0015bAAH\u0019\n1\u0011I\\=SK\u001a\u0004B!OAJ]%\u0019\u0011Q\u0013\u001e\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011\u0011TAR\u001b\t\tYJ\u0003\u0003\u0002\u001e\u0006}\u0015AA5p\u0015\t\t\t+\u0001\u0003kCZ\f\u0017\u0002BAS\u00037\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\"\u0002\t1|\u0017\r\u001a\u000b\u0004]\u00055\u0006BBAX+\u0001\u0007a)\u0001\u0003qCRD\u0007fA\u000bU5\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0017\t\u0005\u0003s\u000by,\u0004\u0002\u0002<*!\u0011QXAP\u0003\u0011a\u0017M\\4\n\t\u0005\u0005\u00171\u0018\u0002\u0007\u001f\nTWm\u0019;)\u0007M!&\fK\u0002\u0013)j\u0003"
)
public class FMRegressor extends Regressor implements FactorizationMachines, FMRegressorParams, DefaultParamsWritable {
   private final String uid;
   private IntParam factorSize;
   private BooleanParam fitLinear;
   private DoubleParam miniBatchFraction;
   private DoubleParam initStd;
   private Param solver;
   private Param weightCol;
   private DoubleParam regParam;
   private BooleanParam fitIntercept;
   private LongParam seed;
   private DoubleParam tol;
   private DoubleParam stepSize;
   private IntParam maxIter;

   public static FMRegressor load(final String path) {
      return FMRegressor$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FMRegressor$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public Vector initCoefficients(final int numFeatures) {
      return FactorizationMachines.initCoefficients$(this, numFeatures);
   }

   public Tuple2 trainImpl(final RDD data, final int numFeatures, final String loss) {
      return FactorizationMachines.trainImpl$(this, data, numFeatures, loss);
   }

   public final int getFactorSize() {
      return FactorizationMachinesParams.getFactorSize$(this);
   }

   public final boolean getFitLinear() {
      return FactorizationMachinesParams.getFitLinear$(this);
   }

   public final double getMiniBatchFraction() {
      return FactorizationMachinesParams.getMiniBatchFraction$(this);
   }

   public final double getInitStd() {
      return FactorizationMachinesParams.getInitStd$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final IntParam factorSize() {
      return this.factorSize;
   }

   public final BooleanParam fitLinear() {
      return this.fitLinear;
   }

   public final DoubleParam miniBatchFraction() {
      return this.miniBatchFraction;
   }

   public final DoubleParam initStd() {
      return this.initStd;
   }

   public final Param solver() {
      return this.solver;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$factorSize_$eq(final IntParam x$1) {
      this.factorSize = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$fitLinear_$eq(final BooleanParam x$1) {
      this.fitLinear = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$miniBatchFraction_$eq(final DoubleParam x$1) {
      this.miniBatchFraction = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$initStd_$eq(final DoubleParam x$1) {
      this.initStd = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public FMRegressor setFactorSize(final int value) {
      return (FMRegressor)this.set(this.factorSize(), BoxesRunTime.boxToInteger(value));
   }

   public FMRegressor setFitIntercept(final boolean value) {
      return (FMRegressor)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public FMRegressor setFitLinear(final boolean value) {
      return (FMRegressor)this.set(this.fitLinear(), BoxesRunTime.boxToBoolean(value));
   }

   public FMRegressor setRegParam(final double value) {
      return (FMRegressor)this.set(this.regParam(), BoxesRunTime.boxToDouble(value));
   }

   public FMRegressor setMiniBatchFraction(final double value) {
      return (FMRegressor)this.set(this.miniBatchFraction(), BoxesRunTime.boxToDouble(value));
   }

   public FMRegressor setInitStd(final double value) {
      return (FMRegressor)this.set(this.initStd(), BoxesRunTime.boxToDouble(value));
   }

   public FMRegressor setMaxIter(final int value) {
      return (FMRegressor)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public FMRegressor setStepSize(final double value) {
      return (FMRegressor)this.set(this.stepSize(), BoxesRunTime.boxToDouble(value));
   }

   public FMRegressor setTol(final double value) {
      return (FMRegressor)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public FMRegressor setSolver(final String value) {
      return (FMRegressor)this.set(this.solver(), value);
   }

   public FMRegressor setSeed(final long value) {
      return (FMRegressor)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public FMRegressionModel train(final Dataset dataset) {
      return (FMRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         int numFeatures;
         boolean var22;
         label39: {
            label38: {
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.factorSize(), this.fitIntercept(), this.fitLinear(), this.regParam(), this.miniBatchFraction(), this.initStd(), this.maxIter(), this.stepSize(), this.tol(), this.solver()}));
               numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
               instr.logNumFeatures((long)numFeatures);
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var7 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var7 == null) {
                     break label38;
                  }
               } else if (var10000.equals(var7)) {
                  break label38;
               }

               var22 = false;
               break label39;
            }

            var22 = true;
         }

         boolean handlePersistence = var22;
         RDD data = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol())), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                  Object l = ((SeqOps)var3.get()).apply(0);
                  Object v = ((SeqOps)var3.get()).apply(1);
                  if (l instanceof Double) {
                     double var6 = BoxesRunTime.unboxToDouble(l);
                     if (v instanceof org.apache.spark.ml.linalg.Vector) {
                        org.apache.spark.ml.linalg.Vector var8 = (org.apache.spark.ml.linalg.Vector)v;
                        return new Tuple2(BoxesRunTime.boxToDouble(var6), Vectors$.MODULE$.fromML(var8));
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName("training instances");
         if (handlePersistence) {
            data.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         Tuple2 var10 = this.trainImpl(data, numFeatures, FactorizationMachines$.MODULE$.SquaredError());
         if (var10 != null) {
            org.apache.spark.ml.linalg.Vector coefficients = (org.apache.spark.ml.linalg.Vector)var10._1();
            Tuple3 var13 = FactorizationMachines$.MODULE$.splitCoefficients(coefficients, numFeatures, BoxesRunTime.unboxToInt(this.$(this.factorSize())), BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToBoolean(this.$(this.fitLinear())));
            if (var13 != null) {
               double intercept = BoxesRunTime.unboxToDouble(var13._1());
               org.apache.spark.ml.linalg.Vector linear = (org.apache.spark.ml.linalg.Vector)var13._2();
               Matrix factors = (Matrix)var13._3();
               Tuple3 var12 = new Tuple3(BoxesRunTime.boxToDouble(intercept), linear, factors);
               double interceptx = BoxesRunTime.unboxToDouble(var12._1());
               org.apache.spark.ml.linalg.Vector linearx = (org.apache.spark.ml.linalg.Vector)var12._2();
               Matrix factorsx = (Matrix)var12._3();
               if (handlePersistence) {
                  data.unpersist(data.unpersist$default$1());
               } else {
                  BoxedUnit var24 = BoxedUnit.UNIT;
               }

               return (FMRegressionModel)this.copyValues(new FMRegressionModel(this.uid(), interceptx, linearx, factorsx), this.copyValues$default$2());
            } else {
               throw new MatchError(var13);
            }
         } else {
            throw new MatchError(var10);
         }
      });
   }

   public FMRegressor copy(final ParamMap extra) {
      return (FMRegressor)this.defaultCopy(extra);
   }

   public FMRegressor(final String uid) {
      this.uid = uid;
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasTol.$init$(this);
      HasSolver.$init$(this);
      HasSeed.$init$(this);
      HasFitIntercept.$init$(this);
      HasRegParam.$init$(this);
      HasWeightCol.$init$(this);
      FactorizationMachinesParams.$init$(this);
      FactorizationMachines.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public FMRegressor() {
      this(Identifiable$.MODULE$.randomUID("fmr"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

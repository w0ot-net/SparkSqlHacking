package org.apache.spark.ml.classification;

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
import org.apache.spark.ml.regression.FactorizationMachines;
import org.apache.spark.ml.regression.FactorizationMachines$;
import org.apache.spark.ml.regression.FactorizationMachinesParams;
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
import scala.Predef.;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mh\u0001\u0002\r\u001a\u0001\u0011B\u0001\u0002\u0013\u0001\u0003\u0006\u0004%\t%\u0013\u0005\tA\u0002\u0011\t\u0011)A\u0005\u0015\")!\r\u0001C\u0001G\")!\r\u0001C\u0001O\")\u0011\u000e\u0001C\u0001U\")1\u000f\u0001C\u0001i\")!\u0010\u0001C\u0001w\")a\u0010\u0001C\u0001\u007f\"9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!a\u000b\u0001\t\u0003\ti\u0003C\u0004\u00024\u0001!\t!!\u000e\t\u000f\u0005m\u0002\u0001\"\u0001\u0002>!9\u0011\u0011\n\u0001\u0005R\u0005-\u0003bBA;\u0001\u0011%\u0011q\u000f\u0005\b\u0003C\u0003A\u0011IAR\u000f\u001d\tI,\u0007E\u0001\u0003w3a\u0001G\r\t\u0002\u0005u\u0006B\u00022\u0015\t\u0003\tY\u000eC\u0004\u0002^R!\t%a8\t\u0013\u0005\u001dH#!A\u0005\n\u0005%(\u0001\u0004$N\u00072\f7o]5gS\u0016\u0014(B\u0001\u000e\u001c\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!\u0001H\u000f\u0002\u00055d'B\u0001\u0010 \u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0013%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0005\u0019qN]4\u0004\u0001M1\u0001!J\u001a:y\t\u0003RAJ\u0014*_Aj\u0011!G\u0005\u0003Qe\u0011q\u0003\u0015:pE\u0006\u0014\u0017\u000e\\5ti&\u001c7\t\\1tg&4\u0017.\u001a:\u0011\u0005)jS\"A\u0016\u000b\u00051Z\u0012A\u00027j]\u0006dw-\u0003\u0002/W\t1a+Z2u_J\u0004\"A\n\u0001\u0011\u0005\u0019\n\u0014B\u0001\u001a\u001a\u0005U1Uj\u00117bgNLg-[2bi&|g.T8eK2\u0004\"\u0001N\u001c\u000e\u0003UR!AN\u000e\u0002\u0015I,wM]3tg&|g.\u0003\u00029k\t)b)Y2u_JL'0\u0019;j_:l\u0015m\u00195j]\u0016\u001c\bC\u0001\u0014;\u0013\tY\u0014D\u0001\nG\u001b\u000ec\u0017m]:jM&,'\u000fU1sC6\u001c\bCA\u001fA\u001b\u0005q$BA \u001c\u0003\u0011)H/\u001b7\n\u0005\u0005s$!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0006\u0014G.\u001a\t\u0003\u0007\u001ak\u0011\u0001\u0012\u0006\u0003\u000bv\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u000f\u0012\u0013q\u0001T8hO&tw-A\u0002vS\u0012,\u0012A\u0013\t\u0003\u0017Rs!\u0001\u0014*\u0011\u00055\u0003V\"\u0001(\u000b\u0005=\u001b\u0013A\u0002\u001fs_>$hHC\u0001R\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0006+\u0001\u0004Qe\u0016$WMZ\u0005\u0003+Z\u0013aa\u0015;sS:<'BA*QQ\r\t\u0001L\u0018\t\u00033rk\u0011A\u0017\u0006\u00037v\t!\"\u00198o_R\fG/[8o\u0013\ti&LA\u0003TS:\u001cW-I\u0001`\u0003\u0015\u0019d\u0006\r\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\tAf,\u0001\u0004=S:LGO\u0010\u000b\u0003_\u0011DQ\u0001S\u0002A\u0002)C3\u0001\u001a-_Q\r\u0019\u0001L\u0018\u000b\u0002_!\u001aA\u0001\u00170\u0002\u001bM,GOR1di>\u00148+\u001b>f)\tYG.D\u0001\u0001\u0011\u0015iW\u00011\u0001o\u0003\u00151\u0018\r\\;f!\ty\u0007/D\u0001Q\u0013\t\t\bKA\u0002J]RD3!\u0002-_\u0003=\u0019X\r\u001e$ji&sG/\u001a:dKB$HCA6v\u0011\u0015ig\u00011\u0001w!\tyw/\u0003\u0002y!\n9!i\\8mK\u0006t\u0007f\u0001\u0004Y=\u0006a1/\u001a;GSRd\u0015N\\3beR\u00111\u000e \u0005\u0006[\u001e\u0001\rA\u001e\u0015\u0004\u000fas\u0016aC:fiJ+w\rU1sC6$2a[A\u0001\u0011\u0019i\u0007\u00021\u0001\u0002\u0004A\u0019q.!\u0002\n\u0007\u0005\u001d\u0001K\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0011as\u0016\u0001F:fi6Kg.\u001b\"bi\u000eDgI]1di&|g\u000eF\u0002l\u0003\u001fAa!\\\u0005A\u0002\u0005\r\u0001fA\u0005Y=\u0006Q1/\u001a;J]&$8\u000b\u001e3\u0015\u0007-\f9\u0002\u0003\u0004n\u0015\u0001\u0007\u00111\u0001\u0015\u0004\u0015as\u0016AC:fi6\u000b\u00070\u0013;feR\u00191.a\b\t\u000b5\\\u0001\u0019\u00018)\u0007-Af,A\u0006tKR\u001cF/\u001a9TSj,GcA6\u0002(!1Q\u000e\u0004a\u0001\u0003\u0007A3\u0001\u0004-_\u0003\u0019\u0019X\r\u001e+pYR\u00191.a\f\t\r5l\u0001\u0019AA\u0002Q\ri\u0001LX\u0001\ng\u0016$8k\u001c7wKJ$2a[A\u001c\u0011\u0015ig\u00021\u0001KQ\rq\u0001LX\u0001\bg\u0016$8+Z3e)\rY\u0017q\b\u0005\u0007[>\u0001\r!!\u0011\u0011\u0007=\f\u0019%C\u0002\u0002FA\u0013A\u0001T8oO\"\u001aq\u0002\u00170\u0002\u000bQ\u0014\u0018-\u001b8\u0015\u0007A\ni\u0005C\u0004\u0002PA\u0001\r!!\u0015\u0002\u000f\u0011\fG/Y:fiB\"\u00111KA2!\u0019\t)&a\u0017\u0002`5\u0011\u0011q\u000b\u0006\u0004\u00033j\u0012aA:rY&!\u0011QLA,\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0019\u0002d1\u0001A\u0001DA3\u0003\u001b\n\t\u0011!A\u0003\u0002\u0005\u001d$aA0%cE!\u0011\u0011NA8!\ry\u00171N\u0005\u0004\u0003[\u0002&a\u0002(pi\"Lgn\u001a\t\u0004_\u0006E\u0014bAA:!\n\u0019\u0011I\\=\u0002\u0017\r\u0014X-\u0019;f\u001b>$W\r\u001c\u000b\fa\u0005e\u0014QQAE\u0003\u001b\u000b9\nC\u0004\u0002PE\u0001\r!a\u001f1\t\u0005u\u0014\u0011\u0011\t\u0007\u0003+\nY&a \u0011\t\u0005\u0005\u0014\u0011\u0011\u0003\r\u0003\u0007\u000bI(!A\u0001\u0002\u000b\u0005\u0011q\r\u0002\u0004?\u0012\u0012\u0004bBAD#\u0001\u0007\u00111A\u0001\nS:$XM]2faRDa!a#\u0012\u0001\u0004I\u0013A\u00027j]\u0016\f'\u000fC\u0004\u0002\u0010F\u0001\r!!%\u0002\u000f\u0019\f7\r^8sgB\u0019!&a%\n\u0007\u0005U5F\u0001\u0004NCR\u0014\u0018\u000e\u001f\u0005\b\u00033\u000b\u0002\u0019AAN\u0003Ay'M[3di&4X\rS5ti>\u0014\u0018\u0010E\u0003p\u0003;\u000b\u0019!C\u0002\u0002 B\u0013Q!\u0011:sCf\fAaY8qsR\u0019q&!*\t\u000f\u0005\u001d&\u00031\u0001\u0002*\u0006)Q\r\u001f;sCB!\u00111VAY\u001b\t\tiKC\u0002\u00020n\tQ\u0001]1sC6LA!a-\u0002.\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u00131zC3\u0001\u0001-_\u000311Uj\u00117bgNLg-[3s!\t1CcE\u0004\u0015\u0003\u007f\u000b)-a3\u0011\u0007=\f\t-C\u0002\u0002DB\u0013a!\u00118z%\u00164\u0007\u0003B\u001f\u0002H>J1!!3?\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!!4\u0002X6\u0011\u0011q\u001a\u0006\u0005\u0003#\f\u0019.\u0001\u0002j_*\u0011\u0011Q[\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002Z\u0006='\u0001D*fe&\fG.\u001b>bE2,GCAA^\u0003\u0011aw.\u00193\u0015\u0007=\n\t\u000f\u0003\u0004\u0002dZ\u0001\rAS\u0001\u0005a\u0006$\b\u000eK\u0002\u00171z\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a;\u0011\t\u00055\u00181_\u0007\u0003\u0003_TA!!=\u0002T\u0006!A.\u00198h\u0013\u0011\t)0a<\u0003\r=\u0013'.Z2uQ\r!\u0002L\u0018\u0015\u0004'as\u0006"
)
public class FMClassifier extends ProbabilisticClassifier implements FactorizationMachines, FMClassifierParams, DefaultParamsWritable {
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

   public static FMClassifier load(final String path) {
      return FMClassifier$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FMClassifier$.MODULE$.read();
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

   public FMClassifier setFactorSize(final int value) {
      return (FMClassifier)this.set(this.factorSize(), BoxesRunTime.boxToInteger(value));
   }

   public FMClassifier setFitIntercept(final boolean value) {
      return (FMClassifier)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public FMClassifier setFitLinear(final boolean value) {
      return (FMClassifier)this.set(this.fitLinear(), BoxesRunTime.boxToBoolean(value));
   }

   public FMClassifier setRegParam(final double value) {
      return (FMClassifier)this.set(this.regParam(), BoxesRunTime.boxToDouble(value));
   }

   public FMClassifier setMiniBatchFraction(final double value) {
      return (FMClassifier)this.set(this.miniBatchFraction(), BoxesRunTime.boxToDouble(value));
   }

   public FMClassifier setInitStd(final double value) {
      return (FMClassifier)this.set(this.initStd(), BoxesRunTime.boxToDouble(value));
   }

   public FMClassifier setMaxIter(final int value) {
      return (FMClassifier)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public FMClassifier setStepSize(final double value) {
      return (FMClassifier)this.set(this.stepSize(), BoxesRunTime.boxToDouble(value));
   }

   public FMClassifier setTol(final double value) {
      return (FMClassifier)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public FMClassifier setSolver(final String value) {
      return (FMClassifier)this.set(this.solver(), value);
   }

   public FMClassifier setSeed(final long value) {
      return (FMClassifier)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public FMClassificationModel train(final Dataset dataset) {
      return (FMClassificationModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         int numClasses = 2;
         if (this.isDefined(this.thresholds())) {
            .MODULE$.require(((double[])this.$(this.thresholds())).length == numClasses, () -> this.getClass().getSimpleName() + ".train() called with non-matching numClasses and thresholds.length. numClasses=" + numClasses + ", but thresholds has length " + ((double[])this.$(this.thresholds())).length);
         }

         int numFeatures;
         boolean var26;
         label47: {
            label46: {
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.factorSize(), this.fitIntercept(), this.fitLinear(), this.regParam(), this.miniBatchFraction(), this.initStd(), this.maxIter(), this.stepSize(), this.tol(), this.solver(), this.thresholds()}));
               instr.logNumClasses((long)numClasses);
               numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
               instr.logNumFeatures((long)numFeatures);
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var8 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var8 == null) {
                     break label46;
                  }
               } else if (var10000.equals(var8)) {
                  break label46;
               }

               var26 = false;
               break label47;
            }

            var26 = true;
         }

         boolean handlePersistence = var26;
         RDD data = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkClassificationLabels((String)this.$(this.labelCol()), new Some(BoxesRunTime.boxToInteger(2))), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
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
            BoxedUnit var27 = BoxedUnit.UNIT;
         }

         Tuple2 var11 = this.trainImpl(data, numFeatures, FactorizationMachines$.MODULE$.LogisticLoss());
         if (var11 != null) {
            org.apache.spark.ml.linalg.Vector coefficients = (org.apache.spark.ml.linalg.Vector)var11._1();
            double[] objectiveHistory = (double[])var11._2();
            Tuple2 var10 = new Tuple2(coefficients, objectiveHistory);
            org.apache.spark.ml.linalg.Vector coefficientsx = (org.apache.spark.ml.linalg.Vector)var10._1();
            double[] objectiveHistoryx = (double[])var10._2();
            Tuple3 var17 = FactorizationMachines$.MODULE$.splitCoefficients(coefficientsx, numFeatures, BoxesRunTime.unboxToInt(this.$(this.factorSize())), BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToBoolean(this.$(this.fitLinear())));
            if (var17 != null) {
               double intercept = BoxesRunTime.unboxToDouble(var17._1());
               org.apache.spark.ml.linalg.Vector linear = (org.apache.spark.ml.linalg.Vector)var17._2();
               Matrix factors = (Matrix)var17._3();
               Tuple3 var16 = new Tuple3(BoxesRunTime.boxToDouble(intercept), linear, factors);
               double intercept = BoxesRunTime.unboxToDouble(var16._1());
               org.apache.spark.ml.linalg.Vector linear = (org.apache.spark.ml.linalg.Vector)var16._2();
               Matrix factors = (Matrix)var16._3();
               if (handlePersistence) {
                  data.unpersist(data.unpersist$default$1());
               } else {
                  BoxedUnit var28 = BoxedUnit.UNIT;
               }

               return this.createModel(dataset, intercept, linear, factors, objectiveHistoryx);
            } else {
               throw new MatchError(var17);
            }
         } else {
            throw new MatchError(var11);
         }
      });
   }

   private FMClassificationModel createModel(final Dataset dataset, final double intercept, final org.apache.spark.ml.linalg.Vector linear, final Matrix factors, final double[] objectiveHistory) {
      FMClassificationModel model = (FMClassificationModel)this.copyValues(new FMClassificationModel(this.uid(), intercept, linear, factors), this.copyValues$default$2());
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var11 = model.findSummaryModel();
      if (var11 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var11._1();
         String probabilityColName = (String)var11._2();
         String predictionColName = (String)var11._3();
         Tuple3 var10 = new Tuple3(summaryModel, probabilityColName, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var10._1();
         String probabilityColName = (String)var10._2();
         String predictionColName = (String)var10._3();
         FMClassificationTrainingSummaryImpl summary = new FMClassificationTrainingSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), weightColName, objectiveHistory);
         return (FMClassificationModel)model.setSummary(new Some(summary));
      } else {
         throw new MatchError(var11);
      }
   }

   public FMClassifier copy(final ParamMap extra) {
      return (FMClassifier)this.defaultCopy(extra);
   }

   public FMClassifier(final String uid) {
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

   public FMClassifier() {
      this(Identifiable$.MODULE$.randomUID("fmc"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

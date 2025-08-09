package org.apache.spark.ml.classification;

import breeze.linalg.DenseVector;
import breeze.optimize.CachedDiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.OWLQN;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.feature.InstanceBlock$;
import org.apache.spark.ml.feature.StandardScalerModel$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.optim.aggregator.HingeBlockAggregator;
import org.apache.spark.ml.optim.loss.L2Regularization;
import org.apache.spark.ml.optim.loss.RDDLossFunction;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasThreshold;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.MultiClassSummarizer;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.MetadataUtils$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.storage.StorageLevel;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t=b\u0001B\f\u0019\u0001\rB\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0005\u0010\u0005\t'\u0002\u0011\t\u0011)A\u0005{!)Q\u000b\u0001C\u0001-\")Q\u000b\u0001C\u00015\")A\f\u0001C\u0001;\")a\r\u0001C\u0001O\")Q\u000e\u0001C\u0001]\")A\u000f\u0001C\u0001k\")\u0001\u0010\u0001C\u0001s\")A\u0010\u0001C\u0001{\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\u0005\u0001\u0011\u0005\u00111\u0002\u0005\b\u0003#\u0001A\u0011AA\n\u0011\u001d\ti\u0002\u0001C!\u0003?Aq!a\r\u0001\t#\n)\u0004C\u0004\u0002`\u0001!I!!\u0019\t\u000f\u0005\u0005\u0005\u0001\"\u0003\u0002\u0004\u001e9\u0011Q\u001e\r\t\u0002\u0005=hAB\f\u0019\u0011\u0003\t\t\u0010\u0003\u0004V'\u0011\u0005!q\u0002\u0005\b\u0005#\u0019B\u0011\tB\n\u0011%\u0011YbEA\u0001\n\u0013\u0011iBA\u0005MS:,\u0017M]*W\u0007*\u0011\u0011DG\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tYB$\u0001\u0002nY*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001!#'\u000e\t\u0006K\u0019BcfL\u0007\u00021%\u0011q\u0005\u0007\u0002\u000b\u00072\f7o]5gS\u0016\u0014\bCA\u0015-\u001b\u0005Q#BA\u0016\u001b\u0003\u0019a\u0017N\\1mO&\u0011QF\u000b\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005\u0015\u0002\u0001CA\u00131\u0013\t\t\u0004D\u0001\bMS:,\u0017M]*W\u00076{G-\u001a7\u0011\u0005\u0015\u001a\u0014B\u0001\u001b\u0019\u0005=a\u0015N\\3beN36\tU1sC6\u001c\bC\u0001\u001c:\u001b\u00059$B\u0001\u001d\u001b\u0003\u0011)H/\u001b7\n\u0005i:$!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#A\u001f\u0011\u0005y:eBA F!\t\u00015)D\u0001B\u0015\t\u0011%%\u0001\u0004=e>|GO\u0010\u0006\u0002\t\u0006)1oY1mC&\u0011aiQ\u0001\u0007!J,G-\u001a4\n\u0005!K%AB*ue&twM\u0003\u0002G\u0007\"\u001a\u0011aS)\u0011\u00051{U\"A'\u000b\u00059c\u0012AC1o]>$\u0018\r^5p]&\u0011\u0001+\u0014\u0002\u0006'&t7-Z\u0011\u0002%\u0006)!G\f\u001a/a\u0005!Q/\u001b3!Q\r\u00111*U\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00059:\u0006\"B\u001e\u0004\u0001\u0004i\u0004fA,L#\"\u001a1aS)\u0015\u00039B3\u0001B&R\u0003-\u0019X\r\u001e*fOB\u000b'/Y7\u0015\u0005y{V\"\u0001\u0001\t\u000b\u0001,\u0001\u0019A1\u0002\u000bY\fG.^3\u0011\u0005\t\u001cW\"A\"\n\u0005\u0011\u001c%A\u0002#pk\ndW\rK\u0002\u0006\u0017F\u000b!b]3u\u001b\u0006D\u0018\n^3s)\tq\u0006\u000eC\u0003a\r\u0001\u0007\u0011\u000e\u0005\u0002cU&\u00111n\u0011\u0002\u0004\u0013:$\bf\u0001\u0004L#\u0006y1/\u001a;GSRLe\u000e^3sG\u0016\u0004H\u000f\u0006\u0002__\")\u0001m\u0002a\u0001aB\u0011!-]\u0005\u0003e\u000e\u0013qAQ8pY\u0016\fg\u000eK\u0002\b\u0017F\u000baa]3u)>dGC\u00010w\u0011\u0015\u0001\u0007\u00021\u0001bQ\rA1*U\u0001\u0013g\u0016$8\u000b^1oI\u0006\u0014H-\u001b>bi&|g\u000e\u0006\u0002_u\")\u0001-\u0003a\u0001a\"\u001a\u0011bS)\u0002\u0019M,GoV3jO\"$8i\u001c7\u0015\u0005ys\b\"\u00021\u000b\u0001\u0004i\u0004f\u0001\u0006L#\u0006a1/\u001a;UQJ,7\u000f[8mIR\u0019a,!\u0002\t\u000b\u0001\\\u0001\u0019A1)\u0007-Y\u0015+A\ntKR\fum\u001a:fO\u0006$\u0018n\u001c8EKB$\b\u000eF\u0002_\u0003\u001bAQ\u0001\u0019\u0007A\u0002%D3\u0001D&R\u0003M\u0019X\r^'bq\ncwnY6TSj,\u0017J\\'C)\rq\u0016Q\u0003\u0005\u0006A6\u0001\r!\u0019\u0015\u0005\u001b-\u000bI\"\t\u0002\u0002\u001c\u0005)1GL\u0019/a\u0005!1m\u001c9z)\rq\u0013\u0011\u0005\u0005\b\u0003Gq\u0001\u0019AA\u0013\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t9#!\f\u000e\u0005\u0005%\"bAA\u00165\u0005)\u0001/\u0019:b[&!\u0011qFA\u0015\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bf\u0001\bL#\u0006)AO]1j]R\u0019q&a\u000e\t\u000f\u0005er\u00021\u0001\u0002<\u00059A-\u0019;bg\u0016$\b\u0007BA\u001f\u0003\u001b\u0002b!a\u0010\u0002F\u0005%SBAA!\u0015\r\t\u0019\u0005H\u0001\u0004gFd\u0017\u0002BA$\u0003\u0003\u0012q\u0001R1uCN,G\u000f\u0005\u0003\u0002L\u00055C\u0002\u0001\u0003\r\u0003\u001f\n9$!A\u0001\u0002\u000b\u0005\u0011\u0011\u000b\u0002\u0004?\u0012\n\u0014\u0003BA*\u00033\u00022AYA+\u0013\r\t9f\u0011\u0002\b\u001d>$\b.\u001b8h!\r\u0011\u00171L\u0005\u0004\u0003;\u001a%aA!os\u0006Y1M]3bi\u0016lu\u000eZ3m)%y\u00131MA8\u0003g\n9\bC\u0004\u0002:A\u0001\r!!\u001a1\t\u0005\u001d\u00141\u000e\t\u0007\u0003\u007f\t)%!\u001b\u0011\t\u0005-\u00131\u000e\u0003\r\u0003[\n\u0019'!A\u0001\u0002\u000b\u0005\u0011\u0011\u000b\u0002\u0004?\u0012\u0012\u0004BBA9!\u0001\u0007\u0001&\u0001\u0007d_\u00164g-[2jK:$8\u000f\u0003\u0004\u0002vA\u0001\r!Y\u0001\nS:$XM]2faRDq!!\u001f\u0011\u0001\u0004\tY(\u0001\tpE*,7\r^5wK\"K7\u000f^8ssB!!-! b\u0013\r\tyh\u0011\u0002\u0006\u0003J\u0014\u0018-_\u0001\niJ\f\u0017N\\%na2$b\"!\"\u0002\f\u0006\u001d\u00161VAX\u0003g\u000bi\rE\u0004c\u0003\u000f\u000bY(a\u001f\n\u0007\u0005%5I\u0001\u0004UkBdWM\r\u0005\b\u0003\u001b\u000b\u0002\u0019AAH\u0003%Ign\u001d;b]\u000e,7\u000f\u0005\u0004\u0002\u0012\u0006]\u00151T\u0007\u0003\u0003'S1!!&\u001d\u0003\r\u0011H\rZ\u0005\u0005\u00033\u000b\u0019JA\u0002S\t\u0012\u0003B!!(\u0002$6\u0011\u0011q\u0014\u0006\u0004\u0003CS\u0012a\u00024fCR,(/Z\u0005\u0005\u0003K\u000byJ\u0001\u0005J]N$\u0018M\\2f\u0011\u0019\tI+\u0005a\u0001C\u0006\u0019\u0012m\u0019;vC2\u0014En\\2l'&TX-\u00138N\u0005\"9\u0011QV\tA\u0002\u0005m\u0014a\u00034fCR,(/Z:Ti\u0012Dq!!-\u0012\u0001\u0004\tY(\u0001\u0007gK\u0006$XO]3t\u001b\u0016\fg\u000eC\u0004\u00026F\u0001\r!a.\u0002\u001dI,w-\u001e7be&T\u0018\r^5p]B)!-!/\u0002>&\u0019\u00111X\"\u0003\r=\u0003H/[8o!\u0011\ty,!3\u000e\u0005\u0005\u0005'\u0002BAb\u0003\u000b\fA\u0001\\8tg*\u0019\u0011q\u0019\u000e\u0002\u000b=\u0004H/[7\n\t\u0005-\u0017\u0011\u0019\u0002\u0011\u0019J\u0012VmZ;mCJL'0\u0019;j_:Dq!a4\u0012\u0001\u0004\t\t.A\u0005paRLW.\u001b>feB9\u00111[AoS\u0006\u0005XBAAk\u0015\u0011\t9.!7\u0002\u0011=\u0004H/[7ju\u0016T!!a7\u0002\r\t\u0014X-\u001a>f\u0013\u0011\ty.!6\u0003\u000b=;F*\u0015(\u0011\u000b\u0005\r\u0018q]1\u000e\u0005\u0005\u0015(bA\u0016\u0002Z&!\u0011\u0011^As\u0005-!UM\\:f-\u0016\u001cGo\u001c:)\u0007\u0001Y\u0015+A\u0005MS:,\u0017M]*W\u0007B\u0011QeE\n\b'\u0005M\u0018\u0011`A\u0000!\r\u0011\u0017Q_\u0005\u0004\u0003o\u001c%AB!osJ+g\r\u0005\u00037\u0003wt\u0013bAA\u007fo\t)B)\u001a4bk2$\b+\u0019:b[N\u0014V-\u00193bE2,\u0007\u0003\u0002B\u0001\u0005\u0017i!Aa\u0001\u000b\t\t\u0015!qA\u0001\u0003S>T!A!\u0003\u0002\t)\fg/Y\u0005\u0005\u0005\u001b\u0011\u0019A\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002p\u0006!An\\1e)\rq#Q\u0003\u0005\u0007\u0005/)\u0002\u0019A\u001f\u0002\tA\fG\u000f\u001b\u0015\u0004+-\u000b\u0016\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u0010!\u0011\u0011\tCa\n\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0005\u000f\tA\u0001\\1oO&!!\u0011\u0006B\u0012\u0005\u0019y%M[3di\"\u001a1cS))\u0007IY\u0015\u000b"
)
public class LinearSVC extends Classifier implements LinearSVCParams, DefaultParamsWritable {
   private final String uid;
   private DoubleParam threshold;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private Param weightCol;
   private BooleanParam standardization;
   private DoubleParam tol;
   private BooleanParam fitIntercept;
   private IntParam maxIter;
   private DoubleParam regParam;

   public static LinearSVC load(final String path) {
      return LinearSVC$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LinearSVC$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public double getThreshold() {
      return HasThreshold.getThreshold$(this);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final boolean getStandardization() {
      return HasStandardization.getStandardization$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final DoubleParam threshold() {
      return this.threshold;
   }

   public final void org$apache$spark$ml$classification$LinearSVCParams$_setter_$threshold_$eq(final DoubleParam x$1) {
      this.threshold = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasThreshold$_setter_$threshold_$eq(final DoubleParam x$1) {
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final BooleanParam standardization() {
      return this.standardization;
   }

   public final void org$apache$spark$ml$param$shared$HasStandardization$_setter_$standardization_$eq(final BooleanParam x$1) {
      this.standardization = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public LinearSVC setRegParam(final double value) {
      return (LinearSVC)this.set(this.regParam(), BoxesRunTime.boxToDouble(value));
   }

   public LinearSVC setMaxIter(final int value) {
      return (LinearSVC)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public LinearSVC setFitIntercept(final boolean value) {
      return (LinearSVC)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public LinearSVC setTol(final double value) {
      return (LinearSVC)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public LinearSVC setStandardization(final boolean value) {
      return (LinearSVC)this.set(this.standardization(), BoxesRunTime.boxToBoolean(value));
   }

   public LinearSVC setWeightCol(final String value) {
      return (LinearSVC)this.set(this.weightCol(), value);
   }

   public LinearSVC setThreshold(final double value) {
      return (LinearSVC)this.set(this.threshold(), BoxesRunTime.boxToDouble(value));
   }

   public LinearSVC setAggregationDepth(final int value) {
      return (LinearSVC)this.set(this.aggregationDepth(), BoxesRunTime.boxToInteger(value));
   }

   public LinearSVC setMaxBlockSizeInMB(final double value) {
      return (LinearSVC)this.set(this.maxBlockSizeInMB(), BoxesRunTime.boxToDouble(value));
   }

   public LinearSVC copy(final ParamMap extra) {
      return (LinearSVC)this.defaultCopy(extra);
   }

   public LinearSVCModel train(final Dataset dataset) {
      return (LinearSVCModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         label84: {
            instr.logPipelineStage(this);
            instr.logDataset(dataset);
            instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.weightCol(), this.featuresCol(), this.predictionCol(), this.rawPredictionCol(), this.regParam(), this.maxIter(), this.fitIntercept(), this.tol(), this.standardization(), this.threshold(), this.aggregationDepth(), this.maxBlockSizeInMB()}));
            StorageLevel var10000 = dataset.storageLevel();
            StorageLevel var6 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var6 == null) {
                  break label84;
               }
            } else if (var10000.equals(var6)) {
               break label84;
            }

            instr.logWarning((Function0)(() -> "Input instances will be standardized, blockified to blocks, and then cached during training. Be careful of double caching!"));
         }

         RDD instances = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkClassificationLabels((String)this.$(this.labelCol()), new Some(BoxesRunTime.boxToInteger(2))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
                  Object l = ((SeqOps)var3.get()).apply(0);
                  Object w = ((SeqOps)var3.get()).apply(1);
                  Object v = ((SeqOps)var3.get()).apply(2);
                  if (l instanceof Double) {
                     double var7 = BoxesRunTime.unboxToDouble(l);
                     if (w instanceof Double) {
                        double var9 = BoxesRunTime.unboxToDouble(w);
                        if (v instanceof Vector) {
                           Vector var11 = (Vector)v;
                           return new Instance(var7, var9, var11);
                        }
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Instance.class)).setName("training instances");
         Tuple2 var9 = Summarizer$.MODULE$.getClassificationSummarizers(instances, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("std", new scala.collection.immutable..colon.colon("count", scala.collection.immutable.Nil..MODULE$))));
         if (var9 != null) {
            SummarizerBuffer summarizer = (SummarizerBuffer)var9._1();
            MultiClassSummarizer labelSummarizer = (MultiClassSummarizer)var9._2();
            Tuple2 var8 = new Tuple2(summarizer, labelSummarizer);
            SummarizerBuffer summarizerx = (SummarizerBuffer)var8._1();
            MultiClassSummarizer labelSummarizer = (MultiClassSummarizer)var8._2();
            double[] histogram = labelSummarizer.histogram();
            long numInvalid = labelSummarizer.countInvalid();
            int numFeatures = summarizerx.mean().size();
            instr.logNumExamples(summarizerx.count());
            instr.logNamedValue("lowestLabelWeight", scala.Predef..MODULE$.wrapDoubleArray(labelSummarizer.histogram()).min(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).toString());
            instr.logNamedValue("highestLabelWeight", scala.Predef..MODULE$.wrapDoubleArray(labelSummarizer.histogram()).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).toString());
            instr.logSumOfWeights(summarizerx.weightSum());
            double actualBlockSizeInMB = BoxesRunTime.unboxToDouble(this.$(this.maxBlockSizeInMB()));
            if (actualBlockSizeInMB == (double)0) {
               actualBlockSizeInMB = InstanceBlock$.MODULE$.DefaultBlockSizeInMB();
               scala.Predef..MODULE$.require(actualBlockSizeInMB > (double)0, () -> "inferred actual BlockSizeInMB must > 0");
               instr.logNamedValue("actualBlockSizeInMB", Double.toString(actualBlockSizeInMB));
            }

            int var41;
            label88: {
               Option var21 = MetadataUtils$.MODULE$.getNumClasses(dataset.schema().apply((String)this.$(this.labelCol())));
               if (var21 instanceof Some) {
                  Some var22 = (Some)var21;
                  int n = BoxesRunTime.unboxToInt(var22.value());
                  if (true) {
                     scala.Predef..MODULE$.require(n >= histogram.length, () -> "Specified number of classes " + n + " was less than the number of unique labels " + histogram.length + ".");
                     var41 = n;
                     break label88;
                  }
               }

               if (!scala.None..MODULE$.equals(var21)) {
                  throw new MatchError(var21);
               }

               var41 = histogram.length;
            }

            int numClasses = var41;
            scala.Predef..MODULE$.require(numClasses == 2, () -> "LinearSVC only supports binary classification. " + numClasses + " classes detected in " + this.labelCol());
            instr.logNumClasses((long)numClasses);
            instr.logNumFeatures((long)numFeatures);
            if (numInvalid != 0L) {
               MessageWithContext msg = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Classification labels should be in "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RANGE..MODULE$, "[0 to " + (numClasses - 1) + "]")})))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Found ", " invalid labels."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToLong(numInvalid))}))));
               instr.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg));
               throw new SparkException(msg.message());
            } else {
               double[] featuresStd = summarizerx.std().toArray();
               double[] featuresMean = summarizerx.mean().toArray();
               Function1 getFeaturesStd = (j) -> featuresStd[j];
               Object var42;
               if (BoxesRunTime.unboxToDouble(this.$(this.regParam())) != (double)0.0F) {
                  Function1 shouldApply = (idx) -> idx >= 0 && idx < numFeatures;
                  var42 = new Some(new L2Regularization(BoxesRunTime.unboxToDouble(this.$(this.regParam())), shouldApply, (Option)(BoxesRunTime.unboxToBoolean(this.$(this.standardization())) ? scala.None..MODULE$ : new Some(getFeaturesStd))));
               } else {
                  var42 = scala.None..MODULE$;
               }

               Option regularization = (Option)var42;
               OWLQN optimizer = new OWLQN(BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, regParamL1Fun$1(), BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.linalg.DenseVector..MODULE$.space_Double());
               Tuple2 var33 = this.trainImpl(instances, actualBlockSizeInMB, featuresStd, featuresMean, regularization, optimizer);
               if (var33 != null) {
                  double[] rawCoefficients = (double[])var33._1();
                  double[] objectiveHistory = (double[])var33._2();
                  Tuple2 var32 = new Tuple2(rawCoefficients, objectiveHistory);
                  double[] rawCoefficientsx = (double[])var32._1();
                  double[] objectiveHistoryx = (double[])var32._2();
                  if (rawCoefficientsx == null) {
                     MLUtils$.MODULE$.optimizerFailed(instr, optimizer.getClass());
                  }

                  double[] coefficientArray = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> featuresStd[i] != (double)0.0F ? rawCoefficientsx[i] / featuresStd[i] : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
                  double intercept = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(rawCoefficientsx))) : (double)0.0F;
                  return this.createModel(dataset, org.apache.spark.ml.linalg.Vectors..MODULE$.dense(coefficientArray), intercept, objectiveHistoryx);
               } else {
                  throw new MatchError(var33);
               }
            }
         } else {
            throw new MatchError(var9);
         }
      });
   }

   private LinearSVCModel createModel(final Dataset dataset, final Vector coefficients, final double intercept, final double[] objectiveHistory) {
      LinearSVCModel model = (LinearSVCModel)this.copyValues(new LinearSVCModel(this.uid(), coefficients, intercept), this.copyValues$default$2());
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var10 = model.findSummaryModel();
      if (var10 != null) {
         ClassificationModel summaryModel = (ClassificationModel)var10._1();
         String rawPredictionColName = (String)var10._2();
         String predictionColName = (String)var10._3();
         Tuple3 var9 = new Tuple3(summaryModel, rawPredictionColName, predictionColName);
         ClassificationModel summaryModel = (ClassificationModel)var9._1();
         String rawPredictionColName = (String)var9._2();
         String predictionColName = (String)var9._3();
         LinearSVCTrainingSummaryImpl summary = new LinearSVCTrainingSummaryImpl(summaryModel.transform(dataset), rawPredictionColName, predictionColName, (String)this.$(this.labelCol()), weightColName, objectiveHistory);
         return (LinearSVCModel)model.setSummary(new Some(summary));
      } else {
         throw new MatchError(var10);
      }
   }

   private Tuple2 trainImpl(final RDD instances, final double actualBlockSizeInMB, final double[] featuresStd, final double[] featuresMean, final Option regularization, final OWLQN optimizer) {
      int numFeatures = featuresStd.length;
      int numFeaturesPlusIntercept = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? numFeatures + 1 : numFeatures;
      double[] inverseStd = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(featuresStd), (JFunction1.mcDD.sp)(std) -> std != (double)0 ? (double)1.0F / std : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
      double[] scaledMean = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> inverseStd[i] * featuresMean[i], scala.reflect.ClassTag..MODULE$.Double());
      Broadcast bcInverseStd = instances.context().broadcast(inverseStd, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)));
      Broadcast bcScaledMean = instances.context().broadcast(scaledMean, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)));
      RDD standardized = instances.mapPartitions((iter) -> {
         Function1 func = StandardScalerModel$.MODULE$.getTransformFunc((double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), (double[])bcInverseStd.value(), false, true);
         return iter.map((x0$1) -> {
            if (x0$1 != null) {
               double label = x0$1.label();
               double weight = x0$1.weight();
               Vector vec = x0$1.features();
               return new Instance(label, weight, (Vector)func.apply(vec));
            } else {
               throw new MatchError(x0$1);
            }
         });
      }, instances.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Instance.class));
      long maxMemUsage = (long)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(actualBlockSizeInMB * (double)1024L * (double)1024L));
      RDD blocks = InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(standardized, maxMemUsage).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()).setName("training blocks (blockSizeInMB=" + actualBlockSizeInMB + ")");
      Function1 getAggregatorFunc = (x$4) -> new HingeBlockAggregator(bcInverseStd, bcScaledMean, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), x$4);
      RDDLossFunction costFun = new RDDLossFunction(blocks, getAggregatorFunc, regularization, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(InstanceBlock.class), scala.reflect.ClassTag..MODULE$.apply(HingeBlockAggregator.class));
      double[] initialSolution = (double[])scala.Array..MODULE$.ofDim(numFeaturesPlusIntercept, scala.reflect.ClassTag..MODULE$.Double());
      if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, initialSolution, 1, scaledMean, 1);
         initialSolution[numFeatures] += adapt;
      }

      Iterator states = optimizer.iterations(new CachedDiffFunction(costFun, breeze.linalg.DenseVector..MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double())), new DenseVector.mcD.sp(initialSolution));
      ArrayBuilder arrayBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      FirstOrderMinimizer.State state = null;

      while(states.hasNext()) {
         state = (FirstOrderMinimizer.State)states.next();
         arrayBuilder.$plus$eq(BoxesRunTime.boxToDouble(state.adjustedValue()));
      }

      blocks.unpersist(blocks.unpersist$default$1());
      bcInverseStd.destroy();
      bcScaledMean.destroy();
      double[] solution = state == null ? null : ((DenseVector)state.x()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && solution != null) {
         double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, solution, 1, scaledMean, 1);
         solution[numFeatures] -= adapt;
      }

      return new Tuple2(solution, arrayBuilder.result());
   }

   private static final Function1 regParamL1Fun$1() {
      return (JFunction1.mcDI.sp)(index) -> (double)0.0F;
   }

   public LinearSVC(final String uid) {
      this.uid = uid;
      HasRegParam.$init$(this);
      HasMaxIter.$init$(this);
      HasFitIntercept.$init$(this);
      HasTol.$init$(this);
      HasStandardization.$init$(this);
      HasWeightCol.$init$(this);
      HasAggregationDepth.$init$(this);
      HasThreshold.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      LinearSVCParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public LinearSVC() {
      this(Identifiable$.MODULE$.randomUID("linearsvc"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

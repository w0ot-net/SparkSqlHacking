package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001\u0002\t\u0012\u0005qA\u0001B\f\u0001\u0003\u0006\u0004%\te\f\u0005\t\r\u0002\u0011\t\u0011)A\u0005a!)\u0001\n\u0001C\u0001\u0013\")\u0001\n\u0001C\u0001\u001d\")\u0001\u000b\u0001C\u0001#\")!\f\u0001C\u00017\")a\f\u0001C\u0001?\")!\r\u0001C!G\")\u0011\u0010\u0001C!u\"9\u0011\u0011\u0002\u0001\u0005B\u0005-qaBA\u0011#!\u0005\u00111\u0005\u0004\u0007!EA\t!!\n\t\r!cA\u0011AA\"\u0011\u001d\t)\u0005\u0004C!\u0003\u000fB\u0011\"a\u0014\r\u0003\u0003%I!!\u0015\u00033Y\u000b'/[1oG\u0016$\u0006N]3tQ>dGmU3mK\u000e$xN\u001d\u0006\u0003%M\tqAZ3biV\u0014XM\u0003\u0002\u0015+\u0005\u0011Q\u000e\u001c\u0006\u0003-]\tQa\u001d9be.T!\u0001G\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0012aA8sO\u000e\u00011\u0003\u0002\u0001\u001eK!\u00022AH\u0010\"\u001b\u0005\u0019\u0012B\u0001\u0011\u0014\u0005%)5\u000f^5nCR|'\u000f\u0005\u0002#G5\t\u0011#\u0003\u0002%#\tqb+\u0019:jC:\u001cW\r\u00165sKNDw\u000e\u001c3TK2,7\r^8s\u001b>$W\r\u001c\t\u0003E\u0019J!aJ\t\u0003?Y\u000b'/[1oG\u0016$\u0006N]3tQ>dGmU3mK\u000e$xN\u001d)be\u0006l7\u000f\u0005\u0002*Y5\t!F\u0003\u0002,'\u0005!Q\u000f^5m\u0013\ti#FA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u00011!\t\t$H\u0004\u00023qA\u00111GN\u0007\u0002i)\u0011QgG\u0001\u0007yI|w\u000e\u001e \u000b\u0003]\nQa]2bY\u0006L!!\u000f\u001c\u0002\rA\u0013X\rZ3g\u0013\tYDH\u0001\u0004TiJLgn\u001a\u0006\u0003sYB3!\u0001 E!\ty$)D\u0001A\u0015\t\tU#\u0001\u0006b]:|G/\u0019;j_:L!a\u0011!\u0003\u000bMKgnY3\"\u0003\u0015\u000bQa\r\u00182]A\nA!^5eA!\u001a!A\u0010#\u0002\rqJg.\u001b;?)\tQ5\n\u0005\u0002#\u0001!)af\u0001a\u0001a!\u001a1J\u0010#)\u0007\rqD\tF\u0001KQ\r!a\bR\u0001\u0015g\u0016$h+\u0019:jC:\u001cW\r\u00165sKNDw\u000e\u001c3\u0015\u0005I\u001bV\"\u0001\u0001\t\u000bQ+\u0001\u0019A+\u0002\u000bY\fG.^3\u0011\u0005Y;V\"\u0001\u001c\n\u0005a3$A\u0002#pk\ndW\rK\u0002\u0006}\u0011\u000bab]3u\r\u0016\fG/\u001e:fg\u000e{G\u000e\u0006\u0002S9\")AK\u0002a\u0001a!\u001aaA\u0010#\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\u0005I\u0003\u0007\"\u0002+\b\u0001\u0004\u0001\u0004fA\u0004?\t\u0006\u0019a-\u001b;\u0015\u0005\u0005\"\u0007\"B3\t\u0001\u00041\u0017a\u00023bi\u0006\u001cX\r\u001e\u0019\u0003O>\u00042\u0001[6n\u001b\u0005I'B\u00016\u0016\u0003\r\u0019\u0018\u000f\\\u0005\u0003Y&\u0014q\u0001R1uCN,G\u000f\u0005\u0002o_2\u0001A!\u00039e\u0003\u0003\u0005\tQ!\u0001r\u0005\ryF%M\t\u0003eV\u0004\"AV:\n\u0005Q4$a\u0002(pi\"Lgn\u001a\t\u0003-ZL!a\u001e\u001c\u0003\u0007\u0005s\u0017\u0010K\u0002\t}\u0011\u000bq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0004w\u0006\r\u0001C\u0001?\u0000\u001b\u0005i(B\u0001@j\u0003\u0015!\u0018\u0010]3t\u0013\r\t\t! \u0002\u000b'R\u0014Xo\u0019;UsB,\u0007BBA\u0003\u0013\u0001\u000710\u0001\u0004tG\",W.\u0019\u0015\u0004\u0013y\"\u0015\u0001B2paf$2ASA\u0007\u0011\u001d\tyA\u0003a\u0001\u0003#\tQ!\u001a=ue\u0006\u0004B!a\u0005\u0002\u001a5\u0011\u0011Q\u0003\u0006\u0004\u0003/\u0019\u0012!\u00029be\u0006l\u0017\u0002BA\u000e\u0003+\u0011\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004\u0015y\"\u0005f\u0001\u0001?\t\u0006Ib+\u0019:jC:\u001cW\r\u00165sKNDw\u000e\u001c3TK2,7\r^8s!\t\u0011CbE\u0004\r\u0003O\ti#a\r\u0011\u0007Y\u000bI#C\u0002\u0002,Y\u0012a!\u00118z%\u00164\u0007\u0003B\u0015\u00020)K1!!\r+\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!!\u000e\u0002@5\u0011\u0011q\u0007\u0006\u0005\u0003s\tY$\u0001\u0002j_*\u0011\u0011QH\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002B\u0005]\"\u0001D*fe&\fG.\u001b>bE2,GCAA\u0012\u0003\u0011aw.\u00193\u0015\u0007)\u000bI\u0005\u0003\u0004\u0002L9\u0001\r\u0001M\u0001\u0005a\u0006$\b\u000eK\u0002\u000f}\u0011\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0015\u0011\t\u0005U\u00131L\u0007\u0003\u0003/RA!!\u0017\u0002<\u0005!A.\u00198h\u0013\u0011\ti&a\u0016\u0003\r=\u0013'.Z2uQ\raa\b\u0012\u0015\u0004\u0017y\"\u0005"
)
public final class VarianceThresholdSelector extends Estimator implements VarianceThresholdSelectorParams, DefaultParamsWritable {
   private final String uid;
   private DoubleParam varianceThreshold;
   private Param outputCol;
   private Param featuresCol;

   public static VarianceThresholdSelector load(final String path) {
      return VarianceThresholdSelector$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VarianceThresholdSelector$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getVarianceThreshold() {
      return VarianceThresholdSelectorParams.getVarianceThreshold$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final DoubleParam varianceThreshold() {
      return this.varianceThreshold;
   }

   public final void org$apache$spark$ml$feature$VarianceThresholdSelectorParams$_setter_$varianceThreshold_$eq(final DoubleParam x$1) {
      this.varianceThreshold = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public VarianceThresholdSelector setVarianceThreshold(final double value) {
      return (VarianceThresholdSelector)this.set(this.varianceThreshold(), BoxesRunTime.boxToDouble(value));
   }

   public VarianceThresholdSelector setFeaturesCol(final String value) {
      return (VarianceThresholdSelector)this.set(this.featuresCol(), value);
   }

   public VarianceThresholdSelector setOutputCol(final String value) {
      return (VarianceThresholdSelector)this.set(this.outputCol(), value);
   }

   public VarianceThresholdSelectorModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Row var4 = (Row)dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"max", "min", "variance"}))).summary(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))).as("summary")}))).select("summary.max", .MODULE$.wrapRefArray((Object[])(new String[]{"summary.min", "summary.variance"}))).first();
      if (var4 != null) {
         Some var5 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var4);
         if (!var5.isEmpty() && var5.get() != null && ((SeqOps)var5.get()).lengthCompare(3) == 0) {
            Object maxs = ((SeqOps)var5.get()).apply(0);
            Object mins = ((SeqOps)var5.get()).apply(1);
            Object variances = ((SeqOps)var5.get()).apply(2);
            if (maxs instanceof Vector) {
               Vector var9 = (Vector)maxs;
               if (mins instanceof Vector) {
                  Vector var10 = (Vector)mins;
                  if (variances instanceof Vector) {
                     Vector var11 = (Vector)variances;
                     Tuple3 var3 = new Tuple3(var9, var10, var11);
                     Vector maxs = (Vector)var3._1();
                     Vector mins = (Vector)var3._2();
                     Vector variances = (Vector)var3._3();
                     int numFeatures = maxs.size();
                     int[] indices = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.Array..MODULE$.tabulate(numFeatures, (i) -> $anonfun$fit$1(maxs, mins, variances, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$fit$2(this, x$2)))), (x$3) -> BoxesRunTime.boxToInteger($anonfun$fit$3(x$3)), scala.reflect.ClassTag..MODULE$.Int());
                     return (VarianceThresholdSelectorModel)this.copyValues((new VarianceThresholdSelectorModel(this.uid(), (int[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps(indices), scala.math.Ordering.Int..MODULE$))).setParent(this), this.copyValues$default$2());
                  }
               }
            }
         }
      }

      throw new MatchError(var4);
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   public VarianceThresholdSelector copy(final ParamMap extra) {
      return (VarianceThresholdSelector)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$fit$1(final Vector maxs$1, final Vector mins$1, final Vector variances$1, final int i) {
      return new Tuple2.mcID.sp(i, maxs$1.apply(i) == mins$1.apply(i) ? (double)0.0F : variances$1.apply(i));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$2(final VarianceThresholdSelector $this, final Tuple2 x$2) {
      return x$2._2$mcD$sp() > $this.getVarianceThreshold();
   }

   // $FF: synthetic method
   public static final int $anonfun$fit$3(final Tuple2 x$3) {
      return x$3._1$mcI$sp();
   }

   public VarianceThresholdSelector(final String uid) {
      this.uid = uid;
      HasFeaturesCol.$init$(this);
      HasOutputCol.$init$(this);
      VarianceThresholdSelectorParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public VarianceThresholdSelector() {
      this(Identifiable$.MODULE$.randomUID("VarianceThresholdSelector"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

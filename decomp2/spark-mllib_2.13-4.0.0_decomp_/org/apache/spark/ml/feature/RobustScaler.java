package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasRelativeError;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.catalyst.util.QuantileSummaries;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}f\u0001B\u000b\u0017\u0001\u0005B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0005\u000e\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005k!)Q\n\u0001C\u0001\u001d\")Q\n\u0001C\u0001'\")A\u000b\u0001C\u0001+\")\u0011\f\u0001C\u00015\")A\f\u0001C\u0001;\")1\r\u0001C\u0001I\")a\r\u0001C\u0001O\")A\u000e\u0001C\u0001[\")q\u000e\u0001C\u0001a\")!\u000f\u0001C!g\"9\u0011\u0011\u0003\u0001\u0005B\u0005M\u0001bBA\u0013\u0001\u0011\u0005\u0013qE\u0004\b\u0003w1\u0002\u0012AA\u001f\r\u0019)b\u0003#\u0001\u0002@!1Q\n\u0005C\u0001\u0003;B\u0001\"a\u0018\u0011\t\u0003A\u0012\u0011\r\u0005\b\u0003G\u0003B\u0011IAS\u0011%\tY\u000bEA\u0001\n\u0013\tiK\u0001\u0007S_\n,8\u000f^*dC2,'O\u0003\u0002\u00181\u00059a-Z1ukJ,'BA\r\u001b\u0003\tiGN\u0003\u0002\u001c9\u0005)1\u000f]1sW*\u0011QDH\u0001\u0007CB\f7\r[3\u000b\u0003}\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0012+[A\u00191\u0005\n\u0014\u000e\u0003aI!!\n\r\u0003\u0013\u0015\u001bH/[7bi>\u0014\bCA\u0014)\u001b\u00051\u0012BA\u0015\u0017\u0005E\u0011vNY;tiN\u001b\u0017\r\\3s\u001b>$W\r\u001c\t\u0003O-J!\u0001\f\f\u0003%I{'-^:u'\u000e\fG.\u001a:QCJ\fWn\u001d\t\u0003]Ej\u0011a\f\u0006\u0003aa\tA!\u001e;jY&\u0011!g\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002kA\u0011ag\u0010\b\u0003ou\u0002\"\u0001O\u001e\u000e\u0003eR!A\u000f\u0011\u0002\rq\u0012xn\u001c;?\u0015\u0005a\u0014!B:dC2\f\u0017B\u0001 <\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005yZ\u0004fA\u0001D\u0013B\u0011AiR\u0007\u0002\u000b*\u0011aIG\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001%F\u0005\u0015\u0019\u0016N\\2fC\u0005Q\u0015!B\u001a/a9\u0002\u0014\u0001B;jI\u0002B3AA\"J\u0003\u0019a\u0014N\\5u}Q\u0011q\n\u0015\t\u0003O\u0001AQaM\u0002A\u0002UB3\u0001U\"JQ\r\u00191)\u0013\u000b\u0002\u001f\u0006Y1/\u001a;J]B,HoQ8m)\t1v+D\u0001\u0001\u0011\u0015AV\u00011\u00016\u0003\u00151\u0018\r\\;f\u00031\u0019X\r^(viB,HoQ8m)\t16\fC\u0003Y\r\u0001\u0007Q'\u0001\u0005tKRdun^3s)\t1f\fC\u0003Y\u000f\u0001\u0007q\f\u0005\u0002aC6\t1(\u0003\u0002cw\t1Ai\\;cY\u0016\f\u0001b]3u+B\u0004XM\u001d\u000b\u0003-\u0016DQ\u0001\u0017\u0005A\u0002}\u000b\u0001c]3u/&$\bnQ3oi\u0016\u0014\u0018N\\4\u0015\u0005YC\u0007\"\u0002-\n\u0001\u0004I\u0007C\u00011k\u0013\tY7HA\u0004C_>dW-\u00198\u0002\u001dM,GoV5uQN\u001b\u0017\r\\5oOR\u0011aK\u001c\u0005\u00061*\u0001\r![\u0001\u0011g\u0016$(+\u001a7bi&4X-\u0012:s_J$\"AV9\t\u000ba[\u0001\u0019A0\u0002\u0007\u0019LG\u000f\u0006\u0002'i\")Q\u000f\u0004a\u0001m\u00069A-\u0019;bg\u0016$\bGA<\u0000!\rA80`\u0007\u0002s*\u0011!PG\u0001\u0004gFd\u0017B\u0001?z\u0005\u001d!\u0015\r^1tKR\u0004\"A`@\r\u0001\u0011Y\u0011\u0011\u0001;\u0002\u0002\u0003\u0005)\u0011AA\u0002\u0005\ryF%M\t\u0005\u0003\u000b\tY\u0001E\u0002a\u0003\u000fI1!!\u0003<\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001YA\u0007\u0013\r\tya\u000f\u0002\u0004\u0003:L\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005U\u0011\u0011\u0005\t\u0005\u0003/\ti\"\u0004\u0002\u0002\u001a)\u0019\u00111D=\u0002\u000bQL\b/Z:\n\t\u0005}\u0011\u0011\u0004\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\u0012\u001b\u0001\u0007\u0011QC\u0001\u0007g\u000eDW-\\1\u0002\t\r|\u0007/\u001f\u000b\u0004\u001f\u0006%\u0002bBA\u0016\u001d\u0001\u0007\u0011QF\u0001\u0006Kb$(/\u0019\t\u0005\u0003_\t)$\u0004\u0002\u00022)\u0019\u00111\u0007\r\u0002\u000bA\f'/Y7\n\t\u0005]\u0012\u0011\u0007\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001a\u0001aQ%\u0002\u0019I{'-^:u'\u000e\fG.\u001a:\u0011\u0005\u001d\u00022c\u0002\t\u0002B\u0005\u001d\u0013Q\n\t\u0004A\u0006\r\u0013bAA#w\t1\u0011I\\=SK\u001a\u0004BALA%\u001f&\u0019\u00111J\u0018\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011qJA-\u001b\t\t\tF\u0003\u0003\u0002T\u0005U\u0013AA5p\u0015\t\t9&\u0001\u0003kCZ\f\u0017\u0002BA.\u0003#\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"!!\u0010\u0002!\r|W\u000e];uKN+X.\\1sS\u0016\u001cH\u0003CA2\u0003\u0013\u000bY*a(\u0011\r\u0005\u0015\u00141NA8\u001b\t\t9GC\u0002\u0002ji\t1A\u001d3e\u0013\u0011\ti'a\u001a\u0003\u0007I#E\tE\u0004a\u0003c\n)(a\u001f\n\u0007\u0005M4H\u0001\u0004UkBdWM\r\t\u0004A\u0006]\u0014bAA=w\t\u0019\u0011J\u001c;\u0011\t\u0005u\u0014QQ\u0007\u0003\u0003\u007fR1\u0001MAA\u0015\r\t\u0019)_\u0001\tG\u0006$\u0018\r\\=ti&!\u0011qQA@\u0005E\tV/\u00198uS2,7+^7nCJLWm\u001d\u0005\b\u0003\u0017\u0013\u0002\u0019AAG\u0003\u001d1Xm\u0019;peN\u0004b!!\u001a\u0002l\u0005=\u0005\u0003BAI\u0003/k!!a%\u000b\u0007\u0005U\u0005$\u0001\u0004mS:\fGnZ\u0005\u0005\u00033\u000b\u0019J\u0001\u0004WK\u000e$xN\u001d\u0005\b\u0003;\u0013\u0002\u0019AA;\u0003-qW/\u001c$fCR,(/Z:\t\r\u0005\u0005&\u00031\u0001`\u00035\u0011X\r\\1uSZ,WI\u001d:pe\u0006!An\\1e)\ry\u0015q\u0015\u0005\u0007\u0003S\u001b\u0002\u0019A\u001b\u0002\tA\fG\u000f[\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003_\u0003B!!-\u000286\u0011\u00111\u0017\u0006\u0005\u0003k\u000b)&\u0001\u0003mC:<\u0017\u0002BA]\u0003g\u0013aa\u00142kK\u000e$\bf\u0001\tD\u0013\"\u001aqbQ%"
)
public class RobustScaler extends Estimator implements RobustScalerParams, DefaultParamsWritable {
   private final String uid;
   private DoubleParam lower;
   private DoubleParam upper;
   private BooleanParam withCentering;
   private BooleanParam withScaling;
   private DoubleParam relativeError;
   private Param outputCol;
   private Param inputCol;

   public static RobustScaler load(final String path) {
      return RobustScaler$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RobustScaler$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getLower() {
      return RobustScalerParams.getLower$(this);
   }

   public double getUpper() {
      return RobustScalerParams.getUpper$(this);
   }

   public boolean getWithCentering() {
      return RobustScalerParams.getWithCentering$(this);
   }

   public boolean getWithScaling() {
      return RobustScalerParams.getWithScaling$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return RobustScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final double getRelativeError() {
      return HasRelativeError.getRelativeError$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public DoubleParam lower() {
      return this.lower;
   }

   public DoubleParam upper() {
      return this.upper;
   }

   public BooleanParam withCentering() {
      return this.withCentering;
   }

   public BooleanParam withScaling() {
      return this.withScaling;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$lower_$eq(final DoubleParam x$1) {
      this.lower = x$1;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$upper_$eq(final DoubleParam x$1) {
      this.upper = x$1;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$withCentering_$eq(final BooleanParam x$1) {
      this.withCentering = x$1;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$withScaling_$eq(final BooleanParam x$1) {
      this.withScaling = x$1;
   }

   public final DoubleParam relativeError() {
      return this.relativeError;
   }

   public final void org$apache$spark$ml$param$shared$HasRelativeError$_setter_$relativeError_$eq(final DoubleParam x$1) {
      this.relativeError = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public RobustScaler setInputCol(final String value) {
      return (RobustScaler)this.set(this.inputCol(), value);
   }

   public RobustScaler setOutputCol(final String value) {
      return (RobustScaler)this.set(this.outputCol(), value);
   }

   public RobustScaler setLower(final double value) {
      return (RobustScaler)this.set(this.lower(), BoxesRunTime.boxToDouble(value));
   }

   public RobustScaler setUpper(final double value) {
      return (RobustScaler)this.set(this.upper(), BoxesRunTime.boxToDouble(value));
   }

   public RobustScaler setWithCentering(final boolean value) {
      return (RobustScaler)this.set(this.withCentering(), BoxesRunTime.boxToBoolean(value));
   }

   public RobustScaler setWithScaling(final boolean value) {
      return (RobustScaler)this.set(this.withScaling(), BoxesRunTime.boxToBoolean(value));
   }

   public RobustScaler setRelativeError(final double value) {
      return (RobustScaler)this.set(this.relativeError(), BoxesRunTime.boxToDouble(value));
   }

   public RobustScalerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.inputCol()));
      RDD vectors = dataset.select((String)this.$(this.inputCol()), .MODULE$).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var4 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(1) == 0) {
               Object vec = ((SeqOps)var4.get()).apply(0);
               if (vec instanceof Vector) {
                  Vector var6 = (Vector)vec;
                  scala.Predef..MODULE$.require(var6.size() == numFeatures, () -> "Number of dimensions must be " + numFeatures + " but got " + var6.size());
                  return var6;
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      double localUpper = BoxesRunTime.unboxToDouble(this.$(this.upper()));
      double localLower = BoxesRunTime.unboxToDouble(this.$(this.lower()));
      Tuple2 var10 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(RobustScaler$.MODULE$.computeSummaries(vectors, numFeatures, BoxesRunTime.unboxToDouble(this.$(this.relativeError()))), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(QuantileSummaries.class), scala.math.Ordering.Int..MODULE$).mapValues((s) -> {
         double range = BoxesRunTime.unboxToDouble(s.query(localUpper).get()) - BoxesRunTime.unboxToDouble(s.query(localLower).get());
         double median = BoxesRunTime.unboxToDouble(s.query((double)0.5F).get());
         return new Tuple2.mcDD.sp(range, median);
      }).collect()), (x$1) -> BoxesRunTime.boxToInteger($anonfun$fit$4(x$1)), scala.math.Ordering.Int..MODULE$)), (x$2) -> (Tuple2)x$2._2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double());
      if (var10 != null) {
         double[] ranges = (double[])var10._1();
         double[] medians = (double[])var10._2();
         Tuple2 var9 = new Tuple2(ranges, medians);
         double[] ranges = (double[])var9._1();
         double[] medians = (double[])var9._2();
         scala.Predef..MODULE$.require(ranges.length == numFeatures, () -> "QuantileSummaries on some features are missing");
         return (RobustScalerModel)this.copyValues((new RobustScalerModel(this.uid(), org.apache.spark.ml.linalg.Vectors..MODULE$.dense(ranges).compressed(), org.apache.spark.ml.linalg.Vectors..MODULE$.dense(medians).compressed())).setParent(this), this.copyValues$default$2());
      } else {
         throw new MatchError(var10);
      }
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public RobustScaler copy(final ParamMap extra) {
      return (RobustScaler)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final int $anonfun$fit$4(final Tuple2 x$1) {
      return x$1._1$mcI$sp();
   }

   public RobustScaler(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasRelativeError.$init$(this);
      RobustScalerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public RobustScaler() {
      this(Identifiable$.MODULE$.randomUID("robustScal"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

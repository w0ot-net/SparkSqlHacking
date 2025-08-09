package org.apache.spark.ml.feature;

import java.io.IOException;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001B\t\u0013\u0001uA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0005\r\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005c!)\u0011\n\u0001C\u0001\u0015\")\u0011\n\u0001C\u0001\u001f\")1\u000b\u0001C\u0001)\")\u0011\f\u0001C\u00015\")Q\f\u0001C\u0001=\")Q\r\u0001C\u0001M\")\u0011\u000e\u0001C!U\"9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0001bBA\u000e\u0001\u0011\u0005\u0013QD\u0004\b\u0003o\u0011\u0002\u0012AA\u001d\r\u0019\t\"\u0003#\u0001\u0002<!1\u0011*\u0004C\u0001\u00033Bq!a\u0017\u000e\t\u0003\ni\u0006C\u0005\u0002j5\t\t\u0011\"\u0003\u0002l\tq1\u000b^1oI\u0006\u0014HmU2bY\u0016\u0014(BA\n\u0015\u0003\u001d1W-\u0019;ve\u0016T!!\u0006\f\u0002\u00055d'BA\f\u0019\u0003\u0015\u0019\b/\u0019:l\u0015\tI\"$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0005\u0019qN]4\u0004\u0001M!\u0001A\b\u0014*!\ry\u0002EI\u0007\u0002)%\u0011\u0011\u0005\u0006\u0002\n\u000bN$\u0018.\\1u_J\u0004\"a\t\u0013\u000e\u0003II!!\n\n\u0003'M#\u0018M\u001c3be\u0012\u001c6-\u00197fe6{G-\u001a7\u0011\u0005\r:\u0013B\u0001\u0015\u0013\u0005Q\u0019F/\u00198eCJ$7kY1mKJ\u0004\u0016M]1ngB\u0011!&L\u0007\u0002W)\u0011A\u0006F\u0001\u0005kRLG.\u0003\u0002/W\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t\u0011\u0007\u0005\u00023w9\u00111'\u000f\t\u0003i]j\u0011!\u000e\u0006\u0003mq\ta\u0001\u0010:p_Rt$\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005i:\u0014A\u0002)sK\u0012,g-\u0003\u0002={\t11\u000b\u001e:j]\u001eT!AO\u001c)\u0007\u0005yT\t\u0005\u0002A\u00076\t\u0011I\u0003\u0002C-\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011\u000b%!B*j]\u000e,\u0017%\u0001$\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005}*\u0015A\u0002\u001fj]&$h\b\u0006\u0002L\u0019B\u00111\u0005\u0001\u0005\u0006_\r\u0001\r!\r\u0015\u0004\u0019~*\u0005fA\u0002@\u000bR\t1\nK\u0002\u0005\u007fE\u000b\u0013AU\u0001\u0006c9\u0012d\u0006M\u0001\fg\u0016$\u0018J\u001c9vi\u000e{G\u000e\u0006\u0002V-6\t\u0001\u0001C\u0003X\u000b\u0001\u0007\u0011'A\u0003wC2,X\rK\u0002\u0006\u007fE\u000bAb]3u\u001fV$\b/\u001e;D_2$\"!V.\t\u000b]3\u0001\u0019A\u0019)\u0007\u0019y\u0014+A\u0006tKR<\u0016\u000e\u001e5NK\u0006tGCA+`\u0011\u00159v\u00011\u0001a!\t\t'-D\u00018\u0013\t\u0019wGA\u0004C_>dW-\u00198)\u0007\u001dyT)\u0001\u0006tKR<\u0016\u000e\u001e5Ti\u0012$\"!V4\t\u000b]C\u0001\u0019\u00011)\u0007!yT)A\u0002gSR$\"AI6\t\u000b1L\u0001\u0019A7\u0002\u000f\u0011\fG/Y:fiB\u0012aN\u001e\t\u0004_J$X\"\u00019\u000b\u0005E4\u0012aA:rY&\u00111\u000f\u001d\u0002\b\t\u0006$\u0018m]3u!\t)h\u000f\u0004\u0001\u0005\u0013]\\\u0017\u0011!A\u0001\u0006\u0003A(aA0%cE\u0011\u0011\u0010 \t\u0003CjL!a_\u001c\u0003\u000f9{G\u000f[5oOB\u0011\u0011-`\u0005\u0003}^\u00121!\u00118zQ\u0011Iq(!\u0001\"\u0005\u0005\r\u0011!\u0002\u001a/a9\u0002\u0014a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005%\u0011Q\u0003\t\u0005\u0003\u0017\t\t\"\u0004\u0002\u0002\u000e)\u0019\u0011q\u00029\u0002\u000bQL\b/Z:\n\t\u0005M\u0011Q\u0002\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\f\u0015\u0001\u0007\u0011\u0011B\u0001\u0007g\u000eDW-\\1)\u0007)yT)\u0001\u0003d_BLHcA&\u0002 !9\u0011\u0011E\u0006A\u0002\u0005\r\u0012!B3yiJ\f\u0007\u0003BA\u0013\u0003Wi!!a\n\u000b\u0007\u0005%B#A\u0003qCJ\fW.\u0003\u0003\u0002.\u0005\u001d\"\u0001\u0003)be\u0006lW*\u00199)\t-y\u0014\u0011G\u0011\u0003\u0003g\tQ!\r\u00185]EB3\u0001A R\u00039\u0019F/\u00198eCJ$7kY1mKJ\u0004\"aI\u0007\u0014\u000f5\ti$a\u0011\u0002JA\u0019\u0011-a\u0010\n\u0007\u0005\u0005sG\u0001\u0004B]f\u0014VM\u001a\t\u0005U\u0005\u00153*C\u0002\u0002H-\u0012Q\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eC\ndW\r\u0005\u0003\u0002L\u0005USBAA'\u0015\u0011\ty%!\u0015\u0002\u0005%|'BAA*\u0003\u0011Q\u0017M^1\n\t\u0005]\u0013Q\n\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003s\tA\u0001\\8bIR\u00191*a\u0018\t\r\u0005\u0005t\u00021\u00012\u0003\u0011\u0001\u0018\r\u001e5)\t=y\u0014QM\u0011\u0003\u0003O\nQ!\r\u00187]A\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001c\u0011\t\u0005=\u0014QO\u0007\u0003\u0003cRA!a\u001d\u0002R\u0005!A.\u00198h\u0013\u0011\t9(!\u001d\u0003\r=\u0013'.Z2uQ\u0011iq(!\u001a)\t1y\u0014Q\r"
)
public class StandardScaler extends Estimator implements StandardScalerParams, DefaultParamsWritable {
   private final String uid;
   private BooleanParam withMean;
   private BooleanParam withStd;
   private Param outputCol;
   private Param inputCol;

   public static StandardScaler load(final String path) {
      return StandardScaler$.MODULE$.load(path);
   }

   public static MLReader read() {
      return StandardScaler$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public boolean getWithMean() {
      return StandardScalerParams.getWithMean$(this);
   }

   public boolean getWithStd() {
      return StandardScalerParams.getWithStd$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return StandardScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public BooleanParam withMean() {
      return this.withMean;
   }

   public BooleanParam withStd() {
      return this.withStd;
   }

   public void org$apache$spark$ml$feature$StandardScalerParams$_setter_$withMean_$eq(final BooleanParam x$1) {
      this.withMean = x$1;
   }

   public void org$apache$spark$ml$feature$StandardScalerParams$_setter_$withStd_$eq(final BooleanParam x$1) {
      this.withStd = x$1;
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

   public StandardScaler setInputCol(final String value) {
      return (StandardScaler)this.set(this.inputCol(), value);
   }

   public StandardScaler setOutputCol(final String value) {
      return (StandardScaler)this.set(this.outputCol(), value);
   }

   public StandardScaler setWithMean(final boolean value) {
      return (StandardScaler)this.set(this.withMean(), BoxesRunTime.boxToBoolean(value));
   }

   public StandardScaler setWithStd(final boolean value) {
      return (StandardScaler)this.set(this.withStd(), BoxesRunTime.boxToBoolean(value));
   }

   public StandardScalerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Row var4 = (Row)dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"mean", "std"}))).summary(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))).as("summary")}))).select("summary.mean", .MODULE$.wrapRefArray((Object[])(new String[]{"summary.std"}))).first();
      if (var4 != null) {
         Some var5 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var4);
         if (!var5.isEmpty() && var5.get() != null && ((SeqOps)var5.get()).lengthCompare(2) == 0) {
            Object mean = ((SeqOps)var5.get()).apply(0);
            Object std = ((SeqOps)var5.get()).apply(1);
            if (mean instanceof Vector) {
               Vector var8 = (Vector)mean;
               if (std instanceof Vector) {
                  Vector var9 = (Vector)std;
                  Tuple2 var3 = new Tuple2(var8, var9);
                  Vector mean = (Vector)var3._1();
                  Vector std = (Vector)var3._2();
                  return (StandardScalerModel)this.copyValues((new StandardScalerModel(this.uid(), std.compressed(), mean.compressed())).setParent(this), this.copyValues$default$2());
               }
            }
         }
      }

      throw new MatchError(var4);
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public StandardScaler copy(final ParamMap extra) {
      return (StandardScaler)this.defaultCopy(extra);
   }

   public StandardScaler(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      StandardScalerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public StandardScaler() {
      this(Identifiable$.MODULE$.randomUID("stdScal"));
   }
}

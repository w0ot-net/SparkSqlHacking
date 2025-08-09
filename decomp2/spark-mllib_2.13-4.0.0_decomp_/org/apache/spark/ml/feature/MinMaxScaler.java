package org.apache.spark.ml.feature;

import java.io.IOException;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
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
   bytes = "\u0006\u0005\u0005Ud\u0001B\t\u0013\u0001uA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0005\r\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005c!)\u0011\n\u0001C\u0001\u0015\")\u0011\n\u0001C\u0001\u001f\")\u0011\u000b\u0001C\u0001%\")q\u000b\u0001C\u00011\")1\f\u0001C\u00019\")1\r\u0001C\u0001I\")q\r\u0001C!Q\"9\u0011\u0011\u0001\u0001\u0005B\u0005\r\u0001bBA\f\u0001\u0011\u0005\u0013\u0011D\u0004\b\u0003_\u0011\u0002\u0012AA\u0019\r\u0019\t\"\u0003#\u0001\u00024!1\u0011*\u0004C\u0001\u0003#Bq!a\u0015\u000e\t\u0003\n)\u0006C\u0005\u0002b5\t\t\u0011\"\u0003\u0002d\taQ*\u001b8NCb\u001c6-\u00197fe*\u00111\u0003F\u0001\bM\u0016\fG/\u001e:f\u0015\t)b#\u0001\u0002nY*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001qb%\u000b\t\u0004?\u0001\u0012S\"\u0001\u000b\n\u0005\u0005\"\"!C#ti&l\u0017\r^8s!\t\u0019C%D\u0001\u0013\u0013\t)#CA\tNS:l\u0015\r_*dC2,'/T8eK2\u0004\"aI\u0014\n\u0005!\u0012\"AE'j]6\u000b\u0007pU2bY\u0016\u0014\b+\u0019:b[N\u0004\"AK\u0017\u000e\u0003-R!\u0001\f\u000b\u0002\tU$\u0018\u000e\\\u0005\u0003]-\u0012Q\u0003R3gCVdG\u000fU1sC6\u001cxK]5uC\ndW-A\u0002vS\u0012,\u0012!\r\t\u0003emr!aM\u001d\u0011\u0005Q:T\"A\u001b\u000b\u0005Yb\u0012A\u0002\u001fs_>$hHC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQt'\u0001\u0004Qe\u0016$WMZ\u0005\u0003yu\u0012aa\u0015;sS:<'B\u0001\u001e8Q\r\tq(\u0012\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005Z\t!\"\u00198o_R\fG/[8o\u0013\t!\u0015IA\u0003TS:\u001cW-I\u0001G\u0003\u0015\td&\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\tyT)\u0001\u0004=S:LGO\u0010\u000b\u0003\u00172\u0003\"a\t\u0001\t\u000b=\u001a\u0001\u0019A\u0019)\u00071{T\tK\u0002\u0004\u007f\u0015#\u0012a\u0013\u0015\u0004\t}*\u0015aC:fi&s\u0007/\u001e;D_2$\"a\u0015+\u000e\u0003\u0001AQ!V\u0003A\u0002E\nQA^1mk\u0016D3!B F\u00031\u0019X\r^(viB,HoQ8m)\t\u0019\u0016\fC\u0003V\r\u0001\u0007\u0011\u0007K\u0002\u0007\u007f\u0015\u000baa]3u\u001b&tGCA*^\u0011\u0015)v\u00011\u0001_!\ty\u0006-D\u00018\u0013\t\twG\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000f}*\u0015AB:fi6\u000b\u0007\u0010\u0006\u0002TK\")Q\u000b\u0003a\u0001=\"\u001a\u0001bP#\u0002\u0007\u0019LG\u000f\u0006\u0002#S\")!.\u0003a\u0001W\u00069A-\u0019;bg\u0016$\bG\u00017u!\ri\u0007O]\u0007\u0002]*\u0011qNF\u0001\u0004gFd\u0017BA9o\u0005\u001d!\u0015\r^1tKR\u0004\"a\u001d;\r\u0001\u0011IQ/[A\u0001\u0002\u0003\u0015\tA\u001e\u0002\u0004?\u0012\n\u0014CA<{!\ty\u00060\u0003\u0002zo\t9aj\u001c;iS:<\u0007CA0|\u0013\taxGA\u0002B]fD3!C \u007fC\u0005y\u0018!\u0002\u001a/a9\u0002\u0014a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005\u0015\u0011\u0011\u0003\t\u0005\u0003\u000f\ti!\u0004\u0002\u0002\n)\u0019\u00111\u00028\u0002\u000bQL\b/Z:\n\t\u0005=\u0011\u0011\u0002\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\n\u0015\u0001\u0007\u0011QA\u0001\u0007g\u000eDW-\\1)\u0007)yT)\u0001\u0003d_BLHcA&\u0002\u001c!9\u0011QD\u0006A\u0002\u0005}\u0011!B3yiJ\f\u0007\u0003BA\u0011\u0003Oi!!a\t\u000b\u0007\u0005\u0015B#A\u0003qCJ\fW.\u0003\u0003\u0002*\u0005\r\"\u0001\u0003)be\u0006lW*\u00199)\u0007-yT\tK\u0002\u0001\u007f\u0015\u000bA\"T5o\u001b\u0006D8kY1mKJ\u0004\"aI\u0007\u0014\u000f5\t)$a\u000f\u0002BA\u0019q,a\u000e\n\u0007\u0005erG\u0001\u0004B]f\u0014VM\u001a\t\u0005U\u0005u2*C\u0002\u0002@-\u0012Q\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eC\ndW\r\u0005\u0003\u0002D\u00055SBAA#\u0015\u0011\t9%!\u0013\u0002\u0005%|'BAA&\u0003\u0011Q\u0017M^1\n\t\u0005=\u0013Q\t\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003c\tA\u0001\\8bIR\u00191*a\u0016\t\r\u0005es\u00021\u00012\u0003\u0011\u0001\u0018\r\u001e5)\t=y\u0014QL\u0011\u0003\u0003?\nQ!\r\u00187]A\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001a\u0011\t\u0005\u001d\u0014QN\u0007\u0003\u0003SRA!a\u001b\u0002J\u0005!A.\u00198h\u0013\u0011\ty'!\u001b\u0003\r=\u0013'.Z2uQ\u0011iq(!\u0018)\t1y\u0014Q\f"
)
public class MinMaxScaler extends Estimator implements MinMaxScalerParams, DefaultParamsWritable {
   private final String uid;
   private DoubleParam min;
   private DoubleParam max;
   private Param outputCol;
   private Param inputCol;

   public static MinMaxScaler load(final String path) {
      return MinMaxScaler$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MinMaxScaler$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getMin() {
      return MinMaxScalerParams.getMin$(this);
   }

   public double getMax() {
      return MinMaxScalerParams.getMax$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return MinMaxScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public DoubleParam min() {
      return this.min;
   }

   public DoubleParam max() {
      return this.max;
   }

   public void org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$min_$eq(final DoubleParam x$1) {
      this.min = x$1;
   }

   public void org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$max_$eq(final DoubleParam x$1) {
      this.max = x$1;
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

   public MinMaxScaler setInputCol(final String value) {
      return (MinMaxScaler)this.set(this.inputCol(), value);
   }

   public MinMaxScaler setOutputCol(final String value) {
      return (MinMaxScaler)this.set(this.outputCol(), value);
   }

   public MinMaxScaler setMin(final double value) {
      return (MinMaxScaler)this.set(this.min(), BoxesRunTime.boxToDouble(value));
   }

   public MinMaxScaler setMax(final double value) {
      return (MinMaxScaler)this.set(this.max(), BoxesRunTime.boxToDouble(value));
   }

   public MinMaxScalerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Row var4 = (Row)dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"max", "min"}))).summary(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))).as("summary")}))).select("summary.max", .MODULE$.wrapRefArray((Object[])(new String[]{"summary.min"}))).first();
      if (var4 != null) {
         Some var5 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var4);
         if (!var5.isEmpty() && var5.get() != null && ((SeqOps)var5.get()).lengthCompare(2) == 0) {
            Object max = ((SeqOps)var5.get()).apply(0);
            Object min = ((SeqOps)var5.get()).apply(1);
            if (max instanceof Vector) {
               Vector var8 = (Vector)max;
               if (min instanceof Vector) {
                  Vector var9 = (Vector)min;
                  Tuple2 var3 = new Tuple2(var8, var9);
                  Vector max = (Vector)var3._1();
                  Vector min = (Vector)var3._2();
                  return (MinMaxScalerModel)this.copyValues((new MinMaxScalerModel(this.uid(), min.compressed(), max.compressed())).setParent(this), this.copyValues$default$2());
               }
            }
         }
      }

      throw new MatchError(var4);
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public MinMaxScaler copy(final ParamMap extra) {
      return (MinMaxScaler)this.defaultCopy(extra);
   }

   public MinMaxScaler(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      MinMaxScalerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public MinMaxScaler() {
      this(Identifiable$.MODULE$.randomUID("minMaxScal"));
   }
}

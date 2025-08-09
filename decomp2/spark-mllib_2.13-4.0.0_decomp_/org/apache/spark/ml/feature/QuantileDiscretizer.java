package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntArrayParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.param.shared.HasRelativeError;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001\u0002\f\u0018\u0005\tB\u0001\u0002\u000e\u0001\u0003\u0006\u0004%\t%\u000e\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005m!)a\n\u0001C\u0001\u001f\")a\n\u0001C\u0001)\")a\u000b\u0001C\u0001/\")!\r\u0001C\u0001G\")\u0011\u000e\u0001C\u0001U\")Q\u000e\u0001C\u0001]\")\u0011\u000f\u0001C\u0001e\")q\u000f\u0001C\u0001q\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003'\u0001A\u0011IA\u000b\u0011\u001d\ti\u0003\u0001C!\u0003_Aq!a\u0016\u0001\t\u0013\tI\u0006C\u0004\u0002b\u0001!\t%a\u0019\b\u000f\u0005et\u0003#\u0001\u0002|\u00191ac\u0006E\u0001\u0003{BaA\u0014\n\u0005\u0002\u0005\u001d\u0006bBAU%\u0011\u0005\u00131\u0016\u0005\n\u0003g\u0013\u0012\u0011!C\u0005\u0003k\u00131#U;b]RLG.\u001a#jg\u000e\u0014X\r^5{KJT!\u0001G\r\u0002\u000f\u0019,\u0017\r^;sK*\u0011!dG\u0001\u0003[2T!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\u0002\u0001'\u0011\u00011e\u000b\u0018\u0011\u0007\u0011*s%D\u0001\u001a\u0013\t1\u0013DA\u0005FgRLW.\u0019;peB\u0011\u0001&K\u0007\u0002/%\u0011!f\u0006\u0002\u000b\u0005V\u001c7.\u001a;ju\u0016\u0014\bC\u0001\u0015-\u0013\tisCA\fRk\u0006tG/\u001b7f\t&\u001c8M]3uSj,'OQ1tKB\u0011qFM\u0007\u0002a)\u0011\u0011'G\u0001\u0005kRLG.\u0003\u00024a\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\ta\u0007\u0005\u00028\u0001:\u0011\u0001H\u0010\t\u0003sqj\u0011A\u000f\u0006\u0003w\u0005\na\u0001\u0010:p_Rt$\"A\u001f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}b\u0014A\u0002)sK\u0012,g-\u0003\u0002B\u0005\n11\u000b\u001e:j]\u001eT!a\u0010\u001f)\u0007\u0005!%\n\u0005\u0002F\u00116\taI\u0003\u0002H7\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005%3%!B*j]\u000e,\u0017%A&\u0002\u000bErcG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005\u0011S\u0015A\u0002\u001fj]&$h\b\u0006\u0002Q#B\u0011\u0001\u0006\u0001\u0005\u0006i\r\u0001\rA\u000e\u0015\u0004#\u0012S\u0005fA\u0002E\u0015R\t\u0001\u000bK\u0002\u0005\t*\u000b\u0001c]3u%\u0016d\u0017\r^5wK\u0016\u0013(o\u001c:\u0015\u0005aKV\"\u0001\u0001\t\u000bi+\u0001\u0019A.\u0002\u000bY\fG.^3\u0011\u0005qkV\"\u0001\u001f\n\u0005yc$A\u0002#pk\ndW\rK\u0002\u0006\t\u0002\f\u0013!Y\u0001\u0006e9\u0002d\u0006M\u0001\u000eg\u0016$h*^7Ck\u000e\\W\r^:\u0015\u0005a#\u0007\"\u0002.\u0007\u0001\u0004)\u0007C\u0001/g\u0013\t9GHA\u0002J]RD3A\u0002#K\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\u0005a[\u0007\"\u0002.\b\u0001\u00041\u0004fA\u0004E\u0015\u0006a1/\u001a;PkR\u0004X\u000f^\"pYR\u0011\u0001l\u001c\u0005\u00065\"\u0001\rA\u000e\u0015\u0004\u0011\u0011S\u0015\u0001E:fi\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5e)\tA6\u000fC\u0003[\u0013\u0001\u0007a\u0007K\u0002\n\tV\f\u0013A^\u0001\u0006e9\nd\u0006M\u0001\u0013g\u0016$h*^7Ck\u000e\\W\r^:BeJ\f\u0017\u0010\u0006\u0002Ys\")!L\u0003a\u0001uB\u0019Al_3\n\u0005qd$!B!se\u0006L\bf\u0001\u0006E}\u0006\nq0A\u00033]Mr\u0003'\u0001\u0007tKRLe\u000e];u\u0007>d7\u000fF\u0002Y\u0003\u000bAaAW\u0006A\u0002\u0005\u001d\u0001c\u0001/|m!\u001a1\u0002\u0012@\u0002\u001bM,GoT;uaV$8i\u001c7t)\rA\u0016q\u0002\u0005\u000752\u0001\r!a\u0002)\u00071!e0A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\t9\"a\n\u0011\t\u0005e\u00111E\u0007\u0003\u00037QA!!\b\u0002 \u0005)A/\u001f9fg*\u0019\u0011\u0011E\u000e\u0002\u0007M\fH.\u0003\u0003\u0002&\u0005m!AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011F\u0007A\u0002\u0005]\u0011AB:dQ\u0016l\u0017\rK\u0002\u000e\t*\u000b1AZ5u)\r9\u0013\u0011\u0007\u0005\b\u0003gq\u0001\u0019AA\u001b\u0003\u001d!\u0017\r^1tKR\u0004D!a\u000e\u0002DA1\u0011\u0011HA\u001e\u0003\u007fi!!a\b\n\t\u0005u\u0012q\u0004\u0002\b\t\u0006$\u0018m]3u!\u0011\t\t%a\u0011\r\u0001\u0011a\u0011QIA\u0019\u0003\u0003\u0005\tQ!\u0001\u0002H\t\u0019q\fJ\u0019\u0012\t\u0005%\u0013q\n\t\u00049\u0006-\u0013bAA'y\t9aj\u001c;iS:<\u0007c\u0001/\u0002R%\u0019\u00111\u000b\u001f\u0003\u0007\u0005s\u0017\u0010K\u0002\u000f\t\u0002\f\u0011cZ3u\t&\u001cH/\u001b8diN\u0003H.\u001b;t)\u0011\tY&!\u0018\u0011\u0007q[8\fC\u0004\u0002`=\u0001\r!a\u0017\u0002\rM\u0004H.\u001b;t\u0003\u0011\u0019w\u000e]=\u0015\u0007A\u000b)\u0007C\u0004\u0002hA\u0001\r!!\u001b\u0002\u000b\u0015DHO]1\u0011\t\u0005-\u0014\u0011O\u0007\u0003\u0003[R1!a\u001c\u001a\u0003\u0015\u0001\u0018M]1n\u0013\u0011\t\u0019(!\u001c\u0003\u0011A\u000b'/Y7NCBD3\u0001\u0005#KQ\r\u0001AIS\u0001\u0014#V\fg\u000e^5mK\u0012K7o\u0019:fi&TXM\u001d\t\u0003QI\u0019\u0012BEA@\u0003\u000b\u000bY)a&\u0011\u0007q\u000b\t)C\u0002\u0002\u0004r\u0012a!\u00118z%\u00164\u0007\u0003B\u0018\u0002\bBK1!!#1\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!!$\u0002\u00146\u0011\u0011q\u0012\u0006\u0004\u0003#[\u0012\u0001C5oi\u0016\u0014h.\u00197\n\t\u0005U\u0015q\u0012\u0002\b\u0019><w-\u001b8h!\u0011\tI*a)\u000e\u0005\u0005m%\u0002BAO\u0003?\u000b!![8\u000b\u0005\u0005\u0005\u0016\u0001\u00026bm\u0006LA!!*\u0002\u001c\na1+\u001a:jC2L'0\u00192mKR\u0011\u00111P\u0001\u0005Y>\fG\rF\u0002Q\u0003[Ca!a,\u0015\u0001\u00041\u0014\u0001\u00029bi\"D3\u0001\u0006#K\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\f\u0005\u0003\u0002:\u0006}VBAA^\u0015\u0011\ti,a(\u0002\t1\fgnZ\u0005\u0005\u0003\u0003\fYL\u0001\u0004PE*,7\r\u001e\u0015\u0004%\u0011S\u0005fA\tE\u0015\u0002"
)
public final class QuantileDiscretizer extends Estimator implements QuantileDiscretizerBase, DefaultParamsWritable {
   private final String uid;
   private IntParam numBuckets;
   private IntArrayParam numBucketsArray;
   private Param handleInvalid;
   private DoubleParam relativeError;
   private StringArrayParam outputCols;
   private StringArrayParam inputCols;
   private Param outputCol;
   private Param inputCol;

   public static QuantileDiscretizer load(final String path) {
      return QuantileDiscretizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return QuantileDiscretizer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getNumBuckets() {
      return QuantileDiscretizerBase.getNumBuckets$(this);
   }

   public int[] getNumBucketsArray() {
      return QuantileDiscretizerBase.getNumBucketsArray$(this);
   }

   public final double getRelativeError() {
      return HasRelativeError.getRelativeError$(this);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public IntParam numBuckets() {
      return this.numBuckets;
   }

   public IntArrayParam numBucketsArray() {
      return this.numBucketsArray;
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public void org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$numBuckets_$eq(final IntParam x$1) {
      this.numBuckets = x$1;
   }

   public void org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$numBucketsArray_$eq(final IntArrayParam x$1) {
      this.numBucketsArray = x$1;
   }

   public void org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final DoubleParam relativeError() {
      return this.relativeError;
   }

   public final void org$apache$spark$ml$param$shared$HasRelativeError$_setter_$relativeError_$eq(final DoubleParam x$1) {
      this.relativeError = x$1;
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
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

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public QuantileDiscretizer setRelativeError(final double value) {
      return (QuantileDiscretizer)this.set(this.relativeError(), BoxesRunTime.boxToDouble(value));
   }

   public QuantileDiscretizer setNumBuckets(final int value) {
      return (QuantileDiscretizer)this.set(this.numBuckets(), BoxesRunTime.boxToInteger(value));
   }

   public QuantileDiscretizer setInputCol(final String value) {
      return (QuantileDiscretizer)this.set(this.inputCol(), value);
   }

   public QuantileDiscretizer setOutputCol(final String value) {
      return (QuantileDiscretizer)this.set(this.outputCol(), value);
   }

   public QuantileDiscretizer setHandleInvalid(final String value) {
      return (QuantileDiscretizer)this.set(this.handleInvalid(), value);
   }

   public QuantileDiscretizer setNumBucketsArray(final int[] value) {
      return (QuantileDiscretizer)this.set(this.numBucketsArray(), value);
   }

   public QuantileDiscretizer setInputCols(final String[] value) {
      return (QuantileDiscretizer)this.set(this.inputCols(), value);
   }

   public QuantileDiscretizer setOutputCols(final String[] value) {
      return (QuantileDiscretizer)this.set(this.outputCols(), value);
   }

   public StructType transformSchema(final StructType schema) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new .colon.colon(this.outputCol(), scala.collection.immutable.Nil..MODULE$), new .colon.colon(this.outputCols(), scala.collection.immutable.Nil..MODULE$));
      if (this.isSet(this.inputCol())) {
         scala.Predef..MODULE$.require(!this.isSet(this.numBucketsArray()), () -> "numBucketsArray can't be set for single-column QuantileDiscretizer.");
      }

      if (this.isSet(this.inputCols())) {
         scala.Predef..MODULE$.require(this.getInputCols().length == this.getOutputCols().length, () -> "QuantileDiscretizer " + this + " has mismatched Params for multi-column transform. Params (inputCols, outputCols) should have equal lengths, but they have different lengths: (" + this.getInputCols().length + ", " + this.getOutputCols().length + ").");
         if (this.isSet(this.numBucketsArray())) {
            scala.Predef..MODULE$.require(this.getInputCols().length == this.getNumBucketsArray().length, () -> "QuantileDiscretizer " + this + " has mismatched Params for multi-column transform. Params (inputCols, outputCols, numBucketsArray) should have equal lengths, but they have different lengths: (" + this.getInputCols().length + ", " + this.getOutputCols().length + ", " + this.getNumBucketsArray().length + ").");
            scala.Predef..MODULE$.require(!this.isSet(this.numBuckets()), () -> "exactly one of numBuckets, numBucketsArray Params to be set, but both are set.");
         }
      }

      Tuple2 var4 = this.isSet(this.inputCols()) ? new Tuple2(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.$(this.inputCols())).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.$(this.outputCols())).toImmutableArraySeq()) : new Tuple2(new .colon.colon((String)this.$(this.inputCol()), scala.collection.immutable.Nil..MODULE$), new .colon.colon((String)this.$(this.outputCol()), scala.collection.immutable.Nil..MODULE$));
      if (var4 != null) {
         Seq inputColNames = (Seq)var4._1();
         Seq outputColNames = (Seq)var4._2();
         Tuple2 var3 = new Tuple2(inputColNames, outputColNames);
         Seq inputColNames = (Seq)var3._1();
         Seq outputColNames = (Seq)var3._2();
         ObjectRef outputFields = ObjectRef.create(schema.fields());
         ((IterableOnceOps)inputColNames.zip(outputColNames)).foreach((x0$1) -> {
            $anonfun$transformSchema$5(schema, outputFields, x0$1);
            return BoxedUnit.UNIT;
         });
         return new StructType((StructField[])outputFields.elem);
      } else {
         throw new MatchError(var4);
      }
   }

   public Bucketizer fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Bucketizer bucketizer = (new Bucketizer(this.uid())).setHandleInvalid((String)this.$(this.handleInvalid()));
      if (this.isSet(this.inputCols())) {
         String[] quantileColNames = (String[])scala.Array..MODULE$.tabulate(((String[])this.$(this.inputCols())).length, (index) -> $anonfun$fit$1(BoxesRunTime.unboxToInt(index)), scala.reflect.ClassTag..MODULE$.apply(String.class));
         Dataset quantileDataset = dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(this.$(this.inputCols())))), (x0$1) -> {
            if (x0$1 != null) {
               String colName = (String)x0$1._1();
               int index = x0$1._2$mcI$sp();
               return org.apache.spark.sql.functions..MODULE$.col(colName).alias(quantileColNames[index]);
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Column.class))).toImmutableArraySeq());
         double[][] var10000;
         if (this.isSet(this.numBucketsArray())) {
            double[][] probArrayPerCol = (double[][])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps((int[])this.$(this.numBucketsArray())), (numOfBuckets) -> $anonfun$fit$3(BoxesRunTime.unboxToInt(numOfBuckets)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
            double[] probabilityArray = (double[])scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps((Object[])probArrayPerCol), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), scala.reflect.ClassTag..MODULE$.Double())), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)));
            double[][] splitsArrayRaw = quantileDataset.stat().approxQuantile(quantileColNames, probabilityArray, BoxesRunTime.unboxToDouble(this.$(this.relativeError())));
            var10000 = (double[][])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])splitsArrayRaw), scala.Predef..MODULE$.wrapRefArray((Object[])probArrayPerCol))), (x0$2) -> {
               if (x0$2 != null) {
                  double[] splits = (double[])x0$2._1();
                  double[] probs = (double[])x0$2._2();
                  Set probSet = scala.Predef..MODULE$.wrapDoubleArray(probs).toSet();
                  Set idxSet = scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.collect$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(probabilityArray))), new Serializable(probSet) {
                     private static final long serialVersionUID = 0L;
                     private final Set probSet$1;

                     public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                        if (x1 != null) {
                           double p = x1._1$mcD$sp();
                           int idx = x1._2$mcI$sp();
                           if (this.probSet$1.apply(BoxesRunTime.boxToDouble(p))) {
                              return BoxesRunTime.boxToInteger(idx);
                           }
                        }

                        return default.apply(x1);
                     }

                     public final boolean isDefinedAt(final Tuple2 x1) {
                        if (x1 != null) {
                           double p = x1._1$mcD$sp();
                           if (this.probSet$1.apply(BoxesRunTime.boxToDouble(p))) {
                              return true;
                           }
                        }

                        return false;
                     }

                     public {
                        this.probSet$1 = probSet$1;
                     }
                  }, scala.reflect.ClassTag..MODULE$.Int())).toSet();
                  return (double[])scala.collection.ArrayOps..MODULE$.collect$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(splits))), new Serializable(idxSet) {
                     private static final long serialVersionUID = 0L;
                     private final Set idxSet$1;

                     public final Object applyOrElse(final Tuple2 x2, final Function1 default) {
                        if (x2 != null) {
                           double s = x2._1$mcD$sp();
                           int idx = x2._2$mcI$sp();
                           if (this.idxSet$1.apply(BoxesRunTime.boxToInteger(idx))) {
                              return BoxesRunTime.boxToDouble(s);
                           }
                        }

                        return default.apply(x2);
                     }

                     public final boolean isDefinedAt(final Tuple2 x2) {
                        if (x2 != null) {
                           int idx = x2._2$mcI$sp();
                           if (this.idxSet$1.apply(BoxesRunTime.boxToInteger(idx))) {
                              return true;
                           }
                        }

                        return false;
                     }

                     public {
                        this.idxSet$1 = idxSet$1;
                     }
                  }, scala.reflect.ClassTag..MODULE$.Double());
               } else {
                  throw new MatchError(x0$2);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
         } else {
            var10000 = quantileDataset.stat().approxQuantile(quantileColNames, (double[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), BoxesRunTime.unboxToInt(this.$(this.numBuckets()))).map((JFunction1.mcDI.sp)(x$3) -> (double)x$3 / (double)BoxesRunTime.unboxToInt(this.$(this.numBuckets()))).toArray(scala.reflect.ClassTag..MODULE$.Double()), BoxesRunTime.unboxToDouble(this.$(this.relativeError())));
         }

         double[][] splitsArray = var10000;
         bucketizer.setSplitsArray((double[][])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])splitsArray), (splitsx) -> this.getDistinctSplits(splitsx), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE))));
      } else {
         Dataset quantileDataset = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol())).alias("c_0")})));
         double[] splits = quantileDataset.stat().approxQuantile("c_0", (double[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), BoxesRunTime.unboxToInt(this.$(this.numBuckets()))).map((JFunction1.mcDI.sp)(x$4) -> (double)x$4 / (double)BoxesRunTime.unboxToInt(this.$(this.numBuckets()))).toArray(scala.reflect.ClassTag..MODULE$.Double()), BoxesRunTime.unboxToDouble(this.$(this.relativeError())));
         bucketizer.setSplits(this.getDistinctSplits(splits));
      }

      return (Bucketizer)this.copyValues(bucketizer.setParent(this), this.copyValues$default$2());
   }

   private double[] getDistinctSplits(final double[] splits) {
      splits[0] = Double.NEGATIVE_INFINITY;
      splits[splits.length - 1] = Double.POSITIVE_INFINITY;
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.doubleArrayOps(splits)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         if (splits[i] == (double)-0.0F) {
            splits[i] = (double)0.0F;
         }
      });
      double[] distinctSplits = (double[])scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.doubleArrayOps(splits));
      if (splits.length != distinctSplits.length) {
         this.log().warn("Some quantiles were identical. Bucketing to " + (distinctSplits.length - 1) + " buckets as a result.");
      }

      return (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps(distinctSplits), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
   }

   public QuantileDiscretizer copy(final ParamMap extra) {
      return (QuantileDiscretizer)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final void $anonfun$transformSchema$5(final StructType schema$1, final ObjectRef outputFields$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String inputColName = (String)x0$1._1();
         String outputColName = (String)x0$1._2();
         SchemaUtils$.MODULE$.checkNumericType(schema$1, inputColName, SchemaUtils$.MODULE$.checkNumericType$default$3());
         scala.Predef..MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema$1.fieldNames()), outputColName), () -> "Output column " + outputColName + " already exists.");
         NominalAttribute attr = NominalAttribute$.MODULE$.defaultAttr().withName(outputColName);
         outputFields$1.elem = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])((StructField[])outputFields$1.elem)), attr.toStructField(), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$fit$1(final int index) {
      return "c_" + index;
   }

   // $FF: synthetic method
   public static final double[] $anonfun$fit$3(final int numOfBuckets) {
      return (double[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), numOfBuckets).map((JFunction1.mcDI.sp)(x$2) -> (double)x$2 / (double)numOfBuckets).toArray(scala.reflect.ClassTag..MODULE$.Double());
   }

   public QuantileDiscretizer(final String uid) {
      this.uid = uid;
      HasHandleInvalid.$init$(this);
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCols.$init$(this);
      HasRelativeError.$init$(this);
      QuantileDiscretizerBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public QuantileDiscretizer() {
      this(Identifiable$.MODULE$.randomUID("quantileDiscretizer"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

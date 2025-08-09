package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.attribute.BinaryAttribute$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.param.shared.HasThreshold;
import org.apache.spark.ml.param.shared.HasThresholds;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.ArrayImplicits.;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a\u0001\u0002\r\u001a\u0005\u0011B\u0001B\u0012\u0001\u0003\u0006\u0004%\te\u0012\u0005\t=\u0002\u0011\t\u0011)A\u0005\u0011\")\u0001\r\u0001C\u0001C\")\u0001\r\u0001C\u0001O\"9\u0011\u000e\u0001b\u0001\n\u0003R\u0007B\u00029\u0001A\u0003%1\u000eC\u0003s\u0001\u0011\u00051\u000fC\u0004}\u0001\t\u0007I\u0011I?\t\u000f\u0005%\u0001\u0001)A\u0005}\"9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!!\u000e\u0001\t\u0003\t9\u0004C\u0004\u0002>\u0001!\t%a\u0010\t\u000f\u00055\u0005\u0001\"\u0011\u0002\u0010\"9\u00111\u0015\u0001\u0005B\u0005\u0015\u0006bBA\\\u0001\u0011\u0005\u0013\u0011X\u0004\b\u0003\u007fK\u0002\u0012AAa\r\u0019A\u0012\u0004#\u0001\u0002D\"1\u0001\r\u0006C\u0001\u0003CDq!a9\u0015\t\u0003\n)\u000fC\u0005\u0002rR\t\t\u0011\"\u0003\u0002t\nI!)\u001b8be&TXM\u001d\u0006\u00035m\tqAZ3biV\u0014XM\u0003\u0002\u001d;\u0005\u0011Q\u000e\u001c\u0006\u0003=}\tQa\u001d9be.T!\u0001I\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0013aA8sO\u000e\u00011#\u0003\u0001&SE\"tGO\u001fA!\t1s%D\u0001\u001c\u0013\tA3DA\u0006Ue\u0006t7OZ8s[\u0016\u0014\bC\u0001\u00160\u001b\u0005Y#B\u0001\u0017.\u0003\u0019\u0019\b.\u0019:fI*\u0011afG\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003a-\u0012A\u0002S1t)\"\u0014Xm\u001d5pY\u0012\u0004\"A\u000b\u001a\n\u0005MZ#!\u0004%bgRC'/Z:i_2$7\u000f\u0005\u0002+k%\u0011ag\u000b\u0002\f\u0011\u0006\u001c\u0018J\u001c9vi\u000e{G\u000e\u0005\u0002+q%\u0011\u0011h\u000b\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\u001c\t\u0003UmJ!\u0001P\u0016\u0003\u0019!\u000b7/\u00138qkR\u001cu\u000e\\:\u0011\u0005)r\u0014BA ,\u00055A\u0015m](viB,HoQ8mgB\u0011\u0011\tR\u0007\u0002\u0005*\u00111iG\u0001\u0005kRLG.\u0003\u0002F\u0005\n)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t\u0001\n\u0005\u0002J%:\u0011!\n\u0015\t\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001b\u000e\na\u0001\u0010:p_Rt$\"A(\u0002\u000bM\u001c\u0017\r\\1\n\u0005Es\u0015A\u0002)sK\u0012,g-\u0003\u0002T)\n11\u000b\u001e:j]\u001eT!!\u0015()\u0007\u00051F\f\u0005\u0002X56\t\u0001L\u0003\u0002Z;\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005mC&!B*j]\u000e,\u0017%A/\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005Yc\u0016A\u0002\u001fj]&$h\b\u0006\u0002cIB\u00111\rA\u0007\u00023!)ai\u0001a\u0001\u0011\"\u001aAM\u0016/)\u0007\r1F\fF\u0001cQ\r!a\u000bX\u0001\ni\"\u0014Xm\u001d5pY\u0012,\u0012a\u001b\t\u0003Y6l\u0011!L\u0005\u0003]6\u00121\u0002R8vE2,\u0007+\u0019:b[\"\u001aQA\u0016/\u0002\u0015QD'/Z:i_2$\u0007\u0005K\u0002\u0007-r\u000bAb]3u)\"\u0014Xm\u001d5pY\u0012$\"\u0001^;\u000e\u0003\u0001AQA^\u0004A\u0002]\fQA^1mk\u0016\u0004\"\u0001_=\u000e\u00039K!A\u001f(\u0003\r\u0011{WO\u00197fQ\r9a\u000bX\u0001\u000bi\"\u0014Xm\u001d5pY\u0012\u001cX#\u0001@\u0011\u00051|\u0018bAA\u0001[\t\u0001Bi\\;cY\u0016\f%O]1z!\u0006\u0014\u0018-\u001c\u0015\u0005\u0011Y\u000b)!\t\u0002\u0002\b\u0005)1G\f\u0019/a\u0005YA\u000f\u001b:fg\"|G\u000eZ:!Q\u0011Ia+!\u0002\u0002\u001bM,G\u000f\u00165sKNDw\u000e\u001c3t)\r!\u0018\u0011\u0003\u0005\u0007m*\u0001\r!a\u0005\u0011\ta\f)b^\u0005\u0004\u0003/q%!B!se\u0006L\b\u0006\u0002\u0006W\u0003\u000b\t1b]3u\u0013:\u0004X\u000f^\"pYR\u0019A/a\b\t\u000bY\\\u0001\u0019\u0001%)\u0007-1F,\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000eF\u0002u\u0003OAQA\u001e\u0007A\u0002!C3\u0001\u0004,]\u00031\u0019X\r^%oaV$8i\u001c7t)\r!\u0018q\u0006\u0005\u0007m6\u0001\r!!\r\u0011\ta\f)\u0002\u0013\u0015\u0005\u001bY\u000b)!A\u0007tKR|U\u000f\u001e9vi\u000e{Gn\u001d\u000b\u0004i\u0006e\u0002B\u0002<\u000f\u0001\u0004\t\t\u0004\u000b\u0003\u000f-\u0006\u0015\u0011!\u0003;sC:\u001chm\u001c:n)\u0011\t\t%a\u0019\u0011\t\u0005\r\u0013Q\f\b\u0005\u0003\u000b\n9F\u0004\u0003\u0002H\u0005Mc\u0002BA%\u0003#rA!a\u0013\u0002P9\u00191*!\u0014\n\u0003\tJ!\u0001I\u0011\n\u0005yy\u0012bAA+;\u0005\u00191/\u001d7\n\t\u0005e\u00131L\u0001\ba\u0006\u001c7.Y4f\u0015\r\t)&H\u0005\u0005\u0003?\n\tGA\u0005ECR\fgI]1nK*!\u0011\u0011LA.\u0011\u001d\t)g\u0004a\u0001\u0003O\nq\u0001Z1uCN,G\u000f\r\u0003\u0002j\u0005U\u0004CBA6\u0003[\n\t(\u0004\u0002\u0002\\%!\u0011qNA.\u0005\u001d!\u0015\r^1tKR\u0004B!a\u001d\u0002v1\u0001A\u0001DA<\u0003G\n\t\u0011!A\u0003\u0002\u0005e$aA0%cE!\u00111PAA!\rA\u0018QP\u0005\u0004\u0003\u007fr%a\u0002(pi\"Lgn\u001a\t\u0004q\u0006\r\u0015bAAC\u001d\n\u0019\u0011I\\=)\t=1\u0016\u0011R\u0011\u0003\u0003\u0017\u000bQA\r\u00181]A\nq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003#\u000bi\n\u0005\u0003\u0002\u0014\u0006eUBAAK\u0015\u0011\t9*a\u0017\u0002\u000bQL\b/Z:\n\t\u0005m\u0015Q\u0013\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBAP!\u0001\u0007\u0011\u0011S\u0001\u0007g\u000eDW-\\1)\u0007A1F,\u0001\u0003d_BLHc\u00012\u0002(\"9\u0011\u0011V\tA\u0002\u0005-\u0016!B3yiJ\f\u0007c\u00017\u0002.&\u0019\u0011qV\u0017\u0003\u0011A\u000b'/Y7NCBDC!\u0005,\u00024\u0006\u0012\u0011QW\u0001\u0006c9\"d&M\u0001\ti>\u001cFO]5oOR\t\u0001\n\u000b\u0003\u0013-\u0006\u0015\u0001f\u0001\u0001W9\u0006I!)\u001b8be&TXM\u001d\t\u0003GR\u0019r\u0001FAc\u0003\u0017\f\t\u000eE\u0002y\u0003\u000fL1!!3O\u0005\u0019\te.\u001f*fMB!\u0011)!4c\u0013\r\tyM\u0011\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\t\u0019.!8\u000e\u0005\u0005U'\u0002BAl\u00033\f!![8\u000b\u0005\u0005m\u0017\u0001\u00026bm\u0006LA!a8\u0002V\na1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011Y\u0001\u0005Y>\fG\rF\u0002c\u0003ODa!!;\u0017\u0001\u0004A\u0015\u0001\u00029bi\"DCA\u0006,\u0002n\u0006\u0012\u0011q^\u0001\u0006c92d\u0006M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u0004B!a>\u0002~6\u0011\u0011\u0011 \u0006\u0005\u0003w\fI.\u0001\u0003mC:<\u0017\u0002BA\u0000\u0003s\u0014aa\u00142kK\u000e$\b\u0006\u0002\u000bW\u0003[DCa\u0005,\u0002n\u0002"
)
public final class Binarizer extends Transformer implements HasThreshold, HasThresholds, HasInputCol, HasOutputCol, HasInputCols, HasOutputCols, DefaultParamsWritable {
   private final String uid;
   private final DoubleParam threshold;
   private final DoubleArrayParam thresholds;
   private StringArrayParam outputCols;
   private StringArrayParam inputCols;
   private Param outputCol;
   private Param inputCol;

   public static Binarizer load(final String path) {
      return Binarizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Binarizer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
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

   public double[] getThresholds() {
      return HasThresholds.getThresholds$(this);
   }

   public double getThreshold() {
      return HasThreshold.getThreshold$(this);
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

   public void org$apache$spark$ml$param$shared$HasThresholds$_setter_$thresholds_$eq(final DoubleArrayParam x$1) {
   }

   public void org$apache$spark$ml$param$shared$HasThreshold$_setter_$threshold_$eq(final DoubleParam x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public DoubleParam threshold() {
      return this.threshold;
   }

   public Binarizer setThreshold(final double value) {
      return (Binarizer)this.set(this.threshold(), BoxesRunTime.boxToDouble(value));
   }

   public DoubleArrayParam thresholds() {
      return this.thresholds;
   }

   public Binarizer setThresholds(final double[] value) {
      return (Binarizer)this.set(this.thresholds(), value);
   }

   public Binarizer setInputCol(final String value) {
      return (Binarizer)this.set(this.inputCol(), value);
   }

   public Binarizer setOutputCol(final String value) {
      return (Binarizer)this.set(this.outputCol(), value);
   }

   public Binarizer setInputCols(final String[] value) {
      return (Binarizer)this.set(this.inputCols(), value);
   }

   public Binarizer setOutputCols(final String[] value) {
      return (Binarizer)this.set(this.outputCols(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Tuple3 var5 = this.isSet(this.inputCols()) ? (this.isSet(this.thresholds()) ? new Tuple3(.MODULE$.SparkArrayOps(this.$(this.inputCols())).toImmutableArraySeq(), .MODULE$.SparkArrayOps(this.$(this.outputCols())).toImmutableArraySeq(), .MODULE$.SparkArrayOps(this.$(this.thresholds())).toImmutableArraySeq()) : new Tuple3(.MODULE$.SparkArrayOps(this.$(this.inputCols())).toImmutableArraySeq(), .MODULE$.SparkArrayOps(this.$(this.outputCols())).toImmutableArraySeq(), scala.package..MODULE$.Seq().fill(((String[])this.$(this.inputCols())).length, (JFunction0.mcD.sp)() -> BoxesRunTime.unboxToDouble(this.$(this.threshold()))))) : new Tuple3(new scala.collection.immutable..colon.colon((String)this.$(this.inputCol()), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon((String)this.$(this.outputCol()), scala.collection.immutable.Nil..MODULE$), scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{BoxesRunTime.unboxToDouble(this.$(this.threshold()))})));
      if (var5 != null) {
         Seq inputColNames = (Seq)var5._1();
         Seq outputColNames = (Seq)var5._2();
         Seq tds = (Seq)var5._3();
         Tuple3 var4 = new Tuple3(inputColNames, outputColNames, tds);
         Seq inputColNames = (Seq)var4._1();
         Seq outputColNames = (Seq)var4._2();
         Seq tds = (Seq)var4._3();
         Seq mappedOutputCols = (Seq)((IterableOps)inputColNames.zip(tds)).map((x0$1) -> {
            if (x0$1 != null) {
               String colName = (String)x0$1._1();
               double td = x0$1._2$mcD$sp();
               boolean var9 = false;
               Object var10 = null;
               DataType var11 = SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), colName).dataType();
               if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(var11)) {
                  return org.apache.spark.sql.functions..MODULE$.when(org.apache.spark.sql.functions..MODULE$.col(colName).isNaN().unary_$bang().$amp$amp(org.apache.spark.sql.functions..MODULE$.col(colName).$greater(BoxesRunTime.boxToDouble(td))), org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F))).otherwise(org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)0.0F)));
               } else {
                  if (var11 instanceof VectorUDT) {
                     var9 = true;
                     VectorUDT var20 = (VectorUDT)var11;
                     if (td >= (double)0) {
                        functions var21 = org.apache.spark.sql.functions..MODULE$;
                        Function1 var22 = (vector) -> {
                           ArrayBuilder indices = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
                           ArrayBuilder values = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
                           vector.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> {
                              if (value > td) {
                                 indices.$plus$eq(BoxesRunTime.boxToInteger(index));
                                 values.$plus$eq(BoxesRunTime.boxToDouble((double)1.0F));
                              }
                           });
                           int[] idxArray = (int[])indices.result();
                           double[] valArray = (double[])values.result();
                           return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(vector.size(), idxArray, valArray).compressedWithNNZ(idxArray.length);
                        };
                        JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                        JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Binarizer.class.getClassLoader());

                        final class $typecreator1$1 extends TypeCreator {
                           public Types.TypeApi apply(final Mirror $m$untyped) {
                              Universe $u = $m$untyped.universe();
                              return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
                           }

                           public $typecreator1$1() {
                           }
                        }

                        TypeTags.TypeTag var23 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
                        JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                        JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Binarizer.class.getClassLoader());

                        final class $typecreator2$1 extends TypeCreator {
                           public Types.TypeApi apply(final Mirror $m$untyped) {
                              Universe $u = $m$untyped.universe();
                              return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
                           }

                           public $typecreator2$1() {
                           }
                        }

                        return var21.udf(var22, var23, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(colName)})));
                     }
                  }

                  if (var9 && td < (double)0) {
                     this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Binarization operations on sparse dataset with negative threshold "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " will build a dense output, so take care when "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THRESHOLD..MODULE$, BoxesRunTime.boxToDouble(td))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"applying to sparse input."})))).log(scala.collection.immutable.Nil..MODULE$))));
                     functions var10000 = org.apache.spark.sql.functions..MODULE$;
                     Function1 var10001 = (vector) -> {
                        double[] values = (double[])scala.Array..MODULE$.fill(vector.size(), (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double());
                        IntRef nnz = IntRef.create(vector.size());
                        vector.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> {
                           if (value <= td) {
                              values[index] = (double)0.0F;
                              --nnz.elem;
                           }
                        });
                        return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(values).compressedWithNNZ(nnz.elem);
                     };
                     JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                     JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Binarizer.class.getClassLoader());

                     final class $typecreator3$1 extends TypeCreator {
                        public Types.TypeApi apply(final Mirror $m$untyped) {
                           Universe $u = $m$untyped.universe();
                           return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
                        }

                        public $typecreator3$1() {
                        }
                     }

                     TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1());
                     JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                     JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Binarizer.class.getClassLoader());

                     final class $typecreator4$1 extends TypeCreator {
                        public Types.TypeApi apply(final Mirror $m$untyped) {
                           Universe $u = $m$untyped.universe();
                           return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
                        }

                        public $typecreator4$1() {
                        }
                     }

                     return var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(colName)})));
                  } else {
                     throw new MatchError(var11);
                  }
               }
            } else {
               throw new MatchError(x0$1);
            }
         });
         Seq outputMetadata = (Seq)outputColNames.map((x$2) -> outputSchema.apply(x$2).metadata());
         return dataset.withColumns(outputColNames, mappedOutputCols, outputMetadata);
      } else {
         throw new MatchError(var5);
      }
   }

   public StructType transformSchema(final StructType schema) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new scala.collection.immutable..colon.colon(this.outputCol(), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon(this.outputCols(), scala.collection.immutable.Nil..MODULE$));
      if (this.isSet(this.inputCol())) {
         scala.Predef..MODULE$.require(!this.isSet(this.thresholds()), () -> "thresholds can't be set for single-column Binarizer.");
      }

      if (this.isSet(this.inputCols())) {
         scala.Predef..MODULE$.require(this.getInputCols().length == this.getOutputCols().length, () -> "Binarizer " + this + " has mismatched Params for multi-column transform. Params (inputCols, outputCols) should have equal lengths, but they have different lengths: (" + this.getInputCols().length + ", " + this.getOutputCols().length + ").");
         if (this.isSet(this.thresholds())) {
            scala.Predef..MODULE$.require(this.getInputCols().length == this.getThresholds().length, () -> "Binarizer " + this + " has mismatched Params for multi-column transform. Params (inputCols, outputCols, thresholds) should have equal lengths, but they have different lengths: (" + this.getInputCols().length + ", " + this.getOutputCols().length + ", " + this.getThresholds().length + ").");
            scala.Predef..MODULE$.require(!this.isSet(this.threshold()), () -> "exactly one of threshold, thresholds Params to be set, but both are set.");
         }
      }

      Tuple2 var4 = this.isSet(this.inputCols()) ? new Tuple2(.MODULE$.SparkArrayOps(this.$(this.inputCols())).toImmutableArraySeq(), .MODULE$.SparkArrayOps(this.$(this.outputCols())).toImmutableArraySeq()) : new Tuple2(new scala.collection.immutable..colon.colon((String)this.$(this.inputCol()), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon((String)this.$(this.outputCol()), scala.collection.immutable.Nil..MODULE$));
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

   public Binarizer copy(final ParamMap extra) {
      return (Binarizer)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "Binarizer: uid=" + var10000 + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "") + this.get(this.outputCols()).map((c) -> ", numOutputCols=" + c.length).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final void $anonfun$transformSchema$5(final StructType schema$1, final ObjectRef outputFields$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String inputColName = (String)x0$1._1();
         String outputColName = (String)x0$1._2();
         scala.Predef..MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema$1.fieldNames()), outputColName), () -> "Output column " + outputColName + " already exists.");

         DataType var19;
         try {
            var19 = SchemaUtils$.MODULE$.getSchemaFieldType(schema$1, inputColName);
         } catch (Throwable var18) {
            if (var18 instanceof SparkIllegalArgumentException) {
               SparkIllegalArgumentException var12 = (SparkIllegalArgumentException)var18;
               String var10000 = var12.getCondition();
               String var13 = "FIELD_NOT_FOUND";
               if (var10000 == null) {
                  if (var13 == null) {
                     throw new SparkException("Input column " + inputColName + " does not exist.");
                  }
               } else if (var10000.equals(var13)) {
                  throw new SparkException("Input column " + inputColName + " does not exist.");
               }
            }

            if (var18 instanceof Exception) {
               Exception var14 = (Exception)var18;
               throw var14;
            }

            throw var18;
         }

         DataType inputType = var19;
         StructField var20;
         if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(inputType)) {
            var20 = BinaryAttribute$.MODULE$.defaultAttr().withName(outputColName).toStructField();
         } else {
            if (!(inputType instanceof VectorUDT)) {
               throw new IllegalArgumentException("Data type " + inputType + " is not supported.");
            }

            int size = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(schema$1, inputColName)).size();
            var20 = size < 0 ? new StructField(outputColName, new VectorUDT(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()) : (new AttributeGroup(outputColName, size)).toStructField();
         }

         StructField outputField = var20;
         outputFields$1.elem = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])((StructField[])outputFields$1.elem)), outputField, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         BoxedUnit var21 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public Binarizer(final String uid) {
      this.uid = uid;
      HasThreshold.$init$(this);
      HasThresholds.$init$(this);
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCols.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.threshold = new DoubleParam(this, "threshold", "threshold used to binarize continuous features");
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.threshold().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
      this.thresholds = new DoubleArrayParam(this, "thresholds", "Array of threshold used to binarize continuous features. This is for multiple columns input. If transforming multiple columns and thresholds is not set, but threshold is set, then threshold will be applied across all columns.");
      Statics.releaseFence();
   }

   public Binarizer() {
      this(Identifiable$.MODULE$.randomUID("binarizer"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

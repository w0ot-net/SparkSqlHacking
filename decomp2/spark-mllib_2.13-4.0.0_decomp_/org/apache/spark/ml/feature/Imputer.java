package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
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
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.Array.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ef\u0001\u0002\u000f\u001e\u0001!B\u0001B\u000f\u0001\u0003\u0006\u0004%\te\u000f\u0005\t%\u0002\u0011\t\u0011)A\u0005y!)A\u000b\u0001C\u0001+\")A\u000b\u0001C\u00015\")A\f\u0001C\u0001;\")A\r\u0001C\u0001K\")\u0001\u000e\u0001C\u0001S\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0001k\")\u0001\u0010\u0001C\u0001s\"1q\u0010\u0001C\u0001\u0003\u0003Aq!a\u0002\u0001\t\u0003\nI\u0001C\u0004\u00024\u0001!\t%!\u000e\t\u000f\u0005\u001d\u0003\u0001\"\u0011\u0002J\u001d9\u0011QL\u000f\t\u0002\u0005}cA\u0002\u000f\u001e\u0011\u0003\t\t\u0007\u0003\u0004U!\u0011\u0005\u0011q\u0010\u0005\u000b\u0003\u0003\u0003\"\u0019!C\u0001;\u0005\r\u0005\u0002CAH!\u0001\u0006I!!\"\t\u0015\u0005E\u0005C1A\u0005\u0002u\t\u0019\t\u0003\u0005\u0002\u0014B\u0001\u000b\u0011BAC\u0011)\t)\n\u0005b\u0001\n\u0003i\u00121\u0011\u0005\t\u0003/\u0003\u0002\u0015!\u0003\u0002\u0006\"Q\u0011\u0011\u0014\tC\u0002\u0013\u0005Q$a'\t\u0011\u0005}\u0005\u0003)A\u0005\u0003;Cq!!)\u0011\t\u0003\n\u0019\u000bC\u0005\u0002,B\t\t\u0011\"\u0003\u0002.\n9\u0011*\u001c9vi\u0016\u0014(B\u0001\u0010 \u0003\u001d1W-\u0019;ve\u0016T!\u0001I\u0011\u0002\u00055d'B\u0001\u0012$\u0003\u0015\u0019\b/\u0019:l\u0015\t!S%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002M\u0005\u0019qN]4\u0004\u0001M!\u0001!K\u00195!\rQ3&L\u0007\u0002?%\u0011Af\b\u0002\n\u000bN$\u0018.\\1u_J\u0004\"AL\u0018\u000e\u0003uI!\u0001M\u000f\u0003\u0019%k\u0007/\u001e;fe6{G-\u001a7\u0011\u00059\u0012\u0014BA\u001a\u001e\u00055IU\u000e];uKJ\u0004\u0016M]1ngB\u0011Q\u0007O\u0007\u0002m)\u0011qgH\u0001\u0005kRLG.\u0003\u0002:m\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\tA\b\u0005\u0002>\r:\u0011a\b\u0012\t\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003\u001e\na\u0001\u0010:p_Rt$\"A\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0013\u0015A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!\u0012\")\u0007\u0005Q\u0005\u000b\u0005\u0002L\u001d6\tAJ\u0003\u0002NC\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005=c%!B*j]\u000e,\u0017%A)\u0002\u000bIr#G\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005)\u0003\u0016A\u0002\u001fj]&$h\b\u0006\u0002W/B\u0011a\u0006\u0001\u0005\u0006u\r\u0001\r\u0001\u0010\u0015\u0004/*\u0003\u0006fA\u0002K!R\ta\u000bK\u0002\u0005\u0015B\u000b1b]3u\u0013:\u0004X\u000f^\"pYR\u0011alX\u0007\u0002\u0001!)\u0001-\u0002a\u0001y\u0005)a/\u00197vK\"\u001aQA\u00132\"\u0003\r\fQa\r\u00181]A\nAb]3u\u001fV$\b/\u001e;D_2$\"A\u00184\t\u000b\u00014\u0001\u0019\u0001\u001f)\u0007\u0019Q%-\u0001\u0007tKRLe\u000e];u\u0007>d7\u000f\u0006\u0002_U\")\u0001m\u0002a\u0001WB\u0019A.\u001c\u001f\u000e\u0003\tK!A\u001c\"\u0003\u000b\u0005\u0013(/Y=)\u0007\u001dQ\u0005+A\u0007tKR|U\u000f\u001e9vi\u000e{Gn\u001d\u000b\u0003=JDQ\u0001\u0019\u0005A\u0002-D3\u0001\u0003&Q\u0003-\u0019X\r^*ue\u0006$XmZ=\u0015\u0005y3\b\"\u00021\n\u0001\u0004a\u0004fA\u0005K!\u0006y1/\u001a;NSN\u001c\u0018N\\4WC2,X\r\u0006\u0002_u\")\u0001M\u0003a\u0001wB\u0011A\u000e`\u0005\u0003{\n\u0013a\u0001R8vE2,\u0007f\u0001\u0006K!\u0006\u00012/\u001a;SK2\fG/\u001b<f\u000bJ\u0014xN\u001d\u000b\u0004=\u0006\r\u0001\"\u00021\f\u0001\u0004Y\bfA\u0006KE\u0006\u0019a-\u001b;\u0015\u00075\nY\u0001C\u0004\u0002\u000e1\u0001\r!a\u0004\u0002\u000f\u0011\fG/Y:fiB\"\u0011\u0011CA\u0011!\u0019\t\u0019\"!\u0007\u0002\u001e5\u0011\u0011Q\u0003\u0006\u0004\u0003/\t\u0013aA:rY&!\u00111DA\u000b\u0005\u001d!\u0015\r^1tKR\u0004B!a\b\u0002\"1\u0001A\u0001DA\u0012\u0003\u0017\t\t\u0011!A\u0003\u0002\u0005\u0015\"aA0%cE!\u0011qEA\u0017!\ra\u0017\u0011F\u0005\u0004\u0003W\u0011%a\u0002(pi\"Lgn\u001a\t\u0004Y\u0006=\u0012bAA\u0019\u0005\n\u0019\u0011I\\=\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!a\u000e\u0002DA!\u0011\u0011HA \u001b\t\tYD\u0003\u0003\u0002>\u0005U\u0011!\u0002;za\u0016\u001c\u0018\u0002BA!\u0003w\u0011!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\t)%\u0004a\u0001\u0003o\taa]2iK6\f\u0017\u0001B2paf$2AVA&\u0011\u001d\tiE\u0004a\u0001\u0003\u001f\nQ!\u001a=ue\u0006\u0004B!!\u0015\u0002X5\u0011\u00111\u000b\u0006\u0004\u0003+z\u0012!\u00029be\u0006l\u0017\u0002BA-\u0003'\u0012\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004\u0001)\u0003\u0016aB%naV$XM\u001d\t\u0003]A\u0019r\u0001EA2\u0003S\ny\u0007E\u0002m\u0003KJ1!a\u001aC\u0005\u0019\te.\u001f*fMB!Q'a\u001bW\u0013\r\tiG\u000e\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\t\t(a\u001f\u000e\u0005\u0005M$\u0002BA;\u0003o\n!![8\u000b\u0005\u0005e\u0014\u0001\u00026bm\u0006LA!! \u0002t\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011qL\u0001\u0005[\u0016\fg.\u0006\u0002\u0002\u0006B!\u0011qQAG\u001b\t\tII\u0003\u0003\u0002\f\u0006]\u0014\u0001\u00027b]\u001eL1aRAE\u0003\u0015iW-\u00198!\u0003\u0019iW\rZ5b]\u00069Q.\u001a3jC:\u0004\u0013\u0001B7pI\u0016\fQ!\\8eK\u0002\n1c];qa>\u0014H/\u001a3TiJ\fG/Z4jKN,\"!!(\u0011\t1l\u0017QQ\u0001\u0015gV\u0004\bo\u001c:uK\u0012\u001cFO]1uK\u001eLWm\u001d\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004-\u0006\u0015\u0006BBAT5\u0001\u0007A(\u0001\u0003qCRD\u0007f\u0001\u000eK!\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0016\t\u0005\u0003\u000f\u000b\t,\u0003\u0003\u00024\u0006%%AB(cU\u0016\u001cG\u000fK\u0002\u0011\u0015BC3a\u0004&Q\u0001"
)
public class Imputer extends Estimator implements ImputerParams, DefaultParamsWritable {
   private final String uid;
   private Param strategy;
   private DoubleParam missingValue;
   private DoubleParam relativeError;
   private StringArrayParam outputCols;
   private Param outputCol;
   private StringArrayParam inputCols;
   private Param inputCol;

   public static Imputer load(final String path) {
      return Imputer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Imputer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getStrategy() {
      return ImputerParams.getStrategy$(this);
   }

   public double getMissingValue() {
      return ImputerParams.getMissingValue$(this);
   }

   public Tuple2 getInOutCols() {
      return ImputerParams.getInOutCols$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return ImputerParams.validateAndTransformSchema$(this, schema);
   }

   public final double getRelativeError() {
      return HasRelativeError.getRelativeError$(this);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final Param strategy() {
      return this.strategy;
   }

   public final DoubleParam missingValue() {
      return this.missingValue;
   }

   public final void org$apache$spark$ml$feature$ImputerParams$_setter_$strategy_$eq(final Param x$1) {
      this.strategy = x$1;
   }

   public final void org$apache$spark$ml$feature$ImputerParams$_setter_$missingValue_$eq(final DoubleParam x$1) {
      this.missingValue = x$1;
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

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
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

   public Imputer setInputCol(final String value) {
      return (Imputer)this.set(this.inputCol(), value);
   }

   public Imputer setOutputCol(final String value) {
      return (Imputer)this.set(this.outputCol(), value);
   }

   public Imputer setInputCols(final String[] value) {
      return (Imputer)this.set(this.inputCols(), value);
   }

   public Imputer setOutputCols(final String[] value) {
      return (Imputer)this.set(this.outputCols(), value);
   }

   public Imputer setStrategy(final String value) {
      return (Imputer)this.set(this.strategy(), value);
   }

   public Imputer setMissingValue(final double value) {
      return (Imputer)this.set(this.missingValue(), BoxesRunTime.boxToDouble(value));
   }

   public Imputer setRelativeError(final double value) {
      return (Imputer)this.set(this.relativeError(), BoxesRunTime.boxToDouble(value));
   }

   public ImputerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      SparkSession spark = dataset.sparkSession();
      Tuple2 var6 = this.getInOutCols();
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         String[] inputColumns;
         double[] var32;
         label52: {
            Column[] cols;
            int numCols;
            label58: {
               inputColumns = (String[])var6._1();
               String[] transformedColNames = (String[]).MODULE$.tabulate(inputColumns.length, (index) -> $anonfun$fit$1(BoxesRunTime.unboxToInt(index)), scala.reflect.ClassTag..MODULE$.apply(String.class));
               cols = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColumns), scala.Predef..MODULE$.wrapRefArray((Object[])transformedColNames))), (x0$1) -> {
                  if (x0$1 != null) {
                     String inputCol = (String)x0$1._1();
                     String transformedColName = (String)x0$1._2();
                     return org.apache.spark.sql.functions..MODULE$.when(org.apache.spark.sql.functions..MODULE$.col(inputCol).equalTo(this.$(this.missingValue())), (Object)null).when(org.apache.spark.sql.functions..MODULE$.col(inputCol).isNaN(), (Object)null).otherwise(org.apache.spark.sql.functions..MODULE$.col(inputCol)).cast(org.apache.spark.sql.types.DoubleType..MODULE$).as(transformedColName);
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
               numCols = cols.length;
               String var12 = (String)this.$(this.strategy());
               String var10000 = Imputer$.MODULE$.mean();
               if (var10000 == null) {
                  if (var12 == null) {
                     break label58;
                  }
               } else if (var10000.equals(var12)) {
                  break label58;
               }

               label59: {
                  var10000 = Imputer$.MODULE$.median();
                  if (var10000 == null) {
                     if (var12 == null) {
                        break label59;
                     }
                  } else if (var10000.equals(var12)) {
                     break label59;
                  }

                  var10000 = Imputer$.MODULE$.mode();
                  if (var10000 == null) {
                     if (var12 != null) {
                        throw new MatchError(var12);
                     }
                  } else if (!var10000.equals(var12)) {
                     throw new MatchError(var12);
                  }

                  Predef var31 = scala.Predef..MODULE$;
                  Dataset var10001 = dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
                  Function1 var10002 = (rowx) -> scala.package..MODULE$.Iterator().range(0, numCols).flatMap((i) -> $anonfun$fit$8(rowx, BoxesRunTime.unboxToInt(i)));
                  SQLImplicits var10003 = spark.implicits();
                  JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                  JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Imputer.class.getClassLoader());

                  final class $typecreator5$1 extends TypeCreator {
                     public Types.TypeApi apply(final Mirror $m$untyped) {
                        Universe $u = $m$untyped.universe();
                        return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
                     }

                     public $typecreator5$1() {
                     }
                  }

                  var10001 = var10001.flatMap(var10002, var10003.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"index", "value"}))).groupBy("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value"}))).agg(org.apache.spark.sql.functions..MODULE$.negate(org.apache.spark.sql.functions..MODULE$.count(org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToInteger(0)))).as("negative_count"), scala.collection.immutable.Nil..MODULE$).groupBy("index", scala.collection.immutable.Nil..MODULE$).agg(org.apache.spark.sql.functions..MODULE$.min(org.apache.spark.sql.functions..MODULE$.struct("negative_count", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value"})))).as("mode"), scala.collection.immutable.Nil..MODULE$).select("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mode.value"})));
                  SQLImplicits var34 = spark.implicits();
                  JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                  JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Imputer.class.getClassLoader());

                  final class $typecreator10$1 extends TypeCreator {
                     public Types.TypeApi apply(final Mirror $m$untyped) {
                        Universe $u = $m$untyped.universe();
                        return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
                     }

                     public $typecreator10$1() {
                     }
                  }

                  Map modes = var31.wrapRefArray(var10001.as(var34.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).collect()).toMap(scala..less.colon.less..MODULE$.refl());
                  var32 = (double[]).MODULE$.tabulate(numCols, (JFunction1.mcDI.sp)(i) -> BoxesRunTime.unboxToDouble(modes.getOrElse(BoxesRunTime.boxToInteger(i), (JFunction0.mcD.sp)() -> Double.NaN)), scala.reflect.ClassTag..MODULE$.Double());
                  break label52;
               }

               var32 = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(cols).toImmutableArraySeq()).stat().approxQuantile(transformedColNames, new double[]{(double)0.5F}, BoxesRunTime.unboxToDouble(this.$(this.relativeError())))), (x$2x) -> BoxesRunTime.boxToDouble($anonfun$fit$5(x$2x)), scala.reflect.ClassTag..MODULE$.Double());
               break label52;
            }

            Row row = (Row)dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])cols), (e) -> org.apache.spark.sql.functions..MODULE$.avg(e), scala.reflect.ClassTag..MODULE$.apply(Column.class))).toImmutableArraySeq()).head();
            var32 = (double[]).MODULE$.tabulate(numCols, (JFunction1.mcDI.sp)(i) -> row.isNullAt(i) ? Double.NaN : row.getDouble(i), scala.reflect.ClassTag..MODULE$.Double());
         }

         double[] results = var32;
         String[] emptyCols = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColumns), scala.Predef..MODULE$.wrapDoubleArray(results))), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$fit$11(x$3)))), (x$4) -> (String)x$4._1(), scala.reflect.ClassTag..MODULE$.apply(String.class));
         if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])emptyCols))) {
            String var35 = scala.Predef..MODULE$.wrapRefArray((Object[])emptyCols).mkString(",");
            throw new SparkException("surrogate cannot be computed. All the values in " + var35 + " are Null, Nan or missingValue(" + this.$(this.missingValue()) + ")");
         } else {
            SparkContext qual$1 = spark.sparkContext();
            Seq x$1 = new scala.collection.immutable..colon.colon(org.apache.spark.sql.Row..MODULE$.fromSeq(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(results).toImmutableArraySeq()), scala.collection.immutable.Nil..MODULE$);
            int x$2 = qual$1.parallelize$default$2();
            RDD rows = qual$1.parallelize(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Row.class));
            StructType schema = new StructType((StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColumns), (col) -> new StructField(col, org.apache.spark.sql.types.DoubleType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
            Dataset surrogateDF = spark.createDataFrame(rows, schema);
            return (ImputerModel)this.copyValues((new ImputerModel(this.uid(), surrogateDF)).setParent(this), this.copyValues$default$2());
         }
      }
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public Imputer copy(final ParamMap extra) {
      return (Imputer)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final String $anonfun$fit$1(final int index) {
      return "c_" + index;
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$5(final double[] x$2) {
      return BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.doubleArrayOps(x$2)).getOrElse((JFunction0.mcD.sp)() -> Double.NaN));
   }

   // $FF: synthetic method
   public static final Option $anonfun$fit$8(final Row row$2, final int i) {
      return (Option)(row$2.isNullAt(i) ? scala.None..MODULE$ : new Some(new Tuple2.mcID.sp(i, row$2.getDouble(i))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$11(final Tuple2 x$3) {
      return Double.isNaN(x$3._2$mcD$sp());
   }

   public Imputer(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasOutputCols.$init$(this);
      HasRelativeError.$init$(this);
      ImputerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public Imputer() {
      this(Identifiable$.MODULE$.randomUID("imputer"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

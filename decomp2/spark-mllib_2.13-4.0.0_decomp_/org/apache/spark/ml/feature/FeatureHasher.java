package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasNumFeatures;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.BooleanType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.collection.OpenHashMap;
import org.slf4j.Logger;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%h\u0001B\r\u001b\u0001\u0015B\u0001B\u0010\u0001\u0003\u0006\u0004%\te\u0010\u0005\t-\u0002\u0011\t\u0011)A\u0005\u0001\")\u0001\f\u0001C\u00013\")\u0001\f\u0001C\u0001=\"9\u0001\r\u0001b\u0001\n\u0003\t\u0007BB4\u0001A\u0003%!\rC\u0003j\u0001\u0011\u0005!\u000eC\u0003t\u0001\u0011\u0005A\u000fC\u0003t\u0001\u0011\u00051\u0010C\u0004\u0002\u0004\u0001!\t!!\u0002\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0001bBA\r\u0001\u0011\u0005\u00131\u0004\u0005\b\u0003K\u0002A\u0011IA4\u0011\u001d\t)\b\u0001C!\u0003oBq!a#\u0001\t\u0003\niiB\u0004\u0002\u0018jA\t!!'\u0007\reQ\u0002\u0012AAN\u0011\u0019A&\u0003\"\u0001\u0002:\"9\u00111\u0018\n\u0005B\u0005u\u0006\"CAc%\t\u0007I\u0011BAd\u0011\u001d\tIM\u0005Q\u0001\n9D\u0001\"a3\u0013\t\u0003Q\u0012Q\u001a\u0005\n\u0003+\u0014\u0012\u0011!C\u0005\u0003/\u0014QBR3biV\u0014X\rS1tQ\u0016\u0014(BA\u000e\u001d\u0003\u001d1W-\u0019;ve\u0016T!!\b\u0010\u0002\u00055d'BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M1\u0001A\n\u00163ka\u0002\"a\n\u0015\u000e\u0003qI!!\u000b\u000f\u0003\u0017Q\u0013\u0018M\\:g_JlWM\u001d\t\u0003WAj\u0011\u0001\f\u0006\u0003[9\naa\u001d5be\u0016$'BA\u0018\u001d\u0003\u0015\u0001\u0018M]1n\u0013\t\tDF\u0001\u0007ICNLe\u000e];u\u0007>d7\u000f\u0005\u0002,g%\u0011A\u0007\f\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\u001c\t\u0003WYJ!a\u000e\u0017\u0003\u001d!\u000b7OT;n\r\u0016\fG/\u001e:fgB\u0011\u0011\bP\u0007\u0002u)\u00111\bH\u0001\u0005kRLG.\u0003\u0002>u\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t\u0001\t\u0005\u0002B\u0015:\u0011!\t\u0013\t\u0003\u0007\u001ak\u0011\u0001\u0012\u0006\u0003\u000b\u0012\na\u0001\u0010:p_Rt$\"A$\u0002\u000bM\u001c\u0017\r\\1\n\u0005%3\u0015A\u0002)sK\u0012,g-\u0003\u0002L\u0019\n11\u000b\u001e:j]\u001eT!!\u0013$)\u0007\u0005qE\u000b\u0005\u0002P%6\t\u0001K\u0003\u0002R=\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0003&!B*j]\u000e,\u0017%A+\u0002\u000bIr3G\f\u0019\u0002\tULG\r\t\u0015\u0004\u00059#\u0016A\u0002\u001fj]&$h\b\u0006\u0002[9B\u00111\fA\u0007\u00025!)ah\u0001a\u0001\u0001\"\u001aAL\u0014+\u0015\u0003iC3\u0001\u0002(U\u0003=\u0019\u0017\r^3h_JL7-\u00197D_2\u001cX#\u00012\u0011\u0005\r$W\"\u0001\u0018\n\u0005\u0015t#\u0001E*ue&tw-\u0011:sCf\u0004\u0016M]1nQ\r)a\nV\u0001\u0011G\u0006$XmZ8sS\u000e\fGnQ8mg\u0002B3A\u0002(U\u00039\u0019X\r\u001e(v[\u001a+\u0017\r^;sKN$\"a\u001b7\u000e\u0003\u0001AQ!\\\u0004A\u00029\fQA^1mk\u0016\u0004\"a\u001c9\u000e\u0003\u0019K!!\u001d$\u0003\u0007%sG\u000fK\u0002\b\u001dR\u000bAb]3u\u0013:\u0004X\u000f^\"pYN$\"a[;\t\u000bYD\u0001\u0019A<\u0002\rY\fG.^3t!\ry\u0007\u0010Q\u0005\u0003s\u001a\u0013!\u0002\u0010:fa\u0016\fG/\u001a3?Q\rAa\n\u0016\u000b\u0003WrDQ!\\\u0005A\u0002u\u00042a\u001c@A\u0013\tyhIA\u0003BeJ\f\u0017\u0010K\u0002\n\u001dR\u000bAb]3u\u001fV$\b/\u001e;D_2$2a[A\u0004\u0011\u0015i'\u00021\u0001AQ\rQa\nV\u0001\u0013O\u0016$8)\u0019;fO>\u0014\u0018nY1m\u0007>d7/F\u0001~Q\rYa\nV\u0001\u0013g\u0016$8)\u0019;fO>\u0014\u0018nY1m\u0007>d7\u000fF\u0002l\u0003+AQ!\u001c\u0007A\u0002uD3\u0001\u0004(U\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0003\u0002\u001e\u0005}\u0002\u0003BA\u0010\u0003sqA!!\t\u000249!\u00111EA\u0018\u001d\u0011\t)#!\f\u000f\t\u0005\u001d\u00121\u0006\b\u0004\u0007\u0006%\u0012\"A\u0012\n\u0005\u0005\u0012\u0013BA\u0010!\u0013\r\t\tDH\u0001\u0004gFd\u0017\u0002BA\u001b\u0003o\tq\u0001]1dW\u0006<WMC\u0002\u00022yIA!a\u000f\u0002>\tIA)\u0019;b\rJ\fW.\u001a\u0006\u0005\u0003k\t9\u0004C\u0004\u0002B5\u0001\r!a\u0011\u0002\u000f\u0011\fG/Y:fiB\"\u0011QIA)!\u0019\t9%!\u0013\u0002N5\u0011\u0011qG\u0005\u0005\u0003\u0017\n9DA\u0004ECR\f7/\u001a;\u0011\t\u0005=\u0013\u0011\u000b\u0007\u0001\t1\t\u0019&a\u0010\u0002\u0002\u0003\u0005)\u0011AA+\u0005\ryF%M\t\u0005\u0003/\ni\u0006E\u0002p\u00033J1!a\u0017G\u0005\u001dqu\u000e\u001e5j]\u001e\u00042a\\A0\u0013\r\t\tG\u0012\u0002\u0004\u0003:L\bfA\u0007O)\u0006!1m\u001c9z)\rQ\u0016\u0011\u000e\u0005\b\u0003Wr\u0001\u0019AA7\u0003\u0015)\u0007\u0010\u001e:b!\r\u0019\u0017qN\u0005\u0004\u0003cr#\u0001\u0003)be\u0006lW*\u00199)\u00079qE+A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\tI(!\"\u0011\t\u0005m\u0014\u0011Q\u0007\u0003\u0003{RA!a \u00028\u0005)A/\u001f9fg&!\u00111QA?\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003\u000f{\u0001\u0019AA=\u0003\u0019\u00198\r[3nC\"\u001aqB\u0014+\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\u0011\u0015\u0005!9\u000b\t*\t\u0002\u0002\u0014\u0006)1G\f\u0019/a!\u001a\u0001A\u0014+\u0002\u001b\u0019+\u0017\r^;sK\"\u000b7\u000f[3s!\tY&cE\u0004\u0013\u0003;\u000b\u0019+!+\u0011\u0007=\fy*C\u0002\u0002\"\u001a\u0013a!\u00118z%\u00164\u0007\u0003B\u001d\u0002&jK1!a*;\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!a+\u000266\u0011\u0011Q\u0016\u0006\u0005\u0003_\u000b\t,\u0001\u0002j_*\u0011\u00111W\u0001\u0005U\u00064\u0018-\u0003\u0003\u00028\u00065&\u0001D*fe&\fG.\u001b>bE2,GCAAM\u0003\u0011aw.\u00193\u0015\u0007i\u000by\f\u0003\u0004\u0002BR\u0001\r\u0001Q\u0001\u0005a\u0006$\b\u000eK\u0002\u0015\u001dR\u000bAa]3fIV\ta.A\u0003tK\u0016$\u0007%A\u0006nkJlWO]\u001aICNDGc\u00018\u0002P\"9\u0011\u0011[\fA\u0002\u0005u\u0013\u0001\u0002;fe6D3a\u0006(U\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u000e\u0005\u0003\u0002\\\u0006\u0005XBAAo\u0015\u0011\ty.!-\u0002\t1\fgnZ\u0005\u0005\u0003G\fiN\u0001\u0004PE*,7\r\u001e\u0015\u0004%9#\u0006fA\tO)\u0002"
)
public class FeatureHasher extends Transformer implements HasInputCols, HasOutputCol, HasNumFeatures, DefaultParamsWritable {
   private final String uid;
   private final StringArrayParam categoricalCols;
   private IntParam numFeatures;
   private Param outputCol;
   private StringArrayParam inputCols;

   public static FeatureHasher load(final String path) {
      return FeatureHasher$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FeatureHasher$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final int getNumFeatures() {
      return HasNumFeatures.getNumFeatures$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final IntParam numFeatures() {
      return this.numFeatures;
   }

   public final void org$apache$spark$ml$param$shared$HasNumFeatures$_setter_$numFeatures_$eq(final IntParam x$1) {
      this.numFeatures = x$1;
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

   public String uid() {
      return this.uid;
   }

   public StringArrayParam categoricalCols() {
      return this.categoricalCols;
   }

   public FeatureHasher setNumFeatures(final int value) {
      return (FeatureHasher)this.set(this.numFeatures(), BoxesRunTime.boxToInteger(value));
   }

   public FeatureHasher setInputCols(final Seq values) {
      return this.setInputCols((String[])values.toArray(.MODULE$.apply(String.class)));
   }

   public FeatureHasher setInputCols(final String[] value) {
      return (FeatureHasher)this.set(this.inputCols(), value);
   }

   public FeatureHasher setOutputCol(final String value) {
      return (FeatureHasher)this.set(this.outputCol(), value);
   }

   public String[] getCategoricalCols() {
      return (String[])this.$(this.categoricalCols());
   }

   public FeatureHasher setCategoricalCols(final String[] value) {
      return (FeatureHasher)this.set(this.categoricalCols(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema());
      Function1 hashFunc = (term) -> BoxesRunTime.boxToInteger($anonfun$transform$1(term));
      int n = BoxesRunTime.unboxToInt(this.$(this.numFeatures()));
      String[] localInputCols = (String[])this.$(this.inputCols());
      ObjectRef catCols = ObjectRef.create((String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])localInputCols), (localInputCol) -> SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), localInputCol), .MODULE$.apply(StructField.class))), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$transform$3(x$1)))), (x$2) -> x$2.name(), .MODULE$.apply(String.class)));
      if (this.isSet(this.categoricalCols())) {
         catCols.elem = (String[])scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String[])catCols.elem)), scala.collection.ArrayOps..MODULE$.intersect$extension(scala.Predef..MODULE$.refArrayOps(this.$(this.categoricalCols())), scala.Predef..MODULE$.wrapRefArray((Object[])localInputCols)), .MODULE$.apply(String.class))));
      }

      int[] catIndices = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String[])catCols.elem)), (c) -> BoxesRunTime.boxToInteger($anonfun$transform$5(localInputCols, c)), .MODULE$.Int());
      String[] realCols = (String[])scala.Predef..MODULE$.wrapRefArray((Object[])localInputCols).toSet().$minus$minus(scala.Predef..MODULE$.wrapRefArray((Object[])((String[])catCols.elem))).toArray(.MODULE$.apply(String.class));
      int[] realIndices = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])realCols), (c) -> BoxesRunTime.boxToInteger($anonfun$transform$6(localInputCols, c)), .MODULE$.Int());
      int[] realOutputIndices = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])realCols), (c) -> BoxesRunTime.boxToInteger($anonfun$transform$7(hashFunc, n, c)), .MODULE$.Int());
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (row) -> {
         OpenHashMap map = new OpenHashMap.mcD.sp(.MODULE$.Int(), .MODULE$.Double());

         for(int i = 0; i < realIndices.length; ++i) {
            int realIdx = realIndices[i];
            if (!row.isNullAt(realIdx)) {
               double value = getDouble$1(row.get(realIdx));
               int idx = realOutputIndices[i];
               BoxesRunTime.boxToDouble(map.changeValue$mcD$sp(BoxesRunTime.boxToInteger(idx), (JFunction0.mcD.sp)() -> value, (JFunction1.mcDD.sp)(v) -> v + value));
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         for(int var17 = 0; var17 < catIndices.length; ++var17) {
            int catIdx = catIndices[var17];
            if (!row.isNullAt(catIdx)) {
               String string = row.get(catIdx).toString();
               String var10001 = ((String[])catCols.elem)[var17];
               int rawIdx = BoxesRunTime.unboxToInt(hashFunc.apply(var10001 + "=" + string));
               int idx = org.apache.spark.util.Utils..MODULE$.nonNegativeMod(rawIdx, n);
               BoxesRunTime.boxToDouble(map.changeValue$mcD$sp(BoxesRunTime.boxToInteger(idx), (JFunction0.mcD.sp)() -> (double)1.0F, (JFunction1.mcDD.sp)(v) -> v + (double)1.0F));
            } else {
               BoxedUnit var18 = BoxedUnit.UNIT;
            }
         }

         return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(n, map.toSeq());
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FeatureHasher.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FeatureHasher.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.sql.Row").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction hashFeatures = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      Metadata metadata = outputSchema.apply((String)this.$(this.outputCol())).metadata();
      return dataset.withColumn((String)this.$(this.outputCol()), hashFeatures.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.struct(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.$(this.inputCols())), (colName) -> org.apache.spark.sql.functions..MODULE$.col(colName), .MODULE$.apply(Column.class))).toImmutableArraySeq())}))), metadata);
   }

   public FeatureHasher copy(final ParamMap extra) {
      return (FeatureHasher)this.defaultCopy(extra);
   }

   public StructType transformSchema(final StructType schema) {
      Set localInputCols = scala.Predef..MODULE$.wrapRefArray(this.$(this.inputCols())).toSet();
      if (this.isSet(this.categoricalCols())) {
         String[] set = (String[])scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps(this.$(this.categoricalCols())), (c) -> BoxesRunTime.boxToBoolean($anonfun$transformSchema$1(localInputCols, c)));
         if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])set))) {
            Logger var10000 = this.log();
            ArraySeq.ofRef var10001 = scala.Predef..MODULE$.wrapRefArray((Object[])set);
            var10000.warn("categoricalCols " + var10001.mkString("[", ",", "]") + " do not exist in inputCols");
         }
      }

      localInputCols.foreach((fieldName) -> {
         $anonfun$transformSchema$2(schema, fieldName);
         return BoxedUnit.UNIT;
      });
      AttributeGroup attrGroup = new AttributeGroup((String)this.$(this.outputCol()), BoxesRunTime.unboxToInt(this.$(this.numFeatures())));
      return SchemaUtils$.MODULE$.appendColumn(schema, attrGroup.toStructField());
   }

   public String toString() {
      String var10000 = this.uid();
      return "FeatureHasher: uid=" + var10000 + ", numFeatures=" + this.$(this.numFeatures()) + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "") + this.get(this.categoricalCols()).map((c) -> ", numCategoricalCols=" + c.length).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$1(final Object term) {
      return FeatureHasher$.MODULE$.murmur3Hash(term);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$3(final StructField x$1) {
      return x$1.dataType() instanceof NumericType;
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$5(final String[] localInputCols$1, final String c) {
      Object qual$1 = scala.Predef..MODULE$.refArrayOps((Object[])localInputCols$1);
      int x$2 = scala.collection.ArrayOps..MODULE$.indexOf$default$2$extension(qual$1);
      return scala.collection.ArrayOps..MODULE$.indexOf$extension(qual$1, c, x$2);
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$6(final String[] localInputCols$1, final String c) {
      Object qual$2 = scala.Predef..MODULE$.refArrayOps((Object[])localInputCols$1);
      int x$4 = scala.collection.ArrayOps..MODULE$.indexOf$default$2$extension(qual$2);
      return scala.collection.ArrayOps..MODULE$.indexOf$extension(qual$2, c, x$4);
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$7(final Function1 hashFunc$1, final int n$1, final String c) {
      return org.apache.spark.util.Utils..MODULE$.nonNegativeMod(BoxesRunTime.unboxToInt(hashFunc$1.apply(c)), n$1);
   }

   private static final double getDouble$1(final Object x) {
      if (x instanceof Number var4) {
         return var4.doubleValue();
      } else {
         return BoxesRunTime.unboxToDouble(x);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transformSchema$1(final Set localInputCols$2, final String c) {
      return localInputCols$2.contains(c);
   }

   // $FF: synthetic method
   public static final void $anonfun$transformSchema$2(final StructType schema$1, final String fieldName) {
      StructField field = SchemaUtils$.MODULE$.getSchemaField(schema$1, fieldName);
      DataType dataType = field.dataType();
      scala.Predef..MODULE$.require(dataType instanceof NumericType || dataType instanceof StringType || dataType instanceof BooleanType, () -> {
         String var10000 = org.apache.spark.sql.types.NumericType..MODULE$.simpleString();
         return "FeatureHasher requires columns to be of " + var10000 + ", " + org.apache.spark.sql.types.BooleanType..MODULE$.catalogString() + " or " + org.apache.spark.sql.types.StringType..MODULE$.catalogString() + ". Column " + fieldName + " was " + dataType.catalogString();
      });
   }

   public FeatureHasher(final String uid) {
      this.uid = uid;
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasNumFeatures.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.categoricalCols = new StringArrayParam(this, "categoricalCols", "numeric columns to treat as categorical");
      Statics.releaseFence();
   }

   public FeatureHasher() {
      this(Identifiable$.MODULE$.randomUID("featureHasher"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

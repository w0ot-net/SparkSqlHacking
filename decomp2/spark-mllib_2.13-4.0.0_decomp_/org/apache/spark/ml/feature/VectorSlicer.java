package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntArrayParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.MetadataUtils$;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001B\u000e\u001d\u0005\u001dB\u0001\"\u0010\u0001\u0003\u0006\u0004%\tE\u0010\u0005\t+\u0002\u0011\t\u0011)A\u0005\u007f!)q\u000b\u0001C\u00011\")q\u000b\u0001C\u0001=\"9\u0001\r\u0001b\u0001\n\u0003\t\u0007BB4\u0001A\u0003%!\rC\u0003j\u0001\u0011\u0005!\u000eC\u0003t\u0001\u0011\u0005A\u000fC\u0004z\u0001\t\u0007I\u0011\u0001>\t\r}\u0004\u0001\u0015!\u0003|\u0011\u001d\t\u0019\u0001\u0001C\u0001\u0003\u000bAq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0014\u0001!\t!!\u0006\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e!9\u00111\u0005\u0001\u0005B\u0005\u0015\u0002bBA:\u0001\u0011%\u0011Q\u000f\u0005\b\u0003\u000f\u0003A\u0011IAE\u0011\u001d\ty\t\u0001C!\u0003#Cq!a(\u0001\t\u0003\n\tkB\u0004\u0002,rA\t!!,\u0007\rma\u0002\u0012AAX\u0011\u00199V\u0003\"\u0001\u0002N\"A\u0011qZ\u000b\u0005\u0002q\t\t\u000e\u0003\u0005\u0002\\V!\t\u0001HAo\u0011\u001d\t\t/\u0006C!\u0003GD\u0011\"a<\u0016\u0003\u0003%I!!=\u0003\u0019Y+7\r^8s'2L7-\u001a:\u000b\u0005uq\u0012a\u00024fCR,(/\u001a\u0006\u0003?\u0001\n!!\u001c7\u000b\u0005\u0005\u0012\u0013!B:qCJ\\'BA\u0012%\u0003\u0019\t\u0007/Y2iK*\tQ%A\u0002pe\u001e\u001c\u0001aE\u0003\u0001Q1\"t\u0007\u0005\u0002*U5\ta$\u0003\u0002,=\tYAK]1og\u001a|'/\\3s!\ti#'D\u0001/\u0015\ty\u0003'\u0001\u0004tQ\u0006\u0014X\r\u001a\u0006\u0003cy\tQ\u0001]1sC6L!a\r\u0018\u0003\u0017!\u000b7/\u00138qkR\u001cu\u000e\u001c\t\u0003[UJ!A\u000e\u0018\u0003\u0019!\u000b7oT;uaV$8i\u001c7\u0011\u0005aZT\"A\u001d\u000b\u0005ir\u0012\u0001B;uS2L!\u0001P\u001d\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003}\u0002\"\u0001Q%\u000f\u0005\u0005;\u0005C\u0001\"F\u001b\u0005\u0019%B\u0001#'\u0003\u0019a$o\\8u})\ta)A\u0003tG\u0006d\u0017-\u0003\u0002I\u000b\u00061\u0001K]3eK\u001aL!AS&\u0003\rM#(/\u001b8h\u0015\tAU\tK\u0002\u0002\u001bN\u0003\"AT)\u000e\u0003=S!\u0001\u0015\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002S\u001f\n)1+\u001b8dK\u0006\nA+A\u00032]Ur\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002N'\u00061A(\u001b8jiz\"\"!W.\u0011\u0005i\u0003Q\"\u0001\u000f\t\u000bu\u001a\u0001\u0019A )\u0007mk5\u000bK\u0002\u0004\u001bN#\u0012!\u0017\u0015\u0004\t5\u001b\u0016aB5oI&\u001cWm]\u000b\u0002EB\u00111\rZ\u0007\u0002a%\u0011Q\r\r\u0002\u000e\u0013:$\u0018I\u001d:bsB\u000b'/Y7)\u0007\u0015i5+\u0001\u0005j]\u0012L7-Z:!Q\r1QjU\u0001\u000bO\u0016$\u0018J\u001c3jG\u0016\u001cX#A6\u0011\u00071lw.D\u0001F\u0013\tqWIA\u0003BeJ\f\u0017\u0010\u0005\u0002ma&\u0011\u0011/\u0012\u0002\u0004\u0013:$\bfA\u0004N'\u0006Q1/\u001a;J]\u0012L7-Z:\u0015\u0005U4X\"\u0001\u0001\t\u000b]D\u0001\u0019A6\u0002\u000bY\fG.^3)\u0007!i5+A\u0003oC6,7/F\u0001|!\t\u0019G0\u0003\u0002~a\t\u00012\u000b\u001e:j]\u001e\f%O]1z!\u0006\u0014\u0018-\u001c\u0015\u0004\u00135\u001b\u0016A\u00028b[\u0016\u001c\b\u0005K\u0002\u000b\u001bN\u000b\u0001bZ3u\u001d\u0006lWm]\u000b\u0003\u0003\u000f\u00012\u0001\\7@Q\rYQjU\u0001\tg\u0016$h*Y7fgR\u0019Q/a\u0004\t\r]d\u0001\u0019AA\u0004Q\raQjU\u0001\fg\u0016$\u0018J\u001c9vi\u000e{G\u000eF\u0002v\u0003/AQa^\u0007A\u0002}B3!D'T\u00031\u0019X\r^(viB,HoQ8m)\r)\u0018q\u0004\u0005\u0006o:\u0001\ra\u0010\u0015\u0004\u001d5\u001b\u0016!\u0003;sC:\u001chm\u001c:n)\u0011\t9#!\u0013\u0011\t\u0005%\u00121\t\b\u0005\u0003W\tiD\u0004\u0003\u0002.\u0005eb\u0002BA\u0018\u0003oqA!!\r\u000269\u0019!)a\r\n\u0003\u0015J!a\t\u0013\n\u0005\u0005\u0012\u0013bAA\u001eA\u0005\u00191/\u001d7\n\t\u0005}\u0012\u0011I\u0001\ba\u0006\u001c7.Y4f\u0015\r\tY\u0004I\u0005\u0005\u0003\u000b\n9EA\u0005ECR\fgI]1nK*!\u0011qHA!\u0011\u001d\tYe\u0004a\u0001\u0003\u001b\nq\u0001Z1uCN,G\u000f\r\u0003\u0002P\u0005m\u0003CBA)\u0003'\n9&\u0004\u0002\u0002B%!\u0011QKA!\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0017\u0002\\1\u0001A\u0001DA/\u0003\u0013\n\t\u0011!A\u0003\u0002\u0005}#aA0%cE!\u0011\u0011MA4!\ra\u00171M\u0005\u0004\u0003K*%a\u0002(pi\"Lgn\u001a\t\u0004Y\u0006%\u0014bAA6\u000b\n\u0019\u0011I\\=)\t=i\u0015qN\u0011\u0003\u0003c\nQA\r\u00181]A\n\u0011dZ3u'\u0016dWm\u0019;fI\u001a+\u0017\r^;sK&sG-[2fgR\u00191.a\u001e\t\u000f\u0005e\u0004\u00031\u0001\u0002|\u000511o\u00195f[\u0006\u0004B!! \u0002\u00046\u0011\u0011q\u0010\u0006\u0005\u0003\u0003\u000b\t%A\u0003usB,7/\u0003\u0003\u0002\u0006\u0006}$AC*ueV\u001cG\u000fV=qK\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002|\u0005-\u0005bBA=#\u0001\u0007\u00111\u0010\u0015\u0004#5\u001b\u0016\u0001B2paf$2!WAJ\u0011\u001d\t)J\u0005a\u0001\u0003/\u000bQ!\u001a=ue\u0006\u00042aYAM\u0013\r\tY\n\r\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001a!#T*\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u0010\u0015\u0005'5\u000b)+\t\u0002\u0002(\u0006)1G\f\u0019/a!\u001a\u0001!T*\u0002\u0019Y+7\r^8s'2L7-\u001a:\u0011\u0005i+2cB\u000b\u00022\u0006]\u0016Q\u0018\t\u0004Y\u0006M\u0016bAA[\u000b\n1\u0011I\\=SK\u001a\u0004B\u0001OA]3&\u0019\u00111X\u001d\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011qXAe\u001b\t\t\tM\u0003\u0003\u0002D\u0006\u0015\u0017AA5p\u0015\t\t9-\u0001\u0003kCZ\f\u0017\u0002BAf\u0003\u0003\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\"!!,\u0002\u0019Y\fG.\u001b3J]\u0012L7-Z:\u0015\t\u0005M\u0017\u0011\u001c\t\u0004Y\u0006U\u0017bAAl\u000b\n9!i\\8mK\u0006t\u0007\"\u00021\u0018\u0001\u0004Y\u0017A\u0003<bY&$g*Y7fgR!\u00111[Ap\u0011\u0019I\b\u00041\u0001\u0002\b\u0005!An\\1e)\rI\u0016Q\u001d\u0005\u0007\u0003OL\u0002\u0019A \u0002\tA\fG\u000f\u001b\u0015\u000535\u000bY/\t\u0002\u0002n\u0006)\u0011G\f\u001c/a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001f\t\u0005\u0003k\fY0\u0004\u0002\u0002x*!\u0011\u0011`Ac\u0003\u0011a\u0017M\\4\n\t\u0005u\u0018q\u001f\u0002\u0007\u001f\nTWm\u0019;)\tUi\u00151\u001e\u0015\u0005)5\u000bY\u000f"
)
public final class VectorSlicer extends Transformer implements HasInputCol, HasOutputCol, DefaultParamsWritable {
   private final String uid;
   private final IntArrayParam indices;
   private final StringArrayParam names;
   private Param outputCol;
   private Param inputCol;

   public static VectorSlicer load(final String path) {
      return VectorSlicer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VectorSlicer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
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

   public IntArrayParam indices() {
      return this.indices;
   }

   public int[] getIndices() {
      return (int[])this.$(this.indices());
   }

   public VectorSlicer setIndices(final int[] value) {
      return (VectorSlicer)this.set(this.indices(), value);
   }

   public StringArrayParam names() {
      return this.names;
   }

   public String[] getNames() {
      return (String[])this.$(this.names());
   }

   public VectorSlicer setNames(final String[] value) {
      return (VectorSlicer)this.set(this.names(), value);
   }

   public VectorSlicer setInputCol(final String value) {
      return (VectorSlicer)this.set(this.inputCol(), value);
   }

   public VectorSlicer setOutputCol(final String value) {
      return (VectorSlicer)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema());
      AttributeGroup inputAttr = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), (String)this.$(this.inputCol())));
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.intArrayOps((int[])this.$(this.indices())))) {
         int size = inputAttr.size();
         if (size >= 0) {
            int maxIndex = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])this.$(this.indices())).max(scala.math.Ordering.Int..MODULE$));
            scala.Predef..MODULE$.require(maxIndex < size, () -> "Selected feature index " + maxIndex + " invalid for only " + size + " input features.");
         }
      }

      int[] selectedIndices = this.getSelectedFeatureIndices(dataset.schema());
      Option selectedAttrs = inputAttr.attributes().map((attrsx) -> (Attribute[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(selectedIndices), (i) -> $anonfun$transform$3(attrsx, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Attribute.class)));
      AttributeGroup var10000;
      if (selectedAttrs instanceof Some var10) {
         Attribute[] attrs = (Attribute[])var10.value();
         var10000 = new AttributeGroup((String)this.$(this.outputCol()), attrs);
      } else {
         if (!scala.None..MODULE$.equals(selectedAttrs)) {
            throw new MatchError(selectedAttrs);
         }

         var10000 = new AttributeGroup((String)this.$(this.outputCol()), selectedIndices.length);
      }

      AttributeGroup outputAttr;
      label32: {
         outputAttr = var10000;
         if (selectedIndices.length > 1) {
            Object qual$1 = scala.Predef..MODULE$.intArrayOps(selectedIndices);
            int x$1 = 2;
            int x$2 = .MODULE$.sliding$default$2$extension(qual$1);
            if (.MODULE$.sliding$extension(qual$1, 2, x$2).forall((t) -> BoxesRunTime.boxToBoolean($anonfun$transform$4(t)))) {
               var21 = true;
               break label32;
            }
         }

         var21 = false;
      }

      boolean sorted = var21;
      functions var22 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (vec) -> {
         if (vec instanceof DenseVector var5) {
            return org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(selectedIndices), (JFunction1.mcDI.sp)(i) -> var5.apply(i), scala.reflect.ClassTag..MODULE$.Double()));
         } else if (vec instanceof SparseVector var6) {
            return var6.slice(selectedIndices, sorted);
         } else {
            throw new MatchError(vec);
         }
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorSlicer.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorSlicer.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction slicer = var22.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), slicer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset.apply((String)this.$(this.inputCol()))}))), outputAttr.toMetadata());
   }

   private int[] getSelectedFeatureIndices(final StructType schema) {
      LazyRef errMsg$lzy = new LazyRef();
      int[] nameFeatures = MetadataUtils$.MODULE$.getFeatureIndicesFromNames(SchemaUtils$.MODULE$.getSchemaField(schema, (String)this.$(this.inputCol())), (String[])this.$(this.names()));
      int[] indFeatures = (int[])this.$(this.indices());
      int numDistinctFeatures = ((int[]).MODULE$.distinct$extension(scala.Predef..MODULE$.intArrayOps((int[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps(nameFeatures), indFeatures, scala.reflect.ClassTag..MODULE$.Int())))).length;
      scala.Predef..MODULE$.require(nameFeatures.length + indFeatures.length == numDistinctFeatures, () -> this.errMsg$1(errMsg$lzy, indFeatures, nameFeatures));
      return (int[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps(indFeatures), nameFeatures, scala.reflect.ClassTag..MODULE$.Int());
   }

   public StructType transformSchema(final StructType schema) {
      scala.Predef..MODULE$.require(((int[])this.$(this.indices())).length > 0 || ((String[])this.$(this.names())).length > 0, () -> "VectorSlicer requires that at least one feature be selected.");
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      if (.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol()))) {
         throw new IllegalArgumentException("Output column " + this.$(this.outputCol()) + " already exists.");
      } else {
         int numFeaturesSelected = ((int[])this.$(this.indices())).length + ((String[])this.$(this.names())).length;
         AttributeGroup outputAttr = new AttributeGroup((String)this.$(this.outputCol()), numFeaturesSelected);
         return SchemaUtils$.MODULE$.appendColumn(schema, outputAttr.toStructField());
      }
   }

   public VectorSlicer copy(final ParamMap extra) {
      return (VectorSlicer)this.defaultCopy(extra);
   }

   public String toString() {
      int numSelectedFeatures = BoxesRunTime.unboxToInt(this.get(this.indices()).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$toString$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 0)) + BoxesRunTime.unboxToInt(this.get(this.names()).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$toString$3(x$2))).getOrElse((JFunction0.mcI.sp)() -> 0));
      if (numSelectedFeatures > 0) {
         String var10000 = this.uid();
         return "VectorSlicer: uid=" + var10000 + ", numSelectedFeatures=" + numSelectedFeatures;
      } else {
         return "VectorSlicer: uid=" + this.uid();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$indices$1(final int[] indices) {
      return VectorSlicer$.MODULE$.validIndices(indices);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$names$1(final String[] names) {
      return VectorSlicer$.MODULE$.validNames(names);
   }

   // $FF: synthetic method
   public static final Attribute $anonfun$transform$3(final Attribute[] attrs$1, final int i) {
      return attrs$1[i];
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$4(final int[] t) {
      return t[1] > t[0];
   }

   // $FF: synthetic method
   private final String errMsg$lzycompute$1(final LazyRef errMsg$lzy$1, final int[] indFeatures$1, final int[] nameFeatures$1) {
      synchronized(errMsg$lzy$1){}

      String var5;
      try {
         String var10000;
         if (errMsg$lzy$1.initialized()) {
            var10000 = (String)errMsg$lzy$1.value();
         } else {
            String var10001 = scala.Predef..MODULE$.wrapIntArray(indFeatures$1).mkString("[", ",", "]");
            var10000 = (String)errMsg$lzy$1.initialize("VectorSlicer requires indices and names to be disjoint sets of features, but they overlap. indices: " + var10001 + ". names: " + scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.intArrayOps(nameFeatures$1), scala.Predef..MODULE$.wrapRefArray(this.$(this.names())))), (x0$1) -> {
               if (x0$1 != null) {
                  int i = x0$1._1$mcI$sp();
                  String n = (String)x0$1._2();
                  return i + ":" + n;
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("[", ",", "]"));
         }

         var5 = var10000;
      } catch (Throwable var7) {
         throw var7;
      }

      return var5;
   }

   private final String errMsg$1(final LazyRef errMsg$lzy$1, final int[] indFeatures$1, final int[] nameFeatures$1) {
      return errMsg$lzy$1.initialized() ? (String)errMsg$lzy$1.value() : this.errMsg$lzycompute$1(errMsg$lzy$1, indFeatures$1, nameFeatures$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$toString$1(final int[] x$1) {
      return x$1.length;
   }

   // $FF: synthetic method
   public static final int $anonfun$toString$3(final String[] x$2) {
      return x$2.length;
   }

   public VectorSlicer(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.indices = new IntArrayParam(this, "indices", "An array of indices to select features from a vector column. There can be no overlap with names.", (indices) -> BoxesRunTime.boxToBoolean($anonfun$indices$1(indices)));
      this.names = new StringArrayParam(this, "names", "An array of feature names to select features from a vector column. There can be no overlap with indices.", (names) -> BoxesRunTime.boxToBoolean($anonfun$names$1(names)));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.indices().$minus$greater(scala.Array..MODULE$.emptyIntArray()), this.names().$minus$greater(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)))}));
      Statics.releaseFence();
   }

   public VectorSlicer() {
      this(Identifiable$.MODULE$.randomUID("vectorSlicer"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

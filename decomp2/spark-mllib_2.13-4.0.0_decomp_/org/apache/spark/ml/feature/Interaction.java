package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.Attribute$;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.attribute.BinaryAttribute;
import org.apache.spark.ml.attribute.BinaryAttribute$;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.attribute.UnresolvedAttribute$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCols;
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
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Predef.;
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
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dh\u0001B\n\u0015\u0001}A\u0001\"\u000e\u0001\u0003\u0006\u0004%\tE\u000e\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005o!)q\n\u0001C\u0001!\")q\n\u0001C\u0001-\")\u0001\f\u0001C\u00013\")!\r\u0001C\u0001G\")q\r\u0001C!Q\")A\u000f\u0001C!k\"9\u0011Q\u0007\u0001\u0005\n\u0005]\u0002bBA.\u0001\u0011%\u0011Q\f\u0005\b\u0003[\u0002A\u0011BA8\u0011\u001d\t9\t\u0001C!\u0003\u0013Cq!!'\u0001\t\u0003\nYjB\u0004\u0002&RA\t!a*\u0007\rM!\u0002\u0012AAU\u0011\u0019yu\u0002\"\u0001\u0002H\"9\u0011\u0011Z\b\u0005B\u0005-\u0007\"CAj\u001f\u0005\u0005I\u0011BAk\u0005-Ie\u000e^3sC\u000e$\u0018n\u001c8\u000b\u0005U1\u0012a\u00024fCR,(/\u001a\u0006\u0003/a\t!!\u001c7\u000b\u0005eQ\u0012!B:qCJ\\'BA\u000e\u001d\u0003\u0019\t\u0007/Y2iK*\tQ$A\u0002pe\u001e\u001c\u0001aE\u0003\u0001A\u0011bs\u0006\u0005\u0002\"E5\ta#\u0003\u0002$-\tYAK]1og\u001a|'/\\3s!\t)#&D\u0001'\u0015\t9\u0003&\u0001\u0004tQ\u0006\u0014X\r\u001a\u0006\u0003SY\tQ\u0001]1sC6L!a\u000b\u0014\u0003\u0019!\u000b7/\u00138qkR\u001cu\u000e\\:\u0011\u0005\u0015j\u0013B\u0001\u0018'\u00051A\u0015m](viB,HoQ8m!\t\u00014'D\u00012\u0015\t\u0011d#\u0001\u0003vi&d\u0017B\u0001\u001b2\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u00059\u0004C\u0001\u001dB\u001d\tIt\b\u0005\u0002;{5\t1H\u0003\u0002==\u00051AH]8pizR\u0011AP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0001v\na\u0001\u0015:fI\u00164\u0017B\u0001\"D\u0005\u0019\u0019FO]5oO*\u0011\u0001)\u0010\u0015\u0004\u0003\u0015[\u0005C\u0001$J\u001b\u00059%B\u0001%\u0019\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0015\u001e\u0013QaU5oG\u0016\f\u0013\u0001T\u0001\u0006c92d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003\u000b.\u000ba\u0001P5oSRtDCA)T!\t\u0011\u0006!D\u0001\u0015\u0011\u0015)4\u00011\u00018Q\r\u0019Vi\u0013\u0015\u0004\u0007\u0015[E#A))\u0007\u0011)5*\u0001\u0007tKRLe\u000e];u\u0007>d7\u000f\u0006\u0002[76\t\u0001\u0001C\u0003]\u000b\u0001\u0007Q,\u0001\u0004wC2,Xm\u001d\t\u0004=~;T\"A\u001f\n\u0005\u0001l$!B!se\u0006L\bfA\u0003F\u0017\u0006a1/\u001a;PkR\u0004X\u000f^\"pYR\u0011!\f\u001a\u0005\u0006K\u001a\u0001\raN\u0001\u0006m\u0006dW/\u001a\u0015\u0004\r\u0015[\u0015a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\u0005%\f\bC\u00016p\u001b\u0005Y'B\u00017n\u0003\u0015!\u0018\u0010]3t\u0015\tq\u0007$A\u0002tc2L!\u0001]6\u0003\u0015M#(/^2u)f\u0004X\rC\u0003s\u000f\u0001\u0007\u0011.\u0001\u0004tG\",W.\u0019\u0015\u0004\u000f\u0015[\u0015!\u0003;sC:\u001chm\u001c:n)\r1\u00181\u0002\t\u0004o\u0006\u0015ab\u0001=\u0002\u00029\u0011\u0011p \b\u0003uzt!a_?\u000f\u0005ib\u0018\"A\u000f\n\u0005ma\u0012BA\r\u001b\u0013\tq\u0007$C\u0002\u0002\u00045\fq\u0001]1dW\u0006<W-\u0003\u0003\u0002\b\u0005%!!\u0003#bi\u00064%/Y7f\u0015\r\t\u0019!\u001c\u0005\b\u0003\u001bA\u0001\u0019AA\b\u0003\u001d!\u0017\r^1tKR\u0004D!!\u0005\u0002\u001eA1\u00111CA\u000b\u00033i\u0011!\\\u0005\u0004\u0003/i'a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u00037\ti\u0002\u0004\u0001\u0005\u0019\u0005}\u00111BA\u0001\u0002\u0003\u0015\t!!\t\u0003\u0007}#\u0013'\u0005\u0003\u0002$\u0005%\u0002c\u00010\u0002&%\u0019\u0011qE\u001f\u0003\u000f9{G\u000f[5oOB\u0019a,a\u000b\n\u0007\u00055RHA\u0002B]fDC\u0001C#\u00022\u0005\u0012\u00111G\u0001\u0006e9\u0002d\u0006M\u0001\u0013O\u0016$h)Z1ukJ,WI\\2pI\u0016\u00148\u000f\u0006\u0003\u0002:\u0005\u0005\u0003\u0003\u00020`\u0003w\u00012AUA\u001f\u0013\r\ty\u0004\u0006\u0002\u000f\r\u0016\fG/\u001e:f\u000b:\u001cw\u000eZ3s\u0011\u001d\t\u0019%\u0003a\u0001\u0003\u000b\n\u0001BZ3biV\u0014Xm\u001d\t\u0007\u0003\u000f\ny%!\u0016\u000f\t\u0005%\u0013Q\n\b\u0004u\u0005-\u0013\"\u0001 \n\u0007\u0005\rQ(\u0003\u0003\u0002R\u0005M#aA*fc*\u0019\u00111A\u001f\u0011\u0007)\f9&C\u0002\u0002Z-\u00141b\u0015;sk\u000e$h)[3mI\u0006yq-\u001a;GK\u0006$XO]3BiR\u00148\u000f\u0006\u0003\u0002`\u0005-\u0004\u0003BA1\u0003Oj!!a\u0019\u000b\u0007\u0005\u0015d#A\u0005biR\u0014\u0018NY;uK&!\u0011\u0011NA2\u00059\tE\u000f\u001e:jEV$Xm\u0012:pkBDq!a\u0011\u000b\u0001\u0004\t)%A\nf]\u000e|G-\u001a3GK\u0006$XO]3BiR\u00148\u000f\u0006\u0004\u0002r\u0005e\u0014Q\u0010\t\u0007\u0003\u000f\ny%a\u001d\u0011\t\u0005\u0005\u0014QO\u0005\u0005\u0003o\n\u0019GA\u0005BiR\u0014\u0018NY;uK\"9\u00111P\u0006A\u0002\u0005E\u0014AC5oaV$\u0018\t\u001e;sg\"9\u0011qP\u0006A\u0002\u0005\u0005\u0015!C4s_V\u0004h*Y7f!\u0011q\u00161Q\u001c\n\u0007\u0005\u0015UH\u0001\u0004PaRLwN\\\u0001\u0005G>\u0004\u0018\u0010F\u0002R\u0003\u0017Cq!!$\r\u0001\u0004\ty)A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002\u0012\u0006MU\"\u0001\u0015\n\u0007\u0005U\u0005F\u0001\u0005QCJ\fW.T1qQ\raQiS\u0001\ti>\u001cFO]5oOR\tq\u0007\u000b\u0003\u000e\u000b\u0006}\u0015EAAQ\u0003\u0015\u0019d\u0006\r\u00181Q\r\u0001QiS\u0001\f\u0013:$XM]1di&|g\u000e\u0005\u0002S\u001fM9q\"a+\u00022\u0006]\u0006c\u00010\u0002.&\u0019\u0011qV\u001f\u0003\r\u0005s\u0017PU3g!\u0011\u0001\u00141W)\n\u0007\u0005U\u0016GA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u0011\t\u0005e\u00161Y\u0007\u0003\u0003wSA!!0\u0002@\u0006\u0011\u0011n\u001c\u0006\u0003\u0003\u0003\fAA[1wC&!\u0011QYA^\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t9+\u0001\u0003m_\u0006$GcA)\u0002N\"1\u0011qZ\tA\u0002]\nA\u0001]1uQ\"\u001a\u0011#R&\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0007\u0003BAm\u0003?l!!a7\u000b\t\u0005u\u0017qX\u0001\u0005Y\u0006tw-\u0003\u0003\u0002b\u0006m'AB(cU\u0016\u001cG\u000fK\u0002\u0010\u000b.C3AD#L\u0001"
)
public class Interaction extends Transformer implements HasInputCols, HasOutputCol, DefaultParamsWritable {
   private final String uid;
   private Param outputCol;
   private StringArrayParam inputCols;

   public static Interaction load(final String path) {
      return Interaction$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Interaction$.MODULE$.read();
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

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
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

   public Interaction setInputCols(final String[] values) {
      return (Interaction)this.set(this.inputCols(), values);
   }

   public Interaction setOutputCol(final String value) {
      return (Interaction)this.set(this.outputCol(), value);
   }

   public StructType transformSchema(final StructType schema) {
      .MODULE$.require(this.get(this.inputCols()).isDefined(), () -> "Input cols must be defined first.");
      .MODULE$.require(this.get(this.outputCol()).isDefined(), () -> "Output col must be defined first.");
      .MODULE$.require(((String[])this.$(this.inputCols())).length > 0, () -> "Input cols must have non-zero length.");
      .MODULE$.require(((String[])scala.collection.ArrayOps..MODULE$.distinct$extension(.MODULE$.refArrayOps(this.$(this.inputCols())))).length == ((String[])this.$(this.inputCols())).length, () -> "Input cols must be distinct.");
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.outputCol()), new VectorUDT(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      StructField[] inputFeatures = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.$(this.inputCols())), (c) -> SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), c), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      FeatureEncoder[] featureEncoders = this.getFeatureEncoders(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(inputFeatures).toImmutableArraySeq());
      AttributeGroup featureAttrs = this.getFeatureAttrs(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(inputFeatures).toImmutableArraySeq());
      Column[] featureCols = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.refArrayOps((Object[])inputFeatures), .MODULE$.wrapRefArray(this.$(this.inputCols())))), (x0$1) -> {
         if (x0$1 != null) {
            StructField f = (StructField)x0$1._1();
            String inputCol = (String)x0$1._2();
            DataType var8 = f.dataType();
            if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(var8)) {
               return dataset.apply(inputCol);
            } else if (var8 instanceof VectorUDT) {
               return dataset.apply(inputCol);
            } else if (var8 instanceof NumericType ? true : org.apache.spark.sql.types.BooleanType..MODULE$.equals(var8)) {
               return dataset.apply(inputCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
            } else {
               throw new MatchError(var8);
            }
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
      return dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col("*"), interactFunc$1(featureEncoders).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.struct(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(featureCols).toImmutableArraySeq())}))).as((String)this.$(this.outputCol()), featureAttrs.toMetadata())})));
   }

   private FeatureEncoder[] getFeatureEncoders(final Seq features) {
      return (FeatureEncoder[])((IterableOnceOps)features.map((f) -> {
         DataType var4 = f.dataType();
         int[] var10000;
         if (var4 instanceof NumericType ? true : org.apache.spark.sql.types.BooleanType..MODULE$.equals(var4)) {
            var10000 = new int[]{getNumFeatures$1(Attribute$.MODULE$.fromStructField(f))};
         } else {
            if (!(var4 instanceof VectorUDT)) {
               throw new MatchError(var4);
            }

            Attribute[] attrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(f).attributes().getOrElse(() -> {
               throw new SparkException("Vector attributes must be defined for interaction.");
            });
            var10000 = (int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(attrs), (attr) -> BoxesRunTime.boxToInteger($anonfun$getFeatureEncoders$4(attr)), scala.reflect.ClassTag..MODULE$.Int());
         }

         int[] numFeatures = var10000;
         return new FeatureEncoder(numFeatures);
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(FeatureEncoder.class));
   }

   private AttributeGroup getFeatureAttrs(final Seq features) {
      ObjectRef featureAttrs = ObjectRef.create(scala.collection.immutable.Nil..MODULE$);
      ((IterableOnceOps)features.reverse()).foreach((f) -> {
         $anonfun$getFeatureAttrs$1(this, featureAttrs, f);
         return BoxedUnit.UNIT;
      });
      return new AttributeGroup((String)this.$(this.outputCol()), (Attribute[])((Seq)featureAttrs.elem).toArray(scala.reflect.ClassTag..MODULE$.apply(Attribute.class)));
   }

   private Seq encodedFeatureAttrs(final Seq inputAttrs, final Option groupName) {
      return (Seq)((IterableOps)inputAttrs.zipWithIndex()).flatMap((x0$1) -> {
         if (x0$1 != null) {
            Attribute nominal = (Attribute)x0$1._1();
            int i = x0$1._2$mcI$sp();
            if (nominal instanceof NominalAttribute) {
               NominalAttribute var6 = (NominalAttribute)nominal;
               if (var6.values().isDefined()) {
                  return .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(var6.values().get()), (v) -> BinaryAttribute$.MODULE$.defaultAttr().withName(format$1(i, var6.name(), new Some(v), groupName)), scala.reflect.ClassTag..MODULE$.apply(BinaryAttribute.class)));
               }

               return .MODULE$.wrapRefArray(scala.Array..MODULE$.tabulate(BoxesRunTime.unboxToInt(var6.getNumValues().get()), (j) -> $anonfun$encodedFeatureAttrs$4(i, var6, groupName, BoxesRunTime.unboxToInt(j)), scala.reflect.ClassTag..MODULE$.apply(BinaryAttribute.class)));
            }
         }

         if (x0$1 != null) {
            Attribute a = (Attribute)x0$1._1();
            int i = x0$1._2$mcI$sp();
            if (a != null) {
               return new scala.collection.immutable..colon.colon(NumericAttribute$.MODULE$.defaultAttr().withName(format$1(i, a.name(), scala.None..MODULE$, groupName)), scala.collection.immutable.Nil..MODULE$);
            }
         }

         throw new MatchError(x0$1);
      });
   }

   public Interaction copy(final ParamMap extra) {
      return (Interaction)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "Interaction: uid=" + var10000 + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "");
   }

   private static final UserDefinedFunction interactFunc$1(final FeatureEncoder[] featureEncoders$1) {
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (row) -> {
         ObjectRef indices = ObjectRef.create(scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int()));
         ObjectRef values = ObjectRef.create(scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double()));
         int size = 1;
         ((ArrayBuilder)indices.elem).$plus$eq(BoxesRunTime.boxToInteger(0));
         ((ArrayBuilder)values.elem).$plus$eq(BoxesRunTime.boxToDouble((double)1.0F));

         for(int featureIndex = row.length() - 1; featureIndex >= 0; --featureIndex) {
            int[] prevIndices = (int[])((ArrayBuilder)indices.elem).result();
            double[] prevValues = (double[])((ArrayBuilder)values.elem).result();
            int prevSize = size;
            FeatureEncoder currentEncoder = featureEncoders$1[featureIndex];
            indices.elem = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
            values.elem = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
            size *= currentEncoder.outputSize();
            currentEncoder.foreachNonzeroOutput(row.apply(featureIndex), (JFunction2.mcVID.sp)(i, a) -> {
               for(int j = 0; j < prevIndices.length; ++j) {
                  ((ArrayBuilder)indices.elem).$plus$eq(BoxesRunTime.boxToInteger(prevIndices[j] + i * prevSize));
                  ((ArrayBuilder)values.elem).$plus$eq(BoxesRunTime.boxToDouble(prevValues[j] * a));
               }

            });
         }

         return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, (int[])((ArrayBuilder)indices.elem).result(), (double[])((ArrayBuilder)values.elem).result()).compressed();
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Interaction.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Interaction.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.sql.Row").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      return var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
   }

   private static final int getNumFeatures$1(final Attribute attr) {
      if (attr instanceof NominalAttribute var3) {
         return scala.math.package..MODULE$.max(1, BoxesRunTime.unboxToInt(var3.getNumValues().getOrElse(() -> {
            throw new SparkException("Nominal features must have attr numValues defined.");
         })));
      } else {
         return 1;
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$getFeatureEncoders$4(final Attribute attr) {
      return getNumFeatures$1(attr);
   }

   // $FF: synthetic method
   public static final void $anonfun$getFeatureAttrs$1(final Interaction $this, final ObjectRef featureAttrs$1, final StructField f) {
      DataType var6 = f.dataType();
      Seq var10000;
      if (var6 instanceof NumericType ? true : org.apache.spark.sql.types.BooleanType..MODULE$.equals(var6)) {
         label41: {
            label40: {
               Attribute attr = Attribute$.MODULE$.decodeStructField(f, true);
               UnresolvedAttribute$ var8 = UnresolvedAttribute$.MODULE$;
               if (attr == null) {
                  if (var8 == null) {
                     break label40;
                  }
               } else if (attr.equals(var8)) {
                  break label40;
               }

               var10000 = attr.name().isEmpty() ? $this.encodedFeatureAttrs(new scala.collection.immutable..colon.colon(attr.withName(f.name()), scala.collection.immutable.Nil..MODULE$), scala.None..MODULE$) : $this.encodedFeatureAttrs(new scala.collection.immutable..colon.colon(attr, scala.collection.immutable.Nil..MODULE$), scala.None..MODULE$);
               break label41;
            }

            var10000 = $this.encodedFeatureAttrs(new scala.collection.immutable..colon.colon(NumericAttribute$.MODULE$.defaultAttr().withName(f.name()), scala.collection.immutable.Nil..MODULE$), scala.None..MODULE$);
         }
      } else {
         if (!(var6 instanceof VectorUDT)) {
            throw new MatchError(var6);
         }

         AttributeGroup group = AttributeGroup$.MODULE$.fromStructField(f);
         var10000 = $this.encodedFeatureAttrs(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(group.attributes().get()).toImmutableArraySeq(), new Some(group.name()));
      }

      Seq encodedAttrs = var10000;
      if (((Seq)featureAttrs$1.elem).isEmpty()) {
         featureAttrs$1.elem = encodedAttrs;
      } else {
         featureAttrs$1.elem = (Seq)encodedAttrs.flatMap((head) -> (Seq)((Seq)featureAttrs$1.elem).map((tail) -> {
               NumericAttribute var10000 = NumericAttribute$.MODULE$.defaultAttr();
               String var10001 = (String)head.name().get();
               return var10000.withName(var10001 + ":" + tail.name().get());
            }));
      }
   }

   private static final String format$1(final int index, final Option attrName, final Option categoryName, final Option groupName$1) {
      Seq parts = new scala.collection.immutable..colon.colon(groupName$1, new scala.collection.immutable..colon.colon(new Some(attrName.getOrElse(() -> Integer.toString(index))), new scala.collection.immutable..colon.colon(categoryName, scala.collection.immutable.Nil..MODULE$)));
      return ((IterableOnceOps)parts.flatten(.MODULE$.$conforms())).mkString("_");
   }

   // $FF: synthetic method
   public static final BinaryAttribute $anonfun$encodedFeatureAttrs$4(final int i$1, final NominalAttribute x2$1, final Option groupName$1, final int j) {
      return BinaryAttribute$.MODULE$.defaultAttr().withName(format$1(i$1, x2$1.name(), new Some(Integer.toString(j)), groupName$1));
   }

   public Interaction(final String uid) {
      this.uid = uid;
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public Interaction() {
      this(Identifiable$.MODULE$.randomUID("interaction"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

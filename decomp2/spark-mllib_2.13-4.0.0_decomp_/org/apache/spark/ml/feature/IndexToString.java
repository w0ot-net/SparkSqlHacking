package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.Attribute$;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCol;
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
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f\u0001B\n\u0015\u0001}A\u0001\"\u000e\u0001\u0003\u0006\u0004%\tE\u000e\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005o!)q\n\u0001C\u0001!\")q\n\u0001C\u00011\")!\f\u0001C\u00017\")\u0001\r\u0001C\u0001C\")A\r\u0001C\u0001K\"9A\u000e\u0001b\u0001\n\u000bi\u0007BB:\u0001A\u00035a\u000eC\u0003v\u0001\u0011\u0015a\u000fC\u0003y\u0001\u0011\u0005\u0013\u0010C\u0004\u0002\f\u0001!\t%!\u0004\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z\u001d9\u0011\u0011\u000e\u000b\t\u0002\u0005-dAB\n\u0015\u0011\u0003\ti\u0007\u0003\u0004P\u001f\u0011\u0005\u00111\u0012\u0005\b\u0003\u001b{A\u0011IAH\u0011%\tYjDA\u0001\n\u0013\tiJA\u0007J]\u0012,\u0007\u0010V8TiJLgn\u001a\u0006\u0003+Y\tqAZ3biV\u0014XM\u0003\u0002\u00181\u0005\u0011Q\u000e\u001c\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sO\u000e\u00011#\u0002\u0001!I1z\u0003CA\u0011#\u001b\u00051\u0012BA\u0012\u0017\u0005-!&/\u00198tM>\u0014X.\u001a:\u0011\u0005\u0015RS\"\u0001\u0014\u000b\u0005\u001dB\u0013AB:iCJ,GM\u0003\u0002*-\u0005)\u0001/\u0019:b[&\u00111F\n\u0002\f\u0011\u0006\u001c\u0018J\u001c9vi\u000e{G\u000e\u0005\u0002&[%\u0011aF\n\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\u001c\t\u0003aMj\u0011!\r\u0006\u0003eY\tA!\u001e;jY&\u0011A'\r\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002oA\u0011\u0001(\u0011\b\u0003s}\u0002\"AO\u001f\u000e\u0003mR!\u0001\u0010\u0010\u0002\rq\u0012xn\u001c;?\u0015\u0005q\u0014!B:dC2\f\u0017B\u0001!>\u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001k\u0004fA\u0001F\u0017B\u0011a)S\u0007\u0002\u000f*\u0011\u0001\nG\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001&H\u0005\u0015\u0019\u0016N\\2fC\u0005a\u0015!B\u0019/k9\u0002\u0014\u0001B;jI\u0002B3AA#L\u0003\u0019a\u0014N\\5u}Q\u0011\u0011k\u0015\t\u0003%\u0002i\u0011\u0001\u0006\u0005\u0006k\r\u0001\ra\u000e\u0015\u0004'\u0016[\u0005fA\u0002F-\u0006\nq+A\u00033]Ir\u0003\u0007F\u0001RQ\r!QiS\u0001\fg\u0016$\u0018J\u001c9vi\u000e{G\u000e\u0006\u0002];6\t\u0001\u0001C\u0003_\u000b\u0001\u0007q'A\u0003wC2,X\rK\u0002\u0006\u000b.\u000bAb]3u\u001fV$\b/\u001e;D_2$\"\u0001\u00182\t\u000by3\u0001\u0019A\u001c)\u0007\u0019)5*A\u0005tKRd\u0015MY3mgR\u0011AL\u001a\u0005\u0006=\u001e\u0001\ra\u001a\t\u0004Q&<T\"A\u001f\n\u0005)l$!B!se\u0006L\bfA\u0004F\u0017\u00061A.\u00192fYN,\u0012A\u001c\t\u0003_Bl\u0011\u0001K\u0005\u0003c\"\u0012\u0001c\u0015;sS:<\u0017I\u001d:bsB\u000b'/Y7)\u0007!)5*A\u0004mC\n,Gn\u001d\u0011)\u0007%)5*A\u0005hKRd\u0015MY3mgV\tq\rK\u0002\u000b\u000b.\u000bq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0004u\u0006\u0015\u0001cA>\u0002\u00025\tAP\u0003\u0002~}\u0006)A/\u001f9fg*\u0011q\u0010G\u0001\u0004gFd\u0017bAA\u0002y\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\r\u0005\u001d1\u00021\u0001{\u0003\u0019\u00198\r[3nC\"\u001a1\"R&\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BA\b\u0003[\u0001B!!\u0005\u0002(9!\u00111CA\u0012\u001d\u0011\t)\"!\t\u000f\t\u0005]\u0011q\u0004\b\u0005\u00033\tiBD\u0002;\u00037I\u0011!H\u0005\u00037qI!!\u0007\u000e\n\u0005}D\u0012bAA\u0013}\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u0015\u0003W\u0011\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0007\u0005\u0015b\u0010C\u0004\u000201\u0001\r!!\r\u0002\u000f\u0011\fG/Y:fiB\"\u00111GA !\u0019\t)$a\u000e\u0002<5\ta0C\u0002\u0002:y\u0014q\u0001R1uCN,G\u000f\u0005\u0003\u0002>\u0005}B\u0002\u0001\u0003\r\u0003\u0003\ni#!A\u0001\u0002\u000b\u0005\u00111\t\u0002\u0004?\u0012B\u0014\u0003BA#\u0003\u0017\u00022\u0001[A$\u0013\r\tI%\u0010\u0002\b\u001d>$\b.\u001b8h!\rA\u0017QJ\u0005\u0004\u0003\u001fj$aA!os\"\"A\"RA*C\t\t)&A\u00033]Ar\u0003'\u0001\u0003d_BLHcA)\u0002\\!9\u0011QL\u0007A\u0002\u0005}\u0013!B3yiJ\f\u0007cA8\u0002b%\u0019\u00111\r\u0015\u0003\u0011A\u000b'/Y7NCBD3!D#LQ\r\u0001QiS\u0001\u000e\u0013:$W\r\u001f+p'R\u0014\u0018N\\4\u0011\u0005I{1cB\b\u0002p\u0005U\u00141\u0010\t\u0004Q\u0006E\u0014bAA:{\t1\u0011I\\=SK\u001a\u0004B\u0001MA<#&\u0019\u0011\u0011P\u0019\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011QPAD\u001b\t\tyH\u0003\u0003\u0002\u0002\u0006\r\u0015AA5p\u0015\t\t))\u0001\u0003kCZ\f\u0017\u0002BAE\u0003\u007f\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\u001b\u0002\t1|\u0017\r\u001a\u000b\u0004#\u0006E\u0005BBAJ#\u0001\u0007q'\u0001\u0003qCRD\u0007\u0006B\tF\u0003/\u000b#!!'\u0002\u000bErcG\f\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0005\u0003BAQ\u0003Ok!!a)\u000b\t\u0005\u0015\u00161Q\u0001\u0005Y\u0006tw-\u0003\u0003\u0002*\u0006\r&AB(cU\u0016\u001cG\u000f\u000b\u0003\u0010\u000b\u0006]\u0005\u0006\u0002\bF\u0003/\u0003"
)
public class IndexToString extends Transformer implements HasInputCol, HasOutputCol, DefaultParamsWritable {
   private final String uid;
   private final StringArrayParam labels;
   private Param outputCol;
   private Param inputCol;

   public static IndexToString load(final String path) {
      return IndexToString$.MODULE$.load(path);
   }

   public static MLReader read() {
      return IndexToString$.MODULE$.read();
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

   public IndexToString setInputCol(final String value) {
      return (IndexToString)this.set(this.inputCol(), value);
   }

   public IndexToString setOutputCol(final String value) {
      return (IndexToString)this.set(this.outputCol(), value);
   }

   public IndexToString setLabels(final String[] value) {
      return (IndexToString)this.set(this.labels(), value);
   }

   public final StringArrayParam labels() {
      return this.labels;
   }

   public final String[] getLabels() {
      return (String[])this.$(this.labels());
   }

   public StructType transformSchema(final StructType schema) {
      String inputColName = (String)this.$(this.inputCol());
      DataType inputDataType = SchemaUtils$.MODULE$.getSchemaFieldType(schema, inputColName);
      .MODULE$.require(inputDataType instanceof NumericType, () -> "The input column " + inputColName + " must be a numeric type, but got " + inputDataType + ".");
      StructField[] inputFields = schema.fields();
      String outputColName = (String)this.$(this.outputCol());
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.refArrayOps((Object[])inputFields), (x$12) -> BoxesRunTime.boxToBoolean($anonfun$transformSchema$2(outputColName, x$12))), () -> "Output column " + outputColName + " already exists.");
      StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])inputFields), new StructField((String)this.$(this.outputCol()), org.apache.spark.sql.types.StringType..MODULE$, org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      return new StructType(outputFields);
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      StructField inputColSchema = SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), (String)this.$(this.inputCol()));
      String[] values = this.isDefined(this.labels()) && !scala.collection.ArrayOps..MODULE$.isEmpty$extension(.MODULE$.refArrayOps(this.$(this.labels()))) ? (String[])this.$(this.labels()) : (String[])((NominalAttribute)Attribute$.MODULE$.fromStructField(inputColSchema)).values().get();
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (index) -> $anonfun$transform$5(values, BoxesRunTime.unboxToDouble(index));
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IndexToString.class.getClassLoader());

      final class $typecreator1$4 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$4() {
         }
      }

      UserDefinedFunction indexer = var10000.udf(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
      String outputColName = (String)this.$(this.outputCol());
      return dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col("*"), indexer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset.apply((String)this.$(this.inputCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$)}))).as(outputColName)})));
   }

   public IndexToString copy(final ParamMap extra) {
      return (IndexToString)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transformSchema$2(final String outputColName$2, final StructField x$12) {
      boolean var3;
      label23: {
         String var10000 = x$12.name();
         if (var10000 == null) {
            if (outputColName$2 != null) {
               break label23;
            }
         } else if (!var10000.equals(outputColName$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final String $anonfun$transform$5(final String[] values$1, final double index) {
      int idx = (int)index;
      if (0 <= idx && idx < values$1.length) {
         return values$1[idx];
      } else {
         throw new SparkException("Unseen index: " + index + " ??");
      }
   }

   public IndexToString(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.labels = new StringArrayParam(this, "labels", "Optional array of labels specifying index-string mapping. If not provided or if empty, then metadata from inputCol is used instead.");
      Statics.releaseFence();
   }

   public IndexToString() {
      this(Identifiable$.MODULE$.randomUID("idxToStr"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

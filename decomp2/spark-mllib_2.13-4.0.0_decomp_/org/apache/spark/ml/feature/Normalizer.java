package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.sql.types.DataType;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001B\n\u0015\u0001}A\u0001B\r\u0001\u0003\u0006\u0004%\te\r\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005i!)A\n\u0001C\u0001\u001b\")A\n\u0001C\u0001#\"91\u000b\u0001b\u0001\n\u0003!\u0006B\u0002/\u0001A\u0003%Q\u000bC\u0003_\u0001\u0011\u0005q\fC\u0003f\u0001\u0011\u0005a\rC\u0003l\u0001\u0011EC\u000eC\u0003q\u0001\u0011E\u0013\u000f\u0003\u0004\u0000\u0001\u0011E\u0013\u0011\u0001\u0005\b\u0003\u0007\u0001A\u0011IA\u0003\u0011\u001d\t\u0019\u0002\u0001C!\u0003+9q!a\b\u0015\u0011\u0003\t\tC\u0002\u0004\u0014)!\u0005\u00111\u0005\u0005\u0007\u0019>!\t!!\u0011\t\u000f\u0005\rs\u0002\"\u0011\u0002F!I\u0011\u0011K\b\u0002\u0002\u0013%\u00111\u000b\u0002\u000b\u001d>\u0014X.\u00197ju\u0016\u0014(BA\u000b\u0017\u0003\u001d1W-\u0019;ve\u0016T!a\u0006\r\u0002\u00055d'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\t\u0017\u0011\u000b\u0005\u0012C\u0005\n\u0016\u000e\u0003YI!a\t\f\u0003!Us\u0017M]=Ue\u0006t7OZ8s[\u0016\u0014\bCA\u0013)\u001b\u00051#BA\u0014\u0017\u0003\u0019a\u0017N\\1mO&\u0011\u0011F\n\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005-\u0002Q\"\u0001\u000b\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0005=2\u0012\u0001B;uS2L!!\r\u0018\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003Q\u0002\"!\u000e \u000f\u0005Yb\u0004CA\u001c;\u001b\u0005A$BA\u001d\u001f\u0003\u0019a$o\\8u})\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>u\u00051\u0001K]3eK\u001aL!a\u0010!\u0003\rM#(/\u001b8h\u0015\ti$\bK\u0002\u0002\u0005\"\u0003\"a\u0011$\u000e\u0003\u0011S!!\u0012\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002H\t\n)1+\u001b8dK\u0006\n\u0011*A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002C\u0011\u00061A(\u001b8jiz\"\"A\u000b(\t\u000bI\u001a\u0001\u0019\u0001\u001b)\u00079\u0013\u0005\nK\u0002\u0004\u0005\"#\u0012A\u000b\u0015\u0004\t\tC\u0015!\u00019\u0016\u0003U\u0003\"AV-\u000e\u0003]S!\u0001\u0017\f\u0002\u000bA\f'/Y7\n\u0005i;&a\u0003#pk\ndW\rU1sC6D3!\u0002\"I\u0003\t\u0001\b\u0005K\u0002\u0007\u0005\"\u000bAaZ3u!V\t\u0001\r\u0005\u0002bE6\t!(\u0003\u0002du\t1Ai\\;cY\u0016D3a\u0002\"I\u0003\u0011\u0019X\r\u001e)\u0015\u0005\u001dDW\"\u0001\u0001\t\u000b%D\u0001\u0019\u00011\u0002\u000bY\fG.^3)\u0007!\u0011\u0005*A\nde\u0016\fG/\u001a+sC:\u001chm\u001c:n\rVt7-F\u0001n!\u0011\tg\u000e\n\u0013\n\u0005=T$!\u0003$v]\u000e$\u0018n\u001c82\u0003E1\u0018\r\\5eCR,\u0017J\u001c9viRK\b/\u001a\u000b\u0003eV\u0004\"!Y:\n\u0005QT$\u0001B+oSRDQA\u001e\u0006A\u0002]\f\u0011\"\u001b8qkR$\u0016\u0010]3\u0011\u0005alX\"A=\u000b\u0005i\\\u0018!\u0002;za\u0016\u001c(B\u0001?\u0019\u0003\r\u0019\u0018\u000f\\\u0005\u0003}f\u0014\u0001\u0002R1uCRK\b/Z\u0001\u000f_V$\b/\u001e;ECR\fG+\u001f9f+\u00059\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005\u001d\u0011Q\u0002\t\u0004q\u0006%\u0011bAA\u0006s\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005=A\u00021\u0001\u0002\b\u000511o\u00195f[\u0006D3\u0001\u0004\"I\u0003!!xn\u0015;sS:<G#\u0001\u001b)\t5\u0011\u0015\u0011D\u0011\u0003\u00037\tQa\r\u00181]AB3\u0001\u0001\"I\u0003)quN]7bY&TXM\u001d\t\u0003W=\u0019raDA\u0013\u0003W\t\t\u0004E\u0002b\u0003OI1!!\u000b;\u0005\u0019\te.\u001f*fMB!Q&!\f+\u0013\r\tyC\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\t\u0019$!\u0010\u000e\u0005\u0005U\"\u0002BA\u001c\u0003s\t!![8\u000b\u0005\u0005m\u0012\u0001\u00026bm\u0006LA!a\u0010\u00026\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011E\u0001\u0005Y>\fG\rF\u0002+\u0003\u000fBa!!\u0013\u0012\u0001\u0004!\u0014\u0001\u00029bi\"DC!\u0005\"\u0002N\u0005\u0012\u0011qJ\u0001\u0006c92d\u0006M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003+\u0002B!a\u0016\u0002^5\u0011\u0011\u0011\f\u0006\u0005\u00037\nI$\u0001\u0003mC:<\u0017\u0002BA0\u00033\u0012aa\u00142kK\u000e$\b\u0006B\bC\u0003\u001bBCA\u0004\"\u0002N\u0001"
)
public class Normalizer extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;
   private final DoubleParam p;

   public static Normalizer load(final String path) {
      return Normalizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Normalizer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public DoubleParam p() {
      return this.p;
   }

   public double getP() {
      return BoxesRunTime.unboxToDouble(this.$(this.p()));
   }

   public Normalizer setP(final double value) {
      return (Normalizer)this.set(this.p(), BoxesRunTime.boxToDouble(value));
   }

   public Function1 createTransformFunc() {
      org.apache.spark.mllib.feature.Normalizer normalizer = new org.apache.spark.mllib.feature.Normalizer(BoxesRunTime.unboxToDouble(this.$(this.p())));
      return (vector) -> normalizer.transform(Vectors$.MODULE$.fromML(vector)).asML();
   }

   public void validateInputType(final DataType inputType) {
      .MODULE$.require(inputType instanceof VectorUDT, () -> {
         String var10000 = (new VectorUDT()).catalogString();
         return "Input type must be " + var10000 + " but got " + inputType.catalogString() + ".";
      });
   }

   public DataType outputDataType() {
      return new VectorUDT();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.inputCol()))) && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.outputCol())))) {
         int size = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(schema, (String)this.$(this.inputCol()))).size();
         if (size >= 0) {
            outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), size);
         }
      }

      return outputSchema;
   }

   public String toString() {
      String var10000 = this.uid();
      return "Normalizer: uid=" + var10000 + ", p=" + this.$(this.p());
   }

   public Normalizer(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Normalizer.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10001 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Normalizer.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      super(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.p = new DoubleParam(this, "p", "the p norm value", ParamValidators$.MODULE$.gtEq((double)1.0F));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.p().$minus$greater(BoxesRunTime.boxToDouble((double)2.0F))}));
   }

   public Normalizer() {
      this(Identifiable$.MODULE$.randomUID("normalizer"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

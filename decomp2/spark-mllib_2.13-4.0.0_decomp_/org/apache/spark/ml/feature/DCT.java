package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.jtransforms.dct.DoubleDCT_1D;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001\u0002\n\u0014\u0001yA\u0001\"\r\u0001\u0003\u0006\u0004%\tE\r\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005g!)1\n\u0001C\u0001\u0019\")1\n\u0001C\u0001!\")!\u000b\u0001C\u0001'\")1\f\u0001C\u00019\")Q\r\u0001C\u0001M\")\u0001\u000e\u0001C)S\")Q\u000e\u0001C)]\")A\u0010\u0001C){\")a\u0010\u0001C!\u007f\"9\u00111\u0002\u0001\u0005B\u00055qaBA\f'!\u0005\u0011\u0011\u0004\u0004\u0007%MA\t!a\u0007\t\r-sA\u0011AA\u001d\u0011\u001d\tYD\u0004C!\u0003{A\u0011\"!\u0013\u000f\u0003\u0003%I!a\u0013\u0003\u0007\u0011\u001bEK\u0003\u0002\u0015+\u00059a-Z1ukJ,'B\u0001\f\u0018\u0003\tiGN\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h\u0007\u0001\u00192\u0001A\u0010,!\u0015\u0001\u0013eI\u0012*\u001b\u0005)\u0012B\u0001\u0012\u0016\u0005A)f.\u0019:z)J\fgn\u001d4pe6,'\u000f\u0005\u0002%O5\tQE\u0003\u0002'+\u00051A.\u001b8bY\u001eL!\u0001K\u0013\u0003\rY+7\r^8s!\tQ\u0003!D\u0001\u0014!\tas&D\u0001.\u0015\tqS#\u0001\u0003vi&d\u0017B\u0001\u0019.\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005\u0019\u0004C\u0001\u001b>\u001d\t)4\b\u0005\u00027s5\tqG\u0003\u00029;\u00051AH]8pizR\u0011AO\u0001\u0006g\u000e\fG.Y\u0005\u0003ye\na\u0001\u0015:fI\u00164\u0017B\u0001 @\u0005\u0019\u0019FO]5oO*\u0011A(\u000f\u0015\u0004\u0003\u0005;\u0005C\u0001\"F\u001b\u0005\u0019%B\u0001#\u0018\u0003)\tgN\\8uCRLwN\\\u0005\u0003\r\u000e\u0013QaU5oG\u0016\f\u0013\u0001S\u0001\u0006c9*d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003\u0003\u001e\u000ba\u0001P5oSRtDCA\u0015N\u0011\u0015\t4\u00011\u00014Q\ri\u0015i\u0012\u0015\u0004\u0007\u0005;E#A\u0015)\u0007\u0011\tu)A\u0004j]Z,'o]3\u0016\u0003Q\u0003\"!\u0016-\u000e\u0003YS!aV\u000b\u0002\u000bA\f'/Y7\n\u0005e3&\u0001\u0004\"p_2,\u0017M\u001c)be\u0006l\u0007fA\u0003B\u000f\u0006Q1/\u001a;J]Z,'o]3\u0015\u0005usV\"\u0001\u0001\t\u000b}3\u0001\u0019\u00011\u0002\u000bY\fG.^3\u0011\u0005\u0005\u0014W\"A\u001d\n\u0005\rL$a\u0002\"p_2,\u0017M\u001c\u0015\u0004\r\u0005;\u0015AC4fi&sg/\u001a:tKV\t\u0001\rK\u0002\b\u0003\u001e\u000b1c\u0019:fCR,GK]1og\u001a|'/\u001c$v]\u000e,\u0012A\u001b\t\u0005C.\u001c3%\u0003\u0002ms\tIa)\u001e8di&|g.M\u0001\u0012m\u0006d\u0017\u000eZ1uK&s\u0007/\u001e;UsB,GCA8s!\t\t\u0007/\u0003\u0002rs\t!QK\\5u\u0011\u0015\u0019\u0018\u00021\u0001u\u0003%Ig\u000e];u)f\u0004X\r\u0005\u0002vu6\taO\u0003\u0002xq\u0006)A/\u001f9fg*\u0011\u0011pF\u0001\u0004gFd\u0017BA>w\u0005!!\u0015\r^1UsB,\u0017AD8viB,H\u000fR1uCRK\b/Z\u000b\u0002i\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002\u0002\u0005\u001d\u0001cA;\u0002\u0004%\u0019\u0011Q\u0001<\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002\n-\u0001\r!!\u0001\u0002\rM\u001c\u0007.Z7b\u0003!!xn\u0015;sS:<G#A\u001a)\t1\t\u0015\u0011C\u0011\u0003\u0003'\tQa\r\u00181]AB3\u0001A!H\u0003\r!5\t\u0016\t\u0003U9\u0019rADA\u000f\u0003G\tI\u0003E\u0002b\u0003?I1!!\t:\u0005\u0019\te.\u001f*fMB!A&!\n*\u0013\r\t9#\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\tY#!\u000e\u000e\u0005\u00055\"\u0002BA\u0018\u0003c\t!![8\u000b\u0005\u0005M\u0012\u0001\u00026bm\u0006LA!a\u000e\u0002.\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011D\u0001\u0005Y>\fG\rF\u0002*\u0003\u007fAa!!\u0011\u0011\u0001\u0004\u0019\u0014\u0001\u00029bi\"DC\u0001E!\u0002F\u0005\u0012\u0011qI\u0001\u0006c92d\u0006M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u001b\u0002B!a\u0014\u0002V5\u0011\u0011\u0011\u000b\u0006\u0005\u0003'\n\t$\u0001\u0003mC:<\u0017\u0002BA,\u0003#\u0012aa\u00142kK\u000e$\b\u0006\u0002\bB\u0003\u000bBC!D!\u0002F\u0001"
)
public class DCT extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;

   public static DCT load(final String path) {
      return DCT$.MODULE$.load(path);
   }

   public static MLReader read() {
      return DCT$.MODULE$.read();
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

   public BooleanParam inverse() {
      return new BooleanParam(this, "inverse", "Set transformer to perform inverse DCT");
   }

   public DCT setInverse(final boolean value) {
      return (DCT)this.set(this.inverse(), BoxesRunTime.boxToBoolean(value));
   }

   public boolean getInverse() {
      return BoxesRunTime.unboxToBoolean(this.$(this.inverse()));
   }

   public Function1 createTransformFunc() {
      return (vec) -> {
         double[] result = vec.toArray();
         DoubleDCT_1D jTransformer = new DoubleDCT_1D((long)result.length);
         if (BoxesRunTime.unboxToBoolean(this.$(this.inverse()))) {
            jTransformer.inverse(result, true);
         } else {
            jTransformer.forward(result, true);
         }

         return .MODULE$.dense(result);
      };
   }

   public void validateInputType(final DataType inputType) {
      scala.Predef..MODULE$.require(inputType instanceof VectorUDT, () -> {
         String var10000 = (new VectorUDT()).catalogString();
         return "Input type must be " + var10000 + " but got " + inputType.catalogString() + ".";
      });
   }

   public DataType outputDataType() {
      return new VectorUDT();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.inputCol()))) && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         int size = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(schema, (String)this.$(this.inputCol()))).size();
         if (size >= 0) {
            outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), size);
         }
      }

      return outputSchema;
   }

   public String toString() {
      String var10000 = this.uid();
      return "DCT: uid=" + var10000 + ", inverse=" + this.inverse();
   }

   public DCT(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DCT.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DCT.class.getClassLoader());

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
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.inverse().$minus$greater(BoxesRunTime.boxToBoolean(false))}));
   }

   public DCT() {
      this(Identifiable$.MODULE$.randomUID("dct"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

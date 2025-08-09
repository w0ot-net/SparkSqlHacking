package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.Param;
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
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
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
   bytes = "\u0006\u0005\u0005uc\u0001B\n\u0015\u0001}A\u0001B\r\u0001\u0003\u0006\u0004%\te\r\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005i!)A\n\u0001C\u0001\u001b\")A\n\u0001C\u0001#\"91\u000b\u0001b\u0001\n\u0003!\u0006B\u00020\u0001A\u0003%Q\u000bC\u0003a\u0001\u0011\u0005\u0011\rC\u0003g\u0001\u0011\u0005q\rC\u0003j\u0001\u0011E#\u000eC\u0003p\u0001\u0011E\u0003\u000fC\u0003\u007f\u0001\u0011Es\u0010C\u0004\u0002\u0002\u0001!\t%a\u0001\t\u000f\u0005=\u0001\u0001\"\u0011\u0002\u0012\u001d9\u00111\u0004\u000b\t\u0002\u0005uaAB\n\u0015\u0011\u0003\ty\u0002\u0003\u0004M\u001f\u0011\u0005\u0011Q\b\u0005\b\u0003\u007fyA\u0011IA!\u0011%\tIeDA\u0001\n\u0013\tYE\u0001\nFY\u0016lWM\u001c;xSN,\u0007K]8ek\u000e$(BA\u000b\u0017\u0003\u001d1W-\u0019;ve\u0016T!a\u0006\r\u0002\u00055d'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\t\u0017\u0011\u000b\u0005\u0012C\u0005\n\u0016\u000e\u0003YI!a\t\f\u0003!Us\u0017M]=Ue\u0006t7OZ8s[\u0016\u0014\bCA\u0013)\u001b\u00051#BA\u0014\u0017\u0003\u0019a\u0017N\\1mO&\u0011\u0011F\n\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005-\u0002Q\"\u0001\u000b\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0005=2\u0012\u0001B;uS2L!!\r\u0018\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003Q\u0002\"!\u000e \u000f\u0005Yb\u0004CA\u001c;\u001b\u0005A$BA\u001d\u001f\u0003\u0019a$o\\8u})\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>u\u00051\u0001K]3eK\u001aL!a\u0010!\u0003\rM#(/\u001b8h\u0015\ti$\bK\u0002\u0002\u0005\"\u0003\"a\u0011$\u000e\u0003\u0011S!!\u0012\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002H\t\n)1+\u001b8dK\u0006\n\u0011*A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002C\u0011\u00061A(\u001b8jiz\"\"A\u000b(\t\u000bI\u001a\u0001\u0019\u0001\u001b)\u00079\u0013\u0005\nK\u0002\u0004\u0005\"#\u0012A\u000b\u0015\u0004\t\tC\u0015AC:dC2Lgn\u001a,fGV\tQ\u000bE\u0002W3\u0012j\u0011a\u0016\u0006\u00031Z\tQ\u0001]1sC6L!AW,\u0003\u000bA\u000b'/Y7)\u0007\u0015\u0011E,I\u0001^\u0003\u0015\u0011d\u0006\r\u00181\u0003-\u00198-\u00197j]\u001e4Vm\u0019\u0011)\u0007\u0019\u0011E,A\u0007tKR\u001c6-\u00197j]\u001e4Vm\u0019\u000b\u0003E\u000el\u0011\u0001\u0001\u0005\u0006I\u001e\u0001\r\u0001J\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u000f\tc\u0016!D4fiN\u001b\u0017\r\\5oOZ+7-F\u0001%Q\rA!\tX\u0001\u0014GJ,\u0017\r^3Ue\u0006t7OZ8s[\u001a+hnY\u000b\u0002WB!A.\u001c\u0013%\u001b\u0005Q\u0014B\u00018;\u0005%1UO\\2uS>t\u0017'A\twC2LG-\u0019;f\u0013:\u0004X\u000f\u001e+za\u0016$\"!\u001d;\u0011\u00051\u0014\u0018BA:;\u0005\u0011)f.\u001b;\t\u000bUT\u0001\u0019\u0001<\u0002\u0013%t\u0007/\u001e;UsB,\u0007CA<}\u001b\u0005A(BA={\u0003\u0015!\u0018\u0010]3t\u0015\tY\b$A\u0002tc2L!! =\u0003\u0011\u0011\u000bG/\u0019+za\u0016\fab\\;uaV$H)\u0019;b)f\u0004X-F\u0001w\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BA\u0003\u0003\u0017\u00012a^A\u0004\u0013\r\tI\u0001\u001f\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\u0007\u0019\u0001\u0007\u0011QA\u0001\u0007g\u000eDW-\\1\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\u000e\u0015\u0005\u001b\t\u000b)\"\t\u0002\u0002\u0018\u0005)1G\f\u0019/a!\u001a\u0001A\u0011%\u0002%\u0015cW-\\3oi^L7/\u001a)s_\u0012,8\r\u001e\t\u0003W=\u0019raDA\u0011\u0003O\ti\u0003E\u0002m\u0003GI1!!\n;\u0005\u0019\te.\u001f*fMB!Q&!\u000b+\u0013\r\tYC\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\ty#!\u000f\u000e\u0005\u0005E\"\u0002BA\u001a\u0003k\t!![8\u000b\u0005\u0005]\u0012\u0001\u00026bm\u0006LA!a\u000f\u00022\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011QD\u0001\u0005Y>\fG\rF\u0002+\u0003\u0007Ba!!\u0012\u0012\u0001\u0004!\u0014\u0001\u00029bi\"D3!\u0005\"]\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0005\u0005\u0003\u0002P\u0005USBAA)\u0015\u0011\t\u0019&!\u000e\u0002\t1\fgnZ\u0005\u0005\u0003/\n\tF\u0001\u0004PE*,7\r\u001e\u0015\u0004\u001f\tc\u0006f\u0001\bC9\u0002"
)
public class ElementwiseProduct extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;
   private final Param scalingVec;

   public static ElementwiseProduct load(final String path) {
      return ElementwiseProduct$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ElementwiseProduct$.MODULE$.read();
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

   public Param scalingVec() {
      return this.scalingVec;
   }

   public ElementwiseProduct setScalingVec(final Vector value) {
      return (ElementwiseProduct)this.set(this.scalingVec(), value);
   }

   public Vector getScalingVec() {
      return (Vector)this.getOrDefault(this.scalingVec());
   }

   public Function1 createTransformFunc() {
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps(this.params()), this.scalingVec()), () -> "transformation requires a weight vector");
      org.apache.spark.mllib.feature.ElementwiseProduct elemScaler = new org.apache.spark.mllib.feature.ElementwiseProduct(Vectors$.MODULE$.fromML((Vector)this.$(this.scalingVec())));
      int vectorSize = ((Vector)this.$(this.scalingVec())).size();
      return (vector) -> {
         .MODULE$.require(vector.size() == vectorSize, () -> "vector sizes do not match: Expected " + vectorSize + " but found " + vector.size());
         if (vector instanceof DenseVector var6) {
            Option var7 = org.apache.spark.ml.linalg.DenseVector..MODULE$.unapply(var6);
            if (!var7.isEmpty()) {
               double[] values = (double[])var7.get();
               double[] newValues = elemScaler.transformDense(values);
               return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(newValues);
            }
         }

         if (vector instanceof SparseVector var10) {
            Option var11 = org.apache.spark.ml.linalg.SparseVector..MODULE$.unapply(var10);
            if (!var11.isEmpty()) {
               int size = BoxesRunTime.unboxToInt(((Tuple3)var11.get())._1());
               int[] indices = (int[])((Tuple3)var11.get())._2();
               double[] values = (double[])((Tuple3)var11.get())._3();
               Tuple2 var16 = elemScaler.transformSparse(indices, values);
               if (var16 != null) {
                  int[] newIndices = (int[])var16._1();
                  double[] newValues = (double[])var16._2();
                  Tuple2 var15 = new Tuple2(newIndices, newValues);
                  int[] newIndicesx = (int[])var15._1();
                  double[] newValuesxx = (double[])var15._2();
                  return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, newIndicesx, newValuesxx);
               }

               throw new MatchError(var16);
            }
         }

         throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + vector.getClass() + ".");
      };
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
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.outputCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), ((Vector)this.$(this.scalingVec())).size());
      }

      return outputSchema;
   }

   public String toString() {
      String var10000 = this.uid();
      return "ElementwiseProduct: uid=" + var10000 + this.get(this.scalingVec()).map((v) -> ", vectorSize=" + v.size()).getOrElse(() -> "");
   }

   public ElementwiseProduct(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ElementwiseProduct.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ElementwiseProduct.class.getClassLoader());

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
      this.scalingVec = new Param(this, "scalingVec", "vector for hadamard product", scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public ElementwiseProduct() {
      this(Identifiable$.MODULE$.randomUID("elemProd"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

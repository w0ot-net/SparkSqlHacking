package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.util.ArrayImplicits.;
import scala.Function1;
import scala.Predef;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001\u0002\b\u0010\u0001iA\u0001\"\u0010\u0001\u0003\u0006\u0004%\tE\u0010\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005?!)!\n\u0001C\u0001\u0017\")!\n\u0001C\u0001\u001f\")1\u000b\u0001C))\")\u0011\f\u0001C)5\")\u0001\u000e\u0001C)S\")!\u000e\u0001C!W\u001e)\u0001p\u0004E\u0001s\u001a)ab\u0004E\u0001u\"1!J\u0003C\u0001\u0003'Aq!!\u0006\u000b\t\u0003\n9\u0002C\u0005\u0002$)\t\t\u0011\"\u0003\u0002&\tIAk\\6f]&TXM\u001d\u0006\u0003!E\tqAZ3biV\u0014XM\u0003\u0002\u0013'\u0005\u0011Q\u000e\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sO\u000e\u00011c\u0001\u0001\u001coA)A$H\u0010-k5\t\u0011#\u0003\u0002\u001f#\t\u0001RK\\1ssR\u0013\u0018M\\:g_JlWM\u001d\t\u0003A%r!!I\u0014\u0011\u0005\t*S\"A\u0012\u000b\u0005\u0011J\u0012A\u0002\u001fs_>$hHC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tAS%\u0001\u0004Qe\u0016$WMZ\u0005\u0003U-\u0012aa\u0015;sS:<'B\u0001\u0015&!\ri#g\b\b\u0003]Ar!AI\u0018\n\u0003\u0019J!!M\u0013\u0002\u000fA\f7m[1hK&\u00111\u0007\u000e\u0002\u0004'\u0016\f(BA\u0019&!\t1\u0004!D\u0001\u0010!\tA4(D\u0001:\u0015\tQ\u0014#\u0001\u0003vi&d\u0017B\u0001\u001f:\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005y\u0002fA\u0001A\rB\u0011\u0011\tR\u0007\u0002\u0005*\u00111iE\u0001\u000bC:tw\u000e^1uS>t\u0017BA#C\u0005\u0015\u0019\u0016N\\2fC\u00059\u0015!B\u0019/i9\u0002\u0014\u0001B;jI\u0002B3A\u0001!G\u0003\u0019a\u0014N\\5u}Q\u0011Q\u0007\u0014\u0005\u0006{\r\u0001\ra\b\u0015\u0004\u0019\u00023\u0005fA\u0002A\rR\tQ\u0007K\u0002\u0005\u0001F\u000b\u0013AU\u0001\u0006c9\u0012d\u0006M\u0001\u0014GJ,\u0017\r^3Ue\u0006t7OZ8s[\u001a+hnY\u000b\u0002+B!akV\u0010-\u001b\u0005)\u0013B\u0001-&\u0005%1UO\\2uS>t\u0017'A\twC2LG-\u0019;f\u0013:\u0004X\u000f\u001e+za\u0016$\"a\u00170\u0011\u0005Yc\u0016BA/&\u0005\u0011)f.\u001b;\t\u000b}3\u0001\u0019\u00011\u0002\u0013%t\u0007/\u001e;UsB,\u0007CA1g\u001b\u0005\u0011'BA2e\u0003\u0015!\u0018\u0010]3t\u0015\t)7#A\u0002tc2L!a\u001a2\u0003\u0011\u0011\u000bG/\u0019+za\u0016\fab\\;uaV$H)\u0019;b)f\u0004X-F\u0001a\u0003\u0011\u0019w\u000e]=\u0015\u0005Ub\u0007\"B7\t\u0001\u0004q\u0017!B3yiJ\f\u0007CA8s\u001b\u0005\u0001(BA9\u0012\u0003\u0015\u0001\u0018M]1n\u0013\t\u0019\bO\u0001\u0005QCJ\fW.T1qQ\rA\u0001)^\u0011\u0002m\u0006)\u0011G\f\u001b/c!\u001a\u0001\u0001Q)\u0002\u0013Q{7.\u001a8ju\u0016\u0014\bC\u0001\u001c\u000b'\u0015Q1P`A\u0002!\t1F0\u0003\u0002~K\t1\u0011I\\=SK\u001a\u00042\u0001O@6\u0013\r\t\t!\u000f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\t!![8\u000b\u0005\u00055\u0011\u0001\u00026bm\u0006LA!!\u0005\u0002\b\ta1+\u001a:jC2L'0\u00192mKR\t\u00110\u0001\u0003m_\u0006$GcA\u001b\u0002\u001a!1\u00111\u0004\u0007A\u0002}\tA\u0001]1uQ\"\"A\u0002QA\u0010C\t\t\t#A\u00032]Yr\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002(A!\u0011\u0011FA\u0018\u001b\t\tYC\u0003\u0003\u0002.\u0005-\u0011\u0001\u00027b]\u001eLA!!\r\u0002,\t1qJ\u00196fGRDCA\u0003!\u0002 !\"\u0011\u0002QA\u0010\u0001"
)
public class Tokenizer extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;

   public static Tokenizer load(final String path) {
      return Tokenizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Tokenizer$.MODULE$.read();
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

   public Function1 createTransformFunc() {
      return (x$1) -> .MODULE$.SparkArrayOps(x$1.toLowerCase().split("\\s")).toImmutableArraySeq();
   }

   public void validateInputType(final DataType inputType) {
      Predef var10000;
      boolean var10001;
      label17: {
         label16: {
            var10000 = scala.Predef..MODULE$;
            StringType var2 = org.apache.spark.sql.types.StringType..MODULE$;
            if (inputType == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (inputType.equals(var2)) {
               break label16;
            }

            var10001 = false;
            break label17;
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> {
         String var10000 = org.apache.spark.sql.types.StringType..MODULE$.catalogString();
         return "Input type must be " + var10000 + " type but got " + inputType.catalogString() + ".";
      });
   }

   public DataType outputDataType() {
      return new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, true);
   }

   public Tokenizer copy(final ParamMap extra) {
      return (Tokenizer)this.defaultCopy(extra);
   }

   public Tokenizer(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Tokenizer.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10001 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Tokenizer.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator2$1() {
         }
      }

      super(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
   }

   public Tokenizer() {
      this(Identifiable$.MODULE$.randomUID("tok"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

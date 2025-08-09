package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.apache.spark.sql.types.StructType;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.Tuple1;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001B\u000b\u0017\u0001\u0005B\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005y!A!\n\u0001BC\u0002\u0013\u00051\n\u0003\u0005\\\u0001\t\u0005\t\u0015!\u0003M\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u0015)\u0007\u0001\"\u0011g\u000f\u0015qh\u0003#\u0001\u0000\r\u0019)b\u0003#\u0001\u0002\u0002!1Q\f\u0003C\u0001\u0003/Aq!!\u0007\t\t\u0003\nYb\u0002\u0005\u0002$!A\tAFA\u0013\r!\tI\u0003\u0003E\u0001-\u0005-\u0002BB/\r\t\u0003\ti\u0003C\u0005\u000201\u0011\r\u0011\"\u0003\u00022!A\u0011Q\b\u0007!\u0002\u0013\t\u0019\u0004\u0003\u0006\u0002@1\u0011\r\u0011\"\u0001\u0017\u0003cA\u0001\"!\u0011\rA\u0003%\u00111\u0007\u0005\u0007K2!\t!a\u0011\t\u000f\u0005eA\u0002\"\u0001\u0002P!I\u0011q\u000b\u0005\u0002\u0002\u0013%\u0011\u0011\f\u0002\u001e!><XM]%uKJ\fG/[8o\u00072,8\u000f^3sS:<Wj\u001c3fY*\u0011q\u0003G\u0001\u000bG2,8\u000f^3sS:<'BA\r\u001b\u0003\u0015iG\u000e\\5c\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7\u0001A\n\u0005\u0001\tBc\u0006\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0004B]f\u0014VM\u001a\t\u0003S1j\u0011A\u000b\u0006\u0003Wa\tA!\u001e;jY&\u0011QF\u000b\u0002\t'\u00064X-\u00192mKB\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!a\r\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0013B\u0001\u001c%\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Y\"\u0013!A6\u0016\u0003q\u0002\"aI\u001f\n\u0005y\"#aA%oi\"\u001a\u0011\u0001\u0011$\u0011\u0005\u0005#U\"\u0001\"\u000b\u0005\rS\u0012AC1o]>$\u0018\r^5p]&\u0011QI\u0011\u0002\u0006'&t7-Z\u0011\u0002\u000f\u0006)\u0011GL\u001a/a\u0005\u00111\u000e\t\u0015\u0004\u0005\u00013\u0015aC1tg&<g.\\3oiN,\u0012\u0001\u0014\t\u0004\u001bB\u0013V\"\u0001(\u000b\u0005=S\u0012a\u0001:eI&\u0011\u0011K\u0014\u0002\u0004%\u0012#\u0005CA*X\u001d\t!V+D\u0001\u0017\u0013\t1f#\u0001\rQ_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001eL!\u0001W-\u0003\u0015\u0005\u001b8/[4o[\u0016tGO\u0003\u0002W-!\u001a1\u0001\u0011$\u0002\u0019\u0005\u001c8/[4o[\u0016tGo\u001d\u0011)\u0007\u0011\u0001e)\u0001\u0004=S:LGO\u0010\u000b\u0004?\u0002\u0014\u0007C\u0001+\u0001\u0011\u0015QT\u00011\u0001=Q\r\u0001\u0007I\u0012\u0005\u0006\u0015\u0016\u0001\r\u0001\u0014\u0015\u0004E\u00023\u0005fA\u0003A\r\u0006!1/\u0019<f)\r9'\u000e\u001d\t\u0003G!L!!\u001b\u0013\u0003\tUs\u0017\u000e\u001e\u0005\u0006W\u001a\u0001\r\u0001\\\u0001\u0003g\u000e\u0004\"!\u001c8\u000e\u0003iI!a\u001c\u000e\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000bE4\u0001\u0019\u0001:\u0002\tA\fG\u000f\u001b\t\u0003g^t!\u0001^;\u0011\u0005E\"\u0013B\u0001<%\u0003\u0019\u0001&/\u001a3fM&\u0011\u00010\u001f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Y$\u0003f\u0001\u0004Aw\u0006\nA0A\u00032]Qr\u0003\u0007K\u0002\u0001\u0001\u001a\u000bQ\u0004U8xKJLE/\u001a:bi&|gn\u00117vgR,'/\u001b8h\u001b>$W\r\u001c\t\u0003)\"\u0019b\u0001\u0003\u0012\u0002\u0004\u0005%\u0001\u0003B\u0015\u0002\u0006}K1!a\u0002+\u0005\u0019au.\u00193feB!\u00111BA\u000b\u001b\t\tiA\u0003\u0003\u0002\u0010\u0005E\u0011AA5p\u0015\t\t\u0019\"\u0001\u0003kCZ\f\u0017b\u0001\u001d\u0002\u000eQ\tq0\u0001\u0003m_\u0006$G#B0\u0002\u001e\u0005}\u0001\"B6\u000b\u0001\u0004a\u0007\"B9\u000b\u0001\u0004\u0011\bf\u0001\u0006Aw\u0006a1+\u0019<f\u0019>\fGMV\u0019`aA\u0019\u0011q\u0005\u0007\u000e\u0003!\u0011AbU1wK2{\u0017\r\u001a,2?B\u001a\"\u0001\u0004\u0012\u0015\u0005\u0005\u0015\u0012!\u0005;iSN4uN]7biZ+'o]5p]V\u0011\u00111\u0007\t\u0005\u0003k\tY$\u0004\u0002\u00028)!\u0011\u0011HA\t\u0003\u0011a\u0017M\\4\n\u0007a\f9$\u0001\nuQ&\u001chi\u001c:nCR4VM]:j_:\u0004\u0013!\u0004;iSN\u001cE.Y:t\u001d\u0006lW-\u0001\buQ&\u001c8\t\\1tg:\u000bW.\u001a\u0011\u0015\u000f\u001d\f)%a\u0012\u0002L!)1N\u0005a\u0001Y\"1\u0011\u0011\n\nA\u0002}\u000bQ!\\8eK2DQ!\u001d\nA\u0002ID3A\u0005!|)\u0015y\u0016\u0011KA*\u0011\u0015Y7\u00031\u0001m\u0011\u0015\t8\u00031\u0001sQ\r\u0019\u0002i_\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00037\u0002B!!\u000e\u0002^%!\u0011qLA\u001c\u0005\u0019y%M[3di\"\u001a\u0001\u0002Q>)\u0007\u001d\u00015\u0010"
)
public class PowerIterationClusteringModel implements Saveable, Serializable {
   private final int k;
   private final RDD assignments;

   public static PowerIterationClusteringModel load(final SparkContext sc, final String path) {
      return PowerIterationClusteringModel$.MODULE$.load(sc, path);
   }

   public int k() {
      return this.k;
   }

   public RDD assignments() {
      return this.assignments;
   }

   public void save(final SparkContext sc, final String path) {
      PowerIterationClusteringModel.SaveLoadV1_0$.MODULE$.save(sc, this, path);
   }

   public PowerIterationClusteringModel(final int k, final RDD assignments) {
      this.k = k;
      this.assignments = assignments;
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.PowerIterationClusteringModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final PowerIterationClusteringModel model, final String path) {
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("k"), BoxesRunTime.boxToInteger(model.k())), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         RDD var10 = model.assignments();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("org.apache.spark.mllib.clustering").asModule().moduleClass()), $m$untyped.staticModule("org.apache.spark.mllib.clustering.PowerIterationClustering")), $m$untyped.staticClass("org.apache.spark.mllib.clustering.PowerIterationClustering.Assignment"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var10, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public PowerIterationClusteringModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label36: {
               label35: {
                  String className = (String)var7._1();
                  String formatVersion = (String)var7._2();
                  JValue metadata = (JValue)var7._3();
                  Tuple3 var6 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var6._1();
                  formatVersion = (String)var6._2();
                  metadata = (JValue)var6._3();
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisClassName();
                  if (className == null) {
                     if (var14 == null) {
                        break label35;
                     }
                  } else if (className.equals(var14)) {
                     break label35;
                  }

                  var10001 = false;
                  break label36;
               }

               var10001 = true;
            }

            label28: {
               label27: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var15 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var15 == null) {
                        break label27;
                     }
                  } else if (formatVersion.equals(var15)) {
                     break label27;
                  }

                  var10001 = false;
                  break label28;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int k = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "k")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            Dataset assignments = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Loader$ var22 = Loader$.MODULE$;
            StructType var24 = assignments.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("org.apache.spark.mllib.clustering").asModule().moduleClass()), $m$untyped.staticModule("org.apache.spark.mllib.clustering.PowerIterationClustering")), $m$untyped.staticClass("org.apache.spark.mllib.clustering.PowerIterationClustering.Assignment"), scala.collection.immutable.Nil..MODULE$);
               }

               public $typecreator1$2() {
               }
            }

            var22.checkSchema(var24, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
            RDD assignmentsRDD = assignments.rdd().map((x0$1) -> {
               if (x0$1 != null) {
                  Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                  if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                     Object id = ((SeqOps)var3.get()).apply(0);
                     Object cluster = ((SeqOps)var3.get()).apply(1);
                     if (id instanceof Long) {
                        long var6 = BoxesRunTime.unboxToLong(id);
                        if (cluster instanceof Integer) {
                           int var8 = BoxesRunTime.unboxToInt(cluster);
                           return new PowerIterationClustering.Assignment(var6, var8);
                        }
                     }
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(PowerIterationClustering.Assignment.class));
            return new PowerIterationClusteringModel(k, assignmentsRDD);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

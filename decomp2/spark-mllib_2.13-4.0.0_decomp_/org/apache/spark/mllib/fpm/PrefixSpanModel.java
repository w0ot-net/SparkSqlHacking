package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Predef;
import scala.Tuple1;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055g\u0001\u0002\u000b\u0016\u0001\u0001B\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t=\u0002\u0011\t\u0011)A\u0005y!)\u0001\r\u0001C\u0001C\")a\r\u0001C!O\u001e1q0\u0006E\u0001\u0003\u00031a\u0001F\u000b\t\u0002\u0005\r\u0001B\u00021\u0007\t\u0003\t\u0019\u0003C\u0004\u0002&\u0019!\t%a\n\b\u0011\u0005eb\u0001#\u0001\u0016\u0003w1\u0001\"a\u0010\u0007\u0011\u0003)\u0012\u0011\t\u0005\u0007A*!\t!a\u0011\t\u0013\u0005\u0015#B1A\u0005\n\u0005\u001d\u0003\u0002CA*\u0015\u0001\u0006I!!\u0013\t\u0013\u0005U#B1A\u0005\n\u0005\u001d\u0003\u0002CA,\u0015\u0001\u0006I!!\u0013\t\r\u0019TA\u0011AA-\u0011\u001d\t)C\u0003C\u0001\u0003WBq!a\u001f\u000b\t\u0003\ti\bC\u0005\u0002@\u001a\t\t\u0011\"\u0003\u0002B\ny\u0001K]3gSb\u001c\u0006/\u00198N_\u0012,GN\u0003\u0002\u0017/\u0005\u0019a\r]7\u000b\u0005aI\u0012!B7mY&\u0014'B\u0001\u000e\u001c\u0003\u0015\u0019\b/\u0019:l\u0015\taR$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002=\u0005\u0019qN]4\u0004\u0001U\u0011\u0011\u0005T\n\u0005\u0001\tBc\u0006\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0004B]f\u0014VM\u001a\t\u0003S1j\u0011A\u000b\u0006\u0003W]\tA!\u001e;jY&\u0011QF\u000b\u0002\t'\u00064X-\u00192mKB\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!aM\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0013B\u0001\u001c%\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Y\"\u0013!\u00044sKF\u001cV-];f]\u000e,7/F\u0001=!\ri\u0004IQ\u0007\u0002})\u0011q(G\u0001\u0004e\u0012$\u0017BA!?\u0005\r\u0011F\t\u0012\t\u0004\u0007\u001eSeB\u0001#F\u001b\u0005)\u0012B\u0001$\u0016\u0003)\u0001&/\u001a4jqN\u0003\u0018M\\\u0005\u0003\u0011&\u0013AB\u0012:fcN+\u0017/^3oG\u0016T!AR\u000b\u0011\u0005-cE\u0002\u0001\u0003\u0006\u001b\u0002\u0011\rA\u0014\u0002\u0005\u0013R,W.\u0005\u0002P%B\u00111\u0005U\u0005\u0003#\u0012\u0012qAT8uQ&tw\r\u0005\u0002$'&\u0011A\u000b\n\u0002\u0004\u0003:L\bfA\u0001W9B\u0011qKW\u0007\u00021*\u0011\u0011,G\u0001\u000bC:tw\u000e^1uS>t\u0017BA.Y\u0005\u0015\u0019\u0016N\\2fC\u0005i\u0016!B\u0019/k9\u0002\u0014A\u00044sKF\u001cV-];f]\u000e,7\u000f\t\u0015\u0004\u0005Yc\u0016A\u0002\u001fj]&$h\b\u0006\u0002cGB\u0019A\t\u0001&\t\u000bi\u001a\u0001\u0019\u0001\u001f)\u0007\r4F\fK\u0002\u0004-r\u000bAa]1wKR\u0019\u0001n[9\u0011\u0005\rJ\u0017B\u00016%\u0005\u0011)f.\u001b;\t\u000b1$\u0001\u0019A7\u0002\u0005M\u001c\u0007C\u00018p\u001b\u0005I\u0012B\u00019\u001a\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0011\u0015\u0011H\u00011\u0001t\u0003\u0011\u0001\u0018\r\u001e5\u0011\u0005QDhBA;w!\t\tD%\u0003\u0002xI\u00051\u0001K]3eK\u001aL!!\u001f>\u0003\rM#(/\u001b8h\u0015\t9H\u0005K\u0002\u0005-r\f\u0013!`\u0001\u0006e9\u0002d\u0006\r\u0015\u0004\u0001Yc\u0016a\u0004)sK\u001aL\u0007p\u00159b]6{G-\u001a7\u0011\u0005\u001131C\u0002\u0004#\u0003\u000b\t)\u0002E\u0003*\u0003\u000f\tY!C\u0002\u0002\n)\u0012a\u0001T8bI\u0016\u0014\b\u0007BA\u0007\u0003#\u0001B\u0001\u0012\u0001\u0002\u0010A\u00191*!\u0005\u0005\u0015\u0005Ma!!A\u0001\u0002\u000b\u0005aJA\u0002`IE\u0002B!a\u0006\u0002\"5\u0011\u0011\u0011\u0004\u0006\u0005\u00037\ti\"\u0001\u0002j_*\u0011\u0011qD\u0001\u0005U\u00064\u0018-C\u00029\u00033!\"!!\u0001\u0002\t1|\u0017\r\u001a\u000b\u0007\u0003S\t\u0019$!\u000e1\t\u0005-\u0012q\u0006\t\u0005\t\u0002\ti\u0003E\u0002L\u0003_!!\"!\r\t\u0003\u0003\u0005\tQ!\u0001O\u0005\ryFE\r\u0005\u0006Y\"\u0001\r!\u001c\u0005\u0006e\"\u0001\ra\u001d\u0015\u0004\u0011Yc\u0018\u0001D*bm\u0016du.\u00193Wc}\u0003\u0004cAA\u001f\u00155\taA\u0001\u0007TCZ,Gj\\1e-Fz\u0006g\u0005\u0002\u000bEQ\u0011\u00111H\u0001\u0012i\"L7OR8s[\u0006$h+\u001a:tS>tWCAA%!\u0011\tY%!\u0015\u000e\u0005\u00055#\u0002BA(\u0003;\tA\u0001\\1oO&\u0019\u00110!\u0014\u0002%QD\u0017n\u001d$pe6\fGOV3sg&|g\u000eI\u0001\u000ei\"L7o\u00117bgNt\u0015-\\3\u0002\u001dQD\u0017n]\"mCN\u001ch*Y7fAQ)\u0001.a\u0017\u0002j!9\u0011Q\f\tA\u0002\u0005}\u0013!B7pI\u0016d\u0007\u0007BA1\u0003K\u0002B\u0001\u0012\u0001\u0002dA\u00191*!\u001a\u0005\u0017\u0005\u001d\u00141LA\u0001\u0002\u0003\u0015\tA\u0014\u0002\u0004?\u0012\u001a\u0004\"\u0002:\u0011\u0001\u0004\u0019HCBA7\u0003o\nI\b\r\u0003\u0002p\u0005M\u0004\u0003\u0002#\u0001\u0003c\u00022aSA:\t)\t)(EA\u0001\u0002\u0003\u0015\tA\u0014\u0002\u0004?\u0012\"\u0004\"\u00027\u0012\u0001\u0004i\u0007\"\u0002:\u0012\u0001\u0004\u0019\u0018\u0001\u00037pC\u0012LU\u000e\u001d7\u0016\t\u0005}\u0014q\u0011\u000b\u0007\u0003\u0003\u000bI*a/\u0015\t\u0005\r\u0015\u0011\u0012\t\u0005\t\u0002\t)\tE\u0002L\u0003\u000f#Q!\u0014\nC\u00029C\u0011\"a#\u0013\u0003\u0003\u0005\u001d!!$\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0004\u0002\u0010\u0006U\u0015QQ\u0007\u0003\u0003#S1!a%%\u0003\u001d\u0011XM\u001a7fGRLA!a&\u0002\u0012\nA1\t\\1tgR\u000bw\r\u0003\u0004;%\u0001\u0007\u00111\u0014\t\u0005\u0003;\u000b)L\u0004\u0003\u0002 \u0006Ef\u0002BAQ\u0003[sA!a)\u0002,:!\u0011QUAU\u001d\r\t\u0014qU\u0005\u0002=%\u0011A$H\u0005\u00035mI1!a,\u001a\u0003\r\u0019\u0018\u000f\\\u0005\u0004m\u0005M&bAAX3%!\u0011qWA]\u0005%!\u0015\r^1Ge\u0006lWMC\u00027\u0003gCq!!0\u0013\u0001\u0004\t))\u0001\u0004tC6\u0004H.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0007\u0004B!a\u0013\u0002F&!\u0011qYA'\u0005\u0019y%M[3di\"\u001aaA\u0016?)\u0007\u00151F\u0010"
)
public class PrefixSpanModel implements Saveable, Serializable {
   private final RDD freqSequences;

   public static PrefixSpanModel load(final SparkContext sc, final String path) {
      return PrefixSpanModel$.MODULE$.load(sc, path);
   }

   public RDD freqSequences() {
      return this.freqSequences;
   }

   public void save(final SparkContext sc, final String path) {
      PrefixSpanModel.SaveLoadV1_0$.MODULE$.save(this, path);
   }

   public PrefixSpanModel(final RDD freqSequences) {
      this.freqSequences = freqSequences;
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.fpm.PrefixSpanModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      private String thisClassName() {
         return thisClassName;
      }

      public void save(final PrefixSpanModel model, final String path) {
         SparkContext sc = model.freqSequences().sparkContext();
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         Object sample = scala.runtime.ScalaRunTime..MODULE$.array_apply(((PrefixSpan.FreqSequence)model.freqSequences().first()).sequence()[0], 0);
         String className = sample.getClass().getCanonicalName();
         Symbols.ClassSymbolApi classSymbol = ((Mirror)scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader())).staticClass(className);
         Types.TypeApi tpe = classSymbol.selfType();
         DataType itemType = org.apache.spark.sql.catalyst.ScalaReflection..MODULE$.schemaFor(tpe).dataType();
         StructField[] fields = (StructField[])((Object[])(new StructField[]{new StructField("sequence", org.apache.spark.sql.types.ArrayType..MODULE$.apply(org.apache.spark.sql.types.ArrayType..MODULE$.apply(itemType)), org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("freq", org.apache.spark.sql.types.LongType..MODULE$, org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())}));
         StructType schema = new StructType(fields);
         RDD rowDataRDD = model.freqSequences().map((x) -> org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x.sequence(), BoxesRunTime.boxToLong(x.freq())})), scala.reflect.ClassTag..MODULE$.apply(Row.class));
         spark.createDataFrame(rowDataRDD, schema).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public PrefixSpanModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            String formatVersion;
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
                  JValue var13 = (JValue)var6._3();
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
            Dataset freqSequences = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Object sample = ((Row)freqSequences.select("sequence", scala.collection.immutable.Nil..MODULE$).head()).get(0);
            return this.loadImpl(freqSequences, sample, scala.reflect.ClassTag..MODULE$.Any());
         }
      }

      public PrefixSpanModel loadImpl(final Dataset freqSequences, final Object sample, final ClassTag evidence$4) {
         RDD freqSequencesRDD = freqSequences.select("sequence", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"freq"}))).rdd().map((x) -> {
            Object[] sequence = ((IterableOnceOps)x.getSeq(0).map((x$12) -> x$12.toArray(evidence$4))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(evidence$4.runtimeClass())));
            long freq = x.getLong(1);
            return new PrefixSpan.FreqSequence(sequence, freq);
         }, scala.reflect.ClassTag..MODULE$.apply(PrefixSpan.FreqSequence.class));
         return new PrefixSpanModel(freqSequencesRDD);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

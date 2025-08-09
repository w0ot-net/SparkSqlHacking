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
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Predef;
import scala.Tuple1;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
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
   bytes = "\u0006\u0005\t=b\u0001B\r\u001b\u0001\u0015B\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\tY\u0002\u0011\t\u0011)A\u0005\u0003\"Aa\u000e\u0001BC\u0002\u0013\u0005q\u000e\u0003\u0005\u007f\u0001\t\u0005\t\u0015!\u0003q\u0011)\t\t\u0001\u0001B\u0002B\u0003-\u00111\u0001\u0005\b\u0003\u001f\u0001A\u0011AA\t\u0011\u001d\ty\u0001\u0001C\u0001\u0003KAq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u0002P\u0001!\t%!\u0015\b\u000f\u0005]$\u0004#\u0001\u0002z\u00191\u0011D\u0007E\u0001\u0003wBq!a\u0004\f\t\u0003\tY\nC\u0004\u0002\u001e.!\t%a(\b\u0011\u0005E6\u0002#\u0001\u001b\u0003g3\u0001\"a.\f\u0011\u0003Q\u0012\u0011\u0018\u0005\b\u0003\u001fyA\u0011AA^\u0011%\til\u0004b\u0001\n\u0013\ty\f\u0003\u0005\u0002L>\u0001\u000b\u0011BAa\u0011%\tim\u0004b\u0001\n\u0013\ty\f\u0003\u0005\u0002P>\u0001\u000b\u0011BAa\u0011\u001d\tye\u0004C\u0001\u0003#Dq!!(\u0010\t\u0003\t\u0019\u000fC\u0004\u0002t>!\t!!>\t\u0013\t\u00052\"!A\u0005\n\t\r\"!\u0004$Q\u000fJ|w\u000f\u001e5N_\u0012,GN\u0003\u0002\u001c9\u0005\u0019a\r]7\u000b\u0005uq\u0012!B7mY&\u0014'BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001U\u0011aEW\n\u0005\u0001\u001dj3\u0007\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VM\u001a\t\u0003]Ej\u0011a\f\u0006\u0003aq\tA!\u001e;jY&\u0011!g\f\u0002\t'\u00064X-\u00192mKB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0013BA\u001e*\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mJ\u0013\u0001\u00044sKFLE/Z7tKR\u001cX#A!\u0011\u0007\t+u)D\u0001D\u0015\t!e$A\u0002sI\u0012L!AR\"\u0003\u0007I#E\tE\u0002I+bs!!S*\u000f\u0005)\u0013fBA&R\u001d\ta\u0005K\u0004\u0002N\u001f:\u0011aGT\u0005\u0002G%\u0011\u0011EI\u0005\u0003?\u0001J!!\b\u0010\n\u0005ma\u0012B\u0001+\u001b\u0003!1\u0005k\u0012:poRD\u0017B\u0001,X\u0005-1%/Z9Ji\u0016l7/\u001a;\u000b\u0005QS\u0002CA-[\u0019\u0001!Qa\u0017\u0001C\u0002q\u0013A!\u0013;f[F\u0011Q\f\u0019\t\u0003QyK!aX\u0015\u0003\u000f9{G\u000f[5oOB\u0011\u0001&Y\u0005\u0003E&\u00121!\u00118zQ\r\tAM\u001b\t\u0003K\"l\u0011A\u001a\u0006\u0003Oz\t!\"\u00198o_R\fG/[8o\u0013\tIgMA\u0003TS:\u001cW-I\u0001l\u0003\u0015\tdf\r\u00181\u000351'/Z9Ji\u0016l7/\u001a;tA!\u001a!\u0001\u001a6\u0002\u0017%$X-\\*vaB|'\u000f^\u000b\u0002aB!\u0011/\u001e-y\u001d\t\u00118\u000f\u0005\u00027S%\u0011A/K\u0001\u0007!J,G-\u001a4\n\u0005Y<(aA'ba*\u0011A/\u000b\t\u0003QeL!A_\u0015\u0003\r\u0011{WO\u00197fQ\r\u0019A\r`\u0011\u0002{\u0006)!G\f\u001b/a\u0005a\u0011\u000e^3n'V\u0004\bo\u001c:uA!\u001aA\u0001\u001a?\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003\u0002\u0006\u0005-\u0001,\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u0015\u0002\u000fI,g\r\\3di&!\u0011QBA\u0004\u0005!\u0019E.Y:t)\u0006<\u0017A\u0002\u001fj]&$h\b\u0006\u0004\u0002\u0014\u0005m\u0011q\u0004\u000b\u0005\u0003+\tI\u0002\u0005\u0003\u0002\u0018\u0001AV\"\u0001\u000e\t\u000f\u0005\u0005a\u0001q\u0001\u0002\u0004!)qH\u0002a\u0001\u0003\"\"\u00111\u00043k\u0011\u0015qg\u00011\u0001qQ\u0011\ty\u0002\u001a?)\u0007\u0019!G\u0010\u0006\u0003\u0002(\u00055B\u0003BA\u000b\u0003SA\u0011\"a\u000b\b\u0003\u0003\u0005\u001d!a\u0001\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007C\u0003@\u000f\u0001\u0007\u0011\tK\u0002\bI*\f\u0001dZ3oKJ\fG/Z!tg>\u001c\u0017.\u0019;j_:\u0014V\u000f\\3t)\u0011\t)$!\u0012\u0011\t\t+\u0015q\u0007\t\u0006\u0003s\ty\u0004\u0017\b\u0005\u0003/\tY$C\u0002\u0002>i\t\u0001#Q:t_\u000eL\u0017\r^5p]J+H.Z:\n\t\u0005\u0005\u00131\t\u0002\u0005%VdWMC\u0002\u0002>iAa!a\u0012\t\u0001\u0004A\u0018AC2p]\u001aLG-\u001a8dK\"\"\u0001\u0002ZA&C\t\ti%A\u00032]Ur\u0003'\u0001\u0003tCZ,GCBA*\u00033\n)\u0007E\u0002)\u0003+J1!a\u0016*\u0005\u0011)f.\u001b;\t\u000f\u0005m\u0013\u00021\u0001\u0002^\u0005\u00111o\u0019\t\u0005\u0003?\n\t'D\u0001\u001f\u0013\r\t\u0019G\b\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\b\u0003OJ\u0001\u0019AA5\u0003\u0011\u0001\u0018\r\u001e5\u0011\u0007E\fY'C\u0002\u0002n]\u0014aa\u0015;sS:<\u0007\u0006B\u0005e\u0003c\n#!a\u001d\u0002\u000bIr\u0003G\f\u0019)\u0007\u0001!'.A\u0007G!\u001e\u0013xn\u001e;i\u001b>$W\r\u001c\t\u0004\u0003/Y1CB\u0006(\u0003{\ni\tE\u0003/\u0003\u007f\n\u0019)C\u0002\u0002\u0002>\u0012a\u0001T8bI\u0016\u0014\b\u0007BAC\u0003\u0013\u0003R!a\u0006\u0001\u0003\u000f\u00032!WAE\t)\tYiCA\u0001\u0002\u0003\u0015\t\u0001\u0018\u0002\u0004?\u0012\n\u0004\u0003BAH\u00033k!!!%\u000b\t\u0005M\u0015QS\u0001\u0003S>T!!a&\u0002\t)\fg/Y\u0005\u0004{\u0005EECAA=\u0003\u0011aw.\u00193\u0015\r\u0005\u0005\u00161VAWa\u0011\t\u0019+a*\u0011\u000b\u0005]\u0001!!*\u0011\u0007e\u000b9\u000b\u0002\u0006\u0002*6\t\t\u0011!A\u0003\u0002q\u00131a\u0018\u00133\u0011\u001d\tY&\u0004a\u0001\u0003;Bq!a\u001a\u000e\u0001\u0004\tI\u0007\u000b\u0003\u000eI\u0006E\u0014\u0001D*bm\u0016du.\u00193Wc}\u0003\u0004cAA[\u001f5\t1B\u0001\u0007TCZ,Gj\\1e-Fz\u0006g\u0005\u0002\u0010OQ\u0011\u00111W\u0001\u0012i\"L7OR8s[\u0006$h+\u001a:tS>tWCAAa!\u0011\t\u0019-!3\u000e\u0005\u0005\u0015'\u0002BAd\u0003+\u000bA\u0001\\1oO&!\u0011QNAc\u0003I!\b.[:G_Jl\u0017\r\u001e,feNLwN\u001c\u0011\u0002\u001bQD\u0017n]\"mCN\u001ch*Y7f\u00039!\b.[:DY\u0006\u001c8OT1nK\u0002\"b!a\u0015\u0002T\u0006\u0005\bbBAk+\u0001\u0007\u0011q[\u0001\u0006[>$W\r\u001c\u0019\u0005\u00033\fi\u000eE\u0003\u0002\u0018\u0001\tY\u000eE\u0002Z\u0003;$1\"a8\u0002T\u0006\u0005\t\u0011!B\u00019\n\u0019q\fJ\u001a\t\u000f\u0005\u001dT\u00031\u0001\u0002jQ1\u0011Q]Ax\u0003c\u0004D!a:\u0002lB)\u0011q\u0003\u0001\u0002jB\u0019\u0011,a;\u0005\u0015\u00055h#!A\u0001\u0002\u000b\u0005ALA\u0002`IQBq!a\u0017\u0017\u0001\u0004\ti\u0006C\u0004\u0002hY\u0001\r!!\u001b\u0002\u00111|\u0017\rZ%na2,B!a>\u0002\u0000R1\u0011\u0011 B\u0004\u0005;!B!a?\u0003\u0002A)\u0011q\u0003\u0001\u0002~B\u0019\u0011,a@\u0005\u000bm;\"\u0019\u0001/\t\u0013\t\rq#!AA\u0004\t\u0015\u0011AC3wS\u0012,gnY3%gA1\u0011QAA\u0006\u0003{DaaP\fA\u0002\t%\u0001\u0003\u0002B\u0006\u0005/qAA!\u0004\u0003\u00149\u00191Ja\u0004\n\u0007\tEa$A\u0002tc2L1a\u000fB\u000b\u0015\r\u0011\tBH\u0005\u0005\u00053\u0011YBA\u0005ECR\fgI]1nK*\u00191H!\u0006\t\u000f\t}q\u00031\u0001\u0002~\u000611/Y7qY\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\n\u0011\t\u0005\r'qE\u0005\u0005\u0005S\t)M\u0001\u0004PE*,7\r\u001e\u0015\u0005\u0017\u0011\f\t\b\u000b\u0003\u000bI\u0006E\u0004"
)
public class FPGrowthModel implements Saveable, Serializable {
   private final RDD freqItemsets;
   private final Map itemSupport;
   private final ClassTag evidence$1;

   public static FPGrowthModel load(final SparkContext sc, final String path) {
      return FPGrowthModel$.MODULE$.load(sc, path);
   }

   public RDD freqItemsets() {
      return this.freqItemsets;
   }

   public Map itemSupport() {
      return this.itemSupport;
   }

   public RDD generateAssociationRules(final double confidence) {
      AssociationRules associationRules = new AssociationRules(confidence);
      return associationRules.run(this.freqItemsets(), this.itemSupport(), this.evidence$1);
   }

   public void save(final SparkContext sc, final String path) {
      FPGrowthModel.SaveLoadV1_0$.MODULE$.save(this, path);
   }

   public FPGrowthModel(final RDD freqItemsets, final Map itemSupport, final ClassTag evidence$1) {
      this.freqItemsets = freqItemsets;
      this.itemSupport = itemSupport;
      this.evidence$1 = evidence$1;
   }

   public FPGrowthModel(final RDD freqItemsets, final ClassTag evidence$2) {
      this(freqItemsets, .MODULE$.Map().empty(), evidence$2);
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.fpm.FPGrowthModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      private String thisClassName() {
         return thisClassName;
      }

      public void save(final FPGrowthModel model, final String path) {
         SparkContext sc = model.freqItemsets().sparkContext();
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         Object sample = scala.runtime.ScalaRunTime..MODULE$.array_apply(((FPGrowth.FreqItemset)model.freqItemsets().first()).items(), 0);
         String className = sample.getClass().getCanonicalName();
         Symbols.ClassSymbolApi classSymbol = ((Mirror)scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader())).staticClass(className);
         Types.TypeApi tpe = classSymbol.selfType();
         DataType itemType = org.apache.spark.sql.catalyst.ScalaReflection..MODULE$.schemaFor(tpe).dataType();
         StructField[] fields = (StructField[])((Object[])(new StructField[]{new StructField("items", org.apache.spark.sql.types.ArrayType..MODULE$.apply(itemType), org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("freq", org.apache.spark.sql.types.LongType..MODULE$, org.apache.spark.sql.types.StructField..MODULE$.apply$default$3(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())}));
         StructType schema = new StructType(fields);
         RDD rowDataRDD = model.freqItemsets().map((x) -> org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x.items()).toImmutableArraySeq(), BoxesRunTime.boxToLong(x.freq())})), scala.reflect.ClassTag..MODULE$.apply(Row.class));
         spark.createDataFrame(rowDataRDD, schema).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public FPGrowthModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
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
                  var10000 = .MODULE$;
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
                  var10000 = .MODULE$;
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
            Dataset freqItemsets = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Object sample = ((Row)freqItemsets.select("items", scala.collection.immutable.Nil..MODULE$).head()).get(0);
            return this.loadImpl(freqItemsets, sample, scala.reflect.ClassTag..MODULE$.Any());
         }
      }

      public FPGrowthModel loadImpl(final Dataset freqItemsets, final Object sample, final ClassTag evidence$3) {
         RDD freqItemsetsRDD = freqItemsets.select("items", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"freq"}))).rdd().map((x) -> {
            Object items = ((IterableOnceOps)x.getAs(0)).toArray(evidence$3);
            long freq = x.getLong(1);
            return new FPGrowth.FreqItemset(items, freq);
         }, scala.reflect.ClassTag..MODULE$.apply(FPGrowth.FreqItemset.class));
         return new FPGrowthModel(freqItemsetsRDD, evidence$3);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

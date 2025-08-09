package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.ml.recommendation.ALSModel$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json4s.Formats;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.JsonAssoc.;
import scala.Tuple1;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e!\u0002\u000e\u001c\u0001m)\u0003\u0002\u0003\u001a\u0001\u0005\u000b\u0007I\u0011\u0001\u001b\t\u0011m\u0002!\u0011!Q\u0001\nUB\u0001\u0002\u0010\u0001\u0003\u0006\u0004%\t!\u0010\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005}!)!\n\u0001C\u0005\u0017\"A\u0001\u000b\u0001EC\u0002\u0013\u0005Q\b\u0003\u0005R\u0001!\u0015\r\u0011\"\u0001>\u0011!\u0011\u0006\u0001#b\u0001\n\u0003\u0019\u0006\u0002C3\u0001\u0011\u000b\u0007I\u0011A*\t\u0011\u0019\u0004\u0001R1A\u0005\u0002\u001dDQa\u001b\u0001\u0005\u00021Daa \u0001\u0005B\u0005\u0005q\u0001CA\u00057!\u00051$a\u0003\u0007\u000fiY\u0002\u0012A\u000e\u0002\u000e!1!J\u0004C\u0001\u0003+Aq!a\u0006\u000f\t\u0003\tI\u0002C\u0004\u0002X9!\t%!\u0017\t\u000f\u0005\u0005d\u0002\"\u0011\u0002d\u00191\u0011\u0011\u000e\b\u0001\u0003WB\u0011\"!\u001c\u0014\u0005\u0003\u0005\u000b\u0011\u0002'\t\r)\u001bB\u0011AA8\u0011\u001d\t9h\u0005C)\u0003s2a!a!\u000f\u0001\u0005\u0015\u0005B\u0002&\u0018\t\u0003\t9\tC\u0004\u0002b]!\t%a#\u0003\u0015\u0005c5k\u0016:baB,'O\u0003\u0002\u001d;\u0005\t!O\u0003\u0002\u001f?\u0005\u0011Q\u000e\u001c\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sON\u0019\u0001A\n\u0017\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\ti\u0003'D\u0001/\u0015\tyS$\u0001\u0003vi&d\u0017BA\u0019/\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\tC2\u001cXj\u001c3fY\u000e\u0001Q#A\u001b\u0011\u0005YJT\"A\u001c\u000b\u0005aj\u0012A\u0004:fG>lW.\u001a8eCRLwN\\\u0005\u0003u]\u0012\u0001\"\u0011'T\u001b>$W\r\\\u0001\nC2\u001cXj\u001c3fY\u0002\n\u0011B]1uS:<7i\u001c7\u0016\u0003y\u0002\"a\u0010$\u000f\u0005\u0001#\u0005CA!)\u001b\u0005\u0011%BA\"4\u0003\u0019a$o\\8u}%\u0011Q\tK\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%AB*ue&twM\u0003\u0002FQ\u0005Q!/\u0019;j]\u001e\u001cu\u000e\u001c\u0011\u0002\rqJg.\u001b;?)\raej\u0014\t\u0003\u001b\u0002i\u0011a\u0007\u0005\u0006e\u0015\u0001\r!\u000e\u0005\u0006y\u0015\u0001\rAP\u0001\bkN,'oQ8m\u0003\u001dIG/Z7D_2\f1\"^:fe\u001a\u000b7\r^8sgV\tA\u000b\u0005\u0002VE:\u0011ak\u0018\b\u0003/vs!\u0001\u0017/\u000f\u0005e[fBA![\u0013\u0005!\u0013B\u0001\u0012$\u0013\t\u0001\u0013%\u0003\u0002_?\u0005\u00191/\u001d7\n\u0005\u0001\f\u0017a\u00029bG.\fw-\u001a\u0006\u0003=~I!a\u00193\u0003\u0013\u0011\u000bG/\u0019$sC6,'B\u00011b\u0003-IG/Z7GC\u000e$xN]:\u0002\tI\fgn[\u000b\u0002QB\u0011q%[\u0005\u0003U\"\u00121!\u00138u\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0002U[\")an\u0003a\u0001_\u00069A-\u0019;bg\u0016$\bG\u00019w!\r\t(\u000f^\u0007\u0002C&\u00111/\u0019\u0002\b\t\u0006$\u0018m]3u!\t)h\u000f\u0004\u0001\u0005\u0013]l\u0017\u0011!A\u0001\u0006\u0003A(aA0%cE\u0011\u0011\u0010 \t\u0003OiL!a\u001f\u0015\u0003\u000f9{G\u000f[5oOB\u0011q%`\u0005\u0003}\"\u00121!\u00118z\u0003\u00159(/\u001b;f+\t\t\u0019\u0001E\u0002.\u0003\u000bI1!a\u0002/\u0005!iEj\u0016:ji\u0016\u0014\u0018AC!M'^\u0013\u0018\r\u001d9feB\u0011QJD\n\u0005\u001d\u0019\ny\u0001\u0005\u0003.\u0003#a\u0015bAA\n]\tQQ\n\u0014*fC\u0012\f'\r\\3\u0015\u0005\u0005-\u0011a\u00014jiRiB*a\u0007\u0002 \u0005\u0005\u00121EA\u0013\u0003O\t\t$!\u000e\u0002@\u0005\r\u0013qIA&\u0003\u001f\n\u0019\u0006\u0003\u0004\u0002\u001eA\u0001\r\u0001V\u0001\u0005I\u0006$\u0018\rC\u0003=!\u0001\u0007a\bC\u0003Q!\u0001\u0007a\bC\u0003R!\u0001\u0007a\bC\u0003g!\u0001\u0007\u0001\u000eC\u0004\u0002*A\u0001\r!a\u000b\u0002\u0011I,w\rU1sC6\u00042aJA\u0017\u0013\r\ty\u0003\u000b\u0002\u0007\t>,(\r\\3\t\r\u0005M\u0002\u00031\u0001i\u0003\u001di\u0017\r_%uKJDq!a\u000e\u0011\u0001\u0004\tI$A\u0007j[Bd\u0017nY5u!J,gm\u001d\t\u0004O\u0005m\u0012bAA\u001fQ\t9!i\\8mK\u0006t\u0007bBA!!\u0001\u0007\u00111F\u0001\u0006C2\u0004\b.\u0019\u0005\b\u0003\u000b\u0002\u0002\u0019AA\u001d\u0003-qwN\u001c8fO\u0006$\u0018N^3\t\r\u0005%\u0003\u00031\u0001i\u00035qW/\\+tKJ\u0014En\\2lg\"1\u0011Q\n\tA\u0002!\fQB\\;n\u0013R,WN\u00117pG.\u001c\bBBA)!\u0001\u0007\u0001.\u0001\ndQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006d\u0007BBA+!\u0001\u0007\u0001.\u0001\u0003tK\u0016$\u0017\u0001\u0002:fC\u0012,\"!a\u0017\u0011\t5\ni\u0006T\u0005\u0004\u0003?r#\u0001C'M%\u0016\fG-\u001a:\u0002\t1|\u0017\r\u001a\u000b\u0004\u0019\u0006\u0015\u0004BBA4%\u0001\u0007a(\u0001\u0003qCRD'\u0001E!M'^\u0013\u0018\r\u001d9fe^\u0013\u0018\u000e^3s'\r\u0019\u00121A\u0001\tS:\u001cH/\u00198dKR!\u0011\u0011OA;!\r\t\u0019hE\u0007\u0002\u001d!1\u0011QN\u000bA\u00021\u000b\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0003w\n\t\tE\u0002(\u0003{J1!a )\u0005\u0011)f.\u001b;\t\r\u0005\u001dd\u00031\u0001?\u0005A\tEjU,sCB\u0004XM\u001d*fC\u0012,'oE\u0002\u0018\u00037\"\"!!#\u0011\u0007\u0005Mt\u0003F\u0002M\u0003\u001bCa!a\u001a\u001a\u0001\u0004q\u0004"
)
public class ALSWrapper implements MLWritable {
   private String userCol;
   private String itemCol;
   private Dataset userFactors;
   private Dataset itemFactors;
   private int rank;
   private final ALSModel alsModel;
   private final String ratingCol;
   private volatile byte bitmap$0;

   public static ALSWrapper load(final String path) {
      return ALSWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ALSWrapper$.MODULE$.read();
   }

   public static ALSWrapper fit(final Dataset data, final String ratingCol, final String userCol, final String itemCol, final int rank, final double regParam, final int maxIter, final boolean implicitPrefs, final double alpha, final boolean nonnegative, final int numUserBlocks, final int numItemBlocks, final int checkpointInterval, final int seed) {
      return ALSWrapper$.MODULE$.fit(data, ratingCol, userCol, itemCol, rank, regParam, maxIter, implicitPrefs, alpha, nonnegative, numUserBlocks, numItemBlocks, checkpointInterval, seed);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public ALSModel alsModel() {
      return this.alsModel;
   }

   public String ratingCol() {
      return this.ratingCol;
   }

   private String userCol$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.userCol = this.alsModel().getUserCol();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.userCol;
   }

   public String userCol() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.userCol$lzycompute() : this.userCol;
   }

   private String itemCol$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.itemCol = this.alsModel().getItemCol();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.itemCol;
   }

   public String itemCol() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.itemCol$lzycompute() : this.itemCol;
   }

   private Dataset userFactors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.userFactors = this.alsModel().userFactors();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.userFactors;
   }

   public Dataset userFactors() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.userFactors$lzycompute() : this.userFactors;
   }

   private Dataset itemFactors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.itemFactors = this.alsModel().itemFactors();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.itemFactors;
   }

   public Dataset itemFactors() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.itemFactors$lzycompute() : this.itemFactors;
   }

   private int rank$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.rank = this.alsModel().rank();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rank;
   }

   public int rank() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.rank$lzycompute() : this.rank;
   }

   public Dataset transform(final Dataset dataset) {
      return this.alsModel().transform(dataset);
   }

   public MLWriter write() {
      return new ALSWrapperWriter(this);
   }

   public ALSWrapper(final ALSModel alsModel, final String ratingCol) {
      this.alsModel = alsModel;
      this.ratingCol = ratingCol;
      MLWritable.$init$(this);
   }

   public static class ALSWrapperWriter extends MLWriter {
      private final ALSWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String modelPath = (new Path(path, "model")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("ratingCol"), this.instance.ratingCol()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ALSWrapperWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(rMetadataPath);
         this.instance.alsModel().save(modelPath);
      }

      public ALSWrapperWriter(final ALSWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class ALSWrapperReader extends MLReader {
      public ALSWrapper load(final String path) {
         Formats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String modelPath = (new Path(path, "model")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String ratingCol = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "ratingCol")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         ALSModel alsModel = ALSModel$.MODULE$.load(modelPath);
         return new ALSWrapper(alsModel, ratingCol);
      }
   }
}

package org.apache.spark.mllib.regression.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.json4s.JValue;
import scala.MatchError;
import scala.Some;
import scala.Tuple1;
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
   bytes = "\u0006\u0005\u0005]wA\u0002\u0012$\u0011\u0003)sF\u0002\u00042G!\u0005QE\r\u0005\u0006s\u0005!\taO\u0004\u0006y\u0005A\t!\u0010\u0004\u0006\u007f\u0005A\t\u0001\u0011\u0005\u0006s\u0011!\t!\u0011\u0005\u0006\u0005\u0012!\ta\u0011\u0004\u0005\u001f\u0012\u0001\u0005\u000b\u0003\u0005^\u000f\tU\r\u0011\"\u0001_\u0011!)wA!E!\u0002\u0013y\u0006\u0002\u00034\b\u0005+\u0007I\u0011A4\t\u0011-<!\u0011#Q\u0001\n!DQ!O\u0004\u0005\u00021Dq!]\u0004\u0002\u0002\u0013\u0005!\u000fC\u0004v\u000fE\u0005I\u0011\u0001<\t\u0013\u0005\rq!%A\u0005\u0002\u0005\u0015\u0001\"CA\u0005\u000f\u0005\u0005I\u0011IA\u0006\u0011%\tYbBA\u0001\n\u0003\ti\u0002C\u0005\u0002&\u001d\t\t\u0011\"\u0001\u0002(!I\u00111G\u0004\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003\u0007:\u0011\u0011!C\u0001\u0003\u000bB\u0011\"a\u0014\b\u0003\u0003%\t%!\u0015\t\u0013\u0005Us!!A\u0005B\u0005]\u0003\"CA-\u000f\u0005\u0005I\u0011IA.\u0011%\tifBA\u0001\n\u0003\nyfB\u0005\u0002d\u0011\t\t\u0011#\u0001\u0002f\u0019Aq\nBA\u0001\u0012\u0003\t9\u0007\u0003\u0004:5\u0011\u0005\u0011q\u0010\u0005\n\u00033R\u0012\u0011!C#\u00037B\u0011\"!!\u001b\u0003\u0003%\t)a!\t\u0013\u0005%%$!A\u0005\u0002\u0006-\u0005\"CAO5\u0005\u0005I\u0011BAP\u0011\u001d\t9\u000b\u0002C\u0001\u0003SCq!!3\u0005\t\u0003\tY-\u0001\nH\u00196\u0013Vm\u001a:fgNLwN\\'pI\u0016d'B\u0001\u0013&\u0003\u0011IW\u000e\u001d7\u000b\u0005\u0019:\u0013A\u0003:fOJ,7o]5p]*\u0011\u0001&K\u0001\u0006[2d\u0017N\u0019\u0006\u0003U-\nQa\u001d9be.T!\u0001L\u0017\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0013aA8sOB\u0011\u0001'A\u0007\u0002G\t\u0011r\tT'SK\u001e\u0014Xm]:j_:lu\u000eZ3m'\t\t1\u0007\u0005\u00025o5\tQGC\u00017\u0003\u0015\u00198-\u00197b\u0013\tATG\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq&\u0001\u0007TCZ,Gj\\1e-Fz\u0006\u0007\u0005\u0002?\t5\t\u0011A\u0001\u0007TCZ,Gj\\1e-Fz\u0006g\u0005\u0002\u0005gQ\tQ(A\tuQ&\u001chi\u001c:nCR4VM]:j_:,\u0012\u0001\u0012\t\u0003\u000b2s!A\u0012&\u0011\u0005\u001d+T\"\u0001%\u000b\u0005%S\u0014A\u0002\u001fs_>$h(\u0003\u0002Lk\u00051\u0001K]3eK\u001aL!!\u0014(\u0003\rM#(/\u001b8h\u0015\tYUG\u0001\u0003ECR\f7\u0003B\u00044#R\u0003\"\u0001\u000e*\n\u0005M+$a\u0002)s_\u0012,8\r\u001e\t\u0003+js!A\u0016-\u000f\u0005\u001d;\u0016\"\u0001\u001c\n\u0005e+\u0014a\u00029bG.\fw-Z\u0005\u00037r\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!W\u001b\u0002\u000f],\u0017n\u001a5ugV\tq\f\u0005\u0002aG6\t\u0011M\u0003\u0002cO\u00051A.\u001b8bY\u001eL!\u0001Z1\u0003\rY+7\r^8s\u0003!9X-[4iiN\u0004\u0013!C5oi\u0016\u00148-\u001a9u+\u0005A\u0007C\u0001\u001bj\u0013\tQWG\u0001\u0004E_V\u0014G.Z\u0001\u000bS:$XM]2faR\u0004CcA7paB\u0011anB\u0007\u0002\t!)Q\f\u0004a\u0001?\")a\r\u0004a\u0001Q\u0006!1m\u001c9z)\ri7\u000f\u001e\u0005\b;6\u0001\n\u00111\u0001`\u0011\u001d1W\u0002%AA\u0002!\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001xU\ty\u0006pK\u0001z!\tQx0D\u0001|\u0015\taX0A\u0005v]\u000eDWmY6fI*\u0011a0N\u0001\u000bC:tw\u000e^1uS>t\u0017bAA\u0001w\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0001\u0016\u0003Qb\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0007!\u0011\ty!!\u0007\u000e\u0005\u0005E!\u0002BA\n\u0003+\tA\u0001\\1oO*\u0011\u0011qC\u0001\u0005U\u00064\u0018-C\u0002N\u0003#\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\b\u0011\u0007Q\n\t#C\u0002\u0002$U\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u000b\u00020A\u0019A'a\u000b\n\u0007\u00055RGA\u0002B]fD\u0011\"!\r\u0013\u0003\u0003\u0005\r!a\b\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\u0004\u0005\u0004\u0002:\u0005}\u0012\u0011F\u0007\u0003\u0003wQ1!!\u00106\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0003\nYD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA$\u0003\u001b\u00022\u0001NA%\u0013\r\tY%\u000e\u0002\b\u0005>|G.Z1o\u0011%\t\t\u0004FA\u0001\u0002\u0004\tI#\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0007\u0003'B\u0011\"!\r\u0016\u0003\u0003\u0005\r!a\b\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\b\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0004\u0002\r\u0015\fX/\u00197t)\u0011\t9%!\u0019\t\u0013\u0005E\u0002$!AA\u0002\u0005%\u0012\u0001\u0002#bi\u0006\u0004\"A\u001c\u000e\u0014\u000bi\tI'!\u001e\u0011\u000f\u0005-\u0014\u0011O0i[6\u0011\u0011Q\u000e\u0006\u0004\u0003_*\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003g\niGA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u001e\u0002~5\u0011\u0011\u0011\u0010\u0006\u0005\u0003w\n)\"\u0001\u0002j_&\u00191,!\u001f\u0015\u0005\u0005\u0015\u0014!B1qa2LH#B7\u0002\u0006\u0006\u001d\u0005\"B/\u001e\u0001\u0004y\u0006\"\u00024\u001e\u0001\u0004A\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u001b\u000bI\nE\u00035\u0003\u001f\u000b\u0019*C\u0002\u0002\u0012V\u0012aa\u00149uS>t\u0007#\u0002\u001b\u0002\u0016~C\u0017bAALk\t1A+\u001e9mKJB\u0001\"a'\u001f\u0003\u0003\u0005\r!\\\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAQ!\u0011\ty!a)\n\t\u0005\u0015\u0016\u0011\u0003\u0002\u0007\u001f\nTWm\u0019;\u0002\tM\fg/\u001a\u000b\r\u0003W\u000b\t,!0\u0002B\u0006\u0015\u0017q\u0019\t\u0004i\u00055\u0016bAAXk\t!QK\\5u\u0011\u001d\t\u0019\f\ta\u0001\u0003k\u000b!a]2\u0011\t\u0005]\u0016\u0011X\u0007\u0002S%\u0019\u00111X\u0015\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\r\u0005}\u0006\u00051\u0001E\u0003\u0011\u0001\u0018\r\u001e5\t\r\u0005\r\u0007\u00051\u0001E\u0003)iw\u000eZ3m\u00072\f7o\u001d\u0005\u0006;\u0002\u0002\ra\u0018\u0005\u0006M\u0002\u0002\r\u0001[\u0001\tY>\fG\rR1uCRIQ.!4\u0002P\u0006E\u00171\u001b\u0005\b\u0003g\u000b\u0003\u0019AA[\u0011\u0019\ty,\ta\u0001\t\"1\u00111Y\u0011A\u0002\u0011Cq!!6\"\u0001\u0004\ty\"A\u0006ok64U-\u0019;ve\u0016\u001c\b"
)
public final class GLMRegressionModel {
   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisFormatVersion() {
         return "1.0";
      }

      public void save(final SparkContext sc, final String path, final String modelClass, final Vector weights, final double intercept) {
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), modelClass), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(weights.size())), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         GLMRegressionModel$SaveLoadV1_0$Data data = new GLMRegressionModel$SaveLoadV1_0$Data(weights, intercept);
         var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.regression.impl.GLMRegressionModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public GLMRegressionModel$SaveLoadV1_0$Data loadData(final SparkContext sc, final String path, final String modelClass, final int numFeatures) {
         String dataPath = Loader$.MODULE$.dataPath(path);
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataRDD = spark.read().parquet(dataPath);
         Row[] dataArray = (Row[])dataRDD.select("weights", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"intercept"}))).take(1);
         scala.Predef..MODULE$.assert(dataArray.length == 1, () -> "Unable to load " + modelClass + " data from: " + dataPath);
         Row data = dataArray[0];
         scala.Predef..MODULE$.assert(data.size() == 2, () -> "Unable to load " + modelClass + " data from: " + dataPath);
         if (data != null) {
            Some var12 = org.apache.spark.sql.Row..MODULE$.unapplySeq(data);
            if (!var12.isEmpty() && var12.get() != null && ((SeqOps)var12.get()).lengthCompare(2) == 0) {
               Object weights = ((SeqOps)var12.get()).apply(0);
               Object intercept = ((SeqOps)var12.get()).apply(1);
               if (weights instanceof Vector) {
                  Vector var15 = (Vector)weights;
                  if (intercept instanceof Double) {
                     double var16 = BoxesRunTime.unboxToDouble(intercept);
                     scala.Predef..MODULE$.assert(var15.size() == numFeatures, () -> "Expected " + numFeatures + " features, but found " + var15.size() + " features when loading " + modelClass + " weights from " + dataPath);
                     return new GLMRegressionModel$SaveLoadV1_0$Data(var15, var16);
                  }
               }
            }
         }

         throw new MatchError(data);
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

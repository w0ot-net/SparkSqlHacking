package org.apache.spark.mllib.classification.impl;

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
import scala.Option;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
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
   bytes = "\u0006\u0005\u0005]xAB\u0013'\u0011\u0003A#G\u0002\u00045M!\u0005\u0001&\u000e\u0005\u0006y\u0005!\tAP\u0004\u0006\u007f\u0005A\t\u0001\u0011\u0004\u0006\u0005\u0006A\ta\u0011\u0005\u0006y\u0011!\t\u0001\u0012\u0005\u0006\u000b\u0012!\tA\u0012\u0004\u0005%\u0012\u00015\u000b\u0003\u0005a\u000f\tU\r\u0011\"\u0001b\u0011!AwA!E!\u0002\u0013\u0011\u0007\u0002C5\b\u0005+\u0007I\u0011\u00016\t\u00119<!\u0011#Q\u0001\n-D\u0001b\\\u0004\u0003\u0016\u0004%\t\u0001\u001d\u0005\ti\u001e\u0011\t\u0012)A\u0005c\")Ah\u0002C\u0001k\"91pBA\u0001\n\u0003a\b\"CA\u0001\u000fE\u0005I\u0011AA\u0002\u0011%\tIbBI\u0001\n\u0003\tY\u0002C\u0005\u0002 \u001d\t\n\u0011\"\u0001\u0002\"!I\u0011QE\u0004\u0002\u0002\u0013\u0005\u0013q\u0005\u0005\n\u0003o9\u0011\u0011!C\u0001\u0003sA\u0011\"!\u0011\b\u0003\u0003%\t!a\u0011\t\u0013\u0005=s!!A\u0005B\u0005E\u0003\"CA0\u000f\u0005\u0005I\u0011AA1\u0011%\tYgBA\u0001\n\u0003\ni\u0007C\u0005\u0002r\u001d\t\t\u0011\"\u0011\u0002t!I\u0011QO\u0004\u0002\u0002\u0013\u0005\u0013q\u000f\u0005\n\u0003s:\u0011\u0011!C!\u0003w:\u0011\"a \u0005\u0003\u0003E\t!!!\u0007\u0011I#\u0011\u0011!E\u0001\u0003\u0007Ca\u0001P\u000f\u0005\u0002\u0005m\u0005\"CA;;\u0005\u0005IQIA<\u0011%\ti*HA\u0001\n\u0003\u000by\nC\u0005\u0002(v\t\t\u0011\"!\u0002*\"I\u0011qW\u000f\u0002\u0002\u0013%\u0011\u0011\u0018\u0005\b\u0003\u0003$A\u0011AAb\u0011\u001d\ti\u000f\u0002C\u0001\u0003_\fac\u0012'N\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\u0006\u0003O!\nA![7qY*\u0011\u0011FK\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tYC&A\u0003nY2L'M\u0003\u0002.]\u0005)1\u000f]1sW*\u0011q\u0006M\u0001\u0007CB\f7\r[3\u000b\u0003E\n1a\u001c:h!\t\u0019\u0014!D\u0001'\u0005Y9E*T\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d7CA\u00017!\t9$(D\u00019\u0015\u0005I\u0014!B:dC2\f\u0017BA\u001e9\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u00013\u00031\u0019\u0016M^3M_\u0006$g+M01!\t\tE!D\u0001\u0002\u00051\u0019\u0016M^3M_\u0006$g+M01'\t!a\u0007F\u0001A\u0003E!\b.[:G_Jl\u0017\r\u001e,feNLwN\\\u000b\u0002\u000fB\u0011\u0001j\u0014\b\u0003\u00136\u0003\"A\u0013\u001d\u000e\u0003-S!\u0001T\u001f\u0002\rq\u0012xn\u001c;?\u0013\tq\u0005(\u0001\u0004Qe\u0016$WMZ\u0005\u0003!F\u0013aa\u0015;sS:<'B\u0001(9\u0005\u0011!\u0015\r^1\u0014\t\u001d1Dk\u0016\t\u0003oUK!A\u0016\u001d\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001,\u0018\b\u00033ns!A\u0013.\n\u0003eJ!\u0001\u0018\u001d\u0002\u000fA\f7m[1hK&\u0011al\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u00039b\nqa^3jO\"$8/F\u0001c!\t\u0019g-D\u0001e\u0015\t)'&\u0001\u0004mS:\fGnZ\u0005\u0003O\u0012\u0014aAV3di>\u0014\u0018\u0001C<fS\u001eDGo\u001d\u0011\u0002\u0013%tG/\u001a:dKB$X#A6\u0011\u0005]b\u0017BA79\u0005\u0019!u.\u001e2mK\u0006Q\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0011\u0002\u0013QD'/Z:i_2$W#A9\u0011\u0007]\u00128.\u0003\u0002tq\t1q\n\u001d;j_:\f!\u0002\u001e5sKNDw\u000e\u001c3!)\u00111\b0\u001f>\u0011\u0005]<Q\"\u0001\u0003\t\u000b\u0001t\u0001\u0019\u00012\t\u000b%t\u0001\u0019A6\t\u000b=t\u0001\u0019A9\u0002\t\r|\u0007/\u001f\u000b\u0005mvtx\u0010C\u0004a\u001fA\u0005\t\u0019\u00012\t\u000f%|\u0001\u0013!a\u0001W\"9qn\u0004I\u0001\u0002\u0004\t\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u000bQ3AYA\u0004W\t\tI\u0001\u0005\u0003\u0002\f\u0005UQBAA\u0007\u0015\u0011\ty!!\u0005\u0002\u0013Ut7\r[3dW\u0016$'bAA\nq\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005]\u0011Q\u0002\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003;Q3a[A\u0004\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a\t+\u0007E\f9!A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003S\u0001B!a\u000b\u000265\u0011\u0011Q\u0006\u0006\u0005\u0003_\t\t$\u0001\u0003mC:<'BAA\u001a\u0003\u0011Q\u0017M^1\n\u0007A\u000bi#\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002<A\u0019q'!\u0010\n\u0007\u0005}\u0002HA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002F\u0005-\u0003cA\u001c\u0002H%\u0019\u0011\u0011\n\u001d\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002NU\t\t\u00111\u0001\u0002<\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0015\u0011\r\u0005U\u00131LA#\u001b\t\t9FC\u0002\u0002Za\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti&a\u0016\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003G\nI\u0007E\u00028\u0003KJ1!a\u001a9\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u0014\u0018\u0003\u0003\u0005\r!!\u0012\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003S\ty\u0007C\u0005\u0002Na\t\t\u00111\u0001\u0002<\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002<\u0005AAo\\*ue&tw\r\u0006\u0002\u0002*\u00051Q-];bYN$B!a\u0019\u0002~!I\u0011QJ\u000e\u0002\u0002\u0003\u0007\u0011QI\u0001\u0005\t\u0006$\u0018\r\u0005\u0002x;M)Q$!\"\u0002\u0012BA\u0011qQAGE.\fh/\u0004\u0002\u0002\n*\u0019\u00111\u0012\u001d\u0002\u000fI,h\u000e^5nK&!\u0011qRAE\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003'\u000bI*\u0004\u0002\u0002\u0016*!\u0011qSA\u0019\u0003\tIw.C\u0002_\u0003+#\"!!!\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fY\f\t+a)\u0002&\")\u0001\r\ta\u0001E\")\u0011\u000e\ta\u0001W\")q\u000e\ta\u0001c\u00069QO\\1qa2LH\u0003BAV\u0003g\u0003Ba\u000e:\u0002.B1q'a,cWFL1!!-9\u0005\u0019!V\u000f\u001d7fg!A\u0011QW\u0011\u0002\u0002\u0003\u0007a/A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a/\u0011\t\u0005-\u0012QX\u0005\u0005\u0003\u007f\u000biC\u0001\u0004PE*,7\r^\u0001\u0005g\u00064X\r\u0006\n\u0002F\u0006-\u0017q[An\u0003?\f\u0019/a:\u0002j\u0006-\bcA\u001c\u0002H&\u0019\u0011\u0011\u001a\u001d\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003\u001b\u001c\u0003\u0019AAh\u0003\t\u00198\r\u0005\u0003\u0002R\u0006MW\"\u0001\u0017\n\u0007\u0005UGF\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000f\u0003\u0004\u0002Z\u000e\u0002\raR\u0001\u0005a\u0006$\b\u000e\u0003\u0004\u0002^\u000e\u0002\raR\u0001\u000b[>$W\r\\\"mCN\u001c\bbBAqG\u0001\u0007\u00111H\u0001\f]Vlg)Z1ukJ,7\u000fC\u0004\u0002f\u000e\u0002\r!a\u000f\u0002\u00159,Xn\u00117bgN,7\u000fC\u0003aG\u0001\u0007!\rC\u0003jG\u0001\u00071\u000eC\u0003pG\u0001\u0007\u0011/\u0001\u0005m_\u0006$G)\u0019;b)\u001d1\u0018\u0011_Az\u0003kDq!!4%\u0001\u0004\ty\r\u0003\u0004\u0002Z\u0012\u0002\ra\u0012\u0005\u0007\u0003;$\u0003\u0019A$"
)
public final class GLMClassificationModel {
   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisFormatVersion() {
         return "1.0";
      }

      public void save(final SparkContext sc, final String path, final String modelClass, final int numFeatures, final int numClasses, final Vector weights, final double intercept, final Option threshold) {
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), modelClass), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(numFeatures)), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numClasses"), BoxesRunTime.boxToInteger(numClasses)), (x) -> $anonfun$save$5(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         GLMClassificationModel$SaveLoadV1_0$Data data = new GLMClassificationModel$SaveLoadV1_0$Data(weights, intercept, threshold);
         var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.classification.impl.GLMClassificationModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public GLMClassificationModel$SaveLoadV1_0$Data loadData(final SparkContext sc, final String path, final String modelClass) {
         String dataPath = Loader$.MODULE$.dataPath(path);
         SparkSession spark = .MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataRDD = spark.read().parquet(dataPath);
         Row[] dataArray = (Row[])dataRDD.select("weights", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"intercept", "threshold"}))).take(1);
         scala.Predef..MODULE$.assert(dataArray.length == 1, () -> "Unable to load " + modelClass + " data from: " + dataPath);
         Row data = dataArray[0];
         scala.Predef..MODULE$.assert(data.size() == 3, () -> "Unable to load " + modelClass + " data from: " + dataPath);
         if (data != null) {
            Some var14 = org.apache.spark.sql.Row..MODULE$.unapplySeq(data);
            if (!var14.isEmpty() && var14.get() != null && ((SeqOps)var14.get()).lengthCompare(3) == 0) {
               Object weights = ((SeqOps)var14.get()).apply(0);
               Object intercept = ((SeqOps)var14.get()).apply(1);
               if (weights instanceof Vector) {
                  Vector var17 = (Vector)weights;
                  if (intercept instanceof Double) {
                     double var18 = BoxesRunTime.unboxToDouble(intercept);
                     Tuple2 var12 = new Tuple2(var17, BoxesRunTime.boxToDouble(var18));
                     if (var12 != null) {
                        Vector weights = (Vector)var12._1();
                        double intercept = var12._2$mcD$sp();
                        Tuple2 var11 = new Tuple2(weights, BoxesRunTime.boxToDouble(intercept));
                        Vector weights = (Vector)var11._1();
                        double intercept = var11._2$mcD$sp();
                        Option threshold = (Option)(data.isNullAt(2) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToDouble(data.getDouble(2))));
                        return new GLMClassificationModel$SaveLoadV1_0$Data(weights, intercept, threshold);
                     }

                     throw new MatchError(var12);
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
      public static final JValue $anonfun$save$5(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

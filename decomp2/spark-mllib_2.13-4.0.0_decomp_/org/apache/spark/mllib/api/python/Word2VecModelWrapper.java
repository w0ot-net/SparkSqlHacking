package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD.;
import org.apache.spark.mllib.feature.Word2VecModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a!\u0002\u0006\f\u0001-9\u0002\u0002\u0003\u0010\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0011\t\u000b\u0019\u0002A\u0011A\u0014\t\u000b-\u0002A\u0011\u0001\u0017\t\u000b-\u0002A\u0011\u0001!\t\u000b-\u0003A\u0011\u0001'\t\u000b-\u0003A\u0011\u00011\t\u000b\u0011\u0004A\u0011B3\t\u000bE\u0004A\u0011\u0001:\t\u000bi\u0004A\u0011A>\u0003)]{'\u000f\u001a\u001aWK\u000elu\u000eZ3m/J\f\u0007\u000f]3s\u0015\taQ\"\u0001\u0004qsRDwN\u001c\u0006\u0003\u001d=\t1!\u00199j\u0015\t\u0001\u0012#A\u0003nY2L'M\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h'\t\u0001\u0001\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VMZ\u0001\u0006[>$W\r\\\u0002\u0001!\t\tC%D\u0001#\u0015\t\u0019s\"A\u0004gK\u0006$XO]3\n\u0005\u0015\u0012#!D,pe\u0012\u0014d+Z2N_\u0012,G.\u0001\u0004=S:LGO\u0010\u000b\u0003Q)\u0002\"!\u000b\u0001\u000e\u0003-AQA\b\u0002A\u0002\u0001\n\u0011\u0002\u001e:b]N4wN]7\u0015\u00055\u001a\u0004C\u0001\u00182\u001b\u0005y#B\u0001\u0019\u0010\u0003\u0019a\u0017N\\1mO&\u0011!g\f\u0002\u0007-\u0016\u001cGo\u001c:\t\u000bQ\u001a\u0001\u0019A\u001b\u0002\t]|'\u000f\u001a\t\u0003mur!aN\u001e\u0011\u0005aRR\"A\u001d\u000b\u0005iz\u0012A\u0002\u001fs_>$h(\u0003\u0002=5\u00051\u0001K]3eK\u001aL!AP \u0003\rM#(/\u001b8h\u0015\ta$\u0004\u0006\u0002B\u0011B\u0019!IR\u0017\u000e\u0003\rS!\u0001R#\u0002\t)\fg/\u0019\u0006\u0003\u001dEI!aR\"\u0003\u000f)\u000bg/\u0019*E\t\")\u0011\n\u0002a\u0001\u0015\u0006\u0019!\u000f\u001a3\u0011\u0007\t3U'\u0001\u0007gS:$7+\u001f8p]fl7\u000fF\u0002N5n\u00032A\u0014*U\u001b\u0005y%B\u0001)R\u0003\u0011)H/\u001b7\u000b\u0003\u0011K!aU(\u0003\t1K7\u000f\u001e\t\u0003+bk\u0011A\u0016\u0006\u0003/F\u000bA\u0001\\1oO&\u0011\u0011L\u0016\u0002\u0007\u001f\nTWm\u0019;\t\u000bQ*\u0001\u0019A\u001b\t\u000bq+\u0001\u0019A/\u0002\u00079,X\u000e\u0005\u0002\u001a=&\u0011qL\u0007\u0002\u0004\u0013:$HcA'bG\")!M\u0002a\u0001[\u00051a/Z2u_JDQ\u0001\u0018\u0004A\u0002u\u000bQ\u0002\u001d:fa\u0006\u0014XMU3tk2$HCA'g\u0011\u00159w\u00011\u0001i\u0003\u0019\u0011Xm];miB\u0019\u0011$[6\n\u0005)T\"!B!se\u0006L\b\u0003B\rmk9L!!\u001c\u000e\u0003\rQ+\b\u000f\\33!\tIr.\u0003\u0002q5\t1Ai\\;cY\u0016\f!bZ3u-\u0016\u001cGo\u001c:t+\u0005\u0019\b\u0003\u0002(ukYL!!^(\u0003\u00075\u000b\u0007\u000fE\u0002O%^\u0004\"!\u0007=\n\u0005eT\"!\u0002$m_\u0006$\u0018\u0001B:bm\u0016$B\u0001`@\u0002\fA\u0011\u0011$`\u0005\u0003}j\u0011A!\u00168ji\"9\u0011\u0011A\u0005A\u0002\u0005\r\u0011AA:d!\u0011\t)!a\u0002\u000e\u0003EI1!!\u0003\u0012\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0011\u0019\ti!\u0003a\u0001k\u0005!\u0001/\u0019;i\u0001"
)
public class Word2VecModelWrapper {
   private final Word2VecModel model;

   public Vector transform(final String word) {
      return this.model.transform(word);
   }

   public JavaRDD transform(final JavaRDD rdd) {
      return .MODULE$.fromRDD(rdd.rdd().map((word) -> this.model.transform(word), scala.reflect.ClassTag..MODULE$.apply(Vector.class)), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public List findSynonyms(final String word, final int num) {
      return this.prepareResult(this.model.findSynonyms(word, num));
   }

   public List findSynonyms(final Vector vector, final int num) {
      return this.prepareResult(this.model.findSynonyms(vector, num));
   }

   private List prepareResult(final Tuple2[] result) {
      Vector similarity = Vectors$.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])result), (x$1) -> BoxesRunTime.boxToDouble($anonfun$prepareResult$1(x$1)), scala.reflect.ClassTag..MODULE$.Double()));
      String[] words = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])result), (x$2) -> (String)x$2._1(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((new scala.collection.immutable..colon.colon(words, new scala.collection.immutable..colon.colon(similarity, scala.collection.immutable.Nil..MODULE$))).map((x$3) -> x$3)).asJava();
   }

   public Map getVectors() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)this.model.getVectors().map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            float[] v = (float[])x0$1._2();
            return new Tuple2(k, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.Predef..MODULE$.wrapFloatArray(v).toList()).asJava());
         } else {
            throw new MatchError(x0$1);
         }
      })).asJava();
   }

   public void save(final SparkContext sc, final String path) {
      this.model.save(sc, path);
   }

   // $FF: synthetic method
   public static final double $anonfun$prepareResult$1(final Tuple2 x$1) {
      return x$1._2$mcD$sp();
   }

   public Word2VecModelWrapper(final Word2VecModel model) {
      this.model = model;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

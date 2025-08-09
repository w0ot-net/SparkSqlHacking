package org.apache.spark.ml.clustering;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLFormatRegister;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.collection.ArrayOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005I3A!\u0002\u0004\u0005#!)\u0011\u0005\u0001C\u0001E!)Q\u0005\u0001C!M!)!\u0007\u0001C!M!)1\u0007\u0001C!i\tI\u0012J\u001c;fe:\fGnS'fC:\u001cXj\u001c3fY^\u0013\u0018\u000e^3s\u0015\t9\u0001\"\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!!\u0003\u0006\u0002\u00055d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001M!\u0001A\u0005\r\u001f!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011\u0011\u0004H\u0007\u00025)\u00111\u0004C\u0001\u0005kRLG.\u0003\u0002\u001e5\tqQ\nT,sSR,'OR8s[\u0006$\bCA\r \u0013\t\u0001#D\u0001\tN\u0019\u001a{'/\\1u%\u0016<\u0017n\u001d;fe\u00061A(\u001b8jiz\"\u0012a\t\t\u0003I\u0001i\u0011AB\u0001\u0007M>\u0014X.\u0019;\u0015\u0003\u001d\u0002\"\u0001K\u0018\u000f\u0005%j\u0003C\u0001\u0016\u0015\u001b\u0005Y#B\u0001\u0017\u0011\u0003\u0019a$o\\8u}%\u0011a\u0006F\u0001\u0007!J,G-\u001a4\n\u0005A\n$AB*ue&twM\u0003\u0002/)\u0005I1\u000f^1hK:\u000bW.Z\u0001\u0006oJLG/\u001a\u000b\u0006kaR$\t\u0014\t\u0003'YJ!a\u000e\u000b\u0003\tUs\u0017\u000e\u001e\u0005\u0006s\u0011\u0001\raJ\u0001\u0005a\u0006$\b\u000eC\u0003<\t\u0001\u0007A(\u0001\u0007ta\u0006\u00148nU3tg&|g\u000e\u0005\u0002>\u00016\taH\u0003\u0002@\u0015\u0005\u00191/\u001d7\n\u0005\u0005s$\u0001D*qCJ\\7+Z:tS>t\u0007\"B\"\u0005\u0001\u0004!\u0015!C8qi&|g.T1q!\u0011)%jJ\u0014\u000e\u0003\u0019S!a\u0012%\u0002\u000f5,H/\u00192mK*\u0011\u0011\nF\u0001\u000bG>dG.Z2uS>t\u0017BA&G\u0005\ri\u0015\r\u001d\u0005\u0006\u001b\u0012\u0001\rAT\u0001\u0006gR\fw-\u001a\t\u0003\u001fBk\u0011\u0001C\u0005\u0003#\"\u0011Q\u0002U5qK2Lg.Z*uC\u001e,\u0007"
)
public class InternalKMeansModelWriter implements MLFormatRegister {
   public String shortName() {
      return MLFormatRegister.shortName$(this);
   }

   public String format() {
      return "internal";
   }

   public String stageName() {
      return "org.apache.spark.ml.clustering.KMeansModel";
   }

   public void write(final String path, final SparkSession sparkSession, final Map optionMap, final PipelineStage stage) {
      KMeansModel instance = (KMeansModel)stage;
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, sparkSession);
      ClusterData[] data = (ClusterData[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])instance.clusterCenters()))), (x0$1) -> {
         if (x0$1 != null) {
            Vector center = (Vector)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            return new ClusterData(idx, center);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(ClusterData.class));
      String dataPath = (new Path(path, "data")).toString();
      ArraySeq var10001 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(data).toImmutableArraySeq();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(InternalKMeansModelWriter.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.clustering.ClusterData").asType().toTypeConstructor();
         }

         public $typecreator1$2() {
         }
      }

      sparkSession.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).repartition(1).write().parquet(dataPath);
   }

   public InternalKMeansModelWriter() {
      MLFormatRegister.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

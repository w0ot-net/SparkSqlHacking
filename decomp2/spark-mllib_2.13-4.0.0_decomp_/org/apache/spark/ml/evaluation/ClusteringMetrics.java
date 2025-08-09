package org.apache.spark.ml.evaluation;

import org.apache.spark.sql.Dataset;
import scala.Predef.;
import scala.collection.immutable.ArraySeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4A!\u0003\u0006\u0001+!AA\u0004\u0001B\u0001B\u0003%Q\u0004\u0003\u00040\u0001\u0011\u0005a\u0002\r\u0005\bq\u0001\u0001\r\u0011\"\u0003:\u0011\u001d)\u0005\u00011A\u0005\n\u0019Ca\u0001\u0014\u0001!B\u0013Q\u0004\"B'\u0001\t\u0003I\u0004\"\u0002(\u0001\t\u0003y\u0005\"B*\u0001\t\u0003!&!E\"mkN$XM]5oO6+GO]5dg*\u00111\u0002D\u0001\u000bKZ\fG.^1uS>t'BA\u0007\u000f\u0003\tiGN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003\u001d!\u0017\r^1tKR\u0004$A\b\u0014\u0011\u0007}\u0011C%D\u0001!\u0015\t\tc\"A\u0002tc2L!a\t\u0011\u0003\u000f\u0011\u000bG/Y:fiB\u0011QE\n\u0007\u0001\t%9\u0013!!A\u0001\u0002\u000b\u0005\u0001FA\u0002`IE\n\"!\u000b\u0017\u0011\u0005]Q\u0013BA\u0016\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u0017\n\u00059B\"aA!os\u00061A(\u001b8jiz\"\"!M\u001a\u0011\u0005I\u0002Q\"\u0001\u0006\t\u000bq\u0011\u0001\u0019\u0001\u001b1\u0005U:\u0004cA\u0010#mA\u0011Qe\u000e\u0003\nOM\n\t\u0011!A\u0003\u0002!\nq\u0002Z5ti\u0006t7-Z'fCN,(/Z\u000b\u0002uA\u00111H\u0011\b\u0003y\u0001\u0003\"!\u0010\r\u000e\u0003yR!a\u0010\u000b\u0002\rq\u0012xn\u001c;?\u0013\t\t\u0005$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0007\u0012\u0013aa\u0015;sS:<'BA!\u0019\u0003M!\u0017n\u001d;b]\u000e,W*Z1tkJ,w\fJ3r)\t9%\n\u0005\u0002\u0018\u0011&\u0011\u0011\n\u0007\u0002\u0005+:LG\u000fC\u0004L\t\u0005\u0005\t\u0019\u0001\u001e\u0002\u0007a$\u0013'\u0001\teSN$\u0018M\\2f\u001b\u0016\f7/\u001e:fA\u0005\u0011r-\u001a;ESN$\u0018M\\2f\u001b\u0016\f7/\u001e:f\u0003I\u0019X\r\u001e#jgR\fgnY3NK\u0006\u001cXO]3\u0015\u0005A\u000bV\"\u0001\u0001\t\u000bI;\u0001\u0019\u0001\u001e\u0002\u000bY\fG.^3\u0002\u0015MLG\u000e[8vKR$X\rF\u0001V!\t9b+\u0003\u0002X1\t1Ai\\;cY\u0016D3\u0001C-`!\tQV,D\u0001\\\u0015\taf\"\u0001\u0006b]:|G/\u0019;j_:L!AX.\u0003\u000bMKgnY3\"\u0003\u0001\fQa\r\u00182]AB3\u0001A-`\u0001"
)
public class ClusteringMetrics {
   private final Dataset dataset;
   private String distanceMeasure;

   private String distanceMeasure() {
      return this.distanceMeasure;
   }

   private void distanceMeasure_$eq(final String x$1) {
      this.distanceMeasure = x$1;
   }

   public String getDistanceMeasure() {
      return this.distanceMeasure();
   }

   public ClusteringMetrics setDistanceMeasure(final String value) {
      .MODULE$.require(value.equalsIgnoreCase("squaredEuclidean") || value.equalsIgnoreCase("cosine"));
      this.distanceMeasure_$eq(value);
      return this;
   }

   public double silhouette() {
      ArraySeq columns = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.dataset.columns()).toImmutableArraySeq();
      return this.distanceMeasure().equalsIgnoreCase("squaredEuclidean") ? SquaredEuclideanSilhouette$.MODULE$.computeSilhouetteScore(this.dataset, (String)columns.apply(0), (String)columns.apply(1), (String)columns.apply(2)) : CosineSilhouette$.MODULE$.computeSilhouetteScore(this.dataset, (String)columns.apply(0), (String)columns.apply(1), (String)columns.apply(2));
   }

   public ClusteringMetrics(final Dataset dataset) {
      this.dataset = dataset;
      this.distanceMeasure = "squaredEuclidean";
   }
}

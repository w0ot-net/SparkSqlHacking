package org.apache.spark.ml.ann;

import org.apache.spark.ml.linalg.Vector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q4Q!\u0004\b\u0001\u001daA\u0001b\t\u0001\u0003\u0006\u0004%\t!\n\u0005\tY\u0001\u0011\t\u0011)A\u0005M!)Q\u0006\u0001C\u0005]!)\u0011\u0007\u0001C!e!)\u0011\u0007\u0001C!}\u001d1AI\u0004E\u0001!\u00153a!\u0004\b\t\u0002A1\u0005\"B\u0017\b\t\u0003y\u0005\"\u0002)\b\t\u0003\t\u0006\"B*\b\t\u0003!\u0006b\u00021\b#\u0003%\t!\u0019\u0005\bY\u001e\t\t\u0011\"\u0003n\u0005M1U-\u001a3G_J<\u0018M\u001d3U_B|Gn\\4z\u0015\ty\u0001#A\u0002b]:T!!\u0005\n\u0002\u00055d'BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0014\u0007\u0001Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\u0005j\u0011AD\u0005\u0003E9\u0011\u0001\u0002V8q_2|w-_\u0001\u0007Y\u0006LXM]:\u0004\u0001U\ta\u0005E\u0002\u001bO%J!\u0001K\u000e\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0001R\u0013BA\u0016\u000f\u0005\u0015a\u0015-_3s\u0003\u001da\u0017-_3sg\u0002\na\u0001P5oSRtDCA\u00181!\t\u0001\u0003\u0001C\u0003$\u0007\u0001\u0007a%A\u0003n_\u0012,G\u000e\u0006\u00024mA\u0011\u0001\u0005N\u0005\u0003k9\u0011Q\u0002V8q_2|w-_'pI\u0016d\u0007\"B\u001c\u0005\u0001\u0004A\u0014aB<fS\u001eDGo\u001d\t\u0003sqj\u0011A\u000f\u0006\u0003wA\ta\u0001\\5oC2<\u0017BA\u001f;\u0005\u00191Vm\u0019;peR\u00111g\u0010\u0005\u0006\u0001\u0016\u0001\r!Q\u0001\u0005g\u0016,G\r\u0005\u0002\u001b\u0005&\u00111i\u0007\u0002\u0005\u0019>tw-A\nGK\u0016$gi\u001c:xCJ$Gk\u001c9pY><\u0017\u0010\u0005\u0002!\u000fM\u0019q!G$\u0011\u0005!kU\"A%\u000b\u0005)[\u0015AA5p\u0015\u0005a\u0015\u0001\u00026bm\u0006L!AT%\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0003\u0015\u000bQ!\u00199qYf$\"a\f*\t\u000b\rJ\u0001\u0019\u0001\u0014\u0002)5,H\u000e^5MCf,'\u000fU3sG\u0016\u0004HO]8o)\rySk\u0017\u0005\u0006-*\u0001\raV\u0001\u000bY\u0006LXM]*ju\u0016\u001c\bc\u0001\u000e(1B\u0011!$W\u0005\u00035n\u00111!\u00138u\u0011\u001da&\u0002%AA\u0002u\u000bAb]8gi6\f\u0007p\u00148U_B\u0004\"A\u00070\n\u0005}[\"a\u0002\"p_2,\u0017M\\\u0001\u001f[VdG/\u001b'bs\u0016\u0014\b+\u001a:dKB$(o\u001c8%I\u00164\u0017-\u001e7uII*\u0012A\u0019\u0016\u0003;\u000e\\\u0013\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%\\\u0012AC1o]>$\u0018\r^5p]&\u00111N\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#\u00018\u0011\u0005=\u0014X\"\u00019\u000b\u0005E\\\u0015\u0001\u00027b]\u001eL!a\u001d9\u0003\r=\u0013'.Z2u\u0001"
)
public class FeedForwardTopology implements Topology {
   private final Layer[] layers;

   public static boolean multiLayerPerceptron$default$2() {
      return FeedForwardTopology$.MODULE$.multiLayerPerceptron$default$2();
   }

   public static FeedForwardTopology multiLayerPerceptron(final int[] layerSizes, final boolean softmaxOnTop) {
      return FeedForwardTopology$.MODULE$.multiLayerPerceptron(layerSizes, softmaxOnTop);
   }

   public static FeedForwardTopology apply(final Layer[] layers) {
      return FeedForwardTopology$.MODULE$.apply(layers);
   }

   public Layer[] layers() {
      return this.layers;
   }

   public TopologyModel model(final Vector weights) {
      return FeedForwardModel$.MODULE$.apply(this, weights);
   }

   public TopologyModel model(final long seed) {
      return FeedForwardModel$.MODULE$.apply(this, seed);
   }

   public FeedForwardTopology(final Layer[] layers) {
      this.layers = layers;
   }
}

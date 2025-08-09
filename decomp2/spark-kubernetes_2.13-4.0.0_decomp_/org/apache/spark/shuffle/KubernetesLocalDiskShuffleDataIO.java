package org.apache.spark.shuffle;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.shuffle.api.ShuffleDataIO;
import org.apache.spark.shuffle.api.ShuffleDriverComponents;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.shuffle.sort.io.LocalDiskShuffleDriverComponents;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005I2A!\u0002\u0004\u0001\u001f!Aa\u0004\u0001B\u0001B\u0003%q\u0004C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0011\u0005\u0013\u0006C\u0003.\u0001\u0011\u0005cF\u0001\u0011Lk\n,'O\\3uKNdunY1m\t&\u001c8n\u00155vM\u001adW\rR1uC&{%BA\u0004\t\u0003\u001d\u0019\b.\u001e4gY\u0016T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u0002\u0001'\r\u0001\u0001\u0003\u0007\t\u0003#Yi\u0011A\u0005\u0006\u0003'Q\tA\u0001\\1oO*\tQ#\u0001\u0003kCZ\f\u0017BA\f\u0013\u0005\u0019y%M[3diB\u0011\u0011\u0004H\u0007\u00025)\u00111DB\u0001\u0004CBL\u0017BA\u000f\u001b\u00055\u0019\u0006.\u001e4gY\u0016$\u0015\r^1J\u001f\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003A\u0005j\u0011\u0001C\u0005\u0003E!\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\t)s\u0005\u0005\u0002'\u00015\ta\u0001C\u0003\u001f\u0005\u0001\u0007q$\u0001\u0004ee&4XM\u001d\u000b\u0002UA\u0011\u0011dK\u0005\u0003Yi\u0011qc\u00155vM\u001adW\r\u0012:jm\u0016\u00148i\\7q_:,g\u000e^:\u0002\u0011\u0015DXmY;u_J$\u0012a\f\t\u00033AJ!!\r\u000e\u00033MCWO\u001a4mK\u0016CXmY;u_J\u001cu.\u001c9p]\u0016tGo\u001d"
)
public class KubernetesLocalDiskShuffleDataIO implements ShuffleDataIO {
   private final SparkConf sparkConf;

   public ShuffleDriverComponents driver() {
      return new LocalDiskShuffleDriverComponents();
   }

   public ShuffleExecutorComponents executor() {
      return new KubernetesLocalDiskShuffleExecutorComponents(this.sparkConf);
   }

   public KubernetesLocalDiskShuffleDataIO(final SparkConf sparkConf) {
      this.sparkConf = sparkConf;
      .MODULE$.require(BoxesRunTime.unboxToBoolean(sparkConf.get(Config$.MODULE$.KUBERNETES_DRIVER_REUSE_PVC())), () -> "Ephemeral PVCs are required");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

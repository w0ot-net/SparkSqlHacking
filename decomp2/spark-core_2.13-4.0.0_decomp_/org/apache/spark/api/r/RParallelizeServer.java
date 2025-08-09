package org.apache.spark.api.r;

import java.io.InputStream;
import java.net.Socket;
import org.apache.spark.SparkEnv$;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.security.SocketAuthHelper;
import org.apache.spark.security.SocketAuthServer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013Q!\u0002\u0004\u0001\u0015AA\u0001B\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!)q\u0006\u0001C\u0001a!)Q\u0007\u0001C!m\t\u0011\"\u000bU1sC2dW\r\\5{KN+'O^3s\u0015\t9\u0001\"A\u0001s\u0015\tI!\"A\u0002ba&T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0003\u0001E\u00012AE\u000b\u0018\u001b\u0005\u0019\"B\u0001\u000b\u000b\u0003!\u0019XmY;sSRL\u0018B\u0001\f\u0014\u0005A\u0019vnY6fi\u0006+H\u000f[*feZ,'\u000fE\u0002\u00197ui\u0011!\u0007\u0006\u00035!\tAA[1wC&\u0011A$\u0007\u0002\b\u0015\u00064\u0018M\u0015#E!\rq\u0012eI\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t)\u0011I\u001d:bsB\u0011a\u0004J\u0005\u0003K}\u0011AAQ=uK\u0006\u00111oY\u0002\u0001!\tA\u0012&\u0003\u0002+3\t\u0001\"*\u0019<b'B\f'o[\"p]R,\u0007\u0010^\u0001\fa\u0006\u0014\u0018\r\u001c7fY&\u001cX\u000e\u0005\u0002\u001f[%\u0011af\b\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\bF\u00022gQ\u0002\"A\r\u0001\u000e\u0003\u0019AQAJ\u0002A\u0002!BQaK\u0002A\u00021\n\u0001\u0003[1oI2,7i\u001c8oK\u000e$\u0018n\u001c8\u0015\u0005]9\u0004\"\u0002\u001d\u0005\u0001\u0004I\u0014\u0001B:pG.\u0004\"A\u000f \u000e\u0003mR!\u0001P\u001f\u0002\u00079,GOC\u0001\u001b\u0013\ty4H\u0001\u0004T_\u000e\\W\r\u001e"
)
public class RParallelizeServer extends SocketAuthServer {
   private final JavaSparkContext sc;
   private final int parallelism;

   public JavaRDD handleConnection(final Socket sock) {
      InputStream in = sock.getInputStream();
      return JavaRDD$.MODULE$.readRDDFromInputStream(this.sc.sc(), in, this.parallelism);
   }

   public RParallelizeServer(final JavaSparkContext sc, final int parallelism) {
      super((SocketAuthHelper)(new RAuthHelper(SparkEnv$.MODULE$.get().conf())), "sparkr-parallelize-server");
      this.sc = sc;
      this.parallelism = parallelism;
   }
}

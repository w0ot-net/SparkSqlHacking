package org.apache.spark.api.python;

import java.io.InputStream;
import java.net.Socket;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.security.SocketAuthServer;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153a\u0001B\u0003\u0002\u0002%y\u0001\"B\u0013\u0001\t\u00039\u0003\"\u0002\u0016\u0001\t\u0003Y\u0003\"B\u001b\u0001\r#1$a\u0004)zi\"|gN\u0015#E'\u0016\u0014h/\u001a:\u000b\u0005\u00199\u0011A\u00029zi\"|gN\u0003\u0002\t\u0013\u0005\u0019\u0011\r]5\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\"\u0001\u0001\t\u0011\u0007E!b#D\u0001\u0013\u0015\t\u0019\u0012\"\u0001\u0005tK\u000e,(/\u001b;z\u0013\t)\"C\u0001\tT_\u000e\\W\r^!vi\"\u001cVM\u001d<feB\u0019qC\u0007\u000f\u000e\u0003aQ!!G\u0004\u0002\t)\fg/Y\u0005\u00037a\u0011qAS1wCJ#E\tE\u0002\u001eA\tj\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003;\rJ!\u0001\n\u0010\u0003\t\tKH/Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0001\u0006\u0005\u0002*\u00015\tQ!\u0001\tiC:$G.Z\"p]:,7\r^5p]R\u0011a\u0003\f\u0005\u0006[\t\u0001\rAL\u0001\u0005g>\u001c7\u000e\u0005\u00020g5\t\u0001G\u0003\u00022e\u0005\u0019a.\u001a;\u000b\u0003eI!\u0001\u000e\u0019\u0003\rM{7m[3u\u0003-\u0019HO]3b[R{'\u000b\u0012#\u0015\u0005]j\u0004c\u0001\u001d<95\t\u0011H\u0003\u0002;\u0013\u0005\u0019!\u000f\u001a3\n\u0005qJ$a\u0001*E\t\")ah\u0001a\u0001\u007f\u0005)\u0011N\u001c9viB\u0011\u0001iQ\u0007\u0002\u0003*\u0011!IM\u0001\u0003S>L!\u0001R!\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c"
)
public abstract class PythonRDDServer extends SocketAuthServer {
   public JavaRDD handleConnection(final Socket sock) {
      InputStream in = sock.getInputStream();
      InputStream dechunkedInput = new DechunkedInputStream(in);
      return JavaRDD$.MODULE$.fromRDD(this.streamToRDD(dechunkedInput), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public abstract RDD streamToRDD(final InputStream input);

   public PythonRDDServer() {
      super("pyspark-parallelize-server");
   }
}

package org.apache.spark.deploy.yarn;

import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkApplication;
import org.apache.spark.internal.config.package.;
import org.apache.spark.rpc.RpcEnv;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2Qa\u0001\u0003\u0001\u00119AQ!\u0007\u0001\u0005\u0002mAQA\b\u0001\u0005B}\u0011a#W1s]\u000ecWo\u001d;fe\u0006\u0003\b\u000f\\5dCRLwN\u001c\u0006\u0003\u000b\u0019\tA!_1s]*\u0011q\u0001C\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c2\u0001A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011acF\u0007\u0002\r%\u0011\u0001D\u0002\u0002\u0011'B\f'o[!qa2L7-\u0019;j_:\fa\u0001P5oSRt4\u0001\u0001\u000b\u00029A\u0011Q\u0004A\u0007\u0002\t\u0005)1\u000f^1siR\u0019\u0001eI\u001a\u0011\u0005A\t\u0013B\u0001\u0012\u0012\u0005\u0011)f.\u001b;\t\u000b\u0011\u0012\u0001\u0019A\u0013\u0002\t\u0005\u0014xm\u001d\t\u0004!\u0019B\u0013BA\u0014\u0012\u0005\u0015\t%O]1z!\tI\u0003G\u0004\u0002+]A\u00111&E\u0007\u0002Y)\u0011QFG\u0001\u0007yI|w\u000e\u001e \n\u0005=\n\u0012A\u0002)sK\u0012,g-\u0003\u00022e\t11\u000b\u001e:j]\u001eT!aL\t\t\u000bQ\u0012\u0001\u0019A\u001b\u0002\t\r|gN\u001a\t\u0003m]j\u0011\u0001C\u0005\u0003q!\u0011\u0011b\u00159be.\u001cuN\u001c4"
)
public class YarnClusterApplication implements SparkApplication {
   public void start(final String[] args, final SparkConf conf) {
      conf.remove(.MODULE$.JARS());
      conf.remove(.MODULE$.FILES());
      conf.remove(.MODULE$.ARCHIVES());
      (new Client(new ClientArguments(args), conf, (RpcEnv)null)).run();
   }
}

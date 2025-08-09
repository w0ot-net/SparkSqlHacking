package org.apache.spark.deploy.rest;

import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005m2Q!\u0002\u0004\u0001\rAA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\t;\u0001\u0011\t\u0011)A\u0005=!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\tQ\ta2\u000b^1oI\u0006dwN\\3LS2d'+Z9vKN$8+\u001a:wY\u0016$(BA\u0004\t\u0003\u0011\u0011Xm\u001d;\u000b\u0005%Q\u0011A\u00023fa2|\u0017P\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h'\t\u0001\u0011\u0003\u0005\u0002\u0013'5\ta!\u0003\u0002\u0015\r\t\u00112*\u001b7m%\u0016\fX/Z:u'\u0016\u0014h\u000f\\3u\u00039i\u0017m\u001d;fe\u0016sG\r]8j]R\u001c\u0001\u0001\u0005\u0002\u001975\t\u0011D\u0003\u0002\u001b\u0015\u0005\u0019!\u000f]2\n\u0005qI\"A\u0004*qG\u0016sG\r]8j]R\u0014VMZ\u0001\u0005G>tg\r\u0005\u0002 A5\t!\"\u0003\u0002\"\u0015\tI1\u000b]1sW\u000e{gNZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0011*c\u0005\u0005\u0002\u0013\u0001!)Qc\u0001a\u0001/!)Qd\u0001a\u0001=\u0005Q\u0001.\u00198eY\u0016\\\u0015\u000e\u001c7\u0015\u0005%b\u0003C\u0001\n+\u0013\tYcA\u0001\fLS2d7+\u001e2nSN\u001c\u0018n\u001c8SKN\u0004xN\\:f\u0011\u0015iC\u00011\u0001/\u00031\u0019XOY7jgNLwN\\%e!\ty\u0003H\u0004\u00021mA\u0011\u0011\u0007N\u0007\u0002e)\u00111GF\u0001\u0007yI|w\u000e\u001e \u000b\u0003U\nQa]2bY\u0006L!a\u000e\u001b\u0002\rA\u0013X\rZ3g\u0013\tI$H\u0001\u0004TiJLgn\u001a\u0006\u0003oQ\u0002"
)
public class StandaloneKillRequestServlet extends KillRequestServlet {
   private final RpcEndpointRef masterEndpoint;

   public KillSubmissionResponse handleKill(final String submissionId) {
      DeployMessages.KillDriverResponse response = (DeployMessages.KillDriverResponse)this.masterEndpoint.askSync(new DeployMessages.RequestKillDriver(submissionId), .MODULE$.apply(DeployMessages.KillDriverResponse.class));
      KillSubmissionResponse k = new KillSubmissionResponse();
      k.serverSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      k.message_$eq(response.message());
      k.submissionId_$eq(submissionId);
      k.success_$eq(scala.Predef..MODULE$.boolean2Boolean(response.success()));
      return k;
   }

   public StandaloneKillRequestServlet(final RpcEndpointRef masterEndpoint, final SparkConf conf) {
      this.masterEndpoint = masterEndpoint;
   }
}

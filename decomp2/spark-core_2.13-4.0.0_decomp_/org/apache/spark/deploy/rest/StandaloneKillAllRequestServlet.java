package org.apache.spark.deploy.rest;

import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u000512Q!\u0002\u0004\u0001\rAA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\t;\u0001\u0011\t\u0011)A\u0005=!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\tQ\ty2\u000b^1oI\u0006dwN\\3LS2d\u0017\t\u001c7SKF,Xm\u001d;TKJ4H.\u001a;\u000b\u0005\u001dA\u0011\u0001\u0002:fgRT!!\u0003\u0006\u0002\r\u0011,\u0007\u000f\\8z\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7C\u0001\u0001\u0012!\t\u00112#D\u0001\u0007\u0013\t!bAA\u000bLS2d\u0017\t\u001c7SKF,Xm\u001d;TKJ4H.\u001a;\u0002\u001d5\f7\u000f^3s\u000b:$\u0007o\\5oi\u000e\u0001\u0001C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u000b\u0003\r\u0011\boY\u0005\u00039e\u0011aB\u00159d\u000b:$\u0007o\\5oiJ+g-\u0001\u0003d_:4\u0007CA\u0010!\u001b\u0005Q\u0011BA\u0011\u000b\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0004I\u00152\u0003C\u0001\n\u0001\u0011\u0015)2\u00011\u0001\u0018\u0011\u0015i2\u00011\u0001\u001f\u00035A\u0017M\u001c3mK.KG\u000e\\!mYR\t\u0011\u0006\u0005\u0002\u0013U%\u00111F\u0002\u0002\u001a\u0017&dG.\u00117m'V\u0014W.[:tS>t'+Z:q_:\u001cX\r"
)
public class StandaloneKillAllRequestServlet extends KillAllRequestServlet {
   private final RpcEndpointRef masterEndpoint;

   public KillAllSubmissionResponse handleKillAll() {
      DeployMessages.KillAllDriversResponse response = (DeployMessages.KillAllDriversResponse)this.masterEndpoint.askSync(DeployMessages.RequestKillAllDrivers$.MODULE$, .MODULE$.apply(DeployMessages.KillAllDriversResponse.class));
      KillAllSubmissionResponse k = new KillAllSubmissionResponse();
      k.serverSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      k.message_$eq(response.message());
      k.success_$eq(scala.Predef..MODULE$.boolean2Boolean(response.success()));
      return k;
   }

   public StandaloneKillAllRequestServlet(final RpcEndpointRef masterEndpoint, final SparkConf conf) {
      this.masterEndpoint = masterEndpoint;
   }
}

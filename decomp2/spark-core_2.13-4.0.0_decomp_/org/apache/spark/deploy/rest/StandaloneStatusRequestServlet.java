package org.apache.spark.deploy.rest;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005m2Q!\u0002\u0004\u0001\rAA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\t;\u0001\u0011\t\u0011)A\u0005=!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\tQ\tq2\u000b^1oI\u0006dwN\\3Ti\u0006$Xo\u001d*fcV,7\u000f^*feZdW\r\u001e\u0006\u0003\u000f!\tAA]3ti*\u0011\u0011BC\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\"\u0001A\t\u0011\u0005I\u0019R\"\u0001\u0004\n\u0005Q1!\u0001F*uCR,8OU3rk\u0016\u001cHoU3sm2,G/\u0001\bnCN$XM]#oIB|\u0017N\u001c;\u0004\u0001A\u0011\u0001dG\u0007\u00023)\u0011!DC\u0001\u0004eB\u001c\u0017B\u0001\u000f\u001a\u00059\u0011\u0006oY#oIB|\u0017N\u001c;SK\u001a\fAaY8oMB\u0011q\u0004I\u0007\u0002\u0015%\u0011\u0011E\u0003\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDc\u0001\u0013&MA\u0011!\u0003\u0001\u0005\u0006+\r\u0001\ra\u0006\u0005\u0006;\r\u0001\rAH\u0001\rQ\u0006tG\r\\3Ti\u0006$Xo\u001d\u000b\u0003S1\u0002\"A\u0005\u0016\n\u0005-2!\u0001G*vE6L7o]5p]N#\u0018\r^;t%\u0016\u001c\bo\u001c8tK\")Q\u0006\u0002a\u0001]\u0005a1/\u001e2nSN\u001c\u0018n\u001c8JIB\u0011q\u0006\u000f\b\u0003aY\u0002\"!\r\u001b\u000e\u0003IR!a\r\f\u0002\rq\u0012xn\u001c;?\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011H\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005]\"\u0004"
)
public class StandaloneStatusRequestServlet extends StatusRequestServlet {
   private final RpcEndpointRef masterEndpoint;

   public SubmissionStatusResponse handleStatus(final String submissionId) {
      DeployMessages.DriverStatusResponse response = (DeployMessages.DriverStatusResponse)this.masterEndpoint.askSync(new DeployMessages.RequestDriverStatus(submissionId), .MODULE$.apply(DeployMessages.DriverStatusResponse.class));
      Option message = response.exception().map((x$1) -> {
         String var10000 = this.formatException(x$1);
         return "Exception from the cluster:\n" + var10000;
      });
      SubmissionStatusResponse d = new SubmissionStatusResponse();
      d.serverSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      d.submissionId_$eq(submissionId);
      d.success_$eq(scala.Predef..MODULE$.boolean2Boolean(response.found()));
      d.driverState_$eq((String)response.state().map((x$2) -> x$2.toString()).orNull(scala..less.colon.less..MODULE$.refl()));
      d.workerId_$eq((String)response.workerId().orNull(scala..less.colon.less..MODULE$.refl()));
      d.workerHostPort_$eq((String)response.workerHostPort().orNull(scala..less.colon.less..MODULE$.refl()));
      d.message_$eq((String)message.orNull(scala..less.colon.less..MODULE$.refl()));
      return d;
   }

   public StandaloneStatusRequestServlet(final RpcEndpointRef masterEndpoint, final SparkConf conf) {
      this.masterEndpoint = masterEndpoint;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.deploy.rest;

import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512Q!\u0002\u0004\u0001\rAA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\t;\u0001\u0011\t\u0011)A\u0005=!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\tQ\tq2\u000b^1oI\u0006dwN\\3SK\u0006$\u0017P\u001f*fcV,7\u000f^*feZdW\r\u001e\u0006\u0003\u000f!\tAA]3ti*\u0011\u0011BC\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\"\u0001A\t\u0011\u0005I\u0019R\"\u0001\u0004\n\u0005Q1!\u0001\u0006*fC\u0012L(PU3rk\u0016\u001cHoU3sm2,G/\u0001\bnCN$XM]#oIB|\u0017N\u001c;\u0004\u0001A\u0011\u0001dG\u0007\u00023)\u0011!DC\u0001\u0004eB\u001c\u0017B\u0001\u000f\u001a\u00059\u0011\u0006oY#oIB|\u0017N\u001c;SK\u001a\fAaY8oMB\u0011q\u0004I\u0007\u0002\u0015%\u0011\u0011E\u0003\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDc\u0001\u0013&MA\u0011!\u0003\u0001\u0005\u0006+\r\u0001\ra\u0006\u0005\u0006;\r\u0001\rAH\u0001\rQ\u0006tG\r\\3SK\u0006$\u0017P\u001f\u000b\u0002SA\u0011!CK\u0005\u0003W\u0019\u0011aBU3bIfT(+Z:q_:\u001cX\r"
)
public class StandaloneReadyzRequestServlet extends ReadyzRequestServlet {
   private final RpcEndpointRef masterEndpoint;

   public ReadyzResponse handleReadyz() {
      boolean success = BoxesRunTime.unboxToBoolean(this.masterEndpoint.askSync(DeployMessages.RequestReadyz$.MODULE$, .MODULE$.Boolean()));
      ReadyzResponse r = new ReadyzResponse();
      r.serverSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      r.message_$eq("");
      r.success_$eq(scala.Predef..MODULE$.boolean2Boolean(success));
      return r;
   }

   public StandaloneReadyzRequestServlet(final RpcEndpointRef masterEndpoint, final SparkConf conf) {
      this.masterEndpoint = masterEndpoint;
   }
}

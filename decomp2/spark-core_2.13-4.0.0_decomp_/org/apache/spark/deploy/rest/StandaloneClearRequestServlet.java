package org.apache.spark.deploy.rest;

import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512Q!\u0002\u0004\u0001\rAA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\t;\u0001\u0011\t\u0011)A\u0005=!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\tQ\ti2\u000b^1oI\u0006dwN\\3DY\u0016\f'OU3rk\u0016\u001cHoU3sm2,GO\u0003\u0002\b\u0011\u0005!!/Z:u\u0015\tI!\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sON\u0011\u0001!\u0005\t\u0003%Mi\u0011AB\u0005\u0003)\u0019\u00111c\u00117fCJ\u0014V-];fgR\u001cVM\u001d<mKR\fa\"\\1ti\u0016\u0014XI\u001c3q_&tGo\u0001\u0001\u0011\u0005aYR\"A\r\u000b\u0005iQ\u0011a\u0001:qG&\u0011A$\u0007\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003\u0011\u0019wN\u001c4\u0011\u0005}\u0001S\"\u0001\u0006\n\u0005\u0005R!!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0019A%\n\u0014\u0011\u0005I\u0001\u0001\"B\u000b\u0004\u0001\u00049\u0002\"B\u000f\u0004\u0001\u0004q\u0012a\u00035b]\u0012dWm\u00117fCJ$\u0012!\u000b\t\u0003%)J!a\u000b\u0004\u0003\u001b\rcW-\u0019:SKN\u0004xN\\:f\u0001"
)
public class StandaloneClearRequestServlet extends ClearRequestServlet {
   private final RpcEndpointRef masterEndpoint;

   public ClearResponse handleClear() {
      boolean response = BoxesRunTime.unboxToBoolean(this.masterEndpoint.askSync(DeployMessages.RequestClearCompletedDriversAndApps$.MODULE$, .MODULE$.Boolean()));
      ClearResponse c = new ClearResponse();
      c.serverSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      c.message_$eq("");
      c.success_$eq(scala.Predef..MODULE$.boolean2Boolean(response));
      return c;
   }

   public StandaloneClearRequestServlet(final RpcEndpointRef masterEndpoint, final SparkConf conf) {
      this.masterEndpoint = masterEndpoint;
   }
}

package org.apache.spark.status.api.v1;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import org.apache.spark.ui.UIUtils$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2Qa\u0001\u0003\u0001\tAA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\u0006U\u0001!\ta\u000b\u0002\u0013'\u0016\u0014h/[2f+:\fg/Y5mC\ndWM\u0003\u0002\u0006\r\u0005\u0011a/\r\u0006\u0003\u000f!\t1!\u00199j\u0015\tI!\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sON\u0011\u0001!\u0005\t\u0003%ei\u0011a\u0005\u0006\u0003)U\t!A]:\u000b\u0005Y9\u0012AA<t\u0015\u0005A\u0012a\u00026bW\u0006\u0014H/Y\u0005\u00035M\u0011qcV3c\u0003B\u0004H.[2bi&|g.\u0012=dKB$\u0018n\u001c8\u0002\u00075\u001cxm\u0001\u0001\u0011\u0005y9cBA\u0010&!\t\u00013%D\u0001\"\u0015\t\u0011C$\u0001\u0004=e>|GO\u0010\u0006\u0002I\u0005)1oY1mC&\u0011aeI\u0001\u0007!J,G-\u001a4\n\u0005!J#AB*ue&twM\u0003\u0002'G\u00051A(\u001b8jiz\"\"\u0001\f\u0018\u0011\u00055\u0002Q\"\u0001\u0003\t\u000bm\u0011\u0001\u0019A\u000f"
)
public class ServiceUnavailable extends WebApplicationException {
   public ServiceUnavailable(final String msg) {
      super(UIUtils$.MODULE$.buildErrorResponse(Status.SERVICE_UNAVAILABLE, msg));
   }
}

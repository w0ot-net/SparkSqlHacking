package org.apache.spark.status.api.v1;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import org.apache.spark.ui.UIUtils$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2Q\u0001B\u0003\u0001\u000bEA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006W\u0001!\t\u0001\r\u0002\u0016\u0005\u0006$\u0007+\u0019:b[\u0016$XM]#yG\u0016\u0004H/[8o\u0015\t1q!\u0001\u0002wc)\u0011\u0001\"C\u0001\u0004CBL'B\u0001\u0006\f\u0003\u0019\u0019H/\u0019;vg*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00111CG\u0007\u0002))\u0011QCF\u0001\u0003eNT!a\u0006\r\u0002\u0005]\u001c(\"A\r\u0002\u000f)\f7.\u0019:uC&\u00111\u0004\u0006\u0002\u0018/\u0016\u0014\u0017\t\u001d9mS\u000e\fG/[8o\u000bb\u001cW\r\u001d;j_:\f1!\\:h\u0007\u0001\u0001\"a\b\u0015\u000f\u0005\u00012\u0003CA\u0011%\u001b\u0005\u0011#BA\u0012\u001e\u0003\u0019a$o\\8u})\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\u00051\u0001K]3eK\u001aL!!\u000b\u0016\u0003\rM#(/\u001b8h\u0015\t9C%\u0001\u0004=S:LGO\u0010\u000b\u0003[=\u0002\"A\f\u0001\u000e\u0003\u0015AQ\u0001\b\u0002A\u0002y!B!L\u00194k!)!g\u0001a\u0001=\u0005)\u0001/\u0019:b[\")Ag\u0001a\u0001=\u0005\u0019Q\r\u001f9\t\u000bY\u001a\u0001\u0019\u0001\u0010\u0002\r\u0005\u001cG/^1m\u0001"
)
public class BadParameterException extends WebApplicationException {
   public BadParameterException(final String msg) {
      super(UIUtils$.MODULE$.buildErrorResponse(Status.BAD_REQUEST, msg));
   }

   public BadParameterException(final String param, final String exp, final String actual) {
      this("Bad value for parameter \"" + param + "\".  Expected a " + exp + ", got \"" + actual + "\"");
   }
}

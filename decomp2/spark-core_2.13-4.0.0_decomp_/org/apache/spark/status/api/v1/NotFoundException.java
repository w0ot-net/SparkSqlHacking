package org.apache.spark.status.api.v1;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import org.apache.spark.ui.UIUtils$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2Qa\u0001\u0003\u0001\tAA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\u0006U\u0001!\ta\u000b\u0002\u0012\u001d>$hi\\;oI\u0016C8-\u001a9uS>t'BA\u0003\u0007\u0003\t1\u0018G\u0003\u0002\b\u0011\u0005\u0019\u0011\r]5\u000b\u0005%Q\u0011AB:uCR,8O\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h'\t\u0001\u0011\u0003\u0005\u0002\u001335\t1C\u0003\u0002\u0015+\u0005\u0011!o\u001d\u0006\u0003-]\t!a^:\u000b\u0003a\tqA[1lCJ$\u0018-\u0003\u0002\u001b'\t9r+\u001a2BaBd\u0017nY1uS>tW\t_2faRLwN\\\u0001\u0004[N<7\u0001\u0001\t\u0003=\u001dr!aH\u0013\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0005\tb\u0012A\u0002\u001fs_>$hHC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13%\u0001\u0004Qe\u0016$WMZ\u0005\u0003Q%\u0012aa\u0015;sS:<'B\u0001\u0014$\u0003\u0019a\u0014N\\5u}Q\u0011AF\f\t\u0003[\u0001i\u0011\u0001\u0002\u0005\u00067\t\u0001\r!\b"
)
public class NotFoundException extends WebApplicationException {
   public NotFoundException(final String msg) {
      super(UIUtils$.MODULE$.buildErrorResponse(Status.NOT_FOUND, msg));
   }
}

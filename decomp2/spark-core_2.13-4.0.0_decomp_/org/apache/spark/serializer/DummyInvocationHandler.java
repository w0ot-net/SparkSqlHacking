package org.apache.spark.serializer;

import java.lang.reflect.Method;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e:a\u0001B\u0003\t\u0002\u001diaAB\b\u0006\u0011\u00039\u0001\u0003C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00053%\u0001\fEk6l\u00170\u00138w_\u000e\fG/[8o\u0011\u0006tG\r\\3s\u0015\t1q!\u0001\u0006tKJL\u0017\r\\5{KJT!\u0001C\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Y\u0011AB1qC\u000eDWMC\u0001\r\u0003\ry'o\u001a\t\u0003\u001d\u0005i\u0011!\u0002\u0002\u0017\tVlW._%om>\u001c\u0017\r^5p]\"\u000bg\u000e\u001a7feN\u0019\u0011!E\r\u0011\u0005I9R\"A\n\u000b\u0005Q)\u0012\u0001\u00027b]\u001eT\u0011AF\u0001\u0005U\u00064\u0018-\u0003\u0002\u0019'\t1qJ\u00196fGR\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\n\u0002\u000fI,g\r\\3di&\u0011ad\u0007\u0002\u0012\u0013:4xnY1uS>t\u0007*\u00198eY\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u00035\ta!\u001b8w_.,G\u0003\u0002\u0013+_Q\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007\"B\u0016\u0004\u0001\u0004a\u0013!\u00029s_bL\bCA\u0013.\u0013\tqcEA\u0002B]fDQ\u0001M\u0002A\u0002E\na!\\3uQ>$\u0007C\u0001\u000e3\u0013\t\u00194D\u0001\u0004NKRDw\u000e\u001a\u0005\u0006k\r\u0001\rAN\u0001\u0005CJ<7\u000fE\u0002&o\u0011J!\u0001\u000f\u0014\u0003\u000b\u0005\u0013(/Y="
)
public final class DummyInvocationHandler {
   public static Object invoke(final Object proxy, final Method method, final Object[] args) {
      return DummyInvocationHandler$.MODULE$.invoke(proxy, method, args);
   }
}

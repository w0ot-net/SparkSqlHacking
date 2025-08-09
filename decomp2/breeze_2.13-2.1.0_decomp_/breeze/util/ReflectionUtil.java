package breeze.util;

import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQaN\u0001\u0005\u0002a\naBU3gY\u0016\u001cG/[8o+RLGN\u0003\u0002\b\u0011\u0005!Q\u000f^5m\u0015\u0005I\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00051\tQ\"\u0001\u0004\u0003\u001dI+g\r\\3di&|g.\u0016;jYN\u0011\u0011a\u0004\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005Y\u0011A\u00052pq\u0016$gI]8n!JLW.\u001b;jm\u0016$\"!\u0007\u00191\u0005i9\u0003cA\u000e#K9\u0011A\u0004\t\t\u0003;Ei\u0011A\b\u0006\u0003?)\ta\u0001\u0010:p_Rt\u0014BA\u0011\u0012\u0003\u0019\u0001&/\u001a3fM&\u00111\u0005\n\u0002\u0006\u00072\f7o\u001d\u0006\u0003CE\u0001\"AJ\u0014\r\u0001\u0011I\u0001fAA\u0001\u0002\u0003\u0015\t!\u000b\u0002\u0004?\u0012\u0012\u0014C\u0001\u0016.!\t\u00012&\u0003\u0002-#\t9aj\u001c;iS:<\u0007C\u0001\t/\u0013\ty\u0013CA\u0002B]fDQ!M\u0002A\u0002I\n\u0011a\u0019\u0019\u0003gU\u00022a\u0007\u00125!\t1S\u0007B\u00057a\u0005\u0005\t\u0011!B\u0001S\t\u0019q\fJ\u0019\u0002+\u0015dW-\\\"mCN\u001cH+Y4Ge>l\u0017I\u001d:bsV\u0011\u0011(\u0011\u000b\u0003u\r\u00032a\u000f A\u001b\u0005a$BA\u001f\u0012\u0003\u001d\u0011XM\u001a7fGRL!a\u0010\u001f\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"AJ!\u0005\u000b\t#!\u0019A\u0015\u0003\u0003QCQ\u0001\u0012\u0003A\u0002\u0015\u000bQ!\u0019:sCf\u00042\u0001\u0005$A\u0013\t9\u0015CA\u0003BeJ\f\u0017\u0010"
)
public final class ReflectionUtil {
   public static ClassTag elemClassTagFromArray(final Object array) {
      return ReflectionUtil$.MODULE$.elemClassTagFromArray(array);
   }

   public static Class boxedFromPrimitive(final Class c) {
      return ReflectionUtil$.MODULE$.boxedFromPrimitive(c);
   }
}

package org.apache.spark.sql.catalyst.expressions;

import org.apache.spark.sql.types.DataType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-:Q\u0001B\u0003\t\u0002I1Q\u0001F\u0003\t\u0002UAQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005\u0002}\t!b\u0014:eKJ,F/\u001b7t\u0015\t1q!A\u0006fqB\u0014Xm]:j_:\u001c(B\u0001\u0005\n\u0003!\u0019\u0017\r^1msN$(B\u0001\u0006\f\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sO\u000e\u0001\u0001CA\n\u0002\u001b\u0005)!AC(sI\u0016\u0014X\u000b^5mgN\u0011\u0011A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0011\u0012aC5t\u001fJ$WM]1cY\u0016$\"\u0001I\u0012\u0011\u0005]\t\u0013B\u0001\u0012\u0019\u0005\u001d\u0011un\u001c7fC:DQ\u0001J\u0002A\u0002\u0015\n\u0001\u0002Z1uCRK\b/\u001a\t\u0003M%j\u0011a\n\u0006\u0003Q%\tQ\u0001^=qKNL!AK\u0014\u0003\u0011\u0011\u000bG/\u0019+za\u0016\u0004"
)
public final class OrderUtils {
   public static boolean isOrderable(final DataType dataType) {
      return OrderUtils$.MODULE$.isOrderable(dataType);
   }
}

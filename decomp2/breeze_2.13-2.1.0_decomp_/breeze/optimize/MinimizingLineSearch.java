package breeze.optimize;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0019\u0005\u0011\u0003C\u0004\u001e\u0001E\u0005I\u0011\u0001\u0010\u0003)5Kg.[7ju&tw\rT5oKN+\u0017M]2i\u0015\t)a!\u0001\u0005paRLW.\u001b>f\u0015\u00059\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0005nS:LW.\u001b>f)\r\u0011Rc\u0007\t\u0003\u0017MI!\u0001\u0006\u0007\u0003\r\u0011{WO\u00197f\u0011\u00151\u0012\u00011\u0001\u0018\u0003\u00051\u0007c\u0001\r\u001a%5\tA!\u0003\u0002\u001b\t\taA)\u001b4g\rVt7\r^5p]\"9A$\u0001I\u0001\u0002\u0004\u0011\u0012\u0001B5oSR\f!#\\5oS6L'0\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\tqD\u000b\u0002\u0013A-\n\u0011\u0005\u0005\u0002#O5\t1E\u0003\u0002%K\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003M1\t!\"\u00198o_R\fG/[8o\u0013\tA3EA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public interface MinimizingLineSearch {
   double minimize(final DiffFunction f, final double init);

   // $FF: synthetic method
   static double minimize$default$2$(final MinimizingLineSearch $this) {
      return $this.minimize$default$2();
   }

   default double minimize$default$2() {
      return (double)1.0F;
   }
}

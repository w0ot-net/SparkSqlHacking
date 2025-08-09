package cats.kernel.instances;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0002\u0014'>\u0014H/\u001a3NCBLen\u001d;b]\u000e,7o\r\u0006\u0003\u000b\u0019\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005\u001dA\u0011AB6fe:,GNC\u0001\n\u0003\u0011\u0019\u0017\r^:\u0014\u0007\u0001Y\u0011\u0003\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0003%Mi\u0011\u0001B\u0005\u0003)\u0011\u00111cU8si\u0016$W*\u00199J]N$\u0018M\\2fgJ\na\u0001J5oSR$3\u0001\u0001\u000b\u00021A\u0011A\"G\u0005\u000355\u0011A!\u00168ji\u0006q2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012|%\u000fZ3s\r>\u00148k\u001c:uK\u0012l\u0015\r]\u000b\u0004;12DC\u0001\u00109!\ry\u0002EI\u0007\u0002\r%\u0011\u0011E\u0002\u0002\u0006\u001fJ$WM\u001d\t\u0005G!RS'D\u0001%\u0015\t)c%A\u0005j[6,H/\u00192mK*\u0011q%D\u0001\u000bG>dG.Z2uS>t\u0017BA\u0015%\u0005%\u0019vN\u001d;fI6\u000b\u0007\u000f\u0005\u0002,Y1\u0001A!B\u0017\u0003\u0005\u0004q#!A&\u0012\u0005=\u0012\u0004C\u0001\u00071\u0013\t\tTBA\u0004O_RD\u0017N\\4\u0011\u00051\u0019\u0014B\u0001\u001b\u000e\u0005\r\te.\u001f\t\u0003WY\"Qa\u000e\u0002C\u00029\u0012\u0011A\u0016\u0005\bs\t\t\t\u0011q\u0001;\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\u0007}\u0001S\u0007"
)
public interface SortedMapInstances3 extends SortedMapInstances2 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForSortedMap$(final SortedMapInstances3 $this, final Order evidence$11) {
      return $this.catsKernelStdOrderForSortedMap(evidence$11);
   }

   default Order catsKernelStdOrderForSortedMap(final Order evidence$11) {
      return new SortedMapOrder(evidence$11);
   }

   static void $init$(final SortedMapInstances3 $this) {
   }
}

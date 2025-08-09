package scala.runtime.java8;

import java.io.Serializable;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@FunctionalInterface
@ScalaSignature(
   bytes = "\u0006\u0005\u00053q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0019\u0005\u0011\u0006C\u00035\u0001\u0011\u0005SGA\nK\rVt7\r^5p]J\"Sn\u0019,K\u0013\u0012\u001a\bO\u0003\u0002\u0007\u000f\u0005)!.\u0019<bq)\u0011\u0001\"C\u0001\beVtG/[7f\u0015\u0005Q\u0011!B:dC2\f7\u0001A\n\u0005\u00015\tr\u0003\u0005\u0002\u000f\u001f5\t\u0011\"\u0003\u0002\u0011\u0013\t1\u0011I\\=SK\u001a\u0004RA\u0004\n\u0015)QI!aE\u0005\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004C\u0001\b\u0016\u0013\t1\u0012BA\u0002B]f\u0004\"\u0001\u0007\u0011\u000f\u0005eqbB\u0001\u000e\u001e\u001b\u0005Y\"B\u0001\u000f\f\u0003\u0019a$o\\8u}%\t!\"\u0003\u0002 \u0013\u00059\u0001/Y2lC\u001e,\u0017BA\u0011#\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ty\u0012\"\u0001\u0004%S:LG\u000f\n\u000b\u0002KA\u0011aBJ\u0005\u0003O%\u0011A!\u00168ji\u0006q\u0011\r\u001d9ms\u0012j7M\u0016&JIM\u0004HcA\u0013+_!)1F\u0001a\u0001Y\u0005\u0011a/\r\t\u0003\u001d5J!AL\u0005\u0003\t1{gn\u001a\u0005\u0006a\t\u0001\r!M\u0001\u0003mJ\u0002\"A\u0004\u001a\n\u0005MJ!aA%oi\u0006)\u0011\r\u001d9msR\u0019ACN\u001c\t\u000b-\u001a\u0001\u0019\u0001\u000b\t\u000bA\u001a\u0001\u0019\u0001\u000b)\u0005\u0001I\u0004C\u0001\u001e@\u001b\u0005Y$B\u0001\u001f>\u0003\u0011a\u0017M\\4\u000b\u0003y\nAA[1wC&\u0011\u0001i\u000f\u0002\u0014\rVt7\r^5p]\u0006d\u0017J\u001c;fe\u001a\f7-\u001a"
)
public interface JFunction2$mcVJI$sp extends Function2, Serializable {
   void apply$mcVJI$sp(final long v1, final int v2);

   // $FF: synthetic method
   static Object apply$(final JFunction2$mcVJI$sp $this, final Object v1, final Object v2) {
      return $this.apply(v1, v2);
   }

   default Object apply(final Object v1, final Object v2) {
      this.apply$mcVJI$sp(BoxesRunTime.unboxToLong(v1), BoxesRunTime.unboxToInt(v2));
      return BoxedUnit.UNIT;
   }

   static void $init$(final JFunction2$mcVJI$sp $this) {
   }
}

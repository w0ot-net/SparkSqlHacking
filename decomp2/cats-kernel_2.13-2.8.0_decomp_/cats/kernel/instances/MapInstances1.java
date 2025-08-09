package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006%\u0001!\t\u0001\u0006\u0005\u00061\u0001!\u0019!\u0007\u0005\u0006w\u0001!\u0019\u0001\u0010\u0002\u000e\u001b\u0006\u0004\u0018J\\:uC:\u001cWm]\u0019\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tQ\u0003\u0005\u0002\u000e-%\u0011qC\u0004\u0002\u0005+:LG/A\u000bdCR\u001c8*\u001a:oK2\u001cF\u000fZ#r\r>\u0014X*\u00199\u0016\u0007iac\u0007\u0006\u0002\u001cqA\u0019A$H\u0010\u000e\u0003\u001dI!AH\u0004\u0003\u0005\u0015\u000b\b\u0003\u0002\u0011(UUr!!I\u0013\u0011\u0005\trQ\"A\u0012\u000b\u0005\u0011\u001a\u0012A\u0002\u001fs_>$h(\u0003\u0002'\u001d\u00051\u0001K]3eK\u001aL!\u0001K\u0015\u0003\u00075\u000b\u0007O\u0003\u0002'\u001dA\u00111\u0006\f\u0007\u0001\t\u0015i#A1\u0001/\u0005\u0005Y\u0015CA\u00183!\ti\u0001'\u0003\u00022\u001d\t9aj\u001c;iS:<\u0007CA\u00074\u0013\t!dBA\u0002B]f\u0004\"a\u000b\u001c\u0005\u000b]\u0012!\u0019\u0001\u0018\u0003\u0003YCq!\u000f\u0002\u0002\u0002\u0003\u000f!(\u0001\u0006fm&$WM\\2fIQ\u00022\u0001H\u000f6\u0003e\u0019\u0017\r^:LKJtW\r\\*uI6{gn\\5e\r>\u0014X*\u00199\u0016\u0007u\u001aU\t\u0006\u0002?\rB\u0019AdP!\n\u0005\u0001;!AB'p]>LG\r\u0005\u0003!O\t#\u0005CA\u0016D\t\u0015i3A1\u0001/!\tYS\tB\u00038\u0007\t\u0007a\u0006C\u0004H\u0007\u0005\u0005\t9\u0001%\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0002\u001d\u0013\u0012K!AS\u0004\u0003\u0013M+W.[4s_V\u0004\b"
)
public interface MapInstances1 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForMap$(final MapInstances1 $this, final Eq evidence$4) {
      return $this.catsKernelStdEqForMap(evidence$4);
   }

   default Eq catsKernelStdEqForMap(final Eq evidence$4) {
      return new MapEq(evidence$4);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForMap$(final MapInstances1 $this, final Semigroup evidence$5) {
      return $this.catsKernelStdMonoidForMap(evidence$5);
   }

   default Monoid catsKernelStdMonoidForMap(final Semigroup evidence$5) {
      return new MapMonoid(evidence$5);
   }

   static void $init$(final MapInstances1 $this) {
   }
}

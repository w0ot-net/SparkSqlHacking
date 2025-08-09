package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003>\u0001\u0011\raHA\tMCjLH*[:u\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\r\u0005\u0002\u0015+5\tQ!\u0003\u0002\u0017\u000b\t\u0011B*\u0019>z\u0019&\u001cH/\u00138ti\u0006t7-Z:2\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0004\u0005\u0002\u000f5%\u00111d\u0004\u0002\u0005+:LG/A\u000fdCR\u001c8*\u001a:oK2\u001cF\u000fZ(sI\u0016\u0014hi\u001c:MCjLH*[:u+\tq\u0012\u0007\u0006\u0002 uA\u0019\u0001%I\u0012\u000e\u0003\u001dI!AI\u0004\u0003\u000b=\u0013H-\u001a:\u0011\u0007\u0011bsF\u0004\u0002&U9\u0011a%K\u0007\u0002O)\u0011\u0001fC\u0001\u0007yI|w\u000e\u001e \n\u0003AI!aK\b\u0002\u000fA\f7m[1hK&\u0011QF\f\u0002\t\u0019\u0006T\u0018\u0010T5ti*\u00111f\u0004\t\u0003aEb\u0001\u0001B\u00033\u0005\t\u00071GA\u0001B#\t!t\u0007\u0005\u0002\u000fk%\u0011ag\u0004\u0002\b\u001d>$\b.\u001b8h!\tq\u0001(\u0003\u0002:\u001f\t\u0019\u0011I\\=\t\u000fm\u0012\u0011\u0011!a\u0002y\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u0001\ns&\u0001\u0010dCR\u001c8*\u001a:oK2\u001cF\u000fZ'p]>LGMR8s\u0019\u0006T\u0018\u0010T5tiV\u0011q(R\u000b\u0002\u0001B\u0019\u0001%Q\"\n\u0005\t;!AB'p]>LG\rE\u0002%Y\u0011\u0003\"\u0001M#\u0005\u000bI\u001a!\u0019A\u001a"
)
public interface LazyListInstances extends LazyListInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForLazyList$(final LazyListInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForLazyList(evidence$1);
   }

   default Order catsKernelStdOrderForLazyList(final Order evidence$1) {
      return new LazyListOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForLazyList$(final LazyListInstances $this) {
      return $this.catsKernelStdMonoidForLazyList();
   }

   default Monoid catsKernelStdMonoidForLazyList() {
      return new LazyListMonoid();
   }

   static void $init$(final LazyListInstances $this) {
   }
}

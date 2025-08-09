package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003:\u0001\u0011\r!H\u0001\bRk\u0016,X-\u00138ti\u0006t7-Z:\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u00192\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0011A#F\u0007\u0002\u000b%\u0011a#\u0002\u0002\u0010#V,W/Z%ogR\fgnY3tc\u00051A%\u001b8ji\u0012\"\u0012!\u0007\t\u0003\u001diI!aG\b\u0003\tUs\u0017\u000e^\u0001\u001bG\u0006$8oS3s]\u0016d7\u000b\u001e3Pe\u0012,'OR8s#V,W/Z\u000b\u0003=5\"\"a\b\u001c\u0011\u0007\u0001\n3%D\u0001\b\u0013\t\u0011sAA\u0003Pe\u0012,'\u000fE\u0002%S-j\u0011!\n\u0006\u0003M\u001d\n\u0011\"[7nkR\f'\r\\3\u000b\u0005!z\u0011AC2pY2,7\r^5p]&\u0011!&\n\u0002\u0006#V,W/\u001a\t\u0003Y5b\u0001\u0001B\u0003/\u0005\t\u0007qFA\u0001B#\t\u00014\u0007\u0005\u0002\u000fc%\u0011!g\u0004\u0002\b\u001d>$\b.\u001b8h!\tqA'\u0003\u00026\u001f\t\u0019\u0011I\\=\t\u000f]\u0012\u0011\u0011!a\u0002q\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u0001\n3&A\u000edCR\u001c8*\u001a:oK2\u001cF\u000fZ'p]>LGMR8s#V,W/Z\u000b\u0003w\u0005+\u0012\u0001\u0010\t\u0004Auz\u0014B\u0001 \b\u0005\u0019iuN\\8jIB\u0019A%\u000b!\u0011\u00051\nE!\u0002\u0018\u0004\u0005\u0004y\u0003F\u0001\u0001D!\t!5J\u0004\u0002F\u0011:\u0011\u0001ER\u0005\u0003\u000f\u001e\taaY8na\u0006$\u0018BA%K\u0003Q\u00198-\u00197b-\u0016\u00148/[8o'B,7-\u001b4jG*\u0011qiB\u0005\u0003\u00196\u0013!g];qaJ,7o]+okN,G-S7q_J$x+\u0019:oS:<gi\u001c:TG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0003\u0013*\u0003"
)
public interface QueueInstances extends QueueInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForQueue$(final QueueInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForQueue(evidence$1);
   }

   default Order catsKernelStdOrderForQueue(final Order evidence$1) {
      return new QueueOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForQueue$(final QueueInstances $this) {
      return $this.catsKernelStdMonoidForQueue();
   }

   default Monoid catsKernelStdMonoidForQueue() {
      return new QueueMonoid();
   }

   static void $init$(final QueueInstances $this) {
   }
}

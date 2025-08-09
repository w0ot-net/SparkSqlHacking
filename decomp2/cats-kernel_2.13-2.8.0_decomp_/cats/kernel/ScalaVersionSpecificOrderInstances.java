package cats.kernel;

import cats.kernel.instances.stream.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0005\u0006\u000b\u0002!\u0019A\u0012\u0005\u0006#\u0002!\u0019A\u0015\u0002#'\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2Pe\u0012,'/\u00138ti\u0006t7-Z:\u000b\u0005\u001dA\u0011AB6fe:,GNC\u0001\n\u0003\u0011\u0019\u0017\r^:\u0014\u0007\u0001Y\u0011\u0003\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0003%Mi\u0011AB\u0005\u0003)\u0019\u0011\u0011fU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c\u0007+\u0019:uS\u0006dwJ\u001d3fe&s7\u000f^1oG\u0016\u001c\u0018A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003a\u0001\"\u0001D\r\n\u0005ii!\u0001B+oSR\f\u0001dY1ug.+'O\\3m\u001fJ$WM\u001d$peN#(/Z1n+\tir\u0006\u0006\u0002\u001fqA\u0019!cH\u0011\n\u0005\u00012!!B(sI\u0016\u0014\bc\u0001\u0012+[9\u00111\u0005\u000b\b\u0003I\u001dj\u0011!\n\u0006\u0003MY\ta\u0001\u0010:p_Rt\u0014\"\u0001\b\n\u0005%j\u0011a\u00029bG.\fw-Z\u0005\u0003W1\u0012aa\u0015;sK\u0006l'BA\u0015\u000e!\tqs\u0006\u0004\u0001\u0005\u000bA\u0012!\u0019A\u0019\u0003\u0003\u0005\u000b\"AM\u001b\u0011\u00051\u0019\u0014B\u0001\u001b\u000e\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0004\u001c\n\u0005]j!aA!os\"9\u0011HAA\u0001\u0002\bQ\u0014AC3wS\u0012,gnY3%cA\u0019!cH\u0017)\r\tat\b\u0011\"D!\taQ(\u0003\u0002?\u001b\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\n\u0011)\u0001\u0010Vg\u0016\u00043-\u0019;t\u0017\u0016\u0014h.\u001a7Pe\u0012,'OR8s\u0019\u0006T\u0018\u0010T5ti\u0006)1/\u001b8dK\u0006\nA)A\u00034]Ar\u0003'\u0001\u000edCR\u001c8*\u001a:oK2|%\u000fZ3s\r>\u0014H*\u0019>z\u0019&\u001cH/\u0006\u0002H\u001bR\u0011\u0001J\u0014\t\u0004%}I\u0005c\u0001\u0012K\u0019&\u00111\n\f\u0002\t\u0019\u0006T\u0018\u0010T5tiB\u0011a&\u0014\u0003\u0006a\r\u0011\r!\r\u0005\b\u001f\u000e\t\t\u0011q\u0001Q\u0003))g/\u001b3f]\u000e,GE\r\t\u0004%}a\u0015AG2biN\\UM\u001d8fY>\u0013H-\u001a:G_J\f%O]1z'\u0016\fXCA*_)\t!v\fE\u0002\u0013?U\u00032AV.^\u001b\u00059&B\u0001-Z\u0003%IW.\\;uC\ndWM\u0003\u0002[\u001b\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005q;&\u0001C!se\u0006L8+Z9\u0011\u00059rF!\u0002\u0019\u0005\u0005\u0004\t\u0004b\u00021\u0005\u0003\u0003\u0005\u001d!Y\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004c\u0001\n ;\u0002"
)
public interface ScalaVersionSpecificOrderInstances extends ScalaVersionSpecificPartialOrderInstances {
   // $FF: synthetic method
   static Order catsKernelOrderForStream$(final ScalaVersionSpecificOrderInstances $this, final Order evidence$1) {
      return $this.catsKernelOrderForStream(evidence$1);
   }

   /** @deprecated */
   default Order catsKernelOrderForStream(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForStream(evidence$1);
   }

   // $FF: synthetic method
   static Order catsKernelOrderForLazyList$(final ScalaVersionSpecificOrderInstances $this, final Order evidence$2) {
      return $this.catsKernelOrderForLazyList(evidence$2);
   }

   default Order catsKernelOrderForLazyList(final Order evidence$2) {
      return cats.kernel.instances.lazyList.package$.MODULE$.catsKernelStdOrderForLazyList(evidence$2);
   }

   // $FF: synthetic method
   static Order catsKernelOrderForArraySeq$(final ScalaVersionSpecificOrderInstances $this, final Order evidence$3) {
      return $this.catsKernelOrderForArraySeq(evidence$3);
   }

   default Order catsKernelOrderForArraySeq(final Order evidence$3) {
      return cats.kernel.instances.arraySeq.package$.MODULE$.catsKernelStdOrderForArraySeq(evidence$3);
   }

   static void $init$(final ScalaVersionSpecificOrderInstances $this) {
   }
}

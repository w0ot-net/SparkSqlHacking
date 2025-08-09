package cats.kernel;

import cats.kernel.instances.stream.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0005\u0006\u000b\u0002!\u0019A\u0012\u0005\u0006#\u0002!\u0019A\u0015\u0002\"'\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2ICND\u0017J\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\taa[3s]\u0016d'\"A\u0005\u0002\t\r\fGo]\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u0013'5\ta!\u0003\u0002\u0015\r\ty2kY1mCZ+'o]5p]N\u0003XmY5gS\u000e,\u0015/\u00138ti\u0006t7-Z:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\u0007\t\u0003\u0019eI!AG\u0007\u0003\tUs\u0017\u000e^\u0001\u0018G\u0006$8oS3s]\u0016d\u0007*Y:i\r>\u00148\u000b\u001e:fC6,\"!H\u0018\u0015\u0005yA\u0004c\u0001\n C%\u0011\u0001E\u0002\u0002\u0005\u0011\u0006\u001c\b\u000eE\u0002#U5r!a\t\u0015\u000f\u0005\u0011:S\"A\u0013\u000b\u0005\u00192\u0012A\u0002\u001fs_>$h(C\u0001\u000f\u0013\tIS\"A\u0004qC\u000e\\\u0017mZ3\n\u0005-b#AB*ue\u0016\fWN\u0003\u0002*\u001bA\u0011af\f\u0007\u0001\t\u0015\u0001$A1\u00012\u0005\u0005\t\u0015C\u0001\u001a6!\ta1'\u0003\u00025\u001b\t9aj\u001c;iS:<\u0007C\u0001\u00077\u0013\t9TBA\u0002B]fDq!\u000f\u0002\u0002\u0002\u0003\u000f!(\u0001\u0006fm&$WM\\2fI]\u00022AE\u0010.Q\u0019\u0011Ah\u0010!C\u0007B\u0011A\"P\u0005\u0003}5\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013!Q\u0001\u001e+N,\u0007eY1ug.+'O\\3m\u0011\u0006\u001c\bNR8s\u0019\u0006T\u0018\u0010T5ti\u0006)1/\u001b8dK\u0006\nA)A\u00034]Ar\u0003'A\rdCR\u001c8*\u001a:oK2D\u0015m\u001d5G_Jd\u0015M_=MSN$XCA$N)\tAe\nE\u0002\u0013?%\u00032A\t&M\u0013\tYEF\u0001\u0005MCjLH*[:u!\tqS\nB\u00031\u0007\t\u0007\u0011\u0007C\u0004P\u0007\u0005\u0005\t9\u0001)\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\bE\u0002\u0013?1\u000b\u0011dY1ug.+'O\\3m\u0011\u0006\u001c\bNR8s\u0003J\u0014\u0018-_*fcV\u00111K\u0018\u000b\u0003)~\u00032AE\u0010V!\r16,X\u0007\u0002/*\u0011\u0001,W\u0001\nS6lW\u000f^1cY\u0016T!AW\u0007\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002]/\nA\u0011I\u001d:bsN+\u0017\u000f\u0005\u0002/=\u0012)\u0001\u0007\u0002b\u0001c!9\u0001\rBA\u0001\u0002\b\t\u0017AC3wS\u0012,gnY3%sA\u0019!cH/"
)
public interface ScalaVersionSpecificHashInstances extends ScalaVersionSpecificEqInstances {
   // $FF: synthetic method
   static Hash catsKernelHashForStream$(final ScalaVersionSpecificHashInstances $this, final Hash evidence$7) {
      return $this.catsKernelHashForStream(evidence$7);
   }

   /** @deprecated */
   default Hash catsKernelHashForStream(final Hash evidence$7) {
      return package$.MODULE$.catsKernelStdHashForStream(evidence$7);
   }

   // $FF: synthetic method
   static Hash catsKernelHashForLazyList$(final ScalaVersionSpecificHashInstances $this, final Hash evidence$8) {
      return $this.catsKernelHashForLazyList(evidence$8);
   }

   default Hash catsKernelHashForLazyList(final Hash evidence$8) {
      return cats.kernel.instances.lazyList.package$.MODULE$.catsKernelStdHashForLazyList(evidence$8);
   }

   // $FF: synthetic method
   static Hash catsKernelHashForArraySeq$(final ScalaVersionSpecificHashInstances $this, final Hash evidence$9) {
      return $this.catsKernelHashForArraySeq(evidence$9);
   }

   default Hash catsKernelHashForArraySeq(final Hash evidence$9) {
      return cats.kernel.instances.arraySeq.package$.MODULE$.catsKernelStdHashForArraySeq(evidence$9);
   }

   static void $init$(final ScalaVersionSpecificHashInstances $this) {
   }
}

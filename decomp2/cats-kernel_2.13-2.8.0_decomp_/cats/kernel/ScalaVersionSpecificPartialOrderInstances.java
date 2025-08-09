package cats.kernel;

import cats.kernel.instances.stream.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0005\u0006\u000b\u0002!\u0019A\u0012\u0005\u0006#\u0002!\u0019A\u0015\u0002*'\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2QCJ$\u0018.\u00197Pe\u0012,'/\u00138ti\u0006t7-Z:\u000b\u0005\u001dA\u0011AB6fe:,GNC\u0001\n\u0003\u0011\u0019\u0017\r^:\u0014\u0007\u0001Y\u0011\u0003\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0003%Mi\u0011AB\u0005\u0003)\u0019\u0011\u0011eU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c\u0007*Y:i\u0013:\u001cH/\u00198dKN\fa\u0001J5oSR$3\u0001\u0001\u000b\u00021A\u0011A\"G\u0005\u000355\u0011A!\u00168ji\u0006y2-\u0019;t\u0017\u0016\u0014h.\u001a7QCJ$\u0018.\u00197Pe\u0012,'OR8s'R\u0014X-Y7\u0016\u0005uyCC\u0001\u00109!\r\u0011r$I\u0005\u0003A\u0019\u0011A\u0002U1si&\fGn\u0014:eKJ\u00042A\t\u0016.\u001d\t\u0019\u0003F\u0004\u0002%O5\tQE\u0003\u0002'-\u00051AH]8pizJ\u0011AD\u0005\u0003S5\tq\u0001]1dW\u0006<W-\u0003\u0002,Y\t11\u000b\u001e:fC6T!!K\u0007\u0011\u00059zC\u0002\u0001\u0003\u0006a\t\u0011\r!\r\u0002\u0002\u0003F\u0011!'\u000e\t\u0003\u0019MJ!\u0001N\u0007\u0003\u000f9{G\u000f[5oOB\u0011ABN\u0005\u0003o5\u00111!\u00118z\u0011\u001dI$!!AA\u0004i\n!\"\u001a<jI\u0016t7-\u001a\u00135!\r\u0011r$\f\u0015\u0007\u0005qz\u0004IQ\"\u0011\u00051i\u0014B\u0001 \u000e\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005\t\u0015!J+tK\u0002\u001a\u0017\r^:LKJtW\r\u001c)beRL\u0017\r\\(sI\u0016\u0014hi\u001c:MCjLH*[:u\u0003\u0015\u0019\u0018N\\2fC\u0005!\u0015!B\u001a/a9\u0002\u0014!I2biN\\UM\u001d8fYB\u000b'\u000f^5bY>\u0013H-\u001a:G_Jd\u0015M_=MSN$XCA$N)\tAe\nE\u0002\u0013?%\u00032A\t&M\u0013\tYEF\u0001\u0005MCjLH*[:u!\tqS\nB\u00031\u0007\t\u0007\u0011\u0007C\u0004P\u0007\u0005\u0005\t9\u0001)\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0002\u0013?1\u000b\u0011eY1ug.+'O\\3m!\u0006\u0014H/[1m\u001fJ$WM\u001d$pe\u0006\u0013(/Y=TKF,\"a\u00150\u0015\u0005Q{\u0006c\u0001\n +B\u0019akW/\u000e\u0003]S!\u0001W-\u0002\u0013%lW.\u001e;bE2,'B\u0001.\u000e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u00039^\u0013\u0001\"\u0011:sCf\u001cV-\u001d\t\u0003]y#Q\u0001\r\u0003C\u0002EBq\u0001\u0019\u0003\u0002\u0002\u0003\u000f\u0011-\u0001\u0006fm&$WM\\2fIY\u00022AE\u0010^\u0001"
)
public interface ScalaVersionSpecificPartialOrderInstances extends ScalaVersionSpecificHashInstances {
   // $FF: synthetic method
   static PartialOrder catsKernelPartialOrderForStream$(final ScalaVersionSpecificPartialOrderInstances $this, final PartialOrder evidence$4) {
      return $this.catsKernelPartialOrderForStream(evidence$4);
   }

   /** @deprecated */
   default PartialOrder catsKernelPartialOrderForStream(final PartialOrder evidence$4) {
      return package$.MODULE$.catsKernelStdPartialOrderForStream(evidence$4);
   }

   // $FF: synthetic method
   static PartialOrder catsKernelPartialOrderForLazyList$(final ScalaVersionSpecificPartialOrderInstances $this, final PartialOrder evidence$5) {
      return $this.catsKernelPartialOrderForLazyList(evidence$5);
   }

   default PartialOrder catsKernelPartialOrderForLazyList(final PartialOrder evidence$5) {
      return cats.kernel.instances.lazyList.package$.MODULE$.catsKernelStdPartialOrderForLazyList(evidence$5);
   }

   // $FF: synthetic method
   static PartialOrder catsKernelPartialOrderForArraySeq$(final ScalaVersionSpecificPartialOrderInstances $this, final PartialOrder evidence$6) {
      return $this.catsKernelPartialOrderForArraySeq(evidence$6);
   }

   default PartialOrder catsKernelPartialOrderForArraySeq(final PartialOrder evidence$6) {
      return cats.kernel.instances.arraySeq.package$.MODULE$.catsKernelStdPartialOrderForArraySeq(evidence$6);
   }

   static void $init$(final ScalaVersionSpecificPartialOrderInstances $this) {
   }
}

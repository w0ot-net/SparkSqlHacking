package cats.kernel;

import cats.kernel.instances.stream.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0005\u0006\u0005\u0002!\u0019a\u0011\u0005\u0006\u001d\u0002!\u0019a\u0014\u0002 '\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2Fc&s7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8C\u0001\u0001\f!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0015!\taQ#\u0003\u0002\u0017\u001b\t!QK\\5u\u0003U\u0019\u0017\r^:LKJtW\r\\#r\r>\u00148\u000b\u001e:fC6,\"!\u0007\u0017\u0015\u0005i)\u0004cA\u000e\u001d=5\ta!\u0003\u0002\u001e\r\t\u0011Q)\u001d\t\u0004?\u001dRcB\u0001\u0011&\u001d\t\tC%D\u0001#\u0015\t\u0019##\u0001\u0004=e>|GOP\u0005\u0002\u001d%\u0011a%D\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0013F\u0001\u0004TiJ,\u0017-\u001c\u0006\u0003M5\u0001\"a\u000b\u0017\r\u0001\u0011)QF\u0001b\u0001]\t\t\u0011)\u0005\u00020eA\u0011A\u0002M\u0005\u0003c5\u0011qAT8uQ&tw\r\u0005\u0002\rg%\u0011A'\u0004\u0002\u0004\u0003:L\bb\u0002\u001c\u0003\u0003\u0003\u0005\u001daN\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007E\u0002\u001c9)BcAA\u001d={}\u0002\u0005C\u0001\u0007;\u0013\tYTB\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-I\u0001?\u0003m)6/\u001a\u0011dCR\u001c8*\u001a:oK2,\u0015OR8s\u0019\u0006T\u0018\u0010T5ti\u0006)1/\u001b8dK\u0006\n\u0011)A\u00034]Ar\u0003'A\fdCR\u001c8*\u001a:oK2,\u0015OR8s\u0019\u0006T\u0018\u0010T5tiV\u0011AI\u0013\u000b\u0003\u000b.\u00032a\u0007\u000fG!\ryr)S\u0005\u0003\u0011&\u0012\u0001\u0002T1{s2K7\u000f\u001e\t\u0003W)#Q!L\u0002C\u00029Bq\u0001T\u0002\u0002\u0002\u0003\u000fQ*A\u0006fm&$WM\\2fIE\n\u0004cA\u000e\u001d\u0013\u000692-\u0019;t\u0017\u0016\u0014h.\u001a7Fc\u001a{'/\u0011:sCf\u001cV-]\u000b\u0003!n#\"!\u0015/\u0011\u0007ma\"\u000bE\u0002T1jk\u0011\u0001\u0016\u0006\u0003+Z\u000b\u0011\"[7nkR\f'\r\\3\u000b\u0005]k\u0011AC2pY2,7\r^5p]&\u0011\u0011\f\u0016\u0002\t\u0003J\u0014\u0018-_*fcB\u00111f\u0017\u0003\u0006[\u0011\u0011\rA\f\u0005\b;\u0012\t\t\u0011q\u0001_\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\u0007ma\"\f"
)
public interface ScalaVersionSpecificEqInstances {
   // $FF: synthetic method
   static Eq catsKernelEqForStream$(final ScalaVersionSpecificEqInstances $this, final Eq evidence$10) {
      return $this.catsKernelEqForStream(evidence$10);
   }

   /** @deprecated */
   default Eq catsKernelEqForStream(final Eq evidence$10) {
      return package$.MODULE$.catsKernelStdEqForStream(evidence$10);
   }

   // $FF: synthetic method
   static Eq catsKernelEqForLazyList$(final ScalaVersionSpecificEqInstances $this, final Eq evidence$11) {
      return $this.catsKernelEqForLazyList(evidence$11);
   }

   default Eq catsKernelEqForLazyList(final Eq evidence$11) {
      return cats.kernel.instances.lazyList.package$.MODULE$.catsKernelStdEqForLazyList(evidence$11);
   }

   // $FF: synthetic method
   static Eq catsKernelEqForArraySeq$(final ScalaVersionSpecificEqInstances $this, final Eq evidence$12) {
      return $this.catsKernelEqForArraySeq(evidence$12);
   }

   default Eq catsKernelEqForArraySeq(final Eq evidence$12) {
      return cats.kernel.instances.arraySeq.package$.MODULE$.catsKernelStdEqForArraySeq(evidence$12);
   }

   static void $init$(final ScalaVersionSpecificEqInstances $this) {
   }
}

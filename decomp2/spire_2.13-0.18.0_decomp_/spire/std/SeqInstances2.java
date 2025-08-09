package spire.std;

import algebra.ring.Field;
import cats.kernel.Order;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003T\u0001\u0011\rAKA\u0007TKFLen\u001d;b]\u000e,7O\r\u0006\u0003\r\u001d\t1a\u001d;e\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u0013'5\tQ!\u0003\u0002\u0015\u000b\ti1+Z9J]N$\u0018M\\2fgF\na\u0001J5oSR$C#A\f\u0011\u00051A\u0012BA\r\u000e\u0005\u0011)f.\u001b;\u0002)M+\u0017/\u00138oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f+\ra\"\u0005\f\u000b\u0004;qr\u0005\u0003\u0002\n\u001fA-J!aH\u0003\u0003)M+\u0017/\u00138oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f!\t\t#\u0005\u0004\u0001\u0005\u000b\r\u0012!\u0019\u0001\u0013\u0003\u0003\u0005\u000b\"!\n\u0015\u0011\u000511\u0013BA\u0014\u000e\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001D\u0015\n\u0005)j!aA!osB\u0019\u0011\u0005\f\u0011\u0005\u000b5\u0012!\u0019\u0001\u0018\u0003\u0005\r\u001bUCA\u00188#\t)\u0003\u0007E\u00032iYB4(D\u00013\u0015\t\u0019T\"\u0001\u0006d_2dWm\u0019;j_:L!!\u000e\u001a\u0003\rM+\u0017o\u00149t!\t\ts\u0007B\u0003$Y\t\u0007A\u0005\u0005\u00022s%\u0011!H\r\u0002\u0004'\u0016\f\bcA\u0011-m!)QH\u0001a\u0002}\u00051a-[3mIB\u00022aP&!\u001d\t\u0001\u0005J\u0004\u0002B\r:\u0011!)R\u0007\u0002\u0007*\u0011A)C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!aR\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011JS\u0001\ba\u0006\u001c7.Y4f\u0015\t9u!\u0003\u0002M\u001b\n)a)[3mI*\u0011\u0011J\u0013\u0005\u0006\u001f\n\u0001\u001d\u0001U\u0001\u0005G\n4\u0007\u0007\u0005\u00032#\u0002Z\u0013B\u0001*3\u0005\u001d1\u0015m\u0019;pef\f\u0001bU3r\u001fJ$WM]\u000b\u0004+jcFC\u0001,d!\u0011\u0011r+W.\n\u0005a+!\u0001C*fc>\u0013H-\u001a:\u0011\u0005\u0005RF!B\u0012\u0004\u0005\u0004!\u0003cA\u0011]3\u0012)Qf\u0001b\u0001;V\u0011a,Y\t\u0003K}\u0003R!\r\u001baq\t\u0004\"!I1\u0005\u000b\rb&\u0019\u0001\u0013\u0011\u0007\u0005b\u0006\rC\u0003e\u0007\u0001\u000fQ-\u0001\u0002BaA\u0019qHZ-\n\u0005\u001dl%!B(sI\u0016\u0014\b"
)
public interface SeqInstances2 extends SeqInstances1 {
   // $FF: synthetic method
   static SeqInnerProductSpace SeqInnerProductSpace$(final SeqInstances2 $this, final Field field0, final Factory cbf0) {
      return $this.SeqInnerProductSpace(field0, cbf0);
   }

   default SeqInnerProductSpace SeqInnerProductSpace(final Field field0, final Factory cbf0) {
      return new SeqInnerProductSpace(field0, cbf0);
   }

   // $FF: synthetic method
   static SeqOrder SeqOrder$(final SeqInstances2 $this, final Order A0) {
      return $this.SeqOrder(A0);
   }

   default SeqOrder SeqOrder(final Order A0) {
      return new SeqOrder(A0);
   }

   static void $init$(final SeqInstances2 $this) {
   }
}

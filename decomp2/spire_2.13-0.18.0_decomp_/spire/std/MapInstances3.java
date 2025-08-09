package spire.std;

import algebra.ring.Field;
import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003A\u0001\u0011\r\u0011IA\u0007NCBLen\u001d;b]\u000e,7o\r\u0006\u0003\r\u001d\t1a\u001d;e\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u0013'5\tQ!\u0003\u0002\u0015\u000b\tiQ*\u00199J]N$\u0018M\\2fgJ\na\u0001J5oSR$C#A\f\u0011\u00051A\u0012BA\r\u000e\u0005\u0011)f.\u001b;\u0002)5\u000b\u0007/\u00138oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f+\ra\"\u0005\f\u000b\u0003;9\u0002BA\u0005\u0010!W%\u0011q$\u0002\u0002\u0015\u001b\u0006\u0004\u0018J\u001c8feB\u0013x\u000eZ;diN\u0003\u0018mY3\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\t\u0011\r\u0001\n\u0002\u0002\u0017F\u0011Q\u0005\u000b\t\u0003\u0019\u0019J!aJ\u0007\u0003\u000f9{G\u000f[5oOB\u0011A\"K\u0005\u0003U5\u00111!\u00118z!\t\tC\u0006B\u0003.\u0005\t\u0007AEA\u0001W\u0011\u001dy#!!AA\u0004A\n!\"\u001a<jI\u0016t7-\u001a\u00138!\r\tTh\u000b\b\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005YJ\u0011A\u0002\u001fs_>$h(C\u0001\t\u0013\tIt!A\u0004bY\u001e,'M]1\n\u0005mb\u0014a\u00029bG.\fw-\u001a\u0006\u0003s\u001dI!AP \u0003\u000b\u0019KW\r\u001c3\u000b\u0005mb\u0014!B'ba\u0016\u000bXc\u0001\"H\u0013R\u00111I\u0013\t\u0005%\u00113\u0005*\u0003\u0002F\u000b\t)Q*\u00199FcB\u0011\u0011e\u0012\u0003\u0006G\r\u0011\r\u0001\n\t\u0003C%#Q!L\u0002C\u0002\u0011BQaS\u0002A\u00041\u000b!A\u0016\u0019\u0011\u0007Ej\u0005*\u0003\u0002O\u007f\t\u0011Q)\u001d"
)
public interface MapInstances3 extends MapInstances2 {
   // $FF: synthetic method
   static MapInnerProductSpace MapInnerProductSpace$(final MapInstances3 $this, final Field evidence$7) {
      return $this.MapInnerProductSpace(evidence$7);
   }

   default MapInnerProductSpace MapInnerProductSpace(final Field evidence$7) {
      return new MapInnerProductSpace(evidence$7);
   }

   // $FF: synthetic method
   static MapEq MapEq$(final MapInstances3 $this, final Eq V0) {
      return $this.MapEq(V0);
   }

   default MapEq MapEq(final Eq V0) {
      return new MapEq(V0);
   }

   static void $init$(final MapInstances3 $this) {
   }
}

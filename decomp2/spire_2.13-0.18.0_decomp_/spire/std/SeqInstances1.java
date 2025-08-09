package spire.std;

import algebra.ring.Field;
import cats.kernel.Eq;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.NotGiven;

@ScalaSignature(
   bytes = "\u0006\u0005I4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003^\u0001\u0011\raLA\u0007TKFLen\u001d;b]\u000e,7/\r\u0006\u0003\r\u001d\t1a\u001d;e\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u0013'5\tQ!\u0003\u0002\u0015\u000b\ti1+Z9J]N$\u0018M\\2fgB\na\u0001J5oSR$C#A\f\u0011\u00051A\u0012BA\r\u000e\u0005\u0011)f.\u001b;\u0002\u001dM+\u0017OV3di>\u00148\u000b]1dKV\u0019AD\t\u0017\u0015\tuadj\u0015\t\u0005%y\u00013&\u0003\u0002 \u000b\tq1+Z9WK\u000e$xN]*qC\u000e,\u0007CA\u0011#\u0019\u0001!Qa\t\u0002C\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"\u0001\u0004\u0014\n\u0005\u001dj!a\u0002(pi\"Lgn\u001a\t\u0003\u0019%J!AK\u0007\u0003\u0007\u0005s\u0017\u0010E\u0002\"Y\u0001\"Q!\f\u0002C\u00029\u0012!aQ\"\u0016\u0005=:\u0014CA\u00131!\u0015\tDG\u000e\u001d<\u001b\u0005\u0011$BA\u001a\u000e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003kI\u0012aaU3r\u001fB\u001c\bCA\u00118\t\u0015\u0019CF1\u0001%!\t\t\u0014(\u0003\u0002;e\t\u00191+Z9\u0011\u0007\u0005bc\u0007C\u0003>\u0005\u0001\u000fa(\u0001\u0004gS\u0016dG\r\r\t\u0004\u007f-\u0003cB\u0001!I\u001d\t\teI\u0004\u0002C\u000b6\t1I\u0003\u0002E\u0013\u00051AH]8pizJ\u0011\u0001C\u0005\u0003\u000f\u001e\tq!\u00197hK\n\u0014\u0018-\u0003\u0002J\u0015\u00069\u0001/Y2lC\u001e,'BA$\b\u0013\taUJA\u0003GS\u0016dGM\u0003\u0002J\u0015\")qJ\u0001a\u0002!\u0006!1M\u001941!\u0011\t\u0014\u000bI\u0016\n\u0005I\u0013$a\u0002$bGR|'/\u001f\u0005\u0006)\n\u0001\u001d!V\u0001\u0003KZ\u00042AV,Z\u001b\u00059\u0011B\u0001-\b\u0005!qu\u000e^$jm\u0016t\u0007\u0003\u0002.\\W\u0001j\u0011AS\u0005\u00039*\u0013\u0011CT8s[\u0016$g+Z2u_J\u001c\u0006/Y2f\u0003\u0015\u0019V-]#r+\ryFM\u001a\u000b\u0003A6\u0004BAE1dK&\u0011!-\u0002\u0002\u0006'\u0016\fX)\u001d\t\u0003C\u0011$QaI\u0002C\u0002\u0011\u00022!\t4d\t\u0015i3A1\u0001h+\tA7.\u0005\u0002&SB)\u0011\u0007\u000e69YB\u0011\u0011e\u001b\u0003\u0006G\u0019\u0014\r\u0001\n\t\u0004C\u0019T\u0007\"\u00028\u0004\u0001\by\u0017AA!1!\ry\u0004oY\u0005\u0003c6\u0013!!R9"
)
public interface SeqInstances1 extends SeqInstances0 {
   // $FF: synthetic method
   static SeqVectorSpace SeqVectorSpace$(final SeqInstances1 $this, final Field field0, final Factory cbf0, final NotGiven ev) {
      return $this.SeqVectorSpace(field0, cbf0, ev);
   }

   default SeqVectorSpace SeqVectorSpace(final Field field0, final Factory cbf0, final NotGiven ev) {
      return new SeqVectorSpace(field0, cbf0);
   }

   // $FF: synthetic method
   static SeqEq SeqEq$(final SeqInstances1 $this, final Eq A0) {
      return $this.SeqEq(A0);
   }

   default SeqEq SeqEq(final Eq A0) {
      return new SeqEq(A0);
   }

   static void $init$(final SeqInstances1 $this) {
   }
}

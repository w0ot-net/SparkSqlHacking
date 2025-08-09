package spire.std;

import algebra.ring.Field;
import cats.kernel.Group;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003A\u0001\u0011\r\u0011IA\u0007NCBLen\u001d;b]\u000e,7O\r\u0006\u0003\r\u001d\t1a\u001d;e\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u0013'5\tQ!\u0003\u0002\u0015\u000b\tiQ*\u00199J]N$\u0018M\\2fgF\na\u0001J5oSR$C#A\f\u0011\u00051A\u0012BA\r\u000e\u0005\u0011)f.\u001b;\u0002\u00115\u000b\u0007o\u0012:pkB,2\u0001\b\u0012-)\tib\u0006\u0005\u0003\u0013=\u0001Z\u0013BA\u0010\u0006\u0005!i\u0015\r]$s_V\u0004\bCA\u0011#\u0019\u0001!Qa\t\u0002C\u0002\u0011\u0012\u0011aS\t\u0003K!\u0002\"\u0001\u0004\u0014\n\u0005\u001dj!a\u0002(pi\"Lgn\u001a\t\u0003\u0019%J!AK\u0007\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\"Y\u0011)QF\u0001b\u0001I\t\ta\u000bC\u00040\u0005\u0005\u0005\t9\u0001\u0019\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u00022{-r!A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c\n\u0003\u0019a$o\\8u}%\t\u0001\"\u0003\u0002:\u000f\u00059\u0011\r\\4fEJ\f\u0017BA\u001e=\u0003\u001d\u0001\u0018mY6bO\u0016T!!O\u0004\n\u0005yz$!B$s_V\u0004(BA\u001e=\u00039i\u0015\r\u001d,fGR|'o\u00159bG\u0016,2AQ$J)\t\u0019%\n\u0005\u0003\u0013\t\u001aC\u0015BA#\u0006\u00059i\u0015\r\u001d,fGR|'o\u00159bG\u0016\u0004\"!I$\u0005\u000b\r\u001a!\u0019\u0001\u0013\u0011\u0005\u0005JE!B\u0017\u0004\u0005\u0004!\u0003bB&\u0004\u0003\u0003\u0005\u001d\u0001T\u0001\u000bKZLG-\u001a8dK\u00122\u0004cA\u0019N\u0011&\u0011aj\u0010\u0002\u0006\r&,G\u000e\u001a"
)
public interface MapInstances2 extends MapInstances1 {
   // $FF: synthetic method
   static MapGroup MapGroup$(final MapInstances2 $this, final Group evidence$5) {
      return $this.MapGroup(evidence$5);
   }

   default MapGroup MapGroup(final Group evidence$5) {
      return new MapGroup(evidence$5);
   }

   // $FF: synthetic method
   static MapVectorSpace MapVectorSpace$(final MapInstances2 $this, final Field evidence$6) {
      return $this.MapVectorSpace(evidence$6);
   }

   default MapVectorSpace MapVectorSpace(final Field evidence$6) {
      return new MapVectorSpace(evidence$6);
   }

   static void $init$(final MapInstances2 $this) {
   }
}

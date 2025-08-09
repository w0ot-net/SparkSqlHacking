package spire.std;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\u0007NCBLen\u001d;b]\u000e,7/\r\u0006\u0003\u000b\u0019\t1a\u001d;e\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0004\u0001)\u0001\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012%5\tA!\u0003\u0002\u0014\t\tiQ*\u00199J]N$\u0018M\\2fgB\na\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002\u000f5\u000b\u0007o\u0011*oOV\u00191$I\u0016\u0015\u0005qi\u0003\u0003B\t\u001e?)J!A\b\u0003\u0003\u000f5\u000b\u0007o\u0011*oOB\u0011\u0001%\t\u0007\u0001\t\u0015\u0011#A1\u0001$\u0005\u0005Y\u0015C\u0001\u0013(!\tYQ%\u0003\u0002'\u0019\t9aj\u001c;iS:<\u0007CA\u0006)\u0013\tICBA\u0002B]f\u0004\"\u0001I\u0016\u0005\u000b1\u0012!\u0019A\u0012\u0003\u0003YCqA\f\u0002\u0002\u0002\u0003\u000fq&\u0001\u0006fm&$WM\\2fIQ\u00022\u0001\r\u001f+\u001d\t\t\u0014H\u0004\u00023o9\u00111GN\u0007\u0002i)\u0011Q\u0007C\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!\u0001\u000f\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011!hO\u0001\ba\u0006\u001c7.Y4f\u0015\tAd!\u0003\u0002>}\t)1IU5oO*\u0011!h\u000f"
)
public interface MapInstances1 extends MapInstances0 {
   // $FF: synthetic method
   static MapCRng MapCRng$(final MapInstances1 $this, final CommutativeRing evidence$4) {
      return $this.MapCRng(evidence$4);
   }

   default MapCRng MapCRng(final CommutativeRing evidence$4) {
      return new MapCRng(evidence$4);
   }

   static void $init$(final MapInstances1 $this) {
   }
}

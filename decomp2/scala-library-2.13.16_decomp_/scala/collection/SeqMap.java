package scala.collection;

import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u00032\u0001\u0011\u0005!\u0007\u0003\u00047\u0001\u0001&\tf\u000e\u0005\u0006\u0007\u0002!\t\u0005R\u0004\u0006\u0011&A\t!\u0013\u0004\u0006\u0011%A\tA\u0013\u0005\u0006/\u0016!\t\u0001\u0017\u0005\b3\u0016\t\t\u0011\"\u0003[\u0005\u0019\u0019V-]'ba*\u0011!bC\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0019qB\u0007\u0013\u0014\u000b\u0001\u0001BCJ\u0016\u0011\u0005E\u0011R\"A\u0006\n\u0005MY!AB!osJ+g\r\u0005\u0003\u0016-a\u0019S\"A\u0005\n\u0005]I!aA'baB\u0011\u0011D\u0007\u0007\u0001\t\u0015Y\u0002A1\u0001\u001d\u0005\u0005Y\u0015CA\u000f!!\t\tb$\u0003\u0002 \u0017\t9aj\u001c;iS:<\u0007CA\t\"\u0013\t\u00113BA\u0002B]f\u0004\"!\u0007\u0013\u0005\r\u0015\u0002AQ1\u0001\u001d\u0005\u00051\u0006CB\u000b(1\rJ#&\u0003\u0002)\u0013\t1Q*\u00199PaN\u0004\"!\u0006\u0001\u0011\tU\u0001\u0001d\t\t\u0007+1B2%\u000b\u0018\n\u00055J!AE'ba\u001a\u000b7\r^8ss\u0012+g-Y;miN\u0004\"!F\u0018\n\u0005AJ!\u0001C%uKJ\f'\r\\3\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0004CA\t5\u0013\t)4B\u0001\u0003V]&$\u0018\u0001D:ue&tw\r\u0015:fM&DX#\u0001\u001d\u0011\u0005e\u0002eB\u0001\u001e?!\tY4\"D\u0001=\u0015\tiT\"\u0001\u0004=e>|GOP\u0005\u0003\u007f-\ta\u0001\u0015:fI\u00164\u0017BA!C\u0005\u0019\u0019FO]5oO*\u0011qhC\u0001\u000b[\u0006\u0004h)Y2u_JLX#A#\u0011\u0007U1\u0015&\u0003\u0002H\u0013\tQQ*\u00199GC\u000e$xN]=\u0002\rM+\u0017/T1q!\t)Ra\u0005\u0002\u0006\u0017B\u0019Aj\u0014*\u000f\u0005Ui\u0015B\u0001(\n\u0003)i\u0015\r\u001d$bGR|'/_\u0005\u0003!F\u0013\u0001\u0002R3mK\u001e\fG/\u001a\u0006\u0003\u001d&\u0001\"a\u0015,\u000e\u0003QS!!V\u0005\u0002\u0013%lW.\u001e;bE2,\u0017B\u0001\u0005U\u0003\u0019a\u0014N\\5u}Q\t\u0011*\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001\\!\ta\u0016-D\u0001^\u0015\tqv,\u0001\u0003mC:<'\"\u00011\u0002\t)\fg/Y\u0005\u0003Ev\u0013aa\u00142kK\u000e$\b"
)
public interface SeqMap extends Map {
   static Builder newBuilder() {
      return SeqMap$.MODULE$.newBuilder();
   }

   static Object from(final IterableOnce it) {
      return SeqMap$.MODULE$.from(it);
   }

   // $FF: synthetic method
   static String stringPrefix$(final SeqMap $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "SeqMap";
   }

   // $FF: synthetic method
   static MapFactory mapFactory$(final SeqMap $this) {
      return $this.mapFactory();
   }

   default MapFactory mapFactory() {
      return SeqMap$.MODULE$;
   }

   static void $init$(final SeqMap $this) {
   }
}

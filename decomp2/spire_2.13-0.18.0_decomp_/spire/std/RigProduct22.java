package spire.std;

import algebra.ring.Rig;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u00111\u0004\u0001\u0007\u0004\u0005u\u0001bBA\u0011\u0001\u0019\r\u00111\u0005\u0005\b\u0003O\u0001a1AA\u0015\u0011\u001d\ti\u0003\u0001D\u0002\u0003_Aq!a\r\u0001\r\u0007\t)\u0004C\u0004\u0002:\u00011\u0019!a\u000f\t\u000f\u0005}\u0002Ab\u0001\u0002B!9\u0011Q\t\u0001\u0007\u0004\u0005\u001d\u0003bBA&\u0001\u0019\r\u0011Q\n\u0005\b\u0003#\u0002a1AA*\u0011\u001d\t9\u0006\u0001D\u0002\u00033Bq!!\u0018\u0001\r\u0007\ty\u0006C\u0004\u0002d\u00011\u0019!!\u001a\t\u000f\u0005%\u0004Ab\u0001\u0002l!9\u0011q\u000e\u0001\u0007\u0004\u0005E\u0004bBA;\u0001\u0019\r\u0011q\u000f\u0005\b\u0003w\u0002a1AA?\u0011\u001d\t\t\t\u0001D\u0002\u0003\u0007Cq!a\"\u0001\r\u0007\tI\tC\u0004\u0002\u000e\u00021\u0019!a$\t\u000f\u0005M\u0005Ab\u0001\u0002\u0016\"9\u0011\u0011\u0014\u0001\u0007\u0004\u0005m\u0005bBAP\u0001\u0011\u0005\u0011\u0011\u0015\u0002\r%&<\u0007K]8ek\u000e$(G\r\u0006\u00037q\t1a\u001d;e\u0015\u0005i\u0012!B:qSJ,W\u0003G\u0010=\r&cuJU+Y7z\u000bGm\u001a6naN4\u0018\u0010`@\u0002\u0006M)\u0001\u0001\t\u0014\u0002\nA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u00042a\n\u001b8\u001d\tA\u0013G\u0004\u0002*_9\u0011!FL\u0007\u0002W)\u0011A&L\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ$\u0003\u000219\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u001a4\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\r\u000f\n\u0005U2$a\u0001*jO*\u0011!g\r\t\u001aCaRT\tS&O#R;&,\u00181dM&dwN];ywz\f\u0019!\u0003\u0002:E\t9A+\u001e9mKJ\u0012\u0004CA\u001e=\u0019\u0001!Q!\u0010\u0001C\u0002y\u0012\u0011!Q\t\u0003\u007f\t\u0003\"!\t!\n\u0005\u0005\u0013#a\u0002(pi\"Lgn\u001a\t\u0003C\rK!\u0001\u0012\u0012\u0003\u0007\u0005s\u0017\u0010\u0005\u0002<\r\u0012)q\t\u0001b\u0001}\t\t!\t\u0005\u0002<\u0013\u0012)!\n\u0001b\u0001}\t\t1\t\u0005\u0002<\u0019\u0012)Q\n\u0001b\u0001}\t\tA\t\u0005\u0002<\u001f\u0012)\u0001\u000b\u0001b\u0001}\t\tQ\t\u0005\u0002<%\u0012)1\u000b\u0001b\u0001}\t\ta\t\u0005\u0002<+\u0012)a\u000b\u0001b\u0001}\t\tq\t\u0005\u0002<1\u0012)\u0011\f\u0001b\u0001}\t\t\u0001\n\u0005\u0002<7\u0012)A\f\u0001b\u0001}\t\t\u0011\n\u0005\u0002<=\u0012)q\f\u0001b\u0001}\t\t!\n\u0005\u0002<C\u0012)!\r\u0001b\u0001}\t\t1\n\u0005\u0002<I\u0012)Q\r\u0001b\u0001}\t\tA\n\u0005\u0002<O\u0012)\u0001\u000e\u0001b\u0001}\t\tQ\n\u0005\u0002<U\u0012)1\u000e\u0001b\u0001}\t\ta\n\u0005\u0002<[\u0012)a\u000e\u0001b\u0001}\t\tq\n\u0005\u0002<a\u0012)\u0011\u000f\u0001b\u0001}\t\t\u0001\u000b\u0005\u0002<g\u0012)A\u000f\u0001b\u0001}\t\t\u0011\u000b\u0005\u0002<m\u0012)q\u000f\u0001b\u0001}\t\t!\u000b\u0005\u0002<s\u0012)!\u0010\u0001b\u0001}\t\t1\u000b\u0005\u0002<y\u0012)Q\u0010\u0001b\u0001}\t\tA\u000b\u0005\u0002<\u007f\u00121\u0011\u0011\u0001\u0001C\u0002y\u0012\u0011!\u0016\t\u0004w\u0005\u0015AABA\u0004\u0001\t\u0007aHA\u0001W!m\tY!!\u0004;\u000b\"[e*\u0015+X5v\u00037MZ5m_J,\bp\u001f@\u0002\u00045\t!$C\u0002\u0002\u0010i\u0011\u0011cU3nSJLgn\u001a)s_\u0012,8\r\u001e\u001a3\u0003\u0019!\u0013N\\5uIQ\u0011\u0011Q\u0003\t\u0004C\u0005]\u0011bAA\rE\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0003\u0003?\u00012a\n\u001b;\u0003)\u0019HO];diV\u0014XMM\u000b\u0003\u0003K\u00012a\n\u001bF\u0003)\u0019HO];diV\u0014XmM\u000b\u0003\u0003W\u00012a\n\u001bI\u0003)\u0019HO];diV\u0014X\rN\u000b\u0003\u0003c\u00012a\n\u001bL\u0003)\u0019HO];diV\u0014X-N\u000b\u0003\u0003o\u00012a\n\u001bO\u0003)\u0019HO];diV\u0014XMN\u000b\u0003\u0003{\u00012a\n\u001bR\u0003)\u0019HO];diV\u0014XmN\u000b\u0003\u0003\u0007\u00022a\n\u001bU\u0003)\u0019HO];diV\u0014X\rO\u000b\u0003\u0003\u0013\u00022a\n\u001bX\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003\u001f\u00022a\n\u001b[\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005U\u0003cA\u00145;\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\tY\u0006E\u0002(i\u0001\f1b\u001d;sk\u000e$XO]32eU\u0011\u0011\u0011\r\t\u0004OQ\u001a\u0017aC:ueV\u001cG/\u001e:fcM*\"!a\u001a\u0011\u0007\u001d\"d-A\u0006tiJ,8\r^;sKF\"TCAA7!\r9C'[\u0001\fgR\u0014Xo\u0019;ve\u0016\fT'\u0006\u0002\u0002tA\u0019q\u0005\u000e7\u0002\u0017M$(/^2ukJ,\u0017GN\u000b\u0003\u0003s\u00022a\n\u001bp\u0003-\u0019HO];diV\u0014X-M\u001c\u0016\u0005\u0005}\u0004cA\u00145e\u0006Y1\u000f\u001e:vGR,(/Z\u00199+\t\t)\tE\u0002(iU\f1b\u001d;sk\u000e$XO]32sU\u0011\u00111\u0012\t\u0004OQB\u0018aC:ueV\u001cG/\u001e:feA*\"!!%\u0011\u0007\u001d\"40A\u0006tiJ,8\r^;sKJ\nTCAAL!\r9CG`\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014$'\u0006\u0002\u0002\u001eB!q\u0005NA\u0002\u0003\ryg.Z\u000b\u0002o\u0001"
)
public interface RigProduct22 extends Rig, SemiringProduct22 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   Rig structure7();

   Rig structure8();

   Rig structure9();

   Rig structure10();

   Rig structure11();

   Rig structure12();

   Rig structure13();

   Rig structure14();

   Rig structure15();

   Rig structure16();

   Rig structure17();

   Rig structure18();

   Rig structure19();

   Rig structure20();

   Rig structure21();

   Rig structure22();

   // $FF: synthetic method
   static Tuple22 one$(final RigProduct22 $this) {
      return $this.one();
   }

   default Tuple22 one() {
      return new Tuple22(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one(), this.structure20().one(), this.structure21().one(), this.structure22().one());
   }

   static void $init$(final RigProduct22 $this) {
   }
}

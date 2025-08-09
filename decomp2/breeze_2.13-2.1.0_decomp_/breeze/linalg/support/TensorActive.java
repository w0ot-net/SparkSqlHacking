package breeze.linalg.support;

import breeze.linalg.QuasiTensor;
import scala.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3A!\u0003\u0006\u0001#!A\u0011\u0004\u0001BC\u0002\u0013%!\u0004\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003\u001c\u0011!9\u0003A!A!\u0002\u0017A\u0003\"B\u001b\u0001\t\u00031\u0004\"\u0002\u001f\u0001\t\u0003i\u0004\"B'\u0001\t\u0003q\u0005\"\u0002*\u0001\t\u0003\u0019\u0006\"B,\u0001\t\u0003A&\u0001\u0004+f]N|'/Q2uSZ,'BA\u0006\r\u0003\u001d\u0019X\u000f\u001d9peRT!!\u0004\b\u0002\r1Lg.\u00197h\u0015\u0005y\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\tI\u00014'H\n\u0003\u0001M\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0017A\u0002;f]N|'/F\u0001\u001c!\taR\u0004\u0004\u0001\u0005\ry\u0001AQ1\u0001 \u0005\u0011!\u0006.[:\u0012\u0005\u0001\u001a\u0003C\u0001\u000b\"\u0013\t\u0011SCA\u0004O_RD\u0017N\\4\u0011\u0005Q!\u0013BA\u0013\u0016\u0005\r\te._\u0001\bi\u0016t7o\u001c:!\u0003\t)g\u000f\u0005\u0003\u0015SmY\u0013B\u0001\u0016\u0016\u0005A!C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000f\u0005\u0003-[=\u0012T\"\u0001\u0007\n\u00059b!A\u0002+f]N|'\u000f\u0005\u0002\u001da\u0011)\u0011\u0007\u0001b\u0001?\t\t1\n\u0005\u0002\u001dg\u0011)A\u0007\u0001b\u0001?\t\ta+\u0001\u0004=S:LGO\u0010\u000b\u0003om\"\"\u0001\u000f\u001e\u0011\u000be\u0002qFM\u000e\u000e\u0003)AQa\n\u0003A\u0004!BQ!\u0007\u0003A\u0002m\t\u0001\"\u001b;fe\u0006$xN]\u000b\u0002}A\u0019qh\u0012&\u000f\u0005\u0001+eBA!E\u001b\u0005\u0011%BA\"\u0011\u0003\u0019a$o\\8u}%\ta#\u0003\u0002G+\u00059\u0001/Y2lC\u001e,\u0017B\u0001%J\u0005!IE/\u001a:bi>\u0014(B\u0001$\u0016!\u0011!2j\f\u001a\n\u00051+\"A\u0002+va2,''\u0001\u0003lKf\u001cX#A(\u0011\u000be\u0002vFM\u000e\n\u0005ES!A\u0003+f]N|'oS3zg\u00061a/\u00197vKN,\u0012\u0001\u0016\t\u0006sU{#gG\u0005\u0003-*\u0011A\u0002V3og>\u0014h+\u00197vKN\fQ\u0001]1jeN,\u0012!\u0017\t\u0006si{#gG\u0005\u00037*\u00111\u0002V3og>\u0014\b+Y5sg\u0002"
)
public class TensorActive {
   private final Object tensor;
   private final .less.colon.less ev;

   private Object tensor() {
      return this.tensor;
   }

   public Iterator iterator() {
      return ((QuasiTensor)this.ev.apply(this.tensor())).activeIterator();
   }

   public TensorKeys keys() {
      return new TensorKeys(this.tensor(), true, TensorKeys$.MODULE$.$lessinit$greater$default$3(), this.ev);
   }

   public TensorValues values() {
      return new TensorValues(this.tensor(), true, TensorValues$.MODULE$.$lessinit$greater$default$3(), this.ev);
   }

   public TensorPairs pairs() {
      return new TensorPairs(this.tensor(), true, TensorPairs$.MODULE$.$lessinit$greater$default$3(), this.ev);
   }

   public TensorActive(final Object tensor, final .less.colon.less ev) {
      this.tensor = tensor;
      this.ev = ev;
   }
}

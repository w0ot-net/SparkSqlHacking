package spire.std;

import algebra.ring.EuclideanRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.math.BitString;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u000513q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000f\t\u0002!\u0019!C\u0004G!9q\t\u0001b\u0001\n\u000fA%!\u0004'p]\u001eLen\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0019A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u000b\u0011\u00055)\u0012B\u0001\f\u000f\u0005\u0011)f.\u001b;\u0002\u001b1{gn\u001a\"jiN#(/\u001b8h+\u0005I\u0002c\u0001\u000e\u001e?5\t1D\u0003\u0002\u001d\u0011\u0005!Q.\u0019;i\u0013\tq2DA\u0005CSR\u001cFO]5oOB\u0011Q\u0002I\u0005\u0003C9\u0011A\u0001T8oO\u0006YAj\u001c8h\u00032<WM\u0019:b+\u0005!#cB\u0013(omr\u0014\t\u0012\u0004\u0005M\u0001\u0001AE\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002)i}q!!K\u0019\u000f\u0005)zcBA\u0016/\u001b\u0005a#BA\u0017\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u00021\u0011\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u001a4\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\r\u0005\n\u0005U2$!D#vG2LG-Z1o%&twM\u0003\u00023gA\u0019\u0001(O\u0010\u000e\u0003MJ!AO\u001a\u0003\u000b9\u0013vn\u001c;\u0011\u0007abt$\u0003\u0002>g\tQ\u0011j]%oi\u0016<'/\u00197\u0011\u0007!zt$\u0003\u0002Am\t1BK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8D%&tw\rE\u0002)\u0005~I!a\u0011\u001c\u0003\rMKwM\\3e!\rASiH\u0005\u0003\rZ\u0012Qa\u0014:eKJ\fq\u0001T8oOR\u000bw-F\u0001J!\rQ\"jH\u0005\u0003\u0017n\u0011\u0011BT;nE\u0016\u0014H+Y4"
)
public interface LongInstances {
   void spire$std$LongInstances$_setter_$LongBitString_$eq(final BitString x$1);

   void spire$std$LongInstances$_setter_$LongAlgebra_$eq(final EuclideanRing x$1);

   void spire$std$LongInstances$_setter_$LongTag_$eq(final NumberTag x$1);

   BitString LongBitString();

   EuclideanRing LongAlgebra();

   NumberTag LongTag();

   static void $init$(final LongInstances $this) {
      $this.spire$std$LongInstances$_setter_$LongBitString_$eq(new LongIsBitString());
      $this.spire$std$LongInstances$_setter_$LongAlgebra_$eq(new LongAlgebra());
      $this.spire$std$LongInstances$_setter_$LongTag_$eq(new NumberTag.BuiltinIntTag(BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(Long.MIN_VALUE), BoxesRunTime.boxToLong(Long.MAX_VALUE)));
   }
}

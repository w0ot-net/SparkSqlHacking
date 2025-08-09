package spire.std;

import algebra.ring.EuclideanRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.math.BitString;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u0005%3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000f\t\u0002!\u0019!C\u0004G!9A\t\u0001b\u0001\n\u000f)%!\u0004\"zi\u0016Len\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0019A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u000b\u0011\u00055)\u0012B\u0001\f\u000f\u0005\u0011)f.\u001b;\u0002\u001b\tKH/\u001a\"jiN#(/\u001b8h+\u0005I\u0002c\u0001\u000e\u001e?5\t1D\u0003\u0002\u001d\u0011\u0005!Q.\u0019;i\u0013\tq2DA\u0005CSR\u001cFO]5oOB\u0011Q\u0002I\u0005\u0003C9\u0011AAQ=uK\u0006Y!)\u001f;f\u00032<WM\u0019:b+\u0005!#CB\u0013(omr\u0014I\u0002\u0003'\u0001\u0001!#\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004c\u0001\u00155?9\u0011\u0011&\r\b\u0003U=r!a\u000b\u0018\u000e\u00031R!!\f\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0011B\u0001\u0019\t\u0003\u001d\tGnZ3ce\u0006L!AM\u001a\u0002\u000fA\f7m[1hK*\u0011\u0001\u0007C\u0005\u0003kY\u0012Q\"R;dY&$W-\u00198SS:<'B\u0001\u001a4!\rA\u0014hH\u0007\u0002g%\u0011!h\r\u0002\u000b\u0013NLe\u000e^3he\u0006d\u0007c\u0001\u0015=?%\u0011QH\u000e\u0002\u0017)J,hnY1uK\u0012$\u0015N^5tS>t7IU5oOB\u0019\u0001fP\u0010\n\u0005\u00013$AB*jO:,G\rE\u0002)\u0005~I!a\u0011\u001c\u0003\u000b=\u0013H-\u001a:\u0002\u000f\tKH/\u001a+bOV\ta\tE\u0002\u001b\u000f~I!\u0001S\u000e\u0003\u00139+XNY3s)\u0006<\u0007"
)
public interface ByteInstances {
   void spire$std$ByteInstances$_setter_$ByteBitString_$eq(final BitString x$1);

   void spire$std$ByteInstances$_setter_$ByteAlgebra_$eq(final EuclideanRing x$1);

   void spire$std$ByteInstances$_setter_$ByteTag_$eq(final NumberTag x$1);

   BitString ByteBitString();

   EuclideanRing ByteAlgebra();

   NumberTag ByteTag();

   static void $init$(final ByteInstances $this) {
      $this.spire$std$ByteInstances$_setter_$ByteBitString_$eq(new ByteIsBitString());
      $this.spire$std$ByteInstances$_setter_$ByteAlgebra_$eq(new ByteAlgebra());
      $this.spire$std$ByteInstances$_setter_$ByteTag_$eq(new NumberTag.BuiltinIntTag(BoxesRunTime.boxToByte((byte)0), BoxesRunTime.boxToByte((byte)-128), BoxesRunTime.boxToByte((byte)127)));
   }
}

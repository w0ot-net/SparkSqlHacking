package spire.math;

import algebra.ring.CommutativeRig;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000fu\u0002!\u0019!C\u0004}!9!\t\u0001b\u0001\n\u000f\u0019%!D+J]RLen\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u0005!Q.\u0019;i\u0015\u0005I\u0011!B:qSJ,7\u0001A\n\u0003\u00011\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0015!\tiQ#\u0003\u0002\u0017\u001d\t!QK\\5u\u0003-)\u0016J\u001c;BY\u001e,'M]1\u0016\u0003e\u0011bA\u0007\u000f1i]Rd\u0001B\u000e\u0001\u0001e\u0011A\u0002\u0010:fM&tW-\\3oiz\u00022!H\u0015-\u001d\tqbE\u0004\u0002 I9\u0011\u0001eI\u0007\u0002C)\u0011!EC\u0001\u0007yI|w\u000e\u001e \n\u0003%I!!\n\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0005K\u0001\ba\u0006\u001c7.Y4f\u0015\t)\u0003\"\u0003\u0002+W\t!1IU5h\u0015\t9\u0003\u0006\u0005\u0002.]5\ta!\u0003\u00020\r\t!Q+\u00138u!\r\t$\u0007L\u0007\u0002Q%\u00111\u0007\u000b\u0002\u000b\u0013NLe\u000e^3he\u0006d\u0007cA\u000f6Y%\u0011ag\u000b\u0002\u0012)J,hnY1uK\u0012$\u0015N^5tS>t\u0007cA\u000f9Y%\u0011\u0011h\u000b\u0002\u0016'&<g.\u001a3BI\u0012LG/\u001b<f\u00076{gn\\5e!\ri2\bL\u0005\u0003y-\u0012Qa\u0014:eKJ\fQ\"V%oi\nKGo\u0015;sS:<W#A \u0011\u00075\u0002E&\u0003\u0002B\r\tI!)\u001b;TiJLgnZ\u0001\b+&sG\u000fV1h+\u0005!\u0005cA\u0017FY%\u0011aI\u0002\u0002\n\u001dVl'-\u001a:UC\u001e\u0004"
)
public interface UIntInstances {
   void spire$math$UIntInstances$_setter_$UIntAlgebra_$eq(final CommutativeRig x$1);

   void spire$math$UIntInstances$_setter_$UIntBitString_$eq(final BitString x$1);

   void spire$math$UIntInstances$_setter_$UIntTag_$eq(final NumberTag x$1);

   CommutativeRig UIntAlgebra();

   BitString UIntBitString();

   NumberTag UIntTag();

   static void $init$(final UIntInstances $this) {
      $this.spire$math$UIntInstances$_setter_$UIntAlgebra_$eq(new UIntAlgebra());
      $this.spire$math$UIntInstances$_setter_$UIntBitString_$eq(new UIntBitString());
      $this.spire$math$UIntInstances$_setter_$UIntTag_$eq(new NumberTag.UnsignedIntTag(new UInt(UInt$.MODULE$.MinValue()), new UInt(UInt$.MODULE$.MaxValue())));
   }
}

package spire.math;

import algebra.ring.CommutativeRig;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000fa\u0002!\u0019!C\u0004s\t\u0001b*\u0019;ve\u0006d\u0017J\\:uC:\u001cWm\u001d\u0006\u0003\r\u001d\tA!\\1uQ*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Y\u0001C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002'A\u0011A\u0002F\u0005\u0003+5\u0011A!\u00168ji\u0006qa*\u0019;ve\u0006d\u0017\t\\4fEJ\fW#\u0001\r\u0013\u000beYrFM\u001b\u0007\ti\u0001\u0001\u0001\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u00049!ZcBA\u000f&\u001d\tq2E\u0004\u0002 E5\t\u0001E\u0003\u0002\"\u0013\u00051AH]8pizJ\u0011\u0001C\u0005\u0003I\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002'O\u00059\u0001/Y2lC\u001e,'B\u0001\u0013\b\u0013\tI#F\u0001\u0003D%&<'B\u0001\u0014(!\taS&D\u0001\u0006\u0013\tqSAA\u0004OCR,(/\u00197\u0011\u0007q\u00014&\u0003\u00022U\t)qJ\u001d3feB\u0019AdM\u0016\n\u0005QR#!F*jO:,G-\u00113eSRLg/Z\"N_:|\u0017\u000e\u001a\t\u00049YZ\u0013BA\u001c+\u0005E!&/\u001e8dCR,G\rR5wSNLwN\\\u0001\u000b\u001d\u0006$XO]1m)\u0006<W#\u0001\u001e\u0011\u00071Z4&\u0003\u0002=\u000b\tIa*^7cKJ$\u0016m\u001a"
)
public interface NaturalInstances {
   void spire$math$NaturalInstances$_setter_$NaturalAlgebra_$eq(final CommutativeRig x$1);

   void spire$math$NaturalInstances$_setter_$NaturalTag_$eq(final NumberTag x$1);

   CommutativeRig NaturalAlgebra();

   NumberTag NaturalTag();

   static void $init$(final NaturalInstances $this) {
      $this.spire$math$NaturalInstances$_setter_$NaturalAlgebra_$eq(new NaturalAlgebra());
      $this.spire$math$NaturalInstances$_setter_$NaturalTag_$eq(new NumberTag.CustomTag(NumberTag.Integral$.MODULE$, new Some(Natural$.MODULE$.zero()), new Some(Natural$.MODULE$.zero()), .MODULE$, false, false));
   }
}

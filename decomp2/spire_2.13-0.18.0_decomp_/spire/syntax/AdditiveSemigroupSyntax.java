package spire.syntax;

import algebra.ring.AdditiveSemigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\r\u0011\u0004C\u0003@\u0001\u0011\r\u0001\tC\u0003J\u0001\u0011\r!\nC\u0003S\u0001\u0011\r1KA\fBI\u0012LG/\u001b<f'\u0016l\u0017n\u001a:pkB\u001c\u0016P\u001c;bq*\u0011\u0001\"C\u0001\u0007gftG/\u0019=\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\u000b\u0011\u000591\u0012BA\f\u0010\u0005\u0011)f.\u001b;\u0002)\u0005$G-\u001b;jm\u0016\u001cV-\\5he>,\bo\u00149t+\tQ\"\u0005\u0006\u0002\u001c{Q\u0011Ad\u000b\t\u0004;y\u0001S\"A\u0004\n\u0005}9!\u0001F!eI&$\u0018N^3TK6LwM]8va>\u00038\u000f\u0005\u0002\"E1\u0001A!B\u0012\u0003\u0005\u0004!#!A!\u0012\u0005\u0015B\u0003C\u0001\b'\u0013\t9sBA\u0004O_RD\u0017N\\4\u0011\u00059I\u0013B\u0001\u0016\u0010\u0005\r\te.\u001f\u0005\bY\t\t\t\u0011q\u0001.\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\u00079R\u0004E\u0004\u00020o9\u0011\u0001'\u000e\b\u0003cQj\u0011A\r\u0006\u0003g-\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0006\n\u0005YJ\u0011aB1mO\u0016\u0014'/Y\u0005\u0003qe\nq\u0001]1dW\u0006<WM\u0003\u00027\u0013%\u00111\b\u0010\u0002\u0012\u0003\u0012$\u0017\u000e^5wKN+W.[4s_V\u0004(B\u0001\u001d:\u0011\u0015q$\u00011\u0001!\u0003\u0005\t\u0017A\b7ji\u0016\u0014\u0018\r\\%oi\u0006#G-\u001b;jm\u0016\u001cV-\\5he>,\bo\u00149t)\t\tE\t\u0005\u0002\u001e\u0005&\u00111i\u0002\u0002\u001f\u0019&$XM]1m\u0013:$\u0018\t\u001a3ji&4XmU3nS\u001e\u0014x.\u001e9PaNDQ!R\u0002A\u0002\u0019\u000b1\u0001\u001c5t!\tqq)\u0003\u0002I\u001f\t\u0019\u0011J\u001c;\u0002?1LG/\u001a:bY2{gnZ!eI&$\u0018N^3TK6LwM]8va>\u00038\u000f\u0006\u0002L\u001dB\u0011Q\u0004T\u0005\u0003\u001b\u001e\u0011q\u0004T5uKJ\fG\u000eT8oO\u0006#G-\u001b;jm\u0016\u001cV-\\5he>,\bo\u00149t\u0011\u0015)E\u00011\u0001P!\tq\u0001+\u0003\u0002R\u001f\t!Aj\u001c8h\u0003\u0005b\u0017\u000e^3sC2$u.\u001e2mK\u0006#G-\u001b;jm\u0016\u001cV-\\5he>,\bo\u00149t)\t!v\u000b\u0005\u0002\u001e+&\u0011ak\u0002\u0002\"\u0019&$XM]1m\t>,(\r\\3BI\u0012LG/\u001b<f'\u0016l\u0017n\u001a:pkB|\u0005o\u001d\u0005\u0006\u000b\u0016\u0001\r\u0001\u0017\t\u0003\u001deK!AW\b\u0003\r\u0011{WO\u00197f\u0001"
)
public interface AdditiveSemigroupSyntax {
   // $FF: synthetic method
   static AdditiveSemigroupOps additiveSemigroupOps$(final AdditiveSemigroupSyntax $this, final Object a, final AdditiveSemigroup evidence$11) {
      return $this.additiveSemigroupOps(a, evidence$11);
   }

   default AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
      return new AdditiveSemigroupOps(a, evidence$11);
   }

   // $FF: synthetic method
   static int literalIntAdditiveSemigroupOps$(final AdditiveSemigroupSyntax $this, final int lhs) {
      return $this.literalIntAdditiveSemigroupOps(lhs);
   }

   default int literalIntAdditiveSemigroupOps(final int lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static long literalLongAdditiveSemigroupOps$(final AdditiveSemigroupSyntax $this, final long lhs) {
      return $this.literalLongAdditiveSemigroupOps(lhs);
   }

   default long literalLongAdditiveSemigroupOps(final long lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static double literalDoubleAdditiveSemigroupOps$(final AdditiveSemigroupSyntax $this, final double lhs) {
      return $this.literalDoubleAdditiveSemigroupOps(lhs);
   }

   default double literalDoubleAdditiveSemigroupOps(final double lhs) {
      return lhs;
   }

   static void $init$(final AdditiveSemigroupSyntax $this) {
   }
}

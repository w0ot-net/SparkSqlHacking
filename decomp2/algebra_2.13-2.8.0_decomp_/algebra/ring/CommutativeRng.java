package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4qAB\u0004\u0011\u0002G\u0005AbB\u0003A\u000f!\u0005\u0011IB\u0003\u0007\u000f!\u0005!\tC\u0003V\u0005\u0011\u0005a\u000bC\u0003X\u0005\u0011\u0015\u0001\fC\u0004d\u0005\u0005\u0005I\u0011\u00023\u0003\u001d\r{W.\\;uCRLg/\u001a*oO*\u0011\u0001\"C\u0001\u0005e&twMC\u0001\u000b\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u000e5M!\u0001A\u0004\u000b>!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\r\te.\u001f\t\u0004+YAR\"A\u0004\n\u0005]9!a\u0001*oOB\u0011\u0011D\u0007\u0007\u0001\t%Y\u0002\u0001)A\u0001\u0002\u000b\u0007ADA\u0001B#\tib\u0002\u0005\u0002\u0010=%\u0011q\u0004\u0005\u0002\b\u001d>$\b.\u001b8hQ\u0019Q\u0012\u0005\n\u00184qA\u0011qBI\u0005\u0003GA\u00111b\u001d9fG&\fG.\u001b>fIF*1%\n\u0014)O9\u0011qBJ\u0005\u0003OA\t1!\u00138uc\u0011!\u0013&L\t\u000f\u0005)jS\"A\u0016\u000b\u00051Z\u0011A\u0002\u001fs_>$h(C\u0001\u0012c\u0015\u0019s\u0006\r\u001a2\u001d\ty\u0001'\u0003\u00022!\u0005!Aj\u001c8hc\u0011!\u0013&L\t2\u000b\r\"Tg\u000e\u001c\u000f\u0005=)\u0014B\u0001\u001c\u0011\u0003\u00151En\\1uc\u0011!\u0013&L\t2\u000b\rJ$\bP\u001e\u000f\u0005=Q\u0014BA\u001e\u0011\u0003\u0019!u.\u001e2mKF\"A%K\u0017\u0012!\r)b\bG\u0005\u0003\u007f\u001d\u00111cQ8n[V$\u0018\r^5wKN+W.\u001b:j]\u001e\fabQ8n[V$\u0018\r^5wKJsw\r\u0005\u0002\u0016\u0005M)!a\u0011$K\u001bB\u0011q\u0002R\u0005\u0003\u000bB\u0011a!\u00118z%\u00164\u0007cA\u000bH\u0013&\u0011\u0001j\u0002\u0002\u0017\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9Gk:\u001cG/[8ogB\u0011Q\u0003\u0001\t\u0004+-K\u0015B\u0001'\b\u0005\u0001jU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cV-\\5he>,\bOR;oGRLwN\\:\u0011\u00059\u001bV\"A(\u000b\u0005A\u000b\u0016AA5p\u0015\u0005\u0011\u0016\u0001\u00026bm\u0006L!\u0001V(\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\t\u0015!B1qa2LXCA-])\tQV\fE\u0002\u0016\u0001m\u0003\"!\u0007/\u0005\u000bm!!\u0019\u0001\u000f\t\u000by#\u00019\u0001.\u0002\u0003ID#\u0001\u00021\u0011\u0005=\t\u0017B\u00012\u0011\u0005\u0019Ig\u000e\\5oK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tQ\r\u0005\u0002gS6\tqM\u0003\u0002i#\u0006!A.\u00198h\u0013\tQwM\u0001\u0004PE*,7\r\u001e"
)
public interface CommutativeRng extends Rng, CommutativeSemiring {
   static CommutativeRng apply(final CommutativeRng r) {
      return CommutativeRng$.MODULE$.apply(r);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return CommutativeRng$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return CommutativeRng$.MODULE$.isAdditiveCommutative(ev);
   }
}

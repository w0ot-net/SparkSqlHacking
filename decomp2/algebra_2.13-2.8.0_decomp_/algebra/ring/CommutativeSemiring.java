package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4qAB\u0004\u0011\u0002G\u0005AbB\u0003A\u000f!\u0005\u0011IB\u0003\u0007\u000f!\u0005!\tC\u0003V\u0005\u0011\u0005a\u000bC\u0003X\u0005\u0011\u0015\u0001\fC\u0004d\u0005\u0005\u0005I\u0011\u00023\u0003'\r{W.\\;uCRLg/Z*f[&\u0014\u0018N\\4\u000b\u0005!I\u0011\u0001\u0002:j]\u001eT\u0011AC\u0001\bC2<WM\u0019:b\u0007\u0001)\"!\u0004\u000e\u0014\t\u0001qA#\u0010\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0004\u0003:L\bcA\u000b\u001715\tq!\u0003\u0002\u0018\u000f\tA1+Z7je&tw\r\u0005\u0002\u001a51\u0001A!C\u000e\u0001A\u0003\u0005\tQ1\u0001\u001d\u0005\u0005\t\u0015CA\u000f\u000f!\tya$\u0003\u0002 !\t9aj\u001c;iS:<\u0007F\u0002\u000e\"I9\u001a\u0004\b\u0005\u0002\u0010E%\u00111\u0005\u0005\u0002\fgB,7-[1mSj,G-M\u0003$K\u0019BsE\u0004\u0002\u0010M%\u0011q\u0005E\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013*[Eq!AK\u0017\u000e\u0003-R!\u0001L\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012'B\u00120aI\ndBA\b1\u0013\t\t\u0004#\u0001\u0003M_:<\u0017\u0007\u0002\u0013*[E\tTa\t\u001b6oYr!aD\u001b\n\u0005Y\u0002\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013*[E\tTaI\u001d;ymr!a\u0004\u001e\n\u0005m\u0002\u0012A\u0002#pk\ndW-\r\u0003%S5\n\u0002cA\u000b?1%\u0011qh\u0002\u0002#\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u0007>lW.\u001e;bi&4XmU3nS\u001e\u0014x.\u001e9\u0002'\r{W.\\;uCRLg/Z*f[&\u0014\u0018N\\4\u0011\u0005U\u00111#\u0002\u0002D\r*k\u0005CA\bE\u0013\t)\u0005C\u0001\u0004B]f\u0014VM\u001a\t\u0004+\u001dK\u0015B\u0001%\b\u0005]\tE\rZ5uSZ,Wj\u001c8pS\u00124UO\\2uS>t7\u000f\u0005\u0002\u0016\u0001A\u0019QcS%\n\u00051;!\u0001I'vYRL\u0007\u000f\\5dCRLg/Z*f[&<'o\\;q\rVt7\r^5p]N\u0004\"AT*\u000e\u0003=S!\u0001U)\u0002\u0005%|'\"\u0001*\u0002\t)\fg/Y\u0005\u0003)>\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A!\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005ecFC\u0001.^!\r)\u0002a\u0017\t\u00033q#Qa\u0007\u0003C\u0002qAQA\u0018\u0003A\u0004i\u000b\u0011A\u001d\u0015\u0003\t\u0001\u0004\"aD1\n\u0005\t\u0004\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001f!\t1\u0017.D\u0001h\u0015\tA\u0017+\u0001\u0003mC:<\u0017B\u00016h\u0005\u0019y%M[3di\u0002"
)
public interface CommutativeSemiring extends Semiring, MultiplicativeCommutativeSemigroup {
   static CommutativeSemiring apply(final CommutativeSemiring r) {
      return CommutativeSemiring$.MODULE$.apply(r);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return CommutativeSemiring$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return CommutativeSemiring$.MODULE$.isAdditiveCommutative(ev);
   }
}

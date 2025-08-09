package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4qAB\u0004\u0011\u0002G\u0005AbB\u0003A\u000f!\u0005\u0011IB\u0003\u0007\u000f!\u0005!\tC\u0003V\u0005\u0011\u0005a\u000bC\u0003X\u0005\u0011\u0015\u0001\fC\u0004d\u0005\u0005\u0005I\u0011\u00023\u0003\u0007IKwM\u0003\u0002\t\u0013\u0005!!/\u001b8h\u0015\u0005Q\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\ti!d\u0005\u0003\u0001\u001dQi\u0004CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"aA!osB\u0019QC\u0006\r\u000e\u0003\u001dI!aF\u0004\u0003\u0011M+W.\u001b:j]\u001e\u0004\"!\u0007\u000e\r\u0001\u0011I1\u0004\u0001Q\u0001\u0002\u0003\u0015\r\u0001\b\u0002\u0002\u0003F\u0011QD\u0004\t\u0003\u001fyI!a\b\t\u0003\u000f9{G\u000f[5oO\"2!$\t\u0013/ga\u0002\"a\u0004\u0012\n\u0005\r\u0002\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\u0013'Q\u001dr!a\u0004\u0014\n\u0005\u001d\u0002\u0012aA%oiF\"A%K\u0017\u0012\u001d\tQS&D\u0001,\u0015\ta3\"\u0001\u0004=e>|GOP\u0005\u0002#E*1e\f\u00193c9\u0011q\u0002M\u0005\u0003cA\tA\u0001T8oOF\"A%K\u0017\u0012c\u0015\u0019C'N\u001c7\u001d\tyQ'\u0003\u00027!\u0005)a\t\\8biF\"A%K\u0017\u0012c\u0015\u0019\u0013H\u000f\u001f<\u001d\ty!(\u0003\u0002<!\u00051Ai\\;cY\u0016\fD\u0001J\u0015.#A\u0019QC\u0010\r\n\u0005}:!\u0001F'vYRL\u0007\u000f\\5dCRLg/Z'p]>LG-A\u0002SS\u001e\u0004\"!\u0006\u0002\u0014\u000b\t\u0019eIS'\u0011\u0005=!\u0015BA#\u0011\u0005\u0019\te.\u001f*fMB\u0019QcR%\n\u0005!;!aF!eI&$\u0018N^3N_:|\u0017\u000e\u001a$v]\u000e$\u0018n\u001c8t!\t)\u0002\u0001E\u0002\u0016\u0017&K!\u0001T\u0004\u0003;5+H\u000e^5qY&\u001c\u0017\r^5wK6{gn\\5e\rVt7\r^5p]N\u0004\"AT*\u000e\u0003=S!\u0001U)\u0002\u0005%|'\"\u0001*\u0002\t)\fg/Y\u0005\u0003)>\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A!\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005ecFC\u0001.^!\r)\u0002a\u0017\t\u00033q#Qa\u0007\u0003C\u0002qAQA\u0018\u0003A\u0004i\u000b!!\u001a<)\u0005\u0011\u0001\u0007CA\bb\u0013\t\u0011\u0007C\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002KB\u0011a-[\u0007\u0002O*\u0011\u0001.U\u0001\u0005Y\u0006tw-\u0003\u0002kO\n1qJ\u00196fGR\u0004"
)
public interface Rig extends Semiring, MultiplicativeMonoid {
   static Rig apply(final Rig ev) {
      return Rig$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return Rig$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return Rig$.MODULE$.isAdditiveCommutative(ev);
   }
}

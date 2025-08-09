package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4qAB\u0004\u0011\u0002G\u0005AbB\u0003A\u000f!\u0005\u0011IB\u0003\u0007\u000f!\u0005!\tC\u0003V\u0005\u0011\u0005a\u000bC\u0003X\u0005\u0011\u0015\u0001\fC\u0004d\u0005\u0005\u0005I\u0011\u00023\u0003\u0011M+W.\u001b:j]\u001eT!\u0001C\u0005\u0002\tILgn\u001a\u0006\u0002\u0015\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003\u001bi\u0019B\u0001\u0001\b\u0015{A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t\u0019\u0011I\\=\u0011\u0007U1\u0002$D\u0001\b\u0013\t9rAA\rBI\u0012LG/\u001b<f\u0007>lW.\u001e;bi&4X-T8o_&$\u0007CA\r\u001b\u0019\u0001!\u0011b\u0007\u0001!\u0002\u0003\u0005)\u0019\u0001\u000f\u0003\u0003\u0005\u000b\"!\b\b\u0011\u0005=q\u0012BA\u0010\u0011\u0005\u001dqu\u000e\u001e5j]\u001eDcAG\u0011%]MB\u0004CA\b#\u0013\t\u0019\u0003CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012&M!:cBA\b'\u0013\t9\u0003#A\u0002J]R\fD\u0001J\u0015.#9\u0011!&L\u0007\u0002W)\u0011AfC\u0001\u0007yI|w\u000e\u001e \n\u0003E\tTaI\u00181eEr!a\u0004\u0019\n\u0005E\u0002\u0012\u0001\u0002'p]\u001e\fD\u0001J\u0015.#E*1\u0005N\u001b8m9\u0011q\"N\u0005\u0003mA\tQA\u00127pCR\fD\u0001J\u0015.#E*1%\u000f\u001e=w9\u0011qBO\u0005\u0003wA\ta\u0001R8vE2,\u0017\u0007\u0002\u0013*[E\u00012!\u0006 \u0019\u0013\tytAA\fNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3TK6LwM]8va\u0006A1+Z7je&tw\r\u0005\u0002\u0016\u0005M)!a\u0011$K\u001bB\u0011q\u0002R\u0005\u0003\u000bB\u0011a!\u00118z%\u00164\u0007cA\u000bH\u0013&\u0011\u0001j\u0002\u0002\u0018\u0003\u0012$\u0017\u000e^5wK6{gn\\5e\rVt7\r^5p]N\u0004\"!\u0006\u0001\u0011\u0007UY\u0015*\u0003\u0002M\u000f\t\u0001S*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f\u001d$v]\u000e$\u0018n\u001c8t!\tq5+D\u0001P\u0015\t\u0001\u0016+\u0001\u0002j_*\t!+\u0001\u0003kCZ\f\u0017B\u0001+P\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0011)A\u0003baBd\u00170\u0006\u0002Z9R\u0011!,\u0018\t\u0004+\u0001Y\u0006CA\r]\t\u0015YBA1\u0001\u001d\u0011\u0015qF\u0001q\u0001[\u0003\t)g\u000f\u000b\u0002\u0005AB\u0011q\"Y\u0005\u0003EB\u0011a!\u001b8mS:,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#A3\u0011\u0005\u0019LW\"A4\u000b\u0005!\f\u0016\u0001\u00027b]\u001eL!A[4\u0003\r=\u0013'.Z2u\u0001"
)
public interface Semiring extends AdditiveCommutativeMonoid, MultiplicativeSemigroup {
   static Semiring apply(final Semiring ev) {
      return Semiring$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return Semiring$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return Semiring$.MODULE$.isAdditiveCommutative(ev);
   }
}

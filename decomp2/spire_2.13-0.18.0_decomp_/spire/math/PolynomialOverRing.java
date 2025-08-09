package spire.math;

import algebra.ring.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003@\u0001\u0011\u0005\u0001\tC\u0004E\u0001\t\u0007i1I#\t\u000b\u001d\u0003A\u0011\u0001%\u0003%A{G.\u001f8p[&\fGn\u0014<feJKgn\u001a\u0006\u0003\r\u001d\tA!\\1uQ*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005-A2\u0003\u0002\u0001\r%=\u0002\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n\u0015-5\tQ!\u0003\u0002\u0016\u000b\t\t\u0002k\u001c7z]>l\u0017.\u00197Pm\u0016\u0014(K\\4\u0011\u0005]AB\u0002\u0001\u0003\n3\u0001\u0001\u000b\u0011!AC\u0002i\u0011\u0011aQ\t\u00037y\u0001\"!\u0004\u000f\n\u0005uq!a\u0002(pi\"Lgn\u001a\t\u0003\u001b}I!\u0001\t\b\u0003\u0007\u0005s\u0017\u0010K\u0002\u0019E\u0015\u0002\"!D\u0012\n\u0005\u0011r!aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u0014(S!r!!D\u0014\n\u0005!r\u0011A\u0002#pk\ndW-\r\u0003%U9zaBA\u0016/\u001b\u0005a#BA\u0017\n\u0003\u0019a$o\\8u}%\tq\u0002E\u00021sqr!!\r\u001c\u000f\u0005I\"dBA\u00164\u0013\u0005A\u0011BA\u001b\b\u0003\u001d\tGnZ3ce\u0006L!a\u000e\u001d\u0002\u000fA\f7m[1hK*\u0011QgB\u0005\u0003um\u0012AAU5oO*\u0011q\u0007\u000f\t\u0004'u2\u0012B\u0001 \u0006\u0005)\u0001v\u000e\\=o_6L\u0017\r\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0005\u0003\"!\u0004\"\n\u0005\rs!\u0001B+oSR\faa]2bY\u0006\u0014X#\u0001$\u0011\u0007AJd#A\u0002p]\u0016,\u0012\u0001\u0010"
)
public interface PolynomialOverRing extends PolynomialOverRng, Ring {
   Ring scalar();

   // $FF: synthetic method
   static Polynomial one$(final PolynomialOverRing $this) {
      return $this.one();
   }

   default Polynomial one() {
      return Polynomial$.MODULE$.one(this.eq(), this.scalar(), this.ct());
   }

   // $FF: synthetic method
   static Ring scalar$mcD$sp$(final PolynomialOverRing $this) {
      return $this.scalar$mcD$sp();
   }

   default Ring scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Polynomial one$mcD$sp$(final PolynomialOverRing $this) {
      return $this.one$mcD$sp();
   }

   default Polynomial one$mcD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static boolean specInstance$$(final PolynomialOverRing $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }

   static void $init$(final PolynomialOverRing $this) {
   }
}

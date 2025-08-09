package spire.algebra;

import scala.reflect.ScalaSignature;
import spire.math.Algebraic;
import spire.math.Algebraic$;
import spire.math.Rational;

@ScalaSignature(
   bytes = "\u0006\u0005a3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0019\u0005\u0011\u0006C\u00033\u0001\u0011\u00051gB\u00039\u0015!\u0005\u0011HB\u0003\n\u0015!\u0005!\bC\u0003G\u000b\u0011\u0005q\tC\u0003I\u000b\u0011\u0005\u0011\nC\u0004Q\u000b\u0005\u0005I\u0011B)\u0003\u0015%\u001b(+\u0019;j_:\fGN\u0003\u0002\f\u0019\u00059\u0011\r\\4fEJ\f'\"A\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011\u0001#H\n\u0004\u0001E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"aA!osB\u0019\u0001$G\u000e\u000e\u0003)I!A\u0007\u0006\u0003\u0017%\u001b\u0018\t\\4fEJ\f\u0017n\u0019\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qDA\u0001B#\t\u0001\u0013\u0003\u0005\u0002\u0013C%\u0011!e\u0005\u0002\b\u001d>$\b.\u001b8h\u0003\u0019!\u0013N\\5uIQ\tQ\u0005\u0005\u0002\u0013M%\u0011qe\u0005\u0002\u0005+:LG/\u0001\u0006u_J\u000bG/[8oC2$\"A\u000b\u0019\u0011\u0005-rS\"\u0001\u0017\u000b\u00055b\u0011\u0001B7bi\"L!a\f\u0017\u0003\u0011I\u000bG/[8oC2DQ!\r\u0002A\u0002m\t\u0011!Y\u0001\fi>\fEnZ3ce\u0006L7\r\u0006\u00025oA\u00111&N\u0005\u0003m1\u0012\u0011\"\u00117hK\n\u0014\u0018-[2\t\u000bE\u001a\u0001\u0019A\u000e\u0002\u0015%\u001b(+\u0019;j_:\fG\u000e\u0005\u0002\u0019\u000bM\u0019Qa\u000f \u0011\u0005Ia\u0014BA\u001f\u0014\u0005\u0019\te.\u001f*fMB\u0011q\bR\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0003S>T\u0011aQ\u0001\u0005U\u00064\u0018-\u0003\u0002F\u0001\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!O\u0001\u0006CB\u0004H._\u000b\u0003\u00156#\"a\u0013(\u0011\u0007a\u0001A\n\u0005\u0002\u001d\u001b\u0012)ad\u0002b\u0001?!)qj\u0002a\u0002\u0017\u0006\t\u0011)\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001S!\t\u0019f+D\u0001U\u0015\t)&)\u0001\u0003mC:<\u0017BA,U\u0005\u0019y%M[3di\u0002"
)
public interface IsRational extends IsAlgebraic {
   static IsRational apply(final IsRational A) {
      return IsRational$.MODULE$.apply(A);
   }

   Rational toRational(final Object a);

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final IsRational $this, final Object a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final Object a) {
      return Algebraic$.MODULE$.apply(this.toRational(a));
   }

   static void $init$(final IsRational $this) {
   }
}

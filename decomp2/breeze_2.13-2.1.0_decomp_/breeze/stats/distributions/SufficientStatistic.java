package breeze.stats.distributions;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2\u0001b\u0001\u0003\u0011\u0002G\u00051\"\u0006\u0005\u0006'\u00011\t\u0001\u0006\u0005\u0006C\u00011\tA\t\u0002\u0014'V4g-[2jK:$8\u000b^1uSN$\u0018n\u0019\u0006\u0003\u000b\u0019\tQ\u0002Z5tiJL'-\u001e;j_:\u001c(BA\u0004\t\u0003\u0015\u0019H/\u0019;t\u0015\u0005I\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005192C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u0006)A\u0005\u001d7vgR\u0011Qc\b\t\u0003-]a\u0001\u0001B\u0003\u0019\u0001\t\u0007\u0011DA\u0001U#\tQR\u0004\u0005\u0002\u000f7%\u0011Ad\u0004\u0002\b\u001d>$\b.\u001b8h!\rq\u0002!F\u0007\u0002\t!)\u0001%\u0001a\u0001+\u0005\tA/\u0001\u0004%i&lWm\u001d\u000b\u0003+\rBQ\u0001\n\u0002A\u0002\u0015\naa^3jO\"$\bC\u0001\b'\u0013\t9sB\u0001\u0004E_V\u0014G.\u001a"
)
public interface SufficientStatistic {
   SufficientStatistic $plus(final SufficientStatistic t);

   SufficientStatistic $times(final double weight);
}

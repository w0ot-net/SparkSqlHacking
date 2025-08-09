package breeze.stats.distributions;

import breeze.optimize.DiffFunction;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3q\u0001C\u0005\u0011\u0002G\u0005\u0001\u0003B\u0003\u0019\u0001\t\u0005\u0011\u0004B\u0003!\u0001\t\u0005\u0011\u0005C\u0003(\u0001\u0019\u0005\u0001\u0006C\u0003*\u0001\u0019\u0005!\u0006C\u00032\u0001\u0019\u0005!\u0007C\u00036\u0001\u0019\u0005a\u0007C\u0003?\u0001\u0019\u0005qHA\tFqB|g.\u001a8uS\u0006dg)Y7jYfT!AC\u0006\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\taQ\"A\u0003ti\u0006$8OC\u0001\u000f\u0003\u0019\u0011'/Z3{K\u000e\u0001QcA\tC_M\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0003\u0013A\u000b'/Y7fi\u0016\u0014\u0018C\u0001\u000e\u001e!\t\u00192$\u0003\u0002\u001d)\t9aj\u001c;iS:<\u0007CA\n\u001f\u0013\tyBCA\u0002B]f\u00141cU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e\f\"A\u0007\u0012\u0011\u0007\r\"S%D\u0001\n\u0013\t\u0001\u0013\u0002\u0005\u0002'\u00055\t\u0001!\u0001\rf[B$\u0018pU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e,\u0012!J\u0001\u0017gV4g-[2jK:$8\u000b^1uSN$\u0018n\u0019$peR\u0011Qe\u000b\u0005\u0006Y\u0011\u0001\r!L\u0001\u0002iB\u0011af\f\u0007\u0001\t\u0015\u0001\u0004A1\u0001\u001a\u0005\u0005!\u0016aA7mKR\u00111\u0007\u000e\t\u0003M\u0005AQ\u0001D\u0003A\u0002\u0015\n!\u0003\\5lK2L\u0007n\\8e\rVt7\r^5p]R\u0011q'\u0010\t\u0004qm\u001aT\"A\u001d\u000b\u0005ij\u0011\u0001C8qi&l\u0017N_3\n\u0005qJ$\u0001\u0004#jM\u001a4UO\\2uS>t\u0007\"\u0002\u0007\u0007\u0001\u0004)\u0013\u0001\u00043jgR\u0014\u0018NY;uS>tGC\u0001!J)\t\tE\t\u0005\u0002/\u0005\u0012)1\t\u0001b\u00013\t\tA\tC\u0003F\u000f\u0001\u000fa)\u0001\u0003sC:$\u0007CA\u0012H\u0013\tA\u0015BA\u0005SC:$')Y:jg\")!j\u0002a\u0001g\u0005\t\u0001\u000f"
)
public interface ExponentialFamily {
   SufficientStatistic emptySufficientStatistic();

   SufficientStatistic sufficientStatisticFor(final Object t);

   Object mle(final SufficientStatistic stats);

   DiffFunction likelihoodFunction(final SufficientStatistic stats);

   Object distribution(final Object p, final RandBasis rand);
}

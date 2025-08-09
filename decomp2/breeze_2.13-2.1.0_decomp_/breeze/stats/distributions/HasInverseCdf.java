package breeze.stats.distributions;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a1qAA\u0002\u0011\u0002G\u0005!\u0002C\u0003\u0012\u0001\u0019\u0005!CA\u0007ICNLeN^3sg\u0016\u001cEM\u001a\u0006\u0003\t\u0015\tQ\u0002Z5tiJL'-\u001e;j_:\u001c(B\u0001\u0004\b\u0003\u0015\u0019H/\u0019;t\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001Y\u0001C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g-\u0001\u0006j]Z,'o]3DI\u001a$\"a\u0005\f\u0011\u00051!\u0012BA\u000b\u000e\u0005\u0019!u.\u001e2mK\")q#\u0001a\u0001'\u0005\t\u0001\u000f"
)
public interface HasInverseCdf {
   double inverseCdf(final double p);
}

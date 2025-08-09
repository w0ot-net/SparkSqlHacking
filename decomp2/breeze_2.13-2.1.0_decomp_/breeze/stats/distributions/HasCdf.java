package breeze.stats.distributions;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y1qa\u0001\u0003\u0011\u0002G\u00051\u0002C\u0003\u0013\u0001\u0019\u00051\u0003C\u0003\u001c\u0001\u0019\u0005AD\u0001\u0004ICN\u001cEM\u001a\u0006\u0003\u000b\u0019\tQ\u0002Z5tiJL'-\u001e;j_:\u001c(BA\u0004\t\u0003\u0015\u0019H/\u0019;t\u0015\u0005I\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001a\u0001CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g-A\u0006qe>\u0014\u0017MY5mSRLHc\u0001\u000b\u00183A\u0011Q\"F\u0005\u0003-9\u0011a\u0001R8vE2,\u0007\"\u0002\r\u0002\u0001\u0004!\u0012!\u0001=\t\u000bi\t\u0001\u0019\u0001\u000b\u0002\u0003e\f1a\u00193g)\t!R\u0004C\u0003\u0019\u0005\u0001\u0007A\u0003"
)
public interface HasCdf {
   double probability(final double x, final double y);

   double cdf(final double x);
}

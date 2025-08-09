package org.apache.spark.sql.catalyst.plans.logical;

import org.apache.spark.sql.streaming.TimeMode;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U:Q\u0001B\u0003\t\u0002Q1QAF\u0003\t\u0002]AQAH\u0001\u0005\u0002}AQ\u0001I\u0001\u0005\u0002\u0005\n\u0011\u0002V5nK6{G-Z:\u000b\u0005\u00199\u0011a\u00027pO&\u001c\u0017\r\u001c\u0006\u0003\u0011%\tQ\u0001\u001d7b]NT!AC\u0006\u0002\u0011\r\fG/\u00197zgRT!\u0001D\u0007\u0002\u0007M\fHN\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h\u0007\u0001\u0001\"!F\u0001\u000e\u0003\u0015\u0011\u0011\u0002V5nK6{G-Z:\u0014\u0005\u0005A\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002)\u0005)\u0011\r\u001d9msR\u0011!\u0005\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0003K-\t\u0011b\u001d;sK\u0006l\u0017N\\4\n\u0005\u001d\"#\u0001\u0003+j[\u0016lu\u000eZ3\t\u000b%\u001a\u0001\u0019\u0001\u0016\u0002\u0011QLW.Z'pI\u0016\u0004\"a\u000b\u001a\u000f\u00051\u0002\u0004CA\u0017\u001b\u001b\u0005q#BA\u0018\u0014\u0003\u0019a$o\\8u}%\u0011\u0011GG\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u000225\u0001"
)
public final class TimeModes {
   public static TimeMode apply(final String timeMode) {
      return TimeModes$.MODULE$.apply(timeMode);
   }
}

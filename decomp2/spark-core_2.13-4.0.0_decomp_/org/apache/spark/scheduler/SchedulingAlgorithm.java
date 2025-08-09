package org.apache.spark.scheduler;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00012\u0001BA\u0002\u0011\u0002G\u0005Qa\u0003\u0005\u0006%\u00011\t\u0001\u0006\u0002\u0014'\u000eDW\rZ;mS:<\u0017\t\\4pe&$\b.\u001c\u0006\u0003\t\u0015\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u00199\u0011!B:qCJ\\'B\u0001\u0005\n\u0003\u0019\t\u0007/Y2iK*\t!\"A\u0002pe\u001e\u001c\"\u0001\u0001\u0007\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g\u0003)\u0019w.\u001c9be\u0006$xN]\u0002\u0001)\r)\u0002D\b\t\u0003\u001bYI!a\u0006\b\u0003\u000f\t{w\u000e\\3b]\")\u0011$\u0001a\u00015\u0005\u00111/\r\t\u00037qi\u0011aA\u0005\u0003;\r\u00111bU2iK\u0012,H.\u00192mK\")q$\u0001a\u00015\u0005\u00111O\r"
)
public interface SchedulingAlgorithm {
   boolean comparator(final Schedulable s1, final Schedulable s2);
}

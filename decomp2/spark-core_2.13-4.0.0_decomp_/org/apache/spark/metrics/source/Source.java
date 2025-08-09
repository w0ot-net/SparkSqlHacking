package org.apache.spark.metrics.source;

import com.codahale.metrics.MetricRegistry;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592\u0001b\u0001\u0003\u0011\u0002G\u0005\u0001B\u0004\u0005\u0006+\u00011\ta\u0006\u0005\u0006G\u00011\t\u0001\n\u0002\u0007'>,(oY3\u000b\u0005\u00151\u0011AB:pkJ\u001cWM\u0003\u0002\b\u0011\u00059Q.\u001a;sS\u000e\u001c(BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0014\u0005\u0001y\u0001C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g-\u0001\u0006t_V\u00148-\u001a(b[\u0016\u001c\u0001!F\u0001\u0019!\tI\u0002E\u0004\u0002\u001b=A\u00111$E\u0007\u00029)\u0011QDF\u0001\u0007yI|w\u000e\u001e \n\u0005}\t\u0012A\u0002)sK\u0012,g-\u0003\u0002\"E\t11\u000b\u001e:j]\u001eT!aH\t\u0002\u001d5,GO]5d%\u0016<\u0017n\u001d;ssV\tQ\u0005\u0005\u0002'Y5\tqE\u0003\u0002\bQ)\u0011\u0011FK\u0001\tG>$\u0017\r[1mK*\t1&A\u0002d_6L!!L\u0014\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0002"
)
public interface Source {
   String sourceName();

   MetricRegistry metricRegistry();
}

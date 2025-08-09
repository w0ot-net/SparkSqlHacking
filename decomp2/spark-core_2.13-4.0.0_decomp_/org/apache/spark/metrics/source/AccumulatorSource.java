package org.apache.spark.metrics.source;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.AccumulatorV2;
import scala.MatchError;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Qa\u0002\u0005\u0001\u0019IAQ!\b\u0001\u0005\u0002}Aq!\t\u0001C\u0002\u0013%!\u0005\u0003\u0004-\u0001\u0001\u0006Ia\t\u0005\u0006[\u0001!\tB\f\u0005\u0006;\u0002!\tE\u0018\u0005\u0006?\u0002!\tE\t\u0002\u0012\u0003\u000e\u001cW/\\;mCR|'oU8ve\u000e,'BA\u0005\u000b\u0003\u0019\u0019x.\u001e:dK*\u00111\u0002D\u0001\b[\u0016$(/[2t\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7c\u0001\u0001\u00143A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u0004\"AG\u000e\u000e\u0003!I!\u0001\b\u0005\u0003\rM{WO]2f\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0011\u0011\u0005i\u0001\u0011\u0001\u0003:fO&\u001cHO]=\u0016\u0003\r\u0002\"\u0001\n\u0016\u000e\u0003\u0015R!a\u0003\u0014\u000b\u0005\u001dB\u0013\u0001C2pI\u0006D\u0017\r\\3\u000b\u0003%\n1aY8n\u0013\tYSE\u0001\bNKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0002\u0013I,w-[:uef\u0004\u0013\u0001\u0003:fO&\u001cH/\u001a:\u0016\u0005=\u001aFC\u0001\u00194!\t!\u0012'\u0003\u00023+\t!QK\\5u\u0011\u0015!D\u00011\u00016\u00031\t7mY;nk2\fGo\u001c:t!\u00111T\bQ\"\u000f\u0005]Z\u0004C\u0001\u001d\u0016\u001b\u0005I$B\u0001\u001e\u001f\u0003\u0019a$o\\8u}%\u0011A(F\u0001\u0007!J,G-\u001a4\n\u0005yz$aA'ba*\u0011A(\u0006\t\u0003m\u0005K!AQ \u0003\rM#(/\u001b8ha\t!E\n\u0005\u0003F\u0011*cV\"\u0001$\u000b\u0005\u001dc\u0011\u0001B;uS2L!!\u0013$\u0003\u001b\u0005\u001b7-^7vY\u0006$xN\u001d,3!\tYE\n\u0004\u0001\u0005\u00135s\u0015\u0011!A\u0001\u0006\u0003)&aA0%c!)A\u0007\u0002a\u0001\u001fB!a'\u0010!Qa\t\tF\n\u0005\u0003F\u0011*\u0013\u0006CA&T\t\u0015!FA1\u0001V\u0005\u0005!\u0016C\u0001,Z!\t!r+\u0003\u0002Y+\t9aj\u001c;iS:<\u0007C\u0001\u000b[\u0013\tYVCA\u0002B]f\u0004\"aS*\u0002\u0015M|WO]2f\u001d\u0006lW-F\u0001A\u00039iW\r\u001e:jGJ+w-[:uef\u0004"
)
public class AccumulatorSource implements Source {
   private final MetricRegistry registry = new MetricRegistry();

   private MetricRegistry registry() {
      return this.registry;
   }

   public void register(final Map accumulators) {
      accumulators.foreach((x0$1) -> {
         if (x0$1 != null) {
            String name = (String)x0$1._1();
            AccumulatorV2 accumulator = (AccumulatorV2)x0$1._2();
            Gauge gauge = new Gauge(accumulator) {
               private final AccumulatorV2 accumulator$1;

               public Object getValue() {
                  return this.accumulator$1.value();
               }

               public {
                  this.accumulator$1 = accumulator$1;
               }
            };
            return (Gauge)this.registry().register(MetricRegistry.name(name, new String[0]), gauge);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public String sourceName() {
      return "AccumulatorSource";
   }

   public MetricRegistry metricRegistry() {
      return this.registry();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

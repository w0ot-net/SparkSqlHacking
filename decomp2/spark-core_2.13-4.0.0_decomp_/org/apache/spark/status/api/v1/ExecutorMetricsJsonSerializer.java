package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.metrics.ExecutorMetricType$;
import scala.MatchError;
import scala.Option;
import scala.Predef.ArrowAssoc.;
import scala.collection.mutable.LinkedHashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000513Q\u0001B\u0003\u0001\u0017EAQA\u000b\u0001\u0005\u00021BQa\f\u0001\u0005BABQa\u0011\u0001\u0005B\u0011\u0013Q$\u0012=fGV$xN]'fiJL7m\u001d&t_:\u001cVM]5bY&TXM\u001d\u0006\u0003\r\u001d\t!A^\u0019\u000b\u0005!I\u0011aA1qS*\u0011!bC\u0001\u0007gR\fG/^:\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\"\u0001\u0001\n\u0011\u0007Mab$D\u0001\u0015\u0015\t)b#\u0001\u0005eCR\f'-\u001b8e\u0015\t9\u0002$A\u0004kC\u000e\\7o\u001c8\u000b\u0005eQ\u0012!\u00034bgR,'\u000f_7m\u0015\u0005Y\u0012aA2p[&\u0011Q\u0004\u0006\u0002\u000f\u0015N|gnU3sS\u0006d\u0017N_3s!\ry\"\u0005J\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1q\n\u001d;j_:\u0004\"!\n\u0015\u000e\u0003\u0019R!aJ\u0006\u0002\u0011\u0015DXmY;u_JL!!\u000b\u0014\u0003\u001f\u0015CXmY;u_JlU\r\u001e:jGN\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002[A\u0011a\u0006A\u0007\u0002\u000b\u0005I1/\u001a:jC2L'0\u001a\u000b\u0005cQ2d\b\u0005\u0002 e%\u00111\u0007\t\u0002\u0005+:LG\u000fC\u00036\u0005\u0001\u0007a$A\u0004nKR\u0014\u0018nY:\t\u000b]\u0012\u0001\u0019\u0001\u001d\u0002\u001b)\u001cxN\\$f]\u0016\u0014\u0018\r^8s!\tID(D\u0001;\u0015\tYd#\u0001\u0003d_J,\u0017BA\u001f;\u00055Q5o\u001c8HK:,'/\u0019;pe\")qH\u0001a\u0001\u0001\u0006\u00112/\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s!\t\u0019\u0012)\u0003\u0002C)\t\u00112+\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s\u0003\u001dI7/R7qif$2!\u0012%K!\tyb)\u0003\u0002HA\t9!i\\8mK\u0006t\u0007\"B%\u0004\u0001\u0004\u0001\u0015\u0001\u00039s_ZLG-\u001a:\t\u000b-\u001b\u0001\u0019\u0001\u0010\u0002\u000bY\fG.^3"
)
public class ExecutorMetricsJsonSerializer extends JsonSerializer {
   public void serialize(final Option metrics, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) {
      if (metrics.isEmpty()) {
         jsonGenerator.writeNull();
      } else {
         metrics.foreach((m) -> {
            $anonfun$serialize$1(jsonGenerator, m);
            return BoxedUnit.UNIT;
         });
      }
   }

   public boolean isEmpty(final SerializerProvider provider, final Option value) {
      return value.isEmpty();
   }

   // $FF: synthetic method
   public static final void $anonfun$serialize$1(final JsonGenerator jsonGenerator$1, final ExecutorMetrics m) {
      LinkedHashMap metricsMap = (LinkedHashMap)ExecutorMetricType$.MODULE$.metricToOffset().map((x0$1) -> {
         if (x0$1 != null) {
            String metric = (String)x0$1._1();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(metric), BoxesRunTime.boxToLong(m.getMetricValue(metric)));
         } else {
            throw new MatchError(x0$1);
         }
      });
      jsonGenerator$1.writeObject(metricsMap);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

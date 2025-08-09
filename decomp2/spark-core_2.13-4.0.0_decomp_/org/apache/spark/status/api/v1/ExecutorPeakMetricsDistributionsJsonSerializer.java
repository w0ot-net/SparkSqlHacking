package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.ExecutorMetricType$;
import scala.MatchError;
import scala.Predef.ArrowAssoc.;
import scala.collection.mutable.LinkedHashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2Qa\u0001\u0003\u0001\u0015AAQ!\t\u0001\u0005\u0002\rBQ!\n\u0001\u0005B\u0019\u0012a&\u0012=fGV$xN\u001d)fC.lU\r\u001e:jGN$\u0015n\u001d;sS\n,H/[8og*\u001bxN\\*fe&\fG.\u001b>fe*\u0011QAB\u0001\u0003mFR!a\u0002\u0005\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\n\u0015\u000511\u000f^1ukNT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0003\u0001E\u00012AE\u000e\u001e\u001b\u0005\u0019\"B\u0001\u000b\u0016\u0003!!\u0017\r^1cS:$'B\u0001\f\u0018\u0003\u001dQ\u0017mY6t_:T!\u0001G\r\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\u000e\u0002\u0007\r|W.\u0003\u0002\u001d'\tq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001\u0010 \u001b\u0005!\u0011B\u0001\u0011\u0005\u0005\u0001*\u00050Z2vi>\u0014\b+Z1l\u001b\u0016$(/[2t\t&\u001cHO]5ckRLwN\\:\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001\n\t\u0003=\u0001\t\u0011b]3sS\u0006d\u0017N_3\u0015\t\u001djsf\u000e\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0005+:LG\u000fC\u0003/\u0005\u0001\u0007Q$A\u0004nKR\u0014\u0018nY:\t\u000bA\u0012\u0001\u0019A\u0019\u0002\u001b)\u001cxN\\$f]\u0016\u0014\u0018\r^8s!\t\u0011T'D\u00014\u0015\t!T#\u0001\u0003d_J,\u0017B\u0001\u001c4\u00055Q5o\u001c8HK:,'/\u0019;pe\")\u0001H\u0001a\u0001s\u0005\u00112/\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s!\t\u0011\"(\u0003\u0002<'\t\u00112+\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s\u0001"
)
public class ExecutorPeakMetricsDistributionsJsonSerializer extends JsonSerializer {
   public void serialize(final ExecutorPeakMetricsDistributions metrics, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) {
      LinkedHashMap metricsMap = (LinkedHashMap)ExecutorMetricType$.MODULE$.metricToOffset().map((x0$1) -> {
         if (x0$1 != null) {
            String metric = (String)x0$1._1();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(metric), metrics.getMetricDistribution(metric));
         } else {
            throw new MatchError(x0$1);
         }
      });
      jsonGenerator.writeObject(metricsMap);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

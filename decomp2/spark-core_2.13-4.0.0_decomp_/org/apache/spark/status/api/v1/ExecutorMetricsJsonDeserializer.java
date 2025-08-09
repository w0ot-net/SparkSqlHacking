package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.ExecutorMetrics;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3Q\u0001B\u0003\u0001\u0017EAQA\u000b\u0001\u0005\u00021BQa\f\u0001\u0005BABQA\u0010\u0001\u0005B}\u0012q$\u0012=fGV$xN]'fiJL7m\u001d&t_:$Um]3sS\u0006d\u0017N_3s\u0015\t1q!\u0001\u0002wc)\u0011\u0001\"C\u0001\u0004CBL'B\u0001\u0006\f\u0003\u0019\u0019H/\u0019;vg*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00191\u0003\b\u0010\u000e\u0003QQ!!\u0006\f\u0002\u0011\u0011\fG/\u00192j]\u0012T!a\u0006\r\u0002\u000f)\f7m[:p]*\u0011\u0011DG\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011aG\u0001\u0004G>l\u0017BA\u000f\u0015\u0005AQ5o\u001c8EKN,'/[1mSj,'\u000fE\u0002 E\u0011j\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001dZ\u0011\u0001C3yK\u000e,Ho\u001c:\n\u0005%2#aD#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\f\t\u0003]\u0001i\u0011!B\u0001\fI\u0016\u001cXM]5bY&TX\rF\u0002\u001fceBQA\r\u0002A\u0002M\n!B[:p]B\u000b'o]3s!\t!t'D\u00016\u0015\t1d#\u0001\u0003d_J,\u0017B\u0001\u001d6\u0005)Q5o\u001c8QCJ\u001cXM\u001d\u0005\u0006u\t\u0001\raO\u0001\u0017I\u0016\u001cXM]5bY&T\u0018\r^5p]\u000e{g\u000e^3yiB\u00111\u0003P\u0005\u0003{Q\u0011a\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]R,\u0007\u0010^\u0001\rO\u0016$h*\u001e7m-\u0006dW/\u001a\u000b\u0003=\u0001CQ!Q\u0002A\u0002m\nAa\u0019;yi\u0002"
)
public class ExecutorMetricsJsonDeserializer extends JsonDeserializer {
   public Option deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) {
      Option metricsMap = (Option)jsonParser.readValueAs(new TypeReference() {
      });
      return metricsMap.map((metrics) -> new ExecutorMetrics(metrics));
   }

   public Option getNullValue(final DeserializationContext ctxt) {
      return .MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

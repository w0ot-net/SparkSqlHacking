package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf$;
import scala.Option;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Q\u0001B\u0003\u0001\u0013=A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006g\u0001!\t\u0005\u000e\u0002\u0014'B\f'o[\"p]\u001aLw\r\u0015:pm&$WM\u001d\u0006\u0003\r\u001d\taaY8oM&<'B\u0001\u0005\n\u0003!Ig\u000e^3s]\u0006d'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\u0007\u0001\u0001b\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0003/ai\u0011!B\u0005\u00033\u0015\u0011abQ8oM&<\u0007K]8wS\u0012,'/\u0001\u0003d_:47\u0001\u0001\t\u0005;\t\"C%D\u0001\u001f\u0015\ty\u0002%\u0001\u0003vi&d'\"A\u0011\u0002\t)\fg/Y\u0005\u0003Gy\u00111!T1q!\t)CF\u0004\u0002'UA\u0011qEE\u0007\u0002Q)\u0011\u0011fG\u0001\u0007yI|w\u000e\u001e \n\u0005-\u0012\u0012A\u0002)sK\u0012,g-\u0003\u0002.]\t11\u000b\u001e:j]\u001eT!a\u000b\n\u0002\rqJg.\u001b;?)\t\t$\u0007\u0005\u0002\u0018\u0001!)!D\u0001a\u00019\u0005\u0019q-\u001a;\u0015\u0005UB\u0004cA\t7I%\u0011qG\u0005\u0002\u0007\u001fB$\u0018n\u001c8\t\u000be\u001a\u0001\u0019\u0001\u0013\u0002\u0007-,\u0017\u0010"
)
public class SparkConfigProvider implements ConfigProvider {
   private final Map conf;

   public Option get(final String key) {
      return (Option)(key.startsWith("spark.") ? .MODULE$.apply(this.conf.get(key)).orElse(() -> SparkConf$.MODULE$.getDeprecatedConfig(key, this.conf)) : scala.None..MODULE$);
   }

   public SparkConfigProvider(final Map conf) {
      this.conf = conf;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.sql.streaming;

import java.util.Map;
import org.json4s.JValue;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e;Q!\u0002\u0004\t\nE1Qa\u0005\u0004\t\nQAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQ\u0001N\u0001\u0005\u0002U\n!cU1gK*\u001bxN\\*fe&\fG.\u001b>fe*\u0011q\u0001C\u0001\ngR\u0014X-Y7j]\u001eT!!\u0003\u0006\u0002\u0007M\fHN\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h\u0007\u0001\u0001\"AE\u0001\u000e\u0003\u0019\u0011!cU1gK*\u001bxN\\*fe&\fG.\u001b>feN\u0011\u0011!\u0006\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\t\u0012AE:bM\u0016$u.\u001e2mKR{'JV1mk\u0016$\"aH\u0018\u0011\u0005\u0001bcBA\u0011*\u001d\t\u0011sE\u0004\u0002$M5\tAE\u0003\u0002&!\u00051AH]8pizJ\u0011aD\u0005\u0003Q9\taA[:p]R\u001a\u0018B\u0001\u0016,\u0003\u001dQ5o\u001c8B'RS!\u0001\u000b\b\n\u00055r#A\u0002&WC2,XM\u0003\u0002+W!)\u0001g\u0001a\u0001c\u0005)a/\u00197vKB\u0011aCM\u0005\u0003g]\u0011a\u0001R8vE2,\u0017aD:bM\u0016l\u0015\r\u001d+p\u0015Z\u000bG.^3\u0016\u0005YZEcA\u00108)\")\u0001\b\u0002a\u0001s\u0005\u0019Q.\u00199\u0011\tiz\u0014)S\u0007\u0002w)\u0011A(P\u0001\u0005kRLGNC\u0001?\u0003\u0011Q\u0017M^1\n\u0005\u0001[$aA'baB\u0011!I\u0012\b\u0003\u0007\u0012\u0003\"aI\f\n\u0005\u0015;\u0012A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!R\f\u0011\u0005)[E\u0002\u0001\u0003\u0006\u0019\u0012\u0011\r!\u0014\u0002\u0002)F\u0011a*\u0015\t\u0003-=K!\u0001U\f\u0003\u000f9{G\u000f[5oOB\u0011aCU\u0005\u0003'^\u00111!\u00118z\u0011\u0015)F\u00011\u0001W\u000351\u0018\r\\;f)>Te+\u00197vKB!acV% \u0013\tAvCA\u0005Gk:\u001cG/[8oc\u0001"
)
public final class SafeJsonSerializer {
   public static JValue safeMapToJValue(final Map map, final Function1 valueToJValue) {
      return SafeJsonSerializer$.MODULE$.safeMapToJValue(map, valueToJValue);
   }

   public static JValue safeDoubleToJValue(final double value) {
      return SafeJsonSerializer$.MODULE$.safeDoubleToJValue(value);
   }
}

package org.apache.spark.mllib.stat.test;

import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.dstream.DStream;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m<a\u0001D\u0007\t\u0002=IbAB\u000e\u000e\u0011\u0003yA\u0004C\u0003-\u0003\u0011\u0005a\u0006C\u00040\u0003\t\u0007IQ\t\u0019\t\rQ\n\u0001\u0015!\u00042\u0011\u001d)\u0014A1A\u0005FYBaAO\u0001!\u0002\u001b9\u0004bB\u001e\u0002\u0005\u0004%i\u0001\u0010\u0005\u0007%\u0006\u0001\u000bQB\u001f\t\u000bM\u000bA\u0011\t+\t\u000b9\tA\u0011\u00024\t\u000fE\f\u0011\u0011!C\u0005e\u0006a1\u000b^;eK:$H\u000bV3ti*\u0011abD\u0001\u0005i\u0016\u001cHO\u0003\u0002\u0011#\u0005!1\u000f^1u\u0015\t\u00112#A\u0003nY2L'M\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h!\tQ\u0012!D\u0001\u000e\u00051\u0019F/\u001e3f]R$F+Z:u'\u0011\tQd\t\u0014\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g!\tQB%\u0003\u0002&\u001b\t\u00192\u000b\u001e:fC6Lgn\u001a+fgRlU\r\u001e5pIB\u0011qEK\u0007\u0002Q)\u0011\u0011fE\u0001\tS:$XM\u001d8bY&\u00111\u0006\u000b\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#A\r\u0002\u00155,G\u000f[8e\u001d\u0006lW-F\u00012\u001f\u0005\u0011\u0014%A\u001a\u00023M#X\u000fZ3oi\u001e\u001a\bEM\u0017tC6\u0004H.\u001a\u0011u[Q,7\u000f^\u0001\f[\u0016$\bn\u001c3OC6,\u0007%\u0001\bok2d\u0007*\u001f9pi\",7/[:\u0016\u0003]z\u0011\u0001O\u0011\u0002s\u0005Q\"i\u001c;iA\u001d\u0014x.\u001e9tA!\fg/\u001a\u0011tC6,\u0007%\\3b]\u0006ya.\u001e7m\u0011f\u0004x\u000e\u001e5fg&\u001c\b%A\u0004u)\u0016\u001cH/\u001a:\u0016\u0003u\u00022AP#H\u001b\u0005y$B\u0001!B\u0003\u0015\u0019\u0007.\u001b7m\u0015\t\u00115)A\u0004uo&$H/\u001a:\u000b\u0003\u0011\u000b1aY8n\u0013\t1uH\u0001\u0006NK\u0006$Hj\\2lKJ\u0004\"\u0001\u0013)\u000e\u0003%S!AS&\u0002\u0013%tg-\u001a:f]\u000e,'B\u0001\tM\u0015\tie*A\u0003nCRD7G\u0003\u0002P+\u000591m\\7n_:\u001c\u0018BA)J\u0005\u0015!F+Z:u\u0003!!H+Z:uKJ\u0004\u0013A\u00023p)\u0016\u001cH\u000f\u0006\u0002VAB\u0019akW/\u000e\u0003]S!\u0001W-\u0002\u000f\u0011\u001cHO]3b[*\u0011!lE\u0001\ngR\u0014X-Y7j]\u001eL!\u0001X,\u0003\u000f\u0011\u001bFO]3b[B\u0011!DX\u0005\u0003?6\u00111c\u0015;sK\u0006l\u0017N\\4UKN$(+Z:vYRDQ!Y\u0005A\u0002\t\fA\u0001Z1uCB\u00111\rZ\u0007\u0002\u0003%\u0011Q\r\n\u0002\u0012'VlW.\u0019:z!\u0006L'o\u0015;sK\u0006lGcA/h_\")\u0001N\u0003a\u0001S\u000611\u000f^1ug\u0006\u0003\"A[7\u000e\u0003-T!\u0001\\\n\u0002\tU$\u0018\u000e\\\u0005\u0003].\u00141b\u0015;bi\u000e{WO\u001c;fe\")\u0001O\u0003a\u0001S\u000611\u000f^1ug\n\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u001d\t\u0003ifl\u0011!\u001e\u0006\u0003m^\fA\u0001\\1oO*\t\u00010\u0001\u0003kCZ\f\u0017B\u0001>v\u0005\u0019y%M[3di\u0002"
)
public final class StudentTTest {
   public static DStream doTest(final DStream data) {
      return StudentTTest$.MODULE$.doTest(data);
   }

   public static String nullHypothesis() {
      return StudentTTest$.MODULE$.nullHypothesis();
   }

   public static String methodName() {
      return StudentTTest$.MODULE$.methodName();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return StudentTTest$.MODULE$.LogStringContext(sc);
   }
}

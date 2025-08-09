package scala.concurrent;

import java.util.concurrent.TimeoutException;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaE\u0001\u0005\u0002QAQ!F\u0001\u0005\u0006YAQAR\u0001\u0005\u0006\u001d\u000bQ!Q<bSRT!a\u0002\u0005\u0002\u0015\r|gnY;se\u0016tGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"\u0001D\u0001\u000e\u0003\u0019\u0011Q!Q<bSR\u001c\"!A\b\u0011\u0005A\tR\"\u0001\u0005\n\u0005IA!AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0005)!/Z1esV\u0011q\u0003\t\u000b\u00041iIcBA\r\u001b\u0019\u0001AQaG\u0002A\u0002q\t\u0011\"Y<bSR\f'\r\\3\u0011\u00071ir$\u0003\u0002\u001f\r\tI\u0011i^1ji\u0006\u0014G.\u001a\t\u00033\u0001\"Q!I\u0002C\u0002\t\u0012\u0011\u0001V\t\u0003G\u0019\u0002\"\u0001\u0005\u0013\n\u0005\u0015B!a\u0002(pi\"Lgn\u001a\t\u0003!\u001dJ!\u0001\u000b\u0005\u0003\u0007\u0005s\u0017\u0010C\u0003+\u0007\u0001\u00071&\u0001\u0004bi6{7\u000f\u001e\t\u0003Y=j\u0011!\f\u0006\u0003]\u0019\t\u0001\u0002Z;sCRLwN\\\u0005\u0003a5\u0012\u0001\u0002R;sCRLwN\u001c\u0015\u0004\u0007Ib\u0004c\u0001\t4k%\u0011A\u0007\u0003\u0002\u0007i\"\u0014xn^:\u0011\u0005YJdB\u0001\u00078\u0013\tAd!A\u0004qC\u000e\\\u0017mZ3\n\u0005iZ$\u0001\u0005+j[\u0016|W\u000f^#yG\u0016\u0004H/[8o\u0015\tAdaI\u00016Q\r\u0019a(\u0012\t\u0004!Mz\u0004C\u0001!C\u001d\t\u0001\u0012)\u0003\u00029\u0011%\u00111\t\u0012\u0002\u0015\u0013:$XM\u001d:vaR,G-\u0012=dKB$\u0018n\u001c8\u000b\u0005aB1%A \u0002\rI,7/\u001e7u+\tA%\nF\u0002J\u00176\u0003\"!\u0007&\u0005\u000b\u0005\"!\u0019\u0001\u0012\t\u000bm!\u0001\u0019\u0001'\u0011\u00071i\u0012\nC\u0003+\t\u0001\u00071\u0006K\u0002\u0005eqB3\u0001\u0002 F\u0001"
)
public final class Await {
   public static Object result(final Awaitable awaitable, final Duration atMost) throws TimeoutException, InterruptedException {
      return Await$.MODULE$.result(awaitable, atMost);
   }

   public static Awaitable ready(final Awaitable awaitable, final Duration atMost) throws TimeoutException, InterruptedException {
      return Await$.MODULE$.ready(awaitable, atMost);
   }
}

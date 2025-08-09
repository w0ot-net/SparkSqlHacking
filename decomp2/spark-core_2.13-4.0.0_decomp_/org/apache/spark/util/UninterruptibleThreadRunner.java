package org.apache.spark.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import scala.Function0;
import scala.concurrent.Awaitable;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.concurrent.Future.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3Q!\u0003\u0006\u0001\u0019IA\u0001\"\u0007\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\u0006M\u0001!\ta\n\u0005\bW\u0001\u0011\r\u0011\"\u0003-\u0011\u00191\u0004\u0001)A\u0005[!9q\u0007\u0001b\u0001\n\u0013A\u0004B\u0002 \u0001A\u0003%\u0011\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003S\u0001\u0011\u00051KA\u000eV]&tG/\u001a:skB$\u0018N\u00197f)\"\u0014X-\u00193Sk:tWM\u001d\u0006\u0003\u00171\tA!\u001e;jY*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xm\u0005\u0002\u0001'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\f!\u0002\u001e5sK\u0006$g*Y7f\u0007\u0001\u0001\"\u0001H\u0012\u000f\u0005u\t\u0003C\u0001\u0010\u0016\u001b\u0005y\"B\u0001\u0011\u001b\u0003\u0019a$o\\8u}%\u0011!%F\u0001\u0007!J,G-\u001a4\n\u0005\u0011*#AB*ue&twM\u0003\u0002#+\u00051A(\u001b8jiz\"\"\u0001\u000b\u0016\u0011\u0005%\u0002Q\"\u0001\u0006\t\u000be\u0011\u0001\u0019A\u000e\u0002\rQD'/Z1e+\u0005i\u0003C\u0001\u00185\u001b\u0005y#B\u0001\u00192\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003\u0017IR\u0011aM\u0001\u0005U\u00064\u0018-\u0003\u00026_\tyQ\t_3dkR|'oU3sm&\u001cW-A\u0004uQJ,\u0017\r\u001a\u0011\u0002\u0017\u0015DXmY\"p]R,\u0007\u0010^\u000b\u0002sA\u0011!\bP\u0007\u0002w)\u0011\u0001'F\u0005\u0003{m\u0012q$\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;Fq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u00031)\u00070Z2D_:$X\r\u001f;!\u0003I\u0011XO\\+oS:$XM\u001d:vaRL'\r\\=\u0016\u0005\u0005#EC\u0001\"N!\t\u0019E\t\u0004\u0001\u0005\u000b\u0015;!\u0019\u0001$\u0003\u0003Q\u000b\"a\u0012&\u0011\u0005QA\u0015BA%\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001F&\n\u00051+\"aA!os\"1aj\u0002CA\u0002=\u000bAAY8esB\u0019A\u0003\u0015\"\n\u0005E+\"\u0001\u0003\u001fcs:\fW.\u001a \u0002\u0011MDW\u000f\u001e3po:$\u0012\u0001\u0016\t\u0003)UK!AV\u000b\u0003\tUs\u0017\u000e\u001e"
)
public class UninterruptibleThreadRunner {
   public final String org$apache$spark$util$UninterruptibleThreadRunner$$threadName;
   private final ExecutorService thread;
   private final ExecutionContextExecutorService execContext;

   private ExecutorService thread() {
      return this.thread;
   }

   private ExecutionContextExecutorService execContext() {
      return this.execContext;
   }

   public Object runUninterruptibly(final Function0 body) {
      if (!(Thread.currentThread() instanceof UninterruptibleThread)) {
         Future future = .MODULE$.apply(body, this.execContext());
         return ThreadUtils$.MODULE$.awaitResult((Awaitable)future, scala.concurrent.duration.Duration..MODULE$.Inf());
      } else {
         return body.apply();
      }
   }

   public void shutdown() {
      this.thread().shutdown();
   }

   public UninterruptibleThreadRunner(final String threadName) {
      this.org$apache$spark$util$UninterruptibleThreadRunner$$threadName = threadName;
      this.thread = Executors.newSingleThreadExecutor((r) -> {
         UninterruptibleThread t = new UninterruptibleThread(r) {
            private final Runnable r$1;

            public void run() {
               this.r$1.run();
            }

            public {
               this.r$1 = r$1;
            }
         };
         t.setDaemon(true);
         return t;
      });
      this.execContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(this.thread());
   }
}

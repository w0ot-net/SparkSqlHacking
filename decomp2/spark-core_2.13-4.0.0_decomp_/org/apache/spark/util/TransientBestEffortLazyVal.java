package org.apache.spark.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicReference;
import scala.Function0;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005q3QAB\u0004\u0001\u0013=A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006a\u0001!\t!\r\u0005\u0007k\u0001\u0001\u000b\u0015\u0002\u001c\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b\u001d\u0003A\u0011\u0002%\u00035Q\u0013\u0018M\\:jK:$()Z:u\u000b\u001a4wN\u001d;MCjLh+\u00197\u000b\u0005!I\u0011\u0001B;uS2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u000b\u0003!)\u001a2\u0001A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001$\t\b\u00033}q!A\u0007\u0010\u000e\u0003mQ!\u0001H\u000f\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001F\u0005\u0003AM\tq\u0001]1dW\u0006<W-\u0003\u0002#G\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001eE\u0001\bG>l\u0007/\u001e;f!\r\u0011b\u0005K\u0005\u0003OM\u0011\u0011BR;oGRLwN\u001c\u0019\u0011\u0005%RC\u0002\u0001\u0003\u0006W\u0001\u0011\r\u0001\f\u0002\u0002)F\u0011Q&\u0005\t\u0003%9J!aL\n\u0003\u000f9{G\u000f[5oO\u00061A(\u001b8jiz\"\"A\r\u001b\u0011\u0007M\u0002\u0001&D\u0001\b\u0011\u0015!#\u00011\u0001&\u0003\u0019\u0019\u0017m\u00195fIB\u0019qg\u0010\u0015\u000e\u0003aR!!\u000f\u001e\u0002\r\u0005$x.\\5d\u0015\tYD(\u0001\u0006d_:\u001cWO\u001d:f]RT!\u0001C\u001f\u000b\u0003y\nAA[1wC&\u0011\u0001\t\u000f\u0002\u0010\u0003R|W.[2SK\u001a,'/\u001a8dK\"\u00121A\u0011\t\u0003%\rK!\u0001R\n\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!B1qa2LH#\u0001\u0015\u0002\u0015I,\u0017\rZ(cU\u0016\u001cG\u000f\u0006\u0002J\u0019B\u0011!CS\u0005\u0003\u0017N\u0011A!\u00168ji\")Q*\u0002a\u0001\u001d\u0006\u0019q.[:\u0011\u0005=\u0013V\"\u0001)\u000b\u0005Ek\u0014AA5p\u0013\t\u0019\u0006KA\tPE*,7\r^%oaV$8\u000b\u001e:fC6D3!B+\\!\r\u0011b\u000bW\u0005\u0003/N\u0011a\u0001\u001e5s_^\u001c\bCA(Z\u0013\tQ\u0006KA\u0006J\u001f\u0016C8-\u001a9uS>t7%\u0001-"
)
public class TransientBestEffortLazyVal implements Serializable {
   private final Function0 compute;
   private transient AtomicReference cached;

   public Object apply() {
      Object value = this.cached.get();
      if (value != null) {
         return value;
      } else {
         Object newValue = this.compute.apply();
         .MODULE$.assert(newValue != null, () -> "compute function cannot return null.");
         this.cached.compareAndSet((Object)null, newValue);
         return this.cached.get();
      }
   }

   private void readObject(final ObjectInputStream ois) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         ois.defaultReadObject();
         this.cached = new AtomicReference((Object)null);
      });
   }

   public TransientBestEffortLazyVal(final Function0 compute) {
      this.compute = compute;
      this.cached = new AtomicReference((Object)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

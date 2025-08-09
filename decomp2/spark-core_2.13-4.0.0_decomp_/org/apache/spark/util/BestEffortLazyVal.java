package org.apache.spark.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicReference;
import scala.Function0;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Q!\u0002\u0004\u0001\u00119A\u0001b\t\u0001\u0003\u0002\u0003\u0006K\u0001\n\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0007q\u0001\u0001\u000b\u0011B\u001d\t\u000b\u0011\u0003A\u0011A#\u0003#\t+7\u000f^#gM>\u0014H\u000fT1{sZ\u000bGN\u0003\u0002\b\u0011\u0005!Q\u000f^5m\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<WCA\b*'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]\u0001cB\u0001\r\u001f\u001d\tIR$D\u0001\u001b\u0015\tYB$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0019\u0012BA\u0010\u0013\u0003\u001d\u0001\u0018mY6bO\u0016L!!\t\u0012\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005}\u0011\u0012aB2p[B,H/\u001a\t\u0004#\u0015:\u0013B\u0001\u0014\u0013\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0002)S1\u0001A!\u0002\u0016\u0001\u0005\u0004Y#!\u0001+\u0012\u00051\u0002\u0002CA\t.\u0013\tq#CA\u0004O_RD\u0017N\\4)\u0005\u0005\u0001\u0004CA\t2\u0013\t\u0011$C\u0001\u0005w_2\fG/\u001b7f\u0003\u0019a\u0014N\\5u}Q\u0011Qg\u000e\t\u0004m\u00019S\"\u0001\u0004\t\u000b\r\u0012\u0001\u0019\u0001\u0013\u0002\r\r\f7\r[3e!\rQ$iJ\u0007\u0002w)\u0011A(P\u0001\u0007CR|W.[2\u000b\u0005yz\u0014AC2p]\u000e,(O]3oi*\u0011q\u0001\u0011\u0006\u0002\u0003\u0006!!.\u0019<b\u0013\t\u00195HA\bBi>l\u0017n\u0019*fM\u0016\u0014XM\\2f\u0003\u0015\t\u0007\u000f\u001d7z)\u00059\u0003"
)
public class BestEffortLazyVal implements Serializable {
   private volatile Function0 compute;
   private final AtomicReference cached;

   public Object apply() {
      Object value = this.cached.get();
      if (value != null) {
         return value;
      } else {
         Function0 f = this.compute;
         if (f != null) {
            Object newValue = f.apply();
            .MODULE$.assert(newValue != null, () -> "compute function cannot return null.");
            this.cached.compareAndSet((Object)null, newValue);
            this.compute = null;
         }

         return this.cached.get();
      }
   }

   public BestEffortLazyVal(final Function0 compute) {
      this.compute = compute;
      super();
      this.cached = new AtomicReference((Object)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

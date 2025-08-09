package org.apache.spark.ml.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114a\u0001E\t\u0002\u0002UY\u0002\"B\u0018\u0001\t\u0003\u0001\u0004bB\u001a\u0001\u0001\u0004%I\u0001\u000e\u0005\bq\u0001\u0001\r\u0011\"\u0003:\u0011\u0019y\u0004\u0001)Q\u0005k!IA\t\u0001a\u0001\u0002\u0004%I!\u0012\u0005\n\u0013\u0002\u0001\r\u00111A\u0005\n)C\u0011\u0002\u0014\u0001A\u0002\u0003\u0005\u000b\u0015\u0002$\t\u000f5\u0003!\u0019!D\u0001\u001d\")q\u000b\u0001C\u00011\")\u0011\f\u0001C\u00015\")1\f\u0001C\u0001i!)A\f\u0001D\u00015\")Q\f\u0001C!=\")q\f\u0001C\t\u000b\")\u0001\r\u0001D\tC\nI1\u000b^8qo\u0006$8\r\u001b\u0006\u0003%M\tA!\u001e;jY*\u0011A#F\u0001\u0003[2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\n\u0004\u0001q\u0011\u0003CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g\r\u0005\u0002$Y9\u0011AE\u000b\b\u0003K%j\u0011A\n\u0006\u0003O!\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002?%\u00111FH\u0001\ba\u0006\u001c7.Y4f\u0013\ticF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002,=\u00051A(\u001b8jiz\"\u0012!\r\t\u0003e\u0001i\u0011!E\u0001\beVtg.\u001b8h+\u0005)\u0004CA\u000f7\u0013\t9dDA\u0004C_>dW-\u00198\u0002\u0017I,hN\\5oO~#S-\u001d\u000b\u0003uu\u0002\"!H\u001e\n\u0005qr\"\u0001B+oSRDqAP\u0002\u0002\u0002\u0003\u0007Q'A\u0002yIE\n\u0001B];o]&tw\r\t\u0015\u0003\t\u0005\u0003\"!\b\"\n\u0005\rs\"!\u0003;sC:\u001c\u0018.\u001a8u\u0003%\u0019H/\u0019:u)&lW-F\u0001G!\tir)\u0003\u0002I=\t!Aj\u001c8h\u00035\u0019H/\u0019:u)&lWm\u0018\u0013fcR\u0011!h\u0013\u0005\b}\u0019\t\t\u00111\u0001G\u0003)\u0019H/\u0019:u)&lW\rI\u0001\u0005]\u0006lW-F\u0001P!\t\u0001FK\u0004\u0002R%B\u0011QEH\u0005\u0003'z\ta\u0001\u0015:fI\u00164\u0017BA+W\u0005\u0019\u0019FO]5oO*\u00111KH\u0001\u0006gR\f'\u000f\u001e\u000b\u0002u\u0005!1\u000f^8q)\u00051\u0015!C5t%Vtg.\u001b8h\u0003\u001d)G.\u00199tK\u0012\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u001f\u0006\u0019an\\<\u0002\u0007\u0005$G\r\u0006\u0002;E\")1m\u0004a\u0001\r\u0006AA-\u001e:bi&|g\u000e"
)
public abstract class Stopwatch implements Serializable {
   private transient boolean running = false;
   private long startTime;

   private boolean running() {
      return this.running;
   }

   private void running_$eq(final boolean x$1) {
      this.running = x$1;
   }

   private long startTime() {
      return this.startTime;
   }

   private void startTime_$eq(final long x$1) {
      this.startTime = x$1;
   }

   public abstract String name();

   public void start() {
      .MODULE$.assume(!this.running(), () -> "start() called but the stopwatch is already running.");
      this.running_$eq(true);
      this.startTime_$eq(this.now());
   }

   public long stop() {
      .MODULE$.assume(this.running(), () -> "stop() called but the stopwatch is not running.");
      long duration = this.now() - this.startTime();
      this.add(duration);
      this.running_$eq(false);
      return duration;
   }

   public boolean isRunning() {
      return this.running();
   }

   public abstract long elapsed();

   public String toString() {
      String var10000 = this.name();
      return var10000 + ": " + this.elapsed() + "ms";
   }

   public long now() {
      return TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
   }

   public abstract void add(final long duration);

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

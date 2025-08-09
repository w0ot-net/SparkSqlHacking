package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U3Q!\u0003\u0006\u0001!YAQA\u000b\u0001\u0005\u0002-BqA\f\u0001C\u0002\u0013%q\u0006\u0003\u0004D\u0001\u0001\u0006I\u0001\r\u0005\b\t\u0002\u0011\r\u0011\"\u00030\u0011\u0019)\u0005\u0001)A\u0005a!)a\t\u0001C\u0001\u000f\")Q\n\u0001C\u0001\u001d\")1\u000b\u0001C!)\nYA+[7f)J\f7m[3s\u0015\tYA\"\u0001\u0003j[Bd'BA\u0007\u000f\u0003\u0011!(/Z3\u000b\u0005=\u0001\u0012AA7m\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0014\u000f\u0005})cB\u0001\u0011%\u001b\u0005\t#B\u0001\u0012$\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u000e\n\u0005\u0019J\u0012a\u00029bG.\fw-Z\u0005\u0003Q%\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AJ\r\u0002\rqJg.\u001b;?)\u0005a\u0003CA\u0017\u0001\u001b\u0005Q\u0011AB:uCJ$8/F\u00011!\u0011\td\u0007\u000f!\u000e\u0003IR!a\r\u001b\u0002\u000f5,H/\u00192mK*\u0011Q'G\u0001\u000bG>dG.Z2uS>t\u0017BA\u001c3\u0005\u001dA\u0015m\u001d5NCB\u0004\"!O\u001f\u000f\u0005iZ\u0004C\u0001\u0011\u001a\u0013\ta\u0014$\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f\u001a!\tA\u0012)\u0003\u0002C3\t!Aj\u001c8h\u0003\u001d\u0019H/\u0019:ug\u0002\na\u0001^8uC2\u001c\u0018a\u0002;pi\u0006d7\u000fI\u0001\u0006gR\f'\u000f\u001e\u000b\u0003\u0011.\u0003\"\u0001G%\n\u0005)K\"\u0001B+oSRDQ\u0001\u0014\u0004A\u0002a\n!\u0002^5nKJd\u0015MY3m\u0003\u0011\u0019Ho\u001c9\u0015\u0005=\u0013\u0006C\u0001\rQ\u0013\t\t\u0016D\u0001\u0004E_V\u0014G.\u001a\u0005\u0006\u0019\u001e\u0001\r\u0001O\u0001\ti>\u001cFO]5oOR\t\u0001\b"
)
public class TimeTracker implements Serializable {
   private final HashMap starts = new HashMap();
   private final HashMap totals = new HashMap();

   private HashMap starts() {
      return this.starts;
   }

   private HashMap totals() {
      return this.totals;
   }

   public void start(final String timerLabel) {
      long currentTime = System.nanoTime();
      if (this.starts().contains(timerLabel)) {
         throw new RuntimeException("TimeTracker.start(timerLabel) called again on timerLabel = " + timerLabel + " before that timer was stopped.");
      } else {
         this.starts().update(timerLabel, BoxesRunTime.boxToLong(currentTime));
      }
   }

   public double stop(final String timerLabel) {
      long currentTime = System.nanoTime();
      if (!this.starts().contains(timerLabel)) {
         throw new RuntimeException("TimeTracker.stop(timerLabel) called on timerLabel = " + timerLabel + ", but that timer was not started.");
      } else {
         long elapsed = currentTime - BoxesRunTime.unboxToLong(this.starts().apply(timerLabel));
         this.starts().remove(timerLabel);
         if (this.totals().contains(timerLabel)) {
            this.totals().update(timerLabel, BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(this.totals().apply(timerLabel)) + elapsed));
         } else {
            this.totals().update(timerLabel, BoxesRunTime.boxToLong(elapsed));
         }

         return (double)elapsed / (double)1.0E9F;
      }
   }

   public String toString() {
      return ((IterableOnceOps)this.totals().map((x0$1) -> {
         if (x0$1 != null) {
            String label = (String)x0$1._1();
            long elapsed = x0$1._2$mcJ$sp();
            return "  " + label + ": " + (double)elapsed / (double)1.0E9F;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("\n");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

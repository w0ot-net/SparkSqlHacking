package org.apache.spark.ml;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005q2qa\u0001\u0003\u0011\u0002\u0007\u0005R\u0002C\u0003\u001b\u0001\u0011\u00051\u0004\u0003\u0004 \u0001\u0011Ec\u0001\t\u0002\b\u001b2+e/\u001a8u\u0015\t)a!\u0001\u0002nY*\u0011q\u0001C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0013)\ta!\u00199bG\",'\"A\u0006\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/\u0019\t\u0011b]2iK\u0012,H.\u001a:\n\u0005e1\"AE*qCJ\\G*[:uK:,'/\u0012<f]R\fa\u0001J5oSR$C#\u0001\u000f\u0011\u0005=i\u0012B\u0001\u0010\u0011\u0005\u0011)f.\u001b;\u0002\u00111|w-\u0012<f]R,\u0012!\t\t\u0003\u001f\tJ!a\t\t\u0003\u000f\t{w\u000e\\3b]&J\u0001!J\u0014*W5z\u0013gM\u0005\u0003M\u0011\u0011aAR5u\u000b:$\u0017B\u0001\u0015\u0005\u0005!1\u0015\u000e^*uCJ$\u0018B\u0001\u0016\u0005\u0005=au.\u00193J]N$\u0018M\\2f\u000b:$\u0017B\u0001\u0017\u0005\u0005Eau.\u00193J]N$\u0018M\\2f'R\f'\u000f^\u0005\u0003]\u0011\u0011qbU1wK&s7\u000f^1oG\u0016,e\u000eZ\u0005\u0003a\u0011\u0011\u0011cU1wK&s7\u000f^1oG\u0016\u001cF/\u0019:u\u0013\t\u0011DA\u0001\u0007Ue\u0006t7OZ8s[\u0016sG-\u0003\u00025\t\tqAK]1og\u001a|'/\\*uCJ$\bF\u0001\u00017!\t9$(D\u00019\u0015\tId!\u0001\u0006b]:|G/\u0019;j_:L!a\u000f\u001d\u0003\u0011\u00153x\u000e\u001c<j]\u001e\u0004"
)
public interface MLEvent extends SparkListenerEvent {
   // $FF: synthetic method
   static boolean logEvent$(final MLEvent $this) {
      return $this.logEvent();
   }

   default boolean logEvent() {
      return false;
   }

   static void $init$(final MLEvent $this) {
   }
}

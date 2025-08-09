package org.apache.spark.scheduler;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q1QAA\u0002\u0001\u000b-AQ\u0001\u0005\u0001\u0005\u0002I\u00111FQ1se&,'OS8c%Vtw+\u001b;i\tft\u0017-\\5d\u00032dwnY1uS>tW\t_2faRLwN\u001c\u0006\u0003\t\u0015\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u00199\u0011!B:qCJ\\'B\u0001\u0005\n\u0003\u0019\t\u0007/Y2iK*\t!\"A\u0002pe\u001e\u001c\"\u0001\u0001\u0007\u0011\u00055qQ\"A\u0002\n\u0005=\u0019!A\u0007\"beJLWM\u001d&pE\u0006cGn\\2bi&|gNR1jY\u0016$\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003M\u0001\"!\u0004\u0001"
)
public class BarrierJobRunWithDynamicAllocationException extends BarrierJobAllocationFailed {
   public BarrierJobRunWithDynamicAllocationException() {
      super(BarrierJobAllocationFailed$.MODULE$.ERROR_MESSAGE_RUN_BARRIER_WITH_DYN_ALLOCATION());
   }
}

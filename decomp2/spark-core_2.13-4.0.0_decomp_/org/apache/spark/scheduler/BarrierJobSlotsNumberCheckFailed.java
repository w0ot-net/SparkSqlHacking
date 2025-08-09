package org.apache.spark.scheduler;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152QAB\u0004\u0001\u0013=A\u0001\u0002\u0006\u0001\u0003\u0006\u0004%\tA\u0006\u0005\t;\u0001\u0011\t\u0011)A\u0005/!Aa\u0004\u0001BC\u0002\u0013\u0005a\u0003\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u0018\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0005\u0001\u0012\u0015M\u001d:jKJTuNY*m_R\u001ch*^7cKJ\u001c\u0005.Z2l\r\u0006LG.\u001a3\u000b\u0005!I\u0011!C:dQ\u0016$W\u000f\\3s\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7C\u0001\u0001\u0011!\t\t\"#D\u0001\b\u0013\t\u0019rA\u0001\u000eCCJ\u0014\u0018.\u001a:K_\n\fE\u000e\\8dCRLwN\u001c$bS2,G-A\fsKF,\u0018N]3e\u0007>t7-\u001e:sK:$H+Y:lg\u000e\u0001Q#A\f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\u0007%sG/\u0001\rsKF,\u0018N]3e\u0007>t7-\u001e:sK:$H+Y:lg\u0002\n!#\\1y\u0007>t7-\u001e:sK:$H+Y:lg\u0006\u0019R.\u0019=D_:\u001cWO\u001d:f]R$\u0016m]6tA\u00051A(\u001b8jiz\"2AI\u0012%!\t\t\u0002\u0001C\u0003\u0015\u000b\u0001\u0007q\u0003C\u0003\u001f\u000b\u0001\u0007q\u0003"
)
public class BarrierJobSlotsNumberCheckFailed extends BarrierJobAllocationFailed {
   private final int requiredConcurrentTasks;
   private final int maxConcurrentTasks;

   public int requiredConcurrentTasks() {
      return this.requiredConcurrentTasks;
   }

   public int maxConcurrentTasks() {
      return this.maxConcurrentTasks;
   }

   public BarrierJobSlotsNumberCheckFailed(final int requiredConcurrentTasks, final int maxConcurrentTasks) {
      super(BarrierJobAllocationFailed$.MODULE$.ERROR_MESSAGE_BARRIER_REQUIRE_MORE_SLOTS_THAN_CURRENT_TOTAL_NUMBER());
      this.requiredConcurrentTasks = requiredConcurrentTasks;
      this.maxConcurrentTasks = maxConcurrentTasks;
   }
}

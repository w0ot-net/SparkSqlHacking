package org.apache.spark.scheduler;

import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2Qa\u0001\u0003\u0001\r1AQa\u0006\u0001\u0005\u0002eAQa\u0007\u0001\u0005Bq\u0011qCR1jeN\u001b\u0007.\u001a3vY&tw-\u00117h_JLG\u000f[7\u000b\u0005\u00151\u0011!C:dQ\u0016$W\u000f\\3s\u0015\t9\u0001\"A\u0003ta\u0006\u00148N\u0003\u0002\n\u0015\u00051\u0011\r]1dQ\u0016T\u0011aC\u0001\u0004_J<7c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001F\u000b\u000e\u0003\u0011I!A\u0006\u0003\u0003'M\u001b\u0007.\u001a3vY&tw-\u00117h_JLG\u000f[7\u0002\rqJg.\u001b;?\u0007\u0001!\u0012A\u0007\t\u0003)\u0001\t!bY8na\u0006\u0014\u0018\r^8s)\ri\u0002%\n\t\u0003\u001dyI!aH\b\u0003\u000f\t{w\u000e\\3b]\")\u0011E\u0001a\u0001E\u0005\u00111/\r\t\u0003)\rJ!\u0001\n\u0003\u0003\u0017M\u001b\u0007.\u001a3vY\u0006\u0014G.\u001a\u0005\u0006M\t\u0001\rAI\u0001\u0003gJ\u0002"
)
public class FairSchedulingAlgorithm implements SchedulingAlgorithm {
   public boolean comparator(final Schedulable s1, final Schedulable s2) {
      int minShare1 = s1.minShare();
      int minShare2 = s2.minShare();
      int runningTasks1 = s1.runningTasks();
      int runningTasks2 = s2.runningTasks();
      boolean s1Needy = runningTasks1 < minShare1;
      boolean s2Needy = runningTasks2 < minShare2;
      double minShareRatio1 = (double)runningTasks1 / .MODULE$.max((double)minShare1, (double)1.0F);
      double minShareRatio2 = (double)runningTasks2 / .MODULE$.max((double)minShare2, (double)1.0F);
      double taskToWeightRatio1 = (double)runningTasks1 / (double)s1.weight();
      double taskToWeightRatio2 = (double)runningTasks2 / (double)s2.weight();
      int compare = 0;
      if (s1Needy && !s2Needy) {
         return true;
      } else if (!s1Needy && s2Needy) {
         return false;
      } else {
         if (s1Needy && s2Needy) {
            compare = scala.Predef..MODULE$.double2Double(minShareRatio1).compareTo(scala.Predef..MODULE$.double2Double(minShareRatio2));
         } else {
            compare = scala.Predef..MODULE$.double2Double(taskToWeightRatio1).compareTo(scala.Predef..MODULE$.double2Double(taskToWeightRatio2));
         }

         if (compare < 0) {
            return true;
         } else {
            return compare > 0 ? false : scala.collection.StringOps..MODULE$.$less$extension(scala.Predef..MODULE$.augmentString(s1.name()), s2.name());
         }
      }
   }
}

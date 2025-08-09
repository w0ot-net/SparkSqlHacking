package org.apache.spark.status.api.v1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.apache.spark.util.EnumUtil;

public enum TaskSorting {
   ID(new String[0]),
   INCREASING_RUNTIME(new String[]{"runtime"}),
   DECREASING_RUNTIME(new String[]{"-runtime"});

   private final Set alternateNames = new HashSet();

   private TaskSorting(String... names) {
      Collections.addAll(this.alternateNames, names);
   }

   public static TaskSorting fromString(String str) {
      String lower = str.toLowerCase(Locale.ROOT);

      for(TaskSorting t : values()) {
         if (t.alternateNames.contains(lower)) {
            return t;
         }
      }

      return (TaskSorting)EnumUtil.parseIgnoreCase(TaskSorting.class, str);
   }

   // $FF: synthetic method
   private static TaskSorting[] $values() {
      return new TaskSorting[]{ID, INCREASING_RUNTIME, DECREASING_RUNTIME};
   }
}

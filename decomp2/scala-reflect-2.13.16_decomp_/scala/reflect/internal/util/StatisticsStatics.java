package scala.reflect.internal.util;

import java.lang.invoke.MethodHandle;

public final class StatisticsStatics {
   private static final AlmostFinalValue COLD_STATS = new AlmostFinalValue();
   private static final AlmostFinalValue HOT_STATS = new AlmostFinalValue();
   private static final AlmostFinalValue DEBUG = new AlmostFinalValue();
   private static final AlmostFinalValue DEVELOPER = new AlmostFinalValue();
   public static final MethodHandle COLD_STATS_GETTER;
   public static final MethodHandle HOT_STATS_GETTER;
   public static final MethodHandle DEBUG_GETTER;
   public static final MethodHandle DEVELOPER_GETTER;

   public static void enableColdStatsAndDeoptimize() {
      COLD_STATS.toggleOnAndDeoptimize();
   }

   public static void enableHotStatsAndDeoptimize() {
      HOT_STATS.toggleOnAndDeoptimize();
   }

   public static void enableDebugAndDeoptimize() {
      DEBUG.toggleOnAndDeoptimize();
   }

   public static void enableDeveloperAndDeoptimize() {
      DEVELOPER.toggleOnAndDeoptimize();
   }

   static {
      COLD_STATS_GETTER = COLD_STATS.invoker;
      HOT_STATS_GETTER = HOT_STATS.invoker;
      DEBUG_GETTER = DEBUG.invoker;
      DEVELOPER_GETTER = DEVELOPER.invoker;
   }
}

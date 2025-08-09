package org.apache.spark.launcher;

import java.util.concurrent.ThreadFactory;
import org.apache.spark.util.ThreadUtils$;

public final class LauncherBackend$ {
   public static final LauncherBackend$ MODULE$ = new LauncherBackend$();
   private static final ThreadFactory threadFactory;

   static {
      threadFactory = ThreadUtils$.MODULE$.namedThreadFactory("LauncherBackend");
   }

   public ThreadFactory threadFactory() {
      return threadFactory;
   }

   private LauncherBackend$() {
   }
}

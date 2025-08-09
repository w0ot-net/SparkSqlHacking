package org.apache.commons.io;

import java.io.File;

/** @deprecated */
@Deprecated
public class FileCleaner {
   private static final FileCleaningTracker INSTANCE = new FileCleaningTracker();

   /** @deprecated */
   @Deprecated
   public static synchronized void exitWhenFinished() {
      INSTANCE.exitWhenFinished();
   }

   public static FileCleaningTracker getInstance() {
      return INSTANCE;
   }

   /** @deprecated */
   @Deprecated
   public static int getTrackCount() {
      return INSTANCE.getTrackCount();
   }

   /** @deprecated */
   @Deprecated
   public static void track(File file, Object marker) {
      INSTANCE.track(file, marker);
   }

   /** @deprecated */
   @Deprecated
   public static void track(File file, Object marker, FileDeleteStrategy deleteStrategy) {
      INSTANCE.track(file, marker, deleteStrategy);
   }

   /** @deprecated */
   @Deprecated
   public static void track(String path, Object marker) {
      INSTANCE.track(path, marker);
   }

   /** @deprecated */
   @Deprecated
   public static void track(String path, Object marker, FileDeleteStrategy deleteStrategy) {
      INSTANCE.track(path, marker, deleteStrategy);
   }
}

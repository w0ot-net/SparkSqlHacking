package org.apache.ivy.plugins.lock;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

final class DeleteOnExitHook {
   private static final Set files;

   private DeleteOnExitHook() {
   }

   static synchronized void add(File file) {
      files.add(file);
   }

   static synchronized void remove(File file) {
      files.remove(file);
   }

   static synchronized void runHook() {
      Iterator<File> itr = files.iterator();

      while(itr.hasNext()) {
         ((File)itr.next()).delete();
         itr.remove();
      }

   }

   static {
      Runtime.getRuntime().addShutdownHook(new Thread() {
         public void run() {
            DeleteOnExitHook.runHook();
         }
      });
      files = new LinkedHashSet();
   }
}

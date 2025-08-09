package org.apache.commons.compress.compressors.pack200;

import java.io.IOException;

public enum Pack200Strategy {
   IN_MEMORY {
      AbstractStreamBridge newStreamBridge() {
         return new InMemoryCachingStreamBridge();
      }
   },
   TEMP_FILE {
      AbstractStreamBridge newStreamBridge() throws IOException {
         return new TempFileCachingStreamBridge();
      }
   };

   private Pack200Strategy() {
   }

   abstract AbstractStreamBridge newStreamBridge() throws IOException;

   // $FF: synthetic method
   private static Pack200Strategy[] $values() {
      return new Pack200Strategy[]{IN_MEMORY, TEMP_FILE};
   }
}

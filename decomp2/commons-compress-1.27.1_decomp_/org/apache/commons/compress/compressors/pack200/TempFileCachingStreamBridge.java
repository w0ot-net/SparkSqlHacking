package org.apache.commons.compress.compressors.pack200;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

final class TempFileCachingStreamBridge extends AbstractStreamBridge {
   private final Path path = Files.createTempFile("commons-compress", "packtemp");

   TempFileCachingStreamBridge() throws IOException {
      this.path.toFile().deleteOnExit();
      this.out = Files.newOutputStream(this.path);
   }

   InputStream createInputStream() throws IOException {
      this.out.close();
      return new FilterInputStream(Files.newInputStream(this.path)) {
         public void close() throws IOException {
            try {
               super.close();
            } finally {
               try {
                  Files.deleteIfExists(TempFileCachingStreamBridge.this.path);
               } catch (IOException var7) {
               }

            }

         }
      };
   }
}

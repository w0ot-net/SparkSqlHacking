package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;

public class DefaultBackingStoreSupplier implements ScatterGatherBackingStoreSupplier {
   private static final String PREFIX = "parallelscatter";
   private final AtomicInteger storeNum = new AtomicInteger();
   private final Path dir;

   public DefaultBackingStoreSupplier(Path dir) {
      this.dir = dir;
   }

   public ScatterGatherBackingStore get() throws IOException {
      String suffix = "n" + this.storeNum.incrementAndGet();
      Path tempFile = this.dir == null ? Files.createTempFile("parallelscatter", suffix) : Files.createTempFile(this.dir, "parallelscatter", suffix);
      return new FileBasedScatterGatherBackingStore(tempFile);
   }
}

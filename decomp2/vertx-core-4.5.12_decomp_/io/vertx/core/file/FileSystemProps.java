package io.vertx.core.file;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface FileSystemProps {
   long totalSpace();

   long unallocatedSpace();

   long usableSpace();
}

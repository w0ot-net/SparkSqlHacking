package io.vertx.core.file;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface FileProps {
   long creationTime();

   long lastAccessTime();

   long lastModifiedTime();

   boolean isDirectory();

   boolean isOther();

   boolean isRegularFile();

   boolean isSymbolicLink();

   long size();
}

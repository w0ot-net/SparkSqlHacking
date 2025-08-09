package io.vertx.core.file.impl;

import io.vertx.core.file.FileProps;
import java.nio.file.attribute.BasicFileAttributes;

public class FilePropsImpl implements FileProps {
   private final long creationTime;
   private final long lastAccessTime;
   private final long lastModifiedTime;
   private final boolean isDirectory;
   private final boolean isOther;
   private final boolean isRegularFile;
   private final boolean isSymbolicLink;
   private final long size;

   public FilePropsImpl(BasicFileAttributes attrs) {
      this.creationTime = attrs.creationTime().toMillis();
      this.lastModifiedTime = attrs.lastModifiedTime().toMillis();
      this.lastAccessTime = attrs.lastAccessTime().toMillis();
      this.isDirectory = attrs.isDirectory();
      this.isOther = attrs.isOther();
      this.isRegularFile = attrs.isRegularFile();
      this.isSymbolicLink = attrs.isSymbolicLink();
      this.size = attrs.size();
   }

   public long creationTime() {
      return this.creationTime;
   }

   public long lastAccessTime() {
      return this.lastAccessTime;
   }

   public long lastModifiedTime() {
      return this.lastModifiedTime;
   }

   public boolean isDirectory() {
      return this.isDirectory;
   }

   public boolean isOther() {
      return this.isOther;
   }

   public boolean isRegularFile() {
      return this.isRegularFile;
   }

   public boolean isSymbolicLink() {
      return this.isSymbolicLink;
   }

   public long size() {
      return this.size;
   }
}

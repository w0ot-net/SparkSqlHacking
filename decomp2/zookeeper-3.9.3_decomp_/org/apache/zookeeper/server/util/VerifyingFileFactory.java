package org.apache.zookeeper.server.util;

import java.io.File;
import org.slf4j.Logger;

public final class VerifyingFileFactory {
   private final boolean warnForRelativePath;
   private final boolean failForNonExistingPath;
   private final Logger log;

   public VerifyingFileFactory(Builder builder) {
      this.warnForRelativePath = builder.warnForRelativePathOption;
      this.failForNonExistingPath = builder.failForNonExistingPathOption;
      this.log = builder.log;

      assert this.log != null;

   }

   public File create(String path) {
      File file = new File(path);
      return this.validate(file);
   }

   public File validate(File file) {
      if (this.warnForRelativePath) {
         this.doWarnForRelativePath(file);
      }

      if (this.failForNonExistingPath) {
         this.doFailForNonExistingPath(file);
      }

      return file;
   }

   private void doFailForNonExistingPath(File file) {
      if (!file.exists()) {
         throw new IllegalArgumentException(file.toString() + " file is missing");
      }
   }

   private void doWarnForRelativePath(File file) {
      if (!file.isAbsolute()) {
         if (!file.getPath().substring(0, 2).equals("." + File.separator)) {
            this.log.warn(file.getPath() + " is relative. Prepend ." + File.separator + " to indicate that you're sure!");
         }
      }
   }

   public static class Builder {
      private boolean warnForRelativePathOption = false;
      private boolean failForNonExistingPathOption = false;
      private final Logger log;

      public Builder(Logger log) {
         this.log = log;
      }

      public Builder warnForRelativePath() {
         this.warnForRelativePathOption = true;
         return this;
      }

      public Builder failForNonExistingPath() {
         this.failForNonExistingPathOption = true;
         return this;
      }

      public VerifyingFileFactory build() {
         return new VerifyingFileFactory(this);
      }
   }
}

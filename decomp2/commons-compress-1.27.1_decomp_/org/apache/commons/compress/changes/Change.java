package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;

final class Change {
   private final String targetFileName;
   private final ArchiveEntry entry;
   private final InputStream inputStream;
   private final boolean replaceMode;
   private final ChangeType type;

   Change(ArchiveEntry archiveEntry, InputStream inputStream, boolean replace) {
      this.entry = (ArchiveEntry)Objects.requireNonNull(archiveEntry, "archiveEntry");
      this.inputStream = (InputStream)Objects.requireNonNull(inputStream, "inputStream");
      this.type = Change.ChangeType.ADD;
      this.targetFileName = null;
      this.replaceMode = replace;
   }

   Change(String fileName, ChangeType type) {
      this.targetFileName = (String)Objects.requireNonNull(fileName, "fileName");
      this.type = type;
      this.inputStream = null;
      this.entry = null;
      this.replaceMode = true;
   }

   ArchiveEntry getEntry() {
      return this.entry;
   }

   InputStream getInputStream() {
      return this.inputStream;
   }

   String getTargetFileName() {
      return this.targetFileName;
   }

   ChangeType getType() {
      return this.type;
   }

   boolean isReplaceMode() {
      return this.replaceMode;
   }

   static enum ChangeType {
      DELETE,
      ADD,
      MOVE,
      DELETE_DIR;

      // $FF: synthetic method
      private static ChangeType[] $values() {
         return new ChangeType[]{DELETE, ADD, MOVE, DELETE_DIR};
      }
   }
}

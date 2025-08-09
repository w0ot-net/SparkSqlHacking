package org.apache.logging.log4j.core.appender.rolling.action;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileRenameAction extends AbstractAction {
   private final File source;
   private final File destination;
   private final boolean renameEmptyFiles;

   public FileRenameAction(final File src, final File dst, final boolean renameEmptyFiles) {
      this.source = src;
      this.destination = dst;
      this.renameEmptyFiles = renameEmptyFiles;
   }

   public boolean execute() {
      return execute(this.source, this.destination, this.renameEmptyFiles);
   }

   public File getDestination() {
      return this.destination;
   }

   public File getSource() {
      return this.source;
   }

   public boolean isRenameEmptyFiles() {
      return this.renameEmptyFiles;
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The name of the accessed files is based on a configuration value."
   )
   public static boolean execute(final File source, final File destination, final boolean renameEmptyFiles) {
      if (!renameEmptyFiles && source.length() <= 0L) {
         try {
            return source.delete();
         } catch (Exception exDelete) {
            LOGGER.error("Unable to delete empty file {}: {} {}", source.getAbsolutePath(), exDelete.getClass().getName(), exDelete.getMessage());
         }
      } else {
         File parent = destination.getParentFile();
         if (parent != null && !parent.exists()) {
            parent.mkdirs();
            if (!parent.exists()) {
               LOGGER.error("Unable to create directory {}", parent.getAbsolutePath());
               return false;
            }
         }

         try {
            try {
               return moveFile(Paths.get(source.getAbsolutePath()), Paths.get(destination.getAbsolutePath()));
            } catch (IOException exMove) {
               LOGGER.debug("Unable to move file {} to {}: {} {} - will try to copy and delete", source.getAbsolutePath(), destination.getAbsolutePath(), exMove.getClass().getName(), exMove.getMessage());
               boolean result = source.renameTo(destination);
               if (!result) {
                  try {
                     Files.copy(Paths.get(source.getAbsolutePath()), Paths.get(destination.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                     try {
                        Files.delete(Paths.get(source.getAbsolutePath()));
                        result = true;
                        LOGGER.trace("Renamed file {} to {} using copy and delete", source.getAbsolutePath(), destination.getAbsolutePath());
                     } catch (IOException exDelete) {
                        LOGGER.error("Unable to delete file {}: {} {}", source.getAbsolutePath(), exDelete.getClass().getName(), exDelete.getMessage());

                        try {
                           result = true;
                           (new PrintWriter(source.getAbsolutePath())).close();
                           LOGGER.trace("Renamed file {} to {} with copy and truncation", source.getAbsolutePath(), destination.getAbsolutePath());
                        } catch (IOException exOwerwrite) {
                           LOGGER.error("Unable to overwrite file {}: {} {}", source.getAbsolutePath(), exOwerwrite.getClass().getName(), exOwerwrite.getMessage());
                        }
                     }
                  } catch (IOException exCopy) {
                     LOGGER.error("Unable to copy file {} to {}: {} {}", source.getAbsolutePath(), destination.getAbsolutePath(), exCopy.getClass().getName(), exCopy.getMessage());
                  }
               } else {
                  LOGGER.trace("Renamed file {} to {} with source.renameTo", source.getAbsolutePath(), destination.getAbsolutePath());
               }

               return result;
            }
         } catch (RuntimeException ex) {
            LOGGER.error("Unable to rename file {} to {}: {} {}", source.getAbsolutePath(), destination.getAbsolutePath(), ex.getClass().getName(), ex.getMessage());
         }
      }

      return false;
   }

   private static boolean moveFile(Path source, Path target) throws IOException {
      try {
         Files.move(source, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
         LOGGER.trace("Renamed file {} to {} with Files.move", source.toFile().getAbsolutePath(), target.toFile().getAbsolutePath());
         return true;
      } catch (AtomicMoveNotSupportedException var3) {
         Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
         LOGGER.trace("Renamed file {} to {} with Files.move", source.toFile().getAbsolutePath(), target.toFile().getAbsolutePath());
         return true;
      }
   }

   public String toString() {
      return FileRenameAction.class.getSimpleName() + '[' + this.source + " to " + this.destination + ", renameEmptyFiles=" + this.renameEmptyFiles + ']';
   }
}

package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.ArchiveEntry;

public final class ChangeSet {
   private final Set changes = new LinkedHashSet();

   public void add(ArchiveEntry entry, InputStream input) {
      this.add(entry, input, true);
   }

   public void add(ArchiveEntry entry, InputStream input, boolean replace) {
      this.addAddition(new Change(entry, input, replace));
   }

   private void addAddition(Change addChange) {
      if (Change.ChangeType.ADD == addChange.getType() && addChange.getInputStream() != null) {
         if (!this.changes.isEmpty()) {
            Iterator<Change<E>> it = this.changes.iterator();

            while(it.hasNext()) {
               Change<E> change = (Change)it.next();
               if (change.getType() == Change.ChangeType.ADD && change.getEntry() != null) {
                  ArchiveEntry entry = change.getEntry();
                  if (entry.equals(addChange.getEntry())) {
                     if (addChange.isReplaceMode()) {
                        it.remove();
                        this.changes.add(addChange);
                     }

                     return;
                  }
               }
            }
         }

         this.changes.add(addChange);
      }
   }

   private void addDeletion(Change deleteChange) {
      if ((Change.ChangeType.DELETE == deleteChange.getType() || Change.ChangeType.DELETE_DIR == deleteChange.getType()) && deleteChange.getTargetFileName() != null) {
         String source = deleteChange.getTargetFileName();
         Pattern pattern = Pattern.compile(source + "/.*");
         if (source != null && !this.changes.isEmpty()) {
            Iterator<Change<E>> it = this.changes.iterator();

            while(it.hasNext()) {
               Change<E> change = (Change)it.next();
               if (change.getType() == Change.ChangeType.ADD && change.getEntry() != null) {
                  String target = change.getEntry().getName();
                  if (target != null && (Change.ChangeType.DELETE == deleteChange.getType() && source.equals(target) || Change.ChangeType.DELETE_DIR == deleteChange.getType() && pattern.matcher(target).matches())) {
                     it.remove();
                  }
               }
            }
         }

         this.changes.add(deleteChange);
      }
   }

   public void delete(String fileName) {
      this.addDeletion(new Change(fileName, Change.ChangeType.DELETE));
   }

   public void deleteDir(String dirName) {
      this.addDeletion(new Change(dirName, Change.ChangeType.DELETE_DIR));
   }

   Set getChanges() {
      return new LinkedHashSet(this.changes);
   }
}

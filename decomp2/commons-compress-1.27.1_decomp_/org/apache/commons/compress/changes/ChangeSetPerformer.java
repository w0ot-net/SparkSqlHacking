package org.apache.commons.compress.changes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;

public class ChangeSetPerformer {
   private final Set changes;

   public ChangeSetPerformer(ChangeSet changeSet) {
      this.changes = changeSet.getChanges();
   }

   private void copyStream(InputStream inputStream, ArchiveOutputStream outputStream, ArchiveEntry archiveEntry) throws IOException {
      outputStream.putArchiveEntry(archiveEntry);
      IOUtils.copy(inputStream, outputStream);
      outputStream.closeArchiveEntry();
   }

   private boolean isDeletedLater(Set workingSet, ArchiveEntry entry) {
      String source = entry.getName();
      if (!workingSet.isEmpty()) {
         for(Change change : workingSet) {
            Change.ChangeType type = change.getType();
            String target = change.getTargetFileName();
            if (type == Change.ChangeType.DELETE && source.equals(target)) {
               return true;
            }

            if (type == Change.ChangeType.DELETE_DIR && source.startsWith(target + "/")) {
               return true;
            }
         }
      }

      return false;
   }

   private ChangeSetResults perform(ArchiveEntryIterator entryIterator, ArchiveOutputStream outputStream) throws IOException {
      ChangeSetResults results = new ChangeSetResults();
      Set<Change<E>> workingSet = new LinkedHashSet(this.changes);
      Iterator<Change<E>> it = workingSet.iterator();

      while(it.hasNext()) {
         Change<E> change = (Change)it.next();
         if (change.getType() == Change.ChangeType.ADD && change.isReplaceMode()) {
            InputStream inputStream = change.getInputStream();
            this.copyStream(inputStream, outputStream, change.getEntry());
            it.remove();
            results.addedFromChangeSet(change.getEntry().getName());
         }
      }

      while(entryIterator.hasNext()) {
         E entry = (E)entryIterator.next();
         boolean copy = true;
         Iterator<Change<E>> it = workingSet.iterator();

         while(it.hasNext()) {
            Change<E> change = (Change)it.next();
            Change.ChangeType type = change.getType();
            String name = entry.getName();
            if (type == Change.ChangeType.DELETE && name != null) {
               if (name.equals(change.getTargetFileName())) {
                  copy = false;
                  it.remove();
                  results.deleted(name);
                  break;
               }
            } else if (type == Change.ChangeType.DELETE_DIR && name != null && name.startsWith(change.getTargetFileName() + "/")) {
               copy = false;
               results.deleted(name);
               break;
            }
         }

         if (copy && !this.isDeletedLater(workingSet, entry) && !results.hasBeenAdded(entry.getName())) {
            InputStream inputStream = entryIterator.getInputStream();
            this.copyStream(inputStream, outputStream, entry);
            results.addedFromStream(entry.getName());
         }
      }

      it = workingSet.iterator();

      while(it.hasNext()) {
         Change<E> change = (Change)it.next();
         if (change.getType() == Change.ChangeType.ADD && !change.isReplaceMode() && !results.hasBeenAdded(change.getEntry().getName())) {
            InputStream input = change.getInputStream();
            this.copyStream(input, outputStream, change.getEntry());
            it.remove();
            results.addedFromChangeSet(change.getEntry().getName());
         }
      }

      outputStream.finish();
      return results;
   }

   public ChangeSetResults perform(ArchiveInputStream inputStream, ArchiveOutputStream outputStream) throws IOException {
      return this.perform((ArchiveEntryIterator)(new ArchiveInputStreamIterator(inputStream)), outputStream);
   }

   public ChangeSetResults perform(ZipFile zipFile, ArchiveOutputStream outputStream) throws IOException {
      ArchiveEntryIterator<E> entryIterator = new ZipFileIterator(zipFile);
      return this.perform(entryIterator, outputStream);
   }

   private static final class ArchiveInputStreamIterator implements ArchiveEntryIterator {
      private final ArchiveInputStream inputStream;
      private ArchiveEntry next;

      ArchiveInputStreamIterator(ArchiveInputStream inputStream) {
         this.inputStream = inputStream;
      }

      public InputStream getInputStream() {
         return this.inputStream;
      }

      public boolean hasNext() throws IOException {
         return (this.next = this.inputStream.getNextEntry()) != null;
      }

      public ArchiveEntry next() {
         return this.next;
      }
   }

   private static final class ZipFileIterator implements ArchiveEntryIterator {
      private final ZipFile zipFile;
      private final Enumeration nestedEnumeration;
      private ZipArchiveEntry currentEntry;

      ZipFileIterator(ZipFile zipFile) {
         this.zipFile = zipFile;
         this.nestedEnumeration = zipFile.getEntriesInPhysicalOrder();
      }

      public InputStream getInputStream() throws IOException {
         return this.zipFile.getInputStream(this.currentEntry);
      }

      public boolean hasNext() {
         return this.nestedEnumeration.hasMoreElements();
      }

      public ZipArchiveEntry next() {
         return this.currentEntry = (ZipArchiveEntry)this.nestedEnumeration.nextElement();
      }
   }

   private interface ArchiveEntryIterator {
      InputStream getInputStream() throws IOException;

      boolean hasNext() throws IOException;

      ArchiveEntry next();
   }
}

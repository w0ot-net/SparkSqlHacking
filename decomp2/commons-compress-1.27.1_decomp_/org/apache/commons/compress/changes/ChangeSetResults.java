package org.apache.commons.compress.changes;

import java.util.ArrayList;
import java.util.List;

public class ChangeSetResults {
   private final List addedFromChangeSet = new ArrayList();
   private final List addedFromStream = new ArrayList();
   private final List deleted = new ArrayList();

   void addedFromChangeSet(String fileName) {
      this.addedFromChangeSet.add(fileName);
   }

   void addedFromStream(String fileName) {
      this.addedFromStream.add(fileName);
   }

   void deleted(String fileName) {
      this.deleted.add(fileName);
   }

   public List getAddedFromChangeSet() {
      return this.addedFromChangeSet;
   }

   public List getAddedFromStream() {
      return this.addedFromStream;
   }

   public List getDeleted() {
      return this.deleted;
   }

   boolean hasBeenAdded(String fileName) {
      return this.addedFromChangeSet.contains(fileName) || this.addedFromStream.contains(fileName);
   }
}

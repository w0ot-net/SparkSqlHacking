package org.datanucleus.store.autostart;

import java.util.Collection;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.exceptions.DatastoreInitialisationException;

public interface AutoStartMechanism {
   Mode getMode();

   void setMode(Mode var1);

   Collection getAllClassData() throws DatastoreInitialisationException;

   void open();

   void close();

   boolean isOpen();

   void addClass(StoreData var1);

   void deleteClass(String var1);

   void deleteAllClasses();

   String getStorageDescription();

   public static enum Mode {
      NONE,
      QUIET,
      CHECKED,
      IGNORED;
   }
}

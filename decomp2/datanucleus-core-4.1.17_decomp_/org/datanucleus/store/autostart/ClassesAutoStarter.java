package org.datanucleus.store.autostart;

import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.exceptions.DatastoreInitialisationException;
import org.datanucleus.util.Localiser;

public class ClassesAutoStarter extends AbstractAutoStartMechanism {
   protected String classNames;

   public ClassesAutoStarter(StoreManager storeMgr, ClassLoaderResolver clr) {
      this.classNames = storeMgr.getStringProperty("datanucleus.autoStartClassNames");
   }

   public Collection getAllClassData() throws DatastoreInitialisationException {
      Collection classes = new HashSet();
      if (this.classNames == null) {
         return classes;
      } else {
         StringTokenizer tokeniser = new StringTokenizer(this.classNames, ",");

         while(tokeniser.hasMoreTokens()) {
            classes.add(new StoreData(tokeniser.nextToken().trim(), (MetaData)null, 1, (String)null));
         }

         return classes;
      }
   }

   public void addClass(StoreData data) {
   }

   public void deleteClass(String className) {
   }

   public void deleteAllClasses() {
   }

   public String getStorageDescription() {
      return Localiser.msg("034100");
   }
}

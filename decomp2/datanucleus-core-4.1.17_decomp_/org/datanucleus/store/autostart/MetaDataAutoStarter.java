package org.datanucleus.store.autostart;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.exceptions.DatastoreInitialisationException;
import org.datanucleus.util.Localiser;

public class MetaDataAutoStarter extends AbstractAutoStartMechanism {
   protected String metaDataFiles;
   protected StoreManager storeMgr;
   protected ClassLoaderResolver clr;
   protected Collection classes = new HashSet();

   public MetaDataAutoStarter(StoreManager storeMgr, ClassLoaderResolver clr) {
      this.metaDataFiles = storeMgr.getStringProperty("datanucleus.autoStartMetaDataFiles");
      this.storeMgr = storeMgr;
      this.clr = clr;
   }

   public Collection getAllClassData() throws DatastoreInitialisationException {
      if (this.metaDataFiles == null) {
         return Collections.EMPTY_SET;
      } else {
         for(FileMetaData filemd : this.storeMgr.getNucleusContext().getMetaDataManager().loadFiles(this.metaDataFiles.split(","), this.clr)) {
            for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
               PackageMetaData pmd = filemd.getPackage(i);

               for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
                  ClassMetaData cmd = pmd.getClass(j);
                  this.classes.add(new StoreData(cmd.getFullClassName().trim(), (MetaData)null, 1, (String)null));
               }
            }
         }

         return this.classes;
      }
   }

   public void addClass(StoreData data) {
   }

   public void deleteClass(String className) {
   }

   public void deleteAllClasses() {
   }

   public String getStorageDescription() {
      return Localiser.msg("034150");
   }
}

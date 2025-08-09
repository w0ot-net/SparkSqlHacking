package org.datanucleus.store.rdbms.mapping.datastore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public final class DatastoreMappingFactory {
   private static Map DATASTORE_MAPPING_CONSTRUCTOR_BY_CLASS = new HashMap();
   private static final Class[] DATASTORE_MAPPING_CTR_ARG_CLASSES = new Class[]{JavaTypeMapping.class, RDBMSStoreManager.class, Column.class};

   private DatastoreMappingFactory() {
   }

   public static DatastoreMapping createMapping(Class mappingClass, JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column column) {
      Object obj = null;

      try {
         Object[] args = new Object[]{mapping, storeMgr, column};
         Constructor ctr = (Constructor)DATASTORE_MAPPING_CONSTRUCTOR_BY_CLASS.get(mappingClass);
         if (ctr == null) {
            ctr = mappingClass.getConstructor(DATASTORE_MAPPING_CTR_ARG_CLASSES);
            DATASTORE_MAPPING_CONSTRUCTOR_BY_CLASS.put(mappingClass, ctr);
         }

         try {
            obj = ctr.newInstance(args);
         } catch (InvocationTargetException e) {
            throw (new NucleusException(Localiser.msg("041009", new Object[]{mappingClass.getName(), e.getTargetException()}), e.getTargetException())).setFatal();
         } catch (Exception e) {
            throw (new NucleusException(Localiser.msg("041009", new Object[]{mappingClass.getName(), e}), e)).setFatal();
         }
      } catch (NoSuchMethodException var10) {
         throw (new NucleusException(Localiser.msg("041007", new Object[]{JavaTypeMapping.class, RDBMSStoreManager.class, Column.class, mappingClass.getName()}))).setFatal();
      }

      return (DatastoreMapping)obj;
   }
}

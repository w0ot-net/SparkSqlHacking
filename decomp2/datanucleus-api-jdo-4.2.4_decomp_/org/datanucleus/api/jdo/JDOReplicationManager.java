package org.datanucleus.api.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import javax.jdo.Extent;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JDOReplicationManager {
   final PersistenceManagerFactory pmfSource;
   final PersistenceManagerFactory pmfTarget;
   protected Properties properties = new Properties();

   public JDOReplicationManager(PersistenceManagerFactory pmf1, PersistenceManagerFactory pmf2) {
      if (pmf1 != null && !pmf1.isClosed()) {
         if (pmf2 != null && !pmf2.isClosed()) {
            this.pmfSource = pmf1;
            this.pmfTarget = pmf2;
            this.properties.setProperty("datanucleus.replicateObjectGraph", "true");
            this.properties.setProperty("datanucleus.deleteUnknownObjects", "false");
         } else {
            throw new JDOUserException(Localiser.msg("012050"));
         }
      } else {
         throw new JDOUserException(Localiser.msg("012050"));
      }
   }

   public void setProperty(String key, String value) {
      this.properties.setProperty(key, value);
   }

   public Properties getProperties() {
      return this.properties;
   }

   protected boolean getBooleanProperty(String key) {
      String val = this.properties.getProperty(key);
      return val == null ? false : val.equalsIgnoreCase("true");
   }

   public void replicate(Class... types) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012052", new Object[]{this.pmfSource, this.pmfTarget, StringUtils.objectArrayToString(types)}));
      }

      NucleusContext nucleusCtxSource = ((JDOPersistenceManagerFactory)this.pmfSource).getNucleusContext();
      MetaDataManager mmgr = nucleusCtxSource.getMetaDataManager();
      ClassLoaderResolver clr = nucleusCtxSource.getClassLoaderResolver((ClassLoader)null);

      for(int i = 0; i < types.length; ++i) {
         AbstractClassMetaData cmd = mmgr.getMetaDataForClass(types[i], clr);
         if (!cmd.isDetachable()) {
            throw new JDOUserException("Class " + types[i] + " is not detachable so cannot replicate");
         }
      }

      Object[] detachedObjects = null;
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012053"));
      }

      PersistenceManager pm1 = this.pmfSource.getPersistenceManager();
      Transaction tx1 = pm1.currentTransaction();
      if (this.getBooleanProperty("datanucleus.replicateObjectGraph")) {
         pm1.getFetchPlan().setGroup("all");
         pm1.getFetchPlan().setMaxFetchDepth(-1);
      }

      try {
         tx1.begin();
         ArrayList objects = new ArrayList();

         for(int i = 0; i < types.length; ++i) {
            AbstractClassMetaData cmd = mmgr.getMetaDataForClass(types[i], clr);
            if (!cmd.isEmbeddedOnly()) {
               Extent ex = pm1.getExtent(types[i]);
               Iterator iter = ex.iterator();

               while(iter.hasNext()) {
                  objects.add(iter.next());
               }
            }
         }

         Collection detachedColl = pm1.detachCopyAll(objects);
         detachedObjects = detachedColl.toArray();
         tx1.commit();
      } finally {
         if (tx1.isActive()) {
            tx1.rollback();
         }

         pm1.close();
      }

      this.replicateInTarget(detachedObjects);
   }

   public void replicate(String... classNames) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012052", new Object[]{this.pmfSource, this.pmfTarget, StringUtils.objectArrayToString(classNames)}));
      }

      NucleusContext nucleusCtxSource = ((JDOPersistenceManagerFactory)this.pmfSource).getNucleusContext();
      MetaDataManager mmgr = nucleusCtxSource.getMetaDataManager();
      ClassLoaderResolver clr = nucleusCtxSource.getClassLoaderResolver((ClassLoader)null);

      for(int i = 0; i < classNames.length; ++i) {
         AbstractClassMetaData cmd = mmgr.getMetaDataForClass(classNames[i], clr);
         if (!cmd.isDetachable()) {
            throw new JDOUserException("Class " + classNames[i] + " is not detachable so cannot replicate");
         }
      }

      Object[] detachedObjects = null;
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012053"));
      }

      PersistenceManager pm1 = this.pmfSource.getPersistenceManager();
      Transaction tx1 = pm1.currentTransaction();
      if (this.getBooleanProperty("datanucleus.replicateObjectGraph")) {
         pm1.getFetchPlan().setGroup("all");
         pm1.getFetchPlan().setMaxFetchDepth(-1);
      }

      try {
         tx1.begin();
         clr = ((JDOPersistenceManager)pm1).getExecutionContext().getClassLoaderResolver();
         ArrayList objects = new ArrayList();

         for(int i = 0; i < classNames.length; ++i) {
            Class cls = clr.classForName(classNames[i]);
            AbstractClassMetaData cmd = mmgr.getMetaDataForClass(cls, clr);
            if (!cmd.isEmbeddedOnly()) {
               Extent ex = pm1.getExtent(cls);
               Iterator iter = ex.iterator();

               while(iter.hasNext()) {
                  objects.add(iter.next());
               }
            }
         }

         Collection detachedColl = pm1.detachCopyAll(objects);
         detachedObjects = detachedColl.toArray();
         tx1.commit();
      } finally {
         if (tx1.isActive()) {
            tx1.rollback();
         }

         pm1.close();
      }

      this.replicateInTarget(detachedObjects);
   }

   public void replicate(Object... oids) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012051", new Object[]{this.pmfSource, this.pmfTarget, StringUtils.objectArrayToString(oids)}));
      }

      Object[] detachedObjects = null;
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012053"));
      }

      PersistenceManager pm1 = this.pmfSource.getPersistenceManager();
      Transaction tx1 = pm1.currentTransaction();
      if (this.getBooleanProperty("datanucleus.replicateObjectGraph")) {
         pm1.getFetchPlan().setGroup("all");
         pm1.getFetchPlan().setMaxFetchDepth(-1);
      }

      try {
         tx1.begin();
         Object[] objs = pm1.getObjectsById(oids);
         detachedObjects = pm1.detachCopyAll(objs);
         tx1.commit();
      } finally {
         if (tx1.isActive()) {
            tx1.rollback();
         }

         pm1.close();
      }

      this.replicateInTarget(detachedObjects);
   }

   public void replicateRegisteredClasses() {
      ClassLoaderResolver clr = ((JDOPersistenceManager)this.pmfSource.getPersistenceManager()).getExecutionContext().getClassLoaderResolver();
      MetaDataManager mmgr = ((JDOPersistenceManagerFactory)this.pmfSource).getNucleusContext().getMetaDataManager();
      Collection classNames = mmgr.getClassesWithMetaData();
      ArrayList arrayTypes = new ArrayList();

      for(String className : classNames) {
         AbstractClassMetaData cmd = mmgr.getMetaDataForClass(className, clr);
         if (!cmd.isEmbeddedOnly()) {
            arrayTypes.add(clr.classForName(className));
         }
      }

      this.replicate((Class[])arrayTypes.toArray(new Class[arrayTypes.size()]));
   }

   protected void replicateInTarget(Object... detachedObjects) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012054"));
      }

      JDOPersistenceManager pm2 = (JDOPersistenceManager)this.pmfTarget.getPersistenceManager();
      Transaction tx2 = pm2.currentTransaction();

      try {
         tx2.begin();
         pm2.makePersistentAll(detachedObjects);
         tx2.commit();
      } finally {
         if (tx2.isActive()) {
            tx2.rollback();
         }

         pm2.close();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("012055"));
      }

   }
}

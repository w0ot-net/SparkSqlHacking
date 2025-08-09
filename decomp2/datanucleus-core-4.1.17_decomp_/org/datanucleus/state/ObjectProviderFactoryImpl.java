package org.datanucleus.state;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.cache.CachedPC;
import org.datanucleus.enhancer.EnhancementHelper;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.store.FieldValues;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class ObjectProviderFactoryImpl implements ObjectProviderFactory {
   Class opClass = null;
   public static final Class[] OBJECT_PROVIDER_CTR_ARG_CLASSES = new Class[]{ExecutionContext.class, AbstractClassMetaData.class};

   public ObjectProviderFactoryImpl(PersistenceNucleusContext nucCtx) {
      Configuration conf = nucCtx.getConfiguration();
      String opClassName = conf.getStringProperty("datanucleus.objectProvider.className");
      if (StringUtils.isWhitespace(opClassName)) {
         opClassName = nucCtx.getStoreManager().getDefaultObjectProviderClassName();
      }

      this.opClass = nucCtx.getClassLoaderResolver((ClassLoader)null).classForName(opClassName);
      AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            EnhancementHelper.registerAuthorizedStateManagerClass(ObjectProviderFactoryImpl.this.opClass);
            return null;
         }
      });
   }

   public void close() {
   }

   public ObjectProvider newForHollow(ExecutionContext ec, Class pcClass, Object id) {
      Class cls = this.getInitialisedClassForClass(pcClass, ec.getClassLoaderResolver());
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pcClass, ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForHollow(id, (FieldValues)null, cls);
      return op;
   }

   public ObjectProvider newForHollow(ExecutionContext ec, Class pcClass, Object id, FieldValues fv) {
      Class cls = this.getInitialisedClassForClass(pcClass, ec.getClassLoaderResolver());
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pcClass, ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForHollow(id, fv, cls);
      return op;
   }

   public ObjectProvider newForHollowPreConstructed(ExecutionContext ec, Object id, Object pc) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForHollowPreConstructed(id, pc);
      return op;
   }

   /** @deprecated */
   public ObjectProvider newForHollowPopulatedAppId(ExecutionContext ec, Class pcClass, FieldValues fv) {
      Class cls = this.getInitialisedClassForClass(pcClass, ec.getClassLoaderResolver());
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pcClass, ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForHollowAppId(fv, cls);
      return op;
   }

   public ObjectProvider newForPersistentClean(ExecutionContext ec, Object id, Object pc) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForPersistentClean(id, pc);
      return op;
   }

   public ObjectProvider newForEmbedded(ExecutionContext ec, Object pc, boolean copyPc, ObjectProvider ownerOP, int ownerFieldNumber) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForEmbedded(pc, copyPc);
      if (ownerOP != null) {
         ec.registerEmbeddedRelation(ownerOP, ownerFieldNumber, op);
      }

      return op;
   }

   public ObjectProvider newForEmbedded(ExecutionContext ec, AbstractClassMetaData cmd, ObjectProvider ownerOP, int ownerFieldNumber) {
      Class pcClass = ec.getClassLoaderResolver().classForName(cmd.getFullClassName());
      ObjectProvider op = this.newForHollow(ec, pcClass, (Object)null);
      op.initialiseForEmbedded(op.getObject(), false);
      if (ownerOP != null) {
         ec.registerEmbeddedRelation(ownerOP, ownerFieldNumber, op);
      }

      return op;
   }

   public ObjectProvider newForPersistentNew(ExecutionContext ec, Object pc, FieldValues preInsertChanges) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForPersistentNew(pc, preInsertChanges);
      return op;
   }

   public ObjectProvider newForTransactionalTransient(ExecutionContext ec, Object pc) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForTransactionalTransient(pc);
      return op;
   }

   public ObjectProvider newForDetached(ExecutionContext ec, Object pc, Object id, Object version) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForDetached(pc, id, version);
      return op;
   }

   public ObjectProvider newForPNewToBeDeleted(ExecutionContext ec, Object pc) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pc.getClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForPNewToBeDeleted(pc);
      return op;
   }

   public ObjectProvider newForCachedPC(ExecutionContext ec, Object id, CachedPC cachedPC) {
      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(cachedPC.getObjectClass(), ec.getClassLoaderResolver());
      ObjectProvider op = this.getObjectProvider(ec, cmd);
      op.initialiseForCachedPC(cachedPC, id);
      return op;
   }

   public void disconnectObjectProvider(ObjectProvider op) {
   }

   protected ObjectProvider getObjectProvider(ExecutionContext ec, AbstractClassMetaData cmd) {
      return (ObjectProvider)ClassUtils.newInstance(this.opClass, OBJECT_PROVIDER_CTR_ARG_CLASSES, new Object[]{ec, cmd});
   }

   private Class getInitialisedClassForClass(Class pcCls, ClassLoaderResolver clr) {
      try {
         return clr.classForName(pcCls.getName(), pcCls.getClassLoader(), true);
      } catch (ClassNotResolvedException var4) {
         throw (new NucleusUserException(Localiser.msg("026015", pcCls.getName()))).setFatal();
      }
   }
}

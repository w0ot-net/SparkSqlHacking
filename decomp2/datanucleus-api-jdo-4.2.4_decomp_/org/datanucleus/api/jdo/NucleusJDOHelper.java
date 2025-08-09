package org.datanucleus.api.jdo;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collection;
import javax.jdo.JDOCanRetryException;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOException;
import javax.jdo.JDOFatalDataStoreException;
import javax.jdo.JDOFatalInternalException;
import javax.jdo.JDOFatalUserException;
import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.JDOOptimisticVerificationException;
import javax.jdo.JDOQueryInterruptedException;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.identity.ByteIdentity;
import javax.jdo.identity.CharIdentity;
import javax.jdo.identity.IntIdentity;
import javax.jdo.identity.LongIdentity;
import javax.jdo.identity.ObjectIdentity;
import javax.jdo.identity.ShortIdentity;
import javax.jdo.identity.SingleFieldIdentity;
import javax.jdo.identity.StringIdentity;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.jdo.exceptions.ClassNotPersistenceCapableException;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.exceptions.ClassNotPersistableException;
import org.datanucleus.exceptions.NoPersistenceInformationException;
import org.datanucleus.exceptions.NucleusCanRetryException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.exceptions.NucleusUnsupportedOptionException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.TransactionNotActiveException;
import org.datanucleus.exceptions.TransactionNotReadableException;
import org.datanucleus.exceptions.TransactionNotWritableException;
import org.datanucleus.identity.ByteId;
import org.datanucleus.identity.CharId;
import org.datanucleus.identity.IntId;
import org.datanucleus.identity.LongId;
import org.datanucleus.identity.ObjectId;
import org.datanucleus.identity.ShortId;
import org.datanucleus.identity.SingleFieldId;
import org.datanucleus.identity.StringId;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.DatastoreReadOnlyException;
import org.datanucleus.store.query.QueryInterruptedException;
import org.datanucleus.store.query.QueryTimeoutException;
import org.datanucleus.transaction.HeuristicRollbackException;
import org.datanucleus.util.ClassUtils;

public class NucleusJDOHelper extends JDOHelper {
   public static JDOQueryCache getQueryResultCache(PersistenceManagerFactory pmf) {
      return ((JDOPersistenceManagerFactory)pmf).getQueryCache();
   }

   public static SingleFieldIdentity getSingleFieldIdentityForDataNucleusIdentity(SingleFieldId dnid, Class targetClass) {
      if (dnid instanceof LongId) {
         return new LongIdentity(targetClass, dnid.toString());
      } else if (dnid instanceof IntId) {
         return new IntIdentity(targetClass, dnid.toString());
      } else if (dnid instanceof ShortId) {
         return new ShortIdentity(targetClass, dnid.toString());
      } else if (dnid instanceof ByteId) {
         return new ByteIdentity(targetClass, dnid.toString());
      } else if (dnid instanceof StringId) {
         return new StringIdentity(targetClass, dnid.toString());
      } else {
         return (SingleFieldIdentity)(dnid instanceof CharId ? new CharIdentity(targetClass, dnid.toString()) : new ObjectIdentity(targetClass, dnid.getKeyAsObject()));
      }
   }

   public static SingleFieldId getDataNucleusIdentityForSingleFieldIdentity(SingleFieldIdentity sfid) {
      if (sfid instanceof LongIdentity) {
         return new LongId(sfid.getTargetClass(), sfid.toString());
      } else if (sfid instanceof IntIdentity) {
         return new IntId(sfid.getTargetClass(), sfid.toString());
      } else if (sfid instanceof ShortIdentity) {
         return new ShortId(sfid.getTargetClass(), sfid.toString());
      } else if (sfid instanceof ByteIdentity) {
         return new ByteId(sfid.getTargetClass(), sfid.toString());
      } else if (sfid instanceof StringIdentity) {
         return new StringId(sfid.getTargetClass(), sfid.toString());
      } else {
         return (SingleFieldId)(sfid instanceof CharIdentity ? new CharId(sfid.getTargetClass(), sfid.toString()) : new ObjectId(sfid.getTargetClass(), sfid.getKeyAsObject()));
      }
   }

   public static String getObjectIdClassForInputIdClass(String objectIdClass) {
      if (objectIdClass != null && objectIdClass.startsWith("javax.jdo.identity")) {
         if (objectIdClass.equals("javax.jdo.identity.ByteIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_BYTE;
         }

         if (objectIdClass.equals("javax.jdo.identity.CharIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_CHAR;
         }

         if (objectIdClass.equals("javax.jdo.identity.IntIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_INT;
         }

         if (objectIdClass.equals("javax.jdo.identity.LongIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_LONG;
         }

         if (objectIdClass.equals("javax.jdo.identity.ShortIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_SHORT;
         }

         if (objectIdClass.equals("javax.jdo.identity.StringIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_STRING;
         }

         if (objectIdClass.equals("javax.jdo.identity.ObjectIdentity")) {
            return ClassNameConstants.IDENTITY_SINGLEFIELD_OBJECT;
         }
      }

      return objectIdClass;
   }

   public static void replicate(PersistenceManagerFactory pmf1, PersistenceManagerFactory pmf2, Object... oids) {
      JDOReplicationManager replicator = new JDOReplicationManager(pmf1, pmf2);
      replicator.replicate(oids);
   }

   public static void replicate(PersistenceManagerFactory pmf1, PersistenceManagerFactory pmf2, Class... types) {
      JDOReplicationManager replicator = new JDOReplicationManager(pmf1, pmf2);
      replicator.replicate(types);
   }

   public static void replicate(PersistenceManagerFactory pmf1, PersistenceManagerFactory pmf2, String... classNames) {
      JDOReplicationManager replicator = new JDOReplicationManager(pmf1, pmf2);
      replicator.replicate(classNames);
   }

   public static ClassMetaData getMetaDataForClass(PersistenceManagerFactory pmf, Class cls) {
      if (pmf != null && cls != null) {
         if (!(pmf instanceof JDOPersistenceManagerFactory)) {
            return null;
         } else {
            JDOPersistenceManagerFactory myPMF = (JDOPersistenceManagerFactory)pmf;
            MetaDataManager mdmgr = myPMF.getNucleusContext().getMetaDataManager();
            return (ClassMetaData)mdmgr.getMetaDataForClass(cls, myPMF.getNucleusContext().getClassLoaderResolver((ClassLoader)null));
         }
      } else {
         return null;
      }
   }

   public static String[] getClassesWithMetaData(PersistenceManagerFactory pmf) {
      if (pmf != null && pmf instanceof JDOPersistenceManagerFactory) {
         JDOPersistenceManagerFactory myPMF = (JDOPersistenceManagerFactory)pmf;
         Collection classes = myPMF.getNucleusContext().getMetaDataManager().getClassesWithMetaData();
         return (String[])classes.toArray(new String[classes.size()]);
      } else {
         return null;
      }
   }

   public static Object[] getDetachedStateForObject(Object obj) {
      if (obj != null && isDetached(obj)) {
         try {
            Field fld = ClassUtils.getFieldForClass(obj.getClass(), "dnDetachedState");
            fld.setAccessible(true);
            return fld.get(obj);
         } catch (Exception e) {
            throw new NucleusException("Exception accessing dnDetachedState field", e);
         }
      } else {
         return null;
      }
   }

   public static String[] getDirtyFields(Object obj, PersistenceManager pm) {
      if (obj != null && obj instanceof Persistable) {
         Persistable pc = (Persistable)obj;
         if (isDetached(pc)) {
            ExecutionContext ec = ((JDOPersistenceManager)pm).getExecutionContext();
            ObjectProvider op = ec.getNucleusContext().getObjectProviderFactory().newForDetached(ec, pc, getObjectId(pc), (Object)null);
            pc.dnReplaceStateManager((StateManager)op);
            op.retrieveDetachState(op);
            String[] dirtyFieldNames = op.getDirtyFieldNames();
            pc.dnReplaceStateManager((StateManager)null);
            return dirtyFieldNames;
         } else {
            ExecutionContext ec = ((JDOPersistenceManager)pm).getExecutionContext();
            ObjectProvider op = ec.findObjectProvider(pc);
            return op == null ? null : op.getDirtyFieldNames();
         }
      } else {
         return null;
      }
   }

   public static String[] getLoadedFields(Object obj, PersistenceManager pm) {
      if (obj != null && obj instanceof Persistable) {
         Persistable pc = (Persistable)obj;
         if (isDetached(pc)) {
            ExecutionContext ec = ((JDOPersistenceManager)pm).getExecutionContext();
            ObjectProvider op = ec.getNucleusContext().getObjectProviderFactory().newForDetached(ec, pc, getObjectId(pc), (Object)null);
            pc.dnReplaceStateManager((StateManager)op);
            op.retrieveDetachState(op);
            String[] loadedFieldNames = op.getLoadedFieldNames();
            pc.dnReplaceStateManager((StateManager)null);
            return loadedFieldNames;
         } else {
            ExecutionContext ec = ((JDOPersistenceManager)pm).getExecutionContext();
            ObjectProvider op = ec.findObjectProvider(pc);
            return op == null ? null : op.getLoadedFieldNames();
         }
      } else {
         return null;
      }
   }

   public static Boolean isLoaded(Object obj, String memberName, PersistenceManager pm) {
      if (obj != null && obj instanceof Persistable) {
         Persistable pc = (Persistable)obj;
         if (isDetached(pc)) {
            ExecutionContext ec = ((JDOPersistenceManager)pm).getExecutionContext();
            ObjectProvider op = ec.getNucleusContext().getObjectProviderFactory().newForDetached(ec, pc, getObjectId(pc), (Object)null);
            pc.dnReplaceStateManager((StateManager)op);
            op.retrieveDetachState(op);
            int position = op.getClassMetaData().getAbsolutePositionOfMember(memberName);
            boolean loaded = op.isFieldLoaded(position);
            pc.dnReplaceStateManager((StateManager)null);
            return loaded;
         } else {
            ExecutionContext ec = (ExecutionContext)pc.dnGetExecutionContext();
            ObjectProvider op = ec.findObjectProvider(pc);
            if (op == null) {
               return null;
            } else {
               int position = op.getClassMetaData().getAbsolutePositionOfMember(memberName);
               return op.isFieldLoaded(position);
            }
         }
      } else {
         return null;
      }
   }

   public static Boolean isDirty(Object obj, String memberName, PersistenceManager pm) {
      if (obj != null && obj instanceof Persistable) {
         Persistable pc = (Persistable)obj;
         if (isDetached(pc)) {
            ExecutionContext ec = ((JDOPersistenceManager)pm).getExecutionContext();
            ObjectProvider op = ec.getNucleusContext().getObjectProviderFactory().newForDetached(ec, pc, getObjectId(pc), (Object)null);
            pc.dnReplaceStateManager((StateManager)op);
            op.retrieveDetachState(op);
            int position = op.getClassMetaData().getAbsolutePositionOfMember(memberName);
            boolean[] dirtyFieldNumbers = op.getDirtyFields();
            pc.dnReplaceStateManager((StateManager)null);
            return dirtyFieldNumbers[position];
         } else {
            ExecutionContext ec = (ExecutionContext)pc.dnGetExecutionContext();
            ObjectProvider op = ec.findObjectProvider(pc);
            if (op == null) {
               return null;
            } else {
               int position = op.getClassMetaData().getAbsolutePositionOfMember(memberName);
               boolean[] dirtyFieldNumbers = op.getDirtyFields();
               return dirtyFieldNumbers[position];
            }
         }
      } else {
         return null;
      }
   }

   public static JDOException getJDOExceptionForNucleusException(NucleusException ne) {
      if (ne instanceof ClassNotPersistableException) {
         return new ClassNotPersistenceCapableException(ne.getMessage(), ne);
      } else if (ne instanceof NoPersistenceInformationException) {
         return new org.datanucleus.api.jdo.exceptions.NoPersistenceInformationException(ne.getMessage(), ne);
      } else if (ne instanceof TransactionNotReadableException) {
         return new org.datanucleus.api.jdo.exceptions.TransactionNotReadableException(ne.getMessage(), ne.getCause());
      } else if (ne instanceof TransactionNotWritableException) {
         return new org.datanucleus.api.jdo.exceptions.TransactionNotWritableException(ne.getMessage(), ne.getCause());
      } else if (ne instanceof TransactionNotActiveException) {
         return new org.datanucleus.api.jdo.exceptions.TransactionNotActiveException(ne.getMessage(), ne);
      } else if (ne instanceof QueryInterruptedException) {
         return new JDOQueryInterruptedException(ne.getMessage());
      } else if (ne instanceof QueryTimeoutException) {
         return new JDODataStoreException(ne.getMessage(), ne);
      } else if (ne instanceof NucleusUnsupportedOptionException) {
         return new JDOUnsupportedOptionException(ne.getMessage(), ne);
      } else if (ne instanceof DatastoreReadOnlyException) {
         ClassLoaderResolver clr = ((DatastoreReadOnlyException)ne).getClassLoaderResolver();

         try {
            Class cls = clr.classForName("javax.jdo.JDOReadOnlyException");
            throw (JDOUserException)ClassUtils.newInstance(cls, new Class[]{String.class}, new Object[]{ne.getMessage()});
         } catch (NucleusException ne2) {
            throw new JDOUserException(ne2.getMessage());
         }
      } else if (ne instanceof NucleusDataStoreException) {
         if (ne.isFatal()) {
            if (ne.getFailedObject() != null) {
               return new JDOFatalDataStoreException(ne.getMessage(), ne.getFailedObject());
            } else {
               return ne.getNestedExceptions() != null ? new JDOFatalDataStoreException(ne.getMessage(), ne.getNestedExceptions()) : new JDOFatalDataStoreException(ne.getMessage(), ne);
            }
         } else if (ne.getNestedExceptions() != null) {
            return ne.getFailedObject() != null ? new JDODataStoreException(ne.getMessage(), ne.getNestedExceptions(), ne.getFailedObject()) : new JDODataStoreException(ne.getMessage(), ne.getNestedExceptions());
         } else if (ne.getFailedObject() != null) {
            JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
            return new JDODataStoreException(ne.getMessage(), ne.getFailedObject());
         } else {
            JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
            return new JDODataStoreException(ne.getMessage(), ne);
         }
      } else if (ne instanceof NucleusObjectNotFoundException) {
         if (ne.getFailedObject() != null) {
            return ne.getNestedExceptions() != null ? new JDOObjectNotFoundException(ne.getMessage(), ne.getNestedExceptions(), ne.getFailedObject()) : new JDOObjectNotFoundException(ne.getMessage(), ne, ne.getFailedObject());
         } else {
            return ne.getNestedExceptions() != null ? new JDOObjectNotFoundException(ne.getMessage(), ne.getNestedExceptions()) : new JDOObjectNotFoundException(ne.getMessage(), new Throwable[]{ne});
         }
      } else if (ne instanceof NucleusCanRetryException) {
         if (ne.getNestedExceptions() != null) {
            return ne.getFailedObject() != null ? new JDOCanRetryException(ne.getMessage(), ne.getNestedExceptions(), ne.getFailedObject()) : new JDOCanRetryException(ne.getMessage(), ne.getNestedExceptions());
         } else if (ne.getFailedObject() != null) {
            JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
            return new JDOCanRetryException(ne.getMessage(), ne.getFailedObject());
         } else {
            JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
            return new JDOCanRetryException(ne.getMessage(), ne);
         }
      } else if (ne instanceof NucleusUserException) {
         if (ne.isFatal()) {
            if (ne.getNestedExceptions() != null) {
               return ne.getFailedObject() != null ? new JDOFatalUserException(ne.getMessage(), ne.getNestedExceptions(), ne.getFailedObject()) : new JDOFatalUserException(ne.getMessage(), ne.getNestedExceptions());
            } else if (ne.getFailedObject() != null) {
               JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
               return new JDOFatalUserException(ne.getMessage(), ne.getFailedObject());
            } else {
               JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
               return new JDOFatalUserException(ne.getMessage(), ne);
            }
         } else if (ne.getNestedExceptions() != null) {
            return ne.getFailedObject() != null ? new JDOUserException(ne.getMessage(), ne.getNestedExceptions(), ne.getFailedObject()) : new JDOUserException(ne.getMessage(), ne.getNestedExceptions());
         } else if (ne.getFailedObject() != null) {
            JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
            return new JDOUserException(ne.getMessage(), ne.getFailedObject());
         } else {
            JDOPersistenceManager.LOGGER.info("Exception thrown", ne);
            return new JDOUserException(ne.getMessage(), ne);
         }
      } else if (ne instanceof NucleusOptimisticException) {
         if (ne.getFailedObject() != null) {
            return new JDOOptimisticVerificationException(ne.getMessage(), ne.getFailedObject());
         } else {
            return ne.getNestedExceptions() != null ? new JDOOptimisticVerificationException(ne.getMessage(), ne.getNestedExceptions()) : new JDOOptimisticVerificationException(ne.getMessage(), ne);
         }
      } else if (ne instanceof HeuristicRollbackException && ne.getNestedExceptions().length == 1 && ne.getNestedExceptions()[0].getCause() instanceof SQLException) {
         return new JDODataStoreException(ne.getMessage(), ne.getNestedExceptions()[0].getCause());
      } else if (ne instanceof HeuristicRollbackException && ne.getNestedExceptions().length == 1 && ne.getNestedExceptions()[0] instanceof NucleusDataStoreException) {
         return new JDODataStoreException(ne.getMessage(), ne.getNestedExceptions()[0].getCause());
      } else if (ne.isFatal()) {
         return ne.getNestedExceptions() != null ? new JDOFatalInternalException(ne.getMessage(), ne.getNestedExceptions()) : new JDOFatalInternalException(ne.getMessage(), ne);
      } else {
         return ne.getNestedExceptions() != null ? new JDOException(ne.getMessage(), ne.getNestedExceptions()) : new JDOException(ne.getMessage(), ne);
      }
   }
}

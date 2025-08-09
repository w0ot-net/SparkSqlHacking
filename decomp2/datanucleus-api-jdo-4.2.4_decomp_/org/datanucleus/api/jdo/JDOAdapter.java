package org.datanucleus.api.jdo;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.JDOUserException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.api.jdo.state.LifeCycleStateFactory;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InvalidPrimaryKeyException;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.util.ClassUtils;

public class JDOAdapter implements ApiAdapter {
   private static final long serialVersionUID = 4135712868062532386L;
   protected static Set defaultPersistentTypeNames = new HashSet();

   public String getName() {
      return "JDO";
   }

   public boolean isMemberDefaultPersistent(Class type) {
      String typeName = type.getName();
      if (defaultPersistentTypeNames.contains(typeName)) {
         return true;
      } else if (!Enum.class.isAssignableFrom(type) && !Serializable.class.isAssignableFrom(type)) {
         return this.isPersistable(type);
      } else {
         return true;
      }
   }

   public boolean isManaged(Object pc) {
      return this.getExecutionContext(pc) != null;
   }

   public ExecutionContext getExecutionContext(Object obj) {
      if (obj == null) {
         return null;
      } else if (obj instanceof Persistable) {
         return (ExecutionContext)((Persistable)obj).dnGetExecutionContext();
      } else {
         return obj instanceof JDOPersistenceManager ? ((JDOPersistenceManager)obj).getExecutionContext() : null;
      }
   }

   public LifeCycleState getLifeCycleState(int stateType) {
      return LifeCycleStateFactory.getLifeCycleState(stateType);
   }

   public boolean isPersistent(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnIsPersistent() : false;
   }

   public boolean isNew(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnIsNew() : false;
   }

   public boolean isDirty(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnIsDirty() : false;
   }

   public boolean isDeleted(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnIsDeleted() : false;
   }

   public boolean isDetached(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnIsDetached() : false;
   }

   public boolean isTransactional(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnIsTransactional() : false;
   }

   public boolean isPersistable(Object obj) {
      return obj == null ? false : obj instanceof Persistable;
   }

   public boolean isPersistable(Class cls) {
      return cls == null ? false : Persistable.class.isAssignableFrom(cls);
   }

   public boolean isDetachable(Object obj) {
      return obj == null ? false : obj instanceof Detachable;
   }

   public String getObjectState(Object obj) {
      return obj == null ? null : JDOHelper.getObjectState(obj).toString();
   }

   public void makeDirty(Object obj, String member) {
      ((Persistable)obj).dnMakeDirty(member);
   }

   public Object getIdForObject(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnGetObjectId() : null;
   }

   public Object getVersionForObject(Object obj) {
      return obj instanceof Persistable ? ((Persistable)obj).dnGetVersion() : null;
   }

   public boolean isValidPrimaryKeyClass(Class pkClass, AbstractClassMetaData cmd, ClassLoaderResolver clr, int noOfPkFields, MetaDataManager mmgr) {
      if (ClassUtils.isInnerClass(pkClass.getName()) && !Modifier.isStatic(pkClass.getModifiers())) {
         throw new InvalidPrimaryKeyException("019000", new Object[]{cmd.getFullClassName(), pkClass.getName()});
      } else if (!Modifier.isPublic(pkClass.getModifiers())) {
         throw new InvalidPrimaryKeyException("019001", new Object[]{cmd.getFullClassName(), pkClass.getName()});
      } else if (!Serializable.class.isAssignableFrom(pkClass)) {
         throw new InvalidPrimaryKeyException("019002", new Object[]{cmd.getFullClassName(), pkClass.getName()});
      } else {
         if (IdentityUtils.isSingleFieldIdentityClass(pkClass.getName())) {
            if (noOfPkFields != 1) {
               throw new InvalidPrimaryKeyException("019003", new Object[]{cmd.getFullClassName()});
            }
         } else {
            try {
               Constructor constructor = pkClass.getConstructor();
               if (constructor == null || !Modifier.isPublic(constructor.getModifiers())) {
                  throw new InvalidPrimaryKeyException("019004", new Object[]{cmd.getFullClassName(), pkClass.getName()});
               }
            } catch (NoSuchMethodException var13) {
               throw new InvalidPrimaryKeyException("019004", new Object[]{cmd.getFullClassName(), pkClass.getName()});
            }

            try {
               Constructor constructor = pkClass.getConstructor(String.class);
               if (constructor == null || !Modifier.isPublic(constructor.getModifiers())) {
                  throw new InvalidPrimaryKeyException("019005", new Object[]{cmd.getFullClassName(), pkClass.getName()});
               }
            } catch (NoSuchMethodException var12) {
            }

            try {
               Method method = pkClass.getMethod("toString");
               if (method == null || !Modifier.isPublic(method.getModifiers()) || method.getDeclaringClass().equals(Object.class)) {
                  throw new InvalidPrimaryKeyException("019006", new Object[]{cmd.getFullClassName(), pkClass.getName()});
               }
            } catch (NoSuchMethodException var11) {
            }

            try {
               Method method = pkClass.getMethod("hashCode");
               if (method == null || method.getDeclaringClass().equals(Object.class)) {
                  throw new InvalidPrimaryKeyException("019007", new Object[]{cmd.getFullClassName(), pkClass.getName()});
               }
            } catch (NoSuchMethodException var10) {
            }

            try {
               Method method = pkClass.getMethod("equals", Object.class);
               if (method == null || method.getDeclaringClass().equals(Object.class)) {
                  throw new InvalidPrimaryKeyException("019008", new Object[]{cmd.getFullClassName(), pkClass.getName()});
               }
            } catch (NoSuchMethodException var9) {
            }

            int noPkFields = this.processPrimaryKeyClass(pkClass, cmd, clr, mmgr);

            for(Class supercls : ClassUtils.getSuperclasses(pkClass)) {
               noPkFields += this.processPrimaryKeyClass(supercls, cmd, clr, mmgr);
            }

            if (noOfPkFields != noPkFields && cmd.getIdentityType() == IdentityType.APPLICATION) {
               throw new InvalidPrimaryKeyException("019015", new Object[]{cmd.getFullClassName(), pkClass.getName(), "" + noOfPkFields, "" + noPkFields});
            }
         }

         return true;
      }
   }

   private int processPrimaryKeyClass(Class pkClass, AbstractClassMetaData cmd, ClassLoaderResolver clr, MetaDataManager mmgr) {
      int noOfPkFields = 0;
      Field[] fieldsInPkClass = pkClass.getDeclaredFields();

      for(int i = 0; i < fieldsInPkClass.length; ++i) {
         if (!Modifier.isStatic(fieldsInPkClass[i].getModifiers())) {
            if (!fieldsInPkClass[i].getType().isPrimitive() && !Serializable.class.isAssignableFrom(fieldsInPkClass[i].getType())) {
               throw new InvalidPrimaryKeyException("019009", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName()});
            }

            if (!Modifier.isPublic(fieldsInPkClass[i].getModifiers())) {
               throw new InvalidPrimaryKeyException("019010", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName()});
            }

            AbstractMemberMetaData fieldInPcClass = cmd.getMetaDataForMember(fieldsInPkClass[i].getName());
            boolean found_field = false;
            if (fieldInPcClass == null) {
               throw new InvalidPrimaryKeyException("019011", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName()});
            }

            if (fieldInPcClass.getTypeName().equals(fieldsInPkClass[i].getType().getName())) {
               found_field = true;
            }

            if (!found_field) {
               String fieldTypePkClass = fieldsInPkClass[i].getType().getName();
               AbstractClassMetaData ref_cmd = mmgr.getMetaDataForClassInternal(fieldInPcClass.getType(), clr);
               if (ref_cmd == null) {
                  throw new InvalidPrimaryKeyException("019012", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName(), fieldInPcClass.getType().getName()});
               }

               if (ref_cmd.getObjectidClass() == null && IdentityUtils.isSingleFieldIdentityClass(fieldTypePkClass)) {
                  throw new InvalidPrimaryKeyException("019014", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName(), fieldTypePkClass, ref_cmd.getFullClassName()});
               }

               if (!fieldTypePkClass.equals(ref_cmd.getObjectidClass())) {
                  throw new InvalidPrimaryKeyException("019013", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName(), fieldTypePkClass, ref_cmd.getObjectidClass()});
               }

               found_field = true;
            }

            if (!found_field) {
               throw new InvalidPrimaryKeyException("019012", new Object[]{cmd.getFullClassName(), pkClass.getName(), fieldsInPkClass[i].getName(), fieldInPcClass.getType().getName()});
            }

            ++noOfPkFields;
         }
      }

      return noOfPkFields;
   }

   public boolean allowPersistOfDeletedObject() {
      return false;
   }

   public boolean allowDeleteOfNonPersistentObject() {
      return false;
   }

   public boolean allowReadFieldOfDeletedObject() {
      return false;
   }

   public boolean clearLoadedFlagsOnDeleteObject() {
      return true;
   }

   public boolean getDefaultCascadePersistForField() {
      return true;
   }

   public boolean getDefaultCascadeUpdateForField() {
      return true;
   }

   public boolean getDefaultCascadeDeleteForField() {
      return false;
   }

   public boolean getDefaultCascadeRefreshForField() {
      return false;
   }

   public boolean getDefaultDFGForPersistableField() {
      return false;
   }

   public Map getDefaultFactoryProperties() {
      Map props = new HashMap();
      props.put("datanucleus.DetachAllOnCommit", "false");
      props.put("datanucleus.CopyOnAttach", "true");
      props.put("datanucleus.identifierFactory", "datanucleus2");
      props.put("datanucleus.persistenceByReachabilityAtCommit", "true");
      props.put("datanucleus.query.sql.allowAll", "false");
      props.put("datanucleus.validation.mode", "none");
      props.put("datanucleus.executionContext.closeActiveTxAction", "rollback");
      return props;
   }

   public RuntimeException getUserExceptionForException(String msg, Exception e) {
      return new JDOUserException(msg, e);
   }

   public RuntimeException getDataStoreExceptionForException(String msg, Exception e) {
      return new JDODataStoreException(msg, e);
   }

   public RuntimeException getApiExceptionForNucleusException(NucleusException ne) {
      return NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
   }

   public void copyKeyFieldsFromIdToObject(Object pc, Persistable.ObjectIdFieldConsumer fm, Object id) {
      ((Persistable)pc).dnCopyKeyFieldsFromObjectId(fm, id);
   }

   static {
      defaultPersistentTypeNames.add(ClassNameConstants.BOOLEAN);
      defaultPersistentTypeNames.add(ClassNameConstants.BYTE);
      defaultPersistentTypeNames.add(ClassNameConstants.CHAR);
      defaultPersistentTypeNames.add(ClassNameConstants.DOUBLE);
      defaultPersistentTypeNames.add(ClassNameConstants.FLOAT);
      defaultPersistentTypeNames.add(ClassNameConstants.INT);
      defaultPersistentTypeNames.add(ClassNameConstants.LONG);
      defaultPersistentTypeNames.add(ClassNameConstants.SHORT);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_BOOLEAN);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_BYTE);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_CHARACTER);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_DOUBLE);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_FLOAT);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_INTEGER);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_LONG);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_SHORT);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_STRING);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_UTIL_DATE);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_SQL_DATE);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_SQL_TIME);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_SQL_TIMESTAMP);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_MATH_BIGDECIMAL);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_MATH_BIGINTEGER);
      defaultPersistentTypeNames.add(Locale.class.getName());
      defaultPersistentTypeNames.add(Currency.class.getName());
      defaultPersistentTypeNames.add(ClassNameConstants.BOOLEAN_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.BYTE_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.CHAR_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.DOUBLE_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.FLOAT_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.INT_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.LONG_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.SHORT_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_BOOLEAN_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_BYTE_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_CHARACTER_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_DOUBLE_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_FLOAT_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_INTEGER_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_LONG_ARRAY);
      defaultPersistentTypeNames.add(ClassNameConstants.JAVA_LANG_SHORT_ARRAY);
   }
}

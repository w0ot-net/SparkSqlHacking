package org.datanucleus.identity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.util.ClassUtils;

public class IdentityUtils {
   public static boolean isSingleFieldIdentityClass(String className) {
      if (className != null && className.length() >= 1) {
         return className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_BYTE) || className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_CHAR) || className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_INT) || className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_LONG) || className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_OBJECT) || className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_SHORT) || className.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_STRING);
      } else {
         return false;
      }
   }

   public static String getTargetClassNameForIdentitySimple(Object id) {
      if (id instanceof DatastoreId) {
         return ((DatastoreId)id).getTargetClassName();
      } else {
         return id instanceof SingleFieldId ? ((SingleFieldId)id).getTargetClassName() : null;
      }
   }

   public static boolean isSingleFieldIdentity(Object id) {
      return id instanceof SingleFieldId;
   }

   public static boolean isDatastoreIdentity(Object id) {
      return id != null && id instanceof DatastoreId;
   }

   public static Object getTargetKeyForSingleFieldIdentity(Object id) {
      return id instanceof SingleFieldId ? ((SingleFieldId)id).getKeyAsObject() : null;
   }

   public static Object getTargetKeyForDatastoreIdentity(Object id) {
      return id instanceof DatastoreId ? ((DatastoreId)id).getKeyAsObject() : null;
   }

   public static Class getKeyTypeForSingleFieldIdentityType(Class idType) {
      if (idType == null) {
         return null;
      } else if (!isSingleFieldIdentityClass(idType.getName())) {
         return null;
      } else if (ClassConstants.IDENTITY_SINGLEFIELD_LONG.isAssignableFrom(idType)) {
         return Long.class;
      } else if (ClassConstants.IDENTITY_SINGLEFIELD_INT.isAssignableFrom(idType)) {
         return Integer.class;
      } else if (ClassConstants.IDENTITY_SINGLEFIELD_SHORT.isAssignableFrom(idType)) {
         return Short.class;
      } else if (ClassConstants.IDENTITY_SINGLEFIELD_BYTE.isAssignableFrom(idType)) {
         return Byte.class;
      } else if (ClassConstants.IDENTITY_SINGLEFIELD_CHAR.isAssignableFrom(idType)) {
         return Character.class;
      } else if (ClassConstants.IDENTITY_SINGLEFIELD_STRING.isAssignableFrom(idType)) {
         return String.class;
      } else {
         return ClassConstants.IDENTITY_SINGLEFIELD_OBJECT.isAssignableFrom(idType) ? Object.class : null;
      }
   }

   public static String getPersistableIdentityForId(Object id) {
      if (id == null) {
         return null;
      } else {
         return isSingleFieldIdentity(id) ? ((SingleFieldId)id).getTargetClassName() + ":" + ((SingleFieldId)id).getKeyAsObject() : id.toString();
      }
   }

   public static Object getObjectFromPersistableIdentity(String persistableId, AbstractClassMetaData cmd, ExecutionContext ec) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      Object id = null;
      if (cmd == null) {
         throw new NucleusException("Cannot get object from id=" + persistableId + " since class name was not supplied!");
      } else {
         if (cmd.getIdentityType() == IdentityType.DATASTORE) {
            id = ec.getNucleusContext().getIdentityManager().getDatastoreId(persistableId);
         } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
            if (cmd.usesSingleFieldIdentityClass()) {
               String className = persistableId.substring(0, persistableId.indexOf(58));
               cmd = ec.getMetaDataManager().getMetaDataForClass(className, clr);
               String idStr = persistableId.substring(persistableId.indexOf(58) + 1);
               if (cmd.getObjectidClass().equals(ClassNameConstants.IDENTITY_SINGLEFIELD_OBJECT)) {
                  int[] pkMemberPositions = cmd.getPKMemberPositions();
                  AbstractMemberMetaData pkMmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkMemberPositions[0]);
                  idStr = pkMmd.getTypeName() + ":" + idStr;
               }

               id = ec.getNucleusContext().getIdentityManager().getApplicationId(clr, cmd, idStr);
            } else {
               Class cls = clr.classForName(cmd.getFullClassName());
               id = ec.newObjectId((Class)cls, persistableId);
            }
         }

         return ec.findObject(id, true, false, (String)null);
      }
   }

   public static Object getApplicationIdentityForResultSetRow(ExecutionContext ec, AbstractClassMetaData cmd, Class pcClass, boolean inheritanceCheck, FieldManager resultsFM) {
      if (cmd.getIdentityType() != IdentityType.APPLICATION) {
         return null;
      } else {
         if (pcClass == null) {
            pcClass = ec.getClassLoaderResolver().classForName(cmd.getFullClassName());
         }

         ApiAdapter api = ec.getApiAdapter();
         int[] pkFieldNums = cmd.getPKMemberPositions();
         Object[] pkFieldValues = new Object[pkFieldNums.length];

         for(int i = 0; i < pkFieldNums.length; ++i) {
            AbstractMemberMetaData pkMmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[i]);
            if (pkMmd.getType() == Integer.TYPE) {
               pkFieldValues[i] = resultsFM.fetchIntField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Short.TYPE) {
               pkFieldValues[i] = resultsFM.fetchShortField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Long.TYPE) {
               pkFieldValues[i] = resultsFM.fetchLongField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Character.TYPE) {
               pkFieldValues[i] = resultsFM.fetchCharField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Boolean.TYPE) {
               pkFieldValues[i] = resultsFM.fetchBooleanField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Byte.TYPE) {
               pkFieldValues[i] = resultsFM.fetchByteField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Double.TYPE) {
               pkFieldValues[i] = resultsFM.fetchDoubleField(pkFieldNums[i]);
            } else if (pkMmd.getType() == Float.TYPE) {
               pkFieldValues[i] = resultsFM.fetchFloatField(pkFieldNums[i]);
            } else if (pkMmd.getType() == String.class) {
               pkFieldValues[i] = resultsFM.fetchStringField(pkFieldNums[i]);
            } else {
               pkFieldValues[i] = resultsFM.fetchObjectField(pkFieldNums[i]);
            }
         }

         Class idClass = ec.getClassLoaderResolver().classForName(cmd.getObjectidClass());
         if (!cmd.usesSingleFieldIdentityClass()) {
            try {
               Object id = idClass.newInstance();

               for(int i = 0; i < pkFieldNums.length; ++i) {
                  AbstractMemberMetaData pkMmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[i]);
                  Object value = pkFieldValues[i];
                  if (api.isPersistable(value)) {
                     value = api.getIdForObject(value);
                  }

                  if (pkMmd instanceof FieldMetaData) {
                     Field pkField = ClassUtils.getFieldForClass(idClass, pkMmd.getName());
                     pkField.set(id, value);
                  } else {
                     Method pkMethod = ClassUtils.getSetterMethodForClass(idClass, pkMmd.getName(), pkMmd.getType());
                     pkMethod.invoke(id, value);
                  }
               }

               return id;
            } catch (Exception var14) {
               return null;
            }
         } else {
            Object id = ec.getNucleusContext().getIdentityManager().getSingleFieldId(idClass, pcClass, pkFieldValues[0]);
            if (inheritanceCheck) {
               if (ec.hasIdentityInCache(id)) {
                  return id;
               } else {
                  String[] subclasses = ec.getMetaDataManager().getSubclassesForClass(pcClass.getName(), true);
                  if (subclasses != null) {
                     for(int i = 0; i < subclasses.length; ++i) {
                        Object subid = ec.getNucleusContext().getIdentityManager().getSingleFieldId(idClass, ec.getClassLoaderResolver().classForName(subclasses[i]), getTargetKeyForSingleFieldIdentity(id));
                        if (ec.hasIdentityInCache(subid)) {
                           return subid;
                        }
                     }
                  }

                  String className = ec.getStoreManager().getClassNameForObjectID(id, ec.getClassLoaderResolver(), ec);
                  return ec.getNucleusContext().getIdentityManager().getSingleFieldId(idClass, ec.getClassLoaderResolver().classForName(className), pkFieldValues[0]);
               }
            } else {
               return id;
            }
         }
      }
   }

   public static Object getValueForMemberInId(Object id, AbstractMemberMetaData pkMmd) {
      if (id != null && pkMmd != null && pkMmd.isPrimaryKey()) {
         String memberName = pkMmd.getName();
         Field fld = ClassUtils.getFieldForClass(id.getClass(), memberName);
         if (fld != null && !Modifier.isPrivate(fld.getModifiers())) {
            try {
               return fld.get(id);
            } catch (Exception var7) {
            }
         }

         Method getter = ClassUtils.getGetterMethodForClass(id.getClass(), memberName);
         if (getter != null && !Modifier.isPrivate(getter.getModifiers())) {
            try {
               return getter.invoke(id);
            } catch (Exception var6) {
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static Object getObjectFromIdString(String idStr, AbstractClassMetaData cmd, ExecutionContext ec, boolean checkInheritance) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      Object id = null;
      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         id = ec.getNucleusContext().getIdentityManager().getDatastoreId(idStr);
      } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         if (cmd.usesSingleFieldIdentityClass()) {
            id = ec.getNucleusContext().getIdentityManager().getApplicationId(clr, cmd, idStr);
         } else {
            Class cls = clr.classForName(cmd.getFullClassName());
            id = ec.newObjectId((Class)cls, idStr);
         }
      }

      return ec.findObject(id, true, checkInheritance, (String)null);
   }

   public static Object getObjectFromIdString(String idStr, AbstractMemberMetaData mmd, FieldRole fieldRole, ExecutionContext ec, boolean checkInheritance) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      if (fieldRole == FieldRole.ROLE_FIELD && mmd.getType().isInterface()) {
         String[] implNames = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(mmd, fieldRole, clr, ec.getMetaDataManager());
         if (implNames != null && implNames.length != 0) {
            AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(implNames[0], clr);
            if (cmd.getIdentityType() == IdentityType.DATASTORE) {
               Object id = ec.getNucleusContext().getIdentityManager().getDatastoreId(idStr);
               return ec.findObject(id, true, checkInheritance, (String)null);
            } else {
               if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                  Object id = null;

                  for(int i = 0; i < implNames.length; ++i) {
                     if (i != 0) {
                        cmd = ec.getMetaDataManager().getMetaDataForClass(implNames[i], clr);
                     }

                     if (cmd.usesSingleFieldIdentityClass()) {
                        id = ec.getNucleusContext().getIdentityManager().getApplicationId(clr, cmd, idStr);
                     } else {
                        id = ec.newObjectId((Class)clr.classForName(cmd.getFullClassName()), idStr);
                     }

                     try {
                        return ec.findObject(id, true, checkInheritance, (String)null);
                     }
                  }
               }

               return null;
            }
         } else {
            return null;
         }
      } else {
         AbstractClassMetaData cmd = null;
         if (fieldRole == FieldRole.ROLE_COLLECTION_ELEMENT) {
            cmd = mmd.getCollection().getElementClassMetaData(clr, ec.getMetaDataManager());
         } else if (fieldRole == FieldRole.ROLE_ARRAY_ELEMENT) {
            cmd = mmd.getArray().getElementClassMetaData(clr, ec.getMetaDataManager());
         } else if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            cmd = mmd.getMap().getKeyClassMetaData(clr, ec.getMetaDataManager());
         } else if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            cmd = mmd.getMap().getKeyClassMetaData(clr, ec.getMetaDataManager());
         } else {
            cmd = ec.getMetaDataManager().getMetaDataForClass(mmd.getType(), clr);
         }

         Object id = null;
         if (cmd.getIdentityType() == IdentityType.DATASTORE) {
            id = ec.getNucleusContext().getIdentityManager().getDatastoreId(idStr);
         } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
            if (cmd.usesSingleFieldIdentityClass()) {
               Class cls = clr.classForName(cmd.getFullClassName());
               if (Modifier.isAbstract(cls.getModifiers())) {
                  String[] subclasses = ec.getMetaDataManager().getSubclassesForClass(cmd.getFullClassName(), false);
                  if (subclasses != null) {
                     for(int i = 0; i < subclasses.length; ++i) {
                        cls = clr.classForName(subclasses[i]);
                        if (!Modifier.isAbstract(cls.getModifiers())) {
                           cmd = ec.getMetaDataManager().getMetaDataForClass(cls, clr);
                           break;
                        }
                     }
                  }
               }

               id = ec.getNucleusContext().getIdentityManager().getApplicationId(clr, cmd, idStr);
            } else {
               Class cls = clr.classForName(cmd.getFullClassName());
               id = ec.newObjectId((Class)cls, idStr);
            }
         }

         return ec.findObject(id, true, checkInheritance, (String)null);
      }
   }
}

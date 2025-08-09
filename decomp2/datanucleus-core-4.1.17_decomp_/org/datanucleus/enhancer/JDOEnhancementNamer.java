package org.datanucleus.enhancer;

import org.datanucleus.ClassConstants;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.util.DetachListener;

public class JDOEnhancementNamer implements EnhancementNamer {
   private static JDOEnhancementNamer instance = null;
   private static final Class CL_Detachable = Detachable.class;
   private static final Class CL_Persistable;
   private static final Class CL_ObjectIdFieldConsumer;
   private static final Class CL_ObjectIdFieldSupplier;
   private static final Class CL_ExecutionContextRef;
   private static final Class CL_StateManager;
   private static final String ACN_DetachListener;
   private static final String ACN_StateManager;
   private static final String ACN_ExecutionContext;
   private static final String ACN_Persistable;
   private static final String ACN_Detachable;
   private static final String ACN_ObjectIdFieldConsumer;
   private static final String ACN_ObjectIdFieldSupplier;
   private static final String ACN_DetachedFieldAccessException = "javax/jdo/JDODetachedFieldAccessException";
   private static final String ACN_FatalInternalException = "javax/jdo/JDOFatalInternalException";
   private static final String ACN_ImplHelper;
   private static final String CD_ByteIdentity;
   private static final String CD_CharIdentity;
   private static final String CD_IntIdentity;
   private static final String CD_LongIdentity;
   private static final String CD_ShortIdentity;
   private static final String CD_StringIdentity;
   private static final String CD_ObjectIdentity;
   private static final String CD_StateManager;
   private static final String CD_ExecutionContextRef;
   private static final String CD_Persistable;
   private static final String CD_Detachable;
   private static final String CD_ObjectIdFieldConsumer;
   private static final String CD_ObjectIdFieldSupplier;
   private static final String CD_String;
   private static final String CD_Object;

   public static JDOEnhancementNamer getInstance() {
      if (instance == null) {
         instance = new JDOEnhancementNamer();
      }

      return instance;
   }

   protected JDOEnhancementNamer() {
   }

   public String getStateManagerFieldName() {
      return "dnStateManager";
   }

   public String getFlagsFieldName() {
      return "dnFlags";
   }

   public String getFieldNamesFieldName() {
      return "dnFieldNames";
   }

   public String getFieldTypesFieldName() {
      return "dnFieldTypes";
   }

   public String getFieldFlagsFieldName() {
      return "dnFieldFlags";
   }

   public String getPersistableSuperclassFieldName() {
      return "dnPersistableSuperclass";
   }

   public String getInheritedFieldCountFieldName() {
      return "dnInheritedFieldCount";
   }

   public String getDetachedStateFieldName() {
      return "dnDetachedState";
   }

   public String getSerialVersionUidFieldName() {
      return "serialVersionUID";
   }

   public String getFieldNamesInitMethodName() {
      return "__dnFieldNamesInit";
   }

   public String getFieldTypesInitMethodName() {
      return "__dnFieldTypesInit";
   }

   public String getFieldFlagsInitMethodName() {
      return "__dnFieldFlagsInit";
   }

   public String getGetObjectIdMethodName() {
      return "dnGetObjectId";
   }

   public String getGetTransactionalObjectIdMethodName() {
      return "dnGetTransactionalObjectId";
   }

   public String getGetVersionMethodName() {
      return "dnGetVersion";
   }

   public String getIsDetachedMethodName() {
      return "dnIsDetached";
   }

   public String getIsDetachedInternalMethodName() {
      return "dnIsDetachedInternal";
   }

   public String getIsDeletedMethodName() {
      return "dnIsDeleted";
   }

   public String getIsDirtyMethodName() {
      return "dnIsDirty";
   }

   public String getIsNewMethodName() {
      return "dnIsNew";
   }

   public String getIsPersistentMethodName() {
      return "dnIsPersistent";
   }

   public String getIsTransactionalMethodName() {
      return "dnIsTransactional";
   }

   public String getGetExecutionContextMethodName() {
      return "dnGetExecutionContext";
   }

   public String getPreSerializeMethodName() {
      return "dnPreSerialize";
   }

   public String getGetInheritedFieldCountMethodName() {
      return "__dnGetInheritedFieldCount";
   }

   public String getSuperCloneMethodName() {
      return "dnSuperClone";
   }

   public String getGetManagedFieldCountMethodName() {
      return "dnGetManagedFieldCount";
   }

   public String getPersistableSuperclassInitMethodName() {
      return "__dnPersistableSuperclassInit";
   }

   public String getLoadClassMethodName() {
      return "___dn$loadClass";
   }

   public String getCopyFieldMethodName() {
      return "dnCopyField";
   }

   public String getCopyFieldsMethodName() {
      return "dnCopyFields";
   }

   public String getCopyKeyFieldsFromObjectIdMethodName() {
      return "dnCopyKeyFieldsFromObjectId";
   }

   public String getCopyKeyFieldsToObjectIdMethodName() {
      return "dnCopyKeyFieldsToObjectId";
   }

   public String getProvideFieldMethodName() {
      return "dnProvideField";
   }

   public String getProvideFieldsMethodName() {
      return "dnProvideFields";
   }

   public String getReplaceFieldMethodName() {
      return "dnReplaceField";
   }

   public String getReplaceFieldsMethodName() {
      return "dnReplaceFields";
   }

   public String getReplaceFlagsMethodName() {
      return "dnReplaceFlags";
   }

   public String getReplaceStateManagerMethodName() {
      return "dnReplaceStateManager";
   }

   public String getReplaceDetachedStateMethodName() {
      return "dnReplaceDetachedState";
   }

   public String getMakeDirtyMethodName() {
      return "dnMakeDirty";
   }

   public String getMakeDirtyDetachedMethodName() {
      return "dnMakeDirtyDetached";
   }

   public String getNewInstanceMethodName() {
      return "dnNewInstance";
   }

   public String getNewObjectIdInstanceMethodName() {
      return "dnNewObjectIdInstance";
   }

   public String getGetMethodPrefixMethodName() {
      return "dnGet";
   }

   public String getSetMethodPrefixMethodName() {
      return "dnSet";
   }

   public String getDetachListenerAsmClassName() {
      return ACN_DetachListener;
   }

   public String getStateManagerAsmClassName() {
      return ACN_StateManager;
   }

   public String getExecutionContextAsmClassName() {
      return ACN_ExecutionContext;
   }

   public String getPersistableAsmClassName() {
      return ACN_Persistable;
   }

   public String getDetachableAsmClassName() {
      return ACN_Detachable;
   }

   public String getObjectIdFieldConsumerAsmClassName() {
      return ACN_ObjectIdFieldConsumer;
   }

   public String getObjectIdFieldSupplierAsmClassName() {
      return ACN_ObjectIdFieldSupplier;
   }

   public String getDetachedFieldAccessExceptionAsmClassName() {
      return "javax/jdo/JDODetachedFieldAccessException";
   }

   public String getFatalInternalExceptionAsmClassName() {
      return "javax/jdo/JDOFatalInternalException";
   }

   public String getImplHelperAsmClassName() {
      return ACN_ImplHelper;
   }

   public String getStateManagerDescriptor() {
      return CD_StateManager;
   }

   public String getExecutionContextDescriptor() {
      return CD_ExecutionContextRef;
   }

   public String getPersistableDescriptor() {
      return CD_Persistable;
   }

   public String getDetachableDescriptor() {
      return CD_Detachable;
   }

   public String getSingleFieldIdentityDescriptor(String oidClassName) {
      if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_LONG)) {
         return CD_LongIdentity;
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_INT)) {
         return CD_IntIdentity;
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_STRING)) {
         return CD_StringIdentity;
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_SHORT)) {
         return CD_ShortIdentity;
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_CHAR)) {
         return CD_CharIdentity;
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_BYTE)) {
         return CD_ByteIdentity;
      } else {
         return oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_OBJECT) ? CD_ObjectIdentity : null;
      }
   }

   public String getTypeDescriptorForSingleFieldIdentityGetKey(String oidClassName) {
      if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_LONG)) {
         return Type.LONG_TYPE.getDescriptor();
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_INT)) {
         return Type.INT_TYPE.getDescriptor();
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_SHORT)) {
         return Type.SHORT_TYPE.getDescriptor();
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_CHAR)) {
         return Type.CHAR_TYPE.getDescriptor();
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_BYTE)) {
         return Type.BYTE_TYPE.getDescriptor();
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_STRING)) {
         return CD_String;
      } else {
         return oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_OBJECT) ? CD_Object : null;
      }
   }

   public String getTypeNameForUseWithSingleFieldIdentity(String oidClassName) {
      if (oidClassName == null) {
         return null;
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_BYTE)) {
         return "Byte";
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_CHAR)) {
         return "Char";
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_INT)) {
         return "Int";
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_LONG)) {
         return "Long";
      } else if (oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_SHORT)) {
         return "Short";
      } else {
         return oidClassName.equals(ClassNameConstants.IDENTITY_SINGLEFIELD_STRING) ? "String" : "Object";
      }
   }

   public String getObjectIdFieldConsumerDescriptor() {
      return CD_ObjectIdFieldConsumer;
   }

   public String getObjectIdFieldSupplierDescriptor() {
      return CD_ObjectIdFieldSupplier;
   }

   public Class getExecutionContextClass() {
      return CL_ExecutionContextRef;
   }

   public Class getStateManagerClass() {
      return CL_StateManager;
   }

   public Class getPersistableClass() {
      return CL_Persistable;
   }

   public Class getDetachableClass() {
      return CL_Detachable;
   }

   public Class getObjectIdFieldSupplierClass() {
      return CL_ObjectIdFieldSupplier;
   }

   public Class getObjectIdFieldConsumerClass() {
      return CL_ObjectIdFieldConsumer;
   }

   public Class getObjectIdentityClass() {
      return ClassConstants.IDENTITY_SINGLEFIELD_OBJECT;
   }

   static {
      CL_Persistable = ClassConstants.PERSISTABLE;
      CL_ObjectIdFieldConsumer = Persistable.ObjectIdFieldConsumer.class;
      CL_ObjectIdFieldSupplier = Persistable.ObjectIdFieldSupplier.class;
      CL_ExecutionContextRef = ClassConstants.EXECUTION_CONTEXT_REFERENCE;
      CL_StateManager = ClassConstants.STATE_MANAGER;
      ACN_DetachListener = DetachListener.class.getName().replace('.', '/');
      ACN_StateManager = CL_StateManager.getName().replace('.', '/');
      ACN_ExecutionContext = CL_ExecutionContextRef.getName().replace('.', '/');
      ACN_Persistable = CL_Persistable.getName().replace('.', '/');
      ACN_Detachable = CL_Detachable.getName().replace('.', '/');
      ACN_ObjectIdFieldConsumer = CL_ObjectIdFieldConsumer.getName().replace('.', '/');
      ACN_ObjectIdFieldSupplier = CL_ObjectIdFieldSupplier.getName().replace('.', '/');
      ACN_ImplHelper = EnhancementHelper.class.getName().replace('.', '/');
      CD_ByteIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_BYTE);
      CD_CharIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_CHAR);
      CD_IntIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_INT);
      CD_LongIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_LONG);
      CD_ShortIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_SHORT);
      CD_StringIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_STRING);
      CD_ObjectIdentity = Type.getDescriptor(ClassConstants.IDENTITY_SINGLEFIELD_OBJECT);
      CD_StateManager = Type.getDescriptor(ClassConstants.STATE_MANAGER);
      CD_ExecutionContextRef = Type.getDescriptor(ClassConstants.EXECUTION_CONTEXT_REFERENCE);
      CD_Persistable = Type.getDescriptor(ClassConstants.PERSISTABLE);
      CD_Detachable = Type.getDescriptor(CL_Detachable);
      CD_ObjectIdFieldConsumer = Type.getDescriptor(Persistable.ObjectIdFieldConsumer.class);
      CD_ObjectIdFieldSupplier = Type.getDescriptor(Persistable.ObjectIdFieldSupplier.class);
      CD_String = Type.getDescriptor(String.class);
      CD_Object = Type.getDescriptor(Object.class);
   }
}

package org.datanucleus.enhancer;

public interface EnhancementNamer {
   String getStateManagerFieldName();

   String getFlagsFieldName();

   String getFieldNamesFieldName();

   String getFieldTypesFieldName();

   String getFieldFlagsFieldName();

   String getPersistableSuperclassFieldName();

   String getInheritedFieldCountFieldName();

   String getDetachedStateFieldName();

   String getSerialVersionUidFieldName();

   String getFieldNamesInitMethodName();

   String getFieldTypesInitMethodName();

   String getFieldFlagsInitMethodName();

   String getGetObjectIdMethodName();

   String getGetTransactionalObjectIdMethodName();

   String getGetVersionMethodName();

   String getIsDetachedMethodName();

   String getIsDetachedInternalMethodName();

   String getIsDeletedMethodName();

   String getIsDirtyMethodName();

   String getIsNewMethodName();

   String getIsPersistentMethodName();

   String getIsTransactionalMethodName();

   String getGetExecutionContextMethodName();

   String getPreSerializeMethodName();

   String getGetInheritedFieldCountMethodName();

   String getSuperCloneMethodName();

   String getGetManagedFieldCountMethodName();

   String getPersistableSuperclassInitMethodName();

   String getLoadClassMethodName();

   String getCopyFieldMethodName();

   String getCopyFieldsMethodName();

   String getCopyKeyFieldsFromObjectIdMethodName();

   String getCopyKeyFieldsToObjectIdMethodName();

   String getProvideFieldMethodName();

   String getProvideFieldsMethodName();

   String getReplaceFieldMethodName();

   String getReplaceFieldsMethodName();

   String getReplaceFlagsMethodName();

   String getReplaceStateManagerMethodName();

   String getReplaceDetachedStateMethodName();

   String getMakeDirtyMethodName();

   String getMakeDirtyDetachedMethodName();

   String getNewInstanceMethodName();

   String getNewObjectIdInstanceMethodName();

   String getGetMethodPrefixMethodName();

   String getSetMethodPrefixMethodName();

   String getDetachListenerAsmClassName();

   String getStateManagerAsmClassName();

   String getExecutionContextAsmClassName();

   String getPersistableAsmClassName();

   String getDetachableAsmClassName();

   String getObjectIdFieldConsumerAsmClassName();

   String getObjectIdFieldSupplierAsmClassName();

   String getDetachedFieldAccessExceptionAsmClassName();

   String getFatalInternalExceptionAsmClassName();

   String getImplHelperAsmClassName();

   String getSingleFieldIdentityDescriptor(String var1);

   String getTypeDescriptorForSingleFieldIdentityGetKey(String var1);

   String getTypeNameForUseWithSingleFieldIdentity(String var1);

   String getStateManagerDescriptor();

   String getExecutionContextDescriptor();

   String getPersistableDescriptor();

   String getDetachableDescriptor();

   String getObjectIdFieldConsumerDescriptor();

   String getObjectIdFieldSupplierDescriptor();

   Class getExecutionContextClass();

   Class getStateManagerClass();

   Class getPersistableClass();

   Class getDetachableClass();

   Class getObjectIdFieldSupplierClass();

   Class getObjectIdFieldConsumerClass();

   Class getObjectIdentityClass();
}

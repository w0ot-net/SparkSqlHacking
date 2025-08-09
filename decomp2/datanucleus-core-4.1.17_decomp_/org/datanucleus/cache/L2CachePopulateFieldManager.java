package org.datanucleus.cache;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.fieldmanager.AbstractFieldManager;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOContainer;
import org.datanucleus.util.NucleusLogger;

public class L2CachePopulateFieldManager extends AbstractFieldManager {
   ObjectProvider op;
   ExecutionContext ec;
   CachedPC cachedPC;

   public L2CachePopulateFieldManager(ObjectProvider op, CachedPC cachedpc) {
      this.op = op;
      this.ec = op.getExecutionContext();
      this.cachedPC = cachedpc;
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeCharField(int fieldNumber, char value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeByteField(int fieldNumber, byte value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeShortField(int fieldNumber, short value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeIntField(int fieldNumber, int value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeLongField(int fieldNumber, long value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeFloatField(int fieldNumber, float value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeDoubleField(int fieldNumber, double value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeStringField(int fieldNumber, String value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   public void storeObjectField(int fieldNumber, Object value) {
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      if (mmd.getPersistenceModifier() == FieldPersistenceModifier.TRANSACTIONAL) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else if (!mmd.isCacheable()) {
         this.cachedPC.setLoadedField(fieldNumber, false);
      } else {
         this.cachedPC.setLoadedField(fieldNumber, true);
         if (value == null) {
            this.cachedPC.setFieldValue(fieldNumber, (Object)null);
         } else if (mmd.hasContainer()) {
            this.processContainer(fieldNumber, mmd, value);
         } else {
            this.processField(fieldNumber, mmd, value);
         }
      }
   }

   protected void processContainer(int fieldNumber, AbstractMemberMetaData mmd, Object inputValue) {
      Object value = inputValue;
      if (inputValue instanceof SCOContainer) {
         if (!((SCOContainer)inputValue).isLoaded()) {
            this.cachedPC.setLoadedField(fieldNumber, false);
            return;
         }

         value = ((SCO)inputValue).getValue();
      }

      ApiAdapter api = this.ec.getApiAdapter();
      RelationType relType = mmd.getRelationType(this.ec.getClassLoaderResolver());
      if (mmd.hasMap()) {
         if (!RelationType.isRelationMultiValued(relType)) {
            this.cachedPC.setFieldValue(fieldNumber, value);
         } else if (!mmd.isSerialized() && !mmd.isEmbedded() && !mmd.getMap().isSerializedKey() && !mmd.getMap().isSerializedValue() && (!mmd.getMap().keyIsPersistent() || !mmd.getMap().isEmbeddedKey()) && (!mmd.getMap().valueIsPersistent() || !mmd.getMap().isEmbeddedValue())) {
            try {
               Map returnMap = null;
               if (value.getClass().isInterface()) {
                  returnMap = new HashMap();
               } else if (value instanceof SCO) {
                  returnMap = (Map)((SCO)value).getValue().getClass().newInstance();
               } else {
                  returnMap = (Map)value.getClass().newInstance();
               }

               for(Map.Entry entry : ((Map)value).entrySet()) {
                  Object mapKey = null;
                  Object mapValue = null;
                  if (mmd.getMap().keyIsPersistent()) {
                     mapKey = this.getCacheableIdForId(api, entry.getKey());
                  } else {
                     mapKey = entry.getKey();
                  }

                  if (mmd.getMap().valueIsPersistent()) {
                     mapValue = this.getCacheableIdForId(api, entry.getValue());
                  } else {
                     mapValue = entry.getValue();
                  }

                  returnMap.put(mapKey, mapValue);
               }

               this.cachedPC.setFieldValue(fieldNumber, returnMap);
            } catch (Exception e) {
               NucleusLogger.CACHE.warn("Unable to create object of type " + value.getClass().getName() + " for L2 caching : " + e.getMessage());
               this.cachedPC.setLoadedField(fieldNumber, false);
            }
         } else {
            this.cachedPC.setLoadedField(fieldNumber, false);
         }
      } else if (!mmd.hasCollection()) {
         if (mmd.hasArray()) {
            if (MetaDataUtils.getInstance().storesPersistable(mmd, this.ec)) {
               if (!mmd.isSerialized() && !mmd.isEmbedded() && !mmd.getArray().isSerializedElement() && !mmd.getArray().isEmbeddedElement()) {
                  Object[] returnArr = new Object[Array.getLength(value)];

                  for(int i = 0; i < Array.getLength(value); ++i) {
                     Object element = Array.get(value, i);
                     if (element != null) {
                        returnArr[i] = this.getCacheableIdForId(api, element);
                     } else {
                        returnArr[i] = null;
                     }
                  }

                  this.cachedPC.setFieldValue(fieldNumber, returnArr);
               } else {
                  this.cachedPC.setLoadedField(fieldNumber, false);
               }
            } else {
               this.cachedPC.setFieldValue(fieldNumber, value);
            }
         }
      } else if (RelationType.isRelationMultiValued(relType)) {
         if (value instanceof List && mmd.getOrderMetaData() != null && !mmd.getOrderMetaData().isIndexedList()) {
            this.cachedPC.setLoadedField(fieldNumber, false);
         } else if (!mmd.isSerialized() && !mmd.isEmbedded() && !mmd.getCollection().isSerializedElement() && !mmd.getCollection().isEmbeddedElement()) {
            Collection collValue = (Collection)value;
            Iterator collIter = collValue.iterator();
            Collection returnColl = null;

            try {
               if (value.getClass().isInterface()) {
                  if (!List.class.isAssignableFrom(value.getClass()) && mmd.getOrderMetaData() == null) {
                     returnColl = new HashSet();
                  } else {
                     returnColl = new ArrayList();
                  }
               } else if (value instanceof SCO) {
                  returnColl = (Collection)((SCO)value).getValue().getClass().newInstance();
               } else {
                  returnColl = (Collection)value.getClass().newInstance();
               }

               while(collIter.hasNext()) {
                  Object elem = collIter.next();
                  if (elem == null) {
                     returnColl.add((Object)null);
                  } else {
                     returnColl.add(this.getCacheableIdForId(api, elem));
                  }
               }

               this.cachedPC.setFieldValue(fieldNumber, returnColl);
            } catch (Exception e) {
               NucleusLogger.CACHE.warn("Unable to create object of type " + value.getClass().getName() + " for L2 caching : " + e.getMessage());
               this.cachedPC.setLoadedField(fieldNumber, false);
            }
         } else {
            this.cachedPC.setLoadedField(fieldNumber, false);
         }
      } else {
         this.cachedPC.setFieldValue(fieldNumber, value);
      }
   }

   protected void processField(int fieldNumber, AbstractMemberMetaData mmd, Object value) {
      RelationType relType = mmd.getRelationType(this.ec.getClassLoaderResolver());
      if (relType == RelationType.NONE) {
         Object unwrappedValue = value instanceof SCO ? ((SCO)value).getValue() : value;
         this.cachedPC.setFieldValue(fieldNumber, L2CacheRetrieveFieldManager.copyValue(unwrappedValue));
      } else if (!mmd.isSerialized() && !MetaDataUtils.isMemberEmbedded(mmd, relType, this.ec.getClassLoaderResolver(), this.ec.getMetaDataManager())) {
         this.cachedPC.setFieldValue(fieldNumber, this.getCacheableIdForId(this.ec.getApiAdapter(), value));
      } else {
         if (this.ec.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.cache.level2.cacheEmbedded")) {
            ObjectProvider valueOP = this.ec.findObjectProvider(value);
            int[] loadedFields = valueOP.getLoadedFieldNumbers();
            CachedPC valueCachedPC = new CachedPC(value.getClass(), valueOP.getLoadedFields(), (Object)null);
            if (loadedFields != null && loadedFields.length > 0) {
               valueOP.provideFields(loadedFields, new L2CachePopulateFieldManager(valueOP, valueCachedPC));
            }

            this.cachedPC.setFieldValue(fieldNumber, valueCachedPC);
         } else {
            this.cachedPC.setLoadedField(fieldNumber, false);
         }

      }
   }

   private Object getCacheableIdForId(ApiAdapter api, Object pc) {
      if (pc == null) {
         return null;
      } else {
         Object id = api.getIdForObject(pc);
         return !IdentityUtils.isDatastoreIdentity(id) && !IdentityUtils.isSingleFieldIdentity(id) ? new CachedPC.CachedId(pc.getClass().getName(), id) : id;
      }
   }
}

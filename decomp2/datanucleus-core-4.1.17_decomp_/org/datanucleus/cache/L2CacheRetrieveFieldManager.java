package org.datanucleus.cache;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.fieldmanager.AbstractFieldManager;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.NucleusLogger;

public class L2CacheRetrieveFieldManager extends AbstractFieldManager {
   ObjectProvider op;
   ExecutionContext ec;
   CachedPC cachedPC;
   List fieldsNotLoaded = null;

   public L2CacheRetrieveFieldManager(ObjectProvider op, CachedPC cachedpc) {
      this.op = op;
      this.ec = op.getExecutionContext();
      this.cachedPC = cachedpc;
   }

   public int[] getFieldsNotLoaded() {
      if (this.fieldsNotLoaded == null) {
         return null;
      } else {
         int[] flds = new int[this.fieldsNotLoaded.size()];
         int i = 0;

         for(Integer fldNum : this.fieldsNotLoaded) {
            flds[i++] = fldNum;
         }

         return flds;
      }
   }

   public boolean fetchBooleanField(int fieldNumber) {
      return (Boolean)this.cachedPC.getFieldValue(fieldNumber);
   }

   public byte fetchByteField(int fieldNumber) {
      return (Byte)this.cachedPC.getFieldValue(fieldNumber);
   }

   public char fetchCharField(int fieldNumber) {
      return (Character)this.cachedPC.getFieldValue(fieldNumber);
   }

   public double fetchDoubleField(int fieldNumber) {
      return (Double)this.cachedPC.getFieldValue(fieldNumber);
   }

   public float fetchFloatField(int fieldNumber) {
      return (Float)this.cachedPC.getFieldValue(fieldNumber);
   }

   public int fetchIntField(int fieldNumber) {
      return (Integer)this.cachedPC.getFieldValue(fieldNumber);
   }

   public long fetchLongField(int fieldNumber) {
      return (Long)this.cachedPC.getFieldValue(fieldNumber);
   }

   public short fetchShortField(int fieldNumber) {
      return (Short)this.cachedPC.getFieldValue(fieldNumber);
   }

   public String fetchStringField(int fieldNumber) {
      return (String)this.cachedPC.getFieldValue(fieldNumber);
   }

   public Object fetchObjectField(int fieldNumber) {
      Object value = this.cachedPC.getFieldValue(fieldNumber);
      if (value == null) {
         return null;
      } else {
         AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         return mmd.hasContainer() ? this.processContainer(fieldNumber, mmd, value) : this.processField(fieldNumber, mmd, value);
      }
   }

   protected Object processContainer(int fieldNumber, AbstractMemberMetaData mmd, Object value) {
      RelationType relType = mmd.getRelationType(this.ec.getClassLoaderResolver());
      if (mmd.hasCollection()) {
         if (RelationType.isRelationMultiValued(relType)) {
            Collection coll = (Collection)value;

            try {
               Collection fieldColl = (Collection)coll.getClass().newInstance();

               for(Object cachedId : coll) {
                  if (cachedId != null) {
                     fieldColl.add(this.getObjectFromCacheableId(cachedId));
                  } else {
                     fieldColl.add((Object)null);
                  }
               }

               return SCOUtils.wrapSCOField(this.op, fieldNumber, fieldColl, true);
            } catch (Exception e) {
               if (this.fieldsNotLoaded == null) {
                  this.fieldsNotLoaded = new ArrayList();
               }

               this.fieldsNotLoaded.add(fieldNumber);
               NucleusLogger.CACHE.error("Exception thrown creating value for field " + mmd.getFullFieldName() + " of type " + value.getClass().getName(), e);
               return null;
            }
         }
      } else if (mmd.hasMap()) {
         if (RelationType.isRelationMultiValued(relType)) {
            Map map = (Map)value;

            try {
               Map fieldMap = (Map)map.getClass().newInstance();

               for(Map.Entry entry : map.entrySet()) {
                  Object mapKey = null;
                  if (mmd.getMap().keyIsPersistent()) {
                     mapKey = this.getObjectFromCacheableId(entry.getKey());
                  } else {
                     mapKey = entry.getKey();
                  }

                  Object mapValue = null;
                  Object mapValueId = entry.getValue();
                  if (mapValueId != null) {
                     if (mmd.getMap().valueIsPersistent()) {
                        mapValue = this.getObjectFromCacheableId(entry.getValue());
                     } else {
                        mapValue = entry.getValue();
                     }
                  }

                  fieldMap.put(mapKey, mapValue);
               }

               return SCOUtils.wrapSCOField(this.op, fieldNumber, fieldMap, true);
            } catch (Exception e) {
               if (this.fieldsNotLoaded == null) {
                  this.fieldsNotLoaded = new ArrayList();
               }

               this.fieldsNotLoaded.add(fieldNumber);
               NucleusLogger.CACHE.error("Exception thrown creating value for field " + mmd.getFullFieldName() + " of type " + value.getClass().getName(), e);
               return null;
            }
         }
      } else if (mmd.hasArray() && RelationType.isRelationMultiValued(relType)) {
         try {
            Object[] elementOIDs = value;
            Class componentType = mmd.getType().getComponentType();
            Object fieldArr = Array.newInstance(componentType, elementOIDs.length);
            boolean persistableElement = this.ec.getApiAdapter().isPersistable(componentType);

            for(int i = 0; i < elementOIDs.length; ++i) {
               Object element = null;
               if (elementOIDs[i] != null) {
                  if (!componentType.isInterface() && !persistableElement && componentType != Object.class) {
                     element = elementOIDs[i];
                  } else {
                     element = this.getObjectFromCacheableId(elementOIDs[i]);
                  }
               }

               Array.set(fieldArr, i, element);
            }

            return fieldArr;
         } catch (NucleusException ne) {
            if (this.fieldsNotLoaded == null) {
               this.fieldsNotLoaded = new ArrayList();
            }

            this.fieldsNotLoaded.add(fieldNumber);
            NucleusLogger.CACHE.error("Exception thrown trying to find element of array while getting object with id " + this.op.getInternalObjectId() + " from the L2 cache", ne);
            return null;
         }
      }

      return value;
   }

   protected Object processField(int fieldNumber, AbstractMemberMetaData mmd, Object value) {
      RelationType relType = mmd.getRelationType(this.ec.getClassLoaderResolver());
      if (relType == RelationType.NONE) {
         Object fieldValue = copyValue(value);
         boolean[] mutables = mmd.getAbstractClassMetaData().getSCOMutableMemberFlags();
         return mutables[fieldNumber] ? SCOUtils.wrapSCOField(this.op, fieldNumber, fieldValue, true) : fieldValue;
      } else if ((mmd.isSerialized() || MetaDataUtils.isMemberEmbedded(mmd, relType, this.ec.getClassLoaderResolver(), this.ec.getMetaDataManager())) && this.ec.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.cache.level2.cacheEmbedded") && value instanceof CachedPC) {
         CachedPC valueCachedPC = (CachedPC)value;
         AbstractClassMetaData cmd = this.ec.getMetaDataManager().getMetaDataForClass(valueCachedPC.getObjectClass(), this.ec.getClassLoaderResolver());
         ObjectProvider valueOP = this.ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(this.ec, cmd, this.op, mmd.getAbsoluteFieldNumber());
         int[] fieldsToLoad = ClassUtils.getFlagsSetTo(valueCachedPC.getLoadedFields(), cmd.getAllMemberPositions(), true);
         if (fieldsToLoad != null && fieldsToLoad.length > 0) {
            valueOP.replaceFields(fieldsToLoad, new L2CacheRetrieveFieldManager(valueOP, valueCachedPC));
         }

         return valueOP.getObject();
      } else {
         try {
            return this.getObjectFromCacheableId(value);
         } catch (NucleusObjectNotFoundException var9) {
            if (this.fieldsNotLoaded == null) {
               this.fieldsNotLoaded = new ArrayList();
            }

            this.fieldsNotLoaded.add(fieldNumber);
            return null;
         }
      }
   }

   static Object copyValue(Object scoValue) {
      if (scoValue == null) {
         return null;
      } else if (scoValue instanceof StringBuffer) {
         return new StringBuffer(((StringBuffer)scoValue).toString());
      } else if (scoValue instanceof StringBuilder) {
         return new StringBuilder(((StringBuilder)scoValue).toString());
      } else if (scoValue instanceof Date) {
         return ((Date)scoValue).clone();
      } else {
         return scoValue instanceof Calendar ? ((Calendar)scoValue).clone() : scoValue;
      }
   }

   private Object getObjectFromCacheableId(Object cachedId) {
      Object pcId = null;
      String pcClassName = null;
      if (cachedId instanceof CachedPC.CachedId) {
         CachedPC.CachedId cId = (CachedPC.CachedId)cachedId;
         pcId = cId.getId();
         pcClassName = cId.getClassName();
      } else {
         pcId = cachedId;
         pcClassName = IdentityUtils.getTargetClassNameForIdentitySimple(cachedId);
      }

      Class pcCls = this.ec.getClassLoaderResolver().classForName(pcClassName);
      return this.ec.findObject(pcId, (FieldValues)null, pcCls, false, false);
   }
}

package org.datanucleus.store.types;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.SetStore;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class SCOUtils {
   public static Object unwrapSCOField(ObjectProvider ownerOP, int memberNumber, SCO sco) {
      if (sco == null) {
         return null;
      } else {
         Object unwrappedValue = sco.getValue();
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            AbstractMemberMetaData mmd = ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(memberNumber);
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("026030", StringUtils.toJVMIDString(ownerOP.getObject()), IdentityUtils.getPersistableIdentityForId(ownerOP.getInternalObjectId()), mmd.getName()));
         }

         ownerOP.replaceField(memberNumber, unwrappedValue);
         return unwrappedValue;
      }
   }

   public static Object wrapSCOField(ObjectProvider ownerOP, int memberNumber, Object value, boolean replaceFieldIfChanged) {
      if (value != null && ownerOP.getClassMetaData().getSCOMutableMemberFlags()[memberNumber]) {
         if (value instanceof SCO && ownerOP.getObject() == ((SCO)value).getOwner()) {
            return value;
         } else {
            AbstractMemberMetaData mmd = ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(memberNumber);
            if (replaceFieldIfChanged && NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("026029", StringUtils.toJVMIDString(ownerOP.getObject()), ownerOP.getExecutionContext() != null ? IdentityUtils.getPersistableIdentityForId(ownerOP.getInternalObjectId()) : ownerOP.getInternalObjectId(), mmd.getName()));
            }

            return newSCOInstance(ownerOP, mmd, value.getClass(), value, replaceFieldIfChanged);
         }
      } else {
         return value;
      }
   }

   public static Object wrapAndReplaceSCOField(ObjectProvider ownerOP, int memberNumber, Object newValue, Object oldValue, boolean replaceFieldIfChanged) {
      if (newValue != null && ownerOP.getClassMetaData().getSCOMutableMemberFlags()[memberNumber]) {
         if (newValue instanceof SCO && ownerOP.getObject() == ((SCO)newValue).getOwner()) {
            return newValue;
         } else {
            AbstractMemberMetaData mmd = ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(memberNumber);
            if (replaceFieldIfChanged && NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("026029", StringUtils.toJVMIDString(ownerOP.getObject()), ownerOP.getExecutionContext() != null ? IdentityUtils.getPersistableIdentityForId(ownerOP.getInternalObjectId()) : ownerOP.getInternalObjectId(), mmd.getName()));
            }

            if (newValue != null && newValue instanceof SCO) {
               if (replaceFieldIfChanged) {
                  ownerOP.replaceField(mmd.getAbsoluteFieldNumber(), newValue);
               }

               return newValue;
            } else {
               Class requiredType = newValue != null ? newValue.getClass() : (oldValue != null ? oldValue.getClass() : null);
               SCO sco = createSCOInstance(ownerOP, mmd, requiredType);
               if (replaceFieldIfChanged) {
                  ownerOP.replaceField(mmd.getAbsoluteFieldNumber(), sco);
               }

               sco.initialise(newValue, oldValue);
               return sco;
            }
         }
      } else {
         return newValue;
      }
   }

   public static SCO newSCOInstance(ObjectProvider ownerOP, AbstractMemberMetaData mmd, Class instantiatedType, Object value, boolean replaceField) {
      if (value != null && value instanceof SCO) {
         if (replaceField) {
            ownerOP.replaceField(mmd.getAbsoluteFieldNumber(), value);
         }

         return (SCO)value;
      } else {
         SCO sco = createSCOInstance(ownerOP, mmd, value != null ? value.getClass() : instantiatedType);
         if (replaceField) {
            ownerOP.replaceField(mmd.getAbsoluteFieldNumber(), sco);
         }

         if (value != null) {
            sco.initialise(value);
         } else {
            sco.initialise();
         }

         return sco;
      }
   }

   private static SCO createSCOInstance(ObjectProvider ownerOP, AbstractMemberMetaData mmd, Class instantiatedType) {
      String typeName = instantiatedType != null ? instantiatedType.getName() : mmd.getTypeName();
      StoreManager storeMgr = ownerOP.getExecutionContext().getStoreManager();
      boolean backedWrapper = storeMgr.useBackedSCOWrapperForMember(mmd, ownerOP.getExecutionContext());
      TypeManager typeMgr = ownerOP.getExecutionContext().getNucleusContext().getTypeManager();
      Class wrapperType = null;
      if (mmd.isSerialized()) {
         backedWrapper = false;
      }

      if (backedWrapper) {
         wrapperType = getBackedWrapperTypeForType(mmd.getType(), instantiatedType, typeName, typeMgr);
      } else {
         wrapperType = getSimpleWrapperTypeForType(mmd.getType(), instantiatedType, typeName, typeMgr);
      }

      if (wrapperType == null) {
         throw new NucleusUserException(Localiser.msg("023011", mmd.getTypeName(), typeName, mmd.getFullFieldName()));
      } else {
         try {
            return (SCO)ClassUtils.newInstance(wrapperType, new Class[]{ObjectProvider.class, AbstractMemberMetaData.class}, new Object[]{ownerOP, mmd});
         } catch (UnsupportedOperationException uoe) {
            if (backedWrapper) {
               NucleusLogger.PERSISTENCE.warn("Creation of backed wrapper for " + mmd.getFullFieldName() + " unsupported, so trying simple wrapper");
               wrapperType = getSimpleWrapperTypeForType(mmd.getType(), instantiatedType, typeName, typeMgr);
               return (SCO)ClassUtils.newInstance(wrapperType, new Class[]{ObjectProvider.class, AbstractMemberMetaData.class}, new Object[]{ownerOP, mmd});
            } else {
               throw uoe;
            }
         }
      }
   }

   private static Class getBackedWrapperTypeForType(Class declaredType, Class instantiatedType, String typeName, TypeManager typeMgr) {
      Class wrapperType = typeMgr.getWrappedTypeBackedForType(typeName);
      if (wrapperType == null) {
         if (instantiatedType != null) {
            wrapperType = typeMgr.getWrappedTypeBackedForType(instantiatedType.getName());
         }

         if (wrapperType == null) {
            wrapperType = typeMgr.getWrappedTypeBackedForType(declaredType.getName());
         }
      }

      return wrapperType;
   }

   private static Class getSimpleWrapperTypeForType(Class declaredType, Class instantiatedType, String typeName, TypeManager typeMgr) {
      Class wrapperType = typeMgr.getWrapperTypeForType(typeName);
      if (wrapperType == null) {
         if (instantiatedType != null) {
            wrapperType = typeMgr.getWrapperTypeForType(instantiatedType.getName());
         }

         if (wrapperType == null) {
            wrapperType = typeMgr.getWrapperTypeForType(declaredType.getName());
         }
      }

      return wrapperType;
   }

   public static String getContainerInfoMessage(ObjectProvider ownerOP, String fieldName, SCOContainer cont, boolean useCache, boolean allowNulls, boolean lazyLoading) {
      String msg = Localiser.msg("023004", ownerOP.getObjectAsPrintable(), fieldName, cont.getClass().getName(), "[cache-values=" + useCache + ", lazy-loading=" + lazyLoading + ", allow-nulls=" + allowNulls + "]");
      return msg;
   }

   public static String getSCOWrapperOptionsMessage(boolean useCache, boolean queued, boolean allowNulls, boolean lazyLoading) {
      StringBuilder str = new StringBuilder();
      if (useCache) {
         str.append("cached");
      }

      if (lazyLoading) {
         if (str.length() > 0) {
            str.append(",");
         }

         str.append("lazy-loaded");
      }

      if (queued) {
         if (str.length() > 0) {
            str.append(",");
         }

         str.append("queued");
      }

      if (allowNulls) {
         if (str.length() > 0) {
            str.append(",");
         }

         str.append("allowNulls");
      }

      return str.toString();
   }

   public static boolean allowNullsInContainer(boolean defaultValue, AbstractMemberMetaData mmd) {
      if (mmd.getContainer() == null) {
         return defaultValue;
      } else if (Boolean.TRUE.equals(mmd.getContainer().allowNulls())) {
         return true;
      } else {
         return Boolean.FALSE.equals(mmd.getContainer().allowNulls()) ? false : defaultValue;
      }
   }

   public static boolean useContainerCache(ObjectProvider ownerOP, AbstractMemberMetaData mmd) {
      if (ownerOP == null) {
         return false;
      } else {
         boolean useCache = ownerOP.getExecutionContext().getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.cache.collections");
         if (ownerOP.getExecutionContext().getBooleanProperty("datanucleus.cache.collections") != null) {
            useCache = ownerOP.getExecutionContext().getBooleanProperty("datanucleus.cache.collections");
         }

         if (mmd.getOrderMetaData() != null && !mmd.getOrderMetaData().isIndexedList()) {
            useCache = true;
         } else if (mmd.getContainer() != null && mmd.getContainer().hasExtension("cache")) {
            useCache = Boolean.parseBoolean(mmd.getContainer().getValueForExtension("cache"));
         }

         return useCache;
      }
   }

   public static boolean useCachedLazyLoading(ObjectProvider ownerOP, AbstractMemberMetaData mmd) {
      if (ownerOP == null) {
         return false;
      } else {
         boolean lazy = false;
         AbstractClassMetaData cmd = ownerOP.getClassMetaData();
         Boolean lazyCollections = ownerOP.getExecutionContext().getNucleusContext().getConfiguration().getBooleanObjectProperty("datanucleus.cache.collections.lazy");
         if (lazyCollections != null) {
            lazy = lazyCollections;
         } else if (mmd.getContainer() != null && mmd.getContainer().hasExtension("cache-lazy-loading")) {
            lazy = Boolean.parseBoolean(mmd.getContainer().getValueForExtension("cache-lazy-loading"));
         } else {
            boolean inFP = false;
            int[] fpFields = ownerOP.getExecutionContext().getFetchPlan().getFetchPlanForClass(cmd).getMemberNumbers();
            int fieldNo = mmd.getAbsoluteFieldNumber();
            if (fpFields != null && fpFields.length > 0) {
               for(int i = 0; i < fpFields.length; ++i) {
                  if (fpFields[i] == fieldNo) {
                     inFP = true;
                     break;
                  }
               }
            }

            lazy = !inFP;
         }

         return lazy;
      }
   }

   public static boolean collectionHasElementsWithoutIdentity(AbstractMemberMetaData mmd) {
      boolean elementsWithoutIdentity = false;
      if (mmd.isSerialized()) {
         elementsWithoutIdentity = true;
      } else if (mmd.getElementMetaData() != null && mmd.getElementMetaData().getEmbeddedMetaData() != null && mmd.getJoinMetaData() != null) {
         elementsWithoutIdentity = true;
      } else if (mmd.getCollection() != null && mmd.getCollection().isEmbeddedElement()) {
         elementsWithoutIdentity = true;
      }

      return elementsWithoutIdentity;
   }

   public static boolean mapHasKeysWithoutIdentity(AbstractMemberMetaData fmd) {
      boolean keysWithoutIdentity = false;
      if (fmd.isSerialized()) {
         keysWithoutIdentity = true;
      } else if (fmd.getKeyMetaData() != null && fmd.getKeyMetaData().getEmbeddedMetaData() != null && fmd.getJoinMetaData() != null) {
         keysWithoutIdentity = true;
      } else if (fmd.getMap() != null && fmd.getMap().isEmbeddedKey()) {
         keysWithoutIdentity = true;
      }

      return keysWithoutIdentity;
   }

   public static boolean mapHasValuesWithoutIdentity(AbstractMemberMetaData fmd) {
      boolean valuesWithoutIdentity = false;
      if (fmd.isSerialized()) {
         valuesWithoutIdentity = true;
      } else if (fmd.getValueMetaData() != null && fmd.getValueMetaData().getEmbeddedMetaData() != null && fmd.getJoinMetaData() != null) {
         valuesWithoutIdentity = true;
      } else if (fmd.getMap() != null && fmd.getMap().isEmbeddedValue()) {
         valuesWithoutIdentity = true;
      }

      return valuesWithoutIdentity;
   }

   public static boolean collectionHasSerialisedElements(AbstractMemberMetaData fmd) {
      boolean serialised = fmd.isSerialized();
      if (fmd.getCollection() != null && fmd.getCollection().isEmbeddedElement() && fmd.getJoinMetaData() == null) {
         serialised = true;
      }

      return serialised;
   }

   public static boolean arrayIsStoredInSingleColumn(AbstractMemberMetaData fmd, MetaDataManager mmgr) {
      boolean singleColumn = fmd.isSerialized();
      if (!singleColumn && fmd.getArray() != null && fmd.getJoinMetaData() == null) {
         if (fmd.getArray().isEmbeddedElement()) {
            singleColumn = true;
         }

         Class elementClass = fmd.getType().getComponentType();
         ApiAdapter api = mmgr.getApiAdapter();
         if (!elementClass.isInterface() && !api.isPersistable(elementClass)) {
            singleColumn = true;
         }
      }

      return singleColumn;
   }

   public static boolean mapHasSerialisedKeysAndValues(AbstractMemberMetaData fmd) {
      boolean inverseKeyField = false;
      if (fmd.getKeyMetaData() != null && fmd.getKeyMetaData().getMappedBy() != null) {
         inverseKeyField = true;
      }

      boolean inverseValueField = false;
      if (fmd.getValueMetaData() != null && fmd.getValueMetaData().getMappedBy() != null) {
         inverseValueField = true;
      }

      boolean serialised = fmd.isSerialized();
      if (fmd.getMap() != null && fmd.getJoinMetaData() == null && fmd.getMap().isEmbeddedKey() && fmd.getMap().isEmbeddedValue() && !inverseKeyField && !inverseValueField) {
         serialised = true;
      }

      return serialised;
   }

   public static boolean attachCopyElements(ObjectProvider ownerOP, Collection scoColl, Collection detachedElements, boolean elementsWithoutId) {
      boolean updated = false;
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();
      Iterator scoCollIter = scoColl.iterator();

      while(scoCollIter.hasNext()) {
         Object currentElem = scoCollIter.next();
         Object currentElemId = api.getIdForObject(currentElem);
         Iterator desiredIter = detachedElements.iterator();
         boolean contained = false;
         if (elementsWithoutId) {
            contained = detachedElements.contains(currentElem);
         } else {
            while(desiredIter.hasNext()) {
               Object desiredElem = desiredIter.next();
               if (currentElemId != null) {
                  if (currentElemId.equals(api.getIdForObject(desiredElem))) {
                     contained = true;
                     break;
                  }
               } else if (currentElem == desiredElem) {
                  contained = true;
                  break;
               }
            }
         }

         if (!contained) {
            scoCollIter.remove();
            updated = true;
         }
      }

      for(Object detachedElement : detachedElements) {
         if (elementsWithoutId) {
            if (!scoColl.contains(detachedElement)) {
               scoColl.add(detachedElement);
               updated = true;
            }
         } else {
            Object detachedElemId = api.getIdForObject(detachedElement);
            scoCollIter = scoColl.iterator();
            boolean contained = false;

            while(scoCollIter.hasNext()) {
               Object scoCollElem = scoCollIter.next();
               Object scoCollElemId = api.getIdForObject(scoCollElem);
               if (scoCollElemId != null && scoCollElemId.equals(detachedElemId)) {
                  contained = true;
                  break;
               }
            }

            if (!contained) {
               scoColl.add(detachedElement);
               updated = true;
            } else {
               ownerOP.getExecutionContext().attachObjectCopy(ownerOP, detachedElement, false);
            }
         }
      }

      return updated;
   }

   public static void attachCopyForCollection(ObjectProvider ownerOP, Object[] detachedElements, Collection attached, boolean elementsWithoutIdentity) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(int i = 0; i < detachedElements.length; ++i) {
         if (api.isPersistable(detachedElements[i]) && api.isDetachable(detachedElements[i])) {
            attached.add(ownerOP.getExecutionContext().attachObjectCopy(ownerOP, detachedElements[i], elementsWithoutIdentity));
         } else {
            attached.add(detachedElements[i]);
         }
      }

   }

   public static void attachCopyForMap(ObjectProvider ownerOP, Set detachedEntries, Map attached, boolean keysWithoutIdentity, boolean valuesWithoutIdentity) {
      Iterator iter = detachedEntries.iterator();

      Object val;
      Object key;
      for(ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter(); iter.hasNext(); attached.put(key, val)) {
         Map.Entry entry = (Map.Entry)iter.next();
         val = entry.getValue();
         key = entry.getKey();
         if (api.isPersistable(val) && api.isDetachable(val)) {
            val = ownerOP.getExecutionContext().attachObjectCopy(ownerOP, val, valuesWithoutIdentity);
         }

         if (api.isPersistable(key) && api.isDetachable(key)) {
            key = ownerOP.getExecutionContext().attachObjectCopy(ownerOP, key, keysWithoutIdentity);
         }
      }

   }

   public static boolean updateCollectionWithCollection(ApiAdapter api, Collection coll, Collection elements) {
      boolean updated = false;
      Collection unwrapped = coll;
      if (coll instanceof SCO) {
         unwrapped = (Collection)((SCO)coll).getValue();
      }

      Collection unwrappedCopy = new HashSet(unwrapped);

      for(Object elem : elements) {
         if (api.isPersistable(elem) && !api.isPersistent(elem)) {
            coll.add(elem);
            updated = true;
         } else if (!unwrapped.contains(elem)) {
            coll.add(elem);
            updated = true;
         }
      }

      for(Object elem : unwrappedCopy) {
         if (!elements.contains(elem)) {
            coll.remove(elem);
            updated = true;
         }
      }

      return updated;
   }

   public static boolean updateListWithListElements(List list, List elements) {
      boolean updated = false;
      ArrayList newCopy = new ArrayList(elements);
      Iterator attachedIter = list.iterator();

      while(attachedIter.hasNext()) {
         Object attachedElement = attachedIter.next();
         if (!newCopy.remove(attachedElement)) {
            attachedIter.remove();
            updated = true;
         }
      }

      ArrayList oldCopy = new ArrayList(list);

      for(Object element : elements) {
         if (!oldCopy.remove(element)) {
            list.add(element);
            updated = true;
         }
      }

      Iterator var12 = elements.iterator();

      for(int position = 0; var12.hasNext(); ++position) {
         Object element = var12.next();
         Object currentElement = list.get(position);
         boolean updatePosition = false;
         if ((element != null || currentElement == null) && (element == null || currentElement != null)) {
            if (element != null && currentElement != null && !currentElement.equals(element)) {
               updatePosition = true;
            }
         } else {
            updatePosition = true;
         }

         if (updatePosition) {
            ((SCOList)list).set(position, element, false);
            updated = true;
         }
      }

      return updated;
   }

   public static boolean updateMapWithMapKeysValues(ApiAdapter api, Map map, Map keysValues) {
      boolean updated = false;
      Map copy = new HashMap(map);

      for(Map.Entry entry : keysValues.entrySet()) {
         Object key = entry.getKey();
         if (api.isPersistable(key) && !api.isPersistent(key)) {
            map.put(key, keysValues.get(key));
            updated = true;
         } else if (!map.containsKey(key)) {
            map.put(key, keysValues.get(key));
            updated = true;
         } else {
            Object value = entry.getValue();
            Object oldValue = map.get(key);
            if (api.isPersistable(value) && !api.isPersistent(value)) {
               map.put(key, value);
            } else if (api.isPersistable(value) && api.getIdForObject(value) != api.getIdForObject(oldValue)) {
               map.put(key, value);
            } else if (oldValue == null && value != null || oldValue != null && !oldValue.equals(value)) {
               map.put(key, value);
            }
         }
      }

      for(Map.Entry entry : copy.entrySet()) {
         Object key = entry.getKey();
         if (!keysValues.containsKey(key)) {
            map.remove(key);
            updated = true;
         }
      }

      return updated;
   }

   public static void populateMapDelegateWithStoreData(Map delegate, MapStore store, ObjectProvider ownerOP) {
      Set keys = new HashSet();
      if (!store.keysAreEmbedded() && !store.keysAreSerialised()) {
         SetStore keystore = store.keySetStore();
         Iterator keyIter = keystore.iterator(ownerOP);

         while(keyIter.hasNext()) {
            keys.add(keyIter.next());
         }
      }

      List values = new ArrayList();
      if (!store.valuesAreEmbedded() && !store.valuesAreSerialised()) {
         CollectionStore valuestore = store.valueCollectionStore();
         Iterator valueIter = valuestore.iterator(ownerOP);

         while(valueIter.hasNext()) {
            values.add(valueIter.next());
         }
      }

      SetStore entries = store.entrySetStore();
      Iterator entryIter = entries.iterator(ownerOP);

      while(entryIter.hasNext()) {
         Map.Entry entry = (Map.Entry)entryIter.next();
         Object key = entry.getKey();
         Object value = entry.getValue();
         delegate.put(key, value);
      }

      if (!store.keysAreEmbedded() && !store.keysAreSerialised() && delegate.size() != keys.size()) {
         NucleusLogger.DATASTORE_RETRIEVE.warn("The number of Map key objects (" + keys.size() + ") was different to the number of entries (" + delegate.size() + "). Likely there is a bug in your datastore");
      }

      if (!store.valuesAreEmbedded() && !store.valuesAreSerialised() && delegate.size() != values.size()) {
         NucleusLogger.DATASTORE_RETRIEVE.warn("The number of Map value objects (" + values.size() + ") was different to the number of entries (" + delegate.size() + "). Likely there is a bug in your datastore");
      }

      keys.clear();
      values.clear();
   }

   public static Object[] toArray(CollectionStore backingStore, ObjectProvider op) {
      Object[] result = new Object[backingStore.size(op)];
      Iterator it = backingStore.iterator(op);

      for(int i = 0; it.hasNext(); ++i) {
         result[i] = it.next();
      }

      return result;
   }

   public static Object[] toArray(CollectionStore backingStore, ObjectProvider op, Object[] a) {
      int size = backingStore.size(op);
      if (a.length < size) {
         a = Array.newInstance(a.getClass().getComponentType(), size);
      }

      Iterator it = backingStore.iterator(op);

      for(int i = 0; i < size; ++i) {
         a[i] = it.next();
      }

      if (a.length > size) {
         a[size] = null;
      }

      return a;
   }

   public static Comparator getComparator(AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      Comparator comparator = null;
      String comparatorName = null;
      if (mmd.hasMap()) {
         if (mmd.hasExtension("comparator-name")) {
            comparatorName = mmd.getValueForExtension("comparator-name");
         } else if (mmd.getMap().hasExtension("comparator-name")) {
            comparatorName = mmd.getMap().getValueForExtension("comparator-name");
         }
      } else if (mmd.hasCollection()) {
         if (mmd.hasExtension("comparator-name")) {
            comparatorName = mmd.getValueForExtension("comparator-name");
         } else if (mmd.getCollection().hasExtension("comparator-name")) {
            comparatorName = mmd.getCollection().getValueForExtension("comparator-name");
         }
      }

      if (comparatorName != null) {
         Class comparatorCls = null;

         try {
            comparatorCls = clr.classForName(comparatorName);
            comparator = (Comparator)ClassUtils.newInstance(comparatorCls, (Class[])null, (Object[])null);
         } catch (NucleusException var6) {
            NucleusLogger.PERSISTENCE.warn(Localiser.msg("023012", mmd.getFullFieldName(), comparatorName));
         }
      }

      return comparator;
   }

   public static void refreshFetchPlanFieldsForCollection(ObjectProvider ownerOP, Object[] elements) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(int i = 0; i < elements.length; ++i) {
         if (api.isPersistable(elements[i])) {
            ownerOP.getExecutionContext().refreshObject(elements[i]);
         }
      }

   }

   public static void refreshFetchPlanFieldsForMap(ObjectProvider ownerOP, Set entries) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(Map.Entry entry : entries) {
         Object val = entry.getValue();
         Object key = entry.getKey();
         if (api.isPersistable(key)) {
            ownerOP.getExecutionContext().refreshObject(key);
         }

         if (api.isPersistable(val)) {
            ownerOP.getExecutionContext().refreshObject(val);
         }
      }

   }

   public static void detachForCollection(ObjectProvider ownerOP, Object[] elements, FetchPlanState state) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(int i = 0; i < elements.length; ++i) {
         if (api.isPersistable(elements[i])) {
            ownerOP.getExecutionContext().detachObject(elements[i], state);
         }
      }

   }

   public static void detachCopyForCollection(ObjectProvider ownerOP, Object[] elements, FetchPlanState state, Collection detached) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(int i = 0; i < elements.length; ++i) {
         if (elements[i] == null) {
            detached.add((Object)null);
         } else {
            Object object = elements[i];
            if (api.isPersistable(object)) {
               detached.add(ownerOP.getExecutionContext().detachObjectCopy(object, state));
            } else {
               detached.add(object);
            }
         }
      }

   }

   public static void attachForCollection(ObjectProvider ownerOP, Object[] elements, boolean elementsWithoutIdentity) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      ApiAdapter api = ec.getApiAdapter();

      for(int i = 0; i < elements.length; ++i) {
         if (api.isPersistable(elements[i])) {
            Object attached = ec.getAttachedObjectForId(api.getIdForObject(elements[i]));
            if (attached == null) {
               ec.attachObject(ownerOP, elements[i], elementsWithoutIdentity);
            }
         }
      }

   }

   public static void detachForMap(ObjectProvider ownerOP, Set entries, FetchPlanState state) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(Map.Entry entry : entries) {
         Object val = entry.getValue();
         Object key = entry.getKey();
         if (api.isPersistable(key)) {
            ownerOP.getExecutionContext().detachObject(key, state);
         }

         if (api.isPersistable(val)) {
            ownerOP.getExecutionContext().detachObject(val, state);
         }
      }

   }

   public static void detachCopyForMap(ObjectProvider ownerOP, Set entries, FetchPlanState state, Map detached) {
      ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();

      for(Map.Entry entry : entries) {
         Object val = entry.getValue();
         Object key = entry.getKey();
         if (api.isPersistable(val)) {
            val = ownerOP.getExecutionContext().detachObjectCopy(val, state);
         }

         if (api.isPersistable(key)) {
            key = ownerOP.getExecutionContext().detachObjectCopy(key, state);
         }

         detached.put(key, val);
      }

   }

   public static void attachForMap(ObjectProvider ownerOP, Set entries, boolean keysWithoutIdentity, boolean valuesWithoutIdentity) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      ApiAdapter api = ec.getApiAdapter();

      for(Map.Entry entry : entries) {
         Object val = entry.getValue();
         Object key = entry.getKey();
         if (api.isPersistable(key)) {
            Object attached = ec.getAttachedObjectForId(api.getIdForObject(key));
            if (attached == null) {
               ownerOP.getExecutionContext().attachObject(ownerOP, key, keysWithoutIdentity);
            }
         }

         if (api.isPersistable(val)) {
            Object attached = ec.getAttachedObjectForId(api.getIdForObject(val));
            if (attached == null) {
               ownerOP.getExecutionContext().attachObject(ownerOP, val, valuesWithoutIdentity);
            }
         }
      }

   }

   public static boolean validateObjectForWriting(ExecutionContext ec, Object object, FieldValues fieldValues) {
      boolean persisted = false;
      ApiAdapter api = ec.getApiAdapter();
      if (api.isPersistable(object)) {
         ExecutionContext objectEC = api.getExecutionContext(object);
         if (objectEC != null && ec != objectEC) {
            throw new NucleusUserException(Localiser.msg("023009", StringUtils.toJVMIDString(object)), api.getIdForObject(object));
         }

         if (!api.isPersistent(object)) {
            boolean exists = false;
            if (api.isDetached(object)) {
               if (ec.getBooleanProperty("datanucleus.attachSameDatastore")) {
                  exists = true;
               } else {
                  try {
                     Object obj = ec.findObject(api.getIdForObject(object), true, false, object.getClass().getName());
                     if (obj != null) {
                        ObjectProvider objSM = ec.findObjectProvider(obj);
                        if (objSM != null) {
                           ec.evictFromTransaction(objSM);
                        }
                     }

                     exists = true;
                  } catch (NucleusObjectNotFoundException var9) {
                     exists = false;
                  }
               }
            }

            if (!exists) {
               ec.persistObjectInternal(object, fieldValues, 0);
               persisted = true;
            }
         } else {
            ObjectProvider objectSM = ec.findObjectProvider(object);
            if (objectSM.isWaitingToBeFlushedToDatastore()) {
               if (fieldValues != null) {
                  objectSM.loadFieldValues(fieldValues);
               }

               objectSM.flush();
               persisted = true;
            }
         }
      }

      return persisted;
   }

   public static boolean isListBased(Class type) {
      if (type == null) {
         return false;
      } else if (List.class.isAssignableFrom(type)) {
         return true;
      } else {
         return Queue.class.isAssignableFrom(type);
      }
   }

   public static Class getContainerInstanceType(Class declaredType, Boolean ordered) {
      if (declaredType.isInterface()) {
         if (SortedSet.class.isAssignableFrom(declaredType)) {
            return TreeSet.class;
         } else if (SortedMap.class.isAssignableFrom(declaredType)) {
            return TreeMap.class;
         } else if (List.class.isAssignableFrom(declaredType)) {
            return ArrayList.class;
         } else if (Set.class.isAssignableFrom(declaredType)) {
            return HashSet.class;
         } else if (Map.class.isAssignableFrom(declaredType)) {
            return HashMap.class;
         } else {
            return ordered ? ArrayList.class : HashSet.class;
         }
      } else {
         return declaredType;
      }
   }

   public static boolean detachAsWrapped(ObjectProvider ownerOP) {
      return ownerOP.getExecutionContext().getBooleanProperty("datanucleus.detachAsWrapped");
   }

   public static boolean useQueuedUpdate(ObjectProvider op) {
      return op != null && op.getExecutionContext().operationQueueIsActive();
   }

   public static boolean hasDependentElement(AbstractMemberMetaData mmd) {
      return !collectionHasElementsWithoutIdentity(mmd) && mmd.getCollection() != null && mmd.getCollection().isDependentElement();
   }

   public static boolean hasDependentKey(AbstractMemberMetaData mmd) {
      return !mapHasKeysWithoutIdentity(mmd) && mmd.getMap() != null && mmd.getMap().isDependentKey();
   }

   public static boolean hasDependentValue(AbstractMemberMetaData mmd) {
      return !mapHasValuesWithoutIdentity(mmd) && mmd.getMap() != null && mmd.getMap().isDependentValue();
   }

   public static boolean collectionsAreEqual(ApiAdapter api, Collection oldColl, Collection newColl) {
      if (oldColl == null && newColl == null) {
         return true;
      } else if (oldColl != null && newColl != null) {
         if (oldColl.size() != newColl.size()) {
            return false;
         } else {
            Iterator oldIter = oldColl.iterator();
            Iterator newIter = newColl.iterator();

            while(oldIter.hasNext()) {
               Object oldVal = oldIter.next();
               Object newVal = newIter.next();
               if (oldVal != null || newVal != null) {
                  if (oldVal == null || newVal == null) {
                     return false;
                  }

                  if (api.isPersistable(oldVal)) {
                     Object oldId = api.getIdForObject(oldVal);
                     Object newId = api.getIdForObject(newVal);
                     if (oldId == null || newId == null) {
                        return false;
                     }

                     if (!oldId.equals(newId)) {
                        return false;
                     }
                  } else if (!oldVal.equals(newVal)) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }
}

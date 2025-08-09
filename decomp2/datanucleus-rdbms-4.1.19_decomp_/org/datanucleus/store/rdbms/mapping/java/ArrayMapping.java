package org.datanucleus.store.rdbms.mapping.java;

import java.lang.reflect.Array;
import java.util.List;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.ReachableObjectNotCascadedException;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.scostore.ArrayStore;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.wrappers.backed.BackedSCO;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ArrayMapping extends AbstractContainerMapping implements MappingCallbacks {
   public Class getJavaType() {
      return this.mmd != null ? this.mmd.getType() : null;
   }

   protected boolean containerIsStoredInSingleColumn() {
      if (super.containerIsStoredInSingleColumn()) {
         return true;
      } else {
         return this.mmd != null && this.mmd.hasArray() && this.mmd.getJoinMetaData() == null && MetaDataUtils.getInstance().arrayStorableAsByteArrayInSingleColumn(this.mmd);
      }
   }

   public void insertPostProcessing(ObjectProvider op) {
   }

   public void postInsert(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      Object value = ownerOP.provideField(this.getAbsoluteFieldNumber());
      if (value != null) {
         if (this.containerIsStoredInSingleColumn()) {
            if (this.mmd.getArray().elementIsPersistent()) {
               Object[] arrElements = value;

               for(Object elem : arrElements) {
                  if (elem != null) {
                     ObjectProvider elemOP = ec.findObjectProvider(elem);
                     if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                        ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                     }
                  }
               }
            }

         } else {
            int arrayLength = Array.getLength(value);
            boolean persistentElements = this.mmd.getRelationType(ec.getClassLoaderResolver()) != RelationType.NONE;
            boolean needsAttaching = false;
            if (persistentElements) {
               Object[] array = value;
               if (!this.mmd.isCascadePersist()) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("007006", new Object[]{this.mmd.getFullFieldName()}));
                  }

                  for(int i = 0; i < arrayLength; ++i) {
                     if (!ec.getApiAdapter().isDetached(array[i]) && !ec.getApiAdapter().isPersistent(array[i])) {
                        throw new ReachableObjectNotCascadedException(this.mmd.getFullFieldName(), array[i]);
                     }
                  }
               } else if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("007007", new Object[]{this.mmd.getFullFieldName()}));
               }

               for(int i = 0; i < arrayLength; ++i) {
                  if (ownerOP.getExecutionContext().getApiAdapter().isDetached(array[i])) {
                     needsAttaching = true;
                     break;
                  }
               }
            }

            if (needsAttaching) {
               SCO collWrapper = this.replaceFieldWithWrapper(ownerOP, (Object)null);
               if (arrayLength > 0) {
                  collWrapper.attachCopy(value);
                  ownerOP.getExecutionContext().flushOperationsForBackingStore(((BackedSCO)collWrapper).getBackingStore(), ownerOP);
               }
            } else if (arrayLength > 0) {
               ((ArrayStore)this.storeMgr.getBackingStoreForField(ownerOP.getExecutionContext().getClassLoaderResolver(), this.mmd, (Class)null)).set(ownerOP, value);
            }

         }
      }
   }

   public void postFetch(ObjectProvider op) {
      if (!this.containerIsStoredInSingleColumn()) {
         List elements = ((ArrayStore)this.storeMgr.getBackingStoreForField(op.getExecutionContext().getClassLoaderResolver(), this.mmd, (Class)null)).getArray(op);
         if (elements != null) {
            boolean primitiveArray = this.mmd.getType().getComponentType().isPrimitive();
            Object array = Array.newInstance(this.mmd.getType().getComponentType(), elements.size());

            for(int i = 0; i < elements.size(); ++i) {
               Object element = elements.get(i);
               if (primitiveArray) {
                  if (element instanceof Boolean) {
                     Array.setBoolean(array, i, (Boolean)element);
                  } else if (element instanceof Byte) {
                     Array.setByte(array, i, (Byte)element);
                  } else if (element instanceof Character) {
                     Array.setChar(array, i, (Character)element);
                  } else if (element instanceof Double) {
                     Array.setDouble(array, i, (Double)element);
                  } else if (element instanceof Float) {
                     Array.setFloat(array, i, (Float)element);
                  } else if (element instanceof Integer) {
                     Array.setInt(array, i, (Integer)element);
                  } else if (element instanceof Long) {
                     Array.setLong(array, i, (Long)element);
                  } else if (element instanceof Short) {
                     Array.setShort(array, i, (Short)element);
                  }
               } else {
                  Array.set(array, i, element);
               }
            }

            if (elements.size() == 0) {
               op.replaceFieldMakeDirty(this.getAbsoluteFieldNumber(), (Object)null);
            } else {
               op.replaceFieldMakeDirty(this.getAbsoluteFieldNumber(), array);
            }
         } else {
            op.replaceFieldMakeDirty(this.getAbsoluteFieldNumber(), (Object)null);
         }

      }
   }

   public void postUpdate(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      Object value = ownerOP.provideField(this.getAbsoluteFieldNumber());
      if (!this.containerIsStoredInSingleColumn()) {
         if (value == null) {
            ((ArrayStore)this.storeMgr.getBackingStoreForField(ec.getClassLoaderResolver(), this.mmd, (Class)null)).clear(ownerOP);
         } else if (!this.mmd.isCascadeUpdate()) {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007008", new Object[]{this.mmd.getFullFieldName()}));
            }

         } else {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007009", new Object[]{this.mmd.getFullFieldName()}));
            }

            ArrayStore backingStore = (ArrayStore)this.storeMgr.getBackingStoreForField(ec.getClassLoaderResolver(), this.mmd, (Class)null);
            backingStore.clear(ownerOP);
            backingStore.set(ownerOP, value);
         }
      } else {
         if (value != null && this.mmd.getArray().elementIsPersistent()) {
            Object[] arrElements = value;

            for(Object elem : arrElements) {
               if (elem != null) {
                  ObjectProvider elemOP = ec.findObjectProvider(elem);
                  if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

      }
   }

   public void preDelete(ObjectProvider op) {
      if (!this.containerIsStoredInSingleColumn()) {
         op.isLoaded(this.getAbsoluteFieldNumber());
         Object value = op.provideField(this.getAbsoluteFieldNumber());
         if (value != null) {
            ArrayStore backingStore = (ArrayStore)this.storeMgr.getBackingStoreForField(op.getExecutionContext().getClassLoaderResolver(), this.mmd, (Class)null);
            backingStore.clear(op);
         }
      }
   }
}

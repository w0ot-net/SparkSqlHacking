package org.datanucleus.store.fieldmanager;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOUtils;

public class PersistFieldManager extends AbstractFieldManager {
   private final ObjectProvider op;
   private final boolean replaceSCOsWithWrappers;

   public PersistFieldManager(ObjectProvider op, boolean replaceSCOsWithWrappers) {
      this.op = op;
      this.replaceSCOsWithWrappers = replaceSCOsWithWrappers;
   }

   protected Object processPersistable(Object pc, int ownerFieldNum, int objectType) {
      ApiAdapter adapter = this.op.getExecutionContext().getApiAdapter();
      if (!adapter.isPersistent(pc) || adapter.isPersistent(pc) && adapter.isDeleted(pc)) {
         return objectType != 0 ? this.op.getExecutionContext().persistObjectInternal(pc, this.op, ownerFieldNum, objectType) : this.op.getExecutionContext().persistObjectInternal(pc, (ObjectProvider)null, -1, objectType);
      } else {
         return pc;
      }
   }

   public void storeObjectField(int fieldNumber, Object value) {
      if (value != null) {
         AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         boolean persistCascade = mmd.isCascadePersist();
         ClassLoaderResolver clr = this.op.getExecutionContext().getClassLoaderResolver();
         RelationType relationType = mmd.getRelationType(clr);
         if (this.replaceSCOsWithWrappers) {
            boolean[] secondClassMutableFieldFlags = this.op.getClassMetaData().getSCOMutableMemberFlags();
            if (secondClassMutableFieldFlags[fieldNumber] && !(value instanceof SCO)) {
               value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
            }
         }

         if (persistCascade) {
            if (RelationType.isRelationSingleValued(relationType)) {
               if (!mmd.isEmbedded() && !mmd.isSerialized()) {
                  this.processPersistable(value, -1, 0);
               } else {
                  this.processPersistable(value, fieldNumber, 1);
               }
            } else if (RelationType.isRelationMultiValued(relationType)) {
               ApiAdapter api = this.op.getExecutionContext().getApiAdapter();
               if (mmd.hasCollection()) {
                  Collection coll = (Collection)value;
                  Iterator iter = coll.iterator();

                  for(int position = 0; iter.hasNext(); ++position) {
                     Object element = iter.next();
                     if (api.isPersistable(element)) {
                        if (!mmd.getCollection().isEmbeddedElement() && !mmd.getCollection().isSerializedElement()) {
                           Object newElement = this.processPersistable(element, -1, 0);
                           ObjectProvider elementSM = this.op.getExecutionContext().findObjectProvider(newElement);
                           if (elementSM.getReferencedPC() != null) {
                              if (coll instanceof List) {
                                 ((List)coll).set(position, newElement);
                              } else {
                                 coll.remove(element);
                                 coll.add(newElement);
                              }
                           }
                        } else {
                           this.processPersistable(element, fieldNumber, 2);
                        }
                     }
                  }
               } else if (mmd.hasMap()) {
                  Map map = (Map)value;

                  for(Map.Entry entry : map.entrySet()) {
                     Object mapKey = entry.getKey();
                     Object mapValue = entry.getValue();
                     Object newMapKey = mapKey;
                     Object newMapValue = mapValue;
                     if (api.isPersistable(mapKey)) {
                        if (!mmd.getMap().isEmbeddedKey() && !mmd.getMap().isSerializedKey()) {
                           newMapKey = this.processPersistable(mapKey, -1, 0);
                        } else {
                           this.processPersistable(mapKey, fieldNumber, 3);
                        }
                     }

                     if (api.isPersistable(mapValue)) {
                        if (!mmd.getMap().isEmbeddedValue() && !mmd.getMap().isSerializedValue()) {
                           newMapValue = this.processPersistable(mapValue, -1, 0);
                        } else {
                           this.processPersistable(mapValue, fieldNumber, 4);
                        }
                     }

                     if (newMapKey != mapKey || newMapValue != mapValue) {
                        boolean updateKey = false;
                        boolean updateValue = false;
                        if (newMapKey != mapKey) {
                           ObjectProvider keySM = this.op.getExecutionContext().findObjectProvider(newMapKey);
                           if (keySM.getReferencedPC() != null) {
                              updateKey = true;
                           }
                        }

                        if (newMapValue != mapValue) {
                           ObjectProvider valSM = this.op.getExecutionContext().findObjectProvider(newMapValue);
                           if (valSM.getReferencedPC() != null) {
                              updateValue = true;
                           }
                        }

                        if (updateKey) {
                           map.remove(mapKey);
                           map.put(newMapKey, updateValue ? newMapValue : mapValue);
                        } else if (updateValue) {
                           map.put(mapKey, newMapValue);
                        }
                     }
                  }
               } else if (mmd.hasArray() && value instanceof Object[]) {
                  Object[] array = value;

                  for(int i = 0; i < array.length; ++i) {
                     Object element = array[i];
                     if (api.isPersistable(element)) {
                        if (!mmd.getArray().isEmbeddedElement() && !mmd.getArray().isSerializedElement()) {
                           Object processedElement = this.processPersistable(element, -1, 0);
                           ObjectProvider elementSM = this.op.getExecutionContext().findObjectProvider(processedElement);
                           if (elementSM.getReferencedPC() != null) {
                              array[i] = processedElement;
                           }
                        } else {
                           this.processPersistable(element, fieldNumber, 2);
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public void storeBooleanField(int fieldNumber, boolean value) {
   }

   public void storeByteField(int fieldNumber, byte value) {
   }

   public void storeCharField(int fieldNumber, char value) {
   }

   public void storeDoubleField(int fieldNumber, double value) {
   }

   public void storeFloatField(int fieldNumber, float value) {
   }

   public void storeIntField(int fieldNumber, int value) {
   }

   public void storeLongField(int fieldNumber, long value) {
   }

   public void storeShortField(int fieldNumber, short value) {
   }

   public void storeStringField(int fieldNumber, String value) {
   }
}

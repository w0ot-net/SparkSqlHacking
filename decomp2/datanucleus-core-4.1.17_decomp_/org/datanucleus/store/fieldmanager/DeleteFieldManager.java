package org.datanucleus.store.fieldmanager;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;

public class DeleteFieldManager extends AbstractFieldManager {
   private final ObjectProvider op;
   private boolean manageRelationships;

   public DeleteFieldManager(ObjectProvider op) {
      this(op, false);
   }

   public DeleteFieldManager(ObjectProvider op, boolean manageRelationships) {
      this.manageRelationships = false;
      this.op = op;
      this.manageRelationships = manageRelationships;
   }

   protected void processPersistable(Object pc) {
      ObjectProvider pcOP = this.op.getExecutionContext().findObjectProvider(pc);
      if (pcOP == null || !pcOP.isDeleting() && !pcOP.becomingDeleted()) {
         this.op.getExecutionContext().deleteObjectInternal(pc);
      }
   }

   public void storeObjectField(int fieldNumber, Object value) {
      if (value != null) {
         AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         ExecutionContext ec = this.op.getExecutionContext();
         RelationType relationType = mmd.getRelationType(ec.getClassLoaderResolver());
         if (RelationType.isRelationSingleValued(relationType)) {
            if (mmd.isDependent()) {
               this.processPersistable(value);
            } else if (this.manageRelationships && RelationType.isBidirectional(relationType) && !mmd.isEmbedded()) {
               ObjectProvider valueOP = ec.findObjectProvider(value);
               if (valueOP != null && !valueOP.getLifecycleState().isDeleted() && !valueOP.isDeleting()) {
                  AbstractMemberMetaData relMmd = mmd.getRelatedMemberMetaData(ec.getClassLoaderResolver())[0];
                  if (relationType == RelationType.ONE_TO_ONE_BI) {
                     valueOP.replaceFieldMakeDirty(relMmd.getAbsoluteFieldNumber(), (Object)null);
                     valueOP.flush();
                  } else if (relationType == RelationType.MANY_TO_ONE_BI) {
                     valueOP.loadField(relMmd.getAbsoluteFieldNumber());
                     Object relValue = valueOP.provideField(relMmd.getAbsoluteFieldNumber());
                     if (relValue != null && relValue instanceof Collection) {
                        ((Collection)relValue).remove(this.op.getObject());
                     }
                  }
               }
            }
         } else if (RelationType.isRelationMultiValued(relationType)) {
            ApiAdapter api = ec.getApiAdapter();
            if (value instanceof Collection) {
               boolean dependent = mmd.getCollection().isDependentElement();
               if (mmd.isCascadeRemoveOrphans()) {
                  dependent = true;
               }

               if (dependent) {
                  for(Object element : (Collection)value) {
                     if (api.isPersistable(element)) {
                        this.processPersistable(element);
                     }
                  }
               } else if (this.manageRelationships && RelationType.isBidirectional(relationType) && !mmd.isEmbedded() && !mmd.getCollection().isEmbeddedElement() && relationType == RelationType.ONE_TO_MANY_BI) {
                  for(Object element : (Collection)value) {
                     if (api.isPersistable(element)) {
                        ObjectProvider elementOP = ec.findObjectProvider(element);
                        if (elementOP != null && !elementOP.getLifecycleState().isDeleted() && !elementOP.isDeleting()) {
                           AbstractMemberMetaData relMmd = mmd.getRelatedMemberMetaData(ec.getClassLoaderResolver())[0];
                           elementOP.replaceFieldMakeDirty(relMmd.getAbsoluteFieldNumber(), (Object)null);
                           elementOP.flush();
                        }
                     }
                  }
               }
            } else if (value instanceof Map) {
               Map map = (Map)value;
               if (mmd.hasMap() && mmd.getMap().isDependentKey()) {
                  for(Object mapKey : map.keySet()) {
                     if (api.isPersistable(mapKey)) {
                        this.processPersistable(mapKey);
                     }
                  }
               }

               if (mmd.hasMap() && mmd.getMap().isDependentValue()) {
                  for(Object mapValue : map.values()) {
                     if (api.isPersistable(mapValue)) {
                        this.processPersistable(mapValue);
                     }
                  }
               }
            } else if (value instanceof Object[] && mmd.hasArray() && mmd.getArray().isDependentElement()) {
               for(int i = 0; i < Array.getLength(value); ++i) {
                  Object element = Array.get(value, i);
                  if (api.isPersistable(element)) {
                     this.processPersistable(element);
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

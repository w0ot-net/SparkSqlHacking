package org.datanucleus.store.fieldmanager;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.FetchPlanForClass;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOUtils;

public class MakeTransientFieldManager extends AbstractFetchDepthFieldManager {
   public MakeTransientFieldManager(ObjectProvider sm, boolean[] secondClassMutableFields, FetchPlanForClass fpClass, FetchPlanState state) {
      super(sm, secondClassMutableFields, fpClass, state);
   }

   protected void processPersistable(Object pc) {
      if (this.op.getExecutionContext().getApiAdapter().isPersistent(pc)) {
         this.op.getExecutionContext().getApiAdapter().getExecutionContext(pc).makeObjectTransient(pc, this.state);
      }

   }

   protected Object internalFetchObjectField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      Object value = sfv.fetchObjectField(fieldNumber);
      if (value != null) {
         AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         RelationType relType = mmd.getRelationType(this.op.getExecutionContext().getClassLoaderResolver());
         if (RelationType.isRelationSingleValued(relType)) {
            this.processPersistable(value);
         } else if (RelationType.isRelationMultiValued(relType)) {
            ApiAdapter api = this.op.getExecutionContext().getApiAdapter();
            if (value instanceof Collection) {
               if (!(value instanceof SCO)) {
                  value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
               }

               SCO sco = (SCO)value;

               for(Object element : (Collection)value) {
                  if (api.isPersistable(element)) {
                     this.processPersistable(element);
                  }
               }

               sco.unsetOwner();
            } else if (value instanceof Map) {
               if (!(value instanceof SCO)) {
                  value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
               }

               SCO sco = (SCO)value;
               Map map = (Map)value;

               for(Object mapKey : map.keySet()) {
                  if (api.isPersistable(mapKey)) {
                     this.processPersistable(mapKey);
                  }
               }

               for(Object mapValue : map.values()) {
                  if (api.isPersistable(mapValue)) {
                     this.processPersistable(mapValue);
                  }
               }

               sco.unsetOwner();
            } else if (value instanceof Object[]) {
               Object[] array = value;

               for(int i = 0; i < array.length; ++i) {
                  Object element = array[i];
                  if (api.isPersistable(element)) {
                     this.processPersistable(element);
                  }
               }
            }
         } else if (value instanceof SCO) {
            SCO sco = (SCO)value;
            sco.unsetOwner();
         }
      }

      return value;
   }

   protected Object endOfGraphOperation(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      Object value = sfv.fetchObjectField(fieldNumber);
      if (value != null && this.secondClassMutableFields[fieldNumber] && value instanceof SCO) {
         SCO sco = (SCO)value;
         sco.unsetOwner();
      }

      return value;
   }
}

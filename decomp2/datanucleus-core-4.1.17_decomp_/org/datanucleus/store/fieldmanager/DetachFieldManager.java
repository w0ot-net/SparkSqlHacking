package org.datanucleus.store.fieldmanager;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.FetchPlanForClass;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.DetachState;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOUtils;

public class DetachFieldManager extends AbstractFetchDepthFieldManager {
   boolean copy = true;

   public DetachFieldManager(ObjectProvider op, boolean[] secondClassMutableFields, FetchPlanForClass fpClass, FetchPlanState state, boolean copy) {
      super(op, secondClassMutableFields, fpClass, state);
      this.copy = copy;
   }

   protected Object processPersistable(Object pc) {
      if (pc == null) {
         return null;
      } else {
         ApiAdapter api = this.op.getExecutionContext().getApiAdapter();
         if (!api.isPersistable(pc)) {
            return pc;
         } else {
            if (!api.isDetached(pc) && api.isPersistent(pc)) {
               if (this.copy) {
                  return this.op.getExecutionContext().detachObjectCopy(pc, this.state);
               }

               this.op.getExecutionContext().detachObject(pc, this.state);
            }

            return pc;
         }
      }
   }

   protected Object internalFetchObjectField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      Object value = sfv.fetchObjectField(fieldNumber);
      if (value == null) {
         return null;
      } else {
         ClassLoaderResolver clr = this.op.getExecutionContext().getClassLoaderResolver();
         ApiAdapter api = this.op.getExecutionContext().getApiAdapter();
         AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         RelationType relationType = mmd.getRelationType(clr);
         if (RelationType.isRelationSingleValued(relationType)) {
            if (api.isPersistable(value)) {
               return this.processPersistable(value);
            }
         } else if (RelationType.isRelationMultiValued(relationType)) {
            if (mmd.hasCollection() || mmd.hasMap()) {
               if (this.copy) {
                  if (!(value instanceof SCO)) {
                     value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
                  }

                  if (!(value instanceof SCO) && mmd.isSerialized()) {
                     if (mmd.hasCollection()) {
                        if (mmd.getCollection().elementIsPersistent()) {
                           throw new NucleusUserException("Unable to detach " + mmd.getFullFieldName() + " since is of an unsupported Collection type with persistent elements");
                        }

                        return value;
                     }

                     if (mmd.hasMap()) {
                        if (!mmd.getMap().keyIsPersistent() && !mmd.getMap().valueIsPersistent()) {
                           return value;
                        }

                        throw new NucleusUserException("Unable to detach " + mmd.getFullFieldName() + " since is of an unsupported Map type with persistent keys/values");
                     }
                  }

                  return ((SCO)value).detachCopy(this.state);
               }

               if (!(value instanceof SCO)) {
                  value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
               }

               SCO sco = (SCO)value;
               if (sco instanceof Collection) {
                  SCOUtils.detachForCollection(this.op, ((Collection)sco).toArray(), this.state);
                  sco.unsetOwner();
               } else if (sco instanceof Map) {
                  SCOUtils.detachForMap(this.op, ((Map)sco).entrySet(), this.state);
                  sco.unsetOwner();
               }

               if (SCOUtils.detachAsWrapped(this.op)) {
                  return sco;
               }

               return SCOUtils.unwrapSCOField(this.op, fieldNumber, sco);
            }

            if (mmd.hasArray()) {
               if (!api.isPersistable(mmd.getType().getComponentType())) {
                  return value;
               }

               Object[] arrValue = value;
               Object[] arrDetached = Array.newInstance(mmd.getType().getComponentType(), arrValue.length);

               for(int j = 0; j < arrValue.length; ++j) {
                  arrDetached[j] = this.processPersistable(arrValue[j]);
               }

               return arrDetached;
            }
         } else if (this.secondClassMutableFields[fieldNumber]) {
            if (this.copy) {
               if (!(value instanceof SCO)) {
                  value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
               }

               return ((SCO)value).detachCopy(this.state);
            }

            if (!(value instanceof SCO)) {
               value = SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
            }

            SCO sco = (SCO)value;
            if (SCOUtils.detachAsWrapped(this.op)) {
               return sco;
            }

            return SCOUtils.unwrapSCOField(this.op, fieldNumber, sco);
         }

         return value;
      }
   }

   protected Object endOfGraphOperation(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      Object value = sfv.fetchObjectField(fieldNumber);
      ApiAdapter api = this.op.getExecutionContext().getApiAdapter();
      if (api.isPersistable(value)) {
         if (this.copy) {
            DetachState.Entry entry = ((DetachState)this.state).getDetachedCopyEntry(value);
            if (entry != null) {
               return entry.getDetachedCopyObject();
            }
         } else if (this.op.getExecutionContext().getApiAdapter().isDetached(value)) {
            return value;
         }
      }

      throw new AbstractFetchDepthFieldManager.EndOfFetchPlanGraphException();
   }
}

package org.datanucleus.store.fieldmanager;

import org.datanucleus.FetchPlanForClass;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;

public abstract class AbstractFetchDepthFieldManager extends AbstractFieldManager {
   protected final ObjectProvider op;
   protected final boolean[] secondClassMutableFields;
   protected final FetchPlanForClass fpClass;
   protected final FetchPlanState state;

   public AbstractFetchDepthFieldManager(ObjectProvider op, boolean[] secondClassMutableFields, FetchPlanForClass fpClass, FetchPlanState state) {
      this.op = op;
      this.secondClassMutableFields = secondClassMutableFields;
      this.fpClass = fpClass;
      this.state = state;
   }

   public Object fetchObjectField(int fieldNumber) throws EndOfFetchPlanGraphException {
      AbstractMemberMetaData fmd = this.fpClass.getAbstractClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      boolean requiresFCOFetching = MetaDataUtils.getInstance().storesFCO(fmd, this.op.getExecutionContext());
      int maxFetchDepth = this.fpClass.getFetchPlan().getMaxFetchDepth();
      int currentFetchDepth = this.state.getCurrentFetchDepth();
      if (requiresFCOFetching) {
         if (currentFetchDepth > 0 && maxFetchDepth > 0 && currentFetchDepth == maxFetchDepth) {
            return this.endOfGraphOperation(fieldNumber);
         } else {
            int maxRecursiveDepth = this.fpClass.getMaxRecursionDepthForMember(fieldNumber);
            if (maxRecursiveDepth > 0 && this.state.getObjectDepthForType(fmd.getFullFieldName()) >= maxRecursiveDepth) {
               return this.endOfGraphOperation(fieldNumber);
            } else {
               this.state.addMemberName(fmd.getFullFieldName());
               Object result = this.internalFetchObjectField(fieldNumber);
               this.state.removeLatestMemberName();
               return result;
            }
         }
      } else {
         return this.internalFetchObjectField(fieldNumber);
      }
   }

   protected abstract Object internalFetchObjectField(int var1);

   protected abstract Object endOfGraphOperation(int var1);

   public boolean fetchBooleanField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchBooleanField(fieldNumber);
   }

   public byte fetchByteField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchByteField(fieldNumber);
   }

   public char fetchCharField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchCharField(fieldNumber);
   }

   public double fetchDoubleField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchDoubleField(fieldNumber);
   }

   public float fetchFloatField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchFloatField(fieldNumber);
   }

   public int fetchIntField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchIntField(fieldNumber);
   }

   public long fetchLongField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchLongField(fieldNumber);
   }

   public short fetchShortField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchShortField(fieldNumber);
   }

   public String fetchStringField(int fieldNumber) {
      SingleValueFieldManager sfv = new SingleValueFieldManager();
      this.op.provideFields(new int[]{fieldNumber}, sfv);
      return sfv.fetchStringField(fieldNumber);
   }

   public static class EndOfFetchPlanGraphException extends RuntimeException {
      private static final long serialVersionUID = -7355093484885080388L;
   }
}

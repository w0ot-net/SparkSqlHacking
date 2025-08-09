package org.datanucleus.store.fieldmanager;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;

public class NullifyRelationFieldManager extends AbstractFieldManager {
   private final ObjectProvider op;

   public NullifyRelationFieldManager(ObjectProvider op) {
      this.op = op;
   }

   public Object fetchObjectField(int fieldNumber) {
      Object value = this.op.provideField(fieldNumber);
      AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      RelationType relType = mmd.getRelationType(this.op.getExecutionContext().getClassLoaderResolver());
      if (value == null) {
         return null;
      } else if (RelationType.isRelationSingleValued(relType)) {
         this.op.makeDirty(fieldNumber);
         return null;
      } else {
         if (RelationType.isRelationMultiValued(relType)) {
            if (value instanceof Collection) {
               this.op.makeDirty(fieldNumber);
               ((Collection)value).clear();
               return value;
            }

            if (value instanceof Map) {
               this.op.makeDirty(fieldNumber);
               ((Map)value).clear();
               return value;
            }

            if (value.getClass().isArray() && Object.class.isAssignableFrom(value.getClass().getComponentType())) {
            }
         }

         return value;
      }
   }

   public boolean fetchBooleanField(int fieldNumber) {
      return true;
   }

   public char fetchCharField(int fieldNumber) {
      return '0';
   }

   public byte fetchByteField(int fieldNumber) {
      return 0;
   }

   public double fetchDoubleField(int fieldNumber) {
      return (double)0.0F;
   }

   public float fetchFloatField(int fieldNumber) {
      return 0.0F;
   }

   public int fetchIntField(int fieldNumber) {
      return 0;
   }

   public long fetchLongField(int fieldNumber) {
      return 0L;
   }

   public short fetchShortField(int fieldNumber) {
      return 0;
   }

   public String fetchStringField(int fieldNumber) {
      return "";
   }
}

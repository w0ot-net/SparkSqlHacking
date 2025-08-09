package org.datanucleus.store.rdbms.fieldmanager;

import java.sql.PreparedStatement;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;

public class OldValueParameterSetter extends ParameterSetter {
   public OldValueParameterSetter(ObjectProvider op, PreparedStatement stmt, StatementClassMapping stmtMappings) {
      super(op, stmt, stmtMappings);
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeBooleanField(fieldNumber, (Boolean)oldValue);
      } else {
         super.storeBooleanField(fieldNumber, value);
      }

   }

   public void storeCharField(int fieldNumber, char value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeCharField(fieldNumber, (Character)oldValue);
      } else {
         super.storeCharField(fieldNumber, value);
      }

   }

   public void storeByteField(int fieldNumber, byte value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeByteField(fieldNumber, (Byte)oldValue);
      } else {
         super.storeByteField(fieldNumber, value);
      }

   }

   public void storeShortField(int fieldNumber, short value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeShortField(fieldNumber, (Short)oldValue);
      } else {
         super.storeShortField(fieldNumber, value);
      }

   }

   public void storeIntField(int fieldNumber, int value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeIntField(fieldNumber, (Integer)oldValue);
      } else {
         super.storeIntField(fieldNumber, value);
      }

   }

   public void storeLongField(int fieldNumber, long value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeLongField(fieldNumber, (Long)oldValue);
      } else {
         super.storeLongField(fieldNumber, value);
      }

   }

   public void storeFloatField(int fieldNumber, float value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeFloatField(fieldNumber, (Float)oldValue);
      } else {
         super.storeFloatField(fieldNumber, value);
      }

   }

   public void storeDoubleField(int fieldNumber, double value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeDoubleField(fieldNumber, (Double)oldValue);
      } else {
         super.storeDoubleField(fieldNumber, value);
      }

   }

   public void storeStringField(int fieldNumber, String value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeStringField(fieldNumber, (String)oldValue);
      } else {
         super.storeStringField(fieldNumber, value);
      }

   }

   public void storeObjectField(int fieldNumber, Object value) {
      Object oldValue = this.op.getAssociatedValue("FIELD_VALUE.ORIGINAL." + fieldNumber);
      if (oldValue != null) {
         super.storeObjectField(fieldNumber, oldValue);
      } else {
         super.storeObjectField(fieldNumber, value);
      }

   }
}

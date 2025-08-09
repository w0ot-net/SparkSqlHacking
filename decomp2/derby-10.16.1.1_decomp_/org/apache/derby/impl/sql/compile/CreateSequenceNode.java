package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class CreateSequenceNode extends DDLStatementNode {
   private TableName _sequenceName;
   private DataTypeDescriptor _dataType;
   private Long _initialValue;
   private Long _stepValue;
   private Long _maxValue;
   private Long _minValue;
   private boolean _cycle;
   public static final int SEQUENCE_ELEMENT_COUNT = 1;

   CreateSequenceNode(TableName var1, DataTypeDescriptor var2, Long var3, Long var4, Long var5, Long var6, boolean var7, ContextManager var8) throws StandardException {
      super(var1, var8);
      this._sequenceName = var1;
      if (var2 != null) {
         this._dataType = var2;
      } else {
         this._dataType = DataTypeDescriptor.INTEGER;
      }

      this._stepValue = var4 != null ? var4 : 1L;
      if (this._dataType.getTypeId().equals(TypeId.SMALLINT_ID)) {
         this._minValue = var6 != null ? var6 : -32768L;
         this._maxValue = var5 != null ? var5 : 32767L;
      } else if (this._dataType.getTypeId().equals(TypeId.INTEGER_ID)) {
         this._minValue = var6 != null ? var6 : -2147483648L;
         this._maxValue = var5 != null ? var5 : 2147483647L;
      } else {
         this._minValue = var6 != null ? var6 : Long.MIN_VALUE;
         this._maxValue = var5 != null ? var5 : Long.MAX_VALUE;
      }

      if (var3 != null) {
         this._initialValue = var3;
      } else if (this._stepValue > 0L) {
         this._initialValue = this._minValue;
      } else {
         this._initialValue = this._maxValue;
      }

      this._cycle = var7;
      this.implicitCreateSchema = true;
   }

   public String toString() {
      return "";
   }

   public void bindStatement() throws StandardException {
      SchemaDescriptor var1 = this.getSchemaDescriptor();
      if (this._sequenceName.getSchemaName() == null) {
         this._sequenceName.setSchemaName(var1.getSchemaName());
      }

      if (this._dataType.getTypeId().equals(TypeId.SMALLINT_ID)) {
         if (this._minValue < -32768L || this._minValue >= 32767L) {
            throw StandardException.newException("42XAE", new Object[]{"MINVALUE", "SMALLINT", "-32768", "32767"});
         }

         if (this._maxValue <= -32768L || this._maxValue > 32767L) {
            throw StandardException.newException("42XAE", new Object[]{"MAXVALUE", "SMALLINT", "-32768", "32767"});
         }
      } else if (this._dataType.getTypeId().equals(TypeId.INTEGER_ID)) {
         if (this._minValue < -2147483648L || this._minValue >= 2147483647L) {
            throw StandardException.newException("42XAE", new Object[]{"MINVALUE", "INTEGER", "-2147483648", "2147483647"});
         }

         if (this._maxValue <= -2147483648L || this._maxValue > 2147483647L) {
            throw StandardException.newException("42XAE", new Object[]{"MAXVALUE", "INTEGER", "-2147483648", "2147483647"});
         }
      } else {
         if (this._minValue < Long.MIN_VALUE || this._minValue >= Long.MAX_VALUE) {
            throw StandardException.newException("42XAE", new Object[]{"MINVALUE", "BIGINT", "-9223372036854775808", "9223372036854775807"});
         }

         if (this._maxValue <= Long.MIN_VALUE || this._maxValue > Long.MAX_VALUE) {
            throw StandardException.newException("42XAE", new Object[]{"MAXVALUE", "BIGINT", "-9223372036854775808", "9223372036854775807"});
         }
      }

      if (this._minValue >= this._maxValue) {
         throw StandardException.newException("42XAF", new Object[]{this._minValue.toString(), this._maxValue.toString()});
      } else if (this._initialValue >= this._minValue && this._initialValue <= this._maxValue) {
         if (this._stepValue == 0L) {
            throw StandardException.newException("42XAC", new Object[0]);
         }
      } else {
         throw StandardException.newException("42XAG", new Object[]{this._initialValue.toString(), this._minValue.toString(), this._maxValue.toString()});
      }
   }

   public String statementToString() {
      return "CREATE SEQUENCE";
   }

   public ConstantAction makeConstantAction() {
      return this.getGenericConstantActionFactory().getCreateSequenceConstantAction(this._sequenceName, this._dataType, this._initialValue, this._stepValue, this._maxValue, this._minValue, this._cycle);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this._sequenceName != null) {
         this._sequenceName = (TableName)this._sequenceName.accept(var1);
      }

   }
}

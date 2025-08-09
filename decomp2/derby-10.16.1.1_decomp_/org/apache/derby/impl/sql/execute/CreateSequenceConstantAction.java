package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class CreateSequenceConstantAction extends DDLConstantAction {
   private String _sequenceName;
   private String _schemaName;
   private DataTypeDescriptor _dataType;
   private long _initialValue;
   private long _stepValue;
   private long _maxValue;
   private long _minValue;
   private boolean _cycle;

   public CreateSequenceConstantAction(String var1, String var2, DataTypeDescriptor var3, long var4, long var6, long var8, long var10, boolean var12) {
      this._schemaName = var1;
      this._sequenceName = var2;
      this._dataType = var3;
      this._initialValue = var4;
      this._stepValue = var6;
      this._maxValue = var8;
      this._minValue = var10;
      this._cycle = var12;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var3 = var1.getLanguageConnectionContext();
      DataDictionary var4 = var3.getDataDictionary();
      TransactionController var5 = var3.getTransactionExecute();
      DataDescriptorGenerator var6 = var4.getDataDescriptorGenerator();
      var4.startWriting(var3);
      SchemaDescriptor var2 = DDLConstantAction.getSchemaDescriptorForCreate(var4, var1, this._schemaName);
      SequenceDescriptor var7 = var4.getSequenceDescriptor(var2, this._sequenceName);
      if (var7 != null) {
         throw StandardException.newException("X0Y68.S", new Object[]{var7.getDescriptorType(), this._sequenceName});
      } else {
         var7 = var6.newSequenceDescriptor(var2, var4.getUUIDFactory().createUUID(), this._sequenceName, this._dataType, this._initialValue, this._initialValue, this._minValue, this._maxValue, this._stepValue, this._cycle);
         var4.addDescriptor(var7, (TupleDescriptor)null, 20, false, var5);
      }
   }

   public String toString() {
      return "CREATE SEQUENCE " + this._sequenceName;
   }
}

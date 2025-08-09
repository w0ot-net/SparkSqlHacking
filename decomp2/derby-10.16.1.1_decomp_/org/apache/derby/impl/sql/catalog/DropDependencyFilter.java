package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.TupleFilter;
import org.apache.derby.iapi.types.BooleanDataValue;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.shared.common.error.StandardException;

public class DropDependencyFilter implements TupleFilter {
   UUID providerID;
   UUIDFactory uuidFactory = null;
   DataValueFactory dataValueFactory = null;
   BooleanDataValue trueValue;
   BooleanDataValue falseValue;

   public DropDependencyFilter(UUID var1) {
      this.providerID = var1;
   }

   public void init(ExecRow var1) throws StandardException {
   }

   public BooleanDataValue execute(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(3);
      String var3 = var2.getString();
      UUID var4 = this.getUUIDFactory().recreateUUID(var3);
      return this.providerID.equals(var4) ? this.getTrueValue() : this.getFalseValue();
   }

   private UUIDFactory getUUIDFactory() throws StandardException {
      if (this.uuidFactory == null) {
         this.uuidFactory = DataDictionaryImpl.getMonitor().getUUIDFactory();
      }

      return this.uuidFactory;
   }

   private BooleanDataValue getTrueValue() throws StandardException {
      if (this.trueValue == null) {
         this.trueValue = new SQLBoolean(true);
      }

      return this.trueValue;
   }

   private BooleanDataValue getFalseValue() throws StandardException {
      if (this.falseValue == null) {
         this.falseValue = new SQLBoolean(false);
      }

      return this.falseValue;
   }
}

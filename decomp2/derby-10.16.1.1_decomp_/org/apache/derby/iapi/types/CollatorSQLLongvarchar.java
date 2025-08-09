package org.apache.derby.iapi.types;

import java.text.RuleBasedCollator;
import org.apache.derby.shared.common.error.StandardException;

class CollatorSQLLongvarchar extends SQLLongvarchar implements CollationElementsInterface {
   private WorkHorseForCollatorDatatypes holderForCollationSensitiveInfo;

   CollatorSQLLongvarchar(RuleBasedCollator var1) {
      this.setCollator(var1);
   }

   CollatorSQLLongvarchar(String var1, RuleBasedCollator var2) {
      super(var1);
      this.setCollator(var2);
   }

   protected void setCollator(RuleBasedCollator var1) {
      this.holderForCollationSensitiveInfo = new WorkHorseForCollatorDatatypes(var1, this);
   }

   protected RuleBasedCollator getCollatorForCollation() throws StandardException {
      return this.holderForCollationSensitiveInfo.getCollatorForCollation();
   }

   public boolean hasSingleCollationElement() throws StandardException {
      return this.holderForCollationSensitiveInfo.hasSingleCollationElement();
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      try {
         return new CollatorSQLLongvarchar(this.getString(), this.holderForCollationSensitiveInfo.getCollatorForCollation());
      } catch (StandardException var3) {
         return null;
      }
   }

   public DataValueDescriptor getNewNull() {
      CollatorSQLLongvarchar var1 = new CollatorSQLLongvarchar(this.holderForCollationSensitiveInfo.getCollatorForCollation());
      return var1;
   }

   public StringDataValue getValue(RuleBasedCollator var1) {
      if (var1 != null) {
         this.setCollator(var1);
         return this;
      } else {
         SQLLongvarchar var2 = new SQLLongvarchar();
         var2.copyState(this);
         return var2;
      }
   }

   protected int stringCompare(SQLChar var1, SQLChar var2) throws StandardException {
      return this.holderForCollationSensitiveInfo.stringCompare(var1, var2);
   }

   public int hashCode() {
      return this.hashCodeForCollation();
   }

   public BooleanDataValue like(DataValueDescriptor var1) throws StandardException {
      return this.holderForCollationSensitiveInfo.like(var1);
   }

   public BooleanDataValue like(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      return this.holderForCollationSensitiveInfo.like(var1, var2);
   }
}

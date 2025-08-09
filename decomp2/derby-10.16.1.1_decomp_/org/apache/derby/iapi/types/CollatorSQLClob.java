package org.apache.derby.iapi.types;

import java.sql.Clob;
import java.text.RuleBasedCollator;
import org.apache.derby.shared.common.error.StandardException;

class CollatorSQLClob extends SQLClob implements CollationElementsInterface {
   private WorkHorseForCollatorDatatypes holderForCollationSensitiveInfo;

   CollatorSQLClob(RuleBasedCollator var1) {
      this.setCollator(var1);
   }

   CollatorSQLClob(String var1, RuleBasedCollator var2) {
      super(var1);
      this.setCollator(var2);
   }

   CollatorSQLClob(Clob var1, RuleBasedCollator var2) {
      super(var1);
      this.setCollator(var2);
   }

   private void setCollator(RuleBasedCollator var1) {
      this.holderForCollationSensitiveInfo = new WorkHorseForCollatorDatatypes(var1, this);
   }

   protected RuleBasedCollator getCollatorForCollation() throws StandardException {
      return this.holderForCollationSensitiveInfo.getCollatorForCollation();
   }

   public boolean hasSingleCollationElement() throws StandardException {
      return this.holderForCollationSensitiveInfo.hasSingleCollationElement();
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      if (var1) {
         try {
            return new CollatorSQLClob(this.getString(), this.holderForCollationSensitiveInfo.getCollatorForCollation());
         } catch (StandardException var4) {
            return null;
         }
      } else {
         SQLClob var2 = (SQLClob)super.cloneValue(var1);
         CollatorSQLClob var3 = new CollatorSQLClob(this.holderForCollationSensitiveInfo.getCollatorForCollation());
         var3.copyState(var2);
         return var3;
      }
   }

   public DataValueDescriptor getNewNull() {
      CollatorSQLClob var1 = new CollatorSQLClob((String)null, this.holderForCollationSensitiveInfo.getCollatorForCollation());
      return var1;
   }

   public StringDataValue getValue(RuleBasedCollator var1) {
      if (var1 != null) {
         this.setCollator(var1);
         return this;
      } else {
         SQLClob var2 = new SQLClob();
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

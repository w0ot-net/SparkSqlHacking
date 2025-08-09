package org.apache.derby.iapi.types;

import java.text.CollationElementIterator;
import java.text.CollationKey;
import java.text.RuleBasedCollator;
import org.apache.derby.shared.common.error.StandardException;

final class WorkHorseForCollatorDatatypes {
   private RuleBasedCollator collatorForCharacterDatatypes;
   private SQLChar stringData;

   WorkHorseForCollatorDatatypes(RuleBasedCollator var1, SQLChar var2) {
      this.collatorForCharacterDatatypes = var1;
      this.stringData = var2;
   }

   int stringCompare(SQLChar var1, SQLChar var2) throws StandardException {
      CollationKey var3 = var1.getCollationKey();
      CollationKey var4 = var2.getCollationKey();
      if (var3 != null && var4 != null) {
         return var3.compareTo(var4);
      } else if (var3 != null) {
         return -1;
      } else {
         return var4 != null ? 1 : 0;
      }
   }

   BooleanDataValue like(DataValueDescriptor var1) throws StandardException {
      Boolean var2 = Like.like(this.stringData.getCharArray(), this.stringData.getLength(), ((SQLChar)var1).getCharArray(), var1.getLength(), (char[])null, 0, this.collatorForCharacterDatatypes);
      return SQLBoolean.truthValue(this.stringData, var1, var2);
   }

   BooleanDataValue like(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var2.isNull()) {
         throw StandardException.newException("22501", new Object[0]);
      } else {
         CollationElementsInterface var4 = (CollationElementsInterface)var2;
         if (var2.getLength() == 1 && var4.hasSingleCollationElement()) {
            Boolean var3 = Like.like(this.stringData.getCharArray(), this.stringData.getLength(), ((SQLChar)var1).getCharArray(), var1.getLength(), ((SQLChar)var2).getCharArray(), var2.getLength(), this.collatorForCharacterDatatypes);
            return SQLBoolean.truthValue(this.stringData, var1, var3);
         } else {
            throw StandardException.newException("22019", new Object[]{var4.toString()});
         }
      }
   }

   RuleBasedCollator getCollatorForCollation() {
      return this.collatorForCharacterDatatypes;
   }

   boolean hasSingleCollationElement() throws StandardException {
      if (this.stringData.isNull()) {
         return false;
      } else {
         CollationElementIterator var1 = this.collatorForCharacterDatatypes.getCollationElementIterator(this.stringData.getString());
         return var1.next() != -1 && var1.next() == -1;
      }
   }
}

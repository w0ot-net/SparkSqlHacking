package org.apache.derby.impl.sql.catalog;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.Dependable;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class DDColumnDependableFinder extends DDdependableFinder {
   private byte[] columnBitMap;

   public DDColumnDependableFinder(int var1) {
      super(var1);
   }

   DDColumnDependableFinder(int var1, byte[] var2) {
      super(var1);
      this.columnBitMap = var2;
   }

   Dependable findDependable(DataDictionary var1, UUID var2) throws StandardException {
      TableDescriptor var3 = var1.getTableDescriptor(var2);
      if (var3 != null) {
         var3.setReferencedColumnMap(new FormatableBitSet(this.columnBitMap));
      }

      return var3;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      FormatableHashtable var2 = (FormatableHashtable)var1.readObject();
      this.columnBitMap = (byte[])var2.get("columnBitMap");
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      FormatableHashtable var2 = new FormatableHashtable();
      var2.put("columnBitMap", this.columnBitMap);
      var1.writeObject(var2);
   }
}

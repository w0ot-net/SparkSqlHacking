package org.apache.derby.impl.store.access.heap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.ResultSet;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataType;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RefDataValue;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.raw.data.RecordId;
import org.apache.derby.shared.common.error.StandardException;

public class HeapRowLocation extends DataType implements RowLocation, RefDataValue {
   private long pageno;
   private int recid;
   private RecordHandle rh;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(HeapRowLocation.class);
   private static final int RECORD_HANDLE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(RecordId.class);

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      if (null != this.rh) {
         var1 += RECORD_HANDLE_MEMORY_USAGE;
      }

      return var1;
   }

   public String getTypeName() {
      return "RowLocation";
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) {
   }

   public DataValueDescriptor getNewNull() {
      return new HeapRowLocation();
   }

   public Object getObject() {
      return this;
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new HeapRowLocation(this);
   }

   public DataValueDescriptor recycle() {
      this.pageno = 0L;
      this.recid = 0;
      this.rh = null;
      return this;
   }

   public int getLength() {
      return 10;
   }

   public String getString() {
      return this.toString();
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) {
      int var5 = this.compare(var2);
      switch (var1) {
         case 1 -> {
            return var5 < 0;
         }
         case 2 -> {
            return var5 == 0;
         }
         case 3 -> {
            return var5 <= 0;
         }
         default -> {
            return false;
         }
      }
   }

   public int compare(DataValueDescriptor var1) {
      HeapRowLocation var2 = (HeapRowLocation)var1;
      long var3 = this.pageno;
      long var5 = var2.pageno;
      if (var3 < var5) {
         return -1;
      } else if (var3 > var5) {
         return 1;
      } else {
         int var7 = this.recid;
         int var8 = var2.recid;
         if (var7 == var8) {
            return 0;
         } else {
            return var7 < var8 ? -1 : 1;
         }
      }
   }

   public void setValue(RowLocation var1) {
      HeapRowLocation var2 = (HeapRowLocation)var1;
      this.setFrom(var2.rh);
   }

   HeapRowLocation(RecordHandle var1) {
      this.setFrom(var1);
   }

   public HeapRowLocation() {
      this.pageno = 0L;
      this.recid = 0;
   }

   private HeapRowLocation(HeapRowLocation var1) {
      this.pageno = var1.pageno;
      this.recid = var1.recid;
      this.rh = var1.rh;
   }

   public RecordHandle getRecordHandle(ContainerHandle var1) throws StandardException {
      return this.rh != null ? this.rh : (this.rh = var1.makeRecordHandle(this.pageno, this.recid));
   }

   void setFrom(RecordHandle var1) {
      this.pageno = var1.getPageNumber();
      this.recid = var1.getId();
      this.rh = var1;
   }

   public int getTypeFormatId() {
      return 90;
   }

   public boolean isNull() {
      return false;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      CompressedNumber.writeLong((DataOutput)var1, this.pageno);
      CompressedNumber.writeInt((DataOutput)var1, this.recid);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.pageno = CompressedNumber.readLong((DataInput)var1);
      this.recid = CompressedNumber.readInt((DataInput)var1);
      this.rh = null;
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException, ClassNotFoundException {
      this.pageno = var1.readCompressedLong();
      this.recid = var1.readCompressedInt();
      this.rh = null;
   }

   public void restoreToNull() {
   }

   protected void setFrom(DataValueDescriptor var1) {
      HeapRowLocation var2 = (HeapRowLocation)var1;
      this.pageno = var2.pageno;
      this.recid = var2.recid;
      this.rh = var2.rh;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof HeapRowLocation var2)) {
         return false;
      } else {
         return this.pageno == var2.pageno && this.recid == var2.recid;
      }
   }

   public int hashCode() {
      return (int)this.pageno ^ this.recid;
   }

   public String toString() {
      String var1 = "(" + this.pageno + "," + this.recid + ")";
      return var1;
   }
}

package org.apache.hadoop.hive.serde2.fast;

import java.io.IOException;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

public abstract class DeserializeRead {
   protected TypeInfo[] typeInfos;
   protected boolean useExternalBuffer;
   protected ObjectInspector.Category[] categories;
   protected PrimitiveObjectInspector.PrimitiveCategory[] primitiveCategories;
   public boolean currentBoolean;
   public byte currentByte;
   public short currentShort;
   public int currentInt;
   public long currentLong;
   public float currentFloat;
   public double currentDouble;
   public boolean currentExternalBufferNeeded;
   public int currentExternalBufferNeededLen;
   public byte[] currentBytes;
   public int currentBytesStart;
   public int currentBytesLength;
   public DateWritable currentDateWritable;
   public TimestampWritable currentTimestampWritable;
   public HiveIntervalYearMonthWritable currentHiveIntervalYearMonthWritable;
   public HiveIntervalDayTimeWritable currentHiveIntervalDayTimeWritable;
   public HiveDecimalWritable currentHiveDecimalWritable;

   public DeserializeRead(TypeInfo[] typeInfos, boolean useExternalBuffer) {
      this.typeInfos = typeInfos;
      int count = typeInfos.length;
      this.categories = new ObjectInspector.Category[count];
      this.primitiveCategories = new PrimitiveObjectInspector.PrimitiveCategory[count];

      for(int i = 0; i < count; ++i) {
         TypeInfo typeInfo = typeInfos[i];
         ObjectInspector.Category category = typeInfo.getCategory();
         this.categories[i] = category;
         if (category == ObjectInspector.Category.PRIMITIVE) {
            PrimitiveTypeInfo primitiveTypeInfo = (PrimitiveTypeInfo)typeInfo;
            PrimitiveObjectInspector.PrimitiveCategory primitiveCategory = primitiveTypeInfo.getPrimitiveCategory();
            this.primitiveCategories[i] = primitiveCategory;
            switch (primitiveCategory) {
               case DATE:
                  if (this.currentDateWritable == null) {
                     this.currentDateWritable = new DateWritable();
                  }
                  break;
               case TIMESTAMP:
                  if (this.currentTimestampWritable == null) {
                     this.currentTimestampWritable = new TimestampWritable();
                  }
                  break;
               case INTERVAL_YEAR_MONTH:
                  if (this.currentHiveIntervalYearMonthWritable == null) {
                     this.currentHiveIntervalYearMonthWritable = new HiveIntervalYearMonthWritable();
                  }
                  break;
               case INTERVAL_DAY_TIME:
                  if (this.currentHiveIntervalDayTimeWritable == null) {
                     this.currentHiveIntervalDayTimeWritable = new HiveIntervalDayTimeWritable();
                  }
                  break;
               case DECIMAL:
                  if (this.currentHiveDecimalWritable == null) {
                     this.currentHiveDecimalWritable = new HiveDecimalWritable();
                  }
            }
         }

         this.useExternalBuffer = useExternalBuffer;
      }

   }

   protected DeserializeRead() {
   }

   public TypeInfo[] typeInfos() {
      return this.typeInfos;
   }

   public abstract void set(byte[] var1, int var2, int var3);

   public abstract boolean readNextField() throws IOException;

   public abstract void skipNextField() throws IOException;

   public boolean isReadFieldSupported() {
      return false;
   }

   public boolean readField(int fieldIndex) throws IOException {
      throw new RuntimeException("Not supported");
   }

   public abstract boolean isEndOfInputReached();

   public abstract String getDetailedReadPositionString();

   public void copyToExternalBuffer(byte[] externalBuffer, int externalBufferStart) throws IOException {
      throw new RuntimeException("Not implemented");
   }
}

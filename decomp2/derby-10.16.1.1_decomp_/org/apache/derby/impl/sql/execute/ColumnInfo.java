package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.util.ArrayUtil;

public class ColumnInfo implements Formatable {
   int action;
   String name;
   DataTypeDescriptor dataType;
   DefaultInfo defaultInfo;
   ProviderInfo[] providers;
   DataValueDescriptor defaultValue;
   UUID newDefaultUUID;
   UUID oldDefaultUUID;
   long autoincStart;
   long autoincInc;
   boolean autoincCycle;
   long autoinc_create_or_modify_Start_Increment = -1L;
   public static final int CREATE = 0;
   public static final int DROP = 1;
   public static final int MODIFY_COLUMN_TYPE = 2;
   public static final int MODIFY_COLUMN_CONSTRAINT = 3;
   public static final int MODIFY_COLUMN_CONSTRAINT_NOT_NULL = 4;
   public static final int MODIFY_COLUMN_DEFAULT_RESTART = 5;
   public static final int MODIFY_COLUMN_DEFAULT_INCREMENT = 6;
   public static final int MODIFY_COLUMN_DEFAULT_VALUE = 7;
   public static final int MODIFY_COLUMN_GENERATED_ALWAYS = 8;
   public static final int MODIFY_COLUMN_GENERATED_BY_DEFAULT = 9;
   public static final int MODIFY_COLUMN_DEFAULT_CYCLE = 10;

   public ColumnInfo() {
   }

   public ColumnInfo(String var1, DataTypeDescriptor var2, DataValueDescriptor var3, DefaultInfo var4, ProviderInfo[] var5, UUID var6, UUID var7, int var8, long var9, long var11, boolean var13, long var14) {
      this.name = var1;
      this.dataType = var2;
      this.defaultValue = var3;
      this.defaultInfo = var4;
      this.providers = (ProviderInfo[])ArrayUtil.copy(var5);
      this.newDefaultUUID = var6;
      this.oldDefaultUUID = var7;
      this.action = var8;
      this.autoincStart = var9;
      this.autoincInc = var11;
      this.autoincCycle = var13;
      this.autoinc_create_or_modify_Start_Increment = var14;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      FormatableHashtable var3 = (FormatableHashtable)var1.readObject();
      this.name = (String)var3.get("name");
      this.dataType = (DataTypeDescriptor)var3.get("dataType");
      this.defaultValue = (DataValueDescriptor)var3.get("defaultValue");
      this.defaultInfo = (DefaultInfo)var3.get("defaultInfo");
      this.newDefaultUUID = (UUID)var3.get("newDefaultUUID");
      this.oldDefaultUUID = (UUID)var3.get("oldDefaultUUID");
      this.action = var3.getInt("action");
      if (var3.get("autoincStart") != null) {
         this.autoincStart = var3.getLong("autoincStart");
         this.autoincInc = var3.getLong("autoincInc");
      } else {
         this.autoincInc = this.autoincStart = 0L;
      }

      FormatableArrayHolder var4 = (FormatableArrayHolder)var3.get("providers");
      if (var4 != null) {
         this.providers = (ProviderInfo[])var4.getArray(ProviderInfo[].class);
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatableHashtable var2 = new FormatableHashtable();
      var2.put("name", this.name);
      var2.put("dataType", this.dataType);
      var2.put("defaultValue", this.defaultValue);
      var2.put("defaultInfo", this.defaultInfo);
      var2.put("newDefaultUUID", this.newDefaultUUID);
      var2.put("oldDefaultUUID", this.oldDefaultUUID);
      var2.putInt("action", this.action);
      if (this.autoincInc != 0L) {
         var2.putLong("autoincStart", this.autoincStart);
         var2.putLong("autoincInc", this.autoincInc);
      }

      if (this.providers != null) {
         FormatableArrayHolder var3 = new FormatableArrayHolder(this.providers);
         var2.put("providers", var3);
      }

      var1.writeObject(var2);
   }

   public int getTypeFormatId() {
      return 358;
   }

   public DataTypeDescriptor getDataType() {
      return this.dataType;
   }

   public String toString() {
      return "";
   }
}

package org.apache.derby.impl.sql.execute;

import java.io.DataInput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class FKInfo implements Formatable {
   public static final int FOREIGN_KEY = 1;
   public static final int REFERENCED_KEY = 2;
   String schemaName;
   String tableName;
   int type;
   UUID refUUID;
   long refConglomNumber;
   UUID refConstraintID;
   boolean refConstraintIsDeferrable;
   int stmtType;
   RowLocation rowLocation;
   String[] fkConstraintNames;
   private UUID[] fkUUIDs;
   long[] fkConglomNumbers;
   UUID[] fkIds;
   boolean[] fkIsSelfReferencing;
   int[] colArray;
   int[] raRules;
   boolean[] deferrable;

   public FKInfo() {
   }

   public FKInfo(String[] var1, String var2, String var3, int var4, int var5, UUID var6, long var7, UUID var9, boolean var10, UUID[] var11, long[] var12, boolean[] var13, int[] var14, RowLocation var15, int[] var16, boolean[] var17, UUID[] var18) {
      this.fkConstraintNames = (String[])ArrayUtil.copy(var1);
      this.tableName = var3;
      this.schemaName = var2;
      this.stmtType = var4;
      this.type = var5;
      this.refUUID = var6;
      this.refConglomNumber = var7;
      this.refConstraintID = var9;
      this.refConstraintIsDeferrable = var10;
      this.fkUUIDs = (UUID[])ArrayUtil.copy(var11);
      this.fkConglomNumbers = ArrayUtil.copy(var12);
      this.fkIsSelfReferencing = ArrayUtil.copy(var13);
      this.colArray = ArrayUtil.copy(var14);
      this.rowLocation = var15;
      this.raRules = ArrayUtil.copy(var16);
      this.deferrable = ArrayUtil.copy(var17);
      this.fkIds = (UUID[])ArrayUtil.copy(var18);
   }

   public static FKInfo[] chooseRelevantFKInfos(FKInfo[] var0, int[] var1, boolean var2) {
      if (var0 == null) {
         return (FKInfo[])null;
      } else {
         Vector var3 = new Vector();
         FKInfo[] var4 = null;

         for(int var5 = 0; var5 < var0.length; ++var5) {
            if (var2 && var0[var5].type == 1) {
               var3.addElement(var0[var5]);
            } else {
               int var6 = var0[var5].colArray.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  for(int var8 = 0; var8 < var1.length; ++var8) {
                     if (var0[var5].colArray[var7] == var1[var8]) {
                        var3.addElement(var0[var5]);
                        var7 = var6;
                        break;
                     }
                  }
               }
            }
         }

         int var9 = var3.size();
         if (var9 > 0) {
            var4 = new FKInfo[var9];

            for(int var10 = 0; var10 < var9; ++var10) {
               var4[var10] = (FKInfo)var3.elementAt(var10);
            }
         }

         return var4;
      }
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatIdUtil.writeFormatIdInteger(var1, this.rowLocation.getTypeFormatId());
      var1.writeObject(this.schemaName);
      var1.writeObject(this.tableName);
      var1.writeInt(this.type);
      var1.writeInt(this.stmtType);
      var1.writeObject(this.refUUID);
      var1.writeLong(this.refConglomNumber);
      var1.writeObject(this.refConstraintID);
      var1.writeBoolean(this.refConstraintIsDeferrable);
      ArrayUtil.writeArray(var1, this.fkConstraintNames);
      ArrayUtil.writeArray(var1, this.fkUUIDs);
      ArrayUtil.writeLongArray(var1, this.fkConglomNumbers);
      ArrayUtil.writeBooleanArray(var1, this.fkIsSelfReferencing);
      ArrayUtil.writeIntArray(var1, this.colArray);
      ArrayUtil.writeIntArray(var1, this.raRules);
      ArrayUtil.writeBooleanArray(var1, this.deferrable);
      ArrayUtil.writeArray(var1, this.fkIds);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      try {
         int var2 = FormatIdUtil.readFormatIdInteger((DataInput)var1);
         this.rowLocation = (RowLocation)Monitor.newInstanceFromIdentifier(var2);
         this.schemaName = (String)var1.readObject();
         this.tableName = (String)var1.readObject();
         this.type = var1.readInt();
         this.stmtType = var1.readInt();
         this.refUUID = (UUID)var1.readObject();
         this.refConglomNumber = var1.readLong();
         this.refConstraintID = (UUID)var1.readObject();
         this.refConstraintIsDeferrable = var1.readBoolean();
         this.fkConstraintNames = new String[ArrayUtil.readArrayLength(var1)];
         ArrayUtil.readArrayItems(var1, this.fkConstraintNames);
         this.fkUUIDs = new UUID[ArrayUtil.readArrayLength(var1)];
         ArrayUtil.readArrayItems(var1, this.fkUUIDs);
         this.fkConglomNumbers = ArrayUtil.readLongArray(var1);
         this.fkIsSelfReferencing = ArrayUtil.readBooleanArray(var1);
         this.colArray = ArrayUtil.readIntArray(var1);
         this.raRules = ArrayUtil.readIntArray(var1);
         this.deferrable = ArrayUtil.readBooleanArray(var1);
         this.fkIds = new UUID[ArrayUtil.readArrayLength(var1)];
         ArrayUtil.readArrayItems(var1, this.fkIds);
      } catch (StandardException var3) {
         throw new StreamCorruptedException(var3.toString());
      }
   }

   public int getTypeFormatId() {
      return 282;
   }

   public String toString() {
      return "";
   }
}

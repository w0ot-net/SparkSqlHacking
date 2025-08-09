package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.util.ArrayUtil;

public class RoutineAliasInfo extends MethodAliasInfo {
   private static final String[] SQL_CONTROL = new String[]{"MODIFIES SQL DATA", "READS SQL DATA", "CONTAINS SQL", "NO SQL"};
   public static final short MODIFIES_SQL_DATA = 0;
   public static final short READS_SQL_DATA = 1;
   public static final short CONTAINS_SQL = 2;
   public static final short NO_SQL = 3;
   public static final short PS_JAVA = 0;
   public static final short PS_DERBY_JDBC_RESULT_SET = 1;
   public static final short PS_DERBY = 2;
   private static final short SQL_ALLOWED_MASK = 15;
   private static final short DETERMINISTIC_MASK = 16;
   private static final short SECURITY_DEFINER_MASK = 32;
   private static final short VARARGS_MASK = 64;
   private int parameterCount;
   private TypeDescriptor[] parameterTypes;
   private String[] parameterNames;
   private int[] parameterModes;
   private int dynamicResultSets;
   private TypeDescriptor returnType;
   private short parameterStyle;
   private short sqlOptions;
   private String specificName;
   private boolean calledOnNullInput;
   private transient char aliasType;

   public RoutineAliasInfo() {
   }

   public RoutineAliasInfo(String var1, int var2, String[] var3, TypeDescriptor[] var4, int[] var5, int var6, short var7, short var8, boolean var9, boolean var10) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, false, true, (TypeDescriptor)null);
   }

   public RoutineAliasInfo(String var1, int var2, String[] var3, TypeDescriptor[] var4, int[] var5, int var6, short var7, short var8, boolean var9, boolean var10, boolean var11, boolean var12, TypeDescriptor var13) {
      super(var1);
      this.parameterCount = var2;
      this.parameterNames = (String[])ArrayUtil.copy(var3);
      this.setParameterTypes(var4);
      this.parameterModes = ArrayUtil.copy(var5);
      this.dynamicResultSets = var6;
      this.parameterStyle = var7;
      this.sqlOptions = (short)(var8 & 15);
      if (var9) {
         this.sqlOptions = (short)(this.sqlOptions | 16);
      }

      if (var10) {
         this.sqlOptions = (short)(this.sqlOptions | 64);
      }

      if (var11) {
         this.sqlOptions = (short)(this.sqlOptions | 32);
      }

      this.calledOnNullInput = var12;
      this.returnType = var13;
   }

   public int getParameterCount() {
      return this.parameterCount;
   }

   public TypeDescriptor[] getParameterTypes() {
      return TypeDescriptorImpl.copyTypeDescriptors(this.parameterTypes);
   }

   public void setParameterTypes(TypeDescriptor[] var1) {
      this.parameterTypes = TypeDescriptorImpl.copyTypeDescriptors(var1);
   }

   public int[] getParameterModes() {
      return ArrayUtil.copy(this.parameterModes);
   }

   public String[] getParameterNames() {
      return (String[])ArrayUtil.copy(this.parameterNames);
   }

   public int getMaxDynamicResultSets() {
      return this.dynamicResultSets;
   }

   public short getParameterStyle() {
      return this.parameterStyle;
   }

   public short getSQLAllowed() {
      return (short)(this.sqlOptions & 15);
   }

   public boolean isDeterministic() {
      return (this.sqlOptions & 16) != 0;
   }

   public boolean hasVarargs() {
      return (this.sqlOptions & 64) != 0;
   }

   public boolean hasDefinersRights() {
      return (this.sqlOptions & 32) != 0;
   }

   public boolean calledOnNullInput() {
      return this.calledOnNullInput;
   }

   public TypeDescriptor getReturnType() {
      return this.returnType;
   }

   public boolean isTableFunction() {
      return this.returnType == null ? false : this.returnType.isRowMultiSet();
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.specificName = (String)var1.readObject();
      this.dynamicResultSets = var1.readInt();
      this.parameterCount = var1.readInt();
      this.parameterStyle = var1.readShort();
      this.sqlOptions = var1.readShort();
      this.returnType = getStoredType(var1.readObject());
      this.calledOnNullInput = var1.readBoolean();
      var1.readInt();
      if (this.parameterCount != 0) {
         this.parameterNames = new String[this.parameterCount];
         this.parameterTypes = new TypeDescriptor[this.parameterCount];
         ArrayUtil.readArrayItems(var1, this.parameterNames);

         for(int var2 = 0; var2 < this.parameterTypes.length; ++var2) {
            this.parameterTypes[var2] = getStoredType(var1.readObject());
         }

         this.parameterModes = ArrayUtil.readIntArray(var1);
      } else {
         this.parameterNames = null;
         this.parameterTypes = null;
         this.parameterModes = null;
      }

   }

   public static TypeDescriptor getStoredType(Object var0) {
      return var0 instanceof OldRoutineType ? ((OldRoutineType)var0).getCatalogType() : (TypeDescriptor)var0;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeObject(this.specificName);
      var1.writeInt(this.dynamicResultSets);
      var1.writeInt(this.parameterCount);
      var1.writeShort(this.parameterStyle);
      var1.writeShort(this.sqlOptions);
      var1.writeObject(this.returnType);
      var1.writeBoolean(this.calledOnNullInput);
      var1.writeInt(0);
      if (this.parameterCount != 0) {
         ArrayUtil.writeArrayItems(var1, this.parameterNames);
         ArrayUtil.writeArrayItems(var1, this.parameterTypes);
         ArrayUtil.writeIntArray(var1, this.parameterModes);
      }

   }

   public int getTypeFormatId() {
      return 451;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(100);
      var1.append(this.getMethodName());
      var1.append('(');

      for(int var2 = 0; var2 < this.parameterCount; ++var2) {
         if (var2 != 0) {
            var1.append(',');
         }

         if (this.returnType == null) {
            var1.append(parameterMode(this.parameterModes[var2]));
            var1.append(' ');
         }

         var1.append(IdUtil.normalToDelimited(this.parameterNames[var2]));
         var1.append(' ');
         var1.append(this.parameterTypes[var2].getSQLstring());
      }

      if (this.hasVarargs()) {
         var1.append(" ... ");
      }

      var1.append(')');
      if (this.returnType != null) {
         var1.append(" RETURNS " + this.returnType.getSQLstring());
      }

      var1.append(" LANGUAGE JAVA PARAMETER STYLE ");
      switch (this.parameterStyle) {
         case 0 -> var1.append("JAVA ");
         case 1 -> var1.append("DERBY_JDBC_RESULT_SET ");
         case 2 -> var1.append("DERBY ");
      }

      if (this.isDeterministic()) {
         var1.append(" DETERMINISTIC ");
      }

      if (this.hasDefinersRights()) {
         var1.append(" EXTERNAL SECURITY DEFINER ");
      }

      var1.append(SQL_CONTROL[this.getSQLAllowed()]);
      if (this.returnType == null && this.dynamicResultSets != 0) {
         var1.append(" DYNAMIC RESULT SETS ");
         var1.append(this.dynamicResultSets);
      }

      if (this.returnType != null) {
         var1.append(this.calledOnNullInput ? " CALLED " : " RETURNS NULL ");
         var1.append("ON NULL INPUT");
      }

      return var1.toString();
   }

   public static String parameterMode(int var0) {
      switch (var0) {
         case 1:
            return "IN";
         case 2:
            return "INOUT";
         case 3:
         default:
            return "UNKNOWN";
         case 4:
            return "OUT";
      }
   }

   public void setCollationTypeForAllStringTypes(int var1) {
      if (this.parameterCount != 0) {
         for(int var2 = 0; var2 < this.parameterTypes.length; ++var2) {
            this.parameterTypes[var2] = DataTypeDescriptor.getCatalogType(this.parameterTypes[var2], var1);
         }
      }

      if (this.returnType != null) {
         this.returnType = DataTypeDescriptor.getCatalogType(this.returnType, var1);
      }

   }
}

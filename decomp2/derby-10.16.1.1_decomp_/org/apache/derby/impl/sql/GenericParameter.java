package org.apache.derby.impl.sql;

import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.shared.common.error.StandardException;

final class GenericParameter {
   private static int DECIMAL_PARAMETER_DEFAULT_PRECISION = 31;
   private static int DECIMAL_PARAMETER_DEFAULT_SCALE = 15;
   private final GenericParameterValueSet pvs;
   private DataValueDescriptor value;
   int jdbcTypeId;
   String declaredClassName;
   short parameterMode;
   boolean isSet;
   private final boolean isReturnOutputParameter;
   int registerOutType = 0;
   int registerOutScale = -1;
   int registerOutPrecision = -1;

   GenericParameter(GenericParameterValueSet var1, boolean var2) {
      this.pvs = var1;
      this.parameterMode = (short)((this.isReturnOutputParameter = var2) ? 4 : 1);
   }

   public GenericParameter getClone(GenericParameterValueSet var1) {
      GenericParameter var2 = new GenericParameter(var1, this.isReturnOutputParameter);
      var2.initialize(this.getValue().cloneValue(false), this.jdbcTypeId, this.declaredClassName);
      var2.isSet = true;
      return var2;
   }

   void initialize(DataValueDescriptor var1, int var2, String var3) {
      this.value = var1;
      this.jdbcTypeId = var2;
      this.declaredClassName = var3;
   }

   void clear() {
      this.isSet = false;
   }

   DataValueDescriptor getValue() {
      return this.value;
   }

   void setOutParameter(int var1, int var2) throws StandardException {
      if (this.registerOutType != var1 || var2 != this.registerOutScale) {
         switch (this.parameterMode) {
            case 0:
            case 1:
            case 3:
            default:
               throw StandardException.newException("XCL22.S", new Object[]{this.getJDBCParameterNumberStr()});
            case 2:
            case 4:
               if (!DataTypeDescriptor.isJDBCTypeEquivalent(this.jdbcTypeId, var1)) {
                  throw this.throwInvalidOutParamMap(var1);
               } else {
                  this.registerOutType = var1;
               }
         }
      }
   }

   private StandardException throwInvalidOutParamMap(int var1) {
      String var2 = Util.typeName(var1);
      TypeId var3 = TypeId.getBuiltInTypeId(this.jdbcTypeId);
      String var4 = var3 == null ? this.declaredClassName : var3.getSQLTypeName();
      StandardException var5 = StandardException.newException("XCL25.S", new Object[]{this.getJDBCParameterNumberStr(), var2, var4});
      return var5;
   }

   void validate() throws StandardException {
      switch (this.parameterMode) {
         case 2:
         case 4:
            if (this.registerOutType == 0) {
               throw StandardException.newException("07004", new Object[]{this.getJDBCParameterNumberStr(), RoutineAliasInfo.parameterMode(this.parameterMode)});
            }
         case 0:
         case 1:
         case 3:
         default:
      }
   }

   int getScale() {
      return this.registerOutScale == -1 ? 0 : this.registerOutScale;
   }

   int getPrecision() {
      return this.registerOutPrecision;
   }

   String getJDBCParameterNumberStr() {
      return Integer.toString(this.pvs.getParameterNumber(this));
   }

   public String toString() {
      if (this.value == null) {
         return "null";
      } else {
         try {
            return this.value.getTraceString();
         } catch (StandardException var2) {
            return "unexpected exception from getTraceString() - " + var2;
         }
      }
   }
}

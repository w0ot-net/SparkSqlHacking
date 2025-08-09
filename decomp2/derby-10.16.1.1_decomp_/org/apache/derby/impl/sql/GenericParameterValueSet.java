package org.apache.derby.impl.sql;

import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.UserDataValue;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.shared.common.error.StandardException;

final class GenericParameterValueSet implements ParameterValueSet {
   private final GenericParameter[] parms;
   final ClassInspector ci;
   private final boolean hasReturnOutputParam;

   GenericParameterValueSet(ClassInspector var1, int var2, boolean var3) {
      this.ci = var1;
      this.hasReturnOutputParam = var3;
      this.parms = new GenericParameter[var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         this.parms[var4] = new GenericParameter(this, var3 && var4 == 0);
      }

   }

   private GenericParameterValueSet(int var1, GenericParameterValueSet var2) {
      this.hasReturnOutputParam = var2.hasReturnOutputParam;
      this.ci = var2.ci;
      this.parms = new GenericParameter[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         this.parms[var3] = var2.getGenericParameter(var3).getClone(this);
      }

   }

   public void initialize(DataTypeDescriptor[] var1) throws StandardException {
      for(int var2 = 0; var2 < this.parms.length; ++var2) {
         DataTypeDescriptor var3 = var1[var2];
         this.parms[var2].initialize(var3.getNull(), var3.getJDBCTypeId(), var3.getTypeId().getCorrespondingJavaTypeName());
      }

   }

   public void setParameterMode(int var1, int var2) {
      this.parms[var1].parameterMode = (short)var2;
   }

   public void clearParameters() {
      for(int var1 = 0; var1 < this.parms.length; ++var1) {
         this.parms[var1].clear();
      }

   }

   public int getParameterCount() {
      return this.parms.length;
   }

   public DataValueDescriptor getParameter(int var1) throws StandardException {
      try {
         return this.parms[var1].getValue();
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.checkPosition(var1);
         return null;
      }
   }

   public DataValueDescriptor getParameterForSet(int var1) throws StandardException {
      try {
         GenericParameter var2 = this.parms[var1];
         if (var2.parameterMode == 4) {
            throw StandardException.newException("XCL27.S", new Object[0]);
         } else {
            var2.isSet = true;
            return var2.getValue();
         }
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.checkPosition(var1);
         return null;
      }
   }

   public DataValueDescriptor getParameterForGet(int var1) throws StandardException {
      try {
         GenericParameter var2 = this.parms[var1];
         switch (var2.parameterMode) {
            case 0:
            case 1:
               throw StandardException.newException("XCL26.S", new Object[]{Integer.toString(var1 + 1)});
            default:
               return var2.getValue();
         }
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.checkPosition(var1);
         return null;
      }
   }

   public void setParameterAsObject(int var1, Object var2) throws StandardException {
      UserDataValue var3 = (UserDataValue)this.getParameterForSet(var1);
      GenericParameter var4 = this.parms[var1];
      if (var2 != null) {
         ClassNotFoundException var6 = null;

         boolean var5;
         try {
            var5 = !this.ci.instanceOf(var4.declaredClassName, var2);
         } catch (ClassNotFoundException var8) {
            var6 = var8;
            var5 = true;
         }

         if (var5) {
            throw StandardException.newException("XCL12.S", var6, new Object[]{ClassInspector.readableClassName(var2.getClass()), var4.declaredClassName});
         }
      }

      var3.setValue(var2);
   }

   public boolean allAreSet() {
      for(int var1 = 0; var1 < this.parms.length; ++var1) {
         GenericParameter var2 = this.parms[var1];
         if (!var2.isSet) {
            switch (var2.parameterMode) {
               case 0:
               case 1:
               case 2:
                  return false;
               case 3:
               case 4:
            }
         }
      }

      return true;
   }

   public void transferDataValues(ParameterValueSet var1) throws StandardException {
      int var2 = var1.hasReturnOutputParameter() ? 1 : 0;

      for(int var3 = var2; var3 < this.parms.length; ++var3) {
         GenericParameter var4 = this.parms[var3];
         if (var4.registerOutType != 0) {
            var1.registerOutParameter(var3, var4.registerOutType, var4.registerOutScale);
         }

         if (var4.isSet) {
            DataValueDescriptor var5 = var4.getValue();
            Object var6 = null;
            if (var5.hasStream()) {
               var1.getParameterForSet(var3).setValue(var5.getStream(), -1);
            } else {
               var1.getParameterForSet(var3).setValue(var5);
            }
         }
      }

   }

   GenericParameter getGenericParameter(int var1) {
      return this.parms[var1];
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();

      for(int var2 = 0; var2 < this.parms.length; ++var2) {
         var1.append("begin parameter #" + (var2 + 1) + ": ");
         var1.append(this.parms[var2].toString());
         var1.append(" :end parameter ");
      }

      return var1.toString();
   }

   private void checkPosition(int var1) throws StandardException {
      if (var1 < 0 || var1 >= this.parms.length) {
         if (this.parms.length == 0) {
            throw StandardException.newException("07009", new Object[0]);
         } else {
            throw StandardException.newException("XCL13.S", new Object[]{String.valueOf(var1 + 1), String.valueOf(this.parms.length)});
         }
      }
   }

   public ParameterValueSet getClone() {
      return new GenericParameterValueSet(this.parms.length, this);
   }

   public void registerOutParameter(int var1, int var2, int var3) throws StandardException {
      this.checkPosition(var1);
      Util.checkSupportedRaiseStandard(var2);
      this.parms[var1].setOutParameter(var2, var3);
   }

   public void validate() throws StandardException {
      for(int var1 = 0; var1 < this.parms.length; ++var1) {
         this.parms[var1].validate();
      }

   }

   public int getParameterNumber(GenericParameter var1) {
      for(int var2 = 0; var2 < this.parms.length; ++var2) {
         if (this.parms[var2] == var1) {
            return var2 + 1;
         }
      }

      return 0;
   }

   public boolean checkNoDeclaredOutputParameters() {
      boolean var1 = false;

      for(int var2 = 0; var2 < this.parms.length; ++var2) {
         GenericParameter var3 = this.parms[var2];
         switch (var3.parameterMode) {
            case 0:
               var3.parameterMode = 1;
            case 1:
            case 3:
            default:
               break;
            case 2:
            case 4:
               var1 = true;
         }
      }

      return var1;
   }

   public short getParameterMode(int var1) {
      short var2 = this.parms[var1 - 1].parameterMode;
      return var2;
   }

   public boolean hasReturnOutputParameter() {
      return this.hasReturnOutputParam;
   }

   public DataValueDescriptor getReturnValueForSet() throws StandardException {
      this.checkPosition(0);
      return this.parms[0].getValue();
   }

   public int getScale(int var1) {
      return this.parms[var1 - 1].getScale();
   }

   public int getPrecision(int var1) {
      return this.parms[var1 - 1].getPrecision();
   }
}

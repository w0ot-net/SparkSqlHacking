package org.apache.derby.impl.sql.compile;

import java.math.BigDecimal;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.iapi.types.SQLDecimal;
import org.apache.derby.iapi.types.SQLDouble;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLReal;
import org.apache.derby.iapi.types.SQLSmallint;
import org.apache.derby.iapi.types.SQLTinyint;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class NumericConstantNode extends ConstantNode {
   static final int K_TINYINT = 0;
   static final int K_SMALLINT = 1;
   static final int K_INT = 2;
   static final int K_BIGINT = 3;
   static final int K_DECIMAL = 4;
   static final int K_DOUBLE = 5;
   static final int K_REAL = 6;
   final int kind;

   NumericConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var2);
      this.setType(var1, this.getPrecision(var1, (Number)null), this.getScale(var1, (Object)null), true, this.getMaxWidth(var1, (Object)null));
      this.kind = this.getKind(var1);
   }

   NumericConstantNode(TypeId var1, Number var2, ContextManager var3) throws StandardException {
      super(var3);
      this.kind = this.getKind(var1);
      this.setType(var1, this.getPrecision(var1, var2), this.getScale(var1, var2), false, this.getMaxWidth(var1, var2));
      this.setValue(var1, var2);
   }

   private int getPrecision(TypeId var1, Number var2) throws StandardException {
      switch (var1.getJDBCTypeId()) {
         case -6:
            return 5;
         case -5:
            return 19;
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 1:
         case 2:
         case 6:
         default:
            return 0;
         case 3:
            if (var2 != null) {
               SQLDecimal var3 = new SQLDecimal((BigDecimal)var2);
               return var3.getDecimalValuePrecision();
            }

            return 31;
         case 4:
            return 10;
         case 5:
            return 5;
         case 7:
            return 23;
         case 8:
            return 52;
      }
   }

   private int getScale(TypeId var1, Object var2) throws StandardException {
      switch (var1.getJDBCTypeId()) {
         case -6:
            return 0;
         case -5:
            return 0;
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 1:
         case 2:
         case 6:
         default:
            return 0;
         case 3:
            if (var2 != null) {
               SQLDecimal var3 = new SQLDecimal((BigDecimal)var2);
               return var3.getDecimalValueScale();
            }

            return 31;
         case 4:
            return 0;
         case 5:
            return 0;
         case 7:
            return 0;
         case 8:
            return 0;
      }
   }

   private int getMaxWidth(TypeId var1, Object var2) throws StandardException {
      switch (var1.getJDBCTypeId()) {
         case -6:
            return var2 != null ? 2 : 0;
         case -5:
            return var2 != null ? 8 : 0;
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 1:
         case 2:
         case 6:
         default:
            return 0;
         case 3:
            if (var2 != null) {
               SQLDecimal var3 = new SQLDecimal((BigDecimal)var2);
               int var4 = var3.getDecimalValuePrecision();
               int var5 = var3.getDecimalValueScale();
               return DataTypeUtilities.computeMaxWidth(var4, var5);
            }

            return 31;
         case 4:
            return var2 != null ? 4 : 0;
         case 5:
            return var2 != null ? 2 : 0;
         case 7:
            return var2 != null ? 4 : 0;
         case 8:
            return var2 != null ? 8 : 0;
      }
   }

   private int getKind(TypeId var1) {
      switch (var1.getJDBCTypeId()) {
         case -6:
            return 0;
         case -5:
            return 3;
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 1:
         case 2:
         case 6:
         default:
            return -1;
         case 3:
            return 4;
         case 4:
            return 2;
         case 5:
            return 1;
         case 7:
            return 6;
         case 8:
            return 5;
      }
   }

   private void setValue(TypeId var1, Number var2) throws StandardException {
      switch (var1.getJDBCTypeId()) {
         case -6:
            this.setValue(new SQLTinyint((Byte)var2));
            break;
         case -5:
            this.setValue(new SQLLongint((Long)var2));
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 1:
         case 2:
         case 6:
         default:
            break;
         case 3:
            this.setValue(new SQLDecimal((BigDecimal)var2));
            break;
         case 4:
            this.setValue(new SQLInteger((Integer)var2));
            break;
         case 5:
            this.setValue(new SQLSmallint((Short)var2));
            break;
         case 7:
            this.setValue(new SQLReal((Float)var2));
            break;
         case 8:
            this.setValue(new SQLDouble((Double)var2));
      }

   }

   Object getConstantValueAsObject() throws StandardException {
      return this.value.getObject();
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      switch (this.kind) {
         case 0:
            var2.push(this.value.getByte());
            break;
         case 1:
            var2.push(this.value.getShort());
            break;
         case 2:
            var2.push(this.value.getInt());
            break;
         case 3:
            var2.push(this.value.getLong());
            break;
         case 4:
            var2.pushNewStart("java.math.BigDecimal");
            var2.push(this.value.getString());
            var2.pushNewComplete(1);
            break;
         case 5:
            var2.push(this.value.getDouble());
            break;
         case 6:
            var2.push(this.value.getFloat());
      }

   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((NumericConstantNode)var1).kind == this.kind;
   }
}

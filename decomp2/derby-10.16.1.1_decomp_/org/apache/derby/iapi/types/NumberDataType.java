package org.apache.derby.iapi.types;

import java.math.BigDecimal;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

public abstract class NumberDataType extends DataType implements NumberDataValue {
   static final BigDecimal MAXLONG_PLUS_ONE;
   static final BigDecimal MINLONG_MINUS_ONE;

   public final NumberDataValue absolute(NumberDataValue var1) throws StandardException {
      if (this.isNegative()) {
         return this.minus(var1);
      } else {
         if (var1 == null) {
            var1 = (NumberDataValue)this.getNewNull();
         }

         var1.setValue(this);
         return var1;
      }
   }

   public NumberDataValue sqrt(NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = (NumberDataValue)this.getNewNull();
      }

      if (this.isNull()) {
         var1.setToNull();
         return var1;
      } else {
         double var2 = this.getDouble();
         if (this.isNegative()) {
            if (var2 != (double)-0.0F) {
               throw StandardException.newException("22013", new Object[]{this});
            }

            var2 = (double)0.0F;
         }

         var1.setValue(Math.sqrt(var2));
         return var1;
      }
   }

   public NumberDataValue plus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = (NumberDataValue)this.getNewNull();
      }

      if (!var1.isNull() && !var2.isNull()) {
         int var4 = var1.getInt();
         int var5 = var2.getInt();
         int var6 = var4 + var5;
         if (var4 < 0 == var5 < 0 && var4 < 0 != var6 < 0) {
            throw this.outOfRange();
         } else {
            var3.setValue(var6);
            return var3;
         }
      } else {
         var3.setToNull();
         return var3;
      }
   }

   public NumberDataValue minus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = (NumberDataValue)this.getNewNull();
      }

      if (!var1.isNull() && !var2.isNull()) {
         int var4 = var1.getInt() - var2.getInt();
         if (var1.getInt() < 0 != var2.getInt() < 0 && var1.getInt() < 0 != var4 < 0) {
            throw this.outOfRange();
         } else {
            var3.setValue(var4);
            return var3;
         }
      } else {
         var3.setToNull();
         return var3;
      }
   }

   public NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      if (var3 == null) {
         var3 = (NumberDataValue)this.getNewNull();
      }

      if (!var1.isNull() && !var2.isNull()) {
         int var4 = var2.getInt();
         if (var4 == 0) {
            throw StandardException.newException("22012", new Object[0]);
         } else {
            var3.setValue(var1.getInt() / var4);
            return var3;
         }
      } else {
         var3.setToNull();
         return var3;
      }
   }

   public NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3, int var4) throws StandardException {
      return this.divide(var1, var2, var3);
   }

   public NumberDataValue mod(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException {
      return null;
   }

   public final int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else {
         boolean var2 = this.isNull();
         boolean var3 = var1.isNull();
         if (!var2 && !var3) {
            return this.typeCompare(var1);
         } else if (!var2) {
            return -1;
         } else {
            return !var3 ? 1 : 0;
         }
      }
   }

   protected abstract int typeCompare(DataValueDescriptor var1) throws StandardException;

   public final boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      return var3 || !this.isNull() && !var2.isNull() ? super.compare(var1, var2, var3, var4) : var4;
   }

   protected abstract boolean isNegative();

   public void setValue(short var1) throws StandardException {
      this.setValue((int)var1);
   }

   public void setValue(byte var1) throws StandardException {
      this.setValue((int)var1);
   }

   public void setValue(Number var1) throws StandardException {
      if (!this.objectNull(var1)) {
         this.setValue((int)var1.intValue());
      }
   }

   void setObject(Object var1) throws StandardException {
      this.setValue((int)(Integer)var1);
   }

   public void setBigDecimal(BigDecimal var1) throws StandardException {
      if (!this.objectNull(var1)) {
         if (var1.compareTo(MINLONG_MINUS_ONE) == 1 && var1.compareTo(MAXLONG_PLUS_ONE) == -1) {
            this.setValue(var1.longValue());
         } else {
            throw StandardException.newException("22003", new Object[]{this.getTypeName()});
         }
      }
   }

   public int typeToBigDecimal() {
      return -5;
   }

   protected final boolean objectNull(Object var1) {
      if (var1 == null) {
         this.restoreToNull();
         return true;
      } else {
         return false;
      }
   }

   public static float normalizeREAL(float var0) throws StandardException {
      boolean var1 = Float.isNaN(var0) || Float.isInfinite(var0);
      if ((var0 < -3.402E38F || var0 > 3.402E38F || var0 > 0.0F && var0 < 1.175E-37F || var0 < 0.0F && var0 > -1.175E-37F) && useDB2Limits()) {
         var1 = true;
      }

      if (var1) {
         throw StandardException.newException("22003", new Object[]{"REAL"});
      } else {
         if (var0 == -0.0F) {
            var0 = 0.0F;
         }

         return var0;
      }
   }

   public static float normalizeREAL(double var0) throws StandardException {
      float var2 = (float)var0;
      boolean var3 = Double.isNaN(var0) || Double.isInfinite(var0) || var2 == 0.0F && var0 != (double)0.0F;
      if ((var0 < (double)-3.402E38F || var0 > (double)3.402E38F || var0 > (double)0.0F && var0 < (double)1.175E-37F || var0 < (double)0.0F && var0 > (double)-1.175E-37F) && useDB2Limits()) {
         var3 = true;
      }

      if (var3) {
         throw StandardException.newException("22003", new Object[]{"REAL"});
      } else {
         if (var2 == -0.0F) {
            var2 = 0.0F;
         }

         return var2;
      }
   }

   public static double normalizeDOUBLE(double var0) throws StandardException {
      boolean var2 = Double.isNaN(var0) || Double.isInfinite(var0);
      if ((var0 < -1.79769E308 || var0 > 1.79769E308 || var0 > (double)0.0F && var0 < 2.225E-307 || var0 < (double)0.0F && var0 > -2.225E-307) && useDB2Limits()) {
         var2 = true;
      }

      if (var2) {
         throw StandardException.newException("22003", new Object[]{"DOUBLE"});
      } else {
         if (var0 == (double)-0.0F) {
            var0 = (double)0.0F;
         }

         return var0;
      }
   }

   private static boolean useDB2Limits() throws StandardException {
      LanguageConnectionContext var0 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      if (var0 != null) {
         return !var0.getDataDictionary().checkVersion(220, (String)null);
      } else {
         return false;
      }
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   static {
      MAXLONG_PLUS_ONE = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.ONE);
      MINLONG_MINUS_ONE = BigDecimal.valueOf(Long.MIN_VALUE).subtract(BigDecimal.ONE);
   }
}

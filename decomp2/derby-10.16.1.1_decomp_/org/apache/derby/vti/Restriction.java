package org.apache.derby.vti;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;

public abstract class Restriction implements Serializable {
   public abstract String toSQL();

   protected String parenthesize(String var1) {
      return "( " + var1 + " )";
   }

   public static class AND extends Restriction {
      public static final long serialVersionUID = -8205388794606605844L;
      private Restriction _leftChild;
      private Restriction _rightChild;

      public AND(Restriction var1, Restriction var2) {
         this._leftChild = var1;
         this._rightChild = var2;
      }

      public Restriction getLeftChild() {
         return this._leftChild;
      }

      public Restriction getRightChild() {
         return this._rightChild;
      }

      public String toSQL() {
         String var10000 = this.parenthesize(this._leftChild.toSQL());
         return var10000 + " AND " + this.parenthesize(this._rightChild.toSQL());
      }
   }

   public static class OR extends Restriction {
      public static final long serialVersionUID = -8205388794606605844L;
      private Restriction _leftChild;
      private Restriction _rightChild;

      public OR(Restriction var1, Restriction var2) {
         this._leftChild = var1;
         this._rightChild = var2;
      }

      public Restriction getLeftChild() {
         return this._leftChild;
      }

      public Restriction getRightChild() {
         return this._rightChild;
      }

      public String toSQL() {
         String var10000 = this.parenthesize(this._leftChild.toSQL());
         return var10000 + " OR " + this.parenthesize(this._rightChild.toSQL());
      }
   }

   public static class ColumnQualifier extends Restriction {
      public static final long serialVersionUID = -8205388794606605844L;
      public static final int ORDER_OP_LESSTHAN = 0;
      public static final int ORDER_OP_EQUALS = 1;
      public static final int ORDER_OP_LESSOREQUALS = 2;
      public static final int ORDER_OP_GREATERTHAN = 3;
      public static final int ORDER_OP_GREATEROREQUALS = 4;
      public static final int ORDER_OP_ISNULL = 5;
      public static final int ORDER_OP_ISNOTNULL = 6;
      public static final int ORDER_OP_NOT_EQUALS = 7;
      private String[] OPERATOR_SYMBOLS = new String[]{"<", "=", "<=", ">", ">=", "IS NULL", "IS NOT NULL", "!="};
      private String _columnName;
      private int _comparisonOperator;
      private Object _constantOperand;

      public ColumnQualifier(String var1, int var2, Object var3) {
         this._columnName = var1;
         this._comparisonOperator = var2;
         this._constantOperand = var3;
      }

      public String getColumnName() {
         return this._columnName;
      }

      public int getComparisonOperator() {
         return this._comparisonOperator;
      }

      public Object getConstantOperand() {
         return this._constantOperand;
      }

      public String toSQL() {
         StringBuffer var1 = new StringBuffer();
         var1.append(IdUtil.normalToDelimited(this._columnName));
         String var10001 = this.OPERATOR_SYMBOLS[this._comparisonOperator];
         var1.append(" " + var10001 + " ");
         if (this._constantOperand != null) {
            var1.append(this.toEscapedString(this._constantOperand));
         }

         return var1.toString();
      }

      protected String toEscapedString(Object var1) {
         if (var1 instanceof Timestamp) {
            return "TIMESTAMP('" + var1.toString() + "')";
         } else if (var1 instanceof Date) {
            return "DATE('" + var1.toString() + "')";
         } else if (var1 instanceof Time) {
            return "TIME('" + var1.toString() + "')";
         } else if (var1 instanceof String) {
            return "'" + var1.toString() + "'";
         } else if (var1 instanceof byte[]) {
            byte[] var2 = (byte[])var1;
            return "X'" + StringUtil.toHexString(var2, 0, var2.length) + "'";
         } else {
            return var1.toString();
         }
      }
   }
}

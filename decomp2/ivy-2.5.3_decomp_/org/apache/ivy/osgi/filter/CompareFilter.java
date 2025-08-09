package org.apache.ivy.osgi.filter;

import java.util.Map;

public class CompareFilter extends OSGiFilter {
   private Operator operator;
   private final String rightValue;
   private final String leftValue;
   private boolean substring;

   public CompareFilter(String leftValue, Operator operator, String rightValue) {
      this.leftValue = leftValue;
      this.rightValue = rightValue;
      this.operator = operator;
      this.substring = operator == CompareFilter.Operator.EQUALS && rightValue.contains("*");
   }

   public String getLeftValue() {
      return this.leftValue;
   }

   public Operator getOperator() {
      return this.operator;
   }

   public String getRightValue() {
      return this.rightValue;
   }

   public void append(StringBuffer builder) {
      builder.append("(");
      builder.append(this.leftValue);
      builder.append(this.operator.toString());
      builder.append(this.rightValue);
      builder.append(")");
   }

   public boolean eval(Map properties) {
      String actualValue = (String)properties.get(this.leftValue);
      if (actualValue == null) {
         return false;
      } else if (this.operator == CompareFilter.Operator.PRESENT) {
         return true;
      } else if (this.operator == CompareFilter.Operator.APPROX) {
         return false;
      } else if (this.substring) {
         return false;
      } else {
         int diff = this.rightValue.compareTo(actualValue);
         switch (this.operator) {
            case EQUALS:
               return diff == 0;
            case GREATER_THAN:
               return diff > 0;
            case GREATER_OR_EQUAL:
               return diff >= 0;
            case LOWER_OR_EQUAL:
               return diff <= 0;
            case LOWER_THAN:
               return diff < 0;
            default:
               throw new IllegalStateException();
         }
      }
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.leftValue == null ? 0 : this.leftValue.hashCode());
      result = 31 * result + (this.operator == null ? 0 : this.operator.hashCode());
      result = 31 * result + (this.rightValue == null ? 0 : this.rightValue.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof CompareFilter)) {
         return false;
      } else {
         CompareFilter other = (CompareFilter)obj;
         if (this.leftValue == null) {
            if (other.leftValue != null) {
               return false;
            }
         } else if (!this.leftValue.equals(other.leftValue)) {
            return false;
         }

         if (this.operator == null) {
            if (other.operator != null) {
               return false;
            }
         } else if (!this.operator.equals(other.operator)) {
            return false;
         }

         return this.rightValue == null ? other.rightValue == null : this.rightValue.equals(other.rightValue);
      }
   }

   public static enum Operator {
      EQUALS("="),
      LOWER_THAN("<"),
      LOWER_OR_EQUAL("<="),
      GREATER_THAN(">"),
      GREATER_OR_EQUAL(">="),
      APPROX("~="),
      PRESENT("=*");

      private String op;

      private Operator(String op) {
         this.op = op;
      }

      public String toString() {
         return this.op;
      }
   }
}

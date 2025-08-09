package org.apache.parquet.filter2.predicate;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.parquet.Preconditions;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.io.api.Binary;

public final class Operators {
   private Operators() {
   }

   public abstract static class Column implements Serializable {
      private final ColumnPath columnPath;
      private final Class columnType;

      protected Column(ColumnPath columnPath, Class columnType) {
         this.columnPath = (ColumnPath)Objects.requireNonNull(columnPath, "columnPath cannot be null");
         this.columnType = (Class)Objects.requireNonNull(columnType, "columnType cannot be null");
      }

      public Class getColumnType() {
         return this.columnType;
      }

      public ColumnPath getColumnPath() {
         return this.columnPath;
      }

      public String toString() {
         return "column(" + this.columnPath.toDotString() + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Column column = (Column)o;
            if (!this.columnType.equals(column.columnType)) {
               return false;
            } else {
               return this.columnPath.equals(column.columnPath);
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.columnPath.hashCode();
         result = 31 * result + this.columnType.hashCode();
         return result;
      }
   }

   public static final class IntColumn extends Column implements SupportsLtGt {
      IntColumn(ColumnPath columnPath) {
         super(columnPath, Integer.class);
      }
   }

   public static final class LongColumn extends Column implements SupportsLtGt {
      LongColumn(ColumnPath columnPath) {
         super(columnPath, Long.class);
      }
   }

   public static final class DoubleColumn extends Column implements SupportsLtGt {
      DoubleColumn(ColumnPath columnPath) {
         super(columnPath, Double.class);
      }
   }

   public static final class FloatColumn extends Column implements SupportsLtGt {
      FloatColumn(ColumnPath columnPath) {
         super(columnPath, Float.class);
      }
   }

   public static final class BooleanColumn extends Column implements SupportsEqNotEq {
      BooleanColumn(ColumnPath columnPath) {
         super(columnPath, Boolean.class);
      }
   }

   public static final class BinaryColumn extends Column implements SupportsLtGt {
      BinaryColumn(ColumnPath columnPath) {
         super(columnPath, Binary.class);
      }
   }

   abstract static class SingleColumnFilterPredicate implements FilterPredicate, Serializable {
      abstract Column getColumn();
   }

   abstract static class ColumnFilterPredicate extends SingleColumnFilterPredicate {
      private final Column column;
      private final Comparable value;

      protected ColumnFilterPredicate(Column column, Comparable value) {
         this.column = (Column)Objects.requireNonNull(column, "column cannot be null");
         this.value = value;
      }

      public Column getColumn() {
         return this.column;
      }

      public Comparable getValue() {
         return this.value;
      }

      public String toString() {
         return this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "(" + this.column.getColumnPath().toDotString() + ", " + this.value + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ColumnFilterPredicate that = (ColumnFilterPredicate)o;
            if (!this.column.equals(that.column)) {
               return false;
            } else {
               if (this.value != null) {
                  if (!this.value.equals(that.value)) {
                     return false;
                  }
               } else if (that.value != null) {
                  return false;
               }

               return true;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.column.hashCode();
         result = 31 * result + (this.value != null ? this.value.hashCode() : 0);
         result = 31 * result + this.getClass().hashCode();
         return result;
      }
   }

   public static final class Eq extends ColumnFilterPredicate {
      public Eq(Column column, Comparable value) {
         super(column, value);
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class NotEq extends ColumnFilterPredicate {
      NotEq(Column column, Comparable value) {
         super(column, value);
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class Lt extends ColumnFilterPredicate {
      Lt(Column column, Comparable value) {
         super(column, (Comparable)Objects.requireNonNull(value, "value cannot be null"));
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class LtEq extends ColumnFilterPredicate {
      LtEq(Column column, Comparable value) {
         super(column, (Comparable)Objects.requireNonNull(value, "value cannot be null"));
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class Gt extends ColumnFilterPredicate {
      Gt(Column column, Comparable value) {
         super(column, (Comparable)Objects.requireNonNull(value, "value cannot be null"));
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class GtEq extends ColumnFilterPredicate {
      GtEq(Column column, Comparable value) {
         super(column, (Comparable)Objects.requireNonNull(value, "value cannot be null"));
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public abstract static class SetColumnFilterPredicate extends SingleColumnFilterPredicate {
      private final Column column;
      private final Set values;

      protected SetColumnFilterPredicate(Column column, Set values) {
         this.column = (Column)Objects.requireNonNull(column, "column cannot be null");
         this.values = (Set)Objects.requireNonNull(values, "values cannot be null");
         Preconditions.checkArgument(!values.isEmpty(), "values in SetColumnFilterPredicate shouldn't be empty!");
      }

      public Column getColumn() {
         return this.column;
      }

      public Set getValues() {
         return this.values;
      }

      public String toString() {
         String name = this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH);
         StringBuilder str = new StringBuilder();
         str.append(name).append("(").append(this.column.getColumnPath().toDotString()).append(", ");
         int iter = 0;

         for(Comparable value : this.values) {
            if (iter >= 100) {
               break;
            }

            str.append(value).append(", ");
            ++iter;
         }

         int length = str.length();
         str = this.values.size() <= 100 ? str.delete(length - 2, length) : str.append("...");
         return str.append(")").toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            SetColumnFilterPredicate<?> that = (SetColumnFilterPredicate)o;
            return this.column.equals(that.column) && this.values.equals(that.values);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.column, this.values});
      }
   }

   public static final class In extends SetColumnFilterPredicate {
      public In(Column column, Set values) {
         super(column, values);
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   private static class DoesNotContain extends Contains {
      Contains underlying;

      protected DoesNotContain(Contains underlying) {
         super(underlying.getColumn());
         this.underlying = underlying;
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit((Contains)this);
      }

      public Object filter(FilterPredicate.Visitor visitor, BiFunction andBehavior, BiFunction orBehavior, Function notBehavior) {
         return notBehavior.apply(visitor.visit(this.underlying));
      }

      public String toString() {
         return "not(" + this.underlying.toString() + ")";
      }
   }

   public abstract static class Contains implements FilterPredicate, Serializable {
      private final Column column;

      protected Contains(Column column) {
         this.column = (Column)Objects.requireNonNull(column, "column cannot be null");
      }

      static Contains of(SingleColumnFilterPredicate pred) {
         return new ContainsColumnPredicate(pred);
      }

      public Column getColumn() {
         return this.column;
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }

      public abstract Object filter(FilterPredicate.Visitor var1, BiFunction var2, BiFunction var3, Function var4);

      Contains and(FilterPredicate other) {
         return new ContainsComposedPredicate(this, (Contains)other, Operators.ContainsComposedPredicate.Combinator.AND);
      }

      Contains or(FilterPredicate other) {
         return new ContainsComposedPredicate(this, (Contains)other, Operators.ContainsComposedPredicate.Combinator.OR);
      }

      Contains not() {
         return new DoesNotContain(this);
      }
   }

   private static class ContainsComposedPredicate extends Contains {
      private final Contains left;
      private final Contains right;
      private final Combinator combinator;

      ContainsComposedPredicate(Contains left, Contains right, Combinator combinator) {
         super(((Contains)Objects.requireNonNull(left, "left predicate cannot be null")).getColumn());
         if (!left.getColumn().columnPath.equals(((Contains)Objects.requireNonNull(right, "right predicate cannot be null")).getColumn().columnPath)) {
            throw new IllegalArgumentException("Composed Contains predicates must reference the same column name; found [" + left.getColumn().columnPath.toDotString() + ", " + right.getColumn().columnPath.toDotString() + "]");
         } else {
            this.left = left;
            this.right = right;
            this.combinator = combinator;
         }
      }

      public Object filter(FilterPredicate.Visitor visitor, BiFunction andBehavior, BiFunction orBehavior, Function notBehavior) {
         R filterLeft = (R)this.left.filter(visitor, andBehavior, orBehavior, notBehavior);
         R filterRight = (R)this.right.filter(visitor, andBehavior, orBehavior, notBehavior);
         return this.combinator == Operators.ContainsComposedPredicate.Combinator.AND ? andBehavior.apply(filterLeft, filterRight) : orBehavior.apply(filterLeft, filterRight);
      }

      public String toString() {
         return this.combinator.toString().toLowerCase() + "(" + this.left + ", " + this.right + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ContainsComposedPredicate<T> that = (ContainsComposedPredicate)o;
            return this.left.equals(that.left) && this.right.equals(that.right);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.getClass().getName(), this.left, this.right});
      }

      private static enum Combinator {
         AND,
         OR;
      }
   }

   private static class ContainsColumnPredicate extends Contains {
      private final SingleColumnFilterPredicate underlying;

      ContainsColumnPredicate(SingleColumnFilterPredicate underlying) {
         super(underlying.getColumn());
         if ((!(underlying instanceof ColumnFilterPredicate) || ((ColumnFilterPredicate)underlying).getValue() != null) && (!(underlying instanceof SetColumnFilterPredicate) || !((SetColumnFilterPredicate)underlying).getValues().contains((Object)null))) {
            this.underlying = underlying;
         } else {
            throw new IllegalArgumentException("Contains predicate does not support null element value(s)");
         }
      }

      public String toString() {
         String name = Contains.class.getSimpleName().toLowerCase(Locale.ENGLISH);
         return name + "(" + this.underlying.toString() + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ContainsColumnPredicate<T, U> that = (ContainsColumnPredicate)o;
            return this.underlying.equals(that.underlying);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.getClass().getName(), this.underlying});
      }

      public Object filter(FilterPredicate.Visitor visitor, BiFunction andBehavior, BiFunction orBehavior, Function notBehavior) {
         return this.underlying.accept(visitor);
      }
   }

   public static final class NotIn extends SetColumnFilterPredicate {
      NotIn(Column column, Set values) {
         super(column, values);
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   private abstract static class BinaryLogicalFilterPredicate implements FilterPredicate, Serializable {
      private final FilterPredicate left;
      private final FilterPredicate right;

      protected BinaryLogicalFilterPredicate(FilterPredicate left, FilterPredicate right) {
         this.left = (FilterPredicate)Objects.requireNonNull(left, "left cannot be null");
         this.right = (FilterPredicate)Objects.requireNonNull(right, "right cannot be null");
      }

      public FilterPredicate getLeft() {
         return this.left;
      }

      public FilterPredicate getRight() {
         return this.right;
      }

      public String toString() {
         return this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "(" + this.left + ", " + this.right + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            BinaryLogicalFilterPredicate that = (BinaryLogicalFilterPredicate)o;
            if (!this.left.equals(that.left)) {
               return false;
            } else {
               return this.right.equals(that.right);
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.left.hashCode();
         result = 31 * result + this.right.hashCode();
         result = 31 * result + this.getClass().hashCode();
         return result;
      }
   }

   public static final class And extends BinaryLogicalFilterPredicate {
      And(FilterPredicate left, FilterPredicate right) {
         super(left, right);
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class Or extends BinaryLogicalFilterPredicate {
      Or(FilterPredicate left, FilterPredicate right) {
         super(left, right);
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static class Not implements FilterPredicate, Serializable {
      private final FilterPredicate predicate;

      Not(FilterPredicate predicate) {
         this.predicate = (FilterPredicate)Objects.requireNonNull(predicate, "predicate cannot be null");
      }

      public FilterPredicate getPredicate() {
         return this.predicate;
      }

      public String toString() {
         return "not(" + this.predicate + ")";
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Not not = (Not)o;
            return this.predicate.equals(not.predicate);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.predicate.hashCode() * 31 + this.getClass().hashCode();
      }
   }

   public abstract static class UserDefined implements FilterPredicate, Serializable {
      protected final Column column;

      UserDefined(Column column) {
         this.column = (Column)Objects.requireNonNull(column, "column cannot be null");
      }

      public Column getColumn() {
         return this.column;
      }

      public abstract UserDefinedPredicate getUserDefinedPredicate();

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class UserDefinedByClass extends UserDefined {
      private final Class udpClass;
      private static final String INSTANTIATION_ERROR_MESSAGE = "Could not instantiate custom filter: %s. User defined predicates must be static classes with a default constructor.";

      UserDefinedByClass(Column column, Class udpClass) {
         super(column);
         this.udpClass = (Class)Objects.requireNonNull(udpClass, "udpClass cannot be null");
         this.getUserDefinedPredicate();
      }

      public Class getUserDefinedPredicateClass() {
         return this.udpClass;
      }

      public UserDefinedPredicate getUserDefinedPredicate() {
         try {
            return (UserDefinedPredicate)this.udpClass.newInstance();
         } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(String.format("Could not instantiate custom filter: %s. User defined predicates must be static classes with a default constructor.", this.udpClass), e);
         }
      }

      public String toString() {
         return this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "(" + this.column.getColumnPath().toDotString() + ", " + this.udpClass.getName() + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            UserDefinedByClass that = (UserDefinedByClass)o;
            if (!this.column.equals(that.column)) {
               return false;
            } else {
               return this.udpClass.equals(that.udpClass);
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.column.hashCode();
         result = 31 * result + this.udpClass.hashCode();
         result = result * 31 + this.getClass().hashCode();
         return result;
      }
   }

   public static final class UserDefinedByInstance extends UserDefined {
      private final UserDefinedPredicate udpInstance;

      UserDefinedByInstance(Column column, UserDefinedPredicate udpInstance) {
         super(column);
         this.udpInstance = (UserDefinedPredicate)Objects.requireNonNull(udpInstance, "udpInstance cannot be null");
      }

      public UserDefinedPredicate getUserDefinedPredicate() {
         return this.udpInstance;
      }

      public String toString() {
         return this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "(" + this.column.getColumnPath().toDotString() + ", " + this.udpInstance + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            UserDefinedByInstance that = (UserDefinedByInstance)o;
            if (!this.column.equals(that.column)) {
               return false;
            } else {
               return this.udpInstance.equals(that.udpInstance);
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.column.hashCode();
         result = 31 * result + this.udpInstance.hashCode();
         result = result * 31 + this.getClass().hashCode();
         return result;
      }
   }

   public static final class LogicalNotUserDefined implements FilterPredicate, Serializable {
      private final UserDefined udp;

      LogicalNotUserDefined(UserDefined userDefined) {
         this.udp = (UserDefined)Objects.requireNonNull(userDefined, "userDefined cannot be null");
      }

      public UserDefined getUserDefined() {
         return this.udp;
      }

      public Object accept(FilterPredicate.Visitor visitor) {
         return visitor.visit(this);
      }

      public String toString() {
         return "inverted(" + this.udp + ")";
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            LogicalNotUserDefined that = (LogicalNotUserDefined)o;
            return this.udp.equals(that.udp);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.udp.hashCode();
         result = result * 31 + this.getClass().hashCode();
         return result;
      }
   }

   public interface SupportsEqNotEq {
   }

   public interface SupportsLtGt extends SupportsEqNotEq {
   }
}

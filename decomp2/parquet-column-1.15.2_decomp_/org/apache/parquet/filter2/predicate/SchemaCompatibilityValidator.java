package org.apache.parquet.filter2.predicate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.schema.MessageType;

public class SchemaCompatibilityValidator implements FilterPredicate.Visitor {
   private final Map columnTypesEncountered = new HashMap();
   private final Map columnsAccordingToSchema = new HashMap();

   public static void validate(FilterPredicate predicate, MessageType schema) {
      Objects.requireNonNull(predicate, "predicate cannot be null");
      Objects.requireNonNull(schema, "schema cannot be null");
      predicate.accept(new SchemaCompatibilityValidator(schema));
   }

   private SchemaCompatibilityValidator(MessageType schema) {
      for(ColumnDescriptor cd : schema.getColumns()) {
         ColumnPath columnPath = ColumnPath.get(cd.getPath());
         this.columnsAccordingToSchema.put(columnPath, cd);
      }

   }

   public Void visit(Operators.Eq pred) {
      this.validateColumnFilterPredicate((Operators.ColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.NotEq pred) {
      this.validateColumnFilterPredicate((Operators.ColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.Lt pred) {
      this.validateColumnFilterPredicate((Operators.ColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.LtEq pred) {
      this.validateColumnFilterPredicate((Operators.ColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.Gt pred) {
      this.validateColumnFilterPredicate((Operators.ColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.GtEq pred) {
      this.validateColumnFilterPredicate((Operators.ColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.In pred) {
      this.validateColumnFilterPredicate((Operators.SetColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.NotIn pred) {
      this.validateColumnFilterPredicate((Operators.SetColumnFilterPredicate)pred);
      return null;
   }

   public Void visit(Operators.Contains pred) {
      this.validateColumnFilterPredicate(pred);
      return null;
   }

   public Void visit(Operators.And and) {
      and.getLeft().accept(this);
      and.getRight().accept(this);
      return null;
   }

   public Void visit(Operators.Or or) {
      or.getLeft().accept(this);
      or.getRight().accept(this);
      return null;
   }

   public Void visit(Operators.Not not) {
      not.getPredicate().accept(this);
      return null;
   }

   public Void visit(Operators.UserDefined udp) {
      this.validateColumn(udp.getColumn());
      return null;
   }

   public Void visit(Operators.LogicalNotUserDefined udp) {
      return (Void)udp.getUserDefined().accept(this);
   }

   private void validateColumnFilterPredicate(Operators.ColumnFilterPredicate pred) {
      this.validateColumn(pred.getColumn());
   }

   private void validateColumnFilterPredicate(Operators.SetColumnFilterPredicate pred) {
      this.validateColumn(pred.getColumn());
   }

   private void validateColumnFilterPredicate(Operators.Contains pred) {
      this.validateColumn(pred.getColumn(), true);
   }

   private void validateColumn(Operators.Column column) {
      this.validateColumn(column, false);
   }

   private void validateColumn(Operators.Column column, boolean shouldBeRepeated) {
      ColumnPath path = column.getColumnPath();
      Class<?> alreadySeen = (Class)this.columnTypesEncountered.get(path);
      if (alreadySeen != null && !alreadySeen.equals(column.getColumnType())) {
         throw new IllegalArgumentException("Column: " + path.toDotString() + " was provided with different types in the same predicate. Found both: (" + alreadySeen + ", " + column.getColumnType() + ")");
      } else {
         if (alreadySeen == null) {
            this.columnTypesEncountered.put(path, column.getColumnType());
         }

         ColumnDescriptor descriptor = this.getColumnDescriptor(path);
         if (descriptor != null) {
            if (shouldBeRepeated && descriptor.getMaxRepetitionLevel() == 0) {
               throw new IllegalArgumentException("FilterPredicate for column " + path.toDotString() + " requires a repeated schema, but found max repetition level " + descriptor.getMaxRepetitionLevel());
            } else if (!shouldBeRepeated && descriptor.getMaxRepetitionLevel() > 0) {
               throw new IllegalArgumentException("FilterPredicates do not currently support repeated columns. Column " + path.toDotString() + " is repeated.");
            } else {
               ValidTypeMap.assertTypeValid(column, descriptor.getType());
            }
         }
      }
   }

   private ColumnDescriptor getColumnDescriptor(ColumnPath columnPath) {
      return (ColumnDescriptor)this.columnsAccordingToSchema.get(columnPath);
   }
}

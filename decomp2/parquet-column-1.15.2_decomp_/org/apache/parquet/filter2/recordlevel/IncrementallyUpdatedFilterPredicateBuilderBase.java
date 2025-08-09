package org.apache.parquet.filter2.recordlevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.parquet.Preconditions;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.io.PrimitiveColumnIO;
import org.apache.parquet.schema.PrimitiveComparator;

public abstract class IncrementallyUpdatedFilterPredicateBuilderBase implements FilterPredicate.Visitor {
   private boolean built = false;
   private final Map valueInspectorsByColumn = new HashMap();
   private final Map comparatorsByColumn = new HashMap();

   /** @deprecated */
   @Deprecated
   public IncrementallyUpdatedFilterPredicateBuilderBase() {
   }

   public IncrementallyUpdatedFilterPredicateBuilderBase(List leaves) {
      for(PrimitiveColumnIO leaf : leaves) {
         ColumnDescriptor descriptor = leaf.getColumnDescriptor();
         ColumnPath path = ColumnPath.get(descriptor.getPath());
         PrimitiveComparator<?> comparator = descriptor.getPrimitiveType().comparator();
         this.comparatorsByColumn.put(path, comparator);
      }

   }

   public final IncrementallyUpdatedFilterPredicate build(FilterPredicate pred) {
      Preconditions.checkArgument(!this.built, "This builder has already been used");
      IncrementallyUpdatedFilterPredicate incremental = (IncrementallyUpdatedFilterPredicate)pred.accept(this);
      this.built = true;
      return incremental;
   }

   protected final void addValueInspector(ColumnPath columnPath, IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector) {
      List<IncrementallyUpdatedFilterPredicate.ValueInspector> valueInspectors = (List)this.valueInspectorsByColumn.get(columnPath);
      if (valueInspectors == null) {
         valueInspectors = new ArrayList();
         this.valueInspectorsByColumn.put(columnPath, valueInspectors);
      }

      valueInspectors.add(valueInspector);
   }

   public Map getValueInspectorsByColumn() {
      return this.valueInspectorsByColumn;
   }

   protected final PrimitiveComparator getComparator(ColumnPath path) {
      return (PrimitiveComparator)this.comparatorsByColumn.get(path);
   }

   public final IncrementallyUpdatedFilterPredicate visit(Operators.And and) {
      return new IncrementallyUpdatedFilterPredicate.And((IncrementallyUpdatedFilterPredicate)and.getLeft().accept(this), (IncrementallyUpdatedFilterPredicate)and.getRight().accept(this));
   }

   public final IncrementallyUpdatedFilterPredicate visit(Operators.Or or) {
      return new IncrementallyUpdatedFilterPredicate.Or((IncrementallyUpdatedFilterPredicate)or.getLeft().accept(this), (IncrementallyUpdatedFilterPredicate)or.getRight().accept(this));
   }

   public final IncrementallyUpdatedFilterPredicate visit(Operators.Not not) {
      throw new IllegalArgumentException("This predicate contains a not! Did you forget to run this predicate through LogicalInverseRewriter? " + not);
   }
}

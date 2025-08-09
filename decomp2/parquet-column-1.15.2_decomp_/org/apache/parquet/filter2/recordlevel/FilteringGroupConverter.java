package org.apache.parquet.filter2.recordlevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.Preconditions;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.io.PrimitiveColumnIO;
import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;

public class FilteringGroupConverter extends GroupConverter {
   private final GroupConverter delegate;
   private final List indexFieldPath;
   private final Map valueInspectorsByColumn;
   private final Map columnIOsByIndexFieldPath;

   public FilteringGroupConverter(GroupConverter delegate, List indexFieldPath, Map valueInspectorsByColumn, Map columnIOsByIndexFieldPath) {
      this.delegate = (GroupConverter)Objects.requireNonNull(delegate, "delegate cannot be null");
      this.indexFieldPath = (List)Objects.requireNonNull(indexFieldPath, "indexFieldPath cannot be null");
      this.columnIOsByIndexFieldPath = (Map)Objects.requireNonNull(columnIOsByIndexFieldPath, "columnIOsByIndexFieldPath cannot be null");
      this.valueInspectorsByColumn = (Map)Objects.requireNonNull(valueInspectorsByColumn, "valueInspectorsByColumn cannot be null");
   }

   public Converter getConverter(int fieldIndex) {
      Converter delegateConverter = (Converter)Objects.requireNonNull(this.delegate.getConverter(fieldIndex), "delegate converter cannot be null");
      List<Integer> newIndexFieldPath = new ArrayList(this.indexFieldPath.size() + 1);
      newIndexFieldPath.addAll(this.indexFieldPath);
      newIndexFieldPath.add(fieldIndex);
      if (delegateConverter.isPrimitive()) {
         PrimitiveColumnIO columnIO = this.getColumnIO(newIndexFieldPath);
         ColumnPath columnPath = ColumnPath.get(columnIO.getColumnDescriptor().getPath());
         IncrementallyUpdatedFilterPredicate.ValueInspector[] valueInspectors = this.getValueInspectors(columnPath);
         return new FilteringPrimitiveConverter(delegateConverter.asPrimitiveConverter(), valueInspectors);
      } else {
         return new FilteringGroupConverter(delegateConverter.asGroupConverter(), newIndexFieldPath, this.valueInspectorsByColumn, this.columnIOsByIndexFieldPath);
      }
   }

   private PrimitiveColumnIO getColumnIO(List indexFieldPath) {
      PrimitiveColumnIO found = (PrimitiveColumnIO)this.columnIOsByIndexFieldPath.get(indexFieldPath);
      Preconditions.checkArgument(found != null, "Did not find PrimitiveColumnIO for index field path %s", indexFieldPath);
      return found;
   }

   private IncrementallyUpdatedFilterPredicate.ValueInspector[] getValueInspectors(ColumnPath columnPath) {
      List<IncrementallyUpdatedFilterPredicate.ValueInspector> inspectorsList = (List)this.valueInspectorsByColumn.get(columnPath);
      return inspectorsList == null ? new IncrementallyUpdatedFilterPredicate.ValueInspector[0] : (IncrementallyUpdatedFilterPredicate.ValueInspector[])inspectorsList.toArray(new IncrementallyUpdatedFilterPredicate.ValueInspector[0]);
   }

   public void start() {
      this.delegate.start();
   }

   public void end() {
      this.delegate.end();
   }
}

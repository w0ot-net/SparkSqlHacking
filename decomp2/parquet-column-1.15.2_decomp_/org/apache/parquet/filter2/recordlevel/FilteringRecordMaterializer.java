package org.apache.parquet.filter2.recordlevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.io.PrimitiveColumnIO;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.RecordMaterializer;

public class FilteringRecordMaterializer extends RecordMaterializer {
   private final RecordMaterializer delegate;
   private final FilteringGroupConverter rootConverter;
   private final IncrementallyUpdatedFilterPredicate filterPredicate;

   public FilteringRecordMaterializer(RecordMaterializer delegate, List columnIOs, Map valueInspectorsByColumn, IncrementallyUpdatedFilterPredicate filterPredicate) {
      Objects.requireNonNull(columnIOs, "columnIOs cannot be null");
      Objects.requireNonNull(valueInspectorsByColumn, "valueInspectorsByColumn cannot be null");
      this.filterPredicate = (IncrementallyUpdatedFilterPredicate)Objects.requireNonNull(filterPredicate, "filterPredicate cannot be null");
      this.delegate = (RecordMaterializer)Objects.requireNonNull(delegate, "delegate cannot be null");
      Map<List<Integer>, PrimitiveColumnIO> columnIOsByIndexFieldPath = new HashMap();

      for(PrimitiveColumnIO c : columnIOs) {
         List<Integer> indexFieldPath = (List)Arrays.stream(c.getIndexFieldPath()).boxed().collect(Collectors.toList());
         columnIOsByIndexFieldPath.put(indexFieldPath, c);
      }

      this.rootConverter = new FilteringGroupConverter(delegate.getRootConverter(), Collections.emptyList(), valueInspectorsByColumn, columnIOsByIndexFieldPath);
   }

   public Object getCurrentRecord() {
      boolean keep = IncrementallyUpdatedFilterPredicateEvaluator.evaluate(this.filterPredicate);
      IncrementallyUpdatedFilterPredicateResetter.reset(this.filterPredicate);
      return keep ? this.delegate.getCurrentRecord() : null;
   }

   public void skipCurrentRecord() {
      this.delegate.skipCurrentRecord();
   }

   public GroupConverter getRootConverter() {
      return this.rootConverter;
   }

   /** @deprecated */
   @Deprecated
   public static List getIndexFieldPathList(PrimitiveColumnIO c) {
      return intArrayToList(c.getIndexFieldPath());
   }

   /** @deprecated */
   @Deprecated
   public static List intArrayToList(int[] arr) {
      List<Integer> list = new ArrayList(arr.length);

      for(int i : arr) {
         list.add(i);
      }

      return list;
   }
}

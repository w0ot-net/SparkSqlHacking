package org.apache.orc.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;

public class StripeStatisticsImpl extends StripeStatistics {
   public StripeStatisticsImpl(TypeDescription schema, List list, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
      super(schema, list, writerUsedProlepticGregorian, convertToProlepticGregorian);
   }

   public StripeStatisticsImpl(TypeDescription schema, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
      super(schema, createList(schema), writerUsedProlepticGregorian, convertToProlepticGregorian);
   }

   private static List createList(TypeDescription schema) {
      int len = schema.getMaximumId() - schema.getId() + 1;
      List<OrcProto.ColumnStatistics> result = new ArrayList(len);

      for(int c = 0; c < len; ++c) {
         result.add((Object)null);
      }

      return result;
   }

   public void updateColumn(int column, OrcProto.ColumnStatistics elem) {
      this.cs.set(column, elem);
   }
}

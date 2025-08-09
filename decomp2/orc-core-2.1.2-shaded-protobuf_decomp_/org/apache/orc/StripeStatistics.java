package org.apache.orc;

import java.util.List;
import org.apache.orc.impl.ColumnStatisticsImpl;

public class StripeStatistics {
   protected final List cs;
   protected final TypeDescription schema;
   private final boolean writerUsedProlepticGregorian;
   private final boolean convertToProlepticGregorian;

   public StripeStatistics(List list) {
      this((TypeDescription)null, list, false, false);
   }

   public StripeStatistics(TypeDescription schema, List list, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
      this.schema = schema;
      this.cs = list;
      this.writerUsedProlepticGregorian = writerUsedProlepticGregorian;
      this.convertToProlepticGregorian = convertToProlepticGregorian;
   }

   private int getBase() {
      return this.schema == null ? 0 : this.schema.getId();
   }

   public ColumnStatistics[] getColumnStatistics() {
      ColumnStatistics[] result = new ColumnStatistics[this.cs.size()];
      int base = this.getBase();

      for(int c = 0; c < result.length; ++c) {
         TypeDescription column = this.schema == null ? null : this.schema.findSubtype(base + c);
         result[c] = ColumnStatisticsImpl.deserialize(column, (OrcProto.ColumnStatistics)this.cs.get(c), this.writerUsedProlepticGregorian, this.convertToProlepticGregorian);
      }

      return result;
   }

   public OrcProto.ColumnStatistics getColumn(int column) {
      return (OrcProto.ColumnStatistics)this.cs.get(column);
   }
}

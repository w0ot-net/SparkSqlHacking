package org.apache.derby.iapi.sql.dictionary;

import java.sql.Timestamp;
import org.apache.derby.catalog.Statistics;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.types.DataTypeUtilities;

public class StatisticsDescriptor extends TupleDescriptor {
   private UUID statID;
   private UUID statRefID;
   private UUID statTableID;
   private Timestamp statUpdateTime;
   private String statType;
   private boolean statValid = true;
   private Statistics statStat;
   private int statColumnCount;

   public StatisticsDescriptor(DataDictionary var1, UUID var2, UUID var3, UUID var4, String var5, Statistics var6, int var7) {
      super(var1);
      this.statID = var2;
      this.statRefID = var3;
      this.statTableID = var4;
      this.statUpdateTime = new Timestamp(System.currentTimeMillis());
      this.statType = "I";
      this.statStat = var6;
      this.statColumnCount = var7;
   }

   public UUID getUUID() {
      return this.statID;
   }

   public UUID getTableUUID() {
      return this.statTableID;
   }

   public UUID getReferenceID() {
      return this.statRefID;
   }

   public Timestamp getUpdateTimestamp() {
      return DataTypeUtilities.clone(this.statUpdateTime);
   }

   public String getStatType() {
      return this.statType;
   }

   public boolean isValid() {
      return this.statValid;
   }

   public Statistics getStatistic() {
      return this.statStat;
   }

   public int getColumnCount() {
      return this.statColumnCount;
   }

   public String toString() {
      String var10000 = this.getTableUUID().toString();
      return "statistics: table=" + var10000 + ",conglomerate=" + this.getReferenceID() + ",colCount=" + this.getColumnCount() + ",stat=" + this.getStatistic();
   }
}

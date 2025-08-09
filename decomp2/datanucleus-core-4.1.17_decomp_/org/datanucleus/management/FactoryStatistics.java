package org.datanucleus.management;

public class FactoryStatistics extends AbstractStatistics implements FactoryStatisticsMBean {
   int connectionActiveCurrent;
   int connectionActiveHigh;
   int connectionActiveTotal;

   public FactoryStatistics(String name) {
      super(name);
   }

   public int getConnectionActiveCurrent() {
      return this.connectionActiveCurrent;
   }

   public int getConnectionActiveHigh() {
      return this.connectionActiveHigh;
   }

   public int getConnectionActiveTotal() {
      return this.connectionActiveTotal;
   }

   public void incrementActiveConnections() {
      ++this.connectionActiveCurrent;
      ++this.connectionActiveTotal;
      this.connectionActiveHigh = Math.max(this.connectionActiveHigh, this.connectionActiveCurrent);
   }

   public void decrementActiveConnections() {
      --this.connectionActiveCurrent;
   }
}

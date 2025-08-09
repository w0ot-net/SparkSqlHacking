package org.datanucleus.management;

public class ManagerStatistics extends AbstractStatistics implements ManagerStatisticsMBean {
   public ManagerStatistics(String name, FactoryStatistics parent) {
      super(name);
      this.parent = parent;
   }
}

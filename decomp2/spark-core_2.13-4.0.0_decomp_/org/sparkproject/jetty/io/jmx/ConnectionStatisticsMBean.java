package org.sparkproject.jetty.io.jmx;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import org.sparkproject.jetty.io.ConnectionStatistics;
import org.sparkproject.jetty.jmx.ObjectMBean;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class ConnectionStatisticsMBean extends ObjectMBean {
   public ConnectionStatisticsMBean(Object object) {
      super(object);
   }

   @ManagedAttribute("ConnectionStatistics grouped by connection class")
   public Collection getConnectionStatisticsGroups() {
      ConnectionStatistics delegate = (ConnectionStatistics)this.getManagedObject();
      Map<String, ConnectionStatistics.Stats> groups = delegate.getConnectionStatisticsGroups();
      return (Collection)groups.values().stream().sorted(Comparator.comparing(ConnectionStatistics.Stats::getName)).map((stats) -> stats.dump()).map((dump) -> dump.replaceAll("[\r\n]", " ")).collect(Collectors.toList());
   }
}

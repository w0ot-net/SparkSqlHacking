package org.glassfish.jersey.server.internal.monitoring.jmx;

import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.server.monitoring.ResourceStatistics;

public class ResourcesMBeanGroup {
   private final Map exposedResourceMBeans = new HashMap();
   private final String parentName;
   private final boolean uriResource;
   private final MBeanExposer exposer;

   public ResourcesMBeanGroup(Map resourceStatistics, boolean uriResource, MBeanExposer mBeanExposer, String parentName) {
      this.uriResource = uriResource;
      this.exposer = mBeanExposer;
      this.parentName = parentName;
      this.updateResourcesStatistics(resourceStatistics);
   }

   public void updateResourcesStatistics(Map resourceStatistics) {
      for(Map.Entry entry : resourceStatistics.entrySet()) {
         ResourceMxBeanImpl resourceMxBean = (ResourceMxBeanImpl)this.exposedResourceMBeans.get(entry.getKey());
         if (resourceMxBean == null) {
            resourceMxBean = new ResourceMxBeanImpl((ResourceStatistics)entry.getValue(), (String)entry.getKey(), this.uriResource, this.exposer, this.parentName);
            this.exposedResourceMBeans.put(entry.getKey(), resourceMxBean);
         }

         resourceMxBean.updateResourceStatistics((ResourceStatistics)entry.getValue());
      }

   }
}

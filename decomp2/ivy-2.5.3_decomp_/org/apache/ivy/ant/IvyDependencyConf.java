package org.apache.ivy.ant;

import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.util.StringUtils;

public class IvyDependencyConf {
   private final List mappeds = new ArrayList();
   private String mapped;

   public void setMapped(String mapped) {
      this.mapped = mapped;
   }

   public IvyDependencyConfMapped createMapped() {
      IvyDependencyConfMapped m = new IvyDependencyConfMapped();
      this.mappeds.add(m);
      return m;
   }

   void addConf(DefaultDependencyDescriptor dd, String masterConf) {
      if (this.mapped != null) {
         for(String map : StringUtils.splitToArray(this.mapped)) {
            dd.addDependencyConfiguration(masterConf, map);
         }
      }

      for(IvyDependencyConfMapped m : this.mappeds) {
         dd.addDependencyConfiguration(masterConf, m.name);
      }

   }

   public static class IvyDependencyConfMapped {
      private String name;

      public void setName(String name) {
         this.name = name;
      }
   }
}

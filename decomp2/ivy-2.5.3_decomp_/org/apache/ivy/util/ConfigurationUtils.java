package org.apache.ivy.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;

public final class ConfigurationUtils {
   private ConfigurationUtils() {
   }

   public static String[] replaceWildcards(String[] confs, ModuleDescriptor md) {
      if (confs == null) {
         return md.getConfigurationsNames();
      } else {
         Set<String> result = new LinkedHashSet();
         Set<String> excluded = new LinkedHashSet();

         for(String conf : confs) {
            if ("*".equals(conf)) {
               result.addAll(Arrays.asList(md.getConfigurationsNames()));
            } else if ("*(public)".equals(conf)) {
               for(Configuration cf : md.getConfigurations()) {
                  if (Configuration.Visibility.PUBLIC.equals(cf.getVisibility())) {
                     result.add(cf.getName());
                  }
               }
            } else if ("*(private)".equals(conf)) {
               for(Configuration cf : md.getConfigurations()) {
                  if (Configuration.Visibility.PRIVATE.equals(cf.getVisibility())) {
                     result.add(cf.getName());
                  }
               }
            } else if (conf.startsWith("!")) {
               excluded.add(conf.substring(1));
            } else {
               result.add(conf);
            }
         }

         for(String ex : excluded) {
            result.remove(ex);
         }

         return (String[])result.toArray(new String[result.size()]);
      }
   }
}

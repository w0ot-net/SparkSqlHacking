package org.apache.ivy.plugins.circular;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public final class CircularDependencyHelper {
   private CircularDependencyHelper() {
   }

   public static String formatMessage(ModuleRevisionId[] mrids) {
      Set<ModuleRevisionId> alreadyAdded = new HashSet();
      StringBuilder buff = new StringBuilder();

      for(ModuleRevisionId mrid : mrids) {
         if (buff.length() > 0) {
            buff.append("->");
         }

         if (!alreadyAdded.add(mrid)) {
            buff.append("...");
            break;
         }

         buff.append(mrid);
      }

      return buff.toString();
   }

   public static String formatMessage(ModuleDescriptor[] descriptors) {
      return formatMessageFromDescriptors(Arrays.asList(descriptors));
   }

   public static String formatMessageFromDescriptors(List loopElements) {
      List<ModuleRevisionId> mrids = new LinkedList();

      for(ModuleDescriptor descriptor : loopElements) {
         mrids.add(descriptor.getModuleRevisionId());
      }

      return formatMessage((ModuleRevisionId[])mrids.toArray(new ModuleRevisionId[mrids.size()]));
   }
}

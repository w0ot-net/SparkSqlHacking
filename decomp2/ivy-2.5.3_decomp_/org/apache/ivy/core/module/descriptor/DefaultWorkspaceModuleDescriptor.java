package org.apache.ivy.core.module.descriptor;

import java.util.Date;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.repository.Resource;

public class DefaultWorkspaceModuleDescriptor extends DefaultModuleDescriptor implements WorkspaceModuleDescriptor {
   public DefaultWorkspaceModuleDescriptor(ModuleDescriptorParser parser, Resource res) {
      super(parser, res);
   }

   public DefaultWorkspaceModuleDescriptor(ModuleRevisionId id, String status, Date pubDate) {
      super(id, status, pubDate);
   }

   public DefaultWorkspaceModuleDescriptor(ModuleRevisionId id, String status, Date pubDate, boolean isDefault) {
      super(id, status, pubDate, isDefault);
   }
}

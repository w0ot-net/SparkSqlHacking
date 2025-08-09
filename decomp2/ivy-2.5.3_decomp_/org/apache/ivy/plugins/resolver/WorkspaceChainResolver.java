package org.apache.ivy.plugins.resolver;

import org.apache.ivy.core.settings.IvySettings;

public class WorkspaceChainResolver extends ChainResolver {
   public WorkspaceChainResolver(IvySettings settings, DependencyResolver delegate, AbstractWorkspaceResolver workspaceResolver) {
      this.setName("workspace-chain-" + delegate.getName());
      this.setSettings(settings);
      this.setReturnFirst(true);
      this.add(workspaceResolver);
      this.add(delegate);
   }
}

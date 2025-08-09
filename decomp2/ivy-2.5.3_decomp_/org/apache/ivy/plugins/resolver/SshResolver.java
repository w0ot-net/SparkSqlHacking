package org.apache.ivy.plugins.resolver;

import org.apache.ivy.plugins.repository.ssh.SshRepository;

public class SshResolver extends AbstractSshBasedResolver {
   public SshResolver() {
      this.setRepository(new SshRepository(new LazyTimeoutConstraint(this)));
   }

   public void setPublishPermissions(String permissions) {
      ((SshRepository)this.getRepository()).setPublishPermissions(permissions);
   }

   public void setFileSeparator(String sep) {
      if (sep != null && sep.length() == 1) {
         ((SshRepository)this.getRepository()).setFileSeparator(sep.trim().charAt(0));
      } else {
         throw new IllegalArgumentException("File Separator has to be a single character and not " + sep);
      }
   }

   public void setListCommand(String cmd) {
      ((SshRepository)this.getRepository()).setListCommand(cmd);
   }

   public void setExistCommand(String cmd) {
      ((SshRepository)this.getRepository()).setExistCommand(cmd);
   }

   public void setCreateDirCommand(String cmd) {
      ((SshRepository)this.getRepository()).setExistCommand(cmd);
   }

   public String getTypeName() {
      return "ssh";
   }
}

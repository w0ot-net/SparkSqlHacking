package org.apache.ivy.plugins.resolver;

import org.apache.ivy.plugins.repository.sftp.SFTPRepository;

public class SFTPResolver extends AbstractSshBasedResolver {
   public SFTPResolver() {
      this.setRepository(new SFTPRepository(new LazyTimeoutConstraint(this)));
   }

   public String getTypeName() {
      return "sftp";
   }

   public SFTPRepository getSFTPRepository() {
      return (SFTPRepository)this.getRepository();
   }
}

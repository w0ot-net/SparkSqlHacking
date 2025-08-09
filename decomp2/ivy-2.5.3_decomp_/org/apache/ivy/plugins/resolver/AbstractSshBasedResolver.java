package org.apache.ivy.plugins.resolver;

import java.io.File;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.repository.ssh.AbstractSshBasedRepository;

public abstract class AbstractSshBasedResolver extends RepositoryResolver {
   private boolean passfileSet = false;

   private AbstractSshBasedRepository getSshBasedRepository() {
      return (AbstractSshBasedRepository)this.getRepository();
   }

   public void setKeyFile(File filePath) {
      this.getSshBasedRepository().setKeyFile(filePath);
   }

   public void setAllowedAgentUse(boolean allowedAgentUse) {
      this.getSshBasedRepository().setAllowedAgentUse(allowedAgentUse);
   }

   public void setPassfile(File passfile) {
      this.getSshBasedRepository().setPassFile(passfile);
      this.passfileSet = true;
   }

   public void setSettings(IvySettings settings) {
      super.setSettings(settings);
      if (!this.passfileSet) {
         this.getSshBasedRepository().setPassFile(new File(settings.getDefaultIvyUserDir(), this.getSshBasedRepository().getHost() + ".ssh.passwd"));
      }

   }

   public void setUserPassword(String password) {
      this.getSshBasedRepository().setUserPassword(password);
   }

   public void setKeyFilePassword(String password) {
      this.getSshBasedRepository().setKeyFilePassword(password);
   }

   public void setUser(String user) {
      this.getSshBasedRepository().setUser(user);
   }

   public void setHost(String host) {
      this.getSshBasedRepository().setHost(host);
   }

   public void setPort(int port) {
      this.getSshBasedRepository().setPort(port);
   }

   public void setSshConfig(String sshConfig) {
      this.getSshBasedRepository().setSshConfig(sshConfig);
   }

   public abstract String getTypeName();
}

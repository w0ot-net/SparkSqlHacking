package org.apache.ivy.plugins.resolver;

import org.apache.ivy.plugins.repository.vsftp.VsftpRepository;

public class VsftpResolver extends RepositoryResolver {
   public VsftpResolver() {
      this.setRepository(new VsftpRepository(new LazyTimeoutConstraint(this)));
   }

   public String getTypeName() {
      return "vsftp";
   }

   public VsftpRepository getVsftpRepository() {
      return (VsftpRepository)this.getRepository();
   }

   public void disconnect() {
      this.getVsftpRepository().disconnect();
   }

   public String getAuthentication() {
      return this.getVsftpRepository().getAuthentication();
   }

   public String getHost() {
      return this.getVsftpRepository().getHost();
   }

   public String getUsername() {
      return this.getVsftpRepository().getUsername();
   }

   public void setAuthentication(String authentication) {
      this.getVsftpRepository().setAuthentication(authentication);
   }

   public void setHost(String host) {
      this.getVsftpRepository().setHost(host);
   }

   public void setUsername(String username) {
      this.getVsftpRepository().setUsername(username);
   }

   public void setReuseConnection(long time) {
      this.getVsftpRepository().setReuseConnection(time);
   }

   public void setReadTimeout(long readTimeout) {
      this.getVsftpRepository().setReadTimeout(readTimeout);
   }
}

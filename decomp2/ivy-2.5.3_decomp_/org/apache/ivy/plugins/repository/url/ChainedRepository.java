package org.apache.ivy.plugins.repository.url;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.BasicResource;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Message;

public class ChainedRepository extends AbstractRepository {
   private List repositories;

   public void setRepositories(List repositories) {
      this.repositories = repositories;
   }

   public Resource getResource(String source) throws IOException {
      for(Repository repository : this.repositories) {
         this.logTry(repository);

         try {
            Resource r = repository.getResource(source);
            if (r != null && r.exists()) {
               this.logSuccess(repository);
               return r;
            }
         } catch (Exception e) {
            this.logFailed(repository, e);
         }
      }

      return new BasicResource(source, false, 0L, 0L, true);
   }

   public void get(String source, File destination) throws IOException {
      for(Repository repository : this.repositories) {
         this.logTry(repository);
         boolean ok = false;

         try {
            repository.get(source, destination);
            ok = true;
         } catch (Exception e) {
            this.logFailed(repository, e);
         }

         if (ok) {
            this.logSuccess(repository);
            return;
         }
      }

      throw this.newIOEFail("copy " + source + " into " + destination);
   }

   public List list(String parent) throws IOException {
      for(Repository repository : this.repositories) {
         this.logTry(repository);

         try {
            List<String> list = repository.list(parent);
            if (list != null) {
               this.logSuccess(repository);
               return list;
            }
         } catch (Exception e) {
            this.logFailed(repository, e);
         }
      }

      throw this.newIOEFail("list contents in " + parent);
   }

   private void logTry(Repository repository) {
      Message.debug("Mirrored repository " + this.getName() + ": trying " + repository.getName());
   }

   private void logFailed(Repository repository, Exception e) {
      Message.warn("Mirrored repository " + this.getName() + ": " + repository.getName() + " is not available", e);
      Message.warn("Trying the next one in the mirror list...");
   }

   private void logSuccess(Repository repository) {
      Message.debug("Mirrored repository " + this.getName() + ": success with " + repository.getName());
   }

   private IOException newIOEFail(String action) {
      return new IOException("Mirrored repository " + this.getName() + ": fail to " + action + " with every listed mirror");
   }
}

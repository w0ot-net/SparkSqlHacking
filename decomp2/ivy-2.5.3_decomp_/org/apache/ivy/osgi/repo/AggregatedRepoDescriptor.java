package org.apache.ivy.osgi.repo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class AggregatedRepoDescriptor extends RepoDescriptor {
   private List repos;

   public AggregatedRepoDescriptor(List repos) {
      this.repos = repos;
   }

   public Iterator getModules() {
      final Iterator<RepoDescriptor> itRepos = this.repos.iterator();
      return new Iterator() {
         private Iterator current = null;
         private ModuleDescriptorWrapper next = null;

         public boolean hasNext() {
            while(this.next == null) {
               if (this.current == null) {
                  if (!itRepos.hasNext()) {
                     return false;
                  }

                  RepoDescriptor repo = (RepoDescriptor)itRepos.next();
                  this.current = repo.getModules();
               }

               if (this.current.hasNext()) {
                  this.next = (ModuleDescriptorWrapper)this.current.next();
               } else {
                  this.current = null;
               }
            }

            return true;
         }

         public ModuleDescriptorWrapper next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               ModuleDescriptorWrapper ret = this.next;
               this.next = null;
               return ret;
            }
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   public Set getCapabilities() {
      Set<String> ret = new HashSet();

      for(RepoDescriptor repo : this.repos) {
         Set<String> capabilities = repo.getCapabilities();
         if (capabilities != null) {
            ret.addAll(capabilities);
         }
      }

      return ret;
   }

   public Set findModules(String requirement, String value) {
      Set<ModuleDescriptorWrapper> ret = new HashSet();

      for(RepoDescriptor repo : this.repos) {
         Set<ModuleDescriptorWrapper> modules = repo.findModules(requirement, value);
         if (modules != null) {
            ret.addAll(modules);
         }
      }

      return ret;
   }

   public Set getCapabilityValues(String capabilityName) {
      Set<String> ret = new HashSet();

      for(RepoDescriptor repo : this.repos) {
         Set<String> capabilityValues = repo.getCapabilityValues(capabilityName);
         if (capabilityValues != null) {
            ret.addAll(capabilityValues);
         }
      }

      return ret;
   }
}

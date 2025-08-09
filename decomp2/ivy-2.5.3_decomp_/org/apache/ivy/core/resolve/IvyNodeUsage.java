package org.apache.ivy.core.resolve;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.DependencyArtifactDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.IncludeRule;
import org.apache.ivy.core.module.descriptor.WorkspaceModuleDescriptor;

public class IvyNodeUsage {
   private IvyNode node;
   private Map rootModuleConfs = new HashMap();
   private Map requiredConfs = new HashMap();
   private Map dependers = new HashMap();
   private Map blacklisted = new HashMap();

   public IvyNodeUsage(IvyNode node) {
      this.node = node;
   }

   protected Collection getRequiredConfigurations(IvyNode in, String inConf) {
      return (Collection)this.requiredConfs.get(new NodeConf(in, inConf));
   }

   protected void setRequiredConfs(IvyNode parent, String parentConf, Collection confs) {
      this.requiredConfs.put(new NodeConf(parent, parentConf), new HashSet(confs));
   }

   protected Set getConfigurations(String rootModuleConf) {
      return (Set)this.rootModuleConfs.get(rootModuleConf);
   }

   protected Set addAndGetConfigurations(String rootModuleConf) {
      Set<String> depConfs = (Set)this.rootModuleConfs.get(rootModuleConf);
      if (depConfs == null) {
         depConfs = new HashSet();
         this.rootModuleConfs.put(rootModuleConf, depConfs);
      }

      return depConfs;
   }

   protected Set getRootModuleConfigurations() {
      return this.rootModuleConfs.keySet();
   }

   public void updateDataFrom(Collection usages, String rootModuleConf) {
      for(IvyNodeUsage usage : usages) {
         this.updateDataFrom(usage, rootModuleConf);
      }

   }

   private void updateDataFrom(IvyNodeUsage usage, String rootModuleConf) {
      this.updateMapOfSet(usage.requiredConfs, this.requiredConfs);
      this.updateMapOfSetForKey(usage.rootModuleConfs, this.rootModuleConfs, rootModuleConf);
      this.updateMapOfSetForKey(usage.dependers, this.dependers, rootModuleConf);
   }

   private void updateMapOfSet(Map from, Map to) {
      for(Object key : from.keySet()) {
         this.updateMapOfSetForKey(from, to, key);
      }

   }

   private void updateMapOfSetForKey(Map from, Map to, Object key) {
      Set<V> set = (Set)from.get(key);
      if (set != null) {
         Set<V> toupdate = (Set)to.get(key);
         if (toupdate != null) {
            toupdate.addAll(set);
         } else {
            to.put(key, new HashSet(set));
         }
      }

   }

   private void addObjectsForConf(Object rootModuleConf, Object objectToAdd, Map map) {
      Set<V> set = (Set)map.get(rootModuleConf);
      if (set == null) {
         set = new HashSet();
         map.put(rootModuleConf, set);
      }

      set.add(objectToAdd);
   }

   public void addUsage(String rootModuleConf, DependencyDescriptor dd, String parentConf) {
      this.addObjectsForConf(rootModuleConf, new Depender(dd, parentConf), this.dependers);
   }

   protected Set getDependencyArtifactsSet(String rootModuleConf) {
      if (this.node.getDescriptor() instanceof WorkspaceModuleDescriptor) {
         return null;
      } else {
         Collection<Depender> dependersInConf = (Collection)this.dependers.get(rootModuleConf);
         if (dependersInConf == null) {
            return null;
         } else {
            Set<DependencyArtifactDescriptor> dependencyArtifacts = new HashSet();

            for(Depender depender : dependersInConf) {
               DependencyArtifactDescriptor[] dads = depender.dd.getDependencyArtifacts(depender.dependerConf);
               dependencyArtifacts.addAll(Arrays.asList(dads));
            }

            return dependencyArtifacts;
         }
      }
   }

   protected Set getDependencyIncludesSet(String rootModuleConf) {
      Collection<Depender> dependersInConf = (Collection)this.dependers.get(rootModuleConf);
      if (dependersInConf == null) {
         return null;
      } else {
         Set<IncludeRule> dependencyIncludes = new HashSet();

         for(Depender depender : dependersInConf) {
            IncludeRule[] rules = depender.dd.getIncludeRules(depender.dependerConf);
            if (rules == null || rules.length == 0) {
               return null;
            }

            dependencyIncludes.addAll(Arrays.asList(rules));
         }

         return dependencyIncludes;
      }
   }

   protected void removeRootModuleConf(String rootModuleConf) {
      this.rootModuleConfs.remove(rootModuleConf);
   }

   protected void blacklist(IvyNodeBlacklist bdata) {
      this.blacklisted.put(bdata.getRootModuleConf(), bdata);
   }

   protected boolean isBlacklisted(String rootModuleConf) {
      return this.blacklisted.containsKey(rootModuleConf);
   }

   protected IvyNodeBlacklist getBlacklistData(String rootModuleConf) {
      return (IvyNodeBlacklist)this.blacklisted.get(rootModuleConf);
   }

   protected IvyNode getNode() {
      return this.node;
   }

   public boolean hasTransitiveDepender(String rootModuleConf) {
      Set<Depender> dependersSet = (Set)this.dependers.get(rootModuleConf);
      if (dependersSet == null) {
         return false;
      } else {
         for(Depender depender : dependersSet) {
            if (depender.dd.isTransitive()) {
               return true;
            }
         }

         return false;
      }
   }

   private static final class NodeConf {
      private IvyNode node;
      private String conf;

      public NodeConf(IvyNode node, String conf) {
         if (node == null) {
            throw new NullPointerException("node must not null");
         } else if (conf == null) {
            throw new NullPointerException("conf must not null");
         } else {
            this.node = node;
            this.conf = conf;
         }
      }

      public final String getConf() {
         return this.conf;
      }

      public final IvyNode getNode() {
         return this.node;
      }

      public boolean equals(Object obj) {
         return obj instanceof NodeConf && this.getNode().equals(((NodeConf)obj).getNode()) && this.getConf().equals(((NodeConf)obj).getConf());
      }

      public int hashCode() {
         int hash = 33;
         hash += this.getNode().hashCode() * 17;
         hash += this.getConf().hashCode() * 17;
         return hash;
      }

      public String toString() {
         return "NodeConf(" + this.conf + ")";
      }
   }

   private static final class Depender {
      private DependencyDescriptor dd;
      private String dependerConf;

      public Depender(DependencyDescriptor dd, String dependerConf) {
         this.dd = dd;
         this.dependerConf = dependerConf;
      }

      public String toString() {
         return this.dd + " [" + this.dependerConf + "]";
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Depender)) {
            return false;
         } else {
            Depender other = (Depender)obj;
            return other.dd == this.dd && other.dependerConf.equals(this.dependerConf);
         }
      }

      public int hashCode() {
         int hash = 33;
         hash += this.dd.hashCode() * 13;
         hash += this.dependerConf.hashCode() * 13;
         return hash;
      }
   }
}

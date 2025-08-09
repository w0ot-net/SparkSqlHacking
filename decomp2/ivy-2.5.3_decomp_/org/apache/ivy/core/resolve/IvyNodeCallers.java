package org.apache.ivy.core.resolve;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public class IvyNodeCallers {
   private Map callersByRootConf = new HashMap();
   private Map allCallers = new HashMap();
   private IvyNode node;

   public IvyNodeCallers(IvyNode node) {
      this.node = node;
   }

   public void addCaller(String rootModuleConf, IvyNode callerNode, String callerConf, String requestedConf, String[] dependencyConfs, DependencyDescriptor dd) {
      ModuleDescriptor md = callerNode.getDescriptor();
      ModuleRevisionId mrid = callerNode.getResolvedId();
      if (mrid.getModuleId().equals(this.node.getId().getModuleId())) {
         throw new IllegalArgumentException("a module is not authorized to depend on itself: " + this.node.getId());
      } else {
         Map<ModuleRevisionId, Caller> callers = (Map)this.callersByRootConf.get(rootModuleConf);
         if (callers == null) {
            callers = new HashMap();
            this.callersByRootConf.put(rootModuleConf, callers);
         }

         Caller caller = (Caller)callers.get(mrid);
         if (caller == null) {
            caller = new Caller(md, mrid, dd, callerNode.canExclude(rootModuleConf));
            callers.put(mrid, caller);
         }

         caller.addConfiguration(requestedConf, dependencyConfs);
         IvyNode parent = callerNode.getRealNode();

         for(ModuleId mid : parent.getAllCallersModuleIds()) {
            this.allCallers.put(mid, parent);
         }

         this.allCallers.put(mrid.getModuleId(), callerNode);
      }
   }

   void removeCaller(String rootModuleConf, ModuleRevisionId callerMrid) {
      this.allCallers.remove(callerMrid.getModuleId());
      Map<ModuleRevisionId, Caller> callers = (Map)this.callersByRootConf.get(rootModuleConf);
      if (callers != null) {
         callers.remove(callerMrid);
      }

   }

   public Caller[] getCallers(String rootModuleConf) {
      Map<ModuleRevisionId, Caller> callers = (Map)this.callersByRootConf.get(rootModuleConf);
      return callers == null ? new Caller[0] : (Caller[])callers.values().toArray(new Caller[callers.values().size()]);
   }

   private Set getCallersByMrid(String rootModuleConf, ModuleRevisionId mrid) {
      Map<ModuleRevisionId, Caller> callers = (Map)this.callersByRootConf.get(rootModuleConf);
      if (callers == null) {
         return Collections.emptySet();
      } else {
         Set<Caller> mridCallers = new HashSet();

         for(Caller caller : callers.values()) {
            if (caller.getAskedDependencyId().equals(mrid)) {
               mridCallers.add(caller);
            }
         }

         return mridCallers;
      }
   }

   public Caller[] getAllCallers() {
      Set<Caller> all = new HashSet();

      for(Map callers : this.callersByRootConf.values()) {
         all.addAll(callers.values());
      }

      return (Caller[])all.toArray(new Caller[all.size()]);
   }

   public Caller[] getAllRealCallers() {
      Set<Caller> all = new HashSet();

      for(Map callers : this.callersByRootConf.values()) {
         for(Caller c : callers.values()) {
            if (c.isRealCaller()) {
               all.add(c);
            }
         }
      }

      return (Caller[])all.toArray(new Caller[all.size()]);
   }

   public Collection getAllCallersModuleIds() {
      return this.allCallers.keySet();
   }

   void updateFrom(IvyNodeCallers callers, String rootModuleConf, boolean real) {
      Map<ModuleRevisionId, Caller> nodecallers = (Map)callers.callersByRootConf.get(rootModuleConf);
      if (nodecallers != null) {
         Map<ModuleRevisionId, Caller> thiscallers = (Map)this.callersByRootConf.get(rootModuleConf);
         if (thiscallers == null) {
            thiscallers = new HashMap();
            this.callersByRootConf.put(rootModuleConf, thiscallers);
         }

         for(Caller caller : nodecallers.values()) {
            if (!thiscallers.containsKey(caller.getModuleRevisionId())) {
               if (!real) {
                  caller.setRealCaller(false);
               }

               thiscallers.put(caller.getModuleRevisionId(), caller);
            }
         }
      }

   }

   public IvyNode getDirectCallerFor(ModuleId from) {
      return (IvyNode)this.allCallers.get(from);
   }

   boolean doesCallersExclude(String rootModuleConf, Artifact artifact) {
      return this.doesCallersExclude(rootModuleConf, artifact, new ArrayDeque());
   }

   boolean doesCallersExclude(String rootModuleConf, Artifact artifact, Deque callersStack) {
      callersStack.push(this.node);

      boolean allInconclusive;
      try {
         Set<Caller> callers = this.getCallersByMrid(rootModuleConf, this.node.getId());
         if (!callers.isEmpty()) {
            allInconclusive = true;
            String[] moduleConfs = new String[]{rootModuleConf};

            label116:
            for(Caller caller : callers) {
               for(IvyNode descendant : callersStack) {
                  if (this.node.directlyExcludes(this.node.getDescriptor(), moduleConfs, caller.getDependencyDescriptor(), DefaultArtifact.newIvyArtifact(descendant.getId(), (Date)null))) {
                     allInconclusive = false;
                     continue label116;
                  }
               }

               if (!caller.canExclude()) {
                  boolean var17 = false;
                  return var17;
               }

               Boolean doesExclude = this.node.doesExclude(caller.getModuleDescriptor(), rootModuleConf, caller.getCallerConfigurations(), caller.getDependencyDescriptor(), artifact, callersStack);
               if (doesExclude != null) {
                  if (!doesExclude) {
                     boolean var18 = false;
                     return var18;
                  }

                  allInconclusive = false;
               }
            }

            boolean var15 = !allInconclusive;
            return var15;
         }

         allInconclusive = false;
      } finally {
         callersStack.pop();
      }

      return allInconclusive;
   }

   public static class Caller {
      private ModuleDescriptor md;
      private ModuleRevisionId mrid;
      private Map confs = new HashMap();
      private DependencyDescriptor dd;
      private boolean callerCanExclude;
      private boolean real = true;

      public Caller(ModuleDescriptor md, ModuleRevisionId mrid, DependencyDescriptor dd, boolean callerCanExclude) {
         this.md = md;
         this.mrid = mrid;
         this.dd = dd;
         this.callerCanExclude = callerCanExclude;
      }

      public void addConfiguration(String callerConf, String[] dependencyConfs) {
         this.updateConfs(callerConf, dependencyConfs);
         Configuration conf = this.md.getConfiguration(callerConf);
         if (conf != null) {
            String[] confExtends = conf.getExtends();
            if (confExtends != null) {
               for(String confExtend : confExtends) {
                  this.addConfiguration(confExtend, dependencyConfs);
               }
            }
         }

      }

      private void updateConfs(String callerConf, String[] dependencyConfs) {
         String[] prevDepConfs = (String[])this.confs.get(callerConf);
         if (prevDepConfs != null) {
            Set<String> newDepConfs = new HashSet(Arrays.asList(prevDepConfs));
            newDepConfs.addAll(Arrays.asList(dependencyConfs));
            this.confs.put(callerConf, newDepConfs.toArray(new String[newDepConfs.size()]));
         } else {
            this.confs.put(callerConf, dependencyConfs);
         }

      }

      public String[] getCallerConfigurations() {
         return (String[])this.confs.keySet().toArray(new String[this.confs.keySet().size()]);
      }

      public ModuleRevisionId getModuleRevisionId() {
         return this.mrid;
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Caller)) {
            return false;
         } else {
            Caller other = (Caller)obj;
            return other.confs.equals(this.confs) && this.mrid.equals(other.mrid);
         }
      }

      public int hashCode() {
         int hash = 31;
         hash = hash * 13 + this.confs.hashCode();
         hash = hash * 13 + this.mrid.hashCode();
         return hash;
      }

      public String toString() {
         return this.mrid.toString();
      }

      /** @deprecated */
      @Deprecated
      public ModuleRevisionId getAskedDependencyId(ResolveData resolveData) {
         return this.getAskedDependencyId();
      }

      public ModuleRevisionId getAskedDependencyId() {
         return this.dd.getDependencyRevisionId();
      }

      public ModuleDescriptor getModuleDescriptor() {
         return this.md;
      }

      public boolean canExclude() {
         return this.callerCanExclude || this.md.canExclude() || this.dd.canExclude();
      }

      public DependencyDescriptor getDependencyDescriptor() {
         return this.dd;
      }

      public void setRealCaller(boolean b) {
         this.real = b;
      }

      public boolean isRealCaller() {
         return this.real;
      }
   }
}

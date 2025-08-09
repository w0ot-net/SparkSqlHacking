package org.datanucleus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.SoftValueMap;
import org.datanucleus.util.StringUtils;

public class FetchPlan implements Serializable {
   private static final long serialVersionUID = 6031608568703439025L;
   public static final String DEFAULT = "default";
   public static final String ALL = "all";
   public static final String NONE = "none";
   public static final int DETACH_UNLOAD_FIELDS = 2;
   public static final int DETACH_LOAD_FIELDS = 1;
   public static final int FETCH_SIZE_GREEDY = -1;
   public static final int FETCH_SIZE_OPTIMAL = 0;
   final transient ExecutionContext ec;
   final transient ClassLoaderResolver clr;
   final Set groups = new HashSet();
   transient Set dynamicGroups = null;
   int fetchSize = 0;
   int detachmentOptions = 1;
   final transient Map managedClass = new HashMap();
   int maxFetchDepth = 1;
   Class[] detachmentRootClasses = null;
   Collection detachmentRoots = null;
   private transient Map isToCallPostLoadFetchPlanByCmd;

   public FetchPlan(ExecutionContext ec, ClassLoaderResolver clr) {
      this.ec = ec;
      this.clr = clr;
      this.groups.add("default");
      String flds = ec.getNucleusContext().getConfiguration().getStringProperty("datanucleus.detachmentFields");
      if (flds != null) {
         if (flds.equals("load-unload-fields")) {
            this.detachmentOptions = 3;
         } else if (flds.equalsIgnoreCase("unload-fields")) {
            this.detachmentOptions = 2;
         } else if (flds.equalsIgnoreCase("load-fields")) {
            this.detachmentOptions = 1;
         }
      }

   }

   private void markDirty() {
      Iterator<FetchPlanForClass> it = this.managedClass.values().iterator();

      while(it.hasNext()) {
         ((FetchPlanForClass)it.next()).markDirty();
      }

   }

   public synchronized FetchPlanForClass getFetchPlanForClass(AbstractClassMetaData cmd) {
      FetchPlanForClass fpClass = (FetchPlanForClass)this.managedClass.get(cmd.getFullClassName());
      if (fpClass == null) {
         fpClass = new FetchPlanForClass(cmd, this);
         this.managedClass.put(cmd.getFullClassName(), fpClass);
      }

      return fpClass;
   }

   public synchronized FetchPlan addGroup(String grpName) {
      if (grpName != null) {
         boolean changed = this.groups.add(grpName);
         boolean dynChanged = this.addDynamicGroup(grpName);
         if (changed || dynChanged) {
            this.markDirty();
         }
      }

      return this;
   }

   public synchronized FetchPlan removeGroup(String grpName) {
      if (grpName != null) {
         boolean changed = false;
         changed = this.groups.remove(grpName);
         if (this.dynamicGroups != null) {
            Iterator<FetchGroup> iter = this.dynamicGroups.iterator();

            while(iter.hasNext()) {
               FetchGroup grp = (FetchGroup)iter.next();
               if (grp.getName().equals(grpName)) {
                  grp.deregisterListener(this);
                  changed = true;
                  iter.remove();
               }
            }
         }

         if (changed) {
            this.markDirty();
         }
      }

      return this;
   }

   public synchronized FetchPlan clearGroups() {
      this.clearDynamicGroups();
      this.groups.clear();
      this.markDirty();
      return this;
   }

   public synchronized Set getGroups() {
      return Collections.unmodifiableSet(new HashSet(this.groups));
   }

   public synchronized FetchPlan setGroups(Collection grpNames) {
      this.clearDynamicGroups();
      this.groups.clear();
      if (grpNames != null) {
         Set g = new HashSet(grpNames);
         this.groups.addAll(g);
         Iterator<String> iter = grpNames.iterator();

         while(iter.hasNext()) {
            this.addDynamicGroup((String)iter.next());
         }
      }

      this.markDirty();
      return this;
   }

   public synchronized FetchPlan setGroups(String[] grpNames) {
      this.clearDynamicGroups();
      this.groups.clear();
      if (grpNames != null) {
         for(int i = 0; i < grpNames.length; ++i) {
            this.groups.add(grpNames[i]);
         }

         for(int i = 0; i < grpNames.length; ++i) {
            this.addDynamicGroup(grpNames[i]);
         }
      }

      this.markDirty();
      return this;
   }

   public synchronized FetchPlan setGroup(String grpName) {
      this.clearDynamicGroups();
      this.groups.clear();
      if (grpName != null) {
         this.groups.add(grpName);
         this.addDynamicGroup(grpName);
      }

      this.markDirty();
      return this;
   }

   private void clearDynamicGroups() {
      if (this.dynamicGroups != null) {
         Iterator<FetchGroup> iter = this.dynamicGroups.iterator();

         while(iter.hasNext()) {
            ((FetchGroup)iter.next()).deregisterListener(this);
         }

         this.dynamicGroups.clear();
      }

   }

   private boolean addDynamicGroup(String grpName) {
      boolean changed = false;
      Set<FetchGroup> ecGrpsWithName = this.ec.getFetchGroupsWithName(grpName);
      if (ecGrpsWithName != null) {
         if (this.dynamicGroups == null) {
            this.dynamicGroups = new HashSet();
         }

         for(FetchGroup grp : ecGrpsWithName) {
            this.dynamicGroups.add(grp);
            grp.registerListener(this);
            changed = true;
         }
      }

      if (!changed) {
         Set<FetchGroup> grpsWithName = this.ec.getNucleusContext().getFetchGroupsWithName(grpName);
         if (grpsWithName != null) {
            if (this.dynamicGroups == null) {
               this.dynamicGroups = new HashSet();
            }

            for(FetchGroup grp : grpsWithName) {
               this.dynamicGroups.add(grp);
               grp.registerListener(this);
               changed = true;
            }
         }
      }

      return changed;
   }

   public void notifyFetchGroupChange(FetchGroup group) {
      for(FetchPlanForClass fpClass : this.managedClass.values()) {
         Class cls = this.clr.classForName(fpClass.cmd.getFullClassName());
         if (cls.isAssignableFrom(group.getType()) || group.getType().isAssignableFrom(cls)) {
            fpClass.markDirty();
         }
      }

   }

   public void notifyFetchGroupRemove(FetchGroup group) {
      this.dynamicGroups.remove(group);
      this.notifyFetchGroupChange(group);
   }

   public FetchPlan setDetachmentRoots(Collection roots) {
      if (this.detachmentRootClasses == null && this.detachmentRoots == null) {
         if (roots == null) {
            this.detachmentRoots = null;
         }

         this.detachmentRoots = new ArrayList();
         this.detachmentRoots.addAll(roots);
         return this;
      } else {
         throw new NucleusUserException(Localiser.msg("006003"));
      }
   }

   public Collection getDetachmentRoots() {
      return (Collection)(this.detachmentRoots == null ? Collections.EMPTY_LIST : Collections.unmodifiableCollection(this.detachmentRoots));
   }

   public FetchPlan setDetachmentRootClasses(Class[] rootClasses) {
      if (this.detachmentRootClasses == null && this.detachmentRoots == null) {
         if (rootClasses == null) {
            this.detachmentRootClasses = null;
            return this;
         } else {
            this.detachmentRootClasses = new Class[rootClasses.length];

            for(int i = 0; i < rootClasses.length; ++i) {
               this.detachmentRootClasses[i] = rootClasses[i];
            }

            return this;
         }
      } else {
         throw new NucleusUserException(Localiser.msg("006003"));
      }
   }

   public Class[] getDetachmentRootClasses() {
      return this.detachmentRootClasses == null ? new Class[0] : this.detachmentRootClasses;
   }

   void resetDetachmentRoots() {
      this.detachmentRootClasses = null;
      this.detachmentRoots = null;
   }

   public synchronized FetchPlan setMaxFetchDepth(int max) {
      if (max == 0) {
         throw new NucleusUserException(Localiser.msg("006002", (long)max));
      } else {
         this.maxFetchDepth = max;
         return this;
      }
   }

   public synchronized int getMaxFetchDepth() {
      return this.maxFetchDepth;
   }

   public synchronized FetchPlan setFetchSize(int fetchSize) {
      if (fetchSize != -1 && fetchSize != 0 && fetchSize < 0) {
         return this;
      } else {
         this.fetchSize = fetchSize;
         return this;
      }
   }

   public synchronized int getFetchSize() {
      return this.fetchSize;
   }

   public int getDetachmentOptions() {
      return this.detachmentOptions;
   }

   public FetchPlan setDetachmentOptions(int options) {
      this.detachmentOptions = options;
      return this;
   }

   public synchronized FetchPlan getCopy() {
      FetchPlan fp = new FetchPlan(this.ec, this.clr);
      fp.maxFetchDepth = this.maxFetchDepth;
      fp.groups.remove("default");
      fp.groups.addAll(this.groups);
      if (this.dynamicGroups != null) {
         fp.dynamicGroups = new HashSet(this.dynamicGroups);
      }

      for(Map.Entry entry : this.managedClass.entrySet()) {
         String className = (String)entry.getKey();
         FetchPlanForClass fpcls = (FetchPlanForClass)entry.getValue();
         fp.managedClass.put(className, fpcls.getCopy(fp));
      }

      fp.fetchSize = this.fetchSize;
      return fp;
   }

   Boolean getCachedIsToCallPostLoadFetchPlan(AbstractClassMetaData cmd, BitSet loadedFields) {
      if (this.isToCallPostLoadFetchPlanByCmd == null) {
         this.isToCallPostLoadFetchPlanByCmd = new SoftValueMap();
      }

      Map cachedIsToCallPostLoadFetchPlan = (Map)this.isToCallPostLoadFetchPlanByCmd.get(cmd);
      return cachedIsToCallPostLoadFetchPlan == null ? null : (Boolean)cachedIsToCallPostLoadFetchPlan.get(loadedFields);
   }

   void cacheIsToCallPostLoadFetchPlan(AbstractClassMetaData cmd, BitSet loadedFields, Boolean itcplfp) {
      if (this.isToCallPostLoadFetchPlanByCmd == null) {
         this.isToCallPostLoadFetchPlanByCmd = new SoftValueMap();
      }

      Map cachedIsToCallPostLoadFetchPlan = (Map)this.isToCallPostLoadFetchPlanByCmd.get(cmd);
      if (cachedIsToCallPostLoadFetchPlan == null) {
         cachedIsToCallPostLoadFetchPlan = new SoftValueMap();
         this.isToCallPostLoadFetchPlanByCmd.put(cmd, cachedIsToCallPostLoadFetchPlan);
      }

      cachedIsToCallPostLoadFetchPlan.put(loadedFields, itcplfp);
   }

   void invalidateCachedIsToCallPostLoadFetchPlan(AbstractClassMetaData cmd) {
      if (this.isToCallPostLoadFetchPlanByCmd == null) {
         this.isToCallPostLoadFetchPlanByCmd = new SoftValueMap();
      }

      Map cachedIsToCallPostLoadFetchPlan = (Map)this.isToCallPostLoadFetchPlanByCmd.get(cmd);
      if (cachedIsToCallPostLoadFetchPlan != null) {
         cachedIsToCallPostLoadFetchPlan.clear();
      }

   }

   public String toStringWithClasses() {
      return "FetchPlan " + this.groups.toString() + " classes=" + StringUtils.collectionToString(Collections.unmodifiableCollection(this.managedClass.values()));
   }

   public String toString() {
      return "FetchPlan " + this.groups.toString();
   }
}

package org.datanucleus;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.FetchGroupMemberMetaData;
import org.datanucleus.metadata.FetchGroupMetaData;
import org.datanucleus.util.MultiMap;

public class FetchGroupManager {
   private MultiMap fetchGroupByName;
   private NucleusContext nucleusCtx;

   public FetchGroupManager(NucleusContext ctx) {
      this.nucleusCtx = ctx;
   }

   public synchronized void addFetchGroup(FetchGroup grp) {
      if (this.fetchGroupByName == null) {
         this.fetchGroupByName = new MultiMap();
      }

      Collection<FetchGroup> coll = (Collection)this.fetchGroupByName.get(grp.getName());
      if (coll != null) {
         Iterator<FetchGroup> iter = coll.iterator();

         while(iter.hasNext()) {
            FetchGroup existingGrp = (FetchGroup)iter.next();
            if (existingGrp.getName().equals(grp.getName()) && existingGrp.getType().getName().equals(grp.getType().getName())) {
               existingGrp.disconnectFromListeners();
               iter.remove();
            }
         }
      }

      this.fetchGroupByName.put(grp.getName(), grp);
   }

   public synchronized void removeFetchGroup(FetchGroup grp) {
      if (this.fetchGroupByName != null) {
         Collection<FetchGroup> coll = (Collection)this.fetchGroupByName.get(grp.getName());
         if (coll != null) {
            Iterator<FetchGroup> iter = coll.iterator();

            while(iter.hasNext()) {
               FetchGroup existingGrp = (FetchGroup)iter.next();
               if (existingGrp.getType() == grp.getType()) {
                  existingGrp.disconnectFromListeners();
                  iter.remove();
               }
            }
         }
      }

   }

   public synchronized FetchGroup getFetchGroup(Class cls, String name, boolean createIfNotPresent) {
      if (this.fetchGroupByName != null) {
         Collection<FetchGroup> coll = (Collection)this.fetchGroupByName.get(name);
         if (coll != null) {
            for(FetchGroup grp : coll) {
               if (grp.getType() == cls) {
                  return grp;
               }
            }
         }
      }

      if (createIfNotPresent) {
         FetchGroup<T> grp = this.createFetchGroup(cls, name);
         this.addFetchGroup(grp);
         return grp;
      } else {
         return null;
      }
   }

   public FetchGroup createFetchGroup(Class cls, String name) {
      FetchGroup<T> fg = new FetchGroup(this.nucleusCtx, name, cls);
      if (name.equals("default")) {
         fg.addCategory("default");
      } else {
         ClassLoaderResolver clr = this.nucleusCtx.getClassLoaderResolver(cls.getClassLoader());
         AbstractClassMetaData cmd = this.nucleusCtx.getMetaDataManager().getMetaDataForClass(cls, clr);
         if (cmd != null) {
            FetchGroupMetaData fgmd = cmd.getFetchGroupMetaData(name);
            if (fgmd != null) {
               for(FetchGroupMemberMetaData fgmmd : fgmd.getMembers()) {
                  fg.addMember(fgmmd.getName());
                  if (fgmmd.getRecursionDepth() != 1) {
                     fg.setRecursionDepth(fgmmd.getName(), fgmmd.getRecursionDepth());
                  }
               }
            }
         }
      }

      return fg;
   }

   public synchronized Set getFetchGroupsWithName(String name) {
      if (this.fetchGroupByName != null) {
         Collection coll = (Collection)this.fetchGroupByName.get(name);
         if (coll != null) {
            return new HashSet(coll);
         }
      }

      return null;
   }

   public synchronized void clearFetchGroups() {
      if (this.fetchGroupByName != null) {
         for(FetchGroup grp : this.fetchGroupByName.values()) {
            grp.disconnectFromListeners();
         }

         this.fetchGroupByName.clear();
      }

   }
}

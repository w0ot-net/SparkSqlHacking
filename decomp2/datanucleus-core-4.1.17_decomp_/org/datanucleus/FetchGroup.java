package org.datanucleus;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class FetchGroup implements Serializable {
   private static final long serialVersionUID = 8238931367627119563L;
   public static final String DEFAULT = "default";
   public static final String RELATIONSHIP = "relationship";
   public static final String MULTIVALUED = "multivalued";
   public static final String BASIC = "basic";
   public static final String ALL = "all";
   private NucleusContext nucleusCtx;
   private String name;
   private Class cls;
   private boolean postLoad = false;
   private Set memberNames = Collections.newSetFromMap(new ConcurrentHashMap());
   private Map recursionDepthByMemberName = null;
   private Collection planListeners = Collections.newSetFromMap(new ConcurrentHashMap());
   private boolean unmodifiable = false;

   public FetchGroup(NucleusContext nucleusCtx, String name, Class cls) {
      this.nucleusCtx = nucleusCtx;
      this.name = name;
      this.cls = cls;
   }

   public FetchGroup(FetchGroup grp) {
      this.name = grp.name;
      this.cls = grp.cls;
      this.nucleusCtx = grp.nucleusCtx;
      this.postLoad = grp.postLoad;

      for(String memberName : grp.memberNames) {
         this.addMember(memberName);
      }

      if (grp.recursionDepthByMemberName != null) {
         this.recursionDepthByMemberName = new ConcurrentHashMap(grp.recursionDepthByMemberName);
      }

   }

   public String getName() {
      return this.name;
   }

   public Class getType() {
      return this.cls;
   }

   public void setPostLoad(boolean postLoad) {
      this.assertUnmodifiable();
      this.postLoad = postLoad;
   }

   public boolean getPostLoad() {
      return this.postLoad;
   }

   public int getRecursionDepth(String memberName) {
      if (this.recursionDepthByMemberName != null) {
         Integer recursionValue = (Integer)this.recursionDepthByMemberName.get(memberName);
         if (recursionValue != null) {
            return recursionValue;
         }
      }

      return 1;
   }

   public FetchGroup setRecursionDepth(String memberName, int recursionDepth) {
      this.assertUnmodifiable();
      this.assertNotMember(memberName);
      if (this.memberNames.contains(memberName)) {
         synchronized(this) {
            if (this.recursionDepthByMemberName == null) {
               this.recursionDepthByMemberName = new ConcurrentHashMap();
            }

            this.recursionDepthByMemberName.put(memberName, recursionDepth);
         }
      }

      return this;
   }

   public FetchGroup setUnmodifiable() {
      if (!this.unmodifiable) {
         this.unmodifiable = true;
      }

      return this;
   }

   public boolean isUnmodifiable() {
      return this.unmodifiable;
   }

   public FetchGroup addCategory(String categoryName) {
      this.assertUnmodifiable();
      String[] memberNames = this.getMemberNamesForCategory(categoryName);
      if (memberNames != null) {
         for(int i = 0; i < memberNames.length; ++i) {
            this.memberNames.add(memberNames[i]);
         }

         this.notifyListeners();
      }

      return this;
   }

   public FetchGroup removeCategory(String categoryName) {
      this.assertUnmodifiable();
      String[] memberNames = this.getMemberNamesForCategory(categoryName);
      if (memberNames != null) {
         for(int i = 0; i < memberNames.length; ++i) {
            this.memberNames.remove(memberNames[i]);
         }

         this.notifyListeners();
      }

      return this;
   }

   private String[] getMemberNamesForCategory(String categoryName) {
      AbstractClassMetaData cmd = this.getMetaDataForClass();
      int[] memberPositions = null;
      if (categoryName.equals("default")) {
         memberPositions = cmd.getDFGMemberPositions();
      } else if (categoryName.equals("all")) {
         memberPositions = cmd.getAllMemberPositions();
      } else if (categoryName.equals("basic")) {
         memberPositions = cmd.getBasicMemberPositions(this.nucleusCtx.getClassLoaderResolver((ClassLoader)null), this.nucleusCtx.getMetaDataManager());
      } else if (categoryName.equals("relationship")) {
         memberPositions = cmd.getRelationMemberPositions(this.nucleusCtx.getClassLoaderResolver((ClassLoader)null), this.nucleusCtx.getMetaDataManager());
      } else {
         if (!categoryName.equals("multivalued")) {
            throw this.nucleusCtx.getApiAdapter().getUserExceptionForException("Category " + categoryName + " is invalid", (Exception)null);
         }

         memberPositions = cmd.getMultivaluedMemberPositions();
      }

      String[] names = new String[memberPositions.length];

      for(int i = 0; i < memberPositions.length; ++i) {
         names[i] = cmd.getMetaDataForManagedMemberAtAbsolutePosition(memberPositions[i]).getName();
      }

      return names;
   }

   public Set getMembers() {
      return this.memberNames;
   }

   public FetchGroup addMember(String memberName) {
      this.assertUnmodifiable();
      this.assertNotMember(memberName);
      this.memberNames.add(memberName);
      this.notifyListeners();
      return this;
   }

   public FetchGroup removeMember(String memberName) {
      this.assertUnmodifiable();
      this.assertNotMember(memberName);
      this.memberNames.remove(memberName);
      this.notifyListeners();
      return this;
   }

   public FetchGroup addMembers(String[] members) {
      if (members == null) {
         return this;
      } else {
         for(int i = 0; i < members.length; ++i) {
            this.addMember(members[i]);
         }

         this.notifyListeners();
         return this;
      }
   }

   public FetchGroup removeMembers(String[] members) {
      if (members == null) {
         return this;
      } else {
         for(int i = 0; i < members.length; ++i) {
            this.removeMember(members[i]);
         }

         this.notifyListeners();
         return this;
      }
   }

   private void notifyListeners() {
      if (!this.planListeners.isEmpty()) {
         Iterator<FetchPlan> iter = this.planListeners.iterator();

         while(iter.hasNext()) {
            ((FetchPlan)iter.next()).notifyFetchGroupChange(this);
         }
      }

   }

   public Collection getListenerFPs() {
      return Collections.unmodifiableCollection(this.planListeners);
   }

   public void registerListener(FetchPlan plan) {
      this.planListeners.add(plan);
   }

   public void deregisterListener(FetchPlan plan) {
      if (!this.planListeners.isEmpty()) {
         this.planListeners.remove(plan);
      }

   }

   public void disconnectFromListeners() {
      if (!this.planListeners.isEmpty()) {
         Iterator<FetchPlan> iter = this.planListeners.iterator();

         while(iter.hasNext()) {
            ((FetchPlan)iter.next()).notifyFetchGroupRemove(this);
         }

         this.planListeners.clear();
      }

   }

   private void assertUnmodifiable() {
      if (this.unmodifiable) {
         throw this.nucleusCtx.getApiAdapter().getUserExceptionForException("FetchGroup is not modifiable!", (Exception)null);
      }
   }

   private void assertNotMember(String memberName) {
      AbstractClassMetaData acmd = this.getMetaDataForClass();
      if (!acmd.hasMember(memberName)) {
         throw this.nucleusCtx.getApiAdapter().getUserExceptionForException(Localiser.msg("006004", memberName, this.cls.getName()), (Exception)null);
      }
   }

   private AbstractClassMetaData getMetaDataForClass() {
      AbstractClassMetaData cmd = null;
      if (this.cls.isInterface()) {
         cmd = this.nucleusCtx.getMetaDataManager().getMetaDataForInterface(this.cls, this.nucleusCtx.getClassLoaderResolver((ClassLoader)null));
      } else {
         cmd = this.nucleusCtx.getMetaDataManager().getMetaDataForClass(this.cls, this.nucleusCtx.getClassLoaderResolver((ClassLoader)null));
      }

      return cmd;
   }

   public boolean equals(Object obj) {
      if (obj != null && obj instanceof FetchGroup) {
         FetchGroup other = (FetchGroup)obj;
         return other.cls == this.cls && other.name.equals(this.name);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.name.hashCode() ^ this.cls.hashCode();
   }

   public String toString() {
      return "FetchGroup<" + this.cls.getName() + "> : " + this.name + " members=[" + StringUtils.collectionToString(this.memberNames) + "], modifiable=" + !this.unmodifiable + ", postLoad=" + this.postLoad + ", listeners.size=" + (this.planListeners != null ? this.planListeners.size() : 0);
   }
}

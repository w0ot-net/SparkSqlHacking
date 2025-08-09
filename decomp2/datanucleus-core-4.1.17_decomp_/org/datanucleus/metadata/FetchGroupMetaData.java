package org.datanucleus.metadata;

import java.util.HashSet;
import java.util.Set;

public class FetchGroupMetaData extends MetaData {
   private static final long serialVersionUID = -9024912323171424927L;
   boolean postLoad = false;
   final String name;
   protected Set fetchGroups = null;
   protected Set members = null;

   public FetchGroupMetaData(String name) {
      this.name = name;
   }

   public final String getName() {
      return this.name;
   }

   public final Boolean getPostLoad() {
      return this.postLoad;
   }

   public FetchGroupMetaData setPostLoad(Boolean postLoad) {
      this.postLoad = postLoad;
      return this;
   }

   public final Set getFetchGroups() {
      return this.fetchGroups;
   }

   public final Set getMembers() {
      return this.members;
   }

   public int getNumberOfMembers() {
      return this.members != null ? this.members.size() : 0;
   }

   public void addFetchGroup(FetchGroupMetaData fgmd) {
      if (this.fetchGroups == null) {
         this.fetchGroups = new HashSet();
      }

      this.fetchGroups.add(fgmd);
      fgmd.parent = this;
   }

   public void addMember(FetchGroupMemberMetaData fgmmd) {
      if (this.members == null) {
         this.members = new HashSet();
      }

      this.members.add(fgmmd);
      fgmmd.parent = this;
   }

   public FetchGroupMemberMetaData newMemberMetaData(String name) {
      FetchGroupMemberMetaData fgmmd = new FetchGroupMemberMetaData(this, name);
      this.addMember(fgmmd);
      return fgmmd;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<fetch-group name=\"" + this.name + "\">\n");
      if (this.fetchGroups != null) {
         for(FetchGroupMetaData fgmd : this.fetchGroups) {
            sb.append(fgmd.toString(prefix + indent, indent));
         }
      }

      if (this.members != null) {
         for(FetchGroupMemberMetaData fgmmd : this.members) {
            sb.append(fgmmd.toString(prefix + indent, indent));
         }
      }

      sb.append(prefix + "</fetch-group>\n");
      return sb.toString();
   }
}

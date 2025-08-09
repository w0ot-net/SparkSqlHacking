package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class APIGroupFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private String name;
   private GroupVersionForDiscoveryBuilder preferredVersion;
   private ArrayList serverAddressByClientCIDRs = new ArrayList();
   private ArrayList versions = new ArrayList();
   private Map additionalProperties;

   public APIGroupFluent() {
   }

   public APIGroupFluent(APIGroup instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIGroup instance) {
      instance = instance != null ? instance : new APIGroup();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withPreferredVersion(instance.getPreferredVersion());
         this.withServerAddressByClientCIDRs(instance.getServerAddressByClientCIDRs());
         this.withVersions(instance.getVersions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public APIGroupFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public APIGroupFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public APIGroupFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public GroupVersionForDiscovery buildPreferredVersion() {
      return this.preferredVersion != null ? this.preferredVersion.build() : null;
   }

   public APIGroupFluent withPreferredVersion(GroupVersionForDiscovery preferredVersion) {
      this._visitables.remove("preferredVersion");
      if (preferredVersion != null) {
         this.preferredVersion = new GroupVersionForDiscoveryBuilder(preferredVersion);
         this._visitables.get("preferredVersion").add(this.preferredVersion);
      } else {
         this.preferredVersion = null;
         this._visitables.get("preferredVersion").remove(this.preferredVersion);
      }

      return this;
   }

   public boolean hasPreferredVersion() {
      return this.preferredVersion != null;
   }

   public APIGroupFluent withNewPreferredVersion(String groupVersion, String version) {
      return this.withPreferredVersion(new GroupVersionForDiscovery(groupVersion, version));
   }

   public PreferredVersionNested withNewPreferredVersion() {
      return new PreferredVersionNested((GroupVersionForDiscovery)null);
   }

   public PreferredVersionNested withNewPreferredVersionLike(GroupVersionForDiscovery item) {
      return new PreferredVersionNested(item);
   }

   public PreferredVersionNested editPreferredVersion() {
      return this.withNewPreferredVersionLike((GroupVersionForDiscovery)Optional.ofNullable(this.buildPreferredVersion()).orElse((Object)null));
   }

   public PreferredVersionNested editOrNewPreferredVersion() {
      return this.withNewPreferredVersionLike((GroupVersionForDiscovery)Optional.ofNullable(this.buildPreferredVersion()).orElse((new GroupVersionForDiscoveryBuilder()).build()));
   }

   public PreferredVersionNested editOrNewPreferredVersionLike(GroupVersionForDiscovery item) {
      return this.withNewPreferredVersionLike((GroupVersionForDiscovery)Optional.ofNullable(this.buildPreferredVersion()).orElse(item));
   }

   public APIGroupFluent addToServerAddressByClientCIDRs(int index, ServerAddressByClientCIDR item) {
      if (this.serverAddressByClientCIDRs == null) {
         this.serverAddressByClientCIDRs = new ArrayList();
      }

      ServerAddressByClientCIDRBuilder builder = new ServerAddressByClientCIDRBuilder(item);
      if (index >= 0 && index < this.serverAddressByClientCIDRs.size()) {
         this._visitables.get("serverAddressByClientCIDRs").add(index, builder);
         this.serverAddressByClientCIDRs.add(index, builder);
      } else {
         this._visitables.get("serverAddressByClientCIDRs").add(builder);
         this.serverAddressByClientCIDRs.add(builder);
      }

      return this;
   }

   public APIGroupFluent setToServerAddressByClientCIDRs(int index, ServerAddressByClientCIDR item) {
      if (this.serverAddressByClientCIDRs == null) {
         this.serverAddressByClientCIDRs = new ArrayList();
      }

      ServerAddressByClientCIDRBuilder builder = new ServerAddressByClientCIDRBuilder(item);
      if (index >= 0 && index < this.serverAddressByClientCIDRs.size()) {
         this._visitables.get("serverAddressByClientCIDRs").set(index, builder);
         this.serverAddressByClientCIDRs.set(index, builder);
      } else {
         this._visitables.get("serverAddressByClientCIDRs").add(builder);
         this.serverAddressByClientCIDRs.add(builder);
      }

      return this;
   }

   public APIGroupFluent addToServerAddressByClientCIDRs(ServerAddressByClientCIDR... items) {
      if (this.serverAddressByClientCIDRs == null) {
         this.serverAddressByClientCIDRs = new ArrayList();
      }

      for(ServerAddressByClientCIDR item : items) {
         ServerAddressByClientCIDRBuilder builder = new ServerAddressByClientCIDRBuilder(item);
         this._visitables.get("serverAddressByClientCIDRs").add(builder);
         this.serverAddressByClientCIDRs.add(builder);
      }

      return this;
   }

   public APIGroupFluent addAllToServerAddressByClientCIDRs(Collection items) {
      if (this.serverAddressByClientCIDRs == null) {
         this.serverAddressByClientCIDRs = new ArrayList();
      }

      for(ServerAddressByClientCIDR item : items) {
         ServerAddressByClientCIDRBuilder builder = new ServerAddressByClientCIDRBuilder(item);
         this._visitables.get("serverAddressByClientCIDRs").add(builder);
         this.serverAddressByClientCIDRs.add(builder);
      }

      return this;
   }

   public APIGroupFluent removeFromServerAddressByClientCIDRs(ServerAddressByClientCIDR... items) {
      if (this.serverAddressByClientCIDRs == null) {
         return this;
      } else {
         for(ServerAddressByClientCIDR item : items) {
            ServerAddressByClientCIDRBuilder builder = new ServerAddressByClientCIDRBuilder(item);
            this._visitables.get("serverAddressByClientCIDRs").remove(builder);
            this.serverAddressByClientCIDRs.remove(builder);
         }

         return this;
      }
   }

   public APIGroupFluent removeAllFromServerAddressByClientCIDRs(Collection items) {
      if (this.serverAddressByClientCIDRs == null) {
         return this;
      } else {
         for(ServerAddressByClientCIDR item : items) {
            ServerAddressByClientCIDRBuilder builder = new ServerAddressByClientCIDRBuilder(item);
            this._visitables.get("serverAddressByClientCIDRs").remove(builder);
            this.serverAddressByClientCIDRs.remove(builder);
         }

         return this;
      }
   }

   public APIGroupFluent removeMatchingFromServerAddressByClientCIDRs(Predicate predicate) {
      if (this.serverAddressByClientCIDRs == null) {
         return this;
      } else {
         Iterator<ServerAddressByClientCIDRBuilder> each = this.serverAddressByClientCIDRs.iterator();
         List visitables = this._visitables.get("serverAddressByClientCIDRs");

         while(each.hasNext()) {
            ServerAddressByClientCIDRBuilder builder = (ServerAddressByClientCIDRBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildServerAddressByClientCIDRs() {
      return this.serverAddressByClientCIDRs != null ? build(this.serverAddressByClientCIDRs) : null;
   }

   public ServerAddressByClientCIDR buildServerAddressByClientCIDR(int index) {
      return ((ServerAddressByClientCIDRBuilder)this.serverAddressByClientCIDRs.get(index)).build();
   }

   public ServerAddressByClientCIDR buildFirstServerAddressByClientCIDR() {
      return ((ServerAddressByClientCIDRBuilder)this.serverAddressByClientCIDRs.get(0)).build();
   }

   public ServerAddressByClientCIDR buildLastServerAddressByClientCIDR() {
      return ((ServerAddressByClientCIDRBuilder)this.serverAddressByClientCIDRs.get(this.serverAddressByClientCIDRs.size() - 1)).build();
   }

   public ServerAddressByClientCIDR buildMatchingServerAddressByClientCIDR(Predicate predicate) {
      for(ServerAddressByClientCIDRBuilder item : this.serverAddressByClientCIDRs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingServerAddressByClientCIDR(Predicate predicate) {
      for(ServerAddressByClientCIDRBuilder item : this.serverAddressByClientCIDRs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public APIGroupFluent withServerAddressByClientCIDRs(List serverAddressByClientCIDRs) {
      if (this.serverAddressByClientCIDRs != null) {
         this._visitables.get("serverAddressByClientCIDRs").clear();
      }

      if (serverAddressByClientCIDRs != null) {
         this.serverAddressByClientCIDRs = new ArrayList();

         for(ServerAddressByClientCIDR item : serverAddressByClientCIDRs) {
            this.addToServerAddressByClientCIDRs(item);
         }
      } else {
         this.serverAddressByClientCIDRs = null;
      }

      return this;
   }

   public APIGroupFluent withServerAddressByClientCIDRs(ServerAddressByClientCIDR... serverAddressByClientCIDRs) {
      if (this.serverAddressByClientCIDRs != null) {
         this.serverAddressByClientCIDRs.clear();
         this._visitables.remove("serverAddressByClientCIDRs");
      }

      if (serverAddressByClientCIDRs != null) {
         for(ServerAddressByClientCIDR item : serverAddressByClientCIDRs) {
            this.addToServerAddressByClientCIDRs(item);
         }
      }

      return this;
   }

   public boolean hasServerAddressByClientCIDRs() {
      return this.serverAddressByClientCIDRs != null && !this.serverAddressByClientCIDRs.isEmpty();
   }

   public APIGroupFluent addNewServerAddressByClientCIDR(String clientCIDR, String serverAddress) {
      return this.addToServerAddressByClientCIDRs(new ServerAddressByClientCIDR(clientCIDR, serverAddress));
   }

   public ServerAddressByClientCIDRsNested addNewServerAddressByClientCIDR() {
      return new ServerAddressByClientCIDRsNested(-1, (ServerAddressByClientCIDR)null);
   }

   public ServerAddressByClientCIDRsNested addNewServerAddressByClientCIDRLike(ServerAddressByClientCIDR item) {
      return new ServerAddressByClientCIDRsNested(-1, item);
   }

   public ServerAddressByClientCIDRsNested setNewServerAddressByClientCIDRLike(int index, ServerAddressByClientCIDR item) {
      return new ServerAddressByClientCIDRsNested(index, item);
   }

   public ServerAddressByClientCIDRsNested editServerAddressByClientCIDR(int index) {
      if (this.serverAddressByClientCIDRs.size() <= index) {
         throw new RuntimeException("Can't edit serverAddressByClientCIDRs. Index exceeds size.");
      } else {
         return this.setNewServerAddressByClientCIDRLike(index, this.buildServerAddressByClientCIDR(index));
      }
   }

   public ServerAddressByClientCIDRsNested editFirstServerAddressByClientCIDR() {
      if (this.serverAddressByClientCIDRs.size() == 0) {
         throw new RuntimeException("Can't edit first serverAddressByClientCIDRs. The list is empty.");
      } else {
         return this.setNewServerAddressByClientCIDRLike(0, this.buildServerAddressByClientCIDR(0));
      }
   }

   public ServerAddressByClientCIDRsNested editLastServerAddressByClientCIDR() {
      int index = this.serverAddressByClientCIDRs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last serverAddressByClientCIDRs. The list is empty.");
      } else {
         return this.setNewServerAddressByClientCIDRLike(index, this.buildServerAddressByClientCIDR(index));
      }
   }

   public ServerAddressByClientCIDRsNested editMatchingServerAddressByClientCIDR(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.serverAddressByClientCIDRs.size(); ++i) {
         if (predicate.test((ServerAddressByClientCIDRBuilder)this.serverAddressByClientCIDRs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching serverAddressByClientCIDRs. No match found.");
      } else {
         return this.setNewServerAddressByClientCIDRLike(index, this.buildServerAddressByClientCIDR(index));
      }
   }

   public APIGroupFluent addToVersions(int index, GroupVersionForDiscovery item) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      GroupVersionForDiscoveryBuilder builder = new GroupVersionForDiscoveryBuilder(item);
      if (index >= 0 && index < this.versions.size()) {
         this._visitables.get("versions").add(index, builder);
         this.versions.add(index, builder);
      } else {
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public APIGroupFluent setToVersions(int index, GroupVersionForDiscovery item) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      GroupVersionForDiscoveryBuilder builder = new GroupVersionForDiscoveryBuilder(item);
      if (index >= 0 && index < this.versions.size()) {
         this._visitables.get("versions").set(index, builder);
         this.versions.set(index, builder);
      } else {
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public APIGroupFluent addToVersions(GroupVersionForDiscovery... items) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      for(GroupVersionForDiscovery item : items) {
         GroupVersionForDiscoveryBuilder builder = new GroupVersionForDiscoveryBuilder(item);
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public APIGroupFluent addAllToVersions(Collection items) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      for(GroupVersionForDiscovery item : items) {
         GroupVersionForDiscoveryBuilder builder = new GroupVersionForDiscoveryBuilder(item);
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public APIGroupFluent removeFromVersions(GroupVersionForDiscovery... items) {
      if (this.versions == null) {
         return this;
      } else {
         for(GroupVersionForDiscovery item : items) {
            GroupVersionForDiscoveryBuilder builder = new GroupVersionForDiscoveryBuilder(item);
            this._visitables.get("versions").remove(builder);
            this.versions.remove(builder);
         }

         return this;
      }
   }

   public APIGroupFluent removeAllFromVersions(Collection items) {
      if (this.versions == null) {
         return this;
      } else {
         for(GroupVersionForDiscovery item : items) {
            GroupVersionForDiscoveryBuilder builder = new GroupVersionForDiscoveryBuilder(item);
            this._visitables.get("versions").remove(builder);
            this.versions.remove(builder);
         }

         return this;
      }
   }

   public APIGroupFluent removeMatchingFromVersions(Predicate predicate) {
      if (this.versions == null) {
         return this;
      } else {
         Iterator<GroupVersionForDiscoveryBuilder> each = this.versions.iterator();
         List visitables = this._visitables.get("versions");

         while(each.hasNext()) {
            GroupVersionForDiscoveryBuilder builder = (GroupVersionForDiscoveryBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVersions() {
      return this.versions != null ? build(this.versions) : null;
   }

   public GroupVersionForDiscovery buildVersion(int index) {
      return ((GroupVersionForDiscoveryBuilder)this.versions.get(index)).build();
   }

   public GroupVersionForDiscovery buildFirstVersion() {
      return ((GroupVersionForDiscoveryBuilder)this.versions.get(0)).build();
   }

   public GroupVersionForDiscovery buildLastVersion() {
      return ((GroupVersionForDiscoveryBuilder)this.versions.get(this.versions.size() - 1)).build();
   }

   public GroupVersionForDiscovery buildMatchingVersion(Predicate predicate) {
      for(GroupVersionForDiscoveryBuilder item : this.versions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVersion(Predicate predicate) {
      for(GroupVersionForDiscoveryBuilder item : this.versions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public APIGroupFluent withVersions(List versions) {
      if (this.versions != null) {
         this._visitables.get("versions").clear();
      }

      if (versions != null) {
         this.versions = new ArrayList();

         for(GroupVersionForDiscovery item : versions) {
            this.addToVersions(item);
         }
      } else {
         this.versions = null;
      }

      return this;
   }

   public APIGroupFluent withVersions(GroupVersionForDiscovery... versions) {
      if (this.versions != null) {
         this.versions.clear();
         this._visitables.remove("versions");
      }

      if (versions != null) {
         for(GroupVersionForDiscovery item : versions) {
            this.addToVersions(item);
         }
      }

      return this;
   }

   public boolean hasVersions() {
      return this.versions != null && !this.versions.isEmpty();
   }

   public APIGroupFluent addNewVersion(String groupVersion, String version) {
      return this.addToVersions(new GroupVersionForDiscovery(groupVersion, version));
   }

   public VersionsNested addNewVersion() {
      return new VersionsNested(-1, (GroupVersionForDiscovery)null);
   }

   public VersionsNested addNewVersionLike(GroupVersionForDiscovery item) {
      return new VersionsNested(-1, item);
   }

   public VersionsNested setNewVersionLike(int index, GroupVersionForDiscovery item) {
      return new VersionsNested(index, item);
   }

   public VersionsNested editVersion(int index) {
      if (this.versions.size() <= index) {
         throw new RuntimeException("Can't edit versions. Index exceeds size.");
      } else {
         return this.setNewVersionLike(index, this.buildVersion(index));
      }
   }

   public VersionsNested editFirstVersion() {
      if (this.versions.size() == 0) {
         throw new RuntimeException("Can't edit first versions. The list is empty.");
      } else {
         return this.setNewVersionLike(0, this.buildVersion(0));
      }
   }

   public VersionsNested editLastVersion() {
      int index = this.versions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last versions. The list is empty.");
      } else {
         return this.setNewVersionLike(index, this.buildVersion(index));
      }
   }

   public VersionsNested editMatchingVersion(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.versions.size(); ++i) {
         if (predicate.test((GroupVersionForDiscoveryBuilder)this.versions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching versions. No match found.");
      } else {
         return this.setNewVersionLike(index, this.buildVersion(index));
      }
   }

   public APIGroupFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIGroupFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIGroupFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIGroupFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public APIGroupFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            APIGroupFluent that = (APIGroupFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.preferredVersion, that.preferredVersion)) {
               return false;
            } else if (!Objects.equals(this.serverAddressByClientCIDRs, that.serverAddressByClientCIDRs)) {
               return false;
            } else if (!Objects.equals(this.versions, that.versions)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.name, this.preferredVersion, this.serverAddressByClientCIDRs, this.versions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.preferredVersion != null) {
         sb.append("preferredVersion:");
         sb.append(this.preferredVersion + ",");
      }

      if (this.serverAddressByClientCIDRs != null && !this.serverAddressByClientCIDRs.isEmpty()) {
         sb.append("serverAddressByClientCIDRs:");
         sb.append(this.serverAddressByClientCIDRs + ",");
      }

      if (this.versions != null && !this.versions.isEmpty()) {
         sb.append("versions:");
         sb.append(this.versions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PreferredVersionNested extends GroupVersionForDiscoveryFluent implements Nested {
      GroupVersionForDiscoveryBuilder builder;

      PreferredVersionNested(GroupVersionForDiscovery item) {
         this.builder = new GroupVersionForDiscoveryBuilder(this, item);
      }

      public Object and() {
         return APIGroupFluent.this.withPreferredVersion(this.builder.build());
      }

      public Object endPreferredVersion() {
         return this.and();
      }
   }

   public class ServerAddressByClientCIDRsNested extends ServerAddressByClientCIDRFluent implements Nested {
      ServerAddressByClientCIDRBuilder builder;
      int index;

      ServerAddressByClientCIDRsNested(int index, ServerAddressByClientCIDR item) {
         this.index = index;
         this.builder = new ServerAddressByClientCIDRBuilder(this, item);
      }

      public Object and() {
         return APIGroupFluent.this.setToServerAddressByClientCIDRs(this.index, this.builder.build());
      }

      public Object endServerAddressByClientCIDR() {
         return this.and();
      }
   }

   public class VersionsNested extends GroupVersionForDiscoveryFluent implements Nested {
      GroupVersionForDiscoveryBuilder builder;
      int index;

      VersionsNested(int index, GroupVersionForDiscovery item) {
         this.index = index;
         this.builder = new GroupVersionForDiscoveryBuilder(this, item);
      }

      public Object and() {
         return APIGroupFluent.this.setToVersions(this.index, this.builder.build());
      }

      public Object endVersion() {
         return this.and();
      }
   }
}

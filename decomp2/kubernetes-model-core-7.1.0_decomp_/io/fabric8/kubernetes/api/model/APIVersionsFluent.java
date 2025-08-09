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
import java.util.function.Predicate;

public class APIVersionsFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ArrayList serverAddressByClientCIDRs = new ArrayList();
   private List versions = new ArrayList();
   private Map additionalProperties;

   public APIVersionsFluent() {
   }

   public APIVersionsFluent(APIVersions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIVersions instance) {
      instance = instance != null ? instance : new APIVersions();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withServerAddressByClientCIDRs(instance.getServerAddressByClientCIDRs());
         this.withVersions(instance.getVersions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public APIVersionsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public APIVersionsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public APIVersionsFluent addToServerAddressByClientCIDRs(int index, ServerAddressByClientCIDR item) {
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

   public APIVersionsFluent setToServerAddressByClientCIDRs(int index, ServerAddressByClientCIDR item) {
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

   public APIVersionsFluent addToServerAddressByClientCIDRs(ServerAddressByClientCIDR... items) {
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

   public APIVersionsFluent addAllToServerAddressByClientCIDRs(Collection items) {
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

   public APIVersionsFluent removeFromServerAddressByClientCIDRs(ServerAddressByClientCIDR... items) {
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

   public APIVersionsFluent removeAllFromServerAddressByClientCIDRs(Collection items) {
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

   public APIVersionsFluent removeMatchingFromServerAddressByClientCIDRs(Predicate predicate) {
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

   public APIVersionsFluent withServerAddressByClientCIDRs(List serverAddressByClientCIDRs) {
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

   public APIVersionsFluent withServerAddressByClientCIDRs(ServerAddressByClientCIDR... serverAddressByClientCIDRs) {
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

   public APIVersionsFluent addNewServerAddressByClientCIDR(String clientCIDR, String serverAddress) {
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

   public APIVersionsFluent addToVersions(int index, String item) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      this.versions.add(index, item);
      return this;
   }

   public APIVersionsFluent setToVersions(int index, String item) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      this.versions.set(index, item);
      return this;
   }

   public APIVersionsFluent addToVersions(String... items) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      for(String item : items) {
         this.versions.add(item);
      }

      return this;
   }

   public APIVersionsFluent addAllToVersions(Collection items) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      for(String item : items) {
         this.versions.add(item);
      }

      return this;
   }

   public APIVersionsFluent removeFromVersions(String... items) {
      if (this.versions == null) {
         return this;
      } else {
         for(String item : items) {
            this.versions.remove(item);
         }

         return this;
      }
   }

   public APIVersionsFluent removeAllFromVersions(Collection items) {
      if (this.versions == null) {
         return this;
      } else {
         for(String item : items) {
            this.versions.remove(item);
         }

         return this;
      }
   }

   public List getVersions() {
      return this.versions;
   }

   public String getVersion(int index) {
      return (String)this.versions.get(index);
   }

   public String getFirstVersion() {
      return (String)this.versions.get(0);
   }

   public String getLastVersion() {
      return (String)this.versions.get(this.versions.size() - 1);
   }

   public String getMatchingVersion(Predicate predicate) {
      for(String item : this.versions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingVersion(Predicate predicate) {
      for(String item : this.versions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public APIVersionsFluent withVersions(List versions) {
      if (versions != null) {
         this.versions = new ArrayList();

         for(String item : versions) {
            this.addToVersions(item);
         }
      } else {
         this.versions = null;
      }

      return this;
   }

   public APIVersionsFluent withVersions(String... versions) {
      if (this.versions != null) {
         this.versions.clear();
         this._visitables.remove("versions");
      }

      if (versions != null) {
         for(String item : versions) {
            this.addToVersions(item);
         }
      }

      return this;
   }

   public boolean hasVersions() {
      return this.versions != null && !this.versions.isEmpty();
   }

   public APIVersionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIVersionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIVersionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIVersionsFluent removeFromAdditionalProperties(Map map) {
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

   public APIVersionsFluent withAdditionalProperties(Map additionalProperties) {
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
            APIVersionsFluent that = (APIVersionsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.serverAddressByClientCIDRs, this.versions, this.additionalProperties, super.hashCode()});
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

   public class ServerAddressByClientCIDRsNested extends ServerAddressByClientCIDRFluent implements Nested {
      ServerAddressByClientCIDRBuilder builder;
      int index;

      ServerAddressByClientCIDRsNested(int index, ServerAddressByClientCIDR item) {
         this.index = index;
         this.builder = new ServerAddressByClientCIDRBuilder(this, item);
      }

      public Object and() {
         return APIVersionsFluent.this.setToServerAddressByClientCIDRs(this.index, this.builder.build());
      }

      public Object endServerAddressByClientCIDR() {
         return this.and();
      }
   }
}

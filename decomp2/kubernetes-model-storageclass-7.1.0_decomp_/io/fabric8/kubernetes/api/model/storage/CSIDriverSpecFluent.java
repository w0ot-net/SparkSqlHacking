package io.fabric8.kubernetes.api.model.storage;

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

public class CSIDriverSpecFluent extends BaseFluent {
   private Boolean attachRequired;
   private String fsGroupPolicy;
   private Boolean podInfoOnMount;
   private Boolean requiresRepublish;
   private Boolean seLinuxMount;
   private Boolean storageCapacity;
   private ArrayList tokenRequests = new ArrayList();
   private List volumeLifecycleModes = new ArrayList();
   private Map additionalProperties;

   public CSIDriverSpecFluent() {
   }

   public CSIDriverSpecFluent(CSIDriverSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CSIDriverSpec instance) {
      instance = instance != null ? instance : new CSIDriverSpec();
      if (instance != null) {
         this.withAttachRequired(instance.getAttachRequired());
         this.withFsGroupPolicy(instance.getFsGroupPolicy());
         this.withPodInfoOnMount(instance.getPodInfoOnMount());
         this.withRequiresRepublish(instance.getRequiresRepublish());
         this.withSeLinuxMount(instance.getSeLinuxMount());
         this.withStorageCapacity(instance.getStorageCapacity());
         this.withTokenRequests(instance.getTokenRequests());
         this.withVolumeLifecycleModes(instance.getVolumeLifecycleModes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAttachRequired() {
      return this.attachRequired;
   }

   public CSIDriverSpecFluent withAttachRequired(Boolean attachRequired) {
      this.attachRequired = attachRequired;
      return this;
   }

   public boolean hasAttachRequired() {
      return this.attachRequired != null;
   }

   public String getFsGroupPolicy() {
      return this.fsGroupPolicy;
   }

   public CSIDriverSpecFluent withFsGroupPolicy(String fsGroupPolicy) {
      this.fsGroupPolicy = fsGroupPolicy;
      return this;
   }

   public boolean hasFsGroupPolicy() {
      return this.fsGroupPolicy != null;
   }

   public Boolean getPodInfoOnMount() {
      return this.podInfoOnMount;
   }

   public CSIDriverSpecFluent withPodInfoOnMount(Boolean podInfoOnMount) {
      this.podInfoOnMount = podInfoOnMount;
      return this;
   }

   public boolean hasPodInfoOnMount() {
      return this.podInfoOnMount != null;
   }

   public Boolean getRequiresRepublish() {
      return this.requiresRepublish;
   }

   public CSIDriverSpecFluent withRequiresRepublish(Boolean requiresRepublish) {
      this.requiresRepublish = requiresRepublish;
      return this;
   }

   public boolean hasRequiresRepublish() {
      return this.requiresRepublish != null;
   }

   public Boolean getSeLinuxMount() {
      return this.seLinuxMount;
   }

   public CSIDriverSpecFluent withSeLinuxMount(Boolean seLinuxMount) {
      this.seLinuxMount = seLinuxMount;
      return this;
   }

   public boolean hasSeLinuxMount() {
      return this.seLinuxMount != null;
   }

   public Boolean getStorageCapacity() {
      return this.storageCapacity;
   }

   public CSIDriverSpecFluent withStorageCapacity(Boolean storageCapacity) {
      this.storageCapacity = storageCapacity;
      return this;
   }

   public boolean hasStorageCapacity() {
      return this.storageCapacity != null;
   }

   public CSIDriverSpecFluent addToTokenRequests(int index, TokenRequest item) {
      if (this.tokenRequests == null) {
         this.tokenRequests = new ArrayList();
      }

      TokenRequestBuilder builder = new TokenRequestBuilder(item);
      if (index >= 0 && index < this.tokenRequests.size()) {
         this._visitables.get("tokenRequests").add(index, builder);
         this.tokenRequests.add(index, builder);
      } else {
         this._visitables.get("tokenRequests").add(builder);
         this.tokenRequests.add(builder);
      }

      return this;
   }

   public CSIDriverSpecFluent setToTokenRequests(int index, TokenRequest item) {
      if (this.tokenRequests == null) {
         this.tokenRequests = new ArrayList();
      }

      TokenRequestBuilder builder = new TokenRequestBuilder(item);
      if (index >= 0 && index < this.tokenRequests.size()) {
         this._visitables.get("tokenRequests").set(index, builder);
         this.tokenRequests.set(index, builder);
      } else {
         this._visitables.get("tokenRequests").add(builder);
         this.tokenRequests.add(builder);
      }

      return this;
   }

   public CSIDriverSpecFluent addToTokenRequests(TokenRequest... items) {
      if (this.tokenRequests == null) {
         this.tokenRequests = new ArrayList();
      }

      for(TokenRequest item : items) {
         TokenRequestBuilder builder = new TokenRequestBuilder(item);
         this._visitables.get("tokenRequests").add(builder);
         this.tokenRequests.add(builder);
      }

      return this;
   }

   public CSIDriverSpecFluent addAllToTokenRequests(Collection items) {
      if (this.tokenRequests == null) {
         this.tokenRequests = new ArrayList();
      }

      for(TokenRequest item : items) {
         TokenRequestBuilder builder = new TokenRequestBuilder(item);
         this._visitables.get("tokenRequests").add(builder);
         this.tokenRequests.add(builder);
      }

      return this;
   }

   public CSIDriverSpecFluent removeFromTokenRequests(TokenRequest... items) {
      if (this.tokenRequests == null) {
         return this;
      } else {
         for(TokenRequest item : items) {
            TokenRequestBuilder builder = new TokenRequestBuilder(item);
            this._visitables.get("tokenRequests").remove(builder);
            this.tokenRequests.remove(builder);
         }

         return this;
      }
   }

   public CSIDriverSpecFluent removeAllFromTokenRequests(Collection items) {
      if (this.tokenRequests == null) {
         return this;
      } else {
         for(TokenRequest item : items) {
            TokenRequestBuilder builder = new TokenRequestBuilder(item);
            this._visitables.get("tokenRequests").remove(builder);
            this.tokenRequests.remove(builder);
         }

         return this;
      }
   }

   public CSIDriverSpecFluent removeMatchingFromTokenRequests(Predicate predicate) {
      if (this.tokenRequests == null) {
         return this;
      } else {
         Iterator<TokenRequestBuilder> each = this.tokenRequests.iterator();
         List visitables = this._visitables.get("tokenRequests");

         while(each.hasNext()) {
            TokenRequestBuilder builder = (TokenRequestBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTokenRequests() {
      return this.tokenRequests != null ? build(this.tokenRequests) : null;
   }

   public TokenRequest buildTokenRequest(int index) {
      return ((TokenRequestBuilder)this.tokenRequests.get(index)).build();
   }

   public TokenRequest buildFirstTokenRequest() {
      return ((TokenRequestBuilder)this.tokenRequests.get(0)).build();
   }

   public TokenRequest buildLastTokenRequest() {
      return ((TokenRequestBuilder)this.tokenRequests.get(this.tokenRequests.size() - 1)).build();
   }

   public TokenRequest buildMatchingTokenRequest(Predicate predicate) {
      for(TokenRequestBuilder item : this.tokenRequests) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTokenRequest(Predicate predicate) {
      for(TokenRequestBuilder item : this.tokenRequests) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CSIDriverSpecFluent withTokenRequests(List tokenRequests) {
      if (this.tokenRequests != null) {
         this._visitables.get("tokenRequests").clear();
      }

      if (tokenRequests != null) {
         this.tokenRequests = new ArrayList();

         for(TokenRequest item : tokenRequests) {
            this.addToTokenRequests(item);
         }
      } else {
         this.tokenRequests = null;
      }

      return this;
   }

   public CSIDriverSpecFluent withTokenRequests(TokenRequest... tokenRequests) {
      if (this.tokenRequests != null) {
         this.tokenRequests.clear();
         this._visitables.remove("tokenRequests");
      }

      if (tokenRequests != null) {
         for(TokenRequest item : tokenRequests) {
            this.addToTokenRequests(item);
         }
      }

      return this;
   }

   public boolean hasTokenRequests() {
      return this.tokenRequests != null && !this.tokenRequests.isEmpty();
   }

   public CSIDriverSpecFluent addNewTokenRequest(String audience, Long expirationSeconds) {
      return this.addToTokenRequests(new TokenRequest(audience, expirationSeconds));
   }

   public TokenRequestsNested addNewTokenRequest() {
      return new TokenRequestsNested(-1, (TokenRequest)null);
   }

   public TokenRequestsNested addNewTokenRequestLike(TokenRequest item) {
      return new TokenRequestsNested(-1, item);
   }

   public TokenRequestsNested setNewTokenRequestLike(int index, TokenRequest item) {
      return new TokenRequestsNested(index, item);
   }

   public TokenRequestsNested editTokenRequest(int index) {
      if (this.tokenRequests.size() <= index) {
         throw new RuntimeException("Can't edit tokenRequests. Index exceeds size.");
      } else {
         return this.setNewTokenRequestLike(index, this.buildTokenRequest(index));
      }
   }

   public TokenRequestsNested editFirstTokenRequest() {
      if (this.tokenRequests.size() == 0) {
         throw new RuntimeException("Can't edit first tokenRequests. The list is empty.");
      } else {
         return this.setNewTokenRequestLike(0, this.buildTokenRequest(0));
      }
   }

   public TokenRequestsNested editLastTokenRequest() {
      int index = this.tokenRequests.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last tokenRequests. The list is empty.");
      } else {
         return this.setNewTokenRequestLike(index, this.buildTokenRequest(index));
      }
   }

   public TokenRequestsNested editMatchingTokenRequest(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.tokenRequests.size(); ++i) {
         if (predicate.test((TokenRequestBuilder)this.tokenRequests.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching tokenRequests. No match found.");
      } else {
         return this.setNewTokenRequestLike(index, this.buildTokenRequest(index));
      }
   }

   public CSIDriverSpecFluent addToVolumeLifecycleModes(int index, String item) {
      if (this.volumeLifecycleModes == null) {
         this.volumeLifecycleModes = new ArrayList();
      }

      this.volumeLifecycleModes.add(index, item);
      return this;
   }

   public CSIDriverSpecFluent setToVolumeLifecycleModes(int index, String item) {
      if (this.volumeLifecycleModes == null) {
         this.volumeLifecycleModes = new ArrayList();
      }

      this.volumeLifecycleModes.set(index, item);
      return this;
   }

   public CSIDriverSpecFluent addToVolumeLifecycleModes(String... items) {
      if (this.volumeLifecycleModes == null) {
         this.volumeLifecycleModes = new ArrayList();
      }

      for(String item : items) {
         this.volumeLifecycleModes.add(item);
      }

      return this;
   }

   public CSIDriverSpecFluent addAllToVolumeLifecycleModes(Collection items) {
      if (this.volumeLifecycleModes == null) {
         this.volumeLifecycleModes = new ArrayList();
      }

      for(String item : items) {
         this.volumeLifecycleModes.add(item);
      }

      return this;
   }

   public CSIDriverSpecFluent removeFromVolumeLifecycleModes(String... items) {
      if (this.volumeLifecycleModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.volumeLifecycleModes.remove(item);
         }

         return this;
      }
   }

   public CSIDriverSpecFluent removeAllFromVolumeLifecycleModes(Collection items) {
      if (this.volumeLifecycleModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.volumeLifecycleModes.remove(item);
         }

         return this;
      }
   }

   public List getVolumeLifecycleModes() {
      return this.volumeLifecycleModes;
   }

   public String getVolumeLifecycleMode(int index) {
      return (String)this.volumeLifecycleModes.get(index);
   }

   public String getFirstVolumeLifecycleMode() {
      return (String)this.volumeLifecycleModes.get(0);
   }

   public String getLastVolumeLifecycleMode() {
      return (String)this.volumeLifecycleModes.get(this.volumeLifecycleModes.size() - 1);
   }

   public String getMatchingVolumeLifecycleMode(Predicate predicate) {
      for(String item : this.volumeLifecycleModes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingVolumeLifecycleMode(Predicate predicate) {
      for(String item : this.volumeLifecycleModes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CSIDriverSpecFluent withVolumeLifecycleModes(List volumeLifecycleModes) {
      if (volumeLifecycleModes != null) {
         this.volumeLifecycleModes = new ArrayList();

         for(String item : volumeLifecycleModes) {
            this.addToVolumeLifecycleModes(item);
         }
      } else {
         this.volumeLifecycleModes = null;
      }

      return this;
   }

   public CSIDriverSpecFluent withVolumeLifecycleModes(String... volumeLifecycleModes) {
      if (this.volumeLifecycleModes != null) {
         this.volumeLifecycleModes.clear();
         this._visitables.remove("volumeLifecycleModes");
      }

      if (volumeLifecycleModes != null) {
         for(String item : volumeLifecycleModes) {
            this.addToVolumeLifecycleModes(item);
         }
      }

      return this;
   }

   public boolean hasVolumeLifecycleModes() {
      return this.volumeLifecycleModes != null && !this.volumeLifecycleModes.isEmpty();
   }

   public CSIDriverSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CSIDriverSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CSIDriverSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CSIDriverSpecFluent removeFromAdditionalProperties(Map map) {
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

   public CSIDriverSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            CSIDriverSpecFluent that = (CSIDriverSpecFluent)o;
            if (!Objects.equals(this.attachRequired, that.attachRequired)) {
               return false;
            } else if (!Objects.equals(this.fsGroupPolicy, that.fsGroupPolicy)) {
               return false;
            } else if (!Objects.equals(this.podInfoOnMount, that.podInfoOnMount)) {
               return false;
            } else if (!Objects.equals(this.requiresRepublish, that.requiresRepublish)) {
               return false;
            } else if (!Objects.equals(this.seLinuxMount, that.seLinuxMount)) {
               return false;
            } else if (!Objects.equals(this.storageCapacity, that.storageCapacity)) {
               return false;
            } else if (!Objects.equals(this.tokenRequests, that.tokenRequests)) {
               return false;
            } else if (!Objects.equals(this.volumeLifecycleModes, that.volumeLifecycleModes)) {
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
      return Objects.hash(new Object[]{this.attachRequired, this.fsGroupPolicy, this.podInfoOnMount, this.requiresRepublish, this.seLinuxMount, this.storageCapacity, this.tokenRequests, this.volumeLifecycleModes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attachRequired != null) {
         sb.append("attachRequired:");
         sb.append(this.attachRequired + ",");
      }

      if (this.fsGroupPolicy != null) {
         sb.append("fsGroupPolicy:");
         sb.append(this.fsGroupPolicy + ",");
      }

      if (this.podInfoOnMount != null) {
         sb.append("podInfoOnMount:");
         sb.append(this.podInfoOnMount + ",");
      }

      if (this.requiresRepublish != null) {
         sb.append("requiresRepublish:");
         sb.append(this.requiresRepublish + ",");
      }

      if (this.seLinuxMount != null) {
         sb.append("seLinuxMount:");
         sb.append(this.seLinuxMount + ",");
      }

      if (this.storageCapacity != null) {
         sb.append("storageCapacity:");
         sb.append(this.storageCapacity + ",");
      }

      if (this.tokenRequests != null && !this.tokenRequests.isEmpty()) {
         sb.append("tokenRequests:");
         sb.append(this.tokenRequests + ",");
      }

      if (this.volumeLifecycleModes != null && !this.volumeLifecycleModes.isEmpty()) {
         sb.append("volumeLifecycleModes:");
         sb.append(this.volumeLifecycleModes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public CSIDriverSpecFluent withAttachRequired() {
      return this.withAttachRequired(true);
   }

   public CSIDriverSpecFluent withPodInfoOnMount() {
      return this.withPodInfoOnMount(true);
   }

   public CSIDriverSpecFluent withRequiresRepublish() {
      return this.withRequiresRepublish(true);
   }

   public CSIDriverSpecFluent withSeLinuxMount() {
      return this.withSeLinuxMount(true);
   }

   public CSIDriverSpecFluent withStorageCapacity() {
      return this.withStorageCapacity(true);
   }

   public class TokenRequestsNested extends TokenRequestFluent implements Nested {
      TokenRequestBuilder builder;
      int index;

      TokenRequestsNested(int index, TokenRequest item) {
         this.index = index;
         this.builder = new TokenRequestBuilder(this, item);
      }

      public Object and() {
         return CSIDriverSpecFluent.this.setToTokenRequests(this.index, this.builder.build());
      }

      public Object endTokenRequest() {
         return this.and();
      }
   }
}

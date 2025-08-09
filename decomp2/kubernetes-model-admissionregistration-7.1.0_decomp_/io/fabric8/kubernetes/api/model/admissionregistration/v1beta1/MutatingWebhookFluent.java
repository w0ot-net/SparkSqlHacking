package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class MutatingWebhookFluent extends BaseFluent {
   private List admissionReviewVersions = new ArrayList();
   private WebhookClientConfigBuilder clientConfig;
   private String failurePolicy;
   private String matchPolicy;
   private String name;
   private LabelSelectorBuilder namespaceSelector;
   private LabelSelectorBuilder objectSelector;
   private String reinvocationPolicy;
   private ArrayList rules = new ArrayList();
   private String sideEffects;
   private Integer timeoutSeconds;
   private Map additionalProperties;

   public MutatingWebhookFluent() {
   }

   public MutatingWebhookFluent(MutatingWebhook instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MutatingWebhook instance) {
      instance = instance != null ? instance : new MutatingWebhook();
      if (instance != null) {
         this.withAdmissionReviewVersions(instance.getAdmissionReviewVersions());
         this.withClientConfig(instance.getClientConfig());
         this.withFailurePolicy(instance.getFailurePolicy());
         this.withMatchPolicy(instance.getMatchPolicy());
         this.withName(instance.getName());
         this.withNamespaceSelector(instance.getNamespaceSelector());
         this.withObjectSelector(instance.getObjectSelector());
         this.withReinvocationPolicy(instance.getReinvocationPolicy());
         this.withRules(instance.getRules());
         this.withSideEffects(instance.getSideEffects());
         this.withTimeoutSeconds(instance.getTimeoutSeconds());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MutatingWebhookFluent addToAdmissionReviewVersions(int index, String item) {
      if (this.admissionReviewVersions == null) {
         this.admissionReviewVersions = new ArrayList();
      }

      this.admissionReviewVersions.add(index, item);
      return this;
   }

   public MutatingWebhookFluent setToAdmissionReviewVersions(int index, String item) {
      if (this.admissionReviewVersions == null) {
         this.admissionReviewVersions = new ArrayList();
      }

      this.admissionReviewVersions.set(index, item);
      return this;
   }

   public MutatingWebhookFluent addToAdmissionReviewVersions(String... items) {
      if (this.admissionReviewVersions == null) {
         this.admissionReviewVersions = new ArrayList();
      }

      for(String item : items) {
         this.admissionReviewVersions.add(item);
      }

      return this;
   }

   public MutatingWebhookFluent addAllToAdmissionReviewVersions(Collection items) {
      if (this.admissionReviewVersions == null) {
         this.admissionReviewVersions = new ArrayList();
      }

      for(String item : items) {
         this.admissionReviewVersions.add(item);
      }

      return this;
   }

   public MutatingWebhookFluent removeFromAdmissionReviewVersions(String... items) {
      if (this.admissionReviewVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.admissionReviewVersions.remove(item);
         }

         return this;
      }
   }

   public MutatingWebhookFluent removeAllFromAdmissionReviewVersions(Collection items) {
      if (this.admissionReviewVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.admissionReviewVersions.remove(item);
         }

         return this;
      }
   }

   public List getAdmissionReviewVersions() {
      return this.admissionReviewVersions;
   }

   public String getAdmissionReviewVersion(int index) {
      return (String)this.admissionReviewVersions.get(index);
   }

   public String getFirstAdmissionReviewVersion() {
      return (String)this.admissionReviewVersions.get(0);
   }

   public String getLastAdmissionReviewVersion() {
      return (String)this.admissionReviewVersions.get(this.admissionReviewVersions.size() - 1);
   }

   public String getMatchingAdmissionReviewVersion(Predicate predicate) {
      for(String item : this.admissionReviewVersions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAdmissionReviewVersion(Predicate predicate) {
      for(String item : this.admissionReviewVersions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MutatingWebhookFluent withAdmissionReviewVersions(List admissionReviewVersions) {
      if (admissionReviewVersions != null) {
         this.admissionReviewVersions = new ArrayList();

         for(String item : admissionReviewVersions) {
            this.addToAdmissionReviewVersions(item);
         }
      } else {
         this.admissionReviewVersions = null;
      }

      return this;
   }

   public MutatingWebhookFluent withAdmissionReviewVersions(String... admissionReviewVersions) {
      if (this.admissionReviewVersions != null) {
         this.admissionReviewVersions.clear();
         this._visitables.remove("admissionReviewVersions");
      }

      if (admissionReviewVersions != null) {
         for(String item : admissionReviewVersions) {
            this.addToAdmissionReviewVersions(item);
         }
      }

      return this;
   }

   public boolean hasAdmissionReviewVersions() {
      return this.admissionReviewVersions != null && !this.admissionReviewVersions.isEmpty();
   }

   public WebhookClientConfig buildClientConfig() {
      return this.clientConfig != null ? this.clientConfig.build() : null;
   }

   public MutatingWebhookFluent withClientConfig(WebhookClientConfig clientConfig) {
      this._visitables.remove("clientConfig");
      if (clientConfig != null) {
         this.clientConfig = new WebhookClientConfigBuilder(clientConfig);
         this._visitables.get("clientConfig").add(this.clientConfig);
      } else {
         this.clientConfig = null;
         this._visitables.get("clientConfig").remove(this.clientConfig);
      }

      return this;
   }

   public boolean hasClientConfig() {
      return this.clientConfig != null;
   }

   public ClientConfigNested withNewClientConfig() {
      return new ClientConfigNested((WebhookClientConfig)null);
   }

   public ClientConfigNested withNewClientConfigLike(WebhookClientConfig item) {
      return new ClientConfigNested(item);
   }

   public ClientConfigNested editClientConfig() {
      return this.withNewClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildClientConfig()).orElse((Object)null));
   }

   public ClientConfigNested editOrNewClientConfig() {
      return this.withNewClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildClientConfig()).orElse((new WebhookClientConfigBuilder()).build()));
   }

   public ClientConfigNested editOrNewClientConfigLike(WebhookClientConfig item) {
      return this.withNewClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildClientConfig()).orElse(item));
   }

   public String getFailurePolicy() {
      return this.failurePolicy;
   }

   public MutatingWebhookFluent withFailurePolicy(String failurePolicy) {
      this.failurePolicy = failurePolicy;
      return this;
   }

   public boolean hasFailurePolicy() {
      return this.failurePolicy != null;
   }

   public String getMatchPolicy() {
      return this.matchPolicy;
   }

   public MutatingWebhookFluent withMatchPolicy(String matchPolicy) {
      this.matchPolicy = matchPolicy;
      return this;
   }

   public boolean hasMatchPolicy() {
      return this.matchPolicy != null;
   }

   public String getName() {
      return this.name;
   }

   public MutatingWebhookFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public LabelSelector buildNamespaceSelector() {
      return this.namespaceSelector != null ? this.namespaceSelector.build() : null;
   }

   public MutatingWebhookFluent withNamespaceSelector(LabelSelector namespaceSelector) {
      this._visitables.remove("namespaceSelector");
      if (namespaceSelector != null) {
         this.namespaceSelector = new LabelSelectorBuilder(namespaceSelector);
         this._visitables.get("namespaceSelector").add(this.namespaceSelector);
      } else {
         this.namespaceSelector = null;
         this._visitables.get("namespaceSelector").remove(this.namespaceSelector);
      }

      return this;
   }

   public boolean hasNamespaceSelector() {
      return this.namespaceSelector != null;
   }

   public NamespaceSelectorNested withNewNamespaceSelector() {
      return new NamespaceSelectorNested((LabelSelector)null);
   }

   public NamespaceSelectorNested withNewNamespaceSelectorLike(LabelSelector item) {
      return new NamespaceSelectorNested(item);
   }

   public NamespaceSelectorNested editNamespaceSelector() {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse((Object)null));
   }

   public NamespaceSelectorNested editOrNewNamespaceSelector() {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public NamespaceSelectorNested editOrNewNamespaceSelectorLike(LabelSelector item) {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse(item));
   }

   public LabelSelector buildObjectSelector() {
      return this.objectSelector != null ? this.objectSelector.build() : null;
   }

   public MutatingWebhookFluent withObjectSelector(LabelSelector objectSelector) {
      this._visitables.remove("objectSelector");
      if (objectSelector != null) {
         this.objectSelector = new LabelSelectorBuilder(objectSelector);
         this._visitables.get("objectSelector").add(this.objectSelector);
      } else {
         this.objectSelector = null;
         this._visitables.get("objectSelector").remove(this.objectSelector);
      }

      return this;
   }

   public boolean hasObjectSelector() {
      return this.objectSelector != null;
   }

   public ObjectSelectorNested withNewObjectSelector() {
      return new ObjectSelectorNested((LabelSelector)null);
   }

   public ObjectSelectorNested withNewObjectSelectorLike(LabelSelector item) {
      return new ObjectSelectorNested(item);
   }

   public ObjectSelectorNested editObjectSelector() {
      return this.withNewObjectSelectorLike((LabelSelector)Optional.ofNullable(this.buildObjectSelector()).orElse((Object)null));
   }

   public ObjectSelectorNested editOrNewObjectSelector() {
      return this.withNewObjectSelectorLike((LabelSelector)Optional.ofNullable(this.buildObjectSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public ObjectSelectorNested editOrNewObjectSelectorLike(LabelSelector item) {
      return this.withNewObjectSelectorLike((LabelSelector)Optional.ofNullable(this.buildObjectSelector()).orElse(item));
   }

   public String getReinvocationPolicy() {
      return this.reinvocationPolicy;
   }

   public MutatingWebhookFluent withReinvocationPolicy(String reinvocationPolicy) {
      this.reinvocationPolicy = reinvocationPolicy;
      return this;
   }

   public boolean hasReinvocationPolicy() {
      return this.reinvocationPolicy != null;
   }

   public MutatingWebhookFluent addToRules(int index, RuleWithOperations item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      RuleWithOperationsBuilder builder = new RuleWithOperationsBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").add(index, builder);
         this.rules.add(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public MutatingWebhookFluent setToRules(int index, RuleWithOperations item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      RuleWithOperationsBuilder builder = new RuleWithOperationsBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").set(index, builder);
         this.rules.set(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public MutatingWebhookFluent addToRules(RuleWithOperations... items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(RuleWithOperations item : items) {
         RuleWithOperationsBuilder builder = new RuleWithOperationsBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public MutatingWebhookFluent addAllToRules(Collection items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(RuleWithOperations item : items) {
         RuleWithOperationsBuilder builder = new RuleWithOperationsBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public MutatingWebhookFluent removeFromRules(RuleWithOperations... items) {
      if (this.rules == null) {
         return this;
      } else {
         for(RuleWithOperations item : items) {
            RuleWithOperationsBuilder builder = new RuleWithOperationsBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public MutatingWebhookFluent removeAllFromRules(Collection items) {
      if (this.rules == null) {
         return this;
      } else {
         for(RuleWithOperations item : items) {
            RuleWithOperationsBuilder builder = new RuleWithOperationsBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public MutatingWebhookFluent removeMatchingFromRules(Predicate predicate) {
      if (this.rules == null) {
         return this;
      } else {
         Iterator<RuleWithOperationsBuilder> each = this.rules.iterator();
         List visitables = this._visitables.get("rules");

         while(each.hasNext()) {
            RuleWithOperationsBuilder builder = (RuleWithOperationsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRules() {
      return this.rules != null ? build(this.rules) : null;
   }

   public RuleWithOperations buildRule(int index) {
      return ((RuleWithOperationsBuilder)this.rules.get(index)).build();
   }

   public RuleWithOperations buildFirstRule() {
      return ((RuleWithOperationsBuilder)this.rules.get(0)).build();
   }

   public RuleWithOperations buildLastRule() {
      return ((RuleWithOperationsBuilder)this.rules.get(this.rules.size() - 1)).build();
   }

   public RuleWithOperations buildMatchingRule(Predicate predicate) {
      for(RuleWithOperationsBuilder item : this.rules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRule(Predicate predicate) {
      for(RuleWithOperationsBuilder item : this.rules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MutatingWebhookFluent withRules(List rules) {
      if (this.rules != null) {
         this._visitables.get("rules").clear();
      }

      if (rules != null) {
         this.rules = new ArrayList();

         for(RuleWithOperations item : rules) {
            this.addToRules(item);
         }
      } else {
         this.rules = null;
      }

      return this;
   }

   public MutatingWebhookFluent withRules(RuleWithOperations... rules) {
      if (this.rules != null) {
         this.rules.clear();
         this._visitables.remove("rules");
      }

      if (rules != null) {
         for(RuleWithOperations item : rules) {
            this.addToRules(item);
         }
      }

      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public RulesNested addNewRule() {
      return new RulesNested(-1, (RuleWithOperations)null);
   }

   public RulesNested addNewRuleLike(RuleWithOperations item) {
      return new RulesNested(-1, item);
   }

   public RulesNested setNewRuleLike(int index, RuleWithOperations item) {
      return new RulesNested(index, item);
   }

   public RulesNested editRule(int index) {
      if (this.rules.size() <= index) {
         throw new RuntimeException("Can't edit rules. Index exceeds size.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public RulesNested editFirstRule() {
      if (this.rules.size() == 0) {
         throw new RuntimeException("Can't edit first rules. The list is empty.");
      } else {
         return this.setNewRuleLike(0, this.buildRule(0));
      }
   }

   public RulesNested editLastRule() {
      int index = this.rules.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last rules. The list is empty.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public RulesNested editMatchingRule(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.rules.size(); ++i) {
         if (predicate.test((RuleWithOperationsBuilder)this.rules.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching rules. No match found.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public String getSideEffects() {
      return this.sideEffects;
   }

   public MutatingWebhookFluent withSideEffects(String sideEffects) {
      this.sideEffects = sideEffects;
      return this;
   }

   public boolean hasSideEffects() {
      return this.sideEffects != null;
   }

   public Integer getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   public MutatingWebhookFluent withTimeoutSeconds(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
      return this;
   }

   public boolean hasTimeoutSeconds() {
      return this.timeoutSeconds != null;
   }

   public MutatingWebhookFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MutatingWebhookFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MutatingWebhookFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MutatingWebhookFluent removeFromAdditionalProperties(Map map) {
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

   public MutatingWebhookFluent withAdditionalProperties(Map additionalProperties) {
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
            MutatingWebhookFluent that = (MutatingWebhookFluent)o;
            if (!Objects.equals(this.admissionReviewVersions, that.admissionReviewVersions)) {
               return false;
            } else if (!Objects.equals(this.clientConfig, that.clientConfig)) {
               return false;
            } else if (!Objects.equals(this.failurePolicy, that.failurePolicy)) {
               return false;
            } else if (!Objects.equals(this.matchPolicy, that.matchPolicy)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespaceSelector, that.namespaceSelector)) {
               return false;
            } else if (!Objects.equals(this.objectSelector, that.objectSelector)) {
               return false;
            } else if (!Objects.equals(this.reinvocationPolicy, that.reinvocationPolicy)) {
               return false;
            } else if (!Objects.equals(this.rules, that.rules)) {
               return false;
            } else if (!Objects.equals(this.sideEffects, that.sideEffects)) {
               return false;
            } else if (!Objects.equals(this.timeoutSeconds, that.timeoutSeconds)) {
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
      return Objects.hash(new Object[]{this.admissionReviewVersions, this.clientConfig, this.failurePolicy, this.matchPolicy, this.name, this.namespaceSelector, this.objectSelector, this.reinvocationPolicy, this.rules, this.sideEffects, this.timeoutSeconds, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.admissionReviewVersions != null && !this.admissionReviewVersions.isEmpty()) {
         sb.append("admissionReviewVersions:");
         sb.append(this.admissionReviewVersions + ",");
      }

      if (this.clientConfig != null) {
         sb.append("clientConfig:");
         sb.append(this.clientConfig + ",");
      }

      if (this.failurePolicy != null) {
         sb.append("failurePolicy:");
         sb.append(this.failurePolicy + ",");
      }

      if (this.matchPolicy != null) {
         sb.append("matchPolicy:");
         sb.append(this.matchPolicy + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespaceSelector != null) {
         sb.append("namespaceSelector:");
         sb.append(this.namespaceSelector + ",");
      }

      if (this.objectSelector != null) {
         sb.append("objectSelector:");
         sb.append(this.objectSelector + ",");
      }

      if (this.reinvocationPolicy != null) {
         sb.append("reinvocationPolicy:");
         sb.append(this.reinvocationPolicy + ",");
      }

      if (this.rules != null && !this.rules.isEmpty()) {
         sb.append("rules:");
         sb.append(this.rules + ",");
      }

      if (this.sideEffects != null) {
         sb.append("sideEffects:");
         sb.append(this.sideEffects + ",");
      }

      if (this.timeoutSeconds != null) {
         sb.append("timeoutSeconds:");
         sb.append(this.timeoutSeconds + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClientConfigNested extends WebhookClientConfigFluent implements Nested {
      WebhookClientConfigBuilder builder;

      ClientConfigNested(WebhookClientConfig item) {
         this.builder = new WebhookClientConfigBuilder(this, item);
      }

      public Object and() {
         return MutatingWebhookFluent.this.withClientConfig(this.builder.build());
      }

      public Object endClientConfig() {
         return this.and();
      }
   }

   public class NamespaceSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      NamespaceSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return MutatingWebhookFluent.this.withNamespaceSelector(this.builder.build());
      }

      public Object endNamespaceSelector() {
         return this.and();
      }
   }

   public class ObjectSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      ObjectSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return MutatingWebhookFluent.this.withObjectSelector(this.builder.build());
      }

      public Object endObjectSelector() {
         return this.and();
      }
   }

   public class RulesNested extends RuleWithOperationsFluent implements Nested {
      RuleWithOperationsBuilder builder;
      int index;

      RulesNested(int index, RuleWithOperations item) {
         this.index = index;
         this.builder = new RuleWithOperationsBuilder(this, item);
      }

      public Object and() {
         return MutatingWebhookFluent.this.setToRules(this.index, this.builder.build());
      }

      public Object endRule() {
         return this.and();
      }
   }
}

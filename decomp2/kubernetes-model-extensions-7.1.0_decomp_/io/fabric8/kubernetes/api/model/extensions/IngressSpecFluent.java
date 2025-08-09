package io.fabric8.kubernetes.api.model.extensions;

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

public class IngressSpecFluent extends BaseFluent {
   private IngressBackendBuilder backend;
   private ArrayList rules = new ArrayList();
   private ArrayList tls = new ArrayList();
   private Map additionalProperties;

   public IngressSpecFluent() {
   }

   public IngressSpecFluent(IngressSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressSpec instance) {
      instance = instance != null ? instance : new IngressSpec();
      if (instance != null) {
         this.withBackend(instance.getBackend());
         this.withRules(instance.getRules());
         this.withTls(instance.getTls());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IngressBackend buildBackend() {
      return this.backend != null ? this.backend.build() : null;
   }

   public IngressSpecFluent withBackend(IngressBackend backend) {
      this._visitables.remove("backend");
      if (backend != null) {
         this.backend = new IngressBackendBuilder(backend);
         this._visitables.get("backend").add(this.backend);
      } else {
         this.backend = null;
         this._visitables.get("backend").remove(this.backend);
      }

      return this;
   }

   public boolean hasBackend() {
      return this.backend != null;
   }

   public BackendNested withNewBackend() {
      return new BackendNested((IngressBackend)null);
   }

   public BackendNested withNewBackendLike(IngressBackend item) {
      return new BackendNested(item);
   }

   public BackendNested editBackend() {
      return this.withNewBackendLike((IngressBackend)Optional.ofNullable(this.buildBackend()).orElse((Object)null));
   }

   public BackendNested editOrNewBackend() {
      return this.withNewBackendLike((IngressBackend)Optional.ofNullable(this.buildBackend()).orElse((new IngressBackendBuilder()).build()));
   }

   public BackendNested editOrNewBackendLike(IngressBackend item) {
      return this.withNewBackendLike((IngressBackend)Optional.ofNullable(this.buildBackend()).orElse(item));
   }

   public IngressSpecFluent addToRules(int index, IngressRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      IngressRuleBuilder builder = new IngressRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").add(index, builder);
         this.rules.add(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public IngressSpecFluent setToRules(int index, IngressRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      IngressRuleBuilder builder = new IngressRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").set(index, builder);
         this.rules.set(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public IngressSpecFluent addToRules(IngressRule... items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(IngressRule item : items) {
         IngressRuleBuilder builder = new IngressRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public IngressSpecFluent addAllToRules(Collection items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(IngressRule item : items) {
         IngressRuleBuilder builder = new IngressRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public IngressSpecFluent removeFromRules(IngressRule... items) {
      if (this.rules == null) {
         return this;
      } else {
         for(IngressRule item : items) {
            IngressRuleBuilder builder = new IngressRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public IngressSpecFluent removeAllFromRules(Collection items) {
      if (this.rules == null) {
         return this;
      } else {
         for(IngressRule item : items) {
            IngressRuleBuilder builder = new IngressRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public IngressSpecFluent removeMatchingFromRules(Predicate predicate) {
      if (this.rules == null) {
         return this;
      } else {
         Iterator<IngressRuleBuilder> each = this.rules.iterator();
         List visitables = this._visitables.get("rules");

         while(each.hasNext()) {
            IngressRuleBuilder builder = (IngressRuleBuilder)each.next();
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

   public IngressRule buildRule(int index) {
      return ((IngressRuleBuilder)this.rules.get(index)).build();
   }

   public IngressRule buildFirstRule() {
      return ((IngressRuleBuilder)this.rules.get(0)).build();
   }

   public IngressRule buildLastRule() {
      return ((IngressRuleBuilder)this.rules.get(this.rules.size() - 1)).build();
   }

   public IngressRule buildMatchingRule(Predicate predicate) {
      for(IngressRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRule(Predicate predicate) {
      for(IngressRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public IngressSpecFluent withRules(List rules) {
      if (this.rules != null) {
         this._visitables.get("rules").clear();
      }

      if (rules != null) {
         this.rules = new ArrayList();

         for(IngressRule item : rules) {
            this.addToRules(item);
         }
      } else {
         this.rules = null;
      }

      return this;
   }

   public IngressSpecFluent withRules(IngressRule... rules) {
      if (this.rules != null) {
         this.rules.clear();
         this._visitables.remove("rules");
      }

      if (rules != null) {
         for(IngressRule item : rules) {
            this.addToRules(item);
         }
      }

      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public RulesNested addNewRule() {
      return new RulesNested(-1, (IngressRule)null);
   }

   public RulesNested addNewRuleLike(IngressRule item) {
      return new RulesNested(-1, item);
   }

   public RulesNested setNewRuleLike(int index, IngressRule item) {
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
         if (predicate.test((IngressRuleBuilder)this.rules.get(i))) {
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

   public IngressSpecFluent addToTls(int index, IngressTLS item) {
      if (this.tls == null) {
         this.tls = new ArrayList();
      }

      IngressTLSBuilder builder = new IngressTLSBuilder(item);
      if (index >= 0 && index < this.tls.size()) {
         this._visitables.get("tls").add(index, builder);
         this.tls.add(index, builder);
      } else {
         this._visitables.get("tls").add(builder);
         this.tls.add(builder);
      }

      return this;
   }

   public IngressSpecFluent setToTls(int index, IngressTLS item) {
      if (this.tls == null) {
         this.tls = new ArrayList();
      }

      IngressTLSBuilder builder = new IngressTLSBuilder(item);
      if (index >= 0 && index < this.tls.size()) {
         this._visitables.get("tls").set(index, builder);
         this.tls.set(index, builder);
      } else {
         this._visitables.get("tls").add(builder);
         this.tls.add(builder);
      }

      return this;
   }

   public IngressSpecFluent addToTls(IngressTLS... items) {
      if (this.tls == null) {
         this.tls = new ArrayList();
      }

      for(IngressTLS item : items) {
         IngressTLSBuilder builder = new IngressTLSBuilder(item);
         this._visitables.get("tls").add(builder);
         this.tls.add(builder);
      }

      return this;
   }

   public IngressSpecFluent addAllToTls(Collection items) {
      if (this.tls == null) {
         this.tls = new ArrayList();
      }

      for(IngressTLS item : items) {
         IngressTLSBuilder builder = new IngressTLSBuilder(item);
         this._visitables.get("tls").add(builder);
         this.tls.add(builder);
      }

      return this;
   }

   public IngressSpecFluent removeFromTls(IngressTLS... items) {
      if (this.tls == null) {
         return this;
      } else {
         for(IngressTLS item : items) {
            IngressTLSBuilder builder = new IngressTLSBuilder(item);
            this._visitables.get("tls").remove(builder);
            this.tls.remove(builder);
         }

         return this;
      }
   }

   public IngressSpecFluent removeAllFromTls(Collection items) {
      if (this.tls == null) {
         return this;
      } else {
         for(IngressTLS item : items) {
            IngressTLSBuilder builder = new IngressTLSBuilder(item);
            this._visitables.get("tls").remove(builder);
            this.tls.remove(builder);
         }

         return this;
      }
   }

   public IngressSpecFluent removeMatchingFromTls(Predicate predicate) {
      if (this.tls == null) {
         return this;
      } else {
         Iterator<IngressTLSBuilder> each = this.tls.iterator();
         List visitables = this._visitables.get("tls");

         while(each.hasNext()) {
            IngressTLSBuilder builder = (IngressTLSBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTls() {
      return this.tls != null ? build(this.tls) : null;
   }

   public IngressTLS buildTl(int index) {
      return ((IngressTLSBuilder)this.tls.get(index)).build();
   }

   public IngressTLS buildFirstTl() {
      return ((IngressTLSBuilder)this.tls.get(0)).build();
   }

   public IngressTLS buildLastTl() {
      return ((IngressTLSBuilder)this.tls.get(this.tls.size() - 1)).build();
   }

   public IngressTLS buildMatchingTl(Predicate predicate) {
      for(IngressTLSBuilder item : this.tls) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTl(Predicate predicate) {
      for(IngressTLSBuilder item : this.tls) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public IngressSpecFluent withTls(List tls) {
      if (this.tls != null) {
         this._visitables.get("tls").clear();
      }

      if (tls != null) {
         this.tls = new ArrayList();

         for(IngressTLS item : tls) {
            this.addToTls(item);
         }
      } else {
         this.tls = null;
      }

      return this;
   }

   public IngressSpecFluent withTls(IngressTLS... tls) {
      if (this.tls != null) {
         this.tls.clear();
         this._visitables.remove("tls");
      }

      if (tls != null) {
         for(IngressTLS item : tls) {
            this.addToTls(item);
         }
      }

      return this;
   }

   public boolean hasTls() {
      return this.tls != null && !this.tls.isEmpty();
   }

   public TlsNested addNewTl() {
      return new TlsNested(-1, (IngressTLS)null);
   }

   public TlsNested addNewTlLike(IngressTLS item) {
      return new TlsNested(-1, item);
   }

   public TlsNested setNewTlLike(int index, IngressTLS item) {
      return new TlsNested(index, item);
   }

   public TlsNested editTl(int index) {
      if (this.tls.size() <= index) {
         throw new RuntimeException("Can't edit tls. Index exceeds size.");
      } else {
         return this.setNewTlLike(index, this.buildTl(index));
      }
   }

   public TlsNested editFirstTl() {
      if (this.tls.size() == 0) {
         throw new RuntimeException("Can't edit first tls. The list is empty.");
      } else {
         return this.setNewTlLike(0, this.buildTl(0));
      }
   }

   public TlsNested editLastTl() {
      int index = this.tls.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last tls. The list is empty.");
      } else {
         return this.setNewTlLike(index, this.buildTl(index));
      }
   }

   public TlsNested editMatchingTl(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.tls.size(); ++i) {
         if (predicate.test((IngressTLSBuilder)this.tls.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching tls. No match found.");
      } else {
         return this.setNewTlLike(index, this.buildTl(index));
      }
   }

   public IngressSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressSpecFluent removeFromAdditionalProperties(Map map) {
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

   public IngressSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressSpecFluent that = (IngressSpecFluent)o;
            if (!Objects.equals(this.backend, that.backend)) {
               return false;
            } else if (!Objects.equals(this.rules, that.rules)) {
               return false;
            } else if (!Objects.equals(this.tls, that.tls)) {
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
      return Objects.hash(new Object[]{this.backend, this.rules, this.tls, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.backend != null) {
         sb.append("backend:");
         sb.append(this.backend + ",");
      }

      if (this.rules != null && !this.rules.isEmpty()) {
         sb.append("rules:");
         sb.append(this.rules + ",");
      }

      if (this.tls != null && !this.tls.isEmpty()) {
         sb.append("tls:");
         sb.append(this.tls + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BackendNested extends IngressBackendFluent implements Nested {
      IngressBackendBuilder builder;

      BackendNested(IngressBackend item) {
         this.builder = new IngressBackendBuilder(this, item);
      }

      public Object and() {
         return IngressSpecFluent.this.withBackend(this.builder.build());
      }

      public Object endBackend() {
         return this.and();
      }
   }

   public class RulesNested extends IngressRuleFluent implements Nested {
      IngressRuleBuilder builder;
      int index;

      RulesNested(int index, IngressRule item) {
         this.index = index;
         this.builder = new IngressRuleBuilder(this, item);
      }

      public Object and() {
         return IngressSpecFluent.this.setToRules(this.index, this.builder.build());
      }

      public Object endRule() {
         return this.and();
      }
   }

   public class TlsNested extends IngressTLSFluent implements Nested {
      IngressTLSBuilder builder;
      int index;

      TlsNested(int index, IngressTLS item) {
         this.index = index;
         this.builder = new IngressTLSBuilder(this, item);
      }

      public Object and() {
         return IngressSpecFluent.this.setToTls(this.index, this.builder.build());
      }

      public Object endTl() {
         return this.and();
      }
   }
}

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

public class ConfigFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList clusters = new ArrayList();
   private ArrayList contexts = new ArrayList();
   private String currentContext;
   private ArrayList extensions = new ArrayList();
   private String kind;
   private PreferencesBuilder preferences;
   private ArrayList users = new ArrayList();
   private Map additionalProperties;

   public ConfigFluent() {
   }

   public ConfigFluent(Config instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Config instance) {
      instance = instance != null ? instance : new Config();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withClusters(instance.getClusters());
         this.withContexts(instance.getContexts());
         this.withCurrentContext(instance.getCurrentContext());
         this.withExtensions(instance.getExtensions());
         this.withKind(instance.getKind());
         this.withPreferences(instance.getPreferences());
         this.withUsers(instance.getUsers());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ConfigFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public ConfigFluent addToClusters(int index, NamedCluster item) {
      if (this.clusters == null) {
         this.clusters = new ArrayList();
      }

      NamedClusterBuilder builder = new NamedClusterBuilder(item);
      if (index >= 0 && index < this.clusters.size()) {
         this._visitables.get("clusters").add(index, builder);
         this.clusters.add(index, builder);
      } else {
         this._visitables.get("clusters").add(builder);
         this.clusters.add(builder);
      }

      return this;
   }

   public ConfigFluent setToClusters(int index, NamedCluster item) {
      if (this.clusters == null) {
         this.clusters = new ArrayList();
      }

      NamedClusterBuilder builder = new NamedClusterBuilder(item);
      if (index >= 0 && index < this.clusters.size()) {
         this._visitables.get("clusters").set(index, builder);
         this.clusters.set(index, builder);
      } else {
         this._visitables.get("clusters").add(builder);
         this.clusters.add(builder);
      }

      return this;
   }

   public ConfigFluent addToClusters(NamedCluster... items) {
      if (this.clusters == null) {
         this.clusters = new ArrayList();
      }

      for(NamedCluster item : items) {
         NamedClusterBuilder builder = new NamedClusterBuilder(item);
         this._visitables.get("clusters").add(builder);
         this.clusters.add(builder);
      }

      return this;
   }

   public ConfigFluent addAllToClusters(Collection items) {
      if (this.clusters == null) {
         this.clusters = new ArrayList();
      }

      for(NamedCluster item : items) {
         NamedClusterBuilder builder = new NamedClusterBuilder(item);
         this._visitables.get("clusters").add(builder);
         this.clusters.add(builder);
      }

      return this;
   }

   public ConfigFluent removeFromClusters(NamedCluster... items) {
      if (this.clusters == null) {
         return this;
      } else {
         for(NamedCluster item : items) {
            NamedClusterBuilder builder = new NamedClusterBuilder(item);
            this._visitables.get("clusters").remove(builder);
            this.clusters.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeAllFromClusters(Collection items) {
      if (this.clusters == null) {
         return this;
      } else {
         for(NamedCluster item : items) {
            NamedClusterBuilder builder = new NamedClusterBuilder(item);
            this._visitables.get("clusters").remove(builder);
            this.clusters.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeMatchingFromClusters(Predicate predicate) {
      if (this.clusters == null) {
         return this;
      } else {
         Iterator<NamedClusterBuilder> each = this.clusters.iterator();
         List visitables = this._visitables.get("clusters");

         while(each.hasNext()) {
            NamedClusterBuilder builder = (NamedClusterBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildClusters() {
      return this.clusters != null ? build(this.clusters) : null;
   }

   public NamedCluster buildCluster(int index) {
      return ((NamedClusterBuilder)this.clusters.get(index)).build();
   }

   public NamedCluster buildFirstCluster() {
      return ((NamedClusterBuilder)this.clusters.get(0)).build();
   }

   public NamedCluster buildLastCluster() {
      return ((NamedClusterBuilder)this.clusters.get(this.clusters.size() - 1)).build();
   }

   public NamedCluster buildMatchingCluster(Predicate predicate) {
      for(NamedClusterBuilder item : this.clusters) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCluster(Predicate predicate) {
      for(NamedClusterBuilder item : this.clusters) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ConfigFluent withClusters(List clusters) {
      if (this.clusters != null) {
         this._visitables.get("clusters").clear();
      }

      if (clusters != null) {
         this.clusters = new ArrayList();

         for(NamedCluster item : clusters) {
            this.addToClusters(item);
         }
      } else {
         this.clusters = null;
      }

      return this;
   }

   public ConfigFluent withClusters(NamedCluster... clusters) {
      if (this.clusters != null) {
         this.clusters.clear();
         this._visitables.remove("clusters");
      }

      if (clusters != null) {
         for(NamedCluster item : clusters) {
            this.addToClusters(item);
         }
      }

      return this;
   }

   public boolean hasClusters() {
      return this.clusters != null && !this.clusters.isEmpty();
   }

   public ClustersNested addNewCluster() {
      return new ClustersNested(-1, (NamedCluster)null);
   }

   public ClustersNested addNewClusterLike(NamedCluster item) {
      return new ClustersNested(-1, item);
   }

   public ClustersNested setNewClusterLike(int index, NamedCluster item) {
      return new ClustersNested(index, item);
   }

   public ClustersNested editCluster(int index) {
      if (this.clusters.size() <= index) {
         throw new RuntimeException("Can't edit clusters. Index exceeds size.");
      } else {
         return this.setNewClusterLike(index, this.buildCluster(index));
      }
   }

   public ClustersNested editFirstCluster() {
      if (this.clusters.size() == 0) {
         throw new RuntimeException("Can't edit first clusters. The list is empty.");
      } else {
         return this.setNewClusterLike(0, this.buildCluster(0));
      }
   }

   public ClustersNested editLastCluster() {
      int index = this.clusters.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last clusters. The list is empty.");
      } else {
         return this.setNewClusterLike(index, this.buildCluster(index));
      }
   }

   public ClustersNested editMatchingCluster(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.clusters.size(); ++i) {
         if (predicate.test((NamedClusterBuilder)this.clusters.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching clusters. No match found.");
      } else {
         return this.setNewClusterLike(index, this.buildCluster(index));
      }
   }

   public ConfigFluent addToContexts(int index, NamedContext item) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      NamedContextBuilder builder = new NamedContextBuilder(item);
      if (index >= 0 && index < this.contexts.size()) {
         this._visitables.get("contexts").add(index, builder);
         this.contexts.add(index, builder);
      } else {
         this._visitables.get("contexts").add(builder);
         this.contexts.add(builder);
      }

      return this;
   }

   public ConfigFluent setToContexts(int index, NamedContext item) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      NamedContextBuilder builder = new NamedContextBuilder(item);
      if (index >= 0 && index < this.contexts.size()) {
         this._visitables.get("contexts").set(index, builder);
         this.contexts.set(index, builder);
      } else {
         this._visitables.get("contexts").add(builder);
         this.contexts.add(builder);
      }

      return this;
   }

   public ConfigFluent addToContexts(NamedContext... items) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      for(NamedContext item : items) {
         NamedContextBuilder builder = new NamedContextBuilder(item);
         this._visitables.get("contexts").add(builder);
         this.contexts.add(builder);
      }

      return this;
   }

   public ConfigFluent addAllToContexts(Collection items) {
      if (this.contexts == null) {
         this.contexts = new ArrayList();
      }

      for(NamedContext item : items) {
         NamedContextBuilder builder = new NamedContextBuilder(item);
         this._visitables.get("contexts").add(builder);
         this.contexts.add(builder);
      }

      return this;
   }

   public ConfigFluent removeFromContexts(NamedContext... items) {
      if (this.contexts == null) {
         return this;
      } else {
         for(NamedContext item : items) {
            NamedContextBuilder builder = new NamedContextBuilder(item);
            this._visitables.get("contexts").remove(builder);
            this.contexts.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeAllFromContexts(Collection items) {
      if (this.contexts == null) {
         return this;
      } else {
         for(NamedContext item : items) {
            NamedContextBuilder builder = new NamedContextBuilder(item);
            this._visitables.get("contexts").remove(builder);
            this.contexts.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeMatchingFromContexts(Predicate predicate) {
      if (this.contexts == null) {
         return this;
      } else {
         Iterator<NamedContextBuilder> each = this.contexts.iterator();
         List visitables = this._visitables.get("contexts");

         while(each.hasNext()) {
            NamedContextBuilder builder = (NamedContextBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildContexts() {
      return this.contexts != null ? build(this.contexts) : null;
   }

   public NamedContext buildContext(int index) {
      return ((NamedContextBuilder)this.contexts.get(index)).build();
   }

   public NamedContext buildFirstContext() {
      return ((NamedContextBuilder)this.contexts.get(0)).build();
   }

   public NamedContext buildLastContext() {
      return ((NamedContextBuilder)this.contexts.get(this.contexts.size() - 1)).build();
   }

   public NamedContext buildMatchingContext(Predicate predicate) {
      for(NamedContextBuilder item : this.contexts) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingContext(Predicate predicate) {
      for(NamedContextBuilder item : this.contexts) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ConfigFluent withContexts(List contexts) {
      if (this.contexts != null) {
         this._visitables.get("contexts").clear();
      }

      if (contexts != null) {
         this.contexts = new ArrayList();

         for(NamedContext item : contexts) {
            this.addToContexts(item);
         }
      } else {
         this.contexts = null;
      }

      return this;
   }

   public ConfigFluent withContexts(NamedContext... contexts) {
      if (this.contexts != null) {
         this.contexts.clear();
         this._visitables.remove("contexts");
      }

      if (contexts != null) {
         for(NamedContext item : contexts) {
            this.addToContexts(item);
         }
      }

      return this;
   }

   public boolean hasContexts() {
      return this.contexts != null && !this.contexts.isEmpty();
   }

   public ContextsNested addNewContext() {
      return new ContextsNested(-1, (NamedContext)null);
   }

   public ContextsNested addNewContextLike(NamedContext item) {
      return new ContextsNested(-1, item);
   }

   public ContextsNested setNewContextLike(int index, NamedContext item) {
      return new ContextsNested(index, item);
   }

   public ContextsNested editContext(int index) {
      if (this.contexts.size() <= index) {
         throw new RuntimeException("Can't edit contexts. Index exceeds size.");
      } else {
         return this.setNewContextLike(index, this.buildContext(index));
      }
   }

   public ContextsNested editFirstContext() {
      if (this.contexts.size() == 0) {
         throw new RuntimeException("Can't edit first contexts. The list is empty.");
      } else {
         return this.setNewContextLike(0, this.buildContext(0));
      }
   }

   public ContextsNested editLastContext() {
      int index = this.contexts.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last contexts. The list is empty.");
      } else {
         return this.setNewContextLike(index, this.buildContext(index));
      }
   }

   public ContextsNested editMatchingContext(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.contexts.size(); ++i) {
         if (predicate.test((NamedContextBuilder)this.contexts.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching contexts. No match found.");
      } else {
         return this.setNewContextLike(index, this.buildContext(index));
      }
   }

   public String getCurrentContext() {
      return this.currentContext;
   }

   public ConfigFluent withCurrentContext(String currentContext) {
      this.currentContext = currentContext;
      return this;
   }

   public boolean hasCurrentContext() {
      return this.currentContext != null;
   }

   public ConfigFluent addToExtensions(int index, NamedExtension item) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
      if (index >= 0 && index < this.extensions.size()) {
         this._visitables.get("extensions").add(index, builder);
         this.extensions.add(index, builder);
      } else {
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public ConfigFluent setToExtensions(int index, NamedExtension item) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
      if (index >= 0 && index < this.extensions.size()) {
         this._visitables.get("extensions").set(index, builder);
         this.extensions.set(index, builder);
      } else {
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public ConfigFluent addToExtensions(NamedExtension... items) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      for(NamedExtension item : items) {
         NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public ConfigFluent addAllToExtensions(Collection items) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      for(NamedExtension item : items) {
         NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public ConfigFluent removeFromExtensions(NamedExtension... items) {
      if (this.extensions == null) {
         return this;
      } else {
         for(NamedExtension item : items) {
            NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
            this._visitables.get("extensions").remove(builder);
            this.extensions.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeAllFromExtensions(Collection items) {
      if (this.extensions == null) {
         return this;
      } else {
         for(NamedExtension item : items) {
            NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
            this._visitables.get("extensions").remove(builder);
            this.extensions.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeMatchingFromExtensions(Predicate predicate) {
      if (this.extensions == null) {
         return this;
      } else {
         Iterator<NamedExtensionBuilder> each = this.extensions.iterator();
         List visitables = this._visitables.get("extensions");

         while(each.hasNext()) {
            NamedExtensionBuilder builder = (NamedExtensionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildExtensions() {
      return this.extensions != null ? build(this.extensions) : null;
   }

   public NamedExtension buildExtension(int index) {
      return ((NamedExtensionBuilder)this.extensions.get(index)).build();
   }

   public NamedExtension buildFirstExtension() {
      return ((NamedExtensionBuilder)this.extensions.get(0)).build();
   }

   public NamedExtension buildLastExtension() {
      return ((NamedExtensionBuilder)this.extensions.get(this.extensions.size() - 1)).build();
   }

   public NamedExtension buildMatchingExtension(Predicate predicate) {
      for(NamedExtensionBuilder item : this.extensions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingExtension(Predicate predicate) {
      for(NamedExtensionBuilder item : this.extensions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ConfigFluent withExtensions(List extensions) {
      if (this.extensions != null) {
         this._visitables.get("extensions").clear();
      }

      if (extensions != null) {
         this.extensions = new ArrayList();

         for(NamedExtension item : extensions) {
            this.addToExtensions(item);
         }
      } else {
         this.extensions = null;
      }

      return this;
   }

   public ConfigFluent withExtensions(NamedExtension... extensions) {
      if (this.extensions != null) {
         this.extensions.clear();
         this._visitables.remove("extensions");
      }

      if (extensions != null) {
         for(NamedExtension item : extensions) {
            this.addToExtensions(item);
         }
      }

      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public ConfigFluent addNewExtension(Object extension, String name) {
      return this.addToExtensions(new NamedExtension(extension, name));
   }

   public ExtensionsNested addNewExtension() {
      return new ExtensionsNested(-1, (NamedExtension)null);
   }

   public ExtensionsNested addNewExtensionLike(NamedExtension item) {
      return new ExtensionsNested(-1, item);
   }

   public ExtensionsNested setNewExtensionLike(int index, NamedExtension item) {
      return new ExtensionsNested(index, item);
   }

   public ExtensionsNested editExtension(int index) {
      if (this.extensions.size() <= index) {
         throw new RuntimeException("Can't edit extensions. Index exceeds size.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public ExtensionsNested editFirstExtension() {
      if (this.extensions.size() == 0) {
         throw new RuntimeException("Can't edit first extensions. The list is empty.");
      } else {
         return this.setNewExtensionLike(0, this.buildExtension(0));
      }
   }

   public ExtensionsNested editLastExtension() {
      int index = this.extensions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last extensions. The list is empty.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public ExtensionsNested editMatchingExtension(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.extensions.size(); ++i) {
         if (predicate.test((NamedExtensionBuilder)this.extensions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching extensions. No match found.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public String getKind() {
      return this.kind;
   }

   public ConfigFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public Preferences buildPreferences() {
      return this.preferences != null ? this.preferences.build() : null;
   }

   public ConfigFluent withPreferences(Preferences preferences) {
      this._visitables.remove("preferences");
      if (preferences != null) {
         this.preferences = new PreferencesBuilder(preferences);
         this._visitables.get("preferences").add(this.preferences);
      } else {
         this.preferences = null;
         this._visitables.get("preferences").remove(this.preferences);
      }

      return this;
   }

   public boolean hasPreferences() {
      return this.preferences != null;
   }

   public PreferencesNested withNewPreferences() {
      return new PreferencesNested((Preferences)null);
   }

   public PreferencesNested withNewPreferencesLike(Preferences item) {
      return new PreferencesNested(item);
   }

   public PreferencesNested editPreferences() {
      return this.withNewPreferencesLike((Preferences)Optional.ofNullable(this.buildPreferences()).orElse((Object)null));
   }

   public PreferencesNested editOrNewPreferences() {
      return this.withNewPreferencesLike((Preferences)Optional.ofNullable(this.buildPreferences()).orElse((new PreferencesBuilder()).build()));
   }

   public PreferencesNested editOrNewPreferencesLike(Preferences item) {
      return this.withNewPreferencesLike((Preferences)Optional.ofNullable(this.buildPreferences()).orElse(item));
   }

   public ConfigFluent addToUsers(int index, NamedAuthInfo item) {
      if (this.users == null) {
         this.users = new ArrayList();
      }

      NamedAuthInfoBuilder builder = new NamedAuthInfoBuilder(item);
      if (index >= 0 && index < this.users.size()) {
         this._visitables.get("users").add(index, builder);
         this.users.add(index, builder);
      } else {
         this._visitables.get("users").add(builder);
         this.users.add(builder);
      }

      return this;
   }

   public ConfigFluent setToUsers(int index, NamedAuthInfo item) {
      if (this.users == null) {
         this.users = new ArrayList();
      }

      NamedAuthInfoBuilder builder = new NamedAuthInfoBuilder(item);
      if (index >= 0 && index < this.users.size()) {
         this._visitables.get("users").set(index, builder);
         this.users.set(index, builder);
      } else {
         this._visitables.get("users").add(builder);
         this.users.add(builder);
      }

      return this;
   }

   public ConfigFluent addToUsers(NamedAuthInfo... items) {
      if (this.users == null) {
         this.users = new ArrayList();
      }

      for(NamedAuthInfo item : items) {
         NamedAuthInfoBuilder builder = new NamedAuthInfoBuilder(item);
         this._visitables.get("users").add(builder);
         this.users.add(builder);
      }

      return this;
   }

   public ConfigFluent addAllToUsers(Collection items) {
      if (this.users == null) {
         this.users = new ArrayList();
      }

      for(NamedAuthInfo item : items) {
         NamedAuthInfoBuilder builder = new NamedAuthInfoBuilder(item);
         this._visitables.get("users").add(builder);
         this.users.add(builder);
      }

      return this;
   }

   public ConfigFluent removeFromUsers(NamedAuthInfo... items) {
      if (this.users == null) {
         return this;
      } else {
         for(NamedAuthInfo item : items) {
            NamedAuthInfoBuilder builder = new NamedAuthInfoBuilder(item);
            this._visitables.get("users").remove(builder);
            this.users.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeAllFromUsers(Collection items) {
      if (this.users == null) {
         return this;
      } else {
         for(NamedAuthInfo item : items) {
            NamedAuthInfoBuilder builder = new NamedAuthInfoBuilder(item);
            this._visitables.get("users").remove(builder);
            this.users.remove(builder);
         }

         return this;
      }
   }

   public ConfigFluent removeMatchingFromUsers(Predicate predicate) {
      if (this.users == null) {
         return this;
      } else {
         Iterator<NamedAuthInfoBuilder> each = this.users.iterator();
         List visitables = this._visitables.get("users");

         while(each.hasNext()) {
            NamedAuthInfoBuilder builder = (NamedAuthInfoBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildUsers() {
      return this.users != null ? build(this.users) : null;
   }

   public NamedAuthInfo buildUser(int index) {
      return ((NamedAuthInfoBuilder)this.users.get(index)).build();
   }

   public NamedAuthInfo buildFirstUser() {
      return ((NamedAuthInfoBuilder)this.users.get(0)).build();
   }

   public NamedAuthInfo buildLastUser() {
      return ((NamedAuthInfoBuilder)this.users.get(this.users.size() - 1)).build();
   }

   public NamedAuthInfo buildMatchingUser(Predicate predicate) {
      for(NamedAuthInfoBuilder item : this.users) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingUser(Predicate predicate) {
      for(NamedAuthInfoBuilder item : this.users) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ConfigFluent withUsers(List users) {
      if (this.users != null) {
         this._visitables.get("users").clear();
      }

      if (users != null) {
         this.users = new ArrayList();

         for(NamedAuthInfo item : users) {
            this.addToUsers(item);
         }
      } else {
         this.users = null;
      }

      return this;
   }

   public ConfigFluent withUsers(NamedAuthInfo... users) {
      if (this.users != null) {
         this.users.clear();
         this._visitables.remove("users");
      }

      if (users != null) {
         for(NamedAuthInfo item : users) {
            this.addToUsers(item);
         }
      }

      return this;
   }

   public boolean hasUsers() {
      return this.users != null && !this.users.isEmpty();
   }

   public UsersNested addNewUser() {
      return new UsersNested(-1, (NamedAuthInfo)null);
   }

   public UsersNested addNewUserLike(NamedAuthInfo item) {
      return new UsersNested(-1, item);
   }

   public UsersNested setNewUserLike(int index, NamedAuthInfo item) {
      return new UsersNested(index, item);
   }

   public UsersNested editUser(int index) {
      if (this.users.size() <= index) {
         throw new RuntimeException("Can't edit users. Index exceeds size.");
      } else {
         return this.setNewUserLike(index, this.buildUser(index));
      }
   }

   public UsersNested editFirstUser() {
      if (this.users.size() == 0) {
         throw new RuntimeException("Can't edit first users. The list is empty.");
      } else {
         return this.setNewUserLike(0, this.buildUser(0));
      }
   }

   public UsersNested editLastUser() {
      int index = this.users.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last users. The list is empty.");
      } else {
         return this.setNewUserLike(index, this.buildUser(index));
      }
   }

   public UsersNested editMatchingUser(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.users.size(); ++i) {
         if (predicate.test((NamedAuthInfoBuilder)this.users.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching users. No match found.");
      } else {
         return this.setNewUserLike(index, this.buildUser(index));
      }
   }

   public ConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ConfigFluent removeFromAdditionalProperties(Map map) {
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

   public ConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            ConfigFluent that = (ConfigFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.clusters, that.clusters)) {
               return false;
            } else if (!Objects.equals(this.contexts, that.contexts)) {
               return false;
            } else if (!Objects.equals(this.currentContext, that.currentContext)) {
               return false;
            } else if (!Objects.equals(this.extensions, that.extensions)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.preferences, that.preferences)) {
               return false;
            } else if (!Objects.equals(this.users, that.users)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.clusters, this.contexts, this.currentContext, this.extensions, this.kind, this.preferences, this.users, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.clusters != null && !this.clusters.isEmpty()) {
         sb.append("clusters:");
         sb.append(this.clusters + ",");
      }

      if (this.contexts != null && !this.contexts.isEmpty()) {
         sb.append("contexts:");
         sb.append(this.contexts + ",");
      }

      if (this.currentContext != null) {
         sb.append("currentContext:");
         sb.append(this.currentContext + ",");
      }

      if (this.extensions != null && !this.extensions.isEmpty()) {
         sb.append("extensions:");
         sb.append(this.extensions + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.preferences != null) {
         sb.append("preferences:");
         sb.append(this.preferences + ",");
      }

      if (this.users != null && !this.users.isEmpty()) {
         sb.append("users:");
         sb.append(this.users + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClustersNested extends NamedClusterFluent implements Nested {
      NamedClusterBuilder builder;
      int index;

      ClustersNested(int index, NamedCluster item) {
         this.index = index;
         this.builder = new NamedClusterBuilder(this, item);
      }

      public Object and() {
         return ConfigFluent.this.setToClusters(this.index, this.builder.build());
      }

      public Object endCluster() {
         return this.and();
      }
   }

   public class ContextsNested extends NamedContextFluent implements Nested {
      NamedContextBuilder builder;
      int index;

      ContextsNested(int index, NamedContext item) {
         this.index = index;
         this.builder = new NamedContextBuilder(this, item);
      }

      public Object and() {
         return ConfigFluent.this.setToContexts(this.index, this.builder.build());
      }

      public Object endContext() {
         return this.and();
      }
   }

   public class ExtensionsNested extends NamedExtensionFluent implements Nested {
      NamedExtensionBuilder builder;
      int index;

      ExtensionsNested(int index, NamedExtension item) {
         this.index = index;
         this.builder = new NamedExtensionBuilder(this, item);
      }

      public Object and() {
         return ConfigFluent.this.setToExtensions(this.index, this.builder.build());
      }

      public Object endExtension() {
         return this.and();
      }
   }

   public class PreferencesNested extends PreferencesFluent implements Nested {
      PreferencesBuilder builder;

      PreferencesNested(Preferences item) {
         this.builder = new PreferencesBuilder(this, item);
      }

      public Object and() {
         return ConfigFluent.this.withPreferences(this.builder.build());
      }

      public Object endPreferences() {
         return this.and();
      }
   }

   public class UsersNested extends NamedAuthInfoFluent implements Nested {
      NamedAuthInfoBuilder builder;
      int index;

      UsersNested(int index, NamedAuthInfo item) {
         this.index = index;
         this.builder = new NamedAuthInfoBuilder(this, item);
      }

      public Object and() {
         return ConfigFluent.this.setToUsers(this.index, this.builder.build());
      }

      public Object endUser() {
         return this.and();
      }
   }
}

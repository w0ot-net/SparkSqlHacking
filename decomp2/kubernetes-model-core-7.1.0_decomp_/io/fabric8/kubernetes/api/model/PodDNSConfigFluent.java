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

public class PodDNSConfigFluent extends BaseFluent {
   private List nameservers = new ArrayList();
   private ArrayList options = new ArrayList();
   private List searches = new ArrayList();
   private Map additionalProperties;

   public PodDNSConfigFluent() {
   }

   public PodDNSConfigFluent(PodDNSConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodDNSConfig instance) {
      instance = instance != null ? instance : new PodDNSConfig();
      if (instance != null) {
         this.withNameservers(instance.getNameservers());
         this.withOptions(instance.getOptions());
         this.withSearches(instance.getSearches());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodDNSConfigFluent addToNameservers(int index, String item) {
      if (this.nameservers == null) {
         this.nameservers = new ArrayList();
      }

      this.nameservers.add(index, item);
      return this;
   }

   public PodDNSConfigFluent setToNameservers(int index, String item) {
      if (this.nameservers == null) {
         this.nameservers = new ArrayList();
      }

      this.nameservers.set(index, item);
      return this;
   }

   public PodDNSConfigFluent addToNameservers(String... items) {
      if (this.nameservers == null) {
         this.nameservers = new ArrayList();
      }

      for(String item : items) {
         this.nameservers.add(item);
      }

      return this;
   }

   public PodDNSConfigFluent addAllToNameservers(Collection items) {
      if (this.nameservers == null) {
         this.nameservers = new ArrayList();
      }

      for(String item : items) {
         this.nameservers.add(item);
      }

      return this;
   }

   public PodDNSConfigFluent removeFromNameservers(String... items) {
      if (this.nameservers == null) {
         return this;
      } else {
         for(String item : items) {
            this.nameservers.remove(item);
         }

         return this;
      }
   }

   public PodDNSConfigFluent removeAllFromNameservers(Collection items) {
      if (this.nameservers == null) {
         return this;
      } else {
         for(String item : items) {
            this.nameservers.remove(item);
         }

         return this;
      }
   }

   public List getNameservers() {
      return this.nameservers;
   }

   public String getNameserver(int index) {
      return (String)this.nameservers.get(index);
   }

   public String getFirstNameserver() {
      return (String)this.nameservers.get(0);
   }

   public String getLastNameserver() {
      return (String)this.nameservers.get(this.nameservers.size() - 1);
   }

   public String getMatchingNameserver(Predicate predicate) {
      for(String item : this.nameservers) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingNameserver(Predicate predicate) {
      for(String item : this.nameservers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodDNSConfigFluent withNameservers(List nameservers) {
      if (nameservers != null) {
         this.nameservers = new ArrayList();

         for(String item : nameservers) {
            this.addToNameservers(item);
         }
      } else {
         this.nameservers = null;
      }

      return this;
   }

   public PodDNSConfigFluent withNameservers(String... nameservers) {
      if (this.nameservers != null) {
         this.nameservers.clear();
         this._visitables.remove("nameservers");
      }

      if (nameservers != null) {
         for(String item : nameservers) {
            this.addToNameservers(item);
         }
      }

      return this;
   }

   public boolean hasNameservers() {
      return this.nameservers != null && !this.nameservers.isEmpty();
   }

   public PodDNSConfigFluent addToOptions(int index, PodDNSConfigOption item) {
      if (this.options == null) {
         this.options = new ArrayList();
      }

      PodDNSConfigOptionBuilder builder = new PodDNSConfigOptionBuilder(item);
      if (index >= 0 && index < this.options.size()) {
         this._visitables.get("options").add(index, builder);
         this.options.add(index, builder);
      } else {
         this._visitables.get("options").add(builder);
         this.options.add(builder);
      }

      return this;
   }

   public PodDNSConfigFluent setToOptions(int index, PodDNSConfigOption item) {
      if (this.options == null) {
         this.options = new ArrayList();
      }

      PodDNSConfigOptionBuilder builder = new PodDNSConfigOptionBuilder(item);
      if (index >= 0 && index < this.options.size()) {
         this._visitables.get("options").set(index, builder);
         this.options.set(index, builder);
      } else {
         this._visitables.get("options").add(builder);
         this.options.add(builder);
      }

      return this;
   }

   public PodDNSConfigFluent addToOptions(PodDNSConfigOption... items) {
      if (this.options == null) {
         this.options = new ArrayList();
      }

      for(PodDNSConfigOption item : items) {
         PodDNSConfigOptionBuilder builder = new PodDNSConfigOptionBuilder(item);
         this._visitables.get("options").add(builder);
         this.options.add(builder);
      }

      return this;
   }

   public PodDNSConfigFluent addAllToOptions(Collection items) {
      if (this.options == null) {
         this.options = new ArrayList();
      }

      for(PodDNSConfigOption item : items) {
         PodDNSConfigOptionBuilder builder = new PodDNSConfigOptionBuilder(item);
         this._visitables.get("options").add(builder);
         this.options.add(builder);
      }

      return this;
   }

   public PodDNSConfigFluent removeFromOptions(PodDNSConfigOption... items) {
      if (this.options == null) {
         return this;
      } else {
         for(PodDNSConfigOption item : items) {
            PodDNSConfigOptionBuilder builder = new PodDNSConfigOptionBuilder(item);
            this._visitables.get("options").remove(builder);
            this.options.remove(builder);
         }

         return this;
      }
   }

   public PodDNSConfigFluent removeAllFromOptions(Collection items) {
      if (this.options == null) {
         return this;
      } else {
         for(PodDNSConfigOption item : items) {
            PodDNSConfigOptionBuilder builder = new PodDNSConfigOptionBuilder(item);
            this._visitables.get("options").remove(builder);
            this.options.remove(builder);
         }

         return this;
      }
   }

   public PodDNSConfigFluent removeMatchingFromOptions(Predicate predicate) {
      if (this.options == null) {
         return this;
      } else {
         Iterator<PodDNSConfigOptionBuilder> each = this.options.iterator();
         List visitables = this._visitables.get("options");

         while(each.hasNext()) {
            PodDNSConfigOptionBuilder builder = (PodDNSConfigOptionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildOptions() {
      return this.options != null ? build(this.options) : null;
   }

   public PodDNSConfigOption buildOption(int index) {
      return ((PodDNSConfigOptionBuilder)this.options.get(index)).build();
   }

   public PodDNSConfigOption buildFirstOption() {
      return ((PodDNSConfigOptionBuilder)this.options.get(0)).build();
   }

   public PodDNSConfigOption buildLastOption() {
      return ((PodDNSConfigOptionBuilder)this.options.get(this.options.size() - 1)).build();
   }

   public PodDNSConfigOption buildMatchingOption(Predicate predicate) {
      for(PodDNSConfigOptionBuilder item : this.options) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingOption(Predicate predicate) {
      for(PodDNSConfigOptionBuilder item : this.options) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodDNSConfigFluent withOptions(List options) {
      if (this.options != null) {
         this._visitables.get("options").clear();
      }

      if (options != null) {
         this.options = new ArrayList();

         for(PodDNSConfigOption item : options) {
            this.addToOptions(item);
         }
      } else {
         this.options = null;
      }

      return this;
   }

   public PodDNSConfigFluent withOptions(PodDNSConfigOption... options) {
      if (this.options != null) {
         this.options.clear();
         this._visitables.remove("options");
      }

      if (options != null) {
         for(PodDNSConfigOption item : options) {
            this.addToOptions(item);
         }
      }

      return this;
   }

   public boolean hasOptions() {
      return this.options != null && !this.options.isEmpty();
   }

   public PodDNSConfigFluent addNewOption(String name, String value) {
      return this.addToOptions(new PodDNSConfigOption(name, value));
   }

   public OptionsNested addNewOption() {
      return new OptionsNested(-1, (PodDNSConfigOption)null);
   }

   public OptionsNested addNewOptionLike(PodDNSConfigOption item) {
      return new OptionsNested(-1, item);
   }

   public OptionsNested setNewOptionLike(int index, PodDNSConfigOption item) {
      return new OptionsNested(index, item);
   }

   public OptionsNested editOption(int index) {
      if (this.options.size() <= index) {
         throw new RuntimeException("Can't edit options. Index exceeds size.");
      } else {
         return this.setNewOptionLike(index, this.buildOption(index));
      }
   }

   public OptionsNested editFirstOption() {
      if (this.options.size() == 0) {
         throw new RuntimeException("Can't edit first options. The list is empty.");
      } else {
         return this.setNewOptionLike(0, this.buildOption(0));
      }
   }

   public OptionsNested editLastOption() {
      int index = this.options.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last options. The list is empty.");
      } else {
         return this.setNewOptionLike(index, this.buildOption(index));
      }
   }

   public OptionsNested editMatchingOption(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.options.size(); ++i) {
         if (predicate.test((PodDNSConfigOptionBuilder)this.options.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching options. No match found.");
      } else {
         return this.setNewOptionLike(index, this.buildOption(index));
      }
   }

   public PodDNSConfigFluent addToSearches(int index, String item) {
      if (this.searches == null) {
         this.searches = new ArrayList();
      }

      this.searches.add(index, item);
      return this;
   }

   public PodDNSConfigFluent setToSearches(int index, String item) {
      if (this.searches == null) {
         this.searches = new ArrayList();
      }

      this.searches.set(index, item);
      return this;
   }

   public PodDNSConfigFluent addToSearches(String... items) {
      if (this.searches == null) {
         this.searches = new ArrayList();
      }

      for(String item : items) {
         this.searches.add(item);
      }

      return this;
   }

   public PodDNSConfigFluent addAllToSearches(Collection items) {
      if (this.searches == null) {
         this.searches = new ArrayList();
      }

      for(String item : items) {
         this.searches.add(item);
      }

      return this;
   }

   public PodDNSConfigFluent removeFromSearches(String... items) {
      if (this.searches == null) {
         return this;
      } else {
         for(String item : items) {
            this.searches.remove(item);
         }

         return this;
      }
   }

   public PodDNSConfigFluent removeAllFromSearches(Collection items) {
      if (this.searches == null) {
         return this;
      } else {
         for(String item : items) {
            this.searches.remove(item);
         }

         return this;
      }
   }

   public List getSearches() {
      return this.searches;
   }

   public String getSearch(int index) {
      return (String)this.searches.get(index);
   }

   public String getFirstSearch() {
      return (String)this.searches.get(0);
   }

   public String getLastSearch() {
      return (String)this.searches.get(this.searches.size() - 1);
   }

   public String getMatchingSearch(Predicate predicate) {
      for(String item : this.searches) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingSearch(Predicate predicate) {
      for(String item : this.searches) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodDNSConfigFluent withSearches(List searches) {
      if (searches != null) {
         this.searches = new ArrayList();

         for(String item : searches) {
            this.addToSearches(item);
         }
      } else {
         this.searches = null;
      }

      return this;
   }

   public PodDNSConfigFluent withSearches(String... searches) {
      if (this.searches != null) {
         this.searches.clear();
         this._visitables.remove("searches");
      }

      if (searches != null) {
         for(String item : searches) {
            this.addToSearches(item);
         }
      }

      return this;
   }

   public boolean hasSearches() {
      return this.searches != null && !this.searches.isEmpty();
   }

   public PodDNSConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodDNSConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodDNSConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodDNSConfigFluent removeFromAdditionalProperties(Map map) {
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

   public PodDNSConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            PodDNSConfigFluent that = (PodDNSConfigFluent)o;
            if (!Objects.equals(this.nameservers, that.nameservers)) {
               return false;
            } else if (!Objects.equals(this.options, that.options)) {
               return false;
            } else if (!Objects.equals(this.searches, that.searches)) {
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
      return Objects.hash(new Object[]{this.nameservers, this.options, this.searches, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nameservers != null && !this.nameservers.isEmpty()) {
         sb.append("nameservers:");
         sb.append(this.nameservers + ",");
      }

      if (this.options != null && !this.options.isEmpty()) {
         sb.append("options:");
         sb.append(this.options + ",");
      }

      if (this.searches != null && !this.searches.isEmpty()) {
         sb.append("searches:");
         sb.append(this.searches + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OptionsNested extends PodDNSConfigOptionFluent implements Nested {
      PodDNSConfigOptionBuilder builder;
      int index;

      OptionsNested(int index, PodDNSConfigOption item) {
         this.index = index;
         this.builder = new PodDNSConfigOptionBuilder(this, item);
      }

      public Object and() {
         return PodDNSConfigFluent.this.setToOptions(this.index, this.builder.build());
      }

      public Object endOption() {
         return this.and();
      }
   }
}

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

public class ProjectedVolumeSourceFluent extends BaseFluent {
   private Integer defaultMode;
   private ArrayList sources = new ArrayList();
   private Map additionalProperties;

   public ProjectedVolumeSourceFluent() {
   }

   public ProjectedVolumeSourceFluent(ProjectedVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ProjectedVolumeSource instance) {
      instance = instance != null ? instance : new ProjectedVolumeSource();
      if (instance != null) {
         this.withDefaultMode(instance.getDefaultMode());
         this.withSources(instance.getSources());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getDefaultMode() {
      return this.defaultMode;
   }

   public ProjectedVolumeSourceFluent withDefaultMode(Integer defaultMode) {
      this.defaultMode = defaultMode;
      return this;
   }

   public boolean hasDefaultMode() {
      return this.defaultMode != null;
   }

   public ProjectedVolumeSourceFluent addToSources(int index, VolumeProjection item) {
      if (this.sources == null) {
         this.sources = new ArrayList();
      }

      VolumeProjectionBuilder builder = new VolumeProjectionBuilder(item);
      if (index >= 0 && index < this.sources.size()) {
         this._visitables.get("sources").add(index, builder);
         this.sources.add(index, builder);
      } else {
         this._visitables.get("sources").add(builder);
         this.sources.add(builder);
      }

      return this;
   }

   public ProjectedVolumeSourceFluent setToSources(int index, VolumeProjection item) {
      if (this.sources == null) {
         this.sources = new ArrayList();
      }

      VolumeProjectionBuilder builder = new VolumeProjectionBuilder(item);
      if (index >= 0 && index < this.sources.size()) {
         this._visitables.get("sources").set(index, builder);
         this.sources.set(index, builder);
      } else {
         this._visitables.get("sources").add(builder);
         this.sources.add(builder);
      }

      return this;
   }

   public ProjectedVolumeSourceFluent addToSources(VolumeProjection... items) {
      if (this.sources == null) {
         this.sources = new ArrayList();
      }

      for(VolumeProjection item : items) {
         VolumeProjectionBuilder builder = new VolumeProjectionBuilder(item);
         this._visitables.get("sources").add(builder);
         this.sources.add(builder);
      }

      return this;
   }

   public ProjectedVolumeSourceFluent addAllToSources(Collection items) {
      if (this.sources == null) {
         this.sources = new ArrayList();
      }

      for(VolumeProjection item : items) {
         VolumeProjectionBuilder builder = new VolumeProjectionBuilder(item);
         this._visitables.get("sources").add(builder);
         this.sources.add(builder);
      }

      return this;
   }

   public ProjectedVolumeSourceFluent removeFromSources(VolumeProjection... items) {
      if (this.sources == null) {
         return this;
      } else {
         for(VolumeProjection item : items) {
            VolumeProjectionBuilder builder = new VolumeProjectionBuilder(item);
            this._visitables.get("sources").remove(builder);
            this.sources.remove(builder);
         }

         return this;
      }
   }

   public ProjectedVolumeSourceFluent removeAllFromSources(Collection items) {
      if (this.sources == null) {
         return this;
      } else {
         for(VolumeProjection item : items) {
            VolumeProjectionBuilder builder = new VolumeProjectionBuilder(item);
            this._visitables.get("sources").remove(builder);
            this.sources.remove(builder);
         }

         return this;
      }
   }

   public ProjectedVolumeSourceFluent removeMatchingFromSources(Predicate predicate) {
      if (this.sources == null) {
         return this;
      } else {
         Iterator<VolumeProjectionBuilder> each = this.sources.iterator();
         List visitables = this._visitables.get("sources");

         while(each.hasNext()) {
            VolumeProjectionBuilder builder = (VolumeProjectionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSources() {
      return this.sources != null ? build(this.sources) : null;
   }

   public VolumeProjection buildSource(int index) {
      return ((VolumeProjectionBuilder)this.sources.get(index)).build();
   }

   public VolumeProjection buildFirstSource() {
      return ((VolumeProjectionBuilder)this.sources.get(0)).build();
   }

   public VolumeProjection buildLastSource() {
      return ((VolumeProjectionBuilder)this.sources.get(this.sources.size() - 1)).build();
   }

   public VolumeProjection buildMatchingSource(Predicate predicate) {
      for(VolumeProjectionBuilder item : this.sources) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSource(Predicate predicate) {
      for(VolumeProjectionBuilder item : this.sources) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ProjectedVolumeSourceFluent withSources(List sources) {
      if (this.sources != null) {
         this._visitables.get("sources").clear();
      }

      if (sources != null) {
         this.sources = new ArrayList();

         for(VolumeProjection item : sources) {
            this.addToSources(item);
         }
      } else {
         this.sources = null;
      }

      return this;
   }

   public ProjectedVolumeSourceFluent withSources(VolumeProjection... sources) {
      if (this.sources != null) {
         this.sources.clear();
         this._visitables.remove("sources");
      }

      if (sources != null) {
         for(VolumeProjection item : sources) {
            this.addToSources(item);
         }
      }

      return this;
   }

   public boolean hasSources() {
      return this.sources != null && !this.sources.isEmpty();
   }

   public SourcesNested addNewSource() {
      return new SourcesNested(-1, (VolumeProjection)null);
   }

   public SourcesNested addNewSourceLike(VolumeProjection item) {
      return new SourcesNested(-1, item);
   }

   public SourcesNested setNewSourceLike(int index, VolumeProjection item) {
      return new SourcesNested(index, item);
   }

   public SourcesNested editSource(int index) {
      if (this.sources.size() <= index) {
         throw new RuntimeException("Can't edit sources. Index exceeds size.");
      } else {
         return this.setNewSourceLike(index, this.buildSource(index));
      }
   }

   public SourcesNested editFirstSource() {
      if (this.sources.size() == 0) {
         throw new RuntimeException("Can't edit first sources. The list is empty.");
      } else {
         return this.setNewSourceLike(0, this.buildSource(0));
      }
   }

   public SourcesNested editLastSource() {
      int index = this.sources.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last sources. The list is empty.");
      } else {
         return this.setNewSourceLike(index, this.buildSource(index));
      }
   }

   public SourcesNested editMatchingSource(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.sources.size(); ++i) {
         if (predicate.test((VolumeProjectionBuilder)this.sources.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching sources. No match found.");
      } else {
         return this.setNewSourceLike(index, this.buildSource(index));
      }
   }

   public ProjectedVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ProjectedVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ProjectedVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ProjectedVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ProjectedVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ProjectedVolumeSourceFluent that = (ProjectedVolumeSourceFluent)o;
            if (!Objects.equals(this.defaultMode, that.defaultMode)) {
               return false;
            } else if (!Objects.equals(this.sources, that.sources)) {
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
      return Objects.hash(new Object[]{this.defaultMode, this.sources, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.defaultMode != null) {
         sb.append("defaultMode:");
         sb.append(this.defaultMode + ",");
      }

      if (this.sources != null && !this.sources.isEmpty()) {
         sb.append("sources:");
         sb.append(this.sources + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SourcesNested extends VolumeProjectionFluent implements Nested {
      VolumeProjectionBuilder builder;
      int index;

      SourcesNested(int index, VolumeProjection item) {
         this.index = index;
         this.builder = new VolumeProjectionBuilder(this, item);
      }

      public Object and() {
         return ProjectedVolumeSourceFluent.this.setToSources(this.index, this.builder.build());
      }

      public Object endSource() {
         return this.and();
      }
   }
}

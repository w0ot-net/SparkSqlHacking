package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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

public class TypeCheckingFluent extends BaseFluent {
   private ArrayList expressionWarnings = new ArrayList();
   private Map additionalProperties;

   public TypeCheckingFluent() {
   }

   public TypeCheckingFluent(TypeChecking instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TypeChecking instance) {
      instance = instance != null ? instance : new TypeChecking();
      if (instance != null) {
         this.withExpressionWarnings(instance.getExpressionWarnings());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TypeCheckingFluent addToExpressionWarnings(int index, ExpressionWarning item) {
      if (this.expressionWarnings == null) {
         this.expressionWarnings = new ArrayList();
      }

      ExpressionWarningBuilder builder = new ExpressionWarningBuilder(item);
      if (index >= 0 && index < this.expressionWarnings.size()) {
         this._visitables.get("expressionWarnings").add(index, builder);
         this.expressionWarnings.add(index, builder);
      } else {
         this._visitables.get("expressionWarnings").add(builder);
         this.expressionWarnings.add(builder);
      }

      return this;
   }

   public TypeCheckingFluent setToExpressionWarnings(int index, ExpressionWarning item) {
      if (this.expressionWarnings == null) {
         this.expressionWarnings = new ArrayList();
      }

      ExpressionWarningBuilder builder = new ExpressionWarningBuilder(item);
      if (index >= 0 && index < this.expressionWarnings.size()) {
         this._visitables.get("expressionWarnings").set(index, builder);
         this.expressionWarnings.set(index, builder);
      } else {
         this._visitables.get("expressionWarnings").add(builder);
         this.expressionWarnings.add(builder);
      }

      return this;
   }

   public TypeCheckingFluent addToExpressionWarnings(ExpressionWarning... items) {
      if (this.expressionWarnings == null) {
         this.expressionWarnings = new ArrayList();
      }

      for(ExpressionWarning item : items) {
         ExpressionWarningBuilder builder = new ExpressionWarningBuilder(item);
         this._visitables.get("expressionWarnings").add(builder);
         this.expressionWarnings.add(builder);
      }

      return this;
   }

   public TypeCheckingFluent addAllToExpressionWarnings(Collection items) {
      if (this.expressionWarnings == null) {
         this.expressionWarnings = new ArrayList();
      }

      for(ExpressionWarning item : items) {
         ExpressionWarningBuilder builder = new ExpressionWarningBuilder(item);
         this._visitables.get("expressionWarnings").add(builder);
         this.expressionWarnings.add(builder);
      }

      return this;
   }

   public TypeCheckingFluent removeFromExpressionWarnings(ExpressionWarning... items) {
      if (this.expressionWarnings == null) {
         return this;
      } else {
         for(ExpressionWarning item : items) {
            ExpressionWarningBuilder builder = new ExpressionWarningBuilder(item);
            this._visitables.get("expressionWarnings").remove(builder);
            this.expressionWarnings.remove(builder);
         }

         return this;
      }
   }

   public TypeCheckingFluent removeAllFromExpressionWarnings(Collection items) {
      if (this.expressionWarnings == null) {
         return this;
      } else {
         for(ExpressionWarning item : items) {
            ExpressionWarningBuilder builder = new ExpressionWarningBuilder(item);
            this._visitables.get("expressionWarnings").remove(builder);
            this.expressionWarnings.remove(builder);
         }

         return this;
      }
   }

   public TypeCheckingFluent removeMatchingFromExpressionWarnings(Predicate predicate) {
      if (this.expressionWarnings == null) {
         return this;
      } else {
         Iterator<ExpressionWarningBuilder> each = this.expressionWarnings.iterator();
         List visitables = this._visitables.get("expressionWarnings");

         while(each.hasNext()) {
            ExpressionWarningBuilder builder = (ExpressionWarningBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildExpressionWarnings() {
      return this.expressionWarnings != null ? build(this.expressionWarnings) : null;
   }

   public ExpressionWarning buildExpressionWarning(int index) {
      return ((ExpressionWarningBuilder)this.expressionWarnings.get(index)).build();
   }

   public ExpressionWarning buildFirstExpressionWarning() {
      return ((ExpressionWarningBuilder)this.expressionWarnings.get(0)).build();
   }

   public ExpressionWarning buildLastExpressionWarning() {
      return ((ExpressionWarningBuilder)this.expressionWarnings.get(this.expressionWarnings.size() - 1)).build();
   }

   public ExpressionWarning buildMatchingExpressionWarning(Predicate predicate) {
      for(ExpressionWarningBuilder item : this.expressionWarnings) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingExpressionWarning(Predicate predicate) {
      for(ExpressionWarningBuilder item : this.expressionWarnings) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TypeCheckingFluent withExpressionWarnings(List expressionWarnings) {
      if (this.expressionWarnings != null) {
         this._visitables.get("expressionWarnings").clear();
      }

      if (expressionWarnings != null) {
         this.expressionWarnings = new ArrayList();

         for(ExpressionWarning item : expressionWarnings) {
            this.addToExpressionWarnings(item);
         }
      } else {
         this.expressionWarnings = null;
      }

      return this;
   }

   public TypeCheckingFluent withExpressionWarnings(ExpressionWarning... expressionWarnings) {
      if (this.expressionWarnings != null) {
         this.expressionWarnings.clear();
         this._visitables.remove("expressionWarnings");
      }

      if (expressionWarnings != null) {
         for(ExpressionWarning item : expressionWarnings) {
            this.addToExpressionWarnings(item);
         }
      }

      return this;
   }

   public boolean hasExpressionWarnings() {
      return this.expressionWarnings != null && !this.expressionWarnings.isEmpty();
   }

   public TypeCheckingFluent addNewExpressionWarning(String fieldRef, String warning) {
      return this.addToExpressionWarnings(new ExpressionWarning(fieldRef, warning));
   }

   public ExpressionWarningsNested addNewExpressionWarning() {
      return new ExpressionWarningsNested(-1, (ExpressionWarning)null);
   }

   public ExpressionWarningsNested addNewExpressionWarningLike(ExpressionWarning item) {
      return new ExpressionWarningsNested(-1, item);
   }

   public ExpressionWarningsNested setNewExpressionWarningLike(int index, ExpressionWarning item) {
      return new ExpressionWarningsNested(index, item);
   }

   public ExpressionWarningsNested editExpressionWarning(int index) {
      if (this.expressionWarnings.size() <= index) {
         throw new RuntimeException("Can't edit expressionWarnings. Index exceeds size.");
      } else {
         return this.setNewExpressionWarningLike(index, this.buildExpressionWarning(index));
      }
   }

   public ExpressionWarningsNested editFirstExpressionWarning() {
      if (this.expressionWarnings.size() == 0) {
         throw new RuntimeException("Can't edit first expressionWarnings. The list is empty.");
      } else {
         return this.setNewExpressionWarningLike(0, this.buildExpressionWarning(0));
      }
   }

   public ExpressionWarningsNested editLastExpressionWarning() {
      int index = this.expressionWarnings.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last expressionWarnings. The list is empty.");
      } else {
         return this.setNewExpressionWarningLike(index, this.buildExpressionWarning(index));
      }
   }

   public ExpressionWarningsNested editMatchingExpressionWarning(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.expressionWarnings.size(); ++i) {
         if (predicate.test((ExpressionWarningBuilder)this.expressionWarnings.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching expressionWarnings. No match found.");
      } else {
         return this.setNewExpressionWarningLike(index, this.buildExpressionWarning(index));
      }
   }

   public TypeCheckingFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TypeCheckingFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TypeCheckingFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TypeCheckingFluent removeFromAdditionalProperties(Map map) {
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

   public TypeCheckingFluent withAdditionalProperties(Map additionalProperties) {
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
            TypeCheckingFluent that = (TypeCheckingFluent)o;
            if (!Objects.equals(this.expressionWarnings, that.expressionWarnings)) {
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
      return Objects.hash(new Object[]{this.expressionWarnings, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.expressionWarnings != null && !this.expressionWarnings.isEmpty()) {
         sb.append("expressionWarnings:");
         sb.append(this.expressionWarnings + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ExpressionWarningsNested extends ExpressionWarningFluent implements Nested {
      ExpressionWarningBuilder builder;
      int index;

      ExpressionWarningsNested(int index, ExpressionWarning item) {
         this.index = index;
         this.builder = new ExpressionWarningBuilder(this, item);
      }

      public Object and() {
         return TypeCheckingFluent.this.setToExpressionWarnings(this.index, this.builder.build());
      }

      public Object endExpressionWarning() {
         return this.and();
      }
   }
}

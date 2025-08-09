package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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

public class StructuredResourceHandleFluent extends BaseFluent {
   private String nodeName;
   private ArrayList results = new ArrayList();
   private Object vendorClaimParameters;
   private Object vendorClassParameters;
   private Map additionalProperties;

   public StructuredResourceHandleFluent() {
   }

   public StructuredResourceHandleFluent(StructuredResourceHandle instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StructuredResourceHandle instance) {
      instance = instance != null ? instance : new StructuredResourceHandle();
      if (instance != null) {
         this.withNodeName(instance.getNodeName());
         this.withResults(instance.getResults());
         this.withVendorClaimParameters(instance.getVendorClaimParameters());
         this.withVendorClassParameters(instance.getVendorClassParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getNodeName() {
      return this.nodeName;
   }

   public StructuredResourceHandleFluent withNodeName(String nodeName) {
      this.nodeName = nodeName;
      return this;
   }

   public boolean hasNodeName() {
      return this.nodeName != null;
   }

   public StructuredResourceHandleFluent addToResults(int index, DriverAllocationResult item) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      DriverAllocationResultBuilder builder = new DriverAllocationResultBuilder(item);
      if (index >= 0 && index < this.results.size()) {
         this._visitables.get("results").add(index, builder);
         this.results.add(index, builder);
      } else {
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public StructuredResourceHandleFluent setToResults(int index, DriverAllocationResult item) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      DriverAllocationResultBuilder builder = new DriverAllocationResultBuilder(item);
      if (index >= 0 && index < this.results.size()) {
         this._visitables.get("results").set(index, builder);
         this.results.set(index, builder);
      } else {
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public StructuredResourceHandleFluent addToResults(DriverAllocationResult... items) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      for(DriverAllocationResult item : items) {
         DriverAllocationResultBuilder builder = new DriverAllocationResultBuilder(item);
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public StructuredResourceHandleFluent addAllToResults(Collection items) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      for(DriverAllocationResult item : items) {
         DriverAllocationResultBuilder builder = new DriverAllocationResultBuilder(item);
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public StructuredResourceHandleFluent removeFromResults(DriverAllocationResult... items) {
      if (this.results == null) {
         return this;
      } else {
         for(DriverAllocationResult item : items) {
            DriverAllocationResultBuilder builder = new DriverAllocationResultBuilder(item);
            this._visitables.get("results").remove(builder);
            this.results.remove(builder);
         }

         return this;
      }
   }

   public StructuredResourceHandleFluent removeAllFromResults(Collection items) {
      if (this.results == null) {
         return this;
      } else {
         for(DriverAllocationResult item : items) {
            DriverAllocationResultBuilder builder = new DriverAllocationResultBuilder(item);
            this._visitables.get("results").remove(builder);
            this.results.remove(builder);
         }

         return this;
      }
   }

   public StructuredResourceHandleFluent removeMatchingFromResults(Predicate predicate) {
      if (this.results == null) {
         return this;
      } else {
         Iterator<DriverAllocationResultBuilder> each = this.results.iterator();
         List visitables = this._visitables.get("results");

         while(each.hasNext()) {
            DriverAllocationResultBuilder builder = (DriverAllocationResultBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResults() {
      return this.results != null ? build(this.results) : null;
   }

   public DriverAllocationResult buildResult(int index) {
      return ((DriverAllocationResultBuilder)this.results.get(index)).build();
   }

   public DriverAllocationResult buildFirstResult() {
      return ((DriverAllocationResultBuilder)this.results.get(0)).build();
   }

   public DriverAllocationResult buildLastResult() {
      return ((DriverAllocationResultBuilder)this.results.get(this.results.size() - 1)).build();
   }

   public DriverAllocationResult buildMatchingResult(Predicate predicate) {
      for(DriverAllocationResultBuilder item : this.results) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResult(Predicate predicate) {
      for(DriverAllocationResultBuilder item : this.results) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StructuredResourceHandleFluent withResults(List results) {
      if (this.results != null) {
         this._visitables.get("results").clear();
      }

      if (results != null) {
         this.results = new ArrayList();

         for(DriverAllocationResult item : results) {
            this.addToResults(item);
         }
      } else {
         this.results = null;
      }

      return this;
   }

   public StructuredResourceHandleFluent withResults(DriverAllocationResult... results) {
      if (this.results != null) {
         this.results.clear();
         this._visitables.remove("results");
      }

      if (results != null) {
         for(DriverAllocationResult item : results) {
            this.addToResults(item);
         }
      }

      return this;
   }

   public boolean hasResults() {
      return this.results != null && !this.results.isEmpty();
   }

   public ResultsNested addNewResult() {
      return new ResultsNested(-1, (DriverAllocationResult)null);
   }

   public ResultsNested addNewResultLike(DriverAllocationResult item) {
      return new ResultsNested(-1, item);
   }

   public ResultsNested setNewResultLike(int index, DriverAllocationResult item) {
      return new ResultsNested(index, item);
   }

   public ResultsNested editResult(int index) {
      if (this.results.size() <= index) {
         throw new RuntimeException("Can't edit results. Index exceeds size.");
      } else {
         return this.setNewResultLike(index, this.buildResult(index));
      }
   }

   public ResultsNested editFirstResult() {
      if (this.results.size() == 0) {
         throw new RuntimeException("Can't edit first results. The list is empty.");
      } else {
         return this.setNewResultLike(0, this.buildResult(0));
      }
   }

   public ResultsNested editLastResult() {
      int index = this.results.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last results. The list is empty.");
      } else {
         return this.setNewResultLike(index, this.buildResult(index));
      }
   }

   public ResultsNested editMatchingResult(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.results.size(); ++i) {
         if (predicate.test((DriverAllocationResultBuilder)this.results.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching results. No match found.");
      } else {
         return this.setNewResultLike(index, this.buildResult(index));
      }
   }

   public Object getVendorClaimParameters() {
      return this.vendorClaimParameters;
   }

   public StructuredResourceHandleFluent withVendorClaimParameters(Object vendorClaimParameters) {
      this.vendorClaimParameters = vendorClaimParameters;
      return this;
   }

   public boolean hasVendorClaimParameters() {
      return this.vendorClaimParameters != null;
   }

   public Object getVendorClassParameters() {
      return this.vendorClassParameters;
   }

   public StructuredResourceHandleFluent withVendorClassParameters(Object vendorClassParameters) {
      this.vendorClassParameters = vendorClassParameters;
      return this;
   }

   public boolean hasVendorClassParameters() {
      return this.vendorClassParameters != null;
   }

   public StructuredResourceHandleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StructuredResourceHandleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StructuredResourceHandleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StructuredResourceHandleFluent removeFromAdditionalProperties(Map map) {
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

   public StructuredResourceHandleFluent withAdditionalProperties(Map additionalProperties) {
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
            StructuredResourceHandleFluent that = (StructuredResourceHandleFluent)o;
            if (!Objects.equals(this.nodeName, that.nodeName)) {
               return false;
            } else if (!Objects.equals(this.results, that.results)) {
               return false;
            } else if (!Objects.equals(this.vendorClaimParameters, that.vendorClaimParameters)) {
               return false;
            } else if (!Objects.equals(this.vendorClassParameters, that.vendorClassParameters)) {
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
      return Objects.hash(new Object[]{this.nodeName, this.results, this.vendorClaimParameters, this.vendorClassParameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nodeName != null) {
         sb.append("nodeName:");
         sb.append(this.nodeName + ",");
      }

      if (this.results != null && !this.results.isEmpty()) {
         sb.append("results:");
         sb.append(this.results + ",");
      }

      if (this.vendorClaimParameters != null) {
         sb.append("vendorClaimParameters:");
         sb.append(this.vendorClaimParameters + ",");
      }

      if (this.vendorClassParameters != null) {
         sb.append("vendorClassParameters:");
         sb.append(this.vendorClassParameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ResultsNested extends DriverAllocationResultFluent implements Nested {
      DriverAllocationResultBuilder builder;
      int index;

      ResultsNested(int index, DriverAllocationResult item) {
         this.index = index;
         this.builder = new DriverAllocationResultBuilder(this, item);
      }

      public Object and() {
         return StructuredResourceHandleFluent.this.setToResults(this.index, this.builder.build());
      }

      public Object endResult() {
         return this.and();
      }
   }
}

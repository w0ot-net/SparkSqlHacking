package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorRequirement;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.client.dsl.FilterNested;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.utils.Utils;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class FilterNestedImpl implements FilterNested {
   private static final String INVOLVED_OBJECT_NAME = "involvedObject.name";
   private static final String INVOLVED_OBJECT_NAMESPACE = "involvedObject.namespace";
   private static final String INVOLVED_OBJECT_KIND = "involvedObject.kind";
   private static final String INVOLVED_OBJECT_UID = "involvedObject.uid";
   private static final String INVOLVED_OBJECT_RESOURCE_VERSION = "involvedObject.resourceVersion";
   private static final String INVOLVED_OBJECT_API_VERSION = "involvedObject.apiVersion";
   private static final String INVOLVED_OBJECT_FIELD_PATH = "involvedObject.fieldPath";
   private final BaseOperation baseOperation;
   private OperationContext context;

   FilterNestedImpl(BaseOperation baseOperation) {
      this.baseOperation = baseOperation;
      this.context = this.baseOperation.context;
      this.context = this.context.copy();
      this.context.labels = new LinkedHashMap(this.baseOperation.context.getLabels());
      this.context.labelsNot = new LinkedHashMap(this.baseOperation.context.getLabelsNot());
      this.context.labelsIn = new LinkedHashMap(this.baseOperation.context.getLabelsIn());
      this.context.labelsNotIn = new LinkedHashMap(this.baseOperation.context.getLabelsNotIn());
      this.context.fields = new LinkedHashMap(this.baseOperation.context.getFields());
      this.context.fieldsNot = new LinkedHashMap(this.baseOperation.context.getFieldsNot());
      this.context.selectorAsString = this.baseOperation.context.selectorAsString;
   }

   public FilterNested withLabels(Map labels) {
      this.context.labels.putAll(labels);
      return this;
   }

   public FilterNested withoutLabels(Map labels) {
      labels.forEach(this::withoutLabel);
      return this;
   }

   public FilterNested withLabelIn(String key, String... values) {
      this.context.labelsIn.put(key, values);
      return this;
   }

   public FilterNested withLabelNotIn(String key, String... values) {
      this.context.labelsNotIn.put(key, values);
      return this;
   }

   public FilterNested withLabel(String key, String value) {
      this.context.labels.put(key, value);
      return this;
   }

   public FilterNested withoutLabel(String key, String value) {
      this.context.labelsNot.merge(key, new String[]{value}, (oldList, newList) -> {
         String[] concatList = (String[])Array.newInstance(String.class, oldList.length + newList.length);
         System.arraycopy(oldList, 0, concatList, 0, oldList.length);
         System.arraycopy(newList, 0, concatList, oldList.length, newList.length);
         return concatList;
      });
      return this;
   }

   public FilterNested withFields(Map fields) {
      this.context.fields.putAll(fields);
      return this;
   }

   public FilterNested withField(String key, String value) {
      this.context.fields.put(key, value);
      return this;
   }

   public FilterNested withoutFields(Map fields) {
      fields.forEach(this::withoutField);
      return this;
   }

   public FilterNested withoutField(String key, String value) {
      this.context.fieldsNot.merge(key, new String[]{value}, (oldList, newList) -> {
         if (Utils.isNotNullOrEmpty(newList[0])) {
            String[] concatList = (String[])Array.newInstance(String.class, oldList.length + newList.length);
            System.arraycopy(oldList, 0, concatList, 0, oldList.length);
            System.arraycopy(newList, 0, concatList, oldList.length, newList.length);
            return concatList;
         } else {
            return oldList;
         }
      });
      return this;
   }

   public FilterNested withLabelSelector(LabelSelector selector) {
      Map<String, String> matchLabels = selector.getMatchLabels();
      if (matchLabels != null) {
         this.withLabels(matchLabels);
      }

      List<LabelSelectorRequirement> matchExpressions = selector.getMatchExpressions();
      if (matchExpressions != null) {
         for(LabelSelectorRequirement req : matchExpressions) {
            String operator = req.getOperator();
            String key = req.getKey();
            switch (operator) {
               case "In":
                  this.withLabelIn(key, (String[])req.getValues().toArray(new String[0]));
                  break;
               case "NotIn":
                  this.withLabelNotIn(key, (String[])req.getValues().toArray(new String[0]));
                  break;
               case "DoesNotExist":
                  this.withoutLabel(key);
                  break;
               case "Exists":
                  this.withLabel(key);
                  break;
               default:
                  throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
         }
      }

      return this;
   }

   public FilterNested withInvolvedObject(ObjectReference objectReference) {
      if (objectReference.getName() != null) {
         this.context.fields.put("involvedObject.name", objectReference.getName());
      }

      if (objectReference.getNamespace() != null) {
         this.context.fields.put("involvedObject.namespace", objectReference.getNamespace());
      }

      if (objectReference.getKind() != null) {
         this.context.fields.put("involvedObject.kind", objectReference.getKind());
      }

      if (objectReference.getUid() != null) {
         this.context.fields.put("involvedObject.uid", objectReference.getUid());
      }

      if (objectReference.getResourceVersion() != null) {
         this.context.fields.put("involvedObject.resourceVersion", objectReference.getResourceVersion());
      }

      if (objectReference.getApiVersion() != null) {
         this.context.fields.put("involvedObject.apiVersion", objectReference.getApiVersion());
      }

      if (objectReference.getFieldPath() != null) {
         this.context.fields.put("involvedObject.fieldPath", objectReference.getFieldPath());
      }

      return this;
   }

   public FilterWatchListDeletable and() {
      return this.baseOperation.newInstance(this.context);
   }

   public FilterNested withLabelSelector(String selectorAsString) {
      this.context.selectorAsString = selectorAsString;
      return this;
   }
}

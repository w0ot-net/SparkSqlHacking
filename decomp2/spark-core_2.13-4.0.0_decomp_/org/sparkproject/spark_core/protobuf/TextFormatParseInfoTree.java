package org.sparkproject.spark_core.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextFormatParseInfoTree {
   private Map locationsFromField;
   Map subtreesFromField;

   private TextFormatParseInfoTree(Map locationsFromField, Map subtreeBuildersFromField) {
      Map<Descriptors.FieldDescriptor, List<TextFormatParseLocation>> locs = new HashMap();

      for(Map.Entry kv : locationsFromField.entrySet()) {
         locs.put((Descriptors.FieldDescriptor)kv.getKey(), Collections.unmodifiableList((List)kv.getValue()));
      }

      this.locationsFromField = Collections.unmodifiableMap(locs);
      Map<Descriptors.FieldDescriptor, List<TextFormatParseInfoTree>> subs = new HashMap();

      for(Map.Entry kv : subtreeBuildersFromField.entrySet()) {
         List<TextFormatParseInfoTree> submessagesOfField = new ArrayList();

         for(Builder subBuilder : (List)kv.getValue()) {
            submessagesOfField.add(subBuilder.build());
         }

         subs.put((Descriptors.FieldDescriptor)kv.getKey(), Collections.unmodifiableList(submessagesOfField));
      }

      this.subtreesFromField = Collections.unmodifiableMap(subs);
   }

   public List getLocations(final Descriptors.FieldDescriptor fieldDescriptor) {
      List<TextFormatParseLocation> result = (List)this.locationsFromField.get(fieldDescriptor);
      return result == null ? Collections.emptyList() : result;
   }

   public TextFormatParseLocation getLocation(final Descriptors.FieldDescriptor fieldDescriptor, int index) {
      return (TextFormatParseLocation)getFromList(this.getLocations(fieldDescriptor), index, fieldDescriptor);
   }

   public List getNestedTrees(final Descriptors.FieldDescriptor fieldDescriptor) {
      List<TextFormatParseInfoTree> result = (List)this.subtreesFromField.get(fieldDescriptor);
      return result == null ? Collections.emptyList() : result;
   }

   public TextFormatParseInfoTree getNestedTree(final Descriptors.FieldDescriptor fieldDescriptor, int index) {
      return (TextFormatParseInfoTree)getFromList(this.getNestedTrees(fieldDescriptor), index, fieldDescriptor);
   }

   public static Builder builder() {
      return new Builder();
   }

   private static Object getFromList(List list, int index, Descriptors.FieldDescriptor fieldDescriptor) {
      if (index < list.size() && index >= 0) {
         return list.get(index);
      } else {
         throw new IllegalArgumentException(String.format("Illegal index field: %s, index %d", fieldDescriptor == null ? "<null>" : fieldDescriptor.getName(), index));
      }
   }

   public static class Builder {
      private Map locationsFromField;
      private Map subtreeBuildersFromField;

      private Builder() {
         this.locationsFromField = new HashMap();
         this.subtreeBuildersFromField = new HashMap();
      }

      public Builder setLocation(final Descriptors.FieldDescriptor fieldDescriptor, TextFormatParseLocation location) {
         List<TextFormatParseLocation> fieldLocations = (List)this.locationsFromField.get(fieldDescriptor);
         if (fieldLocations == null) {
            fieldLocations = new ArrayList();
            this.locationsFromField.put(fieldDescriptor, fieldLocations);
         }

         fieldLocations.add(location);
         return this;
      }

      public Builder getBuilderForSubMessageField(final Descriptors.FieldDescriptor fieldDescriptor) {
         List<Builder> submessageBuilders = (List)this.subtreeBuildersFromField.get(fieldDescriptor);
         if (submessageBuilders == null) {
            submessageBuilders = new ArrayList();
            this.subtreeBuildersFromField.put(fieldDescriptor, submessageBuilders);
         }

         Builder subtreeBuilder = new Builder();
         submessageBuilders.add(subtreeBuilder);
         return subtreeBuilder;
      }

      public TextFormatParseInfoTree build() {
         return new TextFormatParseInfoTree(this.locationsFromField, this.subtreeBuildersFromField);
      }
   }
}

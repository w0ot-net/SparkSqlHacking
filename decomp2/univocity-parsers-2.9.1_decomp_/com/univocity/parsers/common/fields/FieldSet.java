package com.univocity.parsers.common.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FieldSet implements Cloneable {
   private List fields = new ArrayList();
   private List wrappedFieldSets;

   public FieldSet() {
      this.wrappedFieldSets = Collections.emptyList();
   }

   public FieldSet(List wrappedFieldSets) {
      this.wrappedFieldSets = wrappedFieldSets;
      if (this.wrappedFieldSets.contains(this)) {
         this.wrappedFieldSets.remove(this);
      }

   }

   public List get() {
      return new ArrayList(this.fields);
   }

   public FieldSet set(Object... fields) {
      this.fields.clear();
      this.add(fields);

      for(FieldSet wrapped : this.wrappedFieldSets) {
         wrapped.set(fields);
      }

      return this;
   }

   public FieldSet add(Object... fields) {
      for(Object field : fields) {
         this.addElement(field);
      }

      for(FieldSet wrapped : this.wrappedFieldSets) {
         wrapped.add(fields);
      }

      return this;
   }

   private void addElement(Object field) {
      this.fields.add(field);
   }

   public FieldSet set(Collection fields) {
      this.fields.clear();
      this.add(fields);

      for(FieldSet wrapped : this.wrappedFieldSets) {
         wrapped.set(fields);
      }

      return this;
   }

   public FieldSet add(Collection fields) {
      for(Object field : fields) {
         this.addElement(field);
      }

      for(FieldSet wrapped : this.wrappedFieldSets) {
         wrapped.add(fields);
      }

      return this;
   }

   public FieldSet remove(Object... fields) {
      for(Object field : fields) {
         this.fields.remove(field);
      }

      for(FieldSet wrapped : this.wrappedFieldSets) {
         wrapped.remove(fields);
      }

      return this;
   }

   public FieldSet remove(Collection fields) {
      this.fields.removeAll(fields);

      for(FieldSet wrapped : this.wrappedFieldSets) {
         wrapped.remove(fields);
      }

      return this;
   }

   public String describe() {
      return "field selection: " + this.fields.toString();
   }

   public String toString() {
      return this.fields.toString();
   }

   public FieldSet clone() {
      try {
         FieldSet out = (FieldSet)super.clone();
         out.fields = new ArrayList(this.fields);
         if (this.wrappedFieldSets != null) {
            out.wrappedFieldSets = new ArrayList();

            for(FieldSet fieldSet : this.wrappedFieldSets) {
               out.wrappedFieldSets.add(fieldSet.clone());
            }
         }

         return out;
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }
}

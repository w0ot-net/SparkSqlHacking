package org.apache.orc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.orc.DataMask;
import org.apache.orc.DataMaskDescription;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.jetbrains.annotations.NotNull;

public class MaskDescriptionImpl implements DataMaskDescription, Comparable {
   private int id;
   private final String name;
   private final String[] parameters;
   private final List columns = new ArrayList();

   public MaskDescriptionImpl(String name, String... parameters) {
      this.name = name;
      this.parameters = parameters == null ? new String[0] : parameters;
   }

   public MaskDescriptionImpl(int id, OrcProto.DataMask mask) {
      this.id = id;
      this.name = mask.getName();
      this.parameters = new String[mask.getMaskParametersCount()];

      for(int p = 0; p < this.parameters.length; ++p) {
         this.parameters[p] = mask.getMaskParameters(p);
      }

   }

   public boolean equals(Object other) {
      if (other != null && other.getClass() == this.getClass()) {
         return this.compareTo((MaskDescriptionImpl)other) == 0;
      } else {
         return false;
      }
   }

   public void addColumn(TypeDescription column) {
      this.columns.add(column);
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public String[] getParameters() {
      return this.parameters;
   }

   public TypeDescription[] getColumns() {
      TypeDescription[] result = (TypeDescription[])this.columns.toArray(new TypeDescription[0]);
      Arrays.sort(result, Comparator.comparingInt(TypeDescription::getId));
      return result;
   }

   public int getId() {
      return this.id;
   }

   public DataMask create(TypeDescription schema, DataMask.MaskOverrides overrides) {
      return DataMask.Factory.build(this, schema, overrides);
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append("mask ");
      buffer.append(this.getName());
      buffer.append('(');
      String[] parameters = this.getParameters();
      if (parameters != null) {
         for(int p = 0; p < parameters.length; ++p) {
            if (p != 0) {
               buffer.append(", ");
            }

            buffer.append(parameters[p]);
         }
      }

      buffer.append(')');
      return buffer.toString();
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      result = 31 * result + Arrays.hashCode(this.parameters);
      return result;
   }

   public int compareTo(@NotNull MaskDescriptionImpl other) {
      if (other == this) {
         return 0;
      } else {
         int result = this.name.compareTo(other.name);

         for(int p = 0; result == 0 && p < this.parameters.length && p < other.parameters.length; ++p) {
            result = this.parameters[p].compareTo(other.parameters[p]);
         }

         if (result == 0) {
            result = Integer.compare(this.parameters.length, other.parameters.length);
         }

         return result;
      }
   }
}

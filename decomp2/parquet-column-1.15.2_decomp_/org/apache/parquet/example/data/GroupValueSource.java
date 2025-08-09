package org.apache.parquet.example.data;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.GroupType;

public abstract class GroupValueSource {
   public int getFieldRepetitionCount(String field) {
      return this.getFieldRepetitionCount(this.getType().getFieldIndex(field));
   }

   public GroupValueSource getGroup(String field, int index) {
      return this.getGroup(this.getType().getFieldIndex(field), index);
   }

   public String getString(String field, int index) {
      return this.getString(this.getType().getFieldIndex(field), index);
   }

   public int getInteger(String field, int index) {
      return this.getInteger(this.getType().getFieldIndex(field), index);
   }

   public long getLong(String field, int index) {
      return this.getLong(this.getType().getFieldIndex(field), index);
   }

   public double getDouble(String field, int index) {
      return this.getDouble(this.getType().getFieldIndex(field), index);
   }

   public float getFloat(String field, int index) {
      return this.getFloat(this.getType().getFieldIndex(field), index);
   }

   public boolean getBoolean(String field, int index) {
      return this.getBoolean(this.getType().getFieldIndex(field), index);
   }

   public Binary getBinary(String field, int index) {
      return this.getBinary(this.getType().getFieldIndex(field), index);
   }

   public Binary getInt96(String field, int index) {
      return this.getInt96(this.getType().getFieldIndex(field), index);
   }

   public abstract int getFieldRepetitionCount(int var1);

   public abstract GroupValueSource getGroup(int var1, int var2);

   public abstract String getString(int var1, int var2);

   public abstract int getInteger(int var1, int var2);

   public abstract long getLong(int var1, int var2);

   public abstract double getDouble(int var1, int var2);

   public abstract float getFloat(int var1, int var2);

   public abstract boolean getBoolean(int var1, int var2);

   public abstract Binary getBinary(int var1, int var2);

   public abstract Binary getInt96(int var1, int var2);

   public abstract String getValueToString(int var1, int var2);

   public abstract GroupType getType();
}

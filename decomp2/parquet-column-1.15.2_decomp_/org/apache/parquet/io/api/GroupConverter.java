package org.apache.parquet.io.api;

public abstract class GroupConverter extends Converter {
   public boolean isPrimitive() {
      return false;
   }

   public GroupConverter asGroupConverter() {
      return this;
   }

   public abstract Converter getConverter(int var1);

   public abstract void start();

   public abstract void end();
}

package org.apache.parquet.hadoop.mapred;

public class Container {
   Object object;

   public void set(Object object) {
      this.object = object;
   }

   public Object get() {
      return this.object;
   }
}

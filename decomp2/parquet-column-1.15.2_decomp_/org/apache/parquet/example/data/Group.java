package org.apache.parquet.example.data;

import org.apache.parquet.example.data.simple.NanoTime;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Group extends GroupValueSource {
   private static final Logger LOG = LoggerFactory.getLogger(Group.class);

   public void add(String field, int value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, long value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, float value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, double value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, String value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, NanoTime value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, boolean value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, Binary value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public void add(String field, Group value) {
      this.add(this.getType().getFieldIndex(field), value);
   }

   public Group addGroup(String field) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("add group {} to {}", field, this.getType().getName());
      }

      return this.addGroup(this.getType().getFieldIndex(field));
   }

   public Group getGroup(String field, int index) {
      return this.getGroup(this.getType().getFieldIndex(field), index);
   }

   public abstract void add(int var1, int var2);

   public abstract void add(int var1, long var2);

   public abstract void add(int var1, String var2);

   public abstract void add(int var1, boolean var2);

   public abstract void add(int var1, NanoTime var2);

   public abstract void add(int var1, Binary var2);

   public abstract void add(int var1, float var2);

   public abstract void add(int var1, double var2);

   public abstract void add(int var1, Group var2);

   public abstract Group addGroup(int var1);

   public abstract Group getGroup(int var1, int var2);

   public Group asGroup() {
      return this;
   }

   public Group append(String fieldName, int value) {
      this.add(fieldName, value);
      return this;
   }

   public Group append(String fieldName, float value) {
      this.add(fieldName, value);
      return this;
   }

   public Group append(String fieldName, double value) {
      this.add(fieldName, value);
      return this;
   }

   public Group append(String fieldName, long value) {
      this.add(fieldName, value);
      return this;
   }

   public Group append(String fieldName, NanoTime value) {
      this.add(fieldName, value);
      return this;
   }

   public Group append(String fieldName, String value) {
      this.add(fieldName, Binary.fromString(value));
      return this;
   }

   public Group append(String fieldName, boolean value) {
      this.add(fieldName, value);
      return this;
   }

   public Group append(String fieldName, Binary value) {
      this.add(fieldName, value);
      return this;
   }

   public abstract void writeValue(int var1, int var2, RecordConsumer var3);
}

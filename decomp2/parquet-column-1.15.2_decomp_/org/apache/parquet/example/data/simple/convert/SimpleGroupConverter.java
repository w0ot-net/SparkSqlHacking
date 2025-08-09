package org.apache.parquet.example.data.simple.convert;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.Type;

class SimpleGroupConverter extends GroupConverter {
   private final SimpleGroupConverter parent;
   private final int index;
   protected Group current;
   private Converter[] converters;

   SimpleGroupConverter(SimpleGroupConverter parent, int index, GroupType schema) {
      this.parent = parent;
      this.index = index;
      this.converters = new Converter[schema.getFieldCount()];

      for(int i = 0; i < this.converters.length; ++i) {
         Type type = schema.getType(i);
         if (type.isPrimitive()) {
            this.converters[i] = new SimplePrimitiveConverter(this, i);
         } else {
            this.converters[i] = new SimpleGroupConverter(this, i, type.asGroupType());
         }
      }

   }

   public void start() {
      this.current = this.parent.getCurrentRecord().addGroup(this.index);
   }

   public Converter getConverter(int fieldIndex) {
      return this.converters[fieldIndex];
   }

   public void end() {
   }

   public Group getCurrentRecord() {
      return this.current;
   }
}

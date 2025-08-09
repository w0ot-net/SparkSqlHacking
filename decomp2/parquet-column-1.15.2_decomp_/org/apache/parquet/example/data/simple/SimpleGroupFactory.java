package org.apache.parquet.example.data.simple;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupFactory;
import org.apache.parquet.schema.MessageType;

public class SimpleGroupFactory extends GroupFactory {
   private final MessageType schema;

   public SimpleGroupFactory(MessageType schema) {
      this.schema = schema;
   }

   public Group newGroup() {
      return new SimpleGroup(this.schema);
   }
}

package org.apache.parquet.example.data.simple.convert;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;

public class GroupRecordConverter extends RecordMaterializer {
   private final SimpleGroupFactory simpleGroupFactory;
   private SimpleGroupConverter root;

   public GroupRecordConverter(MessageType schema) {
      this.simpleGroupFactory = new SimpleGroupFactory(schema);
      this.root = new SimpleGroupConverter((SimpleGroupConverter)null, 0, schema) {
         public void start() {
            this.current = GroupRecordConverter.this.simpleGroupFactory.newGroup();
         }

         public void end() {
         }
      };
   }

   public Group getCurrentRecord() {
      return this.root.getCurrentRecord();
   }

   public GroupConverter getRootConverter() {
      return this.root;
   }
}

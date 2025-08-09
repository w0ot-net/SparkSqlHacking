package org.apache.parquet.example.data;

import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.Type;

public class GroupWriter {
   private final RecordConsumer recordConsumer;
   private final GroupType schema;

   public GroupWriter(RecordConsumer recordConsumer, GroupType schema) {
      this.recordConsumer = recordConsumer;
      this.schema = schema;
   }

   public void write(Group group) {
      this.recordConsumer.startMessage();
      this.writeGroup(group, this.schema);
      this.recordConsumer.endMessage();
   }

   private void writeGroup(Group group, GroupType type) {
      int fieldCount = type.getFieldCount();

      for(int field = 0; field < fieldCount; ++field) {
         int valueCount = group.getFieldRepetitionCount(field);
         if (valueCount > 0) {
            Type fieldType = type.getType(field);
            String fieldName = fieldType.getName();
            this.recordConsumer.startField(fieldName, field);

            for(int index = 0; index < valueCount; ++index) {
               if (fieldType.isPrimitive()) {
                  group.writeValue(field, index, this.recordConsumer);
               } else {
                  this.recordConsumer.startGroup();
                  this.writeGroup(group.getGroup(field, index), fieldType.asGroupType());
                  this.recordConsumer.endGroup();
               }
            }

            this.recordConsumer.endField(fieldName, field);
         }
      }

   }
}

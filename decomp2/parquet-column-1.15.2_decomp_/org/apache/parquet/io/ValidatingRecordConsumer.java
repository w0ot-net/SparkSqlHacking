package org.apache.parquet.io;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatingRecordConsumer extends RecordConsumer {
   private static final Logger LOG = LoggerFactory.getLogger(ValidatingRecordConsumer.class);
   private final RecordConsumer delegate;
   private Deque types = new ArrayDeque();
   private Deque fields = new ArrayDeque();
   private Deque previousField = new ArrayDeque();
   private Deque fieldValueCount = new ArrayDeque();

   public ValidatingRecordConsumer(RecordConsumer delegate, MessageType schema) {
      this.delegate = delegate;
      this.types.push(schema);
   }

   public void startMessage() {
      this.previousField.push(-1);
      this.delegate.startMessage();
   }

   public void endMessage() {
      this.delegate.endMessage();
      this.validateMissingFields(((Type)this.types.peek()).asGroupType().getFieldCount());
      this.previousField.pop();
   }

   public void startField(String field, int index) {
      if (index <= (Integer)this.previousField.peek()) {
         throw new InvalidRecordException("fields must be added in order " + field + " index " + index + " is before previous field " + this.previousField.peek());
      } else {
         this.validateMissingFields(index);
         this.fields.push(index);
         this.fieldValueCount.push(0);
         this.delegate.startField(field, index);
      }
   }

   private void validateMissingFields(int index) {
      for(int i = (Integer)this.previousField.peek() + 1; i < index; ++i) {
         Type type = ((Type)this.types.peek()).asGroupType().getType(i);
         if (type.isRepetition(Type.Repetition.REQUIRED)) {
            throw new InvalidRecordException("required field is missing " + type);
         }
      }

   }

   public void endField(String field, int index) {
      this.delegate.endField(field, index);
      this.fieldValueCount.pop();
      this.previousField.push(this.fields.pop());
   }

   public void startGroup() {
      this.previousField.push(-1);
      this.types.push(((Type)this.types.peek()).asGroupType().getType((Integer)this.fields.peek()));
      this.delegate.startGroup();
   }

   public void endGroup() {
      this.delegate.endGroup();
      this.validateMissingFields(((Type)this.types.peek()).asGroupType().getFieldCount());
      this.types.pop();
      this.previousField.pop();
   }

   public void flush() {
      this.delegate.flush();
   }

   private void validate(PrimitiveType.PrimitiveTypeName p) {
      Type currentType = ((Type)this.types.peek()).asGroupType().getType((Integer)this.fields.peek());
      int c = (Integer)this.fieldValueCount.pop() + 1;
      this.fieldValueCount.push(c);
      LOG.debug("validate {} for {}", p, currentType.getName());
      switch (currentType.getRepetition()) {
         case OPTIONAL:
         case REQUIRED:
            if (c > 1) {
               throw new InvalidRecordException("repeated value when the type is not repeated in " + currentType);
            }
         case REPEATED:
            if (currentType.isPrimitive() && currentType.asPrimitiveType().getPrimitiveTypeName() == p) {
               return;
            }

            throw new InvalidRecordException("expected type " + p + " but got " + currentType);
         default:
            throw new InvalidRecordException("unknown repetition " + currentType.getRepetition() + " in " + currentType);
      }
   }

   private void validate(PrimitiveType.PrimitiveTypeName... ptypes) {
      Type currentType = ((Type)this.types.peek()).asGroupType().getType((Integer)this.fields.peek());
      int c = (Integer)this.fieldValueCount.pop() + 1;
      this.fieldValueCount.push(c);
      if (LOG.isDebugEnabled()) {
         LOG.debug("validate " + Arrays.toString(ptypes) + " for " + currentType.getName());
      }

      switch (currentType.getRepetition()) {
         case OPTIONAL:
         case REQUIRED:
            if (c > 1) {
               throw new InvalidRecordException("repeated value when the type is not repeated in " + currentType);
            }
         case REPEATED:
            if (!currentType.isPrimitive()) {
               throw new InvalidRecordException("expected type in " + Arrays.toString(ptypes) + " but got " + currentType);
            } else {
               for(PrimitiveType.PrimitiveTypeName p : ptypes) {
                  if (currentType.asPrimitiveType().getPrimitiveTypeName() == p) {
                     return;
                  }
               }

               throw new InvalidRecordException("expected type in " + Arrays.toString(ptypes) + " but got " + currentType);
            }
         default:
            throw new InvalidRecordException("unknown repetition " + currentType.getRepetition() + " in " + currentType);
      }
   }

   public void addInteger(int value) {
      this.validate(PrimitiveType.PrimitiveTypeName.INT32);
      this.delegate.addInteger(value);
   }

   public void addLong(long value) {
      this.validate(PrimitiveType.PrimitiveTypeName.INT64);
      this.delegate.addLong(value);
   }

   public void addBoolean(boolean value) {
      this.validate(PrimitiveType.PrimitiveTypeName.BOOLEAN);
      this.delegate.addBoolean(value);
   }

   public void addBinary(Binary value) {
      this.validate(PrimitiveType.PrimitiveTypeName.BINARY, PrimitiveType.PrimitiveTypeName.INT96, PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY);
      this.delegate.addBinary(value);
   }

   public void addFloat(float value) {
      this.validate(PrimitiveType.PrimitiveTypeName.FLOAT);
      this.delegate.addFloat(value);
   }

   public void addDouble(double value) {
      this.validate(PrimitiveType.PrimitiveTypeName.DOUBLE);
      this.delegate.addDouble(value);
   }
}

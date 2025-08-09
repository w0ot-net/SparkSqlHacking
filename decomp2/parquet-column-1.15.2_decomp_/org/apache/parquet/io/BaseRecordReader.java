package org.apache.parquet.io;

import org.apache.parquet.column.ColumnReadStore;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.io.api.RecordMaterializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public abstract class BaseRecordReader extends RecordReader {
   private static final Logger LOG = LoggerFactory.getLogger(BaseRecordReader.class);
   public RecordConsumer recordConsumer;
   public RecordMaterializer recordMaterializer;
   public ColumnReadStore columnStore;
   RecordReaderImplementation.State[] caseLookup;
   private String endField;
   private int endIndex;

   public Object read() {
      this.readOneRecord();
      return this.recordMaterializer.getCurrentRecord();
   }

   protected abstract void readOneRecord();

   protected void currentLevel(int currentLevel) {
      LOG.debug("currentLevel: {}", currentLevel);
   }

   protected void log(String message) {
      LOG.debug("bc: {}", message);
   }

   protected final int getCaseId(int state, int currentLevel, int d, int nextR) {
      return this.caseLookup[state].getCase(currentLevel, d, nextR).getID();
   }

   protected final void startMessage() {
      this.endField = null;
      LOG.debug("startMessage()");
      this.recordConsumer.startMessage();
   }

   protected final void startGroup(String field, int index) {
      this.startField(field, index);
      LOG.debug("startGroup()");
      this.recordConsumer.startGroup();
   }

   private void startField(String field, int index) {
      LOG.debug("startField({},{})", field, index);
      if (this.endField != null && index == this.endIndex) {
         this.endField = null;
      } else {
         if (this.endField != null) {
            this.recordConsumer.endField(this.endField, this.endIndex);
            this.endField = null;
         }

         this.recordConsumer.startField(field, index);
      }

   }

   protected final void addPrimitiveINT64(String field, int index, long value) {
      this.startField(field, index);
      LOG.debug("addLong({})", value);
      this.recordConsumer.addLong(value);
      this.endField(field, index);
   }

   private void endField(String field, int index) {
      LOG.debug("endField({},{})", field, index);
      if (this.endField != null) {
         this.recordConsumer.endField(this.endField, this.endIndex);
      }

      this.endField = field;
      this.endIndex = index;
   }

   protected final void addPrimitiveBINARY(String field, int index, Binary value) {
      this.startField(field, index);
      LOG.debug("addBinary({})", value);
      this.recordConsumer.addBinary(value);
      this.endField(field, index);
   }

   protected final void addPrimitiveINT32(String field, int index, int value) {
      this.startField(field, index);
      LOG.debug("addInteger({})", value);
      this.recordConsumer.addInteger(value);
      this.endField(field, index);
   }

   protected final void endGroup(String field, int index) {
      if (this.endField != null) {
         this.recordConsumer.endField(this.endField, this.endIndex);
         this.endField = null;
      }

      LOG.debug("endGroup()");
      this.recordConsumer.endGroup();
      this.endField(field, index);
   }

   protected final void endMessage() {
      if (this.endField != null) {
         this.recordConsumer.endField(this.endField, this.endIndex);
         this.endField = null;
      }

      LOG.debug("endMessage()");
      this.recordConsumer.endMessage();
   }

   protected void error(String message) {
      throw new ParquetDecodingException(message);
   }
}

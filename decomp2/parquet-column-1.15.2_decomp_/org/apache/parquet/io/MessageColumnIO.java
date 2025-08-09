package org.apache.parquet.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.column.ColumnWriteStore;
import org.apache.parquet.column.ColumnWriter;
import org.apache.parquet.column.impl.ColumnReadStoreImpl;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.recordlevel.FilteringRecordMaterializer;
import org.apache.parquet.filter2.recordlevel.IncrementallyUpdatedFilterPredicate;
import org.apache.parquet.filter2.recordlevel.IncrementallyUpdatedFilterPredicateBuilder;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;

public class MessageColumnIO extends GroupColumnIO {
   private static final Logger LOG = LoggerFactory.getLogger(MessageColumnIO.class);
   private static final boolean DEBUG;
   private List leaves;
   private final boolean validating;
   private final String createdBy;

   MessageColumnIO(MessageType messageType, boolean validating, String createdBy) {
      super(messageType, (GroupColumnIO)null, 0);
      this.validating = validating;
      this.createdBy = createdBy;
   }

   public List getColumnNames() {
      return super.getColumnNames();
   }

   public RecordReader getRecordReader(PageReadStore columns, RecordMaterializer recordMaterializer) {
      return this.getRecordReader(columns, recordMaterializer, FilterCompat.NOOP);
   }

   /** @deprecated */
   @Deprecated
   public RecordReader getRecordReader(PageReadStore columns, RecordMaterializer recordMaterializer, UnboundRecordFilter filter) {
      return this.getRecordReader(columns, recordMaterializer, FilterCompat.get(filter));
   }

   public RecordReader getRecordReader(final PageReadStore columns, final RecordMaterializer recordMaterializer, FilterCompat.Filter filter) {
      Objects.requireNonNull(columns, "columns cannot be null");
      Objects.requireNonNull(recordMaterializer, "recordMaterializer cannot be null");
      Objects.requireNonNull(filter, "filter cannot be null");
      return (RecordReader)(this.leaves.isEmpty() ? new EmptyRecordReader(recordMaterializer) : (RecordReader)filter.accept(new FilterCompat.Visitor() {
         public RecordReader visit(FilterCompat.FilterPredicateCompat filterPredicateCompat) {
            FilterPredicate predicate = filterPredicateCompat.getFilterPredicate();
            IncrementallyUpdatedFilterPredicateBuilder builder = new IncrementallyUpdatedFilterPredicateBuilder(MessageColumnIO.this.leaves);
            IncrementallyUpdatedFilterPredicate streamingPredicate = builder.build(predicate);
            RecordMaterializer<T> filteringRecordMaterializer = new FilteringRecordMaterializer(recordMaterializer, MessageColumnIO.this.leaves, builder.getValueInspectorsByColumn(), streamingPredicate);
            return new RecordReaderImplementation(MessageColumnIO.this, filteringRecordMaterializer, MessageColumnIO.this.validating, new ColumnReadStoreImpl(columns, filteringRecordMaterializer.getRootConverter(), MessageColumnIO.this.getType(), MessageColumnIO.this.createdBy));
         }

         public RecordReader visit(FilterCompat.UnboundRecordFilterCompat unboundRecordFilterCompat) {
            return new FilteredRecordReader(MessageColumnIO.this, recordMaterializer, MessageColumnIO.this.validating, new ColumnReadStoreImpl(columns, recordMaterializer.getRootConverter(), MessageColumnIO.this.getType(), MessageColumnIO.this.createdBy), unboundRecordFilterCompat.getUnboundRecordFilter(), columns.getRowCount());
         }

         public RecordReader visit(FilterCompat.NoOpFilter noOpFilter) {
            return new RecordReaderImplementation(MessageColumnIO.this, recordMaterializer, MessageColumnIO.this.validating, new ColumnReadStoreImpl(columns, recordMaterializer.getRootConverter(), MessageColumnIO.this.getType(), MessageColumnIO.this.createdBy));
         }
      }));
   }

   public RecordConsumer getRecordWriter(ColumnWriteStore columns) {
      RecordConsumer recordWriter = new MessageColumnIORecordConsumer(columns);
      if (DEBUG) {
         recordWriter = new RecordConsumerLoggingWrapper(recordWriter);
      }

      return (RecordConsumer)(this.validating ? new ValidatingRecordConsumer(recordWriter, this.getType()) : recordWriter);
   }

   void setLevels() {
      this.setLevels(0, 0, new String[0], new int[0], Collections.singletonList(this), Collections.singletonList(this));
   }

   void setLeaves(List leaves) {
      this.leaves = leaves;
   }

   public List getLeaves() {
      return this.leaves;
   }

   public MessageType getType() {
      return (MessageType)super.getType();
   }

   static {
      DEBUG = LOG.isDebugEnabled();
   }

   private class MessageColumnIORecordConsumer extends RecordConsumer {
      private ColumnIO currentColumnIO;
      private int currentLevel = 0;
      private final FieldsMarker[] fieldsWritten;
      private final int[] r;
      private final ColumnWriter[] columnWriters;
      private Map groupToLeafWriter = new HashMap();
      private Map groupNullCache = new HashMap();
      private final ColumnWriteStore columns;
      private boolean emptyField = true;

      private void buildGroupToLeafWriterMap(PrimitiveColumnIO primitive, ColumnWriter writer) {
         GroupColumnIO parent = primitive.getParent();

         do {
            this.getLeafWriters(parent).add(writer);
            parent = parent.getParent();
         } while(parent != null);

      }

      private List getLeafWriters(GroupColumnIO group) {
         return (List)this.groupToLeafWriter.computeIfAbsent(group, (k) -> new ArrayList());
      }

      public MessageColumnIORecordConsumer(ColumnWriteStore columns) {
         this.columns = columns;
         int maxDepth = 0;
         this.columnWriters = new ColumnWriter[MessageColumnIO.this.getLeaves().size()];

         for(PrimitiveColumnIO primitiveColumnIO : MessageColumnIO.this.getLeaves()) {
            ColumnWriter w = columns.getColumnWriter(primitiveColumnIO.getColumnDescriptor());
            maxDepth = Math.max(maxDepth, primitiveColumnIO.getFieldPath().length);
            this.columnWriters[primitiveColumnIO.getId()] = w;
            this.buildGroupToLeafWriterMap(primitiveColumnIO, w);
         }

         this.fieldsWritten = new FieldsMarker[maxDepth];

         for(int i = 0; i < maxDepth; ++i) {
            this.fieldsWritten[i] = new FieldsMarker();
         }

         this.r = new int[maxDepth];
      }

      private void printState() {
         if (MessageColumnIO.DEBUG) {
            this.log(this.currentLevel + ", " + this.fieldsWritten[this.currentLevel] + ": " + Arrays.toString(this.currentColumnIO.getFieldPath()) + " r:" + this.r[this.currentLevel]);
            if (this.r[this.currentLevel] > this.currentColumnIO.getRepetitionLevel()) {
               throw new InvalidRecordException(this.r[this.currentLevel] + "(r) > " + this.currentColumnIO.getRepetitionLevel() + " ( schema r)");
            }
         }

      }

      private void log(Object message, Object... parameters) {
         if (MessageColumnIO.DEBUG) {
            StringBuilder indent = new StringBuilder(this.currentLevel * 2);

            for(int i = 0; i < this.currentLevel; ++i) {
               indent.append("  ");
            }

            if (parameters.length == 0) {
               MessageColumnIO.LOG.debug(indent.toString() + message);
            } else {
               MessageColumnIO.LOG.debug(indent.toString() + message, parameters);
            }
         }

      }

      public void startMessage() {
         if (MessageColumnIO.DEBUG) {
            this.log("< MESSAGE START >");
         }

         this.currentColumnIO = MessageColumnIO.this;
         this.r[0] = 0;
         int numberOfFieldsToVisit = ((GroupColumnIO)this.currentColumnIO).getChildrenCount();
         this.fieldsWritten[0].reset(numberOfFieldsToVisit);
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void endMessage() {
         this.writeNullForMissingFieldsAtCurrentLevel();
         if (this.columns.isColumnFlushNeeded()) {
            this.flush();
         }

         this.columns.endRecord();
         if (MessageColumnIO.DEBUG) {
            this.log("< MESSAGE END >");
         }

         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void startField(String field, int index) {
         try {
            if (MessageColumnIO.DEBUG) {
               this.log("startField({}, {})", field, index);
            }

            this.currentColumnIO = ((GroupColumnIO)this.currentColumnIO).getChild(index);
            this.emptyField = true;
            if (MessageColumnIO.DEBUG) {
               this.printState();
            }

         } catch (RuntimeException e) {
            throw new ParquetEncodingException("error starting field " + field + " at " + index, e);
         }
      }

      public void endField(String field, int index) {
         if (MessageColumnIO.DEBUG) {
            this.log("endField({}, {})", field, index);
         }

         this.currentColumnIO = this.currentColumnIO.getParent();
         if (this.emptyField) {
            throw new ParquetEncodingException("empty fields are illegal, the field should be ommited completely instead");
         } else {
            this.fieldsWritten[this.currentLevel].markWritten(index);
            this.r[this.currentLevel] = this.currentLevel == 0 ? 0 : this.r[this.currentLevel - 1];
            if (MessageColumnIO.DEBUG) {
               this.printState();
            }

         }
      }

      private void writeNullForMissingFieldsAtCurrentLevel() {
         int currentFieldsCount = ((GroupColumnIO)this.currentColumnIO).getChildrenCount();

         for(int i = 0; i < currentFieldsCount; ++i) {
            if (!this.fieldsWritten[this.currentLevel].isWritten(i)) {
               try {
                  ColumnIO undefinedField = ((GroupColumnIO)this.currentColumnIO).getChild(i);
                  int d = this.currentColumnIO.getDefinitionLevel();
                  if (MessageColumnIO.DEBUG) {
                     this.log(Arrays.toString(undefinedField.getFieldPath()) + ".writeNull(" + this.r[this.currentLevel] + "," + d + ")");
                  }

                  this.writeNull(undefinedField, this.r[this.currentLevel], d);
               } catch (RuntimeException e) {
                  throw new ParquetEncodingException("error while writing nulls for fields of indexes " + i + " . current index: " + this.fieldsWritten[this.currentLevel], e);
               }
            }
         }

      }

      private void writeNull(ColumnIO undefinedField, int r, int d) {
         if (undefinedField.getType().isPrimitive()) {
            this.columnWriters[((PrimitiveColumnIO)undefinedField).getId()].writeNull(r, d);
         } else {
            GroupColumnIO groupColumnIO = (GroupColumnIO)undefinedField;
            this.cacheNullForGroup(groupColumnIO, r);
         }

      }

      private void cacheNullForGroup(GroupColumnIO group, int r) {
         IntArrayList nulls = (IntArrayList)this.groupNullCache.get(group);
         if (nulls == null) {
            nulls = new IntArrayList();
            this.groupNullCache.put(group, nulls);
         }

         nulls.add(r);
      }

      private void writeNullToLeaves(GroupColumnIO group) {
         IntArrayList nullCache = (IntArrayList)this.groupNullCache.get(group);
         if (nullCache != null && !nullCache.isEmpty()) {
            int parentDefinitionLevel = group.getParent().getDefinitionLevel();

            for(ColumnWriter leafWriter : (List)this.groupToLeafWriter.get(group)) {
               IntIterator iter = nullCache.iterator();

               while(iter.hasNext()) {
                  int repetitionLevel = iter.nextInt();
                  leafWriter.writeNull(repetitionLevel, parentDefinitionLevel);
               }
            }

            nullCache.clear();
         }
      }

      private void setRepetitionLevel() {
         this.r[this.currentLevel] = this.currentColumnIO.getRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.log("r: {}", this.r[this.currentLevel]);
         }

      }

      public void startGroup() {
         if (MessageColumnIO.DEBUG) {
            this.log("startGroup()");
         }

         GroupColumnIO group = (GroupColumnIO)this.currentColumnIO;
         if (this.hasNullCache(group)) {
            this.flushCachedNulls(group);
         }

         ++this.currentLevel;
         this.r[this.currentLevel] = this.r[this.currentLevel - 1];
         int fieldsCount = ((GroupColumnIO)this.currentColumnIO).getChildrenCount();
         this.fieldsWritten[this.currentLevel].reset(fieldsCount);
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      private boolean hasNullCache(GroupColumnIO group) {
         IntArrayList nulls = (IntArrayList)this.groupNullCache.get(group);
         return nulls != null && !nulls.isEmpty();
      }

      private void flushCachedNulls(GroupColumnIO group) {
         for(int i = 0; i < group.getChildrenCount(); ++i) {
            ColumnIO child = group.getChild(i);
            if (child instanceof GroupColumnIO) {
               this.flushCachedNulls((GroupColumnIO)child);
            }
         }

         this.writeNullToLeaves(group);
      }

      public void endGroup() {
         if (MessageColumnIO.DEBUG) {
            this.log("endGroup()");
         }

         this.emptyField = false;
         this.writeNullForMissingFieldsAtCurrentLevel();
         --this.currentLevel;
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      private ColumnWriter getColumnWriter() {
         return this.columnWriters[((PrimitiveColumnIO)this.currentColumnIO).getId()];
      }

      public void addInteger(int value) {
         if (MessageColumnIO.DEBUG) {
            this.log("addInt({})", value);
         }

         this.emptyField = false;
         this.getColumnWriter().write(value, this.r[this.currentLevel], this.currentColumnIO.getDefinitionLevel());
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void addLong(long value) {
         if (MessageColumnIO.DEBUG) {
            this.log("addLong({})", value);
         }

         this.emptyField = false;
         this.getColumnWriter().write(value, this.r[this.currentLevel], this.currentColumnIO.getDefinitionLevel());
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void addBoolean(boolean value) {
         if (MessageColumnIO.DEBUG) {
            this.log("addBoolean({})", value);
         }

         this.emptyField = false;
         this.getColumnWriter().write(value, this.r[this.currentLevel], this.currentColumnIO.getDefinitionLevel());
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void addBinary(Binary value) {
         if (MessageColumnIO.DEBUG) {
            this.log("addBinary({} bytes)", value.length());
         }

         this.emptyField = false;
         this.getColumnWriter().write(value, this.r[this.currentLevel], this.currentColumnIO.getDefinitionLevel());
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void addFloat(float value) {
         if (MessageColumnIO.DEBUG) {
            this.log("addFloat({})", value);
         }

         this.emptyField = false;
         this.getColumnWriter().write(value, this.r[this.currentLevel], this.currentColumnIO.getDefinitionLevel());
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void addDouble(double value) {
         if (MessageColumnIO.DEBUG) {
            this.log("addDouble({})", value);
         }

         this.emptyField = false;
         this.getColumnWriter().write(value, this.r[this.currentLevel], this.currentColumnIO.getDefinitionLevel());
         this.setRepetitionLevel();
         if (MessageColumnIO.DEBUG) {
            this.printState();
         }

      }

      public void flush() {
         this.flushCachedNulls(MessageColumnIO.this);
      }

      private class FieldsMarker {
         private BitSet visitedIndexes;

         private FieldsMarker() {
            this.visitedIndexes = new BitSet();
         }

         public String toString() {
            return "VisitedIndex{visitedIndexes=" + this.visitedIndexes + '}';
         }

         public void reset(int fieldsCount) {
            this.visitedIndexes.clear(0, fieldsCount);
         }

         public void markWritten(int i) {
            this.visitedIndexes.set(i);
         }

         public boolean isWritten(int i) {
            return this.visitedIndexes.get(i);
         }
      }
   }
}

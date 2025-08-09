package org.apache.orc.impl.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcProto;
import org.apache.orc.StringColumnStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.Dictionary;
import org.apache.orc.impl.DynamicIntArray;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.OutStream;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.PositionedOutputStream;
import org.apache.orc.impl.StreamName;
import org.apache.orc.impl.StringHashTableDictionary;
import org.apache.orc.impl.StringRedBlackTree;

public abstract class StringBaseTreeWriter extends TreeWriterBase {
   private final OutStream stringOutput;
   protected final IntegerWriter lengthOutput;
   private final IntegerWriter rowOutput;
   protected final DynamicIntArray rows = new DynamicIntArray();
   protected final PositionedOutputStream directStreamOutput;
   private final List savedRowIndex = new ArrayList();
   private final boolean buildIndex;
   private final List rowIndexValueCount = new ArrayList();
   private final double dictionaryKeySizeThreshold;
   protected Dictionary dictionary;
   protected boolean useDictionaryEncoding = true;
   private boolean isDirectV2 = true;
   private boolean doneDictionaryCheck;
   private final boolean strideDictionaryCheck;

   private static Dictionary createDict(Configuration conf) {
      String dictImpl = conf.get(OrcConf.DICTIONARY_IMPL.getAttribute(), OrcConf.DICTIONARY_IMPL.getDefaultValue().toString()).toUpperCase();
      switch (Dictionary.IMPL.valueOf(dictImpl)) {
         case RBTREE -> {
            return new StringRedBlackTree(4096);
         }
         case HASH -> {
            return new StringHashTableDictionary(4096);
         }
         default -> throw new UnsupportedOperationException("Unknown implementation:" + dictImpl);
      }
   }

   StringBaseTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      Configuration conf = context.getConfiguration();
      this.dictionary = createDict(conf);
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.directStreamOutput = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.stringOutput = context.createStream(new StreamName(this.id, Kind.DICTIONARY_DATA, encryption));
      this.lengthOutput = this.createIntegerWriter(context.createStream(new StreamName(this.id, Kind.LENGTH, encryption)), false, this.isDirectV2, context);
      this.rowOutput = this.createIntegerWriter(this.directStreamOutput, false, this.isDirectV2, context);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

      this.rowIndexValueCount.add(0L);
      this.buildIndex = context.buildIndex();
      this.dictionaryKeySizeThreshold = context.getDictionaryKeySizeThreshold(this.id);
      this.strideDictionaryCheck = OrcConf.ROW_INDEX_STRIDE_DICTIONARY_CHECK.getBoolean(conf);
      if (this.dictionaryKeySizeThreshold <= (double)0.0F) {
         this.useDictionaryEncoding = false;
         this.doneDictionaryCheck = true;
         this.recordDirectStreamPosition();
      } else {
         this.doneDictionaryCheck = false;
      }

   }

   private void checkDictionaryEncoding() {
      if (!this.doneDictionaryCheck) {
         float ratio = this.rows.size() > 0 ? (float)this.dictionary.size() / (float)this.rows.size() : 0.0F;
         this.useDictionaryEncoding = !this.isDirectV2 || (double)ratio <= this.dictionaryKeySizeThreshold;
         this.doneDictionaryCheck = true;
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      this.checkDictionaryEncoding();
      if (!this.useDictionaryEncoding) {
         this.stringOutput.suppress();
      }

      super.writeStripe(requiredIndexEntries);
      this.dictionary.clear();
      this.savedRowIndex.clear();
      this.rowIndexValueCount.clear();
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

      this.rowIndexValueCount.add(0L);
      if (!this.useDictionaryEncoding) {
         this.recordDirectStreamPosition();
      }

   }

   private void flushDictionary() throws IOException {
      final int[] dumpOrder = new int[this.dictionary.size()];
      if (this.useDictionaryEncoding) {
         this.dictionary.visit(new Dictionary.Visitor() {
            private int currentId = 0;

            public void visit(Dictionary.VisitorContext context) throws IOException {
               context.writeBytes(StringBaseTreeWriter.this.stringOutput);
               StringBaseTreeWriter.this.lengthOutput.write((long)context.getLength());
               dumpOrder[context.getOriginalPosition()] = this.currentId++;
            }
         });
      } else {
         this.stringOutput.suppress();
      }

      int length = this.rows.size();
      int rowIndexEntry = 0;
      OrcProto.RowIndex.Builder rowIndex = this.getRowIndex();

      for(int i = 0; i <= length; ++i) {
         OrcProto.RowIndexEntry.Builder base;
         if (this.buildIndex) {
            for(; (long)i == (Long)this.rowIndexValueCount.get(rowIndexEntry) && rowIndexEntry < this.savedRowIndex.size(); rowIndex.addEntry(base.build())) {
               base = ((OrcProto.RowIndexEntry)this.savedRowIndex.get(rowIndexEntry++)).toBuilder();
               if (this.useDictionaryEncoding) {
                  this.rowOutput.getPosition(new TreeWriterBase.RowIndexPositionRecorder(base));
               } else {
                  PositionRecorder posn = new TreeWriterBase.RowIndexPositionRecorder(base);
                  this.directStreamOutput.getPosition(posn);
                  this.lengthOutput.getPosition(posn);
               }
            }
         }

         if (i != length) {
            if (this.useDictionaryEncoding) {
               this.rowOutput.write((long)dumpOrder[this.rows.get(i)]);
            } else {
               int writeLen = this.dictionary.writeTo(this.directStreamOutput, this.rows.get(i));
               this.lengthOutput.write((long)writeLen);
            }
         }
      }

      this.rows.clear();
   }

   OrcProto.ColumnEncoding.Builder getEncoding() {
      OrcProto.ColumnEncoding.Builder result = super.getEncoding();
      if (this.useDictionaryEncoding) {
         result.setDictionarySize(this.dictionary.size());
         if (this.isDirectV2) {
            result.setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DICTIONARY_V2);
         } else {
            result.setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DICTIONARY);
         }
      } else if (this.isDirectV2) {
         result.setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT_V2);
      } else {
         result.setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT);
      }

      return result;
   }

   public void createRowIndexEntry() throws IOException {
      this.getStripeStatistics().merge(this.indexStatistics);
      OrcProto.RowIndexEntry.Builder rowIndexEntry = this.getRowIndexEntry();
      rowIndexEntry.setStatistics(this.indexStatistics.serialize());
      this.indexStatistics.reset();
      OrcProto.RowIndexEntry base = rowIndexEntry.build();
      this.savedRowIndex.add(base);
      rowIndexEntry.clear();
      this.addBloomFilterEntry();
      this.recordPosition(this.rowIndexPosition);
      this.rowIndexValueCount.add((long)this.rows.size());
      if (this.strideDictionaryCheck) {
         this.checkDictionaryEncoding();
      }

      if (!this.useDictionaryEncoding) {
         if (this.rows.size() > 0) {
            this.flushDictionary();
            this.recordDirectStreamPosition();
         } else {
            this.recordDirectStreamPosition();
            this.getRowIndex().addEntry(base);
         }
      }

   }

   private void recordDirectStreamPosition() throws IOException {
      if (this.rowIndexPosition != null) {
         this.directStreamOutput.getPosition(this.rowIndexPosition);
         this.lengthOutput.getPosition(this.rowIndexPosition);
      }

   }

   public long estimateMemory() {
      long parent = super.estimateMemory();
      return this.useDictionaryEncoding ? parent + this.dictionary.getSizeInBytes() + (long)this.rows.getSizeInBytes() : parent + this.lengthOutput.estimateMemory() + this.directStreamOutput.getBufferSize();
   }

   public long getRawDataSize() {
      StringColumnStatistics scs = (StringColumnStatistics)this.fileStatistics;
      long numVals = this.fileStatistics.getNumberOfValues();
      if (numVals == 0L) {
         return 0L;
      } else {
         int avgSize = (int)(scs.getSum() / numVals);
         return numVals * (long)JavaDataModel.get().lengthForStringOfLength(avgSize);
      }
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.checkDictionaryEncoding();
      if (this.useDictionaryEncoding) {
         this.flushDictionary();
         this.stringOutput.flush();
         this.lengthOutput.flush();
         this.rowOutput.flush();
      } else {
         if (this.rows.size() > 0) {
            this.flushDictionary();
         }

         this.stringOutput.suppress();
         this.directStreamOutput.flush();
         this.lengthOutput.flush();
      }

   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      Consumer<byte[]> updater = CryptoUtils.modifyIvForStripe((long)stripeId);
      this.stringOutput.changeIv(updater);
      this.lengthOutput.changeIv(updater);
      this.rowOutput.changeIv(updater);
      this.directStreamOutput.changeIv(updater);
   }
}

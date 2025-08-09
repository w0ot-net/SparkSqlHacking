package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import org.apache.arrow.flatbuf.DictionaryBatch;

public class ArrowDictionaryBatch implements ArrowMessage {
   private final long dictionaryId;
   private final ArrowRecordBatch dictionary;
   private final boolean isDelta;

   /** @deprecated */
   @Deprecated
   public ArrowDictionaryBatch(long dictionaryId, ArrowRecordBatch dictionary) {
      this(dictionaryId, dictionary, false);
   }

   public ArrowDictionaryBatch(long dictionaryId, ArrowRecordBatch dictionary, boolean isDelta) {
      this.dictionaryId = dictionaryId;
      this.dictionary = dictionary;
      this.isDelta = isDelta;
   }

   public boolean isDelta() {
      return this.isDelta;
   }

   public byte getMessageType() {
      return 2;
   }

   public long getDictionaryId() {
      return this.dictionaryId;
   }

   public ArrowRecordBatch getDictionary() {
      return this.dictionary;
   }

   public int writeTo(FlatBufferBuilder builder) {
      int dataOffset = this.dictionary.writeTo(builder);
      DictionaryBatch.startDictionaryBatch(builder);
      DictionaryBatch.addId(builder, this.dictionaryId);
      DictionaryBatch.addData(builder, dataOffset);
      DictionaryBatch.addIsDelta(builder, this.isDelta);
      return DictionaryBatch.endDictionaryBatch(builder);
   }

   public long computeBodyLength() {
      return this.dictionary.computeBodyLength();
   }

   public Object accepts(ArrowMessage.ArrowMessageVisitor visitor) {
      return visitor.visit(this);
   }

   public String toString() {
      long var10000 = this.dictionaryId;
      return "ArrowDictionaryBatch [dictionaryId=" + var10000 + ", dictionary=" + String.valueOf(this.dictionary) + "]";
   }

   public void close() {
      this.dictionary.close();
   }
}

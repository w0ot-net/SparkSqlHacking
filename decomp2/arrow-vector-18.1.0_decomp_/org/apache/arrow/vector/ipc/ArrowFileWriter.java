package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.arrow.util.VisibleForTesting;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.compression.CompressionUtil;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.ipc.message.ArrowBlock;
import org.apache.arrow.vector.ipc.message.ArrowDictionaryBatch;
import org.apache.arrow.vector.ipc.message.ArrowFooter;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;
import org.apache.arrow.vector.ipc.message.IpcOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrowFileWriter extends ArrowWriter {
   private static final Logger LOGGER = LoggerFactory.getLogger(ArrowFileWriter.class);
   private final List dictionaryBlocks;
   private final List recordBlocks;
   private Map metaData;
   private boolean dictionariesWritten;

   public ArrowFileWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out) {
      super(root, provider, out);
      this.dictionaryBlocks = new ArrayList();
      this.recordBlocks = new ArrayList();
      this.dictionariesWritten = false;
   }

   public ArrowFileWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, Map metaData) {
      super(root, provider, out);
      this.dictionaryBlocks = new ArrayList();
      this.recordBlocks = new ArrayList();
      this.dictionariesWritten = false;
      this.metaData = metaData;
   }

   public ArrowFileWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, IpcOption option) {
      super(root, provider, out, option);
      this.dictionaryBlocks = new ArrayList();
      this.recordBlocks = new ArrayList();
      this.dictionariesWritten = false;
   }

   public ArrowFileWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, Map metaData, IpcOption option) {
      super(root, provider, out, option);
      this.dictionaryBlocks = new ArrayList();
      this.recordBlocks = new ArrayList();
      this.dictionariesWritten = false;
      this.metaData = metaData;
   }

   public ArrowFileWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, Map metaData, IpcOption option, CompressionCodec.Factory compressionFactory, CompressionUtil.CodecType codecType) {
      this(root, provider, out, metaData, option, compressionFactory, codecType, Optional.empty());
   }

   public ArrowFileWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, Map metaData, IpcOption option, CompressionCodec.Factory compressionFactory, CompressionUtil.CodecType codecType, Optional compressionLevel) {
      super(root, provider, out, option, compressionFactory, codecType, compressionLevel);
      this.dictionaryBlocks = new ArrayList();
      this.recordBlocks = new ArrayList();
      this.dictionariesWritten = false;
      this.metaData = metaData;
   }

   protected void startInternal(WriteChannel out) throws IOException {
      ArrowMagic.writeMagic(out, true);
   }

   protected ArrowBlock writeDictionaryBatch(ArrowDictionaryBatch batch) throws IOException {
      ArrowBlock block = super.writeDictionaryBatch(batch);
      this.dictionaryBlocks.add(block);
      return block;
   }

   protected ArrowBlock writeRecordBatch(ArrowRecordBatch batch) throws IOException {
      ArrowBlock block = super.writeRecordBatch(batch);
      this.recordBlocks.add(block);
      return block;
   }

   protected void endInternal(WriteChannel out) throws IOException {
      if (!this.option.write_legacy_ipc_format) {
         out.writeIntLittleEndian(-1);
      }

      out.writeIntLittleEndian(0);
      long footerStart = out.getCurrentPosition();
      out.write(new ArrowFooter(this.schema, this.dictionaryBlocks, this.recordBlocks, this.metaData, this.option.metadataVersion), false);
      int footerLength = (int)(out.getCurrentPosition() - footerStart);
      if (footerLength <= 0) {
         throw new InvalidArrowFileException("invalid footer");
      } else {
         out.writeIntLittleEndian(footerLength);
         LOGGER.debug("Footer starts at {}, length: {}", footerStart, footerLength);
         ArrowMagic.writeMagic(out, false);
         LOGGER.debug("magic written, now at {}", out.getCurrentPosition());
      }
   }

   protected void ensureDictionariesWritten(DictionaryProvider provider, Set dictionaryIdsUsed) throws IOException {
      if (!this.dictionariesWritten) {
         this.dictionariesWritten = true;

         for(long id : dictionaryIdsUsed) {
            Dictionary dictionary = provider.lookup(id);
            this.writeDictionaryBatch(dictionary);
         }

      }
   }

   @VisibleForTesting
   public List getRecordBlocks() {
      return this.recordBlocks;
   }

   @VisibleForTesting
   public List getDictionaryBlocks() {
      return this.dictionaryBlocks;
   }
}

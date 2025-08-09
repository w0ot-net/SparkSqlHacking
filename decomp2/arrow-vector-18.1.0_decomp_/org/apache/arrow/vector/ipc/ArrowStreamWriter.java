package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.arrow.util.AutoCloseables;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.compare.VectorEqualsVisitor;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.compression.CompressionUtil;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.ipc.message.IpcOption;

public class ArrowStreamWriter extends ArrowWriter {
   private final Map previousDictionaries;

   public ArrowStreamWriter(VectorSchemaRoot root, DictionaryProvider provider, OutputStream out) {
      this(root, provider, Channels.newChannel(out));
   }

   public ArrowStreamWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out) {
      this(root, provider, out, IpcOption.DEFAULT);
   }

   public ArrowStreamWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, IpcOption option) {
      super(root, provider, out, option);
      this.previousDictionaries = new HashMap();
   }

   public ArrowStreamWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, IpcOption option, CompressionCodec.Factory compressionFactory, CompressionUtil.CodecType codecType) {
      this(root, provider, out, option, compressionFactory, codecType, Optional.empty());
   }

   public ArrowStreamWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, IpcOption option, CompressionCodec.Factory compressionFactory, CompressionUtil.CodecType codecType, Optional compressionLevel) {
      super(root, provider, out, option, compressionFactory, codecType, compressionLevel);
      this.previousDictionaries = new HashMap();
   }

   public static void writeEndOfStream(WriteChannel out, IpcOption option) throws IOException {
      if (!option.write_legacy_ipc_format) {
         out.writeIntLittleEndian(-1);
      }

      out.writeIntLittleEndian(0);
   }

   protected void endInternal(WriteChannel out) throws IOException {
      writeEndOfStream(out, this.option);
   }

   protected void ensureDictionariesWritten(DictionaryProvider provider, Set dictionaryIdsUsed) throws IOException {
      for(long id : dictionaryIdsUsed) {
         Dictionary dictionary = provider.lookup(id);
         FieldVector vector = dictionary.getVector();
         if (!this.previousDictionaries.containsKey(id) || !VectorEqualsVisitor.vectorEquals(vector, (ValueVector)this.previousDictionaries.get(id))) {
            this.writeDictionaryBatch(dictionary);
            if (this.previousDictionaries.containsKey(id)) {
               ((FieldVector)this.previousDictionaries.get(id)).close();
            }

            this.previousDictionaries.put(id, copyVector(vector));
         }
      }

   }

   public void close() {
      super.close();

      try {
         AutoCloseables.close(this.previousDictionaries.values());
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   private static FieldVector copyVector(FieldVector source) {
      FieldVector copy = source.getField().createVector(source.getAllocator());
      copy.allocateNew();

      for(int i = 0; i < source.getValueCount(); ++i) {
         copy.copyFromSafe(i, i, source);
      }

      copy.setValueCount(source.getValueCount());
      return copy;
   }
}

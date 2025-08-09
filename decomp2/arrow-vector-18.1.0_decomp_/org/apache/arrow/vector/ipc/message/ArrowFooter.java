package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.arrow.flatbuf.Block;
import org.apache.arrow.flatbuf.Footer;
import org.apache.arrow.flatbuf.KeyValue;
import org.apache.arrow.vector.types.MetadataVersion;
import org.apache.arrow.vector.types.pojo.Schema;

public class ArrowFooter implements FBSerializable {
   private final Schema schema;
   private final List dictionaries;
   private final List recordBatches;
   private final Map metaData;
   private final MetadataVersion metadataVersion;

   public ArrowFooter(Schema schema, List dictionaries, List recordBatches) {
      this(schema, dictionaries, recordBatches, (Map)null);
   }

   public ArrowFooter(Schema schema, List dictionaries, List recordBatches, Map metaData) {
      this(schema, dictionaries, recordBatches, metaData, MetadataVersion.DEFAULT);
   }

   public ArrowFooter(Schema schema, List dictionaries, List recordBatches, Map metaData, MetadataVersion metadataVersion) {
      this.schema = schema;
      this.dictionaries = dictionaries;
      this.recordBatches = recordBatches;
      this.metaData = metaData;
      this.metadataVersion = metadataVersion;
   }

   public ArrowFooter(Footer footer) {
      this(Schema.convertSchema(footer.schema()), dictionaries(footer), recordBatches(footer), metaData(footer), MetadataVersion.fromFlatbufID(footer.version()));
   }

   private static List recordBatches(Footer footer) {
      List<ArrowBlock> recordBatches = new ArrayList();
      Block tempBlock = new Block();
      int recordBatchesLength = footer.recordBatchesLength();

      for(int i = 0; i < recordBatchesLength; ++i) {
         Block block = footer.recordBatches(tempBlock, i);
         recordBatches.add(new ArrowBlock(block.offset(), block.metaDataLength(), block.bodyLength()));
      }

      return recordBatches;
   }

   private static List dictionaries(Footer footer) {
      List<ArrowBlock> dictionaries = new ArrayList();
      Block tempBlock = new Block();
      int dictionariesLength = footer.dictionariesLength();

      for(int i = 0; i < dictionariesLength; ++i) {
         Block block = footer.dictionaries(tempBlock, i);
         dictionaries.add(new ArrowBlock(block.offset(), block.metaDataLength(), block.bodyLength()));
      }

      return dictionaries;
   }

   private static Map metaData(Footer footer) {
      Map<String, String> metaData = new HashMap();
      int metaDataLength = footer.customMetadataLength();

      for(int i = 0; i < metaDataLength; ++i) {
         KeyValue kv = footer.customMetadata(i);
         metaData.put(kv.key(), kv.value());
      }

      return metaData;
   }

   public Schema getSchema() {
      return this.schema;
   }

   public List getDictionaries() {
      return this.dictionaries;
   }

   public List getRecordBatches() {
      return this.recordBatches;
   }

   public Map getMetaData() {
      return this.metaData;
   }

   public MetadataVersion getMetadataVersion() {
      return this.metadataVersion;
   }

   public int writeTo(FlatBufferBuilder builder) {
      int schemaIndex = this.schema.getSchema(builder);
      Footer.startDictionariesVector(builder, this.dictionaries.size());
      int dicsOffset = FBSerializables.writeAllStructsToVector(builder, this.dictionaries);
      Footer.startRecordBatchesVector(builder, this.recordBatches.size());
      int rbsOffset = FBSerializables.writeAllStructsToVector(builder, this.recordBatches);
      int metaDataOffset = 0;
      if (this.metaData != null) {
         metaDataOffset = FBSerializables.writeKeyValues(builder, this.metaData);
      }

      Footer.startFooter(builder);
      Footer.addSchema(builder, schemaIndex);
      Footer.addDictionaries(builder, dicsOffset);
      Footer.addRecordBatches(builder, rbsOffset);
      Footer.addCustomMetadata(builder, metaDataOffset);
      Footer.addVersion(builder, this.metadataVersion.toFlatbufID());
      return Footer.endFooter(builder);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.dictionaries == null ? 0 : this.dictionaries.hashCode());
      result = 31 * result + (this.recordBatches == null ? 0 : this.recordBatches.hashCode());
      result = 31 * result + (this.schema == null ? 0 : this.schema.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ArrowFooter other = (ArrowFooter)obj;
         if (this.dictionaries == null) {
            if (other.dictionaries != null) {
               return false;
            }
         } else if (!this.dictionaries.equals(other.dictionaries)) {
            return false;
         }

         if (this.recordBatches == null) {
            if (other.recordBatches != null) {
               return false;
            }
         } else if (!this.recordBatches.equals(other.recordBatches)) {
            return false;
         }

         if (this.schema == null) {
            if (other.schema != null) {
               return false;
            }
         } else if (!this.schema.equals(other.schema)) {
            return false;
         }

         return true;
      }
   }
}

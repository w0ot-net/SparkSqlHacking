package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.OrcFile;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;

public interface TreeWriter {
   long estimateMemory();

   long getRawDataSize();

   void prepareStripe(int var1);

   void writeRootBatch(VectorizedRowBatch var1, int var2, int var3) throws IOException;

   void writeBatch(ColumnVector var1, int var2, int var3) throws IOException;

   void createRowIndexEntry() throws IOException;

   void flushStreams() throws IOException;

   void writeStripe(int var1) throws IOException;

   void addStripeStatistics(StripeStatistics[] var1) throws IOException;

   void writeFileStatistics() throws IOException;

   void getCurrentStatistics(ColumnStatistics[] var1);

   public static class Factory {
      public static TreeWriter create(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext streamFactory) throws IOException {
         if (encryption == null) {
            encryption = streamFactory.getEncryption(schema.getId());
            if (encryption != null) {
               return new EncryptionTreeWriter(schema, encryption, streamFactory);
            }
         }

         return createSubtree(schema, encryption, streamFactory);
      }

      static TreeWriter createSubtree(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext streamFactory) throws IOException {
         OrcFile.Version version = streamFactory.getVersion();
         switch (schema.getCategory()) {
            case BOOLEAN:
               return new BooleanTreeWriter(schema, encryption, streamFactory);
            case BYTE:
               return new ByteTreeWriter(schema, encryption, streamFactory);
            case SHORT:
            case INT:
            case LONG:
               return new IntegerTreeWriter(schema, encryption, streamFactory);
            case FLOAT:
               return new FloatTreeWriter(schema, encryption, streamFactory);
            case DOUBLE:
               return new DoubleTreeWriter(schema, encryption, streamFactory);
            case STRING:
               return new StringTreeWriter(schema, encryption, streamFactory);
            case CHAR:
               return new CharTreeWriter(schema, encryption, streamFactory);
            case VARCHAR:
               return new VarcharTreeWriter(schema, encryption, streamFactory);
            case BINARY:
               return new BinaryTreeWriter(schema, encryption, streamFactory);
            case TIMESTAMP:
               return new TimestampTreeWriter(schema, encryption, streamFactory, false);
            case TIMESTAMP_INSTANT:
               return new TimestampTreeWriter(schema, encryption, streamFactory, true);
            case DATE:
               return new DateTreeWriter(schema, encryption, streamFactory);
            case DECIMAL:
               if (version == OrcFile.Version.UNSTABLE_PRE_2_0 && schema.getPrecision() <= 18) {
                  return new Decimal64TreeWriter(schema, encryption, streamFactory);
               }

               return new DecimalTreeWriter(schema, encryption, streamFactory);
            case STRUCT:
               return new StructTreeWriter(schema, encryption, streamFactory);
            case MAP:
               return new MapTreeWriter(schema, encryption, streamFactory);
            case LIST:
               return new ListTreeWriter(schema, encryption, streamFactory);
            case UNION:
               return new UnionTreeWriter(schema, encryption, streamFactory);
            default:
               throw new IllegalArgumentException("Bad category: " + String.valueOf(schema.getCategory()));
         }
      }
   }
}

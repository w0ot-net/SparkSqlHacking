package org.apache.parquet.hadoop;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.ByteBufferReleaser;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.page.DataPage;
import org.apache.parquet.column.page.DataPageV1;
import org.apache.parquet.column.page.DataPageV2;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.DictionaryPageReadStore;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.column.page.PageReader;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.internal.filter2.columnindex.RowRanges;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ColumnChunkPageReadStore implements PageReadStore, DictionaryPageReadStore {
   private static final Logger LOG = LoggerFactory.getLogger(ColumnChunkPageReadStore.class);
   private final Map readers;
   private final long rowCount;
   private final long rowIndexOffset;
   private final RowRanges rowRanges;
   private ByteBufferAllocator allocator;
   private ByteBufferReleaser releaser;

   public ColumnChunkPageReadStore(long rowCount) {
      this(rowCount, -1L);
   }

   ColumnChunkPageReadStore(RowRanges rowRanges) {
      this(rowRanges, -1L);
   }

   ColumnChunkPageReadStore(long rowCount, long rowIndexOffset) {
      this.readers = new HashMap();
      this.rowCount = rowCount;
      this.rowIndexOffset = rowIndexOffset;
      this.rowRanges = null;
   }

   ColumnChunkPageReadStore(RowRanges rowRanges, long rowIndexOffset) {
      this.readers = new HashMap();
      this.rowRanges = rowRanges;
      this.rowIndexOffset = rowIndexOffset;
      this.rowCount = rowRanges.rowCount();
   }

   public long getRowCount() {
      return this.rowCount;
   }

   public Optional getRowIndexOffset() {
      return this.rowIndexOffset < 0L ? Optional.empty() : Optional.of(this.rowIndexOffset);
   }

   public PageReader getPageReader(ColumnDescriptor path) {
      PageReader pageReader = (PageReader)this.readers.get(path);
      if (pageReader == null) {
         throw new IllegalArgumentException(path + " is not in the store: " + this.readers.keySet() + " " + this.rowCount);
      } else {
         return pageReader;
      }
   }

   public DictionaryPage readDictionaryPage(ColumnDescriptor descriptor) {
      return ((ColumnChunkPageReader)this.readers.get(descriptor)).readDictionaryPage();
   }

   public Optional getRowIndexes() {
      return this.rowRanges == null ? Optional.empty() : Optional.of(this.rowRanges.iterator());
   }

   void addColumn(ColumnDescriptor path, ColumnChunkPageReader reader) {
      if (this.readers.put(path, reader) != null) {
         throw new RuntimeException(path + " was added twice");
      }
   }

   void setReleaser(ByteBufferReleaser releaser) {
      this.releaser = releaser;
   }

   public void close() {
      for(ColumnChunkPageReader reader : this.readers.values()) {
         reader.releaseBuffers();
      }

      this.releaser.close();
   }

   static final class ColumnChunkPageReader implements PageReader {
      private final CompressionCodecFactory.BytesInputDecompressor decompressor;
      private final long valueCount;
      private final Queue compressedPages;
      private final DictionaryPage compressedDictionaryPage;
      private final OffsetIndex offsetIndex;
      private final long rowCount;
      private final ParquetReadOptions options;
      private int pageIndex = 0;
      private final BlockCipher.Decryptor blockDecryptor;
      private final byte[] dataPageAAD;
      private final byte[] dictionaryPageAAD;
      private final ByteBufferReleaser releaser;

      ColumnChunkPageReader(CompressionCodecFactory.BytesInputDecompressor decompressor, List compressedPages, DictionaryPage compressedDictionaryPage, OffsetIndex offsetIndex, long rowCount, BlockCipher.Decryptor blockDecryptor, byte[] fileAAD, int rowGroupOrdinal, int columnOrdinal, ParquetReadOptions options) {
         this.decompressor = decompressor;
         this.compressedPages = new ArrayDeque(compressedPages);
         this.compressedDictionaryPage = compressedDictionaryPage;
         long count = 0L;

         for(DataPage p : compressedPages) {
            count += (long)p.getValueCount();
         }

         this.valueCount = count;
         this.offsetIndex = offsetIndex;
         this.rowCount = rowCount;
         this.options = options;
         this.releaser = new ByteBufferReleaser(options.getAllocator());
         this.blockDecryptor = blockDecryptor;
         if (null != blockDecryptor) {
            this.dataPageAAD = AesCipher.createModuleAAD(fileAAD, ModuleCipherFactory.ModuleType.DataPage, rowGroupOrdinal, columnOrdinal, 0);
            this.dictionaryPageAAD = AesCipher.createModuleAAD(fileAAD, ModuleCipherFactory.ModuleType.DictionaryPage, rowGroupOrdinal, columnOrdinal, -1);
         } else {
            this.dataPageAAD = null;
            this.dictionaryPageAAD = null;
         }

      }

      private int getPageOrdinal(int currentPageIndex) {
         return null == this.offsetIndex ? currentPageIndex : this.offsetIndex.getPageOrdinal(currentPageIndex);
      }

      public long getTotalValueCount() {
         return this.valueCount;
      }

      public DataPage readPage() {
         DataPage compressedPage = (DataPage)this.compressedPages.poll();
         if (compressedPage == null) {
            return null;
         } else {
            final int currentPageIndex = this.pageIndex++;
            if (null != this.blockDecryptor) {
               AesCipher.quickUpdatePageAAD(this.dataPageAAD, this.getPageOrdinal(currentPageIndex));
            }

            return (DataPage)compressedPage.accept(new DataPage.Visitor() {
               public DataPage visit(DataPageV1 dataPageV1) {
                  try {
                     BytesInput bytes = dataPageV1.getBytes();
                     BytesInput decompressed;
                     if (ColumnChunkPageReader.this.options.getAllocator().isDirect() && ColumnChunkPageReader.this.options.useOffHeapDecryptBuffer()) {
                        ByteBuffer byteBuffer = bytes.toByteBuffer(ColumnChunkPageReader.this.releaser);
                        if (!byteBuffer.isDirect()) {
                           throw new ParquetDecodingException("Expected a direct buffer");
                        }

                        if (ColumnChunkPageReader.this.blockDecryptor != null) {
                           byteBuffer = ColumnChunkPageReader.this.blockDecryptor.decrypt(byteBuffer, ColumnChunkPageReader.this.dataPageAAD);
                        }

                        long compressedSize = (long)byteBuffer.limit();
                        ByteBuffer decompressedBuffer = ColumnChunkPageReader.this.options.getAllocator().allocate(dataPageV1.getUncompressedSize());
                        ColumnChunkPageReader.this.releaser.releaseLater(decompressedBuffer);
                        long start = System.nanoTime();
                        ColumnChunkPageReader.this.decompressor.decompress(byteBuffer, (int)compressedSize, decompressedBuffer, dataPageV1.getUncompressedSize());
                        ColumnChunkPageReader.this.setDecompressMetrics(bytes, start);
                        decompressedBuffer.flip();
                        decompressed = BytesInput.from(new ByteBuffer[]{decompressedBuffer});
                     } else {
                        if (null != ColumnChunkPageReader.this.blockDecryptor) {
                           bytes = BytesInput.from(ColumnChunkPageReader.this.blockDecryptor.decrypt(bytes.toByteArray(), ColumnChunkPageReader.this.dataPageAAD));
                        }

                        long start = System.nanoTime();
                        decompressed = ColumnChunkPageReader.this.decompressor.decompress(bytes, dataPageV1.getUncompressedSize());
                        ColumnChunkPageReader.this.setDecompressMetrics(bytes, start);
                     }

                     DataPageV1 decompressedPage;
                     if (ColumnChunkPageReader.this.offsetIndex == null) {
                        decompressedPage = new DataPageV1(decompressed, dataPageV1.getValueCount(), dataPageV1.getUncompressedSize(), dataPageV1.getStatistics(), dataPageV1.getRlEncoding(), dataPageV1.getDlEncoding(), dataPageV1.getValueEncoding());
                     } else {
                        long firstRowIndex = ColumnChunkPageReader.this.offsetIndex.getFirstRowIndex(currentPageIndex);
                        decompressedPage = new DataPageV1(decompressed, dataPageV1.getValueCount(), dataPageV1.getUncompressedSize(), firstRowIndex, Math.toIntExact(ColumnChunkPageReader.this.offsetIndex.getLastRowIndex(currentPageIndex, ColumnChunkPageReader.this.rowCount) - firstRowIndex + 1L), dataPageV1.getStatistics(), dataPageV1.getRlEncoding(), dataPageV1.getDlEncoding(), dataPageV1.getValueEncoding());
                     }

                     if (dataPageV1.getCrc().isPresent()) {
                        decompressedPage.setCrc(dataPageV1.getCrc().getAsInt());
                     }

                     return decompressedPage;
                  } catch (IOException e) {
                     throw new ParquetDecodingException("could not decompress page", e);
                  }
               }

               public DataPage visit(DataPageV2 dataPageV2) {
                  if (!dataPageV2.isCompressed() && ColumnChunkPageReader.this.offsetIndex == null && null == ColumnChunkPageReader.this.blockDecryptor) {
                     return dataPageV2;
                  } else {
                     BytesInput pageBytes = dataPageV2.getData();

                     try {
                        if (ColumnChunkPageReader.this.options.getAllocator().isDirect() && ColumnChunkPageReader.this.options.useOffHeapDecryptBuffer()) {
                           ByteBuffer byteBuffer = pageBytes.toByteBuffer(ColumnChunkPageReader.this.releaser);
                           if (!byteBuffer.isDirect()) {
                              throw new ParquetDecodingException("Expected a direct buffer");
                           }

                           if (ColumnChunkPageReader.this.blockDecryptor != null) {
                              byteBuffer = ColumnChunkPageReader.this.blockDecryptor.decrypt(byteBuffer, ColumnChunkPageReader.this.dataPageAAD);
                           }

                           long compressedSize = (long)byteBuffer.limit();
                           if (dataPageV2.isCompressed()) {
                              int uncompressedSize = Math.toIntExact((long)dataPageV2.getUncompressedSize() - dataPageV2.getDefinitionLevels().size() - dataPageV2.getRepetitionLevels().size());
                              ByteBuffer decompressedBuffer = ColumnChunkPageReader.this.options.getAllocator().allocate(uncompressedSize);
                              ColumnChunkPageReader.this.releaser.releaseLater(decompressedBuffer);
                              long start = System.nanoTime();
                              ColumnChunkPageReader.this.decompressor.decompress(byteBuffer, (int)compressedSize, decompressedBuffer, uncompressedSize);
                              ColumnChunkPageReader.this.setDecompressMetrics(pageBytes, start);
                              decompressedBuffer.flip();
                              pageBytes = BytesInput.from(new ByteBuffer[]{decompressedBuffer});
                           } else {
                              pageBytes = BytesInput.from(new ByteBuffer[]{byteBuffer});
                           }
                        } else {
                           if (null != ColumnChunkPageReader.this.blockDecryptor) {
                              pageBytes = BytesInput.from(ColumnChunkPageReader.this.blockDecryptor.decrypt(pageBytes.toByteArray(), ColumnChunkPageReader.this.dataPageAAD));
                           }

                           if (dataPageV2.isCompressed()) {
                              int uncompressedSize = Math.toIntExact((long)dataPageV2.getUncompressedSize() - dataPageV2.getDefinitionLevels().size() - dataPageV2.getRepetitionLevels().size());
                              long start = System.nanoTime();
                              pageBytes = ColumnChunkPageReader.this.decompressor.decompress(pageBytes, uncompressedSize);
                              ColumnChunkPageReader.this.setDecompressMetrics(pageBytes, start);
                           }
                        }
                     } catch (IOException e) {
                        throw new ParquetDecodingException("could not decompress page", e);
                     }

                     DataPageV2 decompressedPage;
                     if (ColumnChunkPageReader.this.offsetIndex == null) {
                        decompressedPage = DataPageV2.uncompressed(dataPageV2.getRowCount(), dataPageV2.getNullCount(), dataPageV2.getValueCount(), dataPageV2.getRepetitionLevels(), dataPageV2.getDefinitionLevels(), dataPageV2.getDataEncoding(), pageBytes, dataPageV2.getStatistics());
                     } else {
                        decompressedPage = DataPageV2.uncompressed(dataPageV2.getRowCount(), dataPageV2.getNullCount(), dataPageV2.getValueCount(), ColumnChunkPageReader.this.offsetIndex.getFirstRowIndex(currentPageIndex), dataPageV2.getRepetitionLevels(), dataPageV2.getDefinitionLevels(), dataPageV2.getDataEncoding(), pageBytes, dataPageV2.getStatistics());
                     }

                     if (dataPageV2.getCrc().isPresent()) {
                        decompressedPage.setCrc(dataPageV2.getCrc().getAsInt());
                     }

                     return decompressedPage;
                  }
               }
            });
         }
      }

      private void setDecompressMetrics(BytesInput bytes, long start) {
         ParquetMetricsCallback metricsCallback = this.options.getMetricsCallback();
         if (metricsCallback != null) {
            long time = Math.max(System.nanoTime() - start, 0L);
            long len = bytes.size();
            double throughput = (double)len / (double)time * (double)1.0E9F / (double)1048576.0F;
            ColumnChunkPageReadStore.LOG.debug("Decompress block: Length: {} MB, Time: {} msecs, throughput: {} MB/s", new Object[]{len / 1048576L, time / 1000000L, throughput});
            metricsCallback.setDuration(ParquetFileReaderMetrics.DecompressTime.name(), time);
            metricsCallback.setValueLong(ParquetFileReaderMetrics.DecompressSize.name(), len);
            metricsCallback.setValueDouble(ParquetFileReaderMetrics.DecompressThroughput.name(), throughput);
         }

      }

      public DictionaryPage readDictionaryPage() {
         if (this.compressedDictionaryPage == null) {
            return null;
         } else {
            try {
               BytesInput bytes = this.compressedDictionaryPage.getBytes();
               if (null != this.blockDecryptor) {
                  bytes = BytesInput.from(this.blockDecryptor.decrypt(bytes.toByteArray(), this.dictionaryPageAAD));
               }

               long start = System.nanoTime();
               this.setDecompressMetrics(bytes, start);
               DictionaryPage decompressedPage = new DictionaryPage(this.decompressor.decompress(bytes, this.compressedDictionaryPage.getUncompressedSize()), this.compressedDictionaryPage.getDictionarySize(), this.compressedDictionaryPage.getEncoding());
               if (this.compressedDictionaryPage.getCrc().isPresent()) {
                  decompressedPage.setCrc(this.compressedDictionaryPage.getCrc().getAsInt());
               }

               return decompressedPage;
            } catch (IOException e) {
               throw new ParquetDecodingException("Could not decompress dictionary page", e);
            }
         }
      }

      private void releaseBuffers() {
         this.releaser.close();
      }
   }
}

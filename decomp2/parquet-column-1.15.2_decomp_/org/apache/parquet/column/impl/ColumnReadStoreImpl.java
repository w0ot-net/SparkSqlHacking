package org.apache.parquet.column.impl;

import java.util.Optional;
import java.util.PrimitiveIterator;
import org.apache.parquet.VersionParser;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ColumnReadStore;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.column.page.PageReader;
import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;

public class ColumnReadStoreImpl implements ColumnReadStore {
   private final PageReadStore pageReadStore;
   private final GroupConverter recordConverter;
   private final MessageType schema;
   private final VersionParser.ParsedVersion writerVersion;

   public ColumnReadStoreImpl(PageReadStore pageReadStore, GroupConverter recordConverter, MessageType schema, String createdBy) {
      this.pageReadStore = pageReadStore;
      this.recordConverter = recordConverter;
      this.schema = schema;

      VersionParser.ParsedVersion version;
      try {
         version = VersionParser.parse(createdBy);
      } catch (VersionParser.VersionParseException | RuntimeException var7) {
         version = null;
      }

      this.writerVersion = version;
   }

   public ColumnReader getColumnReader(ColumnDescriptor path) {
      PrimitiveConverter converter = this.getPrimitiveConverter(path);
      PageReader pageReader = this.pageReadStore.getPageReader(path);
      Optional<PrimitiveIterator.OfLong> rowIndexes = this.pageReadStore.getRowIndexes();
      return (ColumnReader)(rowIndexes.isPresent() ? new SynchronizingColumnReader(path, pageReader, converter, this.writerVersion, (PrimitiveIterator.OfLong)rowIndexes.get()) : new ColumnReaderImpl(path, pageReader, converter, this.writerVersion));
   }

   private ColumnReaderImpl newMemColumnReader(ColumnDescriptor path, PageReader pageReader) {
      PrimitiveConverter converter = this.getPrimitiveConverter(path);
      return new ColumnReaderImpl(path, pageReader, converter, this.writerVersion);
   }

   private PrimitiveConverter getPrimitiveConverter(ColumnDescriptor path) {
      Type currentType = this.schema;
      Converter currentConverter = this.recordConverter;

      for(String fieldName : path.getPath()) {
         GroupType groupType = currentType.asGroupType();
         int fieldIndex = groupType.getFieldIndex(fieldName);
         currentType = groupType.getType(fieldName);
         currentConverter = currentConverter.asGroupConverter().getConverter(fieldIndex);
      }

      PrimitiveConverter converter = currentConverter.asPrimitiveConverter();
      return converter;
   }
}

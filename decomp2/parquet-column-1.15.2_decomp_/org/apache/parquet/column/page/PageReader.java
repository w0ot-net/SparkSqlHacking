package org.apache.parquet.column.page;

public interface PageReader {
   DictionaryPage readDictionaryPage();

   long getTotalValueCount();

   DataPage readPage();
}

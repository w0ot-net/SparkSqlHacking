package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;

class IndexInfoImpl {
   private IndexRowGenerator irg;
   private long conglomerateNumber;
   private final CatalogRowFactory crf;
   private final int indexNumber;

   IndexInfoImpl(int var1, CatalogRowFactory var2) {
      this.crf = var2;
      this.indexNumber = var1;
      this.conglomerateNumber = -1L;
   }

   long getConglomerateNumber() {
      return this.conglomerateNumber;
   }

   void setConglomerateNumber(long var1) {
      this.conglomerateNumber = var1;
   }

   String getIndexName() {
      return this.crf.getIndexName(this.indexNumber);
   }

   int getColumnCount() {
      return this.crf.getIndexColumnCount(this.indexNumber);
   }

   IndexRowGenerator getIndexRowGenerator() {
      return this.irg;
   }

   void setIndexRowGenerator(IndexRowGenerator var1) {
      this.irg = var1;
   }

   int getBaseColumnPosition(int var1) {
      return this.crf.getIndexColumnPositions(this.indexNumber)[var1];
   }

   boolean isIndexUnique() {
      return this.crf.isIndexUnique(this.indexNumber);
   }
}

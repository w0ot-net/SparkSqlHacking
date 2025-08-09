package org.apache.arrow.vector.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.util.TransferPair;

public class Table extends BaseTable implements Iterable {
   public Table(Iterable vectors) {
      this((List)StreamSupport.stream(vectors.spliterator(), false).collect(Collectors.toList()));
   }

   public static Table of(FieldVector... vectors) {
      return new Table((List)Arrays.stream(vectors).collect(Collectors.toList()));
   }

   public Table(List fieldVectors) {
      this(fieldVectors, fieldVectors.size() == 0 ? 0 : ((FieldVector)fieldVectors.get(0)).getValueCount());
   }

   public Table(List fieldVectors, int rowCount) {
      super(fieldVectors, rowCount, (DictionaryProvider)null);
   }

   public Table(List fieldVectors, int rowCount, DictionaryProvider provider) {
      super(fieldVectors, rowCount, provider);
   }

   public Table(VectorSchemaRoot vsr) {
      this(vsr.getFieldVectors(), vsr.getRowCount());
      vsr.clear();
   }

   public Table copy() {
      List<FieldVector> vectorCopies = new ArrayList();

      for(int i = 0; i < this.getVectorCount(); ++i) {
         vectorCopies.add(this.getVectorCopy(i));
      }

      DictionaryProvider providerCopy = null;
      if (this.dictionaryProvider != null) {
         Set<Long> ids = this.dictionaryProvider.getDictionaryIds();
         Dictionary[] dictionaryCopies = new Dictionary[ids.size()];
         int i = 0;

         for(Long id : ids) {
            Dictionary src = this.dictionaryProvider.lookup(id);
            FieldVector srcVector = src.getVector();
            FieldVector destVector = srcVector.getField().createVector(srcVector.getAllocator());
            destVector.copyFromSafe(0, srcVector.getValueCount(), srcVector);
            DictionaryEncoding srcEncoding = src.getEncoding();
            Dictionary dest = new Dictionary(destVector, new DictionaryEncoding(srcEncoding.getId(), srcEncoding.isOrdered(), srcEncoding.getIndexType()));
            dictionaryCopies[i] = dest;
            ++i;
         }

         providerCopy = new DictionaryProvider.MapDictionaryProvider(dictionaryCopies);
      }

      return new Table(vectorCopies, (int)this.getRowCount(), providerCopy);
   }

   public Table addVector(int index, FieldVector vector) {
      return new Table(this.insertVector(index, vector));
   }

   public Table removeVector(int index) {
      return new Table(this.extractVector(index));
   }

   public Table slice(int index) {
      return this.slice(index, this.rowCount - index);
   }

   public Table slice(int index, int length) {
      Preconditions.checkArgument(index >= 0, "expecting non-negative index");
      Preconditions.checkArgument(length >= 0, "expecting non-negative length");
      Preconditions.checkArgument(index + length <= this.rowCount, "index + length should <= rowCount");
      if (index == 0 && length == this.rowCount) {
         return this;
      } else {
         List<FieldVector> sliceVectors = (List)this.fieldVectors.stream().map((v) -> {
            TransferPair transferPair = v.getTransferPair(v.getAllocator());
            transferPair.splitAndTransfer(index, length);
            return (FieldVector)transferPair.getTo();
         }).collect(Collectors.toList());
         return new Table(sliceVectors);
      }
   }

   public Iterator iterator() {
      return new Iterator() {
         private final Row row = new Row(Table.this);

         public Row next() {
            this.row.next();
            return this.row;
         }

         public boolean hasNext() {
            return this.row.hasNext();
         }
      };
   }
}

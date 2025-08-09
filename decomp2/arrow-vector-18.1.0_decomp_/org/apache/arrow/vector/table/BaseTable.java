package org.apache.arrow.vector.table;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.arrow.util.AutoCloseables;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryEncoder;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.TransferPair;

public abstract class BaseTable implements AutoCloseable {
   protected final List fieldVectors;
   protected DictionaryProvider dictionaryProvider;
   protected final Map fieldVectorsMap = new LinkedHashMap();
   protected Schema schema;
   protected int rowCount;

   public BaseTable(List fieldVectors, int rowCount, DictionaryProvider provider) {
      this.dictionaryProvider = provider;
      this.rowCount = rowCount;
      this.fieldVectors = new ArrayList();
      List<Field> fields = new ArrayList();

      for(FieldVector fv : fieldVectors) {
         TransferPair transferPair = fv.getTransferPair(fv.getAllocator());
         transferPair.transfer();
         FieldVector newVector = (FieldVector)transferPair.getTo();
         newVector.setValueCount(rowCount);
         Field newField = newVector.getField();
         this.fieldVectors.add(newVector);
         fields.add(newField);
         this.fieldVectorsMap.put(newField, newVector);
      }

      this.schema = new Schema(fields);
   }

   BaseTable() {
      this.fieldVectors = new ArrayList();
   }

   public FieldReader getReader(String name) {
      for(Map.Entry entry : this.fieldVectorsMap.entrySet()) {
         if (((Field)entry.getKey()).getName().equals(name)) {
            return ((FieldVector)entry.getValue()).getReader();
         }
      }

      return null;
   }

   public FieldReader getReader(Field field) {
      return ((FieldVector)this.fieldVectorsMap.get(field)).getReader();
   }

   public FieldReader getReader(int index) {
      Preconditions.checkArgument(index >= 0 && index < this.fieldVectors.size());
      return ((FieldVector)this.fieldVectors.get(index)).getReader();
   }

   public Schema getSchema() {
      return this.schema;
   }

   public Field getField(String fieldName) {
      return this.getSchema().findField(fieldName);
   }

   List insertVector(int index, FieldVector vector) {
      Preconditions.checkNotNull(vector);
      Preconditions.checkArgument(index >= 0 && index <= this.fieldVectors.size());
      List<FieldVector> newVectors = new ArrayList();
      if (index == this.fieldVectors.size()) {
         newVectors.addAll(this.fieldVectors);
         newVectors.add(vector);
      } else {
         for(int i = 0; i < this.fieldVectors.size(); ++i) {
            if (i == index) {
               newVectors.add(vector);
            }

            newVectors.add((FieldVector)this.fieldVectors.get(i));
         }
      }

      return newVectors;
   }

   List extractVector(int index) {
      Preconditions.checkArgument(index >= 0 && index < this.fieldVectors.size());
      List<FieldVector> newVectors = new ArrayList();

      for(int i = 0; i < this.fieldVectors.size(); ++i) {
         if (i != index) {
            newVectors.add((FieldVector)this.fieldVectors.get(i));
         }
      }

      return newVectors;
   }

   public int getVectorCount() {
      return this.fieldVectors.size();
   }

   void clear() {
      this.close();
      this.rowCount = 0;
   }

   public void close() {
      try {
         AutoCloseables.close(this.fieldVectors);
      } catch (RuntimeException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new RuntimeException(ex);
      }
   }

   public long getRowCount() {
      return (long)this.rowCount;
   }

   public VectorSchemaRoot toVectorSchemaRoot() {
      VectorSchemaRoot vsr = new VectorSchemaRoot((Iterable)this.fieldVectors.stream().map((v) -> {
         TransferPair transferPair = v.getTransferPair(v.getAllocator());
         transferPair.transfer();
         return (FieldVector)transferPair.getTo();
      }).collect(Collectors.toList()));
      this.clear();
      return vsr;
   }

   FieldVector getVector(String columnName) {
      for(Map.Entry entry : this.fieldVectorsMap.entrySet()) {
         if (((Field)entry.getKey()).getName().equals(columnName)) {
            return (FieldVector)entry.getValue();
         }
      }

      throw new IllegalArgumentException(String.format("No vector named '%s' is present in the table", columnName));
   }

   FieldVector getVector(int columnIndex) {
      return (FieldVector)this.fieldVectors.get(columnIndex);
   }

   public FieldVector getVectorCopy(String columnName) {
      for(Map.Entry entry : this.fieldVectorsMap.entrySet()) {
         if (((Field)entry.getKey()).getName().equals(columnName)) {
            FieldVector source = (FieldVector)entry.getValue();
            FieldVector copy = source.getField().createVector(source.getAllocator());
            copy.allocateNew();

            for(int i = 0; i < source.getValueCount(); ++i) {
               copy.copyFromSafe(i, i, source);
            }

            copy.setValueCount(source.getValueCount());
            return copy;
         }
      }

      throw new IllegalStateException(String.format("No vector named '%s' is present in the table", columnName));
   }

   public FieldVector getVectorCopy(int columnIndex) {
      FieldVector source = (FieldVector)this.fieldVectors.get(columnIndex);
      FieldVector copy = source.getField().createVector(source.getAllocator());
      copy.allocateNew();

      for(int i = 0; i < source.getValueCount(); ++i) {
         copy.copyFromSafe(i, i, source);
      }

      copy.setValueCount(source.getValueCount());
      return copy;
   }

   public Row immutableRow() {
      return new Row(this);
   }

   public String contentToTSVString() {
      StringBuilder sb = new StringBuilder();
      List<Object> row = new ArrayList(this.schema.getFields().size());

      for(Field field : this.schema.getFields()) {
         row.add(field.getName());
      }

      this.printRow(sb, row);

      for(int i = 0; i < this.rowCount; ++i) {
         row.clear();

         for(FieldVector v : this.fieldVectors) {
            row.add(v.getObject(i));
         }

         this.printRow(sb, row);
      }

      return sb.toString();
   }

   private void printRow(StringBuilder sb, List row) {
      boolean first = true;

      for(Object v : row) {
         if (first) {
            first = false;
         } else {
            sb.append("\t");
         }

         sb.append(v);
      }

      sb.append("\n");
   }

   public boolean isRowDeleted(int rowNumber) {
      return false;
   }

   public DictionaryProvider getDictionaryProvider() {
      return this.dictionaryProvider;
   }

   public ValueVector decode(String vectorName, long dictionaryId) {
      Dictionary dictionary = this.getDictionary(dictionaryId);
      FieldVector vector = this.getVector(vectorName);
      if (vector == null) {
         throw new IllegalArgumentException(String.format("No vector with name '%s' is present in table", vectorName));
      } else {
         DictionaryEncoder decoder = new DictionaryEncoder(dictionary, vector.getAllocator());
         return decoder.decode(vector);
      }
   }

   public ValueVector encode(String vectorName, long dictionaryId) {
      Dictionary dictionary = this.getDictionary(dictionaryId);
      FieldVector vector = this.getVector(vectorName);
      if (vector == null) {
         throw new IllegalArgumentException(String.format("No vector with name '%s' is present in table", vectorName));
      } else {
         DictionaryEncoder decoder = new DictionaryEncoder(dictionary, vector.getAllocator());
         return decoder.encode(vector);
      }
   }

   private Dictionary getDictionary(long dictionaryId) {
      if (this.dictionaryProvider == null) {
         throw new IllegalStateException("No dictionary provider is present in table.");
      } else {
         Dictionary dictionary = this.dictionaryProvider.lookup(dictionaryId);
         if (dictionary == null) {
            throw new IllegalArgumentException("No dictionary with id '%n' exists in the table");
         } else {
            return dictionary;
         }
      }
   }
}

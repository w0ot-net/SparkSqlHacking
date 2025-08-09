package org.apache.arrow.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.AutoCloseables;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compare.ApproxEqualsVisitor;
import org.apache.arrow.vector.compare.Range;
import org.apache.arrow.vector.compare.VectorEqualsVisitor;
import org.apache.arrow.vector.compare.VectorValueEqualizer;
import org.apache.arrow.vector.compare.util.ValueEpsilonEqualizers;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.TransferPair;

public class VectorSchemaRoot implements AutoCloseable {
   private Schema schema;
   private int rowCount;
   private final List fieldVectors;
   private final Map fieldVectorsMap;

   public VectorSchemaRoot(Iterable vectors) {
      this((List)StreamSupport.stream(vectors.spliterator(), false).map((t) -> t.getField()).collect(Collectors.toList()), (List)StreamSupport.stream(vectors.spliterator(), false).collect(Collectors.toList()));
   }

   public VectorSchemaRoot(FieldVector parent) {
      this(parent.getField().getChildren(), parent.getChildrenFromFields(), parent.getValueCount());
   }

   public VectorSchemaRoot(List fields, List fieldVectors) {
      this(new Schema(fields), fieldVectors, fieldVectors.size() == 0 ? 0 : ((FieldVector)fieldVectors.get(0)).getValueCount());
   }

   public VectorSchemaRoot(List fields, List fieldVectors, int rowCount) {
      this(new Schema(fields), fieldVectors, rowCount);
   }

   public VectorSchemaRoot(Schema schema, List fieldVectors, int rowCount) {
      this.fieldVectorsMap = new LinkedHashMap();
      if (schema.getFields().size() != fieldVectors.size()) {
         int var10002 = fieldVectors.size();
         throw new IllegalArgumentException("Fields must match field vectors. Found " + var10002 + " vectors and " + schema.getFields().size() + " fields");
      } else {
         this.schema = schema;
         this.rowCount = rowCount;
         this.fieldVectors = fieldVectors;

         for(int i = 0; i < schema.getFields().size(); ++i) {
            Field field = (Field)schema.getFields().get(i);
            FieldVector vector = (FieldVector)fieldVectors.get(i);
            this.fieldVectorsMap.put(field, vector);
         }

      }
   }

   public static VectorSchemaRoot create(Schema schema, BufferAllocator allocator) {
      List<FieldVector> fieldVectors = new ArrayList(schema.getFields().size());

      for(Field field : schema.getFields()) {
         FieldVector vector = field.createVector(allocator);
         fieldVectors.add(vector);
      }

      if (fieldVectors.size() != schema.getFields().size()) {
         int var10002 = fieldVectors.size();
         throw new IllegalArgumentException("The root vector did not create the right number of children. found " + var10002 + " expected " + schema.getFields().size());
      } else {
         return new VectorSchemaRoot(schema, fieldVectors, 0);
      }
   }

   public static VectorSchemaRoot of(FieldVector... vectors) {
      return new VectorSchemaRoot((Iterable)Arrays.stream(vectors).collect(Collectors.toList()));
   }

   public void allocateNew() {
      for(FieldVector v : this.fieldVectors) {
         v.allocateNew();
      }

      this.rowCount = 0;
   }

   public void clear() {
      for(FieldVector v : this.fieldVectors) {
         v.clear();
      }

      this.rowCount = 0;
   }

   public List getFieldVectors() {
      return Collections.unmodifiableList(this.fieldVectors);
   }

   public FieldVector getVector(String name) {
      for(Map.Entry entry : this.fieldVectorsMap.entrySet()) {
         if (((Field)entry.getKey()).getName().equals(name)) {
            return (FieldVector)entry.getValue();
         }
      }

      return null;
   }

   public FieldVector getVector(Field field) {
      return (FieldVector)this.fieldVectorsMap.get(field);
   }

   public FieldVector getVector(int index) {
      Preconditions.checkArgument(index >= 0 && index < this.fieldVectors.size());
      return (FieldVector)this.fieldVectors.get(index);
   }

   public VectorSchemaRoot addVector(int index, FieldVector vector) {
      Preconditions.checkNotNull(vector);
      Preconditions.checkArgument(index >= 0 && index < this.fieldVectors.size());
      List<FieldVector> newVectors = new ArrayList();

      for(int i = 0; i < this.fieldVectors.size(); ++i) {
         if (i == index) {
            newVectors.add(vector);
         }

         newVectors.add((FieldVector)this.fieldVectors.get(i));
      }

      return new VectorSchemaRoot(newVectors);
   }

   public VectorSchemaRoot removeVector(int index) {
      Preconditions.checkArgument(index >= 0 && index < this.fieldVectors.size());
      List<FieldVector> newVectors = new ArrayList();

      for(int i = 0; i < this.fieldVectors.size(); ++i) {
         if (i != index) {
            newVectors.add((FieldVector)this.fieldVectors.get(i));
         }
      }

      return new VectorSchemaRoot(newVectors);
   }

   public Schema getSchema() {
      return this.schema;
   }

   public int getRowCount() {
      return this.rowCount;
   }

   public void setRowCount(int rowCount) {
      this.rowCount = rowCount;

      for(FieldVector v : this.fieldVectors) {
         v.setValueCount(rowCount);
      }

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

   public boolean syncSchema() {
      List<Field> oldFields = this.schema.getFields();
      List<Field> newFields = (List)this.fieldVectors.stream().map(ValueVector::getField).collect(Collectors.toList());
      if (!oldFields.equals(newFields)) {
         this.schema = new Schema(newFields);
         return true;
      } else {
         return false;
      }
   }

   public VectorSchemaRoot slice(int index) {
      return this.slice(index, this.rowCount - index);
   }

   public VectorSchemaRoot slice(int index, int length) {
      Preconditions.checkArgument(index >= 0, "expecting non-negative index");
      Preconditions.checkArgument(length >= 0, "expecting non-negative length");
      Preconditions.checkArgument(index + length <= this.rowCount, "index + length should <= rowCount");
      List<FieldVector> sliceVectors = (List)this.fieldVectors.stream().map((v) -> {
         TransferPair transferPair = v.getTransferPair(v.getAllocator());
         transferPair.splitAndTransfer(index, length);
         return (FieldVector)transferPair.getTo();
      }).collect(Collectors.toList());
      return new VectorSchemaRoot(sliceVectors);
   }

   public boolean equals(VectorSchemaRoot other) {
      if (other == null) {
         return false;
      } else if (!this.schema.equals(other.schema)) {
         return false;
      } else if (this.rowCount != other.rowCount) {
         return false;
      } else {
         for(int i = 0; i < this.fieldVectors.size(); ++i) {
            FieldVector vector = (FieldVector)this.fieldVectors.get(i);
            FieldVector otherVector = (FieldVector)other.fieldVectors.get(i);
            if (!VectorEqualsVisitor.vectorEquals(vector, otherVector)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean approxEquals(VectorSchemaRoot other, VectorValueEqualizer floatDiffFunction, VectorValueEqualizer doubleDiffFunction) {
      Preconditions.checkNotNull(floatDiffFunction);
      Preconditions.checkNotNull(doubleDiffFunction);
      if (other == null) {
         return false;
      } else if (!this.schema.equals(other.schema)) {
         return false;
      } else if (this.rowCount != other.rowCount) {
         return false;
      } else {
         Range range = new Range(0, 0, 0);

         for(int i = 0; i < this.fieldVectors.size(); ++i) {
            FieldVector vector = (FieldVector)this.fieldVectors.get(i);
            FieldVector otherVector = (FieldVector)other.fieldVectors.get(i);
            if (vector.getValueCount() != otherVector.getValueCount()) {
               return false;
            }

            ApproxEqualsVisitor visitor = new ApproxEqualsVisitor(vector, otherVector, floatDiffFunction, doubleDiffFunction);
            range.setLength(vector.getValueCount());
            if (!visitor.rangeEquals(range)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean approxEquals(VectorSchemaRoot other) {
      VectorValueEqualizer<Float4Vector> floatDiffFunction = new ValueEpsilonEqualizers.Float4EpsilonEqualizer(1.0E-6F);
      VectorValueEqualizer<Float8Vector> doubleDiffFunction = new ValueEpsilonEqualizers.Float8EpsilonEqualizer(1.0E-6);
      return this.approxEquals(other, floatDiffFunction, doubleDiffFunction);
   }
}

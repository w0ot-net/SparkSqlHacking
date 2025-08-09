package org.apache.arrow.vector.complex;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.ValueVectorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractContainerVector implements ValueVector, DensityAwareVector {
   static final Logger logger = LoggerFactory.getLogger(AbstractContainerVector.class);
   protected final String name;
   protected final BufferAllocator allocator;
   protected final CallBack callBack;

   protected AbstractContainerVector(String name, BufferAllocator allocator, CallBack callBack) {
      this.name = name;
      this.allocator = allocator;
      this.callBack = callBack;
   }

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount());
   }

   public void allocateNew() throws OutOfMemoryException {
      if (!this.allocateNewSafe()) {
         throw new OutOfMemoryException();
      }
   }

   public BufferAllocator getAllocator() {
      return this.allocator;
   }

   public FieldVector getChild(String name) {
      return this.getChild(name, FieldVector.class);
   }

   public void close() {
      for(ValueVector vector : this) {
         vector.close();
      }

   }

   protected ValueVector typeify(ValueVector v, Class clazz) {
      if (clazz.isAssignableFrom(v.getClass())) {
         return (ValueVector)clazz.cast(v);
      } else {
         throw new IllegalStateException(String.format("Vector requested [%s] was different than type stored [%s]. Arrow doesn't yet support heterogeneous types.", clazz.getSimpleName(), v.getClass().getSimpleName()));
      }
   }

   protected boolean supportsDirectRead() {
      return false;
   }

   public abstract int size();

   public abstract FieldVector addOrGet(String var1, FieldType var2, Class var3);

   public abstract FieldVector getChild(String var1, Class var2);

   public abstract VectorWithOrdinal getChildVectorWithOrdinal(String var1);

   public StructVector addOrGetStruct(String name) {
      return (StructVector)this.addOrGet(name, FieldType.nullable(new ArrowType.Struct()), StructVector.class);
   }

   public ListVector addOrGetList(String name) {
      return (ListVector)this.addOrGet(name, FieldType.nullable(new ArrowType.List()), ListVector.class);
   }

   public ListViewVector addOrGetListView(String name) {
      return (ListViewVector)this.addOrGet(name, FieldType.nullable(new ArrowType.ListView()), ListViewVector.class);
   }

   public UnionVector addOrGetUnion(String name) {
      return (UnionVector)this.addOrGet(name, FieldType.nullable(Types.MinorType.UNION.getType()), UnionVector.class);
   }

   public FixedSizeListVector addOrGetFixedSizeList(String name, int listSize) {
      return (FixedSizeListVector)this.addOrGet(name, FieldType.nullable(new ArrowType.FixedSizeList(listSize)), FixedSizeListVector.class);
   }

   public MapVector addOrGetMap(String name, boolean keysSorted) {
      return (MapVector)this.addOrGet(name, FieldType.nullable(new ArrowType.Map(keysSorted)), MapVector.class);
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      throw new UnsupportedOperationException();
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      throw new UnsupportedOperationException();
   }

   public String getName() {
      return this.name;
   }
}

package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.PromotableMultiMapWithOrdinal;
import org.apache.arrow.vector.util.ValueVectorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractStructVector extends AbstractContainerVector {
   private static final Logger logger = LoggerFactory.getLogger(AbstractContainerVector.class);
   private static final String STRUCT_CONFLICT_POLICY_ENV = "ARROW_STRUCT_CONFLICT_POLICY";
   private static final String STRUCT_CONFLICT_POLICY_JVM = "arrow.struct.conflict.policy";
   private static final ConflictPolicy DEFAULT_CONFLICT_POLICY;
   private final PromotableMultiMapWithOrdinal vectors;
   protected final boolean allowConflictPolicyChanges;
   private ConflictPolicy conflictPolicy;

   protected AbstractStructVector(String name, BufferAllocator allocator, CallBack callBack, ConflictPolicy conflictPolicy, boolean allowConflictPolicyChanges) {
      super(name, allocator, callBack);
      this.conflictPolicy = conflictPolicy == null ? DEFAULT_CONFLICT_POLICY : conflictPolicy;
      this.vectors = new PromotableMultiMapWithOrdinal(allowConflictPolicyChanges, this.conflictPolicy);
      this.allowConflictPolicyChanges = allowConflictPolicyChanges;
   }

   public ConflictPolicy setConflictPolicy(ConflictPolicy conflictPolicy) {
      ConflictPolicy tmp = this.conflictPolicy;
      this.conflictPolicy = conflictPolicy;
      this.vectors.setConflictPolicy(conflictPolicy);
      return tmp;
   }

   public ConflictPolicy getConflictPolicy() {
      return this.conflictPolicy;
   }

   public void close() {
      for(ValueVector valueVector : this.vectors.values()) {
         valueVector.close();
      }

      this.vectors.clear();
      super.close();
   }

   public boolean allocateNewSafe() {
      boolean success = false;

      boolean var4;
      try {
         Iterator var2 = this.vectors.values().iterator();

         ValueVector v;
         do {
            if (!var2.hasNext()) {
               success = true;
               return true;
            }

            v = (ValueVector)var2.next();
         } while(v.allocateNewSafe());

         var4 = false;
      } finally {
         if (!success) {
            this.clear();
         }

      }

      return var4;
   }

   public void reAlloc() {
      for(ValueVector v : this.vectors.values()) {
         v.reAlloc();
      }

   }

   public FieldVector addOrGet(String childName, FieldType fieldType, Class clazz) {
      ValueVector existing = this.getChild(childName);
      boolean create = false;
      if (existing == null) {
         create = true;
      } else {
         if (clazz.isAssignableFrom(existing.getClass())) {
            return (FieldVector)clazz.cast(existing);
         }

         if (this.nullFilled(existing)) {
            existing.clear();
            create = true;
         }
      }

      if (create) {
         T vector = (T)((FieldVector)clazz.cast(fieldType.createNewSingleVector(childName, this.allocator, this.callBack)));
         this.putChild(childName, vector);
         if (this.callBack != null) {
            this.callBack.doWork();
         }

         return vector;
      } else {
         String message = "Arrow does not support schema change yet. Existing[%s] and desired[%s] vector types mismatch";
         throw new IllegalStateException(String.format("Arrow does not support schema change yet. Existing[%s] and desired[%s] vector types mismatch", existing.getClass().getSimpleName(), clazz.getSimpleName()));
      }
   }

   private boolean nullFilled(ValueVector vector) {
      return BitVectorHelper.checkAllBitsEqualTo(vector.getValidityBuffer(), vector.getValueCount(), false);
   }

   public ValueVector getChildByOrdinal(int id) {
      return (ValueVector)this.vectors.getByOrdinal(id);
   }

   public FieldVector getChild(String name, Class clazz) {
      FieldVector f = (FieldVector)this.vectors.get(name);
      return f == null ? null : (FieldVector)this.typeify(f, clazz);
   }

   protected ValueVector add(String childName, FieldType fieldType) {
      FieldVector vector = fieldType.createNewSingleVector(childName, this.allocator, this.callBack);
      this.putChild(childName, vector);
      if (this.callBack != null) {
         this.callBack.doWork();
      }

      return vector;
   }

   protected void putChild(String name, FieldVector vector) {
      this.putVector(name, vector);
   }

   private void put(String name, FieldVector vector, boolean overwrite) {
      boolean old = this.vectors.put((String)Preconditions.checkNotNull(name, "field name cannot be null"), (FieldVector)Preconditions.checkNotNull(vector, "vector cannot be null"), overwrite);
      if (old) {
         logger.debug("Field [{}] mutated to [{}] ", name, vector.getClass().getSimpleName());
      }

   }

   protected void putVector(String name, FieldVector vector) {
      switch (this.conflictPolicy.ordinal()) {
         case 0:
            this.put(name, vector, false);
            break;
         case 1:
            if (!this.vectors.containsKey(name)) {
               this.put(name, vector, false);
            }
            break;
         case 2:
            if (this.vectors.containsKey(name)) {
               this.vectors.removeAll(name);
            }

            this.put(name, vector, true);
            break;
         case 3:
            if (this.vectors.containsKey(name)) {
               throw new IllegalStateException(String.format("Vector already exists: Existing[%s], Requested[%s] ", vector.getClass().getSimpleName(), vector.getField().getFieldType()));
            }

            this.put(name, vector, false);
            break;
         default:
            throw new IllegalStateException(String.format("%s type not a valid conflict state", this.conflictPolicy));
      }

   }

   protected List getChildren() {
      int size = this.vectors.size();
      List<FieldVector> children = new ArrayList();

      for(int i = 0; i < size; ++i) {
         children.add((FieldVector)this.vectors.getByOrdinal(i));
      }

      return children;
   }

   public List getChildFieldNames() {
      return (List)this.getChildren().stream().map((child) -> child.getField().getName()).collect(Collectors.toList());
   }

   public int size() {
      return this.vectors.size();
   }

   public Iterator iterator() {
      return Collections.unmodifiableCollection(this.vectors.values()).iterator();
   }

   public List getPrimitiveVectors() {
      List<ValueVector> primitiveVectors = new ArrayList();

      for(FieldVector v : this.vectors.values()) {
         primitiveVectors.addAll(this.getPrimitiveVectors(v));
      }

      return primitiveVectors;
   }

   private List getPrimitiveVectors(FieldVector v) {
      List<ValueVector> primitives = new ArrayList();
      if (v instanceof AbstractStructVector) {
         AbstractStructVector structVector = (AbstractStructVector)v;
         primitives.addAll(structVector.getPrimitiveVectors());
      } else if (v instanceof ListVector) {
         ListVector listVector = (ListVector)v;
         primitives.addAll(this.getPrimitiveVectors(listVector.getDataVector()));
      } else if (v instanceof FixedSizeListVector) {
         FixedSizeListVector listVector = (FixedSizeListVector)v;
         primitives.addAll(this.getPrimitiveVectors(listVector.getDataVector()));
      } else if (v instanceof UnionVector) {
         UnionVector unionVector = (UnionVector)v;

         for(FieldVector vector : unionVector.getChildrenFromFields()) {
            primitives.addAll(this.getPrimitiveVectors(vector));
         }
      } else {
         primitives.add(v);
      }

      return primitives;
   }

   public VectorWithOrdinal getChildVectorWithOrdinal(String name) {
      int ordinal = this.vectors.getOrdinal(name);
      if (ordinal < 0) {
         return null;
      } else {
         ValueVector vector = (ValueVector)this.vectors.getByOrdinal(ordinal);
         return new VectorWithOrdinal(vector, ordinal);
      }
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      List<ArrowBuf> buffers = new ArrayList();

      for(ValueVector vector : this.vectors.values()) {
         for(ArrowBuf buf : vector.getBuffers(false)) {
            buffers.add(buf);
            if (clear) {
               buf.getReferenceManager().retain(1);
            }
         }

         if (clear) {
            vector.clear();
         }
      }

      return (ArrowBuf[])buffers.toArray(new ArrowBuf[buffers.size()]);
   }

   public int getBufferSize() {
      int actualBufSize = 0;

      for(ValueVector v : this.vectors.values()) {
         for(ArrowBuf buf : v.getBuffers(false)) {
            actualBufSize += (int)buf.writerIndex();
         }
      }

      return actualBufSize;
   }

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount());
   }

   static {
      String conflictPolicyStr = System.getProperty("arrow.struct.conflict.policy", AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE.toString());
      if (conflictPolicyStr == null) {
         conflictPolicyStr = System.getenv("ARROW_STRUCT_CONFLICT_POLICY");
      }

      ConflictPolicy conflictPolicy;
      try {
         conflictPolicy = AbstractStructVector.ConflictPolicy.valueOf(conflictPolicyStr.toUpperCase(Locale.ROOT));
      } catch (Exception var3) {
         conflictPolicy = AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE;
      }

      DEFAULT_CONFLICT_POLICY = conflictPolicy;
   }

   public static enum ConflictPolicy {
      CONFLICT_APPEND,
      CONFLICT_IGNORE,
      CONFLICT_REPLACE,
      CONFLICT_ERROR;

      // $FF: synthetic method
      private static ConflictPolicy[] $values() {
         return new ConflictPolicy[]{CONFLICT_APPEND, CONFLICT_IGNORE, CONFLICT_REPLACE, CONFLICT_ERROR};
      }
   }
}

package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.memory.ReferenceManager;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.Float2Vector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.IntervalDayVector;
import org.apache.arrow.vector.IntervalMonthDayNanoVector;
import org.apache.arrow.vector.IntervalYearVector;
import org.apache.arrow.vector.LargeVarBinaryVector;
import org.apache.arrow.vector.LargeVarCharVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TimeMicroVector;
import org.apache.arrow.vector.TimeMilliVector;
import org.apache.arrow.vector.TimeNanoVector;
import org.apache.arrow.vector.TimeSecVector;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.UInt1Vector;
import org.apache.arrow.vector.UInt2Vector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.UInt8Vector;
import org.apache.arrow.vector.ValueIterableVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VarBinaryVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.ViewVarBinaryVector;
import org.apache.arrow.vector.ViewVarCharVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.impl.ComplexCopier;
import org.apache.arrow.vector.complex.impl.DenseUnionReader;
import org.apache.arrow.vector.complex.impl.DenseUnionWriter;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.DenseUnionHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.holders.NullableFloat2Holder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;
import org.apache.arrow.vector.holders.NullableIntHolder;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;
import org.apache.arrow.vector.holders.NullableLargeVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableLargeVarCharHolder;
import org.apache.arrow.vector.holders.NullableSmallIntHolder;
import org.apache.arrow.vector.holders.NullableTimeMicroHolder;
import org.apache.arrow.vector.holders.NullableTimeMilliHolder;
import org.apache.arrow.vector.holders.NullableTimeNanoHolder;
import org.apache.arrow.vector.holders.NullableTimeSecHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMicroHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.holders.NullableVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.UnionMode;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public class DenseUnionVector extends AbstractContainerVector implements FieldVector, ValueIterableVector {
   int valueCount;
   NonNullableStructVector internalStruct;
   private ArrowBuf typeBuffer;
   private ArrowBuf offsetBuffer;
   private ValueVector[] childVectors = new ValueVector[128];
   private Field[] typeFields = new Field[128];
   private byte[] typeMapFields = new byte[128];
   private byte nextTypeId = 0;
   private FieldReader reader;
   private long typeBufferAllocationSizeInBytes;
   private long offsetBufferAllocationSizeInBytes;
   private final FieldType fieldType;
   public static final byte TYPE_WIDTH = 1;
   public static final byte OFFSET_WIDTH = 4;
   private static final FieldType INTERNAL_STRUCT_TYPE;
   DenseUnionWriter writer;

   public static DenseUnionVector empty(String name, BufferAllocator allocator) {
      FieldType fieldType = FieldType.notNullable(new ArrowType.Union(UnionMode.Dense, (int[])null));
      return new DenseUnionVector(name, allocator, fieldType, (CallBack)null);
   }

   public DenseUnionVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      super(name, allocator, callBack);
      this.fieldType = fieldType;
      this.internalStruct = new NonNullableStructVector("internal", allocator, INTERNAL_STRUCT_TYPE, callBack, AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE, false);
      this.typeBuffer = allocator.getEmpty();
      this.typeBufferAllocationSizeInBytes = 3970L;
      this.offsetBuffer = allocator.getEmpty();
      this.offsetBufferAllocationSizeInBytes = 15880L;
   }

   public BufferAllocator getAllocator() {
      return this.allocator;
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.DENSEUNION;
   }

   public void initializeChildrenFromFields(List children) {
      for(Field field : children) {
         byte typeId = this.registerNewTypeId(field);
         FieldVector vector = (FieldVector)this.internalStruct.add(field.getName(), field.getFieldType());
         vector.initializeChildrenFromFields(field.getChildren());
         this.childVectors[typeId] = vector;
      }

   }

   public List getChildrenFromFields() {
      return this.internalStruct.getChildrenFromFields();
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (ownBuffers.size() != 2) {
         String var10002 = String.valueOf(this.getField().getFieldType());
         throw new IllegalArgumentException("Illegal buffer count for dense union with type " + var10002 + ", expected 2, got: " + ownBuffers.size());
      } else {
         ArrowBuf buffer = (ArrowBuf)ownBuffers.get(0);
         this.typeBuffer.getReferenceManager().release();
         this.typeBuffer = buffer.getReferenceManager().retain(buffer, this.allocator);
         this.typeBufferAllocationSizeInBytes = this.typeBuffer.capacity();
         buffer = (ArrowBuf)ownBuffers.get(1);
         this.offsetBuffer.getReferenceManager().release();
         this.offsetBuffer = buffer.getReferenceManager().retain(buffer, this.allocator);
         this.offsetBufferAllocationSizeInBytes = this.offsetBuffer.capacity();
         this.valueCount = fieldNode.getLength();
      }
   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(2);
      this.setReaderAndWriterIndex();
      result.add(this.typeBuffer);
      result.add(this.offsetBuffer);
      return result;
   }

   private void setReaderAndWriterIndex() {
      this.typeBuffer.readerIndex(0L);
      this.typeBuffer.writerIndex((long)(this.valueCount * 1));
      this.offsetBuffer.readerIndex(0L);
      this.offsetBuffer.writerIndex((long)this.valueCount * 4L);
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use geFieldBuffers");
   }

   private String fieldName(byte typeId, Types.MinorType type) {
      String var10000 = type.name().toLowerCase();
      return var10000 + typeId;
   }

   private FieldType fieldType(Types.MinorType type) {
      return FieldType.nullable(type.getType());
   }

   public synchronized byte registerNewTypeId(Field field) {
      if (this.nextTypeId == this.typeFields.length) {
         throw new IllegalStateException("Dense union vector support at most " + this.typeFields.length + " relative types. Please use union of union instead");
      } else {
         byte typeId = this.nextTypeId;
         if (this.fieldType != null) {
            int[] typeIds = ((ArrowType.Union)this.fieldType.getType()).getTypeIds();
            if (typeIds != null) {
               int thisTypeId = typeIds[this.nextTypeId];
               if (thisTypeId > 127) {
                  throw new IllegalStateException("Dense union vector types must be bytes. " + thisTypeId + " is too large");
               }

               typeId = (byte)thisTypeId;
            }
         }

         this.typeFields[typeId] = field;
         this.typeMapFields[this.nextTypeId] = typeId;
         ++this.nextTypeId;
         return typeId;
      }
   }

   private FieldVector addOrGet(byte typeId, Types.MinorType minorType, Class c) {
      return this.internalStruct.addOrGet(this.fieldName(typeId, minorType), this.fieldType(minorType), c);
   }

   private FieldVector addOrGet(byte typeId, Types.MinorType minorType, ArrowType arrowType, Class c) {
      return this.internalStruct.addOrGet(this.fieldName(typeId, minorType), FieldType.nullable(arrowType), c);
   }

   public long getOffsetBufferAddress() {
      return this.offsetBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getValidityBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getValidityBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getOffsetBuffer() {
      return this.offsetBuffer;
   }

   public ArrowBuf getTypeBuffer() {
      return this.typeBuffer;
   }

   public ArrowBuf getDataBuffer() {
      throw new UnsupportedOperationException();
   }

   public StructVector getStruct(byte typeId) {
      StructVector structVector = typeId < 0 ? null : (StructVector)this.childVectors[typeId];
      if (structVector == null) {
         int vectorCount = this.internalStruct.size();
         structVector = (StructVector)this.addOrGet(typeId, Types.MinorType.STRUCT, StructVector.class);
         if (this.internalStruct.size() > vectorCount) {
            structVector.allocateNew();
            this.childVectors[typeId] = structVector;
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return structVector;
   }

   public TinyIntVector getTinyIntVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TINYINT, TinyIntVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TinyIntVector)vector;
   }

   public UInt1Vector getUInt1Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.UINT1, UInt1Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (UInt1Vector)vector;
   }

   public UInt2Vector getUInt2Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.UINT2, UInt2Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (UInt2Vector)vector;
   }

   public SmallIntVector getSmallIntVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.SMALLINT, SmallIntVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (SmallIntVector)vector;
   }

   public Float2Vector getFloat2Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.FLOAT2, Float2Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (Float2Vector)vector;
   }

   public IntVector getIntVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.INT, IntVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (IntVector)vector;
   }

   public UInt4Vector getUInt4Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.UINT4, UInt4Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (UInt4Vector)vector;
   }

   public Float4Vector getFloat4Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.FLOAT4, Float4Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (Float4Vector)vector;
   }

   public DateDayVector getDateDayVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.DATEDAY, DateDayVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (DateDayVector)vector;
   }

   public IntervalYearVector getIntervalYearVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.INTERVALYEAR, IntervalYearVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (IntervalYearVector)vector;
   }

   public TimeSecVector getTimeSecVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMESEC, TimeSecVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeSecVector)vector;
   }

   public TimeMilliVector getTimeMilliVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMEMILLI, TimeMilliVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeMilliVector)vector;
   }

   public BigIntVector getBigIntVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.BIGINT, BigIntVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (BigIntVector)vector;
   }

   public UInt8Vector getUInt8Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.UINT8, UInt8Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (UInt8Vector)vector;
   }

   public Float8Vector getFloat8Vector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.FLOAT8, Float8Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (Float8Vector)vector;
   }

   public DateMilliVector getDateMilliVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.DATEMILLI, DateMilliVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (DateMilliVector)vector;
   }

   public TimeStampSecVector getTimeStampSecVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMESTAMPSEC, TimeStampSecVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeStampSecVector)vector;
   }

   public TimeStampMilliVector getTimeStampMilliVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMESTAMPMILLI, TimeStampMilliVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeStampMilliVector)vector;
   }

   public TimeStampMicroVector getTimeStampMicroVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMESTAMPMICRO, TimeStampMicroVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeStampMicroVector)vector;
   }

   public TimeStampNanoVector getTimeStampNanoVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMESTAMPNANO, TimeStampNanoVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeStampNanoVector)vector;
   }

   public TimeMicroVector getTimeMicroVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMEMICRO, TimeMicroVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeMicroVector)vector;
   }

   public TimeNanoVector getTimeNanoVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.TIMENANO, TimeNanoVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (TimeNanoVector)vector;
   }

   public IntervalDayVector getIntervalDayVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.INTERVALDAY, IntervalDayVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (IntervalDayVector)vector;
   }

   public IntervalMonthDayNanoVector getIntervalMonthDayNanoVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.INTERVALMONTHDAYNANO, IntervalMonthDayNanoVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (IntervalMonthDayNanoVector)vector;
   }

   public Decimal256Vector getDecimal256Vector(byte typeId, ArrowType arrowType) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.DECIMAL256, arrowType, Decimal256Vector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (Decimal256Vector)vector;
   }

   public DecimalVector getDecimalVector(byte typeId, ArrowType arrowType) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.DECIMAL, arrowType, DecimalVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (DecimalVector)vector;
   }

   public VarBinaryVector getVarBinaryVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.VARBINARY, VarBinaryVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (VarBinaryVector)vector;
   }

   public VarCharVector getVarCharVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.VARCHAR, VarCharVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (VarCharVector)vector;
   }

   public ViewVarBinaryVector getViewVarBinaryVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.VIEWVARBINARY, ViewVarBinaryVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (ViewVarBinaryVector)vector;
   }

   public ViewVarCharVector getViewVarCharVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.VIEWVARCHAR, ViewVarCharVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (ViewVarCharVector)vector;
   }

   public LargeVarCharVector getLargeVarCharVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.LARGEVARCHAR, LargeVarCharVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (LargeVarCharVector)vector;
   }

   public LargeVarBinaryVector getLargeVarBinaryVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.LARGEVARBINARY, LargeVarBinaryVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (LargeVarBinaryVector)vector;
   }

   public BitVector getBitVector(byte typeId) {
      ValueVector vector = typeId < 0 ? null : this.childVectors[typeId];
      if (vector == null) {
         int vectorCount = this.internalStruct.size();
         vector = this.addOrGet(typeId, Types.MinorType.BIT, BitVector.class);
         this.childVectors[typeId] = vector;
         if (this.internalStruct.size() > vectorCount) {
            vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return (BitVector)vector;
   }

   public ListVector getList(byte typeId) {
      ListVector listVector = typeId < 0 ? null : (ListVector)this.childVectors[typeId];
      if (listVector == null) {
         int vectorCount = this.internalStruct.size();
         listVector = (ListVector)this.addOrGet(typeId, Types.MinorType.LIST, ListVector.class);
         if (this.internalStruct.size() > vectorCount) {
            listVector.allocateNew();
            this.childVectors[typeId] = listVector;
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return listVector;
   }

   public MapVector getMap(byte typeId) {
      MapVector mapVector = typeId < 0 ? null : (MapVector)this.childVectors[typeId];
      if (mapVector == null) {
         int vectorCount = this.internalStruct.size();
         mapVector = (MapVector)this.addOrGet(typeId, Types.MinorType.MAP, MapVector.class);
         if (this.internalStruct.size() > vectorCount) {
            mapVector.allocateNew();
            this.childVectors[typeId] = mapVector;
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return mapVector;
   }

   public byte getTypeId(int index) {
      return this.typeBuffer.getByte((long)(index * 1));
   }

   public ValueVector getVectorByType(byte typeId) {
      return typeId < 0 ? null : this.childVectors[typeId];
   }

   public void allocateNew() throws OutOfMemoryException {
      this.clear();
      this.internalStruct.allocateNew();

      try {
         this.allocateTypeBuffer();
         this.allocateOffsetBuffer();
      } catch (Exception e) {
         this.clear();
         throw e;
      }
   }

   public boolean allocateNewSafe() {
      this.clear();
      boolean safe = this.internalStruct.allocateNewSafe();
      if (!safe) {
         return false;
      } else {
         try {
            this.allocateTypeBuffer();
            this.allocateOffsetBuffer();
            return true;
         } catch (Exception var3) {
            this.clear();
            return false;
         }
      }
   }

   private void allocateTypeBuffer() {
      this.typeBuffer = this.allocator.buffer(this.typeBufferAllocationSizeInBytes);
      this.typeBuffer.readerIndex(0L);
      this.setNegative(0L, this.typeBuffer.capacity());
   }

   private void allocateOffsetBuffer() {
      this.offsetBuffer = this.allocator.buffer(this.offsetBufferAllocationSizeInBytes);
      this.offsetBuffer.readerIndex(0L);
      this.offsetBuffer.setZero(0L, this.offsetBuffer.capacity());
   }

   public void reAlloc() {
      this.internalStruct.reAlloc();
      this.reallocTypeBuffer();
      this.reallocOffsetBuffer();
   }

   public int getOffset(int index) {
      return this.offsetBuffer.getInt((long)index * 4L);
   }

   private void reallocTypeBuffer() {
      long currentBufferCapacity = this.typeBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.typeBufferAllocationSizeInBytes > 0L) {
            newAllocationSize = this.typeBufferAllocationSizeInBytes;
         } else {
            newAllocationSize = 7940L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);

      assert newAllocationSize >= 1L;

      if (newAllocationSize > BaseValueVector.MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Unable to expand the buffer");
      } else {
         ArrowBuf newBuf = this.allocator.buffer((long)((int)newAllocationSize));
         newBuf.setBytes(0L, this.typeBuffer, 0L, currentBufferCapacity);
         this.typeBuffer.getReferenceManager().release(1);
         this.typeBuffer = newBuf;
         this.typeBufferAllocationSizeInBytes = (long)((int)newAllocationSize);
         this.setNegative(currentBufferCapacity, newBuf.capacity() - currentBufferCapacity);
      }
   }

   private void reallocOffsetBuffer() {
      long currentBufferCapacity = this.offsetBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.offsetBufferAllocationSizeInBytes > 0L) {
            newAllocationSize = this.offsetBufferAllocationSizeInBytes;
         } else {
            newAllocationSize = 31760L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);

      assert newAllocationSize >= 1L;

      if (newAllocationSize > BaseValueVector.MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Unable to expand the buffer");
      } else {
         ArrowBuf newBuf = this.allocator.buffer((long)((int)newAllocationSize));
         newBuf.setBytes(0L, this.offsetBuffer, 0L, currentBufferCapacity);
         newBuf.setZero(currentBufferCapacity, newBuf.capacity() - currentBufferCapacity);
         this.offsetBuffer.getReferenceManager().release(1);
         this.offsetBuffer = newBuf;
         this.offsetBufferAllocationSizeInBytes = (long)((int)newAllocationSize);
      }
   }

   public void setInitialCapacity(int numRecords) {
   }

   public int getValueCapacity() {
      long capacity = (long)this.getTypeBufferValueCapacity();
      long offsetCapacity = this.getOffsetBufferValueCapacity();
      if (offsetCapacity < capacity) {
         capacity = offsetCapacity;
      }

      long structCapacity = (long)this.internalStruct.getValueCapacity();
      if (structCapacity < capacity) {
         ;
      }

      return (int)capacity;
   }

   public void close() {
      this.clear();
   }

   public void clear() {
      this.valueCount = 0;
      this.typeBuffer.getReferenceManager().release();
      this.typeBuffer = this.allocator.getEmpty();
      this.offsetBuffer.getReferenceManager().release();
      this.offsetBuffer = this.allocator.getEmpty();
      this.internalStruct.clear();
   }

   public void reset() {
      this.valueCount = 0;
      this.setNegative(0L, this.typeBuffer.capacity());
      this.offsetBuffer.setZero(0L, this.offsetBuffer.capacity());
      this.internalStruct.reset();
   }

   public Field getField() {
      int childCount = (int)Arrays.stream(this.typeFields).filter((field) -> field != null).count();
      List<Field> childFields = new ArrayList(childCount);
      int[] typeIds = new int[childCount];

      for(int i = 0; i < this.typeFields.length; ++i) {
         if (this.typeFields[i] != null) {
            int curIdx = childFields.size();
            typeIds[curIdx] = i;
            childFields.add(this.typeFields[i]);
         }
      }

      FieldType fieldType;
      if (this.fieldType == null) {
         fieldType = FieldType.nullable(new ArrowType.Union(UnionMode.Dense, typeIds));
      } else {
         UnionMode mode = UnionMode.Dense;
         fieldType = new FieldType(this.fieldType.isNullable(), new ArrowType.Union(mode, typeIds), this.fieldType.getDictionary(), this.fieldType.getMetadata());
      }

      return new Field(this.name, fieldType, childFields);
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.getTransferPair(this.name, allocator);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return this.getTransferPair((String)ref, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return new TransferImpl(ref, allocator, callBack);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return this.getTransferPair((Field)field, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return new TransferImpl(field, allocator, callBack);
   }

   public TransferPair makeTransferPair(ValueVector target) {
      return new TransferImpl((DenseUnionVector)target);
   }

   public void copyFrom(int inIndex, int outIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      DenseUnionVector fromCast = (DenseUnionVector)from;
      int inOffset = fromCast.offsetBuffer.getInt((long)inIndex * 4L);
      fromCast.getReader().setPosition(inOffset);
      int outOffset = this.offsetBuffer.getInt((long)outIndex * 4L);
      this.getWriter().setPosition(outOffset);
      ComplexCopier.copy(fromCast.reader, this.writer);
   }

   public void copyFromSafe(int inIndex, int outIndex, ValueVector from) {
      this.copyFrom(inIndex, outIndex, from);
   }

   public FieldVector addVector(byte typeId, FieldVector v) {
      String name = v.getName().isEmpty() ? this.fieldName(typeId, v.getMinorType()) : v.getName();
      Preconditions.checkState(this.internalStruct.getChild(name) == null, String.format("%s vector already exists", name));
      FieldVector newVector = this.internalStruct.addOrGet(name, v.getField().getFieldType(), v.getClass());
      v.makeTransferPair(newVector).transfer();
      this.internalStruct.putChild(name, newVector);
      this.childVectors[typeId] = newVector;
      if (this.callBack != null) {
         this.callBack.doWork();
      }

      return newVector;
   }

   public FieldReader getReader() {
      if (this.reader == null) {
         this.reader = new DenseUnionReader(this);
      }

      return this.reader;
   }

   public FieldWriter getWriter() {
      if (this.writer == null) {
         this.writer = new DenseUnionWriter(this);
      }

      return this.writer;
   }

   public int getBufferSize() {
      return this.getBufferSizeFor(this.valueCount);
   }

   public int getBufferSizeFor(int count) {
      if (count == 0) {
         return 0;
      } else {
         int[] counts = new int[128];

         for(int i = 0; i < count; ++i) {
            byte typeId = this.getTypeId(i);
            if (typeId != -1) {
               int var10002 = counts[typeId]++;
            }
         }

         long childBytes = 0L;

         for(int typeId = 0; typeId < this.childVectors.length; ++typeId) {
            ValueVector childVector = this.childVectors[typeId];
            if (childVector != null) {
               childBytes += (long)childVector.getBufferSizeFor(counts[typeId]);
            }
         }

         return (int)((long)(count * 1) + (long)count * 4L + childBytes);
      }
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      List<ArrowBuf> list = new ArrayList();
      this.setReaderAndWriterIndex();
      if (this.getBufferSize() != 0) {
         list.add(this.typeBuffer);
         list.add(this.offsetBuffer);
         list.addAll(Arrays.asList(this.internalStruct.getBuffers(clear)));
      }

      if (clear) {
         this.valueCount = 0;
         this.typeBuffer.getReferenceManager().retain();
         this.typeBuffer.close();
         this.typeBuffer = this.allocator.getEmpty();
         this.offsetBuffer.getReferenceManager().retain();
         this.offsetBuffer.close();
         this.offsetBuffer = this.allocator.getEmpty();
      }

      return (ArrowBuf[])list.toArray(new ArrowBuf[list.size()]);
   }

   public Iterator iterator() {
      return this.internalStruct.iterator();
   }

   private ValueVector getVector(int index) {
      byte typeId = this.typeBuffer.getByte((long)(index * 1));
      return this.getVectorByType(typeId);
   }

   public Object getObject(int index) {
      ValueVector vector = this.getVector(index);
      if (vector != null) {
         int offset = this.offsetBuffer.getInt((long)index * 4L);
         return vector.isNull(offset) ? null : vector.getObject(offset);
      } else {
         return null;
      }
   }

   public void get(int index, DenseUnionHolder holder) {
      FieldReader reader = new DenseUnionReader(this);
      reader.setPosition(index);
      holder.reader = reader;
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public boolean isNull(int index) {
      return false;
   }

   public int getNullCount() {
      return 0;
   }

   public int isSet(int index) {
      return this.isNull(index) ? 0 : 1;
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;

      while(valueCount > this.getTypeBufferValueCapacity()) {
         this.reallocTypeBuffer();
         this.reallocOffsetBuffer();
      }

      this.setChildVectorValueCounts();
   }

   private void setChildVectorValueCounts() {
      int[] counts = new int[128];

      for(int i = 0; i < this.valueCount; ++i) {
         byte typeId = this.getTypeId(i);
         if (typeId != -1) {
            int var10002 = counts[typeId]++;
         }
      }

      for(int i = 0; i < this.nextTypeId; ++i) {
         this.childVectors[this.typeMapFields[i]].setValueCount(counts[this.typeMapFields[i]]);
      }

   }

   public void setSafe(int index, DenseUnionHolder holder) {
      FieldReader reader = holder.reader;
      if (this.writer == null) {
         this.writer = new DenseUnionWriter(this);
      }

      int offset = this.offsetBuffer.getInt((long)index * 4L);
      Types.MinorType type = reader.getMinorType();
      this.writer.setPosition(offset);
      byte typeId = holder.typeId;
      switch (type) {
         case TINYINT:
            NullableTinyIntHolder tinyIntHolder = new NullableTinyIntHolder();
            reader.read(tinyIntHolder);
            this.setSafe(index, tinyIntHolder);
            break;
         case UINT1:
            NullableUInt1Holder uInt1Holder = new NullableUInt1Holder();
            reader.read(uInt1Holder);
            this.setSafe(index, uInt1Holder);
            break;
         case UINT2:
            NullableUInt2Holder uInt2Holder = new NullableUInt2Holder();
            reader.read(uInt2Holder);
            this.setSafe(index, uInt2Holder);
            break;
         case SMALLINT:
            NullableSmallIntHolder smallIntHolder = new NullableSmallIntHolder();
            reader.read(smallIntHolder);
            this.setSafe(index, smallIntHolder);
            break;
         case FLOAT2:
            NullableFloat2Holder float2Holder = new NullableFloat2Holder();
            reader.read(float2Holder);
            this.setSafe(index, float2Holder);
            break;
         case INT:
            NullableIntHolder intHolder = new NullableIntHolder();
            reader.read(intHolder);
            this.setSafe(index, intHolder);
            break;
         case UINT4:
            NullableUInt4Holder uInt4Holder = new NullableUInt4Holder();
            reader.read(uInt4Holder);
            this.setSafe(index, uInt4Holder);
            break;
         case FLOAT4:
            NullableFloat4Holder float4Holder = new NullableFloat4Holder();
            reader.read(float4Holder);
            this.setSafe(index, float4Holder);
            break;
         case DATEDAY:
            NullableDateDayHolder dateDayHolder = new NullableDateDayHolder();
            reader.read(dateDayHolder);
            this.setSafe(index, dateDayHolder);
            break;
         case INTERVALYEAR:
            NullableIntervalYearHolder intervalYearHolder = new NullableIntervalYearHolder();
            reader.read(intervalYearHolder);
            this.setSafe(index, intervalYearHolder);
            break;
         case TIMESEC:
            NullableTimeSecHolder timeSecHolder = new NullableTimeSecHolder();
            reader.read(timeSecHolder);
            this.setSafe(index, timeSecHolder);
            break;
         case TIMEMILLI:
            NullableTimeMilliHolder timeMilliHolder = new NullableTimeMilliHolder();
            reader.read(timeMilliHolder);
            this.setSafe(index, timeMilliHolder);
            break;
         case BIGINT:
            NullableBigIntHolder bigIntHolder = new NullableBigIntHolder();
            reader.read(bigIntHolder);
            this.setSafe(index, bigIntHolder);
            break;
         case UINT8:
            NullableUInt8Holder uInt8Holder = new NullableUInt8Holder();
            reader.read(uInt8Holder);
            this.setSafe(index, uInt8Holder);
            break;
         case FLOAT8:
            NullableFloat8Holder float8Holder = new NullableFloat8Holder();
            reader.read(float8Holder);
            this.setSafe(index, float8Holder);
            break;
         case DATEMILLI:
            NullableDateMilliHolder dateMilliHolder = new NullableDateMilliHolder();
            reader.read(dateMilliHolder);
            this.setSafe(index, dateMilliHolder);
            break;
         case TIMESTAMPSEC:
            NullableTimeStampSecHolder timeStampSecHolder = new NullableTimeStampSecHolder();
            reader.read(timeStampSecHolder);
            this.setSafe(index, timeStampSecHolder);
            break;
         case TIMESTAMPMILLI:
            NullableTimeStampMilliHolder timeStampMilliHolder = new NullableTimeStampMilliHolder();
            reader.read(timeStampMilliHolder);
            this.setSafe(index, timeStampMilliHolder);
            break;
         case TIMESTAMPMICRO:
            NullableTimeStampMicroHolder timeStampMicroHolder = new NullableTimeStampMicroHolder();
            reader.read(timeStampMicroHolder);
            this.setSafe(index, timeStampMicroHolder);
            break;
         case TIMESTAMPNANO:
            NullableTimeStampNanoHolder timeStampNanoHolder = new NullableTimeStampNanoHolder();
            reader.read(timeStampNanoHolder);
            this.setSafe(index, timeStampNanoHolder);
            break;
         case TIMEMICRO:
            NullableTimeMicroHolder timeMicroHolder = new NullableTimeMicroHolder();
            reader.read(timeMicroHolder);
            this.setSafe(index, timeMicroHolder);
            break;
         case TIMENANO:
            NullableTimeNanoHolder timeNanoHolder = new NullableTimeNanoHolder();
            reader.read(timeNanoHolder);
            this.setSafe(index, timeNanoHolder);
            break;
         case INTERVALDAY:
            NullableIntervalDayHolder intervalDayHolder = new NullableIntervalDayHolder();
            reader.read(intervalDayHolder);
            this.setSafe(index, intervalDayHolder);
            break;
         case INTERVALMONTHDAYNANO:
            NullableIntervalMonthDayNanoHolder intervalMonthDayNanoHolder = new NullableIntervalMonthDayNanoHolder();
            reader.read(intervalMonthDayNanoHolder);
            this.setSafe(index, intervalMonthDayNanoHolder);
            break;
         case DECIMAL256:
            NullableDecimal256Holder decimal256Holder = new NullableDecimal256Holder();
            reader.read(decimal256Holder);
            this.setSafe(index, decimal256Holder);
            break;
         case DECIMAL:
            NullableDecimalHolder decimalHolder = new NullableDecimalHolder();
            reader.read(decimalHolder);
            this.setSafe(index, decimalHolder);
            break;
         case VARBINARY:
            NullableVarBinaryHolder varBinaryHolder = new NullableVarBinaryHolder();
            reader.read(varBinaryHolder);
            this.setSafe(index, varBinaryHolder);
            break;
         case VARCHAR:
            NullableVarCharHolder varCharHolder = new NullableVarCharHolder();
            reader.read(varCharHolder);
            this.setSafe(index, varCharHolder);
            break;
         case VIEWVARBINARY:
            NullableViewVarBinaryHolder viewVarBinaryHolder = new NullableViewVarBinaryHolder();
            reader.read(viewVarBinaryHolder);
            this.setSafe(index, viewVarBinaryHolder);
            break;
         case VIEWVARCHAR:
            NullableViewVarCharHolder viewVarCharHolder = new NullableViewVarCharHolder();
            reader.read(viewVarCharHolder);
            this.setSafe(index, viewVarCharHolder);
            break;
         case LARGEVARCHAR:
            NullableLargeVarCharHolder largeVarCharHolder = new NullableLargeVarCharHolder();
            reader.read(largeVarCharHolder);
            this.setSafe(index, largeVarCharHolder);
            break;
         case LARGEVARBINARY:
            NullableLargeVarBinaryHolder largeVarBinaryHolder = new NullableLargeVarBinaryHolder();
            reader.read(largeVarBinaryHolder);
            this.setSafe(index, largeVarBinaryHolder);
            break;
         case BIT:
            NullableBitHolder bitHolder = new NullableBitHolder();
            reader.read(bitHolder);
            this.setSafe(index, bitHolder);
            break;
         case STRUCT:
         case LIST:
            this.setTypeId(index, typeId);
            ComplexCopier.copy(reader, this.writer);
            break;
         default:
            throw new UnsupportedOperationException();
      }

   }

   public void setSafe(int index, NullableTinyIntHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TinyIntVector vector = this.getTinyIntVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableUInt1Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      UInt1Vector vector = this.getUInt1Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableUInt2Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      UInt2Vector vector = this.getUInt2Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableSmallIntHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      SmallIntVector vector = this.getSmallIntVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableFloat2Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      Float2Vector vector = this.getFloat2Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableIntHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      IntVector vector = this.getIntVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableUInt4Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      UInt4Vector vector = this.getUInt4Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableFloat4Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      Float4Vector vector = this.getFloat4Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableDateDayHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      DateDayVector vector = this.getDateDayVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableIntervalYearHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      IntervalYearVector vector = this.getIntervalYearVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeSecHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeSecVector vector = this.getTimeSecVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeMilliHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeMilliVector vector = this.getTimeMilliVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableBigIntHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      BigIntVector vector = this.getBigIntVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableUInt8Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      UInt8Vector vector = this.getUInt8Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableFloat8Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      Float8Vector vector = this.getFloat8Vector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableDateMilliHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      DateMilliVector vector = this.getDateMilliVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeStampSecHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeStampSecVector vector = this.getTimeStampSecVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeStampMilliHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeStampMilliVector vector = this.getTimeStampMilliVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeStampMicroHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeStampMicroVector vector = this.getTimeStampMicroVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeStampNanoHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeStampNanoVector vector = this.getTimeStampNanoVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeMicroHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeMicroVector vector = this.getTimeMicroVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableTimeNanoHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      TimeNanoVector vector = this.getTimeNanoVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableIntervalDayHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      IntervalDayVector vector = this.getIntervalDayVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableIntervalMonthDayNanoHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      IntervalMonthDayNanoVector vector = this.getIntervalMonthDayNanoVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableDecimal256Holder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      Decimal256Vector vector = this.getDecimal256Vector(typeId, new ArrowType.Decimal(holder.precision, holder.scale, 32 * 8));
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableDecimalHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      DecimalVector vector = this.getDecimalVector(typeId, new ArrowType.Decimal(holder.precision, holder.scale, 16 * 8));
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableVarBinaryHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      VarBinaryVector vector = this.getVarBinaryVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableVarCharHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      VarCharVector vector = this.getVarCharVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableViewVarBinaryHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      ViewVarBinaryVector vector = this.getViewVarBinaryVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableViewVarCharHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      ViewVarCharVector vector = this.getViewVarCharVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableLargeVarCharHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      LargeVarCharVector vector = this.getLargeVarCharVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableLargeVarBinaryHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      LargeVarBinaryVector vector = this.getLargeVarBinaryVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setSafe(int index, NullableBitHolder holder) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      byte typeId = this.getTypeId(index);
      BitVector vector = this.getBitVector(typeId);
      int offset = vector.getValueCount();
      vector.setValueCount(offset + 1);
      vector.setSafe(offset, holder);
      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   public void setTypeId(int index, byte typeId) {
      while(index >= this.getTypeBufferValueCapacity()) {
         this.reallocTypeBuffer();
      }

      this.typeBuffer.setByte((long)(index * 1), typeId);
   }

   private int getTypeBufferValueCapacity() {
      return (int)this.typeBuffer.capacity() / 1;
   }

   public void setOffset(int index, int offset) {
      while((long)index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      this.offsetBuffer.setInt((long)index * 4L, offset);
   }

   private long getOffsetBufferValueCapacity() {
      return this.offsetBuffer.capacity() / 4L;
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      if (this.isNull(index)) {
         return 0;
      } else {
         int offset = this.offsetBuffer.getInt((long)index * 4L);
         return this.getVector(index).hashCode(offset, hasher);
      }
   }

   public int hashCode(int index) {
      return this.hashCode(index, SimpleHasher.INSTANCE);
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public String getName() {
      return this.name;
   }

   private void setNegative(long start, long end) {
      for(long i = start; i < end; ++i) {
         this.typeBuffer.setByte(i, -1);
      }

   }

   public FieldVector addOrGet(String name, FieldType fieldType, Class clazz) {
      return this.internalStruct.addOrGet(name, fieldType, clazz);
   }

   public FieldVector getChild(String name, Class clazz) {
      return this.internalStruct.getChild(name, clazz);
   }

   public VectorWithOrdinal getChildVectorWithOrdinal(String name) {
      return this.internalStruct.getChildVectorWithOrdinal(name);
   }

   public int size() {
      return this.internalStruct.size();
   }

   public void setInitialCapacity(int valueCount, double density) {
      for(ValueVector vector : this.internalStruct) {
         if (vector instanceof DensityAwareVector) {
            ((DensityAwareVector)vector).setInitialCapacity(valueCount, density);
         } else {
            vector.setInitialCapacity(valueCount);
         }
      }

   }

   public void setNull(int index) {
      throw new UnsupportedOperationException("The method setNull() is not supported on DenseUnionVector.");
   }

   static {
      INTERNAL_STRUCT_TYPE = new FieldType(false, ArrowType.Struct.INSTANCE, (DictionaryEncoding)null, (Map)null);
   }

   private class TransferImpl implements TransferPair {
      private final TransferPair[] internalTransferPairs;
      private final DenseUnionVector to;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this.internalTransferPairs = new TransferPair[DenseUnionVector.this.nextTypeId];
         this.to = new DenseUnionVector(name, allocator, (FieldType)null, callBack);
         DenseUnionVector.this.internalStruct.makeTransferPair(this.to.internalStruct);
         this.createTransferPairs();
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this.internalTransferPairs = new TransferPair[DenseUnionVector.this.nextTypeId];
         this.to = new DenseUnionVector(field.getName(), allocator, (FieldType)null, callBack);
         DenseUnionVector.this.internalStruct.makeTransferPair(this.to.internalStruct);
         this.createTransferPairs();
      }

      public TransferImpl(DenseUnionVector to) {
         this.internalTransferPairs = new TransferPair[DenseUnionVector.this.nextTypeId];
         this.to = to;
         DenseUnionVector.this.internalStruct.makeTransferPair(to.internalStruct);
         this.createTransferPairs();
      }

      private void createTransferPairs() {
         for(int i = 0; i < DenseUnionVector.this.nextTypeId; ++i) {
            ValueVector srcVec = DenseUnionVector.this.internalStruct.getVectorById(i);
            ValueVector dstVec = this.to.internalStruct.getVectorById(i);
            this.to.typeFields[i] = DenseUnionVector.this.typeFields[i];
            this.to.typeMapFields[i] = DenseUnionVector.this.typeMapFields[i];
            this.to.childVectors[i] = dstVec;
            this.internalTransferPairs[i] = srcVec.makeTransferPair(dstVec);
         }

      }

      public void transfer() {
         this.to.clear();
         ReferenceManager refManager = DenseUnionVector.this.typeBuffer.getReferenceManager();
         this.to.typeBuffer = refManager.transferOwnership(DenseUnionVector.this.typeBuffer, this.to.allocator).getTransferredBuffer();
         refManager = DenseUnionVector.this.offsetBuffer.getReferenceManager();
         this.to.offsetBuffer = refManager.transferOwnership(DenseUnionVector.this.offsetBuffer, this.to.allocator).getTransferredBuffer();

         for(int i = 0; i < DenseUnionVector.this.nextTypeId; ++i) {
            if (this.internalTransferPairs[i] != null) {
               this.internalTransferPairs[i].transfer();
               this.to.childVectors[i] = this.internalTransferPairs[i].getTo();
            }
         }

         this.to.valueCount = DenseUnionVector.this.valueCount;
         DenseUnionVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         this.to.clear();
         int startPoint = startIndex * 1;
         int sliceLength = length * 1;
         ArrowBuf slicedBuffer = DenseUnionVector.this.typeBuffer.slice((long)startPoint, (long)sliceLength);
         ReferenceManager refManager = slicedBuffer.getReferenceManager();
         this.to.typeBuffer = refManager.transferOwnership(slicedBuffer, this.to.allocator).getTransferredBuffer();

         while(this.to.offsetBuffer.capacity() < (long)length * 4L) {
            this.to.reallocOffsetBuffer();
         }

         int[] typeCounts = new int[DenseUnionVector.this.nextTypeId];
         int[] typeStarts = new int[DenseUnionVector.this.nextTypeId];

         for(int i = 0; i < typeCounts.length; ++i) {
            typeCounts[i] = 0;
            typeStarts[i] = -1;
         }

         for(int i = startIndex; i < startIndex + length; ++i) {
            byte typeId = DenseUnionVector.this.typeBuffer.getByte((long)i);
            if (typeId >= 0) {
               this.to.offsetBuffer.setInt((long)(i - startIndex) * 4L, typeCounts[typeId]);
               int var10002 = typeCounts[typeId]++;
               if (typeStarts[typeId] == -1) {
                  typeStarts[typeId] = DenseUnionVector.this.offsetBuffer.getInt((long)i * 4L);
               }
            }
         }

         for(int i = 0; i < DenseUnionVector.this.nextTypeId; ++i) {
            if (typeCounts[i] > 0 && typeStarts[i] != -1) {
               this.internalTransferPairs[i].splitAndTransfer(typeStarts[i], typeCounts[i]);
               this.to.childVectors[i] = this.internalTransferPairs[i].getTo();
            }
         }

         this.to.setValueCount(length);
      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         this.to.copyFrom(from, to, DenseUnionVector.this);
      }
   }
}

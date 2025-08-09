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
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.FixedSizeBinaryVector;
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
import org.apache.arrow.vector.TimeStampMicroTZVector;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.TimeStampMilliTZVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TimeStampNanoTZVector;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.TimeStampSecTZVector;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.UInt1Vector;
import org.apache.arrow.vector.UInt2Vector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.UInt8Vector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VarBinaryVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.ViewVarBinaryVector;
import org.apache.arrow.vector.ViewVarCharVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.impl.ComplexCopier;
import org.apache.arrow.vector.complex.impl.UnionReader;
import org.apache.arrow.vector.complex.impl.UnionWriter;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.ComplexHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.holders.NullableFixedSizeBinaryHolder;
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
import org.apache.arrow.vector.holders.NullableTimeStampMicroTZHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMilliTZHolder;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.NullableTimeStampNanoTZHolder;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.NullableTimeStampSecTZHolder;
import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.holders.NullableVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.holders.UnionHolder;
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
import org.apache.arrow.vector.util.ValueVectorUtility;

public class UnionVector extends AbstractContainerVector implements FieldVector {
   int valueCount;
   NonNullableStructVector internalStruct;
   protected ArrowBuf typeBuffer;
   private StructVector structVector;
   private ListVector listVector;
   private ListViewVector listViewVector;
   private MapVector mapVector;
   private FieldReader reader;
   private int singleType = 0;
   private ValueVector singleVector;
   private int typeBufferAllocationSizeInBytes;
   private final FieldType fieldType;
   private final Field[] typeIds = new Field[128];
   public static final byte TYPE_WIDTH = 1;
   private static final FieldType INTERNAL_STRUCT_TYPE;
   private TinyIntVector tinyIntVector;
   private UInt1Vector uInt1Vector;
   private UInt2Vector uInt2Vector;
   private SmallIntVector smallIntVector;
   private Float2Vector float2Vector;
   private IntVector intVector;
   private UInt4Vector uInt4Vector;
   private Float4Vector float4Vector;
   private DateDayVector dateDayVector;
   private IntervalYearVector intervalYearVector;
   private TimeSecVector timeSecVector;
   private TimeMilliVector timeMilliVector;
   private BigIntVector bigIntVector;
   private UInt8Vector uInt8Vector;
   private Float8Vector float8Vector;
   private DateMilliVector dateMilliVector;
   private DurationVector durationVector;
   private TimeStampSecVector timeStampSecVector;
   private TimeStampMilliVector timeStampMilliVector;
   private TimeStampMicroVector timeStampMicroVector;
   private TimeStampNanoVector timeStampNanoVector;
   private TimeStampSecTZVector timeStampSecTZVector;
   private TimeStampMilliTZVector timeStampMilliTZVector;
   private TimeStampMicroTZVector timeStampMicroTZVector;
   private TimeStampNanoTZVector timeStampNanoTZVector;
   private TimeMicroVector timeMicroVector;
   private TimeNanoVector timeNanoVector;
   private IntervalDayVector intervalDayVector;
   private IntervalMonthDayNanoVector intervalMonthDayNanoVector;
   private Decimal256Vector decimal256Vector;
   private DecimalVector decimalVector;
   private FixedSizeBinaryVector fixedSizeBinaryVector;
   private VarBinaryVector varBinaryVector;
   private VarCharVector varCharVector;
   private ViewVarBinaryVector viewVarBinaryVector;
   private ViewVarCharVector viewVarCharVector;
   private LargeVarCharVector largeVarCharVector;
   private LargeVarBinaryVector largeVarBinaryVector;
   private BitVector bitVector;
   UnionWriter writer;

   public static UnionVector empty(String name, BufferAllocator allocator) {
      FieldType fieldType = FieldType.nullable(new ArrowType.Union(UnionMode.Sparse, (int[])null));
      return new UnionVector(name, allocator, fieldType, (CallBack)null);
   }

   public UnionVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      super(name, allocator, callBack);
      this.fieldType = fieldType;
      this.internalStruct = new NonNullableStructVector("internal", allocator, INTERNAL_STRUCT_TYPE, callBack, AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE, false);
      this.typeBuffer = allocator.getEmpty();
      this.typeBufferAllocationSizeInBytes = 3970;
   }

   public BufferAllocator getAllocator() {
      return this.allocator;
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.UNION;
   }

   public void initializeChildrenFromFields(List children) {
      int count = 0;

      for(Field child : children) {
         int typeId = Types.getMinorTypeForArrowType(child.getType()).ordinal();
         if (this.fieldType != null) {
            int[] typeIds = ((ArrowType.Union)this.fieldType.getType()).getTypeIds();
            if (typeIds != null) {
               typeId = typeIds[count++];
            }
         }

         this.typeIds[typeId] = child;
      }

      this.internalStruct.initializeChildrenFromFields(children);
   }

   public List getChildrenFromFields() {
      return this.internalStruct.getChildrenFromFields();
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (ownBuffers.size() != 1) {
         throw new IllegalArgumentException("Illegal buffer count, expected 1, got: " + ownBuffers.size());
      } else {
         ArrowBuf buffer = (ArrowBuf)ownBuffers.get(0);
         this.typeBuffer.getReferenceManager().release();
         this.typeBuffer = buffer.getReferenceManager().retain(buffer, this.allocator);
         this.typeBufferAllocationSizeInBytes = LargeMemoryUtil.checkedCastToInt(this.typeBuffer.capacity());
         this.valueCount = fieldNode.getLength();
      }
   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(1);
      this.setReaderAndWriterIndex();
      result.add(this.typeBuffer);
      return result;
   }

   private void setReaderAndWriterIndex() {
      this.typeBuffer.readerIndex(0L);
      this.typeBuffer.writerIndex((long)(this.valueCount * 1));
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use geFieldBuffers");
   }

   private String fieldName(Types.MinorType type) {
      return type.name().toLowerCase();
   }

   private FieldType fieldType(Types.MinorType type) {
      return FieldType.nullable(type.getType());
   }

   private FieldVector addOrGet(Types.MinorType minorType, Class c) {
      return this.addOrGet((String)null, (Types.MinorType)minorType, c);
   }

   private FieldVector addOrGet(String name, Types.MinorType minorType, ArrowType arrowType, Class c) {
      return this.internalStruct.addOrGet(name == null ? this.fieldName(minorType) : name, FieldType.nullable(arrowType), c);
   }

   private FieldVector addOrGet(String name, Types.MinorType minorType, Class c) {
      return this.internalStruct.addOrGet(name == null ? this.fieldName(minorType) : name, this.fieldType(minorType), c);
   }

   public long getValidityBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getTypeBufferAddress() {
      return this.typeBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getOffsetBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getTypeBuffer() {
      return this.typeBuffer;
   }

   public ArrowBuf getValidityBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getDataBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getOffsetBuffer() {
      throw new UnsupportedOperationException();
   }

   public StructVector getStruct() {
      if (this.structVector == null) {
         int vectorCount = this.internalStruct.size();
         this.structVector = (StructVector)this.addOrGet(Types.MinorType.STRUCT, StructVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.structVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.structVector;
   }

   public TinyIntVector getTinyIntVector() {
      return this.getTinyIntVector((String)null);
   }

   public TinyIntVector getTinyIntVector(String name) {
      if (this.tinyIntVector == null) {
         int vectorCount = this.internalStruct.size();
         this.tinyIntVector = (TinyIntVector)this.addOrGet(name, Types.MinorType.TINYINT, TinyIntVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.tinyIntVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.tinyIntVector;
   }

   public UInt1Vector getUInt1Vector() {
      return this.getUInt1Vector((String)null);
   }

   public UInt1Vector getUInt1Vector(String name) {
      if (this.uInt1Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.uInt1Vector = (UInt1Vector)this.addOrGet(name, Types.MinorType.UINT1, UInt1Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.uInt1Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.uInt1Vector;
   }

   public UInt2Vector getUInt2Vector() {
      return this.getUInt2Vector((String)null);
   }

   public UInt2Vector getUInt2Vector(String name) {
      if (this.uInt2Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.uInt2Vector = (UInt2Vector)this.addOrGet(name, Types.MinorType.UINT2, UInt2Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.uInt2Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.uInt2Vector;
   }

   public SmallIntVector getSmallIntVector() {
      return this.getSmallIntVector((String)null);
   }

   public SmallIntVector getSmallIntVector(String name) {
      if (this.smallIntVector == null) {
         int vectorCount = this.internalStruct.size();
         this.smallIntVector = (SmallIntVector)this.addOrGet(name, Types.MinorType.SMALLINT, SmallIntVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.smallIntVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.smallIntVector;
   }

   public Float2Vector getFloat2Vector() {
      return this.getFloat2Vector((String)null);
   }

   public Float2Vector getFloat2Vector(String name) {
      if (this.float2Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.float2Vector = (Float2Vector)this.addOrGet(name, Types.MinorType.FLOAT2, Float2Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.float2Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.float2Vector;
   }

   public IntVector getIntVector() {
      return this.getIntVector((String)null);
   }

   public IntVector getIntVector(String name) {
      if (this.intVector == null) {
         int vectorCount = this.internalStruct.size();
         this.intVector = (IntVector)this.addOrGet(name, Types.MinorType.INT, IntVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.intVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.intVector;
   }

   public UInt4Vector getUInt4Vector() {
      return this.getUInt4Vector((String)null);
   }

   public UInt4Vector getUInt4Vector(String name) {
      if (this.uInt4Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.uInt4Vector = (UInt4Vector)this.addOrGet(name, Types.MinorType.UINT4, UInt4Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.uInt4Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.uInt4Vector;
   }

   public Float4Vector getFloat4Vector() {
      return this.getFloat4Vector((String)null);
   }

   public Float4Vector getFloat4Vector(String name) {
      if (this.float4Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.float4Vector = (Float4Vector)this.addOrGet(name, Types.MinorType.FLOAT4, Float4Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.float4Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.float4Vector;
   }

   public DateDayVector getDateDayVector() {
      return this.getDateDayVector((String)null);
   }

   public DateDayVector getDateDayVector(String name) {
      if (this.dateDayVector == null) {
         int vectorCount = this.internalStruct.size();
         this.dateDayVector = (DateDayVector)this.addOrGet(name, Types.MinorType.DATEDAY, DateDayVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.dateDayVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.dateDayVector;
   }

   public IntervalYearVector getIntervalYearVector() {
      return this.getIntervalYearVector((String)null);
   }

   public IntervalYearVector getIntervalYearVector(String name) {
      if (this.intervalYearVector == null) {
         int vectorCount = this.internalStruct.size();
         this.intervalYearVector = (IntervalYearVector)this.addOrGet(name, Types.MinorType.INTERVALYEAR, IntervalYearVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.intervalYearVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.intervalYearVector;
   }

   public TimeSecVector getTimeSecVector() {
      return this.getTimeSecVector((String)null);
   }

   public TimeSecVector getTimeSecVector(String name) {
      if (this.timeSecVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeSecVector = (TimeSecVector)this.addOrGet(name, Types.MinorType.TIMESEC, TimeSecVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeSecVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeSecVector;
   }

   public TimeMilliVector getTimeMilliVector() {
      return this.getTimeMilliVector((String)null);
   }

   public TimeMilliVector getTimeMilliVector(String name) {
      if (this.timeMilliVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeMilliVector = (TimeMilliVector)this.addOrGet(name, Types.MinorType.TIMEMILLI, TimeMilliVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeMilliVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeMilliVector;
   }

   public BigIntVector getBigIntVector() {
      return this.getBigIntVector((String)null);
   }

   public BigIntVector getBigIntVector(String name) {
      if (this.bigIntVector == null) {
         int vectorCount = this.internalStruct.size();
         this.bigIntVector = (BigIntVector)this.addOrGet(name, Types.MinorType.BIGINT, BigIntVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.bigIntVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.bigIntVector;
   }

   public UInt8Vector getUInt8Vector() {
      return this.getUInt8Vector((String)null);
   }

   public UInt8Vector getUInt8Vector(String name) {
      if (this.uInt8Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.uInt8Vector = (UInt8Vector)this.addOrGet(name, Types.MinorType.UINT8, UInt8Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.uInt8Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.uInt8Vector;
   }

   public Float8Vector getFloat8Vector() {
      return this.getFloat8Vector((String)null);
   }

   public Float8Vector getFloat8Vector(String name) {
      if (this.float8Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.float8Vector = (Float8Vector)this.addOrGet(name, Types.MinorType.FLOAT8, Float8Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.float8Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.float8Vector;
   }

   public DateMilliVector getDateMilliVector() {
      return this.getDateMilliVector((String)null);
   }

   public DateMilliVector getDateMilliVector(String name) {
      if (this.dateMilliVector == null) {
         int vectorCount = this.internalStruct.size();
         this.dateMilliVector = (DateMilliVector)this.addOrGet(name, Types.MinorType.DATEMILLI, DateMilliVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.dateMilliVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.dateMilliVector;
   }

   public DurationVector getDurationVector() {
      if (this.durationVector == null) {
         throw new IllegalArgumentException("No Duration present. Provide ArrowType argument to create a new vector");
      } else {
         return this.durationVector;
      }
   }

   public DurationVector getDurationVector(ArrowType arrowType) {
      return this.getDurationVector((String)null, arrowType);
   }

   public DurationVector getDurationVector(String name, ArrowType arrowType) {
      if (this.durationVector == null) {
         int vectorCount = this.internalStruct.size();
         this.durationVector = (DurationVector)this.addOrGet(name, Types.MinorType.DURATION, arrowType, DurationVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.durationVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.durationVector;
   }

   public TimeStampSecVector getTimeStampSecVector() {
      return this.getTimeStampSecVector((String)null);
   }

   public TimeStampSecVector getTimeStampSecVector(String name) {
      if (this.timeStampSecVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampSecVector = (TimeStampSecVector)this.addOrGet(name, Types.MinorType.TIMESTAMPSEC, TimeStampSecVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampSecVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampSecVector;
   }

   public TimeStampMilliVector getTimeStampMilliVector() {
      return this.getTimeStampMilliVector((String)null);
   }

   public TimeStampMilliVector getTimeStampMilliVector(String name) {
      if (this.timeStampMilliVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampMilliVector = (TimeStampMilliVector)this.addOrGet(name, Types.MinorType.TIMESTAMPMILLI, TimeStampMilliVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampMilliVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampMilliVector;
   }

   public TimeStampMicroVector getTimeStampMicroVector() {
      return this.getTimeStampMicroVector((String)null);
   }

   public TimeStampMicroVector getTimeStampMicroVector(String name) {
      if (this.timeStampMicroVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampMicroVector = (TimeStampMicroVector)this.addOrGet(name, Types.MinorType.TIMESTAMPMICRO, TimeStampMicroVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampMicroVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampMicroVector;
   }

   public TimeStampNanoVector getTimeStampNanoVector() {
      return this.getTimeStampNanoVector((String)null);
   }

   public TimeStampNanoVector getTimeStampNanoVector(String name) {
      if (this.timeStampNanoVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampNanoVector = (TimeStampNanoVector)this.addOrGet(name, Types.MinorType.TIMESTAMPNANO, TimeStampNanoVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampNanoVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampNanoVector;
   }

   public TimeStampSecTZVector getTimeStampSecTZVector() {
      if (this.timeStampSecTZVector == null) {
         throw new IllegalArgumentException("No TimeStampSecTZ present. Provide ArrowType argument to create a new vector");
      } else {
         return this.timeStampSecTZVector;
      }
   }

   public TimeStampSecTZVector getTimeStampSecTZVector(ArrowType arrowType) {
      return this.getTimeStampSecTZVector((String)null, arrowType);
   }

   public TimeStampSecTZVector getTimeStampSecTZVector(String name, ArrowType arrowType) {
      if (this.timeStampSecTZVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampSecTZVector = (TimeStampSecTZVector)this.addOrGet(name, Types.MinorType.TIMESTAMPSECTZ, arrowType, TimeStampSecTZVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampSecTZVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampSecTZVector;
   }

   public TimeStampMilliTZVector getTimeStampMilliTZVector() {
      if (this.timeStampMilliTZVector == null) {
         throw new IllegalArgumentException("No TimeStampMilliTZ present. Provide ArrowType argument to create a new vector");
      } else {
         return this.timeStampMilliTZVector;
      }
   }

   public TimeStampMilliTZVector getTimeStampMilliTZVector(ArrowType arrowType) {
      return this.getTimeStampMilliTZVector((String)null, arrowType);
   }

   public TimeStampMilliTZVector getTimeStampMilliTZVector(String name, ArrowType arrowType) {
      if (this.timeStampMilliTZVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampMilliTZVector = (TimeStampMilliTZVector)this.addOrGet(name, Types.MinorType.TIMESTAMPMILLITZ, arrowType, TimeStampMilliTZVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampMilliTZVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampMilliTZVector;
   }

   public TimeStampMicroTZVector getTimeStampMicroTZVector() {
      if (this.timeStampMicroTZVector == null) {
         throw new IllegalArgumentException("No TimeStampMicroTZ present. Provide ArrowType argument to create a new vector");
      } else {
         return this.timeStampMicroTZVector;
      }
   }

   public TimeStampMicroTZVector getTimeStampMicroTZVector(ArrowType arrowType) {
      return this.getTimeStampMicroTZVector((String)null, arrowType);
   }

   public TimeStampMicroTZVector getTimeStampMicroTZVector(String name, ArrowType arrowType) {
      if (this.timeStampMicroTZVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampMicroTZVector = (TimeStampMicroTZVector)this.addOrGet(name, Types.MinorType.TIMESTAMPMICROTZ, arrowType, TimeStampMicroTZVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampMicroTZVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampMicroTZVector;
   }

   public TimeStampNanoTZVector getTimeStampNanoTZVector() {
      if (this.timeStampNanoTZVector == null) {
         throw new IllegalArgumentException("No TimeStampNanoTZ present. Provide ArrowType argument to create a new vector");
      } else {
         return this.timeStampNanoTZVector;
      }
   }

   public TimeStampNanoTZVector getTimeStampNanoTZVector(ArrowType arrowType) {
      return this.getTimeStampNanoTZVector((String)null, arrowType);
   }

   public TimeStampNanoTZVector getTimeStampNanoTZVector(String name, ArrowType arrowType) {
      if (this.timeStampNanoTZVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeStampNanoTZVector = (TimeStampNanoTZVector)this.addOrGet(name, Types.MinorType.TIMESTAMPNANOTZ, arrowType, TimeStampNanoTZVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeStampNanoTZVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeStampNanoTZVector;
   }

   public TimeMicroVector getTimeMicroVector() {
      return this.getTimeMicroVector((String)null);
   }

   public TimeMicroVector getTimeMicroVector(String name) {
      if (this.timeMicroVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeMicroVector = (TimeMicroVector)this.addOrGet(name, Types.MinorType.TIMEMICRO, TimeMicroVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeMicroVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeMicroVector;
   }

   public TimeNanoVector getTimeNanoVector() {
      return this.getTimeNanoVector((String)null);
   }

   public TimeNanoVector getTimeNanoVector(String name) {
      if (this.timeNanoVector == null) {
         int vectorCount = this.internalStruct.size();
         this.timeNanoVector = (TimeNanoVector)this.addOrGet(name, Types.MinorType.TIMENANO, TimeNanoVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.timeNanoVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.timeNanoVector;
   }

   public IntervalDayVector getIntervalDayVector() {
      return this.getIntervalDayVector((String)null);
   }

   public IntervalDayVector getIntervalDayVector(String name) {
      if (this.intervalDayVector == null) {
         int vectorCount = this.internalStruct.size();
         this.intervalDayVector = (IntervalDayVector)this.addOrGet(name, Types.MinorType.INTERVALDAY, IntervalDayVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.intervalDayVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.intervalDayVector;
   }

   public IntervalMonthDayNanoVector getIntervalMonthDayNanoVector() {
      return this.getIntervalMonthDayNanoVector((String)null);
   }

   public IntervalMonthDayNanoVector getIntervalMonthDayNanoVector(String name) {
      if (this.intervalMonthDayNanoVector == null) {
         int vectorCount = this.internalStruct.size();
         this.intervalMonthDayNanoVector = (IntervalMonthDayNanoVector)this.addOrGet(name, Types.MinorType.INTERVALMONTHDAYNANO, IntervalMonthDayNanoVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.intervalMonthDayNanoVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.intervalMonthDayNanoVector;
   }

   public Decimal256Vector getDecimal256Vector() {
      if (this.decimal256Vector == null) {
         throw new IllegalArgumentException("No Decimal256 present. Provide ArrowType argument to create a new vector");
      } else {
         return this.decimal256Vector;
      }
   }

   public Decimal256Vector getDecimal256Vector(ArrowType arrowType) {
      return this.getDecimal256Vector((String)null, arrowType);
   }

   public Decimal256Vector getDecimal256Vector(String name, ArrowType arrowType) {
      if (this.decimal256Vector == null) {
         int vectorCount = this.internalStruct.size();
         this.decimal256Vector = (Decimal256Vector)this.addOrGet(name, Types.MinorType.DECIMAL256, arrowType, Decimal256Vector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.decimal256Vector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.decimal256Vector;
   }

   public DecimalVector getDecimalVector() {
      if (this.decimalVector == null) {
         throw new IllegalArgumentException("No Decimal present. Provide ArrowType argument to create a new vector");
      } else {
         return this.decimalVector;
      }
   }

   public DecimalVector getDecimalVector(ArrowType arrowType) {
      return this.getDecimalVector((String)null, arrowType);
   }

   public DecimalVector getDecimalVector(String name, ArrowType arrowType) {
      if (this.decimalVector == null) {
         int vectorCount = this.internalStruct.size();
         this.decimalVector = (DecimalVector)this.addOrGet(name, Types.MinorType.DECIMAL, arrowType, DecimalVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.decimalVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.decimalVector;
   }

   public FixedSizeBinaryVector getFixedSizeBinaryVector() {
      if (this.fixedSizeBinaryVector == null) {
         throw new IllegalArgumentException("No FixedSizeBinary present. Provide ArrowType argument to create a new vector");
      } else {
         return this.fixedSizeBinaryVector;
      }
   }

   public FixedSizeBinaryVector getFixedSizeBinaryVector(ArrowType arrowType) {
      return this.getFixedSizeBinaryVector((String)null, arrowType);
   }

   public FixedSizeBinaryVector getFixedSizeBinaryVector(String name, ArrowType arrowType) {
      if (this.fixedSizeBinaryVector == null) {
         int vectorCount = this.internalStruct.size();
         this.fixedSizeBinaryVector = (FixedSizeBinaryVector)this.addOrGet(name, Types.MinorType.FIXEDSIZEBINARY, arrowType, FixedSizeBinaryVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.fixedSizeBinaryVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.fixedSizeBinaryVector;
   }

   public VarBinaryVector getVarBinaryVector() {
      return this.getVarBinaryVector((String)null);
   }

   public VarBinaryVector getVarBinaryVector(String name) {
      if (this.varBinaryVector == null) {
         int vectorCount = this.internalStruct.size();
         this.varBinaryVector = (VarBinaryVector)this.addOrGet(name, Types.MinorType.VARBINARY, VarBinaryVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.varBinaryVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.varBinaryVector;
   }

   public VarCharVector getVarCharVector() {
      return this.getVarCharVector((String)null);
   }

   public VarCharVector getVarCharVector(String name) {
      if (this.varCharVector == null) {
         int vectorCount = this.internalStruct.size();
         this.varCharVector = (VarCharVector)this.addOrGet(name, Types.MinorType.VARCHAR, VarCharVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.varCharVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.varCharVector;
   }

   public ViewVarBinaryVector getViewVarBinaryVector() {
      return this.getViewVarBinaryVector((String)null);
   }

   public ViewVarBinaryVector getViewVarBinaryVector(String name) {
      if (this.viewVarBinaryVector == null) {
         int vectorCount = this.internalStruct.size();
         this.viewVarBinaryVector = (ViewVarBinaryVector)this.addOrGet(name, Types.MinorType.VIEWVARBINARY, ViewVarBinaryVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.viewVarBinaryVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.viewVarBinaryVector;
   }

   public ViewVarCharVector getViewVarCharVector() {
      return this.getViewVarCharVector((String)null);
   }

   public ViewVarCharVector getViewVarCharVector(String name) {
      if (this.viewVarCharVector == null) {
         int vectorCount = this.internalStruct.size();
         this.viewVarCharVector = (ViewVarCharVector)this.addOrGet(name, Types.MinorType.VIEWVARCHAR, ViewVarCharVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.viewVarCharVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.viewVarCharVector;
   }

   public LargeVarCharVector getLargeVarCharVector() {
      return this.getLargeVarCharVector((String)null);
   }

   public LargeVarCharVector getLargeVarCharVector(String name) {
      if (this.largeVarCharVector == null) {
         int vectorCount = this.internalStruct.size();
         this.largeVarCharVector = (LargeVarCharVector)this.addOrGet(name, Types.MinorType.LARGEVARCHAR, LargeVarCharVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.largeVarCharVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.largeVarCharVector;
   }

   public LargeVarBinaryVector getLargeVarBinaryVector() {
      return this.getLargeVarBinaryVector((String)null);
   }

   public LargeVarBinaryVector getLargeVarBinaryVector(String name) {
      if (this.largeVarBinaryVector == null) {
         int vectorCount = this.internalStruct.size();
         this.largeVarBinaryVector = (LargeVarBinaryVector)this.addOrGet(name, Types.MinorType.LARGEVARBINARY, LargeVarBinaryVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.largeVarBinaryVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.largeVarBinaryVector;
   }

   public BitVector getBitVector() {
      return this.getBitVector((String)null);
   }

   public BitVector getBitVector(String name) {
      if (this.bitVector == null) {
         int vectorCount = this.internalStruct.size();
         this.bitVector = (BitVector)this.addOrGet(name, Types.MinorType.BIT, BitVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.bitVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.bitVector;
   }

   public ListVector getList() {
      if (this.listVector == null) {
         int vectorCount = this.internalStruct.size();
         this.listVector = (ListVector)this.addOrGet(Types.MinorType.LIST, ListVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.listVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.listVector;
   }

   public ListViewVector getListView() {
      if (this.listViewVector == null) {
         int vectorCount = this.internalStruct.size();
         this.listViewVector = (ListViewVector)this.addOrGet(Types.MinorType.LISTVIEW, ListViewVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.listViewVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.listViewVector;
   }

   public MapVector getMap() {
      if (this.mapVector == null) {
         throw new IllegalArgumentException("No map present. Provide ArrowType argument to create a new vector");
      } else {
         return this.mapVector;
      }
   }

   public MapVector getMap(ArrowType arrowType) {
      return this.getMap((String)null, arrowType);
   }

   public MapVector getMap(String name, ArrowType arrowType) {
      if (this.mapVector == null) {
         int vectorCount = this.internalStruct.size();
         this.mapVector = (MapVector)this.addOrGet(name, Types.MinorType.MAP, arrowType, MapVector.class);
         if (this.internalStruct.size() > vectorCount) {
            this.mapVector.allocateNew();
            if (this.callBack != null) {
               this.callBack.doWork();
            }
         }
      }

      return this.mapVector;
   }

   public int getTypeValue(int index) {
      return this.typeBuffer.getByte((long)(index * 1));
   }

   public void allocateNew() throws OutOfMemoryException {
      this.clear();
      this.internalStruct.allocateNew();

      try {
         this.allocateTypeBuffer();
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
            return true;
         } catch (Exception var3) {
            this.clear();
            return false;
         }
      }
   }

   private void allocateTypeBuffer() {
      this.typeBuffer = this.allocator.buffer((long)this.typeBufferAllocationSizeInBytes);
      this.typeBuffer.readerIndex(0L);
      this.typeBuffer.setZero(0L, this.typeBuffer.capacity());
   }

   public void reAlloc() {
      this.internalStruct.reAlloc();
      this.reallocTypeBuffer();
   }

   private void reallocTypeBuffer() {
      long currentBufferCapacity = this.typeBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.typeBufferAllocationSizeInBytes > 0) {
            newAllocationSize = (long)this.typeBufferAllocationSizeInBytes;
         } else {
            newAllocationSize = 7940L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);

      assert newAllocationSize >= 1L;

      if (newAllocationSize > BaseValueVector.MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Unable to expand the buffer");
      } else {
         ArrowBuf newBuf = this.allocator.buffer((long)LargeMemoryUtil.checkedCastToInt(newAllocationSize));
         newBuf.setBytes(0L, this.typeBuffer, 0L, currentBufferCapacity);
         newBuf.setZero(currentBufferCapacity, newBuf.capacity() - currentBufferCapacity);
         this.typeBuffer.getReferenceManager().release(1);
         this.typeBuffer = newBuf;
         this.typeBufferAllocationSizeInBytes = (int)newAllocationSize;
      }
   }

   public void setInitialCapacity(int numRecords) {
   }

   public int getValueCapacity() {
      return Math.min(this.getTypeBufferValueCapacity(), this.internalStruct.getValueCapacity());
   }

   public void close() {
      this.clear();
   }

   public void clear() {
      this.valueCount = 0;
      this.typeBuffer.getReferenceManager().release();
      this.typeBuffer = this.allocator.getEmpty();
      this.internalStruct.clear();
   }

   public void reset() {
      this.valueCount = 0;
      this.typeBuffer.setZero(0L, this.typeBuffer.capacity());
      this.internalStruct.reset();
   }

   public Field getField() {
      List<Field> childFields = new ArrayList();
      List<FieldVector> children = this.internalStruct.getChildren();
      int[] typeIds = new int[children.size()];

      for(ValueVector v : children) {
         typeIds[childFields.size()] = v.getMinorType().ordinal();
         childFields.add(v.getField());
      }

      FieldType fieldType;
      if (this.fieldType == null) {
         fieldType = FieldType.nullable(new ArrowType.Union(UnionMode.Sparse, typeIds));
      } else {
         UnionMode mode = ((ArrowType.Union)this.fieldType.getType()).getMode();
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
      return new TransferImpl((UnionVector)target);
   }

   public void copyFrom(int inIndex, int outIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      UnionVector fromCast = (UnionVector)from;
      fromCast.getReader().setPosition(inIndex);
      this.getWriter().setPosition(outIndex);
      ComplexCopier.copy(fromCast.reader, this.writer);
   }

   public void copyFromSafe(int inIndex, int outIndex, ValueVector from) {
      this.copyFrom(inIndex, outIndex, from);
   }

   public FieldVector addVector(FieldVector v) {
      String name = v.getName().isEmpty() ? this.fieldName(v.getMinorType()) : v.getName();
      Preconditions.checkState(this.internalStruct.getChild(name) == null, String.format("%s vector already exists", name));
      FieldVector newVector = this.internalStruct.addOrGet(name, v.getField().getFieldType(), v.getClass());
      v.makeTransferPair(newVector).transfer();
      this.internalStruct.putChild(name, newVector);
      if (this.callBack != null) {
         this.callBack.doWork();
      }

      return newVector;
   }

   public void directAddVector(FieldVector v) {
      String name = this.fieldName(v.getMinorType());
      Preconditions.checkState(this.internalStruct.getChild(name) == null, String.format("%s vector already exists", name));
      this.internalStruct.putChild(name, v);
      if (this.callBack != null) {
         this.callBack.doWork();
      }

   }

   public FieldReader getReader() {
      if (this.reader == null) {
         this.reader = new UnionReader(this);
      }

      return this.reader;
   }

   public FieldWriter getWriter() {
      if (this.writer == null) {
         this.writer = new UnionWriter(this);
      }

      return this.writer;
   }

   public int getBufferSize() {
      return this.valueCount == 0 ? 0 : this.valueCount * 1 + this.internalStruct.getBufferSize();
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         long bufferSize = 0L;

         for(ValueVector v : this) {
            bufferSize += (long)v.getBufferSizeFor(valueCount);
         }

         return (int)bufferSize + valueCount * 1;
      }
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      List<ArrowBuf> list = new ArrayList();
      this.setReaderAndWriterIndex();
      if (this.getBufferSize() != 0) {
         list.add(this.typeBuffer);
         list.addAll(Arrays.asList(this.internalStruct.getBuffers(clear)));
      }

      if (clear) {
         this.valueCount = 0;
         this.typeBuffer.getReferenceManager().retain();
         this.typeBuffer.getReferenceManager().release();
         this.typeBuffer = this.allocator.getEmpty();
      }

      return (ArrowBuf[])list.toArray(new ArrowBuf[list.size()]);
   }

   public Iterator iterator() {
      return this.internalStruct.iterator();
   }

   public ValueVector getVector(int index) {
      return this.getVector(index, (ArrowType)null);
   }

   public ValueVector getVector(int index, ArrowType arrowType) {
      int type = this.typeBuffer.getByte((long)(index * 1));
      return this.getVectorByType(type, arrowType);
   }

   public ValueVector getVectorByType(int typeId) {
      return this.getVectorByType(typeId, (ArrowType)null);
   }

   public ValueVector getVectorByType(int typeId, ArrowType arrowType) {
      Field type = this.typeIds[typeId];
      String name = null;
      Types.MinorType minorType;
      if (type == null) {
         minorType = Types.MinorType.values()[typeId];
      } else {
         minorType = Types.getMinorTypeForArrowType(type.getType());
         name = type.getName();
      }

      switch (minorType) {
         case NULL:
            return null;
         case TINYINT:
            return this.getTinyIntVector(name);
         case UINT1:
            return this.getUInt1Vector(name);
         case UINT2:
            return this.getUInt2Vector(name);
         case SMALLINT:
            return this.getSmallIntVector(name);
         case FLOAT2:
            return this.getFloat2Vector(name);
         case INT:
            return this.getIntVector(name);
         case UINT4:
            return this.getUInt4Vector(name);
         case FLOAT4:
            return this.getFloat4Vector(name);
         case DATEDAY:
            return this.getDateDayVector(name);
         case INTERVALYEAR:
            return this.getIntervalYearVector(name);
         case TIMESEC:
            return this.getTimeSecVector(name);
         case TIMEMILLI:
            return this.getTimeMilliVector(name);
         case BIGINT:
            return this.getBigIntVector(name);
         case UINT8:
            return this.getUInt8Vector(name);
         case FLOAT8:
            return this.getFloat8Vector(name);
         case DATEMILLI:
            return this.getDateMilliVector(name);
         case DURATION:
            return this.getDurationVector(name, arrowType);
         case TIMESTAMPSEC:
            return this.getTimeStampSecVector(name);
         case TIMESTAMPMILLI:
            return this.getTimeStampMilliVector(name);
         case TIMESTAMPMICRO:
            return this.getTimeStampMicroVector(name);
         case TIMESTAMPNANO:
            return this.getTimeStampNanoVector(name);
         case TIMESTAMPSECTZ:
            return this.getTimeStampSecTZVector(name, arrowType);
         case TIMESTAMPMILLITZ:
            return this.getTimeStampMilliTZVector(name, arrowType);
         case TIMESTAMPMICROTZ:
            return this.getTimeStampMicroTZVector(name, arrowType);
         case TIMESTAMPNANOTZ:
            return this.getTimeStampNanoTZVector(name, arrowType);
         case TIMEMICRO:
            return this.getTimeMicroVector(name);
         case TIMENANO:
            return this.getTimeNanoVector(name);
         case INTERVALDAY:
            return this.getIntervalDayVector(name);
         case INTERVALMONTHDAYNANO:
            return this.getIntervalMonthDayNanoVector(name);
         case DECIMAL256:
            return this.getDecimal256Vector(name, arrowType);
         case DECIMAL:
            return this.getDecimalVector(name, arrowType);
         case FIXEDSIZEBINARY:
            return this.getFixedSizeBinaryVector(name, arrowType);
         case VARBINARY:
            return this.getVarBinaryVector(name);
         case VARCHAR:
            return this.getVarCharVector(name);
         case VIEWVARBINARY:
            return this.getViewVarBinaryVector(name);
         case VIEWVARCHAR:
            return this.getViewVarCharVector(name);
         case LARGEVARCHAR:
            return this.getLargeVarCharVector(name);
         case LARGEVARBINARY:
            return this.getLargeVarBinaryVector(name);
         case BIT:
            return this.getBitVector(name);
         case STRUCT:
            return this.getStruct();
         case LIST:
            return this.getList();
         case LISTVIEW:
            return this.getListView();
         case MAP:
            return this.getMap(name, arrowType);
         default:
            String var10002 = String.valueOf(Types.MinorType.values()[typeId]);
            throw new UnsupportedOperationException("Cannot support type: " + var10002);
      }
   }

   public Object getObject(int index) {
      ValueVector vector = this.getVector(index);
      if (vector != null) {
         return vector.isNull(index) ? null : vector.getObject(index);
      } else {
         return null;
      }
   }

   public byte[] get(int index) {
      return null;
   }

   public void get(int index, ComplexHolder holder) {
   }

   public void get(int index, UnionHolder holder) {
      FieldReader reader = new UnionReader(this);
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
      }

      this.internalStruct.setValueCount(valueCount);
   }

   public void setSafe(int index, UnionHolder holder) {
      this.setSafe(index, (UnionHolder)holder, (ArrowType)null);
   }

   public void setSafe(int index, UnionHolder holder, ArrowType arrowType) {
      FieldReader reader = holder.reader;
      if (this.writer == null) {
         this.writer = new UnionWriter(this);
      }

      this.writer.setPosition(index);
      Types.MinorType type = reader.getMinorType();
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
         case DURATION:
            NullableDurationHolder durationHolder = new NullableDurationHolder();
            reader.read(durationHolder);
            this.setSafe(index, durationHolder, arrowType);
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
         case TIMESTAMPSECTZ:
            NullableTimeStampSecTZHolder timeStampSecTZHolder = new NullableTimeStampSecTZHolder();
            reader.read(timeStampSecTZHolder);
            this.setSafe(index, timeStampSecTZHolder, arrowType);
            break;
         case TIMESTAMPMILLITZ:
            NullableTimeStampMilliTZHolder timeStampMilliTZHolder = new NullableTimeStampMilliTZHolder();
            reader.read(timeStampMilliTZHolder);
            this.setSafe(index, timeStampMilliTZHolder, arrowType);
            break;
         case TIMESTAMPMICROTZ:
            NullableTimeStampMicroTZHolder timeStampMicroTZHolder = new NullableTimeStampMicroTZHolder();
            reader.read(timeStampMicroTZHolder);
            this.setSafe(index, timeStampMicroTZHolder, arrowType);
            break;
         case TIMESTAMPNANOTZ:
            NullableTimeStampNanoTZHolder timeStampNanoTZHolder = new NullableTimeStampNanoTZHolder();
            reader.read(timeStampNanoTZHolder);
            this.setSafe(index, timeStampNanoTZHolder, arrowType);
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
            this.setSafe(index, decimal256Holder, arrowType);
            break;
         case DECIMAL:
            NullableDecimalHolder decimalHolder = new NullableDecimalHolder();
            reader.read(decimalHolder);
            this.setSafe(index, decimalHolder, arrowType);
            break;
         case FIXEDSIZEBINARY:
            NullableFixedSizeBinaryHolder fixedSizeBinaryHolder = new NullableFixedSizeBinaryHolder();
            reader.read(fixedSizeBinaryHolder);
            this.setSafe(index, fixedSizeBinaryHolder, arrowType);
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
            ComplexCopier.copy(reader, this.writer);
            break;
         case LIST:
            ComplexCopier.copy(reader, this.writer);
            break;
         default:
            throw new UnsupportedOperationException();
      }

   }

   public void setSafe(int index, NullableTinyIntHolder holder) {
      this.setType(index, Types.MinorType.TINYINT);
      this.getTinyIntVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableUInt1Holder holder) {
      this.setType(index, Types.MinorType.UINT1);
      this.getUInt1Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableUInt2Holder holder) {
      this.setType(index, Types.MinorType.UINT2);
      this.getUInt2Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableSmallIntHolder holder) {
      this.setType(index, Types.MinorType.SMALLINT);
      this.getSmallIntVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableFloat2Holder holder) {
      this.setType(index, Types.MinorType.FLOAT2);
      this.getFloat2Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableIntHolder holder) {
      this.setType(index, Types.MinorType.INT);
      this.getIntVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableUInt4Holder holder) {
      this.setType(index, Types.MinorType.UINT4);
      this.getUInt4Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableFloat4Holder holder) {
      this.setType(index, Types.MinorType.FLOAT4);
      this.getFloat4Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableDateDayHolder holder) {
      this.setType(index, Types.MinorType.DATEDAY);
      this.getDateDayVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableIntervalYearHolder holder) {
      this.setType(index, Types.MinorType.INTERVALYEAR);
      this.getIntervalYearVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeSecHolder holder) {
      this.setType(index, Types.MinorType.TIMESEC);
      this.getTimeSecVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeMilliHolder holder) {
      this.setType(index, Types.MinorType.TIMEMILLI);
      this.getTimeMilliVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableBigIntHolder holder) {
      this.setType(index, Types.MinorType.BIGINT);
      this.getBigIntVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableUInt8Holder holder) {
      this.setType(index, Types.MinorType.UINT8);
      this.getUInt8Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableFloat8Holder holder) {
      this.setType(index, Types.MinorType.FLOAT8);
      this.getFloat8Vector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableDateMilliHolder holder) {
      this.setType(index, Types.MinorType.DATEMILLI);
      this.getDateMilliVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableDurationHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.DURATION);
      this.getDurationVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampSecHolder holder) {
      this.setType(index, Types.MinorType.TIMESTAMPSEC);
      this.getTimeStampSecVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampMilliHolder holder) {
      this.setType(index, Types.MinorType.TIMESTAMPMILLI);
      this.getTimeStampMilliVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampMicroHolder holder) {
      this.setType(index, Types.MinorType.TIMESTAMPMICRO);
      this.getTimeStampMicroVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampNanoHolder holder) {
      this.setType(index, Types.MinorType.TIMESTAMPNANO);
      this.getTimeStampNanoVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampSecTZHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.TIMESTAMPSECTZ);
      this.getTimeStampSecTZVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampMilliTZHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.TIMESTAMPMILLITZ);
      this.getTimeStampMilliTZVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampMicroTZHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.TIMESTAMPMICROTZ);
      this.getTimeStampMicroTZVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeStampNanoTZHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.TIMESTAMPNANOTZ);
      this.getTimeStampNanoTZVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeMicroHolder holder) {
      this.setType(index, Types.MinorType.TIMEMICRO);
      this.getTimeMicroVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableTimeNanoHolder holder) {
      this.setType(index, Types.MinorType.TIMENANO);
      this.getTimeNanoVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableIntervalDayHolder holder) {
      this.setType(index, Types.MinorType.INTERVALDAY);
      this.getIntervalDayVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableIntervalMonthDayNanoHolder holder) {
      this.setType(index, Types.MinorType.INTERVALMONTHDAYNANO);
      this.getIntervalMonthDayNanoVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableDecimal256Holder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.DECIMAL256);
      this.getDecimal256Vector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableDecimalHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.DECIMAL);
      this.getDecimalVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableFixedSizeBinaryHolder holder, ArrowType arrowType) {
      this.setType(index, Types.MinorType.FIXEDSIZEBINARY);
      this.getFixedSizeBinaryVector((String)null, arrowType).setSafe(index, holder);
   }

   public void setSafe(int index, NullableVarBinaryHolder holder) {
      this.setType(index, Types.MinorType.VARBINARY);
      this.getVarBinaryVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableVarCharHolder holder) {
      this.setType(index, Types.MinorType.VARCHAR);
      this.getVarCharVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableViewVarBinaryHolder holder) {
      this.setType(index, Types.MinorType.VIEWVARBINARY);
      this.getViewVarBinaryVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableViewVarCharHolder holder) {
      this.setType(index, Types.MinorType.VIEWVARCHAR);
      this.getViewVarCharVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableLargeVarCharHolder holder) {
      this.setType(index, Types.MinorType.LARGEVARCHAR);
      this.getLargeVarCharVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableLargeVarBinaryHolder holder) {
      this.setType(index, Types.MinorType.LARGEVARBINARY);
      this.getLargeVarBinaryVector((String)null).setSafe(index, holder);
   }

   public void setSafe(int index, NullableBitHolder holder) {
      this.setType(index, Types.MinorType.BIT);
      this.getBitVector((String)null).setSafe(index, holder);
   }

   public void setType(int index, Types.MinorType type) {
      while(index >= this.getTypeBufferValueCapacity()) {
         this.reallocTypeBuffer();
      }

      this.typeBuffer.setByte((long)(index * 1), (byte)type.ordinal());
   }

   private int getTypeBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.typeBuffer.capacity() / 1L);
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      ValueVector vec = this.getVector(index);
      return vec == null ? 0 : vec.hashCode(index, hasher);
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public String getName() {
      return this.name;
   }

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount());
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
      throw new UnsupportedOperationException("The method setNull() is not supported on UnionVector.");
   }

   static {
      INTERNAL_STRUCT_TYPE = new FieldType(false, ArrowType.Struct.INSTANCE, (DictionaryEncoding)null, (Map)null);
   }

   private class TransferImpl implements TransferPair {
      private final TransferPair internalStructVectorTransferPair;
      private final UnionVector to;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this.to = new UnionVector(name, allocator, (FieldType)null, callBack);
         this.internalStructVectorTransferPair = UnionVector.this.internalStruct.makeTransferPair(this.to.internalStruct);
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this.to = new UnionVector(field.getName(), allocator, (FieldType)null, callBack);
         this.internalStructVectorTransferPair = UnionVector.this.internalStruct.makeTransferPair(this.to.internalStruct);
      }

      public TransferImpl(UnionVector to) {
         this.to = to;
         this.internalStructVectorTransferPair = UnionVector.this.internalStruct.makeTransferPair(to.internalStruct);
      }

      public void transfer() {
         this.to.clear();
         ReferenceManager refManager = UnionVector.this.typeBuffer.getReferenceManager();
         this.to.typeBuffer = refManager.transferOwnership(UnionVector.this.typeBuffer, this.to.allocator).getTransferredBuffer();
         this.internalStructVectorTransferPair.transfer();
         this.to.valueCount = UnionVector.this.valueCount;
         UnionVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= UnionVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, UnionVector.this.valueCount);
         this.to.clear();
         this.internalStructVectorTransferPair.splitAndTransfer(startIndex, length);
         int startPoint = startIndex * 1;
         int sliceLength = length * 1;
         ArrowBuf slicedBuffer = UnionVector.this.typeBuffer.slice((long)startPoint, (long)sliceLength);
         ReferenceManager refManager = slicedBuffer.getReferenceManager();
         this.to.typeBuffer = refManager.transferOwnership(slicedBuffer, this.to.allocator).getTransferredBuffer();
         this.to.setValueCount(length);
      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         this.to.copyFrom(from, to, UnionVector.this);
      }
   }
}

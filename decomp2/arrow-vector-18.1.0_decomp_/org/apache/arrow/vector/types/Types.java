package org.apache.arrow.vector.types;

import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.ExtensionTypeVector;
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
import org.apache.arrow.vector.NullVector;
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
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.LargeListViewVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.RunEndEncodedVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.complex.impl.BigIntWriterImpl;
import org.apache.arrow.vector.complex.impl.BitWriterImpl;
import org.apache.arrow.vector.complex.impl.DateDayWriterImpl;
import org.apache.arrow.vector.complex.impl.DateMilliWriterImpl;
import org.apache.arrow.vector.complex.impl.Decimal256WriterImpl;
import org.apache.arrow.vector.complex.impl.DecimalWriterImpl;
import org.apache.arrow.vector.complex.impl.DenseUnionWriter;
import org.apache.arrow.vector.complex.impl.DurationWriterImpl;
import org.apache.arrow.vector.complex.impl.FixedSizeBinaryWriterImpl;
import org.apache.arrow.vector.complex.impl.Float2WriterImpl;
import org.apache.arrow.vector.complex.impl.Float4WriterImpl;
import org.apache.arrow.vector.complex.impl.Float8WriterImpl;
import org.apache.arrow.vector.complex.impl.IntWriterImpl;
import org.apache.arrow.vector.complex.impl.IntervalDayWriterImpl;
import org.apache.arrow.vector.complex.impl.IntervalMonthDayNanoWriterImpl;
import org.apache.arrow.vector.complex.impl.IntervalYearWriterImpl;
import org.apache.arrow.vector.complex.impl.LargeVarBinaryWriterImpl;
import org.apache.arrow.vector.complex.impl.LargeVarCharWriterImpl;
import org.apache.arrow.vector.complex.impl.NullableStructWriter;
import org.apache.arrow.vector.complex.impl.SmallIntWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeMicroWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeMilliWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeNanoWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeSecWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampMicroTZWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampMicroWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampMilliTZWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampMilliWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampNanoTZWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampNanoWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampSecTZWriterImpl;
import org.apache.arrow.vector.complex.impl.TimeStampSecWriterImpl;
import org.apache.arrow.vector.complex.impl.TinyIntWriterImpl;
import org.apache.arrow.vector.complex.impl.UInt1WriterImpl;
import org.apache.arrow.vector.complex.impl.UInt2WriterImpl;
import org.apache.arrow.vector.complex.impl.UInt4WriterImpl;
import org.apache.arrow.vector.complex.impl.UInt8WriterImpl;
import org.apache.arrow.vector.complex.impl.UnionLargeListViewWriter;
import org.apache.arrow.vector.complex.impl.UnionLargeListWriter;
import org.apache.arrow.vector.complex.impl.UnionListWriter;
import org.apache.arrow.vector.complex.impl.UnionWriter;
import org.apache.arrow.vector.complex.impl.VarBinaryWriterImpl;
import org.apache.arrow.vector.complex.impl.VarCharWriterImpl;
import org.apache.arrow.vector.complex.impl.ViewVarBinaryWriterImpl;
import org.apache.arrow.vector.complex.impl.ViewVarCharWriterImpl;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;

public class Types {
   public static MinorType getMinorTypeForArrowType(ArrowType arrowType) {
      return (MinorType)arrowType.accept(new ArrowType.ArrowTypeVisitor() {
         public MinorType visit(ArrowType.Null type) {
            return Types.MinorType.NULL;
         }

         public MinorType visit(ArrowType.Struct type) {
            return Types.MinorType.STRUCT;
         }

         public MinorType visit(ArrowType.List type) {
            return Types.MinorType.LIST;
         }

         public MinorType visit(ArrowType.FixedSizeList type) {
            return Types.MinorType.FIXED_SIZE_LIST;
         }

         public MinorType visit(ArrowType.Union type) {
            switch (type.getMode()) {
               case Sparse:
                  return Types.MinorType.UNION;
               case Dense:
                  return Types.MinorType.DENSEUNION;
               default:
                  throw new IllegalArgumentException("only Dense or Sparse unions supported: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.Map type) {
            return Types.MinorType.MAP;
         }

         public MinorType visit(ArrowType.LargeList type) {
            return Types.MinorType.LARGELIST;
         }

         public MinorType visit(ArrowType.Int type) {
            switch (type.getBitWidth()) {
               case 8:
                  return type.getIsSigned() ? Types.MinorType.TINYINT : Types.MinorType.UINT1;
               case 16:
                  return type.getIsSigned() ? Types.MinorType.SMALLINT : Types.MinorType.UINT2;
               case 32:
                  return type.getIsSigned() ? Types.MinorType.INT : Types.MinorType.UINT4;
               case 64:
                  return type.getIsSigned() ? Types.MinorType.BIGINT : Types.MinorType.UINT8;
               default:
                  throw new IllegalArgumentException("only 8, 16, 32, 64 supported: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.FloatingPoint type) {
            switch (type.getPrecision()) {
               case HALF:
                  return Types.MinorType.FLOAT2;
               case SINGLE:
                  return Types.MinorType.FLOAT4;
               case DOUBLE:
                  return Types.MinorType.FLOAT8;
               default:
                  throw new IllegalArgumentException("unknown precision: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.Utf8 type) {
            return Types.MinorType.VARCHAR;
         }

         public MinorType visit(ArrowType.Utf8View type) {
            return Types.MinorType.VIEWVARCHAR;
         }

         public MinorType visit(ArrowType.LargeUtf8 type) {
            return Types.MinorType.LARGEVARCHAR;
         }

         public MinorType visit(ArrowType.Binary type) {
            return Types.MinorType.VARBINARY;
         }

         public MinorType visit(ArrowType.BinaryView type) {
            return Types.MinorType.VIEWVARBINARY;
         }

         public MinorType visit(ArrowType.LargeBinary type) {
            return Types.MinorType.LARGEVARBINARY;
         }

         public MinorType visit(ArrowType.Bool type) {
            return Types.MinorType.BIT;
         }

         public MinorType visit(ArrowType.Decimal type) {
            return type.getBitWidth() == 256 ? Types.MinorType.DECIMAL256 : Types.MinorType.DECIMAL;
         }

         public MinorType visit(ArrowType.FixedSizeBinary type) {
            return Types.MinorType.FIXEDSIZEBINARY;
         }

         public MinorType visit(ArrowType.Date type) {
            switch (type.getUnit()) {
               case DAY:
                  return Types.MinorType.DATEDAY;
               case MILLISECOND:
                  return Types.MinorType.DATEMILLI;
               default:
                  throw new IllegalArgumentException("unknown unit: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.Time type) {
            switch (type.getUnit()) {
               case SECOND:
                  return Types.MinorType.TIMESEC;
               case MILLISECOND:
                  return Types.MinorType.TIMEMILLI;
               case MICROSECOND:
                  return Types.MinorType.TIMEMICRO;
               case NANOSECOND:
                  return Types.MinorType.TIMENANO;
               default:
                  throw new IllegalArgumentException("unknown unit: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.Timestamp type) {
            String tz = type.getTimezone();
            switch (type.getUnit()) {
               case SECOND:
                  return tz == null ? Types.MinorType.TIMESTAMPSEC : Types.MinorType.TIMESTAMPSECTZ;
               case MILLISECOND:
                  return tz == null ? Types.MinorType.TIMESTAMPMILLI : Types.MinorType.TIMESTAMPMILLITZ;
               case MICROSECOND:
                  return tz == null ? Types.MinorType.TIMESTAMPMICRO : Types.MinorType.TIMESTAMPMICROTZ;
               case NANOSECOND:
                  return tz == null ? Types.MinorType.TIMESTAMPNANO : Types.MinorType.TIMESTAMPNANOTZ;
               default:
                  throw new IllegalArgumentException("unknown unit: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.Interval type) {
            switch (type.getUnit()) {
               case DAY_TIME:
                  return Types.MinorType.INTERVALDAY;
               case YEAR_MONTH:
                  return Types.MinorType.INTERVALYEAR;
               case MONTH_DAY_NANO:
                  return Types.MinorType.INTERVALMONTHDAYNANO;
               default:
                  throw new IllegalArgumentException("unknown unit: " + String.valueOf(type));
            }
         }

         public MinorType visit(ArrowType.Duration type) {
            return Types.MinorType.DURATION;
         }

         public MinorType visit(ArrowType.ListView type) {
            return Types.MinorType.LISTVIEW;
         }

         public MinorType visit(ArrowType.LargeListView type) {
            return Types.MinorType.LARGELISTVIEW;
         }

         public MinorType visit(ArrowType.ExtensionType type) {
            return Types.MinorType.EXTENSIONTYPE;
         }

         public MinorType visit(ArrowType.RunEndEncoded type) {
            return Types.MinorType.RUNENDENCODED;
         }
      });
   }

   public static enum MinorType {
      NULL(ArrowType.Null.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new NullVector(field.getName());
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return null;
         }
      },
      STRUCT(ArrowType.Struct.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new StructVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new NullableStructWriter((StructVector)vector);
         }
      },
      TINYINT(new ArrowType.Int(8, true)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TinyIntVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TinyIntWriterImpl((TinyIntVector)vector);
         }
      },
      SMALLINT(new ArrowType.Int(16, true)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new SmallIntVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new SmallIntWriterImpl((SmallIntVector)vector);
         }
      },
      INT(new ArrowType.Int(32, true)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new IntVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new IntWriterImpl((IntVector)vector);
         }
      },
      BIGINT(new ArrowType.Int(64, true)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new BigIntVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new BigIntWriterImpl((BigIntVector)vector);
         }
      },
      DATEDAY(new ArrowType.Date(DateUnit.DAY)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new DateDayVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new DateDayWriterImpl((DateDayVector)vector);
         }
      },
      DATEMILLI(new ArrowType.Date(DateUnit.MILLISECOND)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new DateMilliVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new DateMilliWriterImpl((DateMilliVector)vector);
         }
      },
      TIMESEC(new ArrowType.Time(TimeUnit.SECOND, 32)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeSecVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeSecWriterImpl((TimeSecVector)vector);
         }
      },
      TIMEMILLI(new ArrowType.Time(TimeUnit.MILLISECOND, 32)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeMilliVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeMilliWriterImpl((TimeMilliVector)vector);
         }
      },
      TIMEMICRO(new ArrowType.Time(TimeUnit.MICROSECOND, 64)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeMicroVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeMicroWriterImpl((TimeMicroVector)vector);
         }
      },
      TIMENANO(new ArrowType.Time(TimeUnit.NANOSECOND, 64)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeNanoVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeNanoWriterImpl((TimeNanoVector)vector);
         }
      },
      TIMESTAMPSEC(new ArrowType.Timestamp(TimeUnit.SECOND, (String)null)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampSecVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampSecWriterImpl((TimeStampSecVector)vector);
         }
      },
      TIMESTAMPMILLI(new ArrowType.Timestamp(TimeUnit.MILLISECOND, (String)null)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampMilliVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampMilliWriterImpl((TimeStampMilliVector)vector);
         }
      },
      TIMESTAMPMICRO(new ArrowType.Timestamp(TimeUnit.MICROSECOND, (String)null)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampMicroVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampMicroWriterImpl((TimeStampMicroVector)vector);
         }
      },
      TIMESTAMPNANO(new ArrowType.Timestamp(TimeUnit.NANOSECOND, (String)null)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampNanoVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampNanoWriterImpl((TimeStampNanoVector)vector);
         }
      },
      INTERVALDAY(new ArrowType.Interval(IntervalUnit.DAY_TIME)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new IntervalDayVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new IntervalDayWriterImpl((IntervalDayVector)vector);
         }
      },
      INTERVALMONTHDAYNANO(new ArrowType.Interval(IntervalUnit.MONTH_DAY_NANO)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new IntervalMonthDayNanoVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new IntervalMonthDayNanoWriterImpl((IntervalMonthDayNanoVector)vector);
         }
      },
      DURATION((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new DurationVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new DurationWriterImpl((DurationVector)vector);
         }
      },
      INTERVALYEAR(new ArrowType.Interval(IntervalUnit.YEAR_MONTH)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new IntervalYearVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new IntervalYearWriterImpl((IntervalYearVector)vector);
         }
      },
      FLOAT2(new ArrowType.FloatingPoint(FloatingPointPrecision.HALF)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new Float2Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new Float2WriterImpl((Float2Vector)vector);
         }
      },
      FLOAT4(new ArrowType.FloatingPoint(FloatingPointPrecision.SINGLE)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new Float4Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new Float4WriterImpl((Float4Vector)vector);
         }
      },
      FLOAT8(new ArrowType.FloatingPoint(FloatingPointPrecision.DOUBLE)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new Float8Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new Float8WriterImpl((Float8Vector)vector);
         }
      },
      BIT(ArrowType.Bool.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new BitVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new BitWriterImpl((BitVector)vector);
         }
      },
      VARCHAR(ArrowType.Utf8.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new VarCharVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new VarCharWriterImpl((VarCharVector)vector);
         }
      },
      VIEWVARCHAR(ArrowType.Utf8View.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new ViewVarCharVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new ViewVarCharWriterImpl((ViewVarCharVector)vector);
         }
      },
      LARGEVARCHAR(ArrowType.LargeUtf8.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new LargeVarCharVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new LargeVarCharWriterImpl((LargeVarCharVector)vector);
         }
      },
      LARGEVARBINARY(ArrowType.LargeBinary.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new LargeVarBinaryVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new LargeVarBinaryWriterImpl((LargeVarBinaryVector)vector);
         }
      },
      VARBINARY(ArrowType.Binary.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new VarBinaryVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new VarBinaryWriterImpl((VarBinaryVector)vector);
         }
      },
      VIEWVARBINARY(ArrowType.BinaryView.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new ViewVarBinaryVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new ViewVarBinaryWriterImpl((ViewVarBinaryVector)vector);
         }
      },
      DECIMAL((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new DecimalVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new DecimalWriterImpl((DecimalVector)vector);
         }
      },
      DECIMAL256((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new Decimal256Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new Decimal256WriterImpl((Decimal256Vector)vector);
         }
      },
      FIXEDSIZEBINARY((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new FixedSizeBinaryVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new FixedSizeBinaryWriterImpl((FixedSizeBinaryVector)vector);
         }
      },
      UINT1(new ArrowType.Int(8, false)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new UInt1Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UInt1WriterImpl((UInt1Vector)vector);
         }
      },
      UINT2(new ArrowType.Int(16, false)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new UInt2Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UInt2WriterImpl((UInt2Vector)vector);
         }
      },
      UINT4(new ArrowType.Int(32, false)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new UInt4Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UInt4WriterImpl((UInt4Vector)vector);
         }
      },
      UINT8(new ArrowType.Int(64, false)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new UInt8Vector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UInt8WriterImpl((UInt8Vector)vector);
         }
      },
      LIST(ArrowType.List.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new ListVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UnionListWriter((ListVector)vector);
         }
      },
      LISTVIEW(ArrowType.ListView.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new ListViewVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UnionListWriter((ListVector)vector);
         }
      },
      LARGELIST(ArrowType.LargeList.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new LargeListVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UnionLargeListWriter((LargeListVector)vector);
         }
      },
      LARGELISTVIEW(ArrowType.LargeListView.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new LargeListViewVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UnionLargeListViewWriter((LargeListViewVector)vector);
         }
      },
      FIXED_SIZE_LIST((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new FixedSizeListVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            throw new UnsupportedOperationException("FieldWriter not implemented for FixedSizeList type");
         }
      },
      UNION(new ArrowType.Union(UnionMode.Sparse, (int[])null)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            if (field.getFieldType().getDictionary() != null) {
               throw new UnsupportedOperationException("Dictionary encoding not supported for complex types");
            } else {
               return new UnionVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
            }
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UnionWriter((UnionVector)vector);
         }
      },
      DENSEUNION(new ArrowType.Union(UnionMode.Dense, (int[])null)) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            if (field.getFieldType().getDictionary() != null) {
               throw new UnsupportedOperationException("Dictionary encoding not supported for complex types");
            } else {
               return new DenseUnionVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
            }
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new DenseUnionWriter((DenseUnionVector)vector);
         }
      },
      MAP((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new MapVector(field.getName(), allocator, field.getFieldType(), schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new UnionListWriter((MapVector)vector);
         }
      },
      TIMESTAMPSECTZ((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampSecTZVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampSecTZWriterImpl((TimeStampSecTZVector)vector);
         }
      },
      TIMESTAMPMILLITZ((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampMilliTZVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampMilliTZWriterImpl((TimeStampMilliTZVector)vector);
         }
      },
      TIMESTAMPMICROTZ((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampMicroTZVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampMicroTZWriterImpl((TimeStampMicroTZVector)vector);
         }
      },
      TIMESTAMPNANOTZ((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new TimeStampNanoTZVector(field, allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return new TimeStampNanoTZWriterImpl((TimeStampNanoTZVector)vector);
         }
      },
      EXTENSIONTYPE((ArrowType)null) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return ((ArrowType.ExtensionType)field.getFieldType().getType()).getNewVector(field.getName(), field.getFieldType(), allocator);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            return ((ExtensionTypeVector)vector).getUnderlyingVector().getMinorType().getNewFieldWriter(vector);
         }
      },
      RUNENDENCODED(ArrowType.RunEndEncoded.INSTANCE) {
         public FieldVector getNewVector(Field field, BufferAllocator allocator, CallBack schemaChangeCallback) {
            return new RunEndEncodedVector(field, allocator, schemaChangeCallback);
         }

         public FieldWriter getNewFieldWriter(ValueVector vector) {
            throw new UnsupportedOperationException("FieldWriter for run-end encoded vector is not implemented yet.");
         }
      };

      private final ArrowType type;

      private MinorType(ArrowType type) {
         this.type = type;
      }

      public final ArrowType getType() {
         if (this.type == null) {
            throw new UnsupportedOperationException("Cannot get simple type for type " + this.name());
         } else {
            return this.type;
         }
      }

      public final FieldVector getNewVector(String name, FieldType fieldType, BufferAllocator allocator, CallBack schemaChangeCallback) {
         return this.getNewVector(new Field(name, fieldType, (List)null), allocator, schemaChangeCallback);
      }

      public abstract FieldVector getNewVector(Field var1, BufferAllocator var2, CallBack var3);

      public abstract FieldWriter getNewFieldWriter(ValueVector var1);

      // $FF: synthetic method
      private static MinorType[] $values() {
         return new MinorType[]{NULL, STRUCT, TINYINT, SMALLINT, INT, BIGINT, DATEDAY, DATEMILLI, TIMESEC, TIMEMILLI, TIMEMICRO, TIMENANO, TIMESTAMPSEC, TIMESTAMPMILLI, TIMESTAMPMICRO, TIMESTAMPNANO, INTERVALDAY, INTERVALMONTHDAYNANO, DURATION, INTERVALYEAR, FLOAT2, FLOAT4, FLOAT8, BIT, VARCHAR, VIEWVARCHAR, LARGEVARCHAR, LARGEVARBINARY, VARBINARY, VIEWVARBINARY, DECIMAL, DECIMAL256, FIXEDSIZEBINARY, UINT1, UINT2, UINT4, UINT8, LIST, LISTVIEW, LARGELIST, LARGELISTVIEW, FIXED_SIZE_LIST, UNION, DENSEUNION, MAP, TIMESTAMPSECTZ, TIMESTAMPMILLITZ, TIMESTAMPMICROTZ, TIMESTAMPNANOTZ, EXTENSIONTYPE, RUNENDENCODED};
      }
   }
}

package org.apache.arrow.vector.complex.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DurationVector;
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
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.complex.writer.DateDayWriter;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
import org.apache.arrow.vector.complex.writer.DurationWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.FixedSizeBinaryWriter;
import org.apache.arrow.vector.complex.writer.Float2Writer;
import org.apache.arrow.vector.complex.writer.Float4Writer;
import org.apache.arrow.vector.complex.writer.Float8Writer;
import org.apache.arrow.vector.complex.writer.IntWriter;
import org.apache.arrow.vector.complex.writer.IntervalDayWriter;
import org.apache.arrow.vector.complex.writer.IntervalMonthDayNanoWriter;
import org.apache.arrow.vector.complex.writer.IntervalYearWriter;
import org.apache.arrow.vector.complex.writer.LargeVarBinaryWriter;
import org.apache.arrow.vector.complex.writer.LargeVarCharWriter;
import org.apache.arrow.vector.complex.writer.SmallIntWriter;
import org.apache.arrow.vector.complex.writer.TimeMicroWriter;
import org.apache.arrow.vector.complex.writer.TimeMilliWriter;
import org.apache.arrow.vector.complex.writer.TimeNanoWriter;
import org.apache.arrow.vector.complex.writer.TimeSecWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMicroTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMicroWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMilliTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMilliWriter;
import org.apache.arrow.vector.complex.writer.TimeStampNanoTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampNanoWriter;
import org.apache.arrow.vector.complex.writer.TimeStampSecTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampSecWriter;
import org.apache.arrow.vector.complex.writer.TinyIntWriter;
import org.apache.arrow.vector.complex.writer.UInt1Writer;
import org.apache.arrow.vector.complex.writer.UInt2Writer;
import org.apache.arrow.vector.complex.writer.UInt4Writer;
import org.apache.arrow.vector.complex.writer.UInt8Writer;
import org.apache.arrow.vector.complex.writer.VarBinaryWriter;
import org.apache.arrow.vector.complex.writer.VarCharWriter;
import org.apache.arrow.vector.complex.writer.ViewVarBinaryWriter;
import org.apache.arrow.vector.complex.writer.ViewVarCharWriter;
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;

public class SingleStructWriter extends AbstractFieldWriter {
   protected final NonNullableStructVector container;
   private int initialCapacity;
   private final Map fields = new HashMap();

   public SingleStructWriter(NonNullableStructVector container) {
      if (container instanceof StructVector) {
         throw new IllegalArgumentException("Invalid container: " + String.valueOf(container));
      } else {
         this.container = container;
         this.initialCapacity = 0;

         for(Field child : container.getField().getChildren()) {
            Types.MinorType minorType = Types.getMinorTypeForArrowType(child.getType());
            this.addVectorAsNullable = child.isNullable();
            switch (minorType) {
               case STRUCT:
                  this.struct(child.getName());
                  break;
               case LIST:
                  this.list(child.getName());
                  break;
               case LISTVIEW:
                  this.listView(child.getName());
                  break;
               case MAP:
                  ArrowType.Map arrowType = (ArrowType.Map)child.getType();
                  this.map(child.getName(), arrowType.getKeysSorted());
                  break;
               case DENSEUNION:
                  FieldType fieldType = new FieldType(this.addVectorAsNullable, Types.MinorType.DENSEUNION.getType(), (DictionaryEncoding)null, (Map)null);
                  DenseUnionWriter writer = new DenseUnionWriter((DenseUnionVector)container.addOrGet(child.getName(), fieldType, DenseUnionVector.class), this.getNullableStructWriterFactory());
                  this.fields.put(this.handleCase(child.getName()), writer);
                  break;
               case UNION:
                  FieldType fieldType = new FieldType(this.addVectorAsNullable, Types.MinorType.UNION.getType(), (DictionaryEncoding)null, (Map)null);
                  UnionWriter writer = new UnionWriter((UnionVector)container.addOrGet(child.getName(), fieldType, UnionVector.class), this.getNullableStructWriterFactory());
                  this.fields.put(this.handleCase(child.getName()), writer);
                  break;
               case TINYINT:
                  this.tinyInt(child.getName());
                  break;
               case UINT1:
                  this.uInt1(child.getName());
                  break;
               case UINT2:
                  this.uInt2(child.getName());
                  break;
               case SMALLINT:
                  this.smallInt(child.getName());
                  break;
               case FLOAT2:
                  this.float2(child.getName());
                  break;
               case INT:
                  this.integer(child.getName());
                  break;
               case UINT4:
                  this.uInt4(child.getName());
                  break;
               case FLOAT4:
                  this.float4(child.getName());
                  break;
               case DATEDAY:
                  this.dateDay(child.getName());
                  break;
               case INTERVALYEAR:
                  this.intervalYear(child.getName());
                  break;
               case TIMESEC:
                  this.timeSec(child.getName());
                  break;
               case TIMEMILLI:
                  this.timeMilli(child.getName());
                  break;
               case BIGINT:
                  this.bigInt(child.getName());
                  break;
               case UINT8:
                  this.uInt8(child.getName());
                  break;
               case FLOAT8:
                  this.float8(child.getName());
                  break;
               case DATEMILLI:
                  this.dateMilli(child.getName());
                  break;
               case DURATION:
                  ArrowType.Duration arrowType = (ArrowType.Duration)child.getType();
                  this.duration(child.getName(), arrowType.getUnit());
                  break;
               case TIMESTAMPSEC:
                  this.timeStampSec(child.getName());
                  break;
               case TIMESTAMPMILLI:
                  this.timeStampMilli(child.getName());
                  break;
               case TIMESTAMPMICRO:
                  this.timeStampMicro(child.getName());
                  break;
               case TIMESTAMPNANO:
                  this.timeStampNano(child.getName());
                  break;
               case TIMESTAMPSECTZ:
                  ArrowType.Timestamp arrowType = (ArrowType.Timestamp)child.getType();
                  this.timeStampSecTZ(child.getName(), arrowType.getTimezone());
                  break;
               case TIMESTAMPMILLITZ:
                  ArrowType.Timestamp arrowType = (ArrowType.Timestamp)child.getType();
                  this.timeStampMilliTZ(child.getName(), arrowType.getTimezone());
                  break;
               case TIMESTAMPMICROTZ:
                  ArrowType.Timestamp arrowType = (ArrowType.Timestamp)child.getType();
                  this.timeStampMicroTZ(child.getName(), arrowType.getTimezone());
                  break;
               case TIMESTAMPNANOTZ:
                  ArrowType.Timestamp arrowType = (ArrowType.Timestamp)child.getType();
                  this.timeStampNanoTZ(child.getName(), arrowType.getTimezone());
                  break;
               case TIMEMICRO:
                  this.timeMicro(child.getName());
                  break;
               case TIMENANO:
                  this.timeNano(child.getName());
                  break;
               case INTERVALDAY:
                  this.intervalDay(child.getName());
                  break;
               case INTERVALMONTHDAYNANO:
                  this.intervalMonthDayNano(child.getName());
                  break;
               case DECIMAL256:
                  ArrowType.Decimal arrowType = (ArrowType.Decimal)child.getType();
                  this.decimal256(child.getName(), arrowType.getScale(), arrowType.getPrecision());
                  break;
               case DECIMAL:
                  ArrowType.Decimal arrowType = (ArrowType.Decimal)child.getType();
                  this.decimal(child.getName(), arrowType.getScale(), arrowType.getPrecision());
                  break;
               case FIXEDSIZEBINARY:
                  ArrowType.FixedSizeBinary arrowType = (ArrowType.FixedSizeBinary)child.getType();
                  this.fixedSizeBinary(child.getName(), arrowType.getByteWidth());
                  break;
               case VARBINARY:
                  this.varBinary(child.getName());
                  break;
               case VARCHAR:
                  this.varChar(child.getName());
                  break;
               case VIEWVARBINARY:
                  this.viewVarBinary(child.getName());
                  break;
               case VIEWVARCHAR:
                  this.viewVarChar(child.getName());
                  break;
               case LARGEVARCHAR:
                  this.largeVarChar(child.getName());
                  break;
               case LARGEVARBINARY:
                  this.largeVarBinary(child.getName());
                  break;
               case BIT:
                  this.bit(child.getName());
                  break;
               default:
                  throw new UnsupportedOperationException("Unknown type: " + String.valueOf(minorType));
            }
         }

      }
   }

   protected String handleCase(String input) {
      return input.toLowerCase();
   }

   protected NullableStructWriterFactory getNullableStructWriterFactory() {
      return NullableStructWriterFactory.getNullableStructWriterFactoryInstance();
   }

   public int getValueCapacity() {
      return this.container.getValueCapacity();
   }

   public void setInitialCapacity(int initialCapacity) {
      this.initialCapacity = initialCapacity;
      this.container.setInitialCapacity(initialCapacity);
   }

   public boolean isEmptyStruct() {
      return 0 == this.container.size();
   }

   public Field getField() {
      return this.container.getField();
   }

   public BaseWriter.StructWriter struct(String name) {
      String finalName = this.handleCase(name);
      FieldWriter writer = (FieldWriter)this.fields.get(finalName);
      if (writer == null) {
         int vectorCount = this.container.size();
         FieldType fieldType = new FieldType(this.addVectorAsNullable, Types.MinorType.STRUCT.getType(), (DictionaryEncoding)null, (Map)null);
         StructVector vector = (StructVector)this.container.addOrGet(name, fieldType, StructVector.class);
         writer = new PromotableWriter(vector, this.container, this.getNullableStructWriterFactory());
         if (vectorCount != this.container.size()) {
            writer.allocate();
         }

         writer.setPosition(this.idx());
         this.fields.put(finalName, writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.STRUCT);
      }

      return writer;
   }

   public void close() throws Exception {
      this.clear();
      this.container.close();
   }

   public void allocate() {
      this.container.allocateNew();

      for(FieldWriter w : this.fields.values()) {
         w.allocate();
      }

   }

   public void clear() {
      this.container.clear();

      for(FieldWriter w : this.fields.values()) {
         w.clear();
      }

   }

   public BaseWriter.ListWriter list(String name) {
      String finalName = this.handleCase(name);
      FieldWriter writer = (FieldWriter)this.fields.get(finalName);
      int vectorCount = this.container.size();
      if (writer == null) {
         FieldType fieldType = new FieldType(this.addVectorAsNullable, Types.MinorType.LIST.getType(), (DictionaryEncoding)null, (Map)null);
         writer = new PromotableWriter(this.container.addOrGet(name, fieldType, ListVector.class), this.container, this.getNullableStructWriterFactory());
         if (this.container.size() > vectorCount) {
            writer.allocate();
         }

         writer.setPosition(this.idx());
         this.fields.put(finalName, writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.LIST);
      }

      return writer;
   }

   public BaseWriter.ListWriter listView(String name) {
      String finalName = this.handleCase(name);
      FieldWriter writer = (FieldWriter)this.fields.get(finalName);
      int vectorCount = this.container.size();
      if (writer == null) {
         FieldType fieldType = new FieldType(this.addVectorAsNullable, Types.MinorType.LISTVIEW.getType(), (DictionaryEncoding)null, (Map)null);
         writer = new PromotableViewWriter(this.container.addOrGet(name, fieldType, ListViewVector.class), this.container, this.getNullableStructWriterFactory());
         if (this.container.size() > vectorCount) {
            writer.allocate();
         }

         writer.setPosition(this.idx());
         this.fields.put(finalName, writer);
      } else if (writer instanceof PromotableViewWriter) {
         ((PromotableViewWriter)writer).getWriter(Types.MinorType.LISTVIEW);
      } else {
         writer = ((PromotableWriter)writer).toViewWriter();
         ((PromotableViewWriter)writer).getWriter(Types.MinorType.LISTVIEW);
      }

      return writer;
   }

   public BaseWriter.MapWriter map(String name) {
      return this.map(name, false);
   }

   public BaseWriter.MapWriter map(String name, boolean keysSorted) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         MapVector v = (MapVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Map(keysSorted), (DictionaryEncoding)null, (Map)null), MapVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.MAP, new ArrowType.Map(keysSorted));
      }

      return writer;
   }

   public void setValueCount(int count) {
      this.container.setValueCount(count);
   }

   public void setPosition(int index) {
      super.setPosition(index);

      for(FieldWriter w : this.fields.values()) {
         w.setPosition(index);
      }

   }

   public void start() {
   }

   public void end() {
      this.setPosition(this.idx() + 1);
   }

   public TinyIntWriter tinyInt(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TinyIntVector v = (TinyIntVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TINYINT.getType(), (DictionaryEncoding)null, (Map)null), TinyIntVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TINYINT);
      }

      return writer;
   }

   public UInt1Writer uInt1(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         UInt1Vector v = (UInt1Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.UINT1.getType(), (DictionaryEncoding)null, (Map)null), UInt1Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.UINT1);
      }

      return writer;
   }

   public UInt2Writer uInt2(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         UInt2Vector v = (UInt2Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.UINT2.getType(), (DictionaryEncoding)null, (Map)null), UInt2Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.UINT2);
      }

      return writer;
   }

   public SmallIntWriter smallInt(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         SmallIntVector v = (SmallIntVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.SMALLINT.getType(), (DictionaryEncoding)null, (Map)null), SmallIntVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.SMALLINT);
      }

      return writer;
   }

   public Float2Writer float2(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         Float2Vector v = (Float2Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.FLOAT2.getType(), (DictionaryEncoding)null, (Map)null), Float2Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.FLOAT2);
      }

      return writer;
   }

   public IntWriter integer(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         IntVector v = (IntVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.INT.getType(), (DictionaryEncoding)null, (Map)null), IntVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.INT);
      }

      return writer;
   }

   public UInt4Writer uInt4(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         UInt4Vector v = (UInt4Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.UINT4.getType(), (DictionaryEncoding)null, (Map)null), UInt4Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.UINT4);
      }

      return writer;
   }

   public Float4Writer float4(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         Float4Vector v = (Float4Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.FLOAT4.getType(), (DictionaryEncoding)null, (Map)null), Float4Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.FLOAT4);
      }

      return writer;
   }

   public DateDayWriter dateDay(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         DateDayVector v = (DateDayVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.DATEDAY.getType(), (DictionaryEncoding)null, (Map)null), DateDayVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.DATEDAY);
      }

      return writer;
   }

   public IntervalYearWriter intervalYear(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         IntervalYearVector v = (IntervalYearVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.INTERVALYEAR.getType(), (DictionaryEncoding)null, (Map)null), IntervalYearVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.INTERVALYEAR);
      }

      return writer;
   }

   public TimeSecWriter timeSec(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeSecVector v = (TimeSecVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMESEC.getType(), (DictionaryEncoding)null, (Map)null), TimeSecVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESEC);
      }

      return writer;
   }

   public TimeMilliWriter timeMilli(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeMilliVector v = (TimeMilliVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMEMILLI.getType(), (DictionaryEncoding)null, (Map)null), TimeMilliVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMEMILLI);
      }

      return writer;
   }

   public BigIntWriter bigInt(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         BigIntVector v = (BigIntVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.BIGINT.getType(), (DictionaryEncoding)null, (Map)null), BigIntVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.BIGINT);
      }

      return writer;
   }

   public UInt8Writer uInt8(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         UInt8Vector v = (UInt8Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.UINT8.getType(), (DictionaryEncoding)null, (Map)null), UInt8Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.UINT8);
      }

      return writer;
   }

   public Float8Writer float8(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         Float8Vector v = (Float8Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.FLOAT8.getType(), (DictionaryEncoding)null, (Map)null), Float8Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.FLOAT8);
      }

      return writer;
   }

   public DateMilliWriter dateMilli(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         DateMilliVector v = (DateMilliVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.DATEMILLI.getType(), (DictionaryEncoding)null, (Map)null), DateMilliVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.DATEMILLI);
      }

      return writer;
   }

   public DurationWriter duration(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public DurationWriter duration(String name, TimeUnit unit) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         DurationVector v = (DurationVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Duration(unit), (DictionaryEncoding)null, (Map)null), DurationVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ArrowType arrowType = new ArrowType.Duration(unit);
         ((PromotableWriter)writer).getWriter(Types.MinorType.DURATION, arrowType);
      }

      return writer;
   }

   public TimeStampSecWriter timeStampSec(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampSecVector v = (TimeStampSecVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMESTAMPSEC.getType(), (DictionaryEncoding)null, (Map)null), TimeStampSecVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPSEC);
      }

      return writer;
   }

   public TimeStampMilliWriter timeStampMilli(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampMilliVector v = (TimeStampMilliVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMESTAMPMILLI.getType(), (DictionaryEncoding)null, (Map)null), TimeStampMilliVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPMILLI);
      }

      return writer;
   }

   public TimeStampMicroWriter timeStampMicro(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampMicroVector v = (TimeStampMicroVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMESTAMPMICRO.getType(), (DictionaryEncoding)null, (Map)null), TimeStampMicroVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPMICRO);
      }

      return writer;
   }

   public TimeStampNanoWriter timeStampNano(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampNanoVector v = (TimeStampNanoVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMESTAMPNANO.getType(), (DictionaryEncoding)null, (Map)null), TimeStampNanoVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPNANO);
      }

      return writer;
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name, String timezone) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampSecTZVector v = (TimeStampSecTZVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Timestamp(TimeUnit.SECOND, timezone), (DictionaryEncoding)null, (Map)null), TimeStampSecTZVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ArrowType arrowType = new ArrowType.Timestamp(TimeUnit.SECOND, timezone);
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPSECTZ, arrowType);
      }

      return writer;
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name, String timezone) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampMilliTZVector v = (TimeStampMilliTZVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Timestamp(TimeUnit.MILLISECOND, timezone), (DictionaryEncoding)null, (Map)null), TimeStampMilliTZVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ArrowType arrowType = new ArrowType.Timestamp(TimeUnit.MILLISECOND, timezone);
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPMILLITZ, arrowType);
      }

      return writer;
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name, String timezone) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampMicroTZVector v = (TimeStampMicroTZVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Timestamp(TimeUnit.MICROSECOND, timezone), (DictionaryEncoding)null, (Map)null), TimeStampMicroTZVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ArrowType arrowType = new ArrowType.Timestamp(TimeUnit.MICROSECOND, timezone);
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPMICROTZ, arrowType);
      }

      return writer;
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name, String timezone) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeStampNanoTZVector v = (TimeStampNanoTZVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Timestamp(TimeUnit.NANOSECOND, timezone), (DictionaryEncoding)null, (Map)null), TimeStampNanoTZVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ArrowType arrowType = new ArrowType.Timestamp(TimeUnit.NANOSECOND, timezone);
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMESTAMPNANOTZ, arrowType);
      }

      return writer;
   }

   public TimeMicroWriter timeMicro(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeMicroVector v = (TimeMicroVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMEMICRO.getType(), (DictionaryEncoding)null, (Map)null), TimeMicroVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMEMICRO);
      }

      return writer;
   }

   public TimeNanoWriter timeNano(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         TimeNanoVector v = (TimeNanoVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.TIMENANO.getType(), (DictionaryEncoding)null, (Map)null), TimeNanoVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.TIMENANO);
      }

      return writer;
   }

   public IntervalDayWriter intervalDay(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         IntervalDayVector v = (IntervalDayVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.INTERVALDAY.getType(), (DictionaryEncoding)null, (Map)null), IntervalDayVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.INTERVALDAY);
      }

      return writer;
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         IntervalMonthDayNanoVector v = (IntervalMonthDayNanoVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.INTERVALMONTHDAYNANO.getType(), (DictionaryEncoding)null, (Map)null), IntervalMonthDayNanoVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.INTERVALMONTHDAYNANO);
      }

      return writer;
   }

   public Decimal256Writer decimal256(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public Decimal256Writer decimal256(String name, int scale, int precision) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         Decimal256Vector v = (Decimal256Vector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Decimal(precision, scale, 256), (DictionaryEncoding)null, (Map)null), Decimal256Vector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.DECIMAL256, new ArrowType.Decimal(precision, scale, 256));
      }

      return writer;
   }

   public DecimalWriter decimal(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public DecimalWriter decimal(String name, int scale, int precision) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         DecimalVector v = (DecimalVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.Decimal(precision, scale, 128), (DictionaryEncoding)null, (Map)null), DecimalVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.DECIMAL, new ArrowType.Decimal(precision, scale, 128));
      }

      return writer;
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      Preconditions.checkNotNull(writer);
      return writer;
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name, int byteWidth) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         FixedSizeBinaryVector v = (FixedSizeBinaryVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, new ArrowType.FixedSizeBinary(byteWidth), (DictionaryEncoding)null, (Map)null), FixedSizeBinaryVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ArrowType arrowType = new ArrowType.FixedSizeBinary(byteWidth);
         ((PromotableWriter)writer).getWriter(Types.MinorType.FIXEDSIZEBINARY, arrowType);
      }

      return writer;
   }

   public VarBinaryWriter varBinary(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         VarBinaryVector v = (VarBinaryVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.VARBINARY.getType(), (DictionaryEncoding)null, (Map)null), VarBinaryVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.VARBINARY);
      }

      return writer;
   }

   public VarCharWriter varChar(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         VarCharVector v = (VarCharVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.VARCHAR.getType(), (DictionaryEncoding)null, (Map)null), VarCharVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.VARCHAR);
      }

      return writer;
   }

   public ViewVarBinaryWriter viewVarBinary(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         ViewVarBinaryVector v = (ViewVarBinaryVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.VIEWVARBINARY.getType(), (DictionaryEncoding)null, (Map)null), ViewVarBinaryVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.VIEWVARBINARY);
      }

      return writer;
   }

   public ViewVarCharWriter viewVarChar(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         ViewVarCharVector v = (ViewVarCharVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.VIEWVARCHAR.getType(), (DictionaryEncoding)null, (Map)null), ViewVarCharVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.VIEWVARCHAR);
      }

      return writer;
   }

   public LargeVarCharWriter largeVarChar(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         LargeVarCharVector v = (LargeVarCharVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.LARGEVARCHAR.getType(), (DictionaryEncoding)null, (Map)null), LargeVarCharVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.LARGEVARCHAR);
      }

      return writer;
   }

   public LargeVarBinaryWriter largeVarBinary(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         LargeVarBinaryVector v = (LargeVarBinaryVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.LARGEVARBINARY.getType(), (DictionaryEncoding)null, (Map)null), LargeVarBinaryVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.LARGEVARBINARY);
      }

      return writer;
   }

   public BitWriter bit(String name) {
      FieldWriter writer = (FieldWriter)this.fields.get(this.handleCase(name));
      if (writer == null) {
         ValueVector currentVector = this.container.getChild(name);
         BitVector v = (BitVector)this.container.addOrGet(name, new FieldType(this.addVectorAsNullable, Types.MinorType.BIT.getType(), (DictionaryEncoding)null, (Map)null), BitVector.class);
         writer = new PromotableWriter(v, this.container, this.getNullableStructWriterFactory());
         if (currentVector == null || currentVector != v) {
            if (this.initialCapacity > 0) {
               v.setInitialCapacity(this.initialCapacity);
            }

            v.allocateNewSafe();
         }

         writer.setPosition(this.idx());
         this.fields.put(this.handleCase(name), writer);
      } else if (writer instanceof PromotableWriter) {
         ((PromotableWriter)writer).getWriter(Types.MinorType.BIT);
      }

      return writer;
   }
}

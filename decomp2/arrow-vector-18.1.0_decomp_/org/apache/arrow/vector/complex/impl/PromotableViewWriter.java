package org.apache.arrow.vector.complex.impl;

import java.util.Map;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.AbstractStructVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.LargeListViewVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
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
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;

public class PromotableViewWriter extends PromotableWriter {
   public PromotableViewWriter(ValueVector v, FixedSizeListVector fixedListVector) {
      super(v, fixedListVector);
   }

   public PromotableViewWriter(ValueVector v, FixedSizeListVector fixedListVector, NullableStructWriterFactory nullableStructWriterFactory) {
      super(v, fixedListVector, nullableStructWriterFactory);
   }

   public PromotableViewWriter(ValueVector v, LargeListVector largeListVector) {
      super(v, largeListVector);
   }

   public PromotableViewWriter(ValueVector v, LargeListVector largeListVector, NullableStructWriterFactory nullableStructWriterFactory) {
      super(v, largeListVector, nullableStructWriterFactory);
   }

   public PromotableViewWriter(ValueVector v, ListVector listVector) {
      super(v, listVector);
   }

   public PromotableViewWriter(ValueVector v, ListVector listVector, NullableStructWriterFactory nullableStructWriterFactory) {
      super(v, listVector, nullableStructWriterFactory);
   }

   public PromotableViewWriter(ValueVector v, ListViewVector listViewVector, NullableStructWriterFactory nullableStructWriterFactory) {
      super(v, listViewVector, nullableStructWriterFactory);
   }

   public PromotableViewWriter(ValueVector v, LargeListViewVector largeListViewVector) {
      super(v, largeListViewVector);
   }

   public PromotableViewWriter(ValueVector v, LargeListViewVector largeListViewVector, NullableStructWriterFactory nullableStructWriterFactory) {
      super(v, largeListViewVector, nullableStructWriterFactory);
   }

   public PromotableViewWriter(ValueVector v, AbstractStructVector parentContainer) {
      super(v, parentContainer);
   }

   public PromotableViewWriter(ValueVector v, AbstractStructVector parentContainer, NullableStructWriterFactory nullableStructWriterFactory) {
      super(v, parentContainer, nullableStructWriterFactory);
   }

   protected FieldWriter getWriter(Types.MinorType type, ArrowType arrowType) {
      if (this.state == PromotableWriter.State.UNION) {
         if (this.requiresArrowType(type)) {
            this.writer = ((UnionWriter)this.writer).toViewWriter();
            ((UnionViewWriter)this.writer).getWriter(type, arrowType);
         } else {
            this.writer = ((UnionWriter)this.writer).toViewWriter();
            ((UnionViewWriter)this.writer).getWriter(type);
         }
      } else if (this.state == PromotableWriter.State.UNTYPED) {
         if (type == null) {
            return null;
         }

         if (arrowType == null) {
            arrowType = type.getType();
         }

         FieldType fieldType = new FieldType(this.addVectorAsNullable, arrowType, (DictionaryEncoding)null, (Map)null);
         ValueVector v;
         if (this.listVector != null) {
            v = this.listVector.addOrGetVector(fieldType).getVector();
         } else if (this.fixedListVector != null) {
            v = this.fixedListVector.addOrGetVector(fieldType).getVector();
         } else if (this.listViewVector != null) {
            v = this.listViewVector.addOrGetVector(fieldType).getVector();
         } else if (this.largeListVector != null) {
            v = this.largeListVector.addOrGetVector(fieldType).getVector();
         } else {
            v = this.largeListViewVector.addOrGetVector(fieldType).getVector();
         }

         v.allocateNew();
         this.setWriter(v);
         this.writer.setPosition(this.position);
      } else if (type != this.type) {
         this.promoteToUnion();
         if (this.requiresArrowType(type)) {
            this.writer = ((UnionWriter)this.writer).toViewWriter();
            ((UnionViewWriter)this.writer).getWriter(type, arrowType);
         } else {
            this.writer = ((UnionWriter)this.writer).toViewWriter();
            ((UnionViewWriter)this.writer).getWriter(type);
         }
      }

      return this.writer;
   }

   public BaseWriter.StructWriter struct() {
      return this.getWriter(Types.MinorType.LISTVIEW).struct();
   }

   public TinyIntWriter tinyInt() {
      return this.getWriter(Types.MinorType.LISTVIEW).tinyInt();
   }

   public UInt1Writer uInt1() {
      return this.getWriter(Types.MinorType.LISTVIEW).uInt1();
   }

   public UInt2Writer uInt2() {
      return this.getWriter(Types.MinorType.LISTVIEW).uInt2();
   }

   public SmallIntWriter smallInt() {
      return this.getWriter(Types.MinorType.LISTVIEW).smallInt();
   }

   public Float2Writer float2() {
      return this.getWriter(Types.MinorType.LISTVIEW).float2();
   }

   public IntWriter integer() {
      return this.getWriter(Types.MinorType.LISTVIEW).integer();
   }

   public UInt4Writer uInt4() {
      return this.getWriter(Types.MinorType.LISTVIEW).uInt4();
   }

   public Float4Writer float4() {
      return this.getWriter(Types.MinorType.LISTVIEW).float4();
   }

   public DateDayWriter dateDay() {
      return this.getWriter(Types.MinorType.LISTVIEW).dateDay();
   }

   public IntervalYearWriter intervalYear() {
      return this.getWriter(Types.MinorType.LISTVIEW).intervalYear();
   }

   public TimeSecWriter timeSec() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeSec();
   }

   public TimeMilliWriter timeMilli() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeMilli();
   }

   public BigIntWriter bigInt() {
      return this.getWriter(Types.MinorType.LISTVIEW).bigInt();
   }

   public UInt8Writer uInt8() {
      return this.getWriter(Types.MinorType.LISTVIEW).uInt8();
   }

   public Float8Writer float8() {
      return this.getWriter(Types.MinorType.LISTVIEW).float8();
   }

   public DateMilliWriter dateMilli() {
      return this.getWriter(Types.MinorType.LISTVIEW).dateMilli();
   }

   public DurationWriter duration() {
      return this.getWriter(Types.MinorType.LISTVIEW).duration();
   }

   public TimeStampSecWriter timeStampSec() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampSec();
   }

   public TimeStampMilliWriter timeStampMilli() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampMilli();
   }

   public TimeStampMicroWriter timeStampMicro() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampMicro();
   }

   public TimeStampNanoWriter timeStampNano() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampNano();
   }

   public TimeStampSecTZWriter timeStampSecTZ() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampSecTZ();
   }

   public TimeStampMilliTZWriter timeStampMilliTZ() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampMilliTZ();
   }

   public TimeStampMicroTZWriter timeStampMicroTZ() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampMicroTZ();
   }

   public TimeStampNanoTZWriter timeStampNanoTZ() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeStampNanoTZ();
   }

   public TimeMicroWriter timeMicro() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeMicro();
   }

   public TimeNanoWriter timeNano() {
      return this.getWriter(Types.MinorType.LISTVIEW).timeNano();
   }

   public IntervalDayWriter intervalDay() {
      return this.getWriter(Types.MinorType.LISTVIEW).intervalDay();
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      return this.getWriter(Types.MinorType.LISTVIEW).intervalMonthDayNano();
   }

   public Decimal256Writer decimal256() {
      return this.getWriter(Types.MinorType.LISTVIEW).decimal256();
   }

   public DecimalWriter decimal() {
      return this.getWriter(Types.MinorType.LISTVIEW).decimal();
   }

   public FixedSizeBinaryWriter fixedSizeBinary() {
      return this.getWriter(Types.MinorType.LISTVIEW).fixedSizeBinary();
   }

   public VarBinaryWriter varBinary() {
      return this.getWriter(Types.MinorType.LISTVIEW).varBinary();
   }

   public VarCharWriter varChar() {
      return this.getWriter(Types.MinorType.LISTVIEW).varChar();
   }

   public ViewVarBinaryWriter viewVarBinary() {
      return this.getWriter(Types.MinorType.LISTVIEW).viewVarBinary();
   }

   public ViewVarCharWriter viewVarChar() {
      return this.getWriter(Types.MinorType.LISTVIEW).viewVarChar();
   }

   public LargeVarCharWriter largeVarChar() {
      return this.getWriter(Types.MinorType.LISTVIEW).largeVarChar();
   }

   public LargeVarBinaryWriter largeVarBinary() {
      return this.getWriter(Types.MinorType.LISTVIEW).largeVarBinary();
   }

   public BitWriter bit() {
      return this.getWriter(Types.MinorType.LISTVIEW).bit();
   }

   public void allocate() {
      this.getWriter().allocate();
   }

   public void clear() {
      this.getWriter().clear();
   }

   public Field getField() {
      return this.getWriter().getField();
   }

   public int getValueCapacity() {
      return this.getWriter().getValueCapacity();
   }

   public void close() throws Exception {
      this.getWriter().close();
   }
}

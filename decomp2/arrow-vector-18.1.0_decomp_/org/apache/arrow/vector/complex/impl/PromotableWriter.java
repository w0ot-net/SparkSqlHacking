package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Map;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.AbstractStructVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.LargeListViewVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.Decimal256Holder;
import org.apache.arrow.vector.holders.DecimalHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.Text;
import org.apache.arrow.vector.util.TransferPair;

public class PromotableWriter extends AbstractPromotableFieldWriter {
   protected final AbstractStructVector parentContainer;
   protected final ListVector listVector;
   protected final ListViewVector listViewVector;
   protected final FixedSizeListVector fixedListVector;
   protected final LargeListVector largeListVector;
   protected final LargeListViewVector largeListViewVector;
   protected final NullableStructWriterFactory nullableStructWriterFactory;
   protected int position;
   protected static final int MAX_DECIMAL_PRECISION = 38;
   protected static final int MAX_DECIMAL256_PRECISION = 76;
   protected Types.MinorType type;
   protected ValueVector vector;
   protected UnionVector unionVector;
   protected State state;
   protected FieldWriter writer;

   public PromotableWriter(ValueVector v, AbstractStructVector parentContainer) {
      this(v, parentContainer, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public PromotableWriter(ValueVector v, AbstractStructVector parentContainer, NullableStructWriterFactory nullableStructWriterFactory) {
      this.parentContainer = parentContainer;
      this.listVector = null;
      this.listViewVector = null;
      this.fixedListVector = null;
      this.largeListVector = null;
      this.largeListViewVector = null;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
      this.init(v);
   }

   public PromotableWriter(ValueVector v, ListVector listVector) {
      this(v, listVector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public PromotableWriter(ValueVector v, FixedSizeListVector fixedListVector) {
      this(v, fixedListVector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public PromotableWriter(ValueVector v, LargeListVector largeListVector) {
      this(v, largeListVector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public PromotableWriter(ValueVector v, ListViewVector listViewVector) {
      this(v, listViewVector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public PromotableWriter(ValueVector v, LargeListViewVector largeListViewVector) {
      this(v, largeListViewVector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public PromotableWriter(ValueVector v, ListVector listVector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.listVector = listVector;
      this.listViewVector = null;
      this.parentContainer = null;
      this.fixedListVector = null;
      this.largeListVector = null;
      this.largeListViewVector = null;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
      this.init(v);
   }

   public PromotableWriter(ValueVector v, ListViewVector listViewVector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.listViewVector = listViewVector;
      this.listVector = null;
      this.parentContainer = null;
      this.fixedListVector = null;
      this.largeListVector = null;
      this.largeListViewVector = null;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
      this.init(v);
   }

   public PromotableWriter(ValueVector v, FixedSizeListVector fixedListVector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.fixedListVector = fixedListVector;
      this.parentContainer = null;
      this.listVector = null;
      this.listViewVector = null;
      this.largeListVector = null;
      this.largeListViewVector = null;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
      this.init(v);
   }

   public PromotableWriter(ValueVector v, LargeListVector largeListVector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.largeListVector = largeListVector;
      this.fixedListVector = null;
      this.parentContainer = null;
      this.listVector = null;
      this.listViewVector = null;
      this.largeListViewVector = null;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
      this.init(v);
   }

   public PromotableWriter(ValueVector v, LargeListViewVector largeListViewVector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.largeListViewVector = largeListViewVector;
      this.fixedListVector = null;
      this.parentContainer = null;
      this.listVector = null;
      this.listViewVector = null;
      this.largeListVector = null;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
      this.init(v);
   }

   private void init(ValueVector v) {
      if (v instanceof UnionVector) {
         this.state = PromotableWriter.State.UNION;
         this.unionVector = (UnionVector)v;
         this.writer = new UnionWriter(this.unionVector, this.nullableStructWriterFactory);
      } else if (v instanceof NullVector) {
         this.state = PromotableWriter.State.UNTYPED;
      } else {
         this.setWriter(v);
      }

   }

   public void setAddVectorAsNullable(boolean nullable) {
      super.setAddVectorAsNullable(nullable);
      if (this.writer instanceof AbstractFieldWriter) {
         ((AbstractFieldWriter)this.writer).setAddVectorAsNullable(nullable);
      }

   }

   protected void setWriter(ValueVector v) {
      this.state = PromotableWriter.State.SINGLE;
      this.vector = v;
      this.type = v.getMinorType();
      switch (this.type) {
         case STRUCT:
            this.writer = this.nullableStructWriterFactory.build((StructVector)this.vector);
            break;
         case LIST:
            this.writer = new UnionListWriter((ListVector)this.vector, this.nullableStructWriterFactory);
            break;
         case LISTVIEW:
            this.writer = new UnionListViewWriter((ListViewVector)this.vector, this.nullableStructWriterFactory);
            break;
         case MAP:
            this.writer = new UnionMapWriter((MapVector)this.vector);
            break;
         case UNION:
            this.writer = new UnionWriter((UnionVector)this.vector, this.nullableStructWriterFactory);
            break;
         default:
            this.writer = this.type.getNewFieldWriter(this.vector);
      }

   }

   public void writeNull() {
      FieldWriter w = this.getWriter();
      if (w != null) {
         w.writeNull();
      }

      this.setPosition(this.idx() + 1);
   }

   public void setPosition(int index) {
      super.setPosition(index);
      FieldWriter w = this.getWriter();
      if (w == null) {
         this.position = index;
      } else {
         w.setPosition(index);
      }

   }

   protected boolean requiresArrowType(Types.MinorType type) {
      return type == Types.MinorType.DECIMAL || type == Types.MinorType.MAP || type == Types.MinorType.DURATION || type == Types.MinorType.FIXEDSIZEBINARY || type.name().startsWith("TIMESTAMP") && type.name().endsWith("TZ");
   }

   protected FieldWriter getWriter(Types.MinorType type, ArrowType arrowType) {
      if (this.state == PromotableWriter.State.UNION) {
         if (this.requiresArrowType(type)) {
            ((UnionWriter)this.writer).getWriter(type, arrowType);
         } else {
            ((UnionWriter)this.writer).getWriter(type);
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
         } else {
            v = this.largeListVector.addOrGetVector(fieldType).getVector();
         }

         v.allocateNew();
         this.setWriter(v);
         this.writer.setPosition(this.position);
      } else if (type != this.type) {
         this.promoteToUnion();
         if (this.requiresArrowType(type)) {
            ((UnionWriter)this.writer).getWriter(type, arrowType);
         } else {
            ((UnionWriter)this.writer).getWriter(type);
         }
      }

      return this.writer;
   }

   public boolean isEmptyStruct() {
      return this.writer.isEmptyStruct();
   }

   protected FieldWriter getWriter() {
      return this.writer;
   }

   protected FieldWriter promoteToUnion() {
      String name = this.vector.getField().getName();
      TransferPair tp = this.vector.getTransferPair(this.vector.getMinorType().name().toLowerCase(Locale.ROOT), this.vector.getAllocator());
      tp.transfer();
      if (this.parentContainer != null) {
         this.unionVector = this.parentContainer.addOrGetUnion(name);
         this.unionVector.allocateNew();
      } else if (this.listVector != null) {
         this.unionVector = this.listVector.promoteToUnion();
      } else if (this.fixedListVector != null) {
         this.unionVector = this.fixedListVector.promoteToUnion();
      } else if (this.largeListVector != null) {
         this.unionVector = this.largeListVector.promoteToUnion();
      } else if (this.listViewVector != null) {
         this.unionVector = this.listViewVector.promoteToUnion();
      }

      this.unionVector.addVector((FieldVector)tp.getTo());
      this.writer = new UnionWriter(this.unionVector, this.nullableStructWriterFactory);
      this.writer.setPosition(this.idx());

      for(int i = 0; i <= this.idx(); ++i) {
         this.unionVector.setType(i, this.vector.getMinorType());
      }

      this.vector = null;
      this.state = PromotableWriter.State.UNION;
      return this.writer;
   }

   public void write(DecimalHolder holder) {
      this.getWriter(Types.MinorType.DECIMAL, new ArrowType.Decimal(38, holder.scale, 128)).write(holder);
   }

   public void writeDecimal(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL, new ArrowType.Decimal(38, ((ArrowType.Decimal)arrowType).getScale(), 128)).writeDecimal(start, buffer, arrowType);
   }

   public void writeDecimal(BigDecimal value) {
      this.getWriter(Types.MinorType.DECIMAL, new ArrowType.Decimal(38, value.scale(), 128)).writeDecimal(value);
   }

   public void writeBigEndianBytesToDecimal(byte[] value, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL, new ArrowType.Decimal(38, ((ArrowType.Decimal)arrowType).getScale(), 128)).writeBigEndianBytesToDecimal(value, arrowType);
   }

   public void write(Decimal256Holder holder) {
      this.getWriter(Types.MinorType.DECIMAL256, new ArrowType.Decimal(76, holder.scale, 256)).write(holder);
   }

   public void writeDecimal256(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL256, new ArrowType.Decimal(76, ((ArrowType.Decimal)arrowType).getScale(), 256)).writeDecimal256(start, buffer, arrowType);
   }

   public void writeDecimal256(BigDecimal value) {
      this.getWriter(Types.MinorType.DECIMAL256, new ArrowType.Decimal(76, value.scale(), 256)).writeDecimal256(value);
   }

   public void writeBigEndianBytesToDecimal256(byte[] value, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL256, new ArrowType.Decimal(76, ((ArrowType.Decimal)arrowType).getScale(), 256)).writeBigEndianBytesToDecimal256(value, arrowType);
   }

   public void writeVarBinary(byte[] value) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value);
   }

   public void writeVarBinary(byte[] value, int offset, int length) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value, offset, length);
   }

   public void writeVarBinary(ByteBuffer value) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value);
   }

   public void writeVarBinary(ByteBuffer value, int offset, int length) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value, offset, length);
   }

   public void writeLargeVarBinary(byte[] value) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value);
   }

   public void writeLargeVarBinary(byte[] value, int offset, int length) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value, offset, length);
   }

   public void writeLargeVarBinary(ByteBuffer value) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value);
   }

   public void writeLargeVarBinary(ByteBuffer value, int offset, int length) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value, offset, length);
   }

   public void writeVarChar(Text value) {
      this.getWriter(Types.MinorType.VARCHAR).writeVarChar(value);
   }

   public void writeVarChar(String value) {
      this.getWriter(Types.MinorType.VARCHAR).writeVarChar(value);
   }

   public void writeLargeVarChar(Text value) {
      this.getWriter(Types.MinorType.LARGEVARCHAR).writeLargeVarChar(value);
   }

   public void writeLargeVarChar(String value) {
      this.getWriter(Types.MinorType.LARGEVARCHAR).writeLargeVarChar(value);
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

   public PromotableViewWriter toViewWriter() {
      PromotableViewWriter promotableViewWriter = new PromotableViewWriter(this.unionVector, this.parentContainer, this.nullableStructWriterFactory);
      promotableViewWriter.position = this.position;
      promotableViewWriter.writer = this.writer;
      promotableViewWriter.state = this.state;
      promotableViewWriter.unionVector = this.unionVector;
      promotableViewWriter.type = Types.MinorType.LISTVIEW;
      return promotableViewWriter;
   }

   protected static enum State {
      UNTYPED,
      SINGLE,
      UNION;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{UNTYPED, SINGLE, UNION};
      }
   }
}

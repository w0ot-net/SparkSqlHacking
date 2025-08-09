package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.Positionable;
import org.apache.arrow.vector.complex.impl.DenseUnionWriter;
import org.apache.arrow.vector.complex.impl.UnionWriter;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.holders.DenseUnionHolder;
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public interface BaseReader extends Positionable {
   Field getField();

   Types.MinorType getMinorType();

   void reset();

   void read(UnionHolder var1);

   void read(int var1, UnionHolder var2);

   void copyAsValue(UnionWriter var1);

   void read(DenseUnionHolder var1);

   void read(int var1, DenseUnionHolder var2);

   void copyAsValue(DenseUnionWriter var1);

   boolean isSet();

   public interface ComplexReader {
      StructReader rootAsStruct();

      ListReader rootAsList();

      boolean rootIsStruct();

      boolean ok();
   }

   public interface ListReader extends BaseReader {
      FieldReader reader();
   }

   public interface MapReader extends BaseReader {
      FieldReader reader();
   }

   public interface RepeatedListReader extends ListReader {
      boolean next();

      int size();

      void copyAsValue(BaseWriter.ListWriter var1);
   }

   public interface RepeatedMapReader extends MapReader {
      boolean next();

      int size();

      void copyAsValue(BaseWriter.MapWriter var1);
   }

   public interface RepeatedStructReader extends StructReader {
      boolean next();

      int size();

      void copyAsValue(BaseWriter.StructWriter var1);
   }

   public interface ScalarReader extends TinyIntReader, UInt1Reader, UInt2Reader, SmallIntReader, Float2Reader, IntReader, UInt4Reader, Float4Reader, DateDayReader, IntervalYearReader, TimeSecReader, TimeMilliReader, BigIntReader, UInt8Reader, Float8Reader, DateMilliReader, DurationReader, TimeStampSecReader, TimeStampMilliReader, TimeStampMicroReader, TimeStampNanoReader, TimeStampSecTZReader, TimeStampMilliTZReader, TimeStampMicroTZReader, TimeStampNanoTZReader, TimeMicroReader, TimeNanoReader, IntervalDayReader, IntervalMonthDayNanoReader, Decimal256Reader, DecimalReader, FixedSizeBinaryReader, VarBinaryReader, VarCharReader, ViewVarBinaryReader, ViewVarCharReader, LargeVarCharReader, LargeVarBinaryReader, BitReader, BaseReader {
   }

   public interface StructReader extends BaseReader, Iterable {
      FieldReader reader(String var1);
   }
}

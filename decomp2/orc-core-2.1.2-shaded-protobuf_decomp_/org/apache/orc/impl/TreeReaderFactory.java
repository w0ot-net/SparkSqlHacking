package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Consumer;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DateColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.Decimal64ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.expressions.StringExpr;
import org.apache.hadoop.hive.ql.io.filter.FilterContext;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcFilterContext;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.ColumnEncoding.Kind;
import org.apache.orc.impl.reader.ReaderEncryption;
import org.apache.orc.impl.reader.StripePlanner;
import org.apache.orc.impl.reader.tree.BatchReader;
import org.apache.orc.impl.reader.tree.PrimitiveBatchReader;
import org.apache.orc.impl.reader.tree.StructBatchReader;
import org.apache.orc.impl.reader.tree.TypeReader;
import org.jetbrains.annotations.NotNull;

public class TreeReaderFactory {
   private static final FilterContext NULL_FILTER = new FilterContext() {
      public void reset() {
      }

      public boolean isSelectedInUse() {
         return false;
      }

      public int[] getSelected() {
         return new int[0];
      }

      public int getSelectedSize() {
         return 0;
      }
   };

   public static TypeReader createTreeReader(TypeDescription readerType, Context context) throws IOException {
      OrcFile.Version version = context.getFileFormat();
      SchemaEvolution evolution = context.getSchemaEvolution();
      TypeDescription fileType = evolution.getFileType(readerType);
      if (fileType != null && evolution.includeReaderColumn(readerType.getId())) {
         TypeDescription.Category readerTypeCategory = readerType.getCategory();
         if (!fileType.equals(readerType, false) && readerTypeCategory != TypeDescription.Category.STRUCT && readerTypeCategory != TypeDescription.Category.MAP && readerTypeCategory != TypeDescription.Category.LIST && readerTypeCategory != TypeDescription.Category.UNION) {
            return ConvertTreeReaderFactory.createConvertTreeReader(readerType, context);
         } else {
            switch (readerTypeCategory) {
               case BOOLEAN:
                  return new BooleanTreeReader(fileType.getId(), context);
               case BYTE:
                  return new ByteTreeReader(fileType.getId(), context);
               case DOUBLE:
                  return new DoubleTreeReader(fileType.getId(), context);
               case FLOAT:
                  return new FloatTreeReader(fileType.getId(), context);
               case SHORT:
                  return new ShortTreeReader(fileType.getId(), context);
               case INT:
                  return new IntTreeReader(fileType.getId(), context);
               case LONG:
                  return new LongTreeReader(fileType.getId(), context);
               case STRING:
                  return new StringTreeReader(fileType.getId(), context);
               case CHAR:
                  return new CharTreeReader(fileType.getId(), readerType.getMaxLength(), context);
               case VARCHAR:
                  return new VarcharTreeReader(fileType.getId(), readerType.getMaxLength(), context);
               case BINARY:
                  return new BinaryTreeReader(fileType.getId(), context);
               case TIMESTAMP:
                  return new TimestampTreeReader(fileType.getId(), context, false);
               case TIMESTAMP_INSTANT:
                  return new TimestampTreeReader(fileType.getId(), context, true);
               case DATE:
                  return new DateTreeReader(fileType.getId(), context);
               case DECIMAL:
                  if (isDecimalAsLong(version, fileType.getPrecision())) {
                     return new Decimal64TreeReader(fileType.getId(), fileType.getPrecision(), fileType.getScale(), context);
                  }

                  return new DecimalTreeReader(fileType.getId(), fileType.getPrecision(), fileType.getScale(), context);
               case STRUCT:
                  return new StructTreeReader(fileType.getId(), readerType, context);
               case LIST:
                  return new ListTreeReader(fileType.getId(), readerType, context);
               case MAP:
                  return new MapTreeReader(fileType.getId(), readerType, context);
               case UNION:
                  return new UnionTreeReader(fileType.getId(), readerType, context);
               default:
                  throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerTypeCategory));
            }
         }
      } else {
         return new NullTreeReader(-1, context);
      }
   }

   public static boolean isDecimalAsLong(OrcFile.Version version, int precision) {
      return version == OrcFile.Version.UNSTABLE_PRE_2_0 && precision <= 18;
   }

   public static BatchReader createRootReader(TypeDescription readerType, Context context) throws IOException {
      TypeReader reader = createTreeReader(readerType, context);
      return (BatchReader)(reader instanceof StructTreeReader ? new StructBatchReader(reader, context) : new PrimitiveBatchReader(reader));
   }

   public static class ReaderContext implements Context {
      private SchemaEvolution evolution;
      private boolean skipCorrupt = false;
      private boolean useUTCTimestamp = false;
      private String writerTimezone;
      private OrcFile.Version fileFormat;
      private ReaderEncryption encryption;
      private boolean useProlepticGregorian;
      private boolean fileUsedProlepticGregorian;
      private Set filterColumnIds = Collections.emptySet();
      Consumer filterCallback;

      public ReaderContext setSchemaEvolution(SchemaEvolution evolution) {
         this.evolution = evolution;
         return this;
      }

      public ReaderContext setEncryption(ReaderEncryption value) {
         this.encryption = value;
         return this;
      }

      public ReaderContext setFilterCallback(Set filterColumnsList, Consumer filterCallback) {
         this.filterColumnIds = filterColumnsList;
         this.filterCallback = filterCallback;
         return this;
      }

      public ReaderContext skipCorrupt(boolean skipCorrupt) {
         this.skipCorrupt = skipCorrupt;
         return this;
      }

      public ReaderContext useUTCTimestamp(boolean useUTCTimestamp) {
         this.useUTCTimestamp = useUTCTimestamp;
         return this;
      }

      public ReaderContext writerTimeZone(String writerTimezone) {
         this.writerTimezone = writerTimezone;
         return this;
      }

      public ReaderContext fileFormat(OrcFile.Version version) {
         this.fileFormat = version;
         return this;
      }

      public ReaderContext setProlepticGregorian(boolean file, boolean reader) {
         this.useProlepticGregorian = reader;
         this.fileUsedProlepticGregorian = file;
         return this;
      }

      public SchemaEvolution getSchemaEvolution() {
         return this.evolution;
      }

      public Set getColumnFilterIds() {
         return this.filterColumnIds;
      }

      public Consumer getColumnFilterCallback() {
         return this.filterCallback;
      }

      public boolean isSkipCorrupt() {
         return this.skipCorrupt;
      }

      public boolean getUseUTCTimestamp() {
         return this.useUTCTimestamp;
      }

      public String getWriterTimezone() {
         return this.writerTimezone;
      }

      public OrcFile.Version getFileFormat() {
         return this.fileFormat;
      }

      public ReaderEncryption getEncryption() {
         return this.encryption;
      }

      public boolean useProlepticGregorian() {
         return this.useProlepticGregorian;
      }

      public boolean fileUsedProlepticGregorian() {
         return this.fileUsedProlepticGregorian;
      }

      public TypeReader.ReaderCategory getReaderCategory(int columnId) {
         TypeReader.ReaderCategory result;
         if (this.getColumnFilterIds().contains(columnId)) {
            TypeDescription col = columnId == -1 ? null : this.getSchemaEvolution().getFileSchema().findSubtype(columnId);
            if (col != null && col.getChildren() != null && !col.getChildren().isEmpty()) {
               result = TypeReader.ReaderCategory.FILTER_PARENT;
            } else {
               result = TypeReader.ReaderCategory.FILTER_CHILD;
            }
         } else {
            result = TypeReader.ReaderCategory.NON_FILTER;
         }

         return result;
      }
   }

   public abstract static class TreeReader implements TypeReader {
      protected final int columnId;
      protected BitFieldReader present;
      protected final Context context;
      protected final TypeReader.ReaderCategory readerCategory;
      static final long[] powerOfTenTable = new long[]{1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

      TreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, context);
      }

      protected TreeReader(int columnId, InStream in, @NotNull Context context) throws IOException {
         this.present = null;
         this.columnId = columnId;
         this.context = context;
         if (in == null) {
            this.present = null;
         } else {
            this.present = new BitFieldReader(in);
         }

         this.readerCategory = context.getReaderCategory(columnId);
      }

      public TypeReader.ReaderCategory getReaderCategory() {
         return this.readerCategory;
      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      protected static IntegerReader createIntegerReader(OrcProto.ColumnEncoding.Kind kind, InStream in, boolean signed, Context context) throws IOException {
         switch (kind) {
            case DIRECT_V2:
            case DICTIONARY_V2:
               return new RunLengthIntegerReaderV2(in, signed, context != null && context.isSkipCorrupt());
            case DIRECT:
            case DICTIONARY:
               return new RunLengthIntegerReader(in, signed);
            default:
               throw new IllegalArgumentException("Unknown encoding " + String.valueOf(kind));
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         this.checkEncoding(planner.getEncoding(this.columnId));
         InStream in = planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.PRESENT));
         if (in == null) {
            this.present = null;
         } else {
            this.present = new BitFieldReader(in);
         }

      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.present != null) {
            this.present.seek(index);
         }

      }

      protected static int countNonNullRowsInRange(boolean[] isNull, int start, int end) {
         int result = 0;

         while(start < end) {
            if (!isNull[start++]) {
               ++result;
            }
         }

         return result;
      }

      protected long countNonNulls(long rows) throws IOException {
         if (this.present != null) {
            long result = 0L;

            for(long c = 0L; c < rows; ++c) {
               if (this.present.next() == 1) {
                  ++result;
               }
            }

            return result;
         } else {
            return rows;
         }
      }

      public void nextVector(ColumnVector previous, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.present == null && isNull == null) {
            previous.noNulls = true;

            for(int i = 0; i < batchSize; ++i) {
               previous.isNull[i] = false;
            }
         } else {
            previous.noNulls = true;
            boolean allNull = true;

            for(int i = 0; i < batchSize; ++i) {
               if (isNull != null && isNull[i]) {
                  previous.noNulls = false;
                  previous.isNull[i] = true;
               } else if (this.present != null && this.present.next() != 1) {
                  previous.noNulls = false;
                  previous.isNull[i] = true;
               } else {
                  previous.isNull[i] = false;
                  allNull = false;
               }
            }

            previous.isRepeating = !previous.noNulls && allNull;
         }

      }

      public BitFieldReader getPresent() {
         return this.present;
      }

      public int getColumnId() {
         return this.columnId;
      }
   }

   public static class NullTreeReader extends TreeReader {
      public NullTreeReader(int columnId, Context context) throws IOException {
         super(columnId, context);
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) {
      }

      public void skipRows(long rows, TypeReader.ReadPhase readPhase) {
      }

      public void seek(PositionProvider position, TypeReader.ReadPhase readPhase) {
      }

      public void seek(PositionProvider[] position, TypeReader.ReadPhase readPhase) {
      }

      public void nextVector(ColumnVector vector, boolean[] isNull, int size, FilterContext filterContext, TypeReader.ReadPhase readPhase) {
         vector.noNulls = false;
         vector.isNull[0] = true;
         vector.isRepeating = true;
      }
   }

   public static class BooleanTreeReader extends TreeReader {
      protected BitFieldReader reader;

      BooleanTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, context);
      }

      protected BooleanTreeReader(int columnId, InStream present, InStream data, Context context) throws IOException {
         super(columnId, present, context);
         this.reader = null;
         if (data != null) {
            this.reader = new BitFieldReader(data);
         }

      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         this.reader = new BitFieldReader(planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA)));
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         LongColumnVector result = (LongColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (filterContext.isSelectedInUse()) {
            this.reader.nextVector(result, filterContext, (long)batchSize);
         } else {
            this.reader.nextVector(result, (long)batchSize);
         }

      }
   }

   public static class ByteTreeReader extends TreeReader {
      protected RunLengthByteReader reader;

      ByteTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, context);
      }

      protected ByteTreeReader(int columnId, InStream present, InStream data, Context context) throws IOException {
         super(columnId, present, context);
         this.reader = null;
         this.reader = new RunLengthByteReader(data);
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         this.reader = new RunLengthByteReader(planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA)));
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         LongColumnVector result = (LongColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.reader.nextVector((ColumnVector)result, (long[])result.vector, (long)batchSize);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }
   }

   public static class ShortTreeReader extends TreeReader {
      protected IntegerReader reader;

      ShortTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected ShortTreeReader(int columnId, InStream present, InStream data, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.reader = null;
         if (data != null && encoding != null) {
            this.checkEncoding(encoding);
            this.reader = createIntegerReader(encoding.getKind(), data, true, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.reader = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(name), true, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         LongColumnVector result = (LongColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.reader.nextVector(result, (long[])result.vector, batchSize);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }
   }

   public static class IntTreeReader extends TreeReader {
      protected IntegerReader reader;

      IntTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected IntTreeReader(int columnId, InStream present, InStream data, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.reader = null;
         if (data != null && encoding != null) {
            this.checkEncoding(encoding);
            this.reader = createIntegerReader(encoding.getKind(), data, true, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.reader = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(name), true, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         LongColumnVector result = (LongColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.reader.nextVector(result, (long[])result.vector, batchSize);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }
   }

   public static class LongTreeReader extends TreeReader {
      protected IntegerReader reader;

      LongTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected LongTreeReader(int columnId, InStream present, InStream data, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.reader = null;
         if (data != null && encoding != null) {
            this.checkEncoding(encoding);
            this.reader = createIntegerReader(encoding.getKind(), data, true, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.reader = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(name), true, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         LongColumnVector result = (LongColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.reader.nextVector(result, (long[])result.vector, batchSize);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }
   }

   public static class FloatTreeReader extends TreeReader {
      protected InStream stream;
      private final SerializationUtils utils;

      FloatTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, context);
      }

      protected FloatTreeReader(int columnId, InStream present, InStream data, Context context) throws IOException {
         super(columnId, present, context);
         this.utils = new SerializationUtils();
         this.stream = data;
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.stream = planner.getStream(name);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.stream.seek(index);
      }

      private void nextVector(DoubleColumnVector result, boolean[] isNull, int batchSize) throws IOException {
         boolean hasNulls = !result.noNulls;
         boolean allNulls = hasNulls;
         if (batchSize > 0) {
            if (hasNulls) {
               for(int i = 0; batchSize <= result.isNull.length && i < batchSize; ++i) {
                  allNulls &= result.isNull[i];
               }

               if (allNulls) {
                  result.vector[0] = Double.NaN;
                  result.isRepeating = true;
               } else {
                  result.isRepeating = false;

                  for(int i = 0; batchSize <= result.isNull.length && batchSize <= result.vector.length && i < batchSize; ++i) {
                     if (!result.isNull[i]) {
                        result.vector[i] = (double)this.utils.readFloat(this.stream);
                     } else {
                        result.vector[i] = Double.NaN;
                     }
                  }
               }
            } else {
               boolean repeating = batchSize > 1;
               float f1 = this.utils.readFloat(this.stream);
               result.vector[0] = (double)f1;

               for(int i = 1; i < batchSize && batchSize <= result.vector.length; ++i) {
                  float f2 = this.utils.readFloat(this.stream);
                  repeating = repeating && f1 == f2;
                  result.vector[i] = (double)f2;
               }

               result.isRepeating = repeating;
            }
         }

      }

      private void nextVector(DoubleColumnVector result, boolean[] isNull, FilterContext filterContext, int batchSize) throws IOException {
         boolean hasNulls = !result.noNulls;
         boolean allNulls = hasNulls;
         result.isRepeating = false;
         int previousIdx = 0;
         if (batchSize > 0) {
            if (hasNulls) {
               for(int i = 0; batchSize <= result.isNull.length && i < batchSize; ++i) {
                  allNulls &= result.isNull[i];
               }

               if (allNulls) {
                  result.vector[0] = Double.NaN;
                  result.isRepeating = true;
               } else {
                  for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
                     int idx = filterContext.getSelected()[i];
                     if (idx - previousIdx > 0) {
                        this.utils.skipFloat(this.stream, countNonNullRowsInRange(result.isNull, previousIdx, idx));
                     }

                     if (!result.isNull[idx]) {
                        result.vector[idx] = (double)this.utils.readFloat(this.stream);
                     } else {
                        result.vector[idx] = Double.NaN;
                     }

                     previousIdx = idx + 1;
                  }

                  this.utils.skipFloat(this.stream, countNonNullRowsInRange(result.isNull, previousIdx, batchSize));
               }
            } else {
               for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
                  int idx = filterContext.getSelected()[i];
                  if (idx - previousIdx > 0) {
                     this.utils.skipFloat(this.stream, idx - previousIdx);
                  }

                  result.vector[idx] = (double)this.utils.readFloat(this.stream);
                  previousIdx = idx + 1;
               }

               this.utils.skipFloat(this.stream, batchSize - previousIdx);
            }
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         DoubleColumnVector result = (DoubleColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (filterContext.isSelectedInUse()) {
            this.nextVector(result, isNull, filterContext, batchSize);
         } else {
            this.nextVector(result, isNull, batchSize);
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);

         for(int i = 0; (long)i < items; ++i) {
            this.utils.readFloat(this.stream);
         }

      }
   }

   public static class DoubleTreeReader extends TreeReader {
      protected InStream stream;
      private final SerializationUtils utils;

      DoubleTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, context);
      }

      protected DoubleTreeReader(int columnId, InStream present, InStream data, Context context) throws IOException {
         super(columnId, present, context);
         this.utils = new SerializationUtils();
         this.stream = data;
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.stream = planner.getStream(name);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.stream.seek(index);
      }

      private void nextVector(DoubleColumnVector result, boolean[] isNull, FilterContext filterContext, int batchSize) throws IOException {
         boolean hasNulls = !result.noNulls;
         boolean allNulls = hasNulls;
         result.isRepeating = false;
         if (batchSize != 0) {
            if (hasNulls) {
               for(int i = 0; i < batchSize && batchSize <= result.isNull.length; ++i) {
                  allNulls &= result.isNull[i];
               }

               if (allNulls) {
                  result.vector[0] = Double.NaN;
                  result.isRepeating = true;
               } else {
                  int previousIdx = 0;

                  for(int i = 0; batchSize <= result.isNull.length && i != filterContext.getSelectedSize(); ++i) {
                     int idx = filterContext.getSelected()[i];
                     if (idx - previousIdx > 0) {
                        this.utils.skipDouble(this.stream, countNonNullRowsInRange(result.isNull, previousIdx, idx));
                     }

                     if (!result.isNull[idx]) {
                        result.vector[idx] = this.utils.readDouble(this.stream);
                     } else {
                        result.vector[idx] = Double.NaN;
                     }

                     previousIdx = idx + 1;
                  }

                  this.utils.skipDouble(this.stream, countNonNullRowsInRange(result.isNull, previousIdx, batchSize));
               }
            } else {
               int previousIdx = 0;

               for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
                  int idx = filterContext.getSelected()[i];
                  if (idx - previousIdx > 0) {
                     this.utils.skipDouble(this.stream, idx - previousIdx);
                  }

                  result.vector[idx] = this.utils.readDouble(this.stream);
                  previousIdx = idx + 1;
               }

               this.utils.skipDouble(this.stream, batchSize - previousIdx);
            }
         }

      }

      private void nextVector(DoubleColumnVector result, boolean[] isNull, int batchSize) throws IOException {
         boolean hasNulls = !result.noNulls;
         boolean allNulls = hasNulls;
         if (batchSize != 0) {
            if (hasNulls) {
               for(int i = 0; i < batchSize && batchSize <= result.isNull.length; ++i) {
                  allNulls &= result.isNull[i];
               }

               if (allNulls) {
                  result.vector[0] = Double.NaN;
                  result.isRepeating = true;
               } else {
                  result.isRepeating = false;

                  for(int i = 0; batchSize <= result.isNull.length && batchSize <= result.vector.length && i < batchSize; ++i) {
                     if (!result.isNull[i]) {
                        result.vector[i] = this.utils.readDouble(this.stream);
                     } else {
                        result.vector[i] = Double.NaN;
                     }
                  }
               }
            } else {
               boolean repeating = batchSize > 1;
               double d1 = this.utils.readDouble(this.stream);
               result.vector[0] = d1;

               for(int i = 1; i < batchSize && batchSize <= result.vector.length; ++i) {
                  double d2 = this.utils.readDouble(this.stream);
                  repeating = repeating && d1 == d2;
                  result.vector[i] = d2;
               }

               result.isRepeating = repeating;
            }
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         DoubleColumnVector result = (DoubleColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (filterContext.isSelectedInUse()) {
            this.nextVector(result, isNull, filterContext, batchSize);
         } else {
            this.nextVector(result, isNull, batchSize);
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);

         for(long len = items * 8L; len > 0L; len -= this.stream.skip(len)) {
         }

      }
   }

   public static class BinaryTreeReader extends TreeReader {
      protected InStream stream;
      protected IntegerReader lengths;
      protected final LongColumnVector scratchlcv;

      BinaryTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected BinaryTreeReader(int columnId, InStream present, InStream data, InStream length, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.lengths = null;
         this.scratchlcv = new LongColumnVector();
         this.stream = data;
         if (length != null && encoding != null) {
            this.checkEncoding(encoding);
            this.lengths = createIntegerReader(encoding.getKind(), length, false, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.stream = planner.getStream(name);
         this.lengths = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.LENGTH)), false, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.stream.seek(index);
         this.lengths.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         BytesColumnVector result = (BytesColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.scratchlcv.ensureSize(batchSize, false);
         TreeReaderFactory.BytesColumnVectorUtil.readOrcByteArrays(this.stream, this.lengths, this.scratchlcv, result, batchSize);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         long lengthToSkip = 0L;

         for(int i = 0; (long)i < items; ++i) {
            lengthToSkip += this.lengths.next();
         }

         while(lengthToSkip > 0L) {
            lengthToSkip -= this.stream.skip(lengthToSkip);
         }

      }
   }

   public static class TimestampTreeReader extends TreeReader {
      protected IntegerReader data;
      protected IntegerReader nanos;
      private final Map baseTimestampMap;
      protected long base_timestamp;
      private final TimeZone readerTimeZone;
      private final boolean instantType;
      private TimeZone writerTimeZone;
      private boolean hasSameTZRules;
      private final ThreadLocal threadLocalDateFormat;
      private final boolean useProleptic;
      private final boolean fileUsesProleptic;

      TimestampTreeReader(int columnId, Context context, boolean instantType) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context, instantType);
      }

      protected TimestampTreeReader(int columnId, InStream presentStream, InStream dataStream, InStream nanosStream, OrcProto.ColumnEncoding encoding, Context context, boolean instantType) throws IOException {
         super(columnId, presentStream, context);
         this.data = null;
         this.nanos = null;
         this.instantType = instantType;
         this.threadLocalDateFormat = new ThreadLocal();
         this.threadLocalDateFormat.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
         this.baseTimestampMap = new HashMap();
         if (!instantType && !context.getUseUTCTimestamp()) {
            this.readerTimeZone = TimeZone.getDefault();
         } else {
            this.readerTimeZone = TimeZone.getTimeZone("UTC");
         }

         if (context.getWriterTimezone() != null && !context.getWriterTimezone().isEmpty()) {
            this.base_timestamp = this.getBaseTimestamp(context.getWriterTimezone());
         } else if (instantType) {
            this.base_timestamp = this.getBaseTimestamp(this.readerTimeZone.getID());
         } else {
            this.base_timestamp = this.getBaseTimestamp(TimeZone.getDefault().getID());
         }

         if (encoding != null) {
            this.checkEncoding(encoding);
            if (dataStream != null) {
               this.data = createIntegerReader(encoding.getKind(), dataStream, true, context);
            }

            if (nanosStream != null) {
               this.nanos = createIntegerReader(encoding.getKind(), nanosStream, false, context);
            }
         }

         this.fileUsesProleptic = context.fileUsedProlepticGregorian();
         this.useProleptic = context.useProlepticGregorian();
      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         OrcProto.ColumnEncoding.Kind kind = planner.getEncoding(this.columnId).getKind();
         this.data = createIntegerReader(kind, planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA)), true, this.context);
         this.nanos = createIntegerReader(kind, planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.SECONDARY)), false, this.context);
         if (!this.instantType) {
            this.base_timestamp = this.getBaseTimestamp(planner.getWriterTimezone());
         }

      }

      protected long getBaseTimestamp(String timeZoneId) throws IOException {
         if (timeZoneId == null || timeZoneId.isEmpty()) {
            timeZoneId = this.writerTimeZone.getID();
         }

         if (this.writerTimeZone != null && timeZoneId.equals(this.writerTimeZone.getID())) {
            return this.base_timestamp;
         } else {
            this.writerTimeZone = TimeZone.getTimeZone(timeZoneId);
            this.hasSameTZRules = this.writerTimeZone.hasSameRules(this.readerTimeZone);
            if (!this.baseTimestampMap.containsKey(timeZoneId)) {
               ((DateFormat)this.threadLocalDateFormat.get()).setTimeZone(this.writerTimeZone);

               long var4;
               try {
                  long epoch = ((DateFormat)this.threadLocalDateFormat.get()).parse("2015-01-01 00:00:00").getTime() / 1000L;
                  this.baseTimestampMap.put(timeZoneId, epoch);
                  var4 = epoch;
               } catch (ParseException e) {
                  throw new IOException("Unable to create base timestamp", e);
               } finally {
                  ((DateFormat)this.threadLocalDateFormat.get()).setTimeZone(this.readerTimeZone);
               }

               return var4;
            } else {
               return (Long)this.baseTimestampMap.get(timeZoneId);
            }
         }
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.data.seek(index);
         this.nanos.seek(index);
      }

      public void readTimestamp(TimestampColumnVector result, int idx) throws IOException {
         int newNanos = parseNanos(this.nanos.next());
         long millis = (this.data.next() + this.base_timestamp) * 1000L + (long)(newNanos / 1000000);
         if (millis < 0L && newNanos > 999999) {
            millis -= 1000L;
         }

         long offset = 0L;
         if (!this.hasSameTZRules) {
            offset = SerializationUtils.convertBetweenTimezones(this.writerTimeZone, this.readerTimeZone, millis);
         }

         result.time[idx] = millis + offset;
         result.nanos[idx] = newNanos;
      }

      public void nextVector(TimestampColumnVector result, boolean[] isNull, int batchSize) throws IOException {
         for(int i = 0; i < batchSize; ++i) {
            if (result.noNulls || !result.isNull[i]) {
               this.readTimestamp(result, i);
               if (result.isRepeating && i != 0 && (result.time[0] != result.time[i] || result.nanos[0] != result.nanos[i])) {
                  result.isRepeating = false;
               }
            }
         }

         result.changeCalendar(this.useProleptic, true);
      }

      public void nextVector(TimestampColumnVector result, boolean[] isNull, FilterContext filterContext, int batchSize) throws IOException {
         result.isRepeating = false;
         int previousIdx = 0;
         if (result.noNulls) {
            for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
               int idx = filterContext.getSelected()[i];
               if (idx - previousIdx > 0) {
                  this.skipStreamRows((long)(idx - previousIdx));
               }

               this.readTimestamp(result, idx);
               previousIdx = idx + 1;
            }

            this.skipStreamRows((long)(batchSize - previousIdx));
         } else {
            for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
               int idx = filterContext.getSelected()[i];
               if (idx - previousIdx > 0) {
                  this.skipStreamRows((long)countNonNullRowsInRange(result.isNull, previousIdx, idx));
               }

               if (!result.isNull[idx]) {
                  this.readTimestamp(result, idx);
               }

               previousIdx = idx + 1;
            }

            this.skipStreamRows((long)countNonNullRowsInRange(result.isNull, previousIdx, batchSize));
         }

         result.changeCalendar(this.useProleptic, true);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         TimestampColumnVector result = (TimestampColumnVector)previousVector;
         result.changeCalendar(this.fileUsesProleptic, false);
         super.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
         result.setIsUTC(this.context.getUseUTCTimestamp());
         if (filterContext.isSelectedInUse()) {
            this.nextVector(result, isNull, filterContext, batchSize);
         } else {
            this.nextVector(result, isNull, batchSize);
         }

      }

      private static int parseNanos(long serialized) {
         int zeros = 7 & (int)serialized;
         int result = (int)(serialized >>> 3);
         if (zeros != 0) {
            result *= (int)powerOfTenTable[zeros + 1];
         }

         return result;
      }

      void skipStreamRows(long items) throws IOException {
         this.data.skip(items);
         this.nanos.skip(items);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         this.data.skip(items);
         this.nanos.skip(items);
      }
   }

   public static class DateTreeReader extends TreeReader {
      protected IntegerReader reader;
      private final boolean needsDateColumnVector;
      private final boolean useProleptic;
      private final boolean fileUsesProleptic;

      DateTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected DateTreeReader(int columnId, InStream present, InStream data, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.reader = null;
         this.useProleptic = context.useProlepticGregorian();
         this.fileUsesProleptic = context.fileUsedProlepticGregorian();
         this.needsDateColumnVector = this.useProleptic || this.fileUsesProleptic;
         if (data != null && encoding != null) {
            this.checkEncoding(encoding);
            this.reader = createIntegerReader(encoding.getKind(), data, true, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.reader = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(name), true, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         LongColumnVector result = (LongColumnVector)previousVector;
         if (this.needsDateColumnVector) {
            if (!(result instanceof DateColumnVector)) {
               throw new IllegalArgumentException("Can't use LongColumnVector to read proleptic Gregorian dates.");
            }

            ((DateColumnVector)result).changeCalendar(this.fileUsesProleptic, false);
         }

         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.reader.nextVector(result, (long[])result.vector, batchSize);
         if (this.needsDateColumnVector) {
            ((DateColumnVector)result).changeCalendar(this.useProleptic, true);
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }
   }

   public static class DecimalTreeReader extends TreeReader {
      protected final int precision;
      protected final int scale;
      protected InStream valueStream;
      protected IntegerReader scaleReader;
      private int[] scratchScaleVector;
      private final byte[] scratchBytes;

      DecimalTreeReader(int columnId, int precision, int scale, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, precision, scale, context);
      }

      protected DecimalTreeReader(int columnId, InStream present, InStream valueStream, InStream scaleStream, OrcProto.ColumnEncoding encoding, int precision, int scale, Context context) throws IOException {
         super(columnId, present, context);
         this.scaleReader = null;
         this.precision = precision;
         this.scale = scale;
         this.scratchScaleVector = new int[1024];
         this.valueStream = valueStream;
         this.scratchBytes = new byte[24];
         if (scaleStream != null && encoding != null) {
            this.checkEncoding(encoding);
            this.scaleReader = createIntegerReader(encoding.getKind(), scaleStream, true, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         this.valueStream = planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA));
         this.scaleReader = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.SECONDARY)), true, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.valueStream.seek(index);
         this.scaleReader.seek(index);
      }

      private void nextVector(DecimalColumnVector result, boolean[] isNull, int batchSize) throws IOException {
         if (batchSize > this.scratchScaleVector.length) {
            this.scratchScaleVector = new int[batchSize];
         }

         this.scaleReader.nextVector(result, (int[])this.scratchScaleVector, batchSize);
         HiveDecimalWritable[] vector = result.vector;
         if (result.noNulls) {
            for(int r = 0; r < batchSize; ++r) {
               HiveDecimalWritable decWritable = vector[r];
               if (!decWritable.serializationUtilsRead(this.valueStream, this.scratchScaleVector[r], this.scratchBytes)) {
                  result.isNull[r] = true;
                  result.noNulls = false;
               }

               this.setIsRepeatingIfNeeded(result, r);
            }
         } else if (!result.isRepeating || !result.isNull[0]) {
            for(int r = 0; r < batchSize; ++r) {
               if (!result.isNull[r]) {
                  HiveDecimalWritable decWritable = vector[r];
                  if (!decWritable.serializationUtilsRead(this.valueStream, this.scratchScaleVector[r], this.scratchBytes)) {
                     result.isNull[r] = true;
                     result.noNulls = false;
                  }
               }

               this.setIsRepeatingIfNeeded(result, r);
            }
         }

      }

      private void nextVector(DecimalColumnVector result, boolean[] isNull, FilterContext filterContext, int batchSize) throws IOException {
         if (batchSize > this.scratchScaleVector.length) {
            this.scratchScaleVector = new int[batchSize];
         }

         this.scaleReader.nextVector(result, (int[])this.scratchScaleVector, batchSize);
         HiveDecimalWritable[] vector = result.vector;
         if (result.noNulls) {
            int previousIdx = 0;

            for(int r = 0; r != filterContext.getSelectedSize(); ++r) {
               int idx = filterContext.getSelected()[r];
               if (idx - previousIdx > 0) {
                  this.skipStreamRows((long)(idx - previousIdx));
               }

               HiveDecimalWritable decWritable = vector[idx];
               if (!decWritable.serializationUtilsRead(this.valueStream, this.scratchScaleVector[idx], this.scratchBytes)) {
                  result.isNull[idx] = true;
                  result.noNulls = false;
               }

               this.setIsRepeatingIfNeeded(result, idx);
               previousIdx = idx + 1;
            }

            this.skipStreamRows((long)(batchSize - previousIdx));
         } else if (!result.isRepeating || !result.isNull[0]) {
            int previousIdx = 0;

            for(int r = 0; r != filterContext.getSelectedSize(); ++r) {
               int idx = filterContext.getSelected()[r];
               if (idx - previousIdx > 0) {
                  this.skipStreamRows((long)countNonNullRowsInRange(result.isNull, previousIdx, idx));
               }

               if (!result.isNull[idx]) {
                  HiveDecimalWritable decWritable = vector[idx];
                  if (!decWritable.serializationUtilsRead(this.valueStream, this.scratchScaleVector[idx], this.scratchBytes)) {
                     result.isNull[idx] = true;
                     result.noNulls = false;
                  }
               }

               this.setIsRepeatingIfNeeded(result, idx);
               previousIdx = idx + 1;
            }

            this.skipStreamRows((long)countNonNullRowsInRange(result.isNull, previousIdx, batchSize));
         }

      }

      private void nextVector(Decimal64ColumnVector result, boolean[] isNull, int batchSize) throws IOException {
         if (this.precision > 18) {
            throw new IllegalArgumentException("Reading large precision type into Decimal64ColumnVector.");
         } else {
            if (batchSize > this.scratchScaleVector.length) {
               this.scratchScaleVector = new int[batchSize];
            }

            this.scaleReader.nextVector(result, (int[])this.scratchScaleVector, batchSize);
            if (result.noNulls) {
               for(int r = 0; r < batchSize; ++r) {
                  long scaleFactor = powerOfTenTable[this.scale - this.scratchScaleVector[r]];
                  result.vector[r] = SerializationUtils.readVslong(this.valueStream) * scaleFactor;
                  this.setIsRepeatingIfNeeded(result, r);
               }
            } else if (!result.isRepeating || !result.isNull[0]) {
               for(int r = 0; r < batchSize; ++r) {
                  if (!result.isNull[r]) {
                     long scaleFactor = powerOfTenTable[this.scale - this.scratchScaleVector[r]];
                     result.vector[r] = SerializationUtils.readVslong(this.valueStream) * scaleFactor;
                  }

                  this.setIsRepeatingIfNeeded(result, r);
               }
            }

            result.precision = (short)this.precision;
            result.scale = (short)this.scale;
         }
      }

      private void nextVector(Decimal64ColumnVector result, boolean[] isNull, FilterContext filterContext, int batchSize) throws IOException {
         if (this.precision > 18) {
            throw new IllegalArgumentException("Reading large precision type into Decimal64ColumnVector.");
         } else {
            if (batchSize > this.scratchScaleVector.length) {
               this.scratchScaleVector = new int[batchSize];
            }

            this.scaleReader.nextVector(result, (int[])this.scratchScaleVector, batchSize);
            if (result.noNulls) {
               int previousIdx = 0;

               for(int r = 0; r != filterContext.getSelectedSize(); ++r) {
                  int idx = filterContext.getSelected()[r];
                  if (idx - previousIdx > 0) {
                     this.skipStreamRows((long)(idx - previousIdx));
                  }

                  result.vector[idx] = SerializationUtils.readVslong(this.valueStream);

                  for(int s = this.scratchScaleVector[idx]; s < this.scale; ++s) {
                     long[] var10000 = result.vector;
                     var10000[idx] *= 10L;
                  }

                  this.setIsRepeatingIfNeeded(result, idx);
                  previousIdx = idx + 1;
               }

               this.skipStreamRows((long)(batchSize - previousIdx));
            } else if (!result.isRepeating || !result.isNull[0]) {
               int previousIdx = 0;

               for(int r = 0; r != filterContext.getSelectedSize(); ++r) {
                  int idx = filterContext.getSelected()[r];
                  if (idx - previousIdx > 0) {
                     this.skipStreamRows((long)countNonNullRowsInRange(result.isNull, previousIdx, idx));
                  }

                  if (!result.isNull[idx]) {
                     result.vector[idx] = SerializationUtils.readVslong(this.valueStream);

                     for(int s = this.scratchScaleVector[idx]; s < this.scale; ++s) {
                        long[] var13 = result.vector;
                        var13[idx] *= 10L;
                     }
                  }

                  this.setIsRepeatingIfNeeded(result, idx);
                  previousIdx = idx + 1;
               }

               this.skipStreamRows((long)countNonNullRowsInRange(result.isNull, previousIdx, batchSize));
            }

            result.precision = (short)this.precision;
            result.scale = (short)this.scale;
         }
      }

      private void setIsRepeatingIfNeeded(Decimal64ColumnVector result, int index) {
         if (result.isRepeating && index > 0 && (result.vector[0] != result.vector[index] || result.isNull[0] != result.isNull[index])) {
            result.isRepeating = false;
         }

      }

      private void setIsRepeatingIfNeeded(DecimalColumnVector result, int index) {
         if (result.isRepeating && index > 0 && (!result.vector[0].equals(result.vector[index]) || result.isNull[0] != result.isNull[index])) {
            result.isRepeating = false;
         }

      }

      public void nextVector(ColumnVector result, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (result instanceof Decimal64ColumnVector) {
            if (filterContext.isSelectedInUse()) {
               this.nextVector((Decimal64ColumnVector)result, isNull, filterContext, batchSize);
            } else {
               this.nextVector((Decimal64ColumnVector)result, isNull, batchSize);
            }
         } else if (filterContext.isSelectedInUse()) {
            this.nextVector((DecimalColumnVector)result, isNull, filterContext, batchSize);
         } else {
            this.nextVector((DecimalColumnVector)result, isNull, batchSize);
         }

      }

      void skipStreamRows(long items) throws IOException {
         for(int i = 0; (long)i < items; ++i) {
            while(true) {
               int input = this.valueStream.read();
               if (input == -1) {
                  throw new EOFException("Reading BigInteger past EOF from " + String.valueOf(this.valueStream));
               }

               if (input < 128) {
                  break;
               }
            }
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         HiveDecimalWritable scratchDecWritable = new HiveDecimalWritable();

         for(int i = 0; (long)i < items; ++i) {
            scratchDecWritable.serializationUtilsRead(this.valueStream, 0, this.scratchBytes);
         }

         this.scaleReader.skip(items);
      }
   }

   public static class Decimal64TreeReader extends TreeReader {
      protected final int precision;
      protected final int scale;
      protected final boolean skipCorrupt;
      protected RunLengthIntegerReaderV2 valueReader;

      Decimal64TreeReader(int columnId, int precision, int scale, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, precision, scale, context);
      }

      protected Decimal64TreeReader(int columnId, InStream present, InStream valueStream, OrcProto.ColumnEncoding encoding, int precision, int scale, Context context) throws IOException {
         super(columnId, present, context);
         this.precision = precision;
         this.scale = scale;
         this.valueReader = new RunLengthIntegerReaderV2(valueStream, true, context.isSkipCorrupt());
         this.skipCorrupt = context.isSkipCorrupt();
      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         InStream stream = planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA));
         this.valueReader = new RunLengthIntegerReaderV2(stream, true, this.skipCorrupt);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.valueReader.seek(index);
      }

      private void nextVector(DecimalColumnVector result, FilterContext filterContext, int batchSize) throws IOException {
         if (result.noNulls) {
            if (filterContext.isSelectedInUse()) {
               result.isRepeating = true;
               int previousIdx = 0;

               for(int r = 0; r != filterContext.getSelectedSize(); ++r) {
                  int idx = filterContext.getSelected()[r];
                  if (idx - previousIdx > 0) {
                     this.valueReader.skip((long)(idx - previousIdx));
                  }

                  result.vector[idx].setFromLongAndScale(this.valueReader.next(), this.scale);
                  this.setIsRepeatingIfNeeded(result, idx);
                  previousIdx = idx + 1;
               }

               this.valueReader.skip((long)(batchSize - previousIdx));
            } else {
               result.isRepeating = true;

               for(int r = 0; r < batchSize; ++r) {
                  result.vector[r].setFromLongAndScale(this.valueReader.next(), this.scale);
                  this.setIsRepeatingIfNeeded(result, r);
               }
            }
         } else if (!result.isRepeating || !result.isNull[0]) {
            if (filterContext.isSelectedInUse()) {
               result.isRepeating = true;
               int previousIdx = 0;

               for(int r = 0; r != filterContext.getSelectedSize(); ++r) {
                  int idx = filterContext.getSelected()[r];
                  if (idx - previousIdx > 0) {
                     this.valueReader.skip((long)countNonNullRowsInRange(result.isNull, previousIdx, idx));
                  }

                  if (!result.isNull[r]) {
                     result.vector[idx].setFromLongAndScale(this.valueReader.next(), this.scale);
                  }

                  this.setIsRepeatingIfNeeded(result, idx);
                  previousIdx = idx + 1;
               }

               this.valueReader.skip((long)countNonNullRowsInRange(result.isNull, previousIdx, batchSize));
            } else {
               result.isRepeating = true;

               for(int r = 0; r < batchSize; ++r) {
                  if (!result.isNull[r]) {
                     result.vector[r].setFromLongAndScale(this.valueReader.next(), this.scale);
                  }

                  this.setIsRepeatingIfNeeded(result, r);
               }
            }
         }

         result.precision = (short)this.precision;
         result.scale = (short)this.scale;
      }

      private void nextVector(Decimal64ColumnVector result, FilterContext filterContext, int batchSize) throws IOException {
         this.valueReader.nextVector(result, (long[])result.vector, batchSize);
         result.precision = (short)this.precision;
         result.scale = (short)this.scale;
      }

      private void setIsRepeatingIfNeeded(DecimalColumnVector result, int index) {
         if (result.isRepeating && index > 0 && (!result.vector[0].equals(result.vector[index]) || result.isNull[0] != result.isNull[index])) {
            result.isRepeating = false;
         }

      }

      public void nextVector(ColumnVector result, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (result instanceof Decimal64ColumnVector) {
            this.nextVector((Decimal64ColumnVector)result, filterContext, batchSize);
         } else {
            this.nextVector((DecimalColumnVector)result, filterContext, batchSize);
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         this.valueReader.skip(items);
      }
   }

   public static class StringTreeReader extends TreeReader {
      protected TypeReader reader;

      StringTreeReader(int columnId, Context context) throws IOException {
         super(columnId, context);
      }

      protected StringTreeReader(int columnId, InStream present, InStream data, InStream length, InStream dictionary, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         if (encoding != null) {
            switch (encoding.getKind()) {
               case DIRECT_V2:
               case DIRECT:
                  this.reader = new StringDirectTreeReader(columnId, present, data, length, encoding.getKind(), context);
                  break;
               case DICTIONARY_V2:
               case DICTIONARY:
                  this.reader = new StringDictionaryTreeReader(columnId, present, data, length, dictionary, encoding, context);
                  break;
               default:
                  throw new IllegalArgumentException("Unsupported encoding " + String.valueOf(encoding.getKind()));
            }
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         this.reader.checkEncoding(encoding);
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         switch (planner.getEncoding(this.columnId).getKind()) {
            case DIRECT_V2:
            case DIRECT:
               this.reader = new StringDirectTreeReader(this.columnId, this.context);
               break;
            case DICTIONARY_V2:
            case DICTIONARY:
               this.reader = new StringDictionaryTreeReader(this.columnId, this.context);
               break;
            default:
               OrcProto.ColumnEncoding var10002 = planner.getEncoding(this.columnId);
               throw new IllegalArgumentException("Unsupported encoding " + String.valueOf(var10002.getKind()));
         }

         this.reader.startStripe(planner, readPhase);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.seek(index, readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.seek(index, readPhase);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skipRows(items, readPhase);
      }
   }

   public static class BytesColumnVectorUtil {
      private static byte[] commonReadByteArrays(InStream stream, IntegerReader lengths, LongColumnVector scratchlcv, BytesColumnVector result, int batchSize) throws IOException {
         scratchlcv.isRepeating = result.isRepeating;
         scratchlcv.noNulls = result.noNulls;
         scratchlcv.isNull = result.isNull;
         lengths.nextVector(scratchlcv, (long[])scratchlcv.vector, batchSize);
         int totalLength = 0;
         if (!scratchlcv.isRepeating) {
            for(int i = 0; i < batchSize; ++i) {
               if (!scratchlcv.isNull[i]) {
                  totalLength += (int)scratchlcv.vector[i];
               }
            }
         } else if (!scratchlcv.isNull[0]) {
            totalLength = (int)((long)batchSize * scratchlcv.vector[0]);
         }

         if (totalLength < 0) {
            StringBuilder sb = new StringBuilder("totalLength:" + totalLength + " is a negative number.");
            if (batchSize > 1) {
               sb.append(" The current batch size is ");
               sb.append(batchSize);
               sb.append(", you can reduce the value by '");
               sb.append(OrcConf.ROW_BATCH_SIZE.getAttribute());
               sb.append("'.");
            }

            throw new IOException(sb.toString());
         } else {
            byte[] allBytes = new byte[totalLength];
            int offset = 0;

            int bytesRead;
            for(int len = totalLength; len > 0; offset += bytesRead) {
               bytesRead = stream.read(allBytes, offset, len);
               if (bytesRead < 0) {
                  throw new EOFException("Can't finish byte read from " + String.valueOf(stream));
               }

               len -= bytesRead;
            }

            return allBytes;
         }
      }

      public static void readOrcByteArrays(InStream stream, IntegerReader lengths, LongColumnVector scratchlcv, BytesColumnVector result, int batchSize) throws IOException {
         if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
            byte[] allBytes = commonReadByteArrays(stream, lengths, scratchlcv, result, batchSize);
            result.isRepeating = false;
            int offset = 0;
            if (!scratchlcv.isRepeating) {
               for(int i = 0; i < batchSize; ++i) {
                  if (!scratchlcv.isNull[i]) {
                     result.setRef(i, allBytes, offset, (int)scratchlcv.vector[i]);
                     offset = (int)((long)offset + scratchlcv.vector[i]);
                  } else {
                     result.setRef(i, allBytes, 0, 0);
                  }
               }
            } else {
               for(int i = 0; i < batchSize; ++i) {
                  if (!scratchlcv.isNull[i]) {
                     result.setRef(i, allBytes, offset, (int)scratchlcv.vector[0]);
                     offset = (int)((long)offset + scratchlcv.vector[0]);
                  } else {
                     result.setRef(i, allBytes, 0, 0);
                  }
               }
            }
         }

      }
   }

   public static class StringDirectTreeReader extends TreeReader {
      private static final HadoopShims SHIMS = HadoopShimsFactory.get();
      protected InStream stream;
      protected IntegerReader lengths;
      private final LongColumnVector scratchlcv;

      StringDirectTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding.Kind)null, context);
      }

      protected StringDirectTreeReader(int columnId, InStream present, InStream data, InStream length, OrcProto.ColumnEncoding.Kind encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.scratchlcv = new LongColumnVector();
         this.stream = data;
         if (length != null && encoding != null) {
            this.lengths = createIntegerReader(encoding, length, false, context);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.stream = planner.getStream(name);
         this.lengths = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.LENGTH)), false, this.context);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.stream.seek(index);
         this.lengths.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         BytesColumnVector result = (BytesColumnVector)previousVector;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.scratchlcv.ensureSize(batchSize, false);
         TreeReaderFactory.BytesColumnVectorUtil.readOrcByteArrays(this.stream, this.lengths, this.scratchlcv, result, batchSize);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         long lengthToSkip = 0L;

         for(int i = 0; (long)i < items; ++i) {
            lengthToSkip += this.lengths.next();
         }

         while(lengthToSkip > 0L) {
            lengthToSkip -= this.stream.skip(lengthToSkip);
         }

      }

      public IntegerReader getLengths() {
         return this.lengths;
      }

      public InStream getStream() {
         return this.stream;
      }
   }

   public static class StringDictionaryTreeReader extends TreeReader {
      private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
      private int[] dictionaryOffsets;
      protected IntegerReader reader;
      private InStream lengthStream;
      private InStream dictionaryStream;
      private OrcProto.ColumnEncoding lengthEncoding;
      private byte[] dictionaryBuffer;
      private final LongColumnVector scratchlcv;
      private boolean initDictionary;

      StringDictionaryTreeReader(int columnId, Context context) throws IOException {
         this(columnId, (InStream)null, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected StringDictionaryTreeReader(int columnId, InStream present, InStream data, InStream length, InStream dictionary, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, context);
         this.dictionaryBuffer = null;
         this.initDictionary = false;
         this.scratchlcv = new LongColumnVector();
         if (data != null && encoding != null) {
            this.reader = createIntegerReader(encoding.getKind(), data, false, context);
         }

         this.lengthStream = length;
         this.dictionaryStream = dictionary;
         this.lengthEncoding = encoding;
         this.initDictionary = false;
      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DICTIONARY && encoding.getKind() != Kind.DICTIONARY_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         StreamName name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DICTIONARY_DATA);
         this.dictionaryStream = planner.getStream(name);
         this.initDictionary = false;
         name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.LENGTH);
         InStream in = planner.getStream(name);
         OrcProto.ColumnEncoding encoding = planner.getEncoding(this.columnId);
         this.readDictionaryLengthStream(in, encoding);
         name = new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA);
         this.reader = createIntegerReader(encoding.getKind(), planner.getStream(name), false, this.context);
      }

      private void readDictionaryLengthStream(InStream in, OrcProto.ColumnEncoding encoding) throws IOException {
         int dictionarySize = encoding.getDictionarySize();
         if (in != null) {
            IntegerReader lenReader = createIntegerReader(encoding.getKind(), in, false, this.context);
            int offset = 0;
            if (this.dictionaryOffsets == null || this.dictionaryOffsets.length < dictionarySize + 1) {
               this.dictionaryOffsets = new int[dictionarySize + 1];
            }

            for(int i = 0; i < dictionarySize; ++i) {
               this.dictionaryOffsets[i] = offset;
               offset += (int)lenReader.next();
            }

            this.dictionaryOffsets[dictionarySize] = offset;
            in.close();
         }

      }

      private void readDictionaryStream(InStream in) throws IOException {
         if (in != null) {
            if (in.available() > 0) {
               this.dictionaryBuffer = null;
               int dictionaryBufferSize = this.dictionaryOffsets[this.dictionaryOffsets.length - 1];
               this.dictionaryBuffer = new byte[dictionaryBufferSize];
               int pos = 0;
               int readSize = Math.min(in.available(), dictionaryBufferSize);

               int var7;
               for(byte[] chunkBytes = new byte[readSize]; pos < dictionaryBufferSize; pos += var7) {
                  int currentLength = in.read(chunkBytes, 0, readSize);
                  var7 = Math.min(currentLength, dictionaryBufferSize - pos);
                  System.arraycopy(chunkBytes, 0, this.dictionaryBuffer, pos, var7);
               }
            }

            in.close();
         } else {
            this.dictionaryBuffer = null;
         }

      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.seek(index[this.columnId], readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.reader.seek(index);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         BytesColumnVector result = (BytesColumnVector)previousVector;

         for(int i = 0; i < batchSize; ++i) {
            result.vector[i] = null;
         }

         if (!this.initDictionary) {
            if (this.lengthStream != null && this.lengthEncoding != null) {
               this.readDictionaryLengthStream(this.lengthStream, this.lengthEncoding);
            }

            if (this.dictionaryStream != null) {
               this.readDictionaryStream(this.dictionaryStream);
            }

            this.initDictionary = true;
         }

         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         this.readDictionaryByteArray(result, filterContext, batchSize);
      }

      private void readDictionaryByteArray(BytesColumnVector result, FilterContext filterContext, int batchSize) throws IOException {
         if (this.dictionaryBuffer != null) {
            this.scratchlcv.isRepeating = result.isRepeating;
            this.scratchlcv.noNulls = result.noNulls;
            this.scratchlcv.isNull = result.isNull;
            this.scratchlcv.ensureSize(batchSize, false);
            this.reader.nextVector(this.scratchlcv, (long[])this.scratchlcv.vector, batchSize);
            if (this.scratchlcv.isRepeating) {
               int offset = this.dictionaryOffsets[(int)this.scratchlcv.vector[0]];
               int length = this.getDictionaryEntryLength((int)this.scratchlcv.vector[0], offset);
               result.setRef(0, this.dictionaryBuffer, offset, length);
            } else if (!filterContext.isSelectedInUse()) {
               for(int i = 0; i < batchSize; ++i) {
                  if (!this.scratchlcv.isNull[i]) {
                     int offset = this.dictionaryOffsets[(int)this.scratchlcv.vector[i]];
                     int length = this.getDictionaryEntryLength((int)this.scratchlcv.vector[i], offset);
                     result.setRef(i, this.dictionaryBuffer, offset, length);
                  } else {
                     result.setRef(i, this.dictionaryBuffer, 0, 0);
                  }
               }
            } else {
               for(int i = 0; i < batchSize; ++i) {
                  result.setRef(i, this.dictionaryBuffer, 0, 0);
               }

               for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
                  int idx = filterContext.getSelected()[i];
                  if (!this.scratchlcv.isNull[idx]) {
                     int offset = this.dictionaryOffsets[(int)this.scratchlcv.vector[idx]];
                     int length = this.getDictionaryEntryLength((int)this.scratchlcv.vector[idx], offset);
                     result.setRef(idx, this.dictionaryBuffer, offset, length);
                  }
               }
            }

            result.isRepeating = this.scratchlcv.isRepeating;
         } else if (this.dictionaryOffsets == null) {
            result.isRepeating = true;
            result.noNulls = false;
            result.isNull[0] = true;
            result.setRef(0, EMPTY_BYTE_ARRAY, 0, 0);
         } else {
            for(int i = 0; i < batchSize; ++i) {
               if (!result.isNull[i]) {
                  result.setRef(i, EMPTY_BYTE_ARRAY, 0, 0);
               }
            }
         }

      }

      int getDictionaryEntryLength(int entry, int offset) {
         int length;
         if (entry < this.dictionaryOffsets.length - 1) {
            length = this.dictionaryOffsets[entry + 1] - offset;
         } else {
            length = this.dictionaryBuffer.length - offset;
         }

         return length;
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.reader.skip(this.countNonNulls(items));
      }

      public IntegerReader getReader() {
         return this.reader;
      }
   }

   public static class CharTreeReader extends StringTreeReader {
      int maxLength;

      CharTreeReader(int columnId, int maxLength, Context context) throws IOException {
         this(columnId, maxLength, (InStream)null, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected CharTreeReader(int columnId, int maxLength, InStream present, InStream data, InStream length, InStream dictionary, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, data, length, dictionary, encoding, context);
         this.maxLength = maxLength;
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         super.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
         BytesColumnVector result = (BytesColumnVector)previousVector;
         if (result.isRepeating) {
            if (result.noNulls || !result.isNull[0]) {
               int adjustedDownLen = StringExpr.rightTrimAndTruncate(result.vector[0], result.start[0], result.length[0], this.maxLength);
               if (adjustedDownLen < result.length[0]) {
                  result.setRef(0, result.vector[0], result.start[0], adjustedDownLen);
               }
            }
         } else if (result.noNulls) {
            for(int i = 0; i < batchSize; ++i) {
               int adjustedDownLen = StringExpr.rightTrimAndTruncate(result.vector[i], result.start[i], result.length[i], this.maxLength);
               if (adjustedDownLen < result.length[i]) {
                  result.setRef(i, result.vector[i], result.start[i], adjustedDownLen);
               }
            }
         } else {
            for(int i = 0; i < batchSize; ++i) {
               if (!result.isNull[i]) {
                  int adjustedDownLen = StringExpr.rightTrimAndTruncate(result.vector[i], result.start[i], result.length[i], this.maxLength);
                  if (adjustedDownLen < result.length[i]) {
                     result.setRef(i, result.vector[i], result.start[i], adjustedDownLen);
                  }
               }
            }
         }

      }
   }

   public static class VarcharTreeReader extends StringTreeReader {
      int maxLength;

      VarcharTreeReader(int columnId, int maxLength, Context context) throws IOException {
         this(columnId, maxLength, (InStream)null, (InStream)null, (InStream)null, (InStream)null, (OrcProto.ColumnEncoding)null, context);
      }

      protected VarcharTreeReader(int columnId, int maxLength, InStream present, InStream data, InStream length, InStream dictionary, OrcProto.ColumnEncoding encoding, Context context) throws IOException {
         super(columnId, present, data, length, dictionary, encoding, context);
         this.maxLength = maxLength;
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         super.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
         BytesColumnVector result = (BytesColumnVector)previousVector;
         if (result.isRepeating) {
            if (result.noNulls || !result.isNull[0]) {
               int adjustedDownLen = StringExpr.truncate(result.vector[0], result.start[0], result.length[0], this.maxLength);
               if (adjustedDownLen < result.length[0]) {
                  result.setRef(0, result.vector[0], result.start[0], adjustedDownLen);
               }
            }
         } else if (result.noNulls) {
            for(int i = 0; i < batchSize; ++i) {
               int adjustedDownLen = StringExpr.truncate(result.vector[i], result.start[i], result.length[i], this.maxLength);
               if (adjustedDownLen < result.length[i]) {
                  result.setRef(i, result.vector[i], result.start[i], adjustedDownLen);
               }
            }
         } else {
            for(int i = 0; i < batchSize; ++i) {
               if (!result.isNull[i]) {
                  int adjustedDownLen = StringExpr.truncate(result.vector[i], result.start[i], result.length[i], this.maxLength);
                  if (adjustedDownLen < result.length[i]) {
                     result.setRef(i, result.vector[i], result.start[i], adjustedDownLen);
                  }
               }
            }
         }

      }
   }

   public static class StructTreeReader extends TreeReader {
      public final TypeReader[] fields;

      protected StructTreeReader(int columnId, TypeDescription readerSchema, Context context) throws IOException {
         super(columnId, (InStream)null, context);
         List<TypeDescription> childrenTypes = readerSchema.getChildren();
         this.fields = new TypeReader[childrenTypes.size()];

         for(int i = 0; i < this.fields.length; ++i) {
            TypeDescription subtype = (TypeDescription)childrenTypes.get(i);
            this.fields[i] = TreeReaderFactory.createTreeReader(subtype, context);
         }

      }

      public TypeReader[] getChildReaders() {
         return this.fields;
      }

      protected StructTreeReader(int columnId, InStream present, Context context, OrcProto.ColumnEncoding encoding, TypeReader[] childReaders) throws IOException {
         super(columnId, present, context);
         if (encoding != null) {
            this.checkEncoding(encoding);
         }

         this.fields = childReaders;
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            super.seek(index, readPhase);
         }

         for(TypeReader kid : this.fields) {
            if (kid != null && TypeReader.shouldProcessChild(kid, readPhase)) {
               kid.seek(index, readPhase);
            }
         }

      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            super.seek(index, readPhase);
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         StructColumnVector result = (StructColumnVector)previousVector;
         if (readPhase.contains(this.readerCategory)) {
            super.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
            if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
               result.isRepeating = false;
            }
         }

         if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
            boolean[] mask = result.noNulls ? null : result.isNull;

            for(int f = 0; f < this.fields.length; ++f) {
               if (this.fields[f] != null && TypeReader.shouldProcessChild(this.fields[f], readPhase)) {
                  this.fields[f].nextVector(result.fields[f], mask, batchSize, filterContext, readPhase);
               }
            }
         }

      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            super.startStripe(planner, readPhase);
         }

         for(TypeReader field : this.fields) {
            if (field != null && TypeReader.shouldProcessChild(field, readPhase)) {
               field.startStripe(planner, readPhase);
            }
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            items = this.countNonNulls(items);

            for(TypeReader field : this.fields) {
               if (field != null && TypeReader.shouldProcessChild(field, readPhase)) {
                  field.skipRows(items, readPhase);
               }
            }

         }
      }
   }

   public static class UnionTreeReader extends TreeReader {
      protected final TypeReader[] fields;
      protected RunLengthByteReader tags;

      protected UnionTreeReader(int fileColumn, TypeDescription readerSchema, Context context) throws IOException {
         super(fileColumn, (InStream)null, context);
         List<TypeDescription> childrenTypes = readerSchema.getChildren();
         int fieldCount = childrenTypes.size();
         this.fields = new TypeReader[fieldCount];

         for(int i = 0; i < fieldCount; ++i) {
            TypeDescription subtype = (TypeDescription)childrenTypes.get(i);
            this.fields[i] = TreeReaderFactory.createTreeReader(subtype, context);
         }

      }

      protected UnionTreeReader(int columnId, InStream present, Context context, OrcProto.ColumnEncoding encoding, TypeReader[] childReaders) throws IOException {
         super(columnId, present, context);
         if (encoding != null) {
            this.checkEncoding(encoding);
         }

         this.fields = childReaders;
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            super.seek(index, readPhase);
            this.tags.seek(index[this.columnId]);
         }

         for(TypeReader kid : this.fields) {
            if (TypeReader.shouldProcessChild(kid, readPhase)) {
               kid.seek(index, readPhase);
            }
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         UnionColumnVector result = (UnionColumnVector)previousVector;
         if (readPhase.contains(this.readerCategory)) {
            super.nextVector(result, isNull, batchSize, filterContext, readPhase);
            if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
               result.isRepeating = false;
               this.tags.nextVector(result.noNulls ? null : result.isNull, result.tags, (long)batchSize);
            }
         }

         if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
            boolean[] ignore = new boolean[batchSize];

            for(int f = 0; f < result.fields.length; ++f) {
               if (TypeReader.shouldProcessChild(this.fields[f], readPhase)) {
                  for(int r = 0; r < batchSize; ++r) {
                     ignore[r] = !result.noNulls && result.isNull[r] || result.tags[r] != f;
                  }

                  this.fields[f].nextVector(result.fields[f], ignore, batchSize, filterContext, readPhase);
               }
            }
         }

      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            super.startStripe(planner, readPhase);
            this.tags = new RunLengthByteReader(planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.DATA)));
         }

         for(TypeReader field : this.fields) {
            if (field != null && TypeReader.shouldProcessChild(field, readPhase)) {
               field.startStripe(planner, readPhase);
            }
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         if (readPhase.contains(this.readerCategory)) {
            items = this.countNonNulls(items);
            long[] counts = new long[this.fields.length];

            for(int i = 0; (long)i < items; ++i) {
               ++counts[this.tags.next()];
            }

            for(int i = 0; i < counts.length; ++i) {
               if (TypeReader.shouldProcessChild(this.fields[i], readPhase)) {
                  this.fields[i].skipRows(counts[i], readPhase);
               }
            }

         }
      }
   }

   public static class ListTreeReader extends TreeReader {
      protected final TypeReader elementReader;
      protected IntegerReader lengths = null;

      protected ListTreeReader(int fileColumn, TypeDescription readerSchema, Context context) throws IOException {
         super(fileColumn, context);
         TypeDescription elementType = (TypeDescription)readerSchema.getChildren().get(0);
         this.elementReader = TreeReaderFactory.createTreeReader(elementType, context);
      }

      protected ListTreeReader(int columnId, InStream present, Context context, InStream data, OrcProto.ColumnEncoding encoding, TypeReader elementReader) throws IOException {
         super(columnId, present, context);
         if (data != null && encoding != null) {
            this.checkEncoding(encoding);
            this.lengths = createIntegerReader(encoding.getKind(), data, false, context);
         }

         this.elementReader = elementReader;
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.lengths.seek(index[this.columnId]);
         this.elementReader.seek(index, readPhase);
      }

      public void nextVector(ColumnVector previous, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         ListColumnVector result = (ListColumnVector)previous;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
            this.lengths.nextVector(result, (long[])result.lengths, batchSize);
            result.isRepeating = false;
            result.childCount = 0;

            for(int r = 0; r < batchSize; ++r) {
               if (result.noNulls || !result.isNull[r]) {
                  result.offsets[r] = (long)result.childCount;
                  result.childCount = (int)((long)result.childCount + result.lengths[r]);
               }
            }

            result.child.ensureSize(result.childCount, false);
            this.elementReader.nextVector(result.child, (boolean[])null, result.childCount, TreeReaderFactory.NULL_FILTER, readPhase);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         this.lengths = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.LENGTH)), false, this.context);
         if (this.elementReader != null) {
            this.elementReader.startStripe(planner, readPhase);
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         long childSkip = 0L;

         for(long i = 0L; i < items; ++i) {
            childSkip += this.lengths.next();
         }

         this.elementReader.skipRows(childSkip, readPhase);
      }
   }

   public static class MapTreeReader extends TreeReader {
      protected final TypeReader keyReader;
      protected final TypeReader valueReader;
      protected IntegerReader lengths = null;

      protected MapTreeReader(int fileColumn, TypeDescription readerSchema, Context context) throws IOException {
         super(fileColumn, context);
         TypeDescription keyType = (TypeDescription)readerSchema.getChildren().get(0);
         TypeDescription valueType = (TypeDescription)readerSchema.getChildren().get(1);
         this.keyReader = TreeReaderFactory.createTreeReader(keyType, context);
         this.valueReader = TreeReaderFactory.createTreeReader(valueType, context);
      }

      protected MapTreeReader(int columnId, InStream present, Context context, InStream data, OrcProto.ColumnEncoding encoding, TypeReader keyReader, TypeReader valueReader) throws IOException {
         super(columnId, present, context);
         if (data != null && encoding != null) {
            this.checkEncoding(encoding);
            this.lengths = createIntegerReader(encoding.getKind(), data, false, context);
         }

         this.keyReader = keyReader;
         this.valueReader = valueReader;
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         super.seek(index, readPhase);
         this.lengths.seek(index[this.columnId]);
         this.keyReader.seek(index, readPhase);
         this.valueReader.seek(index, readPhase);
      }

      public void nextVector(ColumnVector previous, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         MapColumnVector result = (MapColumnVector)previous;
         super.nextVector(result, isNull, batchSize, filterContext, readPhase);
         if (result.noNulls || !result.isRepeating || !result.isNull[0]) {
            this.lengths.nextVector(result, (long[])result.lengths, batchSize);
            result.isRepeating = false;
            result.childCount = 0;

            for(int r = 0; r < batchSize; ++r) {
               if (result.noNulls || !result.isNull[r]) {
                  result.offsets[r] = (long)result.childCount;
                  result.childCount = (int)((long)result.childCount + result.lengths[r]);
               }
            }

            result.keys.ensureSize(result.childCount, false);
            result.values.ensureSize(result.childCount, false);
            this.keyReader.nextVector(result.keys, (boolean[])null, result.childCount, TreeReaderFactory.NULL_FILTER, readPhase);
            this.valueReader.nextVector(result.values, (boolean[])null, result.childCount, TreeReaderFactory.NULL_FILTER, readPhase);
         }

      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         if (encoding.getKind() != Kind.DIRECT && encoding.getKind() != Kind.DIRECT_V2) {
            String var10002 = String.valueOf(encoding);
            throw new IOException("Unknown encoding " + var10002 + " in column " + this.columnId);
         }
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         super.startStripe(planner, readPhase);
         this.lengths = createIntegerReader(planner.getEncoding(this.columnId).getKind(), planner.getStream(new StreamName(this.columnId, org.apache.orc.OrcProto.Stream.Kind.LENGTH)), false, this.context);
         if (this.keyReader != null) {
            this.keyReader.startStripe(planner, readPhase);
         }

         if (this.valueReader != null) {
            this.valueReader.startStripe(planner, readPhase);
         }

      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         items = this.countNonNulls(items);
         long childSkip = 0L;

         for(long i = 0L; i < items; ++i) {
            childSkip += this.lengths.next();
         }

         this.keyReader.skipRows(childSkip, readPhase);
         this.valueReader.skipRows(childSkip, readPhase);
      }
   }

   public interface Context {
      SchemaEvolution getSchemaEvolution();

      Set getColumnFilterIds();

      Consumer getColumnFilterCallback();

      boolean isSkipCorrupt();

      boolean getUseUTCTimestamp();

      String getWriterTimezone();

      OrcFile.Version getFileFormat();

      ReaderEncryption getEncryption();

      boolean useProlepticGregorian();

      boolean fileUsedProlepticGregorian();

      TypeReader.ReaderCategory getReaderCategory(int var1);
   }
}

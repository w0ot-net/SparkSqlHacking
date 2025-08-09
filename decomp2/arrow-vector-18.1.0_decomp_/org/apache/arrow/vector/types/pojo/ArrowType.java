package org.apache.arrow.vector.types.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.flatbuffers.FlatBufferBuilder;
import java.util.Arrays;
import java.util.Objects;
import org.apache.arrow.flatbuf.Struct_;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.types.DateUnit;
import org.apache.arrow.vector.types.FloatingPointPrecision;
import org.apache.arrow.vector.types.IntervalUnit;
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.UnionMode;

@JsonTypeInfo(
   use = Id.NAME,
   include = As.PROPERTY,
   property = "name"
)
@JsonSubTypes({@Type(
   value = Null.class,
   name = "null"
), @Type(
   value = Struct.class,
   name = "struct"
), @Type(
   value = List.class,
   name = "list"
), @Type(
   value = LargeList.class,
   name = "largelist"
), @Type(
   value = FixedSizeList.class,
   name = "fixedsizelist"
), @Type(
   value = Union.class,
   name = "union"
), @Type(
   value = Map.class,
   name = "map"
), @Type(
   value = Int.class,
   name = "int"
), @Type(
   value = FloatingPoint.class,
   name = "floatingpoint"
), @Type(
   value = Utf8.class,
   name = "utf8"
), @Type(
   value = Utf8View.class,
   name = "utf8view"
), @Type(
   value = LargeUtf8.class,
   name = "largeutf8"
), @Type(
   value = Binary.class,
   name = "binary"
), @Type(
   value = BinaryView.class,
   name = "binaryview"
), @Type(
   value = LargeBinary.class,
   name = "largebinary"
), @Type(
   value = FixedSizeBinary.class,
   name = "fixedsizebinary"
), @Type(
   value = Bool.class,
   name = "bool"
), @Type(
   value = Decimal.class,
   name = "decimal"
), @Type(
   value = Date.class,
   name = "date"
), @Type(
   value = Time.class,
   name = "time"
), @Type(
   value = Timestamp.class,
   name = "timestamp"
), @Type(
   value = Interval.class,
   name = "interval"
), @Type(
   value = Duration.class,
   name = "duration"
), @Type(
   value = ListView.class,
   name = "listview"
), @Type(
   value = LargeListView.class,
   name = "largelistview"
), @Type(
   value = RunEndEncoded.class,
   name = "runendencoded"
)})
public abstract class ArrowType {
   private static final int defaultDecimalBitWidth = 128;

   @JsonIgnore
   public abstract ArrowTypeID getTypeID();

   @JsonIgnore
   public abstract boolean isComplex();

   public abstract int getType(FlatBufferBuilder var1);

   public abstract Object accept(ArrowTypeVisitor var1);

   public static ArrowType getTypeForField(org.apache.arrow.flatbuf.Field field) {
      switch (field.typeType()) {
         case 1:
            org.apache.arrow.flatbuf.Null nullType = (org.apache.arrow.flatbuf.Null)field.type(new org.apache.arrow.flatbuf.Null());
            return new Null();
         case 2:
            org.apache.arrow.flatbuf.Int intType = (org.apache.arrow.flatbuf.Int)field.type(new org.apache.arrow.flatbuf.Int());
            int bitWidth = intType.bitWidth();
            boolean isSigned = intType.isSigned();
            return new Int(bitWidth, isSigned);
         case 3:
            org.apache.arrow.flatbuf.FloatingPoint floatingpointType = (org.apache.arrow.flatbuf.FloatingPoint)field.type(new org.apache.arrow.flatbuf.FloatingPoint());
            short precision = floatingpointType.precision();
            return new FloatingPoint(FloatingPointPrecision.fromFlatbufID(precision));
         case 4:
            org.apache.arrow.flatbuf.Binary binaryType = (org.apache.arrow.flatbuf.Binary)field.type(new org.apache.arrow.flatbuf.Binary());
            return new Binary();
         case 5:
            org.apache.arrow.flatbuf.Utf8 utf8Type = (org.apache.arrow.flatbuf.Utf8)field.type(new org.apache.arrow.flatbuf.Utf8());
            return new Utf8();
         case 6:
            org.apache.arrow.flatbuf.Bool boolType = (org.apache.arrow.flatbuf.Bool)field.type(new org.apache.arrow.flatbuf.Bool());
            return new Bool();
         case 7:
            org.apache.arrow.flatbuf.Decimal decimalType = (org.apache.arrow.flatbuf.Decimal)field.type(new org.apache.arrow.flatbuf.Decimal());
            int precision = decimalType.precision();
            int scale = decimalType.scale();
            int bitWidth = decimalType.bitWidth();
            if (bitWidth != 128 && bitWidth != 256) {
               throw new IllegalArgumentException("Library only supports 128-bit and 256-bit decimal values");
            }

            return new Decimal(precision, scale, bitWidth);
         case 8:
            org.apache.arrow.flatbuf.Date dateType = (org.apache.arrow.flatbuf.Date)field.type(new org.apache.arrow.flatbuf.Date());
            short unit = dateType.unit();
            return new Date(DateUnit.fromFlatbufID(unit));
         case 9:
            org.apache.arrow.flatbuf.Time timeType = (org.apache.arrow.flatbuf.Time)field.type(new org.apache.arrow.flatbuf.Time());
            short unit = timeType.unit();
            int bitWidth = timeType.bitWidth();
            return new Time(TimeUnit.fromFlatbufID(unit), bitWidth);
         case 10:
            org.apache.arrow.flatbuf.Timestamp timestampType = (org.apache.arrow.flatbuf.Timestamp)field.type(new org.apache.arrow.flatbuf.Timestamp());
            short unit = timestampType.unit();
            String timezone = timestampType.timezone();
            return new Timestamp(TimeUnit.fromFlatbufID(unit), timezone);
         case 11:
            org.apache.arrow.flatbuf.Interval intervalType = (org.apache.arrow.flatbuf.Interval)field.type(new org.apache.arrow.flatbuf.Interval());
            short unit = intervalType.unit();
            return new Interval(IntervalUnit.fromFlatbufID(unit));
         case 12:
            org.apache.arrow.flatbuf.List listType = (org.apache.arrow.flatbuf.List)field.type(new org.apache.arrow.flatbuf.List());
            return new List();
         case 13:
            Struct_ struct_Type = (Struct_)field.type(new Struct_());
            return new Struct();
         case 14:
            org.apache.arrow.flatbuf.Union unionType = (org.apache.arrow.flatbuf.Union)field.type(new org.apache.arrow.flatbuf.Union());
            short mode = unionType.mode();
            int[] typeIds = new int[unionType.typeIdsLength()];

            for(int i = 0; i < typeIds.length; ++i) {
               typeIds[i] = unionType.typeIds(i);
            }

            return new Union(UnionMode.fromFlatbufID(mode), typeIds);
         case 15:
            org.apache.arrow.flatbuf.FixedSizeBinary fixedsizebinaryType = (org.apache.arrow.flatbuf.FixedSizeBinary)field.type(new org.apache.arrow.flatbuf.FixedSizeBinary());
            int byteWidth = fixedsizebinaryType.byteWidth();
            return new FixedSizeBinary(byteWidth);
         case 16:
            org.apache.arrow.flatbuf.FixedSizeList fixedsizelistType = (org.apache.arrow.flatbuf.FixedSizeList)field.type(new org.apache.arrow.flatbuf.FixedSizeList());
            int listSize = fixedsizelistType.listSize();
            return new FixedSizeList(listSize);
         case 17:
            org.apache.arrow.flatbuf.Map mapType = (org.apache.arrow.flatbuf.Map)field.type(new org.apache.arrow.flatbuf.Map());
            boolean keysSorted = mapType.keysSorted();
            return new Map(keysSorted);
         case 18:
            org.apache.arrow.flatbuf.Duration durationType = (org.apache.arrow.flatbuf.Duration)field.type(new org.apache.arrow.flatbuf.Duration());
            short unit = durationType.unit();
            return new Duration(TimeUnit.fromFlatbufID(unit));
         case 19:
            org.apache.arrow.flatbuf.LargeBinary largebinaryType = (org.apache.arrow.flatbuf.LargeBinary)field.type(new org.apache.arrow.flatbuf.LargeBinary());
            return new LargeBinary();
         case 20:
            org.apache.arrow.flatbuf.LargeUtf8 largeutf8Type = (org.apache.arrow.flatbuf.LargeUtf8)field.type(new org.apache.arrow.flatbuf.LargeUtf8());
            return new LargeUtf8();
         case 21:
            org.apache.arrow.flatbuf.LargeList largelistType = (org.apache.arrow.flatbuf.LargeList)field.type(new org.apache.arrow.flatbuf.LargeList());
            return new LargeList();
         case 22:
            org.apache.arrow.flatbuf.RunEndEncoded runendencodedType = (org.apache.arrow.flatbuf.RunEndEncoded)field.type(new org.apache.arrow.flatbuf.RunEndEncoded());
            return new RunEndEncoded();
         case 23:
            org.apache.arrow.flatbuf.BinaryView binaryviewType = (org.apache.arrow.flatbuf.BinaryView)field.type(new org.apache.arrow.flatbuf.BinaryView());
            return new BinaryView();
         case 24:
            org.apache.arrow.flatbuf.Utf8View utf8viewType = (org.apache.arrow.flatbuf.Utf8View)field.type(new org.apache.arrow.flatbuf.Utf8View());
            return new Utf8View();
         case 25:
            org.apache.arrow.flatbuf.ListView listviewType = (org.apache.arrow.flatbuf.ListView)field.type(new org.apache.arrow.flatbuf.ListView());
            return new ListView();
         case 26:
            org.apache.arrow.flatbuf.LargeListView largelistviewType = (org.apache.arrow.flatbuf.LargeListView)field.type(new org.apache.arrow.flatbuf.LargeListView());
            return new LargeListView();
         default:
            throw new UnsupportedOperationException("Unsupported type: " + field.typeType());
      }
   }

   public static Int getInt(org.apache.arrow.flatbuf.Field field) {
      org.apache.arrow.flatbuf.Int intType = (org.apache.arrow.flatbuf.Int)field.type(new org.apache.arrow.flatbuf.Int());
      return new Int(intType.bitWidth(), intType.isSigned());
   }

   public abstract static class PrimitiveType extends ArrowType {
      private PrimitiveType() {
      }

      public boolean isComplex() {
         return false;
      }
   }

   public abstract static class ComplexType extends ArrowType {
      private ComplexType() {
      }

      public boolean isComplex() {
         return true;
      }
   }

   public static enum ArrowTypeID {
      Null((byte)1),
      Struct((byte)13),
      List((byte)12),
      LargeList((byte)21),
      FixedSizeList((byte)16),
      Union((byte)14),
      Map((byte)17),
      Int((byte)2),
      FloatingPoint((byte)3),
      Utf8((byte)5),
      Utf8View((byte)24),
      LargeUtf8((byte)20),
      Binary((byte)4),
      BinaryView((byte)23),
      LargeBinary((byte)19),
      FixedSizeBinary((byte)15),
      Bool((byte)6),
      Decimal((byte)7),
      Date((byte)8),
      Time((byte)9),
      Timestamp((byte)10),
      Interval((byte)11),
      Duration((byte)18),
      ListView((byte)25),
      LargeListView((byte)26),
      RunEndEncoded((byte)22),
      NONE((byte)0);

      private final byte flatbufType;

      public byte getFlatbufID() {
         return this.flatbufType;
      }

      private ArrowTypeID(byte flatbufType) {
         this.flatbufType = flatbufType;
      }

      // $FF: synthetic method
      private static ArrowTypeID[] $values() {
         return new ArrowTypeID[]{Null, Struct, List, LargeList, FixedSizeList, Union, Map, Int, FloatingPoint, Utf8, Utf8View, LargeUtf8, Binary, BinaryView, LargeBinary, FixedSizeBinary, Bool, Decimal, Date, Time, Timestamp, Interval, Duration, ListView, LargeListView, RunEndEncoded, NONE};
      }
   }

   public interface ArrowTypeVisitor {
      Object visit(Null var1);

      Object visit(Struct var1);

      Object visit(List var1);

      Object visit(LargeList var1);

      Object visit(FixedSizeList var1);

      Object visit(Union var1);

      Object visit(Map var1);

      Object visit(Int var1);

      Object visit(FloatingPoint var1);

      Object visit(Utf8 var1);

      Object visit(Utf8View var1);

      Object visit(LargeUtf8 var1);

      Object visit(Binary var1);

      Object visit(BinaryView var1);

      Object visit(LargeBinary var1);

      Object visit(FixedSizeBinary var1);

      Object visit(Bool var1);

      Object visit(Decimal var1);

      Object visit(Date var1);

      Object visit(Time var1);

      Object visit(Timestamp var1);

      Object visit(Interval var1);

      Object visit(Duration var1);

      Object visit(ListView var1);

      Object visit(LargeListView var1);

      Object visit(RunEndEncoded var1);

      default Object visit(ExtensionType type) {
         return type.storageType().accept(this);
      }
   }

   public abstract static class ComplexTypeVisitor implements ArrowTypeVisitor {
      public Object visit(PrimitiveType type) {
         throw new UnsupportedOperationException("Unexpected Primitive type: " + String.valueOf(type));
      }

      public final Object visit(Null type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Int type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(FloatingPoint type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Utf8 type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Utf8View type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(LargeUtf8 type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Binary type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(BinaryView type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(LargeBinary type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(FixedSizeBinary type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Bool type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Decimal type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Date type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Time type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Timestamp type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Interval type) {
         return this.visit((PrimitiveType)type);
      }

      public final Object visit(Duration type) {
         return this.visit((PrimitiveType)type);
      }
   }

   public abstract static class PrimitiveTypeVisitor implements ArrowTypeVisitor {
      public Object visit(ComplexType type) {
         throw new UnsupportedOperationException("Unexpected Complex type: " + String.valueOf(type));
      }

      public final Object visit(Struct type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(List type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(LargeList type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(FixedSizeList type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(Union type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(Map type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(ListView type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(LargeListView type) {
         return this.visit((ComplexType)type);
      }

      public final Object visit(RunEndEncoded type) {
         return this.visit((ComplexType)type);
      }
   }

   public static class Null extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final Null INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Null.startNull(builder);
         return org.apache.arrow.flatbuf.Null.endNull(builder);
      }

      public String toString() {
         return "Null";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof Null;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Null;
         INSTANCE = new Null();
      }
   }

   public static class Struct extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final Struct INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         Struct_.startStruct_(builder);
         return Struct_.endStruct_(builder);
      }

      public String toString() {
         return "Struct";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof Struct;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Struct;
         INSTANCE = new Struct();
      }
   }

   public static class List extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final List INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.List.startList(builder);
         return org.apache.arrow.flatbuf.List.endList(builder);
      }

      public String toString() {
         return "List";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof List;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.List;
         INSTANCE = new List();
      }
   }

   public static class LargeList extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final LargeList INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.LargeList.startLargeList(builder);
         return org.apache.arrow.flatbuf.LargeList.endLargeList(builder);
      }

      public String toString() {
         return "LargeList";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof LargeList;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.LargeList;
         INSTANCE = new LargeList();
      }
   }

   public static class FixedSizeList extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      int listSize;

      @JsonCreator
      public FixedSizeList(@JsonProperty("listSize") int listSize) {
         this.listSize = listSize;
      }

      public int getListSize() {
         return this.listSize;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.FixedSizeList.startFixedSizeList(builder);
         org.apache.arrow.flatbuf.FixedSizeList.addListSize(builder, this.listSize);
         return org.apache.arrow.flatbuf.FixedSizeList.endFixedSizeList(builder);
      }

      public String toString() {
         return "FixedSizeList(" + this.listSize + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.listSize});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof FixedSizeList)) {
            return false;
         } else {
            FixedSizeList that = (FixedSizeList)obj;
            return Objects.deepEquals(this.listSize, that.listSize);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.FixedSizeList;
      }
   }

   public static class Union extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      UnionMode mode;
      int[] typeIds;

      @JsonCreator
      public Union(@JsonProperty("mode") UnionMode mode, @JsonProperty("typeIds") int[] typeIds) {
         this.mode = mode;
         this.typeIds = typeIds;
      }

      public UnionMode getMode() {
         return this.mode;
      }

      public int[] getTypeIds() {
         return this.typeIds;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         int typeIds = this.typeIds == null ? -1 : org.apache.arrow.flatbuf.Union.createTypeIdsVector(builder, this.typeIds);
         org.apache.arrow.flatbuf.Union.startUnion(builder);
         org.apache.arrow.flatbuf.Union.addMode(builder, this.mode.getFlatbufID());
         if (this.typeIds != null) {
            org.apache.arrow.flatbuf.Union.addTypeIds(builder, typeIds);
         }

         return org.apache.arrow.flatbuf.Union.endUnion(builder);
      }

      public String toString() {
         String var10000 = String.valueOf(this.mode);
         return "Union(" + var10000 + ", " + Arrays.toString(this.typeIds) + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.mode, this.typeIds});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Union)) {
            return false;
         } else {
            Union that = (Union)obj;
            return Objects.deepEquals(this.mode, that.mode) && Objects.deepEquals(this.typeIds, that.typeIds);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Union;
      }
   }

   public static class Map extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      boolean keysSorted;

      @JsonCreator
      public Map(@JsonProperty("keysSorted") boolean keysSorted) {
         this.keysSorted = keysSorted;
      }

      public boolean getKeysSorted() {
         return this.keysSorted;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Map.startMap(builder);
         org.apache.arrow.flatbuf.Map.addKeysSorted(builder, this.keysSorted);
         return org.apache.arrow.flatbuf.Map.endMap(builder);
      }

      public String toString() {
         return "Map(" + this.keysSorted + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.keysSorted});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Map)) {
            return false;
         } else {
            Map that = (Map)obj;
            return Objects.deepEquals(this.keysSorted, that.keysSorted);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Map;
      }
   }

   public static class Int extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      int bitWidth;
      boolean isSigned;

      @JsonCreator
      public Int(@JsonProperty("bitWidth") int bitWidth, @JsonProperty("isSigned") boolean isSigned) {
         this.bitWidth = bitWidth;
         this.isSigned = isSigned;
      }

      public int getBitWidth() {
         return this.bitWidth;
      }

      public boolean getIsSigned() {
         return this.isSigned;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Int.startInt(builder);
         org.apache.arrow.flatbuf.Int.addBitWidth(builder, this.bitWidth);
         org.apache.arrow.flatbuf.Int.addIsSigned(builder, this.isSigned);
         return org.apache.arrow.flatbuf.Int.endInt(builder);
      }

      public String toString() {
         return "Int(" + this.bitWidth + ", " + this.isSigned + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.bitWidth, this.isSigned});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Int)) {
            return false;
         } else {
            Int that = (Int)obj;
            return Objects.deepEquals(this.bitWidth, that.bitWidth) && Objects.deepEquals(this.isSigned, that.isSigned);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Int;
      }
   }

   public static class FloatingPoint extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      FloatingPointPrecision precision;

      @JsonCreator
      public FloatingPoint(@JsonProperty("precision") FloatingPointPrecision precision) {
         this.precision = precision;
      }

      public FloatingPointPrecision getPrecision() {
         return this.precision;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.FloatingPoint.startFloatingPoint(builder);
         org.apache.arrow.flatbuf.FloatingPoint.addPrecision(builder, this.precision.getFlatbufID());
         return org.apache.arrow.flatbuf.FloatingPoint.endFloatingPoint(builder);
      }

      public String toString() {
         return "FloatingPoint(" + String.valueOf(this.precision) + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.precision});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof FloatingPoint)) {
            return false;
         } else {
            FloatingPoint that = (FloatingPoint)obj;
            return Objects.deepEquals(this.precision, that.precision);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.FloatingPoint;
      }
   }

   public static class Utf8 extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final Utf8 INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Utf8.startUtf8(builder);
         return org.apache.arrow.flatbuf.Utf8.endUtf8(builder);
      }

      public String toString() {
         return "Utf8";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof Utf8;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Utf8;
         INSTANCE = new Utf8();
      }
   }

   public static class Utf8View extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final Utf8View INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Utf8View.startUtf8View(builder);
         return org.apache.arrow.flatbuf.Utf8View.endUtf8View(builder);
      }

      public String toString() {
         return "Utf8View";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof Utf8View;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Utf8View;
         INSTANCE = new Utf8View();
      }
   }

   public static class LargeUtf8 extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final LargeUtf8 INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.LargeUtf8.startLargeUtf8(builder);
         return org.apache.arrow.flatbuf.LargeUtf8.endLargeUtf8(builder);
      }

      public String toString() {
         return "LargeUtf8";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof LargeUtf8;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.LargeUtf8;
         INSTANCE = new LargeUtf8();
      }
   }

   public static class Binary extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final Binary INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Binary.startBinary(builder);
         return org.apache.arrow.flatbuf.Binary.endBinary(builder);
      }

      public String toString() {
         return "Binary";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof Binary;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Binary;
         INSTANCE = new Binary();
      }
   }

   public static class BinaryView extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final BinaryView INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.BinaryView.startBinaryView(builder);
         return org.apache.arrow.flatbuf.BinaryView.endBinaryView(builder);
      }

      public String toString() {
         return "BinaryView";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof BinaryView;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.BinaryView;
         INSTANCE = new BinaryView();
      }
   }

   public static class LargeBinary extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final LargeBinary INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.LargeBinary.startLargeBinary(builder);
         return org.apache.arrow.flatbuf.LargeBinary.endLargeBinary(builder);
      }

      public String toString() {
         return "LargeBinary";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof LargeBinary;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.LargeBinary;
         INSTANCE = new LargeBinary();
      }
   }

   public static class FixedSizeBinary extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      int byteWidth;

      @JsonCreator
      public FixedSizeBinary(@JsonProperty("byteWidth") int byteWidth) {
         this.byteWidth = byteWidth;
      }

      public int getByteWidth() {
         return this.byteWidth;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.FixedSizeBinary.startFixedSizeBinary(builder);
         org.apache.arrow.flatbuf.FixedSizeBinary.addByteWidth(builder, this.byteWidth);
         return org.apache.arrow.flatbuf.FixedSizeBinary.endFixedSizeBinary(builder);
      }

      public String toString() {
         return "FixedSizeBinary(" + this.byteWidth + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.byteWidth});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof FixedSizeBinary)) {
            return false;
         } else {
            FixedSizeBinary that = (FixedSizeBinary)obj;
            return Objects.deepEquals(this.byteWidth, that.byteWidth);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.FixedSizeBinary;
      }
   }

   public static class Bool extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final Bool INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Bool.startBool(builder);
         return org.apache.arrow.flatbuf.Bool.endBool(builder);
      }

      public String toString() {
         return "Bool";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof Bool;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Bool;
         INSTANCE = new Bool();
      }
   }

   public static class Decimal extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      int precision;
      int scale;
      int bitWidth;

      @JsonCreator
      public static Decimal createDecimal(@JsonProperty("precision") int precision, @JsonProperty("scale") int scale, @JsonProperty("bitWidth") Integer bitWidth) {
         return new Decimal(precision, scale, bitWidth == null ? 128 : bitWidth);
      }

      /** @deprecated */
      @Deprecated
      public Decimal(int precision, int scale) {
         this(precision, scale, 128);
      }

      public Decimal(@JsonProperty("precision") int precision, @JsonProperty("scale") int scale, @JsonProperty("bitWidth") int bitWidth) {
         this.precision = precision;
         this.scale = scale;
         this.bitWidth = bitWidth;
      }

      public int getPrecision() {
         return this.precision;
      }

      public int getScale() {
         return this.scale;
      }

      public int getBitWidth() {
         return this.bitWidth;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Decimal.startDecimal(builder);
         org.apache.arrow.flatbuf.Decimal.addPrecision(builder, this.precision);
         org.apache.arrow.flatbuf.Decimal.addScale(builder, this.scale);
         org.apache.arrow.flatbuf.Decimal.addBitWidth(builder, this.bitWidth);
         return org.apache.arrow.flatbuf.Decimal.endDecimal(builder);
      }

      public String toString() {
         return "Decimal(" + this.precision + ", " + this.scale + ", " + this.bitWidth + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.precision, this.scale, this.bitWidth});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Decimal)) {
            return false;
         } else {
            Decimal that = (Decimal)obj;
            return Objects.deepEquals(this.precision, that.precision) && Objects.deepEquals(this.scale, that.scale) && Objects.deepEquals(this.bitWidth, that.bitWidth);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Decimal;
      }
   }

   public static class Date extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      DateUnit unit;

      @JsonCreator
      public Date(@JsonProperty("unit") DateUnit unit) {
         this.unit = unit;
      }

      public DateUnit getUnit() {
         return this.unit;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Date.startDate(builder);
         org.apache.arrow.flatbuf.Date.addUnit(builder, this.unit.getFlatbufID());
         return org.apache.arrow.flatbuf.Date.endDate(builder);
      }

      public String toString() {
         return "Date(" + String.valueOf(this.unit) + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.unit});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Date)) {
            return false;
         } else {
            Date that = (Date)obj;
            return Objects.deepEquals(this.unit, that.unit);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Date;
      }
   }

   public static class Time extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      TimeUnit unit;
      int bitWidth;

      @JsonCreator
      public Time(@JsonProperty("unit") TimeUnit unit, @JsonProperty("bitWidth") int bitWidth) {
         this.unit = unit;
         this.bitWidth = bitWidth;
      }

      public TimeUnit getUnit() {
         return this.unit;
      }

      public int getBitWidth() {
         return this.bitWidth;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Time.startTime(builder);
         org.apache.arrow.flatbuf.Time.addUnit(builder, this.unit.getFlatbufID());
         org.apache.arrow.flatbuf.Time.addBitWidth(builder, this.bitWidth);
         return org.apache.arrow.flatbuf.Time.endTime(builder);
      }

      public String toString() {
         String var10000 = String.valueOf(this.unit);
         return "Time(" + var10000 + ", " + this.bitWidth + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.unit, this.bitWidth});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Time)) {
            return false;
         } else {
            Time that = (Time)obj;
            return Objects.deepEquals(this.unit, that.unit) && Objects.deepEquals(this.bitWidth, that.bitWidth);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Time;
      }
   }

   public static class Timestamp extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      TimeUnit unit;
      String timezone;

      @JsonCreator
      public Timestamp(@JsonProperty("unit") TimeUnit unit, @JsonProperty("timezone") String timezone) {
         this.unit = unit;
         this.timezone = timezone;
      }

      public TimeUnit getUnit() {
         return this.unit;
      }

      public String getTimezone() {
         return this.timezone;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         int timezone = this.timezone == null ? -1 : builder.createString(this.timezone);
         org.apache.arrow.flatbuf.Timestamp.startTimestamp(builder);
         org.apache.arrow.flatbuf.Timestamp.addUnit(builder, this.unit.getFlatbufID());
         if (this.timezone != null) {
            org.apache.arrow.flatbuf.Timestamp.addTimezone(builder, timezone);
         }

         return org.apache.arrow.flatbuf.Timestamp.endTimestamp(builder);
      }

      public String toString() {
         String var10000 = String.valueOf(this.unit);
         return "Timestamp(" + var10000 + ", " + this.timezone + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.unit, this.timezone});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Timestamp)) {
            return false;
         } else {
            Timestamp that = (Timestamp)obj;
            return Objects.deepEquals(this.unit, that.unit) && Objects.deepEquals(this.timezone, that.timezone);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Timestamp;
      }
   }

   public static class Interval extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      IntervalUnit unit;

      @JsonCreator
      public Interval(@JsonProperty("unit") IntervalUnit unit) {
         this.unit = unit;
      }

      public IntervalUnit getUnit() {
         return this.unit;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Interval.startInterval(builder);
         org.apache.arrow.flatbuf.Interval.addUnit(builder, this.unit.getFlatbufID());
         return org.apache.arrow.flatbuf.Interval.endInterval(builder);
      }

      public String toString() {
         return "Interval(" + String.valueOf(this.unit) + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.unit});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Interval)) {
            return false;
         } else {
            Interval that = (Interval)obj;
            return Objects.deepEquals(this.unit, that.unit);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Interval;
      }
   }

   public static class Duration extends PrimitiveType {
      public static final ArrowTypeID TYPE_TYPE;
      TimeUnit unit;

      @JsonCreator
      public Duration(@JsonProperty("unit") TimeUnit unit) {
         this.unit = unit;
      }

      public TimeUnit getUnit() {
         return this.unit;
      }

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.Duration.startDuration(builder);
         org.apache.arrow.flatbuf.Duration.addUnit(builder, this.unit.getFlatbufID());
         return org.apache.arrow.flatbuf.Duration.endDuration(builder);
      }

      public String toString() {
         return "Duration(" + String.valueOf(this.unit) + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.unit});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Duration)) {
            return false;
         } else {
            Duration that = (Duration)obj;
            return Objects.deepEquals(this.unit, that.unit);
         }
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.Duration;
      }
   }

   public static class ListView extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final ListView INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.ListView.startListView(builder);
         return org.apache.arrow.flatbuf.ListView.endListView(builder);
      }

      public String toString() {
         return "ListView";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof ListView;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.ListView;
         INSTANCE = new ListView();
      }
   }

   public static class LargeListView extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final LargeListView INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.LargeListView.startLargeListView(builder);
         return org.apache.arrow.flatbuf.LargeListView.endLargeListView(builder);
      }

      public String toString() {
         return "LargeListView";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof LargeListView;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.LargeListView;
         INSTANCE = new LargeListView();
      }
   }

   public static class RunEndEncoded extends ComplexType {
      public static final ArrowTypeID TYPE_TYPE;
      public static final RunEndEncoded INSTANCE;

      public ArrowTypeID getTypeID() {
         return TYPE_TYPE;
      }

      public int getType(FlatBufferBuilder builder) {
         org.apache.arrow.flatbuf.RunEndEncoded.startRunEndEncoded(builder);
         return org.apache.arrow.flatbuf.RunEndEncoded.endRunEndEncoded(builder);
      }

      public String toString() {
         return "RunEndEncoded";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[0]);
      }

      public boolean equals(Object obj) {
         return obj instanceof RunEndEncoded;
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }

      static {
         TYPE_TYPE = ArrowType.ArrowTypeID.RunEndEncoded;
         INSTANCE = new RunEndEncoded();
      }
   }

   public abstract static class ExtensionType extends ComplexType {
      public static final String EXTENSION_METADATA_KEY_NAME = "ARROW:extension:name";
      public static final String EXTENSION_METADATA_KEY_METADATA = "ARROW:extension:metadata";

      public abstract ArrowType storageType();

      public abstract String extensionName();

      public abstract boolean extensionEquals(ExtensionType var1);

      public abstract String serialize();

      public abstract ArrowType deserialize(ArrowType var1, String var2);

      public abstract FieldVector getNewVector(String var1, FieldType var2, BufferAllocator var3);

      public ArrowTypeID getTypeID() {
         return this.storageType().getTypeID();
      }

      public int getType(FlatBufferBuilder builder) {
         return this.storageType().getType(builder);
      }

      public String toString() {
         String var10000 = this.extensionName();
         return "ExtensionType(" + var10000 + ", " + this.storageType().toString() + ")";
      }

      public int hashCode() {
         return Arrays.deepHashCode(new Object[]{this.storageType(), this.extensionName()});
      }

      public boolean equals(Object obj) {
         return !(obj instanceof ExtensionType) ? false : this.extensionEquals((ExtensionType)obj);
      }

      public Object accept(ArrowTypeVisitor visitor) {
         return visitor.visit(this);
      }
   }
}

package org.apache.arrow.flatbuf;

public final class Type {
   public static final byte NONE = 0;
   public static final byte Null = 1;
   public static final byte Int = 2;
   public static final byte FloatingPoint = 3;
   public static final byte Binary = 4;
   public static final byte Utf8 = 5;
   public static final byte Bool = 6;
   public static final byte Decimal = 7;
   public static final byte Date = 8;
   public static final byte Time = 9;
   public static final byte Timestamp = 10;
   public static final byte Interval = 11;
   public static final byte List = 12;
   public static final byte Struct_ = 13;
   public static final byte Union = 14;
   public static final byte FixedSizeBinary = 15;
   public static final byte FixedSizeList = 16;
   public static final byte Map = 17;
   public static final byte Duration = 18;
   public static final byte LargeBinary = 19;
   public static final byte LargeUtf8 = 20;
   public static final byte LargeList = 21;
   public static final byte RunEndEncoded = 22;
   public static final byte BinaryView = 23;
   public static final byte Utf8View = 24;
   public static final byte ListView = 25;
   public static final byte LargeListView = 26;
   public static final String[] names = new String[]{"NONE", "Null", "Int", "FloatingPoint", "Binary", "Utf8", "Bool", "Decimal", "Date", "Time", "Timestamp", "Interval", "List", "Struct_", "Union", "FixedSizeBinary", "FixedSizeList", "Map", "Duration", "LargeBinary", "LargeUtf8", "LargeList", "RunEndEncoded", "BinaryView", "Utf8View", "ListView", "LargeListView"};

   private Type() {
   }

   public static String name(int e) {
      return names[e];
   }
}

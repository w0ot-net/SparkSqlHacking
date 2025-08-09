package org.apache.hadoop.hive.serde2.thrift.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class MegaStruct implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("MegaStruct");
   private static final TField MY_BOOL_FIELD_DESC = new TField("my_bool", (byte)2, (short)1);
   private static final TField MY_BYTE_FIELD_DESC = new TField("my_byte", (byte)3, (short)2);
   private static final TField MY_16BIT_INT_FIELD_DESC = new TField("my_16bit_int", (byte)6, (short)3);
   private static final TField MY_32BIT_INT_FIELD_DESC = new TField("my_32bit_int", (byte)8, (short)4);
   private static final TField MY_64BIT_INT_FIELD_DESC = new TField("my_64bit_int", (byte)10, (short)5);
   private static final TField MY_DOUBLE_FIELD_DESC = new TField("my_double", (byte)4, (short)6);
   private static final TField MY_STRING_FIELD_DESC = new TField("my_string", (byte)11, (short)7);
   private static final TField MY_BINARY_FIELD_DESC = new TField("my_binary", (byte)11, (short)8);
   private static final TField MY_STRING_STRING_MAP_FIELD_DESC = new TField("my_string_string_map", (byte)13, (short)9);
   private static final TField MY_STRING_ENUM_MAP_FIELD_DESC = new TField("my_string_enum_map", (byte)13, (short)10);
   private static final TField MY_ENUM_STRING_MAP_FIELD_DESC = new TField("my_enum_string_map", (byte)13, (short)11);
   private static final TField MY_ENUM_STRUCT_MAP_FIELD_DESC = new TField("my_enum_struct_map", (byte)13, (short)12);
   private static final TField MY_ENUM_STRINGLIST_MAP_FIELD_DESC = new TField("my_enum_stringlist_map", (byte)13, (short)13);
   private static final TField MY_ENUM_STRUCTLIST_MAP_FIELD_DESC = new TField("my_enum_structlist_map", (byte)13, (short)14);
   private static final TField MY_STRINGLIST_FIELD_DESC = new TField("my_stringlist", (byte)15, (short)15);
   private static final TField MY_STRUCTLIST_FIELD_DESC = new TField("my_structlist", (byte)15, (short)16);
   private static final TField MY_ENUMLIST_FIELD_DESC = new TField("my_enumlist", (byte)15, (short)17);
   private static final TField MY_STRINGSET_FIELD_DESC = new TField("my_stringset", (byte)14, (short)18);
   private static final TField MY_ENUMSET_FIELD_DESC = new TField("my_enumset", (byte)14, (short)19);
   private static final TField MY_STRUCTSET_FIELD_DESC = new TField("my_structset", (byte)14, (short)20);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new MegaStructStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new MegaStructTupleSchemeFactory();
   private boolean my_bool;
   private byte my_byte;
   private short my_16bit_int;
   private int my_32bit_int;
   private long my_64bit_int;
   private double my_double;
   @Nullable
   private String my_string;
   @Nullable
   private ByteBuffer my_binary;
   @Nullable
   private Map my_string_string_map;
   @Nullable
   private Map my_string_enum_map;
   @Nullable
   private Map my_enum_string_map;
   @Nullable
   private Map my_enum_struct_map;
   @Nullable
   private Map my_enum_stringlist_map;
   @Nullable
   private Map my_enum_structlist_map;
   @Nullable
   private List my_stringlist;
   @Nullable
   private List my_structlist;
   @Nullable
   private List my_enumlist;
   @Nullable
   private Set my_stringset;
   @Nullable
   private Set my_enumset;
   @Nullable
   private Set my_structset;
   private static final int __MY_BOOL_ISSET_ID = 0;
   private static final int __MY_BYTE_ISSET_ID = 1;
   private static final int __MY_16BIT_INT_ISSET_ID = 2;
   private static final int __MY_32BIT_INT_ISSET_ID = 3;
   private static final int __MY_64BIT_INT_ISSET_ID = 4;
   private static final int __MY_DOUBLE_ISSET_ID = 5;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public MegaStruct() {
   }

   public MegaStruct(MegaStruct other) {
      this.__isset_bitfield = other.__isset_bitfield;
      this.my_bool = other.my_bool;
      this.my_byte = other.my_byte;
      this.my_16bit_int = other.my_16bit_int;
      this.my_32bit_int = other.my_32bit_int;
      this.my_64bit_int = other.my_64bit_int;
      this.my_double = other.my_double;
      if (other.isSetMy_string()) {
         this.my_string = other.my_string;
      }

      if (other.isSetMy_binary()) {
         this.my_binary = TBaseHelper.copyBinary(other.my_binary);
      }

      if (other.isSetMy_string_string_map()) {
         Map<String, String> __this__my_string_string_map = new HashMap(other.my_string_string_map);
         this.my_string_string_map = __this__my_string_string_map;
      }

      if (other.isSetMy_string_enum_map()) {
         Map<String, MyEnum> __this__my_string_enum_map = new HashMap(other.my_string_enum_map.size());

         for(Map.Entry other_element : other.my_string_enum_map.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            MyEnum other_element_value = (MyEnum)other_element.getValue();
            __this__my_string_enum_map.put(other_element_key, other_element_value);
         }

         this.my_string_enum_map = __this__my_string_enum_map;
      }

      if (other.isSetMy_enum_string_map()) {
         Map<MyEnum, String> __this__my_enum_string_map = new EnumMap(MyEnum.class);

         for(Map.Entry other_element : other.my_enum_string_map.entrySet()) {
            MyEnum other_element_key = (MyEnum)other_element.getKey();
            String other_element_value = (String)other_element.getValue();
            __this__my_enum_string_map.put(other_element_key, other_element_value);
         }

         this.my_enum_string_map = __this__my_enum_string_map;
      }

      if (other.isSetMy_enum_struct_map()) {
         Map<MyEnum, MiniStruct> __this__my_enum_struct_map = new EnumMap(MyEnum.class);

         for(Map.Entry other_element : other.my_enum_struct_map.entrySet()) {
            MyEnum other_element_key = (MyEnum)other_element.getKey();
            MiniStruct other_element_value = (MiniStruct)other_element.getValue();
            MiniStruct __this__my_enum_struct_map_copy_value = new MiniStruct(other_element_value);
            __this__my_enum_struct_map.put(other_element_key, __this__my_enum_struct_map_copy_value);
         }

         this.my_enum_struct_map = __this__my_enum_struct_map;
      }

      if (other.isSetMy_enum_stringlist_map()) {
         Map<MyEnum, List<String>> __this__my_enum_stringlist_map = new EnumMap(MyEnum.class);

         for(Map.Entry other_element : other.my_enum_stringlist_map.entrySet()) {
            MyEnum other_element_key = (MyEnum)other_element.getKey();
            List<String> other_element_value = (List)other_element.getValue();
            List<String> __this__my_enum_stringlist_map_copy_value = new ArrayList(other_element_value);
            __this__my_enum_stringlist_map.put(other_element_key, __this__my_enum_stringlist_map_copy_value);
         }

         this.my_enum_stringlist_map = __this__my_enum_stringlist_map;
      }

      if (other.isSetMy_enum_structlist_map()) {
         Map<MyEnum, List<MiniStruct>> __this__my_enum_structlist_map = new EnumMap(MyEnum.class);

         for(Map.Entry other_element : other.my_enum_structlist_map.entrySet()) {
            MyEnum other_element_key = (MyEnum)other_element.getKey();
            List<MiniStruct> other_element_value = (List)other_element.getValue();
            List<MiniStruct> __this__my_enum_structlist_map_copy_value = new ArrayList(other_element_value.size());

            for(MiniStruct other_element_value_element : other_element_value) {
               __this__my_enum_structlist_map_copy_value.add(new MiniStruct(other_element_value_element));
            }

            __this__my_enum_structlist_map.put(other_element_key, __this__my_enum_structlist_map_copy_value);
         }

         this.my_enum_structlist_map = __this__my_enum_structlist_map;
      }

      if (other.isSetMy_stringlist()) {
         List<String> __this__my_stringlist = new ArrayList(other.my_stringlist);
         this.my_stringlist = __this__my_stringlist;
      }

      if (other.isSetMy_structlist()) {
         List<MiniStruct> __this__my_structlist = new ArrayList(other.my_structlist.size());

         for(MiniStruct other_element : other.my_structlist) {
            __this__my_structlist.add(new MiniStruct(other_element));
         }

         this.my_structlist = __this__my_structlist;
      }

      if (other.isSetMy_enumlist()) {
         List<MyEnum> __this__my_enumlist = new ArrayList(other.my_enumlist.size());

         for(MyEnum other_element : other.my_enumlist) {
            __this__my_enumlist.add(other_element);
         }

         this.my_enumlist = __this__my_enumlist;
      }

      if (other.isSetMy_stringset()) {
         Set<String> __this__my_stringset = new HashSet(other.my_stringset);
         this.my_stringset = __this__my_stringset;
      }

      if (other.isSetMy_enumset()) {
         Set<MyEnum> __this__my_enumset = EnumSet.noneOf(MyEnum.class);

         for(MyEnum other_element : other.my_enumset) {
            __this__my_enumset.add(other_element);
         }

         this.my_enumset = __this__my_enumset;
      }

      if (other.isSetMy_structset()) {
         Set<MiniStruct> __this__my_structset = new HashSet(other.my_structset.size());

         for(MiniStruct other_element : other.my_structset) {
            __this__my_structset.add(new MiniStruct(other_element));
         }

         this.my_structset = __this__my_structset;
      }

   }

   public MegaStruct deepCopy() {
      return new MegaStruct(this);
   }

   public void clear() {
      this.setMy_boolIsSet(false);
      this.my_bool = false;
      this.setMy_byteIsSet(false);
      this.my_byte = 0;
      this.setMy_16bit_intIsSet(false);
      this.my_16bit_int = 0;
      this.setMy_32bit_intIsSet(false);
      this.my_32bit_int = 0;
      this.setMy_64bit_intIsSet(false);
      this.my_64bit_int = 0L;
      this.setMy_doubleIsSet(false);
      this.my_double = (double)0.0F;
      this.my_string = null;
      this.my_binary = null;
      this.my_string_string_map = null;
      this.my_string_enum_map = null;
      this.my_enum_string_map = null;
      this.my_enum_struct_map = null;
      this.my_enum_stringlist_map = null;
      this.my_enum_structlist_map = null;
      this.my_stringlist = null;
      this.my_structlist = null;
      this.my_enumlist = null;
      this.my_stringset = null;
      this.my_enumset = null;
      this.my_structset = null;
   }

   public boolean isMy_bool() {
      return this.my_bool;
   }

   public void setMy_bool(boolean my_bool) {
      this.my_bool = my_bool;
      this.setMy_boolIsSet(true);
   }

   public void unsetMy_bool() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetMy_bool() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setMy_boolIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public byte getMy_byte() {
      return this.my_byte;
   }

   public void setMy_byte(byte my_byte) {
      this.my_byte = my_byte;
      this.setMy_byteIsSet(true);
   }

   public void unsetMy_byte() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetMy_byte() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setMy_byteIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public short getMy_16bit_int() {
      return this.my_16bit_int;
   }

   public void setMy_16bit_int(short my_16bit_int) {
      this.my_16bit_int = my_16bit_int;
      this.setMy_16bit_intIsSet(true);
   }

   public void unsetMy_16bit_int() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetMy_16bit_int() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setMy_16bit_intIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public int getMy_32bit_int() {
      return this.my_32bit_int;
   }

   public void setMy_32bit_int(int my_32bit_int) {
      this.my_32bit_int = my_32bit_int;
      this.setMy_32bit_intIsSet(true);
   }

   public void unsetMy_32bit_int() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetMy_32bit_int() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setMy_32bit_intIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   public long getMy_64bit_int() {
      return this.my_64bit_int;
   }

   public void setMy_64bit_int(long my_64bit_int) {
      this.my_64bit_int = my_64bit_int;
      this.setMy_64bit_intIsSet(true);
   }

   public void unsetMy_64bit_int() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 4);
   }

   public boolean isSetMy_64bit_int() {
      return EncodingUtils.testBit(this.__isset_bitfield, 4);
   }

   public void setMy_64bit_intIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 4, value);
   }

   public double getMy_double() {
      return this.my_double;
   }

   public void setMy_double(double my_double) {
      this.my_double = my_double;
      this.setMy_doubleIsSet(true);
   }

   public void unsetMy_double() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 5);
   }

   public boolean isSetMy_double() {
      return EncodingUtils.testBit(this.__isset_bitfield, 5);
   }

   public void setMy_doubleIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 5, value);
   }

   @Nullable
   public String getMy_string() {
      return this.my_string;
   }

   public void setMy_string(@Nullable String my_string) {
      this.my_string = my_string;
   }

   public void unsetMy_string() {
      this.my_string = null;
   }

   public boolean isSetMy_string() {
      return this.my_string != null;
   }

   public void setMy_stringIsSet(boolean value) {
      if (!value) {
         this.my_string = null;
      }

   }

   public byte[] getMy_binary() {
      this.setMy_binary(TBaseHelper.rightSize(this.my_binary));
      return this.my_binary == null ? null : this.my_binary.array();
   }

   public ByteBuffer bufferForMy_binary() {
      return TBaseHelper.copyBinary(this.my_binary);
   }

   public void setMy_binary(byte[] my_binary) {
      this.my_binary = my_binary == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)my_binary).clone());
   }

   public void setMy_binary(@Nullable ByteBuffer my_binary) {
      this.my_binary = TBaseHelper.copyBinary(my_binary);
   }

   public void unsetMy_binary() {
      this.my_binary = null;
   }

   public boolean isSetMy_binary() {
      return this.my_binary != null;
   }

   public void setMy_binaryIsSet(boolean value) {
      if (!value) {
         this.my_binary = null;
      }

   }

   public int getMy_string_string_mapSize() {
      return this.my_string_string_map == null ? 0 : this.my_string_string_map.size();
   }

   public void putToMy_string_string_map(String key, String val) {
      if (this.my_string_string_map == null) {
         this.my_string_string_map = new HashMap();
      }

      this.my_string_string_map.put(key, val);
   }

   @Nullable
   public Map getMy_string_string_map() {
      return this.my_string_string_map;
   }

   public void setMy_string_string_map(@Nullable Map my_string_string_map) {
      this.my_string_string_map = my_string_string_map;
   }

   public void unsetMy_string_string_map() {
      this.my_string_string_map = null;
   }

   public boolean isSetMy_string_string_map() {
      return this.my_string_string_map != null;
   }

   public void setMy_string_string_mapIsSet(boolean value) {
      if (!value) {
         this.my_string_string_map = null;
      }

   }

   public int getMy_string_enum_mapSize() {
      return this.my_string_enum_map == null ? 0 : this.my_string_enum_map.size();
   }

   public void putToMy_string_enum_map(String key, MyEnum val) {
      if (this.my_string_enum_map == null) {
         this.my_string_enum_map = new HashMap();
      }

      this.my_string_enum_map.put(key, val);
   }

   @Nullable
   public Map getMy_string_enum_map() {
      return this.my_string_enum_map;
   }

   public void setMy_string_enum_map(@Nullable Map my_string_enum_map) {
      this.my_string_enum_map = my_string_enum_map;
   }

   public void unsetMy_string_enum_map() {
      this.my_string_enum_map = null;
   }

   public boolean isSetMy_string_enum_map() {
      return this.my_string_enum_map != null;
   }

   public void setMy_string_enum_mapIsSet(boolean value) {
      if (!value) {
         this.my_string_enum_map = null;
      }

   }

   public int getMy_enum_string_mapSize() {
      return this.my_enum_string_map == null ? 0 : this.my_enum_string_map.size();
   }

   public void putToMy_enum_string_map(MyEnum key, String val) {
      if (this.my_enum_string_map == null) {
         this.my_enum_string_map = new EnumMap(MyEnum.class);
      }

      this.my_enum_string_map.put(key, val);
   }

   @Nullable
   public Map getMy_enum_string_map() {
      return this.my_enum_string_map;
   }

   public void setMy_enum_string_map(@Nullable Map my_enum_string_map) {
      this.my_enum_string_map = my_enum_string_map;
   }

   public void unsetMy_enum_string_map() {
      this.my_enum_string_map = null;
   }

   public boolean isSetMy_enum_string_map() {
      return this.my_enum_string_map != null;
   }

   public void setMy_enum_string_mapIsSet(boolean value) {
      if (!value) {
         this.my_enum_string_map = null;
      }

   }

   public int getMy_enum_struct_mapSize() {
      return this.my_enum_struct_map == null ? 0 : this.my_enum_struct_map.size();
   }

   public void putToMy_enum_struct_map(MyEnum key, MiniStruct val) {
      if (this.my_enum_struct_map == null) {
         this.my_enum_struct_map = new EnumMap(MyEnum.class);
      }

      this.my_enum_struct_map.put(key, val);
   }

   @Nullable
   public Map getMy_enum_struct_map() {
      return this.my_enum_struct_map;
   }

   public void setMy_enum_struct_map(@Nullable Map my_enum_struct_map) {
      this.my_enum_struct_map = my_enum_struct_map;
   }

   public void unsetMy_enum_struct_map() {
      this.my_enum_struct_map = null;
   }

   public boolean isSetMy_enum_struct_map() {
      return this.my_enum_struct_map != null;
   }

   public void setMy_enum_struct_mapIsSet(boolean value) {
      if (!value) {
         this.my_enum_struct_map = null;
      }

   }

   public int getMy_enum_stringlist_mapSize() {
      return this.my_enum_stringlist_map == null ? 0 : this.my_enum_stringlist_map.size();
   }

   public void putToMy_enum_stringlist_map(MyEnum key, List val) {
      if (this.my_enum_stringlist_map == null) {
         this.my_enum_stringlist_map = new EnumMap(MyEnum.class);
      }

      this.my_enum_stringlist_map.put(key, val);
   }

   @Nullable
   public Map getMy_enum_stringlist_map() {
      return this.my_enum_stringlist_map;
   }

   public void setMy_enum_stringlist_map(@Nullable Map my_enum_stringlist_map) {
      this.my_enum_stringlist_map = my_enum_stringlist_map;
   }

   public void unsetMy_enum_stringlist_map() {
      this.my_enum_stringlist_map = null;
   }

   public boolean isSetMy_enum_stringlist_map() {
      return this.my_enum_stringlist_map != null;
   }

   public void setMy_enum_stringlist_mapIsSet(boolean value) {
      if (!value) {
         this.my_enum_stringlist_map = null;
      }

   }

   public int getMy_enum_structlist_mapSize() {
      return this.my_enum_structlist_map == null ? 0 : this.my_enum_structlist_map.size();
   }

   public void putToMy_enum_structlist_map(MyEnum key, List val) {
      if (this.my_enum_structlist_map == null) {
         this.my_enum_structlist_map = new EnumMap(MyEnum.class);
      }

      this.my_enum_structlist_map.put(key, val);
   }

   @Nullable
   public Map getMy_enum_structlist_map() {
      return this.my_enum_structlist_map;
   }

   public void setMy_enum_structlist_map(@Nullable Map my_enum_structlist_map) {
      this.my_enum_structlist_map = my_enum_structlist_map;
   }

   public void unsetMy_enum_structlist_map() {
      this.my_enum_structlist_map = null;
   }

   public boolean isSetMy_enum_structlist_map() {
      return this.my_enum_structlist_map != null;
   }

   public void setMy_enum_structlist_mapIsSet(boolean value) {
      if (!value) {
         this.my_enum_structlist_map = null;
      }

   }

   public int getMy_stringlistSize() {
      return this.my_stringlist == null ? 0 : this.my_stringlist.size();
   }

   @Nullable
   public Iterator getMy_stringlistIterator() {
      return this.my_stringlist == null ? null : this.my_stringlist.iterator();
   }

   public void addToMy_stringlist(String elem) {
      if (this.my_stringlist == null) {
         this.my_stringlist = new ArrayList();
      }

      this.my_stringlist.add(elem);
   }

   @Nullable
   public List getMy_stringlist() {
      return this.my_stringlist;
   }

   public void setMy_stringlist(@Nullable List my_stringlist) {
      this.my_stringlist = my_stringlist;
   }

   public void unsetMy_stringlist() {
      this.my_stringlist = null;
   }

   public boolean isSetMy_stringlist() {
      return this.my_stringlist != null;
   }

   public void setMy_stringlistIsSet(boolean value) {
      if (!value) {
         this.my_stringlist = null;
      }

   }

   public int getMy_structlistSize() {
      return this.my_structlist == null ? 0 : this.my_structlist.size();
   }

   @Nullable
   public Iterator getMy_structlistIterator() {
      return this.my_structlist == null ? null : this.my_structlist.iterator();
   }

   public void addToMy_structlist(MiniStruct elem) {
      if (this.my_structlist == null) {
         this.my_structlist = new ArrayList();
      }

      this.my_structlist.add(elem);
   }

   @Nullable
   public List getMy_structlist() {
      return this.my_structlist;
   }

   public void setMy_structlist(@Nullable List my_structlist) {
      this.my_structlist = my_structlist;
   }

   public void unsetMy_structlist() {
      this.my_structlist = null;
   }

   public boolean isSetMy_structlist() {
      return this.my_structlist != null;
   }

   public void setMy_structlistIsSet(boolean value) {
      if (!value) {
         this.my_structlist = null;
      }

   }

   public int getMy_enumlistSize() {
      return this.my_enumlist == null ? 0 : this.my_enumlist.size();
   }

   @Nullable
   public Iterator getMy_enumlistIterator() {
      return this.my_enumlist == null ? null : this.my_enumlist.iterator();
   }

   public void addToMy_enumlist(MyEnum elem) {
      if (this.my_enumlist == null) {
         this.my_enumlist = new ArrayList();
      }

      this.my_enumlist.add(elem);
   }

   @Nullable
   public List getMy_enumlist() {
      return this.my_enumlist;
   }

   public void setMy_enumlist(@Nullable List my_enumlist) {
      this.my_enumlist = my_enumlist;
   }

   public void unsetMy_enumlist() {
      this.my_enumlist = null;
   }

   public boolean isSetMy_enumlist() {
      return this.my_enumlist != null;
   }

   public void setMy_enumlistIsSet(boolean value) {
      if (!value) {
         this.my_enumlist = null;
      }

   }

   public int getMy_stringsetSize() {
      return this.my_stringset == null ? 0 : this.my_stringset.size();
   }

   @Nullable
   public Iterator getMy_stringsetIterator() {
      return this.my_stringset == null ? null : this.my_stringset.iterator();
   }

   public void addToMy_stringset(String elem) {
      if (this.my_stringset == null) {
         this.my_stringset = new HashSet();
      }

      this.my_stringset.add(elem);
   }

   @Nullable
   public Set getMy_stringset() {
      return this.my_stringset;
   }

   public void setMy_stringset(@Nullable Set my_stringset) {
      this.my_stringset = my_stringset;
   }

   public void unsetMy_stringset() {
      this.my_stringset = null;
   }

   public boolean isSetMy_stringset() {
      return this.my_stringset != null;
   }

   public void setMy_stringsetIsSet(boolean value) {
      if (!value) {
         this.my_stringset = null;
      }

   }

   public int getMy_enumsetSize() {
      return this.my_enumset == null ? 0 : this.my_enumset.size();
   }

   @Nullable
   public Iterator getMy_enumsetIterator() {
      return this.my_enumset == null ? null : this.my_enumset.iterator();
   }

   public void addToMy_enumset(MyEnum elem) {
      if (this.my_enumset == null) {
         this.my_enumset = EnumSet.noneOf(MyEnum.class);
      }

      this.my_enumset.add(elem);
   }

   @Nullable
   public Set getMy_enumset() {
      return this.my_enumset;
   }

   public void setMy_enumset(@Nullable Set my_enumset) {
      this.my_enumset = my_enumset;
   }

   public void unsetMy_enumset() {
      this.my_enumset = null;
   }

   public boolean isSetMy_enumset() {
      return this.my_enumset != null;
   }

   public void setMy_enumsetIsSet(boolean value) {
      if (!value) {
         this.my_enumset = null;
      }

   }

   public int getMy_structsetSize() {
      return this.my_structset == null ? 0 : this.my_structset.size();
   }

   @Nullable
   public Iterator getMy_structsetIterator() {
      return this.my_structset == null ? null : this.my_structset.iterator();
   }

   public void addToMy_structset(MiniStruct elem) {
      if (this.my_structset == null) {
         this.my_structset = new HashSet();
      }

      this.my_structset.add(elem);
   }

   @Nullable
   public Set getMy_structset() {
      return this.my_structset;
   }

   public void setMy_structset(@Nullable Set my_structset) {
      this.my_structset = my_structset;
   }

   public void unsetMy_structset() {
      this.my_structset = null;
   }

   public boolean isSetMy_structset() {
      return this.my_structset != null;
   }

   public void setMy_structsetIsSet(boolean value) {
      if (!value) {
         this.my_structset = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case MY_BOOL:
            if (value == null) {
               this.unsetMy_bool();
            } else {
               this.setMy_bool((Boolean)value);
            }
            break;
         case MY_BYTE:
            if (value == null) {
               this.unsetMy_byte();
            } else {
               this.setMy_byte((Byte)value);
            }
            break;
         case MY_16BIT_INT:
            if (value == null) {
               this.unsetMy_16bit_int();
            } else {
               this.setMy_16bit_int((Short)value);
            }
            break;
         case MY_32BIT_INT:
            if (value == null) {
               this.unsetMy_32bit_int();
            } else {
               this.setMy_32bit_int((Integer)value);
            }
            break;
         case MY_64BIT_INT:
            if (value == null) {
               this.unsetMy_64bit_int();
            } else {
               this.setMy_64bit_int((Long)value);
            }
            break;
         case MY_DOUBLE:
            if (value == null) {
               this.unsetMy_double();
            } else {
               this.setMy_double((Double)value);
            }
            break;
         case MY_STRING:
            if (value == null) {
               this.unsetMy_string();
            } else {
               this.setMy_string((String)value);
            }
            break;
         case MY_BINARY:
            if (value == null) {
               this.unsetMy_binary();
            } else if (value instanceof byte[]) {
               this.setMy_binary((byte[])value);
            } else {
               this.setMy_binary((ByteBuffer)value);
            }
            break;
         case MY_STRING_STRING_MAP:
            if (value == null) {
               this.unsetMy_string_string_map();
            } else {
               this.setMy_string_string_map((Map)value);
            }
            break;
         case MY_STRING_ENUM_MAP:
            if (value == null) {
               this.unsetMy_string_enum_map();
            } else {
               this.setMy_string_enum_map((Map)value);
            }
            break;
         case MY_ENUM_STRING_MAP:
            if (value == null) {
               this.unsetMy_enum_string_map();
            } else {
               this.setMy_enum_string_map((Map)value);
            }
            break;
         case MY_ENUM_STRUCT_MAP:
            if (value == null) {
               this.unsetMy_enum_struct_map();
            } else {
               this.setMy_enum_struct_map((Map)value);
            }
            break;
         case MY_ENUM_STRINGLIST_MAP:
            if (value == null) {
               this.unsetMy_enum_stringlist_map();
            } else {
               this.setMy_enum_stringlist_map((Map)value);
            }
            break;
         case MY_ENUM_STRUCTLIST_MAP:
            if (value == null) {
               this.unsetMy_enum_structlist_map();
            } else {
               this.setMy_enum_structlist_map((Map)value);
            }
            break;
         case MY_STRINGLIST:
            if (value == null) {
               this.unsetMy_stringlist();
            } else {
               this.setMy_stringlist((List)value);
            }
            break;
         case MY_STRUCTLIST:
            if (value == null) {
               this.unsetMy_structlist();
            } else {
               this.setMy_structlist((List)value);
            }
            break;
         case MY_ENUMLIST:
            if (value == null) {
               this.unsetMy_enumlist();
            } else {
               this.setMy_enumlist((List)value);
            }
            break;
         case MY_STRINGSET:
            if (value == null) {
               this.unsetMy_stringset();
            } else {
               this.setMy_stringset((Set)value);
            }
            break;
         case MY_ENUMSET:
            if (value == null) {
               this.unsetMy_enumset();
            } else {
               this.setMy_enumset((Set)value);
            }
            break;
         case MY_STRUCTSET:
            if (value == null) {
               this.unsetMy_structset();
            } else {
               this.setMy_structset((Set)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case MY_BOOL:
            return this.isMy_bool();
         case MY_BYTE:
            return this.getMy_byte();
         case MY_16BIT_INT:
            return this.getMy_16bit_int();
         case MY_32BIT_INT:
            return this.getMy_32bit_int();
         case MY_64BIT_INT:
            return this.getMy_64bit_int();
         case MY_DOUBLE:
            return this.getMy_double();
         case MY_STRING:
            return this.getMy_string();
         case MY_BINARY:
            return this.getMy_binary();
         case MY_STRING_STRING_MAP:
            return this.getMy_string_string_map();
         case MY_STRING_ENUM_MAP:
            return this.getMy_string_enum_map();
         case MY_ENUM_STRING_MAP:
            return this.getMy_enum_string_map();
         case MY_ENUM_STRUCT_MAP:
            return this.getMy_enum_struct_map();
         case MY_ENUM_STRINGLIST_MAP:
            return this.getMy_enum_stringlist_map();
         case MY_ENUM_STRUCTLIST_MAP:
            return this.getMy_enum_structlist_map();
         case MY_STRINGLIST:
            return this.getMy_stringlist();
         case MY_STRUCTLIST:
            return this.getMy_structlist();
         case MY_ENUMLIST:
            return this.getMy_enumlist();
         case MY_STRINGSET:
            return this.getMy_stringset();
         case MY_ENUMSET:
            return this.getMy_enumset();
         case MY_STRUCTSET:
            return this.getMy_structset();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case MY_BOOL:
               return this.isSetMy_bool();
            case MY_BYTE:
               return this.isSetMy_byte();
            case MY_16BIT_INT:
               return this.isSetMy_16bit_int();
            case MY_32BIT_INT:
               return this.isSetMy_32bit_int();
            case MY_64BIT_INT:
               return this.isSetMy_64bit_int();
            case MY_DOUBLE:
               return this.isSetMy_double();
            case MY_STRING:
               return this.isSetMy_string();
            case MY_BINARY:
               return this.isSetMy_binary();
            case MY_STRING_STRING_MAP:
               return this.isSetMy_string_string_map();
            case MY_STRING_ENUM_MAP:
               return this.isSetMy_string_enum_map();
            case MY_ENUM_STRING_MAP:
               return this.isSetMy_enum_string_map();
            case MY_ENUM_STRUCT_MAP:
               return this.isSetMy_enum_struct_map();
            case MY_ENUM_STRINGLIST_MAP:
               return this.isSetMy_enum_stringlist_map();
            case MY_ENUM_STRUCTLIST_MAP:
               return this.isSetMy_enum_structlist_map();
            case MY_STRINGLIST:
               return this.isSetMy_stringlist();
            case MY_STRUCTLIST:
               return this.isSetMy_structlist();
            case MY_ENUMLIST:
               return this.isSetMy_enumlist();
            case MY_STRINGSET:
               return this.isSetMy_stringset();
            case MY_ENUMSET:
               return this.isSetMy_enumset();
            case MY_STRUCTSET:
               return this.isSetMy_structset();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof MegaStruct ? this.equals((MegaStruct)that) : false;
   }

   public boolean equals(MegaStruct that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_my_bool = this.isSetMy_bool();
         boolean that_present_my_bool = that.isSetMy_bool();
         if (this_present_my_bool || that_present_my_bool) {
            if (!this_present_my_bool || !that_present_my_bool) {
               return false;
            }

            if (this.my_bool != that.my_bool) {
               return false;
            }
         }

         boolean this_present_my_byte = this.isSetMy_byte();
         boolean that_present_my_byte = that.isSetMy_byte();
         if (this_present_my_byte || that_present_my_byte) {
            if (!this_present_my_byte || !that_present_my_byte) {
               return false;
            }

            if (this.my_byte != that.my_byte) {
               return false;
            }
         }

         boolean this_present_my_16bit_int = this.isSetMy_16bit_int();
         boolean that_present_my_16bit_int = that.isSetMy_16bit_int();
         if (this_present_my_16bit_int || that_present_my_16bit_int) {
            if (!this_present_my_16bit_int || !that_present_my_16bit_int) {
               return false;
            }

            if (this.my_16bit_int != that.my_16bit_int) {
               return false;
            }
         }

         boolean this_present_my_32bit_int = this.isSetMy_32bit_int();
         boolean that_present_my_32bit_int = that.isSetMy_32bit_int();
         if (this_present_my_32bit_int || that_present_my_32bit_int) {
            if (!this_present_my_32bit_int || !that_present_my_32bit_int) {
               return false;
            }

            if (this.my_32bit_int != that.my_32bit_int) {
               return false;
            }
         }

         boolean this_present_my_64bit_int = this.isSetMy_64bit_int();
         boolean that_present_my_64bit_int = that.isSetMy_64bit_int();
         if (this_present_my_64bit_int || that_present_my_64bit_int) {
            if (!this_present_my_64bit_int || !that_present_my_64bit_int) {
               return false;
            }

            if (this.my_64bit_int != that.my_64bit_int) {
               return false;
            }
         }

         boolean this_present_my_double = this.isSetMy_double();
         boolean that_present_my_double = that.isSetMy_double();
         if (this_present_my_double || that_present_my_double) {
            if (!this_present_my_double || !that_present_my_double) {
               return false;
            }

            if (this.my_double != that.my_double) {
               return false;
            }
         }

         boolean this_present_my_string = this.isSetMy_string();
         boolean that_present_my_string = that.isSetMy_string();
         if (this_present_my_string || that_present_my_string) {
            if (!this_present_my_string || !that_present_my_string) {
               return false;
            }

            if (!this.my_string.equals(that.my_string)) {
               return false;
            }
         }

         boolean this_present_my_binary = this.isSetMy_binary();
         boolean that_present_my_binary = that.isSetMy_binary();
         if (this_present_my_binary || that_present_my_binary) {
            if (!this_present_my_binary || !that_present_my_binary) {
               return false;
            }

            if (!this.my_binary.equals(that.my_binary)) {
               return false;
            }
         }

         boolean this_present_my_string_string_map = this.isSetMy_string_string_map();
         boolean that_present_my_string_string_map = that.isSetMy_string_string_map();
         if (this_present_my_string_string_map || that_present_my_string_string_map) {
            if (!this_present_my_string_string_map || !that_present_my_string_string_map) {
               return false;
            }

            if (!this.my_string_string_map.equals(that.my_string_string_map)) {
               return false;
            }
         }

         boolean this_present_my_string_enum_map = this.isSetMy_string_enum_map();
         boolean that_present_my_string_enum_map = that.isSetMy_string_enum_map();
         if (this_present_my_string_enum_map || that_present_my_string_enum_map) {
            if (!this_present_my_string_enum_map || !that_present_my_string_enum_map) {
               return false;
            }

            if (!this.my_string_enum_map.equals(that.my_string_enum_map)) {
               return false;
            }
         }

         boolean this_present_my_enum_string_map = this.isSetMy_enum_string_map();
         boolean that_present_my_enum_string_map = that.isSetMy_enum_string_map();
         if (this_present_my_enum_string_map || that_present_my_enum_string_map) {
            if (!this_present_my_enum_string_map || !that_present_my_enum_string_map) {
               return false;
            }

            if (!this.my_enum_string_map.equals(that.my_enum_string_map)) {
               return false;
            }
         }

         boolean this_present_my_enum_struct_map = this.isSetMy_enum_struct_map();
         boolean that_present_my_enum_struct_map = that.isSetMy_enum_struct_map();
         if (this_present_my_enum_struct_map || that_present_my_enum_struct_map) {
            if (!this_present_my_enum_struct_map || !that_present_my_enum_struct_map) {
               return false;
            }

            if (!this.my_enum_struct_map.equals(that.my_enum_struct_map)) {
               return false;
            }
         }

         boolean this_present_my_enum_stringlist_map = this.isSetMy_enum_stringlist_map();
         boolean that_present_my_enum_stringlist_map = that.isSetMy_enum_stringlist_map();
         if (this_present_my_enum_stringlist_map || that_present_my_enum_stringlist_map) {
            if (!this_present_my_enum_stringlist_map || !that_present_my_enum_stringlist_map) {
               return false;
            }

            if (!this.my_enum_stringlist_map.equals(that.my_enum_stringlist_map)) {
               return false;
            }
         }

         boolean this_present_my_enum_structlist_map = this.isSetMy_enum_structlist_map();
         boolean that_present_my_enum_structlist_map = that.isSetMy_enum_structlist_map();
         if (this_present_my_enum_structlist_map || that_present_my_enum_structlist_map) {
            if (!this_present_my_enum_structlist_map || !that_present_my_enum_structlist_map) {
               return false;
            }

            if (!this.my_enum_structlist_map.equals(that.my_enum_structlist_map)) {
               return false;
            }
         }

         boolean this_present_my_stringlist = this.isSetMy_stringlist();
         boolean that_present_my_stringlist = that.isSetMy_stringlist();
         if (this_present_my_stringlist || that_present_my_stringlist) {
            if (!this_present_my_stringlist || !that_present_my_stringlist) {
               return false;
            }

            if (!this.my_stringlist.equals(that.my_stringlist)) {
               return false;
            }
         }

         boolean this_present_my_structlist = this.isSetMy_structlist();
         boolean that_present_my_structlist = that.isSetMy_structlist();
         if (this_present_my_structlist || that_present_my_structlist) {
            if (!this_present_my_structlist || !that_present_my_structlist) {
               return false;
            }

            if (!this.my_structlist.equals(that.my_structlist)) {
               return false;
            }
         }

         boolean this_present_my_enumlist = this.isSetMy_enumlist();
         boolean that_present_my_enumlist = that.isSetMy_enumlist();
         if (this_present_my_enumlist || that_present_my_enumlist) {
            if (!this_present_my_enumlist || !that_present_my_enumlist) {
               return false;
            }

            if (!this.my_enumlist.equals(that.my_enumlist)) {
               return false;
            }
         }

         boolean this_present_my_stringset = this.isSetMy_stringset();
         boolean that_present_my_stringset = that.isSetMy_stringset();
         if (this_present_my_stringset || that_present_my_stringset) {
            if (!this_present_my_stringset || !that_present_my_stringset) {
               return false;
            }

            if (!this.my_stringset.equals(that.my_stringset)) {
               return false;
            }
         }

         boolean this_present_my_enumset = this.isSetMy_enumset();
         boolean that_present_my_enumset = that.isSetMy_enumset();
         if (this_present_my_enumset || that_present_my_enumset) {
            if (!this_present_my_enumset || !that_present_my_enumset) {
               return false;
            }

            if (!this.my_enumset.equals(that.my_enumset)) {
               return false;
            }
         }

         boolean this_present_my_structset = this.isSetMy_structset();
         boolean that_present_my_structset = that.isSetMy_structset();
         if (this_present_my_structset || that_present_my_structset) {
            if (!this_present_my_structset || !that_present_my_structset) {
               return false;
            }

            if (!this.my_structset.equals(that.my_structset)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetMy_bool() ? 131071 : 524287);
      if (this.isSetMy_bool()) {
         hashCode = hashCode * 8191 + (this.my_bool ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetMy_byte() ? 131071 : 524287);
      if (this.isSetMy_byte()) {
         hashCode = hashCode * 8191 + this.my_byte;
      }

      hashCode = hashCode * 8191 + (this.isSetMy_16bit_int() ? 131071 : 524287);
      if (this.isSetMy_16bit_int()) {
         hashCode = hashCode * 8191 + this.my_16bit_int;
      }

      hashCode = hashCode * 8191 + (this.isSetMy_32bit_int() ? 131071 : 524287);
      if (this.isSetMy_32bit_int()) {
         hashCode = hashCode * 8191 + this.my_32bit_int;
      }

      hashCode = hashCode * 8191 + (this.isSetMy_64bit_int() ? 131071 : 524287);
      if (this.isSetMy_64bit_int()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.my_64bit_int);
      }

      hashCode = hashCode * 8191 + (this.isSetMy_double() ? 131071 : 524287);
      if (this.isSetMy_double()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.my_double);
      }

      hashCode = hashCode * 8191 + (this.isSetMy_string() ? 131071 : 524287);
      if (this.isSetMy_string()) {
         hashCode = hashCode * 8191 + this.my_string.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_binary() ? 131071 : 524287);
      if (this.isSetMy_binary()) {
         hashCode = hashCode * 8191 + this.my_binary.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_string_string_map() ? 131071 : 524287);
      if (this.isSetMy_string_string_map()) {
         hashCode = hashCode * 8191 + this.my_string_string_map.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_string_enum_map() ? 131071 : 524287);
      if (this.isSetMy_string_enum_map()) {
         hashCode = hashCode * 8191 + this.my_string_enum_map.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enum_string_map() ? 131071 : 524287);
      if (this.isSetMy_enum_string_map()) {
         hashCode = hashCode * 8191 + this.my_enum_string_map.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enum_struct_map() ? 131071 : 524287);
      if (this.isSetMy_enum_struct_map()) {
         hashCode = hashCode * 8191 + this.my_enum_struct_map.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enum_stringlist_map() ? 131071 : 524287);
      if (this.isSetMy_enum_stringlist_map()) {
         hashCode = hashCode * 8191 + this.my_enum_stringlist_map.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enum_structlist_map() ? 131071 : 524287);
      if (this.isSetMy_enum_structlist_map()) {
         hashCode = hashCode * 8191 + this.my_enum_structlist_map.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_stringlist() ? 131071 : 524287);
      if (this.isSetMy_stringlist()) {
         hashCode = hashCode * 8191 + this.my_stringlist.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_structlist() ? 131071 : 524287);
      if (this.isSetMy_structlist()) {
         hashCode = hashCode * 8191 + this.my_structlist.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enumlist() ? 131071 : 524287);
      if (this.isSetMy_enumlist()) {
         hashCode = hashCode * 8191 + this.my_enumlist.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_stringset() ? 131071 : 524287);
      if (this.isSetMy_stringset()) {
         hashCode = hashCode * 8191 + this.my_stringset.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enumset() ? 131071 : 524287);
      if (this.isSetMy_enumset()) {
         hashCode = hashCode * 8191 + this.my_enumset.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_structset() ? 131071 : 524287);
      if (this.isSetMy_structset()) {
         hashCode = hashCode * 8191 + this.my_structset.hashCode();
      }

      return hashCode;
   }

   public int compareTo(MegaStruct other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMy_bool(), other.isSetMy_bool());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMy_bool()) {
               lastComparison = TBaseHelper.compareTo(this.my_bool, other.my_bool);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMy_byte(), other.isSetMy_byte());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMy_byte()) {
                  lastComparison = TBaseHelper.compareTo(this.my_byte, other.my_byte);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetMy_16bit_int(), other.isSetMy_16bit_int());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetMy_16bit_int()) {
                     lastComparison = TBaseHelper.compareTo(this.my_16bit_int, other.my_16bit_int);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetMy_32bit_int(), other.isSetMy_32bit_int());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetMy_32bit_int()) {
                        lastComparison = TBaseHelper.compareTo(this.my_32bit_int, other.my_32bit_int);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetMy_64bit_int(), other.isSetMy_64bit_int());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetMy_64bit_int()) {
                           lastComparison = TBaseHelper.compareTo(this.my_64bit_int, other.my_64bit_int);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetMy_double(), other.isSetMy_double());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetMy_double()) {
                              lastComparison = TBaseHelper.compareTo(this.my_double, other.my_double);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetMy_string(), other.isSetMy_string());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetMy_string()) {
                                 lastComparison = TBaseHelper.compareTo(this.my_string, other.my_string);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetMy_binary(), other.isSetMy_binary());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetMy_binary()) {
                                    lastComparison = TBaseHelper.compareTo(this.my_binary, other.my_binary);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetMy_string_string_map(), other.isSetMy_string_string_map());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetMy_string_string_map()) {
                                       lastComparison = TBaseHelper.compareTo(this.my_string_string_map, other.my_string_string_map);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetMy_string_enum_map(), other.isSetMy_string_enum_map());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetMy_string_enum_map()) {
                                          lastComparison = TBaseHelper.compareTo(this.my_string_enum_map, other.my_string_enum_map);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetMy_enum_string_map(), other.isSetMy_enum_string_map());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetMy_enum_string_map()) {
                                             lastComparison = TBaseHelper.compareTo(this.my_enum_string_map, other.my_enum_string_map);
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             }
                                          }

                                          lastComparison = Boolean.compare(this.isSetMy_enum_struct_map(), other.isSetMy_enum_struct_map());
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          } else {
                                             if (this.isSetMy_enum_struct_map()) {
                                                lastComparison = TBaseHelper.compareTo(this.my_enum_struct_map, other.my_enum_struct_map);
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                }
                                             }

                                             lastComparison = Boolean.compare(this.isSetMy_enum_stringlist_map(), other.isSetMy_enum_stringlist_map());
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             } else {
                                                if (this.isSetMy_enum_stringlist_map()) {
                                                   lastComparison = TBaseHelper.compareTo(this.my_enum_stringlist_map, other.my_enum_stringlist_map);
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   }
                                                }

                                                lastComparison = Boolean.compare(this.isSetMy_enum_structlist_map(), other.isSetMy_enum_structlist_map());
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                } else {
                                                   if (this.isSetMy_enum_structlist_map()) {
                                                      lastComparison = TBaseHelper.compareTo(this.my_enum_structlist_map, other.my_enum_structlist_map);
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      }
                                                   }

                                                   lastComparison = Boolean.compare(this.isSetMy_stringlist(), other.isSetMy_stringlist());
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   } else {
                                                      if (this.isSetMy_stringlist()) {
                                                         lastComparison = TBaseHelper.compareTo(this.my_stringlist, other.my_stringlist);
                                                         if (lastComparison != 0) {
                                                            return lastComparison;
                                                         }
                                                      }

                                                      lastComparison = Boolean.compare(this.isSetMy_structlist(), other.isSetMy_structlist());
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      } else {
                                                         if (this.isSetMy_structlist()) {
                                                            lastComparison = TBaseHelper.compareTo(this.my_structlist, other.my_structlist);
                                                            if (lastComparison != 0) {
                                                               return lastComparison;
                                                            }
                                                         }

                                                         lastComparison = Boolean.compare(this.isSetMy_enumlist(), other.isSetMy_enumlist());
                                                         if (lastComparison != 0) {
                                                            return lastComparison;
                                                         } else {
                                                            if (this.isSetMy_enumlist()) {
                                                               lastComparison = TBaseHelper.compareTo(this.my_enumlist, other.my_enumlist);
                                                               if (lastComparison != 0) {
                                                                  return lastComparison;
                                                               }
                                                            }

                                                            lastComparison = Boolean.compare(this.isSetMy_stringset(), other.isSetMy_stringset());
                                                            if (lastComparison != 0) {
                                                               return lastComparison;
                                                            } else {
                                                               if (this.isSetMy_stringset()) {
                                                                  lastComparison = TBaseHelper.compareTo(this.my_stringset, other.my_stringset);
                                                                  if (lastComparison != 0) {
                                                                     return lastComparison;
                                                                  }
                                                               }

                                                               lastComparison = Boolean.compare(this.isSetMy_enumset(), other.isSetMy_enumset());
                                                               if (lastComparison != 0) {
                                                                  return lastComparison;
                                                               } else {
                                                                  if (this.isSetMy_enumset()) {
                                                                     lastComparison = TBaseHelper.compareTo(this.my_enumset, other.my_enumset);
                                                                     if (lastComparison != 0) {
                                                                        return lastComparison;
                                                                     }
                                                                  }

                                                                  lastComparison = Boolean.compare(this.isSetMy_structset(), other.isSetMy_structset());
                                                                  if (lastComparison != 0) {
                                                                     return lastComparison;
                                                                  } else {
                                                                     if (this.isSetMy_structset()) {
                                                                        lastComparison = TBaseHelper.compareTo(this.my_structset, other.my_structset);
                                                                        if (lastComparison != 0) {
                                                                           return lastComparison;
                                                                        }
                                                                     }

                                                                     return 0;
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return MegaStruct._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("MegaStruct(");
      boolean first = true;
      if (this.isSetMy_bool()) {
         sb.append("my_bool:");
         sb.append(this.my_bool);
         first = false;
      }

      if (this.isSetMy_byte()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_byte:");
         sb.append(this.my_byte);
         first = false;
      }

      if (this.isSetMy_16bit_int()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_16bit_int:");
         sb.append(this.my_16bit_int);
         first = false;
      }

      if (this.isSetMy_32bit_int()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_32bit_int:");
         sb.append(this.my_32bit_int);
         first = false;
      }

      if (this.isSetMy_64bit_int()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_64bit_int:");
         sb.append(this.my_64bit_int);
         first = false;
      }

      if (this.isSetMy_double()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_double:");
         sb.append(this.my_double);
         first = false;
      }

      if (this.isSetMy_string()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_string:");
         if (this.my_string == null) {
            sb.append("null");
         } else {
            sb.append(this.my_string);
         }

         first = false;
      }

      if (this.isSetMy_binary()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_binary:");
         if (this.my_binary == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.my_binary, sb);
         }

         first = false;
      }

      if (this.isSetMy_string_string_map()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_string_string_map:");
         if (this.my_string_string_map == null) {
            sb.append("null");
         } else {
            sb.append(this.my_string_string_map);
         }

         first = false;
      }

      if (this.isSetMy_string_enum_map()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_string_enum_map:");
         if (this.my_string_enum_map == null) {
            sb.append("null");
         } else {
            sb.append(this.my_string_enum_map);
         }

         first = false;
      }

      if (this.isSetMy_enum_string_map()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enum_string_map:");
         if (this.my_enum_string_map == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enum_string_map);
         }

         first = false;
      }

      if (this.isSetMy_enum_struct_map()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enum_struct_map:");
         if (this.my_enum_struct_map == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enum_struct_map);
         }

         first = false;
      }

      if (this.isSetMy_enum_stringlist_map()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enum_stringlist_map:");
         if (this.my_enum_stringlist_map == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enum_stringlist_map);
         }

         first = false;
      }

      if (this.isSetMy_enum_structlist_map()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enum_structlist_map:");
         if (this.my_enum_structlist_map == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enum_structlist_map);
         }

         first = false;
      }

      if (this.isSetMy_stringlist()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_stringlist:");
         if (this.my_stringlist == null) {
            sb.append("null");
         } else {
            sb.append(this.my_stringlist);
         }

         first = false;
      }

      if (this.isSetMy_structlist()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_structlist:");
         if (this.my_structlist == null) {
            sb.append("null");
         } else {
            sb.append(this.my_structlist);
         }

         first = false;
      }

      if (this.isSetMy_enumlist()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enumlist:");
         if (this.my_enumlist == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enumlist);
         }

         first = false;
      }

      if (this.isSetMy_stringset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_stringset:");
         if (this.my_stringset == null) {
            sb.append("null");
         } else {
            sb.append(this.my_stringset);
         }

         first = false;
      }

      if (this.isSetMy_enumset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enumset:");
         if (this.my_enumset == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enumset);
         }

         first = false;
      }

      if (this.isSetMy_structset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_structset:");
         if (this.my_structset == null) {
            sb.append("null");
         } else {
            sb.append(this.my_structset);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{MegaStruct._Fields.MY_BOOL, MegaStruct._Fields.MY_BYTE, MegaStruct._Fields.MY_16BIT_INT, MegaStruct._Fields.MY_32BIT_INT, MegaStruct._Fields.MY_64BIT_INT, MegaStruct._Fields.MY_DOUBLE, MegaStruct._Fields.MY_STRING, MegaStruct._Fields.MY_BINARY, MegaStruct._Fields.MY_STRING_STRING_MAP, MegaStruct._Fields.MY_STRING_ENUM_MAP, MegaStruct._Fields.MY_ENUM_STRING_MAP, MegaStruct._Fields.MY_ENUM_STRUCT_MAP, MegaStruct._Fields.MY_ENUM_STRINGLIST_MAP, MegaStruct._Fields.MY_ENUM_STRUCTLIST_MAP, MegaStruct._Fields.MY_STRINGLIST, MegaStruct._Fields.MY_STRUCTLIST, MegaStruct._Fields.MY_ENUMLIST, MegaStruct._Fields.MY_STRINGSET, MegaStruct._Fields.MY_ENUMSET, MegaStruct._Fields.MY_STRUCTSET};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(MegaStruct._Fields.MY_BOOL, new FieldMetaData("my_bool", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(MegaStruct._Fields.MY_BYTE, new FieldMetaData("my_byte", (byte)2, new FieldValueMetaData((byte)3)));
      tmpMap.put(MegaStruct._Fields.MY_16BIT_INT, new FieldMetaData("my_16bit_int", (byte)2, new FieldValueMetaData((byte)6)));
      tmpMap.put(MegaStruct._Fields.MY_32BIT_INT, new FieldMetaData("my_32bit_int", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(MegaStruct._Fields.MY_64BIT_INT, new FieldMetaData("my_64bit_int", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(MegaStruct._Fields.MY_DOUBLE, new FieldMetaData("my_double", (byte)2, new FieldValueMetaData((byte)4)));
      tmpMap.put(MegaStruct._Fields.MY_STRING, new FieldMetaData("my_string", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(MegaStruct._Fields.MY_BINARY, new FieldMetaData("my_binary", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(MegaStruct._Fields.MY_STRING_STRING_MAP, new FieldMetaData("my_string_string_map", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(MegaStruct._Fields.MY_STRING_ENUM_MAP, new FieldMetaData("my_string_enum_map", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new EnumMetaData((byte)16, MyEnum.class))));
      tmpMap.put(MegaStruct._Fields.MY_ENUM_STRING_MAP, new FieldMetaData("my_enum_string_map", (byte)2, new MapMetaData((byte)13, new EnumMetaData((byte)16, MyEnum.class), new FieldValueMetaData((byte)11))));
      tmpMap.put(MegaStruct._Fields.MY_ENUM_STRUCT_MAP, new FieldMetaData("my_enum_struct_map", (byte)2, new MapMetaData((byte)13, new EnumMetaData((byte)16, MyEnum.class), new StructMetaData((byte)12, MiniStruct.class))));
      tmpMap.put(MegaStruct._Fields.MY_ENUM_STRINGLIST_MAP, new FieldMetaData("my_enum_stringlist_map", (byte)2, new MapMetaData((byte)13, new EnumMetaData((byte)16, MyEnum.class), new ListMetaData((byte)15, new FieldValueMetaData((byte)11)))));
      tmpMap.put(MegaStruct._Fields.MY_ENUM_STRUCTLIST_MAP, new FieldMetaData("my_enum_structlist_map", (byte)2, new MapMetaData((byte)13, new EnumMetaData((byte)16, MyEnum.class), new ListMetaData((byte)15, new StructMetaData((byte)12, MiniStruct.class)))));
      tmpMap.put(MegaStruct._Fields.MY_STRINGLIST, new FieldMetaData("my_stringlist", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(MegaStruct._Fields.MY_STRUCTLIST, new FieldMetaData("my_structlist", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, MiniStruct.class))));
      tmpMap.put(MegaStruct._Fields.MY_ENUMLIST, new FieldMetaData("my_enumlist", (byte)2, new ListMetaData((byte)15, new EnumMetaData((byte)16, MyEnum.class))));
      tmpMap.put(MegaStruct._Fields.MY_STRINGSET, new FieldMetaData("my_stringset", (byte)2, new SetMetaData((byte)14, new FieldValueMetaData((byte)11))));
      tmpMap.put(MegaStruct._Fields.MY_ENUMSET, new FieldMetaData("my_enumset", (byte)2, new SetMetaData((byte)14, new EnumMetaData((byte)16, MyEnum.class))));
      tmpMap.put(MegaStruct._Fields.MY_STRUCTSET, new FieldMetaData("my_structset", (byte)2, new SetMetaData((byte)14, new StructMetaData((byte)12, MiniStruct.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(MegaStruct.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MY_BOOL((short)1, "my_bool"),
      MY_BYTE((short)2, "my_byte"),
      MY_16BIT_INT((short)3, "my_16bit_int"),
      MY_32BIT_INT((short)4, "my_32bit_int"),
      MY_64BIT_INT((short)5, "my_64bit_int"),
      MY_DOUBLE((short)6, "my_double"),
      MY_STRING((short)7, "my_string"),
      MY_BINARY((short)8, "my_binary"),
      MY_STRING_STRING_MAP((short)9, "my_string_string_map"),
      MY_STRING_ENUM_MAP((short)10, "my_string_enum_map"),
      MY_ENUM_STRING_MAP((short)11, "my_enum_string_map"),
      MY_ENUM_STRUCT_MAP((short)12, "my_enum_struct_map"),
      MY_ENUM_STRINGLIST_MAP((short)13, "my_enum_stringlist_map"),
      MY_ENUM_STRUCTLIST_MAP((short)14, "my_enum_structlist_map"),
      MY_STRINGLIST((short)15, "my_stringlist"),
      MY_STRUCTLIST((short)16, "my_structlist"),
      MY_ENUMLIST((short)17, "my_enumlist"),
      MY_STRINGSET((short)18, "my_stringset"),
      MY_ENUMSET((short)19, "my_enumset"),
      MY_STRUCTSET((short)20, "my_structset");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MY_BOOL;
            case 2:
               return MY_BYTE;
            case 3:
               return MY_16BIT_INT;
            case 4:
               return MY_32BIT_INT;
            case 5:
               return MY_64BIT_INT;
            case 6:
               return MY_DOUBLE;
            case 7:
               return MY_STRING;
            case 8:
               return MY_BINARY;
            case 9:
               return MY_STRING_STRING_MAP;
            case 10:
               return MY_STRING_ENUM_MAP;
            case 11:
               return MY_ENUM_STRING_MAP;
            case 12:
               return MY_ENUM_STRUCT_MAP;
            case 13:
               return MY_ENUM_STRINGLIST_MAP;
            case 14:
               return MY_ENUM_STRUCTLIST_MAP;
            case 15:
               return MY_STRINGLIST;
            case 16:
               return MY_STRUCTLIST;
            case 17:
               return MY_ENUMLIST;
            case 18:
               return MY_STRINGSET;
            case 19:
               return MY_ENUMSET;
            case 20:
               return MY_STRUCTSET;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class MegaStructStandardSchemeFactory implements SchemeFactory {
      private MegaStructStandardSchemeFactory() {
      }

      public MegaStructStandardScheme getScheme() {
         return new MegaStructStandardScheme();
      }
   }

   private static class MegaStructStandardScheme extends StandardScheme {
      private MegaStructStandardScheme() {
      }

      public void read(TProtocol iprot, MegaStruct struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 2) {
                     struct.my_bool = iprot.readBool();
                     struct.setMy_boolIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 3) {
                     struct.my_byte = iprot.readByte();
                     struct.setMy_byteIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 6) {
                     struct.my_16bit_int = iprot.readI16();
                     struct.setMy_16bit_intIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.my_32bit_int = iprot.readI32();
                     struct.setMy_32bit_intIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 10) {
                     struct.my_64bit_int = iprot.readI64();
                     struct.setMy_64bit_intIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 4) {
                     struct.my_double = iprot.readDouble();
                     struct.setMy_doubleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 11) {
                     struct.my_string = iprot.readString();
                     struct.setMy_stringIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 11) {
                     struct.my_binary = iprot.readBinary();
                     struct.setMy_binaryIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map0 = iprot.readMapBegin();
                  struct.my_string_string_map = new HashMap(2 * _map0.size);

                  for(int _i3 = 0; _i3 < _map0.size; ++_i3) {
                     String _key1 = iprot.readString();
                     String _val2 = iprot.readString();
                     struct.my_string_string_map.put(_key1, _val2);
                  }

                  iprot.readMapEnd();
                  struct.setMy_string_string_mapIsSet(true);
                  break;
               case 10:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map4 = iprot.readMapBegin();
                  struct.my_string_enum_map = new HashMap(2 * _map4.size);

                  for(int _i7 = 0; _i7 < _map4.size; ++_i7) {
                     String _key5 = iprot.readString();
                     MyEnum _val6 = MyEnum.findByValue(iprot.readI32());
                     struct.my_string_enum_map.put(_key5, _val6);
                  }

                  iprot.readMapEnd();
                  struct.setMy_string_enum_mapIsSet(true);
                  break;
               case 11:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map8 = iprot.readMapBegin();
                  struct.my_enum_string_map = new EnumMap(MyEnum.class);

                  for(int _i11 = 0; _i11 < _map8.size; ++_i11) {
                     MyEnum _key9 = MyEnum.findByValue(iprot.readI32());
                     String _val10 = iprot.readString();
                     if (_key9 != null) {
                        struct.my_enum_string_map.put(_key9, _val10);
                     }
                  }

                  iprot.readMapEnd();
                  struct.setMy_enum_string_mapIsSet(true);
                  break;
               case 12:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map12 = iprot.readMapBegin();
                  struct.my_enum_struct_map = new EnumMap(MyEnum.class);

                  for(int _i15 = 0; _i15 < _map12.size; ++_i15) {
                     MyEnum _key13 = MyEnum.findByValue(iprot.readI32());
                     MiniStruct _val14 = new MiniStruct();
                     _val14.read(iprot);
                     if (_key13 != null) {
                        struct.my_enum_struct_map.put(_key13, _val14);
                     }
                  }

                  iprot.readMapEnd();
                  struct.setMy_enum_struct_mapIsSet(true);
                  break;
               case 13:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map16 = iprot.readMapBegin();
                  struct.my_enum_stringlist_map = new EnumMap(MyEnum.class);

                  for(int _i19 = 0; _i19 < _map16.size; ++_i19) {
                     MyEnum _key17 = MyEnum.findByValue(iprot.readI32());
                     TList _list20 = iprot.readListBegin();
                     List<String> _val18 = new ArrayList(_list20.size);

                     for(int _i22 = 0; _i22 < _list20.size; ++_i22) {
                        String _elem21 = iprot.readString();
                        _val18.add(_elem21);
                     }

                     iprot.readListEnd();
                     if (_key17 != null) {
                        struct.my_enum_stringlist_map.put(_key17, _val18);
                     }
                  }

                  iprot.readMapEnd();
                  struct.setMy_enum_stringlist_mapIsSet(true);
                  break;
               case 14:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map23 = iprot.readMapBegin();
                  struct.my_enum_structlist_map = new EnumMap(MyEnum.class);

                  for(int _i26 = 0; _i26 < _map23.size; ++_i26) {
                     MyEnum _key24 = MyEnum.findByValue(iprot.readI32());
                     TList _list27 = iprot.readListBegin();
                     List<MiniStruct> _val25 = new ArrayList(_list27.size);

                     for(int _i29 = 0; _i29 < _list27.size; ++_i29) {
                        MiniStruct _elem28 = new MiniStruct();
                        _elem28.read(iprot);
                        _val25.add(_elem28);
                     }

                     iprot.readListEnd();
                     if (_key24 != null) {
                        struct.my_enum_structlist_map.put(_key24, _val25);
                     }
                  }

                  iprot.readMapEnd();
                  struct.setMy_enum_structlist_mapIsSet(true);
                  break;
               case 15:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list30 = iprot.readListBegin();
                  struct.my_stringlist = new ArrayList(_list30.size);

                  for(int _i32 = 0; _i32 < _list30.size; ++_i32) {
                     String _elem31 = iprot.readString();
                     struct.my_stringlist.add(_elem31);
                  }

                  iprot.readListEnd();
                  struct.setMy_stringlistIsSet(true);
                  break;
               case 16:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list33 = iprot.readListBegin();
                  struct.my_structlist = new ArrayList(_list33.size);

                  for(int _i35 = 0; _i35 < _list33.size; ++_i35) {
                     MiniStruct _elem34 = new MiniStruct();
                     _elem34.read(iprot);
                     struct.my_structlist.add(_elem34);
                  }

                  iprot.readListEnd();
                  struct.setMy_structlistIsSet(true);
                  break;
               case 17:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list36 = iprot.readListBegin();
                  struct.my_enumlist = new ArrayList(_list36.size);

                  for(int _i38 = 0; _i38 < _list36.size; ++_i38) {
                     MyEnum _elem37 = MyEnum.findByValue(iprot.readI32());
                     if (_elem37 != null) {
                        struct.my_enumlist.add(_elem37);
                     }
                  }

                  iprot.readListEnd();
                  struct.setMy_enumlistIsSet(true);
                  break;
               case 18:
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set39 = iprot.readSetBegin();
                  struct.my_stringset = new HashSet(2 * _set39.size);

                  for(int _i41 = 0; _i41 < _set39.size; ++_i41) {
                     String _elem40 = iprot.readString();
                     struct.my_stringset.add(_elem40);
                  }

                  iprot.readSetEnd();
                  struct.setMy_stringsetIsSet(true);
                  break;
               case 19:
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set42 = iprot.readSetBegin();
                  struct.my_enumset = EnumSet.noneOf(MyEnum.class);

                  for(int _i44 = 0; _i44 < _set42.size; ++_i44) {
                     MyEnum _elem43 = MyEnum.findByValue(iprot.readI32());
                     if (_elem43 != null) {
                        struct.my_enumset.add(_elem43);
                     }
                  }

                  iprot.readSetEnd();
                  struct.setMy_enumsetIsSet(true);
                  break;
               case 20:
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set45 = iprot.readSetBegin();
                  struct.my_structset = new HashSet(2 * _set45.size);

                  for(int _i47 = 0; _i47 < _set45.size; ++_i47) {
                     MiniStruct _elem46 = new MiniStruct();
                     _elem46.read(iprot);
                     struct.my_structset.add(_elem46);
                  }

                  iprot.readSetEnd();
                  struct.setMy_structsetIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, MegaStruct struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(MegaStruct.STRUCT_DESC);
         if (struct.isSetMy_bool()) {
            oprot.writeFieldBegin(MegaStruct.MY_BOOL_FIELD_DESC);
            oprot.writeBool(struct.my_bool);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMy_byte()) {
            oprot.writeFieldBegin(MegaStruct.MY_BYTE_FIELD_DESC);
            oprot.writeByte(struct.my_byte);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMy_16bit_int()) {
            oprot.writeFieldBegin(MegaStruct.MY_16BIT_INT_FIELD_DESC);
            oprot.writeI16(struct.my_16bit_int);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMy_32bit_int()) {
            oprot.writeFieldBegin(MegaStruct.MY_32BIT_INT_FIELD_DESC);
            oprot.writeI32(struct.my_32bit_int);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMy_64bit_int()) {
            oprot.writeFieldBegin(MegaStruct.MY_64BIT_INT_FIELD_DESC);
            oprot.writeI64(struct.my_64bit_int);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMy_double()) {
            oprot.writeFieldBegin(MegaStruct.MY_DOUBLE_FIELD_DESC);
            oprot.writeDouble(struct.my_double);
            oprot.writeFieldEnd();
         }

         if (struct.my_string != null && struct.isSetMy_string()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRING_FIELD_DESC);
            oprot.writeString(struct.my_string);
            oprot.writeFieldEnd();
         }

         if (struct.my_binary != null && struct.isSetMy_binary()) {
            oprot.writeFieldBegin(MegaStruct.MY_BINARY_FIELD_DESC);
            oprot.writeBinary(struct.my_binary);
            oprot.writeFieldEnd();
         }

         if (struct.my_string_string_map != null && struct.isSetMy_string_string_map()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRING_STRING_MAP_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.my_string_string_map.size()));

            for(Map.Entry _iter48 : struct.my_string_string_map.entrySet()) {
               oprot.writeString((String)_iter48.getKey());
               oprot.writeString((String)_iter48.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_string_enum_map != null && struct.isSetMy_string_enum_map()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRING_ENUM_MAP_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)8, struct.my_string_enum_map.size()));

            for(Map.Entry _iter49 : struct.my_string_enum_map.entrySet()) {
               oprot.writeString((String)_iter49.getKey());
               oprot.writeI32(((MyEnum)_iter49.getValue()).getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_enum_string_map != null && struct.isSetMy_enum_string_map()) {
            oprot.writeFieldBegin(MegaStruct.MY_ENUM_STRING_MAP_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)8, (byte)11, struct.my_enum_string_map.size()));

            for(Map.Entry _iter50 : struct.my_enum_string_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter50.getKey()).getValue());
               oprot.writeString((String)_iter50.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_enum_struct_map != null && struct.isSetMy_enum_struct_map()) {
            oprot.writeFieldBegin(MegaStruct.MY_ENUM_STRUCT_MAP_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)8, (byte)12, struct.my_enum_struct_map.size()));

            for(Map.Entry _iter51 : struct.my_enum_struct_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter51.getKey()).getValue());
               ((MiniStruct)_iter51.getValue()).write(oprot);
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_enum_stringlist_map != null && struct.isSetMy_enum_stringlist_map()) {
            oprot.writeFieldBegin(MegaStruct.MY_ENUM_STRINGLIST_MAP_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)8, (byte)15, struct.my_enum_stringlist_map.size()));

            for(Map.Entry _iter52 : struct.my_enum_stringlist_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter52.getKey()).getValue());
               oprot.writeListBegin(new TList((byte)11, ((List)_iter52.getValue()).size()));

               for(String _iter53 : (List)_iter52.getValue()) {
                  oprot.writeString(_iter53);
               }

               oprot.writeListEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_enum_structlist_map != null && struct.isSetMy_enum_structlist_map()) {
            oprot.writeFieldBegin(MegaStruct.MY_ENUM_STRUCTLIST_MAP_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)8, (byte)15, struct.my_enum_structlist_map.size()));

            for(Map.Entry _iter54 : struct.my_enum_structlist_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter54.getKey()).getValue());
               oprot.writeListBegin(new TList((byte)12, ((List)_iter54.getValue()).size()));

               for(MiniStruct _iter55 : (List)_iter54.getValue()) {
                  _iter55.write(oprot);
               }

               oprot.writeListEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_stringlist != null && struct.isSetMy_stringlist()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRINGLIST_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.my_stringlist.size()));

            for(String _iter56 : struct.my_stringlist) {
               oprot.writeString(_iter56);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_structlist != null && struct.isSetMy_structlist()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRUCTLIST_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.my_structlist.size()));

            for(MiniStruct _iter57 : struct.my_structlist) {
               _iter57.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_enumlist != null && struct.isSetMy_enumlist()) {
            oprot.writeFieldBegin(MegaStruct.MY_ENUMLIST_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)8, struct.my_enumlist.size()));

            for(MyEnum _iter58 : struct.my_enumlist) {
               oprot.writeI32(_iter58.getValue());
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_stringset != null && struct.isSetMy_stringset()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRINGSET_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)11, struct.my_stringset.size()));

            for(String _iter59 : struct.my_stringset) {
               oprot.writeString(_iter59);
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_enumset != null && struct.isSetMy_enumset()) {
            oprot.writeFieldBegin(MegaStruct.MY_ENUMSET_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)8, struct.my_enumset.size()));

            for(MyEnum _iter60 : struct.my_enumset) {
               oprot.writeI32(_iter60.getValue());
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         if (struct.my_structset != null && struct.isSetMy_structset()) {
            oprot.writeFieldBegin(MegaStruct.MY_STRUCTSET_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)12, struct.my_structset.size()));

            for(MiniStruct _iter61 : struct.my_structset) {
               _iter61.write(oprot);
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class MegaStructTupleSchemeFactory implements SchemeFactory {
      private MegaStructTupleSchemeFactory() {
      }

      public MegaStructTupleScheme getScheme() {
         return new MegaStructTupleScheme();
      }
   }

   private static class MegaStructTupleScheme extends TupleScheme {
      private MegaStructTupleScheme() {
      }

      public void write(TProtocol prot, MegaStruct struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetMy_bool()) {
            optionals.set(0);
         }

         if (struct.isSetMy_byte()) {
            optionals.set(1);
         }

         if (struct.isSetMy_16bit_int()) {
            optionals.set(2);
         }

         if (struct.isSetMy_32bit_int()) {
            optionals.set(3);
         }

         if (struct.isSetMy_64bit_int()) {
            optionals.set(4);
         }

         if (struct.isSetMy_double()) {
            optionals.set(5);
         }

         if (struct.isSetMy_string()) {
            optionals.set(6);
         }

         if (struct.isSetMy_binary()) {
            optionals.set(7);
         }

         if (struct.isSetMy_string_string_map()) {
            optionals.set(8);
         }

         if (struct.isSetMy_string_enum_map()) {
            optionals.set(9);
         }

         if (struct.isSetMy_enum_string_map()) {
            optionals.set(10);
         }

         if (struct.isSetMy_enum_struct_map()) {
            optionals.set(11);
         }

         if (struct.isSetMy_enum_stringlist_map()) {
            optionals.set(12);
         }

         if (struct.isSetMy_enum_structlist_map()) {
            optionals.set(13);
         }

         if (struct.isSetMy_stringlist()) {
            optionals.set(14);
         }

         if (struct.isSetMy_structlist()) {
            optionals.set(15);
         }

         if (struct.isSetMy_enumlist()) {
            optionals.set(16);
         }

         if (struct.isSetMy_stringset()) {
            optionals.set(17);
         }

         if (struct.isSetMy_enumset()) {
            optionals.set(18);
         }

         if (struct.isSetMy_structset()) {
            optionals.set(19);
         }

         oprot.writeBitSet(optionals, 20);
         if (struct.isSetMy_bool()) {
            oprot.writeBool(struct.my_bool);
         }

         if (struct.isSetMy_byte()) {
            oprot.writeByte(struct.my_byte);
         }

         if (struct.isSetMy_16bit_int()) {
            oprot.writeI16(struct.my_16bit_int);
         }

         if (struct.isSetMy_32bit_int()) {
            oprot.writeI32(struct.my_32bit_int);
         }

         if (struct.isSetMy_64bit_int()) {
            oprot.writeI64(struct.my_64bit_int);
         }

         if (struct.isSetMy_double()) {
            oprot.writeDouble(struct.my_double);
         }

         if (struct.isSetMy_string()) {
            oprot.writeString(struct.my_string);
         }

         if (struct.isSetMy_binary()) {
            oprot.writeBinary(struct.my_binary);
         }

         if (struct.isSetMy_string_string_map()) {
            oprot.writeI32(struct.my_string_string_map.size());

            for(Map.Entry _iter62 : struct.my_string_string_map.entrySet()) {
               oprot.writeString((String)_iter62.getKey());
               oprot.writeString((String)_iter62.getValue());
            }
         }

         if (struct.isSetMy_string_enum_map()) {
            oprot.writeI32(struct.my_string_enum_map.size());

            for(Map.Entry _iter63 : struct.my_string_enum_map.entrySet()) {
               oprot.writeString((String)_iter63.getKey());
               oprot.writeI32(((MyEnum)_iter63.getValue()).getValue());
            }
         }

         if (struct.isSetMy_enum_string_map()) {
            oprot.writeI32(struct.my_enum_string_map.size());

            for(Map.Entry _iter64 : struct.my_enum_string_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter64.getKey()).getValue());
               oprot.writeString((String)_iter64.getValue());
            }
         }

         if (struct.isSetMy_enum_struct_map()) {
            oprot.writeI32(struct.my_enum_struct_map.size());

            for(Map.Entry _iter65 : struct.my_enum_struct_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter65.getKey()).getValue());
               ((MiniStruct)_iter65.getValue()).write(oprot);
            }
         }

         if (struct.isSetMy_enum_stringlist_map()) {
            oprot.writeI32(struct.my_enum_stringlist_map.size());

            for(Map.Entry _iter66 : struct.my_enum_stringlist_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter66.getKey()).getValue());
               oprot.writeI32(((List)_iter66.getValue()).size());

               for(String _iter67 : (List)_iter66.getValue()) {
                  oprot.writeString(_iter67);
               }
            }
         }

         if (struct.isSetMy_enum_structlist_map()) {
            oprot.writeI32(struct.my_enum_structlist_map.size());

            for(Map.Entry _iter68 : struct.my_enum_structlist_map.entrySet()) {
               oprot.writeI32(((MyEnum)_iter68.getKey()).getValue());
               oprot.writeI32(((List)_iter68.getValue()).size());

               for(MiniStruct _iter69 : (List)_iter68.getValue()) {
                  _iter69.write(oprot);
               }
            }
         }

         if (struct.isSetMy_stringlist()) {
            oprot.writeI32(struct.my_stringlist.size());

            for(String _iter70 : struct.my_stringlist) {
               oprot.writeString(_iter70);
            }
         }

         if (struct.isSetMy_structlist()) {
            oprot.writeI32(struct.my_structlist.size());

            for(MiniStruct _iter71 : struct.my_structlist) {
               _iter71.write(oprot);
            }
         }

         if (struct.isSetMy_enumlist()) {
            oprot.writeI32(struct.my_enumlist.size());

            for(MyEnum _iter72 : struct.my_enumlist) {
               oprot.writeI32(_iter72.getValue());
            }
         }

         if (struct.isSetMy_stringset()) {
            oprot.writeI32(struct.my_stringset.size());

            for(String _iter73 : struct.my_stringset) {
               oprot.writeString(_iter73);
            }
         }

         if (struct.isSetMy_enumset()) {
            oprot.writeI32(struct.my_enumset.size());

            for(MyEnum _iter74 : struct.my_enumset) {
               oprot.writeI32(_iter74.getValue());
            }
         }

         if (struct.isSetMy_structset()) {
            oprot.writeI32(struct.my_structset.size());

            for(MiniStruct _iter75 : struct.my_structset) {
               _iter75.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, MegaStruct struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(20);
         if (incoming.get(0)) {
            struct.my_bool = iprot.readBool();
            struct.setMy_boolIsSet(true);
         }

         if (incoming.get(1)) {
            struct.my_byte = iprot.readByte();
            struct.setMy_byteIsSet(true);
         }

         if (incoming.get(2)) {
            struct.my_16bit_int = iprot.readI16();
            struct.setMy_16bit_intIsSet(true);
         }

         if (incoming.get(3)) {
            struct.my_32bit_int = iprot.readI32();
            struct.setMy_32bit_intIsSet(true);
         }

         if (incoming.get(4)) {
            struct.my_64bit_int = iprot.readI64();
            struct.setMy_64bit_intIsSet(true);
         }

         if (incoming.get(5)) {
            struct.my_double = iprot.readDouble();
            struct.setMy_doubleIsSet(true);
         }

         if (incoming.get(6)) {
            struct.my_string = iprot.readString();
            struct.setMy_stringIsSet(true);
         }

         if (incoming.get(7)) {
            struct.my_binary = iprot.readBinary();
            struct.setMy_binaryIsSet(true);
         }

         if (incoming.get(8)) {
            TMap _map76 = iprot.readMapBegin((byte)11, (byte)11);
            struct.my_string_string_map = new HashMap(2 * _map76.size);

            for(int _i79 = 0; _i79 < _map76.size; ++_i79) {
               String _key77 = iprot.readString();
               String _val78 = iprot.readString();
               struct.my_string_string_map.put(_key77, _val78);
            }

            struct.setMy_string_string_mapIsSet(true);
         }

         if (incoming.get(9)) {
            TMap _map80 = iprot.readMapBegin((byte)11, (byte)8);
            struct.my_string_enum_map = new HashMap(2 * _map80.size);

            for(int _i83 = 0; _i83 < _map80.size; ++_i83) {
               String _key81 = iprot.readString();
               MyEnum _val82 = MyEnum.findByValue(iprot.readI32());
               struct.my_string_enum_map.put(_key81, _val82);
            }

            struct.setMy_string_enum_mapIsSet(true);
         }

         if (incoming.get(10)) {
            TMap _map84 = iprot.readMapBegin((byte)8, (byte)11);
            struct.my_enum_string_map = new EnumMap(MyEnum.class);

            for(int _i87 = 0; _i87 < _map84.size; ++_i87) {
               MyEnum _key85 = MyEnum.findByValue(iprot.readI32());
               String _val86 = iprot.readString();
               if (_key85 != null) {
                  struct.my_enum_string_map.put(_key85, _val86);
               }
            }

            struct.setMy_enum_string_mapIsSet(true);
         }

         if (incoming.get(11)) {
            TMap _map88 = iprot.readMapBegin((byte)8, (byte)12);
            struct.my_enum_struct_map = new EnumMap(MyEnum.class);

            for(int _i91 = 0; _i91 < _map88.size; ++_i91) {
               MyEnum _key89 = MyEnum.findByValue(iprot.readI32());
               MiniStruct _val90 = new MiniStruct();
               _val90.read(iprot);
               if (_key89 != null) {
                  struct.my_enum_struct_map.put(_key89, _val90);
               }
            }

            struct.setMy_enum_struct_mapIsSet(true);
         }

         if (incoming.get(12)) {
            TMap _map92 = iprot.readMapBegin((byte)8, (byte)15);
            struct.my_enum_stringlist_map = new EnumMap(MyEnum.class);

            for(int _i95 = 0; _i95 < _map92.size; ++_i95) {
               MyEnum _key93 = MyEnum.findByValue(iprot.readI32());
               TList _list96 = iprot.readListBegin((byte)11);
               List<String> _val94 = new ArrayList(_list96.size);

               for(int _i98 = 0; _i98 < _list96.size; ++_i98) {
                  String _elem97 = iprot.readString();
                  _val94.add(_elem97);
               }

               if (_key93 != null) {
                  struct.my_enum_stringlist_map.put(_key93, _val94);
               }
            }

            struct.setMy_enum_stringlist_mapIsSet(true);
         }

         if (incoming.get(13)) {
            TMap _map99 = iprot.readMapBegin((byte)8, (byte)15);
            struct.my_enum_structlist_map = new EnumMap(MyEnum.class);

            for(int _i102 = 0; _i102 < _map99.size; ++_i102) {
               MyEnum _key100 = MyEnum.findByValue(iprot.readI32());
               TList _list103 = iprot.readListBegin((byte)12);
               List<MiniStruct> _val101 = new ArrayList(_list103.size);

               for(int _i105 = 0; _i105 < _list103.size; ++_i105) {
                  MiniStruct _elem104 = new MiniStruct();
                  _elem104.read(iprot);
                  _val101.add(_elem104);
               }

               if (_key100 != null) {
                  struct.my_enum_structlist_map.put(_key100, _val101);
               }
            }

            struct.setMy_enum_structlist_mapIsSet(true);
         }

         if (incoming.get(14)) {
            TList _list106 = iprot.readListBegin((byte)11);
            struct.my_stringlist = new ArrayList(_list106.size);

            for(int _i108 = 0; _i108 < _list106.size; ++_i108) {
               String _elem107 = iprot.readString();
               struct.my_stringlist.add(_elem107);
            }

            struct.setMy_stringlistIsSet(true);
         }

         if (incoming.get(15)) {
            TList _list109 = iprot.readListBegin((byte)12);
            struct.my_structlist = new ArrayList(_list109.size);

            for(int _i111 = 0; _i111 < _list109.size; ++_i111) {
               MiniStruct _elem110 = new MiniStruct();
               _elem110.read(iprot);
               struct.my_structlist.add(_elem110);
            }

            struct.setMy_structlistIsSet(true);
         }

         if (incoming.get(16)) {
            TList _list112 = iprot.readListBegin((byte)8);
            struct.my_enumlist = new ArrayList(_list112.size);

            for(int _i114 = 0; _i114 < _list112.size; ++_i114) {
               MyEnum _elem113 = MyEnum.findByValue(iprot.readI32());
               if (_elem113 != null) {
                  struct.my_enumlist.add(_elem113);
               }
            }

            struct.setMy_enumlistIsSet(true);
         }

         if (incoming.get(17)) {
            TSet _set115 = iprot.readSetBegin((byte)11);
            struct.my_stringset = new HashSet(2 * _set115.size);

            for(int _i117 = 0; _i117 < _set115.size; ++_i117) {
               String _elem116 = iprot.readString();
               struct.my_stringset.add(_elem116);
            }

            struct.setMy_stringsetIsSet(true);
         }

         if (incoming.get(18)) {
            TSet _set118 = iprot.readSetBegin((byte)8);
            struct.my_enumset = EnumSet.noneOf(MyEnum.class);

            for(int _i120 = 0; _i120 < _set118.size; ++_i120) {
               MyEnum _elem119 = MyEnum.findByValue(iprot.readI32());
               if (_elem119 != null) {
                  struct.my_enumset.add(_elem119);
               }
            }

            struct.setMy_enumsetIsSet(true);
         }

         if (incoming.get(19)) {
            TSet _set121 = iprot.readSetBegin((byte)12);
            struct.my_structset = new HashSet(2 * _set121.size);

            for(int _i123 = 0; _i123 < _set121.size; ++_i123) {
               MiniStruct _elem122 = new MiniStruct();
               _elem122.read(iprot);
               struct.my_structset.add(_elem122);
            }

            struct.setMy_structsetIsSet(true);
         }

      }
   }
}

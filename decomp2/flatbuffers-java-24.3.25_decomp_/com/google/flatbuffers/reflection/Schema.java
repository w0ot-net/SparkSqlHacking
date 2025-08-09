package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Schema extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Schema getRootAsSchema(ByteBuffer _bb) {
      return getRootAsSchema(_bb, new Schema());
   }

   public static Schema getRootAsSchema(ByteBuffer _bb, Schema obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public static boolean SchemaBufferHasIdentifier(ByteBuffer _bb) {
      return __has_identifier(_bb, "BFBS");
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Schema __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public Object objects(int j) {
      return this.objects(new Object(), j);
   }

   public Object objects(Object obj, int j) {
      int o = this.__offset(4);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int objectsLength() {
      int o = this.__offset(4);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Object objectsByKey(String key) {
      int o = this.__offset(4);
      return o != 0 ? Object.__lookup_by_key((Object)null, this.__vector(o), key, this.bb) : null;
   }

   public Object objectsByKey(Object obj, String key) {
      int o = this.__offset(4);
      return o != 0 ? Object.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public Object.Vector objectsVector() {
      return this.objectsVector(new Object.Vector());
   }

   public Object.Vector objectsVector(Object.Vector obj) {
      int o = this.__offset(4);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public Enum enums(int j) {
      return this.enums(new Enum(), j);
   }

   public Enum enums(Enum obj, int j) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int enumsLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Enum enumsByKey(String key) {
      int o = this.__offset(6);
      return o != 0 ? Enum.__lookup_by_key((Enum)null, this.__vector(o), key, this.bb) : null;
   }

   public Enum enumsByKey(Enum obj, String key) {
      int o = this.__offset(6);
      return o != 0 ? Enum.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public Enum.Vector enumsVector() {
      return this.enumsVector(new Enum.Vector());
   }

   public Enum.Vector enumsVector(Enum.Vector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public String fileIdent() {
      int o = this.__offset(8);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer fileIdentAsByteBuffer() {
      return this.__vector_as_bytebuffer(8, 1);
   }

   public ByteBuffer fileIdentInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 8, 1);
   }

   public String fileExt() {
      int o = this.__offset(10);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer fileExtAsByteBuffer() {
      return this.__vector_as_bytebuffer(10, 1);
   }

   public ByteBuffer fileExtInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 10, 1);
   }

   public Object rootTable() {
      return this.rootTable(new Object());
   }

   public Object rootTable(Object obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Service services(int j) {
      return this.services(new Service(), j);
   }

   public Service services(Service obj, int j) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int servicesLength() {
      int o = this.__offset(14);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Service servicesByKey(String key) {
      int o = this.__offset(14);
      return o != 0 ? Service.__lookup_by_key((Service)null, this.__vector(o), key, this.bb) : null;
   }

   public Service servicesByKey(Service obj, String key) {
      int o = this.__offset(14);
      return o != 0 ? Service.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public Service.Vector servicesVector() {
      return this.servicesVector(new Service.Vector());
   }

   public Service.Vector servicesVector(Service.Vector obj) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public long advancedFeatures() {
      int o = this.__offset(16);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public SchemaFile fbsFiles(int j) {
      return this.fbsFiles(new SchemaFile(), j);
   }

   public SchemaFile fbsFiles(SchemaFile obj, int j) {
      int o = this.__offset(18);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int fbsFilesLength() {
      int o = this.__offset(18);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public SchemaFile fbsFilesByKey(String key) {
      int o = this.__offset(18);
      return o != 0 ? SchemaFile.__lookup_by_key((SchemaFile)null, this.__vector(o), key, this.bb) : null;
   }

   public SchemaFile fbsFilesByKey(SchemaFile obj, String key) {
      int o = this.__offset(18);
      return o != 0 ? SchemaFile.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public SchemaFile.Vector fbsFilesVector() {
      return this.fbsFilesVector(new SchemaFile.Vector());
   }

   public SchemaFile.Vector fbsFilesVector(SchemaFile.Vector obj) {
      int o = this.__offset(18);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public static int createSchema(FlatBufferBuilder builder, int objectsOffset, int enumsOffset, int fileIdentOffset, int fileExtOffset, int rootTableOffset, int servicesOffset, long advancedFeatures, int fbsFilesOffset) {
      builder.startTable(8);
      addAdvancedFeatures(builder, advancedFeatures);
      addFbsFiles(builder, fbsFilesOffset);
      addServices(builder, servicesOffset);
      addRootTable(builder, rootTableOffset);
      addFileExt(builder, fileExtOffset);
      addFileIdent(builder, fileIdentOffset);
      addEnums(builder, enumsOffset);
      addObjects(builder, objectsOffset);
      return endSchema(builder);
   }

   public static void startSchema(FlatBufferBuilder builder) {
      builder.startTable(8);
   }

   public static void addObjects(FlatBufferBuilder builder, int objectsOffset) {
      builder.addOffset(0, objectsOffset, 0);
   }

   public static int createObjectsVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startObjectsVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addEnums(FlatBufferBuilder builder, int enumsOffset) {
      builder.addOffset(1, enumsOffset, 0);
   }

   public static int createEnumsVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startEnumsVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addFileIdent(FlatBufferBuilder builder, int fileIdentOffset) {
      builder.addOffset(2, fileIdentOffset, 0);
   }

   public static void addFileExt(FlatBufferBuilder builder, int fileExtOffset) {
      builder.addOffset(3, fileExtOffset, 0);
   }

   public static void addRootTable(FlatBufferBuilder builder, int rootTableOffset) {
      builder.addOffset(4, rootTableOffset, 0);
   }

   public static void addServices(FlatBufferBuilder builder, int servicesOffset) {
      builder.addOffset(5, servicesOffset, 0);
   }

   public static int createServicesVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startServicesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addAdvancedFeatures(FlatBufferBuilder builder, long advancedFeatures) {
      builder.addLong(6, advancedFeatures, 0L);
   }

   public static void addFbsFiles(FlatBufferBuilder builder, int fbsFilesOffset) {
      builder.addOffset(7, fbsFilesOffset, 0);
   }

   public static int createFbsFilesVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startFbsFilesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static int endSchema(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      builder.required(o, 6);
      return o;
   }

   public static void finishSchemaBuffer(FlatBufferBuilder builder, int offset) {
      builder.finish(offset, "BFBS");
   }

   public static void finishSizePrefixedSchemaBuffer(FlatBufferBuilder builder, int offset) {
      builder.finishSizePrefixed(offset, "BFBS");
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Schema get(int j) {
         return this.get(new Schema(), j);
      }

      public Schema get(Schema obj, int j) {
         return obj.__assign(Schema.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}

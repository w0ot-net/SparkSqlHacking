package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public final class SchemaFile extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static SchemaFile getRootAsSchemaFile(ByteBuffer _bb) {
      return getRootAsSchemaFile(_bb, new SchemaFile());
   }

   public static SchemaFile getRootAsSchemaFile(ByteBuffer _bb, SchemaFile obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public SchemaFile __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public String filename() {
      int o = this.__offset(4);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer filenameAsByteBuffer() {
      return this.__vector_as_bytebuffer(4, 1);
   }

   public ByteBuffer filenameInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 4, 1);
   }

   public String includedFilenames(int j) {
      int o = this.__offset(6);
      return o != 0 ? this.__string(this.__vector(o) + j * 4) : null;
   }

   public int includedFilenamesLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public StringVector includedFilenamesVector() {
      return this.includedFilenamesVector(new StringVector());
   }

   public StringVector includedFilenamesVector(StringVector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public static int createSchemaFile(FlatBufferBuilder builder, int filenameOffset, int includedFilenamesOffset) {
      builder.startTable(2);
      addIncludedFilenames(builder, includedFilenamesOffset);
      addFilename(builder, filenameOffset);
      return endSchemaFile(builder);
   }

   public static void startSchemaFile(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addFilename(FlatBufferBuilder builder, int filenameOffset) {
      builder.addOffset(filenameOffset);
      builder.slot(0);
   }

   public static void addIncludedFilenames(FlatBufferBuilder builder, int includedFilenamesOffset) {
      builder.addOffset(1, includedFilenamesOffset, 0);
   }

   public static int createIncludedFilenamesVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startIncludedFilenamesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static int endSchemaFile(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      return o;
   }

   protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
      return compareStrings(__offset(4, o1, _bb), __offset(4, o2, _bb), _bb);
   }

   public static SchemaFile __lookup_by_key(SchemaFile obj, int vectorLocation, String key, ByteBuffer bb) {
      byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
      int span = bb.getInt(vectorLocation - 4);
      int start = 0;

      while(span != 0) {
         int middle = span / 2;
         int tableOffset = __indirect(vectorLocation + 4 * (start + middle), bb);
         int comp = compareStrings(__offset(4, bb.capacity() - tableOffset, bb), byteKey, bb);
         if (comp > 0) {
            span = middle;
         } else {
            if (comp >= 0) {
               return (obj == null ? new SchemaFile() : obj).__assign(tableOffset, bb);
            }

            ++middle;
            start += middle;
            span -= middle;
         }
      }

      return null;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public SchemaFile get(int j) {
         return this.get(new SchemaFile(), j);
      }

      public SchemaFile get(SchemaFile obj, int j) {
         return obj.__assign(SchemaFile.__indirect(this.__element(j), this.bb), this.bb);
      }

      public SchemaFile getByKey(String key) {
         return SchemaFile.__lookup_by_key((SchemaFile)null, this.__vector(), key, this.bb);
      }

      public SchemaFile getByKey(SchemaFile obj, String key) {
         return SchemaFile.__lookup_by_key(obj, this.__vector(), key, this.bb);
      }
   }
}

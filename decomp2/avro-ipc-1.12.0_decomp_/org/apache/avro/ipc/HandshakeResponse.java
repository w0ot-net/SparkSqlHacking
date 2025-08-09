package org.apache.avro.ipc;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.data.RecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.ResolvingDecoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.avro.specific.SpecificRecordBuilderBase;

@AvroGenerated
public class HandshakeResponse extends SpecificRecordBase implements SpecificRecord {
   private static final long serialVersionUID = 1055336403110395392L;
   public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"HandshakeResponse\",\"namespace\":\"org.apache.avro.ipc\",\"fields\":[{\"name\":\"match\",\"type\":{\"type\":\"enum\",\"name\":\"HandshakeMatch\",\"symbols\":[\"BOTH\",\"CLIENT\",\"NONE\"]}},{\"name\":\"serverProtocol\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"serverHash\",\"type\":[\"null\",{\"type\":\"fixed\",\"name\":\"MD5\",\"size\":16}]},{\"name\":\"meta\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"bytes\",\"avro.java.string\":\"String\"}]}]}");
   private static final SpecificData MODEL$ = new SpecificData();
   private static final BinaryMessageEncoder ENCODER;
   private static final BinaryMessageDecoder DECODER;
   private HandshakeMatch match;
   private String serverProtocol;
   private MD5 serverHash;
   private Map meta;
   private static final DatumWriter WRITER$;
   private static final DatumReader READER$;

   public static Schema getClassSchema() {
      return SCHEMA$;
   }

   public static BinaryMessageEncoder getEncoder() {
      return ENCODER;
   }

   public static BinaryMessageDecoder getDecoder() {
      return DECODER;
   }

   public static BinaryMessageDecoder createDecoder(SchemaStore resolver) {
      return new BinaryMessageDecoder(MODEL$, SCHEMA$, resolver);
   }

   public ByteBuffer toByteBuffer() throws IOException {
      return ENCODER.encode(this);
   }

   public static HandshakeResponse fromByteBuffer(ByteBuffer b) throws IOException {
      return (HandshakeResponse)DECODER.decode(b);
   }

   public HandshakeResponse() {
   }

   public HandshakeResponse(HandshakeMatch match, String serverProtocol, MD5 serverHash, Map meta) {
      this.match = match;
      this.serverProtocol = serverProtocol;
      this.serverHash = serverHash;
      this.meta = meta;
   }

   public SpecificData getSpecificData() {
      return MODEL$;
   }

   public Schema getSchema() {
      return SCHEMA$;
   }

   public Object get(int field$) {
      switch (field$) {
         case 0:
            return this.match;
         case 1:
            return this.serverProtocol;
         case 2:
            return this.serverHash;
         case 3:
            return this.meta;
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + field$);
      }
   }

   public void put(int field$, Object value$) {
      switch (field$) {
         case 0:
            this.match = (HandshakeMatch)value$;
            break;
         case 1:
            this.serverProtocol = value$ != null ? value$.toString() : null;
            break;
         case 2:
            this.serverHash = (MD5)value$;
            break;
         case 3:
            this.meta = (Map)value$;
            break;
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + field$);
      }

   }

   public HandshakeMatch getMatch() {
      return this.match;
   }

   public Optional getOptionalMatch() {
      return Optional.ofNullable(this.match);
   }

   public void setMatch(HandshakeMatch value) {
      this.match = value;
   }

   public String getServerProtocol() {
      return this.serverProtocol;
   }

   public Optional getOptionalServerProtocol() {
      return Optional.ofNullable(this.serverProtocol);
   }

   public void setServerProtocol(String value) {
      this.serverProtocol = value;
   }

   public MD5 getServerHash() {
      return this.serverHash;
   }

   public Optional getOptionalServerHash() {
      return Optional.ofNullable(this.serverHash);
   }

   public void setServerHash(MD5 value) {
      this.serverHash = value;
   }

   public Map getMeta() {
      return this.meta;
   }

   public Optional getOptionalMeta() {
      return Optional.ofNullable(this.meta);
   }

   public void setMeta(Map value) {
      this.meta = value;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public static Builder newBuilder(Builder other) {
      return other == null ? new Builder() : new Builder(other);
   }

   public static Builder newBuilder(HandshakeResponse other) {
      return other == null ? new Builder() : new Builder(other);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      WRITER$.write(this, SpecificData.getEncoder(out));
   }

   public void readExternal(ObjectInput in) throws IOException {
      READER$.read(this, SpecificData.getDecoder(in));
   }

   protected boolean hasCustomCoders() {
      return true;
   }

   public void customEncode(Encoder out) throws IOException {
      out.writeEnum(this.match.ordinal());
      if (this.serverProtocol == null) {
         out.writeIndex(0);
         out.writeNull();
      } else {
         out.writeIndex(1);
         out.writeString(this.serverProtocol);
      }

      if (this.serverHash == null) {
         out.writeIndex(0);
         out.writeNull();
      } else {
         out.writeIndex(1);
         out.writeFixed(this.serverHash.bytes(), 0, 16);
      }

      if (this.meta == null) {
         out.writeIndex(0);
         out.writeNull();
      } else {
         out.writeIndex(1);
         long size0 = (long)this.meta.size();
         out.writeMapStart();
         out.setItemCount(size0);
         long actualSize0 = 0L;

         for(Map.Entry e0 : this.meta.entrySet()) {
            ++actualSize0;
            out.startItem();
            out.writeString((String)e0.getKey());
            ByteBuffer v0 = (ByteBuffer)e0.getValue();
            out.writeBytes(v0);
         }

         out.writeMapEnd();
         if (actualSize0 != size0) {
            throw new ConcurrentModificationException("Map-size written was " + size0 + ", but element count was " + actualSize0 + ".");
         }
      }

   }

   public void customDecode(ResolvingDecoder in) throws IOException {
      Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
      if (fieldOrder == null) {
         this.match = HandshakeMatch.values()[in.readEnum()];
         if (in.readIndex() != 1) {
            in.readNull();
            this.serverProtocol = null;
         } else {
            this.serverProtocol = in.readString();
         }

         if (in.readIndex() != 1) {
            in.readNull();
            this.serverHash = null;
         } else {
            if (this.serverHash == null) {
               this.serverHash = new MD5();
            }

            in.readFixed(this.serverHash.bytes(), 0, 16);
         }

         if (in.readIndex() != 1) {
            in.readNull();
            this.meta = null;
         } else {
            long size0 = in.readMapStart();
            Map<String, ByteBuffer> m0 = this.meta;
            if (m0 == null) {
               m0 = new HashMap((int)(size0 * 4L) / 3 + 1);
               this.meta = m0;
            } else {
               m0.clear();
            }

            while(0L < size0) {
               while(size0 != 0L) {
                  String k0 = null;
                  k0 = in.readString();
                  ByteBuffer v0 = null;
                  v0 = in.readBytes(v0);
                  m0.put(k0, v0);
                  --size0;
               }

               size0 = in.mapNext();
            }
         }
      } else {
         for(int i = 0; i < 4; ++i) {
            long size0;
            Map<String, ByteBuffer> m0;
            switch (fieldOrder[i].pos()) {
               case 0:
                  this.match = HandshakeMatch.values()[in.readEnum()];
                  continue;
               case 1:
                  if (in.readIndex() != 1) {
                     in.readNull();
                     this.serverProtocol = null;
                  } else {
                     this.serverProtocol = in.readString();
                  }
                  continue;
               case 2:
                  if (in.readIndex() != 1) {
                     in.readNull();
                     this.serverHash = null;
                  } else {
                     if (this.serverHash == null) {
                        this.serverHash = new MD5();
                     }

                     in.readFixed(this.serverHash.bytes(), 0, 16);
                  }
                  continue;
               case 3:
                  if (in.readIndex() != 1) {
                     in.readNull();
                     this.meta = null;
                     continue;
                  }

                  size0 = in.readMapStart();
                  m0 = this.meta;
                  if (m0 == null) {
                     m0 = new HashMap((int)(size0 * 4L) / 3 + 1);
                     this.meta = m0;
                  } else {
                     m0.clear();
                  }
                  break;
               default:
                  throw new IOException("Corrupt ResolvingDecoder.");
            }

            while(0L < size0) {
               while(size0 != 0L) {
                  String k0 = null;
                  k0 = in.readString();
                  ByteBuffer v0 = null;
                  v0 = in.readBytes(v0);
                  m0.put(k0, v0);
                  --size0;
               }

               size0 = in.mapNext();
            }
         }
      }

   }

   static {
      ENCODER = new BinaryMessageEncoder(MODEL$, SCHEMA$);
      DECODER = new BinaryMessageDecoder(MODEL$, SCHEMA$);
      WRITER$ = MODEL$.createDatumWriter(SCHEMA$);
      READER$ = MODEL$.createDatumReader(SCHEMA$);
   }

   @AvroGenerated
   public static class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
      private HandshakeMatch match;
      private String serverProtocol;
      private MD5 serverHash;
      private Map meta;

      private Builder() {
         super(HandshakeResponse.SCHEMA$, HandshakeResponse.MODEL$);
      }

      private Builder(Builder other) {
         super(other);
         if (isValidValue(this.fields()[0], other.match)) {
            this.match = (HandshakeMatch)this.data().deepCopy(this.fields()[0].schema(), other.match);
            this.fieldSetFlags()[0] = other.fieldSetFlags()[0];
         }

         if (isValidValue(this.fields()[1], other.serverProtocol)) {
            this.serverProtocol = (String)this.data().deepCopy(this.fields()[1].schema(), other.serverProtocol);
            this.fieldSetFlags()[1] = other.fieldSetFlags()[1];
         }

         if (isValidValue(this.fields()[2], other.serverHash)) {
            this.serverHash = (MD5)this.data().deepCopy(this.fields()[2].schema(), other.serverHash);
            this.fieldSetFlags()[2] = other.fieldSetFlags()[2];
         }

         if (isValidValue(this.fields()[3], other.meta)) {
            this.meta = (Map)this.data().deepCopy(this.fields()[3].schema(), other.meta);
            this.fieldSetFlags()[3] = other.fieldSetFlags()[3];
         }

      }

      private Builder(HandshakeResponse other) {
         super(HandshakeResponse.SCHEMA$, HandshakeResponse.MODEL$);
         if (isValidValue(this.fields()[0], other.match)) {
            this.match = (HandshakeMatch)this.data().deepCopy(this.fields()[0].schema(), other.match);
            this.fieldSetFlags()[0] = true;
         }

         if (isValidValue(this.fields()[1], other.serverProtocol)) {
            this.serverProtocol = (String)this.data().deepCopy(this.fields()[1].schema(), other.serverProtocol);
            this.fieldSetFlags()[1] = true;
         }

         if (isValidValue(this.fields()[2], other.serverHash)) {
            this.serverHash = (MD5)this.data().deepCopy(this.fields()[2].schema(), other.serverHash);
            this.fieldSetFlags()[2] = true;
         }

         if (isValidValue(this.fields()[3], other.meta)) {
            this.meta = (Map)this.data().deepCopy(this.fields()[3].schema(), other.meta);
            this.fieldSetFlags()[3] = true;
         }

      }

      public HandshakeMatch getMatch() {
         return this.match;
      }

      public Optional getOptionalMatch() {
         return Optional.ofNullable(this.match);
      }

      public Builder setMatch(HandshakeMatch value) {
         this.validate(this.fields()[0], value);
         this.match = value;
         this.fieldSetFlags()[0] = true;
         return this;
      }

      public boolean hasMatch() {
         return this.fieldSetFlags()[0];
      }

      public Builder clearMatch() {
         this.match = null;
         this.fieldSetFlags()[0] = false;
         return this;
      }

      public String getServerProtocol() {
         return this.serverProtocol;
      }

      public Optional getOptionalServerProtocol() {
         return Optional.ofNullable(this.serverProtocol);
      }

      public Builder setServerProtocol(String value) {
         this.validate(this.fields()[1], value);
         this.serverProtocol = value;
         this.fieldSetFlags()[1] = true;
         return this;
      }

      public boolean hasServerProtocol() {
         return this.fieldSetFlags()[1];
      }

      public Builder clearServerProtocol() {
         this.serverProtocol = null;
         this.fieldSetFlags()[1] = false;
         return this;
      }

      public MD5 getServerHash() {
         return this.serverHash;
      }

      public Optional getOptionalServerHash() {
         return Optional.ofNullable(this.serverHash);
      }

      public Builder setServerHash(MD5 value) {
         this.validate(this.fields()[2], value);
         this.serverHash = value;
         this.fieldSetFlags()[2] = true;
         return this;
      }

      public boolean hasServerHash() {
         return this.fieldSetFlags()[2];
      }

      public Builder clearServerHash() {
         this.serverHash = null;
         this.fieldSetFlags()[2] = false;
         return this;
      }

      public Map getMeta() {
         return this.meta;
      }

      public Optional getOptionalMeta() {
         return Optional.ofNullable(this.meta);
      }

      public Builder setMeta(Map value) {
         this.validate(this.fields()[3], value);
         this.meta = value;
         this.fieldSetFlags()[3] = true;
         return this;
      }

      public boolean hasMeta() {
         return this.fieldSetFlags()[3];
      }

      public Builder clearMeta() {
         this.meta = null;
         this.fieldSetFlags()[3] = false;
         return this;
      }

      public HandshakeResponse build() {
         try {
            HandshakeResponse record = new HandshakeResponse();
            record.match = this.fieldSetFlags()[0] ? this.match : (HandshakeMatch)this.defaultValue(this.fields()[0]);
            record.serverProtocol = this.fieldSetFlags()[1] ? this.serverProtocol : (String)this.defaultValue(this.fields()[1]);
            record.serverHash = this.fieldSetFlags()[2] ? this.serverHash : (MD5)this.defaultValue(this.fields()[2]);
            record.meta = this.fieldSetFlags()[3] ? this.meta : (Map)this.defaultValue(this.fields()[3]);
            return record;
         } catch (AvroMissingFieldException e) {
            throw e;
         } catch (Exception e) {
            throw new AvroRuntimeException(e);
         }
      }
   }
}

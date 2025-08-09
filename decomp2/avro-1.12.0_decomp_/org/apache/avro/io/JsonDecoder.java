package org.apache.avro.io;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;
import org.apache.avro.io.parsing.JsonGrammarGenerator;
import org.apache.avro.io.parsing.Parser;
import org.apache.avro.io.parsing.Symbol;
import org.apache.avro.util.Utf8;

public class JsonDecoder extends ParsingDecoder implements Parser.ActionHandler {
   private JsonParser in;
   private static JsonFactory jsonFactory = new JsonFactory();
   Stack reorderBuffers;
   ReorderBuffer currentReorderBuffer;

   private JsonDecoder(Symbol root, InputStream in) throws IOException {
      super(root);
      this.reorderBuffers = new Stack();
      this.configure(in);
   }

   private JsonDecoder(Symbol root, String in) throws IOException {
      super(root);
      this.reorderBuffers = new Stack();
      this.configure(in);
   }

   JsonDecoder(Schema schema, InputStream in) throws IOException {
      this(getSymbol(schema), in);
   }

   JsonDecoder(Schema schema, String in) throws IOException {
      this(getSymbol(schema), in);
   }

   private static Symbol getSymbol(Schema schema) {
      Objects.requireNonNull(schema, "Schema cannot be null");
      return (new JsonGrammarGenerator()).generate(schema);
   }

   public JsonDecoder configure(InputStream in) throws IOException {
      Objects.requireNonNull(in, "InputStream cannot be null");
      this.parser.reset();
      this.reorderBuffers.clear();
      this.currentReorderBuffer = null;
      this.in = jsonFactory.createParser(in);
      this.in.nextToken();
      return this;
   }

   public JsonDecoder configure(String in) throws IOException {
      Objects.requireNonNull(in, "String to read from cannot be null");
      this.parser.reset();
      this.reorderBuffers.clear();
      this.currentReorderBuffer = null;
      this.in = (new JsonFactory()).createParser(in);
      this.in.nextToken();
      return this;
   }

   private void advance(Symbol symbol) throws IOException {
      this.parser.processTrailingImplicitActions();
      if (this.in.getCurrentToken() == null && this.parser.depth() == 1) {
         throw new EOFException();
      } else {
         this.parser.advance(symbol);
      }
   }

   public void readNull() throws IOException {
      this.advance(Symbol.NULL);
      if (this.in.getCurrentToken() == JsonToken.VALUE_NULL) {
         this.in.nextToken();
      } else {
         throw this.error("null");
      }
   }

   public boolean readBoolean() throws IOException {
      this.advance(Symbol.BOOLEAN);
      JsonToken t = this.in.getCurrentToken();
      if (t != JsonToken.VALUE_TRUE && t != JsonToken.VALUE_FALSE) {
         throw this.error("boolean");
      } else {
         this.in.nextToken();
         return t == JsonToken.VALUE_TRUE;
      }
   }

   public int readInt() throws IOException {
      this.advance(Symbol.INT);
      if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
         int result = this.in.getIntValue();
         this.in.nextToken();
         return result;
      } else {
         if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
            float value = this.in.getFloatValue();
            if (Math.abs(value - (float)Math.round(value)) <= Float.MIN_VALUE) {
               int result = Math.round(value);
               this.in.nextToken();
               return result;
            }
         }

         throw this.error("int");
      }
   }

   public long readLong() throws IOException {
      this.advance(Symbol.LONG);
      if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
         long result = this.in.getLongValue();
         this.in.nextToken();
         return result;
      } else {
         if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
            double value = this.in.getDoubleValue();
            if (Math.abs(value - (double)Math.round(value)) <= Double.MIN_VALUE) {
               long result = Math.round(value);
               this.in.nextToken();
               return result;
            }
         }

         throw this.error("long");
      }
   }

   public float readFloat() throws IOException {
      this.advance(Symbol.FLOAT);
      if (this.in.getCurrentToken().isNumeric()) {
         float result = this.in.getFloatValue();
         this.in.nextToken();
         return result;
      } else {
         throw this.error("float");
      }
   }

   public double readDouble() throws IOException {
      this.advance(Symbol.DOUBLE);
      if (this.in.getCurrentToken().isNumeric()) {
         double result = this.in.getDoubleValue();
         this.in.nextToken();
         return result;
      } else {
         throw this.error("double");
      }
   }

   public Utf8 readString(Utf8 old) throws IOException {
      return new Utf8(this.readString());
   }

   public String readString() throws IOException {
      this.advance(Symbol.STRING);
      if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
         this.parser.advance(Symbol.MAP_KEY_MARKER);
         if (this.in.getCurrentToken() != JsonToken.FIELD_NAME) {
            throw this.error("map-key");
         }
      } else if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
         throw this.error("string");
      }

      String result = this.in.getText();
      this.in.nextToken();
      return result;
   }

   public void skipString() throws IOException {
      this.advance(Symbol.STRING);
      if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
         this.parser.advance(Symbol.MAP_KEY_MARKER);
         if (this.in.getCurrentToken() != JsonToken.FIELD_NAME) {
            throw this.error("map-key");
         }
      } else if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
         throw this.error("string");
      }

      this.in.nextToken();
   }

   public ByteBuffer readBytes(ByteBuffer old) throws IOException {
      this.advance(Symbol.BYTES);
      if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
         byte[] result = this.readByteArray();
         this.in.nextToken();
         return ByteBuffer.wrap(result);
      } else {
         throw this.error("bytes");
      }
   }

   private byte[] readByteArray() throws IOException {
      byte[] result = this.in.getText().getBytes(StandardCharsets.ISO_8859_1);
      return result;
   }

   public void skipBytes() throws IOException {
      this.advance(Symbol.BYTES);
      if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
         this.in.nextToken();
      } else {
         throw this.error("bytes");
      }
   }

   private void checkFixed(int size) throws IOException {
      this.advance(Symbol.FIXED);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      if (size != top.size) {
         throw new AvroTypeException("Incorrect length for fixed binary: expected " + top.size + " but received " + size + " bytes.");
      }
   }

   public void readFixed(byte[] bytes, int start, int len) throws IOException {
      this.checkFixed(len);
      if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
         byte[] result = this.readByteArray();
         this.in.nextToken();
         if (result.length != len) {
            throw new AvroTypeException("Expected fixed length " + len + ", but got" + result.length);
         } else {
            System.arraycopy(result, 0, bytes, start, len);
         }
      } else {
         throw this.error("fixed");
      }
   }

   public void skipFixed(int length) throws IOException {
      this.checkFixed(length);
      this.doSkipFixed(length);
   }

   private void doSkipFixed(int length) throws IOException {
      if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
         byte[] result = this.readByteArray();
         this.in.nextToken();
         if (result.length != length) {
            throw new AvroTypeException("Expected fixed length " + length + ", but got" + result.length);
         }
      } else {
         throw this.error("fixed");
      }
   }

   protected void skipFixed() throws IOException {
      this.advance(Symbol.FIXED);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      this.doSkipFixed(top.size);
   }

   public int readEnum() throws IOException {
      this.advance(Symbol.ENUM);
      Symbol.EnumLabelsAction top = (Symbol.EnumLabelsAction)this.parser.popSymbol();
      if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
         this.in.getText();
         int n = top.findLabel(this.in.getText());
         if (n >= 0) {
            this.in.nextToken();
            return n;
         } else {
            throw new AvroTypeException("Unknown symbol in enum " + this.in.getText());
         }
      } else {
         throw this.error("fixed");
      }
   }

   public long readArrayStart() throws IOException {
      this.advance(Symbol.ARRAY_START);
      if (this.in.getCurrentToken() == JsonToken.START_ARRAY) {
         this.in.nextToken();
         return this.doArrayNext();
      } else {
         throw this.error("array-start");
      }
   }

   public long arrayNext() throws IOException {
      this.advance(Symbol.ITEM_END);
      return this.doArrayNext();
   }

   private long doArrayNext() throws IOException {
      if (this.in.getCurrentToken() == JsonToken.END_ARRAY) {
         this.parser.advance(Symbol.ARRAY_END);
         this.in.nextToken();
         return 0L;
      } else {
         return 1L;
      }
   }

   public long skipArray() throws IOException {
      this.advance(Symbol.ARRAY_START);
      if (this.in.getCurrentToken() == JsonToken.START_ARRAY) {
         this.in.skipChildren();
         this.in.nextToken();
         this.advance(Symbol.ARRAY_END);
         return 0L;
      } else {
         throw this.error("array-start");
      }
   }

   public long readMapStart() throws IOException {
      this.advance(Symbol.MAP_START);
      if (this.in.getCurrentToken() == JsonToken.START_OBJECT) {
         this.in.nextToken();
         return this.doMapNext();
      } else {
         throw this.error("map-start");
      }
   }

   public long mapNext() throws IOException {
      this.advance(Symbol.ITEM_END);
      return this.doMapNext();
   }

   private long doMapNext() throws IOException {
      if (this.in.getCurrentToken() == JsonToken.END_OBJECT) {
         this.in.nextToken();
         this.advance(Symbol.MAP_END);
         return 0L;
      } else {
         return 1L;
      }
   }

   public long skipMap() throws IOException {
      this.advance(Symbol.MAP_START);
      if (this.in.getCurrentToken() == JsonToken.START_OBJECT) {
         this.in.skipChildren();
         this.in.nextToken();
         this.advance(Symbol.MAP_END);
         return 0L;
      } else {
         throw this.error("map-start");
      }
   }

   public int readIndex() throws IOException {
      this.advance(Symbol.UNION);
      Symbol.Alternative a = (Symbol.Alternative)this.parser.popSymbol();
      String label;
      if (this.in.getCurrentToken() == JsonToken.VALUE_NULL) {
         label = "null";
      } else {
         if (this.in.getCurrentToken() != JsonToken.START_OBJECT || this.in.nextToken() != JsonToken.FIELD_NAME) {
            throw this.error("start-union");
         }

         label = this.in.getText();
         this.in.nextToken();
         this.parser.pushSymbol(Symbol.UNION_END);
      }

      int n = a.findLabel(label);
      if (n < 0) {
         throw new AvroTypeException("Unknown union branch " + label);
      } else {
         this.parser.pushSymbol(a.getSymbol(n));
         return n;
      }
   }

   public Symbol doAction(Symbol input, Symbol top) throws IOException {
      if (!(top instanceof Symbol.FieldAdjustAction)) {
         if (top == Symbol.FIELD_END) {
            if (this.currentReorderBuffer != null && this.currentReorderBuffer.origParser != null) {
               this.in = this.currentReorderBuffer.origParser;
               this.currentReorderBuffer.origParser = null;
            }
         } else if (top == Symbol.RECORD_START) {
            if (this.in.getCurrentToken() != JsonToken.START_OBJECT) {
               throw this.error("record-start");
            }

            this.in.nextToken();
            this.reorderBuffers.push(this.currentReorderBuffer);
            this.currentReorderBuffer = null;
         } else {
            if (top != Symbol.RECORD_END && top != Symbol.UNION_END) {
               throw new AvroTypeException("Unknown action symbol " + String.valueOf(top));
            }

            while(this.in.getCurrentToken() != JsonToken.END_OBJECT) {
               this.in.nextToken();
            }

            if (top == Symbol.RECORD_END) {
               if (this.currentReorderBuffer != null && !this.currentReorderBuffer.savedFields.isEmpty()) {
                  throw this.error("Unknown fields: " + String.valueOf(this.currentReorderBuffer.savedFields.keySet()));
               }

               this.currentReorderBuffer = (ReorderBuffer)this.reorderBuffers.pop();
            }

            this.in.nextToken();
         }
      } else {
         TokenBuffer tokenBuffer;
         Object var14;
         label140: {
            Symbol.FieldAdjustAction fa = (Symbol.FieldAdjustAction)top;
            String name = fa.fname;
            if (this.currentReorderBuffer != null) {
               tokenBuffer = (TokenBuffer)this.currentReorderBuffer.savedFields.get(name);

               try {
                  if (tokenBuffer != null) {
                     this.currentReorderBuffer.savedFields.remove(name);
                     this.currentReorderBuffer.origParser = this.in;
                     this.in = tokenBuffer.asParser();
                     this.in.nextToken();
                     var14 = null;
                     break label140;
                  }
               } catch (Throwable var12) {
                  if (tokenBuffer != null) {
                     try {
                        tokenBuffer.close();
                     } catch (Throwable var10) {
                        var12.addSuppressed(var10);
                     }
                  }

                  throw var12;
               }

               if (tokenBuffer != null) {
                  tokenBuffer.close();
               }
            }

            if (this.in.getCurrentToken() == JsonToken.FIELD_NAME) {
               do {
                  String fn = this.in.getText();
                  this.in.nextToken();
                  if (name.equals(fn) || fa.aliases.contains(fn)) {
                     return null;
                  }

                  if (this.currentReorderBuffer == null) {
                     this.currentReorderBuffer = new ReorderBuffer();
                  }

                  TokenBuffer tokenBuffer = new TokenBuffer(this.in);

                  try {
                     tokenBuffer.copyCurrentStructure(this.in);
                     this.currentReorderBuffer.savedFields.put(fn, tokenBuffer);
                  } catch (Throwable var11) {
                     try {
                        tokenBuffer.close();
                     } catch (Throwable var9) {
                        var11.addSuppressed(var9);
                     }

                     throw var11;
                  }

                  tokenBuffer.close();
                  this.in.nextToken();
               } while(this.in.getCurrentToken() == JsonToken.FIELD_NAME);

               throw new AvroTypeException("Expected field name not found: " + fa.fname);
            }

            return null;
         }

         if (tokenBuffer != null) {
            tokenBuffer.close();
         }

         return (Symbol)var14;
      }

      return null;
   }

   private AvroTypeException error(String type) {
      return new AvroTypeException("Expected " + type + ". Got " + String.valueOf(this.in.getCurrentToken()));
   }

   private static class ReorderBuffer {
      public Map savedFields = new HashMap();
      public JsonParser origParser = null;
   }
}

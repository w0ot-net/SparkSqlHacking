package org.apache.avro.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;
import org.apache.avro.io.parsing.Parser;
import org.apache.avro.io.parsing.Symbol;
import org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import org.apache.avro.util.Utf8;

public class ValidatingDecoder extends ParsingDecoder implements Parser.ActionHandler {
   protected Decoder in;

   ValidatingDecoder(Symbol root, Decoder in) throws IOException {
      super(root);
      this.configure(in);
   }

   ValidatingDecoder(Schema schema, Decoder in) throws IOException {
      this(getSymbol(schema), in);
   }

   private static Symbol getSymbol(Schema schema) {
      Objects.requireNonNull(schema, "Schema cannot be null");
      return (new ValidatingGrammarGenerator()).generate(schema);
   }

   public ValidatingDecoder configure(Decoder in) throws IOException {
      this.parser.reset();
      this.in = in;
      return this;
   }

   public void readNull() throws IOException {
      this.parser.advance(Symbol.NULL);
      this.in.readNull();
   }

   public boolean readBoolean() throws IOException {
      this.parser.advance(Symbol.BOOLEAN);
      return this.in.readBoolean();
   }

   public int readInt() throws IOException {
      this.parser.advance(Symbol.INT);
      return this.in.readInt();
   }

   public long readLong() throws IOException {
      this.parser.advance(Symbol.LONG);
      return this.in.readLong();
   }

   public float readFloat() throws IOException {
      this.parser.advance(Symbol.FLOAT);
      return this.in.readFloat();
   }

   public double readDouble() throws IOException {
      this.parser.advance(Symbol.DOUBLE);
      return this.in.readDouble();
   }

   public Utf8 readString(Utf8 old) throws IOException {
      this.parser.advance(Symbol.STRING);
      return this.in.readString(old);
   }

   public String readString() throws IOException {
      this.parser.advance(Symbol.STRING);
      return this.in.readString();
   }

   public void skipString() throws IOException {
      this.parser.advance(Symbol.STRING);
      this.in.skipString();
   }

   public ByteBuffer readBytes(ByteBuffer old) throws IOException {
      this.parser.advance(Symbol.BYTES);
      return this.in.readBytes(old);
   }

   public void skipBytes() throws IOException {
      this.parser.advance(Symbol.BYTES);
      this.in.skipBytes();
   }

   private void checkFixed(int size) throws IOException {
      this.parser.advance(Symbol.FIXED);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      if (size != top.size) {
         throw new AvroTypeException("Incorrect length for fixed binary: expected " + top.size + " but received " + size + " bytes.");
      }
   }

   public void readFixed(byte[] bytes, int start, int len) throws IOException {
      this.checkFixed(len);
      this.in.readFixed(bytes, start, len);
   }

   public void skipFixed(int length) throws IOException {
      this.checkFixed(length);
      this.in.skipFixed(length);
   }

   protected void skipFixed() throws IOException {
      this.parser.advance(Symbol.FIXED);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      this.in.skipFixed(top.size);
   }

   public int readEnum() throws IOException {
      this.parser.advance(Symbol.ENUM);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      int result = this.in.readEnum();
      if (result >= 0 && result < top.size) {
         return result;
      } else {
         throw new AvroTypeException("Enumeration out of range: max is " + top.size + " but received " + result);
      }
   }

   public long readArrayStart() throws IOException {
      this.parser.advance(Symbol.ARRAY_START);
      long result = this.in.readArrayStart();
      if (result == 0L) {
         this.parser.advance(Symbol.ARRAY_END);
      }

      return result;
   }

   public long arrayNext() throws IOException {
      this.parser.processTrailingImplicitActions();
      long result = this.in.arrayNext();
      if (result == 0L) {
         this.parser.advance(Symbol.ARRAY_END);
      }

      return result;
   }

   public long skipArray() throws IOException {
      this.parser.advance(Symbol.ARRAY_START);

      for(long c = this.in.skipArray(); c != 0L; c = this.in.skipArray()) {
         while(c-- > 0L) {
            this.parser.skipRepeater();
         }
      }

      this.parser.advance(Symbol.ARRAY_END);
      return 0L;
   }

   public long readMapStart() throws IOException {
      this.parser.advance(Symbol.MAP_START);
      long result = this.in.readMapStart();
      if (result == 0L) {
         this.parser.advance(Symbol.MAP_END);
      }

      return result;
   }

   public long mapNext() throws IOException {
      this.parser.processTrailingImplicitActions();
      long result = this.in.mapNext();
      if (result == 0L) {
         this.parser.advance(Symbol.MAP_END);
      }

      return result;
   }

   public long skipMap() throws IOException {
      this.parser.advance(Symbol.MAP_START);

      for(long c = this.in.skipMap(); c != 0L; c = this.in.skipMap()) {
         while(c-- > 0L) {
            this.parser.skipRepeater();
         }
      }

      this.parser.advance(Symbol.MAP_END);
      return 0L;
   }

   public int readIndex() throws IOException {
      this.parser.advance(Symbol.UNION);
      Symbol.Alternative top = (Symbol.Alternative)this.parser.popSymbol();
      int result = this.in.readIndex();
      this.parser.pushSymbol(top.getSymbol(result));
      return result;
   }

   public Symbol doAction(Symbol input, Symbol top) throws IOException {
      return null;
   }
}

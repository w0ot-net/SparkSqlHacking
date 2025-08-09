package org.apache.avro.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;
import org.apache.avro.io.parsing.Parser;
import org.apache.avro.io.parsing.Symbol;
import org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import org.apache.avro.util.Utf8;

public class ValidatingEncoder extends ParsingEncoder implements Parser.ActionHandler {
   protected Encoder out;
   protected final Parser parser;

   ValidatingEncoder(Symbol root, Encoder out) throws IOException {
      this.out = out;
      this.parser = new Parser(root, this);
   }

   ValidatingEncoder(Schema schema, Encoder in) throws IOException {
      this((new ValidatingGrammarGenerator()).generate(schema), in);
   }

   public void flush() throws IOException {
      this.out.flush();
   }

   public ValidatingEncoder configure(Encoder encoder) {
      this.parser.reset();
      this.out = encoder;
      return this;
   }

   public void writeNull() throws IOException {
      this.parser.advance(Symbol.NULL);
      this.out.writeNull();
   }

   public void writeBoolean(boolean b) throws IOException {
      this.parser.advance(Symbol.BOOLEAN);
      this.out.writeBoolean(b);
   }

   public void writeInt(int n) throws IOException {
      this.parser.advance(Symbol.INT);
      this.out.writeInt(n);
   }

   public void writeLong(long n) throws IOException {
      this.parser.advance(Symbol.LONG);
      this.out.writeLong(n);
   }

   public void writeFloat(float f) throws IOException {
      this.parser.advance(Symbol.FLOAT);
      this.out.writeFloat(f);
   }

   public void writeDouble(double d) throws IOException {
      this.parser.advance(Symbol.DOUBLE);
      this.out.writeDouble(d);
   }

   public void writeString(Utf8 utf8) throws IOException {
      this.parser.advance(Symbol.STRING);
      this.out.writeString(utf8);
   }

   public void writeString(String str) throws IOException {
      this.parser.advance(Symbol.STRING);
      this.out.writeString(str);
   }

   public void writeString(CharSequence charSequence) throws IOException {
      this.parser.advance(Symbol.STRING);
      this.out.writeString(charSequence);
   }

   public void writeBytes(ByteBuffer bytes) throws IOException {
      this.parser.advance(Symbol.BYTES);
      this.out.writeBytes(bytes);
   }

   public void writeBytes(byte[] bytes, int start, int len) throws IOException {
      this.parser.advance(Symbol.BYTES);
      this.out.writeBytes(bytes, start, len);
   }

   public void writeFixed(byte[] bytes, int start, int len) throws IOException {
      this.parser.advance(Symbol.FIXED);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      if (len != top.size) {
         throw new AvroTypeException("Incorrect length for fixed binary: expected " + top.size + " but received " + len + " bytes.");
      } else {
         this.out.writeFixed(bytes, start, len);
      }
   }

   public void writeEnum(int e) throws IOException {
      this.parser.advance(Symbol.ENUM);
      Symbol.IntCheckAction top = (Symbol.IntCheckAction)this.parser.popSymbol();
      if (e >= 0 && e < top.size) {
         this.out.writeEnum(e);
      } else {
         throw new AvroTypeException("Enumeration out of range: max is " + top.size + " but received " + e);
      }
   }

   public void writeArrayStart() throws IOException {
      this.push();
      this.parser.advance(Symbol.ARRAY_START);
      this.out.writeArrayStart();
   }

   public void writeArrayEnd() throws IOException {
      this.parser.advance(Symbol.ARRAY_END);
      this.out.writeArrayEnd();
      this.pop();
   }

   public void writeMapStart() throws IOException {
      this.push();
      this.parser.advance(Symbol.MAP_START);
      this.out.writeMapStart();
   }

   public void writeMapEnd() throws IOException {
      this.parser.advance(Symbol.MAP_END);
      this.out.writeMapEnd();
      this.pop();
   }

   public void setItemCount(long itemCount) throws IOException {
      super.setItemCount(itemCount);
      this.out.setItemCount(itemCount);
   }

   public void startItem() throws IOException {
      super.startItem();
      this.out.startItem();
   }

   public void writeIndex(int unionIndex) throws IOException {
      this.parser.advance(Symbol.UNION);
      Symbol.Alternative top = (Symbol.Alternative)this.parser.popSymbol();
      this.parser.pushSymbol(top.getSymbol(unionIndex));
      this.out.writeIndex(unionIndex);
   }

   public Symbol doAction(Symbol input, Symbol top) throws IOException {
      return null;
   }
}

package io.vertx.core.parsetools;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.impl.RecordParserImpl;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface RecordParser extends Handler, ReadStream {
   void setOutput(Handler var1);

   static RecordParser newDelimited(String delim, Handler output) {
      return RecordParserImpl.newDelimited((String)delim, (ReadStream)null, output);
   }

   static RecordParser newDelimited(String delim, ReadStream stream) {
      return RecordParserImpl.newDelimited((String)delim, stream, (Handler)null);
   }

   static RecordParser newDelimited(String delim) {
      return RecordParserImpl.newDelimited((String)delim, (ReadStream)null, (Handler)null);
   }

   static RecordParser newDelimited(Buffer delim) {
      return RecordParserImpl.newDelimited((Buffer)delim, (ReadStream)null, (Handler)null);
   }

   static RecordParser newDelimited(Buffer delim, Handler output) {
      return RecordParserImpl.newDelimited((Buffer)delim, (ReadStream)null, output);
   }

   static RecordParser newDelimited(Buffer delim, ReadStream stream) {
      return RecordParserImpl.newDelimited((Buffer)delim, stream, (Handler)null);
   }

   static RecordParser newFixed(int size) {
      return RecordParserImpl.newFixed(size, (ReadStream)null, (Handler)null);
   }

   static RecordParser newFixed(int size, Handler output) {
      return RecordParserImpl.newFixed(size, (ReadStream)null, output);
   }

   static RecordParser newFixed(int size, ReadStream stream) {
      return RecordParserImpl.newFixed(size, stream, (Handler)null);
   }

   void delimitedMode(String var1);

   void delimitedMode(Buffer var1);

   void fixedSizeMode(int var1);

   @Fluent
   RecordParser maxRecordSize(int var1);

   void handle(Buffer var1);

   RecordParser exceptionHandler(Handler var1);

   RecordParser handler(Handler var1);

   RecordParser pause();

   RecordParser fetch(long var1);

   RecordParser resume();

   RecordParser endHandler(Handler var1);
}

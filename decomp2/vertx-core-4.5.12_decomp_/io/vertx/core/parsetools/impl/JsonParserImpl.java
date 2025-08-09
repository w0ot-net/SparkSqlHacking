package io.vertx.core.parsetools.impl;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.JacksonCodec;
import io.vertx.core.parsetools.JsonEvent;
import io.vertx.core.parsetools.JsonEventType;
import io.vertx.core.parsetools.JsonParser;
import io.vertx.core.streams.ReadStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class JsonParserImpl implements JsonParser {
   private final NonBlockingJsonParser parser;
   private Handler tokenHandler = this::handleEvent;
   private Handler eventHandler;
   private boolean objectValueMode;
   private boolean arrayValueMode;
   private Handler exceptionHandler;
   private String currentField;
   private Handler endHandler;
   private long demand = Long.MAX_VALUE;
   private boolean ended;
   private final ReadStream stream;
   private boolean emitting;
   private final Deque pending = new ArrayDeque();
   private List collectedExceptions;

   public JsonParserImpl(ReadStream stream) {
      this.stream = stream;
      JsonFactory factory = new JsonFactory();

      try {
         this.parser = (NonBlockingJsonParser)factory.createNonBlockingByteArrayParser();
      } catch (Exception e) {
         throw new VertxException(e);
      }
   }

   public JsonParser pause() {
      this.demand = 0L;
      return this;
   }

   public JsonParser resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public JsonParser fetch(long amount) {
      Arguments.require(amount > 0L, "Fetch amount must be > 0L");
      this.demand += amount;
      if (this.demand < 0L) {
         this.demand = Long.MAX_VALUE;
      }

      this.checkPending();
      return this;
   }

   public JsonParser endHandler(Handler handler) {
      if (this.pending.size() > 0 || !this.ended) {
         this.endHandler = handler;
      }

      return this;
   }

   public JsonParser handler(Handler handler) {
      this.eventHandler = handler;
      if (this.stream != null) {
         if (handler != null) {
            this.stream.endHandler((v) -> this.end());
            this.stream.exceptionHandler((err) -> {
               if (this.exceptionHandler != null) {
                  this.exceptionHandler.handle(err);
               }

            });
            this.stream.handler(this);
         } else {
            this.stream.handler((Handler)null);
            this.stream.endHandler((Handler)null);
            this.stream.exceptionHandler((Handler)null);
         }
      }

      return this;
   }

   private void handleEvent(JsonEventImpl event) {
      if (event.type() == JsonEventType.START_OBJECT && this.objectValueMode) {
         BufferingHandler handler = new BufferingHandler();
         handler.handler = (buffer) -> {
            this.tokenHandler = this::handleEvent;
            this.handleEvent(new JsonEventImpl((JsonToken)null, JsonEventType.VALUE, event.fieldName(), new JsonObject((Map)handler.convert(Map.class))));
         };
         this.tokenHandler = handler;
         handler.handle(new JsonEventImpl(JsonToken.START_OBJECT, JsonEventType.START_OBJECT, (String)null, (Object)null));
      } else if (event.type() == JsonEventType.START_ARRAY && this.arrayValueMode) {
         BufferingHandler handler = new BufferingHandler();
         handler.handler = (buffer) -> {
            this.tokenHandler = this::handleEvent;
            this.handleEvent(new JsonEventImpl((JsonToken)null, JsonEventType.VALUE, event.fieldName(), new JsonArray((List)handler.convert(List.class))));
         };
         this.tokenHandler = handler;
         handler.handle(new JsonEventImpl(JsonToken.START_ARRAY, JsonEventType.START_ARRAY, (String)null, (Object)null));
      } else {
         if (this.demand != Long.MAX_VALUE) {
            --this.demand;
         }

         if (this.eventHandler != null) {
            this.eventHandler.handle(event);
         }
      }

   }

   private void handle(IOException ioe) {
      if (this.collectedExceptions == null) {
         this.collectedExceptions = new ArrayList();
      }

      this.collectedExceptions.add(ioe);
   }

   public void handle(Buffer data) {
      byte[] bytes = data.getBytes();

      try {
         this.parser.feedInput(bytes, 0, bytes.length);
      } catch (IOException e) {
         this.handle(e);
      }

      this.checkTokens();
      this.checkPending();
      this.checkExceptions();
   }

   public void end() {
      if (this.ended) {
         throw new IllegalStateException("Parsing already done");
      } else {
         this.ended = true;
         this.parser.endOfInput();
         this.checkTokens();
         this.checkPending();
         this.checkExceptions();
      }
   }

   private void checkTokens() {
      JsonLocation prevLocation = null;

      while(true) {
         JsonToken token;
         while(true) {
            try {
               token = this.parser.nextToken();
               break;
            } catch (IOException e) {
               JsonLocation location = this.parser.currentLocation();
               if (prevLocation != null && location.equals(prevLocation)) {
                  return;
               }

               prevLocation = location;
               this.handle(e);
            }
         }

         if (token == null || token == JsonToken.NOT_AVAILABLE) {
            return;
         }

         prevLocation = null;
         String field = this.currentField;
         this.currentField = null;
         JsonEventImpl event;
         switch (token) {
            case START_OBJECT:
               event = new JsonEventImpl(token, JsonEventType.START_OBJECT, field, (Object)null);
               break;
            case START_ARRAY:
               event = new JsonEventImpl(token, JsonEventType.START_ARRAY, field, (Object)null);
               break;
            case FIELD_NAME:
               try {
                  this.currentField = this.parser.getCurrentName();
               } catch (IOException e) {
                  this.handle(e);
               }
               continue;
            case VALUE_STRING:
               try {
                  event = new JsonEventImpl(token, JsonEventType.VALUE, field, this.parser.getText());
                  break;
               } catch (IOException e) {
                  this.handle(e);
                  continue;
               }
            case VALUE_TRUE:
               event = new JsonEventImpl(token, JsonEventType.VALUE, field, Boolean.TRUE);
               break;
            case VALUE_FALSE:
               event = new JsonEventImpl(token, JsonEventType.VALUE, field, Boolean.FALSE);
               break;
            case VALUE_NULL:
               event = new JsonEventImpl(token, JsonEventType.VALUE, field, (Object)null);
               break;
            case VALUE_NUMBER_INT:
               try {
                  event = new JsonEventImpl(token, JsonEventType.VALUE, field, this.parser.getLongValue());
                  break;
               } catch (IOException e) {
                  this.handle(e);
                  continue;
               }
            case VALUE_NUMBER_FLOAT:
               try {
                  event = new JsonEventImpl(token, JsonEventType.VALUE, field, this.parser.getDoubleValue());
                  break;
               } catch (IOException e) {
                  this.handle(e);
                  continue;
               }
            case END_OBJECT:
               event = new JsonEventImpl(token, JsonEventType.END_OBJECT, (String)null, (Object)null);
               break;
            case END_ARRAY:
               event = new JsonEventImpl(token, JsonEventType.END_ARRAY, (String)null, (Object)null);
               break;
            default:
               throw new UnsupportedOperationException("Token " + token + " not implemented");
         }

         this.pending.add(event);
      }
   }

   private void checkPending() {
      if (!this.emitting) {
         this.emitting = true;

         try {
            while(this.demand > 0L) {
               JsonEventImpl currentToken = (JsonEventImpl)this.pending.poll();
               if (currentToken == null) {
                  break;
               }

               this.tokenHandler.handle(currentToken);
            }

            if (this.ended) {
               if (this.pending.isEmpty()) {
                  this.checkExceptions();
                  Handler<Void> handler = this.endHandler;
                  this.endHandler = null;
                  if (handler != null) {
                     handler.handle((Object)null);
                  }
               }
            } else if (this.demand == 0L) {
               if (this.stream != null) {
                  this.stream.pause();
               }
            } else if (this.stream != null) {
               this.stream.resume();
            }
         } catch (Exception e) {
            if (this.exceptionHandler == null) {
               throw e;
            }

            this.exceptionHandler.handle(e);
         } finally {
            this.emitting = false;
         }
      }

   }

   private void checkExceptions() {
      List<IOException> exceptions = this.collectedExceptions;
      this.collectedExceptions = null;
      if (exceptions != null && exceptions.size() > 0) {
         if (this.exceptionHandler == null) {
            IOException ioe = (IOException)exceptions.get(0);
            throw new DecodeException(ioe.getMessage(), ioe);
         }

         for(IOException ioe : exceptions) {
            this.exceptionHandler.handle(ioe);
         }
      }

   }

   public JsonParser objectEventMode() {
      this.objectValueMode = false;
      return this;
   }

   public JsonParser objectValueMode() {
      this.objectValueMode = true;
      return this;
   }

   public JsonParser arrayEventMode() {
      this.arrayValueMode = false;
      return this;
   }

   public JsonParser arrayValueMode() {
      this.arrayValueMode = true;
      return this;
   }

   public JsonParser write(Buffer buffer) {
      this.handle(buffer);
      return this;
   }

   public JsonParser exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   private static class TokenParser extends ParserBase {
      private ArrayDeque tokens;
      private String text;

      private TokenParser(IOContext ctxt, int features) {
         super(ctxt, features);
         this.tokens = new ArrayDeque();
      }

      public JsonToken nextToken() throws IOException {
         if (this.tokens.isEmpty()) {
            return null;
         } else {
            this.text = null;
            this._numTypesValid = 0;
            this._numberLong = 0L;
            this._numberDouble = (double)0.0F;
            this._currToken = (JsonToken)this.tokens.removeFirst();
            if (this._currToken == JsonToken.FIELD_NAME) {
               String field = (String)this.tokens.removeFirst();
               this._parsingContext.setCurrentName(field);
               this.text = field;
            } else if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
               Long v = (Long)this.tokens.removeFirst();
               this._numTypesValid = 2;
               this._numberLong = v;
            } else if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
               Double v = (Double)this.tokens.removeFirst();
               this._numTypesValid = 8;
               this._numberDouble = v;
            } else if (this._currToken == JsonToken.VALUE_STRING) {
               this.text = (String)this.tokens.removeFirst();
            }

            return this._currToken;
         }
      }

      public String getText() {
         return this.text;
      }

      public char[] getTextCharacters() {
         throw new UnsupportedOperationException();
      }

      public int getTextLength() {
         throw new UnsupportedOperationException();
      }

      public int getTextOffset() {
         throw new UnsupportedOperationException();
      }

      public ObjectCodec getCodec() {
         throw new UnsupportedOperationException();
      }

      public void setCodec(ObjectCodec c) {
         throw new UnsupportedOperationException();
      }

      protected void _closeInput() {
      }
   }

   private class BufferingHandler implements Handler {
      Handler handler;
      int depth;
      TokenParser buffer;

      private BufferingHandler() {
      }

      public void handle(JsonEventImpl event) {
         String fieldName = event.fieldName();
         if (fieldName != null) {
            this.buffer.tokens.add(JsonToken.FIELD_NAME);
            this.buffer.tokens.add(fieldName);
         }

         try {
            switch (event.type()) {
               case START_OBJECT:
               case START_ARRAY:
                  if (this.depth++ == 0) {
                     JsonFactory factory = new JsonFactory();
                     this.buffer = new TokenParser(new IOContext(factory._getBufferRecycler(), this, true), Feature.collectDefaults());
                  }

                  this.buffer.tokens.add(event.token());
                  break;
               case VALUE:
                  JsonToken token = event.token();
                  this.buffer.tokens.add(token);
                  if (token != JsonToken.VALUE_FALSE && token != JsonToken.VALUE_TRUE && token != JsonToken.VALUE_NULL) {
                     this.buffer.tokens.add(event.value());
                  }
                  break;
               case END_OBJECT:
               case END_ARRAY:
                  this.buffer.tokens.add(event.token());
                  if (--this.depth == 0) {
                     this.handler.handle((Object)null);
                     this.buffer.close();
                     this.buffer = null;
                  }
                  break;
               default:
                  throw new UnsupportedOperationException("Not implemented " + event);
            }

         } catch (IOException e) {
            throw new VertxException(e);
         }
      }

      Object convert(Class type) {
         return JacksonCodec.fromParser(this.buffer, type);
      }
   }
}

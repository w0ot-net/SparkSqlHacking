package io.vertx.core.eventbus.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.impl.codecs.BooleanMessageCodec;
import io.vertx.core.eventbus.impl.codecs.BufferMessageCodec;
import io.vertx.core.eventbus.impl.codecs.ByteArrayMessageCodec;
import io.vertx.core.eventbus.impl.codecs.ByteMessageCodec;
import io.vertx.core.eventbus.impl.codecs.CharMessageCodec;
import io.vertx.core.eventbus.impl.codecs.ClusterSerializableCodec;
import io.vertx.core.eventbus.impl.codecs.DoubleMessageCodec;
import io.vertx.core.eventbus.impl.codecs.FloatMessageCodec;
import io.vertx.core.eventbus.impl.codecs.IntMessageCodec;
import io.vertx.core.eventbus.impl.codecs.JsonArrayMessageCodec;
import io.vertx.core.eventbus.impl.codecs.JsonObjectMessageCodec;
import io.vertx.core.eventbus.impl.codecs.LongMessageCodec;
import io.vertx.core.eventbus.impl.codecs.NullMessageCodec;
import io.vertx.core.eventbus.impl.codecs.PingMessageCodec;
import io.vertx.core.eventbus.impl.codecs.ReplyExceptionMessageCodec;
import io.vertx.core.eventbus.impl.codecs.SerializableCodec;
import io.vertx.core.eventbus.impl.codecs.ShortMessageCodec;
import io.vertx.core.eventbus.impl.codecs.StringMessageCodec;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class CodecManager {
   public static final MessageCodec PING_MESSAGE_CODEC = new PingMessageCodec();
   public static final MessageCodec NULL_MESSAGE_CODEC = new NullMessageCodec();
   public static final MessageCodec STRING_MESSAGE_CODEC = new StringMessageCodec();
   public static final MessageCodec BUFFER_MESSAGE_CODEC = new BufferMessageCodec();
   public static final MessageCodec JSON_OBJECT_MESSAGE_CODEC = new JsonObjectMessageCodec();
   public static final MessageCodec JSON_ARRAY_MESSAGE_CODEC = new JsonArrayMessageCodec();
   public static final MessageCodec BYTE_ARRAY_MESSAGE_CODEC = new ByteArrayMessageCodec();
   public static final MessageCodec INT_MESSAGE_CODEC = new IntMessageCodec();
   public static final MessageCodec LONG_MESSAGE_CODEC = new LongMessageCodec();
   public static final MessageCodec FLOAT_MESSAGE_CODEC = new FloatMessageCodec();
   public static final MessageCodec DOUBLE_MESSAGE_CODEC = new DoubleMessageCodec();
   public static final MessageCodec BOOLEAN_MESSAGE_CODEC = new BooleanMessageCodec();
   public static final MessageCodec SHORT_MESSAGE_CODEC = new ShortMessageCodec();
   public static final MessageCodec CHAR_MESSAGE_CODEC = new CharMessageCodec();
   public static final MessageCodec BYTE_MESSAGE_CODEC = new ByteMessageCodec();
   public static final MessageCodec REPLY_EXCEPTION_MESSAGE_CODEC = new ReplyExceptionMessageCodec();
   private final MessageCodec[] systemCodecs;
   private final ConcurrentMap userCodecMap = new ConcurrentHashMap();
   private final ConcurrentMap defaultCodecMap = new ConcurrentHashMap();
   private final ClusterSerializableCodec clusterSerializableCodec = new ClusterSerializableCodec(this);
   private final SerializableCodec serializableCodec = new SerializableCodec(this);
   private volatile Function clusterSerializableCheck = (s) -> Boolean.FALSE;
   private volatile Function serializableCheck;
   private volatile Function codecSelector;

   public CodecManager() {
      this.serializableCheck = EventBus.DEFAULT_SERIALIZABLE_CHECKER;
      this.codecSelector = (o) -> null;
      this.systemCodecs = this.codecs(NULL_MESSAGE_CODEC, PING_MESSAGE_CODEC, STRING_MESSAGE_CODEC, BUFFER_MESSAGE_CODEC, JSON_OBJECT_MESSAGE_CODEC, JSON_ARRAY_MESSAGE_CODEC, BYTE_ARRAY_MESSAGE_CODEC, INT_MESSAGE_CODEC, LONG_MESSAGE_CODEC, FLOAT_MESSAGE_CODEC, DOUBLE_MESSAGE_CODEC, BOOLEAN_MESSAGE_CODEC, SHORT_MESSAGE_CODEC, CHAR_MESSAGE_CODEC, BYTE_MESSAGE_CODEC, REPLY_EXCEPTION_MESSAGE_CODEC, this.clusterSerializableCodec, this.serializableCodec);
   }

   public MessageCodec lookupCodec(Object body, String codecName, boolean local) {
      MessageCodec codec;
      if (codecName != null) {
         codec = this.getCodec(codecName);
      } else if (body == null) {
         codec = NULL_MESSAGE_CODEC;
      } else if (body instanceof String) {
         codec = STRING_MESSAGE_CODEC;
      } else if (body instanceof Buffer) {
         codec = BUFFER_MESSAGE_CODEC;
      } else if (body instanceof JsonObject) {
         codec = JSON_OBJECT_MESSAGE_CODEC;
      } else if (body instanceof JsonArray) {
         codec = JSON_ARRAY_MESSAGE_CODEC;
      } else if (body instanceof byte[]) {
         codec = BYTE_ARRAY_MESSAGE_CODEC;
      } else if (body instanceof Integer) {
         codec = INT_MESSAGE_CODEC;
      } else if (body instanceof Long) {
         codec = LONG_MESSAGE_CODEC;
      } else if (body instanceof Float) {
         codec = FLOAT_MESSAGE_CODEC;
      } else if (body instanceof Double) {
         codec = DOUBLE_MESSAGE_CODEC;
      } else if (body instanceof Boolean) {
         codec = BOOLEAN_MESSAGE_CODEC;
      } else if (body instanceof Short) {
         codec = SHORT_MESSAGE_CODEC;
      } else if (body instanceof Character) {
         codec = CHAR_MESSAGE_CODEC;
      } else if (body instanceof Byte) {
         codec = BYTE_MESSAGE_CODEC;
      } else if (body instanceof ReplyException) {
         codec = (MessageCodec)this.defaultCodecMap.get(body.getClass());
         if (codec == null) {
            codec = REPLY_EXCEPTION_MESSAGE_CODEC;
         }
      } else {
         codec = (MessageCodec)this.defaultCodecMap.get(body.getClass());
         if (codec == null) {
            if ((codecName = (String)this.codecSelector.apply(body)) != null) {
               codec = this.getCodec(codecName);
            } else if (!(body instanceof ClusterSerializable) || !local && !this.acceptClusterSerializable(body.getClass().getName())) {
               if (body instanceof Serializable && (local || this.acceptSerializable(body.getClass().getName()))) {
                  codec = this.serializableCodec;
               }
            } else {
               codec = this.clusterSerializableCodec;
            }
         }
      }

      if (codec == null) {
         throw new IllegalArgumentException("No message codec for type: " + body.getClass());
      } else {
         return codec;
      }
   }

   public MessageCodec getCodec(String codecName) {
      return (MessageCodec)this.userCodecMap.get(codecName);
   }

   public void registerCodec(MessageCodec codec) {
      Objects.requireNonNull(codec, "codec");
      Objects.requireNonNull(codec.name(), "code.name()");
      this.checkSystemCodec(codec);
      if (this.userCodecMap.containsKey(codec.name())) {
         throw new IllegalStateException("Already a codec registered with name " + codec.name());
      } else {
         this.userCodecMap.put(codec.name(), codec);
      }
   }

   public void unregisterCodec(String name) {
      Objects.requireNonNull(name);
      this.userCodecMap.remove(name);
   }

   public void registerDefaultCodec(Class clazz, MessageCodec codec) {
      Objects.requireNonNull(clazz);
      Objects.requireNonNull(codec, "codec");
      Objects.requireNonNull(codec.name(), "code.name()");
      this.checkSystemCodec(codec);
      if (this.defaultCodecMap.containsKey(clazz)) {
         throw new IllegalStateException("Already a default codec registered for class " + clazz);
      } else if (this.userCodecMap.containsKey(codec.name())) {
         throw new IllegalStateException("Already a codec registered with name " + codec.name());
      } else {
         this.defaultCodecMap.put(clazz, codec);
         this.userCodecMap.put(codec.name(), codec);
      }
   }

   public void unregisterDefaultCodec(Class clazz) {
      Objects.requireNonNull(clazz);
      MessageCodec codec = (MessageCodec)this.defaultCodecMap.remove(clazz);
      if (codec != null) {
         this.userCodecMap.remove(codec.name());
      }

   }

   public MessageCodec[] systemCodecs() {
      return this.systemCodecs;
   }

   private void checkSystemCodec(MessageCodec codec) {
      if (codec.systemCodecID() != -1) {
         throw new IllegalArgumentException("Can't register a system codec");
      }
   }

   private MessageCodec[] codecs(MessageCodec... codecs) {
      MessageCodec[] arr = new MessageCodec[codecs.length];

      for(MessageCodec codec : codecs) {
         arr[codec.systemCodecID()] = codec;
      }

      return arr;
   }

   public void clusterSerializableCheck(Function classNamePredicate) {
      this.clusterSerializableCheck = (Function)Objects.requireNonNull(classNamePredicate);
   }

   public boolean acceptClusterSerializable(String className) {
      return (Boolean)this.clusterSerializableCheck.apply(className);
   }

   public void serializableCheck(Function classNamePredicate) {
      this.serializableCheck = (Function)Objects.requireNonNull(classNamePredicate);
   }

   public boolean acceptSerializable(String className) {
      return (Boolean)this.serializableCheck.apply(className);
   }

   public void codecSelector(Function selector) {
      this.codecSelector = (Function)Objects.requireNonNull(selector);
   }
}

package org.apache.spark.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.spark.annotation.Private;
import org.apache.spark.unsafe.Platform;
import scala.reflect.ClassTag;

@Private
public final class DummySerializerInstance extends SerializerInstance {
   public static final DummySerializerInstance INSTANCE = new DummySerializerInstance();

   private DummySerializerInstance() {
   }

   public SerializationStream serializeStream(final OutputStream s) {
      return new SerializationStream() {
         public void flush() {
            try {
               s.flush();
            } catch (IOException e) {
               Platform.throwException(e);
            }

         }

         public SerializationStream writeObject(Object t, ClassTag ev1) {
            throw new UnsupportedOperationException();
         }

         public void close() {
            try {
               s.close();
            } catch (IOException e) {
               Platform.throwException(e);
            }

         }
      };
   }

   public ByteBuffer serialize(Object t, ClassTag ev1) {
      throw new UnsupportedOperationException();
   }

   public DeserializationStream deserializeStream(InputStream s) {
      throw new UnsupportedOperationException();
   }

   public Object deserialize(ByteBuffer bytes, ClassLoader loader, ClassTag ev1) {
      throw new UnsupportedOperationException();
   }

   public Object deserialize(ByteBuffer bytes, ClassTag ev1) {
      throw new UnsupportedOperationException();
   }
}

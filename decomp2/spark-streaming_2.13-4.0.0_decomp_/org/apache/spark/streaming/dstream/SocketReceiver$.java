package org.apache.spark.streaming.dstream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import org.apache.spark.util.NextIterator;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;

public final class SocketReceiver$ implements Serializable {
   public static final SocketReceiver$ MODULE$ = new SocketReceiver$();

   public Iterator bytesToLines(final InputStream inputStream) {
      BufferedReader dataInputStream = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
      return new NextIterator(dataInputStream) {
         private final BufferedReader dataInputStream$1;

         public String getNext() {
            String nextValue = this.dataInputStream$1.readLine();
            if (nextValue == null) {
               this.finished_$eq(true);
            }

            return nextValue;
         }

         public void close() {
            this.dataInputStream$1.close();
         }

         public {
            this.dataInputStream$1 = dataInputStream$1;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SocketReceiver$.class);
   }

   private SocketReceiver$() {
   }
}

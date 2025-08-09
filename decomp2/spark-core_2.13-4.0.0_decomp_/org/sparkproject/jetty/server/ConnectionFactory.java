package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import java.util.List;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;

public interface ConnectionFactory {
   String getProtocol();

   List getProtocols();

   Connection newConnection(Connector var1, EndPoint var2);

   public interface Configuring extends ConnectionFactory {
      void configure(Connector var1);
   }

   public interface Detecting extends ConnectionFactory {
      Detection detect(ByteBuffer var1);

      public static enum Detection {
         RECOGNIZED,
         NOT_RECOGNIZED,
         NEED_MORE_BYTES;

         // $FF: synthetic method
         private static Detection[] $values() {
            return new Detection[]{RECOGNIZED, NOT_RECOGNIZED, NEED_MORE_BYTES};
         }
      }
   }

   public interface Upgrading extends ConnectionFactory {
      Connection upgradeConnection(Connector var1, EndPoint var2, MetaData.Request var3, HttpFields.Mutable var4) throws BadMessageException;
   }
}

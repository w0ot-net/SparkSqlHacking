package org.sparkproject.jetty.io;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.EventListener;

public interface Connection extends Closeable {
   void addEventListener(EventListener var1);

   void removeEventListener(EventListener var1);

   void onOpen();

   void onClose(Throwable var1);

   EndPoint getEndPoint();

   void close();

   boolean onIdleExpired();

   long getMessagesIn();

   long getMessagesOut();

   long getBytesIn();

   long getBytesOut();

   long getCreatedTimeStamp();

   public interface Listener extends EventListener {
      void onOpened(Connection var1);

      void onClosed(Connection var1);

      public static class Adapter implements Listener {
         public void onOpened(Connection connection) {
         }

         public void onClosed(Connection connection) {
         }
      }
   }

   public interface UpgradeFrom {
      ByteBuffer onUpgradeFrom();
   }

   public interface UpgradeTo {
      void onUpgradeTo(ByteBuffer var1);
   }
}

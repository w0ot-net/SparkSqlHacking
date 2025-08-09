package org.apache.logging.log4j.core.layout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/** @deprecated */
@Deprecated
@Plugin(
   name = "SerializedLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class SerializedLayout extends AbstractLayout {
   private static byte[] serializedHeader;

   private SerializedLayout() {
      super((Configuration)null, (byte[])null, (byte[])null);
      LOGGER.warn("SerializedLayout is deprecated due to the inherent security weakness in Java Serialization, see https://www.owasp.org/index.php/Deserialization_of_untrusted_data Consider using another layout, e.g. JsonLayout");
   }

   public byte[] toByteArray(final LogEvent event) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      try {
         ObjectOutputStream oos = new PrivateObjectOutputStream(baos);

         try {
            oos.writeObject(event);
            oos.reset();
         } catch (Throwable var7) {
            try {
               oos.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         oos.close();
      } catch (IOException ioe) {
         LOGGER.error("Serialization of LogEvent failed.", ioe);
      }

      return baos.toByteArray();
   }

   public LogEvent toSerializable(final LogEvent event) {
      return event;
   }

   /** @deprecated */
   @Deprecated
   @PluginFactory
   public static SerializedLayout createLayout() {
      return new SerializedLayout();
   }

   public byte[] getHeader() {
      return serializedHeader;
   }

   public String getContentType() {
      return "application/octet-stream";
   }

   static {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      try {
         (new ObjectOutputStream(baos)).close();
         serializedHeader = baos.toByteArray();
      } catch (Exception ex) {
         LOGGER.error("Unable to generate Object stream header", ex);
      }

   }

   private class PrivateObjectOutputStream extends ObjectOutputStream {
      public PrivateObjectOutputStream(final OutputStream os) throws IOException {
         super(os);
      }

      protected void writeStreamHeader() {
      }
   }
}

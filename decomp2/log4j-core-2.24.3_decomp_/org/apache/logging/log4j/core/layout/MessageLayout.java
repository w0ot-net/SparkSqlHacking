package org.apache.logging.log4j.core.layout;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.message.Message;

@Plugin(
   name = "MessageLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public class MessageLayout extends AbstractLayout {
   public MessageLayout() {
      super((Configuration)null, (byte[])null, (byte[])null);
   }

   public MessageLayout(final Configuration configuration, final byte[] header, final byte[] footer) {
      super(configuration, header, footer);
   }

   public byte[] toByteArray(final LogEvent event) {
      return null;
   }

   public Message toSerializable(final LogEvent event) {
      return event.getMessage();
   }

   public String getContentType() {
      return null;
   }

   @PluginFactory
   public static Layout createLayout() {
      return new MessageLayout();
   }
}

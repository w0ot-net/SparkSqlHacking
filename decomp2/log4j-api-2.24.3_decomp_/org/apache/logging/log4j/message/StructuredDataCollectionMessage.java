package org.apache.logging.log4j.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public class StructuredDataCollectionMessage implements StringBuilderFormattable, MessageCollectionMessage {
   private static final long serialVersionUID = 5725337076388822924L;
   private final List structuredDataMessageList;

   public StructuredDataCollectionMessage(final List messages) {
      this.structuredDataMessageList = messages;
   }

   public Iterator iterator() {
      return this.structuredDataMessageList.iterator();
   }

   public String getFormattedMessage() {
      StringBuilder sb = new StringBuilder();
      this.formatTo(sb);
      return sb.toString();
   }

   public String getFormat() {
      StringBuilder sb = new StringBuilder();

      for(StructuredDataMessage msg : this.structuredDataMessageList) {
         if (msg.getFormat() != null) {
            if (sb.length() > 0) {
               sb.append(", ");
            }

            sb.append(msg.getFormat());
         }
      }

      return sb.toString();
   }

   public void formatTo(final StringBuilder buffer) {
      for(StructuredDataMessage msg : this.structuredDataMessageList) {
         msg.formatTo(buffer);
      }

   }

   public Object[] getParameters() {
      List<Object[]> objectList = new ArrayList();
      int count = 0;

      for(StructuredDataMessage msg : this.structuredDataMessageList) {
         Object[] objects = msg.getParameters();
         if (objects != null) {
            objectList.add(objects);
            count += objects.length;
         }
      }

      Object[] objects = new Object[count];
      int index = 0;

      for(Object[] objs : objectList) {
         for(Object obj : objs) {
            objects[index++] = obj;
         }
      }

      return objects;
   }

   public Throwable getThrowable() {
      for(StructuredDataMessage msg : this.structuredDataMessageList) {
         Throwable t = msg.getThrowable();
         if (t != null) {
            return t;
         }
      }

      return null;
   }
}

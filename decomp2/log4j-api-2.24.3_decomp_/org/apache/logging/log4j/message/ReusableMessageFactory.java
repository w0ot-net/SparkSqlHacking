package org.apache.logging.log4j.message;

import java.io.Serializable;
import org.apache.logging.log4j.util.PerformanceSensitive;

@PerformanceSensitive({"allocation"})
public final class ReusableMessageFactory implements MessageFactory2, Serializable {
   public static final ReusableMessageFactory INSTANCE = new ReusableMessageFactory();
   private static final long serialVersionUID = 1L;
   private final transient ThreadLocal threadLocalParameterized = new ThreadLocal();
   private final transient ThreadLocal threadLocalSimpleMessage = new ThreadLocal();
   private final transient ThreadLocal threadLocalObjectMessage = new ThreadLocal();

   private ReusableParameterizedMessage getParameterized() {
      ReusableParameterizedMessage result = (ReusableParameterizedMessage)this.threadLocalParameterized.get();
      if (result == null) {
         result = new ReusableParameterizedMessage();
         this.threadLocalParameterized.set(result);
      }

      return result.reserved ? (new ReusableParameterizedMessage()).reserve() : result.reserve();
   }

   private ReusableSimpleMessage getSimple() {
      ReusableSimpleMessage result = (ReusableSimpleMessage)this.threadLocalSimpleMessage.get();
      if (result == null) {
         result = new ReusableSimpleMessage();
         this.threadLocalSimpleMessage.set(result);
      }

      return result;
   }

   private ReusableObjectMessage getObject() {
      ReusableObjectMessage result = (ReusableObjectMessage)this.threadLocalObjectMessage.get();
      if (result == null) {
         result = new ReusableObjectMessage();
         this.threadLocalObjectMessage.set(result);
      }

      return result;
   }

   public static void release(final Message message) {
      if (message instanceof Clearable) {
         ((Clearable)message).clear();
      }

   }

   public Message newMessage(final CharSequence charSequence) {
      ReusableSimpleMessage result = this.getSimple();
      result.set(charSequence);
      return result;
   }

   public Message newMessage(final String message, final Object... params) {
      return this.getParameterized().set(message, params);
   }

   public Message newMessage(final String message, final Object p0) {
      return this.getParameterized().set(message, p0);
   }

   public Message newMessage(final String message, final Object p0, final Object p1) {
      return this.getParameterized().set(message, p0, p1);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2) {
      return this.getParameterized().set(message, p0, p1, p2);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      return this.getParameterized().set(message, p0, p1, p2, p3);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return this.getParameterized().set(message, p0, p1, p2, p3, p4);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return this.getParameterized().set(message, p0, p1, p2, p3, p4, p5);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return this.getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return this.getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return this.getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return this.getParameterized().set(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public Message newMessage(final String message) {
      ReusableSimpleMessage result = this.getSimple();
      result.set(message);
      return result;
   }

   public Message newMessage(final Object message) {
      ReusableObjectMessage result = this.getObject();
      result.set(message);
      return result;
   }

   private Object writeReplace() {
      return new SerializationProxy();
   }

   private static class SerializationProxy implements Serializable {
      private static final long serialVersionUID = 1L;

      private SerializationProxy() {
      }

      private Object readResolve() {
         return ReusableMessageFactory.INSTANCE;
      }
   }
}

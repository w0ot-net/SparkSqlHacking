package org.apache.logging.log4j.message;

public final class ParameterizedMessageFactory extends AbstractMessageFactory {
   public static final ParameterizedMessageFactory INSTANCE = new ParameterizedMessageFactory();
   private static final long serialVersionUID = -8970940216592525651L;

   public Message newMessage(final String message, final Object... params) {
      return new ParameterizedMessage(message, params);
   }

   public Message newMessage(final String message, final Object p0) {
      return new ParameterizedMessage(message, p0);
   }

   public Message newMessage(final String message, final Object p0, final Object p1) {
      return new ParameterizedMessage(message, p0, p1);
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3, p4});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3, p4, p5});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3, p4, p5, p6});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3, p4, p5, p6, p7});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8});
   }

   public Message newMessage(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return new ParameterizedMessage(message, new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8, p9});
   }
}

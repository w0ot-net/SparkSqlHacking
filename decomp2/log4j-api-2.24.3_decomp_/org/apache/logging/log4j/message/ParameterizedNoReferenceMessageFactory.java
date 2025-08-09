package org.apache.logging.log4j.message;

public final class ParameterizedNoReferenceMessageFactory extends AbstractMessageFactory {
   private static final long serialVersionUID = 5027639245636870500L;
   public static final ParameterizedNoReferenceMessageFactory INSTANCE = new ParameterizedNoReferenceMessageFactory();

   public Message newMessage(final String message, final Object... params) {
      if (params == null) {
         return new SimpleMessage(message);
      } else {
         ParameterizedMessage msg = new ParameterizedMessage(message, params);
         return new StatusMessage(msg.getFormattedMessage(), msg.getThrowable());
      }
   }

   static class StatusMessage implements Message {
      private static final long serialVersionUID = 4199272162767841280L;
      private final String formattedMessage;
      private final Throwable throwable;

      public StatusMessage(final String formattedMessage, final Throwable throwable) {
         this.formattedMessage = formattedMessage;
         this.throwable = throwable;
      }

      public String getFormattedMessage() {
         return this.formattedMessage;
      }

      public String getFormat() {
         return this.formattedMessage;
      }

      public Object[] getParameters() {
         return null;
      }

      public Throwable getThrowable() {
         return this.throwable;
      }
   }
}

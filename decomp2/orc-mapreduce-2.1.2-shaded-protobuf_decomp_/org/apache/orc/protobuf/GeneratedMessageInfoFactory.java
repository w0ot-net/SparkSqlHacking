package org.apache.orc.protobuf;

class GeneratedMessageInfoFactory implements MessageInfoFactory {
   private static final GeneratedMessageInfoFactory instance = new GeneratedMessageInfoFactory();

   private GeneratedMessageInfoFactory() {
   }

   public static GeneratedMessageInfoFactory getInstance() {
      return instance;
   }

   public boolean isSupported(Class messageType) {
      return GeneratedMessageLite.class.isAssignableFrom(messageType);
   }

   public MessageInfo messageInfoFor(Class messageType) {
      if (!GeneratedMessageLite.class.isAssignableFrom(messageType)) {
         throw new IllegalArgumentException("Unsupported message type: " + messageType.getName());
      } else {
         try {
            return (MessageInfo)GeneratedMessageLite.getDefaultInstance(messageType.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
         } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for " + messageType.getName(), e);
         }
      }
   }
}

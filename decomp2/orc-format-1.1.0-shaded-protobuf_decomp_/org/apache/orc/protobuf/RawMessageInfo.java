package org.apache.orc.protobuf;

@CheckReturnValue
final class RawMessageInfo implements MessageInfo {
   private static final int IS_PROTO2_BIT = 1;
   private static final int IS_EDITION_BIT = 4;
   private final MessageLite defaultInstance;
   private final String info;
   private final Object[] objects;
   private final int flags;

   RawMessageInfo(MessageLite defaultInstance, String info, Object[] objects) {
      this.defaultInstance = defaultInstance;
      this.info = info;
      this.objects = objects;
      int position = 0;
      int value = info.charAt(position++);
      if (value < 55296) {
         this.flags = value;
      } else {
         int result = value & 8191;

         int shift;
         for(shift = 13; (value = info.charAt(position++)) >= 55296; shift += 13) {
            result |= (value & 8191) << shift;
         }

         this.flags = result | value << shift;
      }

   }

   String getStringInfo() {
      return this.info;
   }

   Object[] getObjects() {
      return this.objects;
   }

   public MessageLite getDefaultInstance() {
      return this.defaultInstance;
   }

   public ProtoSyntax getSyntax() {
      if ((this.flags & 1) != 0) {
         return ProtoSyntax.PROTO2;
      } else {
         return (this.flags & 4) == 4 ? ProtoSyntax.EDITIONS : ProtoSyntax.PROTO3;
      }
   }

   public boolean isMessageSetWireFormat() {
      return (this.flags & 2) == 2;
   }
}

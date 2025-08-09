package org.sparkproject.spark_core.protobuf;

public final class DiscardUnknownFieldsParser {
   public static final Parser wrap(final Parser parser) {
      return new AbstractParser() {
         public Message parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Message var3;
            try {
               input.discardUnknownFields();
               var3 = (Message)parser.parsePartialFrom(input, extensionRegistry);
            } finally {
               input.unsetDiscardUnknownFields();
            }

            return var3;
         }
      };
   }

   private DiscardUnknownFieldsParser() {
   }
}

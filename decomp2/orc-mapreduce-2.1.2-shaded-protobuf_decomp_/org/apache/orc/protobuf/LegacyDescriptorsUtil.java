package org.apache.orc.protobuf;

public final class LegacyDescriptorsUtil {
   private LegacyDescriptorsUtil() {
   }

   public static final class LegacyFileDescriptor {
      public static Syntax getSyntax(Descriptors.FileDescriptor descriptor) {
         switch (descriptor.getSyntax()) {
            case UNKNOWN:
               return LegacyDescriptorsUtil.LegacyFileDescriptor.Syntax.UNKNOWN;
            case PROTO2:
               return LegacyDescriptorsUtil.LegacyFileDescriptor.Syntax.PROTO2;
            case PROTO3:
               return LegacyDescriptorsUtil.LegacyFileDescriptor.Syntax.PROTO3;
            default:
               throw new IllegalArgumentException("Unexpected syntax");
         }
      }

      private LegacyFileDescriptor() {
      }

      public static enum Syntax {
         UNKNOWN("unknown"),
         PROTO2("proto2"),
         PROTO3("proto3");

         final String name;

         private Syntax(String name) {
            this.name = name;
         }
      }
   }

   public static final class LegacyFieldDescriptor {
      public static boolean hasOptionalKeyword(Descriptors.FieldDescriptor descriptor) {
         return descriptor.hasOptionalKeyword();
      }

      private LegacyFieldDescriptor() {
      }
   }

   public static final class LegacyOneofDescriptor {
      public static boolean isSynthetic(Descriptors.OneofDescriptor descriptor) {
         return descriptor.isSynthetic();
      }

      private LegacyOneofDescriptor() {
      }
   }
}

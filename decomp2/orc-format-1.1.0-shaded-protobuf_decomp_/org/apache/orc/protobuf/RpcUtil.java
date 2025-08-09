package org.apache.orc.protobuf;

public final class RpcUtil {
   private RpcUtil() {
   }

   public static RpcCallback specializeCallback(final RpcCallback originalCallback) {
      return originalCallback;
   }

   public static RpcCallback generalizeCallback(final RpcCallback originalCallback, final Class originalClass, final Message defaultInstance) {
      return new RpcCallback() {
         public void run(final Message parameter) {
            Type typedParameter;
            try {
               typedParameter = (Type)((Message)originalClass.cast(parameter));
            } catch (ClassCastException var4) {
               typedParameter = (Type)RpcUtil.copyAsType(defaultInstance, parameter);
            }

            originalCallback.run(typedParameter);
         }
      };
   }

   private static Message copyAsType(final Message typeDefaultInstance, final Message source) {
      return typeDefaultInstance.newBuilderForType().mergeFrom(source).build();
   }

   public static RpcCallback newOneTimeCallback(final RpcCallback originalCallback) {
      return new RpcCallback() {
         private boolean alreadyCalled = false;

         public void run(final Object parameter) {
            synchronized(this) {
               if (this.alreadyCalled) {
                  throw new AlreadyCalledException();
               }

               this.alreadyCalled = true;
            }

            originalCallback.run(parameter);
         }
      };
   }

   public static final class AlreadyCalledException extends RuntimeException {
      private static final long serialVersionUID = 5469741279507848266L;

      public AlreadyCalledException() {
         super("This RpcCallback was already called and cannot be called multiple times.");
      }
   }
}

package io.fabric8.kubernetes.client.informers;

public interface SharedInformerEventListener {
   /** @deprecated */
   @Deprecated
   void onException(Exception var1);

   default void onException(SharedIndexInformer informer, Exception e) {
      this.onException(e);
   }
}

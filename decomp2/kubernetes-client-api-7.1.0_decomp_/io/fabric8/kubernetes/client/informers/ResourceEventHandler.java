package io.fabric8.kubernetes.client.informers;

public interface ResourceEventHandler {
   default void onNothing() {
   }

   void onAdd(Object var1);

   void onUpdate(Object var1, Object var2);

   void onDelete(Object var1, boolean var2);
}

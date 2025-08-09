package io.fabric8.kubernetes.client.informers;

import java.util.concurrent.Future;

public interface SharedInformerFactory {
   /** @deprecated */
   @Deprecated
   SharedInformerFactory inNamespace(String var1);

   /** @deprecated */
   @Deprecated
   SharedInformerFactory withName(String var1);

   SharedIndexInformer sharedIndexInformerFor(Class var1, long var2);

   SharedIndexInformer getExistingSharedIndexInformer(Class var1);

   Future startAllRegisteredInformers();

   void stopAllRegisteredInformers();

   /** @deprecated */
   @Deprecated
   void addSharedInformerEventListener(SharedInformerEventListener var1);
}

package org.sparkproject.jetty.client;

import java.util.EventListener;
import org.sparkproject.jetty.client.api.ContentProvider;

/** @deprecated */
@Deprecated
public interface AsyncContentProvider extends ContentProvider {
   void setListener(Listener var1);

   public interface Listener extends EventListener {
      void onContent();
   }
}

package org.apache.ivy.core.event;

import java.util.Arrays;
import javax.swing.event.EventListenerList;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.repository.TransferEvent;
import org.apache.ivy.plugins.repository.TransferListener;
import org.apache.ivy.util.filter.Filter;

public class EventManager implements TransferListener {
   private EventListenerList listeners = new EventListenerList();

   public void addIvyListener(IvyListener listener) {
      this.listeners.add(IvyListener.class, listener);
   }

   public void addIvyListener(IvyListener listener, String eventName) {
      this.addIvyListener(listener, (Filter)(new IvyEventFilter(eventName, (String)null, (PatternMatcher)null)));
   }

   public void addIvyListener(IvyListener listener, Filter filter) {
      this.listeners.add(IvyListener.class, new FilteredIvyListener(listener, filter));
   }

   public void removeIvyListener(IvyListener listener) {
      this.listeners.remove(IvyListener.class, listener);
      IvyListener[] listeners = (IvyListener[])this.listeners.getListeners(IvyListener.class);

      for(IvyListener listen : listeners) {
         if (listen instanceof FilteredIvyListener && listener.equals(((FilteredIvyListener)listen).getIvyListener())) {
            this.listeners.remove(IvyListener.class, listen);
         }
      }

   }

   public boolean hasIvyListener(IvyListener listener) {
      IvyListener[] listeners = (IvyListener[])this.listeners.getListeners(IvyListener.class);

      for(IvyListener listen : listeners) {
         if (listen instanceof FilteredIvyListener && listener.equals(((FilteredIvyListener)listen).getIvyListener())) {
            return true;
         }
      }

      return false;
   }

   public void fireIvyEvent(IvyEvent evt) {
      Object[] listeners = this.listeners.getListenerList();

      for(int i = listeners.length - 2; i >= 0; i -= 2) {
         if (listeners[i] == IvyListener.class) {
            ((IvyListener)listeners[i + 1]).progress(evt);
         }
      }

   }

   public void addTransferListener(TransferListener listener) {
      this.listeners.add(TransferListener.class, listener);
   }

   public void removeTransferListener(TransferListener listener) {
      this.listeners.remove(TransferListener.class, listener);
   }

   public boolean hasTransferListener(TransferListener listener) {
      return Arrays.asList(this.listeners.getListeners(TransferListener.class)).contains(listener);
   }

   protected void fireTransferEvent(TransferEvent evt) {
      Object[] listeners = this.listeners.getListenerList();

      for(int i = listeners.length - 2; i >= 0; i -= 2) {
         if (listeners[i] == TransferListener.class) {
            ((TransferListener)listeners[i + 1]).transferProgress(evt);
         }
      }

   }

   public void transferProgress(TransferEvent evt) {
      this.fireTransferEvent(evt);
      this.fireIvyEvent(evt);
   }
}

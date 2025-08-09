package org.apache.commons.compress.harmony.pack200;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Pack200Adapter {
   protected static final int DEFAULT_BUFFER_SIZE = 8192;
   private final PropertyChangeSupport support = new PropertyChangeSupport(this);
   private final SortedMap properties = new TreeMap();

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.support.addPropertyChangeListener(listener);
   }

   protected void completed(double value) throws IOException {
      this.firePropertyChange("pack.progress", (Object)null, String.valueOf((int)((double)100.0F * value)));
   }

   protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) throws IOException {
      this.support.firePropertyChange(propertyName, oldValue, newValue);
   }

   public SortedMap properties() {
      return this.properties;
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.support.removePropertyChangeListener(listener);
   }
}

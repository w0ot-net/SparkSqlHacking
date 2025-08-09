package org.sparkproject.jetty.util.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

public interface Container {
   boolean addBean(Object var1);

   boolean addBean(Object var1, boolean var2);

   Collection getBeans();

   Collection getBeans(Class var1);

   default Collection getCachedBeans(Class clazz) {
      return this.getBeans(clazz);
   }

   Object getBean(Class var1);

   boolean removeBean(Object var1);

   boolean addEventListener(EventListener var1);

   boolean removeEventListener(EventListener var1);

   void unmanage(Object var1);

   void manage(Object var1);

   boolean isManaged(Object var1);

   Collection getContainedBeans(Class var1);

   default List getEventListeners() {
      return Collections.unmodifiableList(new ArrayList(this.getBeans(EventListener.class)));
   }

   static boolean addBean(Object parent, Object child) {
      return parent instanceof Container ? ((Container)parent).addBean(child) : false;
   }

   static boolean addBean(Object parent, Object child, boolean managed) {
      return parent instanceof Container ? ((Container)parent).addBean(child, managed) : false;
   }

   static boolean removeBean(Object parent, Object child) {
      return parent instanceof Container ? ((Container)parent).removeBean(child) : false;
   }

   public interface InheritedListener extends Listener {
   }

   public interface Listener extends EventListener {
      void beanAdded(Container var1, Object var2);

      void beanRemoved(Container var1, Object var2);
   }
}

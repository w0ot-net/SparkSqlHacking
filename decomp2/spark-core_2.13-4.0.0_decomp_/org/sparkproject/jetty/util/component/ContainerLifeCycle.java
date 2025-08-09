package org.sparkproject.jetty.util.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;

@ManagedObject("Implementation of Container and LifeCycle")
public class ContainerLifeCycle extends AbstractLifeCycle implements Container, Destroyable, Dumpable.DumpableContainer {
   private static final Logger LOG = LoggerFactory.getLogger(ContainerLifeCycle.class);
   private final List _beans = new CopyOnWriteArrayList();
   private final List _listeners = new CopyOnWriteArrayList();
   private boolean _doStarted;
   private boolean _destroyed;

   protected void doStart() throws Exception {
      if (this._destroyed) {
         throw new IllegalStateException("Destroyed container cannot be restarted");
      } else {
         this._doStarted = true;

         try {
            for(Bean b : this._beans) {
               if (!this.isStarting()) {
                  break;
               }

               if (b._bean instanceof LifeCycle) {
                  LifeCycle l = (LifeCycle)b._bean;
                  switch (b._managed.ordinal()) {
                     case 1:
                        if (l.isStopped() || l.isFailed()) {
                           this.start(l);
                        }
                        break;
                     case 3:
                        if (l.isStopped()) {
                           this.manage(b);
                           this.start(l);
                        } else {
                           this.unmanage(b);
                        }
                  }
               }
            }

         } catch (Throwable var8) {
            Throwable th = var8;
            List<Bean> reverse = new ArrayList(this._beans);
            Collections.reverse(reverse);

            for(Bean b : reverse) {
               if (b._bean instanceof LifeCycle && b._managed == ContainerLifeCycle.Managed.MANAGED) {
                  LifeCycle l = (LifeCycle)b._bean;
                  if (l.isRunning()) {
                     try {
                        this.stop(l);
                     } catch (Throwable th2) {
                        if (th2 != th) {
                           th.addSuppressed(th2);
                        }
                     }
                  }
               }
            }

            throw th;
         }
      }
   }

   protected void start(LifeCycle l) throws Exception {
      l.start();
   }

   protected void stop(LifeCycle l) throws Exception {
      l.stop();
   }

   protected void doStop() throws Exception {
      this._doStarted = false;
      super.doStop();
      List<Bean> reverse = new ArrayList(this._beans);
      Collections.reverse(reverse);
      MultiException mex = new MultiException();

      for(Bean b : reverse) {
         if (!this.isStopping()) {
            break;
         }

         if (b._managed == ContainerLifeCycle.Managed.MANAGED && b._bean instanceof LifeCycle) {
            LifeCycle l = (LifeCycle)b._bean;

            try {
               this.stop(l);
            } catch (Throwable th) {
               mex.add(th);
            }
         }
      }

      mex.ifExceptionThrow();
   }

   public void destroy() {
      this._destroyed = true;
      List<Bean> reverse = new ArrayList(this._beans);
      Collections.reverse(reverse);

      for(Bean b : reverse) {
         if (b._bean instanceof Destroyable && (b._managed == ContainerLifeCycle.Managed.MANAGED || b._managed == ContainerLifeCycle.Managed.POJO)) {
            Destroyable d = (Destroyable)b._bean;

            try {
               d.destroy();
            } catch (Throwable th) {
               LOG.warn("Unable to destroy", th);
            }
         }
      }

      this._beans.clear();
   }

   public boolean contains(Object bean) {
      for(Bean b : this._beans) {
         if (b._bean == bean) {
            return true;
         }
      }

      return false;
   }

   public boolean isManaged(Object bean) {
      for(Bean b : this._beans) {
         if (b._bean == bean) {
            return b.isManaged();
         }
      }

      return false;
   }

   public boolean isAuto(Object bean) {
      for(Bean b : this._beans) {
         if (b._bean == bean) {
            return b._managed == ContainerLifeCycle.Managed.AUTO;
         }
      }

      return false;
   }

   public boolean isUnmanaged(Object bean) {
      for(Bean b : this._beans) {
         if (b._bean == bean) {
            return b._managed == ContainerLifeCycle.Managed.UNMANAGED;
         }
      }

      return false;
   }

   public boolean addBean(Object o) {
      if (o instanceof LifeCycle) {
         LifeCycle l = (LifeCycle)o;
         return this.addBean(o, l.isRunning() ? ContainerLifeCycle.Managed.UNMANAGED : ContainerLifeCycle.Managed.AUTO);
      } else {
         return this.addBean(o, ContainerLifeCycle.Managed.POJO);
      }
   }

   public boolean addBean(Object o, boolean managed) {
      return o instanceof LifeCycle ? this.addBean(o, managed ? ContainerLifeCycle.Managed.MANAGED : ContainerLifeCycle.Managed.UNMANAGED) : this.addBean(o, managed ? ContainerLifeCycle.Managed.POJO : ContainerLifeCycle.Managed.UNMANAGED);
   }

   private boolean addBean(Object o, Managed managed) {
      if (o != null && !this.contains(o)) {
         Bean newBean = new Bean(o);
         this._beans.add(newBean);

         for(Container.Listener l : this._listeners) {
            l.beanAdded(this, o);
         }

         if (o instanceof EventListener) {
            this.addEventListener((EventListener)o);
         }

         try {
            switch (managed.ordinal()) {
               case 0:
                  newBean._managed = ContainerLifeCycle.Managed.POJO;
                  break;
               case 1:
                  this.manage(newBean);
                  if (this.isStarting() && this._doStarted) {
                     LifeCycle l = (LifeCycle)o;
                     if (!l.isRunning()) {
                        this.start(l);
                     }
                  }
                  break;
               case 2:
                  this.unmanage(newBean);
                  break;
               case 3:
                  if (o instanceof LifeCycle) {
                     LifeCycle l = (LifeCycle)o;
                     if (this.isStarting()) {
                        if (l.isRunning()) {
                           this.unmanage(newBean);
                        } else if (this._doStarted) {
                           this.manage(newBean);
                           this.start(l);
                        } else {
                           newBean._managed = ContainerLifeCycle.Managed.AUTO;
                        }
                     } else if (this.isStarted()) {
                        this.unmanage(newBean);
                     } else {
                        newBean._managed = ContainerLifeCycle.Managed.AUTO;
                     }
                  } else {
                     newBean._managed = ContainerLifeCycle.Managed.POJO;
                  }
                  break;
               default:
                  throw new IllegalStateException(managed.toString());
            }
         } catch (Error | RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("{} added {}", this, newBean);
         }

         return true;
      } else {
         return false;
      }
   }

   public void addManaged(LifeCycle lifecycle) {
      this.addBean(lifecycle, true);

      try {
         if (this.isRunning() && !lifecycle.isRunning()) {
            this.start(lifecycle);
         }

      } catch (Error | RuntimeException e) {
         throw e;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public boolean addEventListener(EventListener listener) {
      if (super.addEventListener(listener)) {
         if (!this.contains(listener)) {
            this.addBean(listener);
         }

         if (listener instanceof Container.Listener) {
            Container.Listener cl = (Container.Listener)listener;
            this._listeners.add(cl);

            for(Bean b : this._beans) {
               cl.beanAdded(this, b._bean);
               if (listener instanceof Container.InheritedListener && b.isManaged() && b._bean instanceof Container) {
                  if (b._bean instanceof ContainerLifeCycle) {
                     Container.addBean(b._bean, listener, false);
                  } else {
                     Container.addBean(b._bean, listener);
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean removeEventListener(EventListener listener) {
      if (!super.removeEventListener(listener)) {
         return false;
      } else {
         this.removeBean(listener);
         if (listener instanceof Container.Listener && this._listeners.remove(listener)) {
            Container.Listener cl = (Container.Listener)listener;

            for(Bean b : this._beans) {
               cl.beanRemoved(this, b._bean);
               if (listener instanceof Container.InheritedListener && b.isManaged()) {
                  Container.removeBean(b._bean, listener);
               }
            }
         }

         return true;
      }
   }

   public void manage(Object bean) {
      for(Bean b : this._beans) {
         if (b._bean == bean) {
            this.manage(b);
            return;
         }
      }

      throw new IllegalArgumentException("Unknown bean " + String.valueOf(bean));
   }

   private void manage(Bean bean) {
      if (bean._managed != ContainerLifeCycle.Managed.MANAGED) {
         bean._managed = ContainerLifeCycle.Managed.MANAGED;
         if (bean._bean instanceof Container) {
            for(Container.Listener l : this._listeners) {
               if (l instanceof Container.InheritedListener) {
                  if (bean._bean instanceof ContainerLifeCycle) {
                     Container.addBean(bean._bean, l, false);
                  } else {
                     Container.addBean(bean._bean, l);
                  }
               }
            }
         }
      }

   }

   public void unmanage(Object bean) {
      for(Bean b : this._beans) {
         if (b._bean == bean) {
            this.unmanage(b);
            return;
         }
      }

      throw new IllegalArgumentException("Unknown bean " + String.valueOf(bean));
   }

   private void unmanage(Bean bean) {
      if (bean._managed != ContainerLifeCycle.Managed.UNMANAGED) {
         if (bean._managed == ContainerLifeCycle.Managed.MANAGED && bean._bean instanceof Container) {
            for(Container.Listener l : this._listeners) {
               if (l instanceof Container.InheritedListener) {
                  Container.removeBean(bean._bean, l);
               }
            }
         }

         bean._managed = ContainerLifeCycle.Managed.UNMANAGED;
      }

   }

   public void setBeans(Collection beans) {
      for(Object bean : beans) {
         this.addBean(bean);
      }

   }

   public Collection getBeans() {
      return this.getBeans(Object.class);
   }

   public Collection getBeans(Class clazz) {
      ArrayList<T> beans = null;

      for(Bean b : this._beans) {
         if (clazz.isInstance(b._bean)) {
            if (beans == null) {
               beans = new ArrayList();
            }

            beans.add(clazz.cast(b._bean));
         }
      }

      return (Collection)(beans == null ? Collections.emptyList() : beans);
   }

   public Object getBean(Class clazz) {
      for(Bean b : this._beans) {
         if (clazz.isInstance(b._bean)) {
            return clazz.cast(b._bean);
         }
      }

      return null;
   }

   private Bean getBean(Object o) {
      for(Bean b : this._beans) {
         if (b._bean == o) {
            return b;
         }
      }

      return null;
   }

   public void removeBeans() {
      for(Bean b : new ArrayList(this._beans)) {
         this.remove(b);
      }

   }

   public boolean removeBean(Object o) {
      Bean b = this.getBean(o);
      return b != null && this.remove(b);
   }

   private boolean remove(Bean bean) {
      if (!this._beans.remove(bean)) {
         return false;
      } else {
         boolean wasManaged = bean.isManaged();
         this.unmanage(bean);

         for(Container.Listener l : this._listeners) {
            l.beanRemoved(this, bean._bean);
         }

         if (bean._bean instanceof EventListener && this.getEventListeners().contains(bean._bean)) {
            this.removeEventListener((EventListener)bean._bean);
         }

         if (wasManaged && bean._bean instanceof LifeCycle) {
            try {
               this.stop((LifeCycle)bean._bean);
            } catch (Error | RuntimeException e) {
               throw e;
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }

         return true;
      }
   }

   @ManagedOperation("Dump the object to stderr")
   public void dumpStdErr() {
      try {
         this.dump(System.err, "");
         System.err.println("key: +- bean, += managed, +~ unmanaged, +? auto, +: iterable, +] array, +@ map, +> undefined");
      } catch (IOException e) {
         LOG.warn("Unable to dump", e);
      }

   }

   @ManagedOperation("Dump the object to a string")
   public String dump() {
      return Dumpable.dump(this);
   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent);
   }

   public void dump(Appendable out) throws IOException {
      this.dump(out, "");
   }

   protected void dumpObjects(Appendable out, String indent, Object... items) throws IOException {
      Dumpable.dumpObjects(out, indent, this, items);
   }

   public void updateBean(Object oldBean, Object newBean) {
      if (newBean != oldBean) {
         if (oldBean != null) {
            this.removeBean(oldBean);
         }

         if (newBean != null) {
            this.addBean(newBean);
         }
      }

   }

   public void updateBean(Object oldBean, Object newBean, boolean managed) {
      if (newBean != oldBean) {
         if (oldBean != null) {
            this.removeBean(oldBean);
         }

         if (newBean != null) {
            this.addBean(newBean, managed);
         }
      }

   }

   public void updateBeans(Object[] oldBeans, Object[] newBeans) {
      this.updateBeans((Collection)(oldBeans == null ? Collections.emptyList() : Arrays.asList(oldBeans)), (Collection)(newBeans == null ? Collections.emptyList() : Arrays.asList(newBeans)));
   }

   public void updateBeans(Collection oldBeans, Collection newBeans) {
      Objects.requireNonNull(oldBeans);
      Objects.requireNonNull(newBeans);

      for(Object o : oldBeans) {
         if (!newBeans.contains(o)) {
            this.removeBean(o);
         }
      }

      for(Object n : newBeans) {
         if (!oldBeans.contains(n)) {
            this.addBean(n);
         }
      }

   }

   public Collection getContainedBeans(Class clazz) {
      Set<T> beans = new HashSet();
      this.getContainedBeans(clazz, beans);
      return beans;
   }

   protected void getContainedBeans(Class clazz, Collection beans) {
      beans.addAll(this.getBeans(clazz));

      for(Container c : this.getBeans(Container.class)) {
         Bean bean = this.getBean((Object)c);
         if (bean != null && bean.isManageable()) {
            if (c instanceof ContainerLifeCycle) {
               ((ContainerLifeCycle)c).getContainedBeans(clazz, beans);
            } else {
               beans.addAll(c.getContainedBeans(clazz));
            }
         }
      }

   }

   static enum Managed {
      POJO,
      MANAGED,
      UNMANAGED,
      AUTO;

      // $FF: synthetic method
      private static Managed[] $values() {
         return new Managed[]{POJO, MANAGED, UNMANAGED, AUTO};
      }
   }

   private static class Bean {
      private final Object _bean;
      private volatile Managed _managed;

      private Bean(Object b) {
         this._managed = ContainerLifeCycle.Managed.POJO;
         if (b == null) {
            throw new NullPointerException();
         } else {
            this._bean = b;
         }
      }

      public boolean isManaged() {
         return this._managed == ContainerLifeCycle.Managed.MANAGED;
      }

      public boolean isManageable() {
         switch (this._managed.ordinal()) {
            case 1:
               return true;
            case 3:
               return this._bean instanceof LifeCycle && ((LifeCycle)this._bean).isStopped();
            default:
               return false;
         }
      }

      public String toString() {
         return String.format("{%s,%s}", this._bean, this._managed);
      }
   }
}

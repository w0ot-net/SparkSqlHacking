package org.apache.commons.lang3.event;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.function.FailableConsumer;

public class EventListenerSupport implements Serializable {
   private static final long serialVersionUID = 3593265990380473632L;
   private List listeners;
   private transient Object proxy;
   private transient Object[] prototypeArray;

   public static EventListenerSupport create(Class listenerInterface) {
      return new EventListenerSupport(listenerInterface);
   }

   private EventListenerSupport() {
      this.listeners = new CopyOnWriteArrayList();
   }

   public EventListenerSupport(Class listenerInterface) {
      this(listenerInterface, Thread.currentThread().getContextClassLoader());
   }

   public EventListenerSupport(Class listenerInterface, ClassLoader classLoader) {
      this();
      Objects.requireNonNull(listenerInterface, "listenerInterface");
      Objects.requireNonNull(classLoader, "classLoader");
      Validate.isTrue(listenerInterface.isInterface(), "Class %s is not an interface", listenerInterface.getName());
      this.initializeTransientFields(listenerInterface, classLoader);
   }

   public void addListener(Object listener) {
      this.addListener(listener, true);
   }

   public void addListener(Object listener, boolean allowDuplicate) {
      Objects.requireNonNull(listener, "listener");
      if (allowDuplicate || !this.listeners.contains(listener)) {
         this.listeners.add(listener);
      }

   }

   protected InvocationHandler createInvocationHandler() {
      return new ProxyInvocationHandler();
   }

   private void createProxy(Class listenerInterface, ClassLoader classLoader) {
      this.proxy = listenerInterface.cast(Proxy.newProxyInstance(classLoader, new Class[]{listenerInterface}, this.createInvocationHandler()));
   }

   public Object fire() {
      return this.proxy;
   }

   int getListenerCount() {
      return this.listeners.size();
   }

   public Object[] getListeners() {
      return this.listeners.toArray(this.prototypeArray);
   }

   private void initializeTransientFields(Class listenerInterface, ClassLoader classLoader) {
      this.prototypeArray = ArrayUtils.newInstance(listenerInterface, 0);
      this.createProxy(listenerInterface, classLoader);
   }

   private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
      L[] srcListeners = (L[])((Object[])objectInputStream.readObject());
      this.listeners = new CopyOnWriteArrayList(srcListeners);
      Class<L> listenerInterface = ArrayUtils.getComponentType(srcListeners);
      this.initializeTransientFields(listenerInterface, Thread.currentThread().getContextClassLoader());
   }

   public void removeListener(Object listener) {
      Objects.requireNonNull(listener, "listener");
      this.listeners.remove(listener);
   }

   private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
      ArrayList<L> serializableListeners = new ArrayList();
      ObjectOutputStream testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());

      for(Object listener : this.listeners) {
         try {
            testObjectOutputStream.writeObject(listener);
            serializableListeners.add(listener);
         } catch (IOException var7) {
            testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
         }
      }

      objectOutputStream.writeObject(serializableListeners.toArray(this.prototypeArray));
   }

   protected class ProxyInvocationHandler implements InvocationHandler {
      private final FailableConsumer handler;

      public ProxyInvocationHandler() {
         this(ExceptionUtils::rethrow);
      }

      public ProxyInvocationHandler(FailableConsumer handler) {
         this.handler = (FailableConsumer)Objects.requireNonNull(handler);
      }

      protected void handle(Throwable t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
         this.handler.accept(t);
      }

      public Object invoke(Object unusedProxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
         for(Object listener : EventListenerSupport.this.listeners) {
            try {
               method.invoke(listener, args);
            } catch (Throwable t) {
               this.handle(t);
            }
         }

         return null;
      }
   }
}

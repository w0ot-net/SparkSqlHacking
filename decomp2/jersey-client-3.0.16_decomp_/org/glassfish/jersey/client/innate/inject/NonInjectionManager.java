package org.glassfish.jersey.client.innate.inject;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.Binder;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.ClassBinding;
import org.glassfish.jersey.internal.inject.DisposableSupplier;
import org.glassfish.jersey.internal.inject.ForeignDescriptor;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.inject.PerThread;
import org.glassfish.jersey.internal.inject.ServiceHolder;
import org.glassfish.jersey.internal.inject.ServiceHolderImpl;
import org.glassfish.jersey.internal.inject.SupplierClassBinding;
import org.glassfish.jersey.internal.inject.SupplierInstanceBinding;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.process.internal.RequestScoped;

@ConstrainedTo(RuntimeType.CLIENT)
public final class NonInjectionManager implements InjectionManager {
   private static final Logger logger = Logger.getLogger(NonInjectionManager.class.getName());
   private final MultivaluedMap instanceBindings = new MultivaluedHashMap();
   private final MultivaluedMap contractBindings = new MultivaluedHashMap();
   private final MultivaluedMap supplierInstanceBindings = new MultivaluedHashMap();
   private final MultivaluedMap supplierClassBindings = new MultivaluedHashMap();
   private final MultivaluedMap instanceTypeBindings = new MultivaluedHashMap();
   private final MultivaluedMap contractTypeBindings = new MultivaluedHashMap();
   private final MultivaluedMap supplierTypeInstanceBindings = new MultivaluedHashMap();
   private final MultivaluedMap supplierTypeClassBindings = new MultivaluedHashMap();
   private final MultivaluedMap disposableSupplierObjects = new MultivaluedHashMap();
   private final Instances instances = new Instances();
   private final Types types = new Types();
   private volatile boolean isRequestScope = false;
   private volatile boolean shutdown = false;

   public NonInjectionManager() {
   }

   public NonInjectionManager(boolean warning) {
      if (warning) {
         logger.warning(LocalizationMessages.NONINJECT_FALLBACK());
      } else {
         logger.log(Level.FINER, LocalizationMessages.NONINJECT_FALLBACK());
      }

   }

   public void completeRegistration() {
      this.instances._addSingleton(InjectionManager.class, this, new InjectionManagerBinding(), (Annotation[])null);
   }

   public void shutdown() {
      this.shutdown = true;
      this.disposableSupplierObjects.forEach((supplier, objects) -> objects.forEach(supplier::dispose));
      this.disposableSupplierObjects.clear();
      this.instances.dispose();
      this.types.dispose();
   }

   public boolean isShutdown() {
      return this.shutdown;
   }

   private void checkShutdown() {
      if (this.shutdown) {
         throw new IllegalStateException(LocalizationMessages.NONINJECT_SHUTDOWN());
      }
   }

   public void register(Binding binding) {
      this.checkShutdown();
      if (InstanceBinding.class.isInstance(binding)) {
         InstanceBinding instanceBinding = (InstanceBinding)binding;
         Class<?> mainType = binding.getImplementationType();
         if (!this.instanceBindings.containsKey(mainType)) {
            this.instanceBindings.add(mainType, (InstanceBinding)binding);
         }

         for(Type type : instanceBinding.getContracts()) {
            if (isClass(type)) {
               if (!mainType.equals(type)) {
                  this.instanceBindings.add((Class)type, instanceBinding);
               }
            } else {
               this.instanceTypeBindings.add(type, instanceBinding);
            }
         }
      } else if (ClassBinding.class.isInstance(binding)) {
         ClassBinding<?> contractBinding = (ClassBinding)binding;
         Class<?> mainType = binding.getImplementationType();
         if (!this.contractBindings.containsKey(mainType)) {
            this.contractBindings.add(mainType, contractBinding);
         }

         for(Type type : contractBinding.getContracts()) {
            if (isClass(type)) {
               if (!mainType.equals(type)) {
                  this.contractBindings.add((Class)type, contractBinding);
               }
            } else {
               this.contractTypeBindings.add(type, contractBinding);
            }
         }
      } else if (SupplierInstanceBinding.class.isInstance(binding)) {
         SupplierInstanceBinding<?> supplierBinding = (SupplierInstanceBinding)binding;

         for(Type type : supplierBinding.getContracts()) {
            if (isClass(type)) {
               this.supplierInstanceBindings.add((Class)type, supplierBinding);
            } else {
               this.supplierTypeInstanceBindings.add(type, supplierBinding);
            }
         }
      } else if (SupplierClassBinding.class.isInstance(binding)) {
         SupplierClassBinding<?> supplierBinding = (SupplierClassBinding)binding;

         for(Type type : supplierBinding.getContracts()) {
            if (isClass(type)) {
               this.supplierClassBindings.add((Class)type, supplierBinding);
            } else {
               this.supplierTypeClassBindings.add(type, supplierBinding);
            }
         }
      }

   }

   public void register(Iterable descriptors) {
      this.checkShutdown();

      for(Binding binding : descriptors) {
         this.register(binding);
      }

   }

   public void register(Binder binder) {
      this.checkShutdown();
      binder.getBindings().stream().iterator().forEachRemaining(this::register);
   }

   public void register(Object provider) throws IllegalArgumentException {
      throw new UnsupportedOperationException("Register " + provider);
   }

   public boolean isRegistrable(Class clazz) {
      return false;
   }

   public List getAllServiceHolders(Class contractOrImpl, Annotation... qualifiers) {
      this.checkShutdown();
      ClassBindings<T> classBindings = this.classBindings(contractOrImpl, qualifiers);
      return classBindings.getAllServiceHolders(qualifiers);
   }

   public Object getInstance(Class contractOrImpl, Annotation... qualifiers) {
      this.checkShutdown();
      ClassBindings<T> classBindings = this.classBindings(contractOrImpl, qualifiers);
      classBindings.matchQualifiers(qualifiers);
      return classBindings.getInstance();
   }

   public Object getInstance(Class contractOrImpl, String classAnalyzer) {
      throw new UnsupportedOperationException("getInstance(Class, String)");
   }

   public Object getInstance(Class contractOrImpl) {
      this.checkShutdown();
      T instance = (T)this.instances.getInstance(contractOrImpl, (Annotation[])null);
      return instance != null ? instance : this.create(contractOrImpl);
   }

   public Object getInstance(Type contractOrImpl) {
      this.checkShutdown();
      if (ParameterizedType.class.isInstance(contractOrImpl)) {
         T instance = (T)this.types.getInstance(contractOrImpl, (Annotation[])null);
         if (instance != null) {
            return instance;
         } else {
            TypeBindings<T> typeBindings = this.typeBindings(contractOrImpl);
            return typeBindings.getInstance();
         }
      } else if (isClass(contractOrImpl)) {
         return this.getInstance((Class)contractOrImpl);
      } else {
         throw new IllegalStateException(LocalizationMessages.NONINJECT_UNSATISFIED(contractOrImpl));
      }
   }

   private static boolean isClass(Type type) {
      return Class.class.isAssignableFrom(type.getClass());
   }

   public Object getInstance(ForeignDescriptor foreignDescriptor) {
      throw new UnsupportedOperationException("getInstance(ForeignDescriptor foreignDescriptor) ");
   }

   public ForeignDescriptor createForeignDescriptor(Binding binding) {
      throw new UnsupportedOperationException("createForeignDescriptor(Binding binding) ");
   }

   public List getAllInstances(Type contractOrImpl) {
      this.checkShutdown();
      if (!isClass(contractOrImpl)) {
         TypeBindings<T> typeBindings = this.typeBindings(contractOrImpl);
         return typeBindings.allInstances();
      } else {
         ClassBindings<T> classBindings = this.classBindings((Class)contractOrImpl);
         return classBindings.allInstances();
      }
   }

   public Object create(Class createMe) {
      this.checkShutdown();
      if (InjectionManager.class.equals(createMe)) {
         return this;
      } else if (RequestScope.class.equals(createMe)) {
         if (!this.isRequestScope) {
            this.isRequestScope = true;
            return new NonInjectionRequestScope();
         } else {
            throw new IllegalStateException(LocalizationMessages.NONINJECT_REQUESTSCOPE_CREATED());
         }
      } else {
         ClassBindings<T> classBindings = this.classBindings(createMe);
         return classBindings.create(true);
      }
   }

   public Object createAndInitialize(Class createMe) {
      this.checkShutdown();
      if (InjectionManager.class.equals(createMe)) {
         return this;
      } else if (RequestScope.class.equals(createMe)) {
         if (!this.isRequestScope) {
            this.isRequestScope = true;
            return new NonInjectionRequestScope();
         } else {
            throw new IllegalStateException(LocalizationMessages.NONINJECT_REQUESTSCOPE_CREATED());
         }
      } else {
         ClassBindings<T> classBindings = this.classBindings(createMe);
         T t = (T)classBindings.create(false);
         return t != null ? t : this.justCreate(createMe);
      }
   }

   public Object justCreate(Class createMe) {
      T result = (T)null;

      try {
         Constructor<T> mostArgConstructor = findConstructor(createMe);
         if (mostArgConstructor != null) {
            int argCount = mostArgConstructor.getParameterCount();
            if (argCount == 0) {
               ensureAccessible(mostArgConstructor);
               result = (T)mostArgConstructor.newInstance();
            } else if (argCount > 0) {
               Object[] args = this.getArguments(mostArgConstructor, argCount);
               if (args != null) {
                  ensureAccessible(mostArgConstructor);
                  result = (T)mostArgConstructor.newInstance(args);
               }
            }
         }

         if (result == null) {
            throw new IllegalStateException(LocalizationMessages.NONINJECT_NO_CONSTRUCTOR(createMe.getName()));
         }
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }

      this.inject(result);
      return result;
   }

   private static Constructor findConstructor(Class forClass) {
      Constructor<T>[] constructors = forClass.getDeclaredConstructors();
      Constructor<T> mostArgConstructor = null;
      int argCount = -1;

      for(Constructor constructor : constructors) {
         if ((constructor.isAnnotationPresent(Inject.class) || constructor.getParameterCount() == 0) && constructor.getParameterCount() > argCount) {
            mostArgConstructor = constructor;
            argCount = constructor.getParameterCount();
         }
      }

      return mostArgConstructor;
   }

   private Object[] getArguments(Executable executable, int argCount) {
      if (executable == null) {
         return null;
      } else {
         Object[] args = new Object[argCount];

         for(int i = 0; i != argCount; ++i) {
            Type type = executable.getAnnotatedParameterTypes()[i].getType();
            args[i] = isClass(type) ? this.getInstance((Class)type) : this.getInstance(type);
         }

         return args;
      }
   }

   private static void ensureAccessible(Executable executable) {
      try {
         if (!executable.isAccessible()) {
            executable.setAccessible(true);
         }
      } catch (Exception var2) {
      }

   }

   private void checkUnique(List list) {
      if (list.size() != 1) {
         throw new IllegalStateException(LocalizationMessages.NONINJECT_AMBIGUOUS_SERVICES(list.get(0)));
      }
   }

   public void inject(Object injectMe) {
      Method postConstruct = getAnnotatedMethod(injectMe, PostConstruct.class);
      if (postConstruct != null) {
         ensureAccessible(postConstruct);

         try {
            postConstruct.invoke(injectMe);
         } catch (Exception e) {
            throw new IllegalStateException(e);
         }
      }

   }

   public void inject(Object injectMe, String classAnalyzer) {
      throw new UnsupportedOperationException("inject(Object injectMe, String classAnalyzer)");
   }

   public void preDestroy(Object preDestroyMe) {
      Method preDestroy = getAnnotatedMethod(preDestroyMe, PreDestroy.class);
      if (preDestroy != null) {
         ensureAccessible(preDestroy);

         try {
            preDestroy.invoke(preDestroyMe);
         } catch (Exception e) {
            throw new IllegalStateException(e);
         }
      }

   }

   private static Method getAnnotatedMethod(Object object, Class annotation) {
      Class<?> clazz = object.getClass();

      for(Method method : clazz.getMethods()) {
         if (method.isAnnotationPresent(annotation) && method.getParameterCount() == 0) {
            return method;
         }
      }

      return null;
   }

   private Object createSupplierProxyIfNeeded(Boolean createProxy, Class iface, final Supplier supplier) {
      if (createProxy != null && createProxy && iface.isInterface()) {
         T proxy = (T)Proxy.newProxyInstance(iface.getClassLoader(), new Class[]{iface}, new InvocationHandler() {
            final SingleRegisterSupplier singleSupplierRegister = NonInjectionManager.this.new SingleRegisterSupplier(supplier);

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               T t = (T)this.singleSupplierRegister.get();
               Object ret = method.invoke(t, args);
               return ret;
            }
         });
         return proxy;
      } else {
         return this.registerDisposableSupplierAndGet(supplier);
      }
   }

   private Object registerDisposableSupplierAndGet(Supplier supplier) {
      T instance = (T)supplier.get();
      if (DisposableSupplier.class.isInstance(supplier)) {
         this.disposableSupplierObjects.add((DisposableSupplier)supplier, instance);
      }

      return instance;
   }

   private ClassBindings classBindings(Class clazz, Annotation... instancesQualifiers) {
      ClassBindings<T> classBindings = new ClassBindings(clazz, instancesQualifiers);
      List<InstanceBinding<?>> ib = (List)this.instanceBindings.get(clazz);
      if (ib != null) {
         ib.forEach((binding) -> classBindings.instanceBindings.add(binding));
      }

      List<SupplierInstanceBinding<?>> sib = (List)this.supplierInstanceBindings.get(clazz);
      if (sib != null) {
         sib.forEach((binding) -> classBindings.supplierInstanceBindings.add(binding));
      }

      List<ClassBinding<?>> cb = (List)this.contractBindings.get(clazz);
      if (cb != null) {
         cb.forEach((binding) -> classBindings.classBindings.add(binding));
      }

      List<SupplierClassBinding<?>> scb = (List)this.supplierClassBindings.get(clazz);
      if (scb != null) {
         scb.forEach((binding) -> classBindings.supplierClassBindings.add(binding));
      }

      return classBindings;
   }

   private TypeBindings typeBindings(Type type) {
      TypeBindings<T> typeBindings = new TypeBindings(type);
      List<InstanceBinding<?>> ib = (List)this.instanceTypeBindings.get(type);
      if (ib != null) {
         ib.forEach((binding) -> typeBindings.instanceBindings.add(binding));
      }

      List<SupplierInstanceBinding<?>> sib = (List)this.supplierTypeInstanceBindings.get(type);
      if (sib != null) {
         sib.forEach((binding) -> typeBindings.supplierInstanceBindings.add(binding));
      }

      List<ClassBinding<?>> cb = (List)this.contractTypeBindings.get(type);
      if (cb != null) {
         cb.forEach((binding) -> typeBindings.classBindings.add(binding));
      }

      List<SupplierClassBinding<?>> scb = (List)this.supplierTypeClassBindings.get(type);
      if (scb != null) {
         scb.forEach((binding) -> typeBindings.supplierClassBindings.add(binding));
      }

      return typeBindings;
   }

   private class TypedInstances {
      private final MultivaluedMap singletonInstances;
      private ThreadLocal threadInstances;
      private final List threadPredestroyables;

      private TypedInstances() {
         this.singletonInstances = new MultivaluedHashMap();
         this.threadInstances = new ThreadLocal();
         this.threadPredestroyables = Collections.synchronizedList(new LinkedList());
      }

      private List _getSingletons(Object clazz) {
         synchronized(this.singletonInstances) {
            List<InstanceContext<?>> si = (List)this.singletonInstances.get(clazz);
            return si;
         }
      }

      Object _addSingleton(Object clazz, Object instance, Binding binding, Annotation[] qualifiers) {
         synchronized(this.singletonInstances) {
            List<InstanceContext<?>> values = (List)this.singletonInstances.get(clazz);
            if (values != null) {
               List<InstanceContext<?>> qualified = (List)values.stream().filter((ctx) -> ctx.hasQualifiers(qualifiers)).collect(Collectors.toList());
               if (!qualified.isEmpty()) {
                  return ((InstanceContext)qualified.get(0)).instance;
               }
            }

            this.singletonInstances.add(clazz, new InstanceContext(instance, binding, qualifiers));
            this.threadPredestroyables.add(instance);
            return instance;
         }
      }

      Object addSingleton(Object clazz, Object t, Binding binding, Annotation[] instanceQualifiers) {
         T t2 = (T)this._addSingleton(clazz, t, binding, instanceQualifiers);
         if (t2 == t) {
            for(Type contract : binding.getContracts()) {
               if (!clazz.equals(contract) && NonInjectionManager.isClass(contract)) {
                  this._addSingleton(contract, t, binding, instanceQualifiers);
               }
            }
         }

         return t2;
      }

      private List _getThreadInstances(Object clazz) {
         MultivaluedMap<TYPE, InstanceContext<?>> ti = (MultivaluedMap)this.threadInstances.get();
         List<InstanceContext<?>> list = ti == null ? null : new LinkedList();
         return ti != null ? (List)ti.get(clazz) : list;
      }

      private void _addThreadInstance(Object clazz, Object instance, Binding binding, Annotation[] qualifiers) {
         MultivaluedMap<TYPE, InstanceContext<?>> map = (MultivaluedMap)this.threadInstances.get();
         if (map == null) {
            map = new MultivaluedHashMap();
            this.threadInstances.set(map);
         }

         map.add(clazz, new InstanceContext(instance, binding, qualifiers));
         this.threadPredestroyables.add(instance);
      }

      void addThreadInstance(Object clazz, Object t, Binding binding, Annotation[] instanceQualifiers) {
         this._addThreadInstance(clazz, t, binding, instanceQualifiers);

         for(Type contract : binding.getContracts()) {
            if (!clazz.equals(contract) && NonInjectionManager.isClass(contract)) {
               this._addThreadInstance(contract, t, binding, instanceQualifiers);
            }
         }

      }

      private List getInstances(Object clazz, Annotation[] annotations) {
         List<InstanceContext<?>> i = this._getContexts(clazz);
         return NonInjectionManager.InstanceContext.toInstances(i, annotations);
      }

      List getContexts(Object clazz, Annotation[] annotations) {
         List<InstanceContext<?>> i = this._getContexts(clazz);
         return NonInjectionManager.InstanceContext.filterInstances(i, annotations);
      }

      private List _getContexts(Object clazz) {
         List<InstanceContext<?>> si = this._getSingletons(clazz);
         List<InstanceContext<?>> ti = this._getThreadInstances(clazz);
         if (si == null && ti != null) {
            si = ti;
         } else if (ti != null) {
            si.addAll(ti);
         }

         return si;
      }

      Object getInstance(Object clazz, Annotation[] annotations) {
         List<T> i = this.getInstances(clazz, annotations);
         if (i != null) {
            NonInjectionManager.this.checkUnique(i);
            return i.get(0);
         } else {
            return null;
         }
      }

      void dispose() {
         this.singletonInstances.forEach((clazz, instances) -> instances.forEach((instance) -> NonInjectionManager.this.preDestroy(instance.getInstance())));
         this.threadPredestroyables.forEach(NonInjectionManager.this::preDestroy);
         this.threadInstances = null;
      }
   }

   private class Instances extends TypedInstances {
      private Instances() {
      }
   }

   private class Types extends TypedInstances {
      private Types() {
      }
   }

   private class SingleRegisterSupplier {
      private final LazyValue once;

      private SingleRegisterSupplier(Supplier supplier) {
         this.once = Values.lazy(() -> NonInjectionManager.this.registerDisposableSupplierAndGet(supplier));
      }

      Object get() {
         return this.once.get();
      }
   }

   private abstract class XBindings {
      protected final List instanceBindings = new LinkedList();
      protected final List supplierInstanceBindings = new LinkedList();
      protected final List classBindings = new LinkedList();
      protected final List supplierClassBindings = new LinkedList();
      protected final Object type;
      protected final Annotation[] instancesQualifiers;
      protected final TypedInstances instances;

      protected XBindings(Object type, Annotation[] instancesQualifiers, TypedInstances instances) {
         this.type = type;
         this.instancesQualifiers = instancesQualifiers;
         this.instances = instances;
      }

      int size() {
         return this.instanceBindings.size() + this.supplierInstanceBindings.size() + this.classBindings.size() + this.supplierClassBindings.size();
      }

      private void _checkUnique() {
         if (this.size() > 1) {
            throw new IllegalStateException(LocalizationMessages.NONINJECT_AMBIGUOUS_SERVICES(this.type));
         }
      }

      void filterBinding(Binding binding) {
         if (InstanceBinding.class.isInstance(binding)) {
            this.instanceBindings.remove(binding);
         } else if (ClassBinding.class.isInstance(binding)) {
            this.classBindings.remove(binding);
         } else if (SupplierInstanceBinding.class.isInstance(binding)) {
            this.supplierInstanceBindings.remove(binding);
         } else if (SupplierClassBinding.class.isInstance(binding)) {
            this.supplierClassBindings.remove(binding);
         }

      }

      void matchQualifiers(Annotation... bindingQualifiers) {
         if (bindingQualifiers != null) {
            this._filterRequested(this.instanceBindings, bindingQualifiers);
            this._filterRequested(this.classBindings, bindingQualifiers);
            this._filterRequested(this.supplierInstanceBindings, bindingQualifiers);
            this._filterRequested(this.supplierClassBindings, bindingQualifiers);
         }

      }

      private void _filterRequested(List bindingList, Annotation... requestedQualifiers) {
         Iterator<? extends Binding> bindingIterator = bindingList.iterator();

         while(bindingIterator.hasNext()) {
            Binding<X, ?> binding = (Binding)bindingIterator.next();

            label29:
            for(Annotation requestedQualifier : requestedQualifiers) {
               for(Annotation bindingQualifier : binding.getQualifiers()) {
                  if (requestedQualifier.annotationType().isInstance(bindingQualifier)) {
                     continue label29;
                  }
               }

               bindingIterator.remove();
            }
         }

      }

      protected boolean _isPerThread(Class scope) {
         return RequestScoped.class.equals(scope) || PerThread.class.equals(scope);
      }

      private Object _getInstance(InstanceBinding instanceBinding) {
         return instanceBinding.getService();
      }

      private Object _create(SupplierInstanceBinding binding) {
         Supplier<X> supplier = binding.getSupplier();
         X t = (X)NonInjectionManager.this.registerDisposableSupplierAndGet(supplier);
         if (Singleton.class.equals(binding.getScope())) {
            this._addInstance(t, binding);
         } else if (this._isPerThread(binding.getScope())) {
            this._addThreadInstance(t, binding);
         }

         return t;
      }

      Object create(boolean throwWhenNoBinding) {
         this._checkUnique();
         if (!this.instanceBindings.isEmpty()) {
            return this._getInstance((InstanceBinding)this.instanceBindings.get(0));
         } else if (!this.supplierInstanceBindings.isEmpty()) {
            return this._create((SupplierInstanceBinding)this.supplierInstanceBindings.get(0));
         } else if (!this.classBindings.isEmpty()) {
            return this._createAndStore((ClassBinding)this.classBindings.get(0));
         } else if (!this.supplierClassBindings.isEmpty()) {
            return this._create((SupplierClassBinding)this.supplierClassBindings.get(0));
         } else if (throwWhenNoBinding) {
            throw new IllegalStateException(LocalizationMessages.NONINJECT_NO_BINDING(this.type));
         } else {
            return null;
         }
      }

      protected Object getInstance() {
         X instance = (X)this.instances.getInstance(this.type, this.instancesQualifiers);
         return instance != null ? instance : this.create(true);
      }

      List allInstances() {
         List<X> list = new LinkedList();
         List<InstanceContext<?>> instanceContextList = this.instances.getContexts(this.type, this.instancesQualifiers);
         if (instanceContextList != null) {
            instanceContextList.forEach((instanceContext) -> this.filterBinding(instanceContext.getBinding()));
            instanceContextList.forEach((instanceContext) -> list.add(instanceContext.getInstance()));
         }

         list.addAll((Collection)this.instanceBindings.stream().map(this::_getInstance).collect(Collectors.toList()));
         list.addAll((Collection)this.classBindings.stream().map(this::_createAndStore).collect(Collectors.toList()));
         list.addAll((Collection)this.supplierInstanceBindings.stream().map(this::_create).collect(Collectors.toList()));
         list.addAll((Collection)this.supplierClassBindings.stream().map(this::_create).collect(Collectors.toList()));
         return list;
      }

      protected abstract Object _create(SupplierClassBinding var1);

      protected abstract Object _createAndStore(ClassBinding var1);

      protected Object _addInstance(Object type, Object instance, Binding binding) {
         return this.instances.addSingleton(type, instance, binding, this.instancesQualifiers);
      }

      protected void _addThreadInstance(Object type, Object instance, Binding binding) {
         this.instances.addThreadInstance(type, instance, binding, this.instancesQualifiers);
      }

      protected Object _addInstance(Object instance, Binding binding) {
         return this.instances.addSingleton(this.type, instance, binding, this.instancesQualifiers);
      }

      protected void _addThreadInstance(Object instance, Binding binding) {
         this.instances.addThreadInstance(this.type, instance, binding, this.instancesQualifiers);
      }
   }

   private class ClassBindings extends XBindings {
      private ClassBindings(Class clazz, Annotation[] instancesQualifiers) {
         super(clazz, instancesQualifiers, NonInjectionManager.this.instances);
      }

      List getAllServiceHolders(Annotation... qualifiers) {
         this.matchQualifiers(qualifiers);
         List<ServiceHolder<T>> holders = new LinkedList();
         List<InstanceContext<?>> instanceContextList = this.instances.getContexts(this.type, qualifiers);
         if (instanceContextList != null) {
            instanceContextList.forEach((instanceContext) -> this.filterBinding(instanceContext.getBinding()));
            instanceContextList.forEach((instanceContext) -> holders.add(new ServiceHolderImpl(instanceContext.getInstance(), instanceContext.getInstance().getClass(), instanceContext.getBinding().getContracts(), instanceContext.getBinding().getRank() == null ? 0 : instanceContext.getBinding().getRank())));
         }

         List<ServiceHolder<T>> instanceBindingHolders = (List)this.instanceBindings.stream().map(this::_serviceHolder).collect(Collectors.toList());
         holders.addAll(instanceBindingHolders);
         List<ServiceHolder<T>> classBindingHolders = (List)this.classBindings.stream().filter((binding) -> {
            NonInjectionManager var10000 = NonInjectionManager.this;
            return NonInjectionManager.findConstructor(binding.getService()) != null;
         }).map(this::_serviceHolder).collect(Collectors.toList());
         holders.addAll(classBindingHolders);
         return holders;
      }

      private ServiceHolderImpl _serviceHolder(InstanceBinding binding) {
         return new ServiceHolderImpl(binding.getService(), binding.getImplementationType(), binding.getContracts(), binding.getRank() == null ? 0 : binding.getRank());
      }

      private ServiceHolderImpl _serviceHolder(ClassBinding binding) {
         return new ServiceHolderImpl(NonInjectionManager.this.create(binding.getService()), binding.getImplementationType(), binding.getContracts(), binding.getRank() == null ? 0 : binding.getRank());
      }

      protected Object _create(SupplierClassBinding binding) {
         Supplier<T> supplier = (Supplier)this.instances.getInstance(binding.getSupplierClass(), (Annotation[])null);
         if (supplier == null) {
            supplier = (Supplier)NonInjectionManager.this.justCreate(binding.getSupplierClass());
            if (Singleton.class.equals(binding.getSupplierScope())) {
               supplier = (Supplier)this.instances.addSingleton(binding.getSupplierClass(), supplier, binding, (Annotation[])null);
            } else if (this._isPerThread(binding.getSupplierScope())) {
               this.instances.addThreadInstance(binding.getSupplierClass(), supplier, binding, (Annotation[])null);
            }
         }

         T t = (T)NonInjectionManager.this.createSupplierProxyIfNeeded(binding.isProxiable(), (Class)this.type, supplier);
         if (Singleton.class.equals(binding.getScope())) {
            t = (T)this._addInstance(this.type, t, binding);
         } else if (this._isPerThread(binding.getScope())) {
            this._addThreadInstance(this.type, t, binding);
         }

         return t;
      }

      protected Object _createAndStore(ClassBinding binding) {
         T result = (T)NonInjectionManager.this.justCreate(binding.getService());
         result = (T)this._addInstance(binding.getService(), result, binding);
         return result;
      }
   }

   private class TypeBindings extends XBindings {
      private TypeBindings(Type type) {
         super(type, (Annotation[])null, NonInjectionManager.this.types);
      }

      protected Object _create(SupplierClassBinding binding) {
         Supplier<T> supplier = (Supplier)NonInjectionManager.this.justCreate(binding.getSupplierClass());
         T t = (T)NonInjectionManager.this.registerDisposableSupplierAndGet(supplier);
         if (Singleton.class.equals(binding.getScope())) {
            t = (T)this._addInstance(this.type, t, binding);
         } else if (this._isPerThread(binding.getScope())) {
            this._addThreadInstance(this.type, t, binding);
         }

         return t;
      }

      protected Object _createAndStore(ClassBinding binding) {
         T result = (T)NonInjectionManager.this.justCreate(binding.getService());
         result = (T)this._addInstance(this.type, result, binding);
         return result;
      }

      Object create(boolean throwWhenNoBinding) {
         if (ParameterizedType.class.isInstance(this.type)) {
            final ParameterizedType pt = (ParameterizedType)this.type;
            if (Provider.class.equals(pt.getRawType())) {
               return new Provider() {
                  final SingleRegisterSupplier supplier = NonInjectionManager.this.new SingleRegisterSupplier(new Supplier() {
                     public Object get() {
                        Type actualTypeArgument = pt.getActualTypeArguments()[0];
                        return NonInjectionManager.isClass(actualTypeArgument) ? NonInjectionManager.this.getInstance((Class)actualTypeArgument) : NonInjectionManager.this.getInstance(actualTypeArgument);
                     }
                  });

                  public Object get() {
                     return this.supplier.get();
                  }
               };
            }
         }

         return super.create(throwWhenNoBinding);
      }
   }

   private static class InstanceContext {
      private final Object instance;
      private final Binding binding;
      private final Annotation[] createdWithQualifiers;

      private InstanceContext(Object instance, Binding binding, Annotation[] qualifiers) {
         this.instance = instance;
         this.binding = binding;
         this.createdWithQualifiers = qualifiers;
      }

      public Binding getBinding() {
         return this.binding;
      }

      public Object getInstance() {
         return this.instance;
      }

      static List toInstances(List instances, Annotation[] qualifiers) {
         return instances != null ? (List)instances.stream().filter((instance) -> instance.hasQualifiers(qualifiers)).map((pair) -> pair.getInstance()).collect(Collectors.toList()) : null;
      }

      private static List filterInstances(List instances, Annotation... qualifiers) {
         return instances != null ? (List)instances.stream().filter((instance) -> instance.hasQualifiers(qualifiers)).collect(Collectors.toList()) : null;
      }

      private boolean hasQualifiers(Annotation[] requested) {
         if (requested != null) {
            label29:
            for(Annotation req : requested) {
               if (this.createdWithQualifiers != null) {
                  for(Annotation cur : this.createdWithQualifiers) {
                     if (cur.annotationType().isInstance(req)) {
                        continue label29;
                     }
                  }

                  return false;
               }
            }
         }

         return true;
      }
   }

   private static final class InjectionManagerBinding extends Binding {
      private InjectionManagerBinding() {
      }
   }
}

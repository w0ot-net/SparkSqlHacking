package org.glassfish.hk2.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ServiceLocator {
   Object getService(Class var1, Annotation... var2) throws MultiException;

   Object getService(Type var1, Annotation... var2) throws MultiException;

   Object getService(Class var1, String var2, Annotation... var3) throws MultiException;

   Object getService(Type var1, String var2, Annotation... var3) throws MultiException;

   List getAllServices(Class var1, Annotation... var2) throws MultiException;

   List getAllServices(Type var1, Annotation... var2) throws MultiException;

   List getAllServices(Annotation var1, Annotation... var2) throws MultiException;

   List getAllServices(Filter var1) throws MultiException;

   ServiceHandle getServiceHandle(Class var1, Annotation... var2) throws MultiException;

   ServiceHandle getServiceHandle(Type var1, Annotation... var2) throws MultiException;

   ServiceHandle getServiceHandle(Class var1, String var2, Annotation... var3) throws MultiException;

   ServiceHandle getServiceHandle(Type var1, String var2, Annotation... var3) throws MultiException;

   List getAllServiceHandles(Class var1, Annotation... var2) throws MultiException;

   List getAllServiceHandles(Type var1, Annotation... var2) throws MultiException;

   List getAllServiceHandles(Annotation var1, Annotation... var2) throws MultiException;

   List getAllServiceHandles(Filter var1) throws MultiException;

   List getDescriptors(Filter var1);

   ActiveDescriptor getBestDescriptor(Filter var1);

   ActiveDescriptor reifyDescriptor(Descriptor var1, Injectee var2) throws MultiException;

   ActiveDescriptor reifyDescriptor(Descriptor var1) throws MultiException;

   ActiveDescriptor getInjecteeDescriptor(Injectee var1) throws MultiException;

   ServiceHandle getServiceHandle(ActiveDescriptor var1, Injectee var2) throws MultiException;

   ServiceHandle getServiceHandle(ActiveDescriptor var1) throws MultiException;

   /** @deprecated */
   @Deprecated
   Object getService(ActiveDescriptor var1, ServiceHandle var2) throws MultiException;

   Object getService(ActiveDescriptor var1, ServiceHandle var2, Injectee var3) throws MultiException;

   String getDefaultClassAnalyzerName();

   void setDefaultClassAnalyzerName(String var1);

   Unqualified getDefaultUnqualified();

   void setDefaultUnqualified(Unqualified var1);

   String getName();

   long getLocatorId();

   ServiceLocator getParent();

   void shutdown();

   ServiceLocatorState getState();

   boolean isShutdown();

   boolean getNeutralContextClassLoader();

   void setNeutralContextClassLoader(boolean var1);

   Object create(Class var1);

   Object create(Class var1, String var2);

   void inject(Object var1);

   void inject(Object var1, String var2);

   Object assistedInject(Object var1, Method var2, MethodParameter... var3);

   Object assistedInject(Object var1, Method var2, ServiceHandle var3, MethodParameter... var4);

   void postConstruct(Object var1);

   void postConstruct(Object var1, String var2);

   void preDestroy(Object var1);

   void preDestroy(Object var1, String var2);

   Object createAndInitialize(Class var1);

   Object createAndInitialize(Class var1, String var2);
}

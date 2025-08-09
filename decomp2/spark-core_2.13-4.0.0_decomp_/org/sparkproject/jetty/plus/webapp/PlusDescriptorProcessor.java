package org.sparkproject.jetty.plus.webapp;

import java.util.Iterator;
import java.util.Objects;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.jndi.NamingUtil;
import org.sparkproject.jetty.plus.annotation.Injection;
import org.sparkproject.jetty.plus.annotation.InjectionCollection;
import org.sparkproject.jetty.plus.annotation.LifeCycleCallback;
import org.sparkproject.jetty.plus.annotation.LifeCycleCallbackCollection;
import org.sparkproject.jetty.plus.annotation.PostConstructCallback;
import org.sparkproject.jetty.plus.annotation.PreDestroyCallback;
import org.sparkproject.jetty.plus.jndi.EnvEntry;
import org.sparkproject.jetty.plus.jndi.Link;
import org.sparkproject.jetty.plus.jndi.NamingEntry;
import org.sparkproject.jetty.plus.jndi.NamingEntryUtil;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.webapp.Descriptor;
import org.sparkproject.jetty.webapp.FragmentDescriptor;
import org.sparkproject.jetty.webapp.IterativeDescriptorProcessor;
import org.sparkproject.jetty.webapp.Origin;
import org.sparkproject.jetty.webapp.WebAppContext;
import org.sparkproject.jetty.xml.XmlParser;

public class PlusDescriptorProcessor extends IterativeDescriptorProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(PlusDescriptorProcessor.class);

   public PlusDescriptorProcessor() {
      try {
         this.registerVisitor("env-entry", this.getClass().getMethod("visitEnvEntry", __signature));
         this.registerVisitor("resource-ref", this.getClass().getMethod("visitResourceRef", __signature));
         this.registerVisitor("resource-env-ref", this.getClass().getMethod("visitResourceEnvRef", __signature));
         this.registerVisitor("message-destination-ref", this.getClass().getMethod("visitMessageDestinationRef", __signature));
         this.registerVisitor("post-construct", this.getClass().getMethod("visitPostConstruct", __signature));
         this.registerVisitor("pre-destroy", this.getClass().getMethod("visitPreDestroy", __signature));
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   public void start(WebAppContext context, Descriptor descriptor) {
      InjectionCollection injections = (InjectionCollection)context.getAttribute("org.sparkproject.jetty.injectionCollection");
      if (injections == null) {
         injections = new InjectionCollection();
         context.setAttribute("org.sparkproject.jetty.injectionCollection", injections);
      }

      LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection");
      if (callbacks == null) {
         callbacks = new LifeCycleCallbackCollection();
         context.setAttribute("org.sparkproject.jetty.lifecyleCallbackCollection", callbacks);
      }

   }

   public void end(WebAppContext context, Descriptor descriptor) {
   }

   public void visitEnvEntry(WebAppContext context, Descriptor descriptor, XmlParser.Node node) throws Exception {
      String name = node.getString("env-entry-name", false, true);
      String type = node.getString("env-entry-type", false, true);
      String valueStr = node.getString("env-entry-value", false, true);
      Origin o = context.getMetaData().getOrigin("env-entry." + name);
      switch (o) {
         case NotSet:
            context.getMetaData().setOrigin("env-entry." + name, descriptor);
            this.makeEnvEntryInjectionsAndBindings(context, descriptor, node, name, type, valueStr);
            break;
         case WebXml:
         case WebDefaults:
         case WebOverride:
            if (!(descriptor instanceof FragmentDescriptor)) {
               context.getMetaData().setOrigin("env-entry." + name, descriptor);
               this.makeEnvEntryInjectionsAndBindings(context, descriptor, node, name, type, valueStr);
            } else {
               Descriptor d = context.getMetaData().getOriginDescriptor("env-entry." + name + ".injection");
               if (d == null || d instanceof FragmentDescriptor) {
                  this.addInjections(context, descriptor, node, name, TypeUtil.fromName(type));
               }
            }
            break;
         case WebFragment:
            throw new IllegalStateException("Conflicting env-entry " + name + " in " + String.valueOf(descriptor.getResource()));
      }

   }

   public void visitResourceRef(WebAppContext context, Descriptor descriptor, XmlParser.Node node) throws Exception {
      String jndiName = node.getString("res-ref-name", false, true);
      String type = node.getString("res-type", false, true);
      String auth = node.getString("res-auth", false, true);
      String shared = node.getString("res-sharing-scope", false, true);
      Origin o = context.getMetaData().getOrigin("resource-ref." + jndiName);
      switch (o) {
         case NotSet:
            context.getMetaData().setOrigin("resource-ref." + jndiName, descriptor);
            Class<?> typeClass = TypeUtil.fromName(type);
            if (typeClass == null) {
               typeClass = context.loadClass(type);
            }

            this.addInjections(context, descriptor, node, jndiName, typeClass);
            this.bindResourceRef(context, jndiName, typeClass);
            break;
         case WebXml:
         case WebDefaults:
         case WebOverride:
            if (!(descriptor instanceof FragmentDescriptor)) {
               context.getMetaData().setOrigin("resource-ref." + jndiName, descriptor);
               Class<?> typeClass = TypeUtil.fromName(type);
               if (typeClass == null) {
                  typeClass = context.loadClass(type);
               }

               this.addInjections(context, descriptor, node, jndiName, typeClass);
               this.bindResourceRef(context, jndiName, typeClass);
            } else {
               Descriptor d = context.getMetaData().getOriginDescriptor("resource-ref." + jndiName + ".injection");
               if (d == null || d instanceof FragmentDescriptor) {
                  Class<?> typeClass = TypeUtil.fromName(type);
                  if (typeClass == null) {
                     context.loadClass(type);
                  }

                  this.addInjections(context, descriptor, node, jndiName, TypeUtil.fromName(type));
               }
            }
            break;
         case WebFragment:
            Descriptor otherFragment = context.getMetaData().getOriginDescriptor("resource-ref." + jndiName);
            XmlParser.Node otherFragmentRoot = otherFragment.getRoot();
            Iterator<Object> iter = otherFragmentRoot.iterator();
            XmlParser.Node otherNode = null;

            while(iter.hasNext() && otherNode == null) {
               Object obj = iter.next();
               if (obj instanceof XmlParser.Node) {
                  XmlParser.Node n = (XmlParser.Node)obj;
                  if ("resource-ref".equals(n.getTag()) && jndiName.equals(n.getString("res-ref-name", false, true))) {
                     otherNode = n;
                  }
               }
            }

            if (otherNode == null) {
               throw new IllegalStateException("resource-ref." + jndiName + " not found in declaring descriptor " + String.valueOf(otherFragment));
            }

            String otherType = otherNode.getString("res-type", false, true);
            otherType = otherType == null ? "" : otherType;
            String otherAuth = otherNode.getString("res-auth", false, true);
            otherAuth = otherAuth == null ? "" : otherAuth;
            String otherShared = otherNode.getString("res-sharing-scope", false, true);
            otherShared = otherShared == null ? "" : otherShared;
            type = type == null ? "" : type;
            auth = auth == null ? "" : auth;
            shared = shared == null ? "" : shared;
            if (!type.equals(otherType) || !auth.equals(otherAuth) || !shared.equals(otherShared)) {
               throw new IllegalStateException("Conflicting resource-ref " + jndiName + " in " + String.valueOf(descriptor.getResource()));
            }

            this.addInjections(context, descriptor, node, jndiName, TypeUtil.fromName(type));
      }

   }

   public void visitResourceEnvRef(WebAppContext context, Descriptor descriptor, XmlParser.Node node) throws Exception {
      String jndiName = node.getString("resource-env-ref-name", false, true);
      String type = node.getString("resource-env-ref-type", false, true);
      Origin o = context.getMetaData().getOrigin("resource-env-ref." + jndiName);
      switch (o) {
         case NotSet:
            Class<?> typeClass = TypeUtil.fromName(type);
            if (typeClass == null) {
               typeClass = context.loadClass(type);
            }

            this.addInjections(context, descriptor, node, jndiName, typeClass);
            this.bindResourceEnvRef(context, jndiName, typeClass);
            break;
         case WebXml:
         case WebDefaults:
         case WebOverride:
            if (!(descriptor instanceof FragmentDescriptor)) {
               context.getMetaData().setOrigin("resource-env-ref." + jndiName, descriptor);
               Class<?> typeClass = TypeUtil.fromName(type);
               if (typeClass == null) {
                  typeClass = context.loadClass(type);
               }

               this.addInjections(context, descriptor, node, jndiName, typeClass);
               this.bindResourceEnvRef(context, jndiName, typeClass);
            } else {
               Descriptor d = context.getMetaData().getOriginDescriptor("resource-env-ref." + jndiName + ".injection");
               if (d == null || d instanceof FragmentDescriptor) {
                  Class<?> typeClass = TypeUtil.fromName(type);
                  if (typeClass == null) {
                     typeClass = context.loadClass(type);
                  }

                  this.addInjections(context, descriptor, node, jndiName, typeClass);
               }
            }
            break;
         case WebFragment:
            Descriptor otherFragment = context.getMetaData().getOriginDescriptor("resource-env-ref." + jndiName);
            XmlParser.Node otherFragmentRoot = otherFragment.getRoot();
            Iterator<Object> iter = otherFragmentRoot.iterator();
            XmlParser.Node otherNode = null;

            while(iter.hasNext() && otherNode == null) {
               Object obj = iter.next();
               if (obj instanceof XmlParser.Node) {
                  XmlParser.Node n = (XmlParser.Node)obj;
                  if ("resource-env-ref".equals(n.getTag()) && jndiName.equals(n.getString("resource-env-ref-name", false, true))) {
                     otherNode = n;
                  }
               }
            }

            if (otherNode == null) {
               throw new IllegalStateException("resource-env-ref." + jndiName + " not found in declaring descriptor " + String.valueOf(otherFragment));
            }

            String otherType = otherNode.getString("resource-env-ref-type", false, true);
            type = type == null ? "" : type;
            otherType = otherType == null ? "" : otherType;
            if (!type.equals(otherType)) {
               throw new IllegalStateException("Conflicting resource-env-ref " + jndiName + " in " + String.valueOf(descriptor.getResource()));
            }

            this.addInjections(context, descriptor, node, jndiName, TypeUtil.fromName(type));
      }

   }

   public void visitMessageDestinationRef(WebAppContext context, Descriptor descriptor, XmlParser.Node node) throws Exception {
      String jndiName = node.getString("message-destination-ref-name", false, true);
      String type = node.getString("message-destination-type", false, true);
      String usage = node.getString("message-destination-usage", false, true);
      Origin o = context.getMetaData().getOrigin("message-destination-ref." + jndiName);
      switch (o) {
         case NotSet:
            Class<?> typeClass = TypeUtil.fromName(type);
            if (typeClass == null) {
               typeClass = context.loadClass(type);
            }

            this.addInjections(context, descriptor, node, jndiName, typeClass);
            this.bindMessageDestinationRef(context, jndiName, typeClass);
            context.getMetaData().setOrigin("message-destination-ref." + jndiName, descriptor);
            break;
         case WebXml:
         case WebDefaults:
         case WebOverride:
            if (!(descriptor instanceof FragmentDescriptor)) {
               Class<?> typeClass = TypeUtil.fromName(type);
               if (typeClass == null) {
                  typeClass = context.loadClass(type);
               }

               this.addInjections(context, descriptor, node, jndiName, typeClass);
               this.bindMessageDestinationRef(context, jndiName, typeClass);
               context.getMetaData().setOrigin("message-destination-ref." + jndiName, descriptor);
            } else {
               Descriptor d = context.getMetaData().getOriginDescriptor("message-destination-ref." + jndiName + ".injection");
               if (d == null || d instanceof FragmentDescriptor) {
                  Class<?> typeClass = TypeUtil.fromName(type);
                  if (typeClass == null) {
                     typeClass = context.loadClass(type);
                  }

                  this.addInjections(context, descriptor, node, jndiName, typeClass);
               }
            }
            break;
         case WebFragment:
            Descriptor otherFragment = context.getMetaData().getOriginDescriptor("message-destination-ref." + jndiName);
            XmlParser.Node otherFragmentRoot = otherFragment.getRoot();
            Iterator<Object> iter = otherFragmentRoot.iterator();
            XmlParser.Node otherNode = null;

            while(iter.hasNext() && otherNode == null) {
               Object obj = iter.next();
               if (obj instanceof XmlParser.Node) {
                  XmlParser.Node n = (XmlParser.Node)obj;
                  if ("message-destination-ref".equals(n.getTag()) && jndiName.equals(n.getString("message-destination-ref-name", false, true))) {
                     otherNode = n;
                  }
               }
            }

            if (otherNode == null) {
               throw new IllegalStateException("message-destination-ref." + jndiName + " not found in declaring descriptor " + String.valueOf(otherFragment));
            }

            String otherType = node.getString("message-destination-type", false, true);
            String otherUsage = node.getString("message-destination-usage", false, true);
            type = type == null ? "" : type;
            usage = usage == null ? "" : usage;
            if (!type.equals(otherType) || !usage.equalsIgnoreCase(otherUsage)) {
               throw new IllegalStateException("Conflicting message-destination-ref " + jndiName + " in " + String.valueOf(descriptor.getResource()));
            }

            this.addInjections(context, descriptor, node, jndiName, TypeUtil.fromName(type));
      }

   }

   public void visitPostConstruct(WebAppContext context, Descriptor descriptor, XmlParser.Node node) {
      String className = node.getString("lifecycle-callback-class", false, true);
      String methodName = node.getString("lifecycle-callback-method", false, true);
      if (className != null && !className.isEmpty()) {
         if (methodName != null && !methodName.isEmpty()) {
            Origin o = context.getMetaData().getOrigin("post-construct");
            switch (o) {
               case NotSet:
                  context.getMetaData().setOrigin("post-construct", descriptor);
                  LifeCycleCallback callback = new PostConstructCallback(className, methodName);
                  ((LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection")).add(callback);
                  break;
               case WebXml:
               case WebDefaults:
               case WebOverride:
                  if (!(descriptor instanceof FragmentDescriptor)) {
                     try {
                        Class<?> clazz = context.loadClass(className);
                        LifeCycleCallback callback = new PostConstructCallback(clazz, methodName);
                        ((LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection")).add(callback);
                     } catch (ClassNotFoundException var10) {
                        LOG.warn("Couldn't load post-construct target class {}", className);
                     }
                  }
                  break;
               case WebFragment:
                  try {
                     Class<?> clazz = context.loadClass(className);
                     LifeCycleCallback callback = new PostConstructCallback(clazz, methodName);
                     ((LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection")).add(callback);
                  } catch (ClassNotFoundException var9) {
                     LOG.warn("Couldn't load post-construct target class {}", className);
                  }
            }

         } else {
            LOG.warn("No lifecycle-callback-method specified for class {}", className);
         }
      } else {
         LOG.warn("No lifecycle-callback-class specified");
      }
   }

   public void visitPreDestroy(WebAppContext context, Descriptor descriptor, XmlParser.Node node) {
      String className = node.getString("lifecycle-callback-class", false, true);
      String methodName = node.getString("lifecycle-callback-method", false, true);
      if (className != null && !className.isEmpty()) {
         if (methodName != null && !methodName.isEmpty()) {
            Origin o = context.getMetaData().getOrigin("pre-destroy");
            switch (o) {
               case NotSet:
                  context.getMetaData().setOrigin("pre-destroy", descriptor);
                  LifeCycleCallback callback = new PreDestroyCallback(className, methodName);
                  ((LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection")).add(callback);
                  break;
               case WebXml:
               case WebDefaults:
               case WebOverride:
                  if (!(descriptor instanceof FragmentDescriptor)) {
                     try {
                        Class<?> clazz = context.loadClass(className);
                        LifeCycleCallback callback = new PreDestroyCallback(clazz, methodName);
                        ((LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection")).add(callback);
                     } catch (ClassNotFoundException var10) {
                        LOG.warn("Couldn't load pre-destroy target class {}", className);
                     }
                  }
                  break;
               case WebFragment:
                  try {
                     Class<?> clazz = context.loadClass(className);
                     LifeCycleCallback callback = new PreDestroyCallback(clazz, methodName);
                     ((LifeCycleCallbackCollection)context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection")).add(callback);
                  } catch (ClassNotFoundException var9) {
                     LOG.warn("Couldn't load pre-destroy target class {}", className);
                  }
            }

         } else {
            LOG.warn("No lifecycle-callback-method specified for pre-destroy class {}", className);
         }
      } else {
         LOG.warn("No lifecycle-callback-class specified for pre-destroy");
      }
   }

   public void addInjections(WebAppContext context, Descriptor descriptor, XmlParser.Node node, String jndiName, Class valueClass) {
      Objects.requireNonNull(context);
      Objects.requireNonNull(node);
      Iterator<XmlParser.Node> itor = node.iterator("injection-target");

      while(itor.hasNext()) {
         XmlParser.Node injectionNode = (XmlParser.Node)itor.next();
         String targetClassName = injectionNode.getString("injection-target-class", false, true);
         String targetName = injectionNode.getString("injection-target-name", false, true);
         if (targetClassName != null && !targetClassName.isEmpty()) {
            if (targetName != null && !targetName.isEmpty()) {
               InjectionCollection injections = (InjectionCollection)context.getAttribute("org.sparkproject.jetty.injectionCollection");
               if (injections == null) {
                  injections = new InjectionCollection();
                  context.setAttribute("org.sparkproject.jetty.injectionCollection", injections);
               }

               try {
                  Class<?> clazz = context.loadClass(targetClassName);
                  Injection injection = new Injection(clazz, targetName, valueClass, jndiName, (String)null);
                  injections.add(injection);
                  String var10000 = node.getTag();
                  String name = var10000 + "." + jndiName + ".injection";
                  if (context.getMetaData().getOriginDescriptor(name) == null) {
                     context.getMetaData().setOrigin(name, descriptor);
                  }
               } catch (ClassNotFoundException var14) {
                  LOG.warn("Couldn't load injection target class {}", targetClassName);
               }
            } else {
               LOG.warn("No field or method name in injection-target");
            }
         } else {
            LOG.warn("No classname found in injection-target");
         }
      }

   }

   public void bindEnvEntry(String name, Object value) throws Exception {
      InitialContext ic = new InitialContext();
      Context envCtx = (Context)ic.lookup("java:comp/env");
      NamingUtil.bind(envCtx, name, value);
   }

   public void makeEnvEntryInjectionsAndBindings(WebAppContext context, Descriptor descriptor, XmlParser.Node node, String name, String type, String value) throws Exception {
      InitialContext ic = new InitialContext();

      try {
         String var10001 = String.valueOf(NamingEntryUtil.makeNamingEntryName(ic.getNameParser(""), name));
         EnvEntry envEntry = (EnvEntry)ic.lookup("java:comp/env/" + var10001);
         if (StringUtil.isEmpty(value)) {
            if (envEntry != null && envEntry.isOverrideWebXml()) {
               this.addInjections(context, descriptor, node, name, TypeUtil.fromName(type));
            }
         } else {
            this.addInjections(context, descriptor, node, name, TypeUtil.fromName(type));
            if (envEntry == null || !envEntry.isOverrideWebXml()) {
               this.bindEnvEntry(name, TypeUtil.valueOf(type, value));
            }
         }
      } catch (NameNotFoundException var9) {
         this.addInjections(context, descriptor, node, name, TypeUtil.fromName(type));
         this.bindEnvEntry(name, TypeUtil.valueOf(type, value));
      }

   }

   public void bindResourceRef(WebAppContext context, String name, Class typeClass) throws Exception {
      this.bindEntry(context, name, typeClass);
   }

   public void bindResourceEnvRef(WebAppContext context, String name, Class typeClass) throws Exception {
      this.bindEntry(context, name, typeClass);
   }

   public void bindMessageDestinationRef(WebAppContext context, String name, Class typeClass) throws Exception {
      this.bindEntry(context, name, typeClass);
   }

   protected void bindEntry(WebAppContext context, String name, Class typeClass) throws Exception {
      String nameInEnvironment = name;
      boolean bound = false;
      NamingEntry ne = NamingEntryUtil.lookupNamingEntry(context, name);
      if (ne != null && ne instanceof Link) {
         nameInEnvironment = ((Link)ne).getLink();
      }

      bound = NamingEntryUtil.bindToENC(context, name, nameInEnvironment);
      if (!bound) {
         Object scope = context.getServer();
         bound = NamingEntryUtil.bindToENC(scope, name, nameInEnvironment);
         if (!bound) {
            bound = NamingEntryUtil.bindToENC((Object)null, name, nameInEnvironment);
            if (!bound) {
               nameInEnvironment = typeClass.getName() + "/default";
               NamingEntry defaultNE = NamingEntryUtil.lookupNamingEntry(context.getServer(), nameInEnvironment);
               if (defaultNE == null) {
                  defaultNE = NamingEntryUtil.lookupNamingEntry((Object)null, nameInEnvironment);
               }

               if (defaultNE != null) {
                  defaultNE.bindToENC(name);
               } else {
                  throw new IllegalStateException("Nothing to bind for name " + name);
               }
            }
         }
      }
   }
}

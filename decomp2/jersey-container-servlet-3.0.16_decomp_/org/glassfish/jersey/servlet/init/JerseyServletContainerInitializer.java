package org.glassfish.jersey.servlet.init;

import jakarta.servlet.Registration;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.init.internal.LocalizationMessages;
import org.glassfish.jersey.servlet.internal.ServletContainerProviderFactory;
import org.glassfish.jersey.servlet.internal.Utils;
import org.glassfish.jersey.servlet.internal.spi.ServletContainerProvider;

@HandlesTypes({Path.class, Provider.class, Application.class, ApplicationPath.class})
public final class JerseyServletContainerInitializer implements ServletContainerInitializer {
   private static final Logger LOGGER = Logger.getLogger(JerseyServletContainerInitializer.class.getName());

   public void onStartup(Set classes, ServletContext servletContext) throws ServletException {
      ServletContainerProvider[] allServletContainerProviders = ServletContainerProviderFactory.getAllServletContainerProviders();
      if (classes == null) {
         classes = Collections.emptySet();
      }

      for(ServletContainerProvider servletContainerProvider : allServletContainerProviders) {
         servletContainerProvider.preInit(servletContext, classes);
      }

      this.onStartupImpl(classes, servletContext);

      for(ServletContainerProvider servletContainerProvider : allServletContainerProviders) {
         servletContainerProvider.postInit(servletContext, classes, findJerseyServletNames(servletContext));
      }

      for(ServletContainerProvider servletContainerProvider : allServletContainerProviders) {
         servletContainerProvider.onRegister(servletContext, findJerseyServletNames(servletContext));
      }

   }

   private void onStartupImpl(Set classes, ServletContext servletContext) throws ServletException {
      Set<ServletRegistration> registrationsWithApplication = new HashSet();

      for(Class applicationClass : getApplicationClasses(classes)) {
         ServletRegistration servletRegistration = servletContext.getServletRegistration(applicationClass.getName());
         if (servletRegistration != null) {
            addServletWithExistingRegistration(servletContext, servletRegistration, applicationClass, classes);
            registrationsWithApplication.add(servletRegistration);
         } else {
            List<Registration> srs = getInitParamDeclaredRegistrations(servletContext, applicationClass);
            if (!srs.isEmpty()) {
               for(Registration sr : srs) {
                  if (sr instanceof ServletRegistration) {
                     addServletWithExistingRegistration(servletContext, (ServletRegistration)sr, applicationClass, classes);
                     registrationsWithApplication.add((ServletRegistration)sr);
                  }
               }
            } else {
               ServletRegistration sr = addServletWithApplication(servletContext, applicationClass, classes);
               if (sr != null) {
                  registrationsWithApplication.add(sr);
               }
            }
         }
      }

      addServletWithDefaultConfiguration(servletContext, registrationsWithApplication, classes);
   }

   private static Set findJerseyServletNames(ServletContext servletContext) {
      Set<String> jerseyServletNames = new HashSet();

      for(ServletRegistration servletRegistration : servletContext.getServletRegistrations().values()) {
         if (isJerseyServlet(servletRegistration.getClassName())) {
            jerseyServletNames.add(servletRegistration.getName());
         }
      }

      return Collections.unmodifiableSet(jerseyServletNames);
   }

   private static boolean isJerseyServlet(String className) {
      return ServletContainer.class.getName().equals(className) || "org.glassfish.jersey.servlet.portability.PortableServletContainer".equals(className);
   }

   private static List getInitParamDeclaredRegistrations(ServletContext context, Class clazz) {
      List<Registration> registrations = new ArrayList();
      collectJaxRsRegistrations(context.getServletRegistrations(), registrations, clazz);
      collectJaxRsRegistrations(context.getFilterRegistrations(), registrations, clazz);
      return registrations;
   }

   private static void collectJaxRsRegistrations(Map registrations, List collected, Class a) {
      for(Registration sr : registrations.values()) {
         Map<String, String> ips = sr.getInitParameters();
         if (ips.containsKey("jakarta.ws.rs.Application") && ((String)ips.get("jakarta.ws.rs.Application")).equals(a.getName())) {
            collected.add(sr);
         }
      }

   }

   private static void addServletWithDefaultConfiguration(ServletContext context, Set registrationsWithApplication, Set classes) throws ServletException {
      ServletRegistration registration = context.getServletRegistration(Application.class.getName());
      Set<Class<?>> appClasses = getRootResourceAndProviderClasses(classes);
      if (registration != null) {
         ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(ResourceConfig.class, appClasses).addProperties(getInitParams(registration)).addProperties(Utils.getContextParams(context));
         if (registration.getClassName() != null) {
            Utils.store(resourceConfig, context, registration.getName());
         } else {
            ServletContainer servlet = new ServletContainer(resourceConfig);
            registration = context.addServlet(registration.getName(), servlet);
            ((ServletRegistration.Dynamic)registration).setLoadOnStartup(1);
            if (registration.getMappings().isEmpty()) {
               LOGGER.log(Level.WARNING, LocalizationMessages.JERSEY_APP_NO_MAPPING(registration.getName()));
            } else {
               LOGGER.log(Level.CONFIG, LocalizationMessages.JERSEY_APP_REGISTERED_CLASSES(registration.getName(), appClasses));
            }
         }
      }

      for(ServletRegistration servletRegistration : context.getServletRegistrations().values()) {
         if (isJerseyServlet(servletRegistration.getClassName()) && servletRegistration != registration && !registrationsWithApplication.contains(servletRegistration) && getInitParams(servletRegistration).isEmpty()) {
            ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(ResourceConfig.class, appClasses).addProperties(Utils.getContextParams(context));
            Utils.store(resourceConfig, context, servletRegistration.getName());
         }
      }

   }

   private static ServletRegistration addServletWithApplication(ServletContext context, Class clazz, Set defaultClasses) throws ServletException {
      ApplicationPath ap = (ApplicationPath)clazz.getAnnotation(ApplicationPath.class);
      if (ap != null) {
         ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(clazz, defaultClasses).addProperties(Utils.getContextParams(context));
         ServletContainer s = new ServletContainer(resourceConfig);
         ServletRegistration.Dynamic dsr = context.addServlet(clazz.getName(), s);
         dsr.setAsyncSupported(true);
         dsr.setLoadOnStartup(1);
         String mapping = createMappingPath(ap);
         if (!mappingExists(context, mapping)) {
            dsr.addMapping(new String[]{mapping});
            LOGGER.log(Level.CONFIG, LocalizationMessages.JERSEY_APP_REGISTERED_MAPPING(clazz.getName(), mapping));
         } else {
            LOGGER.log(Level.WARNING, LocalizationMessages.JERSEY_APP_MAPPING_CONFLICT(clazz.getName(), mapping));
         }

         return dsr;
      } else {
         return null;
      }
   }

   private static void addServletWithExistingRegistration(ServletContext context, ServletRegistration registration, Class clazz, Set classes) throws ServletException {
      ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(clazz, classes).addProperties(getInitParams(registration)).addProperties(Utils.getContextParams(context));
      if (registration.getClassName() != null) {
         Utils.store(resourceConfig, context, registration.getName());
      } else {
         ServletContainer servlet = new ServletContainer(resourceConfig);
         ServletRegistration.Dynamic dynamicRegistration = context.addServlet(clazz.getName(), servlet);
         dynamicRegistration.setAsyncSupported(true);
         dynamicRegistration.setLoadOnStartup(1);
         registration = dynamicRegistration;
      }

      if (registration.getMappings().isEmpty()) {
         ApplicationPath ap = (ApplicationPath)clazz.getAnnotation(ApplicationPath.class);
         if (ap != null) {
            String mapping = createMappingPath(ap);
            if (!mappingExists(context, mapping)) {
               registration.addMapping(new String[]{mapping});
               LOGGER.log(Level.CONFIG, LocalizationMessages.JERSEY_APP_REGISTERED_MAPPING(clazz.getName(), mapping));
            } else {
               LOGGER.log(Level.WARNING, LocalizationMessages.JERSEY_APP_MAPPING_CONFLICT(clazz.getName(), mapping));
            }
         } else {
            LOGGER.log(Level.WARNING, LocalizationMessages.JERSEY_APP_NO_MAPPING_OR_ANNOTATION(clazz.getName(), ApplicationPath.class.getSimpleName()));
         }
      } else {
         LOGGER.log(Level.CONFIG, LocalizationMessages.JERSEY_APP_REGISTERED_APPLICATION(clazz.getName()));
      }

   }

   private static Map getInitParams(ServletRegistration sr) {
      Map<String, Object> initParams = new HashMap();

      for(Map.Entry entry : sr.getInitParameters().entrySet()) {
         initParams.put(entry.getKey(), entry.getValue());
      }

      return initParams;
   }

   private static boolean mappingExists(ServletContext sc, String mapping) {
      for(ServletRegistration sr : sc.getServletRegistrations().values()) {
         for(String declaredMapping : sr.getMappings()) {
            if (mapping.equals(declaredMapping)) {
               return true;
            }
         }
      }

      return false;
   }

   private static String createMappingPath(ApplicationPath ap) {
      String path = ap.value();
      if (!path.startsWith("/")) {
         path = "/" + path;
      }

      if (!path.endsWith("/*")) {
         if (path.endsWith("/")) {
            path = path + "*";
         } else {
            path = path + "/*";
         }
      }

      return path;
   }

   private static Set getApplicationClasses(Set classes) {
      Set<Class<? extends Application>> s = new LinkedHashSet();

      for(Class c : classes) {
         if (Application.class != c && Application.class.isAssignableFrom(c)) {
            s.add(c.asSubclass(Application.class));
         }
      }

      return s;
   }

   private static Set getRootResourceAndProviderClasses(Set classes) {
      Set<Class<?>> s = new LinkedHashSet();

      for(Class c : classes) {
         if (c.isAnnotationPresent(Path.class) || c.isAnnotationPresent(Provider.class)) {
            s.add(c);
         }
      }

      return s;
   }
}

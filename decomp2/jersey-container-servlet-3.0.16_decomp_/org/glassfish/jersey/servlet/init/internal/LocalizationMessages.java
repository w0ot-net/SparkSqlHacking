package org.glassfish.jersey.servlet.init.internal;

import java.util.Locale;
import java.util.ResourceBundle;
import org.glassfish.jersey.internal.l10n.Localizable;
import org.glassfish.jersey.internal.l10n.LocalizableMessageFactory;
import org.glassfish.jersey.internal.l10n.Localizer;

public final class LocalizationMessages {
   private static final String BUNDLE_NAME = "org.glassfish.jersey.servlet.init.internal.localization";
   private static final LocalizableMessageFactory MESSAGE_FACTORY = new LocalizableMessageFactory("org.glassfish.jersey.servlet.init.internal.localization", new BundleSupplier());
   private static final Localizer LOCALIZER = new Localizer();

   private LocalizationMessages() {
   }

   public static Localizable localizableJERSEY_APP_MAPPING_CONFLICT(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("jersey.app.mapping.conflict", new Object[]{arg0, arg1});
   }

   public static String JERSEY_APP_MAPPING_CONFLICT(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableJERSEY_APP_MAPPING_CONFLICT(arg0, arg1));
   }

   public static Localizable localizableJERSEY_APP_REGISTERED_MAPPING(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("jersey.app.registered.mapping", new Object[]{arg0, arg1});
   }

   public static String JERSEY_APP_REGISTERED_MAPPING(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableJERSEY_APP_REGISTERED_MAPPING(arg0, arg1));
   }

   public static Localizable localizableJERSEY_APP_REGISTERED_CLASSES(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("jersey.app.registered.classes", new Object[]{arg0, arg1});
   }

   public static String JERSEY_APP_REGISTERED_CLASSES(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableJERSEY_APP_REGISTERED_CLASSES(arg0, arg1));
   }

   public static Localizable localizableJERSEY_APP_NO_MAPPING(Object arg0) {
      return MESSAGE_FACTORY.getMessage("jersey.app.no.mapping", new Object[]{arg0});
   }

   public static String JERSEY_APP_NO_MAPPING(Object arg0) {
      return LOCALIZER.localize(localizableJERSEY_APP_NO_MAPPING(arg0));
   }

   public static Localizable localizableSERVLET_ASYNC_CONTEXT_ALREADY_STARTED() {
      return MESSAGE_FACTORY.getMessage("servlet.async.context.already.started", new Object[0]);
   }

   public static String SERVLET_ASYNC_CONTEXT_ALREADY_STARTED() {
      return LOCALIZER.localize(localizableSERVLET_ASYNC_CONTEXT_ALREADY_STARTED());
   }

   public static Localizable localizableJERSEY_APP_REGISTERED_APPLICATION(Object arg0) {
      return MESSAGE_FACTORY.getMessage("jersey.app.registered.application", new Object[]{arg0});
   }

   public static String JERSEY_APP_REGISTERED_APPLICATION(Object arg0) {
      return LOCALIZER.localize(localizableJERSEY_APP_REGISTERED_APPLICATION(arg0));
   }

   public static Localizable localizableJERSEY_APP_NO_MAPPING_OR_ANNOTATION(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("jersey.app.no.mapping.or.annotation", new Object[]{arg0, arg1});
   }

   public static String JERSEY_APP_NO_MAPPING_OR_ANNOTATION(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableJERSEY_APP_NO_MAPPING_OR_ANNOTATION(arg0, arg1));
   }

   private static class BundleSupplier implements LocalizableMessageFactory.ResourceBundleSupplier {
      private BundleSupplier() {
      }

      public ResourceBundle getResourceBundle(Locale locale) {
         return ResourceBundle.getBundle("org.glassfish.jersey.servlet.init.internal.localization", locale);
      }
   }
}

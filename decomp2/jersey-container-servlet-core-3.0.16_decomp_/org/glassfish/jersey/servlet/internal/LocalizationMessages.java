package org.glassfish.jersey.servlet.internal;

import java.util.Locale;
import java.util.ResourceBundle;
import org.glassfish.jersey.internal.l10n.Localizable;
import org.glassfish.jersey.internal.l10n.LocalizableMessageFactory;
import org.glassfish.jersey.internal.l10n.Localizer;

public final class LocalizationMessages {
   private static final String BUNDLE_NAME = "org.glassfish.jersey.servlet.internal.localization";
   private static final LocalizableMessageFactory MESSAGE_FACTORY = new LocalizableMessageFactory("org.glassfish.jersey.servlet.internal.localization", new BundleSupplier());
   private static final Localizer LOCALIZER = new Localizer();

   private LocalizationMessages() {
   }

   public static Localizable localizableHEADER_VALUE_READ_FAILED() {
      return MESSAGE_FACTORY.getMessage("header.value.read.failed", new Object[0]);
   }

   public static String HEADER_VALUE_READ_FAILED() {
      return LOCALIZER.localize(localizableHEADER_VALUE_READ_FAILED());
   }

   public static Localizable localizableFILTER_CONTEXT_PATH_MISSING() {
      return MESSAGE_FACTORY.getMessage("filter.context.path.missing", new Object[0]);
   }

   public static String FILTER_CONTEXT_PATH_MISSING() {
      return LOCALIZER.localize(localizableFILTER_CONTEXT_PATH_MISSING());
   }

   public static Localizable localizableNO_THREAD_LOCAL_VALUE(Object arg0) {
      return MESSAGE_FACTORY.getMessage("no.thread.local.value", new Object[]{arg0});
   }

   public static String NO_THREAD_LOCAL_VALUE(Object arg0) {
      return LOCALIZER.localize(localizableNO_THREAD_LOCAL_VALUE(arg0));
   }

   public static Localizable localizableSERVLET_PATH_MISMATCH(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("servlet.path.mismatch", new Object[]{arg0, arg1});
   }

   public static String SERVLET_PATH_MISMATCH(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableSERVLET_PATH_MISMATCH(arg0, arg1));
   }

   public static Localizable localizableRESOURCE_CONFIG_UNABLE_TO_LOAD(Object arg0) {
      return MESSAGE_FACTORY.getMessage("resource.config.unable.to.load", new Object[]{arg0});
   }

   public static String RESOURCE_CONFIG_UNABLE_TO_LOAD(Object arg0) {
      return LOCALIZER.localize(localizableRESOURCE_CONFIG_UNABLE_TO_LOAD(arg0));
   }

   public static Localizable localizablePERSISTENCE_UNIT_NOT_CONFIGURED(Object arg0) {
      return MESSAGE_FACTORY.getMessage("persistence.unit.not.configured", new Object[]{arg0});
   }

   public static String PERSISTENCE_UNIT_NOT_CONFIGURED(Object arg0) {
      return LOCALIZER.localize(localizablePERSISTENCE_UNIT_NOT_CONFIGURED(arg0));
   }

   public static Localizable localizableEXCEPTION_SENDING_ERROR_RESPONSE(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("exception.sending.error.response", new Object[]{arg0, arg1});
   }

   public static String EXCEPTION_SENDING_ERROR_RESPONSE(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableEXCEPTION_SENDING_ERROR_RESPONSE(arg0, arg1));
   }

   public static Localizable localizableFORM_PARAM_CONSUMED(Object arg0) {
      return MESSAGE_FACTORY.getMessage("form.param.consumed", new Object[]{arg0});
   }

   public static String FORM_PARAM_CONSUMED(Object arg0) {
      return LOCALIZER.localize(localizableFORM_PARAM_CONSUMED(arg0));
   }

   public static Localizable localizableSERVLET_REQUEST_SUSPEND_FAILED() {
      return MESSAGE_FACTORY.getMessage("servlet.request.suspend.failed", new Object[0]);
   }

   public static String SERVLET_REQUEST_SUSPEND_FAILED() {
      return LOCALIZER.localize(localizableSERVLET_REQUEST_SUSPEND_FAILED());
   }

   public static Localizable localizableINIT_PARAM_REGEX_SYNTAX_INVALID(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("init.param.regex.syntax.invalid", new Object[]{arg0, arg1});
   }

   public static String INIT_PARAM_REGEX_SYNTAX_INVALID(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableINIT_PARAM_REGEX_SYNTAX_INVALID(arg0, arg1));
   }

   public static Localizable localizableASYNC_PROCESSING_NOT_SUPPORTED() {
      return MESSAGE_FACTORY.getMessage("async.processing.not.supported", new Object[0]);
   }

   public static String ASYNC_PROCESSING_NOT_SUPPORTED() {
      return LOCALIZER.localize(localizableASYNC_PROCESSING_NOT_SUPPORTED());
   }

   public static Localizable localizableRESOURCE_CONFIG_PARENT_CLASS_INVALID(Object arg0, Object arg1) {
      return MESSAGE_FACTORY.getMessage("resource.config.parent.class.invalid", new Object[]{arg0, arg1});
   }

   public static String RESOURCE_CONFIG_PARENT_CLASS_INVALID(Object arg0, Object arg1) {
      return LOCALIZER.localize(localizableRESOURCE_CONFIG_PARENT_CLASS_INVALID(arg0, arg1));
   }

   private static class BundleSupplier implements LocalizableMessageFactory.ResourceBundleSupplier {
      private BundleSupplier() {
      }

      public ResourceBundle getResourceBundle(Locale locale) {
         return ResourceBundle.getBundle("org.glassfish.jersey.servlet.internal.localization", locale);
      }
   }
}

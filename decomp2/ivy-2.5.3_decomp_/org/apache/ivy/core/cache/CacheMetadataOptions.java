package org.apache.ivy.core.cache;

import org.apache.ivy.plugins.namespace.Namespace;

public class CacheMetadataOptions extends CacheDownloadOptions {
   private boolean validate = false;
   private Namespace namespace;
   private Boolean isCheckmodified;
   private String changingMatcherName;
   private String changingPattern;
   private boolean checkTTL;
   private boolean useCacheOnly;

   public CacheMetadataOptions() {
      this.namespace = Namespace.SYSTEM_NAMESPACE;
      this.isCheckmodified = null;
      this.changingMatcherName = null;
      this.changingPattern = null;
      this.checkTTL = true;
      this.useCacheOnly = false;
   }

   public Namespace getNamespace() {
      return this.namespace;
   }

   public CacheMetadataOptions setNamespace(Namespace namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean isValidate() {
      return this.validate;
   }

   public CacheMetadataOptions setValidate(boolean validate) {
      this.validate = validate;
      return this;
   }

   public Boolean isCheckmodified() {
      return this.isCheckmodified;
   }

   public CacheMetadataOptions setCheckmodified(Boolean isCheckmodified) {
      this.isCheckmodified = isCheckmodified;
      return this;
   }

   public String getChangingMatcherName() {
      return this.changingMatcherName;
   }

   public CacheMetadataOptions setChangingMatcherName(String changingMatcherName) {
      this.changingMatcherName = changingMatcherName;
      return this;
   }

   public String getChangingPattern() {
      return this.changingPattern;
   }

   public CacheMetadataOptions setChangingPattern(String changingPattern) {
      this.changingPattern = changingPattern;
      return this;
   }

   public CacheMetadataOptions setCheckTTL(boolean checkTTL) {
      this.checkTTL = checkTTL;
      return this;
   }

   public boolean isCheckTTL() {
      return this.checkTTL;
   }

   public CacheMetadataOptions setUseCacheOnly(boolean useCacheOnly) {
      this.useCacheOnly = useCacheOnly;
      return this;
   }

   public boolean isUseCacheOnly() {
      return this.useCacheOnly;
   }
}

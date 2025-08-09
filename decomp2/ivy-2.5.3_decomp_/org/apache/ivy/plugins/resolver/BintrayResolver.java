package org.apache.ivy.plugins.resolver;

import org.apache.ivy.util.StringUtils;

public class BintrayResolver extends IBiblioResolver {
   private static final String JCENTER = "https://jcenter.bintray.com/";
   private static final String DL_BINTRAY = "https://dl.bintray.com/";
   private static final String DEFAULT_NAME = "bintray/jcenter";
   private String subject;
   private String repo;
   private boolean isNameUpdatable;

   public BintrayResolver() {
      this.setRoot("https://jcenter.bintray.com/");
      this.updateName("bintray/jcenter");
      this.setM2compatible(true);
      this.setUsepoms(true);
      this.setUseMavenMetadata(true);
   }

   public void setSubject(String subject) {
      this.subject = subject;
      this.updateRoot();
   }

   public void setRepo(String repo) {
      this.repo = repo;
      this.updateRoot();
   }

   private void updateRoot() {
      if (!StringUtils.isNullOrEmpty(this.subject) && !StringUtils.isNullOrEmpty(this.repo)) {
         this.setRoot(String.format("%s%s/%s/", "https://dl.bintray.com/", this.subject, this.repo));
         this.updateName(String.format("bintray/%s/%s", this.subject, this.repo));
      }
   }

   private void updateName(String defaultName) {
      if (StringUtils.isNullOrEmpty(defaultName)) {
         throw new IllegalArgumentException("Default resolver name must not be null or empty");
      } else {
         if (StringUtils.isNullOrEmpty(this.getName()) || this.isNameUpdatable) {
            this.isNameUpdatable = true;
            this.setName(defaultName);
         }

      }
   }
}

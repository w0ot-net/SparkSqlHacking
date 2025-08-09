package org.apache.ivy.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class IvyConfigure extends Task {
   public static final String OVERRIDE_TRUE = "true";
   public static final String OVERRIDE_FALSE = "false";
   public static final String OVERRIDE_NOT_ALLOWED = "notallowed";
   private static final Collection OVERRIDE_VALUES = Arrays.asList("true", "false", "notallowed");
   private String override = "notallowed";
   private IvyAntSettings settings = new IvyAntSettings();

   public void setSettingsId(String settingsId) {
      this.settings.setId(settingsId);
   }

   public String getSettingsId() {
      return this.settings.getId();
   }

   public void setOverride(String override) {
      if (!OVERRIDE_VALUES.contains(override)) {
         throw new IllegalArgumentException("invalid override value '" + override + "'. Valid values are " + OVERRIDE_VALUES);
      } else {
         this.override = override;
      }
   }

   public String getOverride() {
      return this.override;
   }

   public File getFile() {
      return this.settings.getFile();
   }

   public void setFile(File file) {
      this.settings.setFile(file);
   }

   public URL getUrl() {
      return this.settings.getUrl();
   }

   public void setUrl(String url) throws MalformedURLException {
      this.settings.setUrl(url);
   }

   public void setUrl(URL url) {
      if (url == null) {
         throw new NullPointerException("Cannot set a null URL");
      } else {
         this.settings.setUrl(url);
      }
   }

   public String getRealm() {
      return this.settings.getRealm();
   }

   public void setRealm(String realm) {
      this.settings.setRealm(realm);
   }

   public String getHost() {
      return this.settings.getHost();
   }

   public void setHost(String host) {
      this.settings.setHost(host);
   }

   public String getUserName() {
      return this.settings.getUsername();
   }

   public void setUserName(String userName) {
      this.settings.setUsername(userName);
   }

   public String getPasswd() {
      return this.settings.getPasswd();
   }

   public void setPasswd(String passwd) {
      this.settings.setPasswd(passwd);
   }

   public void addConfiguredWorkspaceResolver(AntWorkspaceResolver resolver) {
      this.settings.addConfiguredWorkspaceResolver(resolver);
   }

   public void execute() throws BuildException {
      String settingsId = this.settings.getId();
      Object otherRef = this.getProject().getReference(settingsId);
      if (otherRef != null && "notallowed".equals(this.override)) {
         throw new BuildException("Overriding a previous definition of ivy:settings with the id '" + settingsId + "' is not allowed when using override='" + "notallowed" + "'.");
      } else if (otherRef != null && "false".equals(this.override)) {
         this.verbose("A settings definition is already available for " + settingsId + ": skipping");
      } else {
         this.settings.setProject(this.getProject());
         this.getProject().addReference(settingsId, this.settings);
         this.settings.createIvyEngine(this);
      }
   }

   private void verbose(String msg) {
      this.log(msg, 3);
   }
}

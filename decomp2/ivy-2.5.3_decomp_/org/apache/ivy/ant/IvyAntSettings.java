package org.apache.ivy.ant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.core.settings.IvyVariableContainer;
import org.apache.ivy.plugins.resolver.AbstractWorkspaceResolver;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.url.CredentialsStore;
import org.apache.ivy.util.url.TimeoutConstrainedURLHandler;
import org.apache.ivy.util.url.URLHandlerDispatcher;
import org.apache.ivy.util.url.URLHandlerRegistry;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Property;
import org.apache.tools.ant.types.DataType;

public class IvyAntSettings extends DataType {
   private Ivy ivyEngine = null;
   private File file = null;
   private URL url = null;
   private String realm = null;
   private String host = null;
   private String userName = null;
   private String passwd = null;
   private String id = "ivy.instance";
   private boolean autoRegistered = false;
   private AntWorkspaceResolver antWorkspaceResolver;

   public static IvyAntSettings getDefaultInstance(ProjectComponent task) {
      Project project = task.getProject();
      Object defaultInstanceObj = project.getReference("ivy.instance");
      if (defaultInstanceObj != null && defaultInstanceObj.getClass().getClassLoader() != IvyAntSettings.class.getClassLoader()) {
         task.log("ivy.instance reference an ivy:settings defined in an other classloader.  An new default one will be used in this project.", 1);
         defaultInstanceObj = null;
      }

      if (defaultInstanceObj != null && !(defaultInstanceObj instanceof IvyAntSettings)) {
         throw new BuildException("ivy.instance reference a " + defaultInstanceObj.getClass().getName() + " an not an IvyAntSettings.  Please don't use this reference id ()");
      } else if (defaultInstanceObj == null) {
         task.log("No ivy:settings found for the default reference 'ivy.instance'.  A default instance will be used", 3);
         IvyAntSettings settings = new IvyAntSettings();
         settings.setProject(project);
         project.addReference("ivy.instance", settings);
         settings.createIvyEngine(task);
         return settings;
      } else {
         return (IvyAntSettings)defaultInstanceObj;
      }
   }

   public static IvyAntSettings getDefaultInstance(Task task) {
      return getDefaultInstance((ProjectComponent)task);
   }

   public File getFile() {
      return this.file;
   }

   public URL getUrl() {
      return this.url;
   }

   public String getPasswd() {
      return this.passwd;
   }

   public void setPasswd(String aPasswd) {
      this.passwd = aPasswd;
   }

   public String getRealm() {
      return this.realm;
   }

   public void setRealm(String aRealm) {
      this.realm = format(aRealm);
   }

   public String getHost() {
      return this.host;
   }

   public void setHost(String aHost) {
      this.host = format(aHost);
   }

   public String getUsername() {
      return this.userName;
   }

   public void setUsername(String aUserName) {
      this.userName = format(aUserName);
   }

   public void setProject(Project p) {
      super.setProject(p);
      if ("ivy.instance".equals(this.id) && !this.getProject().getReferences().containsKey(this.id)) {
         this.getProject().addReference("ivy.instance", this);
         this.autoRegistered = true;
      }

   }

   private static String format(String str) {
      return StringUtils.isNullOrEmpty(str) ? null : str.trim();
   }

   public void addConfiguredCredentials(Credentials c) {
      CredentialsStore.INSTANCE.addCredentials(c.getRealm(), c.getHost(), c.getUsername(), c.getPasswd());
   }

   public void setFile(File file) {
      this.file = file;
   }

   public void setUrl(String confUrl) throws MalformedURLException {
      this.url = new URL(confUrl);
   }

   public void setUrl(URL url) {
      this.url = url;
   }

   public void setId(String id) {
      if (this.autoRegistered && this.getProject().getReference(this.id) == this) {
         this.getProject().getReferences().remove(this.id);
         this.autoRegistered = false;
      }

      this.id = id;
      if (this.getProject() != null) {
         this.getProject().addReference(this.id, this);
      }

   }

   public String getId() {
      return this.id;
   }

   public Ivy getConfiguredIvyInstance(ProjectComponent task) {
      if (this.ivyEngine == null) {
         this.createIvyEngine(task);
      }

      return this.ivyEngine;
   }

   public Ivy getConfiguredIvyInstance(Task task) {
      return this.getConfiguredIvyInstance((ProjectComponent)task);
   }

   void createIvyEngine(final ProjectComponent task) {
      Project project = task.getProject();
      Property prop = new Property() {
         public void execute() throws BuildException {
            this.addProperties(IvyAntSettings.this.getDefaultProperties(task));
         }
      };
      prop.setProject(project);
      prop.init();
      prop.execute();
      IvyAntVariableContainer ivyAntVariableContainer = new IvyAntVariableContainer(project);
      IvySettings settings = new IvySettings(ivyAntVariableContainer);
      settings.setBaseDir(project.getBaseDir());
      if (this.file == null && this.url == null) {
         this.defineDefaultSettingFile(ivyAntVariableContainer, task);
      }

      if (this.antWorkspaceResolver != null) {
         settings.addConfigured((AbstractWorkspaceResolver)this.antWorkspaceResolver.getResolver());
      }

      Ivy ivy = Ivy.newInstance(settings);

      try {
         ivy.pushContext();
         AntMessageLogger.register(task, ivy);
         Message.showInfo();
         this.configureURLHandler();
         if (this.file != null) {
            if (!this.file.exists()) {
               throw new BuildException("settings file does not exist: " + this.file);
            }

            ivy.configure(this.file);
         } else {
            if (this.url == null) {
               throw new AssertionError("ivy setting should have either a file, either an url, and if not defineDefaultSettingFile must set it.");
            }

            ivy.configure(this.url);
         }

         ivyAntVariableContainer.updateProject(this.id);
         this.ivyEngine = ivy;
      } catch (IOException | ParseException e) {
         throw new BuildException("impossible to configure ivy:settings with given " + (this.file != null ? "file: " + this.file : "url: " + this.url) + " : " + e, e);
      } finally {
         ivy.popContext();
      }

   }

   protected Properties getDefaultProperties(ProjectComponent task) {
      URL url = IvySettings.getDefaultPropertiesURL();
      Properties props = new Properties();
      task.log("Loading " + url, 3);

      try {
         InputStream is = url.openStream();
         Throwable var5 = null;

         try {
            props.load(is);
         } catch (Throwable var15) {
            var5 = var15;
            throw var15;
         } finally {
            if (is != null) {
               if (var5 != null) {
                  try {
                     is.close();
                  } catch (Throwable var14) {
                     var5.addSuppressed(var14);
                  }
               } else {
                  is.close();
               }
            }

         }

         return props;
      } catch (IOException ex) {
         throw new BuildException(ex);
      }
   }

   private void defineDefaultSettingFile(IvyVariableContainer variableContainer, ProjectComponent task) {
      String settingsFileName = variableContainer.getVariable("ivy.conf.file");
      if (settingsFileName != null && !settingsFileName.equals(variableContainer.getVariable("ivy.settings.file"))) {
         task.log("DEPRECATED: 'ivy.conf.file' is deprecated, use 'ivy.settings.file' instead", 2);
      } else {
         settingsFileName = variableContainer.getVariable("ivy.settings.file");
      }

      File[] settingsLocations = new File[]{new File(this.getProject().getBaseDir(), settingsFileName), new File(this.getProject().getBaseDir(), "ivyconf.xml"), new File(settingsFileName), new File("ivyconf.xml")};

      for(File settingsFile : settingsLocations) {
         task.log("searching settings file: trying " + settingsFile, 3);
         if (settingsFile.exists()) {
            this.file = settingsFile;
            break;
         }
      }

      if (this.file == null) {
         if (Boolean.valueOf(this.getProject().getProperty("ivy.14.compatible"))) {
            task.log("no settings file found, using Ivy 1.4 default...", 3);
            this.url = IvySettings.getDefault14SettingsURL();
         } else {
            String settingsFileUrl = variableContainer.getVariable("ivy.settings.url");
            if (settingsFileUrl != null) {
               try {
                  this.url = new URL(settingsFileUrl);
               } catch (MalformedURLException e) {
                  throw new BuildException("Impossible to configure ivy:settings with given url: " + settingsFileUrl + ": " + e.getMessage(), e);
               }
            } else {
               task.log("no settings file found, using default...", 3);
               this.url = IvySettings.getDefaultSettingsURL();
            }
         }
      }

   }

   private void configureURLHandler() {
      CredentialsStore.INSTANCE.addCredentials(this.getRealm(), this.getHost(), this.getUsername(), this.getPasswd());
      URLHandlerDispatcher dispatcher = new URLHandlerDispatcher();
      TimeoutConstrainedURLHandler httpHandler = URLHandlerRegistry.getHttp();
      dispatcher.setDownloader("http", httpHandler);
      dispatcher.setDownloader("https", httpHandler);
      URLHandlerRegistry.setDefault(dispatcher);
   }

   public void addConfiguredWorkspaceResolver(AntWorkspaceResolver antWorkspaceResolver) {
      this.antWorkspaceResolver = antWorkspaceResolver;
   }

   public static class Credentials {
      private String realm;
      private String host;
      private String username;
      private String passwd;

      public String getPasswd() {
         return this.passwd;
      }

      public void setPasswd(String passwd) {
         this.passwd = passwd;
      }

      public String getRealm() {
         return this.realm;
      }

      public void setRealm(String realm) {
         this.realm = IvyAntSettings.format(realm);
      }

      public String getHost() {
         return this.host;
      }

      public void setHost(String host) {
         this.host = IvyAntSettings.format(host);
      }

      public String getUsername() {
         return this.username;
      }

      public void setUsername(String userName) {
         this.username = IvyAntSettings.format(userName);
      }
   }
}

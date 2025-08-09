package org.apache.ivy.osgi.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public class ExecutionEnvironmentProfileProvider {
   private static final String DEFAULT_PROFILES_FILE = "jvm-packages.properties";
   private static final String PACKAGE_PREFIX = "org/apache/ivy/osgi/core/";
   private Map profileList = loadDefaultProfileList();
   private static final ExecutionEnvironmentProfileProvider INSTANCE;

   public static ExecutionEnvironmentProfileProvider getInstance() {
      return INSTANCE;
   }

   public ExecutionEnvironmentProfileProvider() throws IOException {
   }

   public ExecutionEnvironmentProfile getProfile(String profile) {
      return (ExecutionEnvironmentProfile)this.profileList.get(profile);
   }

   public static Map loadDefaultProfileList() throws IOException {
      ClassLoader loader = ExecutionEnvironmentProfileProvider.class.getClassLoader();
      InputStream defaultProfilesFile = loader.getResourceAsStream("org/apache/ivy/osgi/core/jvm-packages.properties");
      if (defaultProfilesFile == null) {
         throw new FileNotFoundException("org/apache/ivy/osgi/core/jvm-packages.properties not found in the classpath");
      } else {
         Properties props = new Properties();

         try {
            props.load(defaultProfilesFile);
         } finally {
            defaultProfilesFile.close();
         }

         HashMap profiles = new HashMap();

         for(Map.Entry prop : props.entrySet()) {
            String propName = (String)prop.getKey();
            if (propName.endsWith(".pkglist")) {
               String profileName = propName.substring(0, propName.length() - 8);
               if (!profiles.containsKey(profileName)) {
                  loadProfile(props, profiles, profileName);
               }
            }
         }

         return profiles;
      }
   }

   private static ExecutionEnvironmentProfile loadProfile(Properties props, Map profiles, String name) {
      ExecutionEnvironmentProfile profile = new ExecutionEnvironmentProfile(name);
      String extendedProfileName = props.getProperty(name + ".extends");
      if (extendedProfileName != null) {
         ExecutionEnvironmentProfile extendedProfile = (ExecutionEnvironmentProfile)profiles.get(extendedProfileName);
         if (extendedProfile == null) {
            extendedProfile = loadProfile(props, profiles, extendedProfileName);
         }

         profile.pkgNames.addAll(extendedProfile.pkgNames);
      }

      String pkgList = props.getProperty(name + ".pkglist");

      for(String pkg : StringUtils.splitToArray(pkgList)) {
         if (!pkg.isEmpty()) {
            profile.pkgNames.add(pkg);
         }
      }

      profiles.put(name, profile);
      String aliasList = props.getProperty(name + ".aliases");
      if (aliasList != null) {
         for(String alias : StringUtils.splitToArray(aliasList)) {
            if (!alias.isEmpty()) {
               ExecutionEnvironmentProfile profileAlias = new ExecutionEnvironmentProfile(alias);
               profileAlias.pkgNames = profile.pkgNames;
               profiles.put(alias, profileAlias);
            }
         }
      }

      Message.verbose("Execution environment profile " + profile.getName() + " loaded");
      return profile;
   }

   static {
      try {
         INSTANCE = new ExecutionEnvironmentProfileProvider();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}

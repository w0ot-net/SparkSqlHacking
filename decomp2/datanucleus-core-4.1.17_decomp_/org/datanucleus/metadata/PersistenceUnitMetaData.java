package org.datanucleus.metadata;

import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PersistenceUnitMetaData extends MetaData {
   private static final long serialVersionUID = 6021663206256915679L;
   String name = null;
   URI rootURI = null;
   TransactionType transactionType = null;
   String description = null;
   String provider = null;
   String validationMode = null;
   String jtaDataSource = null;
   String nonJtaDataSource = null;
   Set classNames = null;
   Set jarFiles = null;
   Set mappingFileNames = null;
   Properties properties = null;
   boolean excludeUnlistedClasses = false;
   String caching = "UNSPECIFIED";

   public PersistenceUnitMetaData(String name, String transactionType, URI rootURI) {
      this.name = name;
      this.transactionType = TransactionType.getValue(transactionType);
      this.rootURI = rootURI;
   }

   public String getName() {
      return this.name;
   }

   public URI getRootURI() {
      return this.rootURI;
   }

   public TransactionType getTransactionType() {
      return this.transactionType;
   }

   public String getCaching() {
      return this.caching;
   }

   public void setCaching(String cache) {
      this.caching = cache;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String desc) {
      this.description = desc;
   }

   public String getProvider() {
      return this.provider;
   }

   public void setProvider(String provider) {
      this.provider = provider;
   }

   public String getJtaDataSource() {
      return this.jtaDataSource;
   }

   public void setJtaDataSource(String data) {
      this.jtaDataSource = data;
   }

   public String getNonJtaDataSource() {
      return this.nonJtaDataSource;
   }

   public void setNonJtaDataSource(String data) {
      this.nonJtaDataSource = data;
   }

   public void setValidationMode(String validationMode) {
      this.validationMode = validationMode;
   }

   public String getValidationMode() {
      return this.validationMode;
   }

   public void setExcludeUnlistedClasses() {
      this.excludeUnlistedClasses = true;
   }

   public void setExcludeUnlistedClasses(boolean flag) {
      this.excludeUnlistedClasses = flag;
   }

   public boolean getExcludeUnlistedClasses() {
      return this.excludeUnlistedClasses;
   }

   public void addClassName(String className) {
      if (this.classNames == null) {
         this.classNames = new HashSet();
      }

      this.classNames.add(className);
   }

   public void addClassNames(Set classNames) {
      if (this.classNames == null) {
         this.classNames = new HashSet();
      }

      this.classNames.addAll(classNames);
   }

   public void addJarFile(String jarName) {
      if (this.jarFiles == null) {
         this.jarFiles = new HashSet();
      }

      this.jarFiles.add(jarName);
   }

   public void addJarFiles(Set jarNames) {
      if (this.jarFiles == null) {
         this.jarFiles = new HashSet();
      }

      this.jarFiles.addAll(jarNames);
   }

   public void addJarFile(URL jarURL) {
      if (this.jarFiles == null) {
         this.jarFiles = new HashSet();
      }

      this.jarFiles.add(jarURL);
   }

   public void clearJarFiles() {
      if (this.jarFiles != null) {
         this.jarFiles.clear();
      }

      this.jarFiles = null;
   }

   public void addMappingFile(String mappingFile) {
      if (this.mappingFileNames == null) {
         this.mappingFileNames = new HashSet();
      }

      this.mappingFileNames.add(mappingFile);
   }

   public void addProperty(String key, String value) {
      if (key != null && value != null) {
         if (this.properties == null) {
            this.properties = new Properties();
         }

         this.properties.setProperty(key, value);
      }
   }

   public Set getClassNames() {
      return this.classNames;
   }

   public Set getMappingFiles() {
      return this.mappingFileNames;
   }

   public Set getJarFiles() {
      return this.jarFiles;
   }

   public Properties getProperties() {
      return this.properties;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<persistence-unit name=\"" + this.name + "\"");
      if (this.transactionType != null) {
         sb.append(" transaction-type=\"" + this.transactionType + "\"");
      }

      sb.append(">\n");
      if (this.description != null) {
         sb.append(prefix).append(indent).append("<description>" + this.description + "</description>\n");
      }

      if (this.provider != null) {
         sb.append(prefix).append(indent).append("<provider>" + this.provider + "</provider>\n");
      }

      if (this.jtaDataSource != null) {
         sb.append(prefix).append(indent).append("<jta-data-source>" + this.jtaDataSource + "</jta-data-source>\n");
      }

      if (this.nonJtaDataSource != null) {
         sb.append(prefix).append(indent).append("<non-jta-data-source>" + this.nonJtaDataSource + "</non-jta-data-source>\n");
      }

      if (this.classNames != null) {
         for(String className : this.classNames) {
            sb.append(prefix).append(indent).append("<class>" + className + "</class>\n");
         }
      }

      if (this.mappingFileNames != null) {
         for(String mappingFileName : this.mappingFileNames) {
            sb.append(prefix).append(indent).append("<mapping-file>" + mappingFileName + "</mapping-file>\n");
         }
      }

      if (this.jarFiles != null) {
         for(Object jarFile : this.jarFiles) {
            sb.append(prefix).append(indent).append("<jar-file>" + jarFile + "</jar-file>\n");
         }
      }

      if (this.properties != null) {
         sb.append(prefix).append(indent).append("<properties>\n");

         for(Map.Entry entry : this.properties.entrySet()) {
            sb.append(prefix).append(indent).append(indent).append("<property name=" + entry.getKey() + " value=" + entry.getValue() + "</property>\n");
         }

         sb.append(prefix).append(indent).append("</properties>\n");
      }

      if (this.excludeUnlistedClasses) {
         sb.append(prefix).append(indent).append("<exclude-unlisted-classes/>\n");
      }

      sb.append(prefix).append("</persistence-unit>\n");
      return sb.toString();
   }
}

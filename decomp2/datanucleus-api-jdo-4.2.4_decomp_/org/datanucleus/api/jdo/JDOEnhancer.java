package org.datanucleus.api.jdo;

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Properties;
import javax.jdo.metadata.JDOMetadata;
import org.datanucleus.api.jdo.metadata.JDOMetadataImpl;
import org.datanucleus.enhancer.DataNucleusEnhancer;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.MetaDataManager;

public class JDOEnhancer implements javax.jdo.JDOEnhancer {
   DataNucleusEnhancer enhancer;

   public JDOEnhancer() {
      this.enhancer = new DataNucleusEnhancer("JDO", (Properties)null);
   }

   public JDOEnhancer(Properties props) {
      this.enhancer = new DataNucleusEnhancer("JDO", props);
   }

   public JDOMetadata newMetadata() {
      return new JDOMetadataImpl();
   }

   public void registerMetadata(JDOMetadata metadata) {
      MetaDataManager mmgr = this.enhancer.getMetaDataManager();
      FileMetaData filemd = ((JDOMetadataImpl)metadata).getInternal();
      mmgr.loadUserMetaData(filemd, this.enhancer.getClassLoader());
   }

   public JDOEnhancer addClass(String className, byte[] bytes) {
      this.enhancer.addClass(className, bytes);
      return this;
   }

   public JDOEnhancer addClasses(String... classNames) {
      this.enhancer.addClasses(classNames);
      return this;
   }

   public JDOEnhancer addFiles(String... metadataFiles) {
      this.enhancer.addFiles(metadataFiles);
      return this;
   }

   public JDOEnhancer addJar(String jarFileName) {
      this.enhancer.addJar(jarFileName);
      return this;
   }

   public JDOEnhancer addPersistenceUnit(String persistenceUnitName) {
      this.enhancer.addPersistenceUnit(persistenceUnitName);
      return this;
   }

   public int enhance() {
      try {
         return this.enhancer.enhance();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public byte[] getEnhancedBytes(String className) {
      try {
         return this.enhancer.getEnhancedBytes(className);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public byte[] getPkClassBytes(String className) {
      try {
         return this.enhancer.getPkClassBytes(className);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Properties getProperties() {
      return this.enhancer.getProperties();
   }

   public JDOEnhancer setClassLoader(ClassLoader loader) {
      this.enhancer.setClassLoader(loader);
      return this;
   }

   public JDOEnhancer setOutputDirectory(String dir) {
      this.enhancer.setOutputDirectory(dir);
      return this;
   }

   public JDOEnhancer setVerbose(boolean verbose) {
      this.enhancer.setVerbose(verbose);
      return this;
   }

   public int validate() {
      try {
         return this.enhancer.validate();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
      return null;
   }
}

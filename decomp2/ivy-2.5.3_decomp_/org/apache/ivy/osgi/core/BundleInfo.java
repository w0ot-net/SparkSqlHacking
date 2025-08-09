package org.apache.ivy.osgi.core;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.osgi.util.VersionRange;

public class BundleInfo {
   public static final Version DEFAULT_VERSION = new Version(1, 0, 0, (String)null);
   public static final String PACKAGE_TYPE = "package";
   public static final String BUNDLE_TYPE = "bundle";
   public static final String EXECUTION_ENVIRONMENT_TYPE = "ee";
   public static final String SERVICE_TYPE = "service";
   private String symbolicName;
   private String presentationName;
   private String id;
   private Version version;
   private Set requirements = new LinkedHashSet();
   private Set capabilities = new LinkedHashSet();
   private List executionEnvironments = new ArrayList();
   private String description;
   private String documentation;
   private String license;
   private Integer size;
   private boolean isSource = false;
   private String symbolicNameTarget;
   private Version versionTarget;
   private boolean hasInnerClasspath;
   private List classpath;
   private List artifacts = new ArrayList();

   public BundleInfo(String name, Version version) {
      this.symbolicName = name;
      this.version = version;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("BundleInfo [executionEnvironments=");
      builder.append(this.executionEnvironments);
      builder.append(", capabilities=");
      builder.append(this.capabilities);
      builder.append(", requirements=");
      builder.append(this.requirements);
      builder.append(", symbolicName=");
      builder.append(this.symbolicName);
      builder.append(", version=");
      builder.append(this.version);
      builder.append("]");
      if (this.symbolicNameTarget != null) {
         builder.append(" source of ");
         builder.append(this.symbolicNameTarget);
         builder.append("@");
         builder.append(this.versionTarget);
      }

      return builder.toString();
   }

   public String getSymbolicName() {
      return this.symbolicName;
   }

   public Version getVersion() {
      return this.version == null ? DEFAULT_VERSION : this.version;
   }

   public Version getRawVersion() {
      return this.version;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void setPresentationName(String presentationName) {
      this.presentationName = presentationName;
   }

   public String getPresentationName() {
      return this.presentationName;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDocumentation(String documentation) {
      this.documentation = documentation;
   }

   public String getDocumentation() {
      return this.documentation;
   }

   public void setLicense(String license) {
      this.license = license;
   }

   public String getLicense() {
      return this.license;
   }

   public void setSize(Integer size) {
      this.size = size;
   }

   public Integer getSize() {
      return this.size;
   }

   public void addRequirement(BundleRequirement requirement) {
      this.requirements.add(requirement);
   }

   public Set getRequirements() {
      return this.requirements;
   }

   public void addCapability(BundleCapability capability) {
      this.capabilities.add(capability);
   }

   public Set getCapabilities() {
      return this.capabilities;
   }

   public List getExecutionEnvironments() {
      return this.executionEnvironments;
   }

   public void setExecutionEnvironments(List executionEnvironments) {
      this.executionEnvironments = executionEnvironments;

      for(String executionEnvironment : executionEnvironments) {
         this.addRequirement(new BundleRequirement("ee", executionEnvironment, (VersionRange)null, (String)null));
      }

   }

   public void addExecutionEnvironment(String name) {
      this.executionEnvironments.add(name);
   }

   public void setSource(boolean isSource) {
      this.isSource = isSource;
   }

   public boolean isSource() {
      return this.isSource;
   }

   public void setSymbolicNameTarget(String symbolicNameTarget) {
      this.symbolicNameTarget = symbolicNameTarget;
   }

   public String getSymbolicNameTarget() {
      return this.symbolicNameTarget;
   }

   public void setVersionTarget(Version versionTarget) {
      this.versionTarget = versionTarget;
   }

   public Version getVersionTarget() {
      return this.versionTarget;
   }

   public void setHasInnerClasspath(boolean hasInnerClasspath) {
      this.hasInnerClasspath = hasInnerClasspath;
   }

   public boolean hasInnerClasspath() {
      return this.hasInnerClasspath;
   }

   public void setClasspath(List classpath) {
      this.classpath = classpath;
   }

   public List getClasspath() {
      return this.classpath;
   }

   public void addArtifact(BundleArtifact artifact) {
      this.artifacts.add(artifact);
   }

   public void removeArtifact(BundleArtifact same) {
      this.artifacts.remove(same);
   }

   public List getArtifacts() {
      return this.artifacts;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.capabilities == null ? 0 : this.capabilities.hashCode());
      result = 31 * result + (this.requirements == null ? 0 : this.requirements.hashCode());
      result = 31 * result + (this.symbolicName == null ? 0 : this.symbolicName.hashCode());
      result = 31 * result + (this.version == null ? 0 : this.version.hashCode());
      result = 31 * result + (this.executionEnvironments == null ? 0 : this.executionEnvironments.hashCode());
      result = 31 * result + (this.symbolicNameTarget == null ? 0 : this.symbolicNameTarget.hashCode());
      result = 31 * result + (this.versionTarget == null ? 0 : this.versionTarget.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof BundleInfo)) {
         return false;
      } else {
         BundleInfo other = (BundleInfo)obj;
         if (this.capabilities == null) {
            if (other.capabilities != null) {
               return false;
            }
         } else if (!this.capabilities.equals(other.capabilities)) {
            return false;
         }

         if (this.requirements == null) {
            if (other.requirements != null) {
               return false;
            }
         } else if (!this.requirements.equals(other.requirements)) {
            return false;
         }

         if (this.symbolicName == null) {
            if (other.symbolicName != null) {
               return false;
            }
         } else if (!this.symbolicName.equals(other.symbolicName)) {
            return false;
         }

         if (this.version == null) {
            if (other.version != null) {
               return false;
            }
         } else if (!this.version.equals(other.version)) {
            return false;
         }

         if (this.executionEnvironments == null) {
            if (other.executionEnvironments != null) {
               return false;
            }
         } else if (!this.executionEnvironments.equals(other.executionEnvironments)) {
            return false;
         }

         if (this.isSource != other.isSource) {
            return false;
         } else {
            if (this.symbolicNameTarget == null) {
               if (other.symbolicNameTarget != null) {
                  return false;
               }
            } else if (!this.symbolicNameTarget.equals(other.symbolicNameTarget)) {
               return false;
            }

            if (this.versionTarget == null) {
               if (other.versionTarget != null) {
                  return false;
               }
            } else if (!this.versionTarget.equals(other.versionTarget)) {
               return false;
            }

            boolean var10000;
            label109: {
               if (this.hasInnerClasspath == other.hasInnerClasspath) {
                  if (this.classpath == null) {
                     if (other.classpath == null) {
                        break label109;
                     }
                  } else if (this.classpath.equals(other.classpath)) {
                     break label109;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }
   }

   public Set getRequires() {
      Set<BundleRequirement> set = new LinkedHashSet();

      for(BundleRequirement requirement : this.requirements) {
         if (requirement.getType().equals("bundle")) {
            set.add(requirement);
         }
      }

      return set;
   }

   public Set getImports() {
      Set<BundleRequirement> set = new LinkedHashSet();

      for(BundleRequirement requirement : this.requirements) {
         if (requirement.getType().equals("package")) {
            set.add(requirement);
         }
      }

      return set;
   }

   public Set getExports() {
      Set<ExportPackage> set = new LinkedHashSet();

      for(BundleCapability capability : this.capabilities) {
         if ("package".equals(capability.getType())) {
            set.add((ExportPackage)capability);
         }
      }

      return set;
   }

   public Set getServices() {
      Set<BundleCapability> set = new LinkedHashSet();

      for(BundleCapability capability : this.capabilities) {
         if ("service".equals(capability.getType())) {
            set.add(capability);
         }
      }

      return set;
   }
}

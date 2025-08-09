package org.apache.ivy.osgi.core;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Manifest;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.ExtraInfoHolder;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.osgi.util.VersionRange;
import org.apache.ivy.plugins.matcher.ExactOrRegexpPatternMatcher;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.repository.Resource;

public class BundleInfoAdapter {
   public static final String CONF_NAME_DEFAULT = "default";
   public static final Configuration CONF_DEFAULT = new Configuration("default");
   public static final String CONF_NAME_OPTIONAL = "optional";
   public static final Configuration CONF_OPTIONAL;
   public static final String CONF_NAME_TRANSITIVE_OPTIONAL = "transitive-optional";
   public static final Configuration CONF_TRANSITIVE_OPTIONAL;
   public static final String CONF_USE_PREFIX = "use_";
   public static final String EXTRA_INFO_EXPORT_PREFIX = "_osgi_export_";

   public static DefaultModuleDescriptor toModuleDescriptor(ModuleDescriptorParser parser, URI baseUri, BundleInfo bundle, ExecutionEnvironmentProfileProvider profileProvider) {
      return toModuleDescriptor(parser, baseUri, bundle, (Manifest)null, profileProvider);
   }

   public static DefaultModuleDescriptor toModuleDescriptor(ModuleDescriptorParser parser, URI baseUri, BundleInfo bundle, Manifest manifest, ExecutionEnvironmentProfileProvider profileProvider) throws ProfileNotFoundException {
      DefaultModuleDescriptor md = new DefaultModuleDescriptor(parser, (Resource)null);
      md.addExtraAttributeNamespace("o", Ivy.getIvyHomeURL() + "osgi");
      ModuleRevisionId mrid = asMrid("bundle", bundle.getSymbolicName(), bundle.getVersion());
      md.setResolvedPublicationDate(new Date());
      md.setModuleRevisionId(mrid);
      md.addConfiguration(CONF_DEFAULT);
      md.addConfiguration(CONF_OPTIONAL);
      md.addConfiguration(CONF_TRANSITIVE_OPTIONAL);
      Set<String> exportedPkgNames = new HashSet(bundle.getExports().size());

      for(ExportPackage exportPackage : bundle.getExports()) {
         md.getExtraInfos().add(new ExtraInfoHolder("_osgi_export_" + exportPackage.getName(), exportPackage.getVersion().toString()));
         exportedPkgNames.add(exportPackage.getName());
         String[] confDependencies = new String[exportPackage.getUses().size() + 1];
         int i = 0;

         for(String use : exportPackage.getUses()) {
            confDependencies[i++] = "use_" + use;
         }

         confDependencies[i] = "default";
         md.addConfiguration(new Configuration("use_" + exportPackage.getName(), Configuration.Visibility.PUBLIC, "Exported package " + exportPackage.getName(), confDependencies, true, (String)null));
      }

      requirementAsDependency(md, bundle, exportedPkgNames);
      if (baseUri != null) {
         for(BundleArtifact bundleArtifact : bundle.getArtifacts()) {
            String type = "jar";
            String ext = "jar";
            String packaging = null;
            if (bundle.hasInnerClasspath() && !bundleArtifact.isSource()) {
               packaging = "bundle";
            }

            if ("packed".equals(bundleArtifact.getFormat())) {
               ext = "jar.pack.gz";
               if (packaging != null) {
                  packaging = packaging + ",pack200";
               } else {
                  packaging = "pack200";
               }
            }

            if (bundleArtifact.isSource()) {
               type = "source";
            }

            URI uri = bundleArtifact.getUri();
            if (uri != null) {
               DefaultArtifact artifact = buildArtifact(mrid, baseUri, uri, type, ext, packaging);
               md.addArtifact("default", artifact);
            }
         }
      }

      if (profileProvider != null) {
         for(String env : bundle.getExecutionEnvironments()) {
            ExecutionEnvironmentProfile profile = profileProvider.getProfile(env);
            if (profile == null) {
               throw new ProfileNotFoundException("Execution environment profile " + env + " not found");
            }

            for(String pkg : profile.getPkgNames()) {
               ArtifactId id = new ArtifactId(ModuleId.newInstance("package", pkg), "*", "*", "*");
               DefaultExcludeRule rule = new DefaultExcludeRule(id, ExactOrRegexpPatternMatcher.INSTANCE, (Map)null);

               for(String conf : md.getConfigurationsNames()) {
                  rule.addConfiguration(conf);
               }

               md.addExcludeRule(rule);
            }
         }
      }

      if (manifest != null) {
         for(Map.Entry entries : manifest.getMainAttributes().entrySet()) {
            md.addExtraInfo(new ExtraInfoHolder(entries.getKey().toString(), entries.getValue().toString()));
         }
      }

      return md;
   }

   public static DefaultArtifact buildArtifact(ModuleRevisionId mrid, URI baseUri, URI uri, String type, String ext, String packaging) {
      DefaultArtifact artifact;
      if ("ivy".equals(uri.getScheme())) {
         artifact = decodeIvyURI(uri);
      } else {
         if (!uri.isAbsolute()) {
            uri = baseUri.resolve(uri);
         }

         Map<String, String> extraAtt = new HashMap();
         if (packaging != null) {
            extraAtt.put("packaging", packaging);
         }

         try {
            artifact = new DefaultArtifact(mrid, (Date)null, mrid.getName(), type, ext, new URL(uri.toString()), extraAtt);
         } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to make the uri into the url", e);
         }
      }

      return artifact;
   }

   public static List getConfigurations(BundleInfo bundle) {
      List<String> confs = new ArrayList();
      confs.add("default");
      confs.add("optional");
      confs.add("transitive-optional");

      for(ExportPackage exportPackage : bundle.getExports()) {
         confs.add("use_" + exportPackage.getName());
      }

      return confs;
   }

   public static URI buildIvyURI(Artifact artifact) {
      ModuleRevisionId mrid = artifact.getModuleRevisionId();
      return asIvyURI(mrid.getOrganisation(), mrid.getName(), mrid.getBranch(), mrid.getRevision(), artifact.getType(), artifact.getName(), artifact.getExt());
   }

   private static URI asIvyURI(String org, String name, String branch, String rev, String type, String art, String ext) {
      StringBuilder builder = new StringBuilder();
      builder.append("ivy:///").append(org).append('/').append(name).append('?');
      if (branch != null) {
         builder.append("branch=").append(branch);
      }

      if (rev != null) {
         builder.append("&rev=").append(rev);
      }

      if (type != null) {
         builder.append("&type=").append(type);
      }

      if (art != null) {
         builder.append("&art=").append(art);
      }

      if (ext != null) {
         builder.append("&ext=").append(ext);
      }

      try {
         return new URI(builder.toString());
      } catch (URISyntaxException e) {
         throw new RuntimeException("ill-formed ivy url", e);
      }
   }

   private static DefaultArtifact decodeIvyURI(URI uri) {
      String org = null;
      String name = null;
      String branch = null;
      String rev = null;
      String art = null;
      String type = null;
      String ext = null;
      String path = uri.getPath();
      if (!path.startsWith("/")) {
         throw new IllegalArgumentException("An ivy url should be of the form ivy:///org/module but was : " + uri);
      } else {
         int i = path.indexOf(47, 1);
         if (i < 0) {
            throw new IllegalArgumentException("Expecting an organisation in the ivy url: " + uri);
         } else {
            org = path.substring(1, i);
            name = path.substring(i + 1);

            for(String parameter : uri.getQuery().split("&")) {
               if (parameter.length() != 0) {
                  String[] nameAndValue = parameter.split("=");
                  if (nameAndValue.length != 2) {
                     throw new IllegalArgumentException("Malformed query string in the ivy url: " + uri);
                  }

                  if (nameAndValue[0].equals("branch")) {
                     branch = nameAndValue[1];
                  } else if (nameAndValue[0].equals("rev")) {
                     rev = nameAndValue[1];
                  } else if (nameAndValue[0].equals("art")) {
                     art = nameAndValue[1];
                  } else if (nameAndValue[0].equals("type")) {
                     type = nameAndValue[1];
                  } else {
                     if (!nameAndValue[0].equals("ext")) {
                        throw new IllegalArgumentException("Unrecognized parameter '" + nameAndValue[0] + " in the query string of the ivy url: " + uri);
                     }

                     ext = nameAndValue[1];
                  }
               }
            }

            ModuleRevisionId amrid = ModuleRevisionId.newInstance(org, name, branch, rev);
            return new DefaultArtifact(amrid, (Date)null, art, type, ext);
         }
      }
   }

   private static void requirementAsDependency(DefaultModuleDescriptor md, BundleInfo bundleInfo, Set exportedPkgNames) {
      for(BundleRequirement requirement : bundleInfo.getRequirements()) {
         String type = requirement.getType();
         String name = requirement.getName();
         if ((!"package".equals(type) || !exportedPkgNames.contains(name)) && !"ee".equals(type)) {
            ModuleRevisionId ddmrid = asMrid(type, name, requirement.getVersion());
            DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(ddmrid, false);
            String conf = "default";
            if ("package".equals(type)) {
               conf = "use_" + name;
               md.addConfiguration(new Configuration("use_" + name, Configuration.Visibility.PUBLIC, "Exported package " + name, new String[]{"default"}, true, (String)null));
               dd.addDependencyConfiguration(conf, conf);
            }

            if ("optional".equals(requirement.getResolution())) {
               dd.addDependencyConfiguration("optional", conf);
               dd.addDependencyConfiguration("transitive-optional", "transitive-optional");
            } else {
               dd.addDependencyConfiguration("default", conf);
            }

            md.addDependency(dd);
         }
      }

   }

   public static ModuleRevisionId asMrid(String type, String name, Version v) {
      return ModuleRevisionId.newInstance(type, name, v == null ? null : v.toString());
   }

   public static ModuleRevisionId asMrid(String type, String name, VersionRange v) {
      String revision;
      if (v == null) {
         revision = "[0,)";
      } else {
         revision = v.toIvyRevision();
      }

      return ModuleRevisionId.newInstance(type, name, revision);
   }

   static {
      CONF_OPTIONAL = new Configuration("optional", Configuration.Visibility.PUBLIC, "Optional dependencies", new String[]{"default"}, true, (String)null);
      CONF_TRANSITIVE_OPTIONAL = new Configuration("transitive-optional", Configuration.Visibility.PUBLIC, "Optional dependencies", new String[]{"optional"}, true, (String)null);
   }

   public static class ProfileNotFoundException extends RuntimeException {
      public ProfileNotFoundException(String msg) {
         super(msg);
      }
   }
}

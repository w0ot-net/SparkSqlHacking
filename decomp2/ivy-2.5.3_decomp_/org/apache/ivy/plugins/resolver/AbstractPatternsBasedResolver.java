package org.apache.ivy.plugins.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.settings.IvyPattern;
import org.apache.ivy.plugins.matcher.Matcher;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.plugins.resolver.util.ResourceMDParser;
import org.apache.ivy.util.Message;

public abstract class AbstractPatternsBasedResolver extends BasicResolver {
   private List ivyPatterns = new ArrayList();
   private List artifactPatterns = new ArrayList();
   private boolean m2compatible = false;

   public ResolvedResource findIvyFileRef(DependencyDescriptor dd, ResolveData data) {
      ModuleRevisionId mrid = dd.getDependencyRevisionId();
      if (this.isM2compatible()) {
         mrid = this.convertM2IdForResourceSearch(mrid);
      }

      return this.findResourceUsingPatterns(mrid, this.ivyPatterns, DefaultArtifact.newIvyArtifact(mrid, data.getDate()), this.getRMDParser(dd, data), data.getDate());
   }

   public ResolvedResource findArtifactRef(Artifact artifact, Date date) {
      ModuleRevisionId mrid = artifact.getModuleRevisionId();
      if (this.isM2compatible()) {
         mrid = this.convertM2IdForResourceSearch(mrid);
      }

      return this.findResourceUsingPatterns(mrid, this.artifactPatterns, artifact, this.getDefaultRMDParser(artifact.getModuleRevisionId().getModuleId()), date);
   }

   public ResolvedResource findResource(ResolvedResource[] rress, ResourceMDParser rmdparser, ModuleRevisionId mrid, Date date) {
      if (this.isM2compatible()) {
         mrid = this.convertM2ResourceSearchIdToNormal(mrid);
      }

      return super.findResource(rress, rmdparser, mrid, date);
   }

   protected ResolvedResource findResourceUsingPatterns(ModuleRevisionId moduleRevision, List patternList, Artifact artifact, ResourceMDParser rmdparser, Date date) {
      List<ResolvedResource> resolvedResources = new ArrayList();
      Set<String> foundRevisions = new HashSet();
      boolean dynamic = this.getSettings().getVersionMatcher().isDynamic(moduleRevision);

      for(String pattern : patternList) {
         ResolvedResource rres = this.findResourceUsingPattern(moduleRevision, pattern, artifact, rmdparser, date);
         if (rres != null && !foundRevisions.contains(rres.getRevision())) {
            foundRevisions.add(rres.getRevision());
            resolvedResources.add(rres);
            if (!dynamic) {
               break;
            }
         }
      }

      if (resolvedResources.size() > 1) {
         ResolvedResource[] rress = (ResolvedResource[])resolvedResources.toArray(new ResolvedResource[resolvedResources.size()]);
         return this.findResource(rress, rmdparser, moduleRevision, date);
      } else {
         return resolvedResources.size() == 1 ? (ResolvedResource)resolvedResources.get(0) : null;
      }
   }

   protected abstract ResolvedResource findResourceUsingPattern(ModuleRevisionId var1, String var2, Artifact var3, ResourceMDParser var4, Date var5);

   protected Collection findNames(Map tokenValues, String token) {
      Collection<String> names = new HashSet(this.findIvyNames(tokenValues, token));
      if (this.isAllownomd()) {
         names.addAll(this.findArtifactNames(tokenValues, token));
      }

      return names;
   }

   protected Collection findIvyNames(Map tokenValues, String token) {
      Collection<String> names = new HashSet();
      tokenValues = new HashMap(tokenValues);
      tokenValues.put("artifact", "ivy");
      tokenValues.put("type", "ivy");
      tokenValues.put("ext", "xml");
      if (this.isM2compatible()) {
         this.convertM2TokenValuesForResourceSearch(tokenValues);
      }

      this.findTokenValues(names, this.getIvyPatterns(), tokenValues, token);
      this.filterNames(names);
      return names;
   }

   protected Collection findArtifactNames(Map tokenValues, String token) {
      Collection<String> names = new HashSet();
      tokenValues = new HashMap(tokenValues);
      tokenValues.put("artifact", tokenValues.get("module"));
      tokenValues.put("type", "jar");
      tokenValues.put("ext", "jar");
      if (this.isM2compatible()) {
         this.convertM2TokenValuesForResourceSearch(tokenValues);
      }

      this.findTokenValues(names, this.getArtifactPatterns(), tokenValues, token);
      this.filterNames(names);
      return names;
   }

   public Map[] listTokenValues(String[] tokens, Map criteria) {
      Set<Map<String, String>> result = new LinkedHashSet();
      Map<String, Object> subcriteria = new HashMap(criteria);
      subcriteria.put("type", "ivy");
      subcriteria.put("ext", this.getModuleDescriptorExtension());
      if (this.isM2compatible()) {
         this.convertM2CriteriaForResourceSearch(subcriteria);
      }

      for(String ivyPattern : this.getIvyPatterns()) {
         result.addAll(this.resolveTokenValues(tokens, ivyPattern, subcriteria, false));
      }

      if (this.isAllownomd()) {
         subcriteria = new HashMap(criteria);
         subcriteria.put("type", "jar");
         subcriteria.put("ext", "jar");
         if (this.isM2compatible()) {
            this.convertM2CriteriaForResourceSearch(subcriteria);
         }

         for(String artifactPattern : this.getArtifactPatterns()) {
            result.addAll(this.resolveTokenValues(tokens, artifactPattern, subcriteria, true));
         }
      }

      return (Map[])result.toArray(new Map[result.size()]);
   }

   protected String getModuleDescriptorExtension() {
      return "xml";
   }

   private Set resolveTokenValues(String[] tokens, String pattern, Map criteria, boolean noMd) {
      Set<Map<String, String>> result = new LinkedHashSet();
      Set<String> tokenSet = new HashSet(Arrays.asList(tokens));
      Map<String, String> tokenValues = new HashMap();

      for(Map.Entry entry : criteria.entrySet()) {
         Object value = entry.getValue();
         if (value instanceof String) {
            tokenValues.put(entry.getKey(), (String)value);
         }
      }

      if (tokenSet.isEmpty()) {
         result.add(tokenValues);
         return result;
      } else {
         String partiallyResolvedPattern = IvyPatternHelper.substituteTokens(pattern, tokenValues);
         String token = IvyPatternHelper.getFirstToken(partiallyResolvedPattern);
         if (token == null && this.exist(partiallyResolvedPattern)) {
            result.add(tokenValues);
            return result;
         } else {
            tokenSet.remove(token);
            Matcher matcher = null;
            Object criteriaForToken = criteria.get(token);
            if (criteriaForToken instanceof Matcher) {
               matcher = (Matcher)criteriaForToken;
            }

            String[] values = this.listTokenValues(partiallyResolvedPattern, token);
            if (values == null) {
               return result;
            } else {
               List<String> valueList = new ArrayList(Arrays.asList(values));
               this.filterNames(valueList);

               for(String value : valueList) {
                  if (matcher == null || matcher.matches(value)) {
                     tokenValues.put(token, value);
                     String moreResolvedPattern = IvyPatternHelper.substituteTokens(partiallyResolvedPattern, tokenValues);
                     Map<String, Object> newCriteria = new HashMap(criteria);
                     newCriteria.put(token, value);
                     if (noMd && "artifact".equals(token)) {
                        newCriteria.put("module", value);
                     } else if (noMd && "module".equals(token)) {
                        newCriteria.put("artifact", value);
                     }

                     result.addAll(this.resolveTokenValues((String[])tokenSet.toArray(new String[tokenSet.size()]), moreResolvedPattern, newCriteria, noMd));
                  }
               }

               return result;
            }
         }
      }
   }

   protected abstract String[] listTokenValues(String var1, String var2);

   protected abstract boolean exist(String var1);

   protected void findTokenValues(Collection names, List patterns, Map tokenValues, String token) {
   }

   public void addIvyPattern(String pattern) {
      this.ivyPatterns.add(pattern);
   }

   public void addArtifactPattern(String pattern) {
      this.artifactPatterns.add(pattern);
   }

   public List getIvyPatterns() {
      return Collections.unmodifiableList(this.ivyPatterns);
   }

   public List getArtifactPatterns() {
      return Collections.unmodifiableList(this.artifactPatterns);
   }

   protected void setIvyPatterns(List patterns) {
      this.ivyPatterns = patterns;
   }

   protected void setArtifactPatterns(List patterns) {
      this.artifactPatterns = patterns;
   }

   public void addConfiguredIvy(IvyPattern p) {
      this.ivyPatterns.add(p.getPattern());
   }

   public void addConfiguredArtifact(IvyPattern p) {
      this.artifactPatterns.add(p.getPattern());
   }

   public void dumpSettings() {
      super.dumpSettings();
      Message.debug("\t\tm2compatible: " + this.isM2compatible());
      Message.debug("\t\tivy patterns:");

      for(String p : this.getIvyPatterns()) {
         Message.debug("\t\t\t" + p);
      }

      Message.debug("\t\tartifact patterns:");

      for(String p : this.getArtifactPatterns()) {
         Message.debug("\t\t\t" + p);
      }

   }

   public boolean isM2compatible() {
      return this.m2compatible;
   }

   public void setM2compatible(boolean compatible) {
      this.m2compatible = compatible;
   }

   protected ModuleRevisionId convertM2ResourceSearchIdToNormal(ModuleRevisionId mrid) {
      return mrid.getOrganisation() != null && mrid.getOrganisation().indexOf(47) != -1 ? ModuleRevisionId.newInstance(mrid.getOrganisation().replace('/', '.'), mrid.getName(), mrid.getBranch(), mrid.getRevision(), mrid.getQualifiedExtraAttributes()) : mrid;
   }

   protected ModuleRevisionId convertM2IdForResourceSearch(ModuleRevisionId mrid) {
      return mrid.getOrganisation() != null && mrid.getOrganisation().indexOf(46) != -1 ? ModuleRevisionId.newInstance(mrid.getOrganisation().replace('.', '/'), mrid.getName(), mrid.getBranch(), mrid.getRevision(), mrid.getQualifiedExtraAttributes()) : mrid;
   }

   protected String convertM2OrganizationForResourceSearch(String org) {
      return org.replace('.', '/');
   }

   protected void convertM2TokenValuesForResourceSearch(Map tokenValues) {
      tokenValues.put("organisation", this.convertM2OrganizationForResourceSearch((String)tokenValues.get("organisation")));
   }

   protected void convertM2CriteriaForResourceSearch(Map criteria) {
      Object org = criteria.get("organisation");
      if (org instanceof String) {
         criteria.put("organisation", this.convertM2OrganizationForResourceSearch((String)org));
      }

   }
}

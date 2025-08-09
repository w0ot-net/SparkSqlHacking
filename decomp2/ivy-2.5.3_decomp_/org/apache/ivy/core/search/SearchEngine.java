package org.apache.ivy.core.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.matcher.Matcher;
import org.apache.ivy.plugins.matcher.MatcherHelper;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.resolver.AbstractResolver;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;

public class SearchEngine {
   private IvySettings settings;

   public SearchEngine(IvySettings settings) {
      this.settings = settings;
   }

   public String[] listTokenValues(String token, Map otherTokenValues) {
      Set<String> entries = new LinkedHashSet();

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] values = resolver.listTokenValues(new String[]{token}, otherTokenValues);

         for(Map value : values) {
            entries.add(value.get(token));
         }
      }

      return (String[])entries.toArray(new String[entries.size()]);
   }

   public OrganisationEntry[] listOrganisationEntries() {
      Set<OrganisationEntry> entries = new HashSet();

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] orgs = resolver.listTokenValues((String[])(new String[]{"organisation"}), new HashMap());

         for(Map oe : orgs) {
            String org = (String)oe.get("organisation");
            entries.add(new OrganisationEntry(resolver, org));
         }
      }

      return (OrganisationEntry[])entries.toArray(new OrganisationEntry[entries.size()]);
   }

   public String[] listOrganisations() {
      Set<String> entries = new HashSet();

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] orgs = resolver.listTokenValues((String[])(new String[]{"organisation"}), new HashMap());

         for(Map org : orgs) {
            entries.add(org.get("organisation"));
         }
      }

      return (String[])entries.toArray(new String[entries.size()]);
   }

   public ModuleEntry[] listModuleEntries(OrganisationEntry org) {
      Set<ModuleEntry> entries = new HashSet();
      Map<String, Object> tokenValues = new HashMap();
      tokenValues.put("organisation", org.getOrganisation());

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] modules = resolver.listTokenValues(new String[]{"module"}, tokenValues);

         for(Map me : modules) {
            String module = (String)me.get("module");
            entries.add(new ModuleEntry(org, module));
         }
      }

      return (ModuleEntry[])entries.toArray(new ModuleEntry[entries.size()]);
   }

   public String[] listModules(String org) {
      Set<String> entries = new HashSet();
      Map<String, Object> tokenValues = new HashMap();
      tokenValues.put("organisation", org);

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] modules = resolver.listTokenValues(new String[]{"module"}, tokenValues);

         for(Map module : modules) {
            entries.add(module.get("module"));
         }
      }

      return (String[])entries.toArray(new String[entries.size()]);
   }

   public RevisionEntry[] listRevisionEntries(ModuleEntry module) {
      Set<RevisionEntry> entries = new HashSet();
      Map<String, Object> tokenValues = new HashMap();
      tokenValues.put("organisation", module.getOrganisation());
      tokenValues.put("module", module.getModule());

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] revisions = resolver.listTokenValues(new String[]{"revision"}, tokenValues);

         for(Map revision : revisions) {
            entries.add(new RevisionEntry(module, (String)revision.get("revision")));
         }
      }

      return (RevisionEntry[])entries.toArray(new RevisionEntry[entries.size()]);
   }

   public String[] listRevisions(String org, String module) {
      Set<String> entries = new HashSet();
      Map<String, Object> tokenValues = new HashMap();
      tokenValues.put("organisation", org);
      tokenValues.put("module", module);

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] revisions = resolver.listTokenValues(new String[]{"revision"}, tokenValues);

         for(Map revision : revisions) {
            entries.add(revision.get("revision"));
         }
      }

      return (String[])entries.toArray(new String[entries.size()]);
   }

   public ModuleId[] listModules(ModuleId moduleCrit, PatternMatcher matcher) {
      List<ModuleId> ret = new ArrayList();
      Map<String, Object> criteria = new HashMap();
      this.addMatcher(matcher, moduleCrit.getOrganisation(), criteria, "organisation");
      this.addMatcher(matcher, moduleCrit.getName(), criteria, "module");
      String[] tokensToList = new String[]{"organisation", "module"};

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] moduleIdAsMap = resolver.listTokenValues(tokensToList, criteria);

         for(Map moduleId : moduleIdAsMap) {
            String org = (String)moduleId.get("organisation");
            String name = (String)moduleId.get("module");
            ModuleId modId = ModuleId.newInstance(org, name);
            ret.add(NameSpaceHelper.transform(modId, resolver.getNamespace().getToSystemTransformer()));
         }
      }

      return (ModuleId[])ret.toArray(new ModuleId[ret.size()]);
   }

   public ModuleRevisionId[] listModules(ModuleRevisionId moduleCrit, PatternMatcher matcher) {
      List<ModuleRevisionId> ret = new ArrayList();
      Map<String, Object> criteria = new HashMap();

      for(Map.Entry entry : moduleCrit.getAttributes().entrySet()) {
         this.addMatcher(matcher, (String)entry.getValue(), criteria, (String)entry.getKey());
      }

      String[] tokensToList = (String[])moduleCrit.getAttributes().keySet().toArray(new String[moduleCrit.getAttributes().size()]);

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         Map<String, String>[] moduleIdAsMap = resolver.listTokenValues(tokensToList, criteria);

         for(Map moduleId : moduleIdAsMap) {
            String org = (String)moduleId.get("organisation");
            String name = (String)moduleId.get("module");
            String branch = (String)moduleId.get("branch");
            String rev = (String)moduleId.get("revision");
            Map<String, String> foundExtraAtts = new HashMap();

            for(String qualifiedKey : moduleCrit.getQualifiedExtraAttributes().keySet()) {
               String value = null;
               int colonIndex = qualifiedKey.indexOf(58);
               if (colonIndex == -1) {
                  value = (String)moduleId.get(qualifiedKey);
               } else {
                  value = (String)moduleId.get(qualifiedKey.substring(colonIndex + 1));
               }

               if (value != null) {
                  foundExtraAtts.put(qualifiedKey, value);
               }
            }

            ModuleRevisionId modRevId = ModuleRevisionId.newInstance(org, name, branch, rev, foundExtraAtts);
            ret.add(resolver.getNamespace().getToSystemTransformer().transform(modRevId));
         }
      }

      return (ModuleRevisionId[])ret.toArray(new ModuleRevisionId[ret.size()]);
   }

   public ModuleRevisionId[] listModules(DependencyResolver resolver, ModuleRevisionId moduleCrit, PatternMatcher matcher) {
      Map<String, Object> criteria = new HashMap();

      for(Map.Entry entry : moduleCrit.getAttributes().entrySet()) {
         this.addMatcher(matcher, (String)entry.getValue(), criteria, (String)entry.getKey());
      }

      String[] tokensToList = (String[])moduleCrit.getAttributes().keySet().toArray(new String[moduleCrit.getAttributes().size()]);
      Map<String, String>[] moduleIdAsMap = resolver.listTokenValues(tokensToList, criteria);
      Set<ModuleRevisionId> result = new LinkedHashSet();

      for(Map moduleId : moduleIdAsMap) {
         String org = (String)moduleId.get("organisation");
         String name = (String)moduleId.get("module");
         String branch = (String)moduleId.get("branch");
         String rev = (String)moduleId.get("revision");
         Map<String, String> foundExtraAtts = new HashMap();

         for(String qualifiedKey : moduleCrit.getQualifiedExtraAttributes().keySet()) {
            String value = null;
            int colonIndex = qualifiedKey.indexOf(58);
            if (colonIndex == -1) {
               value = (String)moduleId.get(qualifiedKey);
            } else {
               value = (String)moduleId.get(qualifiedKey.substring(colonIndex + 1));
            }

            if (value != null) {
               foundExtraAtts.put(qualifiedKey, value);
            }
         }

         ModuleRevisionId modRevId = ModuleRevisionId.newInstance(org, name, branch, rev, foundExtraAtts);
         result.add(resolver.getNamespace().getToSystemTransformer().transform(modRevId));
      }

      return (ModuleRevisionId[])result.toArray(new ModuleRevisionId[result.size()]);
   }

   private void addMatcher(PatternMatcher patternMatcher, String expression, Map criteria, String key) {
      if (expression != null) {
         Matcher matcher = patternMatcher.getMatcher(expression);
         if (matcher.isExact()) {
            criteria.put(key, expression);
         } else {
            criteria.put(key, matcher);
         }

      }
   }

   public Collection findModuleRevisionIds(DependencyResolver resolver, ModuleRevisionId pattern, PatternMatcher matcher) {
      Collection<ModuleRevisionId> mrids = new ArrayList();
      String resolverName = resolver.getName();
      Message.verbose("looking for modules matching " + pattern + " using " + matcher.getName());
      Namespace fromNamespace = null;
      if (resolver instanceof AbstractResolver) {
         fromNamespace = resolver.getNamespace();
      }

      Collection<ModuleEntry> modules = new ArrayList();
      OrganisationEntry[] orgs = resolver.listOrganisations();
      if (orgs != null && orgs.length != 0) {
         Matcher orgMatcher = matcher.getMatcher(pattern.getOrganisation());

         for(OrganisationEntry oe : orgs) {
            String org = oe.getOrganisation();
            String systemOrg = fromNamespace == null ? org : NameSpaceHelper.transformOrganisation(org, fromNamespace.getToSystemTransformer());
            if (orgMatcher.matches(systemOrg)) {
               modules.addAll(Arrays.asList(resolver.listModules(new OrganisationEntry(resolver, org))));
            }
         }
      } else {
         String org = pattern.getOrganisation();
         if (fromNamespace != null) {
            org = NameSpaceHelper.transform(pattern.getModuleId(), fromNamespace.getFromSystemTransformer()).getOrganisation();
         }

         modules.addAll(Arrays.asList(resolver.listModules(new OrganisationEntry(resolver, org))));
      }

      Message.debug("found " + modules.size() + " modules for " + pattern.getOrganisation() + " on " + resolverName);
      boolean foundModule = false;

      for(ModuleEntry mEntry : modules) {
         ModuleId foundMid = new ModuleId(mEntry.getOrganisation(), mEntry.getModule());
         ModuleId systemMid = foundMid;
         if (fromNamespace != null) {
            systemMid = NameSpaceHelper.transform(foundMid, fromNamespace.getToSystemTransformer());
         }

         if (MatcherHelper.matches(matcher, pattern.getModuleId(), systemMid)) {
            foundModule = true;
            RevisionEntry[] rEntries = resolver.listRevisions(mEntry);
            Message.debug("found " + rEntries.length + " revisions for [" + mEntry.getOrganisation() + ", " + mEntry.getModule() + "] on " + resolverName);
            boolean foundRevision = false;

            for(RevisionEntry rEntry : rEntries) {
               ModuleRevisionId foundMrid = ModuleRevisionId.newInstance(mEntry.getOrganisation(), mEntry.getModule(), rEntry.getRevision());
               ModuleRevisionId systemMrid = foundMrid;
               if (fromNamespace != null) {
                  systemMrid = fromNamespace.getToSystemTransformer().transform(foundMrid);
               }

               if (MatcherHelper.matches(matcher, pattern, systemMrid)) {
                  foundRevision = true;
                  mrids.add(systemMrid);
               }
            }

            if (!foundRevision) {
               Message.debug("no revision found matching " + pattern + " in [" + mEntry.getOrganisation() + "," + mEntry.getModule() + "] using " + resolverName);
            }
         }
      }

      if (!foundModule) {
         Message.debug("no module found matching " + pattern + " using " + resolverName);
      }

      return mrids;
   }
}

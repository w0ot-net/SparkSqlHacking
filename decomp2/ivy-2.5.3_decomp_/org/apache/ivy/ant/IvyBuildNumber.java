package org.apache.ivy.ant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.SearchEngine;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.matcher.ExactOrRegexpPatternMatcher;
import org.apache.ivy.plugins.matcher.ExactPatternMatcher;
import org.apache.ivy.plugins.matcher.Matcher;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;

public class IvyBuildNumber extends IvyTask {
   private String organisation;
   private String module;
   private String branch;
   private String revision;
   private String revSep = ".";
   private String prefix = "ivy.";
   private String defaultValue = "0";
   private String defaultBuildNumber = "0";
   private String resolver = null;

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getDefault() {
      return this.defaultValue;
   }

   public void setDefault(String default1) {
      this.defaultValue = default1;
   }

   public String getResolver() {
      return this.resolver;
   }

   public void setResolver(String resolver) {
      this.resolver = resolver;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   public void doExecute() throws BuildException {
      if (this.organisation == null) {
         throw new BuildException("no organisation provided for ivy buildnumber task");
      } else if (this.module == null) {
         throw new BuildException("no module name provided for ivy buildnumber task");
      } else if (this.prefix == null) {
         throw new BuildException("null prefix not allowed");
      } else {
         Ivy ivy = this.getIvyInstance();
         IvySettings settings = ivy.getSettings();
         if (this.branch == null) {
            this.branch = settings.getDefaultBranch(new ModuleId(this.organisation, this.module));
         }

         if (StringUtils.isNullOrEmpty(this.revision)) {
            this.revision = "latest.integration";
         } else if (!this.revision.endsWith("+")) {
            this.revision = this.revision + "+";
         }

         if (!this.prefix.endsWith(".") && !this.prefix.isEmpty()) {
            this.prefix = this.prefix + ".";
         }

         SearchEngine searcher = new SearchEngine(settings);
         PatternMatcher patternMatcher = new PatternMatcher() {
            private PatternMatcher exact = new ExactPatternMatcher();
            private PatternMatcher regexp = new ExactOrRegexpPatternMatcher();

            public Matcher getMatcher(String expression) {
               return !expression.equals(IvyBuildNumber.this.organisation) && !expression.equals(IvyBuildNumber.this.module) && !expression.equals(IvyBuildNumber.this.branch) ? this.regexp.getMatcher(expression) : this.exact.getMatcher(expression);
            }

            public String getName() {
               return "buildnumber-matcher";
            }
         };
         String revisionPattern = ".*";
         if (this.revision.endsWith("+")) {
            revisionPattern = Pattern.quote(this.revision.substring(0, this.revision.length() - 1)) + ".*";
         }

         ModuleRevisionId mrid = ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, revisionPattern);
         ModuleRevisionId[] revisions;
         if (this.resolver == null) {
            revisions = searcher.listModules(mrid, patternMatcher);
         } else {
            DependencyResolver depResolver = settings.getResolver(this.resolver);
            if (depResolver == null) {
               throw new BuildException("Unknown resolver: " + this.resolver);
            }

            revisions = searcher.listModules(depResolver, mrid, patternMatcher);
         }

         List<ArtifactInfo> infos = new ArrayList(revisions.length);

         for(ModuleRevisionId rev : revisions) {
            infos.add(new ResolvedModuleRevisionArtifactInfo(rev));
         }

         VersionMatcher matcher = settings.getVersionMatcher();
         LatestStrategy latestStrategy = settings.getLatestStrategy("latest-revision");
         List<ArtifactInfo> sorted = latestStrategy.sort((ArtifactInfo[])infos.toArray(new ArtifactInfo[revisions.length]));
         ModuleRevisionId askedMrid = ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision);
         String foundRevision = null;
         ListIterator<ArtifactInfo> iter = sorted.listIterator(sorted.size());

         while(iter.hasPrevious()) {
            ResolvedModuleRevisionArtifactInfo info = (ResolvedModuleRevisionArtifactInfo)iter.previous();
            if (matcher.accept(askedMrid, info.rmr)) {
               if (matcher.needModuleDescriptor(askedMrid, info.rmr)) {
                  ResolvedModuleRevision rmr = ivy.findModule(info.rmr);
                  if (matcher.accept(askedMrid, rmr.getDescriptor())) {
                     foundRevision = info.rmr.getRevision();
                  }
               } else {
                  foundRevision = info.rmr.getRevision();
               }

               if (foundRevision != null) {
                  break;
               }
            }
         }

         NewRevision newRevision = this.computeNewRevision(foundRevision);
         this.setProperty("revision", newRevision.getRevision());
         this.setProperty("new.revision", newRevision.getNewRevision());
         this.setProperty("build.number", newRevision.getBuildNumber());
         this.setProperty("new.build.number", newRevision.getNewBuildNumber());
      }
   }

   private void setProperty(String propertyName, String value) {
      if (value != null) {
         this.getProject().setProperty(this.prefix + propertyName, value);
      }

   }

   private NewRevision computeNewRevision(String revision) {
      String revPrefix = "latest.integration".equals(this.revision) ? "" : this.revision.substring(0, this.revision.length() - 1);
      if (revision != null && !revision.startsWith(revPrefix)) {
         throw new BuildException("invalid exception found in repository: '" + revision + "' for '" + revPrefix + "'");
      } else if (revision == null) {
         if (revPrefix.length() > 0) {
            return new NewRevision(revision, revPrefix + (revPrefix.endsWith(this.revSep) ? this.defaultBuildNumber : this.revSep + this.defaultBuildNumber), (String)null, this.defaultBuildNumber);
         } else {
            Range r = this.findLastNumber(this.defaultValue);
            if (r == null) {
               return new NewRevision(revision, this.defaultValue, (String)null, (String)null);
            } else {
               long n = Long.parseLong(this.defaultValue.substring(r.getStartIndex(), r.getEndIndex()));
               return new NewRevision(revision, this.defaultValue, (String)null, String.valueOf(n));
            }
         }
      } else {
         Range r;
         if (revPrefix.length() == 0) {
            r = this.findLastNumber(revision);
            if (r == null) {
               return new NewRevision(revision, revision + (revision.endsWith(this.revSep) ? "1" : this.revSep + "1"), (String)null, "1");
            }
         } else {
            r = this.findFirstNumber(revision, revPrefix.length());
            if (r == null) {
               return new NewRevision(revision, revPrefix + (revPrefix.endsWith(this.revSep) ? "1" : this.revSep + "1"), (String)null, "1");
            }
         }

         long n = Long.parseLong(revision.substring(r.getStartIndex(), r.getEndIndex())) + 1L;
         return new NewRevision(revision, revision.substring(0, r.getStartIndex()) + n, String.valueOf(n - 1L), String.valueOf(n));
      }
   }

   private Range findFirstNumber(String str, int startIndex) {
      int startNumberIndex;
      for(startNumberIndex = startIndex; startNumberIndex < str.length() && !Character.isDigit(str.charAt(startNumberIndex)); ++startNumberIndex) {
      }

      if (startNumberIndex == str.length()) {
         return null;
      } else {
         int endNumberIndex;
         for(endNumberIndex = startNumberIndex + 1; endNumberIndex < str.length() && Character.isDigit(str.charAt(endNumberIndex)); ++endNumberIndex) {
         }

         return new Range(startNumberIndex, endNumberIndex);
      }
   }

   private Range findLastNumber(String str) {
      int endNumberIndex;
      for(endNumberIndex = str.length() - 1; endNumberIndex >= 0 && !Character.isDigit(str.charAt(endNumberIndex)); --endNumberIndex) {
      }

      int startNumberIndex;
      for(startNumberIndex = endNumberIndex == -1 ? -1 : endNumberIndex - 1; startNumberIndex >= 0 && Character.isDigit(str.charAt(startNumberIndex)); --startNumberIndex) {
      }

      ++endNumberIndex;
      ++startNumberIndex;
      return startNumberIndex == endNumberIndex ? null : new Range(startNumberIndex, endNumberIndex);
   }

   public String getRevSep() {
      return this.revSep;
   }

   public void setRevSep(String revSep) {
      this.revSep = revSep;
   }

   public String getDefaultBuildNumber() {
      return this.defaultBuildNumber;
   }

   public void setDefaultBuildNumber(String defaultBuildNumber) {
      this.defaultBuildNumber = defaultBuildNumber;
   }

   public static class ResolvedModuleRevisionArtifactInfo implements ArtifactInfo {
      private ModuleRevisionId rmr;

      public ResolvedModuleRevisionArtifactInfo(ModuleRevisionId rmr) {
         this.rmr = rmr;
      }

      public String getRevision() {
         return this.rmr.getRevision();
      }

      public long getLastModified() {
         return -1L;
      }
   }

   private static class Range {
      private int startIndex;
      private int endIndex;

      public Range(int startIndex, int endIndex) {
         this.startIndex = startIndex;
         this.endIndex = endIndex;
      }

      public int getStartIndex() {
         return this.startIndex;
      }

      public int getEndIndex() {
         return this.endIndex;
      }
   }

   private static class NewRevision {
      private String revision;
      private String newRevision;
      private String buildNumber;
      private String newBuildNumber;

      public NewRevision(String revision, String newRevision, String buildNumber, String newBuildNumber) {
         this.revision = revision;
         this.newRevision = newRevision;
         this.buildNumber = buildNumber;
         this.newBuildNumber = newBuildNumber;
      }

      public String getRevision() {
         return this.revision;
      }

      public String getNewRevision() {
         return this.newRevision;
      }

      public String getBuildNumber() {
         return this.buildNumber;
      }

      public String getNewBuildNumber() {
         return this.newBuildNumber;
      }
   }
}

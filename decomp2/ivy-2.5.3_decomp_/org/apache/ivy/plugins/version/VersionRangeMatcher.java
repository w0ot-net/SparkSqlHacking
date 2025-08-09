package org.apache.ivy.plugins.version;

import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.latest.LatestStrategy;

public class VersionRangeMatcher extends AbstractVersionMatcher {
   private static final String OPEN_INC = "[";
   private static final String OPEN_EXC = "]";
   private static final String OPEN_EXC_MAVEN = "(";
   private static final String CLOSE_INC = "]";
   private static final String CLOSE_EXC = "[";
   private static final String CLOSE_EXC_MAVEN = ")";
   private static final String LOWER_INFINITE = "(";
   private static final String UPPER_INFINITE = ")";
   private static final String SEPARATOR = ",";
   private static final String OPEN_INC_PATTERN = "\\[";
   private static final String OPEN_EXC_PATTERN = "\\]\\(";
   private static final String CLOSE_INC_PATTERN = "\\]";
   private static final String CLOSE_EXC_PATTERN = "\\[\\)";
   private static final String LI_PATTERN = "\\(";
   private static final String UI_PATTERN = "\\)";
   private static final String SEP_PATTERN = "\\s*\\,\\s*";
   private static final String OPEN_PATTERN = "[\\[\\]\\(]";
   private static final String CLOSE_PATTERN = "[\\]\\[\\)]";
   private static final String ANY_NON_SPECIAL_PATTERN = "[^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]";
   private static final String FINITE_PATTERN = "[\\[\\]\\(]\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*\\,\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*[\\]\\[\\)]";
   private static final String LOWER_INFINITE_PATTERN = "\\(\\s*\\,\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*[\\]\\[\\)]";
   private static final String UPPER_INFINITE_PATTERN = "[\\[\\]\\(]\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*\\,\\s*\\)";
   private static final Pattern FINITE_RANGE = Pattern.compile("[\\[\\]\\(]\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*\\,\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*[\\]\\[\\)]");
   private static final Pattern LOWER_INFINITE_RANGE = Pattern.compile("\\(\\s*\\,\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*[\\]\\[\\)]");
   private static final Pattern UPPER_INFINITE_RANGE = Pattern.compile("[\\[\\]\\(]\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*\\,\\s*\\)");
   private static final Pattern ALL_RANGE = Pattern.compile("[\\[\\]\\(]\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*\\,\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*[\\]\\[\\)]|\\(\\s*\\,\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*[\\]\\[\\)]|[\\[\\]\\(]\\s*([^\\s,\\[\\]\\(\\]\\[\\)\\(\\)]+)\\s*\\,\\s*\\)");
   private final Comparator comparator = new Comparator() {
      public int compare(ModuleRevisionId o1, ModuleRevisionId o2) {
         if (o1.equals(o2)) {
            return 0;
         } else {
            ArtifactInfo art1 = VersionRangeMatcher.this.new MRIDArtifactInfo(o1);
            ArtifactInfo art2 = VersionRangeMatcher.this.new MRIDArtifactInfo(o2);
            ArtifactInfo art = VersionRangeMatcher.this.getLatestStrategy().findLatest(new ArtifactInfo[]{art1, art2}, (Date)null);
            return art == art1 ? -1 : 1;
         }
      }
   };
   private LatestStrategy latestStrategy;
   private String latestStrategyName = "default";

   public VersionRangeMatcher() {
      super("version-range");
   }

   public VersionRangeMatcher(String name) {
      super(name);
   }

   public VersionRangeMatcher(String name, LatestStrategy strategy) {
      super(name);
      this.latestStrategy = strategy;
   }

   public boolean isDynamic(ModuleRevisionId askedMrid) {
      String revision = askedMrid.getRevision();
      return ALL_RANGE.matcher(revision).matches();
   }

   public boolean accept(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid) {
      String revision = askedMrid.getRevision();
      Matcher m = FINITE_RANGE.matcher(revision);
      if (!m.matches()) {
         m = LOWER_INFINITE_RANGE.matcher(revision);
         if (m.matches()) {
            String upper = m.group(1);
            return this.isLower(askedMrid, upper, foundMrid, revision.endsWith("]"));
         } else {
            m = UPPER_INFINITE_RANGE.matcher(revision);
            if (m.matches()) {
               String lower = m.group(1);
               return this.isUpper(askedMrid, lower, foundMrid, revision.startsWith("["));
            } else {
               return false;
            }
         }
      } else {
         String lower = m.group(1);
         String upper = m.group(2);
         return this.isUpper(askedMrid, lower, foundMrid, revision.startsWith("[")) && this.isLower(askedMrid, upper, foundMrid, revision.endsWith("]"));
      }
   }

   private boolean isLower(ModuleRevisionId askedMrid, String revision, ModuleRevisionId foundMrid, boolean inclusive) {
      ModuleRevisionId mRevId = ModuleRevisionId.newInstance(askedMrid, revision);
      int result = this.comparator.compare(mRevId, foundMrid);
      return result <= (inclusive ? 0 : -1);
   }

   private boolean isUpper(ModuleRevisionId askedMrid, String revision, ModuleRevisionId foundMrid, boolean inclusive) {
      ModuleRevisionId mRevId = ModuleRevisionId.newInstance(askedMrid, revision);
      int result = this.comparator.compare(mRevId, foundMrid);
      return result >= (inclusive ? 0 : 1);
   }

   public int compare(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid, Comparator staticComparator) {
      String revision = askedMrid.getRevision();
      Matcher m = UPPER_INFINITE_RANGE.matcher(revision);
      if (m.matches()) {
         return 1;
      } else {
         m = FINITE_RANGE.matcher(revision);
         String upper;
         if (m.matches()) {
            upper = m.group(2);
         } else {
            m = LOWER_INFINITE_RANGE.matcher(revision);
            if (!m.matches()) {
               throw new IllegalArgumentException("impossible to compare: askedMrid is not a dynamic revision: " + askedMrid);
            }

            upper = m.group(1);
         }

         int c = staticComparator.compare(ModuleRevisionId.newInstance(askedMrid, upper), foundMrid);
         return c == 0 ? -1 : c;
      }
   }

   public LatestStrategy getLatestStrategy() {
      if (this.latestStrategy == null) {
         if (this.getSettings() == null) {
            throw new IllegalStateException("no ivy instance nor latest strategy configured in version range matcher " + this);
         }

         if (this.latestStrategyName == null) {
            throw new IllegalStateException("null latest strategy defined in version range matcher " + this);
         }

         this.latestStrategy = this.getSettings().getLatestStrategy(this.latestStrategyName);
         if (this.latestStrategy == null) {
            throw new IllegalStateException("unknown latest strategy '" + this.latestStrategyName + "' configured in version range matcher " + this);
         }
      }

      return this.latestStrategy;
   }

   public void setLatestStrategy(LatestStrategy latestStrategy) {
      this.latestStrategy = latestStrategy;
   }

   public void setLatest(String latestStrategyName) {
      this.latestStrategyName = latestStrategyName;
   }

   private final class MRIDArtifactInfo implements ArtifactInfo {
      private ModuleRevisionId mrid;

      public MRIDArtifactInfo(ModuleRevisionId id) {
         this.mrid = id;
      }

      public long getLastModified() {
         return 0L;
      }

      public String getRevision() {
         return this.mrid.getRevision();
      }
   }
}

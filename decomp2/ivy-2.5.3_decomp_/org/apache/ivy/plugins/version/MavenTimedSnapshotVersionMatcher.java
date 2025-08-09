package org.apache.ivy.plugins.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.util.StringUtils;

public class MavenTimedSnapshotVersionMatcher extends AbstractVersionMatcher {
   private static final String SNAPSHOT_SUFFIX = "-SNAPSHOT";
   private static final Pattern M2_TIMESTAMPED_SNAPSHOT_REV_PATTERN = Pattern.compile("^(.*)-([0-9]{8}.[0-9]{6})-([0-9]+)$");

   public MavenTimedSnapshotVersionMatcher() {
      super("maven-timed-snapshot");
   }

   public boolean isDynamic(ModuleRevisionId askedMrid) {
      if (askedMrid == null) {
         return false;
      } else {
         Matcher snapshotPatternMatcher = M2_TIMESTAMPED_SNAPSHOT_REV_PATTERN.matcher(askedMrid.getRevision());
         return snapshotPatternMatcher.matches();
      }
   }

   public boolean accept(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid) {
      if (askedMrid != null && foundMrid != null) {
         MavenSnapshotRevision askedSnapshotVersion = computeIfSnapshot(askedMrid.getRevision());
         if (askedSnapshotVersion == null) {
            return false;
         } else if (!askedSnapshotVersion.isTimestampedSnapshot()) {
            return false;
         } else {
            MavenSnapshotRevision foundSnapshotVersion = computeIfSnapshot(foundMrid.getRevision());
            return foundSnapshotVersion == null ? false : askedSnapshotVersion.baseRevision.equals(foundSnapshotVersion.baseRevision);
         }
      } else {
         return false;
      }
   }

   public static MavenSnapshotRevision computeIfSnapshot(String revision) {
      if (StringUtils.isNullOrEmpty(revision)) {
         return null;
      } else {
         boolean regularSnapshot = revision.endsWith("-SNAPSHOT");
         Matcher snapshotPatternMatcher = M2_TIMESTAMPED_SNAPSHOT_REV_PATTERN.matcher(revision);
         boolean timestampedSnaphost = snapshotPatternMatcher.matches();
         if (!regularSnapshot && !timestampedSnaphost) {
            return null;
         } else {
            return timestampedSnaphost ? new MavenSnapshotRevision(true, revision, snapshotPatternMatcher.group(1)) : new MavenSnapshotRevision(false, revision, revision.substring(0, revision.indexOf("-SNAPSHOT")));
         }
      }
   }

   public static final class MavenSnapshotRevision {
      private final boolean timedsnapshot;
      private final String wholeRevision;
      private final String baseRevision;

      private MavenSnapshotRevision(boolean timedsnapshot, String wholeRevision, String baseRevision) {
         if (wholeRevision == null) {
            throw new IllegalArgumentException("Revision, of a Maven snapshot, cannot be null");
         } else if (baseRevision == null) {
            throw new IllegalArgumentException("Base revision, of a Maven snapshot revision, cannot be null");
         } else {
            this.timedsnapshot = timedsnapshot;
            this.wholeRevision = wholeRevision;
            this.baseRevision = baseRevision;
         }
      }

      public boolean isTimestampedSnapshot() {
         return this.timedsnapshot;
      }

      public String getBaseRevision() {
         return this.baseRevision;
      }

      public String getRevision() {
         return this.wholeRevision;
      }
   }
}

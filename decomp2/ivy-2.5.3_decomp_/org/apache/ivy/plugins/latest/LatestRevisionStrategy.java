package org.apache.ivy.plugins.latest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.version.VersionMatcher;

public class LatestRevisionStrategy extends ComparatorLatestStrategy {
   private static final Map DEFAULT_SPECIAL_MEANINGS = new HashMap();
   private final Comparator mridComparator = new MridComparator();
   private final Comparator artifactInfoComparator = new ArtifactInfoComparator();
   private Map specialMeanings = null;
   private boolean usedefaultspecialmeanings = true;

   public LatestRevisionStrategy() {
      this.setComparator(this.artifactInfoComparator);
      this.setName("latest-revision");
   }

   public void addConfiguredSpecialMeaning(SpecialMeaning meaning) {
      meaning.validate();
      this.getSpecialMeanings().put(meaning.getName().toLowerCase(Locale.US), meaning.getValue());
   }

   public synchronized Map getSpecialMeanings() {
      if (this.specialMeanings == null) {
         this.specialMeanings = new HashMap();
         if (this.isUsedefaultspecialmeanings()) {
            this.specialMeanings.putAll(DEFAULT_SPECIAL_MEANINGS);
         }
      }

      return this.specialMeanings;
   }

   public boolean isUsedefaultspecialmeanings() {
      return this.usedefaultspecialmeanings;
   }

   public void setUsedefaultspecialmeanings(boolean usedefaultspecialmeanings) {
      this.usedefaultspecialmeanings = usedefaultspecialmeanings;
   }

   static {
      DEFAULT_SPECIAL_MEANINGS.put("dev", -1);
      DEFAULT_SPECIAL_MEANINGS.put("rc", 1);
      DEFAULT_SPECIAL_MEANINGS.put("final", 2);
   }

   final class MridComparator implements Comparator {
      public int compare(ModuleRevisionId o1, ModuleRevisionId o2) {
         String rev1 = o1.getRevision();
         String rev2 = o2.getRevision();
         rev1 = rev1.replaceAll("([a-zA-Z])(\\d)", "$1.$2");
         rev1 = rev1.replaceAll("(\\d)([a-zA-Z])", "$1.$2");
         rev2 = rev2.replaceAll("([a-zA-Z])(\\d)", "$1.$2");
         rev2 = rev2.replaceAll("(\\d)([a-zA-Z])", "$1.$2");
         String[] parts1 = rev1.split("[\\._\\-\\+]");
         String[] parts2 = rev2.split("[\\._\\-\\+]");

         int i;
         for(i = 0; i < parts1.length && i < parts2.length; ++i) {
            if (!parts1[i].equals(parts2[i])) {
               boolean is1Number = this.isNumber(parts1[i]);
               boolean is2Number = this.isNumber(parts2[i]);
               if (is1Number && !is2Number) {
                  return 1;
               }

               if (is2Number && !is1Number) {
                  return -1;
               }

               if (is1Number && is2Number) {
                  return Long.valueOf(parts1[i]).compareTo(Long.valueOf(parts2[i]));
               }

               Map<String, Integer> specialMeanings = LatestRevisionStrategy.this.getSpecialMeanings();
               Integer sm1 = (Integer)specialMeanings.get(parts1[i].toLowerCase(Locale.US));
               Integer sm2 = (Integer)specialMeanings.get(parts2[i].toLowerCase(Locale.US));
               if (sm1 != null) {
                  if (sm2 == null) {
                     sm2 = new Integer(0);
                  }

                  return sm1.compareTo(sm2);
               }

               if (sm2 != null) {
                  return (new Integer(0)).compareTo(sm2);
               }

               return parts1[i].compareTo(parts2[i]);
            }
         }

         if (i < parts1.length) {
            return this.isNumber(parts1[i]) ? 1 : -1;
         } else if (i < parts2.length) {
            return this.isNumber(parts2[i]) ? -1 : 1;
         } else {
            return 0;
         }
      }

      private boolean isNumber(String str) {
         return str.matches("\\d+");
      }
   }

   final class ArtifactInfoComparator implements Comparator {
      public int compare(ArtifactInfo o1, ArtifactInfo o2) {
         String rev1 = o1.getRevision();
         String rev2 = o2.getRevision();
         VersionMatcher vmatcher = IvyContext.getContext().getSettings().getVersionMatcher();
         ModuleRevisionId mrid1 = ModuleRevisionId.newInstance("", "", rev1);
         ModuleRevisionId mrid2 = ModuleRevisionId.newInstance("", "", rev2);
         if (vmatcher.isDynamic(mrid1)) {
            int c = vmatcher.compare(mrid1, mrid2, LatestRevisionStrategy.this.mridComparator);
            return c >= 0 ? 1 : -1;
         } else if (vmatcher.isDynamic(mrid2)) {
            int c = vmatcher.compare(mrid2, mrid1, LatestRevisionStrategy.this.mridComparator);
            return c >= 0 ? -1 : 1;
         } else {
            return LatestRevisionStrategy.this.mridComparator.compare(mrid1, mrid2);
         }
      }
   }

   public static class SpecialMeaning {
      private String name;
      private Integer value;

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public Integer getValue() {
         return this.value;
      }

      public void setValue(Integer value) {
         this.value = value;
      }

      public void validate() {
         if (this.name == null) {
            throw new IllegalStateException("a special meaning should have a name");
         } else if (this.value == null) {
            throw new IllegalStateException("a special meaning should have a value");
         }
      }
   }
}

package org.apache.ivy.core.module.id;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.util.extendable.UnmodifiableExtendableItem;

public class ModuleRevisionId extends UnmodifiableExtendableItem {
   private static final String ENCODE_SEPARATOR = ":#@#:";
   private static final String ENCODE_PREFIX = "+";
   private static final String NULL_ENCODE = "@#:NULL:#@";
   static final String STRICT_CHARS_PATTERN = "[a-zA-Z0-9\\-/\\._+=]";
   private static final String REV_STRICT_CHARS_PATTERN = "[a-zA-Z0-9\\-/\\._+=,\\[\\]\\{\\}\\(\\):@]";
   private static final Map CACHE = new WeakHashMap();
   public static final Pattern MRID_PATTERN = Pattern.compile("([a-zA-Z0-9\\-/\\._+=]*)#([a-zA-Z0-9\\-/\\._+=]+)(?:#([a-zA-Z0-9\\-/\\._+=]+))?;([a-zA-Z0-9\\-/\\._+=,\\[\\]\\{\\}\\(\\):@]+)");
   public static final Pattern NON_CAPTURING_PATTERN = Pattern.compile("(?:[a-zA-Z0-9\\-/\\._+=]*)#(?:[a-zA-Z0-9\\-/\\._+=]+)(?:#(?:[a-zA-Z0-9\\-/\\._+=]+))?;(?:[a-zA-Z0-9\\-/\\._+=,\\[\\]\\{\\}\\(\\):@]+)");
   private final ModuleId moduleId;
   private final String branch;
   private final String revision;
   private int hash;

   public static ModuleRevisionId parse(String mrid) {
      Matcher m = MRID_PATTERN.matcher(mrid.trim());
      if (!m.matches()) {
         throw new IllegalArgumentException("module revision text representation do not match expected pattern. given mrid='" + mrid + "' expected form=" + MRID_PATTERN.pattern());
      } else {
         return newInstance(m.group(1), m.group(2), m.group(3), m.group(4));
      }
   }

   public static ModuleRevisionId newInstance(String organisation, String name, String revision) {
      return intern(new ModuleRevisionId(ModuleId.newInstance(organisation, name), revision));
   }

   public static ModuleRevisionId newInstance(String organisation, String name, String revision, Map extraAttributes) {
      return intern(new ModuleRevisionId(ModuleId.newInstance(organisation, name), revision, extraAttributes));
   }

   public static ModuleRevisionId newInstance(String organisation, String name, String branch, String revision) {
      return intern(new ModuleRevisionId(ModuleId.newInstance(organisation, name), branch, revision));
   }

   public static ModuleRevisionId newInstance(String organisation, String name, String branch, String revision, Map extraAttributes) {
      return intern(new ModuleRevisionId(ModuleId.newInstance(organisation, name), branch, revision, extraAttributes));
   }

   public static ModuleRevisionId newInstance(String organisation, String name, String branch, String revision, Map extraAttributes, boolean replaceNullBranchWithDefault) {
      return intern(new ModuleRevisionId(ModuleId.newInstance(organisation, name), branch, revision, extraAttributes, replaceNullBranchWithDefault));
   }

   public static ModuleRevisionId newInstance(ModuleRevisionId mrid, String rev) {
      return intern(new ModuleRevisionId(mrid.getModuleId(), mrid.getBranch(), rev, mrid.getQualifiedExtraAttributes()));
   }

   public static ModuleRevisionId newInstance(ModuleRevisionId mrid, String branch, String rev) {
      return intern(new ModuleRevisionId(mrid.getModuleId(), branch, rev, mrid.getQualifiedExtraAttributes()));
   }

   public static ModuleRevisionId intern(ModuleRevisionId moduleRevisionId) {
      ModuleRevisionId r = null;
      synchronized(CACHE) {
         WeakReference<ModuleRevisionId> ref = (WeakReference)CACHE.get(moduleRevisionId);
         if (ref != null) {
            r = (ModuleRevisionId)ref.get();
         }

         if (r == null) {
            r = moduleRevisionId;
            CACHE.put(moduleRevisionId, new WeakReference(moduleRevisionId));
         }

         return r;
      }
   }

   public ModuleRevisionId(ModuleId moduleId, String revision) {
      this(moduleId, (String)null, revision, (Map)null);
   }

   public ModuleRevisionId(ModuleId moduleId, String branch, String revision) {
      this(moduleId, branch, revision, (Map)null);
   }

   private ModuleRevisionId(ModuleId moduleId, String revision, Map extraAttributes) {
      this(moduleId, (String)null, revision, extraAttributes);
   }

   private ModuleRevisionId(ModuleId moduleId, String branch, String revision, Map extraAttributes) {
      this(moduleId, branch, revision, extraAttributes, true);
   }

   private ModuleRevisionId(ModuleId moduleId, String branch, String revision, Map extraAttributes, boolean replaceNullBranchWithDefault) {
      super((Map)null, extraAttributes);
      this.moduleId = moduleId;
      IvyContext context = IvyContext.getContext();
      this.branch = replaceNullBranchWithDefault && branch == null ? (context.peekIvy() == null ? null : context.getSettings().getDefaultBranch(moduleId)) : branch;
      this.revision = revision == null ? Ivy.getWorkingRevision() : normalizeRevision(revision);
      this.setStandardAttribute("organisation", this.moduleId.getOrganisation());
      this.setStandardAttribute("module", this.moduleId.getName());
      this.setStandardAttribute("branch", this.branch);
      this.setStandardAttribute("revision", this.revision);
   }

   public ModuleId getModuleId() {
      return this.moduleId;
   }

   public String getName() {
      return this.getModuleId().getName();
   }

   public String getOrganisation() {
      return this.getModuleId().getOrganisation();
   }

   public String getRevision() {
      return this.revision;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ModuleRevisionId)) {
         return false;
      } else {
         ModuleRevisionId other = (ModuleRevisionId)obj;
         return other.getRevision().equals(this.getRevision()) && (other.getBranch() != null || this.getBranch() == null) && (other.getBranch() == null || other.getBranch().equals(this.getBranch())) && other.getModuleId().equals(this.getModuleId()) && other.getQualifiedExtraAttributes().equals(this.getQualifiedExtraAttributes());
      }
   }

   public int hashCode() {
      if (this.hash == 0) {
         this.hash = 31;
         this.hash = this.hash * 13 + (this.getBranch() == null ? 0 : this.getBranch().hashCode());
         this.hash = this.hash * 13 + this.getRevision().hashCode();
         this.hash = this.hash * 13 + this.getModuleId().hashCode();
         this.hash = this.hash * 13 + this.getQualifiedExtraAttributes().hashCode();
      }

      return this.hash;
   }

   public String toString() {
      return this.moduleId + (this.branch != null && this.branch.length() != 0 ? "#" + this.branch : "") + ";" + (this.revision == null ? "NONE" : this.revision);
   }

   public String encodeToString() {
      StringBuilder buf = new StringBuilder();
      Map<String, String> attributes = new HashMap(this.getAttributes());
      attributes.keySet().removeAll(this.getExtraAttributes().keySet());
      attributes.putAll(this.getQualifiedExtraAttributes());

      for(Map.Entry att : attributes.entrySet()) {
         String value = (String)att.getValue();
         value = value == null ? "@#:NULL:#@" : value;
         buf.append("+").append((String)att.getKey()).append(":#@#:").append("+").append(value).append(":#@#:");
      }

      return buf.toString();
   }

   public static ModuleRevisionId decode(String encoded) {
      String[] parts = encoded.split(":#@#:");
      if (parts.length % 2 != 0) {
         throw new IllegalArgumentException("badly encoded module revision id: '" + encoded + "'");
      } else {
         Map<String, String> attributes = new HashMap();

         for(int i = 0; i < parts.length; i += 2) {
            String attName = parts[i];
            if (!attName.startsWith("+")) {
               throw new IllegalArgumentException("badly encoded module revision id: '" + encoded + "': " + attName + " doesn't start with " + "+");
            }

            attName = attName.substring(1);
            String attValue = parts[i + 1];
            if (!attValue.startsWith("+")) {
               throw new IllegalArgumentException("badly encoded module revision id: '" + encoded + "': " + attValue + " doesn't start with " + "+");
            }

            attValue = attValue.substring(1);
            if ("@#:NULL:#@".equals(attValue)) {
               attValue = null;
            }

            attributes.put(attName, attValue);
         }

         String org = (String)attributes.remove("organisation");
         String mod = (String)attributes.remove("module");
         String rev = (String)attributes.remove("revision");
         String branch = (String)attributes.remove("branch");
         if (org == null) {
            throw new IllegalArgumentException("badly encoded module revision id: '" + encoded + "': no organisation");
         } else if (mod == null) {
            throw new IllegalArgumentException("badly encoded module revision id: '" + encoded + "': no module name");
         } else if (rev == null) {
            throw new IllegalArgumentException("badly encoded module revision id: '" + encoded + "': no revision");
         } else {
            return newInstance(org, mod, branch, rev, attributes);
         }
      }
   }

   public String getBranch() {
      return this.branch;
   }

   private static String normalizeRevision(String revision) {
      if (revision.startsWith("[") && revision.endsWith("]") && revision.indexOf(44) == -1) {
         return IvyPatternHelper.getTokenString("revision").equals(revision) ? revision : revision.substring(1, revision.length() - 1);
      } else {
         return revision;
      }
   }
}

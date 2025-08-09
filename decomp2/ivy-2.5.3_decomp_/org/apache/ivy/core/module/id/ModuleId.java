package org.apache.ivy.core.module.id;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleId implements Comparable {
   static final String ENCODE_SEPARATOR = ":#@#:";
   private static final Map CACHE = new WeakHashMap();
   private String organisation;
   private String name;
   private int hash;
   private Map attributes = new HashMap();
   public static final Pattern MID_PATTERN = Pattern.compile("([a-zA-Z0-9\\-/\\._+=]*)#([a-zA-Z0-9\\-/\\._+=]+)");

   public static ModuleId newInstance(String org, String name) {
      return intern(new ModuleId(org, name));
   }

   public static ModuleId intern(ModuleId moduleId) {
      ModuleId r = null;
      synchronized(CACHE) {
         WeakReference<ModuleId> ref = (WeakReference)CACHE.get(moduleId);
         if (ref != null) {
            r = (ModuleId)ref.get();
         }

         if (r == null) {
            r = moduleId;
            CACHE.put(moduleId, new WeakReference(moduleId));
         }

         return r;
      }
   }

   public ModuleId(String organisation, String name) {
      if (name == null) {
         throw new IllegalArgumentException("null name not allowed");
      } else {
         this.organisation = organisation;
         this.name = name;
         this.attributes.put("organisation", organisation);
         this.attributes.put("module", name);
      }
   }

   public String getName() {
      return this.name;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ModuleId)) {
         return false;
      } else {
         ModuleId other = (ModuleId)obj;
         return other.organisation == null ? this.organisation == null && other.name.equals(this.name) : other.organisation.equals(this.organisation) && other.name.equals(this.name);
      }
   }

   public int hashCode() {
      if (this.hash == 0) {
         this.hash = 31;
         this.hash = this.hash * 13 + (this.organisation == null ? 0 : this.organisation.hashCode());
         this.hash = this.hash * 13 + this.name.hashCode();
      }

      return this.hash;
   }

   public String toString() {
      return this.organisation + "#" + this.name;
   }

   public int compareTo(ModuleId that) {
      int result = this.organisation.compareTo(that.organisation);
      if (result == 0) {
         result = this.name.compareTo(that.name);
      }

      return result;
   }

   public String encodeToString() {
      return this.getOrganisation() + ":#@#:" + this.getName();
   }

   public Map getAttributes() {
      return this.attributes;
   }

   public static ModuleId decode(String encoded) {
      String[] parts = encoded.split(":#@#:");
      if (parts.length != 2) {
         throw new IllegalArgumentException("badly encoded module id: '" + encoded + "'");
      } else {
         return new ModuleId(parts[0], parts[1]);
      }
   }

   public static ModuleId parse(String mid) {
      Matcher m = MID_PATTERN.matcher(mid);
      if (!m.matches()) {
         throw new IllegalArgumentException("module text representation do not match expected pattern. given mid='" + mid + "' expected form=" + MID_PATTERN.pattern());
      } else {
         return newInstance(m.group(1), m.group(2));
      }
   }
}

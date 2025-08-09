package org.apache.ivy.plugins.namespace;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.util.Message;

public class MRIDTransformationRule implements NamespaceTransformer {
   private final List src = new ArrayList();
   private MRIDRule dest;

   public void addSrc(MRIDRule src) {
      this.src.add(src);
   }

   public void addDest(MRIDRule dest) {
      if (this.dest != null) {
         throw new IllegalArgumentException("only one dest is allowed per mapping");
      } else {
         this.dest = dest;
      }
   }

   public ModuleRevisionId transform(ModuleRevisionId mrid) {
      MridRuleMatcher matcher = new MridRuleMatcher();

      for(MRIDRule rule : this.src) {
         if (matcher.match(rule, mrid)) {
            ModuleRevisionId destMrid = matcher.apply(this.dest, mrid);
            Message.debug("found matching namespace rule: " + rule + ". Applied " + this.dest + " on " + mrid + ". Transformed to " + destMrid);
            return destMrid;
         }
      }

      return mrid;
   }

   public boolean isIdentity() {
      return false;
   }

   private static class MridRuleMatcher {
      private static final String[] TYPES = new String[]{"o", "m", "b", "r"};
      private Matcher[] matchers;

      private MridRuleMatcher() {
         this.matchers = new Matcher[TYPES.length];
      }

      public boolean match(MRIDRule src, ModuleRevisionId mrid) {
         this.matchers[0] = Pattern.compile(this.getPattern(src.getOrg())).matcher(mrid.getOrganisation());
         if (!this.matchers[0].matches()) {
            return false;
         } else {
            this.matchers[1] = Pattern.compile(this.getPattern(src.getModule())).matcher(mrid.getName());
            if (!this.matchers[1].matches()) {
               return false;
            } else {
               if (mrid.getBranch() == null) {
                  this.matchers[2] = null;
               } else {
                  this.matchers[2] = Pattern.compile(this.getPattern(src.getBranch())).matcher(mrid.getBranch());
                  if (!this.matchers[2].matches()) {
                     return false;
                  }
               }

               this.matchers[3] = Pattern.compile(this.getPattern(src.getRev())).matcher(mrid.getRevision());
               return this.matchers[3].matches();
            }
         }
      }

      public ModuleRevisionId apply(MRIDRule dest, ModuleRevisionId mrid) {
         String org = this.applyRules(dest.getOrg(), "o");
         String mod = this.applyRules(dest.getModule(), "m");
         String branch = this.applyRules(dest.getBranch(), "b");
         String rev = this.applyRules(dest.getRev(), "r");
         return ModuleRevisionId.newInstance(org, mod, branch, rev, mrid.getQualifiedExtraAttributes());
      }

      private String applyRules(String str, String type) {
         for(int i = 0; i < TYPES.length; ++i) {
            str = this.applyTypeRule(str, TYPES[i], type, this.matchers[i]);
         }

         return str;
      }

      private String applyTypeRule(String rule, String type, String ruleType, Matcher m) {
         if (m == null) {
            return rule;
         } else {
            String res = rule == null ? "$" + ruleType + "0" : rule;

            for(String tp : TYPES) {
               if (tp.equals(type)) {
                  res = res.replaceAll("([^\\\\])\\$" + type, "$1\\$");
                  res = res.replaceAll("^\\$" + type, "\\$");
               } else {
                  res = res.replaceAll("([^\\\\])\\$" + tp, "$1\\\\\\$" + tp);
                  res = res.replaceAll("^\\$" + tp, "\\\\\\$" + tp);
               }
            }

            StringBuffer sb = new StringBuffer();
            m.reset();
            m.find();
            m.appendReplacement(sb, res);
            String str = sb.toString();
            if (rule == null && ("$" + ruleType + "0").equals(str)) {
               return null;
            } else {
               return str;
            }
         }
      }

      private String getPattern(String p) {
         return p == null ? ".*" : p;
      }
   }
}

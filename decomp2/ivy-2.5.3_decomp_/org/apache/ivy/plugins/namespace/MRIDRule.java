package org.apache.ivy.plugins.namespace;

public class MRIDRule {
   private String org;
   private String module;
   private String branch;
   private String rev;

   public MRIDRule(String org, String mod, String rev) {
      this.org = org;
      this.module = mod;
      this.rev = rev;
   }

   public MRIDRule() {
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrg() {
      return this.org;
   }

   public void setOrg(String org) {
      this.org = org;
   }

   public String getRev() {
      return this.rev;
   }

   public void setRev(String rev) {
      this.rev = rev;
   }

   public String toString() {
      return "[ " + this.org + " " + this.module + (this.branch != null ? " " + this.branch : "") + " " + this.rev + " ]";
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }
}

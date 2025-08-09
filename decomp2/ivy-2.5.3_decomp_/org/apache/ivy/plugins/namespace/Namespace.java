package org.apache.ivy.plugins.namespace;

import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public class Namespace {
   public static final Namespace SYSTEM_NAMESPACE = new Namespace();
   private final List rules = new ArrayList();
   private String name;
   private boolean chainRules = false;
   private NamespaceTransformer fromSystemTransformer = new NamespaceTransformer() {
      public ModuleRevisionId transform(ModuleRevisionId mrid) {
         if (mrid == null) {
            return null;
         } else {
            for(NamespaceRule rule : Namespace.this.rules) {
               ModuleRevisionId nmrid = rule.getFromSystem().transform(mrid);
               if (Namespace.this.chainRules) {
                  mrid = nmrid;
               } else if (!nmrid.equals(mrid)) {
                  return nmrid;
               }
            }

            return mrid;
         }
      }

      public boolean isIdentity() {
         return Namespace.this.rules.isEmpty();
      }
   };
   private NamespaceTransformer toSystemTransformer = new NamespaceTransformer() {
      public ModuleRevisionId transform(ModuleRevisionId mrid) {
         if (mrid == null) {
            return null;
         } else {
            for(NamespaceRule rule : Namespace.this.rules) {
               ModuleRevisionId nmrid = rule.getToSystem().transform(mrid);
               if (Namespace.this.chainRules) {
                  mrid = nmrid;
               } else if (!nmrid.equals(mrid)) {
                  return nmrid;
               }
            }

            return mrid;
         }
      }

      public boolean isIdentity() {
         return Namespace.this.rules.isEmpty();
      }
   };

   public void addRule(NamespaceRule rule) {
      this.rules.add(rule);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public NamespaceTransformer getFromSystemTransformer() {
      return this.fromSystemTransformer;
   }

   public NamespaceTransformer getToSystemTransformer() {
      return this.toSystemTransformer;
   }

   public boolean isChainrules() {
      return this.chainRules;
   }

   public void setChainrules(boolean chainRules) {
      this.chainRules = chainRules;
   }
}

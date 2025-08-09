package org.apache.ivy.plugins.version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.matcher.Matcher;

public class PatternVersionMatcher extends AbstractVersionMatcher {
   private final List matches = new ArrayList();
   private final Map revisionMatches = new HashMap();
   private boolean init = false;

   public void addMatch(Match match) {
      this.matches.add(match);
   }

   private void init() {
      if (!this.init) {
         for(Match match : this.matches) {
            List<Match> revMatches = (List)this.revisionMatches.get(match.getRevision());
            if (revMatches == null) {
               revMatches = new ArrayList();
               this.revisionMatches.put(match.getRevision(), revMatches);
            }

            revMatches.add(match);
         }

         this.init = true;
      }

   }

   public boolean accept(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid) {
      this.init();
      boolean accept = false;
      String revision = askedMrid.getRevision();
      int bracketIndex = revision.indexOf(40);
      if (bracketIndex > 0) {
         revision = revision.substring(0, bracketIndex);
      }

      List<Match> revMatches = (List)this.revisionMatches.get(revision);
      if (revMatches != null) {
         for(Match match : revMatches) {
            Matcher matcher = match.getPatternMatcher(askedMrid);
            accept = matcher.matches(foundMrid.getRevision());
            if (accept) {
               break;
            }
         }
      }

      return accept;
   }

   public boolean isDynamic(ModuleRevisionId askedMrid) {
      this.init();
      String revision = askedMrid.getRevision();
      int bracketIndex = revision.indexOf(40);
      if (bracketIndex > 0) {
         revision = revision.substring(0, bracketIndex);
      }

      return this.revisionMatches.containsKey(revision);
   }
}

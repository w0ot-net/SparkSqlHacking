package org.apache.ivy.plugins.version;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.IvySettingsAware;
import org.apache.ivy.util.Checks;

public class ChainVersionMatcher extends AbstractVersionMatcher {
   private final List matchers = new LinkedList();

   public ChainVersionMatcher() {
      super("chain");
   }

   public void add(VersionMatcher matcher) {
      Checks.checkNotNull(matcher, "matcher");
      this.matchers.add(0, matcher);
      if (this.getSettings() != null && matcher instanceof IvySettingsAware) {
         ((IvySettingsAware)matcher).setSettings(this.getSettings());
      }

   }

   public void setSettings(IvySettings settings) {
      super.setSettings(settings);

      for(VersionMatcher matcher : this.matchers) {
         if (matcher instanceof IvySettingsAware) {
            ((IvySettingsAware)matcher).setSettings(settings);
         }
      }

   }

   public List getMatchers() {
      return Collections.unmodifiableList(this.matchers);
   }

   public boolean isDynamic(ModuleRevisionId askedMrid) {
      Checks.checkNotNull(askedMrid, "askedMrid");

      for(VersionMatcher matcher : this.matchers) {
         if (matcher.isDynamic(askedMrid)) {
            return true;
         }
      }

      return false;
   }

   public int compare(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid, Comparator staticComparator) {
      Checks.checkNotNull(askedMrid, "askedMrid");
      Checks.checkNotNull(foundMrid, "foundMrid");
      Checks.checkNotNull(staticComparator, "staticComparator");

      for(VersionMatcher matcher : this.matchers) {
         if (matcher.isDynamic(askedMrid)) {
            return matcher.compare(askedMrid, foundMrid, staticComparator);
         }
      }

      throw new IllegalArgumentException("impossible to compare revisions: askedMrid is not dynamic: " + askedMrid);
   }

   public boolean accept(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid) {
      Checks.checkNotNull(askedMrid, "askedMrid");
      Checks.checkNotNull(foundMrid, "foundMrid");
      Iterator<VersionMatcher> iter = this.matchers.iterator();

      while(iter.hasNext()) {
         VersionMatcher matcher = (VersionMatcher)iter.next();
         if (!iter.hasNext() || matcher.isDynamic(askedMrid)) {
            return matcher.accept(askedMrid, foundMrid);
         }
      }

      return false;
   }

   public boolean needModuleDescriptor(ModuleRevisionId askedMrid, ModuleRevisionId foundMrid) {
      Checks.checkNotNull(askedMrid, "askedMrid");
      Checks.checkNotNull(foundMrid, "foundMrid");
      Iterator<VersionMatcher> iter = this.matchers.iterator();

      while(iter.hasNext()) {
         VersionMatcher matcher = (VersionMatcher)iter.next();
         if (!iter.hasNext() || matcher.isDynamic(askedMrid)) {
            return matcher.needModuleDescriptor(askedMrid, foundMrid);
         }
      }

      return false;
   }

   public boolean accept(ModuleRevisionId askedMrid, ModuleDescriptor foundMD) {
      Checks.checkNotNull(askedMrid, "askedMrid");
      Checks.checkNotNull(foundMD, "foundMD");
      Iterator<VersionMatcher> iter = this.matchers.iterator();

      while(iter.hasNext()) {
         VersionMatcher matcher = (VersionMatcher)iter.next();
         if (!iter.hasNext() || matcher.isDynamic(askedMrid)) {
            return matcher.accept(askedMrid, foundMD);
         }
      }

      return false;
   }
}

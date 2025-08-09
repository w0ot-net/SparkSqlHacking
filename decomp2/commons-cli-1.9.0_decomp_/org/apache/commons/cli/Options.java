package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Options implements Serializable {
   private static final long serialVersionUID = 1L;
   private final Map shortOpts = new LinkedHashMap();
   private final Map longOpts = new LinkedHashMap();
   private final List requiredOpts = new ArrayList();
   private final Map optionGroups = new LinkedHashMap();

   public Options addOption(Option opt) {
      String key = opt.getKey();
      if (opt.hasLongOpt()) {
         this.longOpts.put(opt.getLongOpt(), opt);
      }

      if (opt.isRequired()) {
         if (this.requiredOpts.contains(key)) {
            this.requiredOpts.remove(this.requiredOpts.indexOf(key));
         }

         this.requiredOpts.add(key);
      }

      this.shortOpts.put(key, opt);
      return this;
   }

   public Options addOption(String opt, boolean hasArg, String description) {
      this.addOption(opt, (String)null, hasArg, description);
      return this;
   }

   public Options addOption(String opt, String description) {
      this.addOption(opt, (String)null, false, description);
      return this;
   }

   public Options addOption(String opt, String longOpt, boolean hasArg, String description) {
      this.addOption(new Option(opt, longOpt, hasArg, description));
      return this;
   }

   public Options addOptionGroup(OptionGroup group) {
      if (group.isRequired()) {
         this.requiredOpts.add(group);
      }

      for(Option option : group.getOptions()) {
         option.setRequired(false);
         this.addOption(option);
         this.optionGroups.put(option.getKey(), group);
      }

      return this;
   }

   public Options addOptions(Options options) {
      for(Option opt : options.getOptions()) {
         if (this.hasOption(opt.getKey())) {
            throw new IllegalArgumentException("Duplicate key: " + opt.getKey());
         }

         this.addOption(opt);
      }

      options.getOptionGroups().forEach(this::addOptionGroup);
      return this;
   }

   public Options addRequiredOption(String opt, String longOpt, boolean hasArg, String description) {
      Option option = new Option(opt, longOpt, hasArg, description);
      option.setRequired(true);
      this.addOption(option);
      return this;
   }

   public List getMatchingOptions(String opt) {
      String clean = Util.stripLeadingHyphens(opt);
      List<String> matchingOpts = new ArrayList();
      if (this.longOpts.containsKey(clean)) {
         return Collections.singletonList(clean);
      } else {
         for(String longOpt : this.longOpts.keySet()) {
            if (longOpt.startsWith(clean)) {
               matchingOpts.add(longOpt);
            }
         }

         return matchingOpts;
      }
   }

   public Option getOption(String opt) {
      String clean = Util.stripLeadingHyphens(opt);
      Option option = (Option)this.shortOpts.get(clean);
      return option != null ? option : (Option)this.longOpts.get(clean);
   }

   public OptionGroup getOptionGroup(Option opt) {
      return (OptionGroup)this.optionGroups.get(opt.getKey());
   }

   Collection getOptionGroups() {
      return new HashSet(this.optionGroups.values());
   }

   public Collection getOptions() {
      return Collections.unmodifiableCollection(this.helpOptions());
   }

   public List getRequiredOptions() {
      return Collections.unmodifiableList(this.requiredOpts);
   }

   public boolean hasLongOption(String opt) {
      return this.longOpts.containsKey(Util.stripLeadingHyphens(opt));
   }

   public boolean hasOption(String opt) {
      String clean = Util.stripLeadingHyphens(opt);
      return this.shortOpts.containsKey(clean) || this.longOpts.containsKey(clean);
   }

   public boolean hasShortOption(String opt) {
      String clean = Util.stripLeadingHyphens(opt);
      return this.shortOpts.containsKey(clean);
   }

   List helpOptions() {
      return new ArrayList(this.shortOpts.values());
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("[ Options: [ short ");
      buf.append(this.shortOpts.toString());
      buf.append(" ] [ long ");
      buf.append(this.longOpts);
      buf.append(" ]");
      return buf.toString();
   }
}

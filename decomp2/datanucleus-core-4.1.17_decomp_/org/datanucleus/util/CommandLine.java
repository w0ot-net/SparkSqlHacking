package org.datanucleus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandLine {
   protected Map options = new HashMap();
   protected Map valueOptions = new HashMap();
   protected List optionList = new ArrayList();
   protected String[] defaultArg;
   protected boolean displaysDash = true;

   public CommandLine() {
   }

   public CommandLine(boolean displaysDash) {
      this.displaysDash = displaysDash;
   }

   public void addOption(String shortName, String longName, String argName, String desc) {
      Option option = null;
      if (StringUtils.isEmpty(shortName) && StringUtils.isEmpty(longName)) {
         throw new IllegalArgumentException("require shortName or longName");
      } else {
         if (StringUtils.notEmpty(argName)) {
            option = new WithArgOption(shortName, longName, desc, argName);
         } else {
            option = new NoArgOption(shortName, longName, desc);
         }

         this.optionList.add(option);
         if (StringUtils.notEmpty(shortName)) {
            this.options.put("-" + shortName, option);
            this.valueOptions.put(shortName, option);
         }

         if (StringUtils.notEmpty(longName)) {
            this.options.put("--" + longName, option);
            this.valueOptions.put(longName, option);
         }

      }
   }

   public void parse(String[] args) {
      List<String> defaultArg = new ArrayList();
      if (args != null && args.length != 0) {
         for(int i = 0; i < args.length; ++i) {
            if (!StringUtils.isEmpty(args[i])) {
               if (args[i].startsWith("-")) {
                  if (this.options.containsKey(args[i])) {
                     Option option = (Option)this.options.get(args[i]);
                     if (option instanceof NoArgOption) {
                        ((NoArgOption)option).selected = true;
                     } else {
                        if (args.length - 1 == i) {
                           throw new RuntimeException("option " + args[i] + " needs an argument");
                        }

                        ((WithArgOption)option).option = args[i + 1];
                        ++i;
                     }
                  } else {
                     defaultArg.add(args[i]);
                  }
               } else {
                  defaultArg.add(args[i]);
               }
            }
         }

         if (defaultArg.size() == 0) {
            this.defaultArg = new String[0];
         }

         String[] result = new String[defaultArg.size()];

         for(int var5 = 0; var5 < result.length; ++var5) {
            result[var5] = (String)defaultArg.get(var5);
         }

         this.defaultArg = result;
      }
   }

   public boolean hasOption(String name) {
      if (!this.valueOptions.containsKey(name)) {
         throw new IllegalArgumentException("no such option " + name);
      } else {
         Option option = (Option)this.valueOptions.get(name);
         return option instanceof NoArgOption ? ((NoArgOption)option).selected : StringUtils.notEmpty(((WithArgOption)option).option);
      }
   }

   public String getOptionArg(String name) {
      if (!this.valueOptions.containsKey(name)) {
         throw new IllegalArgumentException("no such option " + name);
      } else {
         Option option = (Option)this.valueOptions.get(name);
         return option instanceof NoArgOption ? "" + ((NoArgOption)option).selected : ((WithArgOption)option).option;
      }
   }

   public String toString() {
      if (this.optionList.size() == 0) {
         return "[NO OPTIONS]";
      } else {
         int maxLength = 80;
         StringBuilder sb = new StringBuilder();
         int shortMax = 0;
         int longMax = 0;
         int argNameMax = 0;
         int descMax = 0;

         for(int i = 0; i < this.optionList.size(); ++i) {
            Option o = (Option)this.optionList.get(i);
            if (o.shortName != null) {
               if (o.shortName.length() > shortMax) {
                  shortMax = o.shortName.length();
               }

               if (o.longName != null && o.longName.length() > longMax) {
                  longMax = o.longName.length();
               }

               if (o instanceof WithArgOption) {
                  WithArgOption op = (WithArgOption)o;
                  if (op.name.length() > argNameMax) {
                     argNameMax = op.name.length();
                  }
               }

               if (o.description != null && o.description.length() > descMax) {
                  descMax = o.description.length();
               }
            }
         }

         if (shortMax > 0) {
            shortMax += 3;
         }

         if (longMax > 0) {
            longMax += 3;
         }

         if (argNameMax > 0) {
            argNameMax += 3;
         }

         for(int i = 0; i < this.optionList.size(); ++i) {
            int j = 0;
            Option o = (Option)this.optionList.get(i);
            if (StringUtils.notEmpty(o.shortName)) {
               if (this.displaysDash) {
                  sb.append("-");
               }

               sb.append(o.shortName);
               j = o.shortName.length() + 1;
            }

            while(j < shortMax) {
               sb.append(" ");
               ++j;
            }

            j = 0;
            if (StringUtils.notEmpty(o.longName)) {
               sb.append("--");
               sb.append(o.longName);
               j = o.longName.length() + 2;
            }

            while(j < longMax) {
               sb.append(" ");
               ++j;
            }

            j = 0;
            if (o instanceof WithArgOption) {
               WithArgOption op = (WithArgOption)o;
               sb.append(op.name);
               j = op.name.length();
            }

            while(j < argNameMax) {
               sb.append(" ");
               ++j;
            }

            if (StringUtils.notEmpty(o.description)) {
               int basePos;
               if (shortMax + longMax + argNameMax > maxLength) {
                  basePos = maxLength / 2;
                  sb.append("\n");

                  for(int k = 0; k < basePos; ++k) {
                     sb.append(" ");
                  }
               } else {
                  basePos = shortMax + longMax + argNameMax;
               }

               int pos = basePos;

               for(int var18 = 0; var18 < o.description.length(); ++var18) {
                  sb.append(o.description.charAt(var18));
                  if (pos >= maxLength) {
                     if (var18 < o.description.length() - 1 && o.description.charAt(var18 + 1) != ' ') {
                        for(int p = sb.length() - 1; p >= 0; --p) {
                           if (sb.charAt(p) == ' ') {
                              sb.insert(p, '\n');

                              for(int k = 0; k < basePos - 1; ++k) {
                                 sb.insert(p + 1, " ");
                              }
                              break;
                           }
                        }
                     } else {
                        sb.append("\n");

                        for(int k = 0; k < basePos; ++k) {
                           sb.append(" ");
                        }
                     }

                     pos = basePos;
                  }

                  ++pos;
               }
            }

            sb.append("\n");
         }

         return sb.toString();
      }
   }

   public String[] getDefaultArgs() {
      return this.defaultArg;
   }

   protected static class Option {
      final String shortName;
      final String longName;
      final String description;

      public Option(String shortName, String longName, String desc) {
         this.shortName = shortName;
         this.longName = longName;
         this.description = desc;
      }
   }

   protected static class NoArgOption extends Option {
      boolean selected;

      public NoArgOption(String shortName, String longName, String desc) {
         super(shortName, longName, desc);
      }
   }

   protected static class WithArgOption extends Option {
      String name;
      String option;

      public WithArgOption(String shortName, String longName, String desc, String name) {
         super(shortName, longName, desc);
         this.name = name;
      }
   }
}

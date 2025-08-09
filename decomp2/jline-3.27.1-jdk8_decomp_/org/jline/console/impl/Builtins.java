package org.jline.console.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jline.builtins.Commands;
import org.jline.builtins.Completers;
import org.jline.builtins.ConfigurationPath;
import org.jline.builtins.SyntaxHighlighter;
import org.jline.builtins.TTop;
import org.jline.console.CommandInput;
import org.jline.console.CommandMethods;
import org.jline.console.CommandRegistry;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.Widget;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;

public class Builtins extends JlineCommandRegistry implements CommandRegistry {
   private final ConfigurationPath configPath;
   private final Function widgetCreator;
   private final Supplier workDir;
   private LineReader reader;

   public Builtins(Path workDir, ConfigurationPath configPath, Function widgetCreator) {
      this((Set)null, (Supplier)(() -> workDir), configPath, widgetCreator);
   }

   public Builtins(Set commands, Path workDir, ConfigurationPath configpath, Function widgetCreator) {
      this(commands, (Supplier)(() -> workDir), configpath, widgetCreator);
   }

   public Builtins(Supplier workDir, ConfigurationPath configPath, Function widgetCreator) {
      this((Set)null, (Supplier)workDir, configPath, widgetCreator);
   }

   public Builtins(Set commands, Supplier workDir, ConfigurationPath configpath, Function widgetCreator) {
      Objects.requireNonNull(configpath);
      this.configPath = configpath;
      this.widgetCreator = widgetCreator;
      this.workDir = workDir;
      Map<Command, String> commandName = new HashMap();
      Map<Command, CommandMethods> commandExecute = new HashMap();
      Set<Command> cmds;
      if (commands == null) {
         cmds = new HashSet(EnumSet.allOf(Command.class));
      } else {
         cmds = new HashSet(commands);
      }

      for(Command c : cmds) {
         commandName.put(c, c.name().toLowerCase());
      }

      commandExecute.put(Builtins.Command.NANO, new CommandMethods(this::nano, this::nanoCompleter));
      commandExecute.put(Builtins.Command.LESS, new CommandMethods(this::less, this::lessCompleter));
      commandExecute.put(Builtins.Command.HISTORY, new CommandMethods(this::history, this::historyCompleter));
      commandExecute.put(Builtins.Command.WIDGET, new CommandMethods(this::widget, this::widgetCompleter));
      commandExecute.put(Builtins.Command.KEYMAP, new CommandMethods(this::keymap, this::defaultCompleter));
      commandExecute.put(Builtins.Command.SETOPT, new CommandMethods(this::setopt, this::setoptCompleter));
      commandExecute.put(Builtins.Command.SETVAR, new CommandMethods(this::setvar, this::setvarCompleter));
      commandExecute.put(Builtins.Command.UNSETOPT, new CommandMethods(this::unsetopt, this::unsetoptCompleter));
      commandExecute.put(Builtins.Command.TTOP, new CommandMethods(this::ttop, this::defaultCompleter));
      commandExecute.put(Builtins.Command.COLORS, new CommandMethods(this::colors, this::defaultCompleter));
      commandExecute.put(Builtins.Command.HIGHLIGHTER, new CommandMethods(this::highlighter, this::highlighterCompleter));
      this.registerCommands(commandName, commandExecute);
   }

   public void setLineReader(LineReader reader) {
      this.reader = reader;
   }

   private void less(CommandInput input) {
      try {
         Commands.less(input.terminal(), input.in(), input.out(), input.err(), (Path)this.workDir.get(), input.xargs(), this.configPath);
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void nano(CommandInput input) {
      try {
         Commands.nano(input.terminal(), input.out(), input.err(), (Path)this.workDir.get(), input.args(), this.configPath);
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void history(CommandInput input) {
      try {
         Commands.history(this.reader, input.out(), input.err(), (Path)this.workDir.get(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void widget(CommandInput input) {
      try {
         Commands.widget(this.reader, input.out(), input.err(), this.widgetCreator, input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void keymap(CommandInput input) {
      try {
         Commands.keymap(this.reader, input.out(), input.err(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void setopt(CommandInput input) {
      try {
         Commands.setopt(this.reader, input.out(), input.err(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void setvar(CommandInput input) {
      try {
         Commands.setvar(this.reader, input.out(), input.err(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void unsetopt(CommandInput input) {
      try {
         Commands.unsetopt(this.reader, input.out(), input.err(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void ttop(CommandInput input) {
      try {
         TTop.ttop(input.terminal(), input.out(), input.err(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void colors(CommandInput input) {
      try {
         Commands.colors(input.terminal(), input.out(), input.args());
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private void highlighter(CommandInput input) {
      try {
         Commands.highlighter(this.reader, input.terminal(), input.out(), input.err(), input.args(), this.configPath);
      } catch (Exception e) {
         this.saveException(e);
      }

   }

   private List unsetOptions(boolean set) {
      List<String> out = new ArrayList();

      for(LineReader.Option option : LineReader.Option.values()) {
         if (set == (this.reader.isSet(option) == option.isDef())) {
            out.add((option.isDef() ? "no-" : "") + option.toString().toLowerCase().replace('_', '-'));
         }
      }

      return out;
   }

   private List highlighterCompleter(String name) {
      List<Completer> completers = new ArrayList();
      List<Completers.OptDesc> optDescs = this.commandOptions(name);

      for(Completers.OptDesc o : optDescs) {
         if (o.shortOption() != null && (o.shortOption().equals("-v") || o.shortOption().equals("-s"))) {
            Path userConfig = null;
            if (o.shortOption().equals("-s")) {
               try {
                  userConfig = this.configPath.getUserConfig("jnanorc");
               } catch (IOException var8) {
               }
            }

            if (o.shortOption().equals("-v") || userConfig != null) {
               Path ct = SyntaxHighlighter.build(this.configPath.getConfig("jnanorc"), (String)null).getCurrentTheme();
               if (ct != null) {
                  o.setValueCompleter(new Completers.FilesCompleter(ct.getParent(), "*.nanorctheme"));
               }
            }
         }
      }

      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(NullCompleter.INSTANCE, optDescs, 1)}));
      return completers;
   }

   private Set allWidgets() {
      Set<String> out = new HashSet();

      for(String s : this.reader.getWidgets().keySet()) {
         out.add(s);
         out.add(((Widget)this.reader.getWidgets().get(s)).toString());
      }

      return out;
   }

   private List nanoCompleter(String name) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(new Completers.FilesCompleter(this.workDir), this::commandOptions, 1)}));
      return completers;
   }

   private List lessCompleter(String name) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(new Completers.FilesCompleter(this.workDir), this::commandOptions, 1)}));
      return completers;
   }

   private List historyCompleter(String name) {
      List<Completer> completers = new ArrayList();
      List<Completers.OptDesc> optDescs = this.commandOptions(name);

      for(Completers.OptDesc o : optDescs) {
         if (o.shortOption() != null && (o.shortOption().equals("-A") || o.shortOption().equals("-W") || o.shortOption().equals("-R"))) {
            o.setValueCompleter(new Completers.FilesCompleter(this.workDir));
         }
      }

      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(NullCompleter.INSTANCE, optDescs, 1)}));
      return completers;
   }

   private List widgetCompleter(String name) {
      List<Completer> completers = new ArrayList();
      List<Completers.OptDesc> optDescs = this.commandOptions(name);
      Candidate aliasOption = new Candidate("-A", "-A", (String)null, (String)null, (String)null, (String)null, true);
      Iterator<Completers.OptDesc> i = optDescs.iterator();

      while(i.hasNext()) {
         Completers.OptDesc o = (Completers.OptDesc)i.next();
         if (o.shortOption() != null) {
            if (o.shortOption().equals("-D")) {
               o.setValueCompleter(new StringsCompleter(() -> this.reader.getWidgets().keySet()));
            } else if (o.shortOption().equals("-A")) {
               aliasOption = new Candidate(o.shortOption(), o.shortOption(), (String)null, o.description(), (String)null, (String)null, true);
               i.remove();
            }
         }
      }

      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(NullCompleter.INSTANCE, optDescs, 1)}));
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new StringsCompleter(new Candidate[]{aliasOption}), new StringsCompleter(this::allWidgets), new StringsCompleter(() -> this.reader.getWidgets().keySet()), NullCompleter.INSTANCE}));
      return completers;
   }

   private List setvarCompleter(String name) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new StringsCompleter(() -> this.reader.getVariables().keySet()), NullCompleter.INSTANCE}));
      return completers;
   }

   private List setoptCompleter(String name) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new StringsCompleter(() -> this.unsetOptions(true))}));
      return completers;
   }

   private List unsetoptCompleter(String name) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new StringsCompleter(() -> this.unsetOptions(false))}));
      return completers;
   }

   public static enum Command {
      NANO,
      LESS,
      HISTORY,
      WIDGET,
      KEYMAP,
      SETOPT,
      SETVAR,
      UNSETOPT,
      TTOP,
      COLORS,
      HIGHLIGHTER;

      // $FF: synthetic method
      private static Command[] $values() {
         return new Command[]{NANO, LESS, HISTORY, WIDGET, KEYMAP, SETOPT, SETVAR, UNSETOPT, TTOP, COLORS, HIGHLIGHTER};
      }
   }
}

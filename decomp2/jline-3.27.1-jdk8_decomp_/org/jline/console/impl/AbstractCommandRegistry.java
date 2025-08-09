package org.jline.console.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jline.console.CmdDesc;
import org.jline.console.CommandInput;
import org.jline.console.CommandMethods;
import org.jline.console.CommandRegistry;
import org.jline.reader.impl.completer.SystemCompleter;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;

public abstract class AbstractCommandRegistry implements CommandRegistry {
   private CmdRegistry cmdRegistry;
   private Exception exception;

   public CmdDesc doHelpDesc(String command, List info, CmdDesc cmdDesc) {
      List<AttributedString> mainDesc = new ArrayList();
      AttributedStringBuilder asb = new AttributedStringBuilder();
      asb.append((CharSequence)command.toLowerCase()).append((CharSequence)" -  ");

      for(String s : info) {
         if (asb.length() == 0) {
            asb.append((CharSequence)"\t");
         }

         asb.append((CharSequence)s);
         mainDesc.add(asb.toAttributedString());
         asb = new AttributedStringBuilder();
         asb.tabs(2);
      }

      asb = new AttributedStringBuilder();
      asb.tabs(7);
      asb.append((CharSequence)"Usage:");

      for(AttributedString as : cmdDesc.getMainDesc()) {
         asb.append((CharSequence)"\t");
         asb.append(as);
         mainDesc.add(asb.toAttributedString());
         asb = new AttributedStringBuilder();
         asb.tabs(7);
      }

      return new CmdDesc(mainDesc, new ArrayList(), cmdDesc.getOptsDesc());
   }

   public void registerCommands(Map commandName, Map commandExecute) {
      this.cmdRegistry = new EnumCmdRegistry(commandName, commandExecute);
   }

   public void registerCommands(Map commandExecute) {
      this.cmdRegistry = new NameCmdRegistry(commandExecute);
   }

   public Object invoke(CommandRegistry.CommandSession session, String command, Object... args) throws Exception {
      this.exception = null;
      CommandMethods methods = this.getCommandMethods(command);
      Object out = methods.execute().apply(new CommandInput(command, args, session));
      if (this.exception != null) {
         throw this.exception;
      } else {
         return out;
      }
   }

   public void saveException(Exception exception) {
      this.exception = exception;
   }

   public boolean hasCommand(String command) {
      return this.cmdRegistry.hasCommand(command);
   }

   public Set commandNames() {
      return this.cmdRegistry.commandNames();
   }

   public Map commandAliases() {
      return this.cmdRegistry.commandAliases();
   }

   public void rename(Enum command, String newName) {
      this.cmdRegistry.rename(command, newName);
   }

   public void alias(String alias, String command) {
      this.cmdRegistry.alias(alias, command);
   }

   public SystemCompleter compileCompleters() {
      return this.cmdRegistry.compileCompleters();
   }

   public CommandMethods getCommandMethods(String command) {
      return this.cmdRegistry.getCommandMethods(command);
   }

   public Object registeredCommand(String command) {
      return this.cmdRegistry.command(command);
   }

   private static class EnumCmdRegistry implements CmdRegistry {
      private final Map commandName;
      private Map nameCommand = new HashMap();
      private final Map commandExecute;
      private final Map aliasCommand = new HashMap();

      public EnumCmdRegistry(Map commandName, Map commandExecute) {
         this.commandName = commandName;
         this.commandExecute = commandExecute;
         this.doNameCommand();
      }

      private void doNameCommand() {
         this.nameCommand = (Map)this.commandName.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
      }

      public Set commandNames() {
         return this.nameCommand.keySet();
      }

      public Map commandAliases() {
         return this.aliasCommand;
      }

      public void rename(Enum command, String newName) {
         if (this.nameCommand.containsKey(newName)) {
            throw new IllegalArgumentException("Duplicate command name!");
         } else if (!this.commandName.containsKey(command)) {
            throw new IllegalArgumentException("Command does not exists!");
         } else {
            this.commandName.put(command, newName);
            this.doNameCommand();
         }
      }

      public void alias(String alias, String command) {
         if (!this.nameCommand.containsKey(command)) {
            throw new IllegalArgumentException("Command does not exists!");
         } else {
            this.aliasCommand.put(alias, command);
         }
      }

      public boolean hasCommand(String name) {
         return this.nameCommand.containsKey(name) || this.aliasCommand.containsKey(name);
      }

      public SystemCompleter compileCompleters() {
         SystemCompleter out = new SystemCompleter();

         for(Map.Entry entry : this.commandName.entrySet()) {
            out.add((String)entry.getValue(), (List)((CommandMethods)this.commandExecute.get(entry.getKey())).compileCompleter().apply((String)entry.getValue()));
         }

         out.addAliases(this.aliasCommand);
         return out;
      }

      public Enum command(String name) {
         name = (String)this.aliasCommand.getOrDefault(name, name);
         if (this.nameCommand.containsKey(name)) {
            T out = (T)((Enum)this.nameCommand.get(name));
            return out;
         } else {
            throw new IllegalArgumentException("Command does not exists!");
         }
      }

      public CommandMethods getCommandMethods(String command) {
         return (CommandMethods)this.commandExecute.get(this.command(command));
      }
   }

   private static class NameCmdRegistry implements CmdRegistry {
      private final Map commandExecute;
      private final Map aliasCommand = new HashMap();

      public NameCmdRegistry(Map commandExecute) {
         this.commandExecute = commandExecute;
      }

      public Set commandNames() {
         return this.commandExecute.keySet();
      }

      public Map commandAliases() {
         return this.aliasCommand;
      }

      public void rename(Enum command, String newName) {
         throw new IllegalArgumentException();
      }

      public void alias(String alias, String command) {
         if (!this.commandExecute.containsKey(command)) {
            throw new IllegalArgumentException("Command does not exists!");
         } else {
            this.aliasCommand.put(alias, command);
         }
      }

      public boolean hasCommand(String name) {
         return this.commandExecute.containsKey(name) || this.aliasCommand.containsKey(name);
      }

      public SystemCompleter compileCompleters() {
         SystemCompleter out = new SystemCompleter();

         for(String c : this.commandExecute.keySet()) {
            out.add(c, (List)((CommandMethods)this.commandExecute.get(c)).compileCompleter().apply(c));
         }

         out.addAliases(this.aliasCommand);
         return out;
      }

      public String command(String name) {
         return this.commandExecute.containsKey(name) ? name : (String)this.aliasCommand.get(name);
      }

      public CommandMethods getCommandMethods(String command) {
         return (CommandMethods)this.commandExecute.get(this.command(command));
      }
   }

   private interface CmdRegistry {
      boolean hasCommand(String var1);

      Set commandNames();

      Map commandAliases();

      Object command(String var1);

      void rename(Enum var1, String var2);

      void alias(String var1, String var2);

      SystemCompleter compileCompleters();

      CommandMethods getCommandMethods(String var1);
   }
}

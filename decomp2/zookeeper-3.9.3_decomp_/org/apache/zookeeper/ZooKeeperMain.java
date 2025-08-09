package org.apache.zookeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.admin.ZooKeeperAdmin;
import org.apache.zookeeper.cli.CliCommand;
import org.apache.zookeeper.cli.CliException;
import org.apache.zookeeper.cli.CommandFactory;
import org.apache.zookeeper.cli.CommandNotFoundException;
import org.apache.zookeeper.cli.MalformedCommandException;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class ZooKeeperMain {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperMain.class);
   static final Map commandMap = new HashMap();
   static final Map commandMapCli = new HashMap();
   protected MyCommandOptions cl = new MyCommandOptions();
   protected HashMap history = new HashMap();
   protected int commandCount = 0;
   protected boolean printWatches = true;
   protected int exitCode;
   protected ZooKeeper zk;
   protected String host;
   private CountDownLatch connectLatch;

   public boolean getPrintWatches() {
      return this.printWatches;
   }

   static void usage() {
      System.err.println("ZooKeeper -server host:port -client-configuration properties-file cmd args");
      List<String> cmdList = new ArrayList(commandMap.keySet());
      Collections.sort(cmdList);

      for(String cmd : cmdList) {
         System.err.println("\t" + cmd + " " + (String)commandMap.get(cmd));
      }

   }

   protected void addToHistory(int i, String cmd) {
      this.history.put(i, cmd);
   }

   public static List getCommands() {
      List<String> cmdList = new ArrayList(commandMap.keySet());
      Collections.sort(cmdList);
      return cmdList;
   }

   protected String getPrompt() {
      return "[zk: " + this.host + "(" + this.zk.getState() + ") " + this.commandCount + "] ";
   }

   public static void printMessage(String msg) {
      System.out.println("\n" + msg);
   }

   protected void connectToZK(String newHost) throws InterruptedException, IOException {
      if (this.zk != null && this.zk.getState().isAlive()) {
         this.zk.close();
      }

      this.host = newHost;
      boolean readOnly = this.cl.getOption("readonly") != null;
      if (this.cl.getOption("secure") != null) {
         System.setProperty("zookeeper.client.secure", "true");
         System.out.println("Secure connection is enabled");
      }

      ZKClientConfig clientConfig = null;
      if (this.cl.getOption("client-configuration") != null) {
         try {
            clientConfig = new ZKClientConfig(this.cl.getOption("client-configuration"));
         } catch (QuorumPeerConfig.ConfigException e) {
            e.printStackTrace();
            ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
         }
      }

      if (this.cl.getOption("waitforconnection") != null) {
         this.connectLatch = new CountDownLatch(1);
      }

      int timeout = Integer.parseInt(this.cl.getOption("timeout"));
      this.zk = new ZooKeeperAdmin(this.host, timeout, new MyWatcher(), readOnly, clientConfig);
      if (this.connectLatch != null && !this.connectLatch.await((long)timeout, TimeUnit.MILLISECONDS)) {
         this.zk.close();
         throw new IOException(KeeperException.create(KeeperException.Code.CONNECTIONLOSS));
      }
   }

   public static void main(String[] args) throws IOException, InterruptedException {
      ZooKeeperMain main = new ZooKeeperMain(args);
      main.run();
   }

   public ZooKeeperMain(String[] args) throws IOException, InterruptedException {
      this.exitCode = ExitCode.EXECUTION_FINISHED.getValue();
      this.host = "";
      this.connectLatch = null;
      this.cl.parseOptions(args);
      System.out.println("Connecting to " + this.cl.getOption("server"));
      this.connectToZK(this.cl.getOption("server"));
   }

   public ZooKeeperMain(ZooKeeper zk) {
      this.exitCode = ExitCode.EXECUTION_FINISHED.getValue();
      this.host = "";
      this.connectLatch = null;
      this.zk = zk;
   }

   void run() throws IOException, InterruptedException {
      if (this.cl.getCommand() == null) {
         System.out.println("Welcome to ZooKeeper!");
         boolean jlinemissing = false;

         try {
            Class<?> consoleC = Class.forName("jline.console.ConsoleReader");
            Class<?> completorC = Class.forName("org.apache.zookeeper.JLineZNodeCompleter");
            System.out.println("JLine support is enabled");
            Object console = consoleC.getConstructor().newInstance();
            Object completor = completorC.getConstructor(ZooKeeper.class).newInstance(this.zk);
            Method addCompletor = consoleC.getMethod("addCompleter", Class.forName("jline.console.completer.Completer"));
            addCompletor.invoke(console, completor);
            Method readLine = consoleC.getMethod("readLine", String.class);

            String line;
            while((line = (String)readLine.invoke(console, this.getPrompt())) != null) {
               this.executeLine(line);
            }
         } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            LOG.debug("Unable to start jline", e);
            jlinemissing = true;
         }

         if (jlinemissing) {
            System.out.println("JLine support is disabled");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while((line = br.readLine()) != null) {
               this.executeLine(line);
            }
         }
      } else {
         this.processCmd(this.cl);
      }

      ServiceUtils.requestSystemExit(this.exitCode);
   }

   public void executeLine(String line) throws InterruptedException, IOException {
      if (!line.equals("")) {
         this.cl.parseCommand(line);
         this.addToHistory(this.commandCount, line);
         this.processCmd(this.cl);
         ++this.commandCount;
      }

   }

   protected boolean processCmd(MyCommandOptions co) throws IOException, InterruptedException {
      boolean watch = false;

      try {
         watch = this.processZKCmd(co);
         this.exitCode = ExitCode.EXECUTION_FINISHED.getValue();
      } catch (CliException ex) {
         this.exitCode = ex.getExitCode();
         System.err.println(ex.getMessage());
      }

      return watch;
   }

   protected boolean processZKCmd(MyCommandOptions co) throws CliException, IOException, InterruptedException {
      String[] args = co.getArgArray();
      String cmd = co.getCommand();
      if (args.length < 1) {
         usage();
         throw new MalformedCommandException("No command entered");
      } else if (!commandMap.containsKey(cmd)) {
         usage();
         throw new CommandNotFoundException("Command not found " + cmd);
      } else {
         boolean watch = false;
         LOG.debug("Processing {}", cmd);
         if (cmd.equals("quit")) {
            this.zk.close();
            ServiceUtils.requestSystemExit(this.exitCode);
         } else if (cmd.equals("redo") && args.length >= 2) {
            Integer i = Integer.decode(args[1]);
            if (this.commandCount <= i || i < 0) {
               throw new MalformedCommandException("Command index out of range");
            }

            this.cl.parseCommand((String)this.history.get(i));
            if (this.cl.getCommand().equals("redo")) {
               throw new MalformedCommandException("No redoing redos");
            }

            this.history.put(this.commandCount, (String)this.history.get(i));
            this.processCmd(this.cl);
         } else if (cmd.equals("history")) {
            for(int i = this.commandCount - 10; i <= this.commandCount; ++i) {
               if (i >= 0) {
                  System.out.println(i + " - " + (String)this.history.get(i));
               }
            }
         } else if (cmd.equals("printwatches")) {
            if (args.length == 1) {
               System.out.println("printwatches is " + (this.printWatches ? "on" : "off"));
            } else {
               this.printWatches = args[1].equals("on");
            }
         } else if (cmd.equals("connect")) {
            if (args.length >= 2) {
               this.connectToZK(args[1]);
            } else {
               this.connectToZK(this.host);
            }
         }

         if (this.zk != null && this.zk.getState().isAlive()) {
            CliCommand cliCmd = (CliCommand)commandMapCli.get(cmd);
            if (cliCmd != null) {
               cliCmd.setZk(this.zk);
               watch = cliCmd.parse(args).exec();
            } else if (!commandMap.containsKey(cmd)) {
               usage();
            }

            return watch;
         } else {
            System.out.println("Not connected");
            return false;
         }
      }
   }

   static {
      commandMap.put("connect", "host:port");
      commandMap.put("history", "");
      commandMap.put("redo", "cmdno");
      commandMap.put("printwatches", "on|off");
      commandMap.put("quit", "");
      Stream.of(CommandFactory.Command.values()).map((command) -> CommandFactory.getInstance(command)).forEach((cliCommand) -> {
         cliCommand.addToMap(commandMapCli);
         commandMap.put(cliCommand.getCmdStr(), cliCommand.getOptionStr());
      });
   }

   private class MyWatcher implements Watcher {
      private MyWatcher() {
      }

      public void process(WatchedEvent event) {
         if (ZooKeeperMain.this.getPrintWatches()) {
            ZooKeeperMain.printMessage("WATCHER::");
            ZooKeeperMain.printMessage(event.toString());
         }

         if (ZooKeeperMain.this.connectLatch != null && event.getType() == Watcher.Event.EventType.None && event.getState() == Watcher.Event.KeeperState.SyncConnected) {
            ZooKeeperMain.this.connectLatch.countDown();
         }

      }
   }

   static class MyCommandOptions {
      private Map options = new HashMap();
      private List cmdArgs = null;
      private String command = null;
      public static final Pattern ARGS_PATTERN = Pattern.compile("\\s*([^\"']\\S*|\"[^\"]*\"|'[^']*')\\s*");
      public static final Pattern QUOTED_PATTERN = Pattern.compile("^(['\"])(.*)(\\1)$");

      public MyCommandOptions() {
         this.options.put("server", "localhost:2181");
         this.options.put("timeout", "30000");
      }

      public String getOption(String opt) {
         return (String)this.options.get(opt);
      }

      public String getCommand() {
         return this.command;
      }

      public String getCmdArgument(int index) {
         return (String)this.cmdArgs.get(index);
      }

      public int getNumArguments() {
         return this.cmdArgs.size();
      }

      public String[] getArgArray() {
         return (String[])this.cmdArgs.toArray(new String[0]);
      }

      public boolean parseOptions(String[] args) {
         List<String> argList = Arrays.asList(args);
         Iterator<String> it = argList.iterator();

         while(it.hasNext()) {
            String opt = (String)it.next();

            try {
               if (opt.equals("-server")) {
                  this.options.put("server", (String)it.next());
               } else if (opt.equals("-timeout")) {
                  this.options.put("timeout", (String)it.next());
               } else if (opt.equals("-r")) {
                  this.options.put("readonly", "true");
               } else if (opt.equals("-client-configuration")) {
                  this.options.put("client-configuration", (String)it.next());
               } else if (opt.equals("-waitforconnection")) {
                  this.options.put("waitforconnection", "true");
               }
            } catch (NoSuchElementException var6) {
               System.err.println("Error: no argument found for option " + opt);
               return false;
            }

            if (!opt.startsWith("-")) {
               this.command = opt;
               this.cmdArgs = new ArrayList();
               this.cmdArgs.add(this.command);

               while(it.hasNext()) {
                  this.cmdArgs.add((String)it.next());
               }

               return true;
            }
         }

         return true;
      }

      public boolean parseCommand(String cmdstring) {
         Matcher matcher = ARGS_PATTERN.matcher(cmdstring);

         List<String> args;
         String value;
         for(args = new LinkedList(); matcher.find(); args.add(value)) {
            value = matcher.group(1);
            if (QUOTED_PATTERN.matcher(value).matches()) {
               value = value.substring(1, value.length() - 1);
            }
         }

         if (args.isEmpty()) {
            return false;
         } else {
            this.command = (String)args.get(0);
            this.cmdArgs = args;
            return true;
         }
      }
   }
}

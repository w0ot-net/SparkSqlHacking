package org.apache.zookeeper.cli;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SyncCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   public static final long SYNC_TIMEOUT;

   public SyncCommand() {
      super("sync", "path");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      CommandLine cl;
      try {
         cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = cl.getArgs();
      if (this.args.length < 2) {
         throw new CliParseException(this.getUsageStr());
      } else {
         return this;
      }
   }

   public boolean exec() throws CliException {
      String path = this.args[1];
      CompletableFuture<Integer> cf = new CompletableFuture();

      try {
         this.zk.sync(path, (rc, path1, ctx) -> cf.complete(rc), (Object)null);
         int resultCode = (Integer)cf.get(SYNC_TIMEOUT, TimeUnit.MILLISECONDS);
         if (resultCode == 0) {
            this.out.println("Sync is OK");
         } else {
            this.out.println("Sync has failed. rc=" + resultCode);
         }

         return false;
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException ie) {
         Thread.currentThread().interrupt();
         throw new CliWrapperException(ie);
      } catch (ExecutionException | TimeoutException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      SYNC_TIMEOUT = TimeUnit.SECONDS.toMillis(30L);
   }
}

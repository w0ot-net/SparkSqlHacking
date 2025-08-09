package org.apache.zookeeper.cli;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.EphemeralType;

public class CreateCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public CreateCommand() {
      super("create", "[-s] [-e] [-c] [-t ttl] path [data] [acl]");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = this.cl.getArgs();
      if (this.args.length < 2) {
         throw new CliParseException(this.getUsageStr());
      } else {
         return this;
      }
   }

   public boolean exec() throws CliException {
      boolean hasE = this.cl.hasOption("e");
      boolean hasS = this.cl.hasOption("s");
      boolean hasC = this.cl.hasOption("c");
      boolean hasT = this.cl.hasOption("t");
      if (!hasC || !hasE && !hasS) {
         long ttl;
         try {
            ttl = hasT ? Long.parseLong(this.cl.getOptionValue("t")) : 0L;
         } catch (NumberFormatException var17) {
            throw new MalformedCommandException("-t argument must be a long value");
         }

         if (hasT && hasE) {
            throw new MalformedCommandException("TTLs cannot be used with Ephemeral znodes");
         } else if (hasT && hasC) {
            throw new MalformedCommandException("TTLs cannot be used with Container znodes");
         } else {
            CreateMode flags;
            if (hasE && hasS) {
               flags = CreateMode.EPHEMERAL_SEQUENTIAL;
            } else if (hasE) {
               flags = CreateMode.EPHEMERAL;
            } else if (hasS) {
               flags = hasT ? CreateMode.PERSISTENT_SEQUENTIAL_WITH_TTL : CreateMode.PERSISTENT_SEQUENTIAL;
            } else if (hasC) {
               flags = CreateMode.CONTAINER;
            } else {
               flags = hasT ? CreateMode.PERSISTENT_WITH_TTL : CreateMode.PERSISTENT;
            }

            if (hasT) {
               try {
                  EphemeralType.TTL.toEphemeralOwner(ttl);
               } catch (IllegalArgumentException e) {
                  throw new MalformedCommandException(e.getMessage());
               }
            }

            String path = this.args[1];
            byte[] data = null;
            if (this.args.length > 2) {
               data = this.args[2].getBytes(StandardCharsets.UTF_8);
            }

            List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
            if (this.args.length > 3) {
               acl = AclParser.parse(this.args[3]);
            }

            try {
               String newPath = hasT ? this.zk.create(path, data, acl, flags, new Stat(), ttl) : this.zk.create(path, data, acl, flags);
               this.err.println("Created " + newPath);
               return true;
            } catch (IllegalArgumentException ex) {
               throw new MalformedPathException(ex.getMessage());
            } catch (KeeperException.EphemeralOnLocalSessionException e) {
               this.err.println("Unable to create ephemeral node on a local session");
               throw new CliWrapperException(e);
            } catch (KeeperException.InvalidACLException ex) {
               this.err.println(ex.getMessage());
               throw new CliWrapperException(ex);
            } catch (InterruptedException | KeeperException ex) {
               throw new CliWrapperException(ex);
            }
         }
      } else {
         throw new MalformedCommandException("-c cannot be combined with -s or -e. Containers cannot be ephemeral or sequential.");
      }
   }

   static {
      options.addOption(new Option("e", false, "ephemeral"));
      options.addOption(new Option("s", false, "sequential"));
      options.addOption(new Option("c", false, "container"));
      options.addOption(new Option("t", true, "ttl"));
   }
}

package org.apache.commons.compress.archivers.sevenz;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class CLI {
   private static Mode grabMode(String[] args) {
      return args.length < 2 ? CLI.Mode.LIST : (Mode)Enum.valueOf(Mode.class, args[1].toUpperCase(Locale.ROOT));
   }

   public static void main(String[] args) throws Exception {
      if (args.length == 0) {
         usage();
      } else {
         Mode mode = grabMode(args);
         System.out.println(mode.getMessage() + " " + args[0]);
         File file = new File(args[0]);
         if (!file.isFile()) {
            System.err.println(file + " doesn't exist or is a directory");
         }

         SevenZFile archive = ((SevenZFile.Builder)SevenZFile.builder().setFile(file)).get();

         SevenZArchiveEntry ae;
         try {
            while((ae = archive.getNextEntry()) != null) {
               mode.takeAction(archive, ae);
            }
         } catch (Throwable var7) {
            if (archive != null) {
               try {
                  archive.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (archive != null) {
            archive.close();
         }

      }
   }

   private static void usage() {
      System.out.println("Parameters: archive-name [list]");
   }

   private static enum Mode {
      LIST("Analysing") {
         private String getContentMethods(SevenZArchiveEntry entry) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;

            for(SevenZMethodConfiguration m : entry.getContentMethods()) {
               if (!first) {
                  sb.append(", ");
               }

               first = false;
               sb.append(m.getMethod());
               if (m.getOptions() != null) {
                  sb.append("(").append(m.getOptions()).append(")");
               }
            }

            return sb.toString();
         }

         public void takeAction(SevenZFile archive, SevenZArchiveEntry entry) {
            System.out.print(entry.getName());
            if (entry.isDirectory()) {
               System.out.print(" dir");
            } else {
               System.out.print(" " + entry.getCompressedSize() + "/" + entry.getSize());
            }

            if (entry.getHasLastModifiedDate()) {
               System.out.print(" " + entry.getLastModifiedDate());
            } else {
               System.out.print(" no last modified date");
            }

            if (!entry.isDirectory()) {
               System.out.println(" " + this.getContentMethods(entry));
            } else {
               System.out.println();
            }

         }
      };

      private final String message;

      private Mode(String message) {
         this.message = message;
      }

      public String getMessage() {
         return this.message;
      }

      public abstract void takeAction(SevenZFile var1, SevenZArchiveEntry var2) throws IOException;

      // $FF: synthetic method
      private static Mode[] $values() {
         return new Mode[]{LIST};
      }
   }
}

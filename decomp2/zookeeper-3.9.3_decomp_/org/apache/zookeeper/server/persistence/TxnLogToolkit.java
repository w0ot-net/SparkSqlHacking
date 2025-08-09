package org.apache.zookeeper.server.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Adler32;
import java.util.zip.Checksum;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.util.LogChopper;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.apache.zookeeper.txn.CreateContainerTxn;
import org.apache.zookeeper.txn.CreateTTLTxn;
import org.apache.zookeeper.txn.CreateTxn;
import org.apache.zookeeper.txn.MultiTxn;
import org.apache.zookeeper.txn.SetDataTxn;
import org.apache.zookeeper.txn.Txn;
import org.apache.zookeeper.txn.TxnHeader;
import org.apache.zookeeper.util.ServiceUtils;

public class TxnLogToolkit implements Closeable {
   private File txnLogFile;
   private boolean recoveryMode = false;
   private boolean verbose = false;
   private FileInputStream txnFis;
   private BinaryInputArchive logStream;
   private int crcFixed = 0;
   private FileOutputStream recoveryFos;
   private BinaryOutputArchive recoveryOa;
   private File recoveryLogFile;
   private FilePadding filePadding = new FilePadding();
   private boolean force = false;
   private long zxid = -1L;

   public static void main(String[] args) throws Exception {
      try {
         TxnLogToolkit lt = parseCommandLine(args);

         try {
            if (lt.isDumpMode()) {
               lt.dump(new Scanner(System.in));
               lt.printStat();
            } else {
               lt.chop();
            }
         } catch (Throwable var5) {
            if (lt != null) {
               try {
                  lt.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (lt != null) {
            lt.close();
         }
      } catch (TxnLogToolkitParseException e) {
         System.err.println(e.getMessage() + "\n");
         printHelpAndExit(e.getExitCode(), e.getOptions());
      } catch (TxnLogToolkitException e) {
         System.err.println(e.getMessage());
         ServiceUtils.requestSystemExit(e.getExitCode());
      }

   }

   public TxnLogToolkit(boolean recoveryMode, boolean verbose, String txnLogFileName, boolean force) throws FileNotFoundException, TxnLogToolkitException {
      this.recoveryMode = recoveryMode;
      this.verbose = verbose;
      this.force = force;
      this.txnLogFile = this.loadTxnFile(txnLogFileName);
      if (recoveryMode) {
         this.recoveryLogFile = new File(this.txnLogFile.toString() + ".fixed");
         if (this.recoveryLogFile.exists()) {
            throw new TxnLogToolkitException(ExitCode.UNEXPECTED_ERROR.getValue(), "Recovery file %s already exists or not writable", new Object[]{this.recoveryLogFile});
         }
      }

      this.openTxnLogFile();
      if (recoveryMode) {
         this.openRecoveryFile();
      }

   }

   public TxnLogToolkit(String txnLogFileName, String zxidName) throws TxnLogToolkitException {
      this.txnLogFile = this.loadTxnFile(txnLogFileName);
      this.zxid = Long.decode(zxidName);
   }

   private File loadTxnFile(String txnLogFileName) throws TxnLogToolkitException {
      File logFile = new File(txnLogFileName);
      if (logFile.exists() && logFile.canRead()) {
         return logFile;
      } else {
         throw new TxnLogToolkitException(ExitCode.UNEXPECTED_ERROR.getValue(), "File doesn't exist or not readable: %s", new Object[]{logFile});
      }
   }

   public void dump(Scanner scanner) throws Exception {
      this.crcFixed = 0;
      FileHeader fhdr = new FileHeader();
      fhdr.deserialize(this.logStream, "fileheader");
      if (fhdr.getMagic() != FileTxnLog.TXNLOG_MAGIC) {
         throw new TxnLogToolkitException(ExitCode.INVALID_INVOCATION.getValue(), "Invalid magic number for %s", new Object[]{this.txnLogFile.getName()});
      } else {
         System.out.println("ZooKeeper Transactional Log File with dbid " + fhdr.getDbid() + " txnlog format version " + fhdr.getVersion());
         if (this.recoveryMode) {
            fhdr.serialize(this.recoveryOa, "fileheader");
            this.recoveryFos.flush();
            this.filePadding.setCurrentSize(this.recoveryFos.getChannel().position());
         }

         int count = 0;

         while(true) {
            long crcValue;
            byte[] bytes;
            try {
               crcValue = this.logStream.readLong("crcvalue");
               bytes = this.logStream.readBuffer("txnEntry");
            } catch (EOFException var8) {
               System.out.println("EOF reached after " + count + " txns.");
               return;
            }

            if (bytes.length == 0) {
               System.out.println("EOF reached after " + count + " txns.");
               return;
            }

            Checksum crc = new Adler32();
            crc.update(bytes, 0, bytes.length);
            if (crcValue != crc.getValue()) {
               if (this.recoveryMode) {
                  if (!this.force) {
                     this.printTxn(bytes, "CRC ERROR");
                     if (this.askForFix(scanner)) {
                        crcValue = crc.getValue();
                        ++this.crcFixed;
                     }
                  } else {
                     crcValue = crc.getValue();
                     this.printTxn(bytes, "CRC FIXED");
                     ++this.crcFixed;
                  }
               } else {
                  this.printTxn(bytes, "CRC ERROR");
               }
            }

            if (!this.recoveryMode || this.verbose) {
               this.printTxn(bytes);
            }

            if (this.logStream.readByte("EOR") != 66) {
               throw new TxnLogToolkitException(ExitCode.UNEXPECTED_ERROR.getValue(), "Last transaction was partial.", new Object[0]);
            }

            if (this.recoveryMode) {
               this.filePadding.padFile(this.recoveryFos.getChannel());
               this.recoveryOa.writeLong(crcValue, "crcvalue");
               this.recoveryOa.writeBuffer(bytes, "txnEntry");
               this.recoveryOa.writeByte((byte)66, "EOR");
            }

            ++count;
         }
      }
   }

   public void chop() {
      File targetFile = new File(this.txnLogFile.getParentFile(), this.txnLogFile.getName() + ".chopped" + this.zxid);

      try {
         InputStream is = new BufferedInputStream(new FileInputStream(this.txnLogFile));

         try {
            OutputStream os = new BufferedOutputStream(new FileOutputStream(targetFile));

            try {
               if (!LogChopper.chop(is, os, this.zxid)) {
                  throw new TxnLogToolkitException(ExitCode.INVALID_INVOCATION.getValue(), "Failed to chop %s", new Object[]{this.txnLogFile.getName()});
               }
            } catch (Throwable var8) {
               try {
                  os.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            os.close();
         } catch (Throwable var9) {
            try {
               is.close();
            } catch (Throwable var6) {
               var9.addSuppressed(var6);
            }

            throw var9;
         }

         is.close();
      } catch (Exception e) {
         System.out.println("Got exception: " + e.getMessage());
      }

   }

   public boolean isDumpMode() {
      return this.zxid < 0L;
   }

   private boolean askForFix(Scanner scanner) throws TxnLogToolkitException {
      while(true) {
         System.out.print("Would you like to fix it (Yes/No/Abort) ? ");
         char answer = Character.toUpperCase(scanner.next().charAt(0));
         switch (answer) {
            case 'A':
               throw new TxnLogToolkitException(ExitCode.EXECUTION_FINISHED.getValue(), "Recovery aborted.", new Object[0]);
            case 'N':
               return false;
            case 'Y':
               return true;
         }
      }
   }

   private void printTxn(byte[] bytes) throws IOException {
      this.printTxn(bytes, "");
   }

   private void printTxn(byte[] bytes, String prefix) throws IOException {
      TxnLogEntry logEntry = SerializeUtils.deserializeTxn(bytes);
      TxnHeader hdr = logEntry.getHeader();
      Record txn = logEntry.getTxn();
      String txnStr = getFormattedTxnStr(txn);
      String txns = String.format("%s session 0x%s cxid 0x%s zxid 0x%s %s %s", DateFormat.getDateTimeInstance(3, 1).format(new Date(hdr.getTime())), Long.toHexString(hdr.getClientId()), Long.toHexString((long)hdr.getCxid()), Long.toHexString(hdr.getZxid()), Request.op2String(hdr.getType()), txnStr);
      if (prefix != null && !"".equals(prefix.trim())) {
         System.out.print(prefix + " - ");
      }

      if (txns.endsWith("\n")) {
         System.out.print(txns);
      } else {
         System.out.println(txns);
      }

   }

   private static String getFormattedTxnStr(Record txn) throws IOException {
      StringBuilder txnData = new StringBuilder();
      if (txn == null) {
         return txnData.toString();
      } else {
         if (txn instanceof CreateTxn) {
            CreateTxn createTxn = (CreateTxn)txn;
            txnData.append(createTxn.getPath() + "," + checkNullToEmpty(createTxn.getData())).append("," + createTxn.getAcl() + "," + createTxn.getEphemeral()).append("," + createTxn.getParentCVersion());
         } else if (txn instanceof SetDataTxn) {
            SetDataTxn setDataTxn = (SetDataTxn)txn;
            txnData.append(setDataTxn.getPath() + "," + checkNullToEmpty(setDataTxn.getData())).append("," + setDataTxn.getVersion());
         } else if (txn instanceof CreateContainerTxn) {
            CreateContainerTxn createContainerTxn = (CreateContainerTxn)txn;
            txnData.append(createContainerTxn.getPath() + "," + checkNullToEmpty(createContainerTxn.getData())).append("," + createContainerTxn.getAcl() + "," + createContainerTxn.getParentCVersion());
         } else if (txn instanceof CreateTTLTxn) {
            CreateTTLTxn createTTLTxn = (CreateTTLTxn)txn;
            txnData.append(createTTLTxn.getPath() + "," + checkNullToEmpty(createTTLTxn.getData())).append("," + createTTLTxn.getAcl() + "," + createTTLTxn.getParentCVersion()).append("," + createTTLTxn.getTtl());
         } else if (txn instanceof MultiTxn) {
            MultiTxn multiTxn = (MultiTxn)txn;
            List<Txn> txnList = multiTxn.getTxns();

            for(int i = 0; i < txnList.size(); ++i) {
               Txn t = (Txn)txnList.get(i);
               if (i == 0) {
                  txnData.append(Request.op2String(t.getType()) + ":" + checkNullToEmpty(t.getData()));
               } else {
                  txnData.append(";" + Request.op2String(t.getType()) + ":" + checkNullToEmpty(t.getData()));
               }
            }
         } else {
            txnData.append(txn.toString());
         }

         return txnData.toString();
      }
   }

   private static String checkNullToEmpty(byte[] data) throws IOException {
      return data != null && data.length != 0 ? new String(data, StandardCharsets.UTF_8) : "";
   }

   private void openTxnLogFile() throws FileNotFoundException {
      this.txnFis = new FileInputStream(this.txnLogFile);
      this.logStream = BinaryInputArchive.getArchive(this.txnFis);
   }

   private void closeTxnLogFile() throws IOException {
      if (this.txnFis != null) {
         this.txnFis.close();
      }

   }

   private void openRecoveryFile() throws FileNotFoundException {
      this.recoveryFos = new FileOutputStream(this.recoveryLogFile);
      this.recoveryOa = BinaryOutputArchive.getArchive(this.recoveryFos);
   }

   private void closeRecoveryFile() throws IOException {
      if (this.recoveryFos != null) {
         this.recoveryFos.close();
      }

   }

   private static TxnLogToolkit parseCommandLine(String[] args) throws TxnLogToolkitException, FileNotFoundException {
      CommandLineParser parser = new DefaultParser();
      Options options = new Options();
      Option helpOpt = new Option("h", "help", false, "Print help message");
      options.addOption(helpOpt);
      Option recoverOpt = new Option("r", "recover", false, "Recovery mode. Re-calculate CRC for broken entries.");
      options.addOption(recoverOpt);
      Option quietOpt = new Option("v", "verbose", false, "Be verbose in recovery mode: print all entries, not just fixed ones.");
      options.addOption(quietOpt);
      Option dumpOpt = new Option("d", "dump", false, "Dump mode. Dump all entries of the log file with printing the content of a nodepath (default)");
      options.addOption(dumpOpt);
      Option forceOpt = new Option("y", "yes", false, "Non-interactive mode: repair all CRC errors without asking");
      options.addOption(forceOpt);
      Option chopOpt = new Option("c", "chop", false, "Chop mode. Chop txn file to a zxid.");
      Option zxidOpt = new Option("z", "zxid", true, "Used with chop. Zxid to which to chop.");
      options.addOption(chopOpt);
      options.addOption(zxidOpt);

      try {
         CommandLine cli = parser.parse(options, args);
         if (cli.hasOption("help")) {
            printHelpAndExit(0, options);
         }

         if (cli.getArgs().length < 1) {
            printHelpAndExit(1, options);
         }

         return cli.hasOption("chop") && cli.hasOption("zxid") ? new TxnLogToolkit(cli.getArgs()[0], cli.getOptionValue("zxid")) : new TxnLogToolkit(cli.hasOption("recover"), cli.hasOption("verbose"), cli.getArgs()[0], cli.hasOption("yes"));
      } catch (ParseException e) {
         throw new TxnLogToolkitParseException(options, ExitCode.UNEXPECTED_ERROR.getValue(), e.getMessage(), new Object[0]);
      }
   }

   private static void printHelpAndExit(int exitCode, Options options) {
      HelpFormatter help = new HelpFormatter();
      help.printHelp(120, "TxnLogToolkit [-dhrvc] <txn_log_file_name> (-z <zxid>)", "", options, "");
      ServiceUtils.requestSystemExit(exitCode);
   }

   private void printStat() {
      if (this.recoveryMode) {
         System.out.printf("Recovery file %s has been written with %d fixed CRC error(s)%n", this.recoveryLogFile, this.crcFixed);
      }

   }

   public void close() throws IOException {
      if (this.recoveryMode) {
         this.closeRecoveryFile();
      }

      this.closeTxnLogFile();
   }

   static class TxnLogToolkitException extends Exception {
      private static final long serialVersionUID = 1L;
      private int exitCode;

      TxnLogToolkitException(int exitCode, String message, Object... params) {
         super(String.format(message, params));
         this.exitCode = exitCode;
      }

      int getExitCode() {
         return this.exitCode;
      }
   }

   static class TxnLogToolkitParseException extends TxnLogToolkitException {
      private static final long serialVersionUID = 1L;
      private Options options;

      TxnLogToolkitParseException(Options options, int exitCode, String message, Object... params) {
         super(exitCode, message, params);
         this.options = options;
      }

      Options getOptions() {
         return this.options;
      }
   }
}

package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashSet;
import org.datanucleus.exceptions.NucleusFatalUserException;
import org.datanucleus.util.NucleusLogger;

public class StreamableSpooler {
   protected static StreamableSpooler _instance = new StreamableSpooler();
   protected StreamableSpoolerGC gcInstance = null;
   protected File spoolDirectory;

   public static StreamableSpooler instance() {
      return _instance;
   }

   private StreamableSpooler() {
      String spool = null;
      spool = System.getProperty("datanucleus.binarystream.spool.directory");
      if (spool != null) {
         File f = new File(spool);
         if (!f.isDirectory()) {
            throw new NucleusFatalUserException("Invalid binarystream spool directory:" + spool);
         }

         this.spoolDirectory = f;
      } else {
         spool = System.getProperty("user.dir");
         if (spool != null) {
            File f = new File(spool);
            if (!f.isDirectory()) {
               throw new NucleusFatalUserException("Invalid binarystream spool directory:" + spool);
            }

            this.spoolDirectory = f;
         }
      }

      if (spool == null) {
         throw new NucleusFatalUserException("Cannot get binary stream spool directory");
      }
   }

   public void spoolStreamTo(InputStream is, File target) throws IOException {
      copyStream(is, new BufferedOutputStream(new FileOutputStream(target)), false, true);
   }

   public File spoolStream(InputStream is) throws IOException {
      File spool = File.createTempFile("datanucleus.binarystream-", ".bin", this.spoolDirectory);
      if (this.gcInstance == null) {
         this.gcInstance = new StreamableSpoolerGC();
      }

      this.gcInstance.add(spool);
      NucleusLogger.GENERAL.debug("spool file created: " + spool.getAbsolutePath());
      spool.deleteOnExit();
      copyStream(is, new BufferedOutputStream(new FileOutputStream(spool)), false, true);
      return spool;
   }

   public StreamableSpoolerGC getGCInstance() {
      return this.gcInstance;
   }

   public static void copyStream(InputStream is, OutputStream os) throws IOException {
      copyStream(is, os, false, false);
   }

   public static void copyStream(InputStream is, OutputStream os, boolean close_src, boolean close_dest) throws IOException {
      int b;
      while((b = is.read()) != -1) {
         os.write(b);
      }

      if (close_src) {
         is.close();
      }

      if (close_dest) {
         os.close();
      }

   }

   public class StreamableSpoolerGC extends Thread {
      protected ReferenceQueue refQ = new ReferenceQueue();
      protected Collection references = new HashSet();

      public StreamableSpoolerGC() {
         this.setDaemon(true);
         this.start();
      }

      public void add(File f) {
         try {
            FileWeakReference fwr = StreamableSpooler.this.new FileWeakReference(f, this.refQ);
            this.references.add(fwr);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

      public void run() {
         while(true) {
            try {
               Reference ref = this.refQ.remove(0L);
               FileWeakReference fwr = (FileWeakReference)ref;
               fwr.gc();
               this.references.remove(fwr);
            } catch (IllegalArgumentException var3) {
            } catch (InterruptedException ex) {
               ex.printStackTrace();

               for(FileWeakReference fwr : this.references) {
                  System.err.println(fwr.getFilename() + " not gc'ed");
               }

               return;
            }
         }
      }

      public void interrupt() {
         System.gc();
         System.runFinalization();
         super.interrupt();
      }
   }

   class FileWeakReference extends WeakReference {
      protected String filename;

      public FileWeakReference(File f) throws IOException {
         super(f);
         this.filename = f.getCanonicalPath();
      }

      public FileWeakReference(File f, ReferenceQueue refQ) throws IOException {
         super(f, refQ);
         this.filename = f.getCanonicalPath();
      }

      public void gc() {
         if (this.filename != null) {
            File f = new File(this.filename);
            f.delete();
            System.err.println(this.filename + " deleted");
            this.filename = null;
         }

      }

      public String getFilename() {
         return this.filename;
      }
   }
}

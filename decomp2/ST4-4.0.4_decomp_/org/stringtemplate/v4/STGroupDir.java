package org.stringtemplate.v4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.antlr.runtime.ANTLRInputStream;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.ErrorType;
import org.stringtemplate.v4.misc.Misc;

public class STGroupDir extends STGroup {
   public String groupDirName;
   public URL root;

   public STGroupDir(String dirName) {
      this(dirName, '<', '>');
   }

   public STGroupDir(String dirName, char delimiterStartChar, char delimiterStopChar) {
      super(delimiterStartChar, delimiterStopChar);
      this.groupDirName = dirName;
      File dir = new File(dirName);
      if (dir.exists() && dir.isDirectory()) {
         try {
            this.root = dir.toURI().toURL();
         } catch (MalformedURLException e) {
            throw new STException("can't load dir " + dirName, e);
         }

         if (verbose) {
            System.out.println("STGroupDir(" + dirName + ") found at " + this.root);
         }
      } else {
         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         this.root = cl.getResource(dirName);
         if (this.root == null) {
            cl = this.getClass().getClassLoader();
            this.root = cl.getResource(dirName);
         }

         if (verbose) {
            System.out.println("STGroupDir(" + dirName + ") found via CLASSPATH at " + this.root);
         }

         if (this.root == null) {
            throw new IllegalArgumentException("No such directory: " + dirName);
         }
      }

   }

   public STGroupDir(String dirName, String encoding) {
      this(dirName, encoding, '<', '>');
   }

   public STGroupDir(String dirName, String encoding, char delimiterStartChar, char delimiterStopChar) {
      this(dirName, delimiterStartChar, delimiterStopChar);
      this.encoding = encoding;
   }

   public STGroupDir(URL root, String encoding, char delimiterStartChar, char delimiterStopChar) {
      super(delimiterStartChar, delimiterStopChar);
      this.root = root;
      this.encoding = encoding;
   }

   protected CompiledST load(String name) {
      if (verbose) {
         System.out.println("STGroupDir.load(" + name + ")");
      }

      String parent = Misc.getParent(name);
      String prefix = Misc.getPrefix(name);
      URL groupFileURL = null;

      try {
         groupFileURL = new URL(this.root + parent + ".stg");
      } catch (MalformedURLException e) {
         this.errMgr.internalError((ST)null, "bad URL: " + this.root + parent + ".stg", e);
         return null;
      }

      InputStream is = null;

      try {
         is = groupFileURL.openStream();
      } catch (FileNotFoundException var9) {
         String unqualifiedName = Misc.getFileName(name);
         return this.loadTemplateFile(prefix, unqualifiedName + ".st");
      } catch (IOException ioe) {
         this.errMgr.internalError((ST)null, "can't load template file " + name, ioe);
      }

      try {
         if (is != null) {
            is.close();
         }
      } catch (IOException ioe) {
         this.errMgr.internalError((ST)null, "can't close template file stream " + name, ioe);
      }

      this.loadGroupFile(prefix, this.root + parent + ".stg");
      return this.rawGetTemplate(name);
   }

   public CompiledST loadTemplateFile(String prefix, String unqualifiedFileName) {
      if (verbose) {
         System.out.println("loadTemplateFile(" + unqualifiedFileName + ") in groupdir " + "from " + this.root + " prefix=" + prefix);
      }

      URL f = null;

      try {
         f = new URL(this.root + prefix + unqualifiedFileName);
      } catch (MalformedURLException me) {
         this.errMgr.runTimeError((Interpreter)null, (ST)null, 0, ErrorType.INVALID_TEMPLATE_NAME, (Throwable)me, this.root + unqualifiedFileName);
         return null;
      }

      ANTLRInputStream fs;
      try {
         fs = new ANTLRInputStream(f.openStream(), this.encoding);
         fs.name = unqualifiedFileName;
      } catch (IOException var7) {
         if (verbose) {
            System.out.println(this.root + "/" + unqualifiedFileName + " doesn't exist");
         }

         return null;
      }

      return this.loadTemplateFile(prefix, unqualifiedFileName, fs);
   }

   public String getName() {
      return this.groupDirName;
   }

   public String getFileName() {
      return this.root.getFile();
   }

   public URL getRootDirURL() {
      return this.root;
   }
}

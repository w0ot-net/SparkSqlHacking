package org.datanucleus.store.schema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.FileSet;
import org.datanucleus.util.Localiser;

public class SchemaToolTask extends Java {
   private SchemaTool.Mode mode;
   private String schemaName;
   List filesets;

   public SchemaToolTask() {
      this.mode = SchemaTool.Mode.CREATE;
      this.filesets = new ArrayList();
      this.setClassname("org.datanucleus.store.schema.SchemaTool");
      this.setFork(true);
   }

   public void execute() throws BuildException {
      if (this.mode == SchemaTool.Mode.CREATE_SCHEMA) {
         if (this.schemaName == null) {
            throw new BuildException("If using 'create schema' then need to set schemaName");
         }

         this.createArg().setValue("-createSchema " + this.schemaName);
      } else if (this.mode == SchemaTool.Mode.DELETE_SCHEMA) {
         if (this.schemaName == null) {
            throw new BuildException("If using 'create schema' then need to set schemaName");
         }

         this.createArg().setValue("-deleteSchema " + this.schemaName);
      } else if (this.mode == SchemaTool.Mode.CREATE) {
         this.createArg().setValue("-create");
      } else if (this.mode == SchemaTool.Mode.DELETE) {
         this.createArg().setValue("-delete");
      } else if (this.mode == SchemaTool.Mode.DELETE_CREATE) {
         this.createArg().setValue("-deletecreate");
      } else if (this.mode == SchemaTool.Mode.VALIDATE) {
         this.createArg().setValue("-validate");
      } else if (this.mode == SchemaTool.Mode.DATABASE_INFO) {
         this.createArg().setValue("-dbinfo");
      } else if (this.mode == SchemaTool.Mode.SCHEMA_INFO) {
         this.createArg().setValue("-schemainfo");
      }

      File[] files = this.getFiles();

      for(int i = 0; i < files.length; ++i) {
         this.createArg().setFile(files[i]);
      }

      super.execute();
   }

   public void addFileSet(FileSet fs) {
      this.filesets.add(fs);
   }

   protected File[] getFiles() {
      List<File> v = new ArrayList();

      for(FileSet fs : this.filesets) {
         DirectoryScanner ds = fs.getDirectoryScanner(this.getProject());
         ds.scan();
         String[] f = ds.getIncludedFiles();

         for(int j = 0; j < f.length; ++j) {
            String pathname = f[j];
            File file = new File(ds.getBasedir(), pathname);
            file = this.getProject().resolveFile(file.getPath());
            v.add(file);
         }
      }

      return (File[])v.toArray(new File[v.size()]);
   }

   public void setVerbose(boolean verbose) {
      if (verbose) {
         this.createArg().setValue("-v");
         this.log("SchemaTool verbose: " + verbose, 3);
      }

   }

   public void setProps(String propsFileName) {
      if (propsFileName != null && propsFileName.length() > 0) {
         this.createArg().setLine("-props " + propsFileName);
         this.log("SchemaTool props: " + propsFileName, 3);
      }

   }

   public void setDdlFile(String file) {
      if (file != null && file.length() > 0) {
         this.createArg().setLine("-ddlFile " + file);
         this.log("SchemaTool ddlFile: " + file, 3);
      }

   }

   public void setSchemaName(String schemaName) {
      this.schemaName = schemaName;
   }

   public void setCompleteDdl(boolean complete) {
      if (complete) {
         this.createArg().setValue("-completeDdl");
         this.log("SchemaTool completeDdl: " + complete, 3);
      }

   }

   public void setIncludeAutoStart(boolean include) {
      if (include) {
         this.createArg().setValue("-includeAutoStart");
         this.log("SchemaTool includeAutoStart: " + include, 3);
      }

   }

   public void setPersistenceUnit(String unitName) {
      if (unitName != null && unitName.length() > 0) {
         this.createArg().setLine("-pu " + unitName);
         this.log("SchemaTool pu: " + unitName, 3);
      }

   }

   public void setApi(String api) {
      if (api != null && api.length() > 0) {
         this.createArg().setValue("-api");
         this.createArg().setValue(api);
         this.log("SchemaTool api: " + api, 3);
      }

   }

   public void setMode(String mode) {
      if (mode != null) {
         if (mode.equalsIgnoreCase("createSchema")) {
            this.mode = SchemaTool.Mode.CREATE_SCHEMA;
         } else if (mode.equalsIgnoreCase("deleteSchema")) {
            this.mode = SchemaTool.Mode.DELETE_SCHEMA;
         } else if (mode.equalsIgnoreCase("create")) {
            this.mode = SchemaTool.Mode.CREATE;
         } else if (mode.equalsIgnoreCase("delete")) {
            this.mode = SchemaTool.Mode.DELETE;
         } else if (mode.equalsIgnoreCase("deletecreate")) {
            this.mode = SchemaTool.Mode.DELETE_CREATE;
         } else if (mode.equalsIgnoreCase("validate")) {
            this.mode = SchemaTool.Mode.VALIDATE;
         } else if (mode.equalsIgnoreCase("dbinfo")) {
            this.mode = SchemaTool.Mode.DATABASE_INFO;
         } else if (mode.equalsIgnoreCase("schemainfo")) {
            this.mode = SchemaTool.Mode.SCHEMA_INFO;
         } else {
            System.err.println(Localiser.msg("014036"));
         }

      }
   }
}

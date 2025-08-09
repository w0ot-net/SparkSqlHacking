package org.apache.ivy.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

public class AddPathTask extends Task {
   private String toPath;
   private boolean first = false;
   private Path toAdd;

   public String getTopath() {
      return this.toPath;
   }

   public void setTopath(String toPath) {
      this.toPath = toPath;
   }

   public void setProject(Project project) {
      super.setProject(project);
      this.toAdd = new Path(project);
   }

   public void execute() throws BuildException {
      Object element = this.getProject().getReference(this.toPath);
      if (element == null) {
         throw new BuildException("destination path not found: " + this.toPath);
      } else if (!(element instanceof Path)) {
         throw new BuildException("destination path is not a path: " + element.getClass());
      } else {
         Path dest = (Path)element;
         if (this.first) {
            this.toAdd.append(dest);
            this.getProject().addReference(this.toPath, this.toAdd);
         } else {
            dest.append(this.toAdd);
         }

      }
   }

   public void add(Path path) throws BuildException {
      this.toAdd.add(path);
   }

   public void addDirset(DirSet dset) throws BuildException {
      this.toAdd.addDirset(dset);
   }

   public void addFilelist(FileList fl) throws BuildException {
      this.toAdd.addFilelist(fl);
   }

   public void addFileset(FileSet fs) throws BuildException {
      this.toAdd.addFileset(fs);
   }

   public Path createPath() throws BuildException {
      return this.toAdd.createPath();
   }

   public Path.PathElement createPathElement() throws BuildException {
      return this.toAdd.createPathElement();
   }

   public boolean isFirst() {
      return this.first;
   }

   public void setFirst(boolean first) {
      this.first = first;
   }
}

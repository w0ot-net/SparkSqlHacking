package org.apache.ivy.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.plugins.trigger.AbstractTrigger;
import org.apache.ivy.plugins.trigger.Trigger;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.taskdefs.Property;

public class AntBuildTrigger extends AbstractTrigger implements Trigger {
   private boolean onlyOnce = true;
   private String target = null;
   private Collection builds = new ArrayList();
   private String buildFilePattern;
   private String prefix;

   public void progress(IvyEvent event) {
      File f = this.getBuildFile(event);
      if (f.exists()) {
         if (this.onlyOnce && this.isBuilt(f)) {
            Message.verbose("target build file already built, skipping: " + f);
         } else {
            Ant ant = new Ant();
            Project project = (Project)IvyContext.peekInContextStack("ant-project");
            if (project == null) {
               project = new Project();
               project.init();
            }

            ant.setProject(project);
            ant.setTaskName("ant");
            ant.setAntfile(f.getAbsolutePath());
            ant.setInheritAll(false);
            String target = this.getTarget();
            if (target != null) {
               ant.setTarget(target);
            }

            for(Map.Entry entry : event.getAttributes().entrySet()) {
               if (entry.getValue() != null) {
                  Property p = ant.createProperty();
                  p.setName(this.prefix == null ? (String)entry.getKey() : this.prefix + (String)entry.getKey());
                  p.setValue((String)entry.getValue());
               }
            }

            Message.verbose("triggering build: " + f + " target=" + target + " for " + event);

            try {
               ant.execute();
            } catch (BuildException e) {
               Message.verbose("Exception occurred while executing target " + target);
               throw e;
            }

            this.markBuilt(f);
            Message.debug("triggered build finished: " + f + " target=" + target + " for " + event);
         }
      } else {
         Message.verbose("no build file found for dependency, skipping: " + f);
      }

   }

   private void markBuilt(File f) {
      this.builds.add(f.getAbsolutePath());
   }

   private boolean isBuilt(File f) {
      return this.builds.contains(f.getAbsolutePath());
   }

   private File getBuildFile(IvyEvent event) {
      return IvyContext.getContext().getSettings().resolveFile(IvyPatternHelper.substituteTokens(this.getBuildFilePattern(), event.getAttributes()));
   }

   public String getBuildFilePattern() {
      return this.buildFilePattern;
   }

   public void setAntfile(String pattern) {
      this.buildFilePattern = pattern;
   }

   public String getTarget() {
      return this.target;
   }

   public void setTarget(String target) {
      this.target = target;
   }

   public boolean isOnlyonce() {
      return this.onlyOnce;
   }

   public void setOnlyonce(boolean onlyonce) {
      this.onlyOnce = onlyonce;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
      if (!prefix.endsWith(".")) {
         this.prefix = this.prefix + ".";
      }

   }
}

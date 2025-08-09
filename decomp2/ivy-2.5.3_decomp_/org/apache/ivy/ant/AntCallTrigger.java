package org.apache.ivy.ant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.plugins.trigger.AbstractTrigger;
import org.apache.ivy.plugins.trigger.Trigger;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.CallTarget;
import org.apache.tools.ant.taskdefs.Property;

public class AntCallTrigger extends AbstractTrigger implements Trigger {
   private boolean onlyonce = true;
   private String target = null;
   private Collection calls = new ArrayList();
   private String prefix;

   public void progress(IvyEvent event) {
      Project project = (Project)IvyContext.peekInContextStack("ant-project");
      if (project == null) {
         Message.info("ant call trigger can only be used from an ant build. Ignoring.");
      } else {
         if (this.onlyonce && this.isTriggered(event)) {
            Message.verbose("call already triggered for this event, skipping: " + event);
         } else {
            CallTarget call = new CallTarget();
            call.setProject(project);
            call.setTaskName("antcall");
            Map<String, String> attributes = event.getAttributes();
            String target = IvyPatternHelper.substituteTokens(this.getTarget(), attributes);
            call.setTarget(target);

            for(Map.Entry entry : attributes.entrySet()) {
               Property p = call.createParam();
               p.setName(this.prefix == null ? (String)entry.getKey() : this.prefix + (String)entry.getKey());
               p.setValue(entry.getValue() == null ? "" : (String)entry.getValue());
            }

            Message.verbose("triggering ant call: target=" + target + " for " + event);
            call.execute();
            this.markTriggered(event);
            Message.debug("triggered ant call finished: target=" + target + " for " + event);
         }

      }
   }

   private void markTriggered(IvyEvent event) {
      this.calls.add(event);
   }

   private boolean isTriggered(IvyEvent event) {
      return this.calls.contains(event);
   }

   public String getTarget() {
      return this.target;
   }

   public void setTarget(String target) {
      this.target = target;
   }

   public boolean isOnlyonce() {
      return this.onlyonce;
   }

   public void setOnlyonce(boolean onlyonce) {
      this.onlyonce = onlyonce;
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

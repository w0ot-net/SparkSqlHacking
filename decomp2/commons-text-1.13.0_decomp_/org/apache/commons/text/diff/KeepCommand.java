package org.apache.commons.text.diff;

public class KeepCommand extends EditCommand {
   public KeepCommand(Object object) {
      super(object);
   }

   public void accept(CommandVisitor visitor) {
      visitor.visitKeepCommand(this.getObject());
   }
}

package org.apache.commons.text.diff;

public class DeleteCommand extends EditCommand {
   public DeleteCommand(Object object) {
      super(object);
   }

   public void accept(CommandVisitor visitor) {
      visitor.visitDeleteCommand(this.getObject());
   }
}

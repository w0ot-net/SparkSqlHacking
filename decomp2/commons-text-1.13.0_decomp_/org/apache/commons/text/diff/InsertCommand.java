package org.apache.commons.text.diff;

public class InsertCommand extends EditCommand {
   public InsertCommand(Object object) {
      super(object);
   }

   public void accept(CommandVisitor visitor) {
      visitor.visitInsertCommand(this.getObject());
   }
}

package org.apache.derby.iapi.store.raw;

public interface Compensation extends Loggable {
   void setUndoOp(Undoable var1);
}

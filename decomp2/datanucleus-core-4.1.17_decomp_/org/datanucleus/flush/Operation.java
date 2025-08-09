package org.datanucleus.flush;

import org.datanucleus.state.ObjectProvider;

public interface Operation {
   ObjectProvider getObjectProvider();

   void perform();
}

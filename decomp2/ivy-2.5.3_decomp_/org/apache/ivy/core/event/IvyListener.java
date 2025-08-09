package org.apache.ivy.core.event;

import java.util.EventListener;

public interface IvyListener extends EventListener {
   void progress(IvyEvent var1);
}

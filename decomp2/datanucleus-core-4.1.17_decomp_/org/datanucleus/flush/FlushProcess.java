package org.datanucleus.flush;

import java.util.List;
import org.datanucleus.ExecutionContext;

public interface FlushProcess {
   List execute(ExecutionContext var1, List var2, List var3, OperationQueue var4);
}

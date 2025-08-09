package org.apache.curator.shaded.com.google.common.graph;

import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@ElementTypesAreNonnullByDefault
@Beta
public interface PredecessorsFunction {
   Iterable predecessors(Object node);
}

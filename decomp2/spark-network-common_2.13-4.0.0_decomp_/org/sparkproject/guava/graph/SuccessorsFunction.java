package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.DoNotMock;
import org.sparkproject.guava.annotations.Beta;

@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@ElementTypesAreNonnullByDefault
@Beta
public interface SuccessorsFunction {
   Iterable successors(Object node);
}

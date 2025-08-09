package org.apache.spark.api.java;

import java.util.List;
import java.util.concurrent.Future;

public interface JavaFutureAction extends Future {
   List jobIds();
}

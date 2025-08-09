package org.apache.spark.deploy.worker;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}1\u0001BA\u0002\u0011\u0002G\u0005Q!\u0004\u0005\u0006)\u00011\tA\u0006\u0002\b'2,W\r]3s\u0015\t!Q!\u0001\u0004x_J\\WM\u001d\u0006\u0003\r\u001d\ta\u0001Z3qY>L(B\u0001\u0005\n\u0003\u0015\u0019\b/\u0019:l\u0015\tQ1\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0005\u0019qN]4\u0014\u0005\u0001q\u0001CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g-A\u0003tY\u0016,\u0007o\u0001\u0001\u0015\u0005]Q\u0002CA\b\u0019\u0013\tI\u0002C\u0001\u0003V]&$\b\"B\u000e\u0002\u0001\u0004a\u0012aB:fG>tGm\u001d\t\u0003\u001fuI!A\b\t\u0003\u0007%sG\u000f"
)
public interface Sleeper {
   void sleep(final int seconds);
}

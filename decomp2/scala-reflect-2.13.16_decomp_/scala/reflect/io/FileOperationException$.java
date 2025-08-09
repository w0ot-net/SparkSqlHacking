package scala.reflect.io;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class FileOperationException$ extends AbstractFunction1 implements Serializable {
   public static final FileOperationException$ MODULE$ = new FileOperationException$();

   public final String toString() {
      return "FileOperationException";
   }

   public FileOperationException apply(final String msg) {
      return new FileOperationException(msg);
   }

   public Option unapply(final FileOperationException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FileOperationException$.class);
   }

   private FileOperationException$() {
   }
}

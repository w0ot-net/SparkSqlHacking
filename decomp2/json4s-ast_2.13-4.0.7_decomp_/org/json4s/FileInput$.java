package org.json4s;

import java.io.File;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class FileInput$ extends AbstractFunction1 implements Serializable {
   public static final FileInput$ MODULE$ = new FileInput$();

   public final String toString() {
      return "FileInput";
   }

   public FileInput apply(final File file) {
      return new FileInput(file);
   }

   public Option unapply(final FileInput x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.file()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FileInput$.class);
   }

   private FileInput$() {
   }
}

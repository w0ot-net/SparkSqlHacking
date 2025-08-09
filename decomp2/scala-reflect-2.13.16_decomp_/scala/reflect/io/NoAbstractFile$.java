package scala.reflect.io;

import java.io.InputStream;
import java.io.OutputStream;
import scala.NotImplementedError;
import scala.collection.Iterator;
import scala.package.;

public final class NoAbstractFile$ extends AbstractFile {
   public static final NoAbstractFile$ MODULE$ = new NoAbstractFile$();

   public AbstractFile absolute() {
      return this;
   }

   public AbstractFile container() {
      return this;
   }

   public void create() {
      throw new NotImplementedError();
   }

   public void delete() {
      throw new NotImplementedError();
   }

   public java.io.File file() {
      return null;
   }

   public InputStream input() {
      return null;
   }

   public boolean isDirectory() {
      return false;
   }

   public boolean isVirtual() {
      return true;
   }

   public Iterator iterator() {
      if (.MODULE$.Iterator() == null) {
         throw null;
      } else {
         return scala.collection.Iterator..scala$collection$Iterator$$_empty;
      }
   }

   public long lastModified() {
      return 0L;
   }

   public AbstractFile lookupName(final String name, final boolean directory) {
      return null;
   }

   public AbstractFile lookupNameUnchecked(final String name, final boolean directory) {
      return null;
   }

   public String name() {
      return "";
   }

   public OutputStream output() {
      return null;
   }

   public String path() {
      return "";
   }

   public byte[] toByteArray() {
      return (byte[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Byte());
   }

   public String toString() {
      return "<no file>";
   }

   private NoAbstractFile$() {
   }
}

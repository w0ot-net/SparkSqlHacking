package breeze.linalg;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class MatrixSingularException$ implements Serializable {
   public static final MatrixSingularException$ MODULE$ = new MatrixSingularException$();

   public String $lessinit$greater$default$1() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MatrixSingularException$.class);
   }

   private MatrixSingularException$() {
   }
}

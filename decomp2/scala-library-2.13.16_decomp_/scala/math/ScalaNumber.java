package scala.math;

public abstract class ScalaNumber extends Number {
   protected abstract boolean isWhole();

   public abstract Object underlying();
}

package scala.annotation;

import scala.Predef;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.runtime.ScalaRunTime$;

public final class elidable$ {
   public static final elidable$ MODULE$ = new elidable$();
   private static final Map byName;

   static {
      // $FF: Couldn't be decompiled
   }

   public final int ALL() {
      return Integer.MIN_VALUE;
   }

   public final int FINEST() {
      return 300;
   }

   public final int FINER() {
      return 400;
   }

   public final int FINE() {
      return 500;
   }

   public final int CONFIG() {
      return 700;
   }

   public final int INFO() {
      return 800;
   }

   public final int WARNING() {
      return 900;
   }

   public final int SEVERE() {
      return 1000;
   }

   public final int OFF() {
      return Integer.MAX_VALUE;
   }

   public final int MAXIMUM() {
      return Integer.MAX_VALUE;
   }

   public final int MINIMUM() {
      return Integer.MIN_VALUE;
   }

   public final int ASSERTION() {
      return 2000;
   }

   public Map byName() {
      return byName;
   }

   private elidable$() {
   }
}

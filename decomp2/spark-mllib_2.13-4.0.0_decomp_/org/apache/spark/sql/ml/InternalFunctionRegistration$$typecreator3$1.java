package org.apache.spark.sql.ml;

import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

public final class InternalFunctionRegistration$$typecreator3$1 extends TypeCreator {
   public Types.TypeApi apply(final Mirror $m$untyped) {
      Universe $u = $m$untyped.universe();
      return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
   }
}

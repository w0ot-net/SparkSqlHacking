package org.apache.spark.sql.ml;

import scala.collection.immutable.;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

public final class InternalFunctionRegistration$$typecreator2$1 extends TypeCreator {
   public Types.TypeApi apply(final Mirror $m$untyped) {
      Universe $u = $m$untyped.universe();
      return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new .colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
   }
}

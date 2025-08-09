package org.apache.spark.sql.ml;

import scala.collection.immutable.;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

public final class InternalFunctionRegistration$$typecreator4$1 extends TypeCreator {
   public Types.TypeApi apply(final Mirror $m$untyped) {
      Universe $u = $m$untyped.universe();
      return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new .colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
   }
}

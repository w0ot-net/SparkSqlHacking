package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.Vertx;

/** @deprecated */
@DataObject
@Deprecated
public interface AuthOptions {
   AuthOptions clone();

   AuthProvider createProvider(Vertx var1);
}

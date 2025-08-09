package io.vertx.ext.web.common.template;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.Map;

@VertxGen
public interface TemplateEngine {
   default void render(JsonObject context, String templateFileName, Handler handler) {
      this.render(context.getMap(), templateFileName, handler);
   }

   default Future render(JsonObject context, String templateFileName) {
      Promise<Buffer> promise = Promise.promise();
      this.render((JsonObject)context, templateFileName, promise);
      return promise.future();
   }

   @GenIgnore({"permitted-type"})
   void render(Map var1, String var2, Handler var3);

   @GenIgnore({"permitted-type"})
   default Future render(Map context, String templateFileName) {
      Promise<Buffer> promise = Promise.promise();
      this.render((Map)context, templateFileName, promise);
      return promise.future();
   }

   @GenIgnore({"permitted-type"})
   default Object unwrap() throws ClassCastException {
      return null;
   }

   void clearCache();
}

package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.multipart.MultipartForm;
import io.vertx.uritemplate.Variables;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@VertxGen
public interface HttpRequest {
   @Fluent
   HttpRequest method(HttpMethod var1);

   HttpMethod method();

   @Fluent
   HttpRequest port(int var1);

   int port();

   HttpRequest as(BodyCodec var1);

   BodyCodec bodyCodec();

   @Fluent
   HttpRequest host(String var1);

   String host();

   @Fluent
   HttpRequest virtualHost(String var1);

   String virtualHost();

   @Fluent
   HttpRequest uri(String var1);

   String uri();

   @Fluent
   HttpRequest putHeaders(MultiMap var1);

   @Fluent
   HttpRequest putHeader(String var1, String var2);

   @Fluent
   @GenIgnore({"permitted-type"})
   HttpRequest putHeader(String var1, Iterable var2);

   @CacheReturn
   MultiMap headers();

   @Fluent
   @GenIgnore({"permitted-type"})
   HttpRequest authentication(Credentials var1);

   @Fluent
   default HttpRequest basicAuthentication(String id, String password) {
      return this.authentication((new UsernamePasswordCredentials(id, password)).applyHttpChallenge((String)null));
   }

   @Fluent
   default HttpRequest basicAuthentication(Buffer id, Buffer password) {
      return this.basicAuthentication(id.toString(), password.toString());
   }

   @Fluent
   default HttpRequest bearerTokenAuthentication(String bearerToken) {
      return this.authentication((new TokenCredentials(bearerToken)).applyHttpChallenge((String)null));
   }

   @Fluent
   HttpRequest ssl(Boolean var1);

   Boolean ssl();

   @Fluent
   HttpRequest timeout(long var1);

   long timeout();

   @Fluent
   HttpRequest idleTimeout(long var1);

   long idleTimeout();

   @Fluent
   HttpRequest connectTimeout(long var1);

   long connectTimeout();

   @Fluent
   HttpRequest addQueryParam(String var1, String var2);

   @Fluent
   HttpRequest setQueryParam(String var1, String var2);

   @Fluent
   HttpRequest setTemplateParam(String var1, String var2);

   @Fluent
   HttpRequest setTemplateParam(String var1, List var2);

   @Fluent
   HttpRequest setTemplateParam(String var1, Map var2);

   @Fluent
   HttpRequest followRedirects(boolean var1);

   boolean followRedirects();

   @Fluent
   HttpRequest proxy(ProxyOptions var1);

   ProxyOptions proxy();

   /** @deprecated */
   @Deprecated
   @Fluent
   default HttpRequest expect(Function predicate) {
      predicate.getClass();
      return this.expect(predicate::apply);
   }

   /** @deprecated */
   @Deprecated
   @Fluent
   HttpRequest expect(ResponsePredicate var1);

   /** @deprecated */
   @Deprecated
   List expectations();

   MultiMap queryParams();

   Variables templateParams();

   HttpRequest copy();

   @Fluent
   HttpRequest multipartMixed(boolean var1);

   boolean multipartMixed();

   @Fluent
   HttpRequest traceOperation(String var1);

   String traceOperation();

   void sendStream(ReadStream var1, Handler var2);

   default Future sendStream(ReadStream body) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendStream(body, promise);
      return promise.future();
   }

   void sendBuffer(Buffer var1, Handler var2);

   default Future sendBuffer(Buffer body) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendBuffer(body, promise);
      return promise.future();
   }

   void sendJsonObject(JsonObject var1, Handler var2);

   default Future sendJsonObject(JsonObject body) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendJsonObject(body, promise);
      return promise.future();
   }

   void sendJson(@Nullable Object var1, Handler var2);

   default Future sendJson(@Nullable Object body) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendJson(body, promise);
      return promise.future();
   }

   void sendForm(MultiMap var1, Handler var2);

   default Future sendForm(MultiMap body) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendForm(body, (Handler)promise);
      return promise.future();
   }

   void sendForm(MultiMap var1, String var2, Handler var3);

   default Future sendForm(MultiMap body, String charset) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendForm(body, charset, promise);
      return promise.future();
   }

   void sendMultipartForm(MultipartForm var1, Handler var2);

   default Future sendMultipartForm(MultipartForm body) {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.sendMultipartForm(body, promise);
      return promise.future();
   }

   void send(Handler var1);

   default Future send() {
      Promise<HttpResponse<T>> promise = Promise.promise();
      this.send(promise);
      return promise.future();
   }
}

package io.vertx.ext.web.client.predicate;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.impl.predicate.ResponsePredicateImpl;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/** @deprecated */
@Deprecated
@VertxGen
public interface ResponsePredicate extends Function {
   ResponsePredicate SC_INFORMATIONAL_RESPONSE = status(100, 200);
   ResponsePredicate SC_CONTINUE = status(100);
   ResponsePredicate SC_SWITCHING_PROTOCOLS = status(101);
   ResponsePredicate SC_PROCESSING = status(102);
   ResponsePredicate SC_EARLY_HINTS = status(103);
   ResponsePredicate SC_SUCCESS = status(200, 300);
   ResponsePredicate SC_OK = status(200);
   ResponsePredicate SC_CREATED = status(201);
   ResponsePredicate SC_ACCEPTED = status(202);
   ResponsePredicate SC_NON_AUTHORITATIVE_INFORMATION = status(203);
   ResponsePredicate SC_NO_CONTENT = status(204);
   ResponsePredicate SC_RESET_CONTENT = status(205);
   ResponsePredicate SC_PARTIAL_CONTENT = status(206);
   ResponsePredicate SC_MULTI_STATUS = status(207);
   ResponsePredicate SC_REDIRECTION = status(300, 400);
   ResponsePredicate SC_MULTIPLE_CHOICES = status(300);
   ResponsePredicate SC_MOVED_PERMANENTLY = status(301);
   ResponsePredicate SC_FOUND = status(302);
   ResponsePredicate SC_SEE_OTHER = status(303);
   ResponsePredicate SC_NOT_MODIFIED = status(304);
   ResponsePredicate SC_USE_PROXY = status(305);
   ResponsePredicate SC_TEMPORARY_REDIRECT = status(307);
   ResponsePredicate SC_PERMANENT_REDIRECT = status(308);
   ResponsePredicate SC_CLIENT_ERRORS = status(400, 500);
   ResponsePredicate SC_BAD_REQUEST = status(400);
   ResponsePredicate SC_UNAUTHORIZED = status(401);
   ResponsePredicate SC_PAYMENT_REQUIRED = status(402);
   ResponsePredicate SC_FORBIDDEN = status(403);
   ResponsePredicate SC_NOT_FOUND = status(404);
   ResponsePredicate SC_METHOD_NOT_ALLOWED = status(405);
   ResponsePredicate SC_NOT_ACCEPTABLE = status(406);
   ResponsePredicate SC_PROXY_AUTHENTICATION_REQUIRED = status(407);
   ResponsePredicate SC_REQUEST_TIMEOUT = status(408);
   ResponsePredicate SC_CONFLICT = status(409);
   ResponsePredicate SC_GONE = status(410);
   ResponsePredicate SC_LENGTH_REQUIRED = status(411);
   ResponsePredicate SC_PRECONDITION_FAILED = status(412);
   ResponsePredicate SC_REQUEST_ENTITY_TOO_LARGE = status(413);
   ResponsePredicate SC_REQUEST_URI_TOO_LONG = status(414);
   ResponsePredicate SC_UNSUPPORTED_MEDIA_TYPE = status(415);
   ResponsePredicate SC_REQUESTED_RANGE_NOT_SATISFIABLE = status(416);
   ResponsePredicate SC_EXPECTATION_FAILED = status(417);
   ResponsePredicate SC_MISDIRECTED_REQUEST = status(421);
   ResponsePredicate SC_UNPROCESSABLE_ENTITY = status(422);
   ResponsePredicate SC_LOCKED = status(423);
   ResponsePredicate SC_FAILED_DEPENDENCY = status(424);
   ResponsePredicate SC_UNORDERED_COLLECTION = status(425);
   ResponsePredicate SC_UPGRADE_REQUIRED = status(426);
   ResponsePredicate SC_PRECONDITION_REQUIRED = status(428);
   ResponsePredicate SC_TOO_MANY_REQUESTS = status(429);
   ResponsePredicate SC_REQUEST_HEADER_FIELDS_TOO_LARGE = status(431);
   ResponsePredicate SC_SERVER_ERRORS = status(500, 600);
   ResponsePredicate SC_INTERNAL_SERVER_ERROR = status(500);
   ResponsePredicate SC_NOT_IMPLEMENTED = status(501);
   ResponsePredicate SC_BAD_GATEWAY = status(502);
   ResponsePredicate SC_SERVICE_UNAVAILABLE = status(503);
   ResponsePredicate SC_GATEWAY_TIMEOUT = status(504);
   ResponsePredicate SC_HTTP_VERSION_NOT_SUPPORTED = status(505);
   ResponsePredicate SC_VARIANT_ALSO_NEGOTIATES = status(506);
   ResponsePredicate SC_INSUFFICIENT_STORAGE = status(507);
   ResponsePredicate SC_NOT_EXTENDED = status(510);
   ResponsePredicate SC_NETWORK_AUTHENTICATION_REQUIRED = status(511);
   ResponsePredicate JSON = contentType("application/json");

   static ResponsePredicate status(int statusCode) {
      return status(statusCode, statusCode + 1);
   }

   static ResponsePredicate status(int min, int max) {
      return (response) -> {
         int sc = response.statusCode();
         if (sc >= min && sc < max) {
            return ResponsePredicateResult.success();
         } else {
            return max - min == 1 ? ResponsePredicateResult.failure("Response status code " + sc + " is not equal to " + min) : ResponsePredicateResult.failure("Response status code " + sc + " is not between " + min + " and " + max);
         }
      };
   }

   static ResponsePredicate contentType(String mimeType) {
      return contentType(Collections.singletonList(mimeType));
   }

   static ResponsePredicate contentType(List mimeTypes) {
      return (response) -> {
         String contentType = response.headers().get(HttpHeaders.CONTENT_TYPE);
         if (contentType == null) {
            return ResponsePredicateResult.failure("Missing response content type");
         } else {
            int paramIdx = contentType.indexOf(59);
            String mediaType = paramIdx != -1 ? contentType.substring(0, paramIdx) : contentType;

            for(String mimeType : mimeTypes) {
               if (mediaType.equalsIgnoreCase(mimeType)) {
                  return ResponsePredicateResult.success();
               }
            }

            StringBuilder sb = (new StringBuilder("Expect content type ")).append(contentType).append(" to be one of ");
            boolean first = true;

            for(String mimeType : mimeTypes) {
               if (!first) {
                  sb.append(", ");
               }

               first = false;
               sb.append(mimeType);
            }

            return ResponsePredicateResult.failure(sb.toString());
         }
      };
   }

   static ResponsePredicate create(Function test) {
      return test::apply;
   }

   static ResponsePredicate create(Function test, ErrorConverter errorConverter) {
      return new ResponsePredicateImpl(test, errorConverter);
   }

   default ErrorConverter errorConverter() {
      return ErrorConverter.DEFAULT_CONVERTER;
   }
}

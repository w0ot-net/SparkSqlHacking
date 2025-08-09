package io.vertx.core.http;

import io.vertx.core.Expectation;
import io.vertx.core.VertxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface HttpResponseExpectation extends Expectation {
   HttpResponseExpectation SC_INFORMATIONAL_RESPONSE = status(100, 200);
   HttpResponseExpectation SC_CONTINUE = status(100);
   HttpResponseExpectation SC_SWITCHING_PROTOCOLS = status(101);
   HttpResponseExpectation SC_PROCESSING = status(102);
   HttpResponseExpectation SC_EARLY_HINTS = status(103);
   HttpResponseExpectation SC_SUCCESS = status(200, 300);
   HttpResponseExpectation SC_OK = status(200);
   HttpResponseExpectation SC_CREATED = status(201);
   HttpResponseExpectation SC_ACCEPTED = status(202);
   HttpResponseExpectation SC_NON_AUTHORITATIVE_INFORMATION = status(203);
   HttpResponseExpectation SC_NO_CONTENT = status(204);
   HttpResponseExpectation SC_RESET_CONTENT = status(205);
   HttpResponseExpectation SC_PARTIAL_CONTENT = status(206);
   HttpResponseExpectation SC_MULTI_STATUS = status(207);
   HttpResponseExpectation SC_REDIRECTION = status(300, 400);
   HttpResponseExpectation SC_MULTIPLE_CHOICES = status(300);
   HttpResponseExpectation SC_MOVED_PERMANENTLY = status(301);
   HttpResponseExpectation SC_FOUND = status(302);
   HttpResponseExpectation SC_SEE_OTHER = status(303);
   HttpResponseExpectation SC_NOT_MODIFIED = status(304);
   HttpResponseExpectation SC_USE_PROXY = status(305);
   HttpResponseExpectation SC_TEMPORARY_REDIRECT = status(307);
   HttpResponseExpectation SC_PERMANENT_REDIRECT = status(308);
   HttpResponseExpectation SC_CLIENT_ERRORS = status(400, 500);
   HttpResponseExpectation SC_BAD_REQUEST = status(400);
   HttpResponseExpectation SC_UNAUTHORIZED = status(401);
   HttpResponseExpectation SC_PAYMENT_REQUIRED = status(402);
   HttpResponseExpectation SC_FORBIDDEN = status(403);
   HttpResponseExpectation SC_NOT_FOUND = status(404);
   HttpResponseExpectation SC_METHOD_NOT_ALLOWED = status(405);
   HttpResponseExpectation SC_NOT_ACCEPTABLE = status(406);
   HttpResponseExpectation SC_PROXY_AUTHENTICATION_REQUIRED = status(407);
   HttpResponseExpectation SC_REQUEST_TIMEOUT = status(408);
   HttpResponseExpectation SC_CONFLICT = status(409);
   HttpResponseExpectation SC_GONE = status(410);
   HttpResponseExpectation SC_LENGTH_REQUIRED = status(411);
   HttpResponseExpectation SC_PRECONDITION_FAILED = status(412);
   HttpResponseExpectation SC_REQUEST_ENTITY_TOO_LARGE = status(413);
   HttpResponseExpectation SC_REQUEST_URI_TOO_LONG = status(414);
   HttpResponseExpectation SC_UNSUPPORTED_MEDIA_TYPE = status(415);
   HttpResponseExpectation SC_REQUESTED_RANGE_NOT_SATISFIABLE = status(416);
   HttpResponseExpectation SC_EXPECTATION_FAILED = status(417);
   HttpResponseExpectation SC_MISDIRECTED_REQUEST = status(421);
   HttpResponseExpectation SC_UNPROCESSABLE_ENTITY = status(422);
   HttpResponseExpectation SC_LOCKED = status(423);
   HttpResponseExpectation SC_FAILED_DEPENDENCY = status(424);
   HttpResponseExpectation SC_UNORDERED_COLLECTION = status(425);
   HttpResponseExpectation SC_UPGRADE_REQUIRED = status(426);
   HttpResponseExpectation SC_PRECONDITION_REQUIRED = status(428);
   HttpResponseExpectation SC_TOO_MANY_REQUESTS = status(429);
   HttpResponseExpectation SC_REQUEST_HEADER_FIELDS_TOO_LARGE = status(431);
   HttpResponseExpectation SC_SERVER_ERRORS = status(500, 600);
   HttpResponseExpectation SC_INTERNAL_SERVER_ERROR = status(500);
   HttpResponseExpectation SC_NOT_IMPLEMENTED = status(501);
   HttpResponseExpectation SC_BAD_GATEWAY = status(502);
   HttpResponseExpectation SC_SERVICE_UNAVAILABLE = status(503);
   HttpResponseExpectation SC_GATEWAY_TIMEOUT = status(504);
   HttpResponseExpectation SC_HTTP_VERSION_NOT_SUPPORTED = status(505);
   HttpResponseExpectation SC_VARIANT_ALSO_NEGOTIATES = status(506);
   HttpResponseExpectation SC_INSUFFICIENT_STORAGE = status(507);
   HttpResponseExpectation SC_NOT_EXTENDED = status(510);
   HttpResponseExpectation SC_NETWORK_AUTHENTICATION_REQUIRED = status(511);
   HttpResponseExpectation JSON = contentType("application/json");

   static HttpResponseExpectation status(int statusCode) {
      return status(statusCode, statusCode + 1);
   }

   static HttpResponseExpectation status(final int min, final int max) {
      return new HttpResponseExpectation() {
         public boolean test(HttpResponseHead value) {
            int sc = value.statusCode();
            return sc >= min && sc < max;
         }

         public Exception describe(HttpResponseHead value) {
            int sc = value.statusCode();
            return max - min == 1 ? new VertxException("Response status code " + sc + " is not equal to " + min, true) : new VertxException("Response status code " + sc + " is not between " + min + " and " + max, true);
         }
      };
   }

   static HttpResponseExpectation contentType(String mimeType) {
      return contentType(Collections.singletonList(mimeType));
   }

   static HttpResponseExpectation contentType(String... mimeTypes) {
      return contentType(Arrays.asList(mimeTypes));
   }

   static HttpResponseExpectation contentType(final List mimeTypes) {
      return new HttpResponseExpectation() {
         public boolean test(HttpResponseHead value) {
            String contentType = value.headers().get(HttpHeaders.CONTENT_TYPE);
            if (contentType == null) {
               return false;
            } else {
               int paramIdx = contentType.indexOf(59);
               String mediaType = paramIdx != -1 ? contentType.substring(0, paramIdx) : contentType;

               for(String mimeType : mimeTypes) {
                  if (mediaType.equalsIgnoreCase(mimeType)) {
                     return true;
                  }
               }

               return false;
            }
         }

         public Exception describe(HttpResponseHead value) {
            String contentType = value.headers().get(HttpHeaders.CONTENT_TYPE);
            if (contentType == null) {
               return new VertxException("Missing response content type", true);
            } else {
               StringBuilder sb = (new StringBuilder("Expect content type ")).append(contentType).append(" to be one of ");
               boolean first = true;

               for(String mimeType : mimeTypes) {
                  if (!first) {
                     sb.append(", ");
                  }

                  first = false;
                  sb.append(mimeType);
               }

               return new VertxException(sb.toString(), true);
            }
         }
      };
   }
}

package org.glassfish.jersey.http;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status.Family;

public final class ResponseStatus {
   public static class Info1xx {
      public static final Response.StatusType CONTINUE_100 = new ResponseStatusImpl(100, "Continue");
      public static final Response.StatusType SWITCHING_PROTOCOLS_101 = new ResponseStatusImpl(101, "Switching Protocols");
      public static final Response.StatusType PROCESSING_102 = new ResponseStatusImpl(102, "Processing");
      public static final Response.StatusType EARLY_HINTS_103 = new ResponseStatusImpl(103, "Early Hints");
   }

   public static class Success2xx {
      public static final Response.StatusType OK_200 = new ResponseStatusImpl(200, "OK");
      public static final Response.StatusType CREATED_201 = new ResponseStatusImpl(201, "Created");
      public static final Response.StatusType ACCEPTED_202 = new ResponseStatusImpl(202, "Accepted");
      public static final Response.StatusType NON_AUTHORITATIVE_INFORMATION_203 = new ResponseStatusImpl(203, "Non-Authoritative Information");
      public static final Response.StatusType NO_CONTENT_204 = new ResponseStatusImpl(204, "No Content");
      public static final Response.StatusType RESET_CONTENT_205 = new ResponseStatusImpl(205, "Reset Content");
      public static final Response.StatusType PARTIAL_CONTENT_206 = new ResponseStatusImpl(206, "Partial Content");
      public static final Response.StatusType MULTI_STATUS_207 = new ResponseStatusImpl(207, "Multi-Status");
      public static final Response.StatusType ALREADY_REPORTED_208 = new ResponseStatusImpl(208, "Already Reported");
      public static final Response.StatusType IM_USED_226 = new ResponseStatusImpl(226, "IM used");
   }

   public static class Redirect3xx {
      public static final Response.StatusType MULTIPLE_CHOICES_300 = new ResponseStatusImpl(300, "Multiple Choices");
      public static final Response.StatusType MOVED_PERMANENTLY_301 = new ResponseStatusImpl(301, "Moved Permanently");
      public static final Response.StatusType FOUND_302 = new ResponseStatusImpl(302, "Found");
      public static final Response.StatusType SEE_OTHER_303 = new ResponseStatusImpl(303, "See Other");
      public static final Response.StatusType NOT_MODIFIED_304 = new ResponseStatusImpl(304, "Not Modified");
      public static final Response.StatusType USE_PROXY_305 = new ResponseStatusImpl(305, "Use Proxy");
      public static final Response.StatusType TEMPORARY_REDIRECT_307 = new ResponseStatusImpl(307, "Temporary Redirect");
      public static final Response.StatusType PERMANENT_REDIRECT_308 = new ResponseStatusImpl(308, "Permanent Redirect");
   }

   public static class ClientError4xx {
      public static final Response.StatusType BAD_REQUEST_400 = new ResponseStatusImpl(400, "Bad Request");
      public static final Response.StatusType UNAUTHORIZED_401 = new ResponseStatusImpl(401, "Unauthorized");
      public static final Response.StatusType PAYMENT_REQUIRED_402 = new ResponseStatusImpl(402, "Payment Required");
      public static final Response.StatusType FORBIDDEN_403 = new ResponseStatusImpl(403, "Forbidden");
      public static final Response.StatusType NOT_FOUND_404 = new ResponseStatusImpl(404, "Not Found");
      public static final Response.StatusType METHOD_NOT_ALLOWED_405 = new ResponseStatusImpl(405, "Method Not Allowed");
      public static final Response.StatusType NOT_ACCEPTABLE_406 = new ResponseStatusImpl(406, "Not Acceptable");
      public static final Response.StatusType PROXY_AUTHENTICATION_REQUIRED_407 = new ResponseStatusImpl(407, "Proxy Authentication Required");
      public static final Response.StatusType REQUEST_TIMEOUT_408 = new ResponseStatusImpl(408, "Request Timeout");
      public static final Response.StatusType CONFLICT_409 = new ResponseStatusImpl(409, "Conflict");
      public static final Response.StatusType GONE_410 = new ResponseStatusImpl(410, "Gone");
      public static final Response.StatusType LENGTH_REQUIRED_411 = new ResponseStatusImpl(411, "Length Required");
      public static final Response.StatusType PRECONDITION_FAILED_412 = new ResponseStatusImpl(412, "Precondition Failed");
      public static final Response.StatusType REQUEST_ENTITY_TOO_LARGE_413 = new ResponseStatusImpl(413, "Request Entity Too Large");
      public static final Response.StatusType REQUEST_URI_TOO_LONG_414 = new ResponseStatusImpl(414, "Request-URI Too Long");
      public static final Response.StatusType UNSUPPORTED_MEDIA_TYPE_415 = new ResponseStatusImpl(415, "Unsupported Media Type");
      public static final Response.StatusType REQUESTED_RANGE_NOT_SATISFIABLE_416 = new ResponseStatusImpl(416, "Requested Range Not Satisfiable");
      public static final Response.StatusType EXPECTATION_FAILED_417 = new ResponseStatusImpl(417, "Expectation Failed");
      public static final Response.StatusType I_AM_A_TEAPOT_418 = new ResponseStatusImpl(418, "I'm a teapot");
      public static final Response.StatusType MISDIRECTED_REQUEST_421 = new ResponseStatusImpl(421, "Misdirected Request");
      public static final Response.StatusType UNPROCESSABLE_CONTENT_422 = new ResponseStatusImpl(422, "Unprocessable Content");
      public static final Response.StatusType LOCKED_423 = new ResponseStatusImpl(423, "Locked");
      public static final Response.StatusType FAILED_DEPENDENCY_424 = new ResponseStatusImpl(424, "Failed Dependency");
      public static final Response.StatusType TOO_EARLY_425 = new ResponseStatusImpl(425, "Too Early");
      public static final Response.StatusType UPGRADE_REQUIRED_426 = new ResponseStatusImpl(426, "Upgrade Required");
      public static final Response.StatusType PRECONDITION_REQUIRED_428 = new ResponseStatusImpl(428, "Precondition Required");
      public static final Response.StatusType TOO_MANY_REQUESTS_429 = new ResponseStatusImpl(429, "Too Many Requests");
      public static final Response.StatusType REQUEST_HEADER_FIELDS_TOO_LARGE_431 = new ResponseStatusImpl(431, "Request Header Fields Too Large");
      public static final Response.StatusType UNAVAILABLE_FOR_LEGAL_REASONS_451 = new ResponseStatusImpl(451, "Unavailable For Legal Reasons");
   }

   public static class ServerError5xx {
      public static final Response.StatusType INTERNAL_SERVER_ERROR_500 = new ResponseStatusImpl(500, "Internal Server Error");
      public static final Response.StatusType NOT_IMPLEMENTED_501 = new ResponseStatusImpl(501, "Not Implemented");
      public static final Response.StatusType BAD_GATEWAY_502 = new ResponseStatusImpl(502, "Bad Gateway");
      public static final Response.StatusType SERVICE_UNAVAILABLE_503 = new ResponseStatusImpl(503, "Service Unavailable");
      public static final Response.StatusType GATEWAY_TIMEOUT_504 = new ResponseStatusImpl(504, "Gateway Timeout");
      public static final Response.StatusType HTTP_VERSION_NOT_SUPPORTED_505 = new ResponseStatusImpl(505, "HTTP Version Not Supported");
      public static final Response.StatusType VARIANT_ALSO_NEGOTIATES_506 = new ResponseStatusImpl(506, "Variant Also Negotiates");
      public static final Response.StatusType INSUFFICIENT_STORAGE_507 = new ResponseStatusImpl(507, "Insufficient Storage");
      public static final Response.StatusType LOOP_DETECTED_508 = new ResponseStatusImpl(508, "Loop Detected");
      public static final Response.StatusType NOT_EXTENDED_510 = new ResponseStatusImpl(510, "Not Extended");
      public static final Response.StatusType NETWORK_AUTHENTICATION_REQUIRED_511 = new ResponseStatusImpl(511, "Network Authentication Required");
   }

   private static class ResponseStatusImpl implements Response.StatusType {
      private final int statusCode;
      private final String reasonPhrase;
      private final Response.Status.Family family;

      private ResponseStatusImpl(int statusCode, String reasonPhrase) {
         this.statusCode = statusCode;
         this.reasonPhrase = reasonPhrase;
         this.family = Family.familyOf(statusCode);
      }

      public int getStatusCode() {
         return this.statusCode;
      }

      public Response.Status.Family getFamily() {
         return this.family;
      }

      public String getReasonPhrase() {
         return this.reasonPhrase;
      }
   }
}

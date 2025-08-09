package io.fabric8.kubernetes.client.http;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusMessage {
   private static final String UNKNOWN_STATUS = "Unknown Status";
   private static final Map REASONS = new HashMap();

   private static void entry(int i, String string) {
      REASONS.put(i, string);
   }

   public static String getMessageForStatus(int status) {
      return (String)REASONS.getOrDefault(status, "Unknown Status");
   }

   private HttpStatusMessage() {
   }

   static {
      entry(100, "Continue");
      entry(101, "Switching Protocols");
      entry(102, "Processing");
      entry(103, "Early Hints");
      entry(200, "OK");
      entry(201, "Created");
      entry(202, "Accepted");
      entry(203, "Non-Authoritative Information");
      entry(204, "No Content");
      entry(205, "Reset Content");
      entry(206, "Partial Content");
      entry(207, "Multi Status");
      entry(208, "Already Reported");
      entry(226, "IM Used");
      entry(300, "Multiple Choice");
      entry(301, "Moved Permanently");
      entry(302, "Found");
      entry(303, "See Other");
      entry(304, "Not Modified");
      entry(305, "Use Proxy");
      entry(307, "Temporary Redirect");
      entry(308, "Permanent Redirect");
      entry(400, "Bad Request");
      entry(401, "Unauthorized");
      entry(402, "Payment Required");
      entry(403, "Forbidden");
      entry(404, "Not Found");
      entry(405, "Method Not Allowed");
      entry(406, "Not Acceptable");
      entry(407, "Proxy Authentication Required");
      entry(408, "Request Timeout");
      entry(409, "Conflict");
      entry(410, "Gone");
      entry(411, "Length Required");
      entry(412, "Precondition Failed");
      entry(413, "Payload Too Long");
      entry(414, "URI Too Long");
      entry(415, "Unsupported Media Type");
      entry(416, "Range Not Satisfiable");
      entry(417, "Expectation Failed");
      entry(418, "I'm a Teapot");
      entry(421, "Misdirected Request");
      entry(422, "Unprocessable Entity");
      entry(423, "Locked");
      entry(424, "Failed Dependency");
      entry(425, "Too Early");
      entry(426, "Upgrade Required");
      entry(428, "Precondition Required");
      entry(429, "Too Many Requests");
      entry(431, "Request Header Fields Too Large");
      entry(451, "Unavailable for Legal Reasons");
      entry(500, "Internal Server Error");
      entry(501, "Not Implemented");
      entry(502, "Bad Gateway");
      entry(503, "Service Unavailable");
      entry(504, "Gateway Timeout");
      entry(505, "HTTP Version Not Supported");
      entry(506, "Variant Also Negotiates");
      entry(507, "Insufficient Storage");
      entry(508, "Loop Detected");
      entry(510, "Not Extended");
      entry(511, "Network Authentication Required");
   }
}

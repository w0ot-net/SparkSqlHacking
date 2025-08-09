package org.sparkproject.jetty.http;

import java.nio.ByteBuffer;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.StringUtil;

public enum HttpMethod {
   ACL(HttpMethod.Type.IDEMPOTENT),
   BASELINE_CONTROL(HttpMethod.Type.IDEMPOTENT),
   BIND(HttpMethod.Type.IDEMPOTENT),
   CHECKIN(HttpMethod.Type.IDEMPOTENT),
   CHECKOUT(HttpMethod.Type.IDEMPOTENT),
   CONNECT(HttpMethod.Type.NORMAL),
   COPY(HttpMethod.Type.IDEMPOTENT),
   DELETE(HttpMethod.Type.IDEMPOTENT),
   GET(HttpMethod.Type.SAFE),
   HEAD(HttpMethod.Type.SAFE),
   LABEL(HttpMethod.Type.IDEMPOTENT),
   LINK(HttpMethod.Type.IDEMPOTENT),
   LOCK(HttpMethod.Type.NORMAL),
   MERGE(HttpMethod.Type.IDEMPOTENT),
   MKACTIVITY(HttpMethod.Type.IDEMPOTENT),
   MKCALENDAR(HttpMethod.Type.IDEMPOTENT),
   MKCOL(HttpMethod.Type.IDEMPOTENT),
   MKREDIRECTREF(HttpMethod.Type.IDEMPOTENT),
   MKWORKSPACE(HttpMethod.Type.IDEMPOTENT),
   MOVE(HttpMethod.Type.IDEMPOTENT),
   OPTIONS(HttpMethod.Type.SAFE),
   ORDERPATCH(HttpMethod.Type.IDEMPOTENT),
   PATCH(HttpMethod.Type.NORMAL),
   POST(HttpMethod.Type.NORMAL),
   PRI(HttpMethod.Type.SAFE),
   PROPFIND(HttpMethod.Type.SAFE),
   PROPPATCH(HttpMethod.Type.IDEMPOTENT),
   PUT(HttpMethod.Type.IDEMPOTENT),
   REBIND(HttpMethod.Type.IDEMPOTENT),
   REPORT(HttpMethod.Type.SAFE),
   SEARCH(HttpMethod.Type.SAFE),
   TRACE(HttpMethod.Type.SAFE),
   UNBIND(HttpMethod.Type.IDEMPOTENT),
   UNCHECKOUT(HttpMethod.Type.IDEMPOTENT),
   UNLINK(HttpMethod.Type.IDEMPOTENT),
   UNLOCK(HttpMethod.Type.IDEMPOTENT),
   UPDATE(HttpMethod.Type.IDEMPOTENT),
   UPDATEREDIRECTREF(HttpMethod.Type.IDEMPOTENT),
   VERSION_CONTROL(HttpMethod.Type.IDEMPOTENT),
   PROXY(HttpMethod.Type.NORMAL);

   private final String _method = this.name().replace('_', '-');
   private final byte[] _bytes;
   private final ByteBuffer _buffer;
   private final Type _type;
   public static final Index INSENSITIVE_CACHE = (new Index.Builder()).caseSensitive(false).withAll(values(), HttpMethod::asString).build();
   public static final Index CACHE = (new Index.Builder()).caseSensitive(true).withAll(values(), HttpMethod::asString).build();
   public static final Index LOOK_AHEAD = (new Index.Builder()).caseSensitive(true).withAll(values(), (httpMethod) -> httpMethod.asString() + " ").build();
   public static final int ACL_AS_INT = 1094929440;
   public static final int GET_AS_INT = 1195725856;
   public static final int PRI_AS_INT = 1347569952;
   public static final int PUT_AS_INT = 1347769376;
   public static final int POST_AS_INT = 1347375956;
   public static final int HEAD_AS_INT = 1212498244;

   private HttpMethod(Type type) {
      this._type = type;
      this._bytes = StringUtil.getBytes(this._method);
      this._buffer = ByteBuffer.wrap(this._bytes);
   }

   public byte[] getBytes() {
      return this._bytes;
   }

   public boolean is(String s) {
      return this.toString().equalsIgnoreCase(s);
   }

   public boolean isSafe() {
      return this._type == HttpMethod.Type.SAFE;
   }

   public boolean isIdempotent() {
      return this._type.ordinal() >= HttpMethod.Type.IDEMPOTENT.ordinal();
   }

   public ByteBuffer asBuffer() {
      return this._buffer.asReadOnlyBuffer();
   }

   public String asString() {
      return this._method;
   }

   public String toString() {
      return this._method;
   }

   /** @deprecated */
   @Deprecated
   public static HttpMethod lookAheadGet(byte[] bytes, int position, int limit) {
      return (HttpMethod)LOOK_AHEAD.getBest(bytes, position, limit - position);
   }

   public static HttpMethod lookAheadGet(ByteBuffer buffer) {
      int len = buffer.remaining();
      if (len > 3) {
         switch (buffer.getInt(buffer.position())) {
            case 1094929440:
               return ACL;
            case 1195725856:
               return GET;
            case 1212498244:
               if (len > 4 && buffer.get(buffer.position() + 4) == 32) {
                  return HEAD;
               }
               break;
            case 1347375956:
               if (len > 4 && buffer.get(buffer.position() + 4) == 32) {
                  return POST;
               }
               break;
            case 1347569952:
               return PRI;
            case 1347769376:
               return PUT;
         }
      }

      return (HttpMethod)LOOK_AHEAD.getBest((ByteBuffer)buffer, 0, len);
   }

   public static HttpMethod fromString(String method) {
      return (HttpMethod)CACHE.get(method);
   }

   // $FF: synthetic method
   private static HttpMethod[] $values() {
      return new HttpMethod[]{ACL, BASELINE_CONTROL, BIND, CHECKIN, CHECKOUT, CONNECT, COPY, DELETE, GET, HEAD, LABEL, LINK, LOCK, MERGE, MKACTIVITY, MKCALENDAR, MKCOL, MKREDIRECTREF, MKWORKSPACE, MOVE, OPTIONS, ORDERPATCH, PATCH, POST, PRI, PROPFIND, PROPPATCH, PUT, REBIND, REPORT, SEARCH, TRACE, UNBIND, UNCHECKOUT, UNLINK, UNLOCK, UPDATE, UPDATEREDIRECTREF, VERSION_CONTROL, PROXY};
   }

   private static enum Type {
      NORMAL,
      IDEMPOTENT,
      SAFE;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{NORMAL, IDEMPOTENT, SAFE};
      }
   }
}

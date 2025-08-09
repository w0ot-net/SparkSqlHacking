package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Map;

public final class RtspMethods {
   public static final HttpMethod OPTIONS;
   public static final HttpMethod DESCRIBE;
   public static final HttpMethod ANNOUNCE;
   public static final HttpMethod SETUP;
   public static final HttpMethod PLAY;
   public static final HttpMethod PAUSE;
   public static final HttpMethod TEARDOWN;
   public static final HttpMethod GET_PARAMETER;
   public static final HttpMethod SET_PARAMETER;
   public static final HttpMethod REDIRECT;
   public static final HttpMethod RECORD;
   private static final Map methodMap;

   public static HttpMethod valueOf(String name) {
      name = ObjectUtil.checkNonEmptyAfterTrim(name, "name").toUpperCase();
      HttpMethod result = (HttpMethod)methodMap.get(name);
      return result != null ? result : HttpMethod.valueOf(name);
   }

   private RtspMethods() {
   }

   static {
      OPTIONS = HttpMethod.OPTIONS;
      DESCRIBE = HttpMethod.valueOf("DESCRIBE");
      ANNOUNCE = HttpMethod.valueOf("ANNOUNCE");
      SETUP = HttpMethod.valueOf("SETUP");
      PLAY = HttpMethod.valueOf("PLAY");
      PAUSE = HttpMethod.valueOf("PAUSE");
      TEARDOWN = HttpMethod.valueOf("TEARDOWN");
      GET_PARAMETER = HttpMethod.valueOf("GET_PARAMETER");
      SET_PARAMETER = HttpMethod.valueOf("SET_PARAMETER");
      REDIRECT = HttpMethod.valueOf("REDIRECT");
      RECORD = HttpMethod.valueOf("RECORD");
      methodMap = new HashMap();
      methodMap.put(DESCRIBE.toString(), DESCRIBE);
      methodMap.put(ANNOUNCE.toString(), ANNOUNCE);
      methodMap.put(GET_PARAMETER.toString(), GET_PARAMETER);
      methodMap.put(OPTIONS.toString(), OPTIONS);
      methodMap.put(PAUSE.toString(), PAUSE);
      methodMap.put(PLAY.toString(), PLAY);
      methodMap.put(RECORD.toString(), RECORD);
      methodMap.put(REDIRECT.toString(), REDIRECT);
      methodMap.put(SETUP.toString(), SETUP);
      methodMap.put(SET_PARAMETER.toString(), SET_PARAMETER);
      methodMap.put(TEARDOWN.toString(), TEARDOWN);
   }
}

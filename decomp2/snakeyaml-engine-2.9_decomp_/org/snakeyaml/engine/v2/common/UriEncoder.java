package org.snakeyaml.engine.v2.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import org.snakeyaml.engine.external.com.google.gdata.util.common.base.Escaper;
import org.snakeyaml.engine.external.com.google.gdata.util.common.base.PercentEscaper;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;

public abstract class UriEncoder {
   private static final CharsetDecoder UTF8Decoder;
   private static final String SAFE_CHARS = "-_.!~*'()@:$&,;=[]/";
   private static final Escaper escaper;

   private UriEncoder() {
   }

   public static String encode(String uri) {
      return escaper.escape(uri);
   }

   public static String decode(ByteBuffer buff) throws CharacterCodingException {
      CharBuffer chars = UTF8Decoder.decode(buff);
      return chars.toString();
   }

   public static String decode(String buff) {
      try {
         return URLDecoder.decode(buff, "UTF-8");
      } catch (UnsupportedEncodingException e) {
         throw new YamlEngineException(e);
      }
   }

   static {
      UTF8Decoder = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT);
      escaper = new PercentEscaper("-_.!~*'()@:$&,;=[]/", false);
   }
}

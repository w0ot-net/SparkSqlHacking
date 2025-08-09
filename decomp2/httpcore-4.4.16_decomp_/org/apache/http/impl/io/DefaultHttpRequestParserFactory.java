package org.apache.http.impl.io;

import org.apache.http.HttpRequestFactory;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.MessageConstraints;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;

@Contract(
   threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL
)
public class DefaultHttpRequestParserFactory implements HttpMessageParserFactory {
   public static final DefaultHttpRequestParserFactory INSTANCE = new DefaultHttpRequestParserFactory();
   private final LineParser lineParser;
   private final HttpRequestFactory requestFactory;

   public DefaultHttpRequestParserFactory(LineParser lineParser, HttpRequestFactory requestFactory) {
      this.lineParser = (LineParser)(lineParser != null ? lineParser : BasicLineParser.INSTANCE);
      this.requestFactory = (HttpRequestFactory)(requestFactory != null ? requestFactory : DefaultHttpRequestFactory.INSTANCE);
   }

   public DefaultHttpRequestParserFactory() {
      this((LineParser)null, (HttpRequestFactory)null);
   }

   public HttpMessageParser create(SessionInputBuffer buffer, MessageConstraints constraints) {
      return new DefaultHttpRequestParser(buffer, this.lineParser, this.requestFactory, constraints);
   }
}

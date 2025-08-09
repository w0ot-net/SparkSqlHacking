package org.apache.http.impl.conn;

import org.apache.http.HttpResponseFactory;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.MessageConstraints;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;

@Contract(
   threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL
)
public class DefaultHttpResponseParserFactory implements HttpMessageParserFactory {
   public static final DefaultHttpResponseParserFactory INSTANCE = new DefaultHttpResponseParserFactory();
   private final LineParser lineParser;
   private final HttpResponseFactory responseFactory;

   public DefaultHttpResponseParserFactory(LineParser lineParser, HttpResponseFactory responseFactory) {
      this.lineParser = (LineParser)(lineParser != null ? lineParser : BasicLineParser.INSTANCE);
      this.responseFactory = (HttpResponseFactory)(responseFactory != null ? responseFactory : DefaultHttpResponseFactory.INSTANCE);
   }

   public DefaultHttpResponseParserFactory(HttpResponseFactory responseFactory) {
      this((LineParser)null, responseFactory);
   }

   public DefaultHttpResponseParserFactory() {
      this((LineParser)null, (HttpResponseFactory)null);
   }

   public HttpMessageParser create(SessionInputBuffer buffer, MessageConstraints constraints) {
      return new DefaultHttpResponseParser(buffer, this.lineParser, this.responseFactory, constraints);
   }
}

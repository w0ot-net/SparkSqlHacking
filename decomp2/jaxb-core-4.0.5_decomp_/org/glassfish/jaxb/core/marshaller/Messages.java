package org.glassfish.jaxb.core.marshaller;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Messages {
   public static final String NOT_MARSHALLABLE = "MarshallerImpl.NotMarshallable";
   public static final String UNSUPPORTED_RESULT = "MarshallerImpl.UnsupportedResult";
   public static final String UNSUPPORTED_ENCODING = "MarshallerImpl.UnsupportedEncoding";
   public static final String NULL_WRITER = "MarshallerImpl.NullWriterParam";
   public static final String ASSERT_FAILED = "SAXMarshaller.AssertFailed";
   /** @deprecated */
   @Deprecated(
      since = "2.0",
      forRemoval = true
   )
   public static final String ERR_MISSING_OBJECT = "SAXMarshaller.MissingObject";
   /** @deprecated */
   @Deprecated(
      since = "2.0",
      forRemoval = true
   )
   public static final String ERR_DANGLING_IDREF = "SAXMarshaller.DanglingIDREF";
   /** @deprecated */
   @Deprecated(
      since = "2.0",
      forRemoval = true
   )
   public static final String ERR_NOT_IDENTIFIABLE = "SAXMarshaller.NotIdentifiable";
   public static final String DOM_IMPL_DOESNT_SUPPORT_CREATELEMENTNS = "SAX2DOMEx.DomImplDoesntSupportCreateElementNs";

   private Messages() {
   }

   public static String format(String property) {
      return format(property, (Object[])null);
   }

   public static String format(String property, Object arg1) {
      return format(property, new Object[]{arg1});
   }

   public static String format(String property, Object arg1, Object arg2) {
      return format(property, new Object[]{arg1, arg2});
   }

   public static String format(String property, Object arg1, Object arg2, Object arg3) {
      return format(property, new Object[]{arg1, arg2, arg3});
   }

   static String format(String property, Object[] args) {
      String text = ResourceBundle.getBundle(Messages.class.getName()).getString(property);
      return MessageFormat.format(text, args);
   }
}

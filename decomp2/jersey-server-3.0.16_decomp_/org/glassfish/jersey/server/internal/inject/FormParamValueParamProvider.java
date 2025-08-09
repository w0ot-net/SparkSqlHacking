package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Encoded;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.internal.util.collection.NullableMultivaluedHashMap;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.model.Parameter;

@Singleton
final class FormParamValueParamProvider extends AbstractValueParamProvider {
   public FormParamValueParamProvider(Provider mpep) {
      super(mpep, Source.FORM);
   }

   public Function createValueProvider(Parameter parameter) {
      String parameterName = parameter.getSourceName();
      if (parameterName != null && !parameterName.isEmpty()) {
         MultivaluedParameterExtractor e = this.get(parameter);
         return e == null ? null : new FormParamValueProvider(e, !parameter.isEncoded());
      } else {
         return null;
      }
   }

   private static final class FormParamValueProvider implements Function {
      private static final Annotation encodedAnnotation = getEncodedAnnotation();
      private final MultivaluedParameterExtractor extractor;
      private final boolean decode;

      FormParamValueProvider(MultivaluedParameterExtractor extractor, boolean decode) {
         this.extractor = extractor;
         this.decode = decode;
      }

      private static Form getCachedForm(ContainerRequest request, boolean decode) {
         return (Form)request.getProperty(decode ? "jersey.config.server.representation.decoded.form" : "jersey.config.server.representation.form");
      }

      private static ContainerRequest ensureValidRequest(ContainerRequest request) throws IllegalStateException {
         if (request.getMethod().equals("GET")) {
            throw new IllegalStateException(LocalizationMessages.FORM_PARAM_METHOD_ERROR());
         } else if (!MediaTypes.typeEqual(MediaType.APPLICATION_FORM_URLENCODED_TYPE, request.getMediaType())) {
            throw new IllegalStateException(LocalizationMessages.FORM_PARAM_CONTENT_TYPE_ERROR());
         } else {
            return request;
         }
      }

      private static Annotation getEncodedAnnotation() {
         @Encoded
         final class EncodedAnnotationTemp {
         }

         return EncodedAnnotationTemp.class.getAnnotation(Encoded.class);
      }

      public Object apply(ContainerRequest request) {
         Form form = getCachedForm(request, this.decode);
         if (form == null) {
            Form otherForm = getCachedForm(request, !this.decode);
            if (otherForm != null) {
               form = this.switchUrlEncoding(request, otherForm);
               this.cacheForm(request, form);
            } else {
               form = this.getForm(request);
               this.cacheForm(request, form);
            }
         }

         try {
            return this.extractor.extract(form.asMap());
         } catch (ExtractorException e) {
            throw new ParamException.FormParamException(e.getCause(), this.extractor.getName(), this.extractor.getDefaultValueString());
         }
      }

      private Form switchUrlEncoding(ContainerRequest request, Form otherForm) {
         Set<Map.Entry<String, List<String>>> entries = otherForm.asMap().entrySet();
         MultivaluedMap<String, String> formMap = new NullableMultivaluedHashMap();

         for(Map.Entry entry : entries) {
            String charsetName = ReaderWriter.getCharset(MediaType.valueOf(request.getHeaderString("Content-Type"))).name();

            try {
               String key = this.decode ? URLDecoder.decode((String)entry.getKey(), charsetName) : URLEncoder.encode((String)entry.getKey(), charsetName);

               for(String value : (List)entry.getValue()) {
                  if (value != null) {
                     formMap.add(key, this.decode ? URLDecoder.decode(value, charsetName) : URLEncoder.encode(value, charsetName));
                  } else {
                     formMap.add(key, (Object)null);
                  }
               }
            } catch (UnsupportedEncodingException uee) {
               throw new ProcessingException(LocalizationMessages.ERROR_UNSUPPORTED_ENCODING(charsetName, this.extractor.getName()), uee);
            }
         }

         return new Form(formMap);
      }

      private void cacheForm(ContainerRequest request, Form form) {
         request.setProperty(this.decode ? "jersey.config.server.representation.decoded.form" : "jersey.config.server.representation.form", form);
      }

      private Form getForm(ContainerRequest request) {
         return this.getFormParameters(ensureValidRequest(request));
      }

      private Form getFormParameters(ContainerRequest request) {
         if (MediaTypes.typeEqual(MediaType.APPLICATION_FORM_URLENCODED_TYPE, request.getMediaType())) {
            request.bufferEntity();
            Form form;
            if (this.decode) {
               form = (Form)request.readEntity(Form.class);
            } else {
               Annotation[] annotations = new Annotation[1];
               annotations[0] = encodedAnnotation;
               form = (Form)request.readEntity(Form.class, annotations);
            }

            return form == null ? new Form() : form;
         } else {
            return new Form();
         }
      }
   }
}

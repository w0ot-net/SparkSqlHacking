package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.layout.template.json.util.Recycler;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterConsumer;
import org.apache.logging.log4j.message.ParameterVisitable;

public final class MessageParameterResolver implements EventResolver {
   private final Recycler parameterConsumerStateRecycler;
   private final boolean stringified;
   private final int index;
   private static final ParameterConsumer PARAMETER_CONSUMER = (parameter, index, state) -> {
      boolean arrayNeeded = state.resolver.index < 0;
      if (arrayNeeded && index > 0) {
         state.jsonWriter.writeSeparator();
      }

      if (arrayNeeded || state.resolver.index == index) {
         if (state.resolver.stringified) {
            String stringifiedParameter = String.valueOf(parameter);
            state.jsonWriter.writeString((CharSequence)stringifiedParameter);
         } else {
            state.jsonWriter.writeValue(parameter);
         }
      }

   };

   MessageParameterResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      this.parameterConsumerStateRecycler = context.getRecyclerFactory().create(() -> new ParameterConsumerState());
      this.stringified = config.getBoolean("stringified", false);
      Integer index = config.getInteger("index");
      if (index != null && index < 0) {
         throw new IllegalArgumentException("was expecting a positive index: " + config);
      } else {
         this.index = index == null ? -1 : index;
      }
   }

   static String getName() {
      return "messageParameter";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      Message message = logEvent.getMessage();
      if (message instanceof ParameterVisitable) {
         ParameterVisitable parameterVisitable = (ParameterVisitable)message;
         this.resolve(parameterVisitable, jsonWriter);
      } else {
         Object[] parameters = message.getParameters();
         if (parameters != null && parameters.length != 0 && this.index < parameters.length) {
            if (this.index < 0) {
               jsonWriter.writeArrayStart();

               for(int i = 0; i < parameters.length; ++i) {
                  if (i > 0) {
                     jsonWriter.writeSeparator();
                  }

                  Object parameter = parameters[i];
                  if (this.stringified) {
                     String stringifiedParameter = String.valueOf(parameter);
                     jsonWriter.writeString((CharSequence)stringifiedParameter);
                  } else {
                     jsonWriter.writeValue(parameter);
                  }
               }

               jsonWriter.writeArrayEnd();
            } else {
               Object parameter = parameters[this.index];
               if (this.stringified) {
                  String stringifiedParameter = String.valueOf(parameter);
                  jsonWriter.writeString((CharSequence)stringifiedParameter);
               } else {
                  jsonWriter.writeValue(parameter);
               }
            }

         } else {
            if (this.index < 0) {
               jsonWriter.writeArrayStart();
               jsonWriter.writeArrayEnd();
            } else {
               jsonWriter.writeNull();
            }

         }
      }
   }

   private void resolve(final ParameterVisitable parameterVisitable, final JsonWriter jsonWriter) {
      ParameterConsumerState parameterConsumerState = (ParameterConsumerState)this.parameterConsumerStateRecycler.acquire();

      try {
         boolean arrayNeeded = this.index < 0;
         if (arrayNeeded) {
            jsonWriter.writeArrayStart();
         }

         StringBuilder buf = jsonWriter.getStringBuilder();
         int startIndex = buf.length();
         parameterConsumerState.resolver = this;
         parameterConsumerState.jsonWriter = jsonWriter;
         parameterVisitable.forEachParameter(PARAMETER_CONSUMER, parameterConsumerState);
         if (arrayNeeded) {
            jsonWriter.writeArrayEnd();
         } else if (startIndex == buf.length()) {
            jsonWriter.writeNull();
         }
      } finally {
         this.parameterConsumerStateRecycler.release(parameterConsumerState);
      }

   }

   private static final class ParameterConsumerState {
      private MessageParameterResolver resolver;
      private JsonWriter jsonWriter;

      private ParameterConsumerState() {
      }
   }
}

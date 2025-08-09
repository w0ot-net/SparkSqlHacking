package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.WriterInterceptor;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.transform.Source;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.guava.Primitives;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.DataStructures;
import org.glassfish.jersey.internal.util.collection.KeyComparator;
import org.glassfish.jersey.internal.util.collection.KeyComparatorHashMap;
import org.glassfish.jersey.internal.util.collection.KeyComparatorLinkedHashMap;
import org.glassfish.jersey.message.AbstractEntityProviderModel;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.ReaderModel;
import org.glassfish.jersey.message.WriterModel;

public class MessageBodyFactory implements MessageBodyWorkers {
   private static final Logger LOGGER = Logger.getLogger(MessageBodyFactory.class.getName());
   public static final KeyComparator MEDIA_TYPE_KEY_COMPARATOR = new KeyComparator() {
      private static final long serialVersionUID = 1616819828630827763L;

      public boolean equals(MediaType mt1, MediaType mt2) {
         return mt1.isCompatible(mt2);
      }

      public int hash(MediaType mt) {
         return mt.getType().toLowerCase(Locale.ROOT).hashCode() + mt.getSubtype().toLowerCase(Locale.ROOT).hashCode();
      }
   };
   static final Comparator WORKER_BY_TYPE_COMPARATOR = new Comparator() {
      public int compare(AbstractEntityProviderModel o1, AbstractEntityProviderModel o2) {
         Class<?> o1ProviderClassParam = o1.providedType();
         Class<?> o2ProviderClassParam = o2.providedType();
         if (o1ProviderClassParam == o2ProviderClassParam) {
            return this.compare(o2.declaredTypes(), o1.declaredTypes());
         } else if (o1ProviderClassParam.isAssignableFrom(o2ProviderClassParam)) {
            return 1;
         } else {
            return o2ProviderClassParam.isAssignableFrom(o1ProviderClassParam) ? -1 : MessageBodyFactory.CLASS_BY_NAME_COMPARATOR.compare(o1ProviderClassParam, o2ProviderClassParam);
         }
      }

      private int compare(List mediaTypeList1, List mediaTypeList2) {
         mediaTypeList1 = mediaTypeList1.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : mediaTypeList1;
         mediaTypeList2 = mediaTypeList2.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : mediaTypeList2;
         return MediaTypes.MEDIA_TYPE_LIST_COMPARATOR.compare(mediaTypeList2, mediaTypeList1);
      }
   };
   private static final Comparator CLASS_BY_NAME_COMPARATOR = Comparator.comparing(Class::getName);
   private InjectionManager injectionManager;
   private final Boolean legacyProviderOrdering;
   private List readers;
   private List writers;
   private final Map readersCache;
   private final Map writersCache;
   private static final int LOOKUP_CACHE_INITIAL_CAPACITY = 32;
   private static final float LOOKUP_CACHE_LOAD_FACTOR = 0.75F;
   private final Map mbrTypeLookupCache;
   private final Map mbwTypeLookupCache;
   private final Map typeToMediaTypeReadersCache;
   private final Map typeToMediaTypeWritersCache;
   private final Map mbrLookupCache;
   private final Map mbwLookupCache;
   private static final Function MODEL_TO_WRITER = AbstractEntityProviderModel::provider;
   private static final Function MODEL_TO_READER = AbstractEntityProviderModel::provider;

   public MessageBodyFactory(Configuration configuration) {
      this.readersCache = new KeyComparatorHashMap(MEDIA_TYPE_KEY_COMPARATOR);
      this.writersCache = new KeyComparatorHashMap(MEDIA_TYPE_KEY_COMPARATOR);
      this.mbrTypeLookupCache = new ConcurrentHashMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
      this.mbwTypeLookupCache = new ConcurrentHashMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
      this.typeToMediaTypeReadersCache = new ConcurrentHashMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
      this.typeToMediaTypeWritersCache = new ConcurrentHashMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
      this.mbrLookupCache = new ConcurrentHashMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
      this.mbwLookupCache = new ConcurrentHashMap(32, 0.75F, DataStructures.DEFAULT_CONCURENCY_LEVEL);
      this.legacyProviderOrdering = configuration != null && PropertiesHelper.isProperty(configuration.getProperty("jersey.config.workers.legacyOrdering"));
   }

   public void initialize(InjectionManager injectionManager) {
      this.injectionManager = injectionManager;
      this.readers = new ArrayList();
      Set<MessageBodyReader> customMbrs = Providers.getCustomProviders(injectionManager, MessageBodyReader.class);
      Set<MessageBodyReader> mbrs = Providers.getProviders(injectionManager, MessageBodyReader.class);
      addReaders(this.readers, customMbrs, true);
      mbrs.removeAll(customMbrs);
      addReaders(this.readers, mbrs, false);
      if (this.legacyProviderOrdering) {
         this.readers.sort(new LegacyWorkerComparator(MessageBodyReader.class));

         for(ReaderModel model : this.readers) {
            for(MediaType mt : model.declaredTypes()) {
               List<MessageBodyReader> readerList = (List)this.readersCache.get(mt);
               if (readerList == null) {
                  readerList = new ArrayList();
                  this.readersCache.put(mt, readerList);
               }

               readerList.add(model.provider());
            }
         }
      }

      this.writers = new ArrayList();
      Set<MessageBodyWriter> customMbws = Providers.getCustomProviders(injectionManager, MessageBodyWriter.class);
      Set<MessageBodyWriter> mbws = Providers.getProviders(injectionManager, MessageBodyWriter.class);
      addWriters(this.writers, customMbws, true);
      mbws.removeAll(customMbws);
      addWriters(this.writers, mbws, false);
      if (this.legacyProviderOrdering) {
         this.writers.sort(new LegacyWorkerComparator(MessageBodyWriter.class));

         for(AbstractEntityProviderModel model : this.writers) {
            for(MediaType mt : model.declaredTypes()) {
               List<MessageBodyWriter> writerList = (List)this.writersCache.get(mt);
               if (writerList == null) {
                  writerList = new ArrayList();
                  this.writersCache.put(mt, writerList);
               }

               writerList.add(model.provider());
            }
         }
      }

   }

   private static void addReaders(List models, Set readers, boolean custom) {
      for(MessageBodyReader provider : readers) {
         List<MediaType> values = MediaTypes.createFrom((Consumes)provider.getClass().getAnnotation(Consumes.class));
         models.add(new ReaderModel(provider, values, custom));
      }

   }

   private static void addWriters(List models, Set writers, boolean custom) {
      for(MessageBodyWriter provider : writers) {
         List<MediaType> values = MediaTypes.createFrom((Produces)provider.getClass().getAnnotation(Produces.class));
         models.add(new WriterModel(provider, values, custom));
      }

   }

   public Map getReaders(MediaType mediaType) {
      Map<MediaType, List<MessageBodyReader>> subSet = new KeyComparatorLinkedHashMap(MEDIA_TYPE_KEY_COMPARATOR);
      getCompatibleProvidersMap(mediaType, this.readers, subSet);
      return subSet;
   }

   public Map getWriters(MediaType mediaType) {
      Map<MediaType, List<MessageBodyWriter>> subSet = new KeyComparatorLinkedHashMap(MEDIA_TYPE_KEY_COMPARATOR);
      getCompatibleProvidersMap(mediaType, this.writers, subSet);
      return subSet;
   }

   public String readersToString(Map readers) {
      return this.toString(readers);
   }

   public String writersToString(Map writers) {
      return this.toString(writers);
   }

   private String toString(Map set) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);

      for(Map.Entry e : set.entrySet()) {
         pw.append(((MediaType)e.getKey()).toString()).println(" ->");

         for(Object t : (List)e.getValue()) {
            pw.append("  ").println(t.getClass().getName());
         }
      }

      pw.flush();
      return sw.toString();
   }

   public MessageBodyReader getMessageBodyReader(Class c, Type t, Annotation[] as, MediaType mediaType) {
      return this.getMessageBodyReader(c, t, as, mediaType, (PropertiesDelegate)null);
   }

   public MessageBodyReader getMessageBodyReader(Class c, Type t, Annotation[] as, MediaType mediaType, PropertiesDelegate propertiesDelegate) {
      MessageBodyReader<T> p = null;
      if (this.legacyProviderOrdering) {
         if (mediaType != null) {
            p = this._getMessageBodyReader(c, t, as, mediaType, mediaType, propertiesDelegate);
            if (p == null) {
               p = this._getMessageBodyReader(c, t, as, mediaType, MediaTypes.getTypeWildCart(mediaType), propertiesDelegate);
            }
         }

         if (p == null) {
            p = this._getMessageBodyReader(c, t, as, mediaType, MediaType.WILDCARD_TYPE, propertiesDelegate);
         }
      } else {
         p = this._getMessageBodyReader(c, t, as, mediaType, this.readers, propertiesDelegate);
      }

      return p;
   }

   public List getMessageBodyReaderMediaTypes(Class type, Type genericType, Annotation[] annotations) {
      Set<MediaType> readableMediaTypes = new LinkedHashSet();

      for(ReaderModel model : this.readers) {
         boolean readableWorker = false;

         for(MediaType mt : model.declaredTypes()) {
            if (model.isReadable(type, genericType, annotations, mt)) {
               readableMediaTypes.add(mt);
               readableWorker = true;
            }

            if (!readableMediaTypes.contains(MediaType.WILDCARD_TYPE) && readableWorker && model.declaredTypes().contains(MediaType.WILDCARD_TYPE)) {
               readableMediaTypes.add(MediaType.WILDCARD_TYPE);
            }
         }
      }

      List<MediaType> mtl = new ArrayList(readableMediaTypes);
      mtl.sort(MediaTypes.PARTIAL_ORDER_COMPARATOR);
      return mtl;
   }

   private boolean isCompatible(AbstractEntityProviderModel model, Class c, MediaType mediaType) {
      if (model.providedType().equals(Object.class) || model.providedType().isAssignableFrom(c) || c.isAssignableFrom(model.providedType())) {
         for(MediaType mt : model.declaredTypes()) {
            if (mediaType == null) {
               return true;
            }

            if (mediaType.isCompatible(mt)) {
               return true;
            }
         }
      }

      return false;
   }

   private MessageBodyReader _getMessageBodyReader(Class c, Type t, Annotation[] as, MediaType mediaType, List models, PropertiesDelegate propertiesDelegate) {
      MediaType lookupType = mediaType != null && !mediaType.getParameters().isEmpty() ? new MediaType(mediaType.getType(), mediaType.getSubtype()) : mediaType;
      ModelLookupKey lookupKey = new ModelLookupKey(c, lookupType);
      List<ReaderModel> readers = (List)this.mbrLookupCache.get(lookupKey);
      if (readers == null) {
         readers = new ArrayList();

         for(ReaderModel model : models) {
            if (this.isCompatible(model, c, mediaType)) {
               readers.add(model);
            }
         }

         readers.sort(new WorkerComparator(c, mediaType));
         this.mbrLookupCache.put(lookupKey, readers);
      }

      if (readers.isEmpty()) {
         return null;
      } else {
         TracingLogger tracingLogger = TracingLogger.getInstance(propertiesDelegate);
         MessageBodyReader<T> selected = null;

         for(ReaderModel model : readers) {
            if (model.isReadable(c, t, as, mediaType)) {
               selected = (MessageBodyReader)model.provider();
               tracingLogger.log(MsgTraceEvent.MBR_SELECTED, selected);
               break;
            }

            tracingLogger.log(MsgTraceEvent.MBR_NOT_READABLE, model.provider());
         }

         Iterator iterator;
         if (tracingLogger.isLogEnabled(MsgTraceEvent.MBR_SKIPPED)) {
            while(iterator.hasNext()) {
               ReaderModel model = (ReaderModel)iterator.next();
               tracingLogger.log(MsgTraceEvent.MBR_SKIPPED, model.provider());
            }
         }

         return selected;
      }
   }

   private MessageBodyReader _getMessageBodyReader(Class c, Type t, Annotation[] as, MediaType mediaType, MediaType lookup, PropertiesDelegate propertiesDelegate) {
      List<MessageBodyReader> readers = (List)this.readersCache.get(lookup);
      if (readers == null) {
         return null;
      } else {
         TracingLogger tracingLogger = TracingLogger.getInstance(propertiesDelegate);
         MessageBodyReader<T> selected = null;

         for(MessageBodyReader p : readers) {
            if (isReadable(p, c, t, as, mediaType)) {
               selected = p;
               tracingLogger.log(MsgTraceEvent.MBR_SELECTED, p);
               break;
            }

            tracingLogger.log(MsgTraceEvent.MBR_NOT_READABLE, p);
         }

         Iterator iterator;
         if (tracingLogger.isLogEnabled(MsgTraceEvent.MBR_SKIPPED)) {
            while(iterator.hasNext()) {
               MessageBodyReader p = (MessageBodyReader)iterator.next();
               tracingLogger.log(MsgTraceEvent.MBR_SKIPPED, p);
            }
         }

         return selected;
      }
   }

   public MessageBodyWriter getMessageBodyWriter(Class c, Type t, Annotation[] as, MediaType mediaType) {
      return this.getMessageBodyWriter(c, t, as, mediaType, (PropertiesDelegate)null);
   }

   public MessageBodyWriter getMessageBodyWriter(Class c, Type t, Annotation[] as, MediaType mediaType, PropertiesDelegate propertiesDelegate) {
      MessageBodyWriter<T> p = null;
      if (this.legacyProviderOrdering) {
         if (mediaType != null) {
            p = this._getMessageBodyWriter(c, t, as, mediaType, mediaType, propertiesDelegate);
            if (p == null) {
               p = this._getMessageBodyWriter(c, t, as, mediaType, MediaTypes.getTypeWildCart(mediaType), propertiesDelegate);
            }
         }

         if (p == null) {
            p = this._getMessageBodyWriter(c, t, as, mediaType, MediaType.WILDCARD_TYPE, propertiesDelegate);
         }
      } else {
         p = this._getMessageBodyWriter(c, t, as, mediaType, this.writers, propertiesDelegate);
      }

      return p;
   }

   private MessageBodyWriter _getMessageBodyWriter(Class c, Type t, Annotation[] as, MediaType mediaType, List models, PropertiesDelegate propertiesDelegate) {
      MediaType lookupType = mediaType != null && !mediaType.getParameters().isEmpty() ? new MediaType(mediaType.getType(), mediaType.getSubtype()) : mediaType;
      ModelLookupKey lookupKey = new ModelLookupKey(c, lookupType);
      List<WriterModel> writers = (List)this.mbwLookupCache.get(lookupKey);
      if (writers == null) {
         writers = new ArrayList();

         for(WriterModel model : models) {
            if (this.isCompatible(model, c, mediaType)) {
               writers.add(model);
            }
         }

         writers.sort(new WorkerComparator(c, mediaType));
         this.mbwLookupCache.put(lookupKey, writers);
      }

      if (writers.isEmpty()) {
         return null;
      } else {
         TracingLogger tracingLogger = TracingLogger.getInstance(propertiesDelegate);
         MessageBodyWriter<T> selected = null;

         for(WriterModel model : writers) {
            if (model.isWriteable(c, t, as, mediaType)) {
               selected = (MessageBodyWriter)model.provider();
               tracingLogger.log(MsgTraceEvent.MBW_SELECTED, selected);
               break;
            }

            tracingLogger.log(MsgTraceEvent.MBW_NOT_WRITEABLE, model.provider());
         }

         Iterator iterator;
         if (tracingLogger.isLogEnabled(MsgTraceEvent.MBW_SKIPPED)) {
            while(iterator.hasNext()) {
               WriterModel model = (WriterModel)iterator.next();
               tracingLogger.log(MsgTraceEvent.MBW_SKIPPED, model.provider());
            }
         }

         return selected;
      }
   }

   private MessageBodyWriter _getMessageBodyWriter(Class c, Type t, Annotation[] as, MediaType mediaType, MediaType lookup, PropertiesDelegate propertiesDelegate) {
      List<MessageBodyWriter> writers = (List)this.writersCache.get(lookup);
      if (writers == null) {
         return null;
      } else {
         TracingLogger tracingLogger = TracingLogger.getInstance(propertiesDelegate);
         MessageBodyWriter<T> selected = null;

         for(MessageBodyWriter p : writers) {
            if (isWriteable(p, c, t, as, mediaType)) {
               selected = p;
               tracingLogger.log(MsgTraceEvent.MBW_SELECTED, p);
               break;
            }

            tracingLogger.log(MsgTraceEvent.MBW_NOT_WRITEABLE, p);
         }

         Iterator iterator;
         if (tracingLogger.isLogEnabled(MsgTraceEvent.MBW_SKIPPED)) {
            while(iterator.hasNext()) {
               MessageBodyWriter p = (MessageBodyWriter)iterator.next();
               tracingLogger.log(MsgTraceEvent.MBW_SKIPPED, p);
            }
         }

         return selected;
      }
   }

   private static void getCompatibleProvidersMap(MediaType mediaType, List set, Map subSet) {
      if (mediaType.isWildcardType()) {
         getCompatibleProvidersList(mediaType, set, subSet);
      } else if (mediaType.isWildcardSubtype()) {
         getCompatibleProvidersList(mediaType, set, subSet);
         getCompatibleProvidersList(MediaType.WILDCARD_TYPE, set, subSet);
      } else {
         getCompatibleProvidersList(mediaType, set, subSet);
         getCompatibleProvidersList(MediaTypes.getTypeWildCart(mediaType), set, subSet);
         getCompatibleProvidersList(MediaType.WILDCARD_TYPE, set, subSet);
      }

   }

   private static void getCompatibleProvidersList(MediaType mediaType, List set, Map subSet) {
      List<T> providers = (List)set.stream().filter((model) -> model.declaredTypes().contains(mediaType)).map(AbstractEntityProviderModel::provider).collect(Collectors.toList());
      if (!providers.isEmpty()) {
         subSet.put(mediaType, Collections.unmodifiableList(providers));
      }

   }

   public List getMessageBodyWriterMediaTypes(Class c, Type t, Annotation[] as) {
      Set<MediaType> writeableMediaTypes = new LinkedHashSet();

      for(WriterModel model : this.writers) {
         boolean writeableWorker = false;

         for(MediaType mt : model.declaredTypes()) {
            if (model.isWriteable(c, t, as, mt)) {
               writeableMediaTypes.add(mt);
               writeableWorker = true;
            }

            if (!writeableMediaTypes.contains(MediaType.WILDCARD_TYPE) && writeableWorker && model.declaredTypes().contains(MediaType.WILDCARD_TYPE)) {
               writeableMediaTypes.add(MediaType.WILDCARD_TYPE);
            }
         }
      }

      List<MediaType> mtl = new ArrayList(writeableMediaTypes);
      mtl.sort(MediaTypes.PARTIAL_ORDER_COMPARATOR);
      return mtl;
   }

   public List getMessageBodyWritersForType(Class type) {
      return (List)this.getWritersModelsForType(type).stream().map(MODEL_TO_WRITER).collect(Collectors.toList());
   }

   public List getWritersModelsForType(Class type) {
      List<WriterModel> writerModels = (List)this.mbwTypeLookupCache.get(type);
      return writerModels != null ? writerModels : this.processMessageBodyWritersForType(type);
   }

   private List processMessageBodyWritersForType(Class clazz) {
      List<WriterModel> suitableWriters = new ArrayList();
      if (Response.class.isAssignableFrom(clazz)) {
         suitableWriters.addAll(this.writers);
      } else {
         Class<?> wrapped = Primitives.wrap(clazz);

         for(WriterModel model : this.writers) {
            if (model.providedType() == null || model.providedType() == clazz || model.providedType().isAssignableFrom(wrapped)) {
               suitableWriters.add(model);
            }
         }
      }

      suitableWriters.sort(WORKER_BY_TYPE_COMPARATOR);
      this.mbwTypeLookupCache.put(clazz, suitableWriters);
      this.typeToMediaTypeWritersCache.put(clazz, getMessageBodyWorkersMediaTypesByType(suitableWriters));
      return suitableWriters;
   }

   public List getMessageBodyWriterMediaTypesByType(Class type) {
      if (!this.typeToMediaTypeWritersCache.containsKey(type)) {
         this.processMessageBodyWritersForType(type);
      }

      return (List)this.typeToMediaTypeWritersCache.get(type);
   }

   public List getMessageBodyReaderMediaTypesByType(Class type) {
      if (!this.typeToMediaTypeReadersCache.containsKey(type)) {
         this.processMessageBodyReadersForType(type);
      }

      return (List)this.typeToMediaTypeReadersCache.get(type);
   }

   private static List getMessageBodyWorkersMediaTypesByType(List workerModels) {
      Set<MediaType> mediaTypeSet = new HashSet();

      for(AbstractEntityProviderModel model : workerModels) {
         mediaTypeSet.addAll(model.declaredTypes());
      }

      List<MediaType> mediaTypes = new ArrayList(mediaTypeSet);
      mediaTypes.sort(MediaTypes.PARTIAL_ORDER_COMPARATOR);
      return mediaTypes;
   }

   public List getMessageBodyReadersForType(Class type) {
      return (List)this.getReaderModelsForType(type).stream().map(MODEL_TO_READER).collect(Collectors.toList());
   }

   public List getReaderModelsForType(Class type) {
      if (!this.mbrTypeLookupCache.containsKey(type)) {
         this.processMessageBodyReadersForType(type);
      }

      return (List)this.mbrTypeLookupCache.get(type);
   }

   private List processMessageBodyReadersForType(Class clazz) {
      List<ReaderModel> suitableReaders = new ArrayList();
      Class<?> wrapped = Primitives.wrap(clazz);

      for(ReaderModel reader : this.readers) {
         if (reader.providedType() == null || reader.providedType() == clazz || reader.providedType().isAssignableFrom(wrapped)) {
            suitableReaders.add(reader);
         }
      }

      suitableReaders.sort(WORKER_BY_TYPE_COMPARATOR);
      this.mbrTypeLookupCache.put(clazz, suitableReaders);
      this.typeToMediaTypeReadersCache.put(clazz, getMessageBodyWorkersMediaTypesByType(suitableReaders));
      return suitableReaders;
   }

   public MediaType getMessageBodyWriterMediaType(Class c, Type t, Annotation[] as, List acceptableMediaTypes) {
      for(MediaType acceptable : acceptableMediaTypes) {
         for(WriterModel model : this.writers) {
            for(MediaType mt : model.declaredTypes()) {
               if (mt.isCompatible(acceptable) && model.isWriteable(c, t, as, acceptable)) {
                  return MediaTypes.mostSpecific(mt, acceptable);
               }
            }
         }
      }

      return null;
   }

   public Object readFrom(Class rawType, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, PropertiesDelegate propertiesDelegate, InputStream entityStream, Iterable readerInterceptors, boolean translateNce) throws WebApplicationException, IOException {
      ReaderInterceptorExecutor executor = new ReaderInterceptorExecutor(rawType, type, annotations, mediaType, httpHeaders, propertiesDelegate, entityStream, this, readerInterceptors, translateNce, this.injectionManager);
      TracingLogger tracingLogger = TracingLogger.getInstance(propertiesDelegate);
      long timestamp = tracingLogger.timestamp(MsgTraceEvent.RI_SUMMARY);

      Object var19;
      try {
         Object instance = executor.proceed();
         if (!(instance instanceof Closeable) && !(instance instanceof Source)) {
            InputStream stream = executor.getInputStream();
            if (stream != entityStream && stream != null) {
               ReaderWriter.safelyClose(stream);
            }
         }

         var19 = instance;
      } finally {
         tracingLogger.logDuration(MsgTraceEvent.RI_SUMMARY, timestamp, executor.getProcessedCount());
      }

      return var19;
   }

   public OutputStream writeTo(Object t, Class rawType, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, PropertiesDelegate propertiesDelegate, OutputStream entityStream, Iterable writerInterceptors) throws IOException, WebApplicationException {
      WriterInterceptorExecutor executor = new WriterInterceptorExecutor(t, rawType, type, annotations, mediaType, httpHeaders, propertiesDelegate, entityStream, this, writerInterceptors, this.injectionManager);
      TracingLogger tracingLogger = TracingLogger.getInstance(propertiesDelegate);
      long timestamp = tracingLogger.timestamp(MsgTraceEvent.WI_SUMMARY);

      try {
         executor.proceed();
      } finally {
         tracingLogger.logDuration(MsgTraceEvent.WI_SUMMARY, timestamp, executor.getProcessedCount());
      }

      return executor.getOutputStream();
   }

   public static boolean isWriteable(MessageBodyWriter provider, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
      try {
         return provider.isWriteable(type, genericType, annotations, mediaType);
      } catch (Exception ex) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, LocalizationMessages.ERROR_MBW_ISWRITABLE(provider.getClass().getName()), ex);
         }

         return false;
      }
   }

   public static boolean isReadable(MessageBodyReader provider, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
      try {
         return provider.isReadable(type, genericType, annotations, mediaType);
      } catch (Exception ex) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, LocalizationMessages.ERROR_MBR_ISREADABLE(provider.getClass().getName()), ex);
         }

         return false;
      }
   }

   public static class MessageBodyWorkersConfigurator implements BootstrapConfigurator {
      private MessageBodyFactory messageBodyFactory;

      public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
         this.messageBodyFactory = new MessageBodyFactory(bootstrapBag.getConfiguration());
         InstanceBinding<MessageBodyFactory> binding = (InstanceBinding)Bindings.service((Object)this.messageBodyFactory).to(MessageBodyWorkers.class);
         injectionManager.register((Binding)binding);
      }

      public void postInit(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
         this.messageBodyFactory.initialize(injectionManager);
         bootstrapBag.setMessageBodyWorkers(this.messageBodyFactory);
      }
   }

   private static class DeclarationDistanceComparator implements Comparator {
      private final Class declared;
      private final Map distanceMap = new HashMap();

      DeclarationDistanceComparator(Class declared) {
         this.declared = declared;
      }

      public int compare(Object o1, Object o2) {
         int d1 = this.getDistance(o1);
         int d2 = this.getDistance(o2);
         return d2 - d1;
      }

      private int getDistance(Object t) {
         Integer distance = (Integer)this.distanceMap.get(t.getClass());
         if (distance != null) {
            return distance;
         } else {
            ReflectionHelper.DeclaringClassInterfacePair p = ReflectionHelper.getClass(t.getClass(), this.declared);
            Class[] as = ReflectionHelper.getParameterizedClassArguments(p);
            Class a = as != null ? as[0] : null;

            for(distance = 0; a != null && a != Object.class; a = a.getSuperclass()) {
               distance = distance + 1;
            }

            this.distanceMap.put(t.getClass(), distance);
            return distance;
         }
      }
   }

   private static class WorkerComparator implements Comparator {
      final Class wantedType;
      final MediaType wantedMediaType;

      private WorkerComparator(Class wantedType, MediaType wantedMediaType) {
         this.wantedType = wantedType;
         this.wantedMediaType = wantedMediaType;
      }

      public int compare(AbstractEntityProviderModel modelA, AbstractEntityProviderModel modelB) {
         int distance = this.compareTypeDistances(modelA.providedType(), modelB.providedType());
         if (distance != 0) {
            return distance;
         } else {
            int mediaTypeComparison = this.getMediaTypeDistance(this.wantedMediaType, modelA.declaredTypes()) - this.getMediaTypeDistance(this.wantedMediaType, modelB.declaredTypes());
            if (mediaTypeComparison != 0) {
               return mediaTypeComparison;
            } else if (modelA.isCustom() ^ modelB.isCustom()) {
               return modelA.isCustom() ? -1 : 1;
            } else {
               return 0;
            }
         }
      }

      private int getMediaTypeDistance(MediaType wanted, List mtl) {
         if (wanted == null) {
            return 0;
         } else {
            int distance = 2;

            for(MediaType mt : mtl) {
               if (MediaTypes.typeEqual(wanted, mt)) {
                  return 0;
               }

               if (distance > 1 && MediaTypes.typeEqual(MediaTypes.getTypeWildCart(wanted), mt)) {
                  distance = 1;
               }
            }

            return distance;
         }
      }

      private int compareTypeDistances(Class providerClassParam1, Class providerClassParam2) {
         return this.getTypeDistance(providerClassParam1) - this.getTypeDistance(providerClassParam2);
      }

      private int getTypeDistance(Class classParam) {
         Class<?> tmp1 = this.wantedType;
         Class<?> tmp2 = classParam;
         Iterator<Class<?>> it1 = this.getClassHierarchyIterator(tmp1);
         Iterator<Class<?>> it2 = this.getClassHierarchyIterator(classParam);
         int distance = 0;

         while(!this.wantedType.equals(tmp2) && !classParam.equals(tmp1)) {
            ++distance;
            if (!this.wantedType.equals(tmp2)) {
               tmp2 = it2.hasNext() ? (Class)it2.next() : null;
            }

            if (!classParam.equals(tmp1)) {
               tmp1 = it1.hasNext() ? (Class)it1.next() : null;
            }

            if (tmp2 == null && tmp1 == null) {
               return Integer.MAX_VALUE;
            }
         }

         return distance;
      }

      private Iterator getClassHierarchyIterator(Class classParam) {
         if (classParam == null) {
            return Collections.emptyList().iterator();
         } else {
            ArrayList<Class<?>> classes = new ArrayList();
            LinkedList<Class<?>> unprocessed = new LinkedList();
            boolean objectFound = false;
            unprocessed.add(classParam);

            while(!unprocessed.isEmpty()) {
               Class<?> clazz = (Class)unprocessed.removeFirst();
               if (Object.class.equals(clazz)) {
                  objectFound = true;
               } else {
                  classes.add(clazz);
               }

               unprocessed.addAll(Arrays.asList(clazz.getInterfaces()));
               Class<?> superclazz = clazz.getSuperclass();
               if (superclazz != null) {
                  unprocessed.add(superclazz);
               }
            }

            if (objectFound) {
               classes.add(Object.class);
            }

            return classes.iterator();
         }
      }
   }

   private static class LegacyWorkerComparator implements Comparator {
      final DeclarationDistanceComparator distanceComparator;

      private LegacyWorkerComparator(Class type) {
         this.distanceComparator = new DeclarationDistanceComparator(type);
      }

      public int compare(AbstractEntityProviderModel modelA, AbstractEntityProviderModel modelB) {
         if (modelA.isCustom() ^ modelB.isCustom()) {
            return modelA.isCustom() ? -1 : 1;
         } else {
            MediaType mtA = (MediaType)modelA.declaredTypes().get(0);
            MediaType mtB = (MediaType)modelB.declaredTypes().get(0);
            int mediaTypeComparison = MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(mtA, mtB);
            return mediaTypeComparison != 0 && !mtA.isCompatible(mtB) ? mediaTypeComparison : this.distanceComparator.compare(modelA.provider(), modelB.provider());
         }
      }
   }

   private static class ModelLookupKey {
      final Class clazz;
      final MediaType mediaType;

      private ModelLookupKey(Class clazz, MediaType mediaType) {
         this.clazz = clazz;
         this.mediaType = mediaType;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            boolean var10000;
            label44: {
               label30: {
                  ModelLookupKey that = (ModelLookupKey)o;
                  if (this.clazz != null) {
                     if (!this.clazz.equals(that.clazz)) {
                        break label30;
                     }
                  } else if (that.clazz != null) {
                     break label30;
                  }

                  if (this.mediaType != null) {
                     if (this.mediaType.equals(that.mediaType)) {
                        break label44;
                     }
                  } else if (that.mediaType == null) {
                     break label44;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.clazz != null ? this.clazz.hashCode() : 0;
         result = 31 * result + (this.mediaType != null ? this.mediaType.hashCode() : 0);
         return result;
      }
   }
}

package org.snakeyaml.engine.v2.api;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import org.snakeyaml.engine.v2.emitter.Emitter;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.representer.BaseRepresenter;
import org.snakeyaml.engine.v2.representer.StandardRepresenter;
import org.snakeyaml.engine.v2.serializer.Serializer;

public class Dump {
   protected DumpSettings settings;
   protected BaseRepresenter representer;

   public Dump(DumpSettings settings) {
      this(settings, new StandardRepresenter(settings));
   }

   public Dump(DumpSettings settings, BaseRepresenter representer) {
      Objects.requireNonNull(settings, "DumpSettings cannot be null");
      Objects.requireNonNull(representer, "Representer cannot be null");
      this.settings = settings;
      this.representer = representer;
   }

   public void dumpAll(Iterator instancesIterator, StreamDataWriter streamDataWriter) {
      Objects.requireNonNull(instancesIterator, "Iterator cannot be null");
      Objects.requireNonNull(streamDataWriter, "StreamDataWriter cannot be null");
      Serializer serializer = new Serializer(this.settings, new Emitter(this.settings, streamDataWriter));
      serializer.emitStreamStart();

      while(instancesIterator.hasNext()) {
         Object instance = instancesIterator.next();
         Node node = this.representer.represent(instance);
         serializer.serializeDocument(node);
      }

      serializer.emitStreamEnd();
   }

   public void dump(Object yaml, StreamDataWriter streamDataWriter) {
      Iterator<? extends Object> iter = Collections.singleton(yaml).iterator();
      this.dumpAll(iter, streamDataWriter);
   }

   public String dumpAllToString(Iterator instancesIterator) {
      StreamToStringWriter writer = new StreamToStringWriter();
      this.dumpAll(instancesIterator, writer);
      return writer.toString();
   }

   public String dumpToString(Object yaml) {
      StreamToStringWriter writer = new StreamToStringWriter();
      this.dump(yaml, writer);
      return writer.toString();
   }

   public void dumpNode(Node node, StreamDataWriter streamDataWriter) {
      Objects.requireNonNull(node, "Node cannot be null");
      Objects.requireNonNull(streamDataWriter, "StreamDataWriter cannot be null");
      Serializer serializer = new Serializer(this.settings, new Emitter(this.settings, streamDataWriter));
      serializer.emitStreamStart();
      serializer.serializeDocument(node);
      serializer.emitStreamEnd();
   }
}

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.glassfish.hk2.api.messaging.Topic;
import org.glassfish.hk2.api.messaging.TopicDistributionService;
import org.glassfish.hk2.utilities.NamedImpl;

public class TopicImpl implements Topic {
   private final ServiceLocatorImpl locator;
   private final Type topicType;
   private final Set requiredQualifiers;

   TopicImpl(ServiceLocatorImpl locator, Type topicType, Set requiredQualifiers) {
      this.locator = locator;
      this.topicType = topicType;
      this.requiredQualifiers = Collections.unmodifiableSet(requiredQualifiers);
   }

   public void publish(Object message) {
      if (message == null) {
         throw new IllegalArgumentException();
      } else {
         TopicDistributionService distributor = (TopicDistributionService)this.locator.getService(TopicDistributionService.class);
         if (distributor == null) {
            throw new IllegalStateException("There is no implementation of the TopicDistributionService to distribute the message");
         } else {
            distributor.distributeMessage(this, message);
         }
      }
   }

   public Topic named(String name) {
      return this.qualifiedWith(new NamedImpl(name));
   }

   public Topic ofType(Type type) {
      return new TopicImpl(this.locator, type, this.requiredQualifiers);
   }

   public Topic qualifiedWith(Annotation... qualifiers) {
      HashSet<Annotation> moreAnnotations = new HashSet(this.requiredQualifiers);

      for(Annotation qualifier : qualifiers) {
         moreAnnotations.add(qualifier);
      }

      return new TopicImpl(this.locator, this.topicType, moreAnnotations);
   }

   public Type getTopicType() {
      return this.topicType;
   }

   public Set getTopicQualifiers() {
      return this.requiredQualifiers;
   }
}

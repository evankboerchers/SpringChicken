package com.springchicken.presentation.auth;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import javax.annotation.CheckForNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.springframework.security.access.annotation.Jsr250SecurityConfig.DENY_ALL_ATTRIBUTE;

/**
 *This class runs at start-up, and checks all other classes with the @Controller annotation for an annotation of
 * PreAuthorize or PostAuthorize. If it finds one for the whole class, it does nothing.
 * If the controller class does not have these annotations, it will cycle through each of the class’s methods,
 * and check if the methods do if any of the methods are not annotated,
 * it will provide a DENY_ALL_ATTRIBUTE to these methods, ensuring that  nobody can access them.
 * The purpose of this is to ensure all methods or classes have security annotations,
 * and if this annotation is missed, that the endpoint will remain secured regardless.
 */
public class CustomAuthorityMethodSecurityMetadataSource extends AbstractFallbackMethodSecurityMetadataSource
{
        @Override
        protected Collection<ConfigAttribute> findAttributes(Class<?> clazz)
        {
                return null;
        }

        @Override
        protected Collection<ConfigAttribute> findAttributes(Method method, Class<?> targetClass)
        {
                Annotation[] annotations = method.getAnnotations();

                List<ConfigAttribute> attributes = new ArrayList<>();

                // if the class is annotated as @Controller we should by default deny access to all methods
                if (AnnotationUtils.findAnnotation(targetClass, Controller.class) != null)
                {
                        attributes.add(DENY_ALL_ATTRIBUTE);
                }

                if (annotations != null)
                {
                        for (Annotation a : annotations)
                        {
                        // but not if the method has at least a PreAuthorize or PostAuthorize annotation
                                if (a instanceof PreAuthorize || a instanceof PostAuthorize)
                                {
                                        return null;
                                }
                        }
                }
                Annotation[] classAnnotations = targetClass.getAnnotations();
                if (classAnnotations != null)
                {
                        for (Annotation a : classAnnotations)
                        {
                        // but not if the method has at least a PreAuthorize or PostAuthorize annotation
                                if (a instanceof PreAuthorize || a instanceof PostAuthorize)
                                {
                                        return null;
                                }
                        }
                }

                return attributes;
        }

        @Override
        @CheckForNull
        public Collection<ConfigAttribute> getAllConfigAttributes()
        {
                return null;
        }
}
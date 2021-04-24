package com.springchicken;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Applies the {@link Nonnull} annotation to every class field unless overridden.
 * Copy of deprecated {@link edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault}
 */
@Documented
@Nonnull
@TypeQualifierDefault(ElementType.METHOD) // <-- use METHOD for return values
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnValuesAreNonnullByDefault
{
    // nothing to add
}

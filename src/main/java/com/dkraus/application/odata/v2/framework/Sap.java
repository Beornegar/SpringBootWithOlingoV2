package com.dkraus.application.odata.v2.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface Sap {
    public boolean creatable() default false;
    public boolean updatable() default false;
    public boolean sortable() default false;
    public boolean filterable() default false;

    public String displayFormat() default "";
    public String label() default "";
    public String text() default "";
   
}

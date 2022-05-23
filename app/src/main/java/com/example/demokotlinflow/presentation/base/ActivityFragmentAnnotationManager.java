package com.example.demokotlinflow.presentation.base;

public class ActivityFragmentAnnotationManager {
    public static int check(Object clazz) {
        boolean annotationPresent = clazz.getClass().isAnnotationPresent(ActivityFragmentAnnotation.class);
        if (!annotationPresent){
            throw new IllegalStateException("Activity annotation illegal State");
        }
        ActivityFragmentAnnotation annotation = clazz.getClass().getAnnotation(ActivityFragmentAnnotation.class);
        return annotation.contentId();
    }
}

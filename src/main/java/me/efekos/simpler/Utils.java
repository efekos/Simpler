package me.efekos.simpler;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class Utils {

    public static <T> ArrayList<T> fromStreamToArrayList(Stream<T> stream){
        ArrayList<T> list = new ArrayList<>();
        stream.forEach(list::add);
        return list;
    }

    public static void runAllMethodsAnnotatedWith(Class<? extends Annotation> annotation,Object ...params) throws Exception {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> methods = reflections.getMethodsAnnotatedWith(annotation);

        for (Method m : methods) {
            // for simplicity, invokes methods as static without parameters
            m.invoke(params);
        }
    }

    public static Class<?>[] getAllClassesAnnotatedWith(Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(new TypeAnnotationsScanner()));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);

        return (Class<?>[]) classes.stream().filter(Objects::nonNull).toArray();
    }
}

package app.util.mvc;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    public static final String CONTROLLER_PACKAGE_PREFIX = "app.controller.";
    public static final String CONTROLLER_CLASS_SUFFIX = "Controller";
    public static final String DEFAULT_CONTROLLER_NAME = "Default";

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = null;
        RequestMapping methodAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (methodAnnotation != null) {
            if (methodAnnotation.value().length == 0) {
                methodAnnotation = createReplaceAnnotation(method.getName(), methodAnnotation);
            }

        } else {
            if (Modifier.isPublic(method.getModifiers())) {
                methodAnnotation = createReplaceAnnotation(method.getName());
            }
        }
        if (methodAnnotation != null) {
            info = createRequestMappingInfo(methodAnnotation, null);
            RequestMapping typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
            if (typeAnnotation != null) {
                info = createRequestMappingInfo(typeAnnotation, null).combine(info);
            } else {
                String name = handlerType.getName();
                if (name.startsWith(CONTROLLER_PACKAGE_PREFIX)) {
                    name = name.substring(CONTROLLER_PACKAGE_PREFIX.length());
                }
                if (name.endsWith(CONTROLLER_CLASS_SUFFIX)) {
                    name = name.substring(0, name.length() - CONTROLLER_CLASS_SUFFIX.length());
                }
                String[] arr = name.split("\\.");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                    sb.append("/");
                    if (i + 1 < arr.length) {
                        sb.append(arr[i]);
                    } else {
                        String tmp = arr[i];
                        if (!tmp.equals(DEFAULT_CONTROLLER_NAME)) {
                            tmp = tmp.substring(0, 1).toLowerCase() + tmp.substring(1);
                            sb.append(tmp);
                        } else {
                            //删除最后的斜杠
                            sb.deleteCharAt(sb.length() - 1);
                        }
                    }
                }
                info = createRequestMappingInfo(createReplaceAnnotation(sb.toString()), null).combine(info);
            }
        }
        return info;
    }

    private RequestMapping createReplaceAnnotation(final String name, final RequestMapping old) {
        return new RequestMapping() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return old.getClass();
            }

            @Override
            public String[] value() {
                return new String[]{name};
            }

            @Override
            public RequestMethod[] method() {
                return old.method();
            }

            @Override
            public String[] params() {
                return old.params();
            }

            @Override
            public String[] headers() {
                return old.headers();
            }

            @Override
            public String[] consumes() {
                return old.consumes();
            }

            @Override
            public String[] produces() {
                return old.produces();
            }
        };
    }

    private RequestMapping createReplaceAnnotation(final String name) {
        return new RequestMapping() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return RequestMapping.class;
            }

            @Override
            public String[] value() {
                return new String[]{name};
            }

            @Override
            public RequestMethod[] method() {
                return new RequestMethod[0];
            }

            @Override
            public String[] params() {
                return new String[0];
            }

            @Override
            public String[] headers() {
                return new String[0];
            }

            @Override
            public String[] consumes() {
                return new String[0];
            }

            @Override
            public String[] produces() {
                return new String[0];
            }
        };
    }
}
package edu.umich.runner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class FunctionalTestRunner {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    public void runForClass(Class klass) throws Exception {
        Object classInstance = klass.newInstance();
        try {
            beanFactory.autowireBean(classInstance);

            List<Method> beforeTestMethods = resolveMethods(BeforeFunctionalTest.class, classInstance);
            List<Method> afterTestMethods = resolveMethods(AfterFunctionalTest.class, classInstance);
            List<Method> functionalTestMethods = resolveMethods(FunctionalTest.class, classInstance);

            runTests(beforeTestMethods, afterTestMethods, functionalTestMethods, classInstance);
        } finally {
            beanFactory.destroyBean(classInstance);
        }
    }

    private List< Method> resolveMethods(Class annotation, Object classInstance) {
        List methods = new ArrayList<>();
        for (Method method : classInstance.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }
        return methods;
    }

    private void runTests(List<Method> beforeTestMethods, List<Method> afterTestMethods, List<Method> functionalTestMethods, Object classInstance) throws Exception {
        for (Method functionalTestMethod : functionalTestMethods) {
            try {
                runMethods(beforeTestMethods, classInstance);
                runMethod(functionalTestMethod, classInstance);
            } finally {
                runMethods(afterTestMethods, classInstance);
            }
        }
    }

    private void runMethods(List<Method> methods, Object classInstance) throws Exception {
        for (Method method : methods) {
            runMethod(method, classInstance);
        }
    }

    private void runMethod(Method method, Object classInstance) throws Exception {
        System.out.println("invoking " + method.getName());
        try {
            method.invoke(classInstance);
        } catch (Exception e) {
            Result result = new Result();
            result.setClassName(classInstance.getClass().getName());
            result.setName(method.getName());
            result.setFailure(e.getCause().getMessage());
            TestResultReporter.getInstance().addResults(result);
        }
    }
}

package com.example.javatest01;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtensionFor14 implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final long THRESHOLD = 1000L;  //1초

    private ExtensionContext.Store getStore(ExtensionContext context){
        //ExtensionContext 를 보면 값을 저장해두는 store라는 개념이 있음
        String testClassName = context.getRequiredTestClass().getName(); // 컨텍스트에서 테스트 클래스명과
        String testMethodName = context.getRequiredTestMethod().getName(); //테스트 메서드명을 가져와서

        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        //스토어를 가져옴

        return store;
    }


    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        CustomTagFor07Slow slowAnnotation = context.getRequiredTestMethod().getAnnotation(CustomTagFor07Slow.class);
        // 이 부분은 자바의 리플렉션을 공부하면 알 수 있다고 하네요..

        ExtensionContext.Store store = getStore(context);
        String testMethodName = context.getRequiredTestMethod().getName();

        long startTime = store.remove("START_TIME", long.class); //start time 을 가져오면서 지움
        long duration = System.currentTimeMillis() - startTime; // 해당 단위 메서드가 실행되는데 걸린 millisec

        if(duration > THRESHOLD && slowAnnotation == null){ //실행 시간이 일정시간 이상이면서 slow 어노테이션이 안붙어있을 경우
            System.out.printf("Please consider mark @CustomTagFor07Slow for test unit :: [%s] \n", testMethodName);
        }

    }


}

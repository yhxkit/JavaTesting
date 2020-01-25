package com.example.javatest01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

class TestClass09Test {

    @ParameterizedTest
    @ValueSource(strings = {"스트링", "말고도", "다른", "타입도", "됩니다"})
    void testWithParam(String msg){
        System.out.println(msg);
    }

    @DisplayName("테스트 이름 변경")
    @ParameterizedTest(name = "{index} : {displayName} = {0}" ) //이름에 인덱스도 사용가능
    // 파라미터도 참조 가능.. 현재는 파라미터 하나 뿐이라 {0}
    @ValueSource(strings = {"스트링", "말고도", "다른", "타입도", "됩니다"})
    void testWithParamNamed(String msg){
        System.out.println(msg);
    }


    @DisplayName("테스트 이름 변경")
    @ParameterizedTest(name = "{index} : {displayName} = {0}" ) //이름에 인덱스도 사용가능
    // 파라미터도 참조 가능.. 현재는 파라미터 하나 뿐이라 {0}
    @ValueSource(strings = {"스트링", "말고도", "다른", "타입도", "됩니다"})
    @EmptySource //빈 값 하나를 추가
    @NullSource // null 값 하나를 추가
    void testWithParamNamedNull(String msg){
        System.out.println(msg);
    }

    @DisplayName("테스트 이름 변경")
    @ParameterizedTest(name = "{index} : {displayName} = {0}" ) //이름에 인덱스도 사용가능
    // 파라미터도 참조 가능.. 현재는 파라미터 하나 뿐이라 {0}
    @ValueSource(strings = {"스트링", "말고도", "다른", "타입도", "됩니다"})
    @NullAndEmptySource
    void testWithParamNamedNullAndEmpty(String msg){
        System.out.println(msg);
    }


 //----------------------------------------------------


    //파라미터를 받아서 객체에 매핑하기
    @DisplayName("테스트에 준 파라미터들로 객체 생성") //SimpleArgumentConverter 필요
    @ParameterizedTest(name = "{index} : {displayName} = {0}" ) //이름에 인덱스도 사용가능
    // 파라미터도 참조 가능.. 현재는 파라미터 하나 뿐이라 {0}
    @ValueSource(ints = {10, 20, 30})
    void testWithArgument(@ConvertWith(testClass03Converter.class) TestClass03 test03){
        System.out.println(test03.getLimit());
    }

    static class testClass03Converter extends SimpleArgumentConverter {
        // 반드시 static inner class 또는 public 으로 정의되어야 함
        // SimpleArgumentConverter 는 하나의 아규먼트인 경우에만 적용됨
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(TestClass03.class, targetType,"TestClass03으로만 변환 가능");
            return new TestClass03(Integer.parseInt(source.toString()));
        }
    }



    //파라미터를 받아서 객체에 매핑하기
    @DisplayName("테스트에 준 파라미터들로 애그리게이트 사용하여 객체 생성") //ArgumentsAccessor 필요
    @ParameterizedTest(name = "{index} : {displayName} = {0}" ) //이름에 인덱스도 사용가능
    // 파라미터도 참조 가능.. 현재는 파라미터 하나 뿐이라 {0}
    @CsvSource({"'이름1', 10", "'이름2', 20", "'이름3', 30"})
    void testWithAggregate(@AggregateWith(TestClass03Aggregator.class) TestClass03 testClass03){
        System.out.println(testClass03);
    }


    static class TestClass03Aggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new TestClass03(accessor.getString(0), accessor.getInteger(1));
        }
    }


}
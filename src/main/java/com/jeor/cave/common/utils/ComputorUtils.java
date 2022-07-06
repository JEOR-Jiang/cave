package com.jeor.cave.common.utils;

import javax.script.*;

/**
 * @Author jeor
 * @Date 2021/6/25 14:53
 * @Version 1.0
 */
public class ComputorUtils {
    public static void main(String[] args) {
        System.out.println("start Computor ...");

//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
//        Compilable compilable = (Compilable) engine;
//        Bindings bindings = engine.createBindings(); //Local级别的Binding
//        String script = "(1+0.1 * (F/100) * T)*P0"; //定义函数并调用
//        CompiledScript JSFunction = null; //解析编译脚本函数
//        try {
//            JSFunction = compilable.compile(script);
//            bindings.put("F", 2.5);
//            bindings.put("T", 30);
//            bindings.put("A", 100);
//            bindings.put("P0", 100);
//            Object result = JSFunction.eval(bindings);
//            System.out.println(result); //调用缓存着的脚本函数对象，Bindings作为参数容器传入
//
//            String script2 = "2**5"; //定义函数并调用
//            JSFunction = compilable.compile(script2);
////            bindings.put("^", "**");
//            Object result2 = JSFunction.eval(bindings);
//            System.out.println(result2);
//        } catch (javax.script.ScriptException e) {
//            e.printStackTrace();
//        }

//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("javascript");
//        engine.put("a", 4);
//        engine.put("b", 3);
//        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
//        try {
//            // 只能为Double，使用Float和Integer会抛出异常
//
//            Double result = (Double) engine.eval("a+b");
//
//            System.out.println("result = " + result);
//            engine.eval("c=a**b");
//            Double c = (Double)engine.get("c");
//            System.out.println("c = " + c);
//
//        } catch (ScriptException e) {
//            e.printStackTrace();
//
//        }
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Compilable compilable = (Compilable) engine;
        Bindings bindings = engine.createBindings(); //Local级别的Binding
        String script = "[年报和收入]*T"; //定义函数并调用
        CompiledScript JSFunction = null; //解析编译脚本函数
        try {
            JSFunction = compilable.compile(script);
            bindings.put("年报和收入", 2.5);
            bindings.put("T", 30);
            bindings.put("A", 100);
            bindings.put("P0", 100);
            Object result = JSFunction.eval(bindings);
            System.out.println(result); //调用缓存着的脚本函数对象，Bindings作为参数容器传入
        } catch (javax.script.ScriptException e) {
            e.printStackTrace();
        }
    }

}

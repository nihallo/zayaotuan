package com.apinception.apinception.util;

import java.lang.reflect.Method;

public class Eval {

    public static Object eval(String str) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("public class Temp");
        sb.append("{");
        sb.append(" public Object getObject()");
        sb.append(" {");
        sb.append(" " + str + "return new Object();");
        sb.append(" }");
        sb.append("}");
//调用自定义类加载器加载编译在内存中class文件
        Class clazz = new MyClassLoader().findClass(sb.toString());
        Method method = clazz.getMethod("getObject");
//通过反射调用方法
        return method.invoke(clazz.newInstance());
    }

    public static void main(String[] args) throws Exception
    {
        Object rval = eval("System.out.println();");
        System.out.println(rval);
    }
}

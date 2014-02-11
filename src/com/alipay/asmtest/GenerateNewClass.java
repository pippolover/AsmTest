/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.asmtest;

import java.lang.reflect.Method;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import com.alipay.asmtest.adapter.faultInjectAdapter;

/**
 * 
 * @author yimingwym 
 * @version $Id: GenerateNewClass.java, v 0.1 2014-2-10 ÏÂÎç1:32:55 yimingwym Exp $
 */
public class GenerateNewClass extends ClassLoader {
    public static void main(String[] args) throws Exception {
        String userName = "ee";
        ClassReader cr = new ClassReader("com.alipay.asmtest.beans.TestClass");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new faultInjectAdapter(cw, userName);

        cr.accept(classVisitor, ClassReader.SKIP_DEBUG);

        byte[] classFile = cw.toByteArray();

        /*        File file = new File("bin/com/alipay/asmtest/beans/TestClass.class");
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(classFile);
                stream.close();*/

        GenerateNewClass loader = new GenerateNewClass();
        Class example = loader.defineClass("com.alipay.asmtest.beans.TestClass", classFile, 0,
            classFile.length);
        Method method = example.getDeclaredMethod("sayName", null);
        method.invoke(example.newInstance(), null);

        Method method1 = example.getDeclaredMethod("sayHello", null);
        method1.invoke(example.newInstance(), null);
    }
}

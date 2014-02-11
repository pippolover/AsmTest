/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.asmtest.adapter;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import com.alipay.asmtest.beans.constants.ASMEXCEPTIONS;

/**
 * 
 * @author yimingwym 
 * @version $Id: AddSleepAdapter.java, v 0.1 2014-2-10 ����1:47:39 yimingwym Exp $
 */
public class faultInjectAdapter extends ClassVisitor {
    protected String userName;

    public faultInjectAdapter(ClassVisitor classvisitor, String userName) {
        super(ASM4, classvisitor);
        this.userName = userName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                                     String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);

        if (name.equalsIgnoreCase("sayName")) {
            MethodVisitor addSleepVisitor = new addSleepMethodAdapter(methodVisitor, userName);

            return addSleepVisitor;
        } else if (name.equalsIgnoreCase("sayHello")) {
            /**
             * ����Ҫ�޸�method�����throws Exception
             * ��Ϊģ���쳣ʱ�������throw ĳ��exception�������п϶��Ѿ������˴���exception
             * runtime Exception ���Բ�������throw
             */

            MethodVisitor throwExceptionVisitor = new throwExceptionAdapter(methodVisitor,
                ASMEXCEPTIONS.NULLPOINTEXCEPTION);

            return throwExceptionVisitor;
        } else {
            return methodVisitor;
        }

    }

    public static void main(String[] args) {
        System.out.println("aaa");
    }

}

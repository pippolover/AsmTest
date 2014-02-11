/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.asmtest.adapter;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 
 * @author yimingwym 
 * @version $Id: throwExceptionAdapter.java, v 0.1 2014-2-11 ÏÂÎç3:23:30 yimingwym Exp $
 */
public class throwExceptionAdapter extends MethodVisitor {

    public throwExceptionAdapter(MethodVisitor mv) {
        super(ASM4, mv);
    }

    @Override
    public void visitEnd() {
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/NullPointerException");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/NullPointerException", "<init>", "()V");
        mv.visitInsn(Opcodes.ATHROW);
    }

}

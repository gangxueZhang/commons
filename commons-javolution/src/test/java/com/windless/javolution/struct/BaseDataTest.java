package com.windless.javolution.struct;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.Assert.*;

public class BaseDataTest {

    @Test
    public void setUp(){
        //这里模拟了一下数据的输入
        byte[] data = new byte[221];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        ByteBuffer b = ByteBuffer.wrap(data);
        //小端
        b.order(ByteOrder.LITTLE_ENDIAN);
        BaseData info=new BaseData ();
        //设置byte数组
        info.setByteBuffer(b, 0);
        //打印输出，这里输出byte字符串
        System.out.println(info);
        //如果想打印每一个项，直接
        System.out.println(info.unsigned16);

        System.out.println("#########################");
    }

}

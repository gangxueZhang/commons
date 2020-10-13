package com.windless.javolution.struct;

import javolution.io.Struct;

import java.nio.ByteOrder;

/**
 * 类名: BaseData
 * 功能描述: 基础数据类，用于测试数据换
 * 代码版本: Since 1.0
 * @author zhanggangxue
 */
public class BaseData extends Struct {
    Unsigned8 unsigned8=new Unsigned8();
    Unsigned16 unsigned16=new Unsigned16();
    Unsigned32 Unsigned32=new Unsigned32();
    Signed8 signed8 =new Signed8();
    Signed16 signed16 =new Signed16();
    Signed32 signed32 =new Signed32();
    Signed64 signed64 =new Signed64();
    UTF8String utf8String=new UTF8String(10);
    /**
     * 方法名: isPacked
     * 功能描述: 一定要设置为true，不然会出现对齐的问题
     *         指示此结构是否已打包（可配置）。 默认情况下，结构的成员在对应于成员基本类型的边界上对齐； 必要时执行填充。
     *     该指令不被内部结构继承。 子类可以通过重写此方法来更改包装指令。
     * @return boolean true：忽略字长要求；false：否则（默认）。
     */
    @Override
    public boolean isPacked() {
        return true;
    }

    /**
     * 方法名: byteOrder
     * 功能描述:返回此结构的字节顺序(可配置)，字节顺序由内部结构继承。BIG_ENDIAN：表示按照此顺序，多字节值的字节按从最高有效到最低有效的顺序排列。
     *     LITTLE_ENDIAN：表示按此顺序，多字节值的字节从最低有效到最高有效的顺序排列。
     *        设置为小端数据
     * @return ByteOrder BIG_ENDIAN：大端；LITTLE_ENDIAN：小端。
     */
    @Override
    public ByteOrder byteOrder() {
        return ByteOrder.LITTLE_ENDIAN;
    }

}

# STRUCT说明
## 简介
* 等效于C/C++结构；该类赋予Java类和C/C++结构之间的互操作性。  
* 与C/C++不同，Java对象的存储布局不是由编译器确定的。内存中对象的布局将延迟运行时间，并由解释器（或即时编译器）确定。
这种方法允许动态加载和绑定。但也使得与C/C++代码的连接变得困难。因此，此类的内存​​布局由Struct成员的初始化顺序定义，
并且遵循与C/C++结构相同的wordSize规则。  
* 此类（以及Union子类）有助于：  
  * Java应用程序和本机库之间的内存共享。  
  * 流的直接编码/解码，其结构由旧式C/C++代码定义。  
  * Java对象的序列化/反序列化（完全控制，例如没有类头）  
  * 将Java对象映射到物理地址（使用JNI）。  
* 由于其一对一映射，使用简单的文本宏将C头文件（例如OpenGL绑定）转换为Java Struct / Union相对容易。  
## 数据类型说明(以Struct内部类的方式定义) 
 类名 | 类说明 
 :--- | :--- 
 BitField：| 此类表示任意大小（无符号）的位字段，没有字大小限制（它们可以跨越字边界）
Bool：| 此类表示8位布尔值，其中true表示为1，false表示为0。
Enum8<T extends Enum<T>>：| 此类表示8位Enum。
Enum16<T extends Enum<T>>：| 此类表示16位Enum。
Enum32<T extends Enum<T>>：| 此类表示32位Enum。
Enum64<T extends Enum<T>>：| 此类表示64位Enum。
Float32：| 此类表示32位浮点数（C/C++ / Java浮点数）。
Float64：| 此类表示64位浮点数（C/C++ / Java双精度）。
Member：| 此内部类表示所有Struct成员的基类。
Reference32\<S extends Struct>：| 此类表示对Struct对象的32位引用（C/C++指针）（其他类型可能需要Struct包装器）。
Reference64\<S extends Struct>：| 此类表示对Struct对象的64位引用（C/C++指针）（其他类型可能需要Struct包装器）。
Signed8：| 此类表示8位有符号整数。
Signed16：| 此类表示16位有符号整数。
Signed32：| 此类表示32位有符号整数。
Signed64：| 此类表示64位有符号整数。
Unsigned8：| 此类表示8位无符号整数。
Unsigned16：| 此类表示16位无符号整数。
Unsigned32：| 此类表示32位无符号整数。
UTF8String：| 此类表示UTF-8字符串，以null终止（出于C/C++兼容性）

## 字段
修饰符 | 字段类型 | 字段 | 字段说明
 --- | --- | --- | ---
 static | LocalContext.Parameter<Integer> | MAXIMUM_ALIGNMENT | 可配置，以字节为单位保留最大wordSize（默认值为4）。 值应大于或等于1。
## 方法
```text
方法名：address()
返回类型：long
方法说明：返回此结构地址（如果平台支持）。 此方法允许从其他结构引用结构（例如，指针）。

方法名：array(M[] arrayMember)
返回类型：protected <M extends Struct.Member>
方法说明：定义指定的数组成员。 对于预定义成员，该数组在为空时填充；否则，为空。 自定义成员应使用文字（填充）数组。

方法名：array(M[][] arrayMember)
返回类型：protected <M extends Struct.Member>
方法说明：定义指定的二维数组成员。 对于预定义成员，该数组在为空时填充；否则，为空。 自定义成员应使用文字（填充）数组。

方法名：array(M[][][] arrayMember)
返回类型：protected <M extends Struct.Member>
方法说明：定义指定的三维数组成员。 对于预定义成员，该数组在为空时填充；否则，为空。 自定义成员应使用文字（填充）数组。

方法名：array(S[] structs)
返回类型：protected <S extends Struct>
方法说明：将指定的结构数组定义为内部结构。 如有必要，使用struct组件默认构造函数（必须为public）填充该数组。

方法名：array(S[][] structs)
返回类型：protected <S extends Struct>
方法说明：将指定的二维结构数组定义为内部结构。 如有必要，使用struct组件默认构造函数（必须为public）填充该数组。

方法名：array(S[][][] structs)
返回类型：protected <S extends Struct>
方法说明：将指定的三维结构数组定义为内部结构。 如有必要，使用struct组件默认构造函数（必须为public）填充该数组。

方法名：array(Struct.UTF8String[] array, int stringLength)
返回类型：protected Struct.UTF8String[]
方法说明：定义指定的UTF-8字符串数组，所有字符串均具有指定的长度（便捷方法）。

方法名：byteOrder()
返回类型：ByteOrder
方法说明：返回此结构的字节顺序（可配置）。 字节顺序由内部结构继承。 子类可以通过重写此方法来更改字节顺序。

方法名：getByteBuffer()
返回类型：ByteBuffer
方法说明：返回此结构的字节缓冲区。 如果未设置任何直接缓冲区，则此方法将分配一个新的直接缓冲区。在此结构中可以看到对缓冲区内容的更改，反之亦然。内部结构的缓冲区与其父结构相同。如果未设置字节缓冲区，则将分配一个直接缓冲区，其容量等于此结构的大小。 

方法名：getByteBufferPosition()
返回类型：int
方法说明：返回此结构在其关联的字节缓冲区中的绝对字节位置。

方法名：inner(S struct)
返回类型：protected <S extends Struct>
方法说明：将指定的结构定义为该结构的内部。

方法名：isPacked()
返回类型：boolean
方法说明：指示此结构是否已打包（可配置）。 默认情况下，结构的成员在对应于成员基本类型的边界上对齐； 必要时执行填充。 该指令不被内部结构继承。 子类可以通过重写此方法来更改包装指令。

方法名：isUnion()
返回类型：boolean
方法说明：指示此结构的成员是否映射到内存中的相同位置（默认为false）。 此方法对于使用新成员类型扩展Struct以便从这些新结构创建联合的应用程序很有用。

方法名：outer()
返回类型：Struct
方法说明：返回此结构的外部，如果此结构不是内部结构，则返回null。

方法名：read(InputStream in)
返回类型：int
方法说明：从指定的输入流中读取此结构（使用流I/O时的便捷方法）。 为了获得更好的性能，建议使用块I/O（例如java.nio.channels。*）。 当并非所有数据都可从输入流获得时，此方法将正常运行。 当输入流与诸如TCP连接之类的内容相关联时，不完整的数据非常普遍。 在这些情况下，典型的使用模式是重复调用read()直到接收到整个消息。

方法名：readBits(int bitOffset, int bitSize)
返回类型：long
方法说明：从该Struct中读取指定的位，作为长整数（有符号）。 

方法名：setByteBuffer(ByteBuffer byteBuffer, int position)
返回类型：Struct
方法说明：

方法名：setByteBufferPosition(int position)
返回类型：Struct
方法说明：设置此结构在其字节缓冲区中的字节位置。

方法名：size()
返回类型：int
方法说明：返回此结构的大小（以字节为单位）。 大小包括尾部填充，以满足结构字大小要求（由其成员的最大字大小定义）。

方法名：toString()
返回类型：String
方法说明：以其构成字节（十六进制）的形式返回此结构的String表示形式。

方法名：write(OutputStream out)
返回类型：void
方法说明：将此结构写入指定的输出流（使用流I/O时的便捷方法）。 为了获得更好的性能，建议使用块I/O（例如：java.nio.channels.*）。

方法名：writeBits(long value, int bitOffset, int bitSize)
返回类型：void
方法说明：将指定的位写入此Struct。
```



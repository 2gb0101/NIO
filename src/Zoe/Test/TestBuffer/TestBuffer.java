package Zoe.Test.TestBuffer;

import java.nio.ByteBuffer;

import org.junit.Test;

public class TestBuffer {
	//测试capacity、limit、position
	@Test
	public void test1(){
		String str = "abcde";
		//1. 分配一个指定大小的缓冲区,allocate()的参数就是要指定的大小
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		System.out.println("-----------------allocate()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//2. 利用 put() 存入数据到缓冲区中，缓冲区默认的状态是可写的
		buf.put(str.getBytes());
		
		System.out.println("-----------------put()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//3. 由于缓冲区默认的状态是可写的，现在我们要读取数据，所以把状态切换到读取状态
		buf.flip();
		
		System.out.println("-----------------flip()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//4. 利用 get() 读取缓冲区中的数据
		byte[] dst = new byte[buf.limit()];
		//在get数据的时候，position会依次往下移动，get一次position移动一下
		buf.get(dst);
		//把byte字节数组转为String
		System.out.println(new String(dst, 0, dst.length));
		
		System.out.println("-----------------get()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//5. rewind() : 可重复读，把position的位置恢复到使用get()方法前
		buf.rewind();
		
		System.out.println("-----------------rewind()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//6. clear() : 清空缓冲区，回到最初状态(恢复到可写状态)，但是缓冲区中的数据依然存在
		//但是position、limit、capacity那几个指针的值，回到了最初状态
		//那时候就没办法正确的读取数据的内容了（意思是可能读取的长度就不正确了）
		//要正确读取数据的话，要重新切换为读取状态
		buf.clear();
		
		System.out.println("-----------------clear()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
	}

	@Test
	//测试mark
	public void test2(){
		String str = "abcde";
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		buf.put(str.getBytes());
		
		buf.flip();
		
		byte[] dst = new byte[buf.limit()];
		//数据读取到dst中，读取两个数据
		buf.get(dst, 0, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println(buf.position());
		
		//mark() : 标记
		buf.mark();
		
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(buf.position());
		
		//reset() : 恢复到 mark 的位置
		buf.reset();
		System.out.println(buf.position());
		
		//判断缓冲区中是否还有剩余数据
		if(buf.hasRemaining()){
			
			//获取缓冲区中可以操作的数量
			System.out.println(buf.remaining());
		}
	}
	
	@Test
	public void test3(){
		//分配直接缓冲区
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		
		//isDirect():判断当前缓冲区是否为直接缓冲区
		System.out.println(buf.isDirect());
	}
}

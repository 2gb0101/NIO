package Zoe.Test.TestBuffer;

import java.nio.ByteBuffer;

import org.junit.Test;

public class TestBuffer {
	//����capacity��limit��position
	@Test
	public void test1(){
		String str = "abcde";
		//1. ����һ��ָ����С�Ļ�����,allocate()�Ĳ�������Ҫָ���Ĵ�С
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		System.out.println("-----------------allocate()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//2. ���� put() �������ݵ��������У�������Ĭ�ϵ�״̬�ǿ�д��
		buf.put(str.getBytes());
		
		System.out.println("-----------------put()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//3. ���ڻ�����Ĭ�ϵ�״̬�ǿ�д�ģ���������Ҫ��ȡ���ݣ����԰�״̬�л�����ȡ״̬
		buf.flip();
		
		System.out.println("-----------------flip()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//4. ���� get() ��ȡ�������е�����
		byte[] dst = new byte[buf.limit()];
		//��get���ݵ�ʱ��position�����������ƶ���getһ��position�ƶ�һ��
		buf.get(dst);
		//��byte�ֽ�����תΪString
		System.out.println(new String(dst, 0, dst.length));
		
		System.out.println("-----------------get()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//5. rewind() : ���ظ�������position��λ�ûָ���ʹ��get()����ǰ
		buf.rewind();
		
		System.out.println("-----------------rewind()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//6. clear() : ��ջ��������ص����״̬(�ָ�����д״̬)�����ǻ������е�������Ȼ����
		//����position��limit��capacity�Ǽ���ָ���ֵ���ص������״̬
		//��ʱ���û�취��ȷ�Ķ�ȡ���ݵ������ˣ���˼�ǿ��ܶ�ȡ�ĳ��ȾͲ���ȷ�ˣ�
		//Ҫ��ȷ��ȡ���ݵĻ���Ҫ�����л�Ϊ��ȡ״̬
		buf.clear();
		
		System.out.println("-----------------clear()----------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
	}

	@Test
	//����mark
	public void test2(){
		String str = "abcde";
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		buf.put(str.getBytes());
		
		buf.flip();
		
		byte[] dst = new byte[buf.limit()];
		//���ݶ�ȡ��dst�У���ȡ��������
		buf.get(dst, 0, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println(buf.position());
		
		//mark() : ���
		buf.mark();
		
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(buf.position());
		
		//reset() : �ָ��� mark ��λ��
		buf.reset();
		System.out.println(buf.position());
		
		//�жϻ��������Ƿ���ʣ������
		if(buf.hasRemaining()){
			
			//��ȡ�������п��Բ���������
			System.out.println(buf.remaining());
		}
	}
	
	@Test
	public void test3(){
		//����ֱ�ӻ�����
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		
		//isDirect():�жϵ�ǰ�������Ƿ�Ϊֱ�ӻ�����
		System.out.println(buf.isDirect());
	}
}

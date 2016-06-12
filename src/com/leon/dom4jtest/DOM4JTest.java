package com.leon.dom4jtest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.leon.entity.Book;

public class DOM4JTest {
	public static ArrayList<Book> bookList=new ArrayList<Book>(); 
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		File file = new File("books.xml");
		try {
			Document document = reader.read(file);
			Element rootElement = document.getRootElement();
			@SuppressWarnings("rawtypes")
			Iterator it = rootElement.elementIterator();
			// 遍历迭代器 获取根节点中的信息
			while (it.hasNext()) {
				System.out.println("开始解析某一项");
				Book bookObj = new Book();
				Element book = (Element) it.next();
				@SuppressWarnings("unchecked")
				List<Attribute> attrs = book.attributes();
				for (Attribute element : attrs) {
					System.out.println("属性名：" + element.getName() + "---属性值：" + element.getValue());
					if (element.getName().equals("id")) {
						bookObj.setId(element.getValue());
					}
					@SuppressWarnings("rawtypes")
					Iterator itt = book.elementIterator();
					while (itt.hasNext()) {
						Element bookChild = (Element) itt.next();
						System.out.print("节点名：" + bookChild.getName());
						System.out.println("---节点值：" + bookChild.getStringValue());
						if (bookChild.getName().equals("name")) {
							bookObj.setName(bookChild.getStringValue());
						}
						if (bookChild.getName().equals("author")) {
							bookObj.setAuthor(bookChild.getStringValue());
						}
						if (bookChild.getName().equals("year")) {
							bookObj.setYear(bookChild.getStringValue());
						}
						if (bookChild.getName().equals("price")) {
							bookObj.setPrice(bookChild.getStringValue());
						}
						if (bookChild.getName().equals("language")) {
							bookObj.setLanguage(bookChild.getStringValue());
						}
					}
				}
				DOM4JTest.bookList.add(bookObj);
				bookObj=null;
				System.out.println("结束解析某一项");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		for (Book book2 : DOM4JTest.bookList) {
			System.out.println(book2.toString());
		}
	}
}

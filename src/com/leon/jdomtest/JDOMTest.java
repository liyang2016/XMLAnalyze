package com.leon.jdomtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.leon.entity.Book;

public class JDOMTest {
	public static ArrayList<Book> bookList = new ArrayList<Book>();

	public static void main(String[] args) throws FileNotFoundException {
		// 创建一个SAXBuilder对象
		SAXBuilder builder = new SAXBuilder();
		// 创建一个输入流
		InputStream in = new FileInputStream("books.xml");
		try {
			Document document = builder.build(in);
			Element rootElement = document.getRootElement();
			List<Element> bookList = rootElement.getChildren();
			for (Element element : bookList) {
				System.out.println("开始解析某一本书");
				Book bookObj = new Book();
				List<Attribute> attrs = element.getAttributes();
				for (Attribute attribute : attrs) {
					String atrrName = attribute.getName();
					String atrrValue = attribute.getValue();
					if (atrrName.equals("id")) {
						bookObj.setId(atrrValue);
					}
					System.out.println("属性名：" + atrrName + "----属性值：" + atrrValue);
				}
				// 对子节点和子节点值进行遍历
				List<Element> bookChildren = element.getChildren();
				for (Element child : bookChildren) {
					System.out.print("节点名：" + child.getName());
					System.out.println("----节点值：" + child.getValue());
					if (child.getName().equals("name")) {
						bookObj.setName(child.getValue());
					}
					if (child.getName().equals("author")) {
						bookObj.setAuthor(child.getValue());
					}
					if (child.getName().equals("year")) {
						bookObj.setYear(child.getValue());
					}
					if (child.getName().equals("price")) {
						bookObj.setPrice(child.getValue());
					}
					if (child.getName().equals("language")) {
						bookObj.setLanguage(child.getValue());
					}
				}
				JDOMTest.bookList.add(bookObj);
				bookObj = null;
				System.out.println("结束解析某一本书");
				for (Book book : JDOMTest.bookList) {
					System.out.println(book.toString());
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

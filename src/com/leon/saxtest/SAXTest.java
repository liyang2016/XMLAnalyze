package com.leon.saxtest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.leon.entity.Book;

public class SAXTest {
	public static void main(String[] args) {
		// 获取一个SAXParserFactor的实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			// 创建一个Handler对象
			SAXParserHandler handler = new SAXParserHandler();
			parser.parse(new File("books.xml"), handler);
			//检测是否保存到对象集合
			ArrayList<Book> bookList=handler.getBookList();
			for (Book book : bookList) {
				System.out.println(book.toString());
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

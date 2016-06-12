package com.leon.saxtest;


import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.leon.entity.Book;

public class SAXParserHandler extends DefaultHandler {
	String value=null;
	Book bookObj=null;
	private ArrayList<Book> bookList=new ArrayList<Book>();
	
	public ArrayList<Book> getBookList() {
		return bookList;
	}

	/**
	 * 用来遍历XML文件的开始标签
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("book")) {
			bookObj=new Book();
			System.out.println("====开始遍历某一本书====");
			// 已知book元素属性的名称和个数
			// String value=attributes.getValue("id");
			// System.out.println(value);
			// 位置属性的名称和个数
			int num = attributes.getLength();
			for (int i = 0; i < num; i++) {
				System.out.println("属性名为：" + attributes.getQName(i) + "---属性值为：" + attributes.getValue(i));
				if(attributes.getQName(i).equals("id")){
					bookObj.setId(attributes.getValue(i));
				}
			}
		}else if(!qName.equals("bookstore")){
			System.out.print("节点名是："+qName);
		}
	}

	/**
	 * 用来遍历XML文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(qName.equals("book")){
			bookList.add(bookObj);
			bookObj=null;
			System.out.println("====结束遍历某一本书====");			
		}else if(qName.equals("name")){
			bookObj.setName(value);
		}else if(qName.equals("author")){
			bookObj.setAuthor(value);
		}else if(qName.equals("year")){
			bookObj.setYear(value);
		}else if(qName.equals("price")){
			bookObj.setPrice(value);
		}else if(qName.equals("language")){
			bookObj.setLanguage(value);
		}
	}

	/**
	 * 用来标志解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("SAX解析开始");
	}

	/**
	 * 用来标志解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("SAX解析结束");
	}

	/**
	 * 输出节点的值
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		value=new String(ch, start, length);
		if(!value.trim().equals("")){
			System.out.println("----节点值为："+value);			
		}
	}
	
}

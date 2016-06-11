package com.leon.DOMTest;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.leon.entity.Book;

@SuppressWarnings("unused")
public class DOMTest {
	public static ArrayList<Book> bookList=new ArrayList<Book>(); 
	public static void main(String[] args) {
		String XMLfile = "books.xml";
		//创建DOMTest对象
		DOMTest domTest=new DOMTest();
		//创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//创建一个DocumentBuilder的对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document=db.parse(XMLfile);
			NodeList bookList=
					document.getElementsByTagName("book");
			System.out.println("一共有"+bookList.getLength()+"本书");
			//遍历每一个book节点
			for(int i=0;i<bookList.getLength();i++){
				//创建book对象
				Book bookObj=new Book();
				System.out.println("开始遍历第"+(i+1)+"本书----");
				Node book=bookList.item(i);
				NamedNodeMap attrs=book.getAttributes();
				//遍历book的属性
				for(int j=0;j<attrs.getLength();j++){
					Node attr=attrs.item(j);
					System.out.print("属性名："
							+attr.getNodeName()+"----");
					System.out.println("属性值："
							+attr.getNodeValue());
					bookObj.setId(attr.getNodeValue());
				}
				
				//已知book节点有且只有一个节点，节点名已知
				//可以使用下面的方法解析
//				Element book=(Element) bookList.item(i);
//				String attrValue=book.getAttribute("id");
//				System.out.println("id属性值为："+attrValue);
				
				//解析book节点的子节点
				NodeList childNodes=book.getChildNodes();
				for(int k=0;k<childNodes.getLength();k++){
					//区分#text和Element_Node节点
//					System.out.println(
//							childNodes.item(k).getNodeName());
					if(childNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
						System.out.println(childNodes.item(k).getNodeName());
//						System.out.println(
//								childNodes.item(k).getFirstChild().getNodeValue());
						System.out.println(childNodes.item(k).getTextContent());
						if(childNodes.item(k).getNodeName()=="name"){
							bookObj.setName(childNodes.item(k).getTextContent());
						}
						if(childNodes.item(k).getNodeName()=="author"){
							bookObj.setAuthor(childNodes.item(k).getTextContent());
						}
						if(childNodes.item(k).getNodeName()=="year"){
							bookObj.setYear(childNodes.item(k).getTextContent());
						}
						if(childNodes.item(k).getNodeName()=="price"){
							bookObj.setPrice(childNodes.item(k).getTextContent());
						}
						if(childNodes.item(k).getNodeName()=="language"){
							bookObj.setLanguage(childNodes.item(k).getTextContent());
						}
					}
				}
				System.out.println("结束遍历第"+(i+1)+"本书----");
				DOMTest.bookList.add(bookObj);
				bookObj=null;
			}
			
			//XML内容存储到ArrayList中测试
			for (Book book2 : DOMTest.bookList) {
				System.out.println(book2.toString());
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}

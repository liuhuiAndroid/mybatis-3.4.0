package com.lh.demo.test;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by lh on 2018/5/8.
 * DOM解析方式,结合使用XPath解析XML配置文件
 */
public class XPathTest {

    @Test
    public void testXPath() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // 开启验证
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);

        // 创建DocumentBuilder
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        // 设置异常处理对象
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("error:"+exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("fatalError:"+exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("Warn:"+exception.getMessage());
            }
        });

        Document document = builder.parse("src/test/java/inventory.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        //如果重复执行查询，建议先进行编译，然后进行查询，这样性能会好一点
        XPathExpression expression = xPath.compile("//book[author='Neal Stephenson']/title/text()");
        //evaluate()方法的第二个参数，指定了XPath表达式查找的结果类型，在XPathConstants中提供了NODESET、BOOLEAN、NUMBER、STRING、NODE
        Object result = expression.evaluate(document, XPathConstants.NODESET);
        System.out.println("===============================");
        System.out.println("查询作者为Neal Stephenson的图书的标题：");
        NodeList nodeList = (NodeList) result;
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeValue());
        }

        System.out.println("===============================");
        System.out.println("查询1997年之后的图书的标题：");
        nodeList = (NodeList) xPath.evaluate("//book[@year>1997]/title/text()",document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeValue());
        }

        System.out.println("===============================");
        System.out.println("查询1997年之后的图书的属性和标题：");
        nodeList = (NodeList) xPath.evaluate("//book[@year>1997]/@* | //book[@year>1997]//title/text()",document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeValue());
        }

        System.out.println("===============================");
    }

}

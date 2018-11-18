# SimulationFang

#### 项目介绍
SimulationFang Java爬虫 Htmlunit

#### 软件架构

HtmlUnit is a "GUI-Less browser for Java programs". It models HTML documents and provides an API that allows you to invoke pages, fill out forms, click links, etc... just like you do in your "normal" browser.

It has fairly good JavaScript support (which is constantly improving) and is able to work even with quite complex AJAX libraries, simulating Chrome, Firefox or Internet Explorer depending on the configuration used.

It is typically used for testing purposes or to retrieve information from web sites.

HtmlUnit is not a generic unit testing framework. It is specifically a way to simulate a browser for testing purposes and is intended to be used within another testing framework such as JUnit or TestNG. Refer to the document "Getting Started with HtmlUnit" for an introduction.

HtmlUnit is used as the underlying "browser" by different Open Source tools like Canoo WebTest, JWebUnit, WebDriver, JSFUnit, WETATOR, Celerity, Spring MVC Test HtmlUnit, ...

HtmlUnit was originally written by Mike Bowler of Gargoyle Software and is released under the Apache 2 license. Since then, it has received many contributions from other developers, and would not be where it is today without their assistance.


#### 安装教程
Introduction
The dependencies page lists all the jars that you will need to have in your classpath.

The class com.gargoylesoftware.htmlunit.WebClient is the main starting point. This simulates a web browser and will be used to execute all of the tests.

Most unit testing will be done within a framework like JUnit so all the examples here will assume that we are using that.

In the first sample, we create the web client and have it load the homepage from the HtmlUnit website. We then verify that this page has the correct title. Note that getPage() can return different types of pages based on the content type of the returned data. In this case we are expecting a content type of text/html so we cast the result to an com.gargoylesoftware.htmlunit.html.HtmlPage.


```
@Test
public void homePage() throws Exception {
    try (final WebClient webClient = new WebClient()) {
        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

        final String pageAsXml = page.asXml();
        Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

        final String pageAsText = page.asText();
        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
    }
}
```

Submitting a form
Frequently we want to change values in a form and submit the form back to the server. The following example shows how you might do this.


```
@Test
public void submittingForm() throws Exception {
    try (final WebClient webClient = new WebClient()) {

        // Get the first page
        final HtmlPage page1 = webClient.getPage("http://some_url");

        // Get the form that we are dealing with and within that form, 
        // find the submit button and the field that we want to change.
        final HtmlForm form = page1.getFormByName("myform");

        final HtmlSubmitInput button = form.getInputByName("submitbutton");
        final HtmlTextInput textField = form.getInputByName("userid");

        // Change the value of the text field
        textField.type("root");

        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
    }
}
```

Imitating a specific browser
Often you will want to simulate a specific browser. This is done by passing a com.gargoylesoftware.htmlunit.BrowserVersion into the WebClient constructor. Constants have been provided for some common browsers but you can create your own specific version by instantiating a BrowserVersion.

```

@Test
public void homePage_Firefox() throws Exception {
    try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52)) {
        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
    }
}
```

Specifying this BrowserVersion will change the user agent header that is sent up to the server and will change the behavior of some of the JavaScript.

Finding a specific element
Once you have a reference to an HtmlPage, you can search for a specific HtmlElement by one of 'get' methods, or by using XPath or CSS selectors.

Traversing the DOM tree
Below is an example of finding a 'div' by an ID, and getting an anchor by name:


```
@Test
public void getElements() throws Exception {
    try (final WebClient webClient = new WebClient()) {
        final HtmlPage page = webClient.getPage("http://some_url");
        final HtmlDivision div = page.getHtmlElementById("some_div_id");
        final HtmlAnchor anchor = page.getAnchorByName("anchor_name");
    }
}
```

A simple way for finding elements might be to find all elements of a specific type.


```
 @Test
 public void getElements() throws Exception {
     try (final WebClient webClient = new WebClient()) {
         final HtmlPage page = webClient.getPage("http://some_url");
         NodeList inputs = page.getElementsByTagName("input");
         final Iterator<E> nodesIterator = nodes.iterator();
         // now iterate
     }
 }
```

There is rich set of methods usable to locate page elements e.g.

HtmlPage.getAnchors(); HtmlPage.getAnchorByHref(String); HtmlPage.getAnchorByName(String); HtmlPage.getAnchorByText(String)
HtmlPage.getElementById(String); HtmlPage.getElementsById(String); HtmlPage.getElementsByIdAndOrName(String);
HtmlPage.getElementByName(String); HtmlPage.getElementsByName(String)
HtmlPage.getFormByName(String); HtmlPage.getForms()
HtmlPage.getFrameByName(String); HtmlPage.getFrames()
You can also start searching from the document element (HtmlPage.getDocumentElement()) and then traverse the dom tree

HtmlElement.getElementsByAttribute(String, String, String)
DomElement.getElementsByTagName(String); DomElement.getElementsByTagNameNS(String, String)
DomElement.getChildElements(); DomElement.getChildElementCount()
DomElement.getFirstElementChild(); DomElement.getLastElementChild()
HtmlElement.getEnclosingElement(String); HtmlElement.getEnclosingForm()
DomNode.getChildNodes(); DomNode.getChildren(); DomNode.getDescendants(); DomNode.getDomElementDescendants(); DomNode.getFirstChild(); DomNode.getHtmlElementDescendants() DomNode.getLastChild(); DomNode.getNextElementSibling(); DomNode.getNextSibling(); DomNode.getPreviousElementSibling(); getPreviousSibling()
XPath queries
XPath is the suggested way for more complex searches, a brief tutorial can be found in W3Schools


```
@Test
public void xpath() throws Exception {
    try (final WebClient webClient = new WebClient()) {
        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

        //get list of all divs
        final List<?> divs = page.getByXPath("//div");

        //get div which has a 'name' attribute of 'John'
        final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
    }
}
```

CSS Selectors
You can also use CSS selectors


```
@Test
public void cssSelector() throws Exception {
    try (final WebClient webClient = new WebClient()) {
        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

        //get list of all divs
        final DomNodeList<DomNode> divs = page.querySelectorAll("div");
        for (DomNode div : divs) {
            ....
        }

        //get div which has the id 'breadcrumbs'
        final DomNode div = page.querySelector("div#breadcrumbs");
    }
}
```

Using a proxy server
The last WebClient constructor allows you to specify proxy server information in those cases where you need to connect through one.

```

@Test
public void homePage_proxy() throws Exception {
    try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "myproxyserver", myProxyPort)) {

        //set proxy username and password 
        final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
        credentialsProvider.addCredentials("username", "password");

        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
    }
}
Specifying this BrowserVersion will change the user agent header that is sent up to the server and will change the behavior of some of the JavaScript.

#### 使用说明

1. xxxx
```

2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
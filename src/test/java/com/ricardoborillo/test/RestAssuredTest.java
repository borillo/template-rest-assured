package com.ricardoborillo.test;

import com.jayway.restassured.RestAssured;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly.web.GrizzlyWebTestContainerFactory;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.util.Log4jConfigListener;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest extends JerseyTest
{
    private WebResource resource;

    public RestAssuredTest()
    {
        super(new WebAppDescriptor.Builder()
                .contextParam("contextConfigLocation", "classpath:applicationContext.xml")
                .contextParam("log4jConfigLocation", "src/main/webapp/WEB-INF/log4j.properties")
                .contextParam("webAppRootKey", "template-rest-assured.root")
                .contextListenerClass(Log4jConfigListener.class)
                .contextListenerClass(ContextLoaderListener.class)
                .requestListenerClass(RequestContextListener.class)
                .servletClass(SpringServlet.class).build());

        this.resource = resource();

        RestAssured.port = this.resource.getURI().getPort();
    }

    @Override
    protected TestContainerFactory getTestContainerFactory()
    {
        return new GrizzlyWebTestContainerFactory();
    }

    @Test
    public void test()
    {
        expect().body("id", equalTo(1), "name", equalTo("Ricardo Borillo"))
                .when().get("/users/1");
    }
}
